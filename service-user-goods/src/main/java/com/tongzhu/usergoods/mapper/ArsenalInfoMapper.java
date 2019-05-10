package com.tongzhu.usergoods.mapper;

import com.tongzhu.usergoods.model.ArsenalInfo;
import com.tongzhu.usergoods.model.ArsenalInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArsenalInfoMapper {
    int countByExample(ArsenalInfoExample example);

    int deleteByExample(ArsenalInfoExample example);

    int deleteByPrimaryKey(String intensifyId);

    int insert(ArsenalInfo record);

    int insertSelective(ArsenalInfo record);

    List<ArsenalInfo> selectByExample(ArsenalInfoExample example);

    ArsenalInfo selectByPrimaryKey(String intensifyId);

    int updateByExampleSelective(@Param("record") ArsenalInfo record, @Param("example") ArsenalInfoExample example);

    int updateByExample(@Param("record") ArsenalInfo record, @Param("example") ArsenalInfoExample example);

    int updateByPrimaryKeySelective(ArsenalInfo record);

    int updateByPrimaryKey(ArsenalInfo record);
}