package com.tongzhu.fishing.mapper;

import com.tongzhu.fishing.model.FishingSecretOperation;
import com.tongzhu.fishing.model.FishingSecretOperationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FishingSecretOperationMapper {
    int countByExample(FishingSecretOperationExample example);

    int deleteByExample(FishingSecretOperationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FishingSecretOperation record);

    int insertSelective(FishingSecretOperation record);

    List<FishingSecretOperation> selectByExample(FishingSecretOperationExample example);

    FishingSecretOperation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FishingSecretOperation record, @Param("example") FishingSecretOperationExample example);

    int updateByExample(@Param("record") FishingSecretOperation record, @Param("example") FishingSecretOperationExample example);

    int updateByPrimaryKeySelective(FishingSecretOperation record);

    int updateByPrimaryKey(FishingSecretOperation record);
}