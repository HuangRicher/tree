package com.tongzhu.fishing.mapper.ext;


import com.tongzhu.fishing.model.DialInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DialInfoExtMapper {
    Integer getForHeavySum(@Param("userId") String userId);

    List<DialInfo> getDialInfoList(@Param("userId")String userId);
}