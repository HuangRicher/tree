package com.tongzhu.user.afight;

import com.tongzhu.user.controller.vo.Combatant;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MonsterCache {

    public static Map<Integer, List<Combatant>> monsters=new ConcurrentHashMap<>();

    public static Map<Integer, List<Map<String,Object>>> mapList=new ConcurrentHashMap<>();
}
