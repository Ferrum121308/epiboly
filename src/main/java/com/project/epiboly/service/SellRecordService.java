package com.project.epiboly.service;


import com.project.epiboly.dao.SellRecordDao;
import com.project.epiboly.entity.SellRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SellRecordService {

    @Autowired
    SellRecordDao sellRecordDao;

    //通过货主找记录
    public List<SellRecord> findSellRecordByOwner(String goodOwner){
        return sellRecordDao.findSellRecordsByGoodOwner(goodOwner);
    }

    //改变订单状态
    public Map<String,Object> setSellRecordStatus(Long recordId, int status){
        Map<String,Object> condition = new HashMap<>();
        SellRecord sellRecord = sellRecordDao.findSellRecordByRecordId(recordId);
        sellRecord.setOrderStatus(status);
        sellRecordDao.save(sellRecord);
        condition.put("condition",0);
        return condition;
    }

    //删除订单
    public Map<String,Object> cancelRecord(Long recordId){
        Map<String,Object> condition = new HashMap<>();
        if(recordId != null){
            sellRecordDao.deleteById(recordId);
            condition.put("condition",0);
        }
        else{
            condition.put("condition",-3);
        }
        return condition;
    }

    public void delete(){
        sellRecordDao.deleteAll();
    }

}
