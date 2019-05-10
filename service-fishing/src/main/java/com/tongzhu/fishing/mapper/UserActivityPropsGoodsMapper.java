package com.tongzhu.fishing.mapper;

import com.tongzhu.fishing.model.UserActivityPropsGoods;
import com.tongzhu.fishing.model.UserActivityPropsGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserActivityPropsGoodsMapper {
    int countByExample(UserActivityPropsGoodsExample example);

    int deleteByExample(UserActivityPropsGoodsExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserActivityPropsGoods record);

    int insertSelective(UserActivityPropsGoods record);

    List<UserActivityPropsGoods> selectByExample(UserActivityPropsGoodsExample example);

    UserActivityPropsGoods selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserActivityPropsGoods record, @Param("example") UserActivityPropsGoodsExample example);

    int updateByExample(@Param("record") UserActivityPropsGoods record, @Param("example") UserActivityPropsGoodsExample example);

    int updateByPrimaryKeySelective(UserActivityPropsGoods record);

    int updateByPrimaryKey(UserActivityPropsGoods record);
}