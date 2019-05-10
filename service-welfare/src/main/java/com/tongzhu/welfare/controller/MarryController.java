package com.tongzhu.welfare.controller;

import com.alibaba.fastjson.JSONObject;
import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.common.Pager;
import com.tongzhu.welfare.constant.MarryConstant;
import com.tongzhu.welfare.domain.User;
import com.tongzhu.welfare.mapper.EngagementLogMapper;
import com.tongzhu.welfare.model.EngagementLog;
import com.tongzhu.welfare.model.EngagementLogExample;
import com.tongzhu.welfare.model.vo.LoveInfoVo;
import com.tongzhu.welfare.model.vo.LoveTreeInfoVo;
import com.tongzhu.welfare.model.vo.MarryLogVo;
import com.tongzhu.welfare.model.vo.WeddingLogVo;
import com.tongzhu.welfare.po.MarryPo;
import com.tongzhu.welfare.service.MarryService;
import com.tongzhu.welfare.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 结婚系统相关Controller
 *
 * @author 吴恒斌
 * @date 2018年11月30日
 */
@Api(value = "结婚系统controller", tags = {"结婚系统API"})
@RestController
@RequestMapping(value = "/building")
public class MarryController {

//    private static Logger log = LoggerFactory.getLogger(MarryController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private MarryService marryService;
    @Autowired
    private EngagementLogMapper engagementLogMapper;

