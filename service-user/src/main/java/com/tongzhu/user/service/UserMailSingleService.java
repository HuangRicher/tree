package com.tongzhu.user.service;

import com.tongzhu.common.Pager;
import com.tongzhu.user.model.UserMailSingle;
import com.tongzhu.user.service.vo.UserMailSingleVO;

import java.util.List;

/**
 * Created by Administrator on 2018/11/8 0008.
 */
public interface UserMailSingleService {

    int insertSelective(UserMailSingle userMailSingle);

    void batchInsert(List<UserMailSingle> list);

    /**
     * 查询单发邮件
     * @param id
     * @return
     */
    UserMailSingle selectByPrimaryKey(String id);

    int update(UserMailSingle userMailSingle);

    /**
     * 查询用户所有的邮件
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    Pager<UserMailSingleVO> selectUserMailSingleVOList(String userId, int pageNum, int pageSize,Integer receive);

    int delete(String id);
    /**
     * 更新所有邮件的领取状态
     * @param userId
     */
    void receiveAccessoryAll(String userId);

    int userMailMessage(String userId);
}
