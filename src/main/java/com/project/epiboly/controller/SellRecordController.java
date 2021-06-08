package com.project.epiboly.controller;


import com.project.epiboly.entity.SellRecord;
import com.project.epiboly.service.SellRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sellRecord")
public class SellRecordController {

    @Autowired
    SellRecordService sellRecordService;

    @GetMapping("/findRecordsByOwner")
    public List<SellRecord> findRecordByOwner(@RequestParam("goodOwner")String goodOwner){
        return sellRecordService.findSellRecordByOwner(goodOwner);
    }

    @GetMapping("/changeStatus")
    public Map<String,Object> changeStatus(@RequestParam("sellRecordId")Long sellRecordId,@RequestParam("status")Integer status){
        return sellRecordService.setSellRecordStatus(sellRecordId,status);
    }

    @GetMapping("/cancelRecord")
    public Map<String,Object> cancelRecord(@RequestParam("recordId")Long recordId){
        return sellRecordService.cancelRecord(recordId);
    }

    @GetMapping("/delete")
    public void delete(){
        sellRecordService.delete();
    }
}
