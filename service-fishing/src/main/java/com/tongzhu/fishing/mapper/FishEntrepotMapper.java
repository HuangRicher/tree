package com.tongzhu.fishing.mapper;

import com.tongzhu.fishing.model.FishEntrepot;
import com.tongzhu.fishing.model.FishEntrepotExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FishEntrepotMapper {
    int countByExample(FishEntrepotExample example);

    int deleteByExample(FishEntrepotExample example);

    int insert(FishEntrepot record);

    int insertSelective(FishEntrepot record);

    List<FishEntrepot> selectByExample(FishEntrepotExample example);

    int updateByExampleSelective(@Param("record") FishEntrepot record, @Param("example") FishEntrepotExample example);

    int updateByExample(@Param("record") FishEntrepot record, @Param("example") FishEntrepotExample example);
}