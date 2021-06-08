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
@Table(name = "good")
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goodId")
    private Long goodId;

    @Column(name = "goodName")
    private String goodName;

    @Column(name = "goodOwner")
    private String goodOwner;

    @Column(name = "goodDescription")
    private String goodDescription;

    @Column(name = "goodNum")
    private String goodNum;

    @Column(name = "goodStatus")
    private Integer goodStatus;

    @Column(name = "goodPrice")
    private Double goodPrice;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "orderList_good",joinColumns = {@JoinColumn(name = "goodId")},inverseJoinColumns = {@JoinColumn(name = "orderId")})
    private List<OrderList> orderLists;

}
