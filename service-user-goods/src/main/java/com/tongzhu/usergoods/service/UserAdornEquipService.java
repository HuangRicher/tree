package com.tongzhu.usergoods.service;


import com.tongzhu.usergoods.model.UserAdornEquip;

public interface UserAdornEquipService {
    /**
     * 获取用户穿戴信息
     * @param userId
     * @return
     */
    UserAdornEquip getUserAdornEquip(String userId);

    int addUserAddornEquip(UserAdornEquip userAdornEquip);

    int updateUserAddornEquip(UserAdornEquip userAdornEquip);

    /**
     * 更新角色属性
     * @param userId
     * @param vitality
     * @param attack
     * @param spellAttacks
     * @param pdef
     * @param magdef
     * @param crit
     * @param dodge
     * @param hitRate
     * @param defenseCrit
     * @return
     */
    UserAdornEquip addUserAdornEquipAttribute(String userId, Double vitality, Double attack, Double spellAttacks, Double pdef, Double magdef, Double crit, Double dodge, Double hitRate, Double defenseCrit);

    /**
     *  获取增加后的属性不进行数据库更新
     * @param userId
     * @param vitality
     * @param attack
     * @param spellAttacks
     * @param pdef
     * @param magdef
     * @param crit
     * @param dodge
     * @param hitRate
     * @param defenseCrit
     * @return
     */
    UserAdornEquip getUserAdornEquipAttribute(String userId, Double vitality, Double attack, Double spellAttacks, Double pdef, Double magdef, Double crit, Double dodge, Double hitRate, Double defenseCrit);
}
