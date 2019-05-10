package com.tongzhu.usergoods.service.impl;

import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.usergoods.constant.PetConstant;
import com.tongzhu.usergoods.domain.UserRole;
import com.tongzhu.usergoods.mapper.UserPetMapper;
import com.tongzhu.usergoods.mapper.ext.UserPetExtMapper;
import com.tongzhu.usergoods.mapper.vo.PetVO;
import com.tongzhu.usergoods.model.*;
import com.tongzhu.usergoods.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/12/7 0007.
 */
@Service
public class UserPetServiceImpl implements UserPetService {

    @Autowired
    private UserPetMapper userPetMapper;

    @Autowired
    private UserPetExtMapper userPetExtMapper;

    @Autowired
    private UserPetService userPetService;

    @Autowired
    private PetLevelInfoService petLevelInfoService;

    @Autowired
    private PetInfoService petInfoService;

    @Autowired
    private UserAdornEquipService userAdornEquipService;

    @Autowired
    private RoleService roleService;

    @Override
    public List<UserPet> getUserPetList(String userId, Integer overdue) {
        UserPetExample userPetExample = new UserPetExample();
        if (overdue == PetConstant.OVERDUE_PERPETUAL) {
            userPetExample.createCriteria().andUserIdEqualTo(userId).andExpirationTimeIsNull();
            return userPetMapper.selectByExample(userPetExample);
        } else if (overdue == PetConstant.OVERDUE_DEADLINE) {
            userPetExample.createCriteria().andUserIdEqualTo(userId).andExpirationTimeGreaterThanOrEqualTo(new Date());
            return userPetMapper.selectByExample(userPetExample);
        } else {
            UserPetExample.Criteria u = userPetExample.createCriteria();
            u.andUserIdEqualTo(userId).andExpirationTimeGreaterThanOrEqualTo(new Date());
            UserPetExample.Criteria uc = userPetExample.createCriteria();
            uc.andUserIdEqualTo(userId).andExpirationTimeIsNull();
            userPetExample.or(u);
            userPetExample.or(uc);
            List<UserPet> userPetList = userPetMapper.selectByExample(userPetExample);
            return userPetList;
        }
    }

    @Override
    public UserPet getUserPetByUserIdAndPetId(String userId, String petId) {
        UserPetExample userPetExample = new UserPetExample();
        userPetExample.createCriteria().andUserIdEqualTo(userId).andPetIdEqualTo(petId);
        List<UserPet> userPetList = userPetMapper.selectByExample(userPetExample);
        if (userPetList.size() > 0) {
            return userPetList.get(0);
        }
        return null;
    }

    @Override
    public int updateByPrimaryKey(UserPet userPet) {
        return userPetMapper.updateByPrimaryKey(userPet);
    }

    @Override
    public int insert(UserPet userPet) {
        return userPetMapper.insertSelective(userPet);
    }