    /**
     * 发起订婚请求
     *
     * @param biuldingPO
     * @return
     */
    @ApiOperation(value = "发起订婚请求", notes = "请求参数说明 [userId 用户ID] [otherUserId 被求婚人ID]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明[result 返回结果：0请求成功，-1不是好友，-2不是异性，-3亲密度不够，-4没有求婚戒指,"
                    + "-5双方中存在已婚人士，发起订婚请求失败！-6不能在三天内重复发起或者接受求婚0")
    })
    @PostMapping("/engagementByUserId")
    public BaseReturn engagementByUserId(@RequestBody MarryPo marryPo) {
        if (StringUtils.isBlank(marryPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (StringUtils.isBlank(marryPo.getOtherUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "被求婚人ID为空！");
        String userId = marryPo.getUserId();
        String otherUserId = marryPo.getOtherUserId();
        User userDetail = userService.findByUserId(userId);
        User userDetail2 = userService.findByUserId(otherUserId);
        if (userDetail != null) {
            if (userDetail2 != null) {
                //需要判断是否是好友，然后亲密度是否达到要求，背包里是否有戒指，然后进行角色双方性别判断，不支持同性结婚。
                //满足上述发起系统通知
                int i = marryService.engagementByUserId(userId, otherUserId);
                Map<String, Integer> map = new HashMap<>();
                map.put("result", i);
                if (i == -1) {//-1 不是好友，-2不是同性，-3亲密度不满足，-4没有戒指
                    return new BaseReturn("对方不是好友，发起订婚请求失败！", map);
                } else if (i == -2) {
                    return new BaseReturn("不是异性，发起订婚请求失败！", map);
                } else if (i == -3) {
                    return new BaseReturn("亲密度不够，发起订婚请求失败！", map);
                } else if (i == -4) {
                    return new BaseReturn("背包里没有求婚戒指，发起订婚请求失败！", map);
                } else if (i == -5) {
                    return new BaseReturn("对方已婚人士，发起订婚请求失败！", map);
                } else if (i == -6) {
                    return new BaseReturn("双方中存在已婚人士，发起订婚请求失败！", map);
                } else if (i == -7) {
                    return new BaseReturn("对方不在线！", map);
                } else if (i == -8) {
                    return new BaseReturn("刚离婚，三天内不能再次结婚", map);
                } else if (i == -9) {
                    return new BaseReturn("对方刚离婚，三天内不能再次结婚", map);
                } else {
                    return new BaseReturn("发起订婚请求成功！", map);
                }
            } else {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未查询到该名求婚对象的玩家信息，请重新输入ID！");
            }
        } else {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未查询到该名玩家信息，请重新输入ID！");
        }
    }

    /**
     * 处理订婚请求
     *
     * @param biuldingPO
     * @return
     */
    @ApiOperation(value = "处理订婚请求", notes = "请求参数说明 [userId 用户ID] [otherUserId 被求婚人ID][engagementLogId 订婚ID][type 求婚处理类型: 1：接受求婚；2：拒绝求婚]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明[result 返回结果：0请求成功，-1缺少求婚戒指，1拒绝求婚]")
    })
    @PostMapping("/receiveEngagementByUserId")
    public BaseReturn receiveEngagementByUserId(@RequestBody MarryPo marryPo) {
        if (StringUtils.isBlank(marryPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (StringUtils.isBlank(marryPo.getOtherUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "被求婚人ID为空！");
        if (marryPo.getType() == null || marryPo.getType() == 0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "求婚处理类型错误！");
        if (StringUtils.isBlank(marryPo.getEngagementLogId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "订婚ID为空！");

        String userId = marryPo.getUserId();
        User userDetail = userService.findByUserId(userId);
        String otherUserId = marryPo.getOtherUserId();
        String engagementLogId = marryPo.getEngagementLogId();
        int type = marryPo.getType();
        if (userDetail != null) {
            //需要判断发起人是否还有求婚戒指
            int i = marryService.receiveEngagementByUserId(engagementLogId, userId, otherUserId, type);
            if (i == 0) {
                return new BaseReturn("接受订婚请求！");
            } else if (i == -1) {//缺少求婚戒指
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "缺少求婚戒指！");
            } else if (i == -2) {//缺少求婚戒指
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "暂时无法再次订婚");
            } else {
                return new BaseReturn("拒绝求婚成功！");
            }
        } else {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "系统繁忙，请重试！");
        }
    }

    /**
     * 获取当前教堂信息
     *
     * @param MarryPo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取当前教堂信息", notes = "请求参数说明 [userId 用户ID][churchType  1：当前婚礼,2：举办婚礼,3：气球巡游]", response = MarryLogVo.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明[marryLuxuryList 豪华类型婚礼][marryRomanceList 浪漫类型婚礼][marryGeneralList 普通类型婚礼]")
    })
    @PostMapping("/getMarryInfo")
    public BaseReturn getMarryInfo(@RequestBody MarryPo marryPo) throws Exception {
        if (StringUtils.isBlank(marryPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (marryPo.getChurchType() == null || marryPo.getChurchType() == 0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "教堂类型为空！");

        String userId = marryPo.getUserId();
        User userDetail = userService.findByUserId(userId);
        int churchType = marryPo.getChurchType();
        try {
            if (userDetail != null) {
                JSONObject map = marryService.getMarryInfo(userId, churchType);
                return new BaseReturn("获取当前教堂信息！", map);
            } else {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "教堂数据错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, e.getMessage());
        }
    }

    /**
     * 取消订婚
     *
     * @param MarryPo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "取消订婚", notes = "请求参数说明 [userId 用户ID][userRingId 用户求婚戒指ID]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明[marryLuxuryList 豪华类型婚礼][marryRomanceList 浪漫类型婚礼][marryGeneralList 普通类型婚礼]")
    })
    @PostMapping("/cancleEngagementByUserId")
    public BaseReturn cancleEngagementByUserId(@RequestBody MarryPo marryPo) {
        if (StringUtils.isBlank(marryPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (StringUtils.isBlank(marryPo.getUserRingId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户求婚戒指ID为空！");
        String userId = marryPo.getUserId();
        String userRingId = marryPo.getUserRingId();
        User userDetail = userService.findByUserId(userId);
        try {
            if (userDetail != null) {
                //取出个人信息进行判断，是否有订婚成功记录，查询是否有结婚记录，以及结婚的类型是否是豪华婚礼，
                int i = marryService.cancleEngagementByUserId(userId, userRingId);
                return new BaseReturn("摧毁婚戒成功！", i);
            } else {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未查询到该名玩家信息，请重新输入ID！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "系统繁忙，请重试！");
        }
    }

    /**
     * 获取用户情缘信息
     *
     * @param MarryPo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取用户情缘信息", notes = "请求参数说明 [userId 用户ID] [engagementId 订婚ID]", response = LoveInfoVo.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明")
    })
    @PostMapping("/getLoveInfoByUserId")
    public BaseReturn getLoveInfoByUserId(@RequestBody MarryPo marryPo) {
        if (StringUtils.isBlank(marryPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (StringUtils.isBlank(marryPo.getEngagementId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "订婚ID为空！");
        String userId = marryPo.getUserId();
        User userDetail = userService.findByUserId(userId);
        String engagementId = marryPo.getEngagementId();
        try {
            if (userDetail != null) {
                JSONObject LoveInfoVo = marryService.getLoveInfoByUserId(userId, engagementId);
                return new BaseReturn("获取用户情缘信息成功！", LoveInfoVo);
            } else {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未查询到该名玩家信息，请重新输入ID！");
            }
        } catch (Exception e) {
            //e.printStackTrace();
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "系统繁忙，请重试！");
        }
    }

    /**
     * 获取婚礼记录信息
     *
     * @param MarryPo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取婚礼记录信息", notes = "请求参数说明 [userId 用户ID][pageNum 页码][pageSize 每页大小]", response = WeddingLogVo.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明[Pager<MarryLog> list]")
    })
    @PostMapping("/getMarryInfoLog")
    public BaseReturn getMarryInfoLog(@RequestBody MarryPo marryPo) {
        if (StringUtils.isBlank(marryPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (marryPo.getPageNum() == 0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "页码为0！");
        if (marryPo.getPageSize() == 0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "每页大小为0!");

        String userId = marryPo.getUserId();
        User userDetail = userService.findByUserId(userId);
        int pageNum = marryPo.getPageNum();
        int pageSize = marryPo.getPageSize();
        try {
            if (userDetail != null) {
                String otherName = "";
                JSONObject json = new JSONObject();
                //获取订婚ID,用这个来判断是否是订婚状态还是结婚状态
                EngagementLogExample engagementLogExample = new EngagementLogExample();
                EngagementLogExample.Criteria criteria = engagementLogExample.createCriteria();
                EngagementLogExample.Criteria criteria2 = engagementLogExample.createCriteria();
                EngagementLogExample.Criteria criteria3 = engagementLogExample.createCriteria();
                EngagementLogExample.Criteria criteria4 = engagementLogExample.createCriteria();
                criteria.andUserIdEqualTo(userId).andMarryStatusEqualTo(MarryConstant.MARRY_SUCCESS);
                criteria2.andOtherIdEqualTo(userId).andMarryStatusEqualTo(MarryConstant.MARRY_SUCCESS);
                criteria3.andUserIdEqualTo(userId).andMarryStatusEqualTo(MarryConstant.MARRY_TRUE);
                criteria4.andOtherIdEqualTo(userId).andMarryStatusEqualTo(MarryConstant.MARRY_TRUE);
                engagementLogExample.or(criteria2);
                engagementLogExample.or(criteria3);
                engagementLogExample.or(criteria4);
                engagementLogExample.setOrderByClause(" created_time desc");
                List<EngagementLog> engagementLogList = engagementLogMapper.selectByExample(engagementLogExample);
                String engagementLogId = new String();
                if (engagementLogList.size() > 0) {
                    String otherId = "";
                    EngagementLog engagementLog = engagementLogList.get(0);
                    engagementLogId = engagementLog.getId();
                    if (userId.equals(engagementLog.getUserId())) {
                        otherId = engagementLog.getOtherId();
                    } else {
                        otherId = engagementLog.getUserId();
                    }
                    User otherUser = userService.findByUserId(otherId);
                    otherName = otherUser.getName();
                    //用户当前状态为订婚值：1；结婚值：2；
                    json.put("type", engagementLog.getMarryStatus() == 1 ? 1 : 2);
                }
                Pager<WeddingLogVo> list = marryService.getMarryInfoLog(userId, pageNum, pageSize);
                json.put("engagementLogId", engagementLogId);
                json.put("otherName", otherName);
                json.put("WeddingLogVoList", list);
                return new BaseReturn("查询数据成功！", json);
            } else {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未查询到该名玩家信息，请重新输入ID！");
            }
        } catch (Exception e) {
            //e.printStackTrace();
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "系统繁忙，请重试！");
        }
    }

    /**
     * 举行婚礼
     *
     * @param marryPo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据userId举行婚礼", notes = "请求参数说明 [userId 用户ID][marryType 婚礼类型 1：普通婚礼；2：浪漫婚礼；3：豪华婚礼；4：豪华预约婚礼 ][marryDate 豪华预约婚礼开始时间]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回说明")
    })
    @PostMapping("/beginMarry")
    public BaseReturn beginMarry(@RequestBody MarryPo marryPo) {
        if (StringUtils.isBlank(marryPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (marryPo.getMarryType() == null || marryPo.getMarryType() == 0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "婚礼类型为0！");
        if (marryPo.getMarryType() == MarryConstant.MARRY_TYPE_LUXURY_BOOK && marryPo.getMarryDate() == null) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "时间异常");
        }
        //TODO 需要添加返回的消耗数值
        String userId = marryPo.getUserId();
        User userDetail = userService.findByUserId(userId);
        int marryType = marryPo.getMarryType();
        Long marryDate = marryPo.getMarryDate();
        try {
            if (userDetail != null) {
                JSONObject i = marryService.beginMarry(userId, marryType, marryDate);
                return new BaseReturn("举行婚礼成功！", i);
            } else {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未查询到该名玩家信息，请重新输入ID！");
            }
        } catch (Exception e) {
            //e.printStackTrace();
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, e.getMessage());
        }
    }


    /**
     * 进行巡游
     *
     * @param marryPo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "进行巡游", notes = "请求参数说明 [userId 用户ID][cruiseType 巡游类型 1：普通巡游；2：浪漫巡游；3：豪华巡游]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回说明")
    })
    @PostMapping("/cruise")
    public BaseReturn cruise(@RequestBody MarryPo marryPo) {
        if (StringUtils.isBlank(marryPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (marryPo.getCruiseType() == null || marryPo.getCruiseType() == 0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "巡游类型为0！");
        //TODO 需要添加返回的消耗数值
        String userId = marryPo.getUserId();
        User userDetail = userService.findByUserId(userId);
        int cruiseType = marryPo.getCruiseType();
        try {
            if (userDetail != null) {
                JSONObject json = marryService.cruise(userId, cruiseType);
                return new BaseReturn("巡游成功！", json);
            } else {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未查询到该名玩家信息，请重新输入ID！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, e.getMessage());
        }
    }

    /**
     * 根据userId撒喜糖
     *
     * @param MarryPo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据userId撒喜糖", notes = "请求参数说明 [userId 用户ID][marryId 婚礼ID]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明")
    })
    @PostMapping("/andJoyful")
    public BaseReturn andJoyful(@RequestBody MarryPo marryPo) {
        if (StringUtils.isBlank(marryPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (StringUtils.isBlank(marryPo.getMarryId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "婚礼ID为空！");

        String userId = marryPo.getUserId();
        User userDetail = userService.findByUserId(userId);
        String marryId = marryPo.getMarryId();
        try {
            if (userDetail != null) {
                int i = marryService.andJoyful(userId, marryId);
                if (i == 0) {
                    return new BaseReturn("撒喜糖成功！");
                } else if (i == -2) {
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "参数错误！");
                } else {
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "免费撒喜糖次数已经用完请改用钻石撒喜糖！");
                }
            } else {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未查询到该名玩家信息，请重新输入ID！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "系统繁忙，请重试！");
        }
    }

    /**
     * 付费撒喜糖
     *
     * @param MarryPo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "付费撒喜糖", notes = "请求参数说明 [userId 用户ID][marryId 婚礼ID]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明")
    })
    @PostMapping("/diamondAndJoyful")
    public BaseReturn diamondAndJoyful(@RequestBody MarryPo marryPo) {
        if (StringUtils.isBlank(marryPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (StringUtils.isBlank(marryPo.getMarryId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "婚礼ID为空！");

        String userId = marryPo.getUserId();
        User userDetail = userService.findByUserId(userId);
        String marryId = marryPo.getMarryId();
        try {
            if (userDetail != null) {
                JSONObject json = marryService.buyAndJoyful(userId, marryId);
                if (json.getIntValue("code") == 0) {
                    return new BaseReturn("撒喜糖成功！", json.get("ReceiveGoldVo"));
                } else if (json.getIntValue("code") == -1) {
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "钻石不足，请充值后再试！");
                } else {
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "数据错误！");
                }
            } else {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未查询到该名玩家信息，请重新输入ID！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, e.getMessage());
        }
    }

    /**
     * 根据marryId进入别人的婚礼现场，限制20人
     *
     * @param MarryPo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据marryId进入别人的婚礼", notes = "请求参数说明 [userId 用户ID] [marryId 婚礼ID]", response = MarryLogVo.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明[marryLogVo 教堂婚礼信息对象]")
    })
    @PostMapping("/joinWedding")
    public BaseReturn joinWedding(@RequestBody MarryPo marryPo) {
        if (StringUtils.isBlank(marryPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (StringUtils.isBlank(marryPo.getMarryId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "婚礼ID为空！");

        String userId = marryPo.getUserId();
        User userDetail = userService.findByUserId(userId);
        String marryId = marryPo.getMarryId();
        try {
            if (userDetail != null) {
                JSONObject i = marryService.joinWedding(userId, marryId);
                return new BaseReturn("参加婚礼成功！", i);
            } else {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未查询到该名玩家信息，请重新输入ID！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, e.getMessage());
        }
    }

    /**
     * 被求婚时离开别人的婚礼
     *
     * @param MarryPo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "被求婚时离开别人的婚礼", notes = "请求参数说明 [userId 用户ID] [marryId 婚礼ID]", response = MarryLogVo.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明[marryLogVo 教堂婚礼信息对象]")
    })
    @PostMapping("/leaveWedding")
    public BaseReturn leaveWedding(@RequestBody MarryPo marryPo) {
        if (StringUtils.isBlank(marryPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (StringUtils.isBlank(marryPo.getMarryId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "婚礼ID为空！");

        String userId = marryPo.getUserId();
        User userDetail = userService.findByUserId(userId);
        String marryId = marryPo.getMarryId();
        try {
            if (userDetail != null) {
                marryService.leaveWedding(userId, marryId);
                return new BaseReturn("离开婚礼成功！");
            } else {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未查询到该名玩家信息，请重新输入ID！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "系统繁忙，请重试！");
        }
    }

    /**
     * 离开婚房
     *
     * @param MarryPo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "离开婚房", notes = "请求参数说明 [userId 用户ID][loveTreeId 爱情树ID]", response = MarryLogVo.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明")
    })
    @PostMapping("/leaveWeddingRoom")
    public BaseReturn leaveWeddingRoom(@RequestBody MarryPo marryPo) {
        if (StringUtils.isBlank(marryPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (StringUtils.isBlank(marryPo.getLoveTreeId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");

        String userId = marryPo.getUserId();
        String loveTreeId = marryPo.getLoveTreeId();
        User userDetail = userService.findByUserId(userId);
        try {
            if (userDetail != null) {
                marryService.leaveWeddingRoom(userId, loveTreeId);
                return new BaseReturn("离开婚礼成功！");
            } else {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未查询到该名玩家信息，请重新输入ID！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, e.getMessage());
        }
    }

    /**
     * 在婚礼进行时离开别人的婚礼
     *
     * @param MarryPo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "在婚礼进行时离开别人的婚礼", notes = "请求参数说明 [userId 用户ID] [marryId 婚礼ID]", response = MarryLogVo.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明[marryLogVo 教堂婚礼信息对象]")
    })
    @PostMapping("/leaveMarry")
    public BaseReturn leaveMarry(@RequestBody MarryPo marryPo) {
        if (StringUtils.isBlank(marryPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (StringUtils.isBlank(marryPo.getMarryId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "婚礼ID为空！");

        String userId = marryPo.getUserId();
        User userDetail = userService.findByUserId(userId);
        String marryId = marryPo.getMarryId();
        try {
            if (userDetail != null) {
                marryService.leaveMarry(userId, marryId);
                return new BaseReturn("离开婚礼成功！");
            } else {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未查询到该名玩家信息，请重新输入ID！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "系统繁忙，请重试！");
        }
    }

    /**
     * 发送祝福
     *
     * @param MarryPo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "发送祝福", notes = "请求参数说明 [userId 用户ID] [marryId 婚礼ID]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明")
    })
    @PostMapping("/sendWish")
    public BaseReturn sendWish(@RequestBody MarryPo marryPo) {
        if (StringUtils.isBlank(marryPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (StringUtils.isBlank(marryPo.getMarryId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "婚礼ID为空！");

        String userId = marryPo.getUserId();
        User userDetail = userService.findByUserId(userId);
        String marryId = marryPo.getMarryId();
        try {
            if (userDetail != null) {
                JSONObject i = marryService.sendWish(userId, marryId);
                if (i.getIntValue("code") == 0) {
                    return new BaseReturn("发送祝福成功！", i);
                } else {
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "发送祝福次数使用完，不能发送祝福！");
                }
            } else {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未查询到该名玩家信息，请重新输入ID！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, e.getMessage());
        }
    }

    /**
     * 敬酒
     *
     * @param MarryPo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "敬酒", notes = "请求参数说明 [userId 用户ID] [marryId 婚礼ID]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明")
    })
    @PostMapping("/toast")
    public BaseReturn toast(@RequestBody MarryPo marryPo) {
        if (StringUtils.isBlank(marryPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (StringUtils.isBlank(marryPo.getMarryId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "婚礼ID为空！");

        String userId = marryPo.getUserId();
        User userDetail = userService.findByUserId(userId);
        String marryId = marryPo.getMarryId();
        try {
            if (userDetail != null) {
                JSONObject i = marryService.toast(userId, marryId);
                if (i.getIntValue("code") == 0) {
                    return new BaseReturn("敬酒成功！", i);
                } else if (i.getIntValue("code") == -1) {
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "当前敬酒次数已满！");
                } else {
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "数据异常！");
                }
            } else {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未查询到该名玩家信息，请重新输入ID！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, e.getMessage());
        }
    }

    /**
     * 获取婚房界面信息
     *
     * @param MarryPo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取爱情树信息", notes = "请求参数说明 [userId 用户ID] [engagementId 订婚ID]", response = LoveTreeInfoVo.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明见model")
    })
    @PostMapping("/getLoveTreeInfo")
    public BaseReturn getLoveTreeInfo(@RequestBody MarryPo marryPo) {
        if (StringUtils.isBlank(marryPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");

        String userId = marryPo.getUserId();
        User userDetail = userService.findByUserId(userId);
        try {
            if (userDetail != null) {
                JSONObject i = marryService.getLoveTreeInfo(userId);
                if (i.getIntValue("code") == 0) {
                    return new BaseReturn("获取爱情树信息成功！", i);
                } else if (i.getIntValue("code") == -1) {
                    return new BaseReturn("未开启婚房！", i);
                } else {
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "数据异常！");
                }
            } else {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未查询到该名玩家信息，请重新输入ID！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "系统繁忙，请重试！");
        }
    }

    /**
     * 嬉闹
     *
     * @param MarryPo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "嬉闹", notes = "请求参数说明 [userId 用户ID] [engagementId 订婚ID]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明")
    })
    @PostMapping("/playJokes")
    public BaseReturn playJokes(@RequestBody MarryPo marryPo) {
        if (StringUtils.isBlank(marryPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (StringUtils.isBlank(marryPo.getEngagementId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "订婚ID为空！");

        String userId = marryPo.getUserId();
        User userDetail = userService.findByUserId(userId);
        String engagementId = marryPo.getEngagementId();
        try {
            if (userDetail != null) {
                JSONObject i = marryService.playJokes(userId, engagementId);
                return new BaseReturn("嬉闹成功！", i);
            } else {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未查询到该名玩家信息，请重新输入ID！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, e.getMessage());
        }
    }

    /**
     * 洞房
     *
     * @param MarryPo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "洞房", notes = "请求参数说明 [userId 用户ID] [engagementId 订婚ID]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明")
    })
    @PostMapping("/bridegroom")
    public BaseReturn bridegroom(@RequestBody MarryPo marryPo) {
        if (StringUtils.isBlank(marryPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (StringUtils.isBlank(marryPo.getEngagementId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "订婚ID为空！");

        String userId = marryPo.getUserId();
        User userDetail = userService.findByUserId(userId);
        String engagementId = marryPo.getEngagementId();
        try {
            if (userDetail != null) {
                JSONObject i = marryService.bridegroom(userId, engagementId);
                return new BaseReturn("洞房成功！", i);
            } else {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未查询到该名玩家信息，请重新输入ID！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, e.getMessage());
        }
    }

    /**
     * 升级情缘，只能升级结婚对应的情缘
     *
     * @param MarryPo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "升级情缘", notes = "请求参数说明 [userId 用户ID] [engagementId 订婚ID]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明")
    })
    @PostMapping("/updateLove")
    public BaseReturn updateLove(@RequestBody MarryPo marryPo) {
        if (StringUtils.isBlank(marryPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (StringUtils.isBlank(marryPo.getEngagementId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "订婚ID为空！");

        String userId = marryPo.getUserId();
        User userDetail = userService.findByUserId(userId);
        String engagementId = marryPo.getEngagementId();
        try {
            if (userDetail != null) {
                JSONObject json = marryService.updateLove(userId, engagementId);
                return new BaseReturn("升级情缘成功！", json);
            } else {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未查询到该名玩家信息，请重新输入ID！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, e.getMessage());
        }
    }

    /**
     * 离婚
     *
     * @param MarryPo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "离婚", notes = "请求参数说明 [userId 用户ID] [engagementId 订婚ID]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明")
    })
    @PostMapping("/divorce")
    public BaseReturn divorce(@RequestBody MarryPo marryPo) {
        if (StringUtils.isBlank(marryPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (StringUtils.isBlank(marryPo.getEngagementId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "订婚ID为空！");

        String userId = marryPo.getUserId();
        User userDetail = userService.findByUserId(userId);
        String engagementId = marryPo.getEngagementId();
        try {
            if (userDetail != null) {
                int i = marryService.divorce(userId, engagementId);
                if (i == 0) {
                    return new BaseReturn("离婚成功！");
                } else {
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "数据异常！");
                }
            } else {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未查询到该名玩家信息，请重新输入ID！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "系统繁忙，请重试！");
        }
    }

    /**
     * 爱情树升级
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "爱情树升级", notes = "请求参数说明 [userId 用户ID][engagementId 订婚ID]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明")
    })
    @PostMapping("/updateLoveTree")
    public BaseReturn updateLoveTree(@RequestBody MarryPo marryPo) {
        if (StringUtils.isBlank(marryPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (StringUtils.isBlank(marryPo.getEngagementId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "婚礼ID为空！");

        String userId = marryPo.getUserId();
        User userDetail = userService.findByUserId(userId);
        String engagementId = marryPo.getEngagementId();
        try {
            if (userDetail != null) {
                JSONObject i = marryService.updateLoveTree(userId, engagementId);
                return new BaseReturn("爱情树升级成功！", i);
            } else {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未查询到该名玩家信息，请重新输入ID！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, e.getMessage());
        }
    }

    /**
     * 爱情树突破
     *
     * @param MarryPo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "爱情树突破", notes = "请求参数说明 [userId 用户ID][engagementId 婚礼ID]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明")
    })
    @PostMapping("/breakLoveTree")
    public BaseReturn breakLoveTree(@RequestBody MarryPo marryPo) {
        if (StringUtils.isBlank(marryPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (StringUtils.isBlank(marryPo.getEngagementId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "婚礼ID为空！");

        String userId = marryPo.getUserId();
        User userDetail = userService.findByUserId(userId);
        String engagementId = marryPo.getEngagementId();
        try {
            if (userDetail != null) {
                JSONObject i = marryService.breakLoveTree(userId, engagementId);
                return new BaseReturn("爱情树突破成功！", i);
            } else {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未查询到该名玩家信息，请重新输入ID！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, e.getMessage());
        }
    }

}
