package com.tongzhu.user.service.impl;

import com.tongzhu.constant.GoodsConstant;
import com.tongzhu.user.constant.UserSkillConstant;
import com.tongzhu.user.domain.UserGoods;
import com.tongzhu.except.CheckException;
import com.tongzhu.user.mapper.UserSkillMapper;
import com.tongzhu.user.mapper.ext.UserSkillExtMapper;
import com.tongzhu.user.model.*;
import com.tongzhu.user.service.SkillSettingService;
import com.tongzhu.user.service.UserGoodsService;
import com.tongzhu.user.service.UserRoleService;
import com.tongzhu.user.service.UserSkillService;
import com.tongzhu.user.util.ItemGoodsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserSkillServiceImpl implements UserSkillService {

    @Autowired
    private UserSkillMapper userSkillMapper;

    @Autowired
    private UserSkillExtMapper userSkillExtMapper;

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private SkillSettingService skillSettingService;

    @Autowired
    private UserRoleService userRoleService;



    @Override
    public List<UserSkill> findByUserId(String userId) {
        UserSkillExample example=new UserSkillExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return userSkillMapper.selectByExample(example);
    }

    @Override
    public void add(UserSkill userSkill) {
        userSkillMapper.insert(userSkill);
    }

    /**
     * 升级技能(每次升一级)
     * @param id
     *
     */
    @Transactional
    @Override
    public Map<String,Object> upgradeSkillLevel(String id) {
        UserSkill userSkill=this.getById(id);
        Map<String,Object> data=new HashMap<>();
        if(userSkill!=null && userSkill.getLevel()< UserSkillConstant.MAX_SKILL_LEVEL){
            UserRole userRole=userRoleService.getByUserId(userSkill.getUserId());
            SkillSetting skillSetting=skillSettingService.getBySkillIdAndSkillLevel(userSkill.getSkillId(),userSkill.getLevel());
            if(userRole.getRoleLevel()<skillSetting.getRoleLevel())
                throw new CheckException("角色等级未达到！");

            UserSkill usKill=new UserSkill();
            usKill.setId(userSkill.getId());
            usKill.setLevel(userSkill.getLevel()+1);
            userSkillMapper.updateByPrimaryKeySelective(usKill);

            Map<String,Object>datas=userRoleService.updateUserRoleExp(userSkill.getUserId(),(0-skillSetting.getConsumeExperience()));
            List<UserGoods> goodsList=new ArrayList<>();
            UserGoods goods=new UserGoods();
            goods.setGoodsId(GoodsConstant.GOODS_MONEY);
            goods.setAmount(skillSetting.getConsumeMoney());
            goodsList.add(goods);
            List<UserGoods> userGoods=userGoodsService.subUserGoods(userSkill.getUserId(),goodsList);
            //金币不足异常
            if(userGoods==null) throw new CheckException(ItemGoodsUtil.getNameByGoodsId(GoodsConstant.GOODS_MONEY)+"不足");
            data.put("money",userGoods.get(0).getAmount());
            data.put("fullExp",datas.get("fullExp"));
            data.put("roleLevel",datas.get("roleLevel"));
            return data;
        }else {
            return null;
        }
    }

    /**
     * 升级技能(每次可升多级)
     * @param userId
     * @param skillId
     */
    @Transactional
    @Override
    public Map<String,Integer> upgradeSkillSomeLevel(String userId, Integer skillId) {
        UserSkill userSkill=this.findByUserIdAndSkillId(userId,skillId);
        Map<String,Integer> data=new HashMap<>();
        if(userSkill!=null && userSkill.getLevel()< UserSkillConstant.MAX_SKILL_LEVEL){
            UserRole userRole=userRoleService.getByUserId(userId);
            SkillSetting skillSetting=skillSettingService.getBySkillIdAndRoleLevel(skillId,userRole.getRoleLevel());
            if(userRole.getRoleLevel()<skillSetting.getRoleLevel())
                throw new CheckException("角色等级未达到！");
            data.put("money",0);
            UserSkill usKill=new UserSkill();
            usKill.setId(userSkill.getId());
            int consumeMoney=0;
            int consumeExp=0;
            long maxExp=userRole.getExperience();
            int maxMoney=0;
            UserGoods userGoods=userGoodsService.getByUserIdAndGoodsId(userId,GoodsConstant.GOODS_MONEY);
            if(userGoods!=null)  maxMoney=userGoods.getAmount();
            for(int i=userSkill.getLevel(),c=1;i<skillSetting.getSkillLevel();i++,c++){
                    SkillSetting setting=skillSettingService.getBySkillIdAndSkillLevel(skillId,i);
                    if(consumeExp+setting.getConsumeExperience()<=maxExp && consumeMoney+setting.getConsumeMoney()<=maxMoney){
                        consumeExp+=setting.getConsumeExperience();
                        consumeMoney+=setting.getConsumeMoney();
                        usKill.setLevel(userSkill.getLevel()+c);
                    }else{
                        break;
                    }
            }

            List<UserGoods> goodsList=new ArrayList<>();
            UserGoods goods=new UserGoods();
            goods.setGoodsId(GoodsConstant.GOODS_MONEY);
            goods.setAmount(consumeMoney);
            goodsList.add(goods);
            Map<String,Object> datas=userRoleService.updateUserRoleExp(userId,(0-consumeExp));
            List<UserGoods> userGoodsList=userGoodsService.subUserGoods(userId,goodsList);
            if(userGoodsList==null) return  null;
            data.put("money",userGoodsList.get(0).getAmount());
            //data.put("roleLevel",(Integer)datas.get("roleLevel"));
            //data.put("fullExp",((Long)datas.get("fullExp")).intValue());
            if(usKill.getLevel()!=null)
                userSkillMapper.updateByPrimaryKeySelective(usKill);
            return data;
        }else {
            return null;
        }
    }

    @Override
    public UserSkill findByUserIdAndSkillId(String userId, Integer skillId) {
        UserSkillExample example=new UserSkillExample();
        example.createCriteria().andUserIdEqualTo(userId).andSkillIdEqualTo(skillId);
        List<UserSkill> userSkills=userSkillMapper.selectByExample(example);
        if(userSkills!=null && !userSkills.isEmpty()){
            return userSkills.get(0);
        }
        return null;
    }


    @Override
    public List<UserSkill> findByUserIdAndType(String userId, Integer skillType) {
        return userSkillExtMapper.selectByUserIdSkillType(userId,skillType);
    }

    @Override
    public List<UserSkill> findByUserIdAndSkillIdList(String userId, List<Integer> skillIds) {
        UserSkillExample example=new UserSkillExample();
        example.createCriteria().andUserIdEqualTo(userId).andSkillIdIn(skillIds);
        return userSkillMapper.selectByExample(example);
    }

    @Override
    public Map<String,Integer> upgradeAllSkillByType(String userId, Integer skillType) {
        List<UserSkill> skillList=this.findByUserIdAndType(userId,skillType);
        Map<String,Integer> data=new HashMap<>();
        data.put("money",0);
        Map<String,Integer> consumeList=null;
        for(UserSkill skill:skillList){
            Map<String,Integer> result=this.upgradeSkillSomeLevel(userId,skill.getSkillId());
            if(result==null){
                break;
            }else{
                consumeList=result;
            }
        }
        if(consumeList!=null){
            if(consumeList.get("money")!=null){
                int money=consumeList.get("money")+data.get("money");
                data.put("money",money);
            }
        }
        return data;
    }

    @Override
    public List<UserSkill> findByUserIdAndBuffIdLike(String userId, String buffId) {
        return userSkillExtMapper.selectByUserIdBuffLike(userId,"%"+buffId+"%");
    }

    @Override
    public List<Skill> findUserNoneSkill(String userId) {
        return userSkillExtMapper.selectUserNoneSkill(userId);
    }

    @Override
    public UserSkill getById(String id) {
        return userSkillMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean checkSkillCanUpgrade(String userId) {
        List<UserSkill> userSkills= this.findByUserId(userId);
        UserRole role=userRoleService.getByUserId(userId);
        UserGoods userGoods=userGoodsService.getByUserIdAndGoodsId(userId,GoodsConstant.GOODS_MONEY);
        if(userSkills!=null && userSkills.size()>0 && role!=null) {
            for (UserSkill skill : userSkills) {
                SkillSetting setting=skillSettingService.getBySkillIdAndRoleLevel(skill.getSkillId(),role.getRoleLevel());
                SkillSetting skillSetting=skillSettingService.getBySkillIdAndSkillLevel(skill.getSkillId(),skill.getLevel());
                if(setting!=null && setting.getSkillLevel()!=null && skillSetting!=null && skillSetting.getSkillLevel()!=null && userGoods!=null){
                    if(skillSetting.getSkillLevel()<setting.getSkillLevel() &&
                            skillSetting.getConsumeExperience()<=role.getExperience() &&
                            skillSetting.getConsumeMoney()<=userGoods.getAmount()){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean checkSkillCanUpgrade(String userId, Integer skillType) {
        List<UserSkill> userSkills= this.findByUserIdAndType(userId,skillType);
        UserRole role=userRoleService.getByUserId(userId);
        UserGoods userGoods=userGoodsService.getByUserIdAndGoodsId(userId,GoodsConstant.GOODS_MONEY);
        if(userSkills!=null && userSkills.size()>0 && role!=null) {
            for (UserSkill skill : userSkills) {
                SkillSetting setting=skillSettingService.getBySkillIdAndRoleLevel(skill.getSkillId(),role.getRoleLevel());
                SkillSetting skillSetting=skillSettingService.getBySkillIdAndSkillLevel(skill.getSkillId(),skill.getLevel());
                if(setting!=null && setting.getSkillLevel()!=null && skillSetting!=null && skillSetting.getSkillLevel()!=null && userGoods!=null){
                    if(skillSetting.getSkillLevel()<setting.getSkillLevel() &&
                            skillSetting.getConsumeExperience()<=role.getExperience() &&
                            skillSetting.getConsumeMoney()<=userGoods.getAmount()){
                         return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void deleteByUserId(String s) {
        UserSkillExample example=new UserSkillExample();
        example.createCriteria().andUserIdEqualTo(s);
        userSkillMapper.deleteByExample(example);
    }
}
