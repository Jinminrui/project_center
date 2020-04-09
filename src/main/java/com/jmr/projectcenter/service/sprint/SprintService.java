package com.jmr.projectcenter.service.sprint;

import com.jmr.projectcenter.dao.sprint.SprintMapper;
import com.jmr.projectcenter.dao.task.TaskMapper;
import com.jmr.projectcenter.domain.dto.sprint.SprintDTO;
import com.jmr.projectcenter.domain.entity.sprint.Sprint;
import com.jmr.projectcenter.service.task.TaskService;
import com.jmr.projectcenter.utils.UUIDOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SprintService {
    private final SprintMapper sprintMapper;
    private final TaskService taskService;
    private final UUIDOperator uuidOperator;

    public Sprint findById(String pkId) {
        return sprintMapper.selectByPrimaryKey(pkId);
    }

    public List<SprintDTO> list(String projectId) {
        return sprintMapper.getSprintDetailList(projectId);
    }

    public int create(Sprint sprint) {
        sprint.setPkId(uuidOperator.getUUid());
        sprint.setCreateTime(new Date());
        sprint.setUpdateTime(new Date());
        return sprintMapper.insertSelective(sprint);
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
}
