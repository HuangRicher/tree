package com.tongzhu.treehouse.service.impl;

import com.tongzhu.constant.GoodsConstant;
import com.tongzhu.treehouse.constant.StatusConstant;
import com.tongzhu.treehouse.constant.WorkPositionConstant;
import com.tongzhu.treehouse.constant.WorkTypeConstant;
import com.tongzhu.treehouse.domain.User;
import com.tongzhu.treehouse.domain.UserFriend;
import com.tongzhu.treehouse.domain.UserGoods;
import com.tongzhu.treehouse.mapper.UserWorkTypeMapper;
import com.tongzhu.treehouse.mapper.ext.UserWorkTypeExtMapper;
import com.tongzhu.treehouse.model.TreeHouseLevelSetting;
import com.tongzhu.treehouse.model.UserWorkPosition;
import com.tongzhu.treehouse.model.UserWorkType;
import com.tongzhu.treehouse.model.UserWorkTypeExample;
import com.tongzhu.treehouse.service.*;
import com.tongzhu.util.DateComputeUtil;
import com.tongzhu.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserWorkTypeServiceImpl implements UserWorkTypeService {

    @Autowired
    private UserWorkTypeMapper userWorkTypeMapper;

    @Autowired
    private UserWorkTypeExtMapper userWorkTypeExtMapper;

    @Autowired
    private UserWorkPositionService userWorkPositionService;

    @Autowired
    private WorkIncomeSettingService workIncomeSettingService;

    @Autowired
    private UserService userService;

    @Autowired
    private TreeHouseLevelSettingService treeHouseLevelSettingService;

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private FriendService friendService;




    @Override
    public void batchAdd(List<UserWorkType> userWorkTypes) {
        userWorkTypeExtMapper.insertBatch(userWorkTypes);
    }

    @Override
    public int add(UserWorkType userWorkType) {
        return userWorkTypeMapper.insertSelective(userWorkType);
    }

    @Override
    public List<UserWorkType> findByUserId(String userId) {
        UserWorkTypeExample example=new UserWorkTypeExample();
        example.createCriteria().andUserIdEqualTo(userId);
        example.setOrderByClause(" order_ ASC ");
        return userWorkTypeMapper.selectByExample(example);
    }

    @Override
    public UserWorkType findByUserIdAndWorkTypeId(String userId, int workTypeId) {
        UserWorkTypeExample example=new UserWorkTypeExample();
        example.createCriteria().andUserIdEqualTo(userId).andWorkTypeIdEqualTo(workTypeId);
        List<UserWorkType> workTypeList=userWorkTypeMapper.selectByExample(example);
        if(workTypeList!=null && workTypeList.size()>0){
            return workTypeList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public int updateStatusByUserIdAndWorkTypeId(String userId, int workTypeId, int status) {
        UserWorkTypeExample example=new UserWorkTypeExample();
        example.createCriteria().andUserIdEqualTo(userId).andWorkTypeIdEqualTo(workTypeId);
        UserWorkType userWorkType=new UserWorkType();
        userWorkType.setStatus(status);
        return userWorkTypeMapper.updateByExampleSelective(userWorkType,example);
    }

    /**
     * 树屋升级后打工场景和工位相应解锁和升级
     * @param treeHouseGrade  树屋等级
     * @param userId   用户Id
     */
    @Override
    public void upgradeWorkType(int treeHouseGrade, String userId) {
        TreeHouseLevelSetting setting=treeHouseLevelSettingService.findByTreeLevel(treeHouseGrade);
        if(setting!=null){
            if((setting.getWorkTypeId()!=null && setting.getWorkTypeId()>0) ||
                    (setting.getWorkTypeLevel()!=null && setting.getWorkTypeLevel()>0)){
                if(setting.getWorkTypeLevel()== WorkTypeConstant.WORK_TYPE_LEVEL_ONE){
                    UserWorkType userWorkType=new UserWorkType();
                    userWorkType.setLevel(WorkTypeConstant.WORK_TYPE_LEVEL_ONE);
                    userWorkType.setStatus(StatusConstant.WORK_TYPE_UNLOCK);
                    UserWorkTypeExample example=new UserWorkTypeExample();
                    example.createCriteria().andUserIdEqualTo(userId).andWorkTypeIdEqualTo(setting.getWorkTypeId());
                    userWorkTypeMapper.updateByExampleSelective(userWorkType,example);
                    List<UserWorkType> workTypeList=userWorkTypeMapper.selectByExample(example);
                    if(workTypeList!=null && workTypeList.size()>0){
                        //级别到Lv.1建立工位（五个）
                        List<UserWorkPosition> positionList=new ArrayList<>(WorkPositionConstant.WORK_POSITION_COUNT);
                        for(int i=1;i<= WorkPositionConstant.WORK_POSITION_COUNT;i++){
                            UserWorkPosition position=new UserWorkPosition();
                            position.setUserId(userId);
                            position.setOrder(i);
                            position.setWorkPositionId(UUIDUtil.generateUUID());
                            position.setWorkTypeId(workTypeList.get(0).getWorkTypeId());
                            if(i<=WorkPositionConstant.WORK_POSITION_AUTO_UNLOCK_COUNT){
                                position.setStatus(StatusConstant.WORK_POSITION_UNLOCK);
                            }else{
                                position.setStatus(StatusConstant.WORK_POSITION_LOCK);
                            }
                            positionList.add(position);
                        }
                        userWorkPositionService.insertBatch(positionList);
                    }
                }else{
                    UserWorkType userWorkType=new UserWorkType();
                    userWorkType.setLevel(setting.getWorkTypeLevel());
                    UserWorkTypeExample example=new UserWorkTypeExample();
                    example.createCriteria().andUserIdEqualTo(userId).andWorkTypeIdEqualTo(setting.getWorkTypeId());
                    userWorkTypeMapper.updateByExampleSelective(userWorkType,example);
                }
            }
        }
    }


    /**
     * 一次获取xx工种下的所有收益
     * @param userId 用户ID
     * @param workTypeId 工种ID
     * @return
     */
    @Transactional
    @Override
    public List<UserGoods> getWorkIncomeAll(String userId, int workTypeId) {
        List<UserWorkPosition> workPositionList=userWorkPositionService.findByUserIdAndWorkTypeId(userId,workTypeId);
        int sumCount=0;
        for(UserWorkPosition position:workPositionList){
            int sumAmount=0;
            if(position.getStatus()==StatusConstant.WORK_POSITION_UNLOCK){
                int totalWorkTime=0;
                //计算工作时间
               if(position.getWorkStatus()==StatusConstant.WORK_POSITION_WORKING &&
                       position.getStartDate()!=null && DateComputeUtil.checkHaveIncome(position.getStartDate())){
                   totalWorkTime+=DateComputeUtil.countMinutesBetweenTwoDate(new Date(),position.getStartDate());
               }
               if(totalWorkTime>0){
                   UserWorkType workType=this.findByUserIdAndWorkTypeId(userId,workTypeId);
                   if(workType!=null){
                       User user=userService.findByUserId(position.getWorkerId());
                       UserFriend friend=friendService.checkIsMyFriend(userId,position.getWorkerId());
                       int getCount=(int)Math.ceil(user.getSellingPrice()*0.001);
                       int amount=(getCount>30?30:getCount);
                       if(friend!=null){
                           getCount=(int)Math.ceil(getCount*1.1);
                           amount=(getCount>30?30:getCount);
                       }
                       sumAmount+=(amount*totalWorkTime);
                       //获得收益后重新计算
                       userWorkPositionService.resetForGetIncome(position.getWorkPositionId());
                   }
               }
            }
            sumAmount+=position.getLastTime()==null?0:position.getLastTime();
            sumCount+=sumAmount;
        }
        UserGoods map=new UserGoods();
        map.setGoodsId(GoodsConstant.GOODS_MONEY);
        map.setAmount(sumCount>100000?100000:sumCount);
        List<UserGoods> goodsList=new ArrayList<>();
        goodsList.add(map);
        userGoodsService.addUserGoods(userId,goodsList);

        return goodsList;
    }

    /**
     * 升级工种
     * @param userId
     * @param workTypeId
     * @param level
     */
    @Override
    public void upgradeLevel(String userId, Integer workTypeId,Integer level) {
        UserWorkType userWorkType=new UserWorkType();
        userWorkType.setLevel(level);
        UserWorkTypeExample example=new UserWorkTypeExample();
        example.createCriteria().andUserIdEqualTo(userId).andWorkTypeIdEqualTo(workTypeId);
        userWorkTypeMapper.updateByExampleSelective(userWorkType,example);
    }
}
