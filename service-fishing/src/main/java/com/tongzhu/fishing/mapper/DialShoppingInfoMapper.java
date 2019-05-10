package com.tongzhu.fishing.mapper;

import com.tongzhu.fishing.model.DialShoppingInfo;
import com.tongzhu.fishing.model.DialShoppingInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DialShoppingInfoMapper {
    int countByExample(DialShoppingInfoExample example);

    int deleteByExample(DialShoppingInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DialShoppingInfo record);

    int insertSelective(DialShoppingInfo record);

    List<DialShoppingInfo> selectByExample(DialShoppingInfoExample example);

    DialShoppingInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DialShoppingInfo record, @Param("example") DialShoppingInfoExample example);

    int updateByExample(@Param("record") DialShoppingInfo record, @Param("example") DialShoppingInfoExample example);

    int updateByPrimaryKeySelective(DialShoppingInfo record);

    int updateByPrimaryKey(DialShoppingInfo record);
}