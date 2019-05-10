package com.tongzhu.usergoods.service;

import com.tongzhu.common.Pager;
import com.tongzhu.usergoods.mapper.vo.EquipmentInfoVO;
import com.tongzhu.usergoods.model.EquipmentInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2018/10/24 0024.
 */
public interface EquipmentInfoService {
    /**
     * 获取装备对象
     * @param goodsId
     * @return
     */
    EquipmentInfo getEquipmentInfo(String goodsId);

    /**
     * 获取用户装备
     * @param userId
     * @param pageNum
     * @param pageSize
     * @param settingPosition 0 背包 1仓库 2 已佩戴 null 获取所有
     * @return
     */
    Pager<EquipmentInfoVO> queryEquipmentInfoList(String userId, int pageNum, int pageSize, Integer settingPosition);

    boolean batchImport(String fileName, MultipartFile file) throws IOException;

    List<EquipmentInfo> findDressedEquipmentList(String goodsId);

    /**
     * 通过 id 和 original  获取装备信息
     * @param id
     * @param original  是否为原型武器 0 是 1 否
     * @return
     */
    EquipmentInfo getEquipmentInfoByIdAndOriginal(String id, Integer original);

    int update(EquipmentInfo equipmentInfo);

    int insert(EquipmentInfo equipmentInfo);

    EquipmentInfo getEquipmentInfoByWearPositionAndQuality(Integer wearPosition, int quality);

    int deleteByPrimaryKey(String intensifyId);

    Pager<EquipmentInfoVO> queryWarehouseEquipmentInfoList(String userId,Integer pageNum, Integer pageSize);
}
