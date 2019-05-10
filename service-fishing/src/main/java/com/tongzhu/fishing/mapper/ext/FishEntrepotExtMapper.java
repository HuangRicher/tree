package com.tongzhu.fishing.mapper.ext;


import com.tongzhu.fishing.mapper.vo.FishEntrepotVO;
import com.tongzhu.fishing.model.FishEntrepot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FishEntrepotExtMapper {
    List<FishEntrepot> getFishEntrepotList( @Param("userId") String userId,@Param("goodsId") String goodsId);

    int extractGoods( @Param("userId") String userId,@Param("goodsId") String goodsId);

    List<FishEntrepotVO> getFishEntrepotBoutique();
}