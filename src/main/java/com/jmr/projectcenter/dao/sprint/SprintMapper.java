package com.jmr.projectcenter.dao.sprint;

import com.jmr.projectcenter.domain.dto.sprint.SprintDTO;
import com.jmr.projectcenter.domain.entity.sprint.Sprint;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SprintMapper extends Mapper<Sprint> {
    List<SprintDTO> getSprintDetailList(@Param(value = "projectId") String projectId);
}