package com.tongzhu.treehouse.mapper.ext;

import com.tongzhu.treehouse.mapper.vo.TreeHouseRoomCountVO;
import com.tongzhu.treehouse.mapper.vo.TreeHouseRoomDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TreeHouseRoomExtMapper {
    List<TreeHouseRoomCountVO> countRoomsByUserId();

    List<TreeHouseRoomDO> findWorkersListByUserIdOrderBySellingPrice(@Param("userId") String userId);

    void updateForReleaseWorker(@Param("userId") String userId, @Param("workerId") String workerId);
}
