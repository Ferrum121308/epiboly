package com.project.epiboly.service;


import com.project.epiboly.dao.PermissionDao;
import com.project.epiboly.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionService {

    @Autowired
    PermissionDao permissionDao;

    //查询全部权限
    public List<Permission> findAllPermissions(){
        return permissionDao.findAll();
    }

    //根据名称查询权限
    public Permission findPermissionByName(String permissionName){
        return permissionDao.findPermissionByPermissionName(permissionName);
    }


    //根据权限名称模糊查询权限
    public List<Permission> findPermissionsByPermissionNameContaining(String permissionName){
        return permissionDao.findPermissionsByPermissionNameContaining(permissionName);
    }


    //判断权限名是否存在
    public boolean existByName(String permissionName){
        Permission uncheck = permissionDao.findPermissionByPermissionName(permissionName);
        return uncheck != null;
    }

    //新增或修改权限
    public Map<String,Object> savePermission(Permission incomePermission){
        Map<String,Object> condition = new HashMap<>();
        if(incomePermission.getPermissionId() == null){
            if(!existByName(incomePermission.getPermissionName())){
                permissionDao.save(incomePermission);
                condition.put("condition",0);
            }
            else {
                condition.put("condition",-1);
                return condition;
            }
        }
        else {
            if(!permissionDao.existsById(incomePermission.getPermissionId())){
                if(!existByName(incomePermission.getPermissionName())){
                    incomePermission.setPermissionId(null);
                    permissionDao.save(incomePermission);
                    condition.put("condition",0);
                }
                else {
                    condition.put("condition",-1);
                }
            }
            else {
                Permission saving = permissionDao.findPermissionByPermissionId(incomePermission.getPermissionId());
                if(incomePermission.getPermissionName() != null){
                    saving.setPermissionName(incomePermission.getPermissionName());
                }
                if(incomePermission.getPermissionAvailable() != null){
                    saving.setPermissionAvailable(incomePermission.getPermissionAvailable());
                }
                permissionDao.save(saving);
                condition.put("condition",0);
            }
        }
        return condition;
    }

    //删除权限
    public Map<String,Object> deleteById(Long permissionId){
        Map<String,Object> condition = new HashMap<>();
        if(permissionDao.existsById(permissionId)){
            permissionDao.deleteById(permissionId);
            condition.put("condition",0);
        }
        else {
            condition.put("condition",-2);
        }
        return condition;
    }

    //测试用
    public boolean exist(Long permissionId){
        return permissionDao.existsById(permissionId);
    }
}
