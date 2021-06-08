package com.project.epiboly.dao;

import com.project.epiboly.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleDao extends JpaRepository<Role,Long> {

    //通过Id查询角色
    Role findRoleByRoleId(Long roleId);

    //通过名称寻找角色
    Role findRoleByRoleName(String roleName);

    //通过名称模糊查询角色
    List<Role> findRolesByRoleNameContaining(String roleName);


}
