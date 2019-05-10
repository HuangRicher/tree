package com.tongzhu.usergoods.mapper;

import com.tongzhu.usergoods.model.UserWeddingRingLevel;
import com.tongzhu.usergoods.model.UserWeddingRingLevelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserWeddingRingLevelMapper {
    int countByExample(UserWeddingRingLevelExample example);

    int deleteByExample(UserWeddingRingLevelExample example);

    int deleteByPrimaryKey(String userId);

    int insert(UserWeddingRingLevel record);

    int insertSelective(UserWeddingRingLevel record);

    List<UserWeddingRingLevel> selectByExample(UserWeddingRingLevelExample example);

    UserWeddingRingLevel selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") UserWeddingRingLevel record, @Param("example") UserWeddingRingLevelExample example);

    int updateByExample(@Param("record") UserWeddingRingLevel record, @Param("example") UserWeddingRingLevelExample example);

    int updateByPrimaryKeySelective(UserWeddingRingLevel record);

    int updateByPrimaryKey(UserWeddingRingLevel record);
}