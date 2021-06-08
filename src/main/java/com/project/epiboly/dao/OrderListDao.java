package com.project.epiboly.dao;

import com.project.epiboly.entity.Good;
import com.project.epiboly.entity.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrderListDao extends JpaRepository<OrderList,Long> {

    //根据Id获取订单
    OrderList findOrderListByOrderId(Long orderId);

    //根据订单编号查询订单
    OrderList findOrderListByOrderNum(String orderNum);

    //根据用户查询订单
    List<OrderList> findOrderListsByUserId(Long userId);

    //根据货物Id查询订单
    OrderList findOrderListByGoodsContaining(Good good);

    //根据时间查询订单
    OrderList findOrderListByOrderTimeContaining(String orderTime);

}
