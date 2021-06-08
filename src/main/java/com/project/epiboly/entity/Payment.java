package com.project.epiboly.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payId",nullable = false,insertable = false,updatable = false)
    private Long payId;

    @Column(name = "orderId")
    private Long orderId;

    @Column(name = "payNum")
    private String payNum;

    @Column(name = "payAmount")
    private Double payAmount;

    @Column(name = "payTime")
    private String payTime;

    @Column(name = "payStatus")
    private Integer payStatus;


}
