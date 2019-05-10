package com.tongzhu.fishing.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.common.Pager;
import com.tongzhu.fishing.constant.ConstantValue;
import com.tongzhu.fishing.constant.FishingConstant;
import com.tongzhu.constant.GoodsConstant;
import com.tongzhu.fishing.domain.PropInfo;
import com.tongzhu.fishing.domain.TreeHouse;
import com.tongzhu.fishing.domain.UserGoods;
import com.tongzhu.fishing.enums.FishingMultiplyEnum;
import com.tongzhu.fishing.enums.StatusEnum;
import com.tongzhu.fishing.mapper.vo.FishEntrepotVO;
import com.tongzhu.fishing.model.*;
import com.tongzhu.fishing.po.FishingPO;
import com.tongzhu.fishing.redis.RedisService;
import com.tongzhu.fishing.service.*;
import com.tongzhu.fishing.util.*;
import com.tongzhu.util.DateUtil;
import com.tongzhu.util.ObjectUtil;
import com.tongzhu.util.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 钓鱼相关Controller
 *
 * @author 海乐乐
 * @date 2018年8月30日
 */
@Api(value = "钓鱼controller", tags = {"钓鱼API"})
@RestController
@RequestMapping(value = "/fishing")
public class FishingController {

    private static Logger log = LoggerFactory.getLogger(FishingController.class);

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserGoodsService userGoodsService;


    @Autowired
    private TreeHouseService treeHouseService;

    @Autowired
    private FishingInfoService fishingInfoService;

    @Autowired
    private FishingSecretOperationService fishingSecretOperationService;

    @Autowired
    private UserActivityService userActivityService;

    @Autowired
    private UserActivityPropsService userActivityPropsService;

    @Autowired
    private UserActivityPropsGoodsService userActivityPropsGoodsService;

    @Autowired
    private FishInfoService fishInfoService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private FishEntrepotService fishEntrepotService;

    @Autowired
    private PropService propService;

