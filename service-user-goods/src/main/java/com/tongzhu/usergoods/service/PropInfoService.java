package com.tongzhu.usergoods.service;


import com.tongzhu.common.Pager;
import com.tongzhu.usergoods.mapper.vo.PropInfoVO;
import com.tongzhu.usergoods.model.PropInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PropInfoService {
    /**
     * 获取道具
     * @param goodsId
     * @return
     */
    PropInfo getPropInfo(String goodsId);

    /**
     * 获取用户已穿戴的装备
     * @param userId
     * @return
     */
    List<PropInfo> getUserWarePropInfo(String userId);

    /**
     * 获取用户道具
     * @param userId
     * @param pageNum
     * @param pageSize
     * @param settingPosition 0 背包 1仓库 null 获取所有
     * @return
     */
    Pager<PropInfoVO> queryPropList(String userId, int pageNum, int pageSize, Integer settingPosition);

    boolean batchImport(String fileName, MultipartFile file) throws IOException;

    /**
     * 查找指定类型的道具
     * @param userId
     * @param pageNum
     * @param pageSize
     * @param settingPosition
     * @param type
     * @return
     */
    Pager<PropInfoVO> queryPropListByType(String userId, int pageNum, int pageSize, Integer settingPosition,Integer type);

    /**
     * 查询仓库中的道具
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    Pager<PropInfoVO> queryWarehousePropList(String userId, int pageNum, int pageSize);

    List<PropInfo> getPropInfoList();
}
