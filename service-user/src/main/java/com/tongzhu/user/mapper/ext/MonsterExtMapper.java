package com.tongzhu.user.mapper.ext;

import com.tongzhu.user.mapper.vo.MonsterDO;
import com.tongzhu.user.model.Monster;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MonsterExtMapper {
    List<MonsterDO> selectByCopyIdOrderByGroup(@Param("copyId") Integer copyId);

    List<MonsterDO> selectByExplorationIdAndPassId(@Param("explorationId") Integer explorationId,@Param("passId") Integer passId);

    void batchInsert(List<Monster> list);
}
