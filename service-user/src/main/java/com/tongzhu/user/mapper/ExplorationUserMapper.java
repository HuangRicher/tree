package com.tongzhu.user.mapper;

import com.tongzhu.user.model.ExplorationUser;
import com.tongzhu.user.model.ExplorationUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExplorationUserMapper {
    int countByExample(ExplorationUserExample example);

    int deleteByExample(ExplorationUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(ExplorationUser record);

    int insertSelective(ExplorationUser record);

    List<ExplorationUser> selectByExample(ExplorationUserExample example);

    ExplorationUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ExplorationUser record, @Param("example") ExplorationUserExample example);

    int updateByExample(@Param("record") ExplorationUser record, @Param("example") ExplorationUserExample example);

    int updateByPrimaryKeySelective(ExplorationUser record);

    int updateByPrimaryKey(ExplorationUser record);
}