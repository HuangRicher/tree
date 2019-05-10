package com.tongzhu.fishing.service;

import com.tongzhu.common.Pager;
import com.tongzhu.fishing.mapper.vo.FishEntrepotVO;
import com.tongzhu.fishing.model.FishEntrepot;

import java.util.List;

/**
 * Created by Administrator on 2019/1/11 0011.
 */
public interface FishEntrepotService {
    int insertSelective(FishEntrepot fishEntrepot);


    List<FishEntrepot> getFishEntrepotList(String userId,String goodsId);

    int extractGoods(String userId, String goodsId);


    Pager<FishEntrepotVO> getFishEntrepotBoutique(int pageNum, int pageSize);
}
