package com.tongzhu.user.mapper;

import com.tongzhu.user.model.AccountUser;
import com.tongzhu.user.model.AccountUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountUserMapper {
    int countByExample(AccountUserExample example);

    int deleteByExample(AccountUserExample example);

    int deleteByPrimaryKey(String userId);

    int insert(AccountUser record);

    int insertSelective(AccountUser record);

    List<AccountUser> selectByExample(AccountUserExample example);

    AccountUser selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") AccountUser record, @Param("example") AccountUserExample example);

    int updateByExample(@Param("record") AccountUser record, @Param("example") AccountUserExample example);

    int updateByPrimaryKeySelective(AccountUser record);

    int updateByPrimaryKey(AccountUser record);
}