    /**
     * 进入钓鱼界面
     *
     * @param fishingPO -- userId 用户ID
     * @return
     */
    @ApiOperation(value = "进入钓鱼界面，获取用户初始化值", notes = "请求参数说明 [userId 用户ID]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明data[multiple 用户钓鱼默认倍数] [validMultiply 用户可选择倍数] [date 该玩家是否处于渔汛阶段 -1不在渔汛时段 其他返回渔汛剩余时间]" +
                    "[timeSlot 1 上午 0 下午 ] [quality 渔场品质 1 普通渔场 2 高级渔场 3 稀有渔场]")
    })
    @PostMapping(value = "initFishing", produces = "application/json")
    public BaseReturn initFishing(@RequestBody FishingPO fishingPO) {
        String userId = fishingPO.getUserId();
        if (StringUtils.isBlank(userId)) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        int result = FishingUtil.isFishFlood();  // 渔汛剩余时间
        //判断该用户是否已经刷新过鱼塘
        Long invalid = redisService.getInvalid(ConstantValue.REDIS_FISHING_QUALITY + userId);  // 鱼塘过期时间
        Integer qualityRedis = (Integer) redisService.get(ConstantValue.REDIS_FISHING_QUALITY + userId);
        if (result > 0) {//判断是否在鱼汛时间
            Integer quality = FishingConstant.getSingleton().getQuality();

            if (invalid < result) {
                invalid = new Long(result);
            }
            if (qualityRedis != null) {
                if (quality >= qualityRedis) {
                    qualityRedis = quality;
                    //将鱼塘信息存入缓存,时间为鱼汛剩余时间
                }
            } else {
                qualityRedis = quality;
            }
            redisService.set(ConstantValue.REDIS_FISHING_QUALITY + userId, qualityRedis, new Long(invalid));
        }

        TreeHouse treeHouse = treeHouseService.findByUserId(userId);  // 获取用户树屋详情
        if (treeHouse == null || treeHouse.getLevel() == null) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "树屋信息有误");
        }
        int multiple = FishingMultiplyEnum.getValidMultiply(treeHouse.getLevel());

        FishingInfo fishingInfo = fishingInfoService.getFishingInfo(userId);
        if (fishingInfo == null) { // 初始化钓鱼数据
            fishingInfo = new FishingInfo();
            fishingInfo.setMultiple(1);
            fishingInfo.setNumberFishingSum(0);
            fishingInfo.setNumberFishingToday(0);
            fishingInfo.setUpdateTime(new Date());
            fishingInfo.setUserId(userId);
            fishingInfoService.addFishingInfo(fishingInfo);
        }


        Map<String, Object> map = new HashMap<String, Object>();
        map.put("multiple", multiple);//倍率   // 存数据库
        map.put("timeSlot", FishingUtil.getDateInterval());//当前时间上午还是下午
        map.put("date", invalid);//时间
        map.put("quality", qualityRedis);//渔场品质
        return new BaseReturn("操作成功!", map);

    }


    /**
     * @return
     * @Description 钓鱼
     * @Param fishingPO
     * -- userId 用户ID
     * -- multiple 请求倍率
     */
    @ApiOperation(value = "钓鱼", notes = "请求参数说明 [userId 用户ID  multiple 钓鱼次数]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明data [success true] [code 0][msg  操作成功 ] " +
                    "[activityPrizes 活动获取到碎片 id 主键id  gamePropsName 碎步名称 introduce 碎步描述 " +
                    "gamePropsPicture 碎步图片地址] [voucher  精力剩余 amount 剩余数量  goodsId 精力物品id]" +
                    "[ fishGoods  钓鱼的记录 goodsId 鱼类的id rewardId 对应奖品id rewardAmount 奖品数量]")
    })
    @PostMapping(value = "goFishing", produces = "application/json")
    public BaseReturn goFishing(@RequestBody FishingPO fishingPO) {
        String userId = fishingPO.getUserId();
        Integer multiple = fishingPO.getMultiple();
        if (multiple == null || multiple == 0) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (StringUtils.isBlank(userId)) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        //判断用户当前的精力够不够
        UserGoods goodsCount = userGoodsService.getGoodsCount(userId, GoodsConstant.GOODS_VIGOUR);
        if (goodsCount == null || goodsCount.getAmount() < multiple) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "精力值不足");
        }
        Integer quality = (Integer) redisService.get(ConstantValue.REDIS_FISHING_QUALITY + userId);
        // 进行暗箱操作
        FishingInfo fishingInfo = fishingInfoService.getFishingInfo(userId);
        Integer numberFishingToday = fishingInfo.getNumberFishingToday();
        fishingInfo.setNumberFishingSum(fishingInfo.getNumberFishingSum() + multiple);
        fishingInfo.setMultiple(multiple);
        if (!DateUtil.sameDate(fishingInfo.getUpdateTime(), new Date())) {
            numberFishingToday = 0;
            fishingInfo.setNumberFishingToday(multiple);
        } else {
            fishingInfo.setNumberFishingToday(fishingInfo.getNumberFishingToday() + multiple);
        }
        fishingInfo.setUpdateTime(new Date());
        int i = fishingInfoService.renewalFishingInfo(fishingInfo);
        if (i <= 0) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "钓鱼数据更新错误");
        }
        List<FishingSecretOperation> fishingSecretOperationsList =
                fishingSecretOperationService.getSecretPrize(numberFishingToday, numberFishingToday + multiple);
        Map<List<Integer>, Integer> fishPrizes;
        List<UserGoods> result = new ArrayList<>();
        if (fishingSecretOperationsList.size() > 0) {
            List<FishInfo> fishInfoList = new ArrayList<>();
            for (FishingSecretOperation fishingSecretOperation : fishingSecretOperationsList) {
                UserGoods entity;
                if (quality == FishingConstant.FISHING_GROUND_QUALITY_SENIOR) {  // 高级渔场
                    for (int i1 = 0; i1 < fishingSecretOperation.getSeniorTotal(); i1++) {
                        FishInfo fishInfo = fishInfoService.getFishInfoById(Integer.parseInt(fishingSecretOperation.getSeniorFishAwards()));
                        fishInfoList.add(fishInfo);
                    }
                } else if (quality == FishingConstant.FISHING_GROUND_QUALITY_RARE) { // 稀有渔场
                    for (int i1 = 0; i1 < fishingSecretOperation.getRareTotal(); i1++) {
                        FishInfo fishInfo = fishInfoService.getFishInfoById(Integer.parseInt(fishingSecretOperation.getRareFishAwards()));
                        fishInfoList.add(fishInfo);
                    }

                } else {
                    for (int i1 = 0; i1 < fishingSecretOperation.getCommonTotal(); i1++) {
                        FishInfo fishInfo = fishInfoService.getFishInfoById(Integer.parseInt(fishingSecretOperation.getCommonFishAwards()));
                        fishInfoList.add(fishInfo);
                    }
                }

                for (FishInfo fishInfo : fishInfoList) {
                    fishPrizes = redisService.getFishing(ConstantValue.FISH_PRIZES + fishInfo.getId(), new Integer(0));
                    Integer fishPrizesWeight = (Integer) redisService.get(ConstantValue.FISH_PRIZES_WEIGHT + fishInfo.getId());
                    if (fishPrizes == null) {
                        String rewardProbability = fishInfo.getRewardProbability();
                        JSONObject jsonObject = JSON.parseObject(rewardProbability);
                        int record = 0;
                        fishPrizes = new HashMap<>();
                        for (Object key : jsonObject.keySet()) {
                            fishPrizes.put(FisheryUtil.getProbabilityList(record, record + (Integer) jsonObject.get(key)), (Integer) key);
                            record += (Integer) jsonObject.get(key);
                        }
                        redisService.setFishing(ConstantValue.FISH_PRIZES + fishInfo.getId(), fishPrizes);
                        fishPrizes = redisService.getFishing(ConstantValue.FISH_PRIZES + fishInfo.getId(), new Integer(0));
                    }
                    if (fishPrizesWeight == null) {
                        String rewardProbability = fishInfo.getRewardProbability();
                        JSONObject jsonObject = JSON.parseObject(rewardProbability);
                        fishPrizesWeight = 0;
                        for (Object key : jsonObject.keySet()) {
                            fishPrizesWeight += (Integer) jsonObject.get(key);
                        }
                        redisService.set(ConstantValue.FISH_PRIZES_WEIGHT + fishInfo.getId(), fishPrizesWeight);
                        fishPrizesWeight = (Integer) redisService.get(ConstantValue.FISH_PRIZES_WEIGHT + fishInfo.getId());
                    }
                    Integer prizeId = FisheryUtil.getActivityProperty(fishPrizes, fishPrizesWeight);  // 鱼对应奖品id
                    String reward = fishInfo.getReward();
                    JSONObject jsonObject = JSON.parseObject(reward);
                    entity = new UserGoods();
                    entity.setAmount(1);
                    entity.setGoodsId(fishInfo.getId());
                    entity.setRewardId(prizeId);
                    entity.setRewardAmount((Integer) jsonObject.get(prizeId));
                    result.add(entity);
                }
            }
        }


        /**
         * 获取活动道具
         */
        List<UserActivity> activity = userActivityService.getActivity(new Date());
        List<UserActivityProps> retUserActivityProps = new ArrayList<>();
        if (activity.size() > 0) {
            for (UserActivity userActivity : activity) {
                for (int i1 = 0; i1 < multiple; i1++) {
                    Map<List<Integer>, UserActivityProps> jackpotMap = null;
                    UserActivityProps activityProperty = null;
                    if (quality == FishingConstant.FISHING_GROUND_QUALITY_SENIOR) {  // 高级渔场
                        jackpotMap = redisService.getFishing(ConstantValue.SENIOR + userActivity.getId(), new UserActivityProps());   // 高级渔场

                        if (jackpotMap != null && jackpotMap.size() > 0) {
                            activityProperty = FisheryUtil.getActivityProperty(jackpotMap, 100);  // 抽取到的活动奖品
                        } else {
                            List<UserActivityProps> userActivityPropsList = userActivityPropsService.getActivityPropsList(userActivity.getId());
                            jackpotMap = FisheryUtil.getFisheryMap(userActivityPropsList, "seniorFisheryProbability");
                            redisService.setFishing(ConstantValue.SENIOR + userActivity.getId(), jackpotMap);
                            jackpotMap = redisService.getFishing(ConstantValue.SENIOR + userActivity.getId(), new UserActivityProps());   // 高级渔场
                            activityProperty = FisheryUtil.getActivityProperty(jackpotMap, 100);  // 抽取到的活动奖品
                        }
                    } else if (quality == FishingConstant.FISHING_GROUND_QUALITY_RARE) { // 稀有渔场
                        jackpotMap = redisService.getFishing(ConstantValue.RARE + userActivity.getId(), new UserActivityProps());   // 稀有渔场
                        if (jackpotMap != null && jackpotMap.size() > 0) {
                            activityProperty = FisheryUtil.getActivityProperty(jackpotMap, 100);  // 抽取到的活动奖品
                        } else {
                            List<UserActivityProps> userActivityPropsList = userActivityPropsService.getActivityPropsList(userActivity.getId());
                            jackpotMap = FisheryUtil.getFisheryMap(userActivityPropsList, "rareFisheryProbability");
                            redisService.setFishing(ConstantValue.RARE + userActivity.getId(), jackpotMap);
                            jackpotMap = redisService.getFishing(ConstantValue.RARE + userActivity.getId(), new UserActivityProps());   // 稀有渔场
                            activityProperty = FisheryUtil.getActivityProperty(jackpotMap, 100);  // 抽取到的活动奖品
                        }
                    } else {
                        jackpotMap = redisService.getFishing(ConstantValue.COMMONFISHING + userActivity.getId(), new UserActivityProps());   // 普通渔场
                        if (jackpotMap != null && jackpotMap.size() > 0) {
                            activityProperty = FisheryUtil.getActivityProperty(jackpotMap, 100);  // 抽取到的活动奖品
                        } else {
                            List<UserActivityProps> userActivityPropsList = userActivityPropsService.getActivityPropsList(userActivity.getId());
                            jackpotMap = FisheryUtil.getFisheryMap(userActivityPropsList, "commonFisheryProbability");
                            redisService.setFishing(ConstantValue.COMMONFISHING + userActivity.getId(), jackpotMap);
                            jackpotMap = redisService.getFishing(ConstantValue.COMMONFISHING + userActivity.getId(), new UserActivityProps());   // 普通渔场
                            activityProperty = FisheryUtil.getActivityProperty(jackpotMap, 100);  // 抽取到的活动奖品
                        }
                    }
                    // 将活动奖品加到活动背包当中
                    if (activityProperty == null) {  // 没有抽取活动奖品
                        log.info("没有抽取到活动奖品");
                        continue;
                    }
                    retUserActivityProps.add(activityProperty);
                    UserActivityPropsGoods userActivityPropsGoods =
                            userActivityPropsGoodsService.getUserActivityPropsGoods(userActivity.getId(), activityProperty.getId(), userId);
                    if (userActivityPropsGoods == null) {
                        userActivityPropsGoods = new UserActivityPropsGoods();
                        userActivityPropsGoods.setId(UUIDUtil.generateOriginalUUID());
                        userActivityPropsGoods.setCreationStartDate(new Date());
                        userActivityPropsGoods.setRecomposeEndDate(new Date());
                        userActivityPropsGoods.setActivityId(userActivity.getId());
                        userActivityPropsGoods.setStatus(StatusEnum.NORMAL.getStatusCode());
                        userActivityPropsGoods.setUserId(userId);
                        userActivityPropsGoods.setPropsId(activityProperty.getId());
                        i = userActivityPropsGoodsService.addUserActivityPropsGoods(userActivityPropsGoods);

                    } else {
                        userActivityPropsGoods.setAmount(userActivityPropsGoods.getAmount() + 1);
                        i = userActivityPropsGoodsService.update(userActivityPropsGoods);
                    }
                    if (i < 0) {
                        return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "活动奖品增加背包失败");
                    }
                }
            }
        }

        Map<List<Integer>, FishInfo> fishingMap;
        Integer sumWeight;
        if (quality == FishingConstant.FISHING_GROUND_QUALITY_SENIOR) {  // 高级渔场
            fishingMap = redisService.getFishing(ConstantValue.SENIOR_FISH, new FishInfo());
            sumWeight = (Integer) redisService.get(ConstantValue.SENIOR_PROBABILITY);
            if (fishingMap == null) {
                List<FishInfo> fishInfoList = fishInfoService.getFishInfoList();
                fishingMap = FisheryUtil.getFisheryMap(fishInfoList, "seniorProbability");
                redisService.setFishing(ConstantValue.SENIOR_FISH, fishingMap);
                fishingMap = redisService.getFishing(ConstantValue.SENIOR_FISH, new FishInfo());
            }
            if (sumWeight == null) {
                sumWeight = fishInfoService.getSumSeniorProbability();
                redisService.set(ConstantValue.SENIOR_PROBABILITY, sumWeight);
            }
        } else if (quality == FishingConstant.FISHING_GROUND_QUALITY_RARE) { // 稀有渔场
            fishingMap = redisService.getFishing(ConstantValue.RARE_FISH, new FishInfo());
            sumWeight = (Integer) redisService.get(ConstantValue.RARE_PROBABILITY);
            if (fishingMap == null) {
                List<FishInfo> fishInfoList = fishInfoService.getFishInfoList();
                fishingMap = FisheryUtil.getFisheryMap(fishInfoList, "rareProbability");
                redisService.setFishing(ConstantValue.RARE_FISH, fishingMap);
                fishingMap = redisService.getFishing(ConstantValue.RARE_FISH, new FishInfo());
            }
            if (sumWeight == null) {
                sumWeight = fishInfoService.getSumRrareProbability();
                redisService.set(ConstantValue.RARE_PROBABILITY, sumWeight);
            }
        } else {
            fishingMap = redisService.getFishing(ConstantValue.COMMONFISHING_FISH, new FishInfo());
            sumWeight = (Integer) redisService.get(ConstantValue.COMMONFISHING_PROBABILITY);
            if (fishingMap == null) {
                List<FishInfo> fishInfoList = fishInfoService.getFishInfoList();
                fishingMap = FisheryUtil.getFisheryMap(fishInfoList, "commonProbability");
                redisService.setFishing(ConstantValue.COMMONFISHING_FISH, fishingMap);
                fishingMap = redisService.getFishing(ConstantValue.COMMONFISHING_FISH, new FishInfo());
            }
            if (sumWeight == null) {
                sumWeight = fishInfoService.getSumCommonProbability();
                redisService.set(ConstantValue.COMMONFISHING_PROBABILITY, sumWeight);
            }
        }

        List<UserGoods> userGoodsList = new ArrayList<>();

        UserGoods entity;
        for (int i1 = 0; i1 < multiple - fishingSecretOperationsList.size(); i1++) {
            FishInfo fishInfo = FisheryUtil.getActivityProperty(fishingMap, sumWeight);
            fishPrizes = redisService.getFishing(ConstantValue.FISH_PRIZES + fishInfo.getId(), new Integer(0));
            Integer fishPrizesWeight = (Integer) redisService.get(ConstantValue.FISH_PRIZES_WEIGHT + fishInfo.getId());
            if (fishPrizes == null) {
                String rewardProbability = fishInfo.getRewardProbability();
                JSONObject jsonObject = JSON.parseObject(rewardProbability);
                int record = 0;
                fishPrizes = new HashMap<>();
                for (Object key : jsonObject.keySet()) {
                    fishPrizes.put(FisheryUtil.getProbabilityList(record, record + (Integer) jsonObject.get(key)), (Integer) key);
                    record += (Integer) jsonObject.get(key);
                }
                redisService.setFishing(ConstantValue.FISH_PRIZES + fishInfo.getId(), fishPrizes);
                fishPrizes = redisService.getFishing(ConstantValue.FISH_PRIZES + fishInfo.getId(), new Integer(0));
            }
            if (fishPrizesWeight == null) {
                String rewardProbability = fishInfo.getRewardProbability();
                JSONObject jsonObject = JSON.parseObject(rewardProbability);
                fishPrizesWeight = 0;
                for (Object key : jsonObject.keySet()) {
                    fishPrizesWeight += (Integer) jsonObject.get(key);
                }
                redisService.set(ConstantValue.FISH_PRIZES_WEIGHT + fishInfo.getId(), fishPrizesWeight);
                fishPrizesWeight = (Integer) redisService.get(ConstantValue.FISH_PRIZES_WEIGHT + fishInfo.getId());
            }
            Integer prizeId = FisheryUtil.getActivityProperty(fishPrizes, fishPrizesWeight);  // 鱼对应奖品id
            String reward = fishInfo.getReward();
            JSONObject jsonObject = JSON.parseObject(reward);
            entity = new UserGoods();
            entity.setAmount(1);
            entity.setGoodsId(fishInfo.getId());
            entity.setRewardId(prizeId);
            entity.setRewardAmount((Integer) jsonObject.get(prizeId));
            userGoodsList.add(entity);
        }
        result.addAll(userGoodsList);


        /**更新用户背包数据 **/
        UserGoods voucher = userGoodsService.subUserGoodsSingle(userId, GoodsConstant.GOODS_VIGOUR, multiple);//减去对应的钓鱼券
        Map<String, List<Map<String, Object>>> list = new HashMap<String, List<Map<String, Object>>>();//整合的返回信息
        list.put("fishGoods", MyBeanUtils.formatResult(result, Arrays.asList("goodsId", "rewardId", "rewardAmount")));//获得的钓鱼记录(ID：数量)
