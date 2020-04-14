package com.jmr.projectcenter.dao.task;

import com.jmr.projectcenter.domain.dto.task.AnalyseTaskByExecutorDTO;
import com.jmr.projectcenter.domain.entity.task.Task;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TaskMapper extends Mapper<Task> {
    List<AnalyseTaskByExecutorDTO> analyseTaskByExecutor(
            @Param(value = "projectId") String projectId,
            @Param(value = "stage") String stage);

    Integer getNewBugNum(
            @Param(value = "projectId") String projectId,
            @Param(value = "sprintId") String sprintId,
            @Param(value = "relativeTime") String relativeTime);

    Integer getFixedBugNum(@Param(value = "projectId") String projectId,
                           @Param(value = "sprintId") String sprintId,
                           @Param(value = "startTime") String startTime,
                           @Param(value = "relativeTime") String relativeTime);

    Integer countFinishedTaskByMember(
            @Param(value = "projectId") String projectId,
            @Param(value = "executor") String executor);
}