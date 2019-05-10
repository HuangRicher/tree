package com.tongzhu.treehouse.service.impl;

import com.tongzhu.treehouse.mapper.TreeHouseFlowerpotLogMapper;
import com.tongzhu.treehouse.mapper.ext.TreeHouseFlowerpotLogExtMapper;
import com.tongzhu.treehouse.model.TreeHouseFlowerpotLog;
import com.tongzhu.treehouse.model.TreeHouseFlowerpotLogExample;
import com.tongzhu.treehouse.service.TreeHouseFlowerpotLogService;
import com.tongzhu.util.DateComputeUtil;
import com.tongzhu.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class TreeHouseFlowerpotLogServiceImpl implements TreeHouseFlowerpotLogService {

    @Autowired
    private TreeHouseFlowerpotLogMapper treeHouseFlowerpotLogMapper;

    @Autowired
    private TreeHouseFlowerpotLogExtMapper treeHouseFlowerpotLogExtMapper;


    @Override
    public void add(Integer type, String operator, String treeHouseUserId, String message) {
        TreeHouseFlowerpotLog log=new TreeHouseFlowerpotLog();
        log.setId(UUIDUtil.generateUUID());
        log.setCreateDate(new Date());
        log.setTreeHouseId(treeHouseUserId);
        log.setType(type);
        log.setOperator(operator);
        treeHouseFlowerpotLogMapper.insert(log);
    }

    @Override
    public List<TreeHouseFlowerpotLog> findByTreeHouseUserId(String treeHouseUserId) {
        return treeHouseFlowerpotLogExtMapper.queryLogByTreeHouseUserId(treeHouseUserId);
    }
}
