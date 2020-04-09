package com.jmr.projectcenter.dao.task;

import com.jmr.projectcenter.domain.dto.task.AnalyseTaskByExecutorDTO;
import com.jmr.projectcenter.domain.entity.task.Task;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TaskMapper extends Mapper<Task> {
    List<AnalyseTaskByExecutorDTO> analyseTaskByExecutor(@Param(value = "projectId") String projectId,
                                                         @Param(value = "stage") String stage);
}