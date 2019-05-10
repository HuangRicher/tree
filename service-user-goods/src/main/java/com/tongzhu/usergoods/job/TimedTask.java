package com.tongzhu.usergoods.job;

import com.alibaba.fastjson.JSON;
import com.tongzhu.usergoods.model.PetInfo;
import com.tongzhu.usergoods.model.UserAdornEquip;
import com.tongzhu.usergoods.model.UserPet;
import com.tongzhu.usergoods.service.ChatMessageService;
import com.tongzhu.usergoods.service.PetInfoService;
import com.tongzhu.usergoods.service.UserAdornEquipService;
import com.tongzhu.usergoods.service.UserPetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TimedTask {

    @Autowired
    private UserPetService userPetService;

    @Autowired
    private PetInfoService petInfoService;

    @Autowired
    private UserAdornEquipService userAdornEquipService;

    @Autowired
    private ChatMessageService chatMessageService;


    Logger log = LoggerFactory.getLogger(TimedTask.class);

    @Scheduled(cron = "0 0/10 * * * ?")
    public void work() {
        log.info("------更新宠物信息------");
        List<UserPet> userPetList = userPetService.getUserPetOverdueList();
        for (UserPet userPet : userPetList) {
            PetInfo petInfo = petInfoService.getPetInfoByPetId(userPet.getPetId());
            if (petInfo != null) {
                UserAdornEquip userAdornEquip = userAdornEquipService.getUserAdornEquip(userPet.getUserId());
                userAdornEquip.setAttack(userAdornEquip.getAttack() - petInfo.getAttack());
                userAdornEquip.setCrit(userAdornEquip.getCrit() - petInfo.getCrit());
                userAdornEquip.setDodge(userAdornEquip.getDodge() - petInfo.getDodge());
                userAdornEquip.setMagdef(userAdornEquip.getMagdef() - petInfo.getMagdef());
                userAdornEquip.setPdef(userAdornEquip.getPdef() - petInfo.getPdef());
                userAdornEquip.setVitality(userAdornEquip.getVitality() - petInfo.getVitality());
                userAdornEquip.setDefenseCrit(userAdornEquip.getDefenseCrit() - petInfo.getDefenseCrit());
                userAdornEquip.setHitRate(userAdornEquip.getHitRate() - petInfo.getHitRate());
                userAdornEquip.setSpellAttacks(userAdornEquip.getSpellAttacks() - petInfo.getSpellAttacks());
                userAdornEquip.setAlwaysFighting((double) Math.round(userAdornEquip.getVitality() * 0.2 + userAdornEquip.getAttack() + userAdornEquip.getSpellAttacks() +
                        userAdornEquip.getPdef() + userAdornEquip.getMagdef() + userAdornEquip.getCrit() * 1.5
                        + userAdornEquip.getDodge() * 1.5 + userAdornEquip.getHitRate() * 1.5 + userAdornEquip.getDefenseCrit() * 1.5));
                userAdornEquipService.updateUserAddornEquip(userAdornEquip);
            }
            userPetService.delete(userPet.getId());
            Map<String, Object> object = new HashMap<>();
            object.put("type", "pet");
            object.put("petId", userPet.getPetId());
            object.put("operation", "delete");
            chatMessageService.sendMessageToSomeBody(userPet.getUserId(), JSON.toJSONString(object));
        }
    }


}
