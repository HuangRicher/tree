package com.tongzhu.user.service.impl;

import com.tongzhu.user.mapper.AccountMapper;
import com.tongzhu.user.mapper.AccountUserMapper;
import com.tongzhu.user.mapper.ext.AccountExtMapper;
import com.tongzhu.user.mapper.vo.AccountDO;
import com.tongzhu.user.model.Account;
import com.tongzhu.user.model.AccountExample;
import com.tongzhu.user.model.AccountUser;
import com.tongzhu.user.model.AccountUserExample;
import com.tongzhu.user.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private static Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountExtMapper accountExtMapper;

    @Autowired
    private AccountUserMapper accountUserMapper;


    @Transactional
    @Override
    public int add(String account, String password) {
        int accountCount = this.getAccountCount();
        if (accountCount >= 200) {
            return 0;
        }

        Account at = new Account();
        at.setAccount(account);
        at.setPassword(password);
        accountMapper.insert(at);
        return 1;
    }

    @Override
    public void addAccountUser(String account, Integer roleId, String userId) {
        this.updateLastLogin(account);
        AccountUser at = new AccountUser();
        at.setAccount(account);
        at.setLastLogin(1);
        at.setRoleId(roleId);
        at.setUserId(userId);
        accountUserMapper.insert(at);
    }

    @Override
    public List<AccountDO> findByAccount(String account) {
        return accountExtMapper.findByAccount(account);
    }

    @Override
    public Account getByAccountAndPassword(String account, String password) {
        AccountExample example = new AccountExample();
        example.createCriteria().andAccountEqualTo(account).andPasswordEqualTo(password);
        List<Account> list = accountMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public Account getByAccount(String account) {
        return accountMapper.selectByPrimaryKey(account);
    }

    @Override
    public void updateLastLogin(String account) {
        AccountUserExample example = new AccountUserExample();
        example.createCriteria().andAccountEqualTo(account);
        AccountUser at = new AccountUser();
        at.setLastLogin(0);
        accountUserMapper.updateByExampleSelective(at, example);
    }

    @Override
    public void updateLastLogin(String account, Integer roleId) {
        AccountUserExample example = new AccountUserExample();
        example.createCriteria().andAccountEqualTo(account);
        AccountUser at = new AccountUser();
        at.setLastLogin(0);
        accountUserMapper.updateByExampleSelective(at, example);
        AccountUserExample example1 = new AccountUserExample();
        example1.createCriteria().andAccountEqualTo(account).andRoleIdEqualTo(roleId);
        AccountUser at1 = new AccountUser();
        at1.setLastLogin(1);
        accountUserMapper.updateByExampleSelective(at1, example1);
    }

    @Override
    public AccountUser getByAccountAndRoleId(String account, Integer roleId) {
        AccountUserExample example = new AccountUserExample();
        example.createCriteria().andAccountEqualTo(account).andRoleIdEqualTo(roleId);
        List<AccountUser> list = accountUserMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public void updateAccount(String account, String password) {
        Account at = new Account();
        at.setAccount(account);
        at.setPassword(password);
        accountMapper.updateByPrimaryKey(at);
    }

    @Override
    public void deleteByAccount(String s) {
        AccountExample example = new AccountExample();
        example.createCriteria().andAccountEqualTo(s);
        accountMapper.deleteByExample(example);
    }

    @Override
    public void deleteAccountUserByAccount(String s) {
        AccountUserExample example = new AccountUserExample();
        example.createCriteria().andAccountEqualTo(s);
        accountUserMapper.deleteByExample(example);
    }

    @Override
    public int getAccountCount() {
        AccountExample accountExample = new AccountExample();
        int i = accountMapper.countByExample(accountExample);
        log.info("当前注册人数为："+i);
        return i;
    }
}
