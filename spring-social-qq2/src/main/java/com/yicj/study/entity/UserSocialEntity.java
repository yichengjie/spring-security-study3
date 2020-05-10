package com.yicj.study.entity;


import lombok.Data;

import javax.persistence.*;

/**
 * 绑定用户与社交账号关系表
 */
@Data
@Entity
@Table(name = "user_social")
public class UserSocialEntity {
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 用户名
     */
    @Column(nullable = false)
    private String username ;
    /**
     * 社交账号唯一的id
     */
    @Column(nullable = false)
    private String socialId ;
    /**
     * 社交账号类型,eg: QQ，微信等
     */
    @Column(nullable = false)
    private String providerId ;


    public UserSocialEntity(){}

    public UserSocialEntity(String username, String socialId, String providerId) {
        this.username = username ;
        this.socialId = socialId ;
        this.providerId = providerId ;
    }
}
