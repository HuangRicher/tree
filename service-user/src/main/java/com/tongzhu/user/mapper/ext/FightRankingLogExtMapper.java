package com.tongzhu.user.mapper.ext;

import com.tongzhu.user.mapper.vo.FightRankingLogDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FightRankingLogExtMapper {

    List<FightRankingLogDO> selectFightRankingLogList(@Param("userId") String userId);
}
