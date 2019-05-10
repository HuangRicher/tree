package com.tongzhu.treehouse.service.impl;

import com.alibaba.fastjson.JSON;
import com.tongzhu.constant.GoodsConstant;
import com.tongzhu.constant.GoodsTypeConstant;
import com.tongzhu.constant.RedisKey;
import com.tongzhu.treehouse.constant.*;
import com.tongzhu.treehouse.domain.UserGoods;
import com.tongzhu.treehouse.domain.UserMailSingle;
import com.tongzhu.except.CheckException;
import com.tongzhu.treehouse.domain.UserRole;
import com.tongzhu.treehouse.mapper.FlowerNumberSettingMapper;
import com.tongzhu.treehouse.mapper.FlowerSeedsDrawSettingMapper;
import com.tongzhu.treehouse.mapper.TreeHouseFlowerpotMapper;
import com.tongzhu.treehouse.mapper.ext.TreeHouseFlowerpotExtMapper;
import com.tongzhu.treehouse.model.*;
import com.tongzhu.treehouse.redis.RedisService;
import com.tongzhu.treehouse.service.*;
import com.tongzhu.treehouse.service.vo.PropInfoVO;
import com.tongzhu.util.DateComputeUtil;
import com.tongzhu.util.RandomUtil;
import com.tongzhu.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.List;

@Service
public class TreeHouseFlowerpotServiceImpl implements TreeHouseFlowerpotService {

    @Autowired
    private TreeHouseFlowerpotMapper treeHouseFlowerpotMapper;

    @Autowired
    private TreeHouseFlowerpotExtMapper treeHouseFlowerpotExtMapper;

    @Autowired
    private FlowerNumberSettingMapper flowerNumberSettingMapper;

    @Autowired
    private FlowerSeedsDrawSettingMapper flowerSeedsDrawSettingMapper;

    @Autowired
    private HoldFlowersService holdFlowersService;

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private TreeHouseFlowerpotLogService treeHouseFlowerpotLogService;

    @Autowired
    private FlowerSettingService flowerSettingService;

    @Autowired
    private TreeHouseService treeHouseService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserMailSingleService userMailSingleService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TreeHouseWorkSettingService treeHouseWorkSettingService;

    @Autowired
    private TreeHouseFlowerpotPlayerService treeHouseFlowerpotPlayerService;





    @Override
    public void add(TreeHouseFlowerpot treeHouseFlowerpot) {
        treeHouseFlowerpotMapper.insert(treeHouseFlowerpot);
    }

    @Override
    public void addBatch(List<TreeHouseFlowerpot> list) {
        treeHouseFlowerpotExtMapper.insertBatch(list);
    }

