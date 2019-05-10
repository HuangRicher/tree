package com.tongzhu.treehouse.service.impl;

import com.alibaba.fastjson.JSON;
import com.tongzhu.constant.RedisKey;
import com.tongzhu.treehouse.constant.TreeHouseVisitorConstant;
import com.tongzhu.treehouse.mapper.TreeHouseVisitorMapper;
import com.tongzhu.treehouse.mapper.ext.TreeHouseVisitorExtMapper;
import com.tongzhu.treehouse.mapper.vo.TreeHouseVisitorDO;
import com.tongzhu.treehouse.model.TreeHouseVisitor;
import com.tongzhu.treehouse.model.TreeHouseVisitorExample;
import com.tongzhu.treehouse.redis.RedisService;
import com.tongzhu.treehouse.service.ChatMessageService;
import com.tongzhu.treehouse.service.TreeHouseVisitorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class TreeHouseVisitorServiceImpl implements TreeHouseVisitorService {

    @Autowired
    private TreeHouseVisitorMapper treeHouseVisitorMapper;

    @Autowired
    private TreeHouseVisitorExtMapper treeHouseVisitorExtMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private ChatMessageService chatMessageService;


    @Override
    public void add(TreeHouseVisitor treeHouseVisitor) {
        treeHouseVisitorMapper.insert(treeHouseVisitor);
    }

    @Override
    public void addBatch(List<TreeHouseVisitor> list) {
        treeHouseVisitorExtMapper.insertBatch(list);
    }

    @Override
    public List<TreeHouseVisitorDO> findTreeHouseVisitor(String userId,String treeHouseUserId) {
        List<TreeHouseVisitor> treeHouseVisitors=this.findByTreeHouseId(treeHouseUserId);
        List<String> userIds=new ArrayList<>();
        Map<String,Integer> users=(Map<String,Integer>)redisService.get(RedisKey.IN_TREE_HOUSE+treeHouseUserId);
        Map<String,Integer> canNotSpeakUsers=(Map<String,Integer>)redisService.get(RedisKey.CAN_NOT_SPEAK_TREE_HOUSE +treeHouseUserId);
        for(Map.Entry<String,Integer> entry:users.entrySet()){
            userIds.add(entry.getKey());
        }
        List<TreeHouseVisitorDO> visitorDOS=treeHouseVisitorExtMapper.selectForTreeHouseDetail(userIds);
        List<TreeHouseVisitorDO> result=new ArrayList<>();
        int i=0;
        //Object data1=redisService.get(RedisKey.TREE_HOUSE_WATERING +treeHouseUserId);
        //Object data2=redisService.get(RedisKey.SWEEP_TREE_HOUSE+treeHouseUserId);
        //Object data3=redisService.get(RedisKey.TREE_HOUSE_SPREAD_MANURE+treeHouseUserId);
        for(TreeHouseVisitor visitor:treeHouseVisitors){
            TreeHouseVisitorDO visitorDO=new TreeHouseVisitorDO();
            if(i<visitorDOS.size() && visitor.getStatus()==1){
                TreeHouseVisitorDO vdo=visitorDOS.get(i);
                BeanUtils.copyProperties(vdo,visitorDO);
                if(canNotSpeakUsers!=null){
                    visitorDO.setCanSpeak(canNotSpeakUsers.get(visitorDO.getUserId())==null);
                }else {
                    visitorDO.setCanSpeak(true);
                }

                visitorDO.setWorkStatus(0);
                /*if(data1!=null){
                    Map<String,String> data11=(Map<String,String>)data1;
                    String workerId=data11.get("watering_worker");
                    if(workerId.equals(visitorDO.getUserId())){
                        visitorDO.setWorkStatus(1);
                    }
                }
                if(data2!=null){
                    Map<String,String> data22=(Map<String,String>)data2;
                    String workerId=data22.get("sweep_worker");
                    if(StringUtils.isNotBlank(workerId) && workerId.equals(visitorDO.getUserId())){
                        visitorDO.setWorkStatus(1);
                    }
                }
                if(data3!=null){
                    Map<String,String> data33=(Map<String,String>)data3;
                    String workerId=data33.get("spreadManure_worker");
                    if(workerId.equals(visitorDO.getUserId())){
                        visitorDO.setWorkStatus(1);
                    }
                }*/
                i++;
            }
            visitorDO.setId(visitor.getId());
            visitorDO.setStatus(visitor.getStatus());
            result.add(visitorDO);
        }
        return result;
    }

    @Override
    public void deleteVisitor(String treeHouseId, String userId) {

    }

    @Override
    public void disableVisitor(String id) {
        TreeHouseVisitor visitor=new TreeHouseVisitor();
        visitor.setId(id);
        visitor.setStatus(0);
        treeHouseVisitorMapper.updateByPrimaryKeySelective(visitor);
    }

    @Override
    public void updateById(TreeHouseVisitor visitor) {
        treeHouseVisitorMapper.updateByPrimaryKeySelective(visitor);
    }

    @Override
    public int getCanUseVisitorLocation(String treeHouseId) {
        TreeHouseVisitorExample example=new TreeHouseVisitorExample();
        example.createCriteria().andTreeHouseIdEqualTo(treeHouseId)
                .andStatusEqualTo(TreeHouseVisitorConstant.STATUS_ABLE);
        return treeHouseVisitorMapper.countByExample(example);
    }

    @Override
    public List<TreeHouseVisitor> findByTreeHouseId(String treeHouseId) {
        TreeHouseVisitorExample example=new TreeHouseVisitorExample();
        example.createCriteria().andTreeHouseIdEqualTo(treeHouseId);
        return treeHouseVisitorMapper.selectByExample(example);
    }

    @Override
    public List<TreeHouseVisitor> findByVisitorId(String userId) {
        TreeHouseVisitorExample example=new TreeHouseVisitorExample();
        example.createCriteria().andVisitorIdEqualTo(userId);
        return treeHouseVisitorMapper.selectByExample(example);
    }

    @Override
    public void updateByIdWithNull(TreeHouseVisitor visitor) {
        treeHouseVisitorMapper.updateByPrimaryKey(visitor);
    }

    @Override
    public void letOutVisitor(String userId, String outUserId) {
        redisService.set(RedisKey.TREE_HOUSE_LET_OUT+userId+"_"+outUserId,1,2*60*60L);
        redisService.remove(RedisKey.CHAT_TREE_HOUSE_USER+outUserId);
        Map<String,Integer> users=(Map<String,Integer>)redisService.get(RedisKey.IN_TREE_HOUSE+userId);
        Iterator<String> it=users.keySet().iterator();
        while (it.hasNext()){
            String key=it.next();
            if(outUserId.equals(key)) {
                it.remove();
            }
            Map<String,Object> message=new HashMap<>();
            message.put("type","leaveTreeHouse");
            message.put("code",0);
            message.put("userId",outUserId);
            chatMessageService.sendMessageToSomeBody(key, JSON.toJSONString(message));
        }
        redisService.set(RedisKey.IN_TREE_HOUSE+userId,users);
    }

    @Override
    public void updateByTreeHouseIdAndVisitorId(String treeHouseUserId, String userId) {
        TreeHouseVisitorExample example=new TreeHouseVisitorExample();
        example.createCriteria().andTreeHouseIdEqualTo(treeHouseUserId).andVisitorIdEqualTo(userId);
        List<TreeHouseVisitor> list=treeHouseVisitorMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(list)){
            TreeHouseVisitor visitor=list.get(0);
            visitor.setVisitorId(null);
            treeHouseVisitorMapper.updateByPrimaryKey(visitor);
        }
    }
}
