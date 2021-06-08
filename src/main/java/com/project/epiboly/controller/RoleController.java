package com.project.epiboly.controller;


import com.project.epiboly.entity.Role;
import com.project.epiboly.entity.RoleForRequestBody;
import com.project.epiboly.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {


    @Autowired
    RoleService roleService;

    //查询所有角色
    @GetMapping("/findAllRoles")
    public List<Role> findAll(){
        return roleService.findAllRoles();
    }

    //根据名称模糊查询角色
    @GetMapping("/findRolesByName")
    public List<Role> findRolesByNameContaining(@RequestParam("name")String name){
        return roleService.findRolesByNameContaining(name);
    }

    //新建或修改角色
    @PostMapping("/save")
    public Map<String,Object> saveRole(@RequestBody RoleForRequestBody role){
        return roleService.saveRole(role);
    }

    //删除角色
    @GetMapping("/delete")
    public Map<String,Object> deleteById(@RequestParam("roleId")Long roleId){
        return roleService.deleteById(roleId);
    }


}
