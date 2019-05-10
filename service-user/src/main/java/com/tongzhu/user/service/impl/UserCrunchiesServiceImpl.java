package com.tongzhu.user.service.impl;

import com.tongzhu.user.constant.RanklingListConstant;
import com.tongzhu.user.mapper.UserCrunchiesMapper;
import com.tongzhu.user.mapper.ext.UserCrunchiesExtMapper;
import com.tongzhu.user.mapper.vo.UserRankingVO;
import com.tongzhu.user.model.UserCrunchies;
import com.tongzhu.user.service.UserCrunchiesService;
import com.tongzhu.user.service.UserService;
import com.tongzhu.util.DateUtil;
import com.tongzhu.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/11/24 0024.
 */

@Service
public class UserCrunchiesServiceImpl implements UserCrunchiesService {

    @Autowired
    private UserCrunchiesMapper userCrunchiesMapper;

    @Autowired
    private UserCrunchiesExtMapper userCrunchiesExtMapper;

    @Autowired
    private UserService userService;


    @Override
    public void updateCrunchies(String userId, int type, int recordValues) {
        synchronized (userId.intern()) {
            UserCrunchies userCrunchies = userCrunchiesExtMapper.getCrunchiesByUserIdAndType(userId, type, new Date());
            if (userCrunchies == null) {
                userCrunchies = userCrunchiesExtMapper.getCrunchiesByUserIdAndType(userId, type, DateUtil.getLastMonth(new Date()));
                if (userCrunchies == null) {
                    userCrunchies = new UserCrunchies();
                    userCrunchies.setId(UUIDUtil.generateUUID());
                    userCrunchies.setMinimum(0);
                    userCrunchies.setMaximum(recordValues);
                    userCrunchies.setType(type);
                    userCrunchies.setCreateDate(new Date());
                    userCrunchies.setUserId(userId);
                    userCrunchiesMapper.insertSelective(userCrunchies);
                } else {
                    UserCrunchies userCrunchiesNew = new UserCrunchies();
                    userCrunchiesNew.setId(UUIDUtil.generateUUID());
                    if (type == RanklingListConstant.MONTH_ZS_NUM_TYPE) {
                        userCrunchiesNew.setMinimum(userCrunchies.getMaximum());
                        userCrunchiesNew.setMaximum(userCrunchies.getMaximum() + recordValues);
                    } else {
                        userCrunchiesNew.setMinimum(userCrunchies.getMaximum());
                        if (recordValues > userCrunchies.getMaximum()) {
                            userCrunchiesNew.setMaximum(recordValues);
                        } else {
                            userCrunchiesNew.setMaximum(userCrunchies.getMaximum());
                        }
                    }
                    userCrunchiesNew.setType(type);
                    userCrunchiesNew.setCreateDate(new Date());
                    userCrunchiesNew.setUserId(userId);
                    userCrunchiesMapper.insertSelective(userCrunchiesNew);
                }
            } else {
                if (type == RanklingListConstant.MONTH_ZS_NUM_TYPE) {
                    userCrunchies.setMaximum(userCrunchies.getMaximum() + recordValues);
                    userCrunchiesMapper.updateByPrimaryKey(userCrunchies);
                } else {
                    if (recordValues > userCrunchies.getMaximum()) {
                        userCrunchies.setMaximum(recordValues);
                        userCrunchiesMapper.updateByPrimaryKey(userCrunchies);
                    }
                }
            }
        }
    }

    @Override
    public List<UserRankingVO> fightingMonthRankingList(int count, int type) {
        return userCrunchiesExtMapper.fightingMonthRankingList(count, type);
    }


}
