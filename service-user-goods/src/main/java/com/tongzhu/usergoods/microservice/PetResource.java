package com.tongzhu.usergoods.microservice;


import com.tongzhu.usergoods.constant.PetConstant;
import com.tongzhu.usergoods.mapper.vo.PetVO;
import com.tongzhu.usergoods.model.PetInfo;
import com.tongzhu.usergoods.model.UserPet;
import com.tongzhu.usergoods.service.PetInfoService;
import com.tongzhu.usergoods.service.UserPetService;
import com.tongzhu.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/petResource")
public class PetResource {

    @Autowired
    private PetInfoService petInfoService;

    @Autowired
    private UserPetService userPetService;


    @PostMapping("/getPetInfoList/{userId}")
    public List<PetInfo> getPetInfoList(@PathVariable("userId") String userId) {
        List<PetInfo> petInfoList = petInfoService.getPetInfoList(userId, null);
        return petInfoList;
    }

    @PostMapping("/getPetInfoByPetId/{petId}")
    public PetInfo getPetInfoByPetId(@PathVariable("petId") String petId) {
        PetInfo petInfo = petInfoService.getPetInfoByPetId(petId);
        return petInfo;
    }

    /**
     * 查询跟随的宠物
     *
     * @param userId
     * @return
     */
    @PostMapping("/getMyPetFollowed/{userId}")
    public UserPet getMyPetFollowed(@PathVariable("userId") String userId) {
        return userPetService.getUserPetByUserIdAndFollow(userId, PetConstant.PET_FOLLOW_YES);
    }

    /**
     * 查询跟随的宠物
     *
     * @param userId
     * @return
     */
    @PostMapping("/getPetVOByUserIdAndFollow/{userId}")
    public PetVO getPetVOByUserIdAndFollow(@PathVariable("userId") String userId) {
        return userPetService.getPetVOByUserIdAndFollow(userId, PetConstant.PET_FOLLOW_YES);
    }

    /**
     * 更新宠物溢出经验值
     *
     * @param userId
     * @return
     */
    @PostMapping("/udpatePetSpillExp/{userId}/{level}")
    public void udpatePetSpillExp(@PathVariable("userId") String userId, @PathVariable("level") Integer level) {
        List<PetInfo> petInfoList = petInfoService.getPetInfoList(userId, PetConstant.OVERDUE_PERPETUAL);
        for (PetInfo petInfo : petInfoList) {
            userPetService.userFeedPet(userId, petInfo.getPetId(), 0, level);
        }
    }
}
