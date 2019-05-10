package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.SendWishLog;
import com.tongzhu.welfare.model.SendWishLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SendWishLogMapper {
    int countByExample(SendWishLogExample example);

    int deleteByExample(SendWishLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(SendWishLog record);

    int insertSelective(SendWishLog record);

    List<SendWishLog> selectByExample(SendWishLogExample example);

    SendWishLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SendWishLog record, @Param("example") SendWishLogExample example);

    int updateByExample(@Param("record") SendWishLog record, @Param("example") SendWishLogExample example);

    int updateByPrimaryKeySelective(SendWishLog record);

    int updateByPrimaryKey(SendWishLog record);
}