package com.yicj.security;

import com.yicj.client.model.Permission;
import java.util.List;

public interface PermissionService {
    List<Permission> findAll();
    List<Permission> findByAdminUserId(int userId);
}