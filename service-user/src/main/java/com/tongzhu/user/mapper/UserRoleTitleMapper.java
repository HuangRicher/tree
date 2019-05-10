package com.tongzhu.user.mapper;

import com.tongzhu.user.model.UserRoleTitle;
import com.tongzhu.user.model.UserRoleTitleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleTitleMapper {
    int countByExample(UserRoleTitleExample example);

    int deleteByExample(UserRoleTitleExample example);

    int insert(UserRoleTitle record);

    int insertSelective(UserRoleTitle record);

    List<UserRoleTitle> selectByExample(UserRoleTitleExample example);

    int updateByExampleSelective(@Param("record") UserRoleTitle record, @Param("example") UserRoleTitleExample example);

    int updateByExample(@Param("record") UserRoleTitle record, @Param("example") UserRoleTitleExample example);
}