package com.tongzhu.treehouse.service.impl;

import com.tongzhu.treehouse.mapper.TreeHouseFlowerpotPlayerMapper;
import com.tongzhu.treehouse.model.TreeHouseFlowerpotPlayer;
import com.tongzhu.treehouse.model.TreeHouseFlowerpotPlayerExample;
import com.tongzhu.treehouse.service.TreeHouseFlowerpotPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class TreeHouseFlowerpotPlayerServiceImpl implements TreeHouseFlowerpotPlayerService {

    @Autowired
    private TreeHouseFlowerpotPlayerMapper treeHouseFlowerpotPlayerMapper;



    @Override
    public void add(String plantId, String userId) {
        TreeHouseFlowerpotPlayer player=new TreeHouseFlowerpotPlayer();
        player.setPlantId(plantId);
        player.setUserId(userId);
        treeHouseFlowerpotPlayerMapper.insert(player);
    }

    @Override
    public TreeHouseFlowerpotPlayer getByPlantIdAndUserId(String plantId, String userId) {
        TreeHouseFlowerpotPlayerExample example=new TreeHouseFlowerpotPlayerExample();
        example.createCriteria().andPlantIdEqualTo(plantId).andUserIdEqualTo(userId);
        List<TreeHouseFlowerpotPlayer> players=treeHouseFlowerpotPlayerMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(players))
            return players.get(0);
        return null;
    }

    @Override
    public void deleteByPlantId(String plantId) {
        TreeHouseFlowerpotPlayerExample example=new TreeHouseFlowerpotPlayerExample();
        example.createCriteria().andPlantIdEqualTo(plantId);
        treeHouseFlowerpotPlayerMapper.deleteByExample(example);
    }
}
