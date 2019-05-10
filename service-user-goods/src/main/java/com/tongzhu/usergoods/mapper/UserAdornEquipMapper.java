package com.tongzhu.usergoods.mapper;

import com.tongzhu.usergoods.model.UserAdornEquip;
import com.tongzhu.usergoods.model.UserAdornEquipExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAdornEquipMapper {
    int countByExample(UserAdornEquipExample example);

    int deleteByExample(UserAdornEquipExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserAdornEquip record);

    int insertSelective(UserAdornEquip record);

    List<UserAdornEquip> selectByExample(UserAdornEquipExample example);

    UserAdornEquip selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserAdornEquip record, @Param("example") UserAdornEquipExample example);

    int updateByExample(@Param("record") UserAdornEquip record, @Param("example") UserAdornEquipExample example);

    int updateByPrimaryKeySelective(UserAdornEquip record);

    int updateByPrimaryKey(UserAdornEquip record);
}