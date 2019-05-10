package com.tongzhu.treehouse.mapper.ext;

import com.tongzhu.treehouse.model.UserWorkPosition;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserWorkPositionExtMapper {

    void insertBatch(List<UserWorkPosition> list);

    void updateForReleaseWorker(@Param("userId") String userId, @Param("workerId") String workerId,
                                @Param("lastTime") int lastTime, @Param("stopDate") Date stopDate);
}
