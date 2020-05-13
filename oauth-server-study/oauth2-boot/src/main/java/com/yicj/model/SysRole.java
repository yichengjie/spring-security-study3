package com.yicj.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="sys_role")
@Data
public class SysRole {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
}