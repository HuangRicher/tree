package com.tongzhu.treehouse.mapper;

import com.tongzhu.treehouse.model.TreeHouseWorkLog;
import com.tongzhu.treehouse.model.TreeHouseWorkLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TreeHouseWorkLogMapper {
    int countByExample(TreeHouseWorkLogExample example);

    int deleteByExample(TreeHouseWorkLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(TreeHouseWorkLog record);

    int insertSelective(TreeHouseWorkLog record);

    List<TreeHouseWorkLog> selectByExample(TreeHouseWorkLogExample example);

    TreeHouseWorkLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TreeHouseWorkLog record, @Param("example") TreeHouseWorkLogExample example);

    int updateByExample(@Param("record") TreeHouseWorkLog record, @Param("example") TreeHouseWorkLogExample example);

    int updateByPrimaryKeySelective(TreeHouseWorkLog record);

    int updateByPrimaryKey(TreeHouseWorkLog record);
}