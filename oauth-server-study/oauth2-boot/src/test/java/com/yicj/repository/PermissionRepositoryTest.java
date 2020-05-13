package com.yicj.repository;

import com.yicj.BaseTest;
import com.yicj.model.Permission;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PermissionRepositoryTest extends BaseTest {
    @Autowired
    private PermissionRepository permissionRepository ;

    @Test
    public void testInsert(){
        Permission permission = new Permission() ;
        permission.setName("user_add");
        permission.setDescritpion("用户添加");
        permission.setPid(0);
        permission.setUrl("/user/add");
        permissionRepository.save(permission);
    }

}
