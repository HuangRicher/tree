package com.tongzhu.fishing.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.fishing.constant.ConstantValue;
import com.tongzhu.constant.GoodsConstant;
import com.tongzhu.fishing.domain.PropInfo;
import com.tongzhu.fishing.domain.User;
import com.tongzhu.fishing.domain.UserGoods;
import com.tongzhu.fishing.domain.UserMessage;
import com.tongzhu.fishing.enums.CoinlvlEnum;
import com.tongzhu.fishing.model.DialInfo;
import com.tongzhu.fishing.model.DialShoppingInfo;
import com.tongzhu.fishing.model.DialUserRecord;
import com.tongzhu.fishing.po.DialPO;
import com.tongzhu.fishing.po.OpenVaults;
import com.tongzhu.fishing.redis.RedisService;
import com.tongzhu.fishing.service.*;
import com.tongzhu.util.DateUtil;
import com.tongzhu.fishing.util.FisheryUtil;
import com.tongzhu.util.ObjectUtil;
import com.tongzhu.util.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jodd.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Api(value = "转盘controller", tags = {"转盘API"})
@RestController
@RequestMapping(value = "/dial")
public class DialController {

    private static Logger log = LoggerFactory.getLogger(DialController.class);

    @Autowired
    private RedisService redisService;

    @Autowired
    private DialInfoService dialInfoService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private DialUserRecordService dialUserRecordService;

    @Autowired
    private PropService propService;

    @Autowired
    private DialShoppingInfoService dialShoppingInfoService;

    @Autowired
    private FriendService friendService;

