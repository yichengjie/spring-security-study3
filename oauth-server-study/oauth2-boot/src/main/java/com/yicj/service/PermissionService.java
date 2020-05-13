package com.yicj.service;

import com.yicj.model.Permission;
import java.util.List;

public interface PermissionService {
    List<Permission> findAll();
    List<Permission> findByAdminUserId(int userId);
}