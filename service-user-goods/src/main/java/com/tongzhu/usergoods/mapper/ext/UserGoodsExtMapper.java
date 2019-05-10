package com.tongzhu.usergoods.mapper.ext;

import com.tongzhu.usergoods.mapper.vo.UserGoodsVO;
import com.tongzhu.usergoods.model.EquipmentInfo;
import com.tongzhu.usergoods.model.PropInfo;
import com.tongzhu.usergoods.model.UserGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserGoodsExtMapper {

    List<UserGoods> selectByUserId(@Param("userId") String userId);

    int getBackpacksNumber(@Param("userId") String userId, @Param("settingPosition") Integer settingPosition);

    UserGoods getPacksackGoods(@Param("id") String id);

    List<UserGoodsVO> queryKnapsack(@Param("userId")String userId,@Param("settingPosition") int settingPosition);

    List<UserGoodsVO> queryUserWeapon(@Param("userId")String userId, @Param("settingPosition")Integer settingPosition);

    void addVigourForSchedule();

    List<PropInfo> selectUserWarePropInfo(@Param("userId")String userId, @Param("settingPosition")Integer settingPosition);

    int queryExistGoods(@Param("userId") String userId, @Param("goodsIdList")List<String> goodsIdList);

    UserGoodsVO getUserGoodsVO(@Param("userId")String userId, @Param("goodsId")String goodsId);

    Integer getMinEnchantlvl(@Param("userId")String userId);


    List<EquipmentInfo> queryGoodsWeddingRing(@Param("userId")String userId,@Param("wearPosition") Integer wearPosition);

    Integer getSumGemLevel(@Param("userId")String userId);
}
