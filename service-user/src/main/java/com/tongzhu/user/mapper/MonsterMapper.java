package com.tongzhu.user.mapper;

import com.tongzhu.user.model.Monster;
import com.tongzhu.user.model.MonsterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MonsterMapper {
    int countByExample(MonsterExample example);

    int deleteByExample(MonsterExample example);

    int deleteByPrimaryKey(String monsterId);

    int insert(Monster record);

    int insertSelective(Monster record);

    List<Monster> selectByExample(MonsterExample example);

    Monster selectByPrimaryKey(String monsterId);

    int updateByExampleSelective(@Param("record") Monster record, @Param("example") MonsterExample example);

    int updateByExample(@Param("record") Monster record, @Param("example") MonsterExample example);

    int updateByPrimaryKeySelective(Monster record);

    int updateByPrimaryKey(Monster record);
}