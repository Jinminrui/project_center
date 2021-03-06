package com.jmr.projectcenter.service.sprint;

import com.alibaba.fastjson.JSON;
import com.jmr.projectcenter.dao.sprint.SprintMapper;
import com.jmr.projectcenter.domain.dto.sprint.SprintDTO;
import com.jmr.projectcenter.domain.entity.activity.Activity;
import com.jmr.projectcenter.domain.entity.sprint.Sprint;
import com.jmr.projectcenter.service.project.ProjectService;
import com.jmr.projectcenter.service.rocketmq.RocketMQProducerService;
import com.jmr.projectcenter.service.task.TaskService;
import com.jmr.projectcenter.service.user.UserService;
import com.jmr.projectcenter.utils.UUIDOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SprintService {
    private final SprintMapper sprintMapper;
    private final TaskService taskService;
    private final UUIDOperator uuidOperator;
    private final RocketMQProducerService rocketMQProducerService;
    private final UserService userService;
    private final ProjectService projectService;

    public Sprint findById(String pkId) {
        return sprintMapper.selectByPrimaryKey(pkId);
    }

    public List<SprintDTO> list(String projectId) {
        return sprintMapper.getSprintDetailList(projectId);
    }

    public String genSprintCreateTitle (String operator, String projectName, String sprintName) {
        return operator + " 在项目 " + projectName + " 创建迭代 " + sprintName;
    }

    public int create(Sprint sprint) {
        sprint.setPkId(uuidOperator.getUUid());
        sprint.setCreateTime(new Date());
        sprint.setUpdateTime(new Date());
        int insertResult =  sprintMapper.insertSelective(sprint);
        if(insertResult == 1) {
            String activityId = uuidOperator.getUUid();
            Activity activity = Activity.builder()
                    .pkId(activityId)
                    .action("create-sprint")
                    .creatorId(sprint.getCreatorId())
                    .teamId(sprint.getTeamId())
                    .projectId(sprint.getProjectId())
                    .title(genSprintCreateTitle(
                            userService.getUserInfo(sprint.getCreatorId()).getUsername(),
                            projectService.findById(sprint.getProjectId(), sprint.getCreatorId()).getName(),
                            sprint.getTitle()))
                    .createTime(new Date())
                    .updateTime(new Date())
                    .build();
            String msg = JSON.toJSONString(activity);
            rocketMQProducerService.sendMsg(msg,activityId);
            return 1;
        } else {
            return 0;
        }
    }

    public int delete(String pkId) {
        int result1 = taskService.setSprintDefault(pkId);
        int result2 = sprintMapper.deleteByPrimaryKey(pkId);
        log.info(String.valueOf(result1));
        if(result2 == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public int update(Sprint sprint) {
        sprint.setUpdateTime(new Date());
        return sprintMapper.updateByPrimaryKeySelective(sprint);
    }

    public int start(Sprint sprint) {
        sprint.setStatus(2);
        return sprintMapper.updateByPrimaryKeySelective(sprint);
    }

    public int complete(String id) {
        if(sprintMapper.getUnDoneTaskNum(id) != 0) {
            return 2;
        }
        Sprint sprint = Sprint.builder().pkId(id).status(3).build();
        return sprintMapper.updateByPrimaryKeySelective(sprint);
    }

    public List<Sprint> getFinishedSprints(String projectId) {
        Example example = new Example(Sprint.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", 3);
        criteria.andEqualTo("projectId",projectId);
        return sprintMapper.selectByExample(example);
    }
}
