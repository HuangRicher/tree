package com.tongzhu.user.mapper;

import com.tongzhu.user.model.UserMailAllDetails;
import com.tongzhu.user.model.UserMailAllDetailsExample;
import com.tongzhu.user.model.UserMailAllDetailsKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMailAllDetailsMapper {
    int countByExample(UserMailAllDetailsExample example);

    int deleteByExample(UserMailAllDetailsExample example);

    int deleteByPrimaryKey(UserMailAllDetailsKey key);

    int insert(UserMailAllDetails record);

    int insertSelective(UserMailAllDetails record);

    List<UserMailAllDetails> selectByExample(UserMailAllDetailsExample example);

    UserMailAllDetails selectByPrimaryKey(UserMailAllDetailsKey key);

    int updateByExampleSelective(@Param("record") UserMailAllDetails record, @Param("example") UserMailAllDetailsExample example);

    int updateByExample(@Param("record") UserMailAllDetails record, @Param("example") UserMailAllDetailsExample example);

    int updateByPrimaryKeySelective(UserMailAllDetails record);

    int updateByPrimaryKey(UserMailAllDetails record);
}