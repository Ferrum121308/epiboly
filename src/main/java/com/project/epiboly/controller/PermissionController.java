package com.project.epiboly.controller;


import com.project.epiboly.entity.Permission;
import com.project.epiboly.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;


    //测试类 不作为正式使用
    @PostMapping("/exist")
    public Map<String,Object> condition(@RequestParam("Id") Long permissionId){
        Map<String,Object> condition = new HashMap<>();
        if(permissionService.exist(permissionId)){
            condition.put("status","存在");
        }
        else {
            condition.put("status","不存在");
        }
        return condition;
    }

    //查询权限
    @RequestMapping("/findAllPermissions")
    public List<Permission> findAllPermissions(){
        return permissionService.findAllPermissions();
    }

    //按照名称查询权限
    @RequestMapping("/findPermissionsByName")
    public List<Permission> findPermissionByNameContaining(@RequestParam("name")String name){
        return permissionService.findPermissionsByPermissionNameContaining(name);
    }

    //存储或修改权限
    @PostMapping("/save")
    public Map<String,Object> save(@RequestBody Permission permission){
        return permissionService.savePermission(permission);
    }

    //删除权限
    @GetMapping("/delete")
    public Map<String,Object> deleteById(@RequestParam("Id") Long permissionId){
        System.out.println(permissionId);
        return permissionService.deleteById(permissionId);
    }

}
