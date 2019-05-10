package com.tongzhu.usergoods.mapper;

import com.tongzhu.usergoods.model.UpgradeInfo;
import com.tongzhu.usergoods.model.UpgradeInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpgradeInfoMapper {
    int countByExample(UpgradeInfoExample example);

    int deleteByExample(UpgradeInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UpgradeInfo record);

    int insertSelective(UpgradeInfo record);

    List<UpgradeInfo> selectByExample(UpgradeInfoExample example);

    UpgradeInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UpgradeInfo record, @Param("example") UpgradeInfoExample example);

    int updateByExample(@Param("record") UpgradeInfo record, @Param("example") UpgradeInfoExample example);

    int updateByPrimaryKeySelective(UpgradeInfo record);

    int updateByPrimaryKey(UpgradeInfo record);
}