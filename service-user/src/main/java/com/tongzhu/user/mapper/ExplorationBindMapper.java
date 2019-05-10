package com.tongzhu.user.mapper;

import com.tongzhu.user.model.ExplorationBind;
import com.tongzhu.user.model.ExplorationBindExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExplorationBindMapper {
    int countByExample(ExplorationBindExample example);

    int deleteByExample(ExplorationBindExample example);

    int deleteByPrimaryKey(String id);

    int insert(ExplorationBind record);

    int insertSelective(ExplorationBind record);

    List<ExplorationBind> selectByExample(ExplorationBindExample example);

    ExplorationBind selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ExplorationBind record, @Param("example") ExplorationBindExample example);

    int updateByExample(@Param("record") ExplorationBind record, @Param("example") ExplorationBindExample example);

    int updateByPrimaryKeySelective(ExplorationBind record);

    int updateByPrimaryKey(ExplorationBind record);
}