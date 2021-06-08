package com.project.epiboly.service;


import com.project.epiboly.dao.GoodDao;
import com.project.epiboly.dao.OrderListDao;
import com.project.epiboly.dao.SellRecordDao;
import com.project.epiboly.dao.UserDao;
import com.project.epiboly.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderListService {


    @Autowired
    OrderListDao orderListDao;

    @Autowired
    GoodService goodService;

    @Autowired
    GoodDao goodDao;

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    @Autowired
    SellRecordDao sellRecordDao;



    //根据编号判断是否存在
    public boolean existByNum(String orderNum){
        OrderList uncheck = orderListDao.findOrderListByOrderNum(orderNum);
        return uncheck != null;
    }

    //查询全部订单
    public List<OrderList> findAll(){
        return orderListDao.findAll();
    }

    //根据订单编号查询订单
    public OrderList findByListNum(String orderNum){
        return orderListDao.findOrderListByOrderNum(orderNum);
    }

    //新增订单
    public Map<String,Object> insertOrderList(OrderListForRequestBody incomeOrder){
        Map<String,Object> condition = new HashMap<>();
        List<Good> incomeGoods = new ArrayList<>();
        System.out.println(incomeOrder.getGoods());
        SellRecord sellRecord = new SellRecord();

        if(incomeOrder.getGoods() != null){
            for(String goodNum : incomeOrder.getGoods().split("[,|，]")){
                Good good = goodService.findGoodByNum(goodNum);
                if(good != null){
                    if(!incomeGoods.contains(good)) {
                        incomeGoods.add(good);
                        System.out.println(good.getGoodStatus());
                        good.setGoodStatus(-1);
                        goodDao.save(good);
                        sellRecord.setRecordId(null);
                        sellRecord.setGoodName(good.getGoodName());
                        sellRecord.setGoodOwner(good.getGoodOwner());
                        sellRecord.setGoodStatus(good.getGoodStatus());
                        sellRecord.setUserId(incomeOrder.getUserId());
                        sellRecord.setUserName(userDao.findUserByUserId(incomeOrder.getUserId()).getUserName());
                        sellRecord.setOrderTime(incomeOrder.getOrderTime());
                        sellRecord.setOrderStatus(1);
                        System.out.println(good.getGoodStatus());
                        sellRecordDao.save(sellRecord);
                    }
                }
            }
        }

        OrderList toSave = new OrderList();
        toSave.setOrderId(null);
        if(incomeOrder.getUserId() == null){
            condition.put("condition",-3);
            return condition;
        }
        else {
            toSave.setUserId(incomeOrder.getUserId());
        }
        if(incomeOrder.getOrderNum() != null){
            toSave.setOrderNum(incomeOrder.getOrderNum());
        }
        if(incomeOrder.getOrderTime() != null){
            toSave.setOrderTime(incomeOrder.getOrderTime());
        }
        if(incomeOrder.getOrderPrice() != null){
            toSave.setOrderPrice(incomeOrder.getOrderPrice());
        }
        if(incomeGoods.size() != 0){
            /*for(int i = 0; i < incomeGoods.size();i ++){
                System.out.println("==============================================================");
                Good good = goodDao.findGoodByGoodName(incomeGoods.get(i).getGoodName());
                good.setGoodStatus(-1);
                goodDao.save(good);
            }*/
            toSave.setGoods(incomeGoods);
        }

        condition.put("condition",0);
        orderListDao.save(toSave);

        return condition;
    }

    //取消订单
/*    public Map<String,Object> cancelOrder(Long recordId){
        Map<String,Object> condition = new HashMap<>();
        if(recordId != null) {
            SellRecord sellRecord = sellRecordDao.findSellRecordByRecordId(recordId);
            System.out.println(sellRecord.getOrderTime());
            OrderList orderList = orderListDao.findOrderListByOrderTimeContaining(sellRecord.getOrderTime());
            System.out.println(orderList.getOrderNum());
            orderListDao.deleteById(orderList.getOrderId());
            condition.put("condition",0);
        }
        else{
            condition.put("condition",-2);
        }
        return condition;
    }
*/
    //根据用户名查询订单
    public List<OrderList> findOrdersByUserName(String userName){
        User user = userService.findUserByName(userName);
        return orderListDao.findOrderListsByUserId(user.getUserId());
    }


    //根据厂商查询订单
//    public List<OrderList> findOrdersByOwner(String goodOwner){
//        List<Good> search = goodService.findGoodByGoodOwner(goodOwner);
//        List<OrderList> toShow = new ArrayList<>();
//        for(Good inside : search){
//            if(inside != null){
//                Long orderListId = orderList_goodDao.findOrderList_GoodByGoodId(inside.getGoodId());
//                System.out.println(orderListId);
//            }
//        }
//        List<OrderList> newList = toShow.stream().collect(Collectors.toList());
//        for(int i=0;i<newList.size();i++){
//            System.out.println(newList.get(i));
//        }
//        return newList;
//    }

    public void delete(){
        orderListDao.deleteAll();
    }
}
