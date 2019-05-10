package com.tongzhu.user.mapper;

import com.tongzhu.user.model.Exploration;
import com.tongzhu.user.model.ExplorationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExplorationMapper {
    int countByExample(ExplorationExample example);

    int deleteByExample(ExplorationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Exploration record);

    int insertSelective(Exploration record);

    List<Exploration> selectByExample(ExplorationExample example);

    Exploration selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Exploration record, @Param("example") ExplorationExample example);

    int updateByExample(@Param("record") Exploration record, @Param("example") ExplorationExample example);

    int updateByPrimaryKeySelective(Exploration record);

    int updateByPrimaryKey(Exploration record);
}