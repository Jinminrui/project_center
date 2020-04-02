package com.jmr.projectcenter.domain.dto.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

    private String name;

    private String description;

    private String cover;

    private String groupId;

    /**
     * 项目状态
     1-进度正常
     2-存在风险
     3-进度失控
     */
    private Integer status;

    private String teamId;

    private Date createTime;

    private Date updateTime;

    private Integer currentRole;

    private String pkId;

    private String id;

}
