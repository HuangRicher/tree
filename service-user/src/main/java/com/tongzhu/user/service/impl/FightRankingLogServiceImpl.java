package com.tongzhu.user.service.impl;

import com.tongzhu.user.mapper.FightRankingLogMapper;
import com.tongzhu.user.mapper.ext.FightRankingLogExtMapper;
import com.tongzhu.user.mapper.vo.FightRankingLogDO;
import com.tongzhu.user.model.FightRankingLog;
import com.tongzhu.user.model.FightRankingLogExample;
import com.tongzhu.user.service.FightRankingLogService;
import com.tongzhu.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FightRankingLogServiceImpl implements FightRankingLogService {

    @Autowired
    private FightRankingLogMapper fightRankingLogMapper;

    @Autowired
    private FightRankingLogExtMapper fightRankingLogExtMapper;



    @Override
    public List<FightRankingLog> findByUserId(String userId) {
        FightRankingLogExample example=new FightRankingLogExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return fightRankingLogMapper.selectByExample(example);
    }

    @Override
    public List<FightRankingLogDO> findDOByUserId(String userId) {
        return fightRankingLogExtMapper.selectFightRankingLogList(userId);
    }

    @Override
    public void add(String userId, String participantId, Integer fightResult, Integer changeScore) {
        FightRankingLog log=new FightRankingLog();
        log.setId(UUIDUtil.generateUUID());
        log.setFightDate(new Date());
        log.setChangeScore(changeScore);
        log.setUserId(userId);
        log.setParticipantId(participantId);
        log.setFightResult(fightResult);
        fightRankingLogMapper.insert(log);
    }
}
