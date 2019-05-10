package com.tongzhu.user.mapper;

import com.tongzhu.user.model.GameNPC;
import com.tongzhu.user.model.GameNPCExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GameNPCMapper {
    int countByExample(GameNPCExample example);

    int deleteByExample(GameNPCExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GameNPC record);

    int insertSelective(GameNPC record);

    List<GameNPC> selectByExample(GameNPCExample example);

    GameNPC selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GameNPC record, @Param("example") GameNPCExample example);

    int updateByExample(@Param("record") GameNPC record, @Param("example") GameNPCExample example);

    int updateByPrimaryKeySelective(GameNPC record);

    int updateByPrimaryKey(GameNPC record);
}