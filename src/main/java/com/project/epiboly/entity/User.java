package com.project.epiboly.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "phoneNum")
    private String phoneNum;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",joinColumns = {@JoinColumn(name = "userId")},inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<Role> roles;


}
