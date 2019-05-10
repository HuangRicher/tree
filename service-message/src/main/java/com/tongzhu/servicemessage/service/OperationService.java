package com.tongzhu.servicemessage.service;


public interface OperationService {

    void updateForUserOnlineState(String userId,Integer status);

    void saveUsersComeInTreeHouse(String treeHouseUserId,String userId);

    void removeUsersOutTreeHouse(String treeHouseUserId,String userId);
}
