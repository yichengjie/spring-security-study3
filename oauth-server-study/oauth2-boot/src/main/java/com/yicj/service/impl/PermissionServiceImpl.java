package com.yicj.service.impl;

import com.yicj.client.model.Permission;
import com.yicj.repository.PermissionRepository;
import com.yicj.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionRepository permissionRepository ;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Permission> findAll() {
        return null;
    }

    @Override
    public List<Permission> findByAdminUserId(int userId) {

        List<Permission> list = new ArrayList<Permission>();
        List<Object[]> abcs = entityManager.createNativeQuery("select p.* \n" +
                "        from Sys_User u\n" +
                "        LEFT JOIN sys_role_user sru on u.id= sru.Sys_User_id\n" +
                "        LEFT JOIN Sys_Role r on sru.Sys_Role_id=r.id\n" +
                "        LEFT JOIN Sys_permission_role spr on spr.role_id=r.id\n" +
                "        LEFT JOIN Sys_permission p on p.id =spr.permission_id\n" +
                "        where u.id="+userId).getResultList();
        for (Object[] abc : abcs) {
            Permission permission = new Permission();
            permission.setId(Integer.valueOf(abc[0]+""));
            permission.setName(abc[1]+"");
            permission.setDescritpion(abc[2]+"");
            permission.setUrl(abc[3]+"");
//            permission.setPid(Integer.valueOf(abc[4]+""));
            list.add(permission);
        }
        return list;
    }
}