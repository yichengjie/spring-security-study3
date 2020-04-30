package com.yicj.redis.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private long userId ;
    private String username ;
    private String password ;
}
