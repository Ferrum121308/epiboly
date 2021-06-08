package com.project.epiboly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListForRequestBody {

    private Long orderId;
    private Long userId;
    private String orderNum;
    private String orderTime;
    private Double orderPrice;
    private String goods;

}
