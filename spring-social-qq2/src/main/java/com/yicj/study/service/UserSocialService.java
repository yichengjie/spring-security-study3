package com.yicj.study.service;

import com.yicj.study.entity.UserSocial;

public interface UserSocialService {

    UserSocial findUserSocial (String providerId , String socialId)  ;

    void addUserSocial(UserSocial userSocial) ;

}
