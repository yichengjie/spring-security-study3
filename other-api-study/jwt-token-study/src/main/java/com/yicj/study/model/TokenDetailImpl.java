package com.yicj.study.model;

public class TokenDetailImpl implements TokenDetail {

    private final String username;

    public TokenDetailImpl(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}