package com.project.epiboly.dao;

import com.project.epiboly.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionDao extends JpaRepository<Permission,Long> {


    //通过Id查询权限
    Permission findPermissionByPermissionId(Long permissionId);


    //通过名称查询权限
    Permission findPermissionByPermissionName(String permissionName);


    //通过名称模糊查询权限
    List<Permission> findPermissionsByPermissionNameContaining(String permissionName);


}
