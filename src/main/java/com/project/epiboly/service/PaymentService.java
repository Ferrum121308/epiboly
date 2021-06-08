package com.project.epiboly.service;


import com.project.epiboly.dao.PaymentDao;
import com.project.epiboly.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    PaymentDao paymentDao;

    //判断支付信息是否存在
    public boolean existByPayNum(String payNum){
        Payment uncheck = paymentDao.findPaymentByPayNum(payNum);
        return uncheck != null;
    }

    //查询所有支付信息
    public List<Payment> findAllPayment(){
        return paymentDao.findAll();
    }

    //根据付款编号查询记录
    public Payment findPaymentByNum(String payNum){
        return paymentDao.findPaymentByPayNum(payNum);
    }

    //新增付款记录
    public void insertPayment(Payment incomePayment){
        paymentDao.save(incomePayment);
    }

}
