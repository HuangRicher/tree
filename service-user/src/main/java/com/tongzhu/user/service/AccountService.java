package com.tongzhu.user.service;

import com.tongzhu.user.mapper.vo.AccountDO;
import com.tongzhu.user.model.Account;
import com.tongzhu.user.model.AccountUser;

import java.util.List;

public interface AccountService {
    int add(String account,String password);

    void addAccountUser(String account,Integer roleId,String userId);

    List<AccountDO> findByAccount(String account);

    Account getByAccountAndPassword(String account,String password);

    Account getByAccount(String account);

    void updateLastLogin(String account);

    void updateLastLogin(String account,Integer roleId);

    AccountUser getByAccountAndRoleId(String account,Integer roleId);

    void updateAccount(String account, String password);

    void deleteByAccount(String s);

    void deleteAccountUserByAccount(String s);

    int getAccountCount();
}
