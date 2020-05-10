package com.yicj.study.dao;

import com.yicj.study.entity.UserSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSocialRepository extends JpaRepository<UserSocial,Long> {

    UserSocial findByProviderIdAndSocialId(String providerId ,String socialId)  ;

}
