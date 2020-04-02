package com.jmr.projectcenter.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    private String pkId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 微信名称

     */
    private String wxName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 职位
     */
    private String position;

    /**
     * 个人描述
     */
    private String description;
}