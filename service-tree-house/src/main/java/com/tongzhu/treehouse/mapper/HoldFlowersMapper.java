package com.tongzhu.treehouse.mapper;

import com.tongzhu.treehouse.model.HoldFlowers;
import com.tongzhu.treehouse.model.HoldFlowersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HoldFlowersMapper {
    int countByExample(HoldFlowersExample example);

    int deleteByExample(HoldFlowersExample example);

    int deleteByPrimaryKey(String id);

    int insert(HoldFlowers record);

    int insertSelective(HoldFlowers record);

    List<HoldFlowers> selectByExample(HoldFlowersExample example);

    HoldFlowers selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") HoldFlowers record, @Param("example") HoldFlowersExample example);

    int updateByExample(@Param("record") HoldFlowers record, @Param("example") HoldFlowersExample example);

    int updateByPrimaryKeySelective(HoldFlowers record);

    int updateByPrimaryKey(HoldFlowers record);
}