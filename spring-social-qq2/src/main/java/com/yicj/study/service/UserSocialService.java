package com.yicj.study.service;

import com.yicj.study.entity.UserSocialEntity;

public interface UserSocialService {

    UserSocialEntity findUserSocial (String socialId)  ;

    void addUserSocial(UserSocialEntity userSocial) ;



}
