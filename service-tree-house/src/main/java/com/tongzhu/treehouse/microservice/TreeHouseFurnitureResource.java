package com.tongzhu.treehouse.microservice;

import com.tongzhu.treehouse.model.Furniture;
import com.tongzhu.treehouse.service.FurnitureService;
import com.tongzhu.treehouse.service.TreeHouseFurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/treeHouseFurnitureResource")
public class TreeHouseFurnitureResource {

    @Autowired
    private TreeHouseFurnitureService treeHouseFurnitureService;

    @Autowired
    private FurnitureService furnitureService;


    @PostMapping("/addFurniture/{userId}/{goodsId}")
    public void addFurniture(@PathVariable("userId")String userId,@PathVariable("goodsId")String goodsId){
        treeHouseFurnitureService.add(userId,goodsId);
    }


    @PostMapping("/addMultiFurniture/{userId}")
    public void addMultiFurniture(@PathVariable("userId") String userId, @RequestBody Map<String,Integer> furnitureMap){
        for(Map.Entry<String,Integer> entry:furnitureMap.entrySet()){
            treeHouseFurnitureService.add(userId,entry.getKey());
            if(entry.getValue()>0){
                Furniture furniture=furnitureService.getById(entry.getKey());
                treeHouseFurnitureService.arrange(userId,furniture.getCategoryId(),furniture.getLocation());
            }
        }
    }
}
