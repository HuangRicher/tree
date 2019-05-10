package com.tongzhu.usergoods.service;

import com.tongzhu.common.Pager;
import com.tongzhu.usergoods.mapper.vo.ArsenalInfoVO;
import com.tongzhu.usergoods.model.ArsenalInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2018/10/24 0024.
 */
public interface ArsenalInfoService {

    /**
     * 获取武器对象
     * @param goodsId
     * @return
     */
    ArsenalInfo getArsenalInfo(String goodsId);

    /**
     * 获取用户武器
     * @param userId
     * @param pageNum
     * @param pageSize
     * @param settingPosition  2 已佩戴  4武器库 null 获取所有
     * @return
     */
    Pager<ArsenalInfoVO> queryWeaponList(String userId, int pageNum, int pageSize, Integer settingPosition);

    boolean batchImport(String fileName, MultipartFile file) throws IOException;
    /**
     * 通过 id 和 original  获取武器信息
     * @param id
     * @param original  是否为原型武器 0 是 1 否
     * @return
     */
    ArsenalInfo getArsenalInfoByIdAndOriginal(String id, Integer original);

    int update(ArsenalInfo arsenalInfo);

    int insert(ArsenalInfo arsenalInfo);

    /**
     * 获取用户未拥有的武器
     * @param userId
     * @param profession
     * @return
     */
    List<ArsenalInfo> findUserAllNotGetWeaponList(String userId, String profession);
}
