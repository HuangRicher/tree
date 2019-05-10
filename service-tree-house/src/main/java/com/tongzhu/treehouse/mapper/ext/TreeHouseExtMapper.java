package com.tongzhu.treehouse.mapper.ext;

import org.apache.ibatis.annotations.Param;

public interface TreeHouseExtMapper {

    void updateTressHouseCulture(@Param("id") String id, @Param("cultureValue") int cultureValue);

    void updateTressHouseFlourishingRateByUserId(@Param("userId") String userId, @Param("flourishingRate") int count);

    void updateTressHouseFlourishingRateByTreeHouseId(@Param("id") String id, @Param("flourishingRate") int count);

    void deleteMaxRoomIdByUserId(@Param("userId") String userId);

    void updateTreeHouseAmusementCount(@Param("treeHouseId") String treeHouseId,@Param("amuseCount") Integer amuseCount);

    void updateTreeHouseAmbienceCount(@Param("treeHouseId") String treeHouseId,@Param("ambienceCount") Integer ambienceCount);
}
