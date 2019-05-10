package com.tongzhu.usergoods.mapper;

import com.tongzhu.usergoods.model.CompoundInfo;
import com.tongzhu.usergoods.model.CompoundInfoExample;
import com.tongzhu.usergoods.model.CompoundInfoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CompoundInfoMapper {
    int countByExample(CompoundInfoExample example);

    int deleteByExample(CompoundInfoExample example);

    int deleteByPrimaryKey(CompoundInfoKey key);

    int insert(CompoundInfo record);

    int insertSelective(CompoundInfo record);

    List<CompoundInfo> selectByExample(CompoundInfoExample example);

    CompoundInfo selectByPrimaryKey(CompoundInfoKey key);

    int updateByExampleSelective(@Param("record") CompoundInfo record, @Param("example") CompoundInfoExample example);

    int updateByExample(@Param("record") CompoundInfo record, @Param("example") CompoundInfoExample example);

    int updateByPrimaryKeySelective(CompoundInfo record);

    int updateByPrimaryKey(CompoundInfo record);
}