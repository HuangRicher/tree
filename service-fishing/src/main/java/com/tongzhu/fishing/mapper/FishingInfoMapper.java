package com.tongzhu.fishing.mapper;

import com.tongzhu.fishing.model.FishingInfo;
import com.tongzhu.fishing.model.FishingInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FishingInfoMapper {
    int countByExample(FishingInfoExample example);

    int deleteByExample(FishingInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FishingInfo record);

    int insertSelective(FishingInfo record);

    List<FishingInfo> selectByExample(FishingInfoExample example);

    FishingInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FishingInfo record, @Param("example") FishingInfoExample example);

    int updateByExample(@Param("record") FishingInfo record, @Param("example") FishingInfoExample example);

    int updateByPrimaryKeySelective(FishingInfo record);

    int updateByPrimaryKey(FishingInfo record);
}