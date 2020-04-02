package com.jmr.projectcenter.service.taskclass;

import com.jmr.projectcenter.dao.task.TaskMapper;
import com.jmr.projectcenter.dao.task_class.TaskClassMapper;
import com.jmr.projectcenter.domain.dto.taskclass.TaskClassInfoDTO;
import com.jmr.projectcenter.domain.entity.task_class.TaskClass;
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
public class TaskClassService {
    private final UUIDOperator uuidOperator;
    private final TaskClassMapper taskClassMapper;

    public int create(TaskClass taskClass) {
        taskClass.setPkId(uuidOperator.getUUid());
        taskClass.setCreateTime(new Date());
        taskClass.setUpdateTime(new Date());
        log.info(taskClass.toString());
        return taskClassMapper.insertSelective(taskClass);
    }

    public int delete(String id) {
        return taskClassMapper.deleteByPrimaryKey(id);
    }

    public int update(TaskClass taskClass) {
        return taskClassMapper.updateByPrimaryKeySelective(taskClass);
    }

    public List<TaskClassInfoDTO> getClassList(Integer type, String projectId){
        return taskClassMapper.getTaskClassInfo(projectId, type);
    }

    public TaskClass findById(String id) {
        return taskClassMapper.selectByPrimaryKey(id);
    }
}
