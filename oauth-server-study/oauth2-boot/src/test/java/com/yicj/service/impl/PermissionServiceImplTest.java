package com.yicj.service.impl;

import com.yicj.BaseTest;
import com.yicj.client.model.Permission;
import com.yicj.service.PermissionService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PermissionServiceImplTest extends BaseTest {

    @Autowired
    private PermissionService permissionService ;

    @Test
    public void findAll() {
        List<Permission> permissions = permissionService.findByAdminUserId(1);
        System.out.println(permissions);
    }

    @Test
    public void findByAdminUserId() {

    }
}