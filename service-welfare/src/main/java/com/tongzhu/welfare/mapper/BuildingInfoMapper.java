package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.BuildingInfo;
import com.tongzhu.welfare.model.BuildingInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuildingInfoMapper {
    int countByExample(BuildingInfoExample example);

    int deleteByExample(BuildingInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BuildingInfo record);

    int insertSelective(BuildingInfo record);

    List<BuildingInfo> selectByExample(BuildingInfoExample example);

    BuildingInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BuildingInfo record, @Param("example") BuildingInfoExample example);

    int updateByExample(@Param("record") BuildingInfo record, @Param("example") BuildingInfoExample example);

    int updateByPrimaryKeySelective(BuildingInfo record);

    int updateByPrimaryKey(BuildingInfo record);
}