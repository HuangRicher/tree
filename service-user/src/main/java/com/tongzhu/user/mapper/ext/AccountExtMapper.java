package com.tongzhu.user.mapper.ext;

import com.tongzhu.user.mapper.vo.AccountDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountExtMapper {

    List<AccountDO> findByAccount(@Param("account") String account);
}
