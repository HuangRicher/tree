package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.BuildingCoinReduceLog;
import com.tongzhu.welfare.model.BuildingCoinReduceLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuildingCoinReduceLogMapper {
    int countByExample(BuildingCoinReduceLogExample example);

    int deleteByExample(BuildingCoinReduceLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BuildingCoinReduceLog record);

    int insertSelective(BuildingCoinReduceLog record);

    List<BuildingCoinReduceLog> selectByExample(BuildingCoinReduceLogExample example);

    BuildingCoinReduceLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BuildingCoinReduceLog record, @Param("example") BuildingCoinReduceLogExample example);

    int updateByExample(@Param("record") BuildingCoinReduceLog record, @Param("example") BuildingCoinReduceLogExample example);

    int updateByPrimaryKeySelective(BuildingCoinReduceLog record);

    int updateByPrimaryKey(BuildingCoinReduceLog record);
}