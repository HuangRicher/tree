package com.tongzhu.user.mapper;

import com.tongzhu.user.model.CopyExtraAward;
import com.tongzhu.user.model.CopyExtraAwardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CopyExtraAwardMapper {
    int countByExample(CopyExtraAwardExample example);

    int deleteByExample(CopyExtraAwardExample example);

    int deleteByPrimaryKey(String id);

    int insert(CopyExtraAward record);

    int insertSelective(CopyExtraAward record);

    List<CopyExtraAward> selectByExample(CopyExtraAwardExample example);

    CopyExtraAward selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CopyExtraAward record, @Param("example") CopyExtraAwardExample example);

    int updateByExample(@Param("record") CopyExtraAward record, @Param("example") CopyExtraAwardExample example);

    int updateByPrimaryKeySelective(CopyExtraAward record);

    int updateByPrimaryKey(CopyExtraAward record);
}