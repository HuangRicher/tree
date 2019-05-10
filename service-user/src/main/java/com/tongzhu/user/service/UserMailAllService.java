package com.tongzhu.user.service;

import com.tongzhu.user.model.UserMailAll;

import java.util.List;

public interface UserMailAllService {

    int insert(UserMailAll userMailAll);

    /**
     * 获取群发邮件模板
     * @param id
     * @return
     */
    UserMailAll getUserMailAllById(String id);

    int delete(String id);

    /**
     * 获取所有群发邮件
     * @return
     */
    List<UserMailAll> getUserMailAllList();


}
