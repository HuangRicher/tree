package com.tongzhu.treehouse.mapper;

import com.tongzhu.treehouse.model.TreeHouseVisitor;
import com.tongzhu.treehouse.model.TreeHouseVisitorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TreeHouseVisitorMapper {
    int countByExample(TreeHouseVisitorExample example);

    int deleteByExample(TreeHouseVisitorExample example);

    int deleteByPrimaryKey(String id);

    int insert(TreeHouseVisitor record);

    int insertSelective(TreeHouseVisitor record);

    List<TreeHouseVisitor> selectByExample(TreeHouseVisitorExample example);

    TreeHouseVisitor selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TreeHouseVisitor record, @Param("example") TreeHouseVisitorExample example);

    int updateByExample(@Param("record") TreeHouseVisitor record, @Param("example") TreeHouseVisitorExample example);

    int updateByPrimaryKeySelective(TreeHouseVisitor record);

    int updateByPrimaryKey(TreeHouseVisitor record);
}