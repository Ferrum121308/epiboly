package com.project.epiboly.controller;


import com.project.epiboly.entity.Good;
import com.project.epiboly.entity.OrderList;
import com.project.epiboly.entity.OrderListForRequestBody;
import com.project.epiboly.service.OrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orderList")
public class OrderListController {

    @Autowired
    OrderListService orderListService;

    //查找所有订单信息
    @RequestMapping("/findAllOrders")
    public List<OrderList> findAll(){
        return orderListService.findAll();
    }

    //查找当前用户的订单信息
    @GetMapping("/findOrdersByUserName")
    public List<OrderList> findOrdersByUserName(@RequestParam("userName")String userName){
        return orderListService.findOrdersByUserName(userName);
    }

    //插入新的订单
    @PostMapping("/insertOrder")
    public Map<String,Object> insert(@RequestBody OrderListForRequestBody orderList){
        return orderListService.insertOrderList(orderList);
    }

    //取消订单
/*    @GetMapping("/cancelOrder")
    public Map<String,Object> delete(@RequestParam("recordId")Long recordId){
        return orderListService.cancelOrder(recordId);
    }
*/

    @GetMapping("/delete")
    public void delete(){
        orderListService.delete();
    }

}
