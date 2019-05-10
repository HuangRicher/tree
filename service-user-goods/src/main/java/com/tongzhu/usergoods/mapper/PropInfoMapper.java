package com.tongzhu.usergoods.mapper;

import com.tongzhu.usergoods.model.PropInfo;
import com.tongzhu.usergoods.model.PropInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PropInfoMapper {
    int countByExample(PropInfoExample example);

    int deleteByExample(PropInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(PropInfo record);

    int insertSelective(PropInfo record);

    List<PropInfo> selectByExample(PropInfoExample example);

    PropInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PropInfo record, @Param("example") PropInfoExample example);

    int updateByExample(@Param("record") PropInfo record, @Param("example") PropInfoExample example);

    int updateByPrimaryKeySelective(PropInfo record);

    int updateByPrimaryKey(PropInfo record);
}