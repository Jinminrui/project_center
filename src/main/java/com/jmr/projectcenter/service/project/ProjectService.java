package com.jmr.projectcenter.service.project;

import com.alibaba.fastjson.JSON;
import com.jmr.projectcenter.dao.project.ProjectMapper;
import com.jmr.projectcenter.dao.user_project_relation.UserProjectRelationMapper;
import com.jmr.projectcenter.domain.dto.project.DeleteProjectRequestDTO;
import com.jmr.projectcenter.domain.dto.project.ProjectDTO;
import com.jmr.projectcenter.domain.entity.activity.Activity;
import com.jmr.projectcenter.domain.entity.project.Project;
import com.jmr.projectcenter.domain.entity.user_project_relation.UserProjectRelation;
import com.jmr.projectcenter.service.rocketmq.RocketMQProducerService;
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
public class ProjectService {
    private final ProjectMapper projectMapper;
    private final UserProjectRelationMapper userProjectRelationMapper;
    private final UUIDOperator uuidOperator;
    private final RocketMQProducerService rocketMQProducerService;
    private final UserService userService;

    public String genProjectCreateActivityTitle(String creator, String projectName) {
        return creator + " 新建项目 " + projectName;
    }

    public String genProjectDeleteActivityTitle(String operator, String projectName) {
        return operator + " 删除项目 " + projectName;
    }

    public int create(Project project, String creatorId) {
        int insertProjectResult = projectMapper.insertSelective(project);

        // 插入用户和项目的关系
        UserProjectRelation userProjectRelation = UserProjectRelation.builder()
                .pkId(uuidOperator.getUUid())
                .projectId(project.getPkId())
                .userId(creatorId)
                .role(1)
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        int insertRelationResult = userProjectRelationMapper.insertSelective(userProjectRelation);
        if (insertProjectResult == 1 && insertRelationResult == 1) {
            String activityId = uuidOperator.getUUid();
            Activity activity = Activity.builder()
                    .pkId(activityId)
                    .action("create-project")
                    .creatorId(creatorId)
                    .teamId(project.getTeamId())
                    .projectId(project.getPkId())
                    .title(genProjectCreateActivityTitle(userService.getUserInfo(creatorId).getUsername(), project.getName()))
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

    public ProjectDTO findById(String projectId, String userId) {
        return projectMapper.findById(projectId, userId);
    }

    public int update(Project project) {
        return projectMapper.updateByPrimaryKeySelective(project);
    }

    public int delete(DeleteProjectRequestDTO deleteProjectRequestDTO) {
        String id = deleteProjectRequestDTO.getProjectId();
        int deleteProjectResult = projectMapper.deleteByPrimaryKey(id);
        Example example = new Example(UserProjectRelation.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", id);
        int deleteRelation = userProjectRelationMapper.deleteByExample(example);
        if (deleteProjectResult == 1 && deleteRelation == 1) {
            String activityId = uuidOperator.getUUid();
            Activity activity = Activity.builder()
                    .pkId(activityId)
                    .action("delete-project")
                    .creatorId(deleteProjectRequestDTO.getOperatorId())
                    .teamId(deleteProjectRequestDTO.getTeamId())
                    .projectId(id)
                    .title(genProjectDeleteActivityTitle(
                            userService.getUserInfo(deleteProjectRequestDTO.getOperatorId()).getUsername()
                            , deleteProjectRequestDTO.getProjectName()))
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

    public List<ProjectDTO> list(String userId) {
        return projectMapper.list(userId);
    }

    public List<UserProjectRelation> getProjectMembers(String projectId) {
        Example example = new Example(UserProjectRelation.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", projectId);
        return userProjectRelationMapper.selectByExample(example);
    }

    public int invite(String projectId, String userId) {
        UserProjectRelation userProjectRelation = UserProjectRelation.builder()
                .pkId(uuidOperator.getUUid())
                .projectId(projectId)
                .userId(userId)
                .role(2)
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        return userProjectRelationMapper.insertSelective(userProjectRelation);
    }

}
