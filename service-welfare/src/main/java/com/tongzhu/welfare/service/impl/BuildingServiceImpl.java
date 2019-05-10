package com.tongzhu.welfare.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.javafx.image.IntPixelGetter;
import com.tongzhu.constant.MessageConstant;
import com.tongzhu.welfare.constant.WelfareConstant;
import com.tongzhu.welfare.domain.PropInfo;
import com.tongzhu.welfare.model.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongzhu.except.CheckException;
import com.tongzhu.welfare.constant.BuildingConstant;
import com.tongzhu.welfare.constant.MarryConstant;
import com.tongzhu.welfare.domain.TreeHouse;
import com.tongzhu.welfare.domain.UserGoods;
import com.tongzhu.welfare.domain.UserRole;
import com.tongzhu.welfare.enums.ChurchlvlEnum;
import com.tongzhu.welfare.enums.CoinlvlEnum;
import com.tongzhu.welfare.enums.MarriagelvlEnum;
import com.tongzhu.welfare.enums.PetshoplvlEnum;
import com.tongzhu.welfare.enums.SmithylvlEnum;
import com.tongzhu.welfare.enums.TreeHouselvlEnum;
import com.tongzhu.welfare.enums.WelfarelvlEnum;
import com.tongzhu.welfare.enums.WineshoplvlEnum;
import com.tongzhu.welfare.mapper.BuildingChurchSettingMapper;
import com.tongzhu.welfare.mapper.BuildingCoffersSettingMapper;
import com.tongzhu.welfare.mapper.BuildingCoinReduceLogMapper;
import com.tongzhu.welfare.mapper.BuildingGoldLogMapper;
import com.tongzhu.welfare.mapper.BuildingInfoMapper;
import com.tongzhu.welfare.mapper.BuildingMarriageSettingMapper;
import com.tongzhu.welfare.mapper.BuildingPetshopSettingMapper;
import com.tongzhu.welfare.mapper.BuildingSmithySettingMapper;
import com.tongzhu.welfare.mapper.BuildingStatueSettingMapper;
import com.tongzhu.welfare.mapper.BuildingTreeSettingMapper;
import com.tongzhu.welfare.mapper.BuildingUserMapper;
import com.tongzhu.welfare.mapper.BuildingWelfareSettingMapper;
import com.tongzhu.welfare.mapper.BuildingWineshopSettingMapper;
import com.tongzhu.welfare.redis.RedisService;
import com.tongzhu.welfare.service.BuildingService;
import com.tongzhu.welfare.service.FriendService;
import com.tongzhu.welfare.service.MessageService;
import com.tongzhu.welfare.service.TreeHouseService;
import com.tongzhu.welfare.service.UserGoodsService;
import com.tongzhu.welfare.service.UserRoleService;
import com.tongzhu.welfare.vo.BuildingGoldVo;
import com.tongzhu.welfare.vo.BuildingVo;
import com.tongzhu.welfare.vo.ReceiveGoldVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Administrator
 */
@Service
public class BuildingServiceImpl implements BuildingService {

//	private static Logger log = LoggerFactory.getLogger(BuildingServiceImpl.class);

    @Autowired
    BuildingUserMapper buildingUserMapper;
    @Autowired
    BuildingInfoMapper buildingInfoMapper;
    @Autowired
    BuildingCoffersSettingMapper buildingCoffersSettingMapper;
    @Autowired
    BuildingWelfareSettingMapper buildingWelfareSettingMapper;
    @Autowired
    BuildingMarriageSettingMapper buildingMarriageSettingMapper;
    @Autowired
    BuildingChurchSettingMapper buildingChurchSettingMapper;
    @Autowired
    BuildingPetshopSettingMapper buildingPetshopSettingMapper;
    @Autowired
    BuildingSmithySettingMapper buildingSmithySettingMapper;
    @Autowired
    BuildingStatueSettingMapper buildingStatueSettingMapper;
    @Autowired
    BuildingWineshopSettingMapper buildingWineshopSettingMapper;
    @Autowired
    BuildingGoldLogMapper buildingGoldLogMapper;
    @Autowired
    UserGoodsService userGoodsService;
    @Autowired
    TreeHouseService treeHouseService;
    @Autowired
    BuildingTreeSettingMapper buildingTreeSettingMapper;
    @Autowired
    RedisService redisService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    BuildingCoinReduceLogMapper buildingCoinReduceLogMapper;
    @Autowired
    FriendService friendService;
    @Autowired
    private MessageService messageService;

    @Override
    public BuildingVo getTreeHouseByUserId(int treeGrade) {
        Map<String, Object> map = commonSetting(treeGrade, 1);

        //人气值
        long ambienceCount = (long) map.get("ambienceCount");
        //娱乐值
        long amusementCount = (long) map.get("amusementCount");
        //环境值
        long environmentCount = (long) map.get("environmentCount");
        BuildingVo buildingVo = new BuildingVo();
        buildingVo.setAmbienceCount(ambienceCount);
        buildingVo.setAmusementCount(amusementCount);
        buildingVo.setEnvironmentCount(environmentCount);
        return buildingVo;
    }


