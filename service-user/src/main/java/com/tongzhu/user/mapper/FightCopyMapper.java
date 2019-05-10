package com.tongzhu.user.mapper;

import com.tongzhu.user.model.FightCopy;
import com.tongzhu.user.model.FightCopyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FightCopyMapper {
    int countByExample(FightCopyExample example);

    int deleteByExample(FightCopyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FightCopy record);

    int insertSelective(FightCopy record);

    List<FightCopy> selectByExample(FightCopyExample example);

    FightCopy selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FightCopy record, @Param("example") FightCopyExample example);

    int updateByExample(@Param("record") FightCopy record, @Param("example") FightCopyExample example);

    int updateByPrimaryKeySelective(FightCopy record);

    int updateByPrimaryKey(FightCopy record);
}