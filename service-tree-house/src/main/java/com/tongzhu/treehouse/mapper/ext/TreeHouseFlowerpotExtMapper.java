package com.tongzhu.treehouse.mapper.ext;

import com.tongzhu.treehouse.model.TreeHouseFlowerpot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TreeHouseFlowerpotExtMapper {
    void insertBatch(List<TreeHouseFlowerpot> list);

    void wateringAll(@Param("treeHouseId")String treeHouseId);

    void spreadManureAll(@Param("treeHouseId")String treeHouseId);

    void harvestAll(@Param("treeHouseId")String treeHouseId,
                    @Param("waterCount")Integer waterCount,
                    @Param("spreedManureCount")Integer spreedManureCount);

    void deleteAll(@Param("treeHouseId")String treeHouseId,
                   @Param("waterTimes")Integer waterTimes,
                   @Param("spreedManureTimes")Integer spreedManureTimes,
                   @Param("waterCount")Integer waterCount,
                   @Param("spreedManureCount")Integer spreedManureCount);
}
