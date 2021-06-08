package com.project.epiboly.controller;

import com.project.epiboly.entity.Good;
import com.project.epiboly.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/good")
public class GoodController {

    @Autowired
    GoodService goodService;

    //查询所有货物
    @RequestMapping("/findAllGoods")
    public List<Good> findAllGoods(){
        return goodService.findAllGoods();
    }

    //根据货物名称模糊查询货物
    @RequestMapping("/findGoods")
    public List<Good> findGoods(@RequestParam("goodName")String goodName){
        if(goodName != null) {
            return goodService.findGoodsByNameContaining(goodName);
        }
        else{
            return goodService.findAllGoods();
        }
    }

    //根据货号查询货物
    @RequestMapping("/findGoodByNum")
    public Good findGoodsByNum(@RequestParam("goodNum") String goodNum){
        return goodService.findGoodByNum(goodNum);
    }

    //新增或修改货物信息
    @PostMapping("/save")
    public Map<String,Object> saveGoods(@RequestBody Good good){
        return goodService.save(good);
    }

    //根据Id删除货物
    @GetMapping("/delete")
    public Map<String,Object> deleteById(@RequestParam("goodId") Long goodId){
        return goodService.deleteById(goodId);
    }

    //根据status查找货物
    @GetMapping("/findGoodsByStatus")
    public List<Good> findGoodsByStatus(@RequestParam("status") int status){
        return goodService.findGoodsByStatus(status);
    }

    //根据status和名称查询
    @GetMapping("/findGoodsByStatusAndName")
    public List<Good> findGoods(@RequestParam("goodName")String goodName,@RequestParam("status")int status){
        return goodService.findGoodsByNameContainingAndStatusEquals(goodName,status);
    }

    @GetMapping("/findGoodsByOwner")
    public List<Good> findGoodsByOwner(@RequestParam("goodOwner")String goodOwner){
        return goodService.findGoodByGoodOwner(goodOwner);
    }

    @GetMapping("/deleteAll")
    public void deleteAll(){
        goodService.deleteAll();
    }

}
