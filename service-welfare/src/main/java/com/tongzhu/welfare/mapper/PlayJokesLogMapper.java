package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.PlayJokesLog;
import com.tongzhu.welfare.model.PlayJokesLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlayJokesLogMapper {
    int countByExample(PlayJokesLogExample example);

    int deleteByExample(PlayJokesLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PlayJokesLog record);

    int insertSelective(PlayJokesLog record);

    List<PlayJokesLog> selectByExample(PlayJokesLogExample example);

    PlayJokesLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PlayJokesLog record, @Param("example") PlayJokesLogExample example);

    int updateByExample(@Param("record") PlayJokesLog record, @Param("example") PlayJokesLogExample example);

    int updateByPrimaryKeySelective(PlayJokesLog record);

    int updateByPrimaryKey(PlayJokesLog record);
}