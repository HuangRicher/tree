package com.tongzhu.treehouse.mapper.ext;

import com.tongzhu.treehouse.mapper.vo.TreeHouseVisitorDO;
import com.tongzhu.treehouse.model.TreeHouseVisitor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TreeHouseVisitorExtMapper {

    void insertBatch(List<TreeHouseVisitor> list);

    /**
     * 树屋养成-游客列表
     * @param list
     * @return
     */
    List<TreeHouseVisitorDO> selectForTreeHouseDetail(List<String> list);

}
