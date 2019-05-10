package com.tongzhu.treehouse.service.impl;

import com.tongzhu.treehouse.constant.StatusConstant;
import com.tongzhu.treehouse.constant.WorkPositionConstant;
import com.tongzhu.treehouse.domain.User;
import com.tongzhu.treehouse.domain.UserFriend;
import com.tongzhu.treehouse.mapper.WorkTypeMapper;
import com.tongzhu.treehouse.model.UserWorkType;
import com.tongzhu.treehouse.model.WorkType;
import com.tongzhu.treehouse.model.WorkTypeExample;
import com.tongzhu.treehouse.service.*;
import com.tongzhu.treehouse.service.vo.UserWorkTypeVO;
import com.tongzhu.treehouse.service.vo.WorkPositionModel;
import com.tongzhu.treehouse.service.vo.WorkPositionVO;
import com.tongzhu.util.DateComputeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WorkTypeServiceImpl implements WorkTypeService {

    @Autowired
    private WorkTypeMapper workTypeMapper;

    @Autowired
    private UserWorkPositionService userWorkPositionService;

    @Autowired
    private UserWorkTypeService userWorkTypeService;

    @Autowired
    private WorkIncomeSettingService workIncomeSettingService;

    @Autowired
    private UserService userService;

    @Autowired
    private FriendService friendService;



    @Override
    public List<WorkType> findAll() {
        WorkTypeExample example=new WorkTypeExample();
        return workTypeMapper.selectByExample(example);
    }


    /**
     * xx用户的工种列表
     * @param userId
     * @return
     */
    @Override
    public List<UserWorkTypeVO> listUserWorkType(String userId) {
        List<WorkType> workTypes=this.findAll();
        List<UserWorkTypeVO> userWorkTypeVOList=new ArrayList<>();
        for(WorkType workType:workTypes){
            UserWorkTypeVO userWorkTypeVO=new UserWorkTypeVO();
            userWorkTypeVO.setWorkTypeId(workType.getId());
            UserWorkType userWorkType=userWorkTypeService.findByUserIdAndWorkTypeId(userId,workType.getId());
            if(userWorkType!=null){
                userWorkTypeVO.setLevel(userWorkType.getLevel());
                userWorkTypeVO.setStatus(userWorkType.getStatus());
            }else{
                userWorkTypeVO.setStatus(StatusConstant.WORK_TYPE_LOCK);
            }
            userWorkTypeVO.setUnlockLevel(workType.getUnlockLevel());

            //工位详情
            List<WorkPositionModel> positionList=userWorkPositionService.findModelByUserIdAndWorkTypeId(userId,workType.getId());

            List<WorkPositionVO> workPositionVOList=new ArrayList<>();
            //判断是否有收益
            boolean haveIncome=false;
            int count=0;
            int incomeCount=0;
            for(WorkPositionModel positionModel:positionList){
                WorkPositionVO positionVO=new WorkPositionVO();
                positionVO.setStatus(positionModel.getStatus());
                positionVO.setPortraitUrl(positionModel.getPortraitUrl());
                positionVO.setWorkStatus(positionModel.getWorkStatus());
                positionVO.setOrder(positionModel.getOrder());
                workPositionVOList.add(positionVO);
                if(positionModel.getStatus().equals(WorkPositionConstant.STATUS_UNLOCK) &&
                        positionModel.getWorkStatus().equals(WorkPositionConstant.WORK_STATUS_WORKING)){
                    if(userWorkType!=null){
                        User user=userService.findByUserId(positionModel.getWorkerId());
                        if(user!=null){
                            int sumTime=DateComputeUtil.countMinutesBetweenTwoDate(positionModel.getStartDate(),new Date());
                            UserFriend friend=friendService.checkIsMyFriend(userId,positionModel.getWorkerId());
                            int getCount=(int)Math.ceil(user.getSellingPrice()*0.001);
                            int amount=(getCount>30?30:getCount);
                            if(friend!=null){
                                getCount=(int)Math.ceil(getCount*1.1);
                                amount=(getCount>30?30:getCount);
                            }
                            incomeCount+=(amount*sumTime);
                            count+=amount;
                        }
                    }
                }
                if(positionModel.getLastTime()!=null && positionModel.getLastTime()>0){
                    incomeCount+=positionModel.getLastTime();
                }
            }
            if(incomeCount>=100){
                haveIncome=true;
            }
            userWorkTypeVO.setTotalYieldRate(count+"");
            userWorkTypeVO.setPositionList(workPositionVOList);
            userWorkTypeVO.setHaveIncome(haveIncome);
            userWorkTypeVOList.add(userWorkTypeVO);
        }
        return userWorkTypeVOList;
    }

    @Override
    public WorkType findByWorkTypeId(int workTypeId) {
        return workTypeMapper.selectByPrimaryKey(workTypeId);
    }

    @Override
    public UserWorkTypeVO findUserWorkTypeByWorkTypeId(String userId, Integer workTypeId) {
       WorkType workType=this.findByWorkTypeId(workTypeId);
        UserWorkTypeVO userWorkTypeVO=new UserWorkTypeVO();
        userWorkTypeVO.setWorkTypeId(workType.getId());
        UserWorkType userWorkType=userWorkTypeService.findByUserIdAndWorkTypeId(userId,workType.getId());
        if(userWorkType!=null){
            userWorkTypeVO.setLevel(userWorkType.getLevel());
            userWorkTypeVO.setStatus(userWorkType.getStatus());
        }else{
            userWorkTypeVO.setStatus(StatusConstant.WORK_TYPE_LOCK);
        }
        userWorkTypeVO.setUnlockLevel(workType.getUnlockLevel());

        //工位详情
        List<WorkPositionModel> positionList=userWorkPositionService.findModelByUserIdAndWorkTypeId(userId,workType.getId());

        List<WorkPositionVO> workPositionVOList=new ArrayList<>();
        //判断是否有收益
        boolean haveIncome=false;
        int count=0;
        int incomeCount=0;
        for(WorkPositionModel positionModel:positionList){
            WorkPositionVO positionVO=new WorkPositionVO();
            positionVO.setStatus(positionModel.getStatus());
            positionVO.setPortraitUrl(positionModel.getPortraitUrl());
            positionVO.setWorkStatus(positionModel.getWorkStatus());
            positionVO.setOrder(positionModel.getOrder());
            workPositionVOList.add(positionVO);
            if(positionModel.getStatus().equals(WorkPositionConstant.STATUS_UNLOCK) && positionModel.getWorkStatus().equals(WorkPositionConstant.WORK_STATUS_WORKING)){
                if(userWorkType!=null){
                    User user=userService.findByUserId(positionModel.getWorkerId());
                    if(user!=null){
                        int sumTime=DateComputeUtil.countMinutesBetweenTwoDate(positionModel.getStartDate(),new Date());
                        UserFriend friend=friendService.checkIsMyFriend(userId,positionModel.getWorkerId());
                        int getCount=(int)Math.ceil(user.getSellingPrice()*0.001);
                        int amount=(getCount>30?30:getCount);
                        if(friend!=null){
                            getCount=(int)Math.ceil(getCount*1.1);
                            amount=(getCount>30?30:getCount);
                        }
                        incomeCount+=(amount*sumTime);
                        count+=amount;
                    }
                }
            }
            if(positionModel.getLastTime()!=null && positionModel.getLastTime()>0){
                incomeCount+=positionModel.getLastTime();
            }
        }
        if(incomeCount>=100){
            haveIncome=true;
        }
        userWorkTypeVO.setTotalYieldRate(count+"");
        userWorkTypeVO.setPositionList(workPositionVOList);
        userWorkTypeVO.setHaveIncome(haveIncome);


        return userWorkTypeVO;
    }


}