    @Override
    public List<BuildingVo> getbuildingByUserId(String userId) {
        BuildingUserExample example = new BuildingUserExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<BuildingUser> list = buildingUserMapper.selectByExample(example);
        List<BuildingVo> list2 = new ArrayList<>();
        UserGoods goldCount = userGoodsService.getGoodsCount(userId, "50001");
        UserRole userRole = userRoleService.getUserRoleByUserId(userId);
        int roleLevel = userRole.getRoleLevel();
        //需要添加每个建筑的可领取金币数
        if (list != null && list.size() > 0) {
            BuildingVo buildingVo = new BuildingVo();
            for (BuildingUser buildingUser : list) {
                buildingVo = new BuildingVo();
                int buildingType = buildingUser.getBuildingType();
                int grade = buildingUser.getBuildingGrade();
                Map<String, Object> map = commonSetting(grade, buildingType);
                int outputRate = (int) map.get("outputRate");
                //下一等级金币出产速率
                int nextOutputRate = (int) map.get("nextOutputRate");
                int expendGold = (int) map.get("expendGold");
                int updateTime = (int) map.get("updateTime");
                // 容纳人数
                int containNum = (int) map.get("containNum");
                // 下个容纳人数
                int nextContainNum = (int) map.get("nextContainNum");
                //容纳物品数
                int containThingNum = (int) map.get("containThingNum");
                //下个容纳物品数
                int nextContainThingNum = (int) map.get("nextContainThingNum");
                //最多金币数
                int goldMax = (int) map.get("goldMax");
                //下一最多金币数
                int nextGoldMax = (int) map.get("nextGoldMax");
                //人气值
                long ambienceCount = (long) map.get("ambienceCount");
                //娱乐值
                long amusementCount = (long) map.get("amusementCount");
                //环境值
                long environmentCount = (long) map.get("environmentCount");
                //建筑升级状态,默认为升级完成
                int buildingStatus = 2;
                TreeHouse treeHouse = treeHouseService.findByUserId(userId);
                int treeGrade = treeHouse.getLevel();
                // 1 升级中 2 未升级
                Integer buildingUpdateStatus = (Integer) redisService.get(new StringBuilder("buildingUpdateStatus").append(userId).append(Integer.valueOf(buildingType)).toString());

                Long updateStatusTime = redisService.getInvalid(new StringBuilder("buildingUpdateStatus").append(userId).append(Integer.valueOf(buildingType)).toString());
                if (updateStatusTime != null && updateStatusTime>0) {
                    updateTime = Integer.valueOf((updateStatusTime / 60L) + "") + 1;
                }
                if ((buildingUser.getUpdateEndTime() != null && buildingUser.getUpdateEndTime().getTime() > (System.currentTimeMillis())) || (buildingUpdateStatus != null && buildingUpdateStatus == 1)) {
                    buildingStatus = BuildingConstant.BUILDING_UPDATE_TRUE;
                } else {
                    //建筑等级不能大与树屋等级，树屋等级不能大于角色等级,判断建筑是否满足升级条件，升级所需消耗品是否足够，等级是否已经满足
                    if (buildingType == BuildingConstant.BUILDING_TYPE_HOUSE) {
                        //树屋等级不高于角色等级，且背包内金币数量大于升级所需消耗数量
                        if (treeHouse.getAmbienceCount() >= ambienceCount && treeHouse.getAmusementCount() >= amusementCount && treeHouse.getEnvironmentCount() >= environmentCount) {
                            if (treeGrade + 1 <= roleLevel && goldCount.getAmount() > expendGold) {
                                buildingStatus = BuildingConstant.BUILDING_UPDATE_READY;
                            }
                        } else {
                            buildingStatus = BuildingConstant.BUILDING_UPDATE_FALSE;
                        }
                    } else {
                        //建筑等级不高于树屋等级，且背包内金币数量大于升级所需消耗数量
                        if (treeGrade >= grade + 1 && goldCount.getAmount() > expendGold) {
                            if (buildingUser.getBuildingOpen() == 0) {
                                //计算背包内的金币数是否大于升级所要消耗的金币数
                                buildingStatus = BuildingConstant.BUILDING_UPDATE_READY;
                            } else {
                                //计算背包内的金币数是否大于升级所要消耗的金币数
                                buildingStatus = BuildingConstant.BUILDING_UPDATE_FALSE;
                            }
                        } else {
                            buildingStatus = BuildingConstant.BUILDING_UPDATE_FALSE;
                        }
                    }
                }
                buildingVo.setBuildingName(buildingUser.getBuildingName());
                buildingVo.setBuildingStatus(buildingStatus);
                buildingVo.setContainNum(containNum);
                buildingVo.setContainThingNum(containThingNum);
                buildingVo.setGoldMax(goldMax);
                buildingVo.setGoldOutPut(outputRate);
                buildingVo.setNextContainNum(nextContainNum);
                buildingVo.setNextContainThingNum(nextContainThingNum);
                buildingVo.setNextGoldOutPut(nextOutputRate);
                buildingVo.setRoleGrade(grade);
                buildingVo.setNextRoleGrade(grade + 1);
                buildingVo.setUpdateGold(expendGold);
                buildingVo.setUpdateTime(updateTime);
                buildingVo.setNextGoldMax(nextGoldMax);
                buildingVo.setCurrentGrade(grade);
                buildingVo.setNextCurrentGrade(grade + 1);
                buildingVo.setBuildingType(buildingType);

                buildingVo.setTreeGrade(treeGrade);
                buildingVo.setAmbienceCount(ambienceCount);
                buildingVo.setAmusementCount(amusementCount);
                buildingVo.setEnvironmentCount(environmentCount);
                buildingVo.setBuildingOpen(buildingUser.getBuildingOpen());
                list2.add(buildingVo);
            }
            return list2;
        } else {
            return null;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void initBuildingUser(String userId) {
        Date dastart = new Date();
        BuildingUser buildingUser = new BuildingUser();
        BuildingInfoExample example = new BuildingInfoExample();
        BuildingInfo buildingInfo = new BuildingInfo();
        int outputRate = 0;
        int outputMin = 0;
        int outputMax = 0;
        int expendGold = 0;

        for (int i = 1; i <= 8; i++) {
            Map<String, Object> map = commonSetting(1, i);
            outputRate = (int) map.get("outputRate");
            outputMin = (int) map.get("outputMin");
            outputMax = (int) map.get("outputMax");
            expendGold = (int) map.get("expendGold");

            example = new BuildingInfoExample();
            example.createCriteria().andTypeEqualTo(i);
            List<BuildingInfo> list = buildingInfoMapper.selectByExample(example);
            buildingInfo = list.get(0);

            buildingUser = new BuildingUser();
            buildingUser.setBuildingDetailed(buildingInfo.getDetailed());
            //初始化最开始的等级，目前默认为1级
            buildingUser.setBuildingGrade(1);
            buildingUser.setBuildingName(buildingInfo.getName());
            if (i == BuildingConstant.BUILDING_TYPE_HOUSE || i == BuildingConstant.BUILDING_TYPE_MARRIAGE || i == BuildingConstant.BUILDING_TYPE_STATUE) {
                buildingUser.setBuildingStatus(BuildingConstant.BUILDING_OUTPUT_FALSE);
            } else {
                buildingUser.setBuildingStatus(BuildingConstant.BUILDING_OUTPUT_TRUE);
            }
            if (i == BuildingConstant.BUILDING_TYPE_MARRIAGE) {
                buildingUser.setBuildingOpen(BuildingConstant.BUILDING_OPEN_FALSE);
            } else {
                buildingUser.setBuildingOpen(BuildingConstant.BUILDING_OPEN_TRUE);
            }

            buildingUser.setBuildingType(i);
            buildingUser.setBiuldingOutputMin(outputMin);
            buildingUser.setBuildingOutputMax(outputMax);
            buildingUser.setBuildingOutputRate(outputRate);
            buildingUser.setCreatedTime(new Date(System.currentTimeMillis()));
            buildingUser.setBuildingUpgrade(expendGold);
            buildingUser.setUserId(userId);
            buildingUser.setBuildingId(i);
            buildingUserMapper.insert(buildingUser);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer upgradeBuildingTime(String userId, int buildingType, int userGrade) throws Exception {
        BuildingUserExample example = new BuildingUserExample();
        example.createCriteria().andBuildingTypeEqualTo(buildingType).andUserIdEqualTo(userId);
        BuildingUser record;
        List<BuildingUser> list = buildingUserMapper.selectByExample(example);
        TreeHouse treeHouse = treeHouseService.findByUserId(userId);
        //树屋等级  其他建筑等级不高于树屋等级，树屋等级不高于角色等级
        int treeGrade = treeHouse.getLevel();
        //多少分钟之后才能升级完成。还需要处理判断用户的余额是否大于升级所需的金币数
        if (list != null && list.size() > 0) {
            record = list.get(0);
            if (record.getBuildingOpen() == BuildingConstant.BUILDING_OPEN_FALSE) {
                throw new CheckException("当前建筑未开启，请达到指定等级后进行开启！");
            }
            if (record.getBuildingGrade() >= WelfareConstant.BUILDING_MAX_LEVEL) {
                throw new CheckException("建筑等级已达到最高等级，不能升级");
            }
            int grade = record.getBuildingGrade();
            Map<String, Object> map = commonSetting(grade, buildingType);
            //人气值
            long ambienceCount = (long) map.get("ambienceCount");
            //娱乐值
            long amusementCount = (long) map.get("amusementCount");
            //环境值
            long environmentCount = (long) map.get("environmentCount");
            int expendGold = (int) map.get("expendGold");
            UserGoods goldCount = userGoodsService.getGoodsCount(userId, "50001");
            int updateTime = (int) map.get("updateTime");
            Date updateEndTime = record.getUpdateEndTime();
            if (buildingType == BuildingConstant.BUILDING_TYPE_HOUSE) {
                //树屋等级不高于角色等级，且背包内金币数量大于升级所需消耗数量
                if (treeHouse.getAmbienceCount() >= ambienceCount && treeHouse.getAmusementCount() >= amusementCount
                        && treeHouse.getEnvironmentCount() >= environmentCount) {
                    if (treeGrade + 1 <= userGrade && goldCount.getAmount() > expendGold) {
                        updateEndTime = new Date(System.currentTimeMillis() + updateTime * 1000 * 60);
                        record.setUpdateEndTime(updateEndTime);
                        buildingUserMapper.updateByPrimaryKey(record);

                        treeHouse.setAmbienceCount(ambienceCount);
                        treeHouse.setAmusementCount(amusementCount);
                        treeHouse.setEnvironmentCount(environmentCount);
                        TreeHouse treeHouse2 = treeHouseService.subByUserId(treeHouse);
                        if (treeHouse2 == null) {
                            throw new CheckException("人气，环境或着娱乐资源不足，无法升级！");
                        }

                        UserGoods userGoods = new UserGoods();
                        userGoods.setAmount(expendGold);
                        userGoods.setGoodsId(MarryConstant.MARRY_CONSUMABLES_COIN);
                        List<UserGoods> goodsList = new ArrayList<>();
                        goodsList.add(userGoods);
                        //扣除升级所需的金币
                        List<UserGoods> userGoodsList = userGoodsService.subUserGoods(userId, goodsList);
                        if (userGoodsList == null) {
                            throw new CheckException("金币不符合要求!");
                        }
                        JSONObject object = new JSONObject();
                        object.put("updateEndTime", String.valueOf(updateEndTime.getTime()));
                        object.put("userId", userId);
                        object.put("buildingType", String.valueOf(buildingType));
                        redisService.set(new StringBuilder("buildingUpdate").append(userId).append(Integer.valueOf(buildingType)).toString(),
                                object,
                                Long.valueOf(String.valueOf(3600 + updateTime)));
                        // 1 升级中 2 未升级
                        redisService.set(new StringBuilder("buildingUpdateStatus").append(userId).append(Integer.valueOf(buildingType)).toString(), 1,Long.valueOf(updateTime)*60);

                        return updateTime;
                    } else {
                        throw new CheckException("树屋等级或者金币不符合要求!");
                    }
                } else {
                    throw new CheckException("人气，或者环境值或者其他条件不足!");
                }
            } else {//如果升级建筑不是树屋,建筑等级必须不高于树屋等级，计算背包内的金币数是否大于升级所要消耗的金币数
                if (treeGrade >= grade + 1 && goldCount.getAmount() > expendGold) {
                    //建筑状态为开启
                    if (record.getBuildingOpen() == BuildingConstant.BUILDING_OPEN_TRUE) {
                        updateEndTime = new Date(System.currentTimeMillis() + updateTime * 1000 * 60);
                        record.setUpdateEndTime(updateEndTime);
                        buildingUserMapper.updateByPrimaryKey(record);

                        UserGoods userGoods = new UserGoods();
                        userGoods.setAmount(expendGold);
                        userGoods.setGoodsId(MarryConstant.MARRY_CONSUMABLES_COIN);
                        List<UserGoods> goodsList = new ArrayList<>();
                        goodsList.add(userGoods);
                        //扣款
                        List<UserGoods> userGoodsList = userGoodsService.subUserGoods(userId, goodsList);
                        if (userGoodsList == null) {
                            throw new CheckException("金币不符合要求!");
                        }

                        JSONObject object = new JSONObject();
                        object.put("updateEndTime", String.valueOf(updateEndTime.getTime()));
                        object.put("userId", userId);
                        object.put("buildingType", String.valueOf(buildingType));
                        redisService.set(new StringBuilder("buildingUpdate").append(userId).append(Integer.valueOf(buildingType)).toString(),
                                object,
                                Long.valueOf(String.valueOf(3600 + updateTime)));
                        // 1 升级中 2 未升级
                        redisService.set(new StringBuilder("buildingUpdateStatus").append(userId).append(Integer.valueOf(buildingType)).toString(), 1,Long.valueOf(updateTime)*60);
                        return updateTime;
                    } else {
                        throw new CheckException("建筑未开启!");
                    }
                } else {
                    throw new CheckException("树屋等级或者金币不符合要求!");
                }
            }
        } else {
            throw new Exception("建筑信息错误!");
        }
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer upgradeBuildingByUserId(String userId, int buildingType) throws Exception {
        BuildingUserExample example = new BuildingUserExample();
        example.createCriteria().andBuildingTypeEqualTo(buildingType).andUserIdEqualTo(userId);
        BuildingUser record = new BuildingUser();
        List<BuildingUser> list = buildingUserMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            record = list.get(0);
            Map<String, Object> map = commonSetting(record.getBuildingGrade() + 1, buildingType);
            commonUpdateBranch(record.getBuildingGrade() + 1, buildingType, userId);
            int outputRate = (int) map.get("outputRate");
            int outputMin = (int) map.get("outputMin");
            int outputMax = (int) map.get("outputMax");
            int expendGold = (int) map.get("expendGold");
            int updateTime = (int) map.get("updateTime");
            //修改建筑等级
            record.setBuildingGrade(record.getBuildingGrade() + 1);
            //升级所需消耗金币
            record.setBuildingUpgrade(expendGold);
            record.setBiuldingOutputMin(outputMin);
            record.setBuildingOutputMax(outputMax);
            record.setBuildingOutputRate(outputRate);
            record.setUpdateEndTime(new Date(System.currentTimeMillis() + updateTime * 1000));
            buildingUserMapper.updateByExample(record, example);

            updateBuildingProperty(userId, buildingType);

            if (buildingType == BuildingConstant.BUILDING_TYPE_HOUSE) {
                treeHouseService.unlockTreeHouseFlowerpot(userId, record.getBuildingGrade());
            }
        }
        return null;
    }

    @Override
    public ReceiveGoldVo receiveGoldByUserId(String userId, int buildingType) throws Exception {
        //领取建筑物金币方法的实现
        BuildingUserExample userExample = new BuildingUserExample();
        userExample.createCriteria().andUserIdEqualTo(userId).andBuildingTypeEqualTo(buildingType);
        List<BuildingUser> buildingUserList = buildingUserMapper.selectByExample(userExample);
        BuildingUser buildingUser = new BuildingUser();
        if (buildingUserList != null && buildingUserList.size() > 0) {
            buildingUser = buildingUserList.get(0);
        }
        int outputRate = buildingUser.getBuildingOutputRate();
        int outputMin = buildingUser.getBiuldingOutputMin();
        int outputMax = buildingUser.getBuildingOutputMax();
        Date createdDate = buildingUser.getCreatedTime();

        BuildingGoldLogExample goldLogExample = new BuildingGoldLogExample();
        goldLogExample.createCriteria().andUserIdEqualTo(userId).andBuildingTypeEqualTo(buildingType);
        goldLogExample.setOrderByClause(" created_time DESC ");
        List<BuildingGoldLog> goldLogList = buildingGoldLogMapper.selectByExample(goldLogExample);
        BuildingGoldLog buildingGoldLog = new BuildingGoldLog();
        Long nowTime = System.currentTimeMillis();
        /*		Date date = new Date(System.currentTimeMillis());*/
        int bewteen = 0;
        //当有领取记录，用当前系统时间减去上次领取时间计算可领取金币数
        if (goldLogList != null && goldLogList.size() > 0) {
            buildingGoldLog = goldLogList.get(0);
            Long createdTime = buildingGoldLog.getEndTime().getTime();
            createdDate = buildingGoldLog.getEndTime();

            if (nowTime - createdTime >= 3600000) {
                //产出的金币数量
                bewteen = (int) (3600) * outputRate / 60;
            } else {
                //产出的金币数量
                bewteen = (int) ((nowTime - createdTime) / 1000) * outputRate / 60;
            }

        } else {//当没有领取记录的时候通过建筑的初始化时间开始计算金币收益
            Long beginTime = buildingUser.getCreatedTime().getTime();

            if (nowTime - beginTime >= 3600000) {
                //产出的金币数量
                bewteen = (int) (3600) * outputRate / 60;
            } else {
                //产出的金币数量
                bewteen = (int) ((nowTime - beginTime) / 1000) * outputRate / 60;
            }
        }
        //当可领取金币小于最小可领取数,返回0
        if (bewteen < outputMin) {
            bewteen = 1;
        } else if (bewteen > outputMax) {
            bewteen = outputMax;
        }

        //生成金币领取记录，并且把金币数量增加到用户的账户上
        buildingGoldLog = new BuildingGoldLog();
        buildingGoldLog.setEndTime(new Date(System.currentTimeMillis()));
        buildingGoldLog.setBeginTime(createdDate);
        buildingGoldLog.setBuildingType(buildingType);
        buildingGoldLog.setOutputGold(bewteen);
        buildingGoldLog.setUserId(userId);
        buildingGoldLog.setCreatedTime(new Date(System.currentTimeMillis()));
        buildingGoldLogMapper.insert(buildingGoldLog);
        //增加金币到账户上
        UserGoods userGoods = new UserGoods();
        userGoods.setAmount(bewteen);
        userGoods.setGoodsId(MarryConstant.MARRY_CONSUMABLES_COIN);
        List<UserGoods> goodsList = new ArrayList<>();
        goodsList.add(userGoods);
        userGoodsService.addUserGoods(userId, goodsList);

        ReceiveGoldVo receiveGoldVo = new ReceiveGoldVo();
        receiveGoldVo.setAmount(bewteen);
        receiveGoldVo.setGoodsId(50001);
        receiveGoldVo.setId("50001");
        receiveGoldVo.setType(BuildingConstant.Prop);

        return receiveGoldVo;
    }

    /**
     * 公共方法，计算某个建筑当前可领取的金币数
     *
     * @param userId
     * @param buildingType
     * @return
     */
    public Integer countGoldByUserId(String userId, int buildingType) {
        BuildingUserExample userExample = new BuildingUserExample();
        userExample.createCriteria().andUserIdEqualTo(userId).andBuildingTypeEqualTo(buildingType);
        List<BuildingUser> buildingUserList = buildingUserMapper.selectByExample(userExample);
        BuildingUser buildingUser = new BuildingUser();
        if (buildingUserList != null && buildingUserList.size() > 0) {
            buildingUser = buildingUserList.get(0);
            if (buildingUser.getBuildingOpen() == BuildingConstant.BUILDING_OPEN_FALSE) {
                return 0;
            }
        }
        int outputRate = buildingUser.getBuildingOutputRate();
        int outputMin = buildingUser.getBiuldingOutputMin();
        int outputMax = buildingUser.getBuildingOutputMax();

        BuildingGoldLogExample goldLogExample = new BuildingGoldLogExample();
        goldLogExample.createCriteria().andUserIdEqualTo(userId).andBuildingTypeEqualTo(buildingType);
        goldLogExample.setOrderByClause(" created_time DESC ");
        List<BuildingGoldLog> goldLogList = buildingGoldLogMapper.selectByExample(goldLogExample);
        BuildingGoldLog buildingGoldLog = new BuildingGoldLog();
        Long nowTime = System.currentTimeMillis();
        int bewteen = 0;
        //TODO  当有领取记录，必须判断是否有过暂停记录
        if (goldLogList != null && goldLogList.size() > 0) {
            buildingGoldLog = goldLogList.get(0);
            Long createdTime = buildingGoldLog.getEndTime().getTime();

            //产出的金币数量
            bewteen = (int) ((nowTime - createdTime) / 1000) * outputRate / 60;
            //当没有领取记录的时候通过建筑的初始化时间开始计算金币收益
        } else {
            Long beginTime = buildingUser.getCreatedTime().getTime();
            bewteen = (int) (nowTime - beginTime) / 1000 * outputRate / 60;
        }
        //可领取金币数大于建筑最大金币数
        if (bewteen > outputMax) {
            bewteen = outputMax;
            //当不达到最低领取数量的时候返回为0
        } else if (bewteen < outputMin) {
            bewteen = 0;
        }

        return bewteen;
    }

    /**
     * 通用完成升阶支线任务调用
     *
     * @param grade
     * @param buildingType
     * @param userId
     */
    public void commonUpdateBranch(int grade, int buildingType, String userId) {
        //类型为树屋
        if (buildingType == BuildingConstant.BUILDING_TYPE_HOUSE) {
            if (grade % 10 == 0 && grade != 0) {
                int taskId = TreeHouselvlEnum.getTaskId(grade);
                friendService.taskBranchFinish(taskId, userId);
            }
            //类型为金库
        } else if (buildingType == BuildingConstant.BUILDING_TYPE_COFFERS) {
            if (grade % 10 == 0 && grade != 0) {
                int taskId = CoinlvlEnum.getTaskId(grade);
                friendService.taskBranchFinish(taskId, userId);
            }
            //类型为福利社
        } else if (buildingType == BuildingConstant.BUILDING_TYPE_WELFARE) {
            if (grade % 10 == 0 && grade != 0) {
                int taskId = WelfarelvlEnum.getTaskId(grade);
                friendService.taskBranchFinish(taskId, userId);
            }
            //类型为婚房
        } else if (buildingType == BuildingConstant.BUILDING_TYPE_MARRIAGE) {
            if (grade % 10 == 0 && grade != 0) {
                int taskId = MarriagelvlEnum.getTaskId(grade);
                friendService.taskBranchFinish(taskId, userId);
            }
            //类型为教堂
        } else if (buildingType == BuildingConstant.BUILDING_TYPE_CHURCH) {
            if (grade % 10 == 0 && grade != 0) {
                int taskId = ChurchlvlEnum.getTaskId(grade);
                friendService.taskBranchFinish(taskId, userId);
            }
            //类型为铁匠铺
        } else if (buildingType == BuildingConstant.BUILDING_TYPE_SMITHY) {
            if (grade % 10 == 0 && grade != 0) {
                int taskId = SmithylvlEnum.getTaskId(grade);
                friendService.taskBranchFinish(taskId, userId);
            }
            //类型为宠物店
        } else if (buildingType == BuildingConstant.BUILDING_TYPE_PETSHOP) {
            if (grade % 10 == 0 && grade != 0) {
                int taskId = PetshoplvlEnum.getTaskId(grade);
                friendService.taskBranchFinish(taskId, userId);
            }
            //类型为酒馆
        } else if (buildingType == BuildingConstant.BUILDING_TYPE_WINESHOP) {
            if (grade % 10 == 0 && grade != 0) {
                int taskId = WineshoplvlEnum.getTaskId(grade);
                friendService.taskBranchFinish(taskId, userId);
            }

        }
    }

    /**
     * 公共方法，获取某个等级下某个建筑的产出金币速率，可领取金币最小数，可领取金币最大数，升级所需消耗数
     *
     * @param grade
     * @param buildingType
     * @return
     */
    public Map<String, Object> commonSetting(int grade, int buildingType) {
        Map<String, Object> map = new HashMap<>(12);
        int outputRate = 0;
        //下一等级金币出产速率
        int nextOutputRate = 0;
        int outputMin = 0;
        int outputMax = 0;
        int expendGold = 0;
        int updateTime = 0;
        //容纳人数
        int containNum = 0;
        //下个容纳人数
        int nextContainNum = 0;
        //容纳物品数
        int containThingNum = 0;
        //下个容纳物品数
        int nextContainThingNum = 0;
        //最多金币数
        int goldMax = 0;
        //下一最多金币数
        int nextGoldMax = 0;
        //人气值
        long ambienceCount = 0;
        //娱乐值
        long amusementCount = 0;
        //环境值
        long environmentCount = 0;

        //类型为树屋
        if (buildingType == BuildingConstant.BUILDING_TYPE_HOUSE) {
            BuildingTreeSetting buildingTreeSetting = (BuildingTreeSetting) redisService.get("BuildingTreeSetting" + grade);
            if (buildingTreeSetting == null) {
                cacheBuildingSettingInfo();
                buildingTreeSetting = (BuildingTreeSetting) redisService.get("BuildingTreeSetting" + grade);
            }
            // 容纳人数
            containNum = buildingTreeSetting.getContainNum();
            //容纳物品数
            containThingNum = buildingTreeSetting.getContainThing();
            //升到下级所需满足人气值
            ambienceCount = buildingTreeSetting.getAmbienceCount();
            //升到下级所需满足娱乐值
            amusementCount = buildingTreeSetting.getAmusementCount();
            //升到下级所需满足环境值
            environmentCount = buildingTreeSetting.getEnvironmentCount();
            expendGold = buildingTreeSetting.getUpgradeGold();
            updateTime = buildingTreeSetting.getUpgradeTime();

            BuildingTreeSetting buildingTreeSetting1 = (BuildingTreeSetting) redisService.get("BuildingTreeSetting" + (grade + 1));
            // 下个容纳人数
            nextContainNum = buildingTreeSetting1.getContainNum();
            //下个容纳物品数
            nextContainThingNum = buildingTreeSetting1.getContainThing();
            //类型为金库
        } else if (buildingType == BuildingConstant.BUILDING_TYPE_COFFERS) {
            BuildingCoffersSetting buildingCoffersSetting = (BuildingCoffersSetting) redisService.get("BuildingCoffersSetting" + grade);
            if (buildingCoffersSetting == null) {
                cacheBuildingSettingInfo();
                buildingCoffersSetting = (BuildingCoffersSetting) redisService.get("BuildingCoffersSetting" + grade);
            }
            outputRate = buildingCoffersSetting.getOutputGoldRate();
            outputMin = buildingCoffersSetting.getOutputGoldMin();
            outputMax = buildingCoffersSetting.getOutputGoldMax();
            expendGold = buildingCoffersSetting.getUpgradeGold();
            updateTime = buildingCoffersSetting.getUpgradeTime();
            goldMax = buildingCoffersSetting.getGoldMax();

            BuildingCoffersSetting buildingCoffersSetting1 = (BuildingCoffersSetting) redisService.get("BuildingCoffersSetting" + (grade + 1));
            nextOutputRate = buildingCoffersSetting1.getOutputGoldRate();
            outputMin = buildingCoffersSetting1.getOutputGoldMin();
            outputMax = buildingCoffersSetting1.getOutputGoldMax();
            nextGoldMax = buildingCoffersSetting1.getGoldMax();
            //类型为福利社
        } else if (buildingType == BuildingConstant.BUILDING_TYPE_WELFARE) {
            BuildingWelfareSetting buildingWelfareSetting = (BuildingWelfareSetting) redisService.get("BuildingWelfareSetting" + grade);
            if (buildingWelfareSetting == null) {
                cacheBuildingSettingInfo();
                buildingWelfareSetting = (BuildingWelfareSetting) redisService.get("BuildingWelfareSetting" + grade);
            }
            outputRate = buildingWelfareSetting.getOutputGoldRate();
            outputMin = buildingWelfareSetting.getOutputGoldMin();
            outputMax = buildingWelfareSetting.getOutputGoldMax();
            expendGold = buildingWelfareSetting.getUpgradeGold();
            updateTime = buildingWelfareSetting.getUpgradeTime();

            BuildingWelfareSetting buildingWelfareSetting1 = (BuildingWelfareSetting) redisService.get("BuildingWelfareSetting" + (grade + 1));
            nextOutputRate = buildingWelfareSetting1.getOutputGoldRate();
            //类型为婚房
        } else if (buildingType == BuildingConstant.BUILDING_TYPE_MARRIAGE) {
            BuildingMarriageSetting buildingMarriageSetting = (BuildingMarriageSetting) redisService.get("BuildingMarriageSetting" + grade);
            if (buildingMarriageSetting == null) {
                cacheBuildingSettingInfo();
                buildingMarriageSetting = (BuildingMarriageSetting) redisService.get("BuildingMarriageSetting" + grade);
            }
            expendGold = buildingMarriageSetting.getUpgradeGold();
            updateTime = buildingMarriageSetting.getUpgradeTime();
            containThingNum = buildingMarriageSetting.getPlacedNum();

            BuildingMarriageSetting buildingMarriageSetting1 = (BuildingMarriageSetting) redisService.get("BuildingMarriageSetting" + (grade + 1));
            nextContainThingNum = buildingMarriageSetting1.getPlacedNum();
            //类型为教堂
        } else if (buildingType == BuildingConstant.BUILDING_TYPE_CHURCH) {
            BuildingChurchSetting buildingChurchSetting = (BuildingChurchSetting) redisService.get("BuildingChurchSetting" + grade);
            if (buildingChurchSetting == null) {
                cacheBuildingSettingInfo();
                buildingChurchSetting = (BuildingChurchSetting) redisService.get("BuildingChurchSetting" + grade);
            }
            outputRate = buildingChurchSetting.getOutputGoldRate();
            outputMin = buildingChurchSetting.getOutputGoldMin();
            outputMax = buildingChurchSetting.getOutputGoldMax();
            expendGold = buildingChurchSetting.getUpgradeGold();
            updateTime = buildingChurchSetting.getUpgradeTime();

            BuildingChurchSetting buildingChurchSetting1 = (BuildingChurchSetting) redisService.get("BuildingChurchSetting" + (grade + 1));
            nextOutputRate = buildingChurchSetting1.getOutputGoldRate();
            //类型为铁匠铺
        } else if (buildingType == BuildingConstant.BUILDING_TYPE_SMITHY) {
            BuildingSmithySetting buildingSmithySetting = (BuildingSmithySetting) redisService.get("BuildingSmithySetting" + grade);
            if (buildingSmithySetting == null) {
                cacheBuildingSettingInfo();
                buildingSmithySetting = (BuildingSmithySetting) redisService.get("BuildingSmithySetting" + grade);
            }
            outputRate = buildingSmithySetting.getOutputGoldRate();
            outputMin = buildingSmithySetting.getOutputGoldMin();
            outputMax = buildingSmithySetting.getOutputGoldMax();
            expendGold = buildingSmithySetting.getUpgradeGold();
            updateTime = buildingSmithySetting.getUpgradeTime();

            BuildingSmithySetting buildingSmithySetting1 = (BuildingSmithySetting) redisService.get("BuildingSmithySetting" + (grade + 1));
            nextOutputRate = buildingSmithySetting1.getOutputGoldRate();
            //类型为宠物店
        } else if (buildingType == BuildingConstant.BUILDING_TYPE_PETSHOP) {
            BuildingPetshopSetting buildingPetshopSetting = (BuildingPetshopSetting) redisService.get("BuildingPetshopSetting" + grade);
            if (buildingPetshopSetting == null) {
                cacheBuildingSettingInfo();
                buildingPetshopSetting = (BuildingPetshopSetting) redisService.get("BuildingPetshopSetting" + grade);
            }
            outputRate = buildingPetshopSetting.getOutputGoldRate();
            outputMin = buildingPetshopSetting.getOutputGoldMin();
            outputMax = buildingPetshopSetting.getOutputGoldMax();
            expendGold = buildingPetshopSetting.getUpgradeGold();
            updateTime = buildingPetshopSetting.getUpgradeTime();

            BuildingPetshopSetting buildingPetshopSetting1 = (BuildingPetshopSetting) redisService.get("BuildingPetshopSetting" + (grade + 1));
            nextOutputRate = buildingPetshopSetting1.getOutputGoldRate();
            //类型为酒馆
        } else if (buildingType == BuildingConstant.BUILDING_TYPE_WINESHOP) {
            BuildingWineshopSetting buildingWineshopSetting = (BuildingWineshopSetting) redisService.get("BuildingWineshopSetting" + grade);
            if (buildingWineshopSetting == null) {
                cacheBuildingSettingInfo();
                buildingWineshopSetting = (BuildingWineshopSetting) redisService.get("BuildingWineshopSetting" + grade);
            }
            outputRate = buildingWineshopSetting.getOutputGoldRate();
            outputMin = buildingWineshopSetting.getOutputGoldMin();
            outputMax = buildingWineshopSetting.getOutputGoldMax();
            expendGold = buildingWineshopSetting.getUpgradeGold();
            updateTime = buildingWineshopSetting.getUpgradeTime();

            BuildingWineshopSetting buildingWineshopSetting1 = (BuildingWineshopSetting) redisService.get("BuildingWineshopSetting" + (grade + 1));
            nextOutputRate = buildingWineshopSetting1.getOutputGoldRate();

            //类型为雕像
        } else if (buildingType == BuildingConstant.BUILDING_TYPE_STATUE) {
            BuildingStatueSetting buildingStatueSetting = (BuildingStatueSetting) redisService.get("BuildingStatueSetting" + grade);
            if (buildingStatueSetting == null) {
                cacheBuildingSettingInfo();
                buildingStatueSetting = (BuildingStatueSetting) redisService.get("BuildingStatueSetting" + grade);
            }
            expendGold = buildingStatueSetting.getUpgradeGold();
            updateTime = buildingStatueSetting.getUpgradeTime();
        }

        map.put("outputRate", outputRate);
        map.put("outputMin", outputMin);
        map.put("outputMax", outputMax);
        map.put("expendGold", expendGold);
        map.put("updateTime", updateTime);
        map.put("nextUpdateGrade", grade + 1);
        map.put("updateRoleGrade", grade);
        map.put("containNum", containNum);
        map.put("nextContainNum", nextContainNum);
        map.put("containThingNum", containThingNum);
        map.put("nextContainThingNum", nextContainThingNum);
        map.put("goldMax", goldMax);
        map.put("nextGoldMax", nextGoldMax);
        map.put("nextOutputRate", nextOutputRate);
        map.put("ambienceCount", ambienceCount);
        map.put("amusementCount", amusementCount);
        map.put("environmentCount", environmentCount);

        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer upgradeBuildingTask() throws NumberFormatException, Exception {
        Set<String> keys = redisService.likePattern("buildingUpdate" + "*");

        int i = 0;
        for (String key : keys) {
            if (key.contains("buildingUpdateStatus")) {
                continue;
            }
            Object object = redisService.get(key);
            JSONObject object2 = (JSONObject) JSON.parse(object.toString());
            String userId = object2.getString("userId");
            String updateEndTime = object2.getString("updateEndTime");
            String buildingType = object2.getString("buildingType");
            if (Long.valueOf(updateEndTime) < System.currentTimeMillis()) {
                this.upgradeBuildingByUserId(userId, Integer.valueOf(buildingType));
                redisService.remove(key);
                redisService.remove(new StringBuilder("buildingUpdateStatus").append(userId).append(Integer.valueOf(buildingType)).toString());
                i++;
            }
        }
        return i;
    }

    @Override
    public List<BuildingGoldVo> getGoldStatusByUserId(String userId) {
        BuildingGoldVo buildingGoldVo;
        List<BuildingGoldVo> list = new ArrayList<>();
        //需要查询的建筑的种类总数，目前雕像不使用，为8种
        for (int i = 1; i < 9; i++) {
            buildingGoldVo = new BuildingGoldVo();
            int gold = this.countGoldByUserId(userId, i);
            buildingGoldVo.setBuildingType(i);
            if (gold > 0) {
                buildingGoldVo.setGoldStatus(BuildingConstant.BUILDING_GOLD_TRUE);
            } else {
                buildingGoldVo.setGoldStatus(BuildingConstant.BUILDING_GOLD_FALSE);
            }
            list.add(buildingGoldVo);
        }
        return list;
    }

    @Override
    public void updateBuildingUser(String userId) {
        BuildingUserExample userExample = new BuildingUserExample();
        userExample.createCriteria().andUserIdEqualTo(userId).andBuildingTypeEqualTo(BuildingConstant.BUILDING_TYPE_CHURCH);
        List<BuildingUser> buildingUserList = buildingUserMapper.selectByExample(userExample);
        if (buildingUserList.size() > 0) {
            BuildingUser buildingUser = buildingUserList.get(0);
            buildingUser.setBuildingOpen(BuildingConstant.BUILDING_OPEN_TRUE);
            buildingUserMapper.updateByPrimaryKey(buildingUser);
        }
    }

    @Override
    public void openMarriageByUserId(String userId, Integer buildingOpen) {
        BuildingUserExample userExample = new BuildingUserExample();
        userExample.createCriteria().andUserIdEqualTo(userId).andBuildingTypeEqualTo(BuildingConstant.BUILDING_TYPE_MARRIAGE);
        List<BuildingUser> buildingUserList = buildingUserMapper.selectByExample(userExample);
        if (buildingUserList.size() > 0) {
            BuildingUser buildingUser = buildingUserList.get(0);
            buildingUser.setBuildingOpen(buildingOpen);
            buildingUserMapper.updateByPrimaryKey(buildingUser);
        }
    }

    @Override
    public int jugeStatusByUserId(String userId, int buildingType) {
        BuildingUserExample userExample = new BuildingUserExample();
        if (buildingType > 0 && buildingType < 10) {
            userExample.createCriteria().andUserIdEqualTo(userId).andBuildingTypeEqualTo(buildingType);
            List<BuildingUser> buildingUserList = buildingUserMapper.selectByExample(userExample);
            if (buildingUserList.size() > 0) {
                BuildingUser buildingUser = buildingUserList.get(0);
                int buildStatus = buildingUser.getBuildingOpen();
                return buildStatus;
            }
            return -1;
        } else {
            //类型错误
            return -1;
        }
    }

    /**
     * 缓存所有建筑升级配置表信息
     */
    @Override
    public void cacheBuildingSettingInfo() {
        BuildingTreeSettingExample buildingTreeSettingExample = new BuildingTreeSettingExample();
        List<BuildingTreeSetting> bildingTreeSettingList = buildingTreeSettingMapper.selectByExample(buildingTreeSettingExample);
        for (BuildingTreeSetting buildingTreeSetting : bildingTreeSettingList) {
            redisService.set("BuildingTreeSetting" + buildingTreeSetting.getGrade(), buildingTreeSetting);
        }
        BuildingCoffersSettingExample buildingCoffersSettingExample = new BuildingCoffersSettingExample();
        List<BuildingCoffersSetting> coffersSettingList = buildingCoffersSettingMapper.selectByExample(buildingCoffersSettingExample);
        for (BuildingCoffersSetting buildingCoffersSetting : coffersSettingList) {
            redisService.set("BuildingCoffersSetting" + buildingCoffersSetting.getGrade(), buildingCoffersSetting);
        }
        BuildingWelfareSettingExample buildingWelfareSettingExample = new BuildingWelfareSettingExample();
        List<BuildingWelfareSetting> welfareSettingList = buildingWelfareSettingMapper.selectByExample(buildingWelfareSettingExample);
        for (BuildingWelfareSetting buildingWelfareSetting : welfareSettingList) {
            redisService.set("BuildingWelfareSetting" + buildingWelfareSetting.getGrade(), buildingWelfareSetting);
        }
        BuildingMarriageSettingExample buildingMarriageSettingExample = new BuildingMarriageSettingExample();
        List<BuildingMarriageSetting> marriageSettingList = buildingMarriageSettingMapper.selectByExample(buildingMarriageSettingExample);
        for (BuildingMarriageSetting buildingMarriageSetting : marriageSettingList) {
            redisService.set("BuildingMarriageSetting" + buildingMarriageSetting.getGrade(), buildingMarriageSetting);
        }
        BuildingChurchSettingExample buildingChurchSettingExample = new BuildingChurchSettingExample();
        List<BuildingChurchSetting> churchSettingList = buildingChurchSettingMapper.selectByExample(buildingChurchSettingExample);
        for (BuildingChurchSetting buildingChurchSetting : churchSettingList) {
            redisService.set("BuildingChurchSetting" + buildingChurchSetting.getGrade(), buildingChurchSetting);
        }
        BuildingSmithySettingExample buildingSmithySettingExample = new BuildingSmithySettingExample();
        List<BuildingSmithySetting> smithySettingList = buildingSmithySettingMapper.selectByExample(buildingSmithySettingExample);
        for (BuildingSmithySetting buildingSmithySetting : smithySettingList) {
            redisService.set("BuildingSmithySetting" + buildingSmithySetting.getGrade(), buildingSmithySetting);
        }
        BuildingPetshopSettingExample buildingPetshopSettingExample = new BuildingPetshopSettingExample();
        List<BuildingPetshopSetting> petshopSettingList = buildingPetshopSettingMapper.selectByExample(buildingPetshopSettingExample);
        for (BuildingPetshopSetting buildingPetshopSetting : petshopSettingList) {
            redisService.set("BuildingPetshopSetting" + buildingPetshopSetting.getGrade(), buildingPetshopSetting);
        }
        BuildingWineshopSettingExample buildingWineshopSettingExample = new BuildingWineshopSettingExample();
        List<BuildingWineshopSetting> wineshopSettingList = buildingWineshopSettingMapper.selectByExample(buildingWineshopSettingExample);
        for (BuildingWineshopSetting buildingWineshopSetting : wineshopSettingList) {
            redisService.set("BuildingWineshopSetting" + buildingWineshopSetting.getGrade(), buildingWineshopSetting);
        }
        BuildingStatueSettingExample buildingStatueSettingExample = new BuildingStatueSettingExample();
        List<BuildingStatueSetting> statueSettingList = buildingStatueSettingMapper.selectByExample(buildingStatueSettingExample);
        for (BuildingStatueSetting buildingStatueSetting : statueSettingList) {
            redisService.set("BuildingStatueSetting" + buildingStatueSetting.getGrade(), buildingStatueSetting);
        }
    }

    @Override
    public void addBuildingReduceLog(String userId) {
        BuildingCoinReduceLog buildingCoinReduceLog = new BuildingCoinReduceLog();
        buildingCoinReduceLog.setUserId(userId);
        buildingCoinReduceLog.setCreatedTime(new Date(System.currentTimeMillis()));
        buildingCoinReduceLogMapper.insert(buildingCoinReduceLog);
    }

    @Override
    public void judgeBuildingByUserId(String userId) {
        BuildingUserExample example = new BuildingUserExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<BuildingUser> list = buildingUserMapper.selectByExample(example);
        UserGoods goldCount = userGoodsService.getGoodsCount(userId, "50001");
        UserRole userRole = userRoleService.getUserRoleByUserId(userId);
        if (userRole != null) {
            int roleLevel = userRole.getRoleLevel();
            //需要添加每个建筑的可领取金币数
            if (list != null && list.size() > 0) {
                for (BuildingUser buildingUser : list) {
                    int buildingType = buildingUser.getBuildingType();
                    int grade = buildingUser.getBuildingGrade();
                    Map<String, Object> map = commonSetting(grade, buildingType);
                    int expendGold = (int) map.get("expendGold");
                    //人气值
                    long ambienceCount = (long) map.get("ambienceCount");
                    //娱乐值
                    long amusementCount = (long) map.get("amusementCount");
                    //环境值
                    long environmentCount = (long) map.get("environmentCount");
                    //建筑升级状态,默认为升级完成
                    TreeHouse treeHouse = treeHouseService.findByUserId(userId);
                    int treeGrade = treeHouse.getLevel();
                    if (buildingUser.getUpdateEndTime() != null && buildingUser.getUpdateEndTime().getTime() > (System.currentTimeMillis())) {
                    } else {
                        //建筑等级不能大与树屋等级，树屋等级不能大于角色等级,判断建筑是否满足升级条件，升级所需消耗品是否足够，等级是否已经满足
                        if (buildingType == BuildingConstant.BUILDING_TYPE_HOUSE) {
                            //树屋等级不高于角色等级，且背包内金币数量大于升级所需消耗数量
                            if (treeHouse.getAmbienceCount() >= ambienceCount && treeHouse.getAmusementCount() >= amusementCount && treeHouse.getEnvironmentCount() >= environmentCount) {
                                if (treeGrade + 1 <= roleLevel && goldCount.getAmount() > expendGold) {
                                    List jsonList = new ArrayList();
                                    String model = BuildingConstant.BUILDING_UPDATE_READY_STRING + ":1";
                                    jsonList.add(model);
                                    JSONObject json = new JSONObject();
                                    json.put("models", jsonList);
                                    json.put("type", BuildingConstant.RED_TIP);
                                    json.put("code", 0);
                                    messageService.sendMessageToSomeBody(userId, json.toString());
                                    break;
                                }
                            }
                        } else {
                            //建筑等级不高于树屋等级，且背包内金币数量大于升级所需消耗数量
                            if (treeHouse.getAmbienceCount() >= ambienceCount && treeHouse.getAmusementCount() >= amusementCount && treeHouse.getEnvironmentCount() >= environmentCount) {
                                if (treeGrade >= grade + 1 && goldCount.getAmount() > expendGold) {
                                    if (buildingUser.getBuildingOpen() == 0) {
                                        //计算背包内的金币数是否大于升级所要消耗的金币数
                                        List jsonList = new ArrayList();
                                        String model = BuildingConstant.BUILDING_UPDATE_READY_STRING + ":1";
                                        jsonList.add(model);
                                        JSONObject json = new JSONObject();
                                        json.put("models", jsonList);
                                        json.put("type", BuildingConstant.RED_TIP);
                                        json.put("code", 0);
                                        messageService.sendMessageToSomeBody(userId, json.toString());
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean addBuildingCoffersSetting(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<BuildingCoffersSetting> buildingCoffersSettings = new ArrayList<>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new CheckException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if (sheet != null) {
            notNull = true;
        }
        BuildingCoffersSetting buildingCoffersSetting;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            buildingCoffersSetting = new BuildingCoffersSetting();
            Integer grade = new Double(row.getCell(0).getNumericCellValue()).intValue();
            Integer outputGoldRate = new Double(row.getCell(1).getNumericCellValue()).intValue();
            Integer outputGoldMin = new Double(row.getCell(2).getNumericCellValue()).intValue();
            Integer outputGoldMax = new Double(row.getCell(3).getNumericCellValue()).intValue();
            Integer upgradeGold = new Double(row.getCell(4).getNumericCellValue()).intValue();
            Integer upgradeTime = new Double(row.getCell(5).getNumericCellValue()).intValue();
            Integer goldMax = new Double(row.getCell(6).getNumericCellValue()).intValue();
            buildingCoffersSetting.setGrade(grade);
            buildingCoffersSetting.setOutputGoldRate(outputGoldRate);
            buildingCoffersSetting.setOutputGoldMin(outputGoldMin);
            buildingCoffersSetting.setOutputGoldMax(outputGoldMax);
            buildingCoffersSetting.setUpgradeGold(upgradeGold);
            buildingCoffersSetting.setUpgradeTime(upgradeTime);
            buildingCoffersSetting.setGoldMax(goldMax);
            buildingCoffersSettings.add(buildingCoffersSetting);
        }
        for (BuildingCoffersSetting conffers : buildingCoffersSettings) {
            BuildingCoffersSetting conffersByKey = buildingCoffersSettingMapper.selectByPrimaryKey(conffers.getGrade());
            if (conffersByKey == null) {
                buildingCoffersSettingMapper.insert(conffers);
            } else {
                buildingCoffersSettingMapper.updateByPrimaryKey(conffers);
            }
        }
        return notNull;
    }

    @Override
    public boolean addBuildingWelfareSetting(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<BuildingWelfareSetting> buildingWelfareSettings = new ArrayList<>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new CheckException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if (sheet != null) {
            notNull = true;
        }
        BuildingWelfareSetting buildingWelfareSetting;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            buildingWelfareSetting = new BuildingWelfareSetting();
            Integer grade = new Double(row.getCell(0).getNumericCellValue()).intValue();
            Integer upgradeGold = new Double(row.getCell(1).getNumericCellValue()).intValue();
            Integer upgradeTime = new Double(row.getCell(2).getNumericCellValue()).intValue();
            Integer outputGoldRate = new Double(row.getCell(3).getNumericCellValue()).intValue();
            Integer outputGoldMin = new Double(row.getCell(4).getNumericCellValue()).intValue();
            Integer outputGoldMax = new Double(row.getCell(5).getNumericCellValue()).intValue();
            buildingWelfareSetting.setGrade(grade);
            buildingWelfareSetting.setUpgradeGold(upgradeGold);
            buildingWelfareSetting.setUpgradeTime(upgradeTime);
            buildingWelfareSetting.setOutputGoldRate(outputGoldRate);
            buildingWelfareSetting.setOutputGoldMin(outputGoldMin);
            buildingWelfareSetting.setOutputGoldMax(outputGoldMax);
            buildingWelfareSettings.add(buildingWelfareSetting);
        }
        for (BuildingWelfareSetting welfareSetting : buildingWelfareSettings) {
            BuildingWelfareSetting welfareSettingByKey = buildingWelfareSettingMapper.selectByPrimaryKey(welfareSetting.getGrade());
            if (welfareSettingByKey == null) {
                buildingWelfareSettingMapper.insert(welfareSetting);
            } else {
                buildingWelfareSettingMapper.updateByPrimaryKey(welfareSetting);
            }

        }
        return notNull;
    }

    @Override
    public boolean addBuildingChurchSetting(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<BuildingChurchSetting> buildingChurchSettings = new ArrayList<>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new CheckException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if (sheet != null) {
            notNull = true;
        }
        BuildingChurchSetting buildingChurchSetting;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            buildingChurchSetting = new BuildingChurchSetting();
            Integer grade = new Double(row.getCell(0).getNumericCellValue()).intValue();
            Integer upgradeGold = new Double(row.getCell(1).getNumericCellValue()).intValue();
            Integer upgradeTime = new Double(row.getCell(2).getNumericCellValue()).intValue();
            Integer outputGoldRate = new Double(row.getCell(3).getNumericCellValue()).intValue();
            Integer outputGoldMin = new Double(row.getCell(4).getNumericCellValue()).intValue();
            Integer outputGoldMax = new Double(row.getCell(5).getNumericCellValue()).intValue();
            buildingChurchSetting.setGrade(grade);
            buildingChurchSetting.setUpgradeGold(upgradeGold);
            buildingChurchSetting.setUpgradeTime(upgradeTime);
            buildingChurchSetting.setOutputGoldRate(outputGoldRate);
            buildingChurchSetting.setOutputGoldMin(outputGoldMin);
            buildingChurchSetting.setOutputGoldMax(outputGoldMax);
            buildingChurchSettings.add(buildingChurchSetting);
        }
        for (BuildingChurchSetting churchSetting : buildingChurchSettings) {
            BuildingChurchSetting churchSettingByKey = buildingChurchSettingMapper.selectByPrimaryKey(churchSetting.getGrade());
            if (churchSettingByKey == null) {
                buildingChurchSettingMapper.insert(churchSetting);
            } else {
                buildingChurchSettingMapper.updateByPrimaryKey(churchSetting);
            }

        }
        return notNull;
    }

    @Override
    public boolean addBuildingWineshopSetting(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<BuildingWineshopSetting> buildingWineshopSettings = new ArrayList<>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new CheckException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if (sheet != null) {
            notNull = true;
        }
        BuildingWineshopSetting buildingWineshopSetting;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            buildingWineshopSetting = new BuildingWineshopSetting();
            Integer grade = new Double(row.getCell(0).getNumericCellValue()).intValue();
            Integer upgradeGold = new Double(row.getCell(1).getNumericCellValue()).intValue();
            Integer upgradeTime = new Double(row.getCell(2).getNumericCellValue()).intValue();
            Integer outputGoldRate = new Double(row.getCell(3).getNumericCellValue()).intValue();
            Integer outputGoldMin = new Double(row.getCell(4).getNumericCellValue()).intValue();
            Integer outputGoldMax = new Double(row.getCell(5).getNumericCellValue()).intValue();
            buildingWineshopSetting.setGrade(grade);
            buildingWineshopSetting.setUpgradeGold(upgradeGold);
            buildingWineshopSetting.setUpgradeTime(upgradeTime);
            buildingWineshopSetting.setOutputGoldRate(outputGoldRate);
            buildingWineshopSetting.setOutputGoldMin(outputGoldMin);
            buildingWineshopSetting.setOutputGoldMax(outputGoldMax);
            buildingWineshopSettings.add(buildingWineshopSetting);
        }
        for (BuildingWineshopSetting wineshopSetting : buildingWineshopSettings) {
            BuildingWineshopSetting wineshopSettingByKey = buildingWineshopSettingMapper.selectByPrimaryKey(wineshopSetting.getGrade());
            if (wineshopSettingByKey == null) {
                buildingWineshopSettingMapper.insert(wineshopSetting);
            } else {
                buildingWineshopSettingMapper.updateByPrimaryKey(wineshopSetting);
            }

        }
        return notNull;
    }

    @Override
    public boolean addBuildingSmithySetting(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<BuildingSmithySetting> buildingSmithySettings = new ArrayList<>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new CheckException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if (sheet != null) {
            notNull = true;
        }
        BuildingSmithySetting buildingSmithySetting;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            buildingSmithySetting = new BuildingSmithySetting();
            Integer grade = new Double(row.getCell(0).getNumericCellValue()).intValue();
            Integer upgradeGold = new Double(row.getCell(1).getNumericCellValue()).intValue();
            Integer upgradeTime = new Double(row.getCell(2).getNumericCellValue()).intValue();
            Integer outputGoldRate = new Double(row.getCell(3).getNumericCellValue()).intValue();
            Integer outputGoldMin = new Double(row.getCell(4).getNumericCellValue()).intValue();
            Integer outputGoldMax = new Double(row.getCell(5).getNumericCellValue()).intValue();
            buildingSmithySetting.setGrade(grade);
            buildingSmithySetting.setUpgradeGold(upgradeGold);
            buildingSmithySetting.setUpgradeTime(upgradeTime);
            buildingSmithySetting.setOutputGoldRate(outputGoldRate);
            buildingSmithySetting.setOutputGoldMin(outputGoldMin);
            buildingSmithySetting.setOutputGoldMax(outputGoldMax);
            buildingSmithySettings.add(buildingSmithySetting);
        }
        for (BuildingSmithySetting smithySetting : buildingSmithySettings) {
            BuildingSmithySetting smithySettingByKey = buildingSmithySettingMapper.selectByPrimaryKey(smithySetting.getGrade());
            if (smithySettingByKey == null) {
                buildingSmithySettingMapper.insert(smithySetting);
            } else {
                buildingSmithySettingMapper.updateByPrimaryKey(smithySetting);
            }

        }
        return notNull;
    }

    @Override
    public boolean addBuildingStatueSetting(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<BuildingStatueSetting> buildingStatueSettings = new ArrayList<>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new CheckException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if (sheet != null) {
            notNull = true;
        }
        BuildingStatueSetting buildingStatueSetting;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            buildingStatueSetting = new BuildingStatueSetting();
            Integer grade = new Double(row.getCell(0).getNumericCellValue()).intValue();
            Integer upgradeGold = new Double(row.getCell(1).getNumericCellValue()).intValue();
            Integer upgradeTime = new Double(row.getCell(2).getNumericCellValue()).intValue();
            buildingStatueSetting.setGrade(grade);
            buildingStatueSetting.setUpgradeGold(upgradeGold);
            buildingStatueSetting.setUpgradeTime(upgradeTime);

            buildingStatueSettings.add(buildingStatueSetting);
        }
        for (BuildingStatueSetting statueSetting : buildingStatueSettings) {
            BuildingStatueSetting statueSettingByKey = buildingStatueSettingMapper.selectByPrimaryKey(statueSetting.getGrade());
            if (statueSettingByKey == null) {
                buildingStatueSettingMapper.insert(statueSetting);
            } else {
                buildingStatueSettingMapper.updateByPrimaryKey(statueSetting);
            }

        }
        return notNull;
    }

    @Override
    public boolean addBuildingPetshopSetting(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<BuildingPetshopSetting> buildingPetshopSettings = new ArrayList<>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new CheckException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if (sheet != null) {
            notNull = true;
        }
        BuildingPetshopSetting buildingPetshopSetting;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            buildingPetshopSetting = new BuildingPetshopSetting();
            Integer grade = new Double(row.getCell(0).getNumericCellValue()).intValue();
            Integer upgradeGold = new Double(row.getCell(1).getNumericCellValue()).intValue();
            Integer upgradeTime = new Double(row.getCell(2).getNumericCellValue()).intValue();
            Integer outputGoldRate = new Double(row.getCell(3).getNumericCellValue()).intValue();
            Integer outputGoldMin = new Double(row.getCell(4).getNumericCellValue()).intValue();
            Integer outputGoldMax = new Double(row.getCell(5).getNumericCellValue()).intValue();
            buildingPetshopSetting.setGrade(grade);
            buildingPetshopSetting.setUpgradeGold(upgradeGold);
            buildingPetshopSetting.setUpgradeTime(upgradeTime);
            buildingPetshopSetting.setOutputGoldRate(outputGoldRate);
            buildingPetshopSetting.setOutputGoldMin(outputGoldMin);
            buildingPetshopSetting.setOutputGoldMax(outputGoldMax);

            buildingPetshopSettings.add(buildingPetshopSetting);
        }
        for (BuildingPetshopSetting petshopSetting : buildingPetshopSettings) {
            BuildingPetshopSetting petshopSettingByKey = buildingPetshopSettingMapper.selectByPrimaryKey(petshopSetting.getGrade());
            if (petshopSettingByKey == null) {
                buildingPetshopSettingMapper.insert(petshopSetting);
            } else {
                buildingPetshopSettingMapper.updateByPrimaryKey(petshopSetting);
            }

        }
        return notNull;
    }


    /*
     * vitality 生命属性加成
     * attack 物理攻击属性加成
     * spellAttacks 法术攻击属性加成
     * pdef 物理防御属性加成
     * magdef 法术防御属性加成
     * crit 暴击属性加成
     * dodge 闪避属性加成
     * hitRate 命中属性加成
     * defenseCrit 抗暴击属性加成
     */
    public void updateBuildingProperty(String userId, int buildingType) {
        if (buildingType == BuildingConstant.BUILDING_TYPE_HOUSE) {
            //vitality 生命属性加成:1
            userGoodsService.addUserAdornEquipAttribute(userId, BuildingConstant.BUILDING_TYPE_HOUSE_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM);
        } else if (buildingType == BuildingConstant.BUILDING_TYPE_COFFERS) {
            //pdef 物理防御属性加成:4
            userGoodsService.addUserAdornEquipAttribute(userId, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_COFFERS_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM);
        } else if (buildingType == BuildingConstant.BUILDING_TYPE_WELFARE) {
            //magdef 法术防御属性加成:5
            userGoodsService.addUserAdornEquipAttribute(userId, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_WELFARE_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM);
        } else if (buildingType == BuildingConstant.BUILDING_TYPE_MARRIAGE) {
            //attack 物理,法术攻击属性加成:2,3
            userGoodsService.addUserAdornEquipAttribute(userId, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_MARRIAGE_NUM, BuildingConstant.BUILDING_TYPE_MARRIAGE_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM);
        } else if (buildingType == BuildingConstant.BUILDING_TYPE_CHURCH) {
            //crit 暴击属性加成:6
            userGoodsService.addUserAdornEquipAttribute(userId, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_CHURCH_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM);

        } else if (buildingType == BuildingConstant.BUILDING_TYPE_SMITHY) {
            //dodge 闪避属性加成:7
            userGoodsService.addUserAdornEquipAttribute(userId, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_CHURCH_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM);
        } else if (buildingType == BuildingConstant.BUILDING_TYPE_PETSHOP) {
            //defenseCrit 抗暴击属性加成 :9
            userGoodsService.addUserAdornEquipAttribute(userId, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_PETSHOP_NUM);
        } else if (buildingType == BuildingConstant.BUILDING_TYPE_WINESHOP) {
            //hitRate 命中属性加成:8
            userGoodsService.addUserAdornEquipAttribute(userId, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_ZERO_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM,
                    BuildingConstant.BUILDING_TYPE_MARRIAGE_NUM, BuildingConstant.BUILDING_TYPE_ZERO_NUM);
        }
    }


}
