package com.tongzhu.user.service;


import com.tongzhu.user.model.UserMailAllDetails;

public interface UserMailAllDetailsService {
    /**
     * 获取用户群发邮件信息
     * @param userId
     * @param id
     * @return
     */
    UserMailAllDetails getUserMailAllDetails(String userId, String id);

    int inesrt(UserMailAllDetails userMailAllDetails);

    int update(UserMailAllDetails userMailAllDetails);

    int delete(String id, String userId);
}
