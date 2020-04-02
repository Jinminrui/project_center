package com.jmr.projectcenter.dao.project;

import com.jmr.projectcenter.domain.dto.project.ProjectDTO;
import com.jmr.projectcenter.domain.entity.project.Project;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ProjectMapper extends Mapper<Project> {
    List<ProjectDTO> list(@Param("userId") String userId);

    ProjectDTO findById (@Param("projectId") String projectId, @Param("userId") String userId);
}