//		list.put("rewardGoods", MyBeanUtils.formatResult(moneys, Arrays.asList("goodsId", "amount")));//鱼的价值List(Id：金币价值)
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", list);
        map.put("voucher", MyBeanUtils.formatObjResult(voucher, Arrays.asList("goodsId", "amount")));//剩余钓鱼券数量
        if (retUserActivityProps.size() > 0) {
            List<Map<String, Object>> retList = new LinkedList<>();
            for (UserActivityProps userActivityProps : retUserActivityProps) {
                Map<String, Object> ret = new LinkedHashMap<>();
                ret.put("id", userActivityProps.getId());
                ret.put("gamePropsName", userActivityProps.getGamePropsName());
                ret.put("introduce", userActivityProps.getIntroduce());
                ret.put("gamePropsPicture", userActivityProps.getGamePropsPicture());
                retList.add(ret);
            }
            map.put("activityPrizes", retList);
        }

        //将钓鱼奖励入库
        for (UserGoods item : result) {
            userGoodsService.addUserGoodsSingle(userId, item.getRewardId() + "", item.getRewardAmount());
        }

        // 刷新钓鱼任务进展
        taskService.taskProgress(FishingConstant.TASK_TYPE_DY, multiple, userId);

        return new BaseReturn("操作成功!", map);
    }

    /**
     * 使用海螺刷新鱼塘接口
     *
     * @param fishingPO -- userId 用户ID
     * @return
     */
    @ApiOperation(value = "使用海螺刷新鱼塘接口", notes = "请求参数说明 [userId 用户ID]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明data[conch 剩余的海螺数量] [date 该玩家是否处于渔汛阶段 -1不在渔汛时段 其他返回渔汛剩余时间]" +
                    "[timeSlot 1 上午 0 下午 ] [quality 渔场品质 1 普通渔场 2 高级渔场 3 稀有渔场]")
    })
    @PostMapping(value = "refreshPond", produces = "application/json")
    public BaseReturn refreshPond(@RequestBody FishingPO fishingPO) {
        String userId = fishingPO.getUserId();
        if (StringUtils.isBlank(userId)) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        UserGoods goodsCount = userGoodsService.getGoodsCount(userId, GoodsConstant.GOODS_CONCH);
        if (goodsCount == null || goodsCount.getAmount() < 1) {//先判断用户海螺数量够不够刷新一次
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "海螺数量不够!");
        }
        //减去海螺数量
        UserGoods userGoods = userGoodsService.subUserGoodsSingle(userId, GoodsConstant.GOODS_CONCH, 1);


        Integer quality = FishingConstant.getSingleton().getQuality();
        Integer qualityRedis = (Integer) redisService.get(ConstantValue.REDIS_FISHING_QUALITY + userId);
        if (qualityRedis != null) {
            if (quality <= qualityRedis) {
                quality = qualityRedis;
            }
        }
        //将鱼塘信息存入缓存,时间为30分钟
        redisService.set(ConstantValue.REDIS_FISHING_QUALITY + userId, quality, new Long(FishingConstant.FISHING_FLOOD_DATETIME));
        Map<String, Object> map = new HashMap<>();
        map.put("timeSlot", FishingUtil.getDateInterval());//当前时间上午还是下午
        map.put("date", FishingConstant.FISHING_FLOOD_DATETIME);//鱼汛时间
        map.put("quality", quality);//渔场品质
        map.put("conch", userGoods);//剩余海螺数量
        return new BaseReturn("操作成功!", map);
    }


    @ApiOperation(value = "删除redis缓存", notes = "请求参数说明 [key redis 的key值]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "")
    })
    @PostMapping(value = "clearCache/{key}", produces = "application/json")
    public BaseReturn clearCache(@PathVariable("key") String key) {
        if (StringUtils.isBlank(key)) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        redisService.remove(key);
        return new BaseReturn("操作成功!");
    }


    @ApiOperation(value = "抽奖钓鱼", notes = "请求参数说明 [userId 用户ID  multiple 钓鱼次数 goodsId 消耗品 amount 消耗品数量] ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "")
    })
    @PostMapping(value = "lottery", produces = "application/json")
    public BaseReturn lottery(@RequestBody FishingPO fishingPO) {
        String userId = fishingPO.getUserId();
        Integer multiple = fishingPO.getMultiple();
        String consumables = fishingPO.getGoodsId();
        Integer amount = fishingPO.getAmount();
        if (multiple == null || multiple == 0) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (StringUtils.isBlank(userId)) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (StringUtils.isBlank(consumables)) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        try {
            List<FishInfo> fishInfoList = fishingInfoService.lottery(userId, multiple, consumables, amount);
            for (FishInfo fishInfo : fishInfoList) {
                if (fishInfo.getBoutique() == ConstantValue.FISH_BOUTIQUE_YES) {
                    // 发送通知
                }
            }
        } catch (Exception e) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "资源不足!");
        }
        return new BaseReturn("操作成功!");
    }

    @ApiOperation(value = "获取抽奖仓库中的用户道具信息", notes = "请求参数说明 [userId 用户ID  ]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "")
    })
    @PostMapping(value = "queryGoodsLottery", produces = "application/json")
    public BaseReturn queryGoodsLottery(@RequestBody FishingPO fishingPO) {
        String userId = fishingPO.getUserId();
        if (StringUtils.isBlank(userId)) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }

        List<FishEntrepot> fishEntrepots = fishEntrepotService.getFishEntrepotList(userId, null);
        for (FishEntrepot fishEntrepot : fishEntrepots) {
            ObjectUtil.setObjectFieldsEmpty(fishEntrepot, "goodsId", "amount");
        }
        Map retMap = new HashMap();
        retMap.put("userId", userId);
        retMap.put("goodsList", fishEntrepots);
        return new BaseReturn("操作成功!", retMap);
    }


    @ApiOperation(value = "提取抽奖仓库中的用户道具", notes = "请求参数说明 [userId 用户ID  goodsId 提取物品id 不传则提取用户所有的物品 ]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "")
    })
    @PostMapping(value = "extract", produces = "application/json")
    public BaseReturn extract(@RequestBody FishingPO fishingPO) {
        String userId = fishingPO.getUserId();
        String goodsId = fishingPO.getGoodsId();
        if (StringUtils.isBlank(userId)) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }


        List list = new ArrayList();
        List<FishEntrepot> fishEntrepots = fishEntrepotService.getFishEntrepotList(userId, goodsId);
        for (FishEntrepot fishEntrepot : fishEntrepots) {
            UserGoods userGoods = userGoodsService.addUserGoodsSingle(userId, fishEntrepot.getGoodsId(), fishEntrepot.getAmount());
            Map goodsMap = ObjectUtil.getGoodsMap(fishEntrepot.getGoodsId(), fishEntrepot.getGoodsId(), 1, userGoods.getAmount(),null);
            list.add(goodsMap);
            fishEntrepotService.extractGoods(userId, fishEntrepot.getGoodsId());
        }
        Map retMap = new HashMap();
        retMap.put("userId", userId);
        retMap.put("goodsList", list);
        return new BaseReturn("操作成功!", retMap);
    }


    @ApiOperation(value = "抽奖记录", notes = "请求参数说明 [[pageNum 页号] [pageSize 每页大小]]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "")
    })
    @PostMapping(value = "drawRecord", produces = "application/json")
    public BaseReturn drawRecord(@RequestBody FishingPO fishingPO) {

        if (fishingPO.getPageNum() <= 0 || fishingPO.getPageSize() <= 0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "分页参数要大于0!");

        Pager<FishEntrepotVO> pager = fishEntrepotService.getFishEntrepotBoutique(fishingPO.getPageNum(), fishingPO.getPageSize());

        return new BaseReturn("操作成功!", pager);
    }


}
