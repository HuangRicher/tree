package com.tongzhu.usergoods.microservice;

import com.tongzhu.usergoods.model.UserAdornEquip;
import com.tongzhu.usergoods.model.UserGoods;
import com.tongzhu.usergoods.service.UserAdornEquipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userAdornResource")
public class UserAdornResource {

    @Autowired
    private UserAdornEquipService userAdornEquipService;

    /**
     * 更新角色属性
     * @param userId
     * @param vitality 生命属性加成
     * @param attack 物理攻击属性加成
     * @param spellAttacks 法术攻击属性加成
     * @param pdef 物理防御属性加成
     * @param magdef 法术防御属性加成
     * @param crit 暴击属性加成
     * @param dodge 闪避属性加成
     * @param hitRate 命中属性加成
     * @param defenseCrit 抗暴击属性加成
     * @return
     */
    @PostMapping("/addUserAdornEquipAttribute/{userId}/{vitality}/{attack}/{spellAttacks}/{pdef}/{magdef}/{crit}/{dodge}/{hitRate}/{defenseCrit}")
    public UserAdornEquip addUserAdornEquipAttribute(@PathVariable("userId") String userId, @PathVariable("vitality") Double vitality,
                                                     @PathVariable("attack") Double attack,
                                                     @PathVariable("spellAttacks") Double spellAttacks,
                                                     @PathVariable("pdef") Double pdef,
                                                     @PathVariable("magdef") Double magdef,
                                                     @PathVariable("crit") Double crit,
                                                     @PathVariable("dodge") Double dodge,
                                                     @PathVariable("hitRate") Double hitRate,
                                                     @PathVariable("defenseCrit") Double defenseCrit) {
        return userAdornEquipService.addUserAdornEquipAttribute(userId, vitality, attack, spellAttacks, pdef, magdef, crit, dodge, hitRate, defenseCrit);
    }
    /**
     * 获取增加后的属性不进行数据库更新
     * @param userId
     * @param vitality 生命属性加成
     * @param attack 物理攻击属性加成
     * @param spellAttacks 法术攻击属性加成
     * @param pdef 物理防御属性加成
     * @param magdef 法术防御属性加成
     * @param crit 暴击属性加成
     * @param dodge 闪避属性加成
     * @param hitRate 命中属性加成
     * @param defenseCrit 抗暴击属性加成
     * @return
     */
    @PostMapping("/getUserAdornEquipAttribute/{userId}/{vitality}/{attack}/{spellAttacks}/{pdef}/{magdef}/{crit}/{dodge}/{hitRate}/{defenseCrit}")
    public UserAdornEquip getUserAdornEquipAttribute(@PathVariable("userId") String userId, @PathVariable("vitality") Double vitality,
                                                     @PathVariable("attack") Double attack,
                                                     @PathVariable("spellAttacks") Double spellAttacks,
                                                     @PathVariable("pdef") Double pdef,
                                                     @PathVariable("magdef") Double magdef,
                                                     @PathVariable("crit") Double crit,
                                                     @PathVariable("dodge") Double dodge,
                                                     @PathVariable("hitRate") Double hitRate,
                                                     @PathVariable("defenseCrit") Double defenseCrit) {
        return userAdornEquipService.getUserAdornEquipAttribute(userId, vitality, attack, spellAttacks, pdef, magdef, crit, dodge, hitRate, defenseCrit);
    }


}
