package com.tongzhu.usergoods.mapper;

import com.tongzhu.usergoods.model.WeddingRingUpgradeInfo;
import com.tongzhu.usergoods.model.WeddingRingUpgradeInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WeddingRingUpgradeInfoMapper {
    int countByExample(WeddingRingUpgradeInfoExample example);

    int deleteByExample(WeddingRingUpgradeInfoExample example);

    int deleteByPrimaryKey(Integer level);

    int insert(WeddingRingUpgradeInfo record);

    int insertSelective(WeddingRingUpgradeInfo record);

    List<WeddingRingUpgradeInfo> selectByExample(WeddingRingUpgradeInfoExample example);

    WeddingRingUpgradeInfo selectByPrimaryKey(Integer level);

    int updateByExampleSelective(@Param("record") WeddingRingUpgradeInfo record, @Param("example") WeddingRingUpgradeInfoExample example);

    int updateByExample(@Param("record") WeddingRingUpgradeInfo record, @Param("example") WeddingRingUpgradeInfoExample example);

    int updateByPrimaryKeySelective(WeddingRingUpgradeInfo record);

    int updateByPrimaryKey(WeddingRingUpgradeInfo record);
}