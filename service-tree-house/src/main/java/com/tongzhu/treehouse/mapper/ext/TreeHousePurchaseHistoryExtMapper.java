package com.tongzhu.treehouse.mapper.ext;

import com.tongzhu.treehouse.mapper.vo.TreeHousePurchaseHistoryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TreeHousePurchaseHistoryExtMapper {

    List<TreeHousePurchaseHistoryDO> purchaseHistoryList(@Param("userId") String userId);

    Integer getTreeHousePurchaseHistorySum(@Param("userId") String userId);
}