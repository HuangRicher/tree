package com.tongzhu.usergoods.mapper;

import com.tongzhu.usergoods.model.PropGiftInfo;
import com.tongzhu.usergoods.model.PropGiftInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PropGiftInfoMapper {
    int countByExample(PropGiftInfoExample example);

    int deleteByExample(PropGiftInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(PropGiftInfo record);

    int insertSelective(PropGiftInfo record);

    List<PropGiftInfo> selectByExample(PropGiftInfoExample example);

    PropGiftInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PropGiftInfo record, @Param("example") PropGiftInfoExample example);

    int updateByExample(@Param("record") PropGiftInfo record, @Param("example") PropGiftInfoExample example);

    int updateByPrimaryKeySelective(PropGiftInfo record);

    int updateByPrimaryKey(PropGiftInfo record);
}