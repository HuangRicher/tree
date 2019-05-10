package com.tongzhu.fishing.mapper;

import com.tongzhu.fishing.model.UserActivity;
import com.tongzhu.fishing.model.UserActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserActivityMapper {
    int countByExample(UserActivityExample example);

    int deleteByExample(UserActivityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserActivity record);

    int insertSelective(UserActivity record);

    List<UserActivity> selectByExample(UserActivityExample example);

    UserActivity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserActivity record, @Param("example") UserActivityExample example);

    int updateByExample(@Param("record") UserActivity record, @Param("example") UserActivityExample example);

    int updateByPrimaryKeySelective(UserActivity record);

    int updateByPrimaryKey(UserActivity record);
}