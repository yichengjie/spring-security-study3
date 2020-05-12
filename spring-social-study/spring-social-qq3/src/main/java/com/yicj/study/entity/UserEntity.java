package com.yicj.study.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "username" ,nullable = false, unique = true)
    private String username;
    /**
     * 用户密码
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * 用户角色
     */
    @Column(nullable = false)
    private String roles ;

    public UserEntity(){}

    /**
     * 默认赋user角色
     * @param username
     * @param password
     */
    public UserEntity(String username, String password){
        this(username,password,"ROLE_USER") ;
    }

    public UserEntity(String username, String password, String roles){
        this.username = username ;
        this.password = password ;
        this.roles = roles ;
    }



}
