package com.tongzhu.fishing.mapper;

import com.tongzhu.fishing.model.UserActivityProps;
import com.tongzhu.fishing.model.UserActivityPropsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserActivityPropsMapper {
    int countByExample(UserActivityPropsExample example);

    int deleteByExample(UserActivityPropsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserActivityProps record);

    int insertSelective(UserActivityProps record);

    List<UserActivityProps> selectByExample(UserActivityPropsExample example);

    UserActivityProps selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserActivityProps record, @Param("example") UserActivityPropsExample example);

    int updateByExample(@Param("record") UserActivityProps record, @Param("example") UserActivityPropsExample example);

    int updateByPrimaryKeySelective(UserActivityProps record);

    int updateByPrimaryKey(UserActivityProps record);
}