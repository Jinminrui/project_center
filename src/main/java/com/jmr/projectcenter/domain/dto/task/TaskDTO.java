package com.jmr.projectcenter.domain.dto.task;

import com.jmr.projectcenter.domain.dto.project.ProjectDTO;
import com.jmr.projectcenter.domain.dto.user.User;
import com.jmr.projectcenter.domain.entity.project.Project;
import com.jmr.projectcenter.domain.entity.sprint.Sprint;
import com.jmr.projectcenter.domain.entity.task_class.TaskClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
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
    private Date startTime;

    /**
     * 截止时间
     */
    private Date endTime;

    /**
     * 所属项目
     */
    private String projectId;

    /**
     * 执行者id
     */
    private String executor;

    private Integer storyPoints;

    /**
     * 优先级
     * 1-普通
     * 2-紧急
     * 3-非常紧急
     */
    private Integer priority;

    /**
     * 该任务所属的迭代周期id
     */
    private String sprint;

    /**
     * 需求分类和缺陷分类
     */
    private String taskClass;

    private Date createTime;

    private Date updateTime;

    private String creatorId;

    private Date finishTime;

    private User executorInfo;

    private User creatorInfo;

    private TaskClass taskClassDetail;

    private ProjectDTO projectDetail;

    private Sprint sprintDetail;
}
