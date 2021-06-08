package com.project.epiboly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sellRecord")
public class SellRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recordId")
    private Long recordId;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "goodName")
    private String goodName;

    @Column(name = "goodOwner")
    private String goodOwner;

//    @Column(name = "orderId")
//    private Long orderId;

    @Column(name = "orderTime")
    private String orderTime;

    @Column(name = "goodStatus")
    private Integer goodStatus;

    @Column(name = "orderStatus")
    private Integer orderStatus;


}
