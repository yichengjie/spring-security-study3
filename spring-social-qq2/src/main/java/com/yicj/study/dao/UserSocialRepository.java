package com.yicj.study.dao;

import com.yicj.study.entity.UserSocialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSocialRepository extends JpaRepository<UserSocialEntity,Long> {

    UserSocialEntity findBySocialId(String socialId)  ;

}
