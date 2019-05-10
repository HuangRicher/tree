package com.tongzhu.user.service.impl;

import com.tongzhu.user.service.TreeHouseFurnitureService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TreeHouseFurnitureServiceHystrix implements TreeHouseFurnitureService {
    @Override
    public void addMultiFurniture(String userId, Map<String, Integer> furnitureMap) {

    }
}