    @Override
    public List<TreeHouseFlowerpot> findByTreeHouseId(String treeHouseId) {
        TreeHouseFlowerpotExample example=new TreeHouseFlowerpotExample();
        example.createCriteria().andTreeHouseIdEqualTo(treeHouseId);
        example.setOrderByClause(" lock_level ASC ");
        List<TreeHouseFlowerpot> list=treeHouseFlowerpotMapper.selectByExample(example);
        long currentSec=System.currentTimeMillis();
        if(!CollectionUtils.isEmpty(list)){
            for(TreeHouseFlowerpot pot:list){

                pot.setWaterCount(pot.getWaterCount()+pot.getSpreadManureCount());
                if(pot.getSowingDate()!=null){
                    //实际金币收益=基础金币收益+基础金币收益*(浇水次数+施肥次数)*0.01，浇水+施肥次数超过50次按50次算
                    //实际环境值收益=基础环境值收益+基础环境值收益*(浇水次数+施肥次数)*0.01，浇水+施肥次数超过50次按50次算
                    FlowerSetting setting=flowerSettingService.getById(pot.getGoodsId());
                    int waterCount=pot.getSpreadManureCount()>50?50:(pot.getWaterCount()+pot.getSpreadManureCount());
                    int moneyCount=setting.getMoneyAward();
                    pot.setSpreadManureCount(moneyCount+(int)(moneyCount*waterCount*0.01));

                    int tempTime=(int)((currentSec-pot.getSowingDate().getTime())/1000);
                    if(tempTime>=FlowerpotConstant.FLOWER_MATURE_TIME){
                        pot.setFlowerStatus(FlowerpotConstant.FLOWER_STATUS_4);
                    }else if(tempTime>FlowerpotConstant.FLOWER_GROWTH_TIME){
                        pot.setFlowerStatus(FlowerpotConstant.FLOWER_STATUS_3);
                    }else if(tempTime>FlowerpotConstant.FLOWER_SOW_TIME){
                        pot.setFlowerStatus(FlowerpotConstant.FLOWER_STATUS_2);
                    }else {
                        pot.setFlowerStatus(FlowerpotConstant.FLOWER_STATUS_1);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public void unlock(String id) {
        TreeHouseFlowerpot flowerpot=new TreeHouseFlowerpot();
        flowerpot.setId(id);
        flowerpot.setLockStatus(TreeHouseFlowerpotConstant.STATUS_UNLOCK);
        treeHouseFlowerpotMapper.updateByPrimaryKeySelective(flowerpot);
    }

    @Transactional
    @Override
    public void sowSeeds(String userId,String id, String goodsId) {
        TreeHouseFlowerpot flowerpot=new TreeHouseFlowerpot();
        flowerpot.setId(id);
        flowerpot.setGoodsId(goodsId);
        flowerpot.setSowingDate(new Date());
        flowerpot.setPlantId(UUIDUtil.generateUUID());
        flowerpot.setFlowerStatus(FlowerpotConstant.FLOWER_STATUS_1);
        treeHouseFlowerpotMapper.updateByPrimaryKeySelective(flowerpot);
        userGoodsService.subUserGoodsSingle(userId,goodsId,1);
    }

    @Transactional
    @Override
    public Map<String, Object> watering(TreeHouseFlowerpot flowerpot, String userId, String treeHouseUserId) {
        int exp=0;
        Map<String, Object> result=new HashMap<>();
        if(userId.equals(treeHouseUserId)){
            flowerpot.setLastWaterDate(new Date());
            flowerpot.setWaterCount(flowerpot.getWaterCount()+1);
            flowerpot.setFlowerStatus(flowerpot.getFlowerStatus()+1);
            treeHouseFlowerpotMapper.updateByPrimaryKey(flowerpot);
            treeHouseFlowerpotLogService.add(TreeHouseFlowerpotLogConstant.TYPE_WATERING,userId,flowerpot.getTreeHouseId(),null);
        }else{
            flowerpot.setLastWaterDate(new Date());
            flowerpot.setWaterCount(flowerpot.getWaterCount()+1);
            flowerpot.setFlowerStatus(flowerpot.getFlowerStatus()+1);
            treeHouseFlowerpotMapper.updateByPrimaryKey(flowerpot);
            UserRole userRole=roleService.getUserRoleByUserId(userId);
            TreeHouseWorkSetting workSetting=treeHouseWorkSettingService.getByRoleLevel(userRole.getRoleLevel());
            if(workSetting!=null){
                result=roleService.updateRoleExp(userId,workSetting.getOutput());
                exp=workSetting.getOutput();
            }
            treeHouseFlowerpotLogService.add(TreeHouseFlowerpotLogConstant.TYPE_WATERING,userId,treeHouseUserId,null);
        }
        treeHouseFlowerpotPlayerService.add(flowerpot.getPlantId(),userId);
        result.put("exp",exp);
        return result;
    }

    @Transactional
    @Override
    public Map<String, Object> spreadManure(TreeHouseFlowerpot flowerpot, String userId, String treeHouseUserId) {
        int exp=0;
        Map<String, Object> result=new HashMap<>();
        if(userId.equals(treeHouseUserId)){
            flowerpot.setSpreadManureCount(flowerpot.getSpreadManureCount()+1);
            flowerpot.setLastSpreadManureDate(new Date());
            flowerpot.setFlowerStatus(flowerpot.getFlowerStatus()+1);
            treeHouseFlowerpotMapper.updateByPrimaryKey(flowerpot);
            treeHouseFlowerpotLogService.add(TreeHouseFlowerpotLogConstant.TYPE_SPREAD_MANURE,userId,flowerpot.getTreeHouseId(),null);
        }else{
            UserRole userRole=roleService.getUserRoleByUserId(userId);
            TreeHouseWorkSetting workSetting=treeHouseWorkSettingService.getByRoleLevel(userRole.getRoleLevel());
            if(workSetting!=null){
                result=roleService.updateRoleExp(userId,workSetting.getOutput());
                exp=workSetting.getOutput();
            }
            flowerpot.setSpreadManureCount(flowerpot.getSpreadManureCount()+1);
            flowerpot.setLastSpreadManureDate(new Date());
            flowerpot.setFlowerStatus(flowerpot.getFlowerStatus()+1);
            treeHouseFlowerpotMapper.updateByPrimaryKey(flowerpot);
            treeHouseFlowerpotLogService.add(TreeHouseFlowerpotLogConstant.TYPE_SPREAD_MANURE,userId,flowerpot.getTreeHouseId(),null);
        }
        treeHouseFlowerpotPlayerService.add(flowerpot.getPlantId(),userId);
        result.put("exp",exp);
        return result;
    }

    @Override
    public void delete(String id) {
        TreeHouseFlowerpot flowerpot=treeHouseFlowerpotMapper.selectByPrimaryKey(id);
        flowerpot.setGoodsId(null);
        flowerpot.setSowingDate(null);
        treeHouseFlowerpotMapper.updateByPrimaryKey(flowerpot);
    }

    @Transactional
    @Override
    public Map<String,Object> harvest(String id) throws CheckException {
        Map<String,Object> result=new HashMap<>();
        TreeHouseFlowerpot flowerpot=treeHouseFlowerpotMapper.selectByPrimaryKey(id);
        long tempTime=System.currentTimeMillis();
        if((tempTime-flowerpot.getSowingDate().getTime())<(FlowerpotConstant.FLOWER_GROWTH_TIME*1000)){
            throw new CheckException("花还未长成!");
        }
        String goodsId=flowerpot.getGoodsId();
        flowerpot.setSpreadManureCount(0);
        flowerpot.setWaterCount(0);
        flowerpot.setGoodsId(null);
        flowerpot.setSowingDate(null);
        flowerpot.setLastSpreadManureDate(null);
        flowerpot.setLastWaterDate(null);
        flowerpot.setPlantId(null);
        flowerpot.setFlowerStatus(0);
        treeHouseFlowerpotMapper.updateByPrimaryKey(flowerpot);
        HoldFlowers flower=holdFlowersService.findByGoodsId(flowerpot.getTreeHouseId(),goodsId);
        if(flower==null){
            HoldFlowers flowers=new HoldFlowers();
            flowers.setGoodsId(goodsId);
            flowers.setUserId(flowerpot.getTreeHouseId());
            holdFlowersService.add(flowers);
        }
        //实际金币收益=基础金币收益+基础金币收益*(浇水次数+施肥次数)*0.01，浇水+施肥次数超过50次按50次算
        //实际环境值收益=基础环境值收益+基础环境值收益*(浇水次数+施肥次数)*0.01，浇水+施肥次数超过50次按50次算
        FlowerSetting setting=flowerSettingService.getById(goodsId);
        int waterAndSpreadCount=(flowerpot.getWaterCount()+flowerpot.getSpreadManureCount())>50?50:(flowerpot.getWaterCount()+flowerpot.getSpreadManureCount());
        if(setting!=null){
            List<UserGoods> goodsList=new ArrayList<>();
             if(setting.getMoneyAward()!=null){
                 int moneyCount=setting.getMoneyAward();
                 UserGoods goods=new UserGoods();
                 goods.setAmount(moneyCount+(int)(moneyCount*waterAndSpreadCount*0.01));
                 goods.setGoodsId(GoodsConstant.GOODS_MONEY);
                 goods.setType(GoodsTypeConstant.TYPE_PROP);
                 goods.setId(GoodsConstant.GOODS_MONEY);
                 goodsList.add(goods);
             }
             FlowerNumberSetting numberSetting=flowerNumberSettingMapper.selectByPrimaryKey(flowerpot.getDayGainCount());
             if(numberSetting!=null && numberSetting.getProbability()>0 && RandomUtil.probabilityEvent(numberSetting.getProbability()*100)){
                List<FlowerSeedsDrawSetting> drawSettingList=flowerSeedsDrawSettingMapper.selectByExample(new FlowerSeedsDrawSettingExample());
                int randRate=RandomUtil.random(1,1000);
                int mCount=0;
                for (int i = 0; i < drawSettingList.size(); i++) {
                     FlowerSeedsDrawSetting drawSetting = drawSettingList.get(i);
                     mCount=(int)(drawSetting.getProbability()*1000+mCount);
                     if(mCount>=randRate){
                         UserGoods goods=new UserGoods();
                         goods.setAmount(1);
                         goods.setGoodsId(drawSetting.getGoodsId());
                         goods.setType(GoodsTypeConstant.TYPE_PROP);
                         goods.setId(drawSetting.getGoodsId());
                         goodsList.add(goods);
                         break;
                     }
                 }
             }
             int environmentCount=setting.getEnviromentAward()+(int)(setting.getEnviromentAward()*waterAndSpreadCount*0.01);
             userGoodsService.addUserGoods(flowerpot.getTreeHouseId(),goodsList);
             result.put("goodsList",goodsList);
             treeHouseService.updateEnvironmentCount(flowerpot.getTreeHouseId(),environmentCount);
             result.put("environmentCount",environmentCount);
        }
        redisService.remove(RedisKey.HAVEST_OTHERS_FLOWER+flowerpot.getPlantId());
        treeHouseFlowerpotLogService.add(TreeHouseFlowerpotLogConstant.TYPE_HAVEST,flowerpot.getTreeHouseId(),flowerpot.getTreeHouseId(),null);
        taskService.taskProgress(TreeHouseFlowerpotConstant.TASK_TYPE_SHPZ,1,flowerpot.getTreeHouseId());
        return result;
    }

    @Override
    public Map<String, Object> harvestOthers(String flowerpotId, String userId) {
        Map<String,Object> result=new HashMap<>();
        TreeHouseFlowerpot flowerpot=treeHouseFlowerpotMapper.selectByPrimaryKey(flowerpotId);
        long tempTime=System.currentTimeMillis();
        if((tempTime-flowerpot.getSowingDate().getTime())<(FlowerpotConstant.FLOWER_GROWTH_TIME*1000)){
            throw new CheckException("花还未长成!");
        }
        Object obj=redisService.get(RedisKey.HAVEST_OTHERS_FLOWER+flowerpot.getPlantId());
        if(obj!=null){
            Map<String,String> data=(Map<String,String>)obj;
            if(data.entrySet().size()>30)
                throw new CheckException("已超过该盆栽的收获上限!");
            if(data.containsKey(userId)){
                throw new CheckException("你已收过了该盆栽！");
            }else{
                data.put(userId,"1");
                redisService.set(RedisKey.HAVEST_OTHERS_FLOWER+flowerpot.getPlantId(),data);
            }
        }else{
            Map<String,String> data=new HashMap<>();
            data.put(userId,"1");
            redisService.set(RedisKey.HAVEST_OTHERS_FLOWER+flowerpot.getPlantId(),data);
        }
        String goodsId=flowerpot.getGoodsId();
        FlowerSetting setting=flowerSettingService.getById(goodsId);
        int moneyCount=(int)(setting.getMoneyAward()*0.01);
        List<UserGoods> goodsList=new ArrayList<>();
        UserGoods goods=new UserGoods();
        goods.setAmount(moneyCount);
        goods.setGoodsId(GoodsConstant.GOODS_MONEY);
        goods.setType(GoodsTypeConstant.TYPE_PROP);
        goods.setId(GoodsConstant.GOODS_MONEY);
        goodsList.add(goods);
        userGoodsService.addUserGoods(userId,goodsList);
        result.put("goodsList",goodsList);
        return result;
    }

    @Transactional
    @Override
    public void sowSeedsAll(String userId) {
        List<PropInfoVO> propInfoVOS=userGoodsService.querySeedsPropList(userId);
        TreeHouseFlowerpotExample example=new TreeHouseFlowerpotExample();
        example.createCriteria().andTreeHouseIdEqualTo(userId).andGoodsIdIsNull().andLockStatusEqualTo(TreeHouseFlowerpotConstant.STATUS_UNLOCK);
        List<TreeHouseFlowerpot> flowerpots=treeHouseFlowerpotMapper.selectByExample(example);
        int i=0;
        for(TreeHouseFlowerpot flowerpot:flowerpots){
            PropInfoVO current=propInfoVOS.get(i);
            if(current.getAmount()>0){
                propInfoVOS.get(i).setAmount(current.getAmount()-1);
                userGoodsService.subUserGoodsSingle(userId,current.getGoodsId(),1);
                flowerpot.setGoodsId(current.getGoodsId());
                flowerpot.setSowingDate(new Date());
                flowerpot.setLastSpreadManureDate(new Date());
                flowerpot.setLastWaterDate(new Date());
            }else{
                i++;
            }
            treeHouseFlowerpotMapper.updateByPrimaryKey(flowerpot);
        }

    }

    @Transactional
    @Override
    public void wateringAll(String userId, String treeHouseUserId, Integer grade) {
        if(grade== TreeHouseConstant.SIGN_WORK_GRADE_PRIMARY ||
                grade==TreeHouseConstant.SIGN_WORK_GRADE_MEDIUM ||
                grade==TreeHouseConstant.SIGN_WORK_GRADE_SENIOR) {
            if (grade == TreeHouseConstant.SIGN_WORK_GRADE_PRIMARY) {
                //工作玩家 增加经验：8000  金币：7500 ，
                List<UserGoods> goodsList=new ArrayList<>();
                UserGoods goods=new UserGoods();
                goods.setGoodsId(GoodsConstant.GOODS_MONEY);
                goods.setAmount(TreeHouseConstant.GRADE_PRIMARY_MONEY);
                goodsList.add(goods);
                userGoodsService.addUserGoods(userId,goodsList);
                roleService.updateRoleExp(userId,TreeHouseConstant.GRADE_PRIMARY_EXP);
            }
            if (grade == TreeHouseConstant.SIGN_WORK_GRADE_MEDIUM) {
                //增加经验：12000     金币：12500
                List<UserGoods> goodsList=new ArrayList<>();
                UserGoods goods=new UserGoods();
                goods.setGoodsId(GoodsConstant.GOODS_MONEY);
                goods.setAmount(TreeHouseConstant.GRADE_MEDIUM_MONEY);
                goodsList.add(goods);
                userGoodsService.addUserGoods(userId,goodsList);
                roleService.updateRoleExp(userId,TreeHouseConstant.GRADE_MEDIUM_EXP);
            }
            if (grade == TreeHouseConstant.SIGN_WORK_GRADE_SENIOR) {
                //增加经验：16000     金币：17500
                List<UserGoods> goodsList=new ArrayList<>();
                UserGoods goods=new UserGoods();
                goods.setGoodsId(GoodsConstant.GOODS_MONEY);
                goods.setAmount(TreeHouseConstant.GRADE_SENIOR_MONEY);
                goodsList.add(goods);
                userGoodsService.addUserGoods(userId,goodsList);
                roleService.updateRoleExp(userId,TreeHouseConstant.GRADE_SENIOR_EXP);
            }
            treeHouseFlowerpotExtMapper.wateringAll(treeHouseUserId);
            friendService.updateIntimacy(userId, treeHouseUserId,TreeHouseFlowerpotConstant.INTIMACY);
            treeHouseFlowerpotLogService.add(TreeHouseFlowerpotLogConstant.TYPE_WATERING,userId,treeHouseUserId,null);
            this.sendEmailForFlowerSeeds(userId,treeHouseUserId);
        }
    }

    @Transactional
    @Override
    public void spreadManureAll(String userId, String treeHouseUserId, Integer grade) {
        if(grade== TreeHouseConstant.SIGN_WORK_GRADE_PRIMARY ||
        grade==TreeHouseConstant.SIGN_WORK_GRADE_MEDIUM ||
        grade==TreeHouseConstant.SIGN_WORK_GRADE_SENIOR){
            if (grade == TreeHouseConstant.SIGN_WORK_GRADE_PRIMARY) {
                //工作玩家 增加经验：8000  金币：7500 ，
                List<UserGoods> goodsList=new ArrayList<>();
                UserGoods goods=new UserGoods();
                goods.setGoodsId(GoodsConstant.GOODS_MONEY);
                goods.setAmount(TreeHouseConstant.GRADE_PRIMARY_MONEY);
                goodsList.add(goods);
                userGoodsService.addUserGoods(userId,goodsList);
                roleService.updateRoleExp(userId,TreeHouseConstant.GRADE_PRIMARY_EXP);
            }
            if (grade == TreeHouseConstant.SIGN_WORK_GRADE_MEDIUM) {
                //增加经验：12000     金币：12500
                List<UserGoods> goodsList=new ArrayList<>();
                UserGoods goods=new UserGoods();
                goods.setGoodsId(GoodsConstant.GOODS_MONEY);
                goods.setAmount(TreeHouseConstant.GRADE_MEDIUM_MONEY);
                goodsList.add(goods);
                userGoodsService.addUserGoods(userId,goodsList);
                roleService.updateRoleExp(userId,TreeHouseConstant.GRADE_MEDIUM_EXP);
            }
            if (grade == TreeHouseConstant.SIGN_WORK_GRADE_SENIOR) {
                //增加经验：16000     金币：17500
                List<UserGoods> goodsList=new ArrayList<>();
                UserGoods goods=new UserGoods();
                goods.setGoodsId(GoodsConstant.GOODS_MONEY);
                goods.setAmount(TreeHouseConstant.GRADE_SENIOR_MONEY);
                goodsList.add(goods);
                userGoodsService.addUserGoods(userId,goodsList);
                roleService.updateRoleExp(userId,TreeHouseConstant.GRADE_SENIOR_EXP);
            }
            treeHouseFlowerpotExtMapper.spreadManureAll(treeHouseUserId);
            friendService.updateIntimacy(userId, treeHouseUserId,TreeHouseFlowerpotConstant.INTIMACY);
            treeHouseFlowerpotLogService.add(TreeHouseFlowerpotLogConstant.TYPE_SPREAD_MANURE,userId,treeHouseUserId,null);

            this.sendEmailForFlowerSeeds(userId,treeHouseUserId);
        }
    }

    @Override
    public void harvestAll(String userId) {
        treeHouseFlowerpotExtMapper.harvestAll(userId,6,6);
    }

    @Override
    public void deleteAll(String userId) {
        treeHouseFlowerpotExtMapper.deleteAll(userId,50,50,6,6);
    }

    @Override
    public TreeHouseFlowerpot getById(String flowerpotId) {
        return treeHouseFlowerpotMapper.selectByPrimaryKey(flowerpotId);
    }

    private void sendEmailForFlowerSeeds(String userId,String treeHouseUserId){
        int randomValue=RandomUtil.random(1,1000);
        //28201 0.15  28202  0.14  28203  0.14  28204  0.14  28205 0.11  28206 0.11
        //28207 0.11  28208  0.04  28209  0.04  28210  0.01  28211 0.01
        Map<String,Object> data=new HashMap<>();
        Map<String,Object> items=new HashMap<>();
        String name="";
        if(randomValue<150){
            name="薰衣草种子";
            items.put(GoodsConstant.ITEM_SEEDS_28201,1);
        }
        if(randomValue>150 && randomValue<290){
            name="紫罗兰种子";
            items.put(GoodsConstant.ITEM_SEEDS_28202,1);
        }
        if(randomValue>290 && randomValue<430){
            name="风信子种子";
            items.put(GoodsConstant.ITEM_SEEDS_28203,1);
        }
        if(randomValue>430 && randomValue<570){
            name="茉莉花种子";
            items.put(GoodsConstant.ITEM_SEEDS_28204,1);
        }
        if(randomValue>570 && randomValue<680){
            name="曼陀罗种子";
            items.put(GoodsConstant.ITEM_SEEDS_28205,1);
        }
        if(randomValue>680 && randomValue<790){
            name="勿忘我种子";
            items.put(GoodsConstant.ITEM_SEEDS_28206,1);
        }
        if(randomValue>790 && randomValue<900){
            name="紫丁香种子";
            items.put(GoodsConstant.ITEM_SEEDS_28207,1);
        }
        if(randomValue>900 && randomValue<940){
            name="风铃草种子";
            items.put(GoodsConstant.ITEM_SEEDS_28208,1);
        }
        if(randomValue>940 && randomValue<980){
            name="满天星种子";
            items.put(GoodsConstant.ITEM_SEEDS_28209,1);
        }
        if(randomValue>980 && randomValue<990 && redisService.get(userId+"_"+GoodsConstant.ITEM_SEEDS_28210)==null){
            name="蓝色妖姬种子";
            items.put(GoodsConstant.ITEM_SEEDS_28210,1);
            redisService.set(userId+"_"+GoodsConstant.ITEM_SEEDS_28210,1,DateComputeUtil.getSecondsNextEarlyMorning());
        }
        if(randomValue>990 && randomValue<1000 && redisService.get(userId+"_"+GoodsConstant.ITEM_SEEDS_28211)==null){
            name="七彩玫瑰种子";
            items.put(GoodsConstant.ITEM_SEEDS_28211,1);
            redisService.set(userId+"_"+GoodsConstant.ITEM_SEEDS_28211,1,DateComputeUtil.getSecondsNextEarlyMorning());
        }
        data.put("prop",items);

        UserMailSingle userMailSingle = new UserMailSingle();
        userMailSingle.setReceiverId(userId);
        userMailSingle.setSenderId(treeHouseUserId);
        userMailSingle.setType(MailConstant.MAIL_TYPE_SINGLE);
        userMailSingle.setTitle("完成树屋工作，奖励花种");
        userMailSingle.setContent("您的运气太好了，在完成了工作之后获得了"+name+"*1");
        userMailSingle.setAccessory(JSON.toJSONString(data));
        userMailSingle.setRead(MailConstant.MAIL_READ_NO);
        userMailSingle.setReceive(MailConstant.MAIL_RECEIVE_NO);
        userMailSingleService.addUserMailSingle(userMailSingle);
    }

    @Override
    public void unlockByTreeHouseLevel(String treeHouseUserId, Integer level) {
        TreeHouseFlowerpotExample example=new TreeHouseFlowerpotExample();
        example.createCriteria().andTreeHouseIdEqualTo(treeHouseUserId).andLockLevelLessThanOrEqualTo(level);
        TreeHouseFlowerpot treeHouseFlowerpot=new TreeHouseFlowerpot();
        treeHouseFlowerpot.setLockStatus(FlowerpotConstant.UNLOCK_STATUS);
        treeHouseFlowerpotMapper.updateByExampleSelective(treeHouseFlowerpot,example);
    }
}