    @Override
    public PetVO userFeedPet(String userId, String petId, int exp, Integer level) {
        UserRole userRoleByUserId = roleService.getUserRoleByUserId(userId);
        if (userRoleByUserId == null) {
            return null;
        }
        if (level == null) {
            level = userRoleByUserId.getRoleLevel();
        }
        UserPet pet = userPetService.getUserPetByUserIdAndPetId(userId, petId);
        exp = exp + pet.getSpillExp();
        Integer spillExp = 0;
        while (exp > 0) {
            PetVO petVO = userPetService.getPetVOByUserIdAndPetId(userId, petId);
            if (petVO == null) {
                return null;
            }
            if (petVO.getLevel() == PetConstant.LEVEL_MAX && petVO.getUpgradeExp().intValue() - petVO.getExp().intValue() <= 0) {
                return petVO;
            }
            int i = petVO.getUpgradeExp().intValue() - petVO.getExp().intValue();
            if (i > exp) {
                UserPet userPet = userPetService.getUserPetByUserIdAndPetId(userId, petId);
                userPet.setExp(userPet.getExp() + exp);
                userPet.setSpillExp(spillExp);
                userPetService.updateByPrimaryKey(userPet);
                break;
            } else {
                UserPet userPet = userPetService.getUserPetByUserIdAndPetId(userId, petId);
                if (petVO.getLevel() - level >= PetConstant.PET_USER_LEVEL && i == 0) {
                    userPet.setSpillExp(exp);
                    userPetService.updateByPrimaryKey(userPet);
                    break;
                }
                PetLevelInfo petLevelInfo = petLevelInfoService.getPetLevelInfoByLevel(petVO.getLevel());
                PetInfo petInfo = null;
                if (petVO.getLevel() != PetConstant.LEVEL_MAX) {
                    petInfo = petInfoService.getPetInfoByIdAndLevel(petVO.getId(), petLevelInfo.getNextLevel());
                }

                PetInfo petInfoOld = petInfoService.getPetInfoByPetId(userPet.getPetId());
                if (petVO.getLevel() - level >= PetConstant.PET_USER_LEVEL) {
                    spillExp = exp - petVO.getUpgradeExp() + userPet.getExp();
                    userPet.setExp(petVO.getUpgradeExp());
                    userPet.setSpillExp(spillExp);
                    userPetService.updateByPrimaryKey(userPet);
                    exp = exp - i;
                } else {
                    if (petInfo == null) {
                        userPet.setExp(petVO.getUpgradeExp());
                        userPet.setSpillExp(spillExp);
                        userPetService.updateByPrimaryKey(userPet);
                        exp = exp - i;
                        continue;
                    }
                    userPet.setExp(0);
                    userPet.setPetId(petInfo.getPetId());
                    userPet.setSpillExp(spillExp);
                    userPetService.updateByPrimaryKey(userPet);
                    petId = userPet.getPetId();
                    exp = exp - i;
                    UserAdornEquip userAdornEquip = userAdornEquipService.getUserAdornEquip(userId);
                    userAdornEquip.setAttack(userAdornEquip.getAttack() + petInfo.getAttack() - petInfoOld.getAttack());
                    userAdornEquip.setSpellAttacks(userAdornEquip.getSpellAttacks() + petInfo.getSpellAttacks() - petInfoOld.getSpellAttacks());
                    userAdornEquip.setPdef(userAdornEquip.getPdef() + petInfo.getPdef() - petInfoOld.getPdef());
                    userAdornEquip.setMagdef(userAdornEquip.getMagdef() + petInfo.getMagdef() - petInfoOld.getMagdef());
                    userAdornEquip.setVitality(userAdornEquip.getVitality() + petInfo.getVitality() - petInfoOld.getVitality());
                    userAdornEquip.setHitRate(userAdornEquip.getHitRate() + petInfo.getHitRate() - petInfoOld.getHitRate());
                    userAdornEquip.setDodge(userAdornEquip.getDodge() + petInfo.getDodge() - petInfoOld.getDodge());
                    userAdornEquip.setCrit(userAdornEquip.getCrit() + petInfo.getCrit() - petInfoOld.getCrit());
                    userAdornEquip.setDefenseCrit(userAdornEquip.getDefenseCrit() + petInfo.getDefenseCrit() - petInfoOld.getDefenseCrit());
                    userAdornEquip.setAlwaysFighting(userAdornEquip.getAlwaysFighting() + petInfo.getFightingCapacity() - petInfoOld.getFightingCapacity());
                    userAdornEquipService.updateUserAddornEquip(userAdornEquip);
                }
            }
        }
        PetVO petVO = userPetService.getPetVOByUserIdAndPetId(userId, petId);
        return petVO;
    }

    @Override
    public UserPet getUserPetByUserIdAndFollow(String userId, Integer follow) {
        UserPetExample userPetExample = new UserPetExample();
        userPetExample.createCriteria().andUserIdEqualTo(userId).andFollowEqualTo(follow);
        List<UserPet> userPetList = userPetMapper.selectByExample(userPetExample);
        if (userPetList.size() > 0) {
            return userPetList.get(0);
        }
        return null;
    }

    @Override
    public List<UserPet> getUserPetOverdueList() {
        UserPetExample userPetExample = new UserPetExample();
        userPetExample.createCriteria().andExpirationTimeLessThanOrEqualTo(new Date());
        List<UserPet> userPetList = userPetMapper.selectByExample(userPetExample);
        return userPetList;
    }

    @Override
    public Integer delete(String id) {
        return userPetMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UserPet getUserPetByUserIdAnId(int id, String userId) {
        List<UserPet> userPetList = userPetExtMapper.getUserPetByUserIdAnId(id, userId);
        if (userPetList.size() > 0) {
            return userPetList.get(0);
        }
        return null;
    }


    @Override
    public PetVO getPetVOByUserIdAndPetId(String userId, String petId) {
        List<PetVO> list = userPetExtMapper.getUsetPetByUserIdAndPetId(userId, petId);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public PetVO getPetVOByUserIdAndFollow(String userId, Integer follow) {
        return userPetExtMapper.getPetVOByUserIdAndFollow(userId, follow);
    }
}
