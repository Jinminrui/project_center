package com.jmr.projectcenter.dao.task_class;

import com.jmr.projectcenter.domain.dto.taskclass.TaskClassInfoDTO;
import com.jmr.projectcenter.domain.entity.task_class.TaskClass;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TaskClassMapper extends Mapper<TaskClass> {
    List<TaskClassInfoDTO> getTaskClassInfo(@Param(value = "projectId") String projectId,
                                            @Param(value = "type") Integer type);
}