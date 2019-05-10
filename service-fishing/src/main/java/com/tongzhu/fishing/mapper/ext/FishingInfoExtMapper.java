package com.tongzhu.fishing.mapper.ext;


import com.tongzhu.fishing.model.FishingInfo;
import org.apache.ibatis.annotations.Param;

public interface FishingInfoExtMapper {

    FishingInfo selectByUserId(@Param("id") String userId);
}