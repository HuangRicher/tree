package com.tongzhu.treehouse.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.tongzhu.treehouse.constant.StatusConstant;
import com.tongzhu.treehouse.constant.WorkPositionConstant;
import com.tongzhu.treehouse.domain.User;
import com.tongzhu.treehouse.domain.UserFriend;
import com.tongzhu.treehouse.domain.UserGoods;
import com.tongzhu.treehouse.mapper.UserWorkPositionMapper;
import com.tongzhu.treehouse.mapper.ext.UserWorkPositionExtMapper;
import com.tongzhu.treehouse.model.*;
import com.tongzhu.treehouse.service.*;
import com.tongzhu.treehouse.service.vo.WorkPositionModel;
import com.tongzhu.treehouse.service.vo.WorkPositionVO;
import com.tongzhu.util.DateComputeUtil;
import com.tongzhu.util.DateUtil;
import com.tongzhu.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserWorkPositionServiceImpl implements UserWorkPositionService {

    @Autowired
    private UserWorkPositionMapper userWorkPositionMapper;

    @Autowired
    private UserWorkPositionExtMapper userWorkPositionExtMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private TreeHouseRoomService userWorkerService;

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private WorkIncomeSettingService workIncomeSettingService;

    @Autowired
    private UserWorkTypeService userWorkTypeService;

    @Autowired
    private WorkPositionSettingService workPositionSettingService;

    @Autowired
    private TreeHouseRoomService treeHouseRoomService;

    @Autowired
    private FriendService friendService;






    /**
     * 手动解锁工位
     * @param userId
     * @param positionId
     * @param workTypeId
     * @param order
     */
    @Transactional
    @Override
    public List<UserGoods> manualUnlock(String userId, String positionId, int workTypeId, int order)throws RuntimeException {
        UserWorkPosition workPosition=new UserWorkPosition();
        workPosition.setWorkPositionId(positionId);
        workPosition.setStatus(StatusConstant.WORK_POSITION_UNLOCK);
        userWorkPositionMapper.updateByPrimaryKeySelective(workPosition);
        WorkPositionSetting setting=workPositionSettingService.findByWorkTypeIdAndOrder(workTypeId,order);
        if(setting!=null && StringUtils.isNotBlank(setting.getUnlockGoods())){
            JSONArray array=JSONArray.parseArray(setting.getUnlockGoods());
            List<UserGoods> goodsList=new ArrayList<>();
            for(int i=0;i<array.size();i++){
                UserGoods map=new UserGoods();
                map.setGoodsId(array.getJSONObject(i).getString("goodsId"));
                map.setAmount(array.getJSONObject(i).getIntValue("amount"));
                goodsList.add(map);
            }
            //解锁消耗物品
            List<UserGoods> userGoods=userGoodsService.subUserGoods(userId,goodsList);
            return userGoods;
        }
        return null;
    }

    /**
     * 工位列表
     * @param userId
     * @param workTypeId
     * @return
     */
    @Override
    public List<UserWorkPosition> findByUserIdAndWorkTypeId(String userId, int workTypeId) {
        UserWorkPositionExample example=new UserWorkPositionExample();
        example.createCriteria().andUserIdEqualTo(userId).andWorkTypeIdEqualTo(workTypeId);
        example.setOrderByClause(" order_ ASC ");
        return userWorkPositionMapper.selectByExample(example);
    }

    /**
     * 工位列表
     * @param userId
     * @param workTypeId
     * @return
     */
    @Override
    public List<WorkPositionModel> findModelByUserIdAndWorkTypeId(String userId, int workTypeId) {

        List<UserWorkPosition> positionList=this.findByUserIdAndWorkTypeId(userId,workTypeId);
        List<WorkPositionModel> workPositionModelList=new ArrayList<>();
        for(UserWorkPosition position:positionList){
            WorkPositionModel positionModel=new WorkPositionModel();
            BeanUtils.copyProperties(position,positionModel);
            if(StringUtils.isNotBlank(position.getWorkerId())){
                User user=userService.findByUserId(position.getWorkerId());
                positionModel.setPortraitUrl(user.getPortraitUrl());
            }
            workPositionModelList.add(positionModel);
        }
        return workPositionModelList;
    }

    /**
     * xx用户的xx工种的工位列表详情
     * @param userId  用户ID
     * @param workTypeId  工种ID
     * @return
     */
    @Override
    public List<WorkPositionVO> findListByUserIdAndWorkTypeId(String userId, int workTypeId) {
        List<WorkPositionVO> workPositionVOList=new ArrayList<>();
        List<UserWorkPosition> positionList=this.findByUserIdAndWorkTypeId(userId,workTypeId);
        for(UserWorkPosition position:positionList){
            WorkPositionVO positionVO=new WorkPositionVO();
            positionVO.setWorkPositionId(position.getWorkPositionId());
            positionVO.setStatus(position.getStatus());
            positionVO.setUserId(position.getWorkerId());
            positionVO.setWorkStatus(position.getWorkStatus());
            if(StringUtils.isNotBlank(position.getWorkerId())){
                User user=userService.findByUserId(position.getWorkerId());
                positionVO.setPortraitUrl(user.getPortraitUrl());
                positionVO.setUserName(user.getName());
                TreeHouseRoom worker=userWorkerService.findWorkersByUserIdAndWorkerId(userId,position.getWorkerId());
                if(worker!=null){
                    positionVO.setGameFriend(worker.getIsGameFriend());
                    positionVO.setWxFriend(worker.getIsWxFriend());
                }
                if(position.getWorkStatus()!=null && position.getWorkStatus().equals(StatusConstant.WORK_POSITION_WORKING)){
                    int amount=0;
                    if(user!=null){
                        UserFriend friend=friendService.checkIsMyFriend(userId,position.getWorkerId());
                        int getCount=(int)Math.ceil(user.getSellingPrice()*0.001);
                        amount=(getCount>30?30:getCount);
                        if(friend!=null){
                            getCount=(int)Math.ceil(getCount*1.1);
                            amount=(getCount>30?30:getCount);
                        }
                    }
                    positionVO.setIncomeDetail(amount+"");
                }
            }
            WorkPositionSetting setting=workPositionSettingService.findByWorkTypeIdAndOrder(workTypeId,position.getOrder());
            if(setting!=null){
                List<Map> consume=JSON.parseArray(setting.getUnlockGoods(),Map.class);
                positionVO.setConsume(consume);
            }
            workPositionVOList.add(positionVO);
        }
        return workPositionVOList;
    }


    /**
     * 获取收益后重置打工开始时间
     * @param workPositionId
     */
    @Override
    public void resetForGetIncome(String workPositionId) {
        UserWorkPosition position=new UserWorkPosition();
        position.setWorkPositionId(workPositionId);
        position.setStartDate(new Date());
        position.setLastTime(0);
        userWorkPositionMapper.updateByPrimaryKeySelective(position);
    }


    /**
     * 打工中工位派工宅友更换
     * @param userId
     * @param workerId
     * @param workPositionId
     */
    @Transactional
    @Override
    public void assignWorkChange(String userId, String workerId, String workPositionId) {
        UserWorkPosition workPosition=userWorkPositionMapper.selectByPrimaryKey(workPositionId);
        //更新 tz_user_work_position
        this.updateByPositionIdForChangeWorker(workPositionId,workerId);

        //更新 tz_tree_house_room
        userWorkerService.updateByWorkIdForStatusChange(userId,workPosition.getWorkTypeId(),workPosition.getWorkerId(),workerId);

    }

    /**
     * 空闲工位派工
     * @param userId
     * @param workerId
     * @param workPositionId
     */
    @Transactional
    @Override
    public void assignWork(String userId, String workerId, String workPositionId) {
        UserWorkPosition workPosition=userWorkPositionMapper.selectByPrimaryKey(workPositionId);
        //更新 tz_user_work_position
        this.updateByPositionIdForWorker(workPositionId,workerId,workPosition);

        //更新 tz_tree_house_room
        userWorkerService.updateByWorkIdForStatus(userId,workPosition.getWorkTypeId(),workerId);

    }

    /**
     * 选择宅友派工
     * @param workPositionId
     * @param workerId
     * @param workPosition
     */
    @Override
    public void updateByPositionIdForWorker(String workPositionId, String workerId,UserWorkPosition workPosition) {
        UserWorkPosition position=new UserWorkPosition();
        if(workPosition.getStartDate()!=null && workPosition.getLastTime()!=null && workPosition.getLastTime()>0){
            LocalDateTime startDateTime= DateUtil.dateToLocalDateTime(workPosition.getStartDate());
            startDateTime.minusSeconds(workPosition.getLastTime());
            position.setStartDate(DateUtil.LocalDateTimeToDate(startDateTime));
        }else{
            position.setStartDate(new Date());
        }
        position.setWorkPositionId(workPositionId);
        position.setWorkerId(workerId);
        position.setWorkStatus(StatusConstant.WORK_POSITION_WORKING);
        userWorkPositionMapper.updateByPrimaryKeySelective(position);
    }

    /**
     * 更换派工宅友
     * @param workPositionId
     * @param workerId
     */
    @Override
    public void updateByPositionIdForChangeWorker(String workPositionId, String workerId) {
        UserWorkPosition position=new UserWorkPosition();
        position.setWorkPositionId(workPositionId);
        position.setWorkerId(workerId);
        userWorkPositionMapper.updateByPrimaryKeySelective(position);
    }

    /**
     * 批量插入工位
     * @param positionList
     */
    @Override
    public void insertBatch(List<UserWorkPosition> positionList) {
        userWorkPositionExtMapper.insertBatch(positionList);
    }

    @Override
    public UserWorkPosition findByWorkPositionId(String workPositionId) {
        return userWorkPositionMapper.selectByPrimaryKey(workPositionId);
    }

    /**
     * 查找xx用户的工位上的xx宅友
     * @param userId
     * @param workerId
     * @return
     */
    @Override
    public UserWorkPosition findByUserIdAndWorkerId(String userId, String workerId) {
        UserWorkPositionExample example=new UserWorkPositionExample();
        example.createCriteria().andWorkerIdEqualTo(workerId).andUserIdEqualTo(userId);
        List<UserWorkPosition> list=userWorkPositionMapper.selectByExample(example);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 批量初始化插入工位
     * @param workTypeId
     * @param userId
     */
    @Override
    public void initWorkPositionBatch(Integer workTypeId, String userId) {
        List<UserWorkPosition> positionList=new ArrayList<>(WorkPositionConstant.WORK_POSITION_COUNT);
        for(int i=1;i<= WorkPositionConstant.WORK_POSITION_COUNT;i++){
            UserWorkPosition position=new UserWorkPosition();
            position.setUserId(userId);
            position.setOrder(i);
            position.setWorkPositionId(UUIDUtil.generateUUID());
            position.setWorkTypeId(workTypeId);
            position.setWorkStatus(StatusConstant.WORK_POSITION_IDLE);
            if(i<=WorkPositionConstant.WORK_POSITION_AUTO_UNLOCK_COUNT){
                position.setStatus(StatusConstant.WORK_POSITION_UNLOCK);
            }else{
                position.setStatus(StatusConstant.WORK_POSITION_LOCK);
            }
            positionList.add(position);
        }
        this.insertBatch(positionList);

    }

    @Transactional
    @Override
    public void changeWorkerInWorking(String userId, String workerId, String workPositionId) {
        UserWorkPosition workPosition=userWorkPositionMapper.selectByPrimaryKey(workPositionId);
        //更新 tz_user_work_position
        this.updateByPositionIdForChangeWorker(workPositionId,workerId);

        UserWorkPosition position=this.findByWorkerId(workerId);
        if(position!=null && position.getStartDate()!=null){
            Date startDate=position.getStartDate();
            UserWorkPosition posi=new UserWorkPosition();
            posi.setWorkPositionId(position.getWorkPositionId());
            posi.setLastTime((int)(System.currentTimeMillis()-startDate.getTime())/1000);
            posi.setStartDate(null);
            posi.setStopDate(new Date());
            posi.setWorkStatus(StatusConstant.WORK_POSITION_IDLE);
            posi.setWorkerId(null);
            userWorkPositionMapper.updateByPrimaryKeySelective(posi);
        }
        //更新 tz_tree_house_room
        userWorkerService.updateByWorkIdForStatusChange(userId,workPosition.getWorkTypeId(),workPosition.getWorkerId(),workerId);
    }

    @Override
    public void updateByWorkerIdForLeave(String workerId) {
        UserWorkPosition position=this.findByWorkerId(workerId);
        if(position!=null && position.getStartDate()!=null){
            User user=userService.findByUserId(workerId);
            Date startDate=position.getStartDate();
            UserWorkPosition posi=new UserWorkPosition();
            int sumTime= DateComputeUtil.countMinutesBetweenTwoDate(position.getStartDate(),new Date());
            UserFriend friend=friendService.checkIsMyFriend(position.getUserId(),position.getWorkerId());
            int getCount=(int)Math.ceil(user.getSellingPrice()*0.001);
            int amount=(getCount>30?30:getCount);
            if(friend!=null){
                getCount=(int)Math.ceil(getCount*1.1);
                amount=(getCount>30?30:getCount);
            }
            int incomeCount=amount*sumTime;
            posi.setWorkPositionId(position.getWorkPositionId());
            posi.setLastTime(incomeCount);
            posi.setStartDate(null);
            posi.setStopDate(new Date());
            posi.setWorkStatus(StatusConstant.WORK_POSITION_IDLE);
            posi.setWorkerId(null);
            posi.setStatus(position.getStatus());
            posi.setUserId(position.getUserId());
            posi.setOrder(position.getOrder());
            posi.setWorkTypeId(position.getWorkTypeId());
            userWorkPositionMapper.updateByPrimaryKey(posi);
        }
    }

    @Override
    public void updateForReleaseWorker(String userId, String workerId, int lastTime, Date date) {
        userWorkPositionExtMapper.updateForReleaseWorker(userId,workerId,lastTime,date);
    }

    @Override
    public WorkPositionVO findUserWorkPositionByPositionId(String userId,String workPositionId) {
        UserWorkPosition position=this.findByWorkPositionId(workPositionId);
        WorkPositionVO positionVO=new WorkPositionVO();
        positionVO.setWorkPositionId(position.getWorkPositionId());
        positionVO.setStatus(position.getStatus());
        positionVO.setUserId(position.getWorkerId());
        positionVO.setWorkStatus(position.getWorkStatus());
        if(StringUtils.isNotBlank(position.getWorkerId())){
            User user=userService.findByUserId(position.getWorkerId());
            positionVO.setPortraitUrl(user.getPortraitUrl());
            positionVO.setUserName(user.getName());
            TreeHouseRoom worker=userWorkerService.findWorkersByUserIdAndWorkerId(userId,position.getWorkerId());
            if(worker!=null){
                positionVO.setGameFriend(worker.getIsGameFriend());
                positionVO.setWxFriend(worker.getIsWxFriend());
            }

            if(position.getWorkStatus()!=null && position.getWorkStatus().equals(StatusConstant.WORK_POSITION_WORKING)){
                UserWorkType userWorkType=userWorkTypeService.findByUserIdAndWorkTypeId(userId,position.getWorkTypeId());
                if(userWorkType!=null && user!=null){
                    int amount=0;
                    UserFriend friend=friendService.checkIsMyFriend(userId,position.getWorkerId());
                    int getCount=(int)Math.ceil(user.getSellingPrice()*0.001);
                    amount=(getCount>30?30:getCount);
                    if(friend!=null){
                        getCount=(int)Math.ceil(getCount*1.1);
                        amount=(getCount>30?30:getCount);
                    }
                    positionVO.setIncomeDetail(amount+"");

                }
            }
        }
        WorkPositionSetting setting=workPositionSettingService.findByWorkTypeIdAndOrder(position.getWorkTypeId(),position.getOrder());
        if(setting!=null){
            List<Map> consume=JSON.parseArray(setting.getUnlockGoods(),Map.class);
            positionVO.setConsume(consume);
        }

        return positionVO;
    }

    @Transactional
    @Override
    public void makeWorkPositionIdle(String userId, String workerId) {
        UserWorkPosition position=this.findByUserIdAndWorkerId(userId,workerId);
        int lastTime=0;
        if(position!=null && position.getStartDate()!=null){
            Instant instant = position.getStartDate().toInstant();
            ZoneId zone = ZoneId.systemDefault();
            LocalDateTime loginDateTime = LocalDateTime.ofInstant(instant, zone);
            LocalDateTime currentDateTime=LocalDateTime.now();
            Duration duration=Duration.between(currentDateTime,loginDateTime);
            lastTime=(int)duration.toMinutes()*60;
        }
        treeHouseRoomService.makeWorkerIdle(userId,workerId);
        this.updateForReleaseWorker(userId,workerId,lastTime,new Date());
    }


    public UserWorkPosition findByWorkerId(String workerId){
        UserWorkPositionExample example=new UserWorkPositionExample();
        example.createCriteria().andWorkerIdEqualTo(workerId);
        List<UserWorkPosition> positions=userWorkPositionMapper.selectByExample(example);
        if(positions!=null && positions.size()>0){
            return positions.get(0);
        }
        return null;
    }
}
