package com.project.epiboly.dao;

import com.project.epiboly.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentDao extends JpaRepository<Payment,Long> {

    //根据Id查找付款记录
    Payment findPaymentByPayId(Long payId);

    //根据付款编号查找记录
    Payment findPaymentByPayNum(String payNum);

    //根据金额大于查找记录
    List<Payment> findPaymentsByPayAmountGreaterThan(Double minAmount);

    //根据金额小于查找记录
    List<Payment> findPaymentsByPayAmountLessThan(Double maxAmount);

    //根据金额在某区间查找
    List<Payment> findPaymentsByPayAmountBetween(Double min,Double max);

    //根据付款时间查找
    List<Payment> findPaymentsByPayTimeContaining(String payTime);

}
