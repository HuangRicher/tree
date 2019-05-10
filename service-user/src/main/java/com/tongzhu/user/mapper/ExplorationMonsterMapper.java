package com.tongzhu.user.mapper;

import com.tongzhu.user.model.ExplorationMonster;
import com.tongzhu.user.model.ExplorationMonsterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExplorationMonsterMapper {
    int countByExample(ExplorationMonsterExample example);

    int deleteByExample(ExplorationMonsterExample example);

    int deleteByPrimaryKey(String id);

    int insert(ExplorationMonster record);

    int insertSelective(ExplorationMonster record);

    List<ExplorationMonster> selectByExample(ExplorationMonsterExample example);

    ExplorationMonster selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ExplorationMonster record, @Param("example") ExplorationMonsterExample example);

    int updateByExample(@Param("record") ExplorationMonster record, @Param("example") ExplorationMonsterExample example);

    int updateByPrimaryKeySelective(ExplorationMonster record);

    int updateByPrimaryKey(ExplorationMonster record);
}