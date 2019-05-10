package com.tongzhu.usergoods.service;

import com.tongzhu.usergoods.model.PetInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2018/12/7 0007.
 */
public interface PetInfoService {
    /**
     * 查询用户宠物 overdue null 所有宠物 1 永久 2 限时
     * @param userId
     * @param overdue
     * @return
     */
    List<PetInfo> getPetInfoList(String userId,Integer overdue);

    boolean batchImport(String fileName, MultipartFile file) throws IOException;

    /**
     * 根据宠物id和等级获取宠物配置 id 宠物id  nextLevel 等级
     * @param id
     * @param nextLevel
     * @return
     */
    PetInfo getPetInfoByIdAndLevel(Integer id, Integer nextLevel);

    /**
     * 根据宠物实体id获取宠物信息
     * @param petId
     * @return
     */
    PetInfo getPetInfoByPetId(String petId);
}
