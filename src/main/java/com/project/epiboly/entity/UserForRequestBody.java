package com.project.epiboly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForRequestBody {
    private Long userId;
    private String userName;
    private String password;
    private String phoneNum;
    private String role;
}
