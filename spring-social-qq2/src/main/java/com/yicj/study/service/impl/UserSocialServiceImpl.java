package com.yicj.study.service.impl;

import com.yicj.study.dao.UserSocialRepository;
import com.yicj.study.entity.UserSocial;
import com.yicj.study.service.UserSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSocialServiceImpl implements UserSocialService {

    @Autowired
    private UserSocialRepository repository ;

    @Override
    public UserSocial findUserSocial(String userId, String socialId) {
        return repository.findByProviderIdAndSocialId(userId, socialId);
    }

    @Override
    public void addUserSocial(UserSocial userSocial) {
        repository.save(userSocial) ;
    }
}
