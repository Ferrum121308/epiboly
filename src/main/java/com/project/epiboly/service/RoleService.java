package com.project.epiboly.service;


import com.project.epiboly.dao.PermissionDao;
import com.project.epiboly.dao.RoleDao;
import com.project.epiboly.entity.Permission;
import com.project.epiboly.entity.Role;
import com.project.epiboly.entity.RoleForRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleService {

    @Autowired
    RoleDao roleDao;

    @Autowired
    PermissionService permissionService;

    @Autowired
    PermissionDao permissionDao;

    //根据名称查询角色是否存在
    public boolean existByName(String roleName){
        Role uncheck = roleDao.findRoleByRoleName(roleName);
        return uncheck != null;
    }


    //查询所有角色
    public List<Role> findAllRoles(){
        return roleDao.findAll();
    }

    //根据名称查询角色
    public Role findRoleByName(String roleName){
        return roleDao.findRoleByRoleName(roleName);
    }

    //通过角色名称模糊查询角色
    public List<Role> findRolesByNameContaining(String roleName){
        return roleDao.findRolesByRoleNameContaining(roleName);
    }

    //新增或修改角色
    public Map<String,Object> saveRole(RoleForRequestBody incomeRole){
        Map<String,Object> condition = new HashMap<>();
        List<Permission> incomePermissions = new ArrayList<>();
        if(incomeRole.getPermissions() != null){
            for(String permissionName : incomeRole.getPermissions().split("[,|，]")){
                Permission permission = permissionService.findPermissionByName(permissionName);
                if(permission != null){
                    incomePermissions.add(permission);
                }
            }
        }
        if (incomeRole.getRoleId() == null) {
            if(!existByName(incomeRole.getRoleName())){
                Role saveRole = new Role();
                saveAttrs(incomeRole, condition, incomePermissions, saveRole);
            }
            else{
                condition.put("condition",-1);
            }
        }
        else{
            if (!roleDao.existsById(incomeRole.getRoleId())) {
                if(!existByName(incomeRole.getRoleName())){
                    Role savingRole = new Role();
                    savingRole.setRoleId(null);
                    saveAttrs(incomeRole, condition, incomePermissions, savingRole);
                }
                else {
                    condition.put("condition",-1);
                }
            }
            else{
                Role savingRole = roleDao.findRoleByRoleId(incomeRole.getRoleId());
                if(incomeRole.getRoleName() != null){
                    savingRole.setRoleName(incomeRole.getRoleName());
                }
                if(incomeRole.getDescription() != null){
                    savingRole.setDescription(incomeRole.getDescription());
                }
                if(incomeRole.getRoleAvailable() != null){
                    savingRole.setRoleAvailable(incomeRole.getRoleAvailable());
                }
                if(incomePermissions.size() != 0){
                    savingRole.setPermissions(incomePermissions);
                }
                roleDao.save(savingRole);
                condition.put("condition",0);
            }
        }
        return condition;
    }

    private void saveAttrs(RoleForRequestBody incomeRole, Map<String, Object> condition, List<Permission> incomePermissions, Role saveRole) {
        saveRole.setRoleName(incomeRole.getRoleName());
        saveRole.setDescription(incomeRole.getDescription());
        saveRole.setRoleAvailable(incomeRole.getRoleAvailable());
        if(incomePermissions.size() != 0){
            saveRole.setPermissions(incomePermissions);
        }
        roleDao.save(saveRole);
        condition.put("condition",0);
    }

    //删除角色
    public Map<String,Object> deleteById(Long roleId){
        Map<String,Object> condition = new HashMap<>();
        if(roleDao.existsById(roleId)){
            roleDao.deleteById(roleId);
            condition.put("condition",0);
        }
        else {
            condition.put("condition",-2);
        }
        return condition;
    }


}
