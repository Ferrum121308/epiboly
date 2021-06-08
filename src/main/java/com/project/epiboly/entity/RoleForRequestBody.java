package com.project.epiboly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleForRequestBody {
    private Long roleId;
    private String roleName;
    private String description;
    private Integer roleAvailable;
    private String permissions;
}
