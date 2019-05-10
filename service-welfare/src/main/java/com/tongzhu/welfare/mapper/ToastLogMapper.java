package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.ToastLog;
import com.tongzhu.welfare.model.ToastLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ToastLogMapper {
    int countByExample(ToastLogExample example);

    int deleteByExample(ToastLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(ToastLog record);

    int insertSelective(ToastLog record);

    List<ToastLog> selectByExample(ToastLogExample example);

    ToastLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ToastLog record, @Param("example") ToastLogExample example);

    int updateByExample(@Param("record") ToastLog record, @Param("example") ToastLogExample example);

    int updateByPrimaryKeySelective(ToastLog record);

    int updateByPrimaryKey(ToastLog record);
}