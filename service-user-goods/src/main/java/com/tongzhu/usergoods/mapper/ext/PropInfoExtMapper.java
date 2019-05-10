package com.tongzhu.usergoods.mapper.ext;


import com.tongzhu.usergoods.mapper.vo.PropInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PropInfoExtMapper {
    List<PropInfoVO> queryPropList(@Param("userId") String userId,@Param("settingPosition") Integer settingPosition);

    List<PropInfoVO> queryPropListByType(@Param("userId") String userId,
                                         @Param("settingPosition") Integer settingPosition,
                                         @Param("type")Integer type);

    List<PropInfoVO> queryWarehousePropList(@Param("userId")String userId);
}