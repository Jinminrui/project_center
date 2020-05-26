package com.jmr.projectcenter.domain.dto.sprint;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SprintDTO {
    private String pkId;

    /**
     * 所属项目
     */
    private String projectId;

    private String creatorId;

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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startTime;

    /**
     * 截止日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;

    /**
     * 描述
     */
    private String description;

    private Date createTime;

    private Date updateTime;

    /**
     * 所有任务数量
     */
    private Integer allTask;

    /**
     * 已完成的任务数量
     */
    private Integer doneTask;
}
