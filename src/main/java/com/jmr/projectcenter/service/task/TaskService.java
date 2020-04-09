package com.jmr.projectcenter.service.task;

import com.jmr.projectcenter.dao.task.TaskMapper;
import com.jmr.projectcenter.domain.dto.task.AnalyseTaskByExecutorDTO;
import com.jmr.projectcenter.domain.entity.task.Task;
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
public class TaskService {
    private final UUIDOperator uuidOperator;
    private final TaskMapper taskMapper;

    public Task findById(String id) {
        return taskMapper.selectByPrimaryKey(id);
    }

    public int create(Task task) {
        task.setPkId(uuidOperator.getUUid());
        task.setCreateTime(new Date());
        task.setUpdateTime(new Date());
        return taskMapper.insertSelective(task);
    }

    public int delete(String taskId) {
        return taskMapper.deleteByPrimaryKey(taskId);
    }

    public int update(Task task) {
        if (task.getStage() != null) {
            if (task.getStage().equals("已完成")) {
                task.setFinishTime(new Date());
            } else {
                task.setFinishTime(null);
            }
        }
        task.setUpdateTime(new Date());
        return taskMapper.updateByPrimaryKeySelective(task);
    }


    public int getNoClassTaskNum(Integer type, String projectId) {
        Example example = new Example(Task.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", projectId);
        criteria.andEqualTo("type", type);
        criteria.andEqualTo("taskClass", "default");
        return taskMapper.selectCountByExample(example);
    }

    public int getTotalTaskNum(Integer type, String projectId) {
        Example example = new Example(Task.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", projectId);
        criteria.andEqualTo("type", type);
        return taskMapper.selectCountByExample(example);
    }

    public List<Task> getTaskList(String projectId, Integer type, String taskClass, String stage, String creatorId, String executor, String sprint) {
        Example example = new Example(Task.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", projectId);
        criteria.andEqualTo("type", type);
        criteria.andEqualTo("taskClass", taskClass);
        criteria.andEqualTo("creatorId", creatorId);
        criteria.andEqualTo("executor", executor);
        criteria.andEqualTo("sprint", sprint);
        if (stage != null && stage.equals("todo")) {
            criteria.andNotEqualTo("stage", "已完成");
            criteria.andNotEqualTo("stage", "关闭");
        } else {
            criteria.andEqualTo("stage", stage);
        }
        example.setOrderByClause("update_time desc");
        return taskMapper.selectByExample(example);
    }

    public int setTaskClassDefault(String taskClass) {
        Example example = new Example(Task.class);
        Example.Criteria criteria = example.createCriteria();
        Task t = new Task();
        t.setTaskClass("default");
        criteria.orEqualTo("taskClass", taskClass);
        return taskMapper.updateByExampleSelective(t, example);
    }

    public int setSprintDefault(String sprintId) {
        Example example = new Example(Task.class);
        Example.Criteria criteria = example.createCriteria();
        Task t = new Task();
        t.setTaskClass("default");
        criteria.orEqualTo("sprint", sprintId);
        return taskMapper.updateByExampleSelective(t, example);
    }

    /**
     * 统计任务按执行者的分布
     * @param projectId
     * @param stage
     * @return
     */
    public List<AnalyseTaskByExecutorDTO> analyseTaskByExecutor(String projectId, String stage) {
        return taskMapper.analyseTaskByExecutor(projectId, stage);
    }

    /**
     * 统计已完成的需求情况
     * @param projectId
     * @param executorId
     * @return
     */
    public int countFinishedTaskByMember(String projectId,String executorId) {
        Example example = new Example(Task.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId",projectId);
        criteria.andEqualTo("executor",executorId);
        criteria.andEqualTo("stage", "已完成");
        return taskMapper.selectCountByExample(example);
    }
}
