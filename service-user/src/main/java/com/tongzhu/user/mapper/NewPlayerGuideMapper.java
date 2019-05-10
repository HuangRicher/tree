package com.tongzhu.user.mapper;

import com.tongzhu.user.model.NewPlayerGuide;
import com.tongzhu.user.model.NewPlayerGuideExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewPlayerGuideMapper {
    int countByExample(NewPlayerGuideExample example);

    int deleteByExample(NewPlayerGuideExample example);

    int deleteByPrimaryKey(String userId);

    int insert(NewPlayerGuide record);

    int insertSelective(NewPlayerGuide record);

    List<NewPlayerGuide> selectByExample(NewPlayerGuideExample example);

    NewPlayerGuide selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") NewPlayerGuide record, @Param("example") NewPlayerGuideExample example);

    int updateByExample(@Param("record") NewPlayerGuide record, @Param("example") NewPlayerGuideExample example);

    int updateByPrimaryKeySelective(NewPlayerGuide record);

    int updateByPrimaryKey(NewPlayerGuide record);
}