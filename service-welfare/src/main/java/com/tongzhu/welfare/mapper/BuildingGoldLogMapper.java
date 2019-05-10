package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.BuildingGoldLog;
import com.tongzhu.welfare.model.BuildingGoldLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuildingGoldLogMapper {
    int countByExample(BuildingGoldLogExample example);

    int deleteByExample(BuildingGoldLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BuildingGoldLog record);

    int insertSelective(BuildingGoldLog record);

    List<BuildingGoldLog> selectByExample(BuildingGoldLogExample example);

    BuildingGoldLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BuildingGoldLog record, @Param("example") BuildingGoldLogExample example);

    int updateByExample(@Param("record") BuildingGoldLog record, @Param("example") BuildingGoldLogExample example);

    int updateByPrimaryKeySelective(BuildingGoldLog record);

    int updateByPrimaryKey(BuildingGoldLog record);
}