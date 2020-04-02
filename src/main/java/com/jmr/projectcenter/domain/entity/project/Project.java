package com.jmr.projectcenter.domain.entity.project;

import java.util.Date;
import javax.persistence.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "project")
public class Project {
    @Id
    @Column(name = "pk_id")
    private String pkId;

    @GeneratedValue(generator = "JDBC")
    private String id;

    private String name;

    private String description;

    private String cover;

    @Column(name = "group_id")
    private String groupId;

    /**
     * 项目状态
1-进度正常
2-存在风险
3-进度失控
     */
    private Integer status;

    @Column(name = "team_id")
    private String teamId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}