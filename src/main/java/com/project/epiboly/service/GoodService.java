package com.project.epiboly.service;


import com.project.epiboly.dao.GoodDao;
import com.project.epiboly.entity.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodService {


    @Autowired
    GoodDao goodDao;



    //根据货物编号查询货物是否存在
    public boolean existByGoodName(String goodName){
        Good uncheck = goodDao.findGoodByGoodName(goodName);
        return uncheck != null;
    }

    //查询所有货物
    public List<Good> findAllGoods(){
        return goodDao.findAll();
    }

    //根据货号查询货物
    public Good findGoodByNum(String goodNum){
        return goodDao.findGoodByGoodNum(goodNum);
    }

    //新增或修改货物
    public Map<String,Object> save(Good incomeGood){
        Map<String,Object> condition = new HashMap<>();
        if (incomeGood.getGoodId() == null) {
            if(!existByGoodName(incomeGood.getGoodNum())){
                goodDao.save(incomeGood);
                condition.put("condition",0);
            }
            else{
                condition.put("condition",-1);//商品编号重复
            }
        }
        else{
            if (!goodDao.existsById(incomeGood.getGoodId())) {
                if (!existByGoodName(incomeGood.getGoodNum())) {
                    incomeGood.setGoodId(null);
                    goodDao.save(incomeGood);
                    condition.put("condition",0);
                }
                else{
                    condition.put("condition",-1);
                }
            }
            else{
                Good toSave = goodDao.findGoodByGoodId(incomeGood.getGoodId());
                if(incomeGood.getGoodName() != null){
                    toSave.setGoodName(incomeGood.getGoodName());
                }
                if(incomeGood.getGoodOwner() != null){
                    toSave.setGoodOwner(incomeGood.getGoodOwner());
                }
                if(incomeGood.getGoodDescription() != null){
                    toSave.setGoodDescription(incomeGood.getGoodDescription());
                }
                if(incomeGood.getGoodNum() != null){
                    toSave.setGoodNum(incomeGood.getGoodNum());
                }
                if(incomeGood.getGoodStatus() != null){
                    toSave.setGoodStatus(incomeGood.getGoodStatus());
                }
                goodDao.save(toSave);
                condition.put("condition",0);
            }
        }
        return condition;
    }

    //根据商品Id删除商品
    public Map<String,Object> deleteById(Long goodId){
        Map<String,Object> condition = new HashMap<>();
        if (goodDao.existsById(goodId)) {
            goodDao.deleteById(goodId);
            condition.put("condition",0);
        }
        else{
            condition.put("condition",-2);
        }
        return condition;
    }

    //根据status查询商品
    public List<Good> findGoodsByStatus(int status){
        return goodDao.findGoodsByGoodStatus(status);
    }

    //根据名称模糊查询货物
    public List<Good> findGoodsByNameContaining(String goodName){
        return goodDao.findGoodsByGoodNameContaining(goodName);
    }

    //根据名称与status查询货物
    public List<Good> findGoodsByNameContainingAndStatusEquals(String goodName,int status){
        return goodDao.findGoodsByGoodNameContainingAndGoodStatusEquals(goodName,status);
    }

    //根据厂商名查询货物
    public List<Good> findGoodByGoodOwner(String goodOwner){
        return goodDao.findGoodsByGoodOwner(goodOwner);
    }

    public void deleteAll(){
        goodDao.deleteAll();
    }

}
