package com.tongzhu.usergoods.mapper;

import com.tongzhu.usergoods.model.UserGoods;
import com.tongzhu.usergoods.model.UserGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserGoodsMapper {
    int countByExample(UserGoodsExample example);

    int deleteByExample(UserGoodsExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserGoods record);

    int insertSelective(UserGoods record);

    List<UserGoods> selectByExample(UserGoodsExample example);

    UserGoods selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserGoods record, @Param("example") UserGoodsExample example);

    int updateByExample(@Param("record") UserGoods record, @Param("example") UserGoodsExample example);

    int updateByPrimaryKeySelective(UserGoods record);

    int updateByPrimaryKey(UserGoods record);
}