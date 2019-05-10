package com.tongzhu.treehouse.service.impl;

import com.tongzhu.treehouse.mapper.TreeHouseCultureRecordMapper;
import com.tongzhu.treehouse.model.TreeHouseCultureRecord;
import com.tongzhu.treehouse.model.TreeHouseCultureRecordExample;
import com.tongzhu.treehouse.service.TreeHouseCultureRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeHouseCultureRecordServiceImpl implements TreeHouseCultureRecordService {

    @Autowired
    private TreeHouseCultureRecordMapper treeHouseCultureRecordMapper;


    @Override
    public int add(TreeHouseCultureRecord record) {
        return treeHouseCultureRecordMapper.insertSelective(record);
    }

    @Override
    public int update(TreeHouseCultureRecord record) {
        return treeHouseCultureRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public TreeHouseCultureRecord findById(String id) {
        return treeHouseCultureRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public TreeHouseCultureRecord findByUserIdAndTypeAndLevel(String userId, int type, int level) {
        TreeHouseCultureRecordExample example=new TreeHouseCultureRecordExample();
        example.createCriteria().andUserIdEqualTo(userId).andTypeEqualTo(type).andLevelEqualTo(level);
        List<TreeHouseCultureRecord> list=treeHouseCultureRecordMapper.selectByExample(example);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }


    @Override
    public synchronized void updateCount(String id, int cultureCount) {
        TreeHouseCultureRecord record=treeHouseCultureRecordMapper.selectByPrimaryKey(id);
        TreeHouseCultureRecord recordUp=new TreeHouseCultureRecord();
        recordUp.setId(record.getId());
        recordUp.setCount(record.getCount()+cultureCount);
        treeHouseCultureRecordMapper.updateByPrimaryKeySelective(recordUp);
    }
}
