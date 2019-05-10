package com.tongzhu.treehouse.controller;

import com.alibaba.fastjson.JSONArray;
import com.tongzhu.common.BaseReturn;
import com.tongzhu.constant.GoodsConstant;
import com.tongzhu.treehouse.model.TreeHouseLevelSetting;
import com.tongzhu.treehouse.model.WorkIncomeSetting;
import com.tongzhu.treehouse.service.TreeHouseLevelSettingService;
import com.tongzhu.treehouse.service.WorkIncomeSettingService;
import com.tongzhu.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/generateUtil")
public class GenerateUtilController {

    @Autowired
    private WorkIncomeSettingService workIncomeSettingService;

    @Autowired
    private TreeHouseLevelSettingService treeHouseLevelSettingService;

    @GetMapping("/generateWorkIncomeSetting")
    public BaseReturn generateWorkIncomeSetting(){
        for(int i=1;i<6;i++){
            for(int j=1;j<6;j++){
                for(int m=1;m<6;m++){
                    WorkIncomeSetting setting=new WorkIncomeSetting();
                    setting.setId(UUIDUtil.generateUUID());
                    setting.setWorkTypeId(i);
                    setting.setPositionId(j);
                    setting.setWorkTypeLevel(m);
                    List<Map<String,Object>> list=new ArrayList<>();
                    Map<String,Object> map=new HashMap<>();
                    map.put("goodsId",1);
                    map.put("amount",m*5+45f);
                    list.add(map);
                    setting.setIncomeGoods(JSONArray.toJSONString(list));
                    setting.setGameFriendMoreCount(0.04f+m*0.01f);
                    workIncomeSettingService.add(setting);
                }
            }
        }
        return new BaseReturn("success");
    }

    @GetMapping("/generateTreeHouseLevelSetting")
    public BaseReturn generateTreeHouseLevelSetting(){
        List<TreeHouseLevelSetting> settings=treeHouseLevelSettingService.findAll();
        for(TreeHouseLevelSetting setting:settings){
            TreeHouseLevelSetting se=new TreeHouseLevelSetting();
            se.setId(setting.getId());
            if(setting.getCultureValue()!=null && setting.getCultureValue()>0){
                List<Map<String,Object>> list=new ArrayList<>();
                Map<String,Object> bcg1=new HashMap<>();
                bcg1.put("goodsId",GoodsConstant.GOODS_MONEY);
                bcg1.put("amount",5000+setting.getLevel()*20);
                list.add(bcg1);
                Map<String,Object> bcg2=new HashMap<>();
                bcg2.put("goodsId", GoodsConstant.GOODS_SUNSHINE);
                bcg2.put("amount",1);
                list.add(bcg2);
                se.setUpgradeConsumeGoods(JSONArray.toJSONString(list));

                //if(setting.getBreakValue()==null) {
                    List<Map<String, Object>> listAward = new ArrayList<>();
                    Map<String, Object> award = new HashMap<>();
                    award.put("goodsId", GoodsConstant.GOODS_VIGOUR);
                    award.put("amount", 1);
                    listAward.add(award);
                    se.setUpgradeAward(JSONArray.toJSONString(listAward));
                //}
            }
            treeHouseLevelSettingService.update(se);
        }
        return new BaseReturn("success");
    }
}
