package com.jmr.projectcenter.service.task;

import com.jmr.projectcenter.dao.task.TaskMapper;
import com.jmr.projectcenter.domain.dto.task.AnalyseTaskByExecutorDTO;
import com.jmr.projectcenter.domain.dto.task.BugAccumulativeTrend;
import com.jmr.projectcenter.domain.dto.task.BurnDownDTO;
import com.jmr.projectcenter.domain.entity.sprint.Sprint;
import com.jmr.projectcenter.domain.entity.task.Task;
import com.jmr.projectcenter.utils.DateOperator;
import com.jmr.projectcenter.utils.UUIDOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.util.ArrayList;
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
            if (task.getStage().equals("已完成") ||
                    task.getStage().equals("关闭") ||
                    task.getStage().equals("已解决")  ||
                    task.getStage().equals("已拒绝")) {
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
            criteria.andNotEqualTo("stage", "已解决");
            criteria.andNotEqualTo("stage", "已拒绝");
            criteria.andNotEqualTo("stage", "关闭");
        } else {
            criteria.andEqualTo("stage", stage);
        }
        example.setOrderByClause("priority desc");
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
        return taskMapper.countFinishedTaskByMember(projectId,executorId);
    }

    /**
     * 统计bug新增和修复趋势
     * @param projectId
     * @param sprintId
     * @param interval
     * @return
     */
    public List<BugAccumulativeTrend> analyseBugAccumulativeTrend(String projectId, String sprintId, Integer interval) {
        List<String> dates = DateOperator.getIntervalDates(interval);
        List<BugAccumulativeTrend> result = new ArrayList<>();
        for(String date : dates) {
            Integer newBug =  taskMapper.getNewBugNum(projectId, sprintId, date);
            Integer fixedBug = taskMapper.getFixedBugNum(projectId, sprintId, dates.get(interval - 1), date);
            BugAccumulativeTrend newBugItem = new BugAccumulativeTrend(date, "累计新增缺陷", newBug);
            BugAccumulativeTrend fixedBugItem = new BugAccumulativeTrend(date, "累计修复缺陷", fixedBug);
            result.add(newBugItem);
            result.add(fixedBugItem);
        }
       return result;
    }

    public List<BurnDownDTO> analyseBurnDown(Sprint sprint) throws ParseException {
        List<String> dates = DateOperator.getDayBetweenDates(
                DateOperator.parseDate(sprint.getStartTime()),
                DateOperator.parseDate(sprint.getEndTime()));
        Integer allStoryPoints = taskMapper.getAllStoryPoints(sprint.getPkId());
        List<BurnDownDTO> result = new ArrayList<>();

        float interval = allStoryPoints.floatValue() / dates.size();

        for(String date : dates) {
            Integer finishedStoryPoints =  taskMapper.getFinishedStoryPoints(sprint.getPkId(),date);
            BurnDownDTO truthData = BurnDownDTO.builder()
                    .date(date)
                    .type("实际剩余Story Points")
                    .value(allStoryPoints - finishedStoryPoints)
                    .build();
            result.add(truthData);

            int gap = DateOperator.getGapDays(DateOperator.parseString(date), DateOperator.parseString(dates.get(0)));

            float value = allStoryPoints - interval * gap;

            BurnDownDTO dreamData = BurnDownDTO.builder()
                    .date(date)
                    .type("理想剩余Story Points")
                    .value((int) value)
                    .build();
            result.add(dreamData);

        }
        return result;
    }
}
