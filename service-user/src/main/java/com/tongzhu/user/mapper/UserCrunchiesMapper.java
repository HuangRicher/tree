package com.tongzhu.user.mapper;

import com.tongzhu.user.model.UserCrunchies;
import com.tongzhu.user.model.UserCrunchiesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserCrunchiesMapper {
    int countByExample(UserCrunchiesExample example);

    int deleteByExample(UserCrunchiesExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserCrunchies record);

    int insertSelective(UserCrunchies record);

    List<UserCrunchies> selectByExample(UserCrunchiesExample example);

    UserCrunchies selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserCrunchies record, @Param("example") UserCrunchiesExample example);

    int updateByExample(@Param("record") UserCrunchies record, @Param("example") UserCrunchiesExample example);

    int updateByPrimaryKeySelective(UserCrunchies record);

    int updateByPrimaryKey(UserCrunchies record);
}