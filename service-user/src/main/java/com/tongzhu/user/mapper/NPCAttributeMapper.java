package com.tongzhu.user.mapper;

import com.tongzhu.user.model.NPCAttribute;
import com.tongzhu.user.model.NPCAttributeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NPCAttributeMapper {
    int countByExample(NPCAttributeExample example);

    int deleteByExample(NPCAttributeExample example);

    int deleteByPrimaryKey(Integer levelId);

    int insert(NPCAttribute record);

    int insertSelective(NPCAttribute record);

    List<NPCAttribute> selectByExample(NPCAttributeExample example);

    NPCAttribute selectByPrimaryKey(Integer levelId);

    int updateByExampleSelective(@Param("record") NPCAttribute record, @Param("example") NPCAttributeExample example);

    int updateByExample(@Param("record") NPCAttribute record, @Param("example") NPCAttributeExample example);

    int updateByPrimaryKeySelective(NPCAttribute record);

    int updateByPrimaryKey(NPCAttribute record);
}