package com.jmr.projectcenter.domain.entity.activity;

import java.util.Date;
import javax.persistence.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "activity")
public class Activity {
    @Id
    @Column(name = "pk_id")
    private String pkId;

    private String title;

    @Column(name = "creator_id")
    private String creatorId;

    private String content;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    private String action;

    @Column(name = "team_id")
    private String teamId;

    @Column(name = "project_id")
    private String projectId;
}