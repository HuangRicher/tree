package com.tongzhu.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tongzhu.common.Pager;
import com.tongzhu.user.constant.MailConstant;
import com.tongzhu.user.mapper.UserMailSingleMapper;
import com.tongzhu.user.mapper.ext.UserMailSingleExtMapper;
import com.tongzhu.user.model.UserMailSingle;
import com.tongzhu.user.service.ChatMessageService;
import com.tongzhu.user.service.UserMailSingleService;
import com.tongzhu.user.service.vo.UserMailSingleVO;
import com.tongzhu.util.DateComputeUtil;
import com.tongzhu.util.ObjectUtil;
import com.tongzhu.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Administrator on 2018/11/8 0008.
 */
@Service
public class UserMailSingleServiceImpl implements UserMailSingleService {

    @Autowired
    private UserMailSingleMapper userMailSingleMapper;

    @Autowired
    private UserMailSingleExtMapper userMailSingleExtMapper;

    @Autowired
    private ChatMessageService chatMessageService;

    @Override
    public int insertSelective(UserMailSingle userMailSingle) {
        LocalDateTime dateTime = LocalDateTime.now();
        userMailSingle.setId(UUIDUtil.generateUUID());
        userMailSingle.setSendTime(new Date());
        userMailSingle.setRead(MailConstant.MAIL_READ_NO);
        userMailSingle.setReceive(MailConstant.MAIL_RECEIVE_NO);
        if (userMailSingle.getExpireTime() == null)
            userMailSingle.setExpireTime(DateComputeUtil.localDateTimeToDate(dateTime.plusDays(15))); // 十五天

        Map<String, Object> object = ObjectUtil.getRedTipMap("mail");
        chatMessageService.sendMessageToSomeBody(userMailSingle.getReceiverId(), JSON.toJSONString(object));
        return userMailSingleMapper.insertSelective(userMailSingle);
    }

    @Override
    public UserMailSingle selectByPrimaryKey(String id) {
        return userMailSingleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(UserMailSingle userMailSingle) {
        return userMailSingleMapper.updateByPrimaryKeySelective(userMailSingle);
    }

    @Override
    public Pager<UserMailSingleVO> selectUserMailSingleVOList(String userId, int pageNum, int pageSize, Integer receive) {
        Page<UserMailSingleVO> page = PageHelper.startPage(pageNum, pageSize);
        userMailSingleExtMapper.selectUserMailSingleVOList(userId, MailConstant.MAIL_STATUS_NORMAL, receive);
        return new Pager<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), page);
    }

    @Override
    public int delete(String id) {
        return userMailSingleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void receiveAccessoryAll(String userId) {
        userMailSingleExtMapper.updateMailAll(userId, MailConstant.MAIL_RECEIVE_YES, MailConstant.MAIL_READ_YES);
        userMailSingleExtMapper.updateMailSingle(userId, MailConstant.MAIL_RECEIVE_YES, MailConstant.MAIL_READ_YES);

    }

    @Override
    public int userMailMessage(String userId) {
        int i = userMailSingleExtMapper.userMailMessage(userId, MailConstant.MAIL_STATUS_NORMAL, MailConstant.MAIL_RECEIVE_NO);
        return i;
    }

    @Override
    public void batchInsert(List<UserMailSingle> list) {
        userMailSingleExtMapper.batchInsert(list);
    }
}
