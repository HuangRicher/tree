package com.tongzhu.usergoods.service.impl;

import com.tongzhu.usergoods.constant.PacksackConstant;
import com.tongzhu.usergoods.mapper.UserAdornEquipMapper;
import com.tongzhu.usergoods.model.UserAdornEquip;
import com.tongzhu.usergoods.model.UserAdornEquipExample;
import com.tongzhu.usergoods.service.UserAdornEquipService;
import com.tongzhu.usergoods.service.UserService;
import com.tongzhu.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/10/9 0009.
 */
@Service
public class UserAdornEquipServiceImpl implements UserAdornEquipService {

    @Autowired
    private UserAdornEquipMapper userAdornEquipMapper;

    @Autowired
    private UserService userService;

    @Override
    public UserAdornEquip getUserAdornEquip(String userId) {
        UserAdornEquipExample userAdornEquipExample = new UserAdornEquipExample();
        userAdornEquipExample.createCriteria().andUserIdEqualTo(userId);
        List<UserAdornEquip> userAdornEquips = userAdornEquipMapper.selectByExample(userAdornEquipExample);
        if (userAdornEquips.size() > 0) {
            return userAdornEquips.get(0);
        }
        return null;
    }

    @Override
    public int addUserAddornEquip(UserAdornEquip userAdornEquip) {
        // 更新月榜
//        userService.updateCrunchies(userAdornEquip.getUserId(), PacksackConstant.CRUNCHIES_FIGHTING_TYPE, (int) Math.ceil(userAdornEquip.getAlwaysFighting()));
        return userAdornEquipMapper.insertSelective(userAdornEquip);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUserAddornEquip(UserAdornEquip userAdornEquip) {
        // 更新月榜
//        userService.updateCrunchies(userAdornEquip.getUserId(), PacksackConstant.CRUNCHIES_FIGHTING_TYPE, (int) Math.ceil(userAdornEquip.getAlwaysFighting()));
        return userAdornEquipMapper.updateByPrimaryKeySelective(userAdornEquip);
    }

    @Override
    public UserAdornEquip addUserAdornEquipAttribute(String userId, Double vitality, Double attack, Double spellAttacks, Double pdef, Double magdef,
                                                     Double crit, Double dodge, Double hitRate, Double defenseCrit) {
        UserAdornEquip userAdornEquip = this.getUserAdornEquip(userId);
        if (userAdornEquip == null) {
            userAdornEquip = new UserAdornEquip();
            userAdornEquip.setUserId(userId);
            userAdornEquip.setId(UUIDUtil.generateUUID());
            userAdornEquip.setVitality(vitality == null ? 0 : vitality);
            userAdornEquip.setAttack(attack == null ? 0 : attack);
            userAdornEquip.setSpellAttacks(spellAttacks == null ? 0 : spellAttacks);
            userAdornEquip.setPdef(pdef == null ? 0 : pdef);
            userAdornEquip.setMagdef(magdef == null ? 0 : magdef);
            userAdornEquip.setCrit(crit == null ? 0 : crit);
            userAdornEquip.setDodge(dodge == null ? 0 : dodge);
            userAdornEquip.setHitRate(hitRate == null ? 0 : hitRate);
            userAdornEquip.setDefenseCrit(defenseCrit == null ? 0 : defenseCrit);
            userAdornEquip.setAlwaysFighting((double) Math.round(userAdornEquip.getVitality() * 0.2 + userAdornEquip.getAttack() + userAdornEquip.getSpellAttacks() +
                    userAdornEquip.getPdef() + userAdornEquip.getMagdef() + userAdornEquip.getCrit() * 1.5
                    + userAdornEquip.getDodge() * 1.5 + userAdornEquip.getHitRate() * 1.5 + userAdornEquip.getDefenseCrit() * 1.5));
            this.addUserAddornEquip(userAdornEquip);

        } else {
            userAdornEquip.setVitality(userAdornEquip.getVitality() + (vitality == null ? 0 : vitality));
            userAdornEquip.setAttack(userAdornEquip.getAttack() + (attack == null ? 0 : attack));
            userAdornEquip.setSpellAttacks(userAdornEquip.getSpellAttacks() + (spellAttacks == null ? 0 : spellAttacks));
            userAdornEquip.setPdef(userAdornEquip.getPdef() + (pdef == null ? 0 : pdef));
            userAdornEquip.setMagdef(userAdornEquip.getMagdef() + (magdef == null ? 0 : magdef));
            userAdornEquip.setCrit(userAdornEquip.getCrit() + (crit == null ? 0 : crit));
            userAdornEquip.setDodge(userAdornEquip.getDodge() + (dodge == null ? 0 : dodge));
            userAdornEquip.setHitRate(userAdornEquip.getHitRate() + (hitRate == null ? 0 : hitRate));
            userAdornEquip.setDefenseCrit(userAdornEquip.getDefenseCrit() + (defenseCrit == null ? 0 : defenseCrit));
            userAdornEquip.setAlwaysFighting((double) Math.round(userAdornEquip.getVitality() * 0.2 + userAdornEquip.getAttack() + userAdornEquip.getSpellAttacks() +
                    userAdornEquip.getPdef() + userAdornEquip.getMagdef() + userAdornEquip.getCrit() * 1.5
                    + userAdornEquip.getDodge() * 1.5 + userAdornEquip.getHitRate() * 1.5 + userAdornEquip.getDefenseCrit() * 1.5));
            this.updateUserAddornEquip(userAdornEquip);
        }
        return userAdornEquip;
    }

    @Override
    public UserAdornEquip getUserAdornEquipAttribute(String userId, Double vitality, Double attack, Double spellAttacks, Double pdef, Double magdef, Double crit, Double dodge, Double hitRate, Double defenseCrit) {
        UserAdornEquip userAdornEquip = this.getUserAdornEquip(userId);
        if (userAdornEquip == null) {
            userAdornEquip = new UserAdornEquip();
            userAdornEquip.setUserId(userId);
            userAdornEquip.setId(UUIDUtil.generateUUID());
            userAdornEquip.setVitality(vitality == null ? 0 : vitality);
            userAdornEquip.setAttack(attack == null ? 0 : attack);
            userAdornEquip.setSpellAttacks(spellAttacks == null ? 0 : spellAttacks);
            userAdornEquip.setPdef(pdef == null ? 0 : pdef);
            userAdornEquip.setMagdef(magdef == null ? 0 : magdef);
            userAdornEquip.setCrit(crit == null ? 0 : crit);
            userAdornEquip.setDodge(dodge == null ? 0 : dodge);
            userAdornEquip.setHitRate(hitRate == null ? 0 : hitRate);
            userAdornEquip.setDefenseCrit(defenseCrit == null ? 0 : defenseCrit);
            userAdornEquip.setAlwaysFighting((double) Math.round(userAdornEquip.getVitality() * 0.2 + userAdornEquip.getAttack() + userAdornEquip.getSpellAttacks() +
                    userAdornEquip.getPdef() + userAdornEquip.getMagdef() + userAdornEquip.getCrit() * 1.5
                    + userAdornEquip.getDodge() * 1.5 + userAdornEquip.getHitRate() * 1.5 + userAdornEquip.getDefenseCrit() * 1.5));
        } else {
            userAdornEquip.setVitality(userAdornEquip.getVitality() + (vitality == null ? 0 : vitality));
            userAdornEquip.setAttack(userAdornEquip.getAttack() + (attack == null ? 0 : attack));
            userAdornEquip.setSpellAttacks(userAdornEquip.getSpellAttacks() + (spellAttacks == null ? 0 : spellAttacks));
            userAdornEquip.setPdef(userAdornEquip.getPdef() + (pdef == null ? 0 : pdef));
            userAdornEquip.setMagdef(userAdornEquip.getMagdef() + (magdef == null ? 0 : magdef));
            userAdornEquip.setCrit(userAdornEquip.getCrit() + (crit == null ? 0 : crit));
            userAdornEquip.setDodge(userAdornEquip.getDodge() + (dodge == null ? 0 : dodge));
            userAdornEquip.setHitRate(userAdornEquip.getHitRate() + (hitRate == null ? 0 : hitRate));
            userAdornEquip.setDefenseCrit(userAdornEquip.getDefenseCrit() + (defenseCrit == null ? 0 : defenseCrit));
            userAdornEquip.setAlwaysFighting((double) Math.round(userAdornEquip.getVitality() * 0.2 + userAdornEquip.getAttack() + userAdornEquip.getSpellAttacks() +
                    userAdornEquip.getPdef() + userAdornEquip.getMagdef() + userAdornEquip.getCrit() * 1.5
                    + userAdornEquip.getDodge() * 1.5 + userAdornEquip.getHitRate() * 1.5 + userAdornEquip.getDefenseCrit() * 1.5));
        }
        return userAdornEquip;
    }
}
