package com.tongzhu.user.service.impl;

import com.tongzhu.user.mapper.CopyMonsterMapper;
import com.tongzhu.user.mapper.ext.CopyMonsterExtMapper;
import com.tongzhu.user.model.CopyMonster;
import com.tongzhu.user.model.CopyMonsterExample;
import com.tongzhu.user.service.CopyMonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopyMonsterServiceImpl implements CopyMonsterService {

    @Autowired
    private CopyMonsterExtMapper copyMonsterExtMapper;

    @Autowired
    private CopyMonsterMapper copyMonsterMapper;



    @Override
    public void batchInsert(List<CopyMonster> list) {
        copyMonsterExtMapper.batchInsert(list);
    }

    @Override
    public void deleteAll() {
        CopyMonsterExample example=new CopyMonsterExample();
        copyMonsterMapper.deleteByExample(example);
    }

    @Override
    public List<CopyMonster> findByCopyId(Integer copyId) {
        CopyMonsterExample example=new CopyMonsterExample();
        example.createCriteria().andCopyIdEqualTo(copyId);
        return copyMonsterMapper.selectByExample(example);
    }
}
