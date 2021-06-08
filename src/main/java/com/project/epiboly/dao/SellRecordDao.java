package com.project.epiboly.dao;

import com.project.epiboly.entity.SellRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellRecordDao extends JpaRepository<SellRecord,Long> {

    //根据Id查询订单
    SellRecord findSellRecordByRecordId(Long recordId);

    //通过goodOwner查询订单
    List<SellRecord> findSellRecordsByGoodOwner(String goodOwner);

}
