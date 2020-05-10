package com.yicj.study.service.impl;

import com.yicj.study.dao.UserSocialRepository;
import com.yicj.study.entity.UserSocialEntity;
import com.yicj.study.service.UserSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSocialServiceImpl implements UserSocialService {

    @Autowired
    private UserSocialRepository repository ;

    @Override
    public UserSocialEntity findUserSocial(String socialId) {
        return repository.findBySocialId(socialId);
    }

    @Override
    public void addUserSocial(UserSocialEntity userSocial) {
        repository.save(userSocial) ;
    }
}
