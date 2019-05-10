package com.tongzhu.user.mapper.ext;


import com.tongzhu.user.mapper.vo.UserRankingVO;
import com.tongzhu.user.model.UserCrunchies;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserCrunchiesExtMapper {
    UserCrunchies getCrunchiesByUserIdAndType(@Param("userId") String userId, @Param("type") int type, @Param("date") Date date);

    List<UserRankingVO> fightingMonthRankingList(@Param("count") int count, @Param("type") int type);
}