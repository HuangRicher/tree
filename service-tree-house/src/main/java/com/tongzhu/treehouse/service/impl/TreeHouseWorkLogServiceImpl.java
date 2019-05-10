package com.tongzhu.treehouse.service.impl;

import com.tongzhu.treehouse.mapper.TreeHouseWorkLogMapper;
import com.tongzhu.treehouse.model.TreeHouseWorkLog;
import com.tongzhu.treehouse.model.TreeHouseWorkLogExample;
import com.tongzhu.treehouse.service.TreeHouseWorkLogService;
import com.tongzhu.util.DateUtil;
import com.tongzhu.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class TreeHouseWorkLogServiceImpl implements TreeHouseWorkLogService {

    @Autowired
    private TreeHouseWorkLogMapper treeHouseWorkLogMapper;


    @Override
    public void add(String content) {
        TreeHouseWorkLog log=new TreeHouseWorkLog();
        log.setId(UUIDUtil.generateUUID());
        log.setCreateDate(new Date());
        log.setMsgContent(content);
        treeHouseWorkLogMapper.insert(log);
    }

    @Override
    public List<TreeHouseWorkLog> find2HourLog() {
        LocalDateTime dateTime=LocalDateTime.now().minusHours(2L);
        TreeHouseWorkLogExample example=new TreeHouseWorkLogExample();
        example.createCriteria().andCreateDateGreaterThanOrEqualTo(DateUtil.localDateTimeToDate(dateTime));
        example.setOrderByClause(" limit 50");
        return treeHouseWorkLogMapper.selectByExample(example);
    }

    @Override
    public void deleteBefore2Hour() {
        LocalDateTime dateTime=LocalDateTime.now().minusHours(2L);
        TreeHouseWorkLogExample example=new TreeHouseWorkLogExample();
        example.createCriteria().andCreateDateLessThan(DateUtil.localDateTimeToDate(dateTime));
        treeHouseWorkLogMapper.deleteByExample(example);
    }
}
