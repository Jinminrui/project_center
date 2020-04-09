package com.jmr.projectcenter.domain.entity.task;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
public class Task {
    @Id
    @Column(name = "pk_Id")
    private String pkId;

    private String title;

    /**
     * 备注
     */
    private String note;

    /**
     * 任务类型：1-需求 2-缺陷
     */
    private Integer type;

    /**
     * 任务所在阶段
     */
    private String stage;

    /**
     * 开始时间
     */
    @Column(name = "start_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startTime;

    /**
     * 截止时间
     */
    @Column(name = "end_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;

    /**
     * 所属项目
     */
    @Column(name = "project_id")
    private String projectId;

    /**
     * 执行者id
     */
    private String executor;

    @Column(name = "story_points")
    private Integer storyPoints;

    /**
     * 优先级
1-普通
2-紧急
3-非常紧急
     */
    private Integer priority;

    /**
     * 该任务所属的迭代周期id
     */
    private String sprint;

    /**
     * 需求分类和缺陷分类
     */
    @Column(name = "task_class")
    private String taskClass;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "creator_id")
    private String creatorId;

    @Column(name = "finish_time")
    private Date finishTime;
}