package com.project.epiboly.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId",nullable = false,updatable = false,insertable = false)
    private Long roleId;

    @Column(name = "roleName")
    private String roleName;

    @Column(name = "description")
    private String description;

    @Column(name = "roleAvailable")
    private Integer roleAvailable;


    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "user_role",joinColumns = {@JoinColumn(name = "roleId")},inverseJoinColumns = {@JoinColumn(name = "userId")})
    private List<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission",joinColumns = {@JoinColumn(name = "roleId")},inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    private List<Permission> permissions;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRoleAvailable() {
        return roleAvailable;
    }

    public void setRoleAvailable(Integer roleAvailable) {
        this.roleAvailable = roleAvailable;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
