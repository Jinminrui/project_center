package com.jmr.projectcenter.domain.dto.activity;

import com.jmr.projectcenter.domain.dto.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDTO {

    private String pkId;

    private String title;

    private String creatorId;

    private String content;

    private Date createTime;

    private Date updateTime;

    private String action;

    private String teamId;

    private String projectId;

    private User creatorDetail;
}
