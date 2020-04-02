package com.jmr.projectcenter.domain.entity.user_project_relation;

import java.util.Date;
import javax.persistence.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_project_relation")
public class UserProjectRelation {
    @Id
    @Column(name = "pk_id")
    private String pkId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "project_id")
    private String projectId;

    /**
     * 用户和项目的对应关系
1-创建者
2-管理者
3-参与者
     */
    private Integer role;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}