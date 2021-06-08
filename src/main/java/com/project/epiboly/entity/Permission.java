package com.project.epiboly.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permissionId",nullable = false,updatable = false,insertable = false)
    private Long permissionId;

    @Column(name = "permissionName")
    private String permissionName;

    @Column(name = "permissionAvailable")
    private Integer permissionAvailable;


    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "role_permission",joinColumns = {@JoinColumn(name = "permissionId")},inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<Role> roles;

}
