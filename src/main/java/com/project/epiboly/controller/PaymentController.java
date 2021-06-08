package com.project.epiboly.controller;


import com.project.epiboly.entity.Payment;
import com.project.epiboly.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @RequestMapping("/findAllPayments")
    public List<Payment> findAll(){
        return paymentService.findAllPayment();
    }

    @PostMapping("/insertPayment")
    public Map<String,Object> insert(@RequestBody Payment payment){
        Map<String,Object> condition = new HashMap<>();
        paymentService.insertPayment(payment);
        condition.put("condition",0);
        return condition;
    }


}
