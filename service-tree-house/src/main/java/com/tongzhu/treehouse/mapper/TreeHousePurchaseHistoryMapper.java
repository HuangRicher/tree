package com.tongzhu.treehouse.mapper;

import com.tongzhu.treehouse.model.TreeHousePurchaseHistory;
import com.tongzhu.treehouse.model.TreeHousePurchaseHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TreeHousePurchaseHistoryMapper {
    int countByExample(TreeHousePurchaseHistoryExample example);

    int deleteByExample(TreeHousePurchaseHistoryExample example);

    int deleteByPrimaryKey(String id);

    int insert(TreeHousePurchaseHistory record);

    int insertSelective(TreeHousePurchaseHistory record);

    List<TreeHousePurchaseHistory> selectByExample(TreeHousePurchaseHistoryExample example);

    TreeHousePurchaseHistory selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TreeHousePurchaseHistory record, @Param("example") TreeHousePurchaseHistoryExample example);

    int updateByExample(@Param("record") TreeHousePurchaseHistory record, @Param("example") TreeHousePurchaseHistoryExample example);

    int updateByPrimaryKeySelective(TreeHousePurchaseHistory record);

    int updateByPrimaryKey(TreeHousePurchaseHistory record);
}