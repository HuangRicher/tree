package com.tongzhu.user.microservice;

import com.tongzhu.constant.HTTPUrlConstant;
import com.tongzhu.constant.RoleConstant;
import com.tongzhu.user.constant.NewPlayerGuideConstant;
import com.tongzhu.user.domain.ArsenalInfo;
import com.tongzhu.user.domain.ArsenalInfoVO;
import com.tongzhu.user.model.*;
import com.tongzhu.user.service.*;
import com.tongzhu.user.service.vo.UserDetailVO;
import com.tongzhu.util.DateComputeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/userResource")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMessageService userMessageService;

    @Autowired
    private UserCrunchiesService userCrunchiesService;

    @Autowired
    private NewPlayerGuideService newPlayerGuideService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private ArsenalService arsenalService;




    @PostMapping("/findByUserId/{userId}")
    public User findByUserId(@PathVariable("userId") String userId){
        return userService.findByUserId(userId);
    }

    @PostMapping("/getUserDetail/{userId}")
    public UserDetailVO getUserDetail(@PathVariable("userId") String userId){
        return userService.getUserDetail(userId);
    }

    @PostMapping(value = "/getUserByUserIdOrName/{searchUser}")
    public List<UserDetailVO> getUserByUserIdOrName(@PathVariable("searchUser")String searchUser){
        return userService.getUserByUserIdOrName(searchUser);
    }


    @PostMapping("/selectUserForBuyByRand")
    public List<User> selectUserForBuyByRand(@RequestParam("minSellingPrice") int minSellingPrice,
                                             @RequestParam("maxSellingPrice")int maxSellingPrice,
                                             @RequestParam("userId")String userId,
                                             @RequestParam("maxExchangeCount")int maxExchangeCount,
                                             @RequestParam("count")int count){

        return userService.selectUserForBuyByRand(minSellingPrice,maxSellingPrice,userId,new Date(),
                new Date(),maxExchangeCount,count);
    }

    @PostMapping("/selectFriendsForBuyByRand")
    List<User> selectFriendsForBuyByRand(@RequestParam("userId")String userId,
                                         @RequestParam("status")Integer status,
                                         @RequestParam("exchangeCount")Integer exchangeCount,
                                         @RequestParam("sellingPrice")Integer sellingPrice,
                                         @RequestParam("count")Integer count){
        return userService.selectFriendsForBuyByRand(userId, status, new Date(), new Date(),exchangeCount,sellingPrice,count);
    }

    @PostMapping("/selectRecommendFriends/{userId}")
    List<User> selectRecommendFriends(@PathVariable("userId")String userId){
        return userService.selectRecommendFriends(userId);
    }

    @PostMapping("/recommendFriendByOppositeSex/{userId}")
    User recommendFriendByOppositeSex(@PathVariable("userId")String userId){
        User user=userService.findByUserId(userId);
        LocalDate date=DateComputeUtil.DateToLocalDate(user.getCreateDate()).plusDays(10);
        if(LocalDate.now().isBefore(date)){
            user= userService.recommendFriendByOppositeSex(userId);
            if(StringUtils.isBlank(user.getPortraitUrl()))
                user.setPortraitUrl(String.format(HTTPUrlConstant.HEAD_URL,user.getRoleId()));
            return user;
        } else{
            return null;
        }
    }


    @PostMapping("/reduceSellingPrice/{userId}/{workerId}")
    public User reduceSellingPrice(@PathVariable("userId") String userId, @PathVariable("workerId") String workerId){
        return userService.reduceSellingPrice(userId,workerId);
    }


    @PostMapping("/updateUserSellingPrice/{userId}/{sellingPrice}")
    public void updateUserSellingPrice(@PathVariable("userId") String userId, @PathVariable("sellingPrice") Integer sellingPrice){
        userService.updateUserSellingPrice(userId,sellingPrice);
    }

    @PostMapping("/countAllUser")
    public int countAllUser(){
        return userService.countAllUser();
    }

    @PostMapping("/addUserMessage")
    public int addUserMessage(@RequestBody UserMessage userMessage){
        return userMessageService.add(userMessage);
    }


    @PostMapping("/updateForUserOnlineState/{userId}/{status}")
    public void updateForUserOnlineState(@PathVariable("userId") String userId,@PathVariable("status")Integer status){
        User user=new User();
        user.setUserId(userId);
        user.setStatus(status);
        userService.updateUserByUserId(user);
    }

    /**
     * 更新月榜信息
     * @param userId
     * @param type   1 战斗力月榜 2 魅力月榜 3 幸福值月榜
     * @param recordValues 当前记录值 type 1 当前用户战斗力 2 当前用户魅力值 3 当前用户幸福值
     */
    @PostMapping("/updateCrunchies/{userId}/{type}/{recordValues}")
    public void updateCrunchies(@PathVariable("userId") String userId,@PathVariable("type")Integer type,@PathVariable("recordValues")Integer recordValues){
        userCrunchiesService.updateCrunchies(userId,type,recordValues);
    }

    @PostMapping("/getNewPlayerGuide/{userId}")
    public NewPlayerGuide getNewPlayerGuide(@PathVariable("userId") String userId){
        return newPlayerGuideService.getByUserId(userId);
    }

    @PostMapping("/getNewPlayerWeaponList/{userId}")
    public List<Map<String,Object>> getNewPlayerWeaponList(@PathVariable("userId") String userId){
        NewPlayerGuide guide=newPlayerGuideService.getByUserId(userId);
        if(guide!=null && guide.getTaskId()> NewPlayerGuideConstant.TASK_FIGHT_COPY_NEWER){
            return null;
        }
        UserRole userRole=userRoleService.getByUserId(userId);
        List<Map<String,Object>> list=new ArrayList<>();
        List<String> weaponIdList_ws= Arrays.asList("110016","110016","110018","110019","110020","110021","110022","110023","110024","110025");
        List<String> weaponIdList_ck=Arrays.asList("120016","120016","120018","120019","120020","120021","120022","120023","120024","120025");
        List<String> weaponIdList_gj=Arrays.asList("130016","130016","130018","130019","130020","130021","130022","130023","130024","130025");
        List<String> weaponIdList_fs=Arrays.asList("140016","140016","140018","140019","140020","140021","140022","140023","140024","140025");
        //卫士		刺客		工匠		法师
        //110016   120016  130016  140016
        //110017   120017  130017  140017
        //110018   120018  130018  140018
        //110019   120019  130019  140019
        //110020   120020  130020  140020
        //110021   120021  130021  140021
        //110022   120022  130022  140022
        //110023   120023  130023  140023
        //110024   120024  130024  140024
        //110025   120025  130025  140025
        List<String> weaponList=null;
        if(1==userRole.getRoleId() || 2==userRole.getRoleId()){
            weaponList=weaponIdList_ws;
        }
        if(3==userRole.getRoleId() || 4==userRole.getRoleId()){
            weaponList=weaponIdList_ck;
        }
        if(5==userRole.getRoleId() || 6==userRole.getRoleId()){
            weaponList=weaponIdList_gj;
        }
        if(7==userRole.getRoleId() || 8==userRole.getRoleId()){
            weaponList=weaponIdList_fs;
        }
        for(String weaponId:weaponList){
            ArsenalInfo info=arsenalService.getArsenalInfoByIdAndOriginal(weaponId);
            Map<String,Object> map=new HashMap<>();
            map.put("id",info.getIntensifyId());
            map.put("equipmentId",info.getId());
            map.put("limitTime",0);
            list.add(map);
        }
        return list;
    }
}
