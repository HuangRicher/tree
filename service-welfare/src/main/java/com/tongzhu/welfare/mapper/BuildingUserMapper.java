package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.BuildingUser;
import com.tongzhu.welfare.model.BuildingUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuildingUserMapper {
    int countByExample(BuildingUserExample example);

    int deleteByExample(BuildingUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BuildingUser record);

    int insertSelective(BuildingUser record);

    List<BuildingUser> selectByExample(BuildingUserExample example);

    BuildingUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BuildingUser record, @Param("example") BuildingUserExample example);

    int updateByExample(@Param("record") BuildingUser record, @Param("example") BuildingUserExample example);

    int updateByPrimaryKeySelective(BuildingUser record);

    int updateByPrimaryKey(BuildingUser record);
}