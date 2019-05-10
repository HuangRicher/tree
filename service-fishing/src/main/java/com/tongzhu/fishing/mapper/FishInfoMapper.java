package com.tongzhu.fishing.mapper;

import com.tongzhu.fishing.model.FishInfo;
import com.tongzhu.fishing.model.FishInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FishInfoMapper {
    int countByExample(FishInfoExample example);

    int deleteByExample(FishInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FishInfo record);

    int insertSelective(FishInfo record);

    List<FishInfo> selectByExample(FishInfoExample example);

    FishInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FishInfo record, @Param("example") FishInfoExample example);

    int updateByExample(@Param("record") FishInfo record, @Param("example") FishInfoExample example);

    int updateByPrimaryKeySelective(FishInfo record);

    int updateByPrimaryKey(FishInfo record);
}