package com.yicj.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
@Profile("simple")
@SpringBootApplication
public class AuthServerApplicationSimple {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplicationSimple.class, args) ;
    }
}
