package com.jmr.projectcenter.domain.entity.sprint;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sprint")
public class Sprint {
    @Id
    @Column(name = "pk_id")
    private String pkId;

    /**
     * 所属项目
     */
    @Column(name = "project_id")
    private String projectId;

    @Column(name = "creator_id")
    private String creatorId;

    @Column(name = "team_id")
    private String teamId;

    /**
     * 标题
     */
    private String title;

    /**
     * 1-待开始、2-已开始、3-已结束
     */
    private Integer status;

    /**
     * 负责人
     */
    private String director;

    /**
     * 开始日期
     */
    @Column(name = "start_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startTime;

    /**
     * 截止日期
     */
    @Column(name = "end_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;

    /**
     * 描述
     */
    private String description;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}