package com.tongzhu.usergoods.service;

import com.tongzhu.usergoods.mapper.vo.PetVO;
import com.tongzhu.usergoods.model.PetInfo;
import com.tongzhu.usergoods.model.UserPet;

import java.util.List;

/**
 * Created by Administrator on 2018/12/7 0007.
 */
public interface UserPetService {

    /**
     * 获取宠物详情
     *
     * @param userId
     * @param petId  宠物实体id
     * @return
     */
    PetVO getPetVOByUserIdAndPetId(String userId, String petId);

    /**
     * 获取跟随宠物的信息
     *
     * @param userId
     * @param follow 1 不跟随 2 跟随
     * @return
     */
    PetVO getPetVOByUserIdAndFollow(String userId, Integer follow);


    /**
     * 查询用户拥有的宠物 overdue null 所有宠物 1 永久 2 限时
     *
     * @param userId
     * @param overdue
     * @return
     */
    List<UserPet> getUserPetList(String userId, Integer overdue);

    /**
     * 获取用户宠物信息
     *
     * @param userId
     * @param petId
     * @return
     */
    UserPet getUserPetByUserIdAndPetId(String userId, String petId);

    int updateByPrimaryKey(UserPet userPet);

    int insert(UserPet userPet);

    /**
     * 宠物增加经验
     *
     * @param userId 用户id
     * @param petId  宠物实体id
     * @param exp    经验值
     * @return
     */
    PetVO userFeedPet(String userId, String petId, int exp,Integer level);

    /**
     * 获取跟随宠物的信息
     *
     * @param userId
     * @param follow 1 不跟随 2 跟随
     * @return
     */
    UserPet getUserPetByUserIdAndFollow(String userId, Integer follow);

    /**
     * 获取用户限时宠物中的过期宠物
     *
     * @return
     */
    List<UserPet> getUserPetOverdueList();

    Integer delete(String id);

    /**
     * 更加宠物id查询用户宠物
     * @param id
     * @param userId
     * @return
     */
    UserPet getUserPetByUserIdAnId(int id, String userId);
}
