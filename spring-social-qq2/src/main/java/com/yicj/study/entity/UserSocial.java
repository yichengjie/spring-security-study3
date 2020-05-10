package com.yicj.study.entity;


import lombok.Data;

import javax.persistence.*;

/**
 * 绑定用户与社交账号关系表
 */
@Data
@Entity
@Table(name = "user_social")
public class UserSocial {
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 用户id
     */
    @Column(nullable = false)
    private String userId ;
    /**
     * 社交账号类型,eg: QQ，微信等
     */
    @Column(nullable = false)
    private String providerId ;
    /**
     * 社交账号唯一的id
     */
    @Column(nullable = false)
    private String socialId ;
}
