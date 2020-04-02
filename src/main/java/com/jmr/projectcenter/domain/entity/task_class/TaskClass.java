package com.jmr.projectcenter.domain.entity.task_class;

import java.util.Date;
import javax.persistence.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task_class")
public class TaskClass {
    @Id
    @Column(name = "pk_id")
    private String pkId;

    private String name;

    private String description;

    /**
     * 1-需求 2-缺陷
     */
    private Integer type;

    /**
     * 所属项目id
     */
    @Column(name = "project_id")
    private String projectId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}