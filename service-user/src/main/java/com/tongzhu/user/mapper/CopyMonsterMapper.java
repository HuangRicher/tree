package com.tongzhu.user.mapper;

import com.tongzhu.user.model.CopyMonster;
import com.tongzhu.user.model.CopyMonsterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CopyMonsterMapper {
    int countByExample(CopyMonsterExample example);

    int deleteByExample(CopyMonsterExample example);

    int deleteByPrimaryKey(String id);

    int insert(CopyMonster record);

    int insertSelective(CopyMonster record);

    List<CopyMonster> selectByExample(CopyMonsterExample example);

    CopyMonster selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CopyMonster record, @Param("example") CopyMonsterExample example);

    int updateByExample(@Param("record") CopyMonster record, @Param("example") CopyMonsterExample example);

    int updateByPrimaryKeySelective(CopyMonster record);

    int updateByPrimaryKey(CopyMonster record);
}