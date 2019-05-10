package com.tongzhu.user.service.impl;

import com.tongzhu.user.mapper.ExplorationMapper;
import com.tongzhu.user.mapper.ExplorationUserMapper;
import com.tongzhu.user.mapper.ext.ExplorationBindExtMapper;
import com.tongzhu.user.model.*;
import com.tongzhu.user.service.ExplorationService;
import com.tongzhu.user.service.vo.ExplorationUserVO;
import com.tongzhu.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExplorationServiceImpl implements ExplorationService {

    @Autowired
    private ExplorationMapper explorationMapper;

    @Autowired
    private ExplorationUserMapper explorationUserMapper;

    @Autowired
    private ExplorationBindExtMapper explorationBindExtMapper;



    @Override
    public List<ExplorationUserVO> findByUserId(String userId) {
        List<ExplorationUserVO> resultList=new ArrayList<>();
        List<Exploration> explorationList=this.findAll();
        if(explorationList!=null && !explorationList.isEmpty()){
            explorationList.forEach(exploration -> {
                ExplorationUserVO userVO=new ExplorationUserVO();
                List<ExplorationUser> userList=this.findByUserIdAndExplorationId(userId,exploration.getId());
                List<ExplorationUser> userResults=new ArrayList<>();
                if(userList!=null && !userList.isEmpty()){
                    userList.forEach(user->{
                        ExplorationUser uv=new ExplorationUser();
                        uv.setStarCount(user.getStarCount());
                        uv.setPassId(user.getPassId());
                        userResults.add(uv);
                    });
                    userVO.setStatus(1);
                    userVO.setItems(userResults);
                    userVO.setExplorationId(exploration.getId());
                }else{
                    userVO.setStatus(0);
                    userVO.setExplorationId(exploration.getId());
                }
                resultList.add(userVO);
            });
            return resultList;
        }
        return null;
    }


    @Override
    public void updateScoreForExplorationUser(String userId,Integer explorationId,Integer passId,Integer starCount) {
        ExplorationUser user=this.findBuyExplorationIdAndPassId(userId,explorationId,passId);
        if(user==null){
            user=new ExplorationUser();
            user.setExplorationId(explorationId);
            user.setId(UUIDUtil.generateUUID());
            user.setPassId(passId);
            user.setUserId(userId);
            user.setStarCount(starCount);
            explorationUserMapper.insert(user);
        }else {
            if(user.getStarCount()<starCount){
                ExplorationUser updateUser=new ExplorationUser();
                updateUser.setId(user.getId());
                updateUser.setStarCount(starCount);
                explorationUserMapper.updateByPrimaryKeySelective(updateUser);
            }
        }
    }

    @Override
    public List<Exploration> findAll() {
        ExplorationExample example=new ExplorationExample();
        return explorationMapper.selectByExample(example);
    }

    @Override
    public ExplorationUser findBuyExplorationIdAndPassId(String userId, Integer explorationId, Integer passId) {
        ExplorationUserExample example=new ExplorationUserExample();
        example.createCriteria().andUserIdEqualTo(userId).andExplorationIdEqualTo(explorationId).andPassIdEqualTo(passId);
        List<ExplorationUser> list=explorationUserMapper.selectByExample(example);
        if(list!=null && !list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<ExplorationUser> findByUserIdAndExplorationId(String userId, int explorationId) {
        ExplorationUserExample example=new ExplorationUserExample();
        example.createCriteria().andUserIdEqualTo(userId).andExplorationIdEqualTo(explorationId).andStarCountGreaterThan(0);
        example.setOrderByClause(" pass_id ");
        return explorationUserMapper.selectByExample(example);
    }

    @Override
    public void initUserExploration(String userId) {
        List<ExplorationBind> list=new ArrayList<>();
        for(int i=1;i<=5;i++){
            ExplorationBind bind=new ExplorationBind();
            bind.setId(UUIDUtil.generateUUID());
            if(i==1){
                bind.setStatus(1);
            }else{
                bind.setStatus(0);
            }
            bind.setUserId(userId);
            bind.setExplorationId(i);
            list.add(bind);
        }
        explorationBindExtMapper.batchInsert(list);
    }
}
