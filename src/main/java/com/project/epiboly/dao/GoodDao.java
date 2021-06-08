package com.project.epiboly.dao;

import com.project.epiboly.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodDao extends JpaRepository<Good,Long> {

    //根据Id查询货物
    Good findGoodByGoodId(Long goodId);

    //根据货号查询货物
    Good findGoodByGoodNum(String goodNum);

    //根据货物名称查询货物
    Good findGoodByGoodName(String goodName);

    //根据货物名称模糊查询货物
    List<Good> findGoodsByGoodNameContaining(String goodName);

    //根据status寻找货物
    List<Good> findGoodsByGoodStatus(Integer goodStatus);

    //根据description查询货物
    List<Good> findGoodsByGoodNameContainingAndGoodStatusEquals(String goodName,int status);

    //根据拥有者查询货物
    List<Good> findGoodsByGoodOwner(String goodOwner);


}
