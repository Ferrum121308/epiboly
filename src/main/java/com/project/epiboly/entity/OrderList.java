package com.project.epiboly.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orderList")
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId",nullable = false,insertable = false,updatable = false)
    private Long orderId;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "orderNum")
    private String orderNum;

    @Column(name = "orderTime")
    private String orderTime;

    @Column(name = "orderPrice")
    private Double orderPrice;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "orderList_good",joinColumns = {@JoinColumn(name = "orderId")},inverseJoinColumns = {@JoinColumn(name = "goodId")})
    private List<Good> goods;



}
