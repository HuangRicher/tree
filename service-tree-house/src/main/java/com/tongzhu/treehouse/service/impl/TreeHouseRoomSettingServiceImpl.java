package com.tongzhu.treehouse.service.impl;

import com.tongzhu.except.CheckException;
import com.tongzhu.treehouse.mapper.TreeHouseRoomSettingMapper;
import com.tongzhu.treehouse.mapper.vo.TreeHouseRoomCountVO;
import com.tongzhu.treehouse.model.TreeHouse;
import com.tongzhu.treehouse.model.TreeHouseRoomSetting;
import com.tongzhu.treehouse.model.TreeHouseRoomSettingExample;
import com.tongzhu.treehouse.service.TreeHouseRoomService;
import com.tongzhu.treehouse.service.TreeHouseRoomSettingService;
import com.tongzhu.treehouse.service.TreeHouseService;
import com.tongzhu.treehouse.service.UserGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeHouseRoomSettingServiceImpl implements TreeHouseRoomSettingService {

    @Autowired
    private TreeHouseRoomSettingMapper treeHouseRoomSettingMapper;

    @Autowired
    private TreeHouseRoomService treeHouseRoomService;

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private TreeHouseService treeHouseService;


    /**
     * 根据繁荣度查找相关配置
     * @param flourishingRate
     * @return
     */
    @Override
    public TreeHouseRoomSetting getByFlourishingRate(int flourishingRate) {
        TreeHouseRoomSettingExample example=new TreeHouseRoomSettingExample();
        example.setOrderByClause(" flourishing_rate DESC ");
        example.createCriteria().andFlourishingRateLessThanOrEqualTo(flourishingRate);
        List<TreeHouseRoomSetting> settings=treeHouseRoomSettingMapper.selectByExample(example);
        if(settings!=null && settings.size()>0){
            return settings.get(0);
        }
        return null;
    }


    /**
     * 根据设定值每日消耗木材维持树屋房间上限数
     */
    @Override
    public void dailyConsumeBySetting() {
        List<TreeHouseRoomCountVO> rooms=treeHouseRoomService.countByUserId();
        for(TreeHouseRoomCountVO roomCountVO:rooms){
            TreeHouseRoomSetting setting=this.findByWorkerCount(roomCountVO.getAmount());
            if(setting.getDayUse()>0){
                //消耗木材
                try {
                    //木材不足时将会扣除繁荣度，扣除比例10：1
                    treeHouseService.updateTressHouseFlourishingRateByUserId(roomCountVO.getUserId(),setting.getDayUse()*10);
                    //繁荣度下降相应要检查树屋房间数，降级后要减相应房间上限数
                    TreeHouse treeHouse=treeHouseService.findByUserId(roomCountVO.getUserId());
                    if(treeHouse.getFlourishingRate()<setting.getFlourishingRate()){
                        //减房编号最大的房间
                        treeHouseRoomService.deleteFinalRoom(roomCountVO.getUserId());
                    }
                }catch (CheckException e){
                    //木材不足时将会扣除繁荣度，扣除比例10：1
                    treeHouseService.updateTressHouseFlourishingRateByUserId(roomCountVO.getUserId(),setting.getDayUse()*10);
                    //繁荣度下降相应要检查树屋房间数，降级后要减相应房间上限数
                    TreeHouse treeHouse=treeHouseService.findByUserId(roomCountVO.getUserId());
                    if(treeHouse.getFlourishingRate()<setting.getFlourishingRate()){
                        //减房编号最大的房间
                        treeHouseRoomService.deleteFinalRoom(roomCountVO.getUserId());
                    }
                }
            }
        }
    }


    /**
     * 根据房间数上限查找相关配置
     * @return
     */
    @Override
    public TreeHouseRoomSetting findByWorkerCount(int count) {
        TreeHouseRoomSettingExample example=new TreeHouseRoomSettingExample();
        example.createCriteria().andWorkerCountEqualTo(count);
        List<TreeHouseRoomSetting> settings=treeHouseRoomSettingMapper.selectByExample(example);
        if(settings!=null && settings.size()>0){
            return settings.get(0);
        }else {
            return null;
        }
    }


}
