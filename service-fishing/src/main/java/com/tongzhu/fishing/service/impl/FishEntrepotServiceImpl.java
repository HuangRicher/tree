package com.tongzhu.fishing.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tongzhu.common.Pager;
import com.tongzhu.fishing.domain.UserMessage;
import com.tongzhu.fishing.mapper.FishEntrepotMapper;
import com.tongzhu.fishing.mapper.ext.FishEntrepotExtMapper;
import com.tongzhu.fishing.mapper.vo.FishEntrepotVO;
import com.tongzhu.fishing.model.FishEntrepot;
import com.tongzhu.fishing.service.FishEntrepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/1/11 0011.
 */

@Service
public class FishEntrepotServiceImpl implements FishEntrepotService {

    @Autowired
    private FishEntrepotMapper fishEntrepotMapper;

    @Autowired
    private FishEntrepotExtMapper fishEntrepotExtMapper;

    @Override
    public int insertSelective(FishEntrepot fishEntrepot) {
        fishEntrepot.setUpdateDate(new Date());
        return fishEntrepotMapper.insertSelective(fishEntrepot);
    }


    @Override
    public List<FishEntrepot> getFishEntrepotList(String userId,String goodsId) {
        return fishEntrepotExtMapper.getFishEntrepotList(userId,goodsId);
    }

    @Override
    public int extractGoods(String userId, String goodsId) {
        return fishEntrepotExtMapper.extractGoods(userId,goodsId);
    }

    @Override
    public Pager<FishEntrepotVO> getFishEntrepotBoutique(int pageNum, int pageSize) {
        Page<UserMessage> page= PageHelper.startPage(pageNum,pageSize);
        fishEntrepotExtMapper.getFishEntrepotBoutique();
        return new Pager(pageNum,pageSize,page.getPages(),page.getTotal(),page.getResult());
    }


}
