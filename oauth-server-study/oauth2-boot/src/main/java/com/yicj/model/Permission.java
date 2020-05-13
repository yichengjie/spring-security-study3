package com.yicj.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sys_permission")
public class Permission {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //权限名称
    @Column(name="name")
    private String name;
    //权限描述
    @Column(name = "descritpion")
    private String descritpion;
    //授权链接
    @Column(name = "url")
    private String url;
    //父节点id
    @Column(name = "pid")
    private int pid;
}