    /**
     * 配置转盘属性
     *
     * @return
     */
    @ApiOperation(value = "配置转盘属性", notes = "请求参数说明 [userId 用户ID]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "")
    })
    @PostMapping(value = "configDial", produces = "application/json")
    public BaseReturn configDial(@RequestBody DialPO dialPO) {
        if (StringUtil.isEmpty(dialPO.getConfigJson())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        //{"interval": 1800,"count":8,"vipInterval":60,"vipCount":10,"two":20,"four":20,"eight":20,"sixteen":10}
        redisService.set(ConstantValue.DIAL_CONFIG_JSON, dialPO.getConfigJson());
        redisService.set(ConstantValue.DIAL_DROP_COINS, dialPO.getDropCoinsJson());
        redisService.remove(ConstantValue.DIAL_RATE_RANDOM);
        redisService.remove(ConstantValue.DIAL_RATE_RANDOM_WEIGHT);
        return new BaseReturn("操作成功!");
    }


//    /**
//     * 转动转盘
//     * @param dialPO
//     * @return
//     */
//    @ApiOperation(value = "转动转盘", notes = "请求参数说明 [userId 用户ID]")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "   [dialRate  转盘倍率]" +
//                    "    [dialId 转到玩法id ]" +
//                    "    [cdTime 冷却时间]" +
//                    "    [rotationCount 转盘剩余次数]")
//    })
//    @PostMapping(value = "kickOver", produces = "application/json")
//    public BaseReturn kickOver(@RequestBody DialPO dialPO) {
//        if (StringUtil.isEmpty(dialPO.getUserId())) {
//            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
//        }
//        //{"dialRate":{16:5,2:10,4:20,8:10},"vipInterval":60,"count":8,"interval":1800,"vipCount":10}
//        Long date = redisService.getInvalid(ConstantValue.DIAL_FREEZE_TIME + dialPO.getUserId());  // 转盘冻结时间
//        if (date > 0) {
//            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "正在冷却中!");
//        }
//        Long count = redisService.increment(ConstantValue.DIAL_COUNT + dialPO.getUserId(), -1L);
//        if (count < 0) {
//            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "今日次数已经用完!");
//        }
//
//        // 获取转盘倍率数据
//        Map<String, Integer> dialRateMap = redisService.getMap(ConstantValue.DIAL_RATE_RANDOM, new Integer(0));
//        String configJson = (String) redisService.get(ConstantValue.DIAL_CONFIG_JSON);
//        if (dialRateMap == null) {
//            JSONObject dialRateJson = JSON.parseObject(configJson).getJSONObject("dialRate");
//            dialRateMap = new HashMap<>();
//            int record = 0;
//            for (Object s : dialRateJson.keySet()) {
//                dialRateMap.put(FisheryUtil.getProbabilityList(record, record + (Integer) dialRateJson.get(s)).toString()
//                        .replace("[", "-").replace("]", "") + ",", (Integer) s);
//                record += (Integer) dialRateJson.get(s);
//            }
//            redisService.setMap(ConstantValue.DIAL_RATE_RANDOM, dialRateMap);
//            redisService.set(ConstantValue.DIAL_RATE_RANDOM_WEIGHT, record);
//        }
//        Integer dialRateWeight = (Integer) redisService.get(ConstantValue.DIAL_RATE_RANDOM_WEIGHT);
//        Integer dialRate = FisheryUtil.getRandomMapValue(dialRateMap, dialRateWeight);
//
//
//        Map<String, Integer> dialMap = redisService.getMap(ConstantValue.DIAL_RANDOM, new Integer(0));
//        if (dialMap == null) {
//            dialMap = new HashMap<>();
//            int record = 0;
//            List<DialInfo> dialInfoList = dialInfoService.getDialInfoList();
//            for (DialInfo dialInfo : dialInfoList) {
//                dialMap.put(FisheryUtil.getProbabilityList(record, record + dialInfo.getProbability()).toString()
//                        .replace("[", "-").replace("]", "") + ",", dialInfo.getId());
//                record += dialInfo.getProbability();
//            }
//            redisService.setMap(ConstantValue.DIAL_RANDOM, dialMap);
//            redisService.set(ConstantValue.DIAL_RANDOM_WEIGHT, record);
//        }
//        Integer dialWeight = (Integer) redisService.get(ConstantValue.DIAL_RANDOM_WEIGHT);
//        Integer dialId = FisheryUtil.getRandomMapValue(dialMap, dialWeight);
//
//        Long interval = JSON.parseObject(configJson).getLong("interval");
//        redisService.set(ConstantValue.DIAL_FREEZE_TIME + dialPO.getUserId(), dialPO.getUserId(), interval);
//
//
//        Map<String, Object> map = new LinkedHashMap<>();
//        map.put("dialRate", dialRate);
//        map.put("dialId", dialId);
//        map.put("cdTime", interval);
//        map.put("rotationCount", count);
//        return new BaseReturn("操作成功!", map);
//    }

    /**
     * 转动转盘
     *
     * @param dialPO
     * @return
     */
    @ApiOperation(value = "转动转盘", notes = "请求参数说明 [userId 用户ID]")
    @ApiResponses({
            @ApiResponse(code = 200, message = " [dialId 转到玩法id ]" +
                    "    [cdTime 冷却时间]  " +
                    "    [rotationCount 转盘剩余次数]")
    })
    @PostMapping(value = "kickOver", produces = "application/json")
    public BaseReturn kickOver(@RequestBody DialPO dialPO) {
        if (StringUtil.isEmpty(dialPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }

        UserGoods userGoods = userGoodsService.getGoodsCount(dialPO.getUserId(), GoodsConstant.GOODS_VIGOUR);
        if (userGoods.getAmount() <= 0) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "活力不足!");
        }

        Map<String, Object> map = new LinkedHashMap<>();


        // 获取不在抽取中的区域
        Integer heavySum = dialInfoService.getForHeavySum(dialPO.getUserId());
        Map<String, Integer> dialMap = redisService.getMap(ConstantValue.DIAL_RANDOM + heavySum, new Integer(0));
        if (dialMap == null) {
            dialMap = new LinkedHashMap<>();
            int record = 0;
            List<DialInfo> dialInfoList = dialInfoService.getDialInfoList(dialPO.getUserId());
            for (DialInfo dialInfo : dialInfoList) {
                dialMap.put(FisheryUtil.getProbabilityList(record, record + dialInfo.getProbability()).toString()
                        .replace("[", "").replace("]", "") + ",", dialInfo.getId());
                record += dialInfo.getProbability();
            }
            redisService.setMap(ConstantValue.DIAL_RANDOM + heavySum, dialMap);
            redisService.set(ConstantValue.DIAL_RANDOM_WEIGHT + heavySum, record);
        }
        Integer dialWeight = (Integer) redisService.get(ConstantValue.DIAL_RANDOM_WEIGHT + heavySum);
        Integer dialId = FisheryUtil.getRandomMapValue(dialMap, dialWeight);

        DialInfo dialInfo = dialInfoService.getDialInfoById(dialId);
        if (!StringUtils.isEmpty(dialInfo.getAward())) {
            JSONObject awardJson = JSON.parseObject(dialInfo.getAward());
            JSONObject prop = awardJson.getJSONObject("prop");
            for (Object goodsId : prop.keySet()) {
                PropInfo propInfo = propService.getPropInfo((String) goodsId);
                if (propInfo == null) {
                    continue;
                }
                userGoodsService.addUserGoodsSingle(dialPO.getUserId(), goodsId + "", (Integer) prop.get(goodsId));
                Map goodsMap = new HashMap();
                goodsMap.put("id", propInfo.getId());
                goodsMap.put("goodsId", propInfo.getId());
                goodsMap.put("amount", prop.get(goodsId));
                goodsMap.put("type", 1);
                map.put("goods", goodsMap);
            }
        }

        DialUserRecord dialUserRecord = dialUserRecordService.getDialUserRecordByUserIdAndDialId(dialPO.getUserId(), dialInfo.getId());
        if (dialUserRecord == null) {
            dialUserRecord = new DialUserRecord();
            dialUserRecord.setUserId(dialPO.getUserId());
            dialUserRecord.setCount(1);
            dialUserRecord.setCreateDate(new Date());
            dialUserRecord.setUpdateDate(new Date());
            dialUserRecord.setDialId(dialInfo.getId());
            dialUserRecordService.insertSelective(dialUserRecord);
        } else {
            if (!DateUtil.sameDate(dialUserRecord.getUpdateDate(), new Date())) {
                dialUserRecord.setUpdateDate(new Date());
                dialUserRecord.setCount(1);
            } else {
                dialUserRecord.setUpdateDate(new Date());
                dialUserRecord.setCount(dialUserRecord.getCount() + 1);
            }
            dialUserRecordService.updateByPrimaryKeySelective(dialUserRecord);
        }
        userGoodsService.subUserGoodsSingle(dialPO.getUserId(), GoodsConstant.GOODS_VIGOUR, 1);

        map.put("dialId", dialId);
        return new BaseReturn("操作成功!", map);
    }


    /**
     * 进入转盘
     *
     * @param dialPO
     * @return
     */
    @ApiOperation(value = "进入转盘", notes = "请求参数说明 [userId 用户ID]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[userId 用户id]")
    })
    @PostMapping(value = "initDial", produces = "application/json")
    public BaseReturn initDial(@RequestBody DialPO dialPO) {
        if (StringUtil.isEmpty(dialPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        Long date = redisService.getInvalid(ConstantValue.DIAL_FREEZE_TIME + dialPO.getUserId());  // 转盘冻结时间
        Integer count = (Integer) redisService.get(ConstantValue.DIAL_COUNT + dialPO.getUserId());  // 转盘剩余次数
        if (count == null) {
            String configJson = (String) redisService.get(ConstantValue.DIAL_CONFIG_JSON);
            if (configJson == null) {
                return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "转盘参数未配置!");
            }
            count = JSON.parseObject(configJson).getInteger("count");
            redisService.set(ConstantValue.DIAL_COUNT + dialPO.getUserId(), count, DateUtil.restOfDay());
        }
        Map<String, Object> map = new HashMap();
        map.put("cdTime", date);//时间
        map.put("userId", dialPO.getUserId());//用户id
        map.put("rotationCount", count);//次数
        return new BaseReturn("操作成功!", map);
    }

    /**
     * 转盘天降财神
     *
     * @param dialPO
     * @return
     */
    @ApiOperation(value = "转盘天降财神", notes = "请求参数说明 [userId 用户ID]")
    @ApiResponses({
            @ApiResponse(code = 200, message = " [goldCount 奖励金币] [用户id] [id 背包物品id]")
    })
    @PostMapping(value = "dropCoins", produces = "application/json")
    public BaseReturn dropCoins(@RequestBody DialPO dialPO) {
        if (StringUtil.isEmpty(dialPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        String dialDrop = (String) redisService.get(ConstantValue.DIAL_DROP_COINS);
        JSONObject jsonObject = JSON.parseObject(dialDrop);
        User user = userService.findByUserId(dialPO.getUserId());
        int grade = user.getGrade();
        int max = 0;
        for (Object o : jsonObject.keySet()) {
            if (grade <= (Integer) o) {   // 所有的数都比grade大
                if (max == 0 || max > (Integer) o) {
                    max = (Integer) o;
                }
            }
        }
        UserGoods userGoods = userGoodsService.addUserGoodsSingle(dialPO.getUserId(), GoodsConstant.GOODS_MONEY, (Integer) jsonObject.get(max));
        Map<String, Object> map = new HashMap();
        map.put("userId", dialPO.getUserId());//用户id
        map.put("goldCount", jsonObject.get(max));// 奖励金币数
        map.put("id", userGoods.getId());// id
        return new BaseReturn("操作成功!", map);
    }


    /**
     * 砸金库
     *
     * @return
     */
    @ApiOperation(value = "砸金库", notes = "请求参数说明 [userId 用户ID][strength 力度][friendUserId 被砸的用户id]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[friendId 被砸的用id][friendName 被砸用户名称][amount 获取金币数量] [crit 是否暴击 1 暴击 2 没有暴击] {vigour 精力}")
    })
    @PostMapping(value = "openVaults", produces = "application/json")
    public BaseReturn openVaults(@RequestBody OpenVaults openVaults) {
        if (StringUtil.isEmpty(openVaults.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (StringUtil.isEmpty(openVaults.getFriendUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (openVaults.getStrength() == null) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        User user = userService.findByUserId(openVaults.getUserId());
        User friendUser = userService.findByUserId(openVaults.getFriendUserId());
        // 计算到底获取到了多少金币
        int amount = 0;

        Random random = new Random();
        int i = random.nextInt(1500) + 2000;
        Double taskId = CoinlvlEnum.getTaskId(openVaults.getStrength());
        if (taskId == null) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "力度配置错误!");
        }
        amount = new Double(taskId * i).intValue();

        int crit = 2;
        if (openVaults.getStrength() >= 90 && random.nextInt(100) < 20) {
            crit = 1;
            amount = amount * 2;
        }
        UserMessage userMessage = new UserMessage();
        userMessage.setId(UUIDUtil.generateUUID());
        userMessage.setCreateDate(new Date());
        userMessage.setStatus(1); // 1 正常
        userMessage.setReceiverId(openVaults.getFriendUserId());
        userMessage.setSenderId(openVaults.getUserId());
        userMessage.setMessageBody(user.getName() +
                "砸了你家的金库，获得金币" + amount);
        userMessage.setType(ConstantValue.ZJK_MESSAGE);
        userService.addUserMessage(userMessage);

        UserGoods userGoods = userGoodsService.subUserGoodsSingle(openVaults.getUserId(), GoodsConstant.GOODS_VIGOUR, 2);
        Map goodsMap = ObjectUtil.getGoodsMap(userGoods.getGoodsId() + "", userGoods.getGoodsId(), 1, userGoods.getAmount(), null);
        userGoodsService.addUserGoodsSingle(openVaults.getUserId(), GoodsConstant.GOODS_MONEY, amount);

        // 刷新砸金库任务
        taskService.taskProgress(ConstantValue.TASK_TYPE_ZJK, 1, openVaults.getUserId());
        Map<String, Object> map = new HashMap();

        map.put("friendId", friendUser.getId());//用户id
        map.put("friendName", friendUser.getName());//用户id
//        map.put("amount", amount);// 奖励金币数
        map.put("crit", crit);// 1 暴击 2 没有暴击
        Map goods = new HashMap();
        goods.put("id", GoodsConstant.GOODS_MONEY);
        goods.put("goodsId", GoodsConstant.GOODS_MONEY);
        goods.put("type", 1);
        goods.put("amount", amount);
        map.put("goods", goods);
        map.put("vigour", goodsMap);
        return new BaseReturn("操作成功!", map);
    }


//        public static void main(String[] args) {
//        String s = "{\"dialRate\":{16:5,2:10,4:20,8:10},\"vipInterval\":60,\"count\":8,\"interval\":1800,\"vipCount\":10}";
//        {5:500,10:1000,15:2000,25:3000};
//        Map<String, Object> map = new HashMap<>();
//        //{"interval": 1800,"count":8,"vipInterval":60,"vipCount":10,"two":20,"four":20,"eight":20,"sixteen":10}
//        map.put("interval", 1800);
//        map.put("count", 8);
//        map.put("vipInterval", 60);
//        map.put("vipCount", 10);
//        Map<Integer, Integer> i = new HashMap<>();
//        i.put(2, 10);
//        System.out.println("");
//        i.put(4, 20);
//        i.put(8, 10);
//        i.put(16, 5);
//        map.put("dialRate", i);
//        String s = JSONObject.toJSONString(map);
//        System.out.println(s);
//    }

    /**
     * 逛街
     *
     * @return
     */
    @ApiOperation(value = "逛街", notes = "请求参数说明 [userId 用户ID]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[friendId 被砸的用id][friendName 被砸用户名称][amount 获取金币数量] [crit 是否暴击 1 暴击 2 没有暴击]")
    })
    @PostMapping(value = "shopping", produces = "application/json")
    public BaseReturn shopping(@RequestBody DialPO dialPO) {
        if (StringUtils.isEmpty(dialPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (StringUtils.isEmpty(dialPO.getFriendsId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }

        User user = userService.findByUserId(dialPO.getUserId());
        if (user == null) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户不存在!");
        }
        User friendsUser = userService.findByUserId(dialPO.getFriendsId());
        if (friendsUser == null) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户不存在!");
        }

        Map<List<Integer>, DialShoppingInfo> fishingMap = redisService.getFishing(ConstantValue.DIAL_SHOPPING_INFO, new DialShoppingInfo());
        Integer sumWeight = (Integer) redisService.get(ConstantValue.PROBABILITY_DIAL_SHOPPING_INFO);
        if (fishingMap == null) {
            List<DialShoppingInfo> dialShoppingInfoList = dialShoppingInfoService.getDialShoppingInfoList();
            fishingMap = FisheryUtil.getFisheryMap(dialShoppingInfoList, "probability");
            redisService.setFishing(ConstantValue.DIAL_SHOPPING_INFO, fishingMap);
            fishingMap = redisService.getFishing(ConstantValue.DIAL_SHOPPING_INFO, new DialShoppingInfo());
            if (sumWeight == null) {
                sumWeight = 0;
                for (DialShoppingInfo dialShoppingInfo : dialShoppingInfoList) {
                    sumWeight += dialShoppingInfo.getProbability();
                }
                redisService.set(ConstantValue.PROBABILITY_DIAL_SHOPPING_INFO, sumWeight);
            }
        }

        Map map = new HashMap();
        DialShoppingInfo dialShoppingInfo = FisheryUtil.getActivityProperty(fishingMap, sumWeight);
        if (dialShoppingInfo.getId().equals(ConstantValue.DIAL_SHOPPING_GJ)) { // 逛街
            map.put("shoppingId", dialShoppingInfo.getId());
            map.put("msg", "你和" + friendsUser.getName() + "逛了一整天，亲密度增加" + dialShoppingInfo.getParameter());
            friendService.updateIntimacy(dialPO.getUserId(), dialPO.getFriendsId(), dialShoppingInfo.getParameter());
        } else if (dialShoppingInfo.getId().equals(ConstantValue.DIAL_SHOPPING_KDY)) { // 看电影
            map.put("shoppingId", dialShoppingInfo.getId());
            map.put("msg", "你和" + friendsUser.getName() + "看了一场电影，亲密度增加" + dialShoppingInfo.getParameter());
            friendService.updateIntimacy(dialPO.getUserId(), dialPO.getFriendsId(), dialShoppingInfo.getParameter());
        } else if (dialShoppingInfo.getId().equals(ConstantValue.DIAL_SHOPPING_ZF)) { // 做饭
            map.put("shoppingId", dialShoppingInfo.getId());
            map.put("msg", "你和" + friendsUser.getName() + "一起做饭，亲密度增加" + dialShoppingInfo.getParameter());
            friendService.updateIntimacy(dialPO.getUserId(), dialPO.getFriendsId(), dialShoppingInfo.getParameter());
        } else if (dialShoppingInfo.getId().equals(ConstantValue.DIAL_SHOPPING_YX)) {  // 遇袭
            map.put("msg", "你和" + friendsUser.getName() + "逛街时遇到怪物袭击");
            map.put("shoppingId", dialShoppingInfo.getId());
        } else if (dialShoppingInfo.getId().equals(ConstantValue.DIAL_SHOPPING_JKSQ)) { //金库失窃
            map.put("msg", "你和" + friendsUser.getName() + "逛街时，金库失窃了，损失了" + dialShoppingInfo.getParameter() + "%的产出金币");
            map.put("shoppingId", dialShoppingInfo.getId());
        }

        /**
         * 刷新日常任务
         */
        taskService.taskProgress(ConstantValue.TASK_TYPE_GJ, 1, dialPO.getUserId());


        return new BaseReturn("操作成功!", map);
    }


    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("a.age", "46岁,1231");
        map.put("a.name", "牛逼,你说");
        map.put("aab.ziduan2", "1, 2,1");
        map.put("aa.passwode", "你好,你在");
        map.put("b.ziduan1", "bid, biw,1");
        map.put("b.我擦", "1, 2,1");
        Map<String, String> resultMap = sortMapByKey(map);
        Integer i = 1;
        Integer n = 1;
        String s = "";
        Map<String, Map<String, String>> retMap = new HashMap<>();
        for (String str : resultMap.keySet()) {
            String value = resultMap.get(str);
            String[] split = str.split("\\.");
            if (!split[0].equals(s)) {
                n = i;
            }
            s = split[0];
            if (retMap.get(n + "") != null) {
                String[] splitValue = value.split(",");
                for (int j = 0; j < splitValue.length; j++) {
                    retMap.get(n + "").put(split[1], splitValue[j]);
                    System.out.println(JSONObject.toJSONString(retMap));
                    n = n + 1;
                }
                n = n - splitValue.length;
            } else {
                String[] splitValue = value.split(",");
                for (int j = 0; j < splitValue.length; j++) {
                    Map<String, String> valueMap = new HashMap<>();
                    valueMap.put(split[1], splitValue[j]);
                    valueMap.put("id", split[0]);
                    valueMap.put("biaoji", "update");
                    retMap.put(i + "", valueMap);
                    System.out.println(JSONObject.toJSONString(retMap));
                    i = i + 1;
                }
            }
        }
        System.out.println(JSONObject.toJSONString(retMap));
        //{"1":{"age":"46岁","name":"牛逼","id":"a","biaoji","update"},"2":{"ziduan1":"bid","ziduan2":"1","id":"b","biaoji","update"},"3":{"ziduan1":"biw","ziduan2":"2","id":"b","biaoji","update"},"4":{"ziduan1":"1","ziduan2":"1","id":"b","biaoji","update"}

    }

    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<String, String>(
                new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }

    static class MapKeyComparator implements Comparator<String> {
        @Override
        public int compare(String str1, String str2) {
            return str1.compareTo(str2);
        }
    }


}
