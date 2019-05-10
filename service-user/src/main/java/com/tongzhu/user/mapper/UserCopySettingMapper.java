package com.tongzhu.user.mapper;

import com.tongzhu.user.model.UserCopySetting;
import com.tongzhu.user.model.UserCopySettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserCopySettingMapper {
    int countByExample(UserCopySettingExample example);

    int deleteByExample(UserCopySettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserCopySetting record);

    int insertSelective(UserCopySetting record);

    List<UserCopySetting> selectByExample(UserCopySettingExample example);

    UserCopySetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserCopySetting record, @Param("example") UserCopySettingExample example);

    int updateByExample(@Param("record") UserCopySetting record, @Param("example") UserCopySettingExample example);

    int updateByPrimaryKeySelective(UserCopySetting record);

    int updateByPrimaryKey(UserCopySetting record);
}