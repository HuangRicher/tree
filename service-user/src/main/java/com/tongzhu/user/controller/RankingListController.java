package com.tongzhu.user.controller;

import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.constant.GoodsConstant;
import com.tongzhu.user.constant.RanklingListConstant;
import com.tongzhu.user.constant.RoleTitleConstant;
import com.tongzhu.user.controller.vo.RankingListVO;
import com.tongzhu.user.job.RecoveryUserVigourTask;
import com.tongzhu.user.mapper.vo.UserRankingVO;
import com.tongzhu.user.po.RankingListPO;
import com.tongzhu.user.redis.RedisService;
import com.tongzhu.user.service.UserCrunchiesService;
import com.tongzhu.user.service.UserRoleService;
import com.tongzhu.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api(value = "排行榜controller", tags = {"排行榜相关API"})
@RestController
@RequestMapping("/rankingList")
public class RankingListController {

    private static Logger log = LoggerFactory.getLogger(RankingListController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserCrunchiesService userCrunchiesService;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 身价排行榜
     *
     * @param rankingListPO --userId 用户ID
     * @return
     */
    @ApiOperation(value = "身价排行榜", notes = "请求参数说明 [userId 用户ID] ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明 [serial 序号][userId 用户ID] [userName 用户名字] " +
                    "[portraitUrl 头像url][amount 身价数量] [myLocation 我的排名 （等于0表示未上榜）]")
    })
    @PostMapping("/sellingPriceRankingList")
    public BaseReturn sellingPriceRankingList(@RequestBody RankingListPO rankingListPO) {
        if (StringUtils.isBlank(rankingListPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");

        int myLocation = 0;
        List<RankingListVO> list = new ArrayList<>();
        List<UserRankingVO> users = userService.findForRankingListAboutSellingPrice(RanklingListConstant.SELLING_PRICE_RANKING_SHOW_COUNT);
        for (UserRankingVO user : users) {
            RankingListVO vo = new RankingListVO();
            vo.setUserId(user.getUserId());
            vo.setUserName(user.getName());
            vo.setAmount(user.getSellingPrice());
            vo.setPortraitUrl(user.getPortraitUrl());
            vo.setSerial(user.getSerialNo());
            list.add(vo);
            if (rankingListPO.getUserId().equals(user.getUserId())) {
                myLocation = user.getSerialNo();
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("myLocation", myLocation);
        return new BaseReturn("查询成功！", result);
    }


    /**
     * 土豪排行榜
     *
     * @param rankingListPO
     * @return
     */
    @ApiOperation(value = "土豪排行榜", notes = "请求参数说明 [userId 用户ID] ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明 [serial 序号][userId 用户ID] [userName 用户名字] " +
                    "[portraitUrl 头像url][amount 金币数量]  [myLocation 我的排名 （等于0表示未上榜）]")
    })
    @PostMapping("/moneyCountRankingList")
    public BaseReturn moneyCountRankingList(@RequestBody RankingListPO rankingListPO) {
        int myLocation = 0;
        List<RankingListVO> list = new ArrayList<>();
        List<UserRankingVO> users = userService.findForRankingListAboutMoney(GoodsConstant.GOODS_MONEY, RanklingListConstant.MONEY_RANKING_SHOW_COUNT);
        for (UserRankingVO user : users) {
            RankingListVO vo = new RankingListVO();
            vo.setUserId(user.getUserId());
            vo.setUserName(user.getName());
            vo.setAmount(user.getAmount());
            vo.setPortraitUrl(user.getPortraitUrl());
            vo.setSerial(user.getSerialNo());
            list.add(vo);
            if (rankingListPO.getUserId().equals(user.getUserId())) {
                myLocation = user.getSerialNo();
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("myLocation", myLocation);
        return new BaseReturn("查询成功！", result);
    }


    /**
     * 等级排行总榜
     *
     * @param rankingListPO
     * @return
     */
    @ApiOperation(value = "等级排行总榜", notes = "请求参数说明 [userId 用户ID] ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明 [serial 序号][userId 用户ID] [userName 用户名字] " +
                    "[portraitUrl 头像url][sex 1为男性，2为女性] [roleLevel 用户等级] [myLocation 我的排名 （等于0表示未上榜）]")
    })
    @PostMapping("/roleLevelCountRankingList")
    public BaseReturn roleLevelCountRankingList(@RequestBody RankingListPO rankingListPO) {
        int myLocation = 0;
        List<Object> objects = redisService.lRange(RanklingListConstant.ROLE_LEVEL_REDIS, 0, RanklingListConstant.ROLE_LEVEL_RANKING_SHOW_COUNT);
        if (objects != null && objects.size() > 0) {
            for (Object object : objects) {
                UserRankingVO userRankingVO = (UserRankingVO) object;
                if (userRankingVO.getUserId().equals(rankingListPO.getUserId())) {
                    myLocation = userRankingVO.getSerialNo();
                    break;
                }
            }
            Map map = new HashMap();
            map.put("list", objects);
            map.put("myLocation", myLocation);
            return new BaseReturn("查询成功！", map);
        } else {
            List<UserRankingVO> userRankingVOS = userService.findForRankingListAboutRoleLevel(RanklingListConstant.ROLE_LEVEL_RANKING_SHOW_COUNT);
            for (UserRankingVO userRankingVO : userRankingVOS) {
                if (userRankingVO.getUserId().equals(rankingListPO.getUserId())) {
                    myLocation = userRankingVO.getSerialNo();
                }
                redisService.lPush(RanklingListConstant.ROLE_LEVEL_REDIS, userRankingVO);
            }
            Map map = new HashMap();
            map.put("list", userRankingVOS);
            map.put("myLocation", myLocation);
            return new BaseReturn("查询成功！", map);
        }
    }

    /**
     * 魅力值排行总榜
     *
     * @param rankingListPO
     * @return
     */
    @ApiOperation(value = "魅力值排行总榜", notes = "请求参数说明 [userId 用户ID] ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明 [serialNo 序号][userId 用户ID] [userName 用户名字] " +
                    "[portraitUrl 头像url][sex 1为男性，2为女性] [charmNum 魅力值] [myLocation 我的排名 （等于0表示未上榜）]")
    })
    @PostMapping("/charmNumCountRankingList")
    public BaseReturn charmNumCountRankingList(@RequestBody RankingListPO rankingListPO) {
        int myLocation = 0;
        List<Object> objects = redisService.lRange(RanklingListConstant.CHARM_NUM_REDIS, 0, RanklingListConstant.CHARM_NUM_RANKING_SHOW_COUNT);
        if (objects != null && objects.size() > 0) {
            for (Object object : objects) {
                UserRankingVO userRankingVO = (UserRankingVO) object;
                if (userRankingVO.getUserId().equals(rankingListPO.getUserId())) {
                    myLocation = userRankingVO.getSerialNo();
                    break;
                }
            }
            Map map = new HashMap();
            map.put("myLocation", myLocation);
            map.put("list", objects);
            return new BaseReturn("查询成功！", map);
        } else {
            log.info("更新魅力榜");
            List<UserRankingVO> userRankingVOS = userService.findForRankingListAboutCharmNum(RanklingListConstant.CHARM_NUM_RANKING_SHOW_COUNT);
            for (UserRankingVO userRankingVO : userRankingVOS) {
                if (userRankingVO.getUserId().equals(rankingListPO.getUserId())) {
                    myLocation = userRankingVO.getSerialNo();
                }
                if (userRankingVO.getSerialNo() == 1) {
                    log.info(userRankingVO.getUserId() + "：获取魅力榜第一");
                    userRoleService.addRoleTitle(RoleTitleConstant.TITLE_5, userRankingVO.getUserId());
                }
                redisService.lPush(RanklingListConstant.CHARM_NUM_REDIS, userRankingVO);
            }
            Map map = new HashMap();
            map.put("list", userRankingVOS);
            map.put("myLocation", myLocation);
            return new BaseReturn("查询成功！", map);
        }
    }

    /**
     * 战斗力排行总榜
     *
     * @param rankingListPO
     * @return
     */
    @ApiOperation(value = "战斗力排行总榜", notes = "请求参数说明 [userId 用户ID] ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明 [serialNo 序号][userId 用户ID] [userName 用户名字] " +
                    "[portraitUrl 头像url][sex 1为男性，2为女性] [alwaysFighting 战斗力] [myLocation 我的排名 （等于0表示未上榜）]")
    })
    @PostMapping("/fightingCountRankingList")
    public BaseReturn fightingCountRankingList(@RequestBody RankingListPO rankingListPO) {
        int myLocation = 0;
        List<Object> objects = redisService.lRange(RanklingListConstant.FIGHTING_REDIS, 0, RanklingListConstant.FIGHTING_RANKING_SHOW_COUNT);
        if (objects != null && objects.size() > 0) {
            for (Object object : objects) {
                UserRankingVO userRankingVO = (UserRankingVO) object;
                if (userRankingVO.getUserId().equals(rankingListPO.getUserId())) {
                    myLocation = userRankingVO.getSerialNo();
                    break;
                }
            }
            Map<String, Object> map = new HashMap();
            map.put("list", objects);
            map.put("myLocation", myLocation);
            return new BaseReturn("查询成功！", map);
        } else {
            log.info("更新战斗力榜");
            List<UserRankingVO> userRankingVOS = userService.findForRankingListAboutAlwaysFighting(RanklingListConstant.FIGHTING_RANKING_SHOW_COUNT);
            for (UserRankingVO userRankingVO : userRankingVOS) {
                if (userRankingVO.getUserId().equals(rankingListPO.getUserId())) {
                    myLocation = userRankingVO.getSerialNo();
                }
                if (userRankingVO.getSerialNo() == 1) {
                    log.info(userRankingVO.getUserId() + "：获取战斗力第一");
                    userRoleService.addRoleTitle(RoleTitleConstant.TITLE_5, userRankingVO.getUserId());
                }
                redisService.lPush(RanklingListConstant.FIGHTING_REDIS, userRankingVO);
            }
            Map map = new HashMap();
            map.put("list", userRankingVOS);
            map.put("myLocation", myLocation);
            return new BaseReturn("查询成功！", map);
        }
    }

    /**
     * 排行月榜
     *
     * @param rankingListPO
     * @return
     */
    @ApiOperation(value = "排行月榜", notes = "请求参数说明 [userId 用户ID] [type 战斗力 1  魅力月榜 2]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明 [serialNo 序号][userId 用户ID] [name 用户名字] " +
                    "[portraitUrl 头像url][sex 1为男性，2为女性] [promote 提升数值] [myLocation 我的排名 （等于0表示未上榜）]")
    })
    @PostMapping("/fightingMonthRankingList")
    public BaseReturn fightingMonthRankingList(@RequestBody RankingListPO rankingListPO) {
        int myLocation = 0;
        if (rankingListPO.getType() == RanklingListConstant.MONTH_FIGHTING_TYPE) {
            List<Object> objects = redisService.lRange(RanklingListConstant.MONTH_FIGHTING_REDIS, 0, RanklingListConstant.MONTH_FIGHTING_RANKING_SHOW_COUNT);
            if (objects != null && objects.size() > 0) {
                a:
                for (Object object : objects) {
                    UserRankingVO userRankingVO = (UserRankingVO) object;
                    if (userRankingVO.getUserId().equals(rankingListPO.getUserId())) {
                        myLocation = userRankingVO.getSerialNo();
                        break a;
                    }
                }
                Map<String, Object> map = new HashMap();
                map.put("myLocation", myLocation);
                map.put("list", objects);
                return new BaseReturn("查询成功！", map);
            } else {
                List<UserRankingVO> userRankingVOS = userCrunchiesService.fightingMonthRankingList(RanklingListConstant.MONTH_FIGHTING_RANKING_SHOW_COUNT, rankingListPO.getType());
                for (UserRankingVO userRankingVO : userRankingVOS) {
                    if (userRankingVO.getUserId().equals(rankingListPO.getUserId())) {
                        myLocation = userRankingVO.getSerialNo();
                    }
                    redisService.lPush(RanklingListConstant.MONTH_FIGHTING_REDIS, userRankingVO);
                }
                Map map = new HashMap();
                map.put("list", userRankingVOS);
                map.put("myLocation", myLocation);
                return new BaseReturn("查询成功！", map);
            }
        } else if (rankingListPO.getType() == RanklingListConstant.MONTH_CHARM_NUM_TYPE) {
            List<Object> objects = redisService.lRange(RanklingListConstant.MONTH_CHARM_NUM_REDIS, 0, RanklingListConstant.MONTH_CHARM_NUM_RANKING_SHOW_COUNT);
            if (objects != null && objects.size() > 0) {
                for (Object object : objects) {
                    UserRankingVO userRankingVO = (UserRankingVO) object;
                    if (userRankingVO.getUserId().equals(rankingListPO.getUserId())) {
                        myLocation = userRankingVO.getSerialNo();
                        break;
                    }
                }
                Map<String, Object> map = new HashMap();
                map.put("myLocation", myLocation);
                map.put("list", objects);
                return new BaseReturn("查询成功！", map);
            } else {
                List<UserRankingVO> userRankingVOS = userCrunchiesService.fightingMonthRankingList(RanklingListConstant.MONTH_FIGHTING_RANKING_SHOW_COUNT, rankingListPO.getType());
                for (UserRankingVO userRankingVO : userRankingVOS) {
                    if (userRankingVO.getUserId().equals(rankingListPO.getUserId())) {
                        myLocation = userRankingVO.getSerialNo();
                    }
                    redisService.lPush(RanklingListConstant.MONTH_CHARM_NUM_REDIS, userRankingVO);
                }
                Map map = new HashMap();
                map.put("list", userRankingVOS);
                map.put("myLocation", myLocation);
                return new BaseReturn("查询成功！", map);
            }
        } else if (rankingListPO.getType() == RanklingListConstant.MONTH_ZS_NUM_TYPE) {
            List<Object> objects = redisService.lRange(RanklingListConstant.MONTH_ZS_REDIS, 0, RanklingListConstant.MONTH_ZS_RANKING_SHOW_COUNT);
            if (objects != null && objects.size() > 0) {
                for (Object object : objects) {
                    UserRankingVO userRankingVO = (UserRankingVO) object;
                    if (userRankingVO.getUserId().equals(rankingListPO.getUserId())) {
                        myLocation = userRankingVO.getSerialNo();
                        break;
                    }
                }
                Map<String, Object> map = new HashMap();
                map.put("myLocation", myLocation);
                map.put("list", objects);
                return new BaseReturn("查询成功！", map);
            } else {
                List<UserRankingVO> userRankingVOS = userCrunchiesService.fightingMonthRankingList(RanklingListConstant.MONTH_ZS_RANKING_SHOW_COUNT, rankingListPO.getType());
                for (UserRankingVO userRankingVO : userRankingVOS) {
                    if (userRankingVO.getUserId().equals(rankingListPO.getUserId())) {
                        myLocation = userRankingVO.getSerialNo();
                    }
                    redisService.lPush(RanklingListConstant.MONTH_ZS_REDIS, userRankingVO);
                }
                Map map = new HashMap();
                map.put("list", userRankingVOS);
                map.put("myLocation", myLocation);
                return new BaseReturn("查询成功！", map);
            }
        }
        return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误");
    }


    /**
     * 宠物拥有数量排行总榜
     *
     * @param rankingListPO
     * @return
     */
    @ApiOperation(value = "宠物拥有数量排行总榜", notes = "请求参数说明 [userId 用户ID] ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明 [serial 序号][userId 用户ID] [userName 用户名字] " +
                    "[portraitUrl 头像url][sex 1为男性，2为女性] [roleLevel 用户等级] [myLocation 我的排名 （等于0表示未上榜）]")
    })
    @PostMapping("/petCountRankingList")
    public BaseReturn petCountRankingList(@RequestBody RankingListPO rankingListPO) {
        int myLocation = 0;
        List<Object> objects = redisService.lRange(RanklingListConstant.USER_PET_REDIS, 0, RanklingListConstant.USER_PET_RANKING_SHOW_COUNT);
        if (objects != null && objects.size() > 0) {
            for (Object object : objects) {
                UserRankingVO userRankingVO = (UserRankingVO) object;
                if (userRankingVO.getUserId().equals(rankingListPO.getUserId())) {
                    myLocation = userRankingVO.getSerialNo();
                    break;
                }
            }
            Map map = new HashMap();
            map.put("list", objects);
            map.put("myLocation", myLocation);
            return new BaseReturn("查询成功！", map);
        } else {
            List<UserRankingVO> userRankingVOS = userService.findForRankingListAboutPet(RanklingListConstant.USER_PET_RANKING_SHOW_COUNT);
            for (UserRankingVO userRankingVO : userRankingVOS) {
                if (userRankingVO.getUserId().equals(rankingListPO.getUserId())) {
                    myLocation = userRankingVO.getSerialNo();
                }
                redisService.lPush(RanklingListConstant.USER_PET_REDIS, userRankingVO);
            }
            Map map = new HashMap();
            map.put("list", userRankingVOS);
            map.put("myLocation", myLocation);
            return new BaseReturn("查询成功！", map);
        }
    }

}