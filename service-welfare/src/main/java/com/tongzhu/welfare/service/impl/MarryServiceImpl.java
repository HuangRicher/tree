package com.tongzhu.welfare.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.tongzhu.constant.MessageConstant;
import com.tongzhu.util.DateUtil;
import com.tongzhu.util.ObjectUtil;
import com.tongzhu.welfare.controller.MarryController;
import com.tongzhu.welfare.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tongzhu.common.Pager;
import com.tongzhu.except.CheckException;
import com.tongzhu.util.CommonUtil;
import com.tongzhu.util.UUIDUtil;
import com.tongzhu.welfare.constant.BuildingConstant;
import com.tongzhu.welfare.constant.MarryConstant;
import com.tongzhu.welfare.enums.TreelovelvlEnum;
import com.tongzhu.welfare.mapper.BridegroomLogMapper;
import com.tongzhu.welfare.mapper.EngagementCancleLogMapper;
import com.tongzhu.welfare.mapper.EngagementLogMapper;
import com.tongzhu.welfare.mapper.LoveClassSettingMapper;
import com.tongzhu.welfare.mapper.LoveSettingMapper;
import com.tongzhu.welfare.mapper.LoveTreeInfoMapper;
import com.tongzhu.welfare.mapper.LoveTreeMapper;
import com.tongzhu.welfare.mapper.LoveTreeSettingMapper;
import com.tongzhu.welfare.mapper.MarryLogMapper;
import com.tongzhu.welfare.mapper.MarrySendWishSettingMapper;
import com.tongzhu.welfare.mapper.PlayJokesLogMapper;
import com.tongzhu.welfare.mapper.SendWishLogMapper;
import com.tongzhu.welfare.mapper.ToastLogMapper;
import com.tongzhu.welfare.mapper.WeddingLogMapper;
import com.tongzhu.welfare.mapper.ext.MarryLogExtMapper;
import com.tongzhu.welfare.mapper.ext.WeddingLogExtMapper;
import com.tongzhu.welfare.model.BridegroomLog;
import com.tongzhu.welfare.model.BridegroomLogExample;
import com.tongzhu.welfare.model.EngagementCancleLog;
import com.tongzhu.welfare.model.EngagementLog;
import com.tongzhu.welfare.model.EngagementLogExample;
import com.tongzhu.welfare.model.EngagementLogExample.Criteria;
import com.tongzhu.welfare.model.LoveClassSetting;
import com.tongzhu.welfare.model.LoveClassSettingExample;
import com.tongzhu.welfare.model.LoveSetting;
import com.tongzhu.welfare.model.LoveSettingExample;
import com.tongzhu.welfare.model.LoveTree;
import com.tongzhu.welfare.model.LoveTreeExample;
import com.tongzhu.welfare.model.LoveTreeInfo;
import com.tongzhu.welfare.model.LoveTreeInfoExample;
import com.tongzhu.welfare.model.LoveTreeSetting;
import com.tongzhu.welfare.model.LoveTreeSettingExample;
import com.tongzhu.welfare.model.MarryLog;
import com.tongzhu.welfare.model.MarryLogExample;
import com.tongzhu.welfare.model.MarrySendWishSetting;
import com.tongzhu.welfare.model.MarrySendWishSettingExample;
import com.tongzhu.welfare.model.PlayJokesLog;
import com.tongzhu.welfare.model.PlayJokesLogExample;
import com.tongzhu.welfare.model.SendWishLog;
import com.tongzhu.welfare.model.SendWishLogExample;
import com.tongzhu.welfare.model.ToastLog;
import com.tongzhu.welfare.model.ToastLogExample;
import com.tongzhu.welfare.model.WeddingLog;
import com.tongzhu.welfare.model.WeddingLogExample;
import com.tongzhu.welfare.model.vo.FriendDO;
import com.tongzhu.welfare.model.vo.LoveInfoVo;
import com.tongzhu.welfare.model.vo.LoveTreeInfoVo;
import com.tongzhu.welfare.model.vo.MarryLogVo;
import com.tongzhu.welfare.model.vo.WeddingLogVo;
import com.tongzhu.welfare.redis.DistributedLockCallback;
import com.tongzhu.welfare.redis.DistributedLockTemplate;
import com.tongzhu.welfare.redis.RedisService;
import com.tongzhu.welfare.service.BuildingService;
import com.tongzhu.welfare.service.FriendService;
import com.tongzhu.welfare.service.MarryService;
import com.tongzhu.welfare.service.MessageService;
import com.tongzhu.welfare.service.PetService;
import com.tongzhu.welfare.service.UserGoodsService;
import com.tongzhu.welfare.service.UserMessageService;
import com.tongzhu.welfare.service.UserRoleService;
import com.tongzhu.welfare.service.UserService;
import com.tongzhu.welfare.vo.ReceiveGoldVo;

import jodd.util.StringUtil;

@Service
public class MarryServiceImpl implements MarryService {

    @Autowired
    private FriendService friendService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserGoodsService userGoodsService;
    @Autowired
    private EngagementLogMapper engagementLogMapper;
    @Autowired
    private EngagementCancleLogMapper engagementCancleLogMapper;
    @Autowired
    private MarryLogMapper marryLogMapper;
    @Autowired
    private WeddingLogMapper weddingLogMapper;
    @Autowired
    private LoveTreeMapper loveTreeMapper;
    @Autowired
    private SendWishLogMapper sendWishLogMapper;
    @Autowired
    private ToastLogMapper toastLogMapper;
    @Autowired
    private PlayJokesLogMapper playJokesLogMapper;
    @Autowired
    private BridegroomLogMapper bridegroomLogMapper;
    @Autowired
    private LoveTreeInfoMapper loveTreeInfoMapper;
    @Autowired
    private LoveSettingMapper loveSettingMapper;
    @Autowired
    private LoveTreeSettingMapper loveTreeSettingMapper;
    @Autowired
    private MarryLogExtMapper marryLogExtMapper;
    @Autowired
    private WeddingLogExtMapper weddingLogExtMapper;
    @Autowired
    private UserMessageService userMessageService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private PetService petService;
    @Autowired
    private LoveClassSettingMapper loveClassSettingMapper;
    @Autowired
    private RedisService redisService;
    @Autowired
    private BuildingService buildingService;
    @Resource
    DistributedLockTemplate distributedLockTemplate;
    @Autowired
    private MarrySendWishSettingMapper marrySendWishSettingMapper;

    private static Logger log = LoggerFactory.getLogger(MarryServiceImpl.class);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int engagementByUserId(String userId, String otherUserId) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(System.currentTimeMillis()));
        cal.add(Calendar.DAY_OF_YEAR, -3);
        Date beforDay = cal.getTime();
        //双方3天内是否有发起求婚或者答应求婚的记录
        EngagementLogExample engagementLogExample = new EngagementLogExample();
        Criteria criteria = engagementLogExample.createCriteria();
        Criteria criteria1 = engagementLogExample.createCriteria();
        criteria.andCreatedTimeGreaterThan(beforDay).andUserIdEqualTo(userId).andMarryStatusNotEqualTo(MarryConstant.MARRY_DECIDED).andMarryStatusNotEqualTo(MarryConstant.MARRY_FALSE);
        criteria1.andCreatedTimeGreaterThan(beforDay).andOtherIdEqualTo(userId).andMarryStatusNotEqualTo(MarryConstant.MARRY_DECIDED).andMarryStatusNotEqualTo(MarryConstant.MARRY_FALSE);
        engagementLogExample.or(criteria1);
        List<EngagementLog> engagementLogList = engagementLogMapper.selectByExample(engagementLogExample);
        if (engagementLogList.size() > 0) {
            return -8;
        }
        EngagementLogExample otherEngagementLogExample = new EngagementLogExample();
        Criteria criteria3 = otherEngagementLogExample.createCriteria();
        Criteria criteria4 = otherEngagementLogExample.createCriteria();
        criteria3.andCreatedTimeGreaterThan(beforDay).andUserIdEqualTo(otherUserId).andMarryStatusNotEqualTo(MarryConstant.MARRY_DECIDED).andMarryStatusNotEqualTo(MarryConstant.MARRY_FALSE);
        criteria4.andCreatedTimeGreaterThan(beforDay).andOtherIdEqualTo(otherUserId).andMarryStatusNotEqualTo(MarryConstant.MARRY_DECIDED).andMarryStatusNotEqualTo(MarryConstant.MARRY_FALSE);
        otherEngagementLogExample.or(criteria4);
        List<EngagementLog> otherEngagementLogList = engagementLogMapper.selectByExample(otherEngagementLogExample);
        if (otherEngagementLogList.size() > 0) {
            return -9;
        }

        User user = userService.findByUserId(otherUserId);
        if (user != null && user.getStatus() == 0) {
            // 直接通知a 对方不在线
            JSONObject json = new JSONObject();
            json.put("type", "marryResult");
            json.put("code", 0);
            json.put("result", 2);
            messageService.sendMessageToSomeBody(otherUserId, json.toString());
            return -7;
        }

        Friend friend = friendService.checkIsMyFriend(userId, otherUserId);
        if (friend == null) {//判断是否是好友
            return -1;
        } else {
            int intimacy = friendService.getIntimacy(userId, otherUserId);
            if (intimacy >= MarryConstant.MARRY_CONDITIONS_INTIMACY) {//判断是否亲密度大于100
                UserRole friends = userRoleService.getUserRoleByUserId(otherUserId);
                UserRole userRole = userRoleService.getUserRoleByUserId(userId);
                String otherUserName = friends.getUserName();
                String userName = userRole.getUserName();
                if (friends.getSex() == userRole.getSex()) {
                    //判断是否是同性别
                    return -2;
                } else if (!StringUtils.isEmpty(friends.getSpouse()) || !StringUtils.isEmpty(userRole.getSpouse())) {
                    return -5;
                } else {//判断是否背包里有求婚戒指，先看是否有二级戒指，若没有再判断是否有一级戒指
                    Boolean ring = false;
                    List<GeneralityGoods> userRingList = userGoodsService.queryGoodsWeddingRing(userId);
                    if (userRingList.size() > 0) {
                        for (GeneralityGoods generalityGoods : userRingList) {
                            if (generalityGoods.getId().toString().equals(MarryConstant.MARRY_RING_ROMANCE)) {
                                dealEngagementByUserId(userId, otherUserId, generalityGoods.getId().toString(), userName, otherUserName);
                                ring = true;
                                break;
                            } else if (generalityGoods.getId().toString().equals(MarryConstant.MARRY_RING_GENERAL)) {
                                dealEngagementByUserId(userId, otherUserId, generalityGoods.getId().toString(), userName, otherUserName);
                                ring = true;
                                break;
                            }
                        }
                        if (ring) {
                            return 0;
                        } else {
                            return -4;
                        }
                    } else {
                        return -4;
                    }
                }
            } else {
                return -3;
            }
        }
    }

    /**
     * 处理订婚数据
     *
     * @param userId
     * @param otherUserId
     */
    @Transactional(rollbackFor = Exception.class)
    public void dealEngagementByUserId(String userId, String otherUserId, String ringId, String userName, String otherUserName) {
        //增加订婚记录
        EngagementLog engagementLog = new EngagementLog();
        String engagementLogId = UUIDUtil.generateUUID();
        engagementLog.setId(engagementLogId);
        engagementLog.setUserId(userId);
        engagementLog.setOtherId(otherUserId);
        engagementLog.setCreatedTime(new Date(System.currentTimeMillis()));
        engagementLog.setUserRingId(ringId);
        engagementLog.setOtherRingId(ringId);
        engagementLog.setMarryStatus(MarryConstant.MARRY_DECIDED);
        engagementLogMapper.insert(engagementLog);

        JSONObject json = new JSONObject();
        json.put("type", "marry");
        json.put("ring", ringId);
        json.put("userName", otherUserName);
        json.put("code", 0);
        json.put("userId", userId);
        json.put("engagementLogId", engagementLogId);
        messageService.sendMessageToSomeBody(otherUserId, json.toString());

        redisService.add(MarryConstant.MARRY_ENGAGEMENT_LIST, otherUserId);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int receiveEngagementByUserId(String engagementLogId, String userId, String otherUserId, int type) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(System.currentTimeMillis()));
        cal.add(Calendar.DAY_OF_YEAR, -3);
        Date beforDay = cal.getTime();
        synchronized (userId) {
            //双方3天内是否有发起求婚或者答应求婚的记录,
            EngagementLogExample engagementLogExample = new EngagementLogExample();
            Criteria criteria = engagementLogExample.createCriteria();
            Criteria criteria1 = engagementLogExample.createCriteria();
            criteria.andCreatedTimeGreaterThan(beforDay).andUserIdEqualTo(userId).andMarryStatusNotEqualTo(MarryConstant.MARRY_DECIDED).andMarryStatusNotEqualTo(MarryConstant.MARRY_FALSE);
            criteria1.andCreatedTimeGreaterThan(beforDay).andOtherIdEqualTo(otherUserId).andMarryStatusNotEqualTo(MarryConstant.MARRY_DECIDED).andMarryStatusNotEqualTo(MarryConstant.MARRY_FALSE);

            engagementLogExample.or(criteria1);
            List<EngagementLog> engagementLogList1 = engagementLogMapper.selectByExample(engagementLogExample);

            if (engagementLogList1.size() > 0) {
                JSONObject json = new JSONObject();
                json.put("type", "marryResult");
                json.put("code", 0);
                json.put("result", 1);
                messageService.sendMessageToSomeBody(userId, json.toString());
                return -2;
            }

            //求婚处理类型：1：接受求婚；2：拒绝求婚
            if (type == MarryConstant.MARRY_RECEIVE_RESULT) {
                Boolean ring = false;
                List<GeneralityGoods> userRingList = userGoodsService.queryGoodsWeddingRing(userId);
                if (userRingList.size() > 0) {
                    for (GeneralityGoods generalityGoods : userRingList) {
                        dealReceiveEngagementByUserId(engagementLogId, userId, otherUserId, generalityGoods.getId().toString(), generalityGoods.getIntensifyId());
                        ring = true;
                        break;
                    }
                    if (ring) {
                        return 0;
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            } else {

                engagementLogExample = new EngagementLogExample();
                engagementLogExample.createCriteria().andIdEqualTo(engagementLogId);
                List<EngagementLog> engagementLogList = engagementLogMapper.selectByExample(engagementLogExample);
                EngagementLog engagementLog = engagementLogList.get(0);
                engagementLog.setMarryStatus(MarryConstant.MARRY_FALSE);
                engagementLogMapper.updateByPrimaryKey(engagementLog);

                JSONObject json = new JSONObject();
                json.put("type", "marryResult");
                json.put("code", 0);
                json.put("result", 1);
                messageService.sendMessageToSomeBody(userId, json.toString());

                Set<Object> list = redisService.setMembers(MarryConstant.MARRY_ENGAGEMENT_LIST);
                if (list.size() > 0) {
                    redisService.remove(MarryConstant.MARRY_ENGAGEMENT_LIST, otherUserId);
                }
                return 1;
            }
        }
    }

    /**
     * 处理接受结婚
     *
     * @param engagementLogId
     * @param userId          求婚人
     * @param otherUserId     被求婚人
     * @param intensifyId     求婚人id
     * @param goodsId
     */
    @Transactional(rollbackFor = Exception.class)
    public void dealReceiveEngagementByUserId(String engagementLogId, String userId, String otherUserId, String goodsId, String intensifyId) {

        JSONObject json = new JSONObject();
        json.put("type", "marryResult");
        json.put("code", 0);
        json.put("result", 0);
        messageService.sendMessageToSomeBody(userId, json.toString());

        //修改订婚记录为成功或者失败
        EngagementLogExample engagementLogExample = new EngagementLogExample();
        engagementLogExample.createCriteria().andIdEqualTo(engagementLogId).andMarryStatusEqualTo(MarryConstant.MARRY_DECIDED);
        List<EngagementLog> engagementLogList = engagementLogMapper.selectByExample(engagementLogExample);
        synchronized (userId) {
            if (engagementLogList.size() > 0) {
                EngagementLog engagementLog = engagementLogList.get(0);
                engagementLog.setMarryStatus(MarryConstant.MARRY_TRUE);
                engagementLogMapper.updateByPrimaryKey(engagementLog);
            }
        }

        friendService.taskBranchFinish(MarryConstant.MARRY_TASK_BRANCH_NUM, userId);

        Set<Object> list = redisService.setMembers(MarryConstant.MARRY_ENGAGEMENT_LIST);
        if (list.size() > 0) {
            redisService.remove(MarryConstant.MARRY_ENGAGEMENT_LIST, otherUserId);
        }

        // 增加对方戒指
        UserGoods userGoods = userGoodsService.addEquipAndWeapon(otherUserId, MarryConstant.MARRY_ADD_TYPE_EQUIP, Integer.valueOf(goodsId));
        Map goodsMap = ObjectUtil.getGoodsMap(userGoods.getGoodsId(), goodsId, MarryConstant.MARRY_ADD_TYPE_EQUIP, 1, null);
        // 穿戴物品
        Map<String, Object> otherUserAdornEquip = userGoodsService.wearEquipment(otherUserId, userGoods.getGoodsId());
        goodsMap.put("wear", true);
        goodsMap.put("roleFight", JSON.parseObject(JSON.toJSONString(otherUserAdornEquip.get("userAdornEquip")), UserAdornEquip.class).getAlwaysFighting());
        Map map = ObjectUtil.getGoodsWebSock("add", goodsMap);
        messageService.sendMessageToSomeBody(otherUserId, JSONObject.toJSONString(map));

        // 求婚人穿上戒指
        Map<String, Object> userAdornEquip = userGoodsService.wearEquipment(userId, intensifyId);
        Map userGoodsMap = ObjectUtil.getGoodsMap(intensifyId, Integer.valueOf(goodsId), MarryConstant.MARRY_ADD_TYPE_EQUIP, 1, null);
        userGoodsMap.put("wear", true);
        userGoodsMap.put("roleFight", JSON.parseObject(JSON.toJSONString(userAdornEquip.get("userAdornEquip")), UserAdornEquip.class).getAlwaysFighting());
        Map userMap = ObjectUtil.getGoodsWebSock("update", userGoodsMap);
        messageService.sendMessageToSomeBody(userId, JSONObject.toJSONString(userMap));
        // {"type":"goods","code":0,"operation":"add","settingPosition":,2,goods{}}
    }



    /**
     * 获取爱情树的属性
     *
     * @param userId
     * @return
     */
    @Override
    public LoveTreeSetting getLoveTreeSetting(String userId) {
        LoveTreeSettingExample loveTreeSettingExample = new LoveTreeSettingExample();
        LoveTreeExample loveTreeExample = new LoveTreeExample();
        LoveTreeSetting loveTreeSetting = new LoveTreeSetting();
        LoveTreeInfoExample loveTreeInfoExample = new LoveTreeInfoExample();
        loveTreeInfoExample.createCriteria().andUserIdEqualTo(userId);
        List<LoveTreeInfo> list = loveTreeInfoMapper.selectByExample(loveTreeInfoExample);
        if (list.size() > 0) {
            LoveTreeInfo loveTreeInfo = list.get(0);
//			int loveGrade = loveTreeInfo.getLoveGrade();
            String loveTreeId = loveTreeInfo.getLoveTreeId();
            loveTreeExample.createCriteria().andIdEqualTo(loveTreeId);
            List<LoveTree> list2 = loveTreeMapper.selectByExample(loveTreeExample);
            int loveTreeGrade = list2.get(0).getLoveTreeGrade();
            loveTreeSettingExample.createCriteria().andGradeEqualTo(loveTreeGrade);
            List<LoveTreeSetting> list3 = loveTreeSettingMapper.selectByExample(loveTreeSettingExample);
            loveTreeSetting = list3.get(0);
            return loveTreeSetting;
        } else {
            return loveTreeSetting;
        }
    }

    /**
     * 获取情缘属性
     *
     * @param userId
     * @return
     */
    @Override
    public LoveSetting getLoveSetting(String userId) {
        LoveSetting loveSetting = new LoveSetting();
        LoveTreeInfoExample loveTreeInfoExample = new LoveTreeInfoExample();
        loveTreeInfoExample.createCriteria().andUserIdEqualTo(userId);
        List<LoveTreeInfo> list = loveTreeInfoMapper.selectByExample(loveTreeInfoExample);
        if (list.size() > 0) {
            LoveTreeInfo loveTreeInfo = list.get(0);
            int loveGrade = loveTreeInfo.getLoveGrade();
            LoveSettingExample loveSettingExample = new LoveSettingExample();
            loveSettingExample.createCriteria().andGradeEqualTo(loveGrade);
            List<LoveSetting> list2 = loveSettingMapper.selectByExample(loveSettingExample);
            loveSetting = list2.get(0);
            return loveSetting;
        } else {
            return loveSetting;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int cancleEngagementByUserId(String userId, String userRingId) {
        // 取消订婚，摧毁戒指，给双方发邮件通知，传戒指ID
        EngagementLogExample engagementLogExample = new EngagementLogExample();
        EngagementLogExample.Criteria criteria = engagementLogExample.createCriteria();
        EngagementLogExample.Criteria criteria2 = engagementLogExample.createCriteria();
        criteria.andUserIdEqualTo(userId).andUserRingIdEqualTo(userRingId).andMarryStatusEqualTo(MarryConstant.MARRY_DECIDED);
        criteria2.andOtherIdEqualTo(userId).andOtherRingIdEqualTo(userRingId).andMarryStatusEqualTo(MarryConstant.MARRY_DECIDED);
        engagementLogExample.or(criteria2);
        List<EngagementLog> engagementLogList = engagementLogMapper.selectByExample(engagementLogExample);
        EngagementLog engagementLog = engagementLogList.get(0);
        engagementLog.setMarryStatus(MarryConstant.MARRY_FALSE);
        engagementLogMapper.updateByPrimaryKey(engagementLog);

        String otherId;
        log.info(engagementLog.getUserId());
        log.info(engagementLog.getOtherId());
        if (engagementLog.getUserId().equals(userId)) {
            otherId = engagementLog.getOtherId();
        } else {
            otherId = engagementLog.getUserId();
        }
        log.info(otherId);
        log.info(userId);

        //加入取消订婚记录
        EngagementCancleLog engagementCancleLog = new EngagementCancleLog();
        engagementCancleLog.setCreatedTime(new Date(System.currentTimeMillis()));
        engagementCancleLog.setOtherId(otherId);
        engagementCancleLog.setUserId(userId);
        engagementCancleLog.setId(UUIDUtil.generateUUID());
        engagementCancleLogMapper.insert(engagementCancleLog);


        UserGoods userGoods = userGoodsService.deleteWeddingRingByUserIdAndGoodsId(userId);
        Map goodsMap = ObjectUtil.getGoodsMap(userGoods.getGoodsId(), null, MarryConstant.MARRY_ADD_TYPE_EQUIP, 1, null);
        UserAdornEquip userFightingCapacity = userGoodsService.getUserFightingCapacity(userId);

        Map map = ObjectUtil.getGoodsWebSock("delete", goodsMap);
        map.put("roleFight", userFightingCapacity.getAlwaysFighting());
        messageService.sendMessageToSomeBody(userId, JSONObject.toJSONString(map));


        UserGoods otherGoods = userGoodsService.deleteWeddingRingByUserIdAndGoodsId(otherId);
        Map otherGoodsMap = ObjectUtil.getGoodsMap(otherGoods.getGoodsId(), null, MarryConstant.MARRY_ADD_TYPE_EQUIP, 1, null);
        Map otherMap = ObjectUtil.getGoodsWebSock("delete", otherGoodsMap);
        UserAdornEquip otherUserFightingCapacity = userGoodsService.getUserFightingCapacity(otherId);
        otherMap.put("roleFight", otherUserFightingCapacity.getAlwaysFighting());
        messageService.sendMessageToSomeBody(otherId, JSONObject.toJSONString(otherMap));

        UserRole userRole = new UserRole();
        UserMessage userMessage = new UserMessage();
        userMessage.setId(UUIDUtil.generateUUID());
        if (userId.equals(engagementLog.getUserId())) {
            userRole = userRoleService.getUserRoleByUserId(userId);
            userMessage.setSenderId(userId);
            userMessage.setReceiverId(engagementLog.getOtherId());
            userMessage.setType(MessageConstant.TYPE_MARRY_CANCLE);
            userMessage.setMessageBody("玩家" + userRole.getUserName() + "取消了婚约" + "散买卖不散交情");
        } else {
            userRole = userRoleService.getUserRoleByUserId(engagementLog.getOtherId());
            userMessage.setSenderId(engagementLog.getOtherId());
            userMessage.setReceiverId(userId);
            userMessage.setType(MessageConstant.TYPE_MARRY_CANCLE);
            userMessage.setMessageBody("玩家" + userRole.getUserName() + "取消了婚约" + "散买卖不散交情");
        }

        userMessageService.addUserMessage(userMessage);
        return 0;
    }


    @Override
    public JSONObject getMarryInfo(String userId, int churchType) throws Exception {
        Date date = new Date();
        JSONObject json = new JSONObject();
        //还需要判断是否是正在举行婚礼，如果是的直接进入婚礼现场
        List<MarryLogVo> list = marryLogExtMapper.marryLogVoListByUserId(date, userId);
        boolean isSkip = false;
        if (list.size() > 0) {
            EngagementLog engagementLog = engagementLogMapper.selectByPrimaryKey(list.get(0).getEngagementId());
            if (engagementLog != null && (engagementLog.getMarryStatus() == MarryConstant.MARRY_OVERDUE || engagementLog.getMarryStatus() == MarryConstant.MARRY_FALSE)) {
//                订婚状态：0：待定；1：成功；2：失败;3:订婚失效(已离婚);4:成功举行婚礼
                isSkip = true;
            }
            if (!isSkip) {
                String marryId = list.get(0).getId();
                List<JSONObject> friendList = new ArrayList<>();
                List<String> keysList = new ArrayList<>();
                Set<String> friendkeys = redisService.likePattern("MARRYFRIEND" + marryId + "*");//好友席，八位
                Set<String> keys = redisService.likePattern("MARRY" + marryId + "*");//观礼席，20位
//                for (String friendkey : friendkeys) {
//                    String userRoleId = (String) redisService.get(friendkey);
//                    UserPet userPet = petService.getMyPetFollowed(userId);
//                    JSONObject json2 = new JSONObject();
//                    json2.put("userRoleId", userRoleId);
//                    json2.put("userId", friendkey.replace("MARRYFRIEND" + marryId, ""));
//                    if (userPet != null) {
//                        PetInfo petInfoByPetId = petService.getPetInfoByPetId(userPet.getPetId());
//                        json2.put("userPetId", petInfoByPetId == null ? "" : petInfoByPetId.getId());
//                    }
//                    friendList.add(json2);
//                }
                Set<String> petIdKeys = redisService.likePattern("PETID" + marryId + "*");//观礼席，20位
                for (String petIdAndRole : petIdKeys) {
                    String petAndRoleValue = (String) redisService.get(petIdAndRole);
                    log.info(petAndRoleValue);
                    String[] split = petAndRoleValue.split("-");
                    String role = split[0];
                    String petId = split[1];
                    JSONObject json2 = new JSONObject();
                    json2.put("userRoleId", role);
                    json2.put("userId", petIdAndRole.replace("PETID" + marryId, ""));
                    json2.put("userPetId", petId);
                    friendList.add(json2);
                }

                for (String key : keys) {
                    String portraitUrl = (String) redisService.get(key);
                    keysList.add(portraitUrl);
                }
                json.put("isMarry", 1);
                json.put("MarryLogVo", list.get(0));
                json.put("marryFriendList", friendList);
                json.put("marryKeyList", keysList);
                messageService.joinMarry(userId, marryId);
                return json;
            }
        }
        if (churchType == MarryConstant.MARRY_CHURCH_CURRENT) {
            List<MarryLogVo> marryLogList = marryLogExtMapper.marryLogVoList(date);
            //豪华婚讯
            //xxx(初恋女)和奥狄斯于xx:xx(服务器当前时间)举办婚礼，恭迎诸位
            //或
            //xxx(初恋男)和伊丽娅于xx:xx(服务器当前时间)举办婚礼，恭迎诸位
            List<MarryLogVo> marryLogList1 = new ArrayList<>();
            List<MarryLogVo> marryLogList2 = new ArrayList<>();
            List<MarryLogVo> marryLogList3 = new ArrayList<>();
            NewPlayerGuide guide = userService.getNewPlayerGuide(userId);
            if (guide == null || (guide != null && guide.getTaskId() < 7)) {
                MarryLogVo marryLogVo = new MarryLogVo();
                marryLogVo.setBeginTime(new Date());
                marryLogVo.setMarryType(MarryConstant.MARRY_TYPE_LUXURY);
                marryLogVo.setUserName((String) redisService.get(userId + "_loverName_"));
                UserRole userRole = userRoleService.getUserRoleByUserId(userId);
                if (userRole.getSex() == 1) {
                    marryLogVo.setOtherName("奥狄斯");
                } else {
                    marryLogVo.setOtherName("伊丽娅");
                }
                marryLogList3.add(marryLogVo);
            }

            if (marryLogList.size() > 0 && guide != null && guide.getTaskId() > 7) {
                for (MarryLogVo marryLog : marryLogList) {
                    if (marryLog.getMarryType() == MarryConstant.MARRY_TYPE_GENERAL) {//普通类型婚礼
                        marryLogList1.add(marryLog);
                    } else if (marryLog.getMarryType() == MarryConstant.MARRY_TYPE_ROMANCE) {//浪漫类型婚礼
                        marryLogList2.add(marryLog);
                    } else if (marryLog.getMarryType() == MarryConstant.MARRY_TYPE_LUXURY ||
                            (marryLog.getMarryType() == MarryConstant.MARRY_TYPE_LUXURY_BOOK && !isSkip)) {//豪华类型婚礼
                        marryLogList3.add(marryLog);
                    }
                }
            }
            json.put("marryGeneralList", marryLogList1);
            json.put("marryRomanceList", marryLogList2);
            json.put("marryLuxuryList", marryLogList3);
            json.put("isMarry", 2);
        } else if (churchType == MarryConstant.MARRY_CHURCH_PLACE) {
            json = judgeMarryStatus(userId);
            json.put("isMarry", 2);
        } else if (churchType == MarryConstant.MARRY_CHURCH_PARADE) {
            json = judgeCruise(userId);
            json.put("isMarry", 2);
        } else {
            throw new CheckException("类型异常！");
        }
        return json;
    }


    @Override
    public Pager<WeddingLogVo> getMarryInfoLog(String userId, int pageNum, int pageSize) {
        WeddingLogExample weddingLogExample = new WeddingLogExample();
        weddingLogExample.setOrderByClause(" created_time DESC ");
        Page<WeddingLogVo> page = PageHelper.startPage(pageNum, pageSize);
        weddingLogExtMapper.weddingLogVoList();
        List<WeddingLogVo> list = page.getResult();
        return new Pager<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized JSONObject beginMarry(String userId, int marryType, Long marryDate) throws Exception {
        JSONObject json = new JSONObject();
        //判断订婚是否完成，结婚次数是否受限
        //还需要检查双方是否都在线
        EngagementLogExample engagementLogExample = new EngagementLogExample();
        Criteria criteria = engagementLogExample.createCriteria();
        Criteria criteria2 = engagementLogExample.createCriteria();
        Criteria criteria3 = engagementLogExample.createCriteria();
        Criteria criteria4 = engagementLogExample.createCriteria();
        criteria.andUserIdEqualTo(userId).andMarryStatusEqualTo(MarryConstant.MARRY_TRUE);
        criteria2.andOtherIdEqualTo(userId).andMarryStatusEqualTo(MarryConstant.MARRY_TRUE);
        criteria3.andUserIdEqualTo(userId).andMarryStatusEqualTo(MarryConstant.MARRY_SUCCESS);
        criteria4.andOtherIdEqualTo(userId).andMarryStatusEqualTo(MarryConstant.MARRY_SUCCESS);
        engagementLogExample.setOrderByClause(" created_time DESC ");
        engagementLogExample.or(criteria2);
        engagementLogExample.or(criteria3);
        engagementLogExample.or(criteria4);
        List<EngagementLog> list = engagementLogMapper.selectByExample(engagementLogExample);
        if (list.size() > 0) {
            //订婚成功
            EngagementLog engagementLog = list.get(0);
            String engagementId = engagementLog.getId();
            String otherId = engagementLog.getOtherId();
            if (otherId.equals(userId)) {
                otherId = engagementLog.getUserId();
            }
            //短戒指ID
            String userRingId = engagementLog.getUserRingId();
            //自己的长戒指ID
            String userRingId2 = new String();
            List<GeneralityGoods> userRingList = userGoodsService.queryGoodsWeddingRing(userId);
            if (userRingList.size() > 0) {
                for (GeneralityGoods generalityGoods : userRingList) {
                    if (generalityGoods.getId().toString().equals(userRingId)) {
                        userRingId2 = generalityGoods.getIntensifyId();
                        break;
                    }
                }
            } else {
                throw new CheckException("婚戒数据异常！");
            }

            //配偶的长戒指ID
            String userRingId3 = new String();
            List<GeneralityGoods> userRingList2 = userGoodsService.queryGoodsWeddingRing(otherId);
            if (userRingList.size() > 0) {
                for (GeneralityGoods generalityGoods : userRingList2) {
                    if (generalityGoods.getId().toString().equals(userRingId)) {
                        userRingId3 = generalityGoods.getIntensifyId();
                        break;
                    }
                }
            } else {
                throw new CheckException("婚戒数据异常！");
            }

            int happinessNum = 0;
            Date date = new Date(System.currentTimeMillis());
            //订婚成功的，查询对应的结婚记录，没有就可以结婚，有的话就判断是不是重复结婚，然后判断物品是否足够
            MarryLogExample marryLogExample = new MarryLogExample();
            MarryLogExample.Criteria marryLogcriteria = marryLogExample.createCriteria();
            MarryLogExample.Criteria marryLogcriteria2 = marryLogExample.createCriteria();
            marryLogcriteria.andEngagementIdEqualTo(engagementId).andUserIdEqualTo(userId);
            marryLogcriteria2.andEngagementIdEqualTo(engagementId).andOtherIdEqualTo(userId);
            marryLogExample.setOrderByClause(" created_time DESC ");
            marryLogExample.or(marryLogcriteria2);
            List<MarryLog> marryLogList = marryLogMapper.selectByExample(marryLogExample);

            Map<String, Object> map = new HashMap<>(16);
            Map<String, Object> map2 = new HashMap<>(16);
            List<ReceiveGoldVo> list2 = new ArrayList<>();
            UserGoods userGoods = userGoodsService.getGoodsCount(userId, MarryConstant.MARRY_CONSUMABLES_STONE);
            if (userGoods == null) {
                throw new CheckException("心意石不足！");
            }
            int count = userGoods.getAmount();//心意石
            UserGoods userGoods2 = userGoodsService.getGoodsCount(userId, MarryConstant.MARRY_CONSUMABLES_COIN);
            if (userGoods2 == null) {
                throw new CheckException("金币不足！");
            }
            int count2 = userGoods2.getAmount();//金币
            UserGoods userGoods3 = userGoodsService.getGoodsCount(userId, MarryConstant.MARRY_CONSUMABLES_DIAMOND);
            if (userGoods3 == null) {
                throw new CheckException("钻石不足！");
            }
            int count3 = userGoods3.getAmount();//钻石
            if (marryLogList.size() == 0) {//没有结婚记录


                if (marryType == MarryConstant.MARRY_TYPE_GENERAL) { //1：普通婚礼
                    if (count < MarryConstant.MARRY_CONSUMABLES_STONE_NUM || count2 < MarryConstant.MARRY_CONSUMABLES_COIN_NUM) {
                        throw new CheckException("心意石或者金币数量不足！");
                    } else {
                        String marryId = this.addMarryLogByUserId(userId, otherId, engagementId, marryType, Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_STONE_NUM),
                                MarryConstant.MARRY_CONSUMABLES_STONE, MarryConstant.MARRY_CONSUMABLES_COIN_NUM,
                                MarryConstant.MARRY_CONSUMABLES_COIN);

                        ReceiveGoldVo receiveGoldVo = new ReceiveGoldVo();
                        receiveGoldVo.setAmount(count - MarryConstant.MARRY_CONSUMABLES_STONE_NUM);
                        receiveGoldVo.setGoodsId(Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_STONE));
                        receiveGoldVo.setId(MarryConstant.MARRY_CONSUMABLES_STONE);
                        receiveGoldVo.setType(BuildingConstant.Prop);
                        list2.add(receiveGoldVo);

                        receiveGoldVo = new ReceiveGoldVo();
                        receiveGoldVo.setAmount(count2 - MarryConstant.MARRY_CONSUMABLES_COIN_NUM);
                        receiveGoldVo.setGoodsId(Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_COIN));
                        receiveGoldVo.setId(MarryConstant.MARRY_CONSUMABLES_COIN);
                        receiveGoldVo.setType(BuildingConstant.Prop);

                        list2.add(receiveGoldVo);
                        json.put("result", list2);

                        MarryLogVo marryLogVo = marryLogExtMapper.getMarryLogVoById(marryId);
                        json.put("MarryLogVo", marryLogVo);

                        sendMailToFriendByUserId(userId, otherId, marryId);

                    }

                } else if (marryType == MarryConstant.MARRY_TYPE_ROMANCE) {//2：浪漫婚礼

                    if (count < MarryConstant.MARRY_CONSUMABLES_STONE_NUM || count3 < MarryConstant.MARRY_CONSUMABLES_DIAMOND_NUM) {
                        throw new CheckException("心意石或者钻石数量不足！");
                    } else {
                        String marryId = this.addMarryLogByUserId(userId, otherId, engagementId, marryType, Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_STONE_NUM),
                                MarryConstant.MARRY_CONSUMABLES_STONE, MarryConstant.MARRY_CONSUMABLES_DIAMOND_NUM,
                                MarryConstant.MARRY_CONSUMABLES_DIAMOND);

                        happinessNum = MarryConstant.MARRY_HAPPINESS_ROMANCE_NUM;

                        ReceiveGoldVo receiveGoldVo = new ReceiveGoldVo();
                        receiveGoldVo.setAmount(count - MarryConstant.MARRY_CONSUMABLES_STONE_NUM);
                        receiveGoldVo.setGoodsId(Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_STONE));
                        receiveGoldVo.setId(MarryConstant.MARRY_CONSUMABLES_STONE);
                        receiveGoldVo.setType(BuildingConstant.Prop);
                        list2.add(receiveGoldVo);

                        receiveGoldVo = new ReceiveGoldVo();
                        receiveGoldVo.setAmount(count3 - MarryConstant.MARRY_CONSUMABLES_DIAMOND_NUM);
                        receiveGoldVo.setGoodsId(Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_DIAMOND));
                        receiveGoldVo.setId(MarryConstant.MARRY_CONSUMABLES_DIAMOND);
                        receiveGoldVo.setType(BuildingConstant.Prop);

                        list2.add(receiveGoldVo);
                        json.put("result", list2);
                        MarryLogVo marryLogVo = marryLogExtMapper.getMarryLogVoById(marryId);
                        json.put("MarryLogVo", marryLogVo);

                        sendMailToFriendByUserId(userId, otherId, marryId);
                    }
                } else if (marryType == MarryConstant.MARRY_TYPE_LUXURY) {//3：豪华婚礼
                    if (count < MarryConstant.MARRY_CONSUMABLES_STONE_NUM || count3 < MarryConstant.MARRY_CONSUMABLES_DIAMOND_2NUM) {
                        throw new CheckException("心意石或者钻石数量不足！");
                    } else {
                        String marryId = this.addMarryLogByUserId(userId, otherId, engagementId, marryType, Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_STONE_NUM),
                                MarryConstant.MARRY_CONSUMABLES_STONE, MarryConstant.MARRY_CONSUMABLES_DIAMOND_2NUM,
                                MarryConstant.MARRY_CONSUMABLES_DIAMOND);
                        happinessNum = MarryConstant.MARRY_HAPPINESS_LUXURY_NUM;

                        ReceiveGoldVo receiveGoldVo = new ReceiveGoldVo();
                        receiveGoldVo.setAmount(count - MarryConstant.MARRY_CONSUMABLES_STONE_NUM);
                        receiveGoldVo.setGoodsId(Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_STONE));
                        receiveGoldVo.setId(MarryConstant.MARRY_CONSUMABLES_STONE);
                        receiveGoldVo.setType(BuildingConstant.Prop);
                        list2.add(receiveGoldVo);

                        receiveGoldVo = new ReceiveGoldVo();
                        receiveGoldVo.setAmount(count3 - MarryConstant.MARRY_CONSUMABLES_DIAMOND_2NUM);
                        receiveGoldVo.setGoodsId(Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_DIAMOND));
                        receiveGoldVo.setId(MarryConstant.MARRY_CONSUMABLES_DIAMOND);
                        receiveGoldVo.setType(BuildingConstant.Prop);

                        list2.add(receiveGoldVo);
                        json.put("result", list2);
                        MarryLogVo marryLogVo = marryLogExtMapper.getMarryLogVoById(marryId);
                        json.put("MarryLogVo", marryLogVo);

                        sendMailToFriendByUserId(userId, otherId, marryId);
                    }
                } else if (marryType == MarryConstant.MARRY_TYPE_LUXURY_BOOK) {//4：豪华预约婚礼
                    if (count < MarryConstant.MARRY_CONSUMABLES_STONE_NUM || count3 < MarryConstant.MARRY_CONSUMABLES_DIAMOND_2NUM) {
                        throw new CheckException("心意石或者钻石数量不足！");
                    } else {


                        //特别处理预约结婚的情况,不能使用通用处理方法
                        subUserMoney(userId, MarryConstant.MARRY_CONSUMABLES_STONE, MarryConstant.MARRY_CONSUMABLES_STONE_NUM);

                        subUserMoney(userId, MarryConstant.MARRY_CONSUMABLES_DIAMOND, MarryConstant.MARRY_CONSUMABLES_DIAMOND_2NUM);

                        String marryId = UUIDUtil.generateUUID();
                        MarryLog marryLog = new MarryLog();
                        Date endDate = new Date(marryDate + 60 * 30 * 1000);
                        marryLog.setCreatedTime(date);
                        marryLog.setBeginTime(new Date(marryDate));
                        marryLog.setEndTime(endDate);
                        marryLog.setEngagementId(engagementId);
                        marryLog.setMarryType(marryType);
                        marryLog.setUserId(userId);
                        marryLog.setOtherId(otherId);
                        marryLog.setOtherJoyfulNum(MarryConstant.MARRY_TREE_PLAYJOKES);
                        marryLog.setUserJoyfulNum(MarryConstant.MARRY_TREE_PLAYJOKES);
                        marryLog.setId(marryId);
                        marryLogMapper.insert(marryLog);


                        happinessNum = MarryConstant.MARRY_HAPPINESS_LUXURY_NUM;

                        ReceiveGoldVo receiveGoldVo = new ReceiveGoldVo();
                        receiveGoldVo.setAmount(count - MarryConstant.MARRY_CONSUMABLES_STONE_NUM);
                        receiveGoldVo.setGoodsId(Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_STONE));
                        receiveGoldVo.setId(MarryConstant.MARRY_CONSUMABLES_STONE);
                        receiveGoldVo.setType(BuildingConstant.Prop);
                        list2.add(receiveGoldVo);

                        receiveGoldVo = new ReceiveGoldVo();
                        receiveGoldVo.setAmount(count3 - MarryConstant.MARRY_CONSUMABLES_DIAMOND_2NUM);
                        receiveGoldVo.setGoodsId(Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_DIAMOND));
                        receiveGoldVo.setId(MarryConstant.MARRY_CONSUMABLES_DIAMOND);
                        receiveGoldVo.setType(BuildingConstant.Prop);

                        list2.add(receiveGoldVo);
                        json.put("result", list2);
                        MarryLogVo marryLogVo = marryLogExtMapper.getMarryLogVoById(marryId);
                        json.put("MarryLogVo", marryLogVo);

                        sendMailToFriendByUserId(userId, otherId, marryId);
                    }
                }
                //只在第一次结婚时增加结婚记录，用于后续的被查询结婚情侣列表,由于序号的存在必须锁定增加的时候的唯一性
                this.addWeddingLogByUserId(userId, otherId, engagementId);
                //修改角色配偶信息,给双方添加配偶信息
                userRoleService.updateMarryStatus(userId, otherId);
                userRoleService.updateMarryStatus(otherId, userId);
                //增加双方幸福值
                addHappinessByUserId(userId, happinessNum);
                addHappinessByUserId(otherId, happinessNum);
                //TODO 可能导致无法重复结婚，需要进行修改
                engagementLog.setMarryStatus(MarryConstant.MARRY_SUCCESS);
                engagementLogMapper.updateByPrimaryKey(engagementLog);

                //生成公共爱情树的记录，在第二次结婚时增加相关数值,
                String loveTreeId = UUIDUtil.generateUUID();
                LoveTree loveTree = new LoveTree();
                loveTree.setCreatedTime(date);
                loveTree.setEngagementId(engagementId);
                loveTree.setId(loveTreeId);
                loveTree.setExpLimit(MarryConstant.MARRY_TREE_EXPLIMIT);
                loveTree.setLoveTreeGrade(0);
                loveTree.setExpNum(0);
                loveTreeMapper.insert(loveTree);

                LoveTreeInfo loveTreeInfo = new LoveTreeInfo();
                loveTreeInfo.setCreatedTime(date);
                loveTreeInfo.setEngagementId(engagementId);
                loveTreeInfo.setHappinessNum(Long.valueOf(String.valueOf(happinessNum)));
                loveTreeInfo.setId(UUIDUtil.generateUUID());
                loveTreeInfo.setLoveGrade(0);
                loveTreeInfo.setLoveNum(0L);
                loveTreeInfo.setLoveTreeId(loveTreeId);
                loveTreeInfo.setUserId(userId);
                loveTreeInfo.setPlayJokesNum(MarryConstant.MARRY_TREE_PLAYJOKES);
                loveTreeInfo.setWeddingNum(MarryConstant.MARRY_TREE_WEDDING);
                loveTreeInfo.setOtherId(otherId);
                //比翼双飞戒指2级戒指
                if (userRingId.equals(MarryConstant.MARRY_RING_ROMANCE)) {
                    loveTreeInfo.setWeddingRingGrade(2);
                } else {
                    //同心戒指1级戒指
                    loveTreeInfo.setWeddingRingGrade(1);
                }

                loveTreeInfo.setWeddingRingId(userRingId2);
                loveTreeInfoMapper.insert(loveTreeInfo);

                loveTreeInfo.setId(UUIDUtil.generateUUID());
                loveTreeInfo.setUserId(otherId);
                loveTreeInfo.setOtherId(userId);
                loveTreeInfo.setWeddingRingId(userRingId3);
                loveTreeInfoMapper.insert(loveTreeInfo);

                buildingService.openMarriageByUserId(userId, BuildingConstant.BUILDING_OPEN_TRUE);
                buildingService.openMarriageByUserId(otherId, BuildingConstant.BUILDING_OPEN_TRUE);

                friendService.taskBranchFinish(MarryConstant.MARRY_TASK_BRANCH_NUM2, userId);
                friendService.taskBranchFinish(MarryConstant.MARRY_TASK_BRANCH_NUM2, otherId);

                userGoodsService.wearEquipment(userId, userRingId2);
                userGoodsService.wearEquipment(otherId, userRingId3);

                json.put("code", 0);
                return json;
            } else if (marryLogList.size() > 0) {


                //有一次结婚记录，男女各可以举行一次
                for (MarryLog marryLog : marryLogList) {
                    if (marryLog.getMarryType() == MarryConstant.MARRY_TYPE_LUXURY_BOOK && DateUtil.compareDate(marryLog.getEndTime())) {
                        throw new CheckException("预约婚礼还未结束，不能再次举行婚礼！");
                    }
                    if (DateUtil.compareDate(marryLog.getEndTime())) {
                        throw new CheckException("有婚礼正在进行，不能再次举行婚礼！");
                    }
                    map.put(new StringBuilder(marryLog.getEngagementId()).append(marryLog.getUserId()).append(String.valueOf(marryLog.getMarryType())).
                            toString(), marryLog);
                    map2.put(new StringBuilder(marryLog.getEngagementId()).append(String.valueOf(marryLog.getMarryType())).toString(), marryLog);
                }
                if (marryType == MarryConstant.MARRY_TYPE_LUXURY) {
                    if (map.containsKey(new StringBuilder(engagementId).append(userId).append(String.valueOf(marryType)).toString())) {
                        throw new CheckException("该婚礼类型已经举行过，不能重复举行！");
                    } else {
                        if (count < MarryConstant.MARRY_CONSUMABLES_STONE_NUM || count3 < MarryConstant.MARRY_CONSUMABLES_DIAMOND_2NUM) {
                            throw new CheckException("心意石不足或者钻石不足！");
                        } else {
                            String marryId = this.addMarryLogByUserId(userId, otherId, engagementId, marryType, Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_STONE_NUM),
                                    MarryConstant.MARRY_CONSUMABLES_STONE, MarryConstant.MARRY_CONSUMABLES_DIAMOND_2NUM,
                                    MarryConstant.MARRY_CONSUMABLES_DIAMOND);

                            happinessNum = MarryConstant.MARRY_HAPPINESS_LUXURY_NUM;

                            ReceiveGoldVo receiveGoldVo = new ReceiveGoldVo();
                            receiveGoldVo.setAmount(count - MarryConstant.MARRY_CONSUMABLES_STONE_NUM);
                            receiveGoldVo.setGoodsId(Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_STONE));
                            receiveGoldVo.setId(MarryConstant.MARRY_CONSUMABLES_STONE);
                            receiveGoldVo.setType(BuildingConstant.Prop);
                            list2.add(receiveGoldVo);

                            receiveGoldVo = new ReceiveGoldVo();
                            receiveGoldVo.setAmount(count2 - MarryConstant.MARRY_CONSUMABLES_DIAMOND_2NUM);
                            receiveGoldVo.setGoodsId(Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_DIAMOND));
                            receiveGoldVo.setId(MarryConstant.MARRY_CONSUMABLES_DIAMOND);
                            receiveGoldVo.setType(BuildingConstant.Prop);

                            list2.add(receiveGoldVo);
                            json.put("result", list2);
                            MarryLogVo marryLogVo = marryLogExtMapper.getMarryLogVoById(marryId);
                            json.put("MarryLogVo", marryLogVo);

//                            userGoodsService.wearEquipment(userId, userRingId2);
//                            userGoodsService.wearEquipment(otherId, userRingId3);

                            sendMailToFriendByUserId(userId, otherId, marryId);
                        }
                    }
                } else if (marryType == MarryConstant.MARRY_TYPE_LUXURY_BOOK) { //用于区别多传一个参数
                    if (map.containsKey(new StringBuilder(engagementId).append(userId).append(String.valueOf(marryType)).toString())) {
                        throw new CheckException("该婚礼类型已经举行过，不能重复举行！");
                    } else {
                        if (count < MarryConstant.MARRY_CONSUMABLES_STONE_NUM || count3 < MarryConstant.MARRY_CONSUMABLES_DIAMOND_2NUM) {
                            throw new CheckException("心意石不足或者钻石不足！");
                        } else {

                            subUserMoney(userId, MarryConstant.MARRY_CONSUMABLES_STONE, MarryConstant.MARRY_CONSUMABLES_STONE_NUM);

                            subUserMoney(userId, MarryConstant.MARRY_CONSUMABLES_DIAMOND, MarryConstant.MARRY_CONSUMABLES_DIAMOND_2NUM);

                            String marryId = UUIDUtil.generateUUID();
                            MarryLog marryLog = new MarryLog();
                            Date endDate = new Date(marryDate + 60 * 30 * 1000);
                            marryLog.setCreatedTime(date);
                            marryLog.setBeginTime(new Date(marryDate));
                            marryLog.setEndTime(endDate);
                            marryLog.setEngagementId(engagementId);
                            marryLog.setMarryType(marryType);
                            marryLog.setUserId(userId);
                            marryLog.setOtherId(otherId);
                            marryLog.setOtherJoyfulNum(MarryConstant.MARRY_TREE_PLAYJOKES);
                            marryLog.setUserJoyfulNum(MarryConstant.MARRY_TREE_PLAYJOKES);
                            marryLog.setId(marryId);
                            marryLogMapper.insert(marryLog);

                            happinessNum = MarryConstant.MARRY_HAPPINESS_LUXURY_NUM;

                            ReceiveGoldVo receiveGoldVo = new ReceiveGoldVo();
                            receiveGoldVo.setAmount(count - MarryConstant.MARRY_CONSUMABLES_STONE_NUM);
                            receiveGoldVo.setGoodsId(Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_STONE));
                            receiveGoldVo.setId(MarryConstant.MARRY_CONSUMABLES_STONE);
                            receiveGoldVo.setType(BuildingConstant.Prop);
                            list2.add(receiveGoldVo);

                            receiveGoldVo = new ReceiveGoldVo();
                            receiveGoldVo.setAmount(count2 - MarryConstant.MARRY_CONSUMABLES_DIAMOND_2NUM);
                            receiveGoldVo.setGoodsId(Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_DIAMOND));
                            receiveGoldVo.setId(MarryConstant.MARRY_CONSUMABLES_DIAMOND);
                            receiveGoldVo.setType(BuildingConstant.Prop);

                            list2.add(receiveGoldVo);
                            json.put("result", list2);
                            MarryLogVo marryLogVo = marryLogExtMapper.getMarryLogVoById(marryId);
                            json.put("MarryLogVo", marryLogVo);

//                            userGoodsService.wearEquipment(userId, userRingId2);
//                            userGoodsService.wearEquipment(otherId, userRingId3);

                            sendMailToFriendByUserId(userId, otherId, marryId);
                        }
                    }
                } else if (map2.containsKey(new StringBuilder(engagementId).append(String.valueOf(marryType)).toString())) {
                    throw new CheckException("该婚礼类型已经举行过，不能重复举行！");
                } else {
                    if (marryType == MarryConstant.MARRY_TYPE_GENERAL) {//1：普通婚礼
                        if (count < MarryConstant.MARRY_CONSUMABLES_STONE_NUM || count2 < MarryConstant.MARRY_CONSUMABLES_COIN_NUM) {
                            throw new CheckException("金币不足或者心意石不足！");
                        } else {
                            String marryId = this.addMarryLogByUserId(userId, otherId, engagementId, marryType, Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_STONE_NUM),
                                    MarryConstant.MARRY_CONSUMABLES_STONE, MarryConstant.MARRY_CONSUMABLES_COIN_NUM,
                                    MarryConstant.MARRY_CONSUMABLES_COIN);

                            ReceiveGoldVo receiveGoldVo = new ReceiveGoldVo();
                            receiveGoldVo.setAmount(count - MarryConstant.MARRY_CONSUMABLES_STONE_NUM);
                            receiveGoldVo.setGoodsId(Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_STONE));
                            receiveGoldVo.setId(MarryConstant.MARRY_CONSUMABLES_STONE);
                            receiveGoldVo.setType(BuildingConstant.Prop);
                            list2.add(receiveGoldVo);

                            receiveGoldVo = new ReceiveGoldVo();
                            receiveGoldVo.setAmount(count2 - MarryConstant.MARRY_CONSUMABLES_COIN_NUM);
                            receiveGoldVo.setGoodsId(Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_COIN));
                            receiveGoldVo.setId(MarryConstant.MARRY_CONSUMABLES_COIN);
                            receiveGoldVo.setType(BuildingConstant.Prop);

                            list2.add(receiveGoldVo);
                            json.put("result", list2);
                            MarryLogVo marryLogVo = marryLogExtMapper.getMarryLogVoById(marryId);
                            json.put("MarryLogVo", marryLogVo);

//                            userGoodsService.wearEquipment(userId, userRingId2);
//                            userGoodsService.wearEquipment(otherId, userRingId3);

                            sendMailToFriendByUserId(userId, otherId, marryId);
                        }
                    } else if (marryType == MarryConstant.MARRY_TYPE_ROMANCE) {//2：浪漫婚礼
                        if (count < MarryConstant.MARRY_CONSUMABLES_STONE_NUM || count3 < MarryConstant.MARRY_CONSUMABLES_DIAMOND_NUM) {
                            throw new CheckException("心意石不足或者钻石不足！");
                        } else {
                            String marryId = this.addMarryLogByUserId(userId, otherId, engagementId, marryType, Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_STONE_NUM),
                                    MarryConstant.MARRY_CONSUMABLES_STONE, MarryConstant.MARRY_CONSUMABLES_DIAMOND_NUM,
                                    MarryConstant.MARRY_CONSUMABLES_DIAMOND);

                            happinessNum = MarryConstant.MARRY_HAPPINESS_ROMANCE_NUM;

                            ReceiveGoldVo receiveGoldVo = new ReceiveGoldVo();
                            receiveGoldVo.setAmount(count - MarryConstant.MARRY_CONSUMABLES_STONE_NUM);
                            receiveGoldVo.setGoodsId(Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_STONE));
                            receiveGoldVo.setId(MarryConstant.MARRY_CONSUMABLES_STONE);
                            receiveGoldVo.setType(BuildingConstant.Prop);
                            list2.add(receiveGoldVo);

                            receiveGoldVo = new ReceiveGoldVo();
                            receiveGoldVo.setAmount(count2 - MarryConstant.MARRY_CONSUMABLES_DIAMOND_NUM);
                            receiveGoldVo.setGoodsId(Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_DIAMOND));
                            receiveGoldVo.setId(MarryConstant.MARRY_CONSUMABLES_DIAMOND);
                            receiveGoldVo.setType(BuildingConstant.Prop);

                            list2.add(receiveGoldVo);
                            json.put("result", list2);
                            MarryLogVo marryLogVo = marryLogExtMapper.getMarryLogVoById(marryId);
                            json.put("MarryLogVo", marryLogVo);
//                            userGoodsService.wearEquipment(userId, userRingId2);
//                            userGoodsService.wearEquipment(otherId, userRingId3);
                            sendMailToFriendByUserId(userId, otherId, marryId);
                        }
                    }
                }
                //增加双方幸福值
                addHappinessByUserId(userId, happinessNum);
                addHappinessByUserId(otherId, happinessNum);
                json.put("code", 0);
                return json;
            } else {
                throw new CheckException("数据异常！");
            }
        } else {
            throw new CheckException("没有订婚记录不能结婚！");
        }
    }

    public void sendMailToFriendByUserId(String userId, String otherId, String marryId) {
        Date surplusDat =new Date();
        MarryLog marryLog = marryLogMapper.selectByPrimaryKey(marryId);
        if (marryLog != null) {
            surplusDat = marryLog.getEndTime();
        }
        //TODO 补充发送请帖按钮
        User user = userService.findByUserId(userId);
        User other = userService.findByUserId(otherId);
        String userName = user.getName();
        String otherUserName = other.getName();
        List<FriendDO> list = friendService.findFriendList(userId);
        List<FriendDO> list2 = friendService.findFriendList(otherId);
        for (FriendDO friendDO : list) {
            String userId2 = friendDO.getUserId();
            if (!userId2.equals(userId) && !userId2.equals(otherId)) {
                UserMessage userMessage = new UserMessage();
                userMessage.setId(UUIDUtil.generateUUID());
                userMessage.setSenderId(userId);
                userMessage.setReceiverId(friendDO.getUserId());
                userMessage.setMessageBody("您的好友" + userName + "正在举行婚礼，邀请您前去参加" + marryId);
                userMessage.setType(MessageConstant.TYPE_MARRY_JOIN);
                userMessage.setSurplusDate(surplusDat);
                userMessageService.addUserMessage(userMessage);
            }
        }
        for (FriendDO friendDO : list2) {
            String userId2 = friendDO.getUserId();
            if (!userId2.equals(userId) && !userId2.equals(otherId)) {
                UserMessage userMessage = new UserMessage();
                userMessage.setId(UUIDUtil.generateUUID());
                userMessage.setSenderId(otherId);
                userMessage.setReceiverId(friendDO.getUserId());
                userMessage.setType(MessageConstant.TYPE_MARRY_JOIN);
                userMessage.setMessageBody("您的好友" + otherUserName + "正在举行婚礼，邀请您前去参加" + marryId);
                userMessage.setSurplusDate(surplusDat);
                userMessageService.addUserMessage(userMessage);
            }
        }

        JSONObject json2 = new JSONObject();
        json2.put("type", "weddingBegin");
        json2.put("userName", userName);
        json2.put("code", 0);
        json2.put("marryId", marryId);
        if (other.getStatus() == MarryConstant.MARRY_ON_LINE) {
            if (!redisService.exists("JOINBOOKWEDDING" + marryId + otherId)) {
                messageService.sendMessageToSomeBody(otherId, json2.toString());
            }
        }

    }

    /**
     * 增加结婚记录，由于序号的唯一性，特意加锁处理
     *
     * @param userId
     * @param otherId
     * @param engagementId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public synchronized int addWeddingLogByUserId(String userId, String otherId, String engagementId) {
        //处理任务
        Date date = new Date(System.currentTimeMillis());
        WeddingLogExample weddingLogExample = new WeddingLogExample();
        weddingLogExample.setOrderByClause(" created_time DESC ");
        List<WeddingLog> weddingLoglist = weddingLogMapper.selectByExample(weddingLogExample);
        if (weddingLoglist.size() > 0) {
            WeddingLog weddingLog = new WeddingLog();
            weddingLog.setEngagementId(engagementId);
            weddingLog.setId(UUIDUtil.generateUUID());
            weddingLog.setUserId(userId);
            weddingLog.setOtherId(otherId);
            weddingLog.setOrderNo(weddingLoglist.get(0).getOrderNo() + 1);
            weddingLog.setCreatedTime(date);
            weddingLogMapper.insert(weddingLog);
        } else {
            WeddingLog weddingLog = new WeddingLog();
            weddingLog.setEngagementId(engagementId);
            weddingLog.setId(UUIDUtil.generateUUID());
            weddingLog.setUserId(userId);
            weddingLog.setOtherId(otherId);
            weddingLog.setOrderNo(Long.valueOf("1"));
            weddingLog.setCreatedTime(date);
            weddingLogMapper.insert(weddingLog);
        }
        return 0;
    }

    /**
     * 举行婚礼通用处理方法
     *
     * @param userId       求婚人
     * @param otherId      被求婚人
     * @param engagementId 订婚ID
     * @param marryType    结婚类型
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String addMarryLogByUserId(String userId, String otherId, String engagementId, int marryType, int amount, String goodsId, int amount2, String goodsId2) {
        Date date = new Date(System.currentTimeMillis());

        subUserMoney(userId, goodsId, amount);

        subUserMoney(userId, goodsId2, amount2);

        String marryId = UUIDUtil.generateUUID();
        MarryLog marryLog = new MarryLog();
        Date endDate = new Date(System.currentTimeMillis() + 60 * 30 * 1000);
        marryLog.setCreatedTime(date);
        marryLog.setBeginTime(date);
        marryLog.setEndTime(endDate);
        marryLog.setEngagementId(engagementId);
        marryLog.setMarryType(marryType);
        marryLog.setUserId(userId);
        marryLog.setOtherId(otherId);
        marryLog.setOtherJoyfulNum(MarryConstant.MARRY_TREE_PLAYJOKES);
        marryLog.setUserJoyfulNum(MarryConstant.MARRY_TREE_PLAYJOKES);
        marryLog.setId(marryId);
        marryLogMapper.insert(marryLog);

        return marryId;
    }

    @Override
    public JSONObject buyAndJoyful(String userId, String marryId) {
        JSONObject json = new JSONObject();
        UserGoods userGoods2 = userGoodsService.getGoodsCount(userId, MarryConstant.MARRY_CONSUMABLES_DIAMOND);
        int count2 = userGoods2.getAmount();//钻石
        if (count2 < MarryConstant.MARRY_JOYFUL_NUM) {
            json.put("code", -1);
            return json;
        }

        subUserMoney(userId, MarryConstant.MARRY_CONSUMABLES_DIAMOND, MarryConstant.MARRY_JOYFUL_NUM);

        //可能需要提前做判断是否存在该缓存
        Set<String> friendkeys = redisService.likePattern("MARRYFRIEND" + marryId + "*");
        for (String friend : friendkeys) {
            String goodsId = joyful();
            String friendId = friend.replace("MARRYFRIEND" + marryId, "");
            addUserMoney(friendId, goodsId, 1);

            JSONObject json2 = new JSONObject();
            json2.put("type", "weddingCandies");
            json2.put("code", 0);
            json2.put("goodsId", goodsId);
            json2.put("id", goodsId);
            json2.put("amount", 1);
            json2.put("itemType", 1);
            messageService.sendMessageToSomeBody(friendId, json.toString());
        }

        ReceiveGoldVo receiveGoldVo = new ReceiveGoldVo();
        receiveGoldVo.setAmount(userGoods2.getAmount() - MarryConstant.MARRY_JOYFUL_NUM);
        receiveGoldVo.setGoodsId(Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_DIAMOND));
        receiveGoldVo.setId(MarryConstant.MARRY_CONSUMABLES_DIAMOND);
        receiveGoldVo.setType(BuildingConstant.Prop);

        json.put("ReceiveGoldVo", receiveGoldVo);
        json.put("code", 0);
        return json;
    }

    @Override
    public int andJoyful(String userId, String marryId) {
        //撒喜糖
        MarryLogExample marryLogExample = new MarryLogExample();
        marryLogExample.createCriteria().andIdEqualTo(marryId);
        List<MarryLog> list = marryLogMapper.selectByExample(marryLogExample);
        if (list.size() > 0) {
            MarryLog marryLog = list.get(0);
            if (marryLog.getUserId().equals(userId)) {
                if (marryLog.getUserJoyfulNum() > 0) {
                    marryLog.setUserJoyfulNum(marryLog.getUserJoyfulNum() - 1);
                    marryLogMapper.updateByPrimaryKey(marryLog);

                    //免费的喜糖次数-1；随机给出一个道具，根据redis获取到好友席上的人员名单
                    Set<String> friendkeys = redisService.likePattern("MARRYFRIEND" + marryId + "*");
                    for (String friend : friendkeys) {
                        String goodsId = joyful();
                        String friendId = friend.replace("MARRYFRIEND" + marryId, "");
                        addUserMoney(friendId, goodsId, 1);

                        JSONObject json = new JSONObject();
                        json.put("type", "weddingCandies");
                        json.put("code", 0);
                        json.put("goodsId", goodsId);
                        json.put("id", goodsId);
                        json.put("amount", 1);
                        json.put("itemType", 1);
                        messageService.sendMessageToSomeBody(friendId, json.toString());

                    }
                    return 0;
                } else {
                    return -1;//免费撒喜糖次数已经用完，可以使用钻石撒喜糖
                }
            } else if (marryLog.getOtherId().equals(userId)) {
                if (marryLog.getOtherJoyfulNum() > 0) {
                    marryLog.setOtherJoyfulNum(marryLog.getOtherJoyfulNum() - 1);
                    marryLogMapper.updateByPrimaryKey(marryLog);

                    //免费的喜糖次数-1；随机给出一个道具，根据redis获取到好友席上的人员名单
                    Set<String> friendkeys = redisService.likePattern("MARRYFRIEND" + marryId + "*");
                    for (String friend : friendkeys) {
                        String goodsId = joyful();
                        String friendId = friend.replace("MARRYFRIEND" + marryId, "");
                        addUserMoney(friend, goodsId, 1);
                        JSONObject json = new JSONObject();
                        json.put("type", "weddingCandies");
                        json.put("code", 0);
                        json.put("goodsId", goodsId);
                        json.put("id", goodsId);
                        json.put("amount", 1);
                        json.put("itemType", 1);
                        messageService.sendMessageToSomeBody(friendId, json.toString());
                    }
                    return 0;
                } else {
                    return -1;//免费撒喜糖次数已经用完，可以使用钻石撒喜糖
                }
            } else {
                return -2;
            }
        } else {
            return -2;
        }
    }

    /**
     * 撒喜糖，根据相应的配置随机得出一个道具ID,
     *
     * @return
     */
    public String joyful() {
        JSONArray arr = JSONObject.parseArray(MarryConstant.MARRY_JOYFUL);
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            JSONObject object = arr.getJSONObject(i);
            int goodsId = object.getIntValue("goodsId");
            int weight = object.getIntValue("weight");
            list.add(weight);
            list2.add(goodsId);
        }
        int i = this.getWeightRandom(list);
        int goodsId = list2.get(i);
        return String.valueOf(goodsId);
    }

    private int weightArraySum(List<Integer> weightArrays) {
        int weightSum = 0;
        for (int weightValue : weightArrays) {
            weightSum += weightValue;
        }
        return weightSum;
    }

    public int getWeightRandom(List<Integer> weightArrays) {
        int weightSum = weightArraySum(weightArrays);
        int stepWeightSum = 0;
        Random rd = new Random();
        for (int i = 0; i < weightArrays.size(); i++) {
            stepWeightSum += weightArrays.get(i);
            if (rd.nextInt(weightSum) <= stepWeightSum) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 通用增加物品
     *
     * @param userId
     * @param goodsId
     * @param amount
     */
    public void addUserMoney(String userId, String goodsId, Integer amount) {
        UserGoods userGoods = new UserGoods();
        userGoods.setAmount(amount);
        userGoods.setGoodsId(goodsId);
        List<UserGoods> goodsList = new ArrayList<>();
        goodsList.add(userGoods);
        userGoodsService.addUserGoods(userId, goodsList);
    }

    /**
     * 通用消耗物品
     *
     * @param userId
     * @param goodsId
     * @param amount
     */
    public void subUserMoney(String userId, String goodsId, Integer amount) {
        UserGoods userGoods = new UserGoods();
        userGoods.setAmount(amount);
        userGoods.setGoodsId(goodsId);
        List<UserGoods> goodsList = new ArrayList<>();
        goodsList.add(userGoods);
        userGoodsService.subUserGoods(userId, goodsList);
    }

    @Override
    public JSONObject joinWedding(String userId, String marryId) {
        JSONObject json = new JSONObject();
        Date date = new Date(System.currentTimeMillis());
        List<MarryLogVo> list = marryLogExtMapper.marryLogVoListByUserId(date, userId);
        //TODO 正式版需要解开
        MarryLogVo marryLogVo = marryLogExtMapper.getMarryLogVoById(marryId);
        if (marryLogVo == null) {
            throw new CheckException("当前婚礼已结束！或不存在");
        }
        if (marryLogVo.getEndTime().getTime() < System.currentTimeMillis()) {
            throw new CheckException("当前婚礼已结束！");
        }


        List<JSONObject> friendList = new ArrayList<>();
        List<String> keysList = new ArrayList<>();
        Set<String> friendkeys = redisService.likePattern("MARRYFRIEND" + marryId + "*");//好友席，八位
        Set<String> keys = redisService.likePattern("MARRY" + marryId + "*");//观礼席，20位
        Set<String> petIdKeys = redisService.likePattern("PETID" + marryId + "*");//观礼席，20位
//        for (String friendkey : friendkeys) {
//            String userRoleId = (String) redisService.get(friendkey);
//
//            JSONObject json2 = new JSONObject();
//            json2.put("userRoleId", userRoleId);
//            json2.put("userId", friendkey.replace("MARRYFRIEND" + marryId, ""));
//            UserPet userPet = petService.getMyPetFollowed(userId);
//            if (userPet != null) {
//                PetInfo petInfoByPetId = petService.getPetInfoByPetId(userPet.getPetId());
//                json2.put("userPetId", petInfoByPetId == null ? "" : petInfoByPetId.getId());
//            }
//            friendList.add(json2);
//        }
        for (String petIdAndRole : petIdKeys) {
            String petAndRoleValue = (String) redisService.get(petIdAndRole);
            log.info(petAndRoleValue);
            String[] split = petAndRoleValue.split("-");
            String role = split[0];
            String petId = split[1];
            JSONObject json2 = new JSONObject();
            json2.put("userRoleId", role);
            json2.put("userId", petIdAndRole.replace("PETID" + marryId, ""));
            json2.put("userPetId", petId);
            friendList.add(json2);
        }
        for (String key : keys) {
            String portraitUrl = (String) redisService.get(key);
            keysList.add(portraitUrl);
        }

        //标记主人是否已参加预约婚礼
        if (list.size() > 0) {
            json.put("code", 0);
            json.put("MarryLogVo", list.get(0));
            log.info("JOINBOOKWEDDING" + marryId + "  " + userId);
            log.info(redisService.get("JOINBOOKWEDDING" + marryId + userId) + "");
            redisService.set("JOINBOOKWEDDING" + marryId + userId, userId, 7200L);
            json.put("marryFriendList", friendList);
            json.put("marryKeyList", keysList);
            return json;
        }

        // 根据婚礼ID 读取婚礼redis好友剩余席位，当席位数为0数返回人数已满
        JSONObject i = distributedLockTemplate.lock(new DistributedLockCallback<JSONObject>() {
            @Override
            public JSONObject process() {
                MarryLogVo marryLogVo = marryLogExtMapper.getMarryLogVoById(marryId);
                //好友席已满，进入观礼席
                if (friendkeys.size() >= MarryConstant.MARRY_SCENE_FRIEND) {
                    //进入观礼席
                    if (keys.size() < MarryConstant.MARRY_SCENE_ATTEND) {

                        if (redisService.exists("MARRY" + marryId + userId)) {
                            //已经在观礼席中
                            json.put("MarryLogVo", marryLogVo);
                            json.put("code", 0);
                            json.put("marryFriendList", friendList);
                            json.put("marryKeyList", keysList);
                            messageService.joinMarry(userId, marryId);
                            joinMarryMessage2(userId, marryId);
                            return json;
                        } else {
                            User user = userService.findByUserId(userId);
                            redisService.set("MARRY" + marryId + userId, user.getPortraitUrl(), Long.valueOf("7200"));
                            keysList.add(user.getPortraitUrl());
                            joinMarryMessage2(userId, marryId);

                            json.put("code", 0);
                            json.put("MarryLogVo", marryLogVo);
                            json.put("marryFriendList", friendList);
                            json.put("marryKeyList", keysList);
                            messageService.joinMarry(userId, marryId);
                            return json;
                        }
                    } else {
                        //在好友位以及嘉宾位都满了之后自动进入游客位置，不限制游客人数
                        redisService.set("MARRYVISITOR" + marryId + userId, userId, Long.valueOf("7200"));
                        json.put("code", 0);
                        json.put("MarryLogVo", marryLogVo);
                        json.put("marryFriendList", friendList);
                        json.put("marryKeyList", keysList);
                        messageService.joinMarry(userId, marryId);
                        return json;
                    }
                } else {//好友席未满
                    if (redisService.exists("MARRYFRIEND" + marryId + userId)) {
                        json.put("code", 0);
                        json.put("MarryLogVo", marryLogVo);
                        json.put("marryFriendList", friendList);
                        json.put("marryKeyList", keysList);
                        messageService.joinMarry(userId, marryId);
                        joinMarryMessage(userId, marryId);
                        return json;
                    } else {
                        UserRole userRole = userRoleService.getUserRoleByUserId(userId);
                        UserPet userPet = petService.getMyPetFollowed(userId);
                        JSONObject json2 = new JSONObject();
                        json2.put("userRoleId", String.valueOf(userRole.getRoleId()));
                        json2.put("userId", userId);
                        if (userPet != null) {
                            PetInfo petInfoByPetId = petService.getPetInfoByPetId(userPet.getPetId());
                            if (petInfoByPetId != null) {
                                json2.put("userPetId", petInfoByPetId.getId());
                                redisService.set("PETID" + marryId + userId, String.valueOf(userRole.getRoleId()) + "-" + petInfoByPetId.getId(), Long.valueOf("7200"));
                            }

                        }

                        joinMarryMessage(userId, marryId);
                        redisService.set("MARRYFRIEND" + marryId + userId, String.valueOf(userRole.getRoleId()), Long.valueOf("7200"));
                        friendList.add(json2);
                        json.put("code", 0);
                        json.put("MarryLogVo", marryLogVo);
                        messageService.joinMarry(userId, marryId);
                        json.put("marryFriendList", friendList);
                        json.put("marryKeyList", keysList);
                        return json;
                    }
                }
            }

            @Override
            public String getLockName() {
                return "lockForMarry";
            }
        });
        return i;
    }

    @Override
    public void leaveWedding(String userId, String marryId) {
        if (redisService.exists("MARRY" + marryId + userId)) {
            redisService.remove("MARRY" + marryId + userId);
            leaveMarryByUserId2(userId, marryId);
        } else if (redisService.exists("MARRYFRIEND" + marryId + userId)) {
            redisService.remove("MARRYFRIEND" + marryId + userId);
            leaveMarryByUserId(userId, marryId);
        }
        Set<Object> list = redisService.setMembers(MarryConstant.MARRY_ENGAGEMENT_LIST);
        if (list.size() > 0) {
            if (list.contains(userId)) {
                redisService.remove(MarryConstant.MARRY_ENGAGEMENT_LIST, userId);
                EngagementLogExample engagementLogExample = new EngagementLogExample();
                engagementLogExample.createCriteria().andOtherIdEqualTo(userId).andMarryStatusNotEqualTo(MarryConstant.MARRY_DECIDED);
                List<EngagementLog> engagementLogList1 = engagementLogMapper.selectByExample(engagementLogExample);

                if (engagementLogList1.size() > 0) {
                    EngagementLog engagementLog = engagementLogList1.get(0);
                    JSONObject json = new JSONObject();
                    json.put("type", "marryResult");
                    json.put("code", 0);
                    json.put("result", 2);
                    messageService.sendMessageToSomeBody(engagementLog.getUserId(), json.toString());

                    engagementLog.setMarryStatus(MarryConstant.MARRY_FALSE);
                    engagementLogMapper.updateByPrimaryKey(engagementLog);
                }
            }
        }
    }

    @Override
    public JSONObject sendWish(String userId, String marryId) {
        JSONObject json = new JSONObject();
        //判断送祝福的次数，如果大于0就生产新的记录，给自己增加经验，通知客户端发送祝福语，这里没有使用配置表
        SendWishLogExample sendWishLogExample = new SendWishLogExample();
        sendWishLogExample.createCriteria().andMarryIdEqualTo(marryId).andUserIdEqualTo(userId);
        List<SendWishLog> sendWishLogList = sendWishLogMapper.selectByExample(sendWishLogExample);
        int expNum = 0;
        Long curExp = 0L;
        if (sendWishLogList.size() > 0) {
            //次数已满
            throw new CheckException("次数已用完!");
        }
        //增加经验，增加送祝福记录
        SendWishLog sendWishLog = new SendWishLog();
        sendWishLog.setCreatedTime(new Date(System.currentTimeMillis()));
        sendWishLog.setMarryId(marryId);
        sendWishLog.setUserId(userId);
        sendWishLog.setId(UUIDUtil.generateUUID());
        sendWishLogMapper.insert(sendWishLog);
        UserRole userRole = userRoleService.getUserRoleByUserId(userId);
        int grade = userRole.getRoleLevel();
        RoleLevelSetting roleLevelSetting = userRoleService.getUserRoleLevelSetting(grade);
        curExp = userRole.getExperience();
        MarrySendWishSettingExample marrySendWishSettingExample = new MarrySendWishSettingExample();
        marrySendWishSettingExample.createCriteria().andGradeEqualTo(grade);
        List<MarrySendWishSetting> list = marrySendWishSettingMapper.selectByExample(marrySendWishSettingExample);
        Map<String, Object> map;
        if (list.size() > 0) {
            MarrySendWishSetting marrySendWishSetting = list.get(0);
            map = userRoleService.updateRoleExp(userId, marrySendWishSetting.getSendWish());
        } else {
            map = userRoleService.updateRoleExp(userId, MarryConstant.MARRY_WISH_NUM);
        }
        //送祝福成功
        json.put("curExp", map.get("currentExp"));
        json.put("maxExp", map.get("fullExp"));
        json.put("roleLevel", map.get("roleLevel"));
        json.put("skillId", map.get("skillId"));
        json.put("weaponId", map.get("weaponId"));
//        json.put("curExp", curExp + expNum);
//        json.put("maxExp", roleLevelSetting.getExp());
        sendWishMessage(userId, marryId);
        return json;
    }

    @Override
    public JSONObject toast(String userId, String marryId) {
        JSONObject json = new JSONObject();
        //判断敬酒的次数，如果大于0就生产新的记录，给自己增加经验，通知客户端发送祝福语
        ToastLogExample toastLogExample = new ToastLogExample();
        toastLogExample.createCriteria().andMarryIdEqualTo(marryId).andUserIdEqualTo(userId);
        List<ToastLog> toastLogList = toastLogMapper.selectByExample(toastLogExample);
        int expNum = 0;
        Long curExp = 0L;
        if (redisService.exists("MARRYFRIEND" + marryId + userId)) {
            if (toastLogList.size() >= MarryConstant.MARRY_SCENE_TOAST_NUM) {
                throw new CheckException("敬酒次数已用完");
            }
        } else if (redisService.exists("MARRY" + marryId + userId)) {
            if (toastLogList.size() >= 1) {
                throw new CheckException("敬酒次数已用完");
            }
        } else {
            throw new CheckException("数据错误不在婚礼现场");
        }
        //增加经验，增加敬酒记录
        ToastLog toastLog = new ToastLog();
        toastLog.setCreatedTime(new Date(System.currentTimeMillis()));
        toastLog.setMarryId(marryId);
        toastLog.setUserId(userId);
        toastLog.setId(UUIDUtil.generateUUID());
        toastLogMapper.insert(toastLog);
        UserRole userRole = userRoleService.getUserRoleByUserId(userId);
        int grade = userRole.getRoleLevel();
        RoleLevelSetting roleLevelSetting = userRoleService.getUserRoleLevelSetting(grade);
        curExp = userRole.getExperience();
        MarrySendWishSettingExample marrySendWishSettingExample = new MarrySendWishSettingExample();
        marrySendWishSettingExample.createCriteria().andGradeEqualTo(grade);
        List<MarrySendWishSetting> list = marrySendWishSettingMapper.selectByExample(marrySendWishSettingExample);
        Map<String, Object> map;
        if (list.size() > 0) {
            MarrySendWishSetting marrySendWishSetting = list.get(0);
            map = userRoleService.updateRoleExp(userId, marrySendWishSetting.getToast());
        } else {
            map = userRoleService.updateRoleExp(userId, MarryConstant.MARRY_TOAST_NUM);
        }

//        result.put("currentExp", oldExp + exp - setting.getExp());
//        result.put("roleLevel", setting.getNextLevel());
//        result.put("fullExp", nextSetting.getExp());
        //敬酒成功
        json.put("curExp", map.get("currentExp"));
        json.put("maxExp", map.get("fullExp"));
        json.put("roleLevel", map.get("roleLevel"));
        json.put("skillId", map.get("skillId"));
        json.put("weaponId", map.get("weaponId"));

        toastMessage(userId, marryId);
        return json;
    }

    /**
     * 敬酒通知婚礼上的所有人
     *
     * @param userId
     * @param marryId
     */
    public void toastMessage(String userId, String marryId) {
        Set<String> friendkeys = redisService.likePattern("MARRYFRIEND" + marryId + "*");//好友席，八位
        Set<String> keys = redisService.likePattern("MARRY" + marryId + "*");//观礼席，20位
        Set<String> visitorKeys = redisService.likePattern("MARRYVISITOR" + marryId + "*");//游客不限制数量
        User userDetail = userService.findByUserId(userId);
        JSONObject json = new JSONObject();
        json.put("type", "toast");
        json.put("code", 0);
        json.put("senderName", userDetail.getName());
        for (String friendkey : friendkeys) {
            String friendId = friendkey.replace("MARRYFRIEND" + marryId, "");
            messageService.sendMessageToSomeBody(friendId, json.toString());
        }
        for (String key : keys) {
            String friendId = key.replace("MARRY" + marryId, "");
            messageService.sendMessageToSomeBody(friendId, json.toString());
        }
        for (String key : visitorKeys) {
            String friendId = key.replace("MARRYVISITOR" + marryId, "");
            messageService.sendMessageToSomeBody(friendId, json.toString());
        }
        MarryLogExample marryLogExample = new MarryLogExample();
        marryLogExample.createCriteria().andIdEqualTo(marryId);
        List<MarryLog> list = marryLogMapper.selectByExample(marryLogExample);
        if (list.size() > 0) {
            MarryLog marryLog = list.get(0);
            String userId1 = marryLog.getUserId();
            String otherId1 = marryLog.getOtherId();
            messageService.sendMessageToSomeBody(userId1, json.toString());
            messageService.sendMessageToSomeBody(otherId1, json.toString());
        }
    }

    /**
     * 祝福通知婚礼上的所有人
     *
     * @param userId
     * @param marryId
     */
    public void sendWishMessage(String userId, String marryId) {
        Set<String> friendkeys = redisService.likePattern("MARRYFRIEND" + marryId + "*");//好友席，八位
        Set<String> keys = redisService.likePattern("MARRY" + marryId + "*");//观礼席，20位
        Set<String> visitorKeys = redisService.likePattern("MARRYVISITOR" + marryId + "*");//游客不限制数量
        User userDetail = userService.findByUserId(userId);
        JSONObject json = new JSONObject();
        json.put("type", "wish");
        json.put("code", 0);
        json.put("senderName", userDetail.getName());
        for (String friendkey : friendkeys) {
            String friendId = friendkey.replace("MARRYFRIEND" + marryId, "");
            messageService.sendMessageToSomeBody(friendId, json.toString());
        }
        for (String key : keys) {
            String friendId = key.replace("MARRY" + marryId, "");
            messageService.sendMessageToSomeBody(friendId, json.toString());
        }
        for (String key : keys) {
            String friendId = key.replace("MARRY" + marryId, "");
            messageService.sendMessageToSomeBody(friendId, json.toString());
        }
        for (String key : visitorKeys) {
            String friendId = key.replace("MARRY" + marryId, "");
            messageService.sendMessageToSomeBody(friendId, json.toString());
        }
        MarryLogExample marryLogExample = new MarryLogExample();
        marryLogExample.createCriteria().andIdEqualTo(marryId);
        List<MarryLog> list = marryLogMapper.selectByExample(marryLogExample);
        if (list.size() > 0) {
            MarryLog marryLog = list.get(0);
            String userId1 = marryLog.getUserId();
            String otherId1 = marryLog.getOtherId();
            messageService.sendMessageToSomeBody(userId1, json.toString());
            messageService.sendMessageToSomeBody(otherId1, json.toString());
        }
    }

    /**
     * 好友进入通知婚礼上的所有人
     *
     * @param userId
     * @param marryId
     */
    public void joinMarryMessage(String userId, String marryId) {
        Set<String> friendkeys = redisService.likePattern("MARRYFRIEND" + marryId + "*");//好友席，八位
        Set<String> keys = redisService.likePattern("MARRY" + marryId + "*");//观礼席，20位
        Set<String> visitorKeys = redisService.likePattern("MARRYVISITOR" + marryId + "*");//游客不限制数量
        User userDetail = userService.findByUserId(userId);
        int userRoleId = userDetail.getRoleId();
        UserPet userPet = petService.getMyPetFollowed(userId);
        JSONObject json = new JSONObject();
        json.put("type", "joinWeddingFriend");
        json.put("code", 0);
        json.put("userId", userId);
        json.put("userRoleId", userRoleId);
        if (userPet != null) {
            PetInfo petInfoByPetId = petService.getPetInfoByPetId(userPet.getPetId());
            json.put("userPetId", petInfoByPetId == null ? "" : petInfoByPetId.getId());
        }
        for (String friendkey : friendkeys) {
            String friendId = friendkey.replace("MARRYFRIEND" + marryId, "");
            messageService.sendMessageToSomeBody(friendId, json.toString());
        }
        for (String key : keys) {
            String friendId = key.replace("MARRY" + marryId, "");
            messageService.sendMessageToSomeBody(friendId, json.toString());
        }
        for (String key : visitorKeys) {
            String friendId = key.replace("MARRYVISITOR" + marryId, "");
            messageService.sendMessageToSomeBody(friendId, json.toString());
        }
        MarryLogExample marryLogExample = new MarryLogExample();
        marryLogExample.createCriteria().andIdEqualTo(marryId);
        List<MarryLog> list = marryLogMapper.selectByExample(marryLogExample);
        if (list.size() > 0) {
            MarryLog marryLog = list.get(0);
            String userId1 = marryLog.getUserId();
            String otherId1 = marryLog.getOtherId();
            messageService.sendMessageToSomeBody(userId1, json.toString());
            messageService.sendMessageToSomeBody(otherId1, json.toString());
        }
    }

    /**
     * 嘉宾进入通知婚礼上的所有人
     *
     * @param userId
     * @param marryId
     */
    public void joinMarryMessage2(String userId, String marryId) {
        Set<String> friendkeys = redisService.likePattern("MARRYFRIEND" + marryId + "*");//好友席，八位
        Set<String> keys = redisService.likePattern("MARRY" + marryId + "*");//观礼席，20位
        Set<String> visitorKeys = redisService.likePattern("MARRYVISITOR" + marryId + "*");//游客不限制数量
        User userDetail = userService.findByUserId(userId);
        String headUrl = userDetail.getPortraitUrl();
        JSONObject json = new JSONObject();
        json.put("type", "joinWeddingGuest");
        json.put("code", 0);
        json.put("userId", userId);
        json.put("headUrl", headUrl);
        for (String friendkey : friendkeys) {
            String friendId = friendkey.replace("MARRYFRIEND" + marryId, "");
            //不给自己发通知
            if (!friendId.equals(userId)) {
                messageService.sendMessageToSomeBody(friendId, json.toString());
            }
        }
        for (String key : keys) {
            String friendId = key.replace("MARRY" + marryId, "");
            if (!friendId.equals(userId)) {
                messageService.sendMessageToSomeBody(friendId, json.toString());
            }
        }
        for (String key : visitorKeys) {
            String friendId = key.replace("MARRYVISITOR" + marryId, "");
            if (!friendId.equals(userId)) {
                messageService.sendMessageToSomeBody(friendId, json.toString());
            }
        }
        MarryLogExample marryLogExample = new MarryLogExample();
        marryLogExample.createCriteria().andIdEqualTo(marryId);
        List<MarryLog> list = marryLogMapper.selectByExample(marryLogExample);
        if (list.size() > 0) {
            MarryLog marryLog = list.get(0);
            String userId1 = marryLog.getUserId();
            String otherId1 = marryLog.getOtherId();
            messageService.sendMessageToSomeBody(userId1, json.toString());
            messageService.sendMessageToSomeBody(otherId1, json.toString());

        }
    }

    /**
     * 好友在婚礼进行时离开别人的婚礼,通知其他人显示改变
     *
     * @param userId
     * @param marryId
     */
    public void leaveMarryByUserId(String userId, String marryId) {
        Set<String> friendkeys = redisService.likePattern("MARRYFRIEND" + marryId + "*");//好友席，八位
        Set<String> keys = redisService.likePattern("MARRY" + marryId + "*");//观礼席，20位
        Set<String> visitorKeys = redisService.likePattern("MARRYVISITOR" + marryId + "*");//游客不限制数量
        JSONObject json = new JSONObject();
        json.put("type", "leaveWeddingFriend");
        json.put("code", 0);
        json.put("userId", userId);
        for (String friendkey : friendkeys) {
            String friendId = friendkey.replace("MARRYFRIEND" + marryId, "");
            if (!friendId.equals(userId)) {
                messageService.sendMessageToSomeBody(friendId, json.toString());
            }
        }
        for (String key : keys) {
            String friendId = key.replace("MARRY" + marryId, "");
            messageService.sendMessageToSomeBody(friendId, json.toString());
        }
        for (String key : visitorKeys) {
            String friendId = key.replace("MARRYVISITOR" + marryId, "");
            messageService.sendMessageToSomeBody(friendId, json.toString());
        }
        MarryLogExample marryLogExample = new MarryLogExample();
        marryLogExample.createCriteria().andIdEqualTo(marryId);
        List<MarryLog> list = marryLogMapper.selectByExample(marryLogExample);
        if (list.size() > 0) {
            MarryLog marryLog = list.get(0);
            String userId1 = marryLog.getUserId();
            String otherId1 = marryLog.getOtherId();
            messageService.sendMessageToSomeBody(userId1, json.toString());
            messageService.sendMessageToSomeBody(otherId1, json.toString());
        }
    }

    /**
     * 嘉宾在婚礼进行时离开别人的婚礼，通知其他人显示改变
     *
     * @param userId
     * @param marryId
     */
    public void leaveMarryByUserId2(String userId, String marryId) {
        Set<String> friendkeys = redisService.likePattern("MARRYFRIEND" + marryId + "*");//好友席，八位
        Set<String> keys = redisService.likePattern("MARRY" + marryId + "*");//观礼席，20位
        Set<String> visitorKeys = redisService.likePattern("MARRYVISITOR" + marryId + "*");//游客不限制数量
        JSONObject json = new JSONObject();
        json.put("type", "leaveWeddingGuest");
        json.put("code", 0);
        json.put("userId", userId);
        for (String friendkey : friendkeys) {
            String friendId = friendkey.replace("MARRYFRIEND" + marryId, "");
            messageService.sendMessageToSomeBody(friendId, json.toString());
        }
        for (String key : keys) {
            String friendId = key.replace("MARRY" + marryId, "");
            messageService.sendMessageToSomeBody(friendId, json.toString());
        }
        for (String key : visitorKeys) {
            String friendId = key.replace("MARRYVISITOR" + marryId, "");
            messageService.sendMessageToSomeBody(friendId, json.toString());
        }
        MarryLogExample marryLogExample = new MarryLogExample();
        marryLogExample.createCriteria().andIdEqualTo(marryId);
        List<MarryLog> list = marryLogMapper.selectByExample(marryLogExample);
        if (list.size() > 0) {
            MarryLog marryLog = list.get(0);
            String userId1 = marryLog.getUserId();
            String otherId1 = marryLog.getOtherId();
            messageService.sendMessageToSomeBody(userId1, json.toString());
            messageService.sendMessageToSomeBody(otherId1, json.toString());
        }
    }

    @Override
    public void leaveMarry(String userId, String marryId) {
        if (redisService.exists("MARRYFRIEND" + marryId + userId)) {
            leaveMarryByUserId(userId, marryId);
            redisService.remove("MARRYFRIEND" + marryId + userId);
            redisService.remove("PETID" + marryId + userId);
        } else if (redisService.exists("MARRY" + marryId + userId)) {
            leaveMarryByUserId2(userId, marryId);
            redisService.remove("MARRY" + marryId + userId);
        } else if (redisService.exists("MARRYVISITOR" + marryId + userId)) {
            redisService.remove("MARRYVISITOR" + marryId + userId);
        } else {
            MarryLogExample marryLogExample = new MarryLogExample();
            marryLogExample.createCriteria().andIdEqualTo(marryId);
            List<MarryLog> list = marryLogMapper.selectByExample(marryLogExample);
            if (list.size() > 0) {
                MarryLog marryLog = list.get(0);
                String userId1 = marryLog.getUserId();
                String otherId1 = marryLog.getOtherId();
                if (userId.equals(otherId1) || userId.equals(userId1)) {
                } else {
                    throw new CheckException("用户ID错误，不存在于该婚礼现场！");
                }
            }

        }
    }


    @Override
    public JSONObject getLoveTreeInfo(String userId) throws Exception {
        JSONObject json = new JSONObject();
        // TODO 检查对方是否在线
        EngagementLogExample engagementLogExample = new EngagementLogExample();
        Criteria criteria = engagementLogExample.createCriteria();
        Criteria criteria2 = engagementLogExample.createCriteria();
        criteria.andUserIdEqualTo(userId).andMarryStatusEqualTo(MarryConstant.MARRY_SUCCESS);
        criteria2.andOtherIdEqualTo(userId).andMarryStatusEqualTo(MarryConstant.MARRY_SUCCESS);
        engagementLogExample.or(criteria2);
        engagementLogExample.setOrderByClause(" created_time DESC ");
        String engagementId;
        String goodsId;
        Long upgradeHappinessNum;
        Integer upgradeGoodsId;
        Integer level;
        LoveTreeInfoExample loveTreeInfoExample1 = new LoveTreeInfoExample();
        loveTreeInfoExample1.createCriteria().andUserIdEqualTo(userId);
        List<LoveTreeInfo> loveTreeInfos = loveTreeInfoMapper.selectByExample(loveTreeInfoExample1);
        if (loveTreeInfos.size() > 0) {
            LoveTreeInfo loveTreeInfo = loveTreeInfos.get(0);
            engagementId = loveTreeInfo.getEngagementId();
            goodsId = "" + userGoodsService.getUserWeddingRingId(userId);
            Map map = userGoodsService.getUserWeddingRingHappinessNum(userId);
            upgradeHappinessNum = Long.valueOf(map.get("upgradeHappinessNum") + "");
            upgradeGoodsId = Integer.valueOf(map.get("upgradeGoodsId") + "");
            level = Integer.valueOf(map.get("level") + "");
        } else {
            json.put("code", -1);
            return json;
        }
//        if (engagementLogList.size() > 0) {
//            engagementId = engagementLogList.get(0).getId();
//            goodsId = engagementLogList.get(0).getUserRingId();
//        } else {
//            json.put("code", -1);
//            return json;
//        }
        LoveTreeExample loveTreeExample = new LoveTreeExample();
        loveTreeExample.createCriteria().andEngagementIdEqualTo(engagementId);
        List<LoveTree> list = loveTreeMapper.selectByExample(loveTreeExample);
        LoveTreeInfoExample loveTreeInfoExample = new LoveTreeInfoExample();
        loveTreeInfoExample.createCriteria().andUserIdEqualTo(userId).andEngagementIdEqualTo(engagementId);
        List<LoveTreeInfo> list2 = loveTreeInfoMapper.selectByExample(loveTreeInfoExample);
        if (list.size() > 0 && list2.size() > 0) {
            LoveTree loveTree = list.get(0);
            LoveTreeInfo loveTreeInfo = list2.get(0);
            String otherId = loveTreeInfo.getOtherId();
            User user = userService.findByUserId(userId);
            String id = loveTreeInfo.getLoveTreeId();
            //将调取接口的人存入，表明进入了婚房
            redisService.set("WEDDING" + id + userId, userId);
            redisService.set("WEDDINGID" + userId, id);
            if (redisService.exists("WEDDING" + id + otherId)) {
                UserPet userPet = petService.getMyPetFollowed(userId);
                //发送通知对方，自己的进入
                JSONObject json2 = new JSONObject();
                json2.put("type", "enterWeddingRoom");
                json2.put("code", 0);
                json2.put("userId", userId);
                if (userPet != null) {
                    PetInfo petInfoByPetId = petService.getPetInfoByPetId(userPet.getPetId());
                    json2.put("userPetId", petInfoByPetId == null ? "" : petInfoByPetId.getId());
                }
                messageService.sendMessageToSomeBody(otherId, json2.toString());
            }

            LoveTreeSettingExample loveTreeSettingExample = new LoveTreeSettingExample();
            loveTreeSettingExample.createCriteria().andGradeEqualTo(loveTree.getLoveTreeGrade());
            List<LoveTreeSetting> loveTreeSettinglist = loveTreeSettingMapper.selectByExample(loveTreeSettingExample);
            LoveTreeSetting loveTreeSetting = loveTreeSettinglist.get(0);

            Map<String, Integer> map = getPerproty(loveTreeSetting);

            UserRole userRole = userRoleService.getUserRoleByUserId(userId);
            User grilUser = userService.findByUserId(otherId);
            UserRole grilUserRole = userRoleService.getUserRoleByUserId(otherId);
            UserPet userPet = petService.getMyPetFollowed(userId);
            UserPet grilUserPet = petService.getMyPetFollowed(otherId);

            LoveTreeInfoVo loveTreeInfoVo = new LoveTreeInfoVo();
            loveTreeInfoVo.setEngagementId(engagementId);
            loveTreeInfoVo.setExpLimit(loveTree.getExpLimit());
            loveTreeInfoVo.setExpNum(loveTree.getExpNum());
            loveTreeInfoVo.setHappinessNum(loveTreeInfo.getHappinessNum());
            loveTreeInfoVo.setLoveNum(loveTreeInfo.getLoveNum());
            loveTreeInfoVo.setPlayJokesNum(loveTreeInfo.getPlayJokesNum());
            loveTreeInfoVo.setWeddingNum(loveTreeInfo.getWeddingNum());
            loveTreeInfoVo.setTreeGrade(loveTree.getLoveTreeGrade());
            loveTreeInfoVo.setRingGradeNum(level);
            loveTreeInfoVo.setRingId(loveTreeInfo.getWeddingRingId());
            loveTreeInfoVo.setGoodsId(goodsId);
            loveTreeInfoVo.setUpgradeHappinessNum(upgradeHappinessNum);
            loveTreeInfoVo.setUpgradeGoodsId(upgradeGoodsId);
            loveTreeInfoVo.setId(loveTreeInfo.getLoveTreeId());
            loveTreeInfoVo.setProperties(map);
            if (userRole.getSex() == MarryConstant.MARRY_BOY_TYPE) {
                loveTreeInfoVo.setUserName(user.getName());
                if (userPet != null) {
                    loveTreeInfoVo.setUserPetId(userPet.getPetId());
                }
                if (grilUserPet != null) {
                    loveTreeInfoVo.setGrilPetId(grilUserPet.getPetId());
                }
                loveTreeInfoVo.setUserRoleId(String.valueOf(userRole.getRoleId()));
                loveTreeInfoVo.setGrilName(grilUser.getName());
                loveTreeInfoVo.setGrilRoleId(String.valueOf(grilUserRole.getRoleId()));
                if (redisService.exists("WEDDING" + id + otherId)) {
                    loveTreeInfoVo.setOtherSatatus(1);
                } else {
                    loveTreeInfoVo.setOtherSatatus(0);
                }
                loveTreeInfoVo.setUserId(userId);
                loveTreeInfoVo.setOtherId(otherId);
            } else {
                loveTreeInfoVo.setUserName(grilUser.getName());
                if (userPet != null) {
                    loveTreeInfoVo.setGrilPetId(userPet.getPetId());
                }
                if (grilUserPet != null) {
                    loveTreeInfoVo.setUserPetId(grilUserPet.getPetId());
                }
                loveTreeInfoVo.setUserRoleId(String.valueOf(grilUserRole.getRoleId()));
                loveTreeInfoVo.setGrilName(user.getName());
                loveTreeInfoVo.setGrilRoleId(String.valueOf(userRole.getRoleId()));
                if (redisService.exists("WEDDING" + id + otherId)) {
                    loveTreeInfoVo.setOtherSatatus(1);
                } else {
                    loveTreeInfoVo.setOtherSatatus(0);
                }
                loveTreeInfoVo.setUserId(otherId);
                loveTreeInfoVo.setOtherId(userId);
            }
            json.put("code", 0);
            json.put("result", loveTreeInfoVo);
            return json;
        } else {
            json.put("code", -1);
            return json;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject playJokes(String userId, String engagementId) {
//		要判断双方是否在线
        JSONObject json = new JSONObject();
        LoveTreeExample loveTreeExample = new LoveTreeExample();
        loveTreeExample.createCriteria().andEngagementIdEqualTo(engagementId);
        List<LoveTree> list = loveTreeMapper.selectByExample(loveTreeExample);
        if (list.size() > 0) {
            LoveTree loveTree = list.get(0);
            String id = loveTree.getId();
            if (redisService.likePattern("WEDDING" + id + "*").size() < 2) {
                throw new CheckException("配偶不在线不能进行互动活动！");
            }

            //取出嬉闹次数，判断是否大于3，然后确定是否可以进行嬉闹，各自消耗各自的嬉闹次数，增加相应的东西
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(currentTime);
            PlayJokesLogExample playJokesLogExample = new PlayJokesLogExample();

            String loveTreeId = loveTree.getId();
            playJokesLogExample.createCriteria().andLoveTreeIdEqualTo(loveTreeId).andUserIdEqualTo(userId).andPlayJokesDayEqualTo(dateString);
            List<PlayJokesLog> playJokesLoglist = playJokesLogMapper.selectByExample(playJokesLogExample);
            if (playJokesLoglist.size() < MarryConstant.MARRY_SCENE_TOAST_NUM) {
                PlayJokesLog playJokesLog = new PlayJokesLog();
                playJokesLog.setLoveTreeId(loveTreeId);
                playJokesLog.setPlayJokesDay(dateString);
                playJokesLog.setUserId(userId);
                playJokesLogMapper.insert(playJokesLog);

                LoveTreeInfoExample loveTreeInfoExample = new LoveTreeInfoExample();
                loveTreeInfoExample.createCriteria().andUserIdEqualTo(userId).andEngagementIdEqualTo(engagementId);
                List<LoveTreeInfo> list2 = loveTreeInfoMapper.selectByExample(loveTreeInfoExample);
                if (list2.size() > 0) {
                    LoveTreeInfo loveTreeInfo = list2.get(0);
                    loveTreeInfo.setPlayJokesNum(loveTreeInfo.getPlayJokesNum() - 1);
                    loveTreeInfoMapper.updateByPrimaryKey(loveTreeInfo);
                }

                int num = createRandomNum(MarryConstant.MARRY_LOVE_PLAYJOKES_MAX, MarryConstant.MARRY_LOVE_CRIT_NUM);
                addLoveByUserId(userId, num);
                json.put("code", 0);
                json.put("addLoveNum", num);
                return json;
            } else {
                throw new CheckException("嬉闹次数已用完");
            }
        } else {
            throw new CheckException("婚房信息错误");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject bridegroom(String userId, String engagementId) {
        JSONObject json = new JSONObject();
//		要判断双方是否在线
        LoveTreeExample loveTreeExample = new LoveTreeExample();
        loveTreeExample.createCriteria().andEngagementIdEqualTo(engagementId);
        List<LoveTree> list = loveTreeMapper.selectByExample(loveTreeExample);
        if (list.size() > 0) {
            LoveTree loveTree = list.get(0);
            //取出d洞房次数，判断是否大于3，然后确定是否可以进行嬉闹，消耗各自的次数
            String id = loveTree.getId();
            if (redisService.likePattern("WEDDING" + id + "*").size() < 2) {
                throw new CheckException("配偶不在线不能进行互动活动！");
            }

            Date currentTime = new Date(System.currentTimeMillis());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(currentTime);
            String loveTreeId = loveTree.getId();

            BridegroomLogExample bridegroomLogExample = new BridegroomLogExample();
            bridegroomLogExample.createCriteria().andBridegroomDayEqualTo(dateString).andUserIdEqualTo(userId).andLoveTreeIdEqualTo(loveTreeId);
            List<BridegroomLog> bridegroomLoglist = bridegroomLogMapper.selectByExample(bridegroomLogExample);
            if (bridegroomLoglist.size() < MarryConstant.MARRY_SCENE_TOAST_NUM) {
                BridegroomLog bridegroomLog = new BridegroomLog();
                bridegroomLog.setLoveTreeId(loveTreeId);
                bridegroomLog.setBridegroomDay(dateString);
                bridegroomLog.setUserId(userId);
                bridegroomLogMapper.insert(bridegroomLog);

                LoveTreeInfoExample loveTreeInfoExample = new LoveTreeInfoExample();
                loveTreeInfoExample.createCriteria().andUserIdEqualTo(userId).andEngagementIdEqualTo(engagementId);
                List<LoveTreeInfo> list2 = loveTreeInfoMapper.selectByExample(loveTreeInfoExample);
                if (list2.size() > 0) {
                    LoveTreeInfo loveTreeInfo = list2.get(0);
                    loveTreeInfo.setWeddingNum(loveTreeInfo.getWeddingNum() - 1);
                    loveTreeInfoMapper.updateByPrimaryKey(loveTreeInfo);
                }

                int num = createRandomNum(MarryConstant.MARRY_LOVE_WEDDING_MAX, MarryConstant.MARRY_LOVE_WEDDING_MIN);
                addLoveByUserId(userId, num);

                json.put("code", 0);
                json.put("addLoveNum", num);
                return json;
            } else {
                throw new CheckException("洞房次数已用完！");
            }
        } else {
            throw new CheckException("婚房数据错误！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updateLove(String userId, String engagementId) throws Exception {
        JSONObject json = new JSONObject();
        LoveTreeExample loveTreeExample = new LoveTreeExample();
        loveTreeExample.createCriteria().andEngagementIdEqualTo(engagementId);
        List<LoveTree> list = loveTreeMapper.selectByExample(loveTreeExample);
        if (list.size() > 0) {
            LoveTree loveTree = list.get(0);
            if (loveTree != null && loveTree.getLoveTreeGrade() >= 90) {
                throw new CheckException("爱情树等级已满，不能升级了");
            }
            String loveTreeId = loveTree.getId();
            LoveTreeInfoExample loveTreeInfoExample = new LoveTreeInfoExample();
            loveTreeInfoExample.createCriteria().andEngagementIdEqualTo(engagementId).andLoveTreeIdEqualTo(loveTreeId).andUserIdEqualTo(userId);
            List<LoveTreeInfo> loveTreeInfoList = loveTreeInfoMapper.selectByExample(loveTreeInfoExample);
            if (loveTreeInfoList.size() > 0) {
                LoveTreeInfo loveTreeInfo = loveTreeInfoList.get(0);
                int loveGrade = loveTreeInfo.getLoveGrade();
                Long loveNum = loveTreeInfo.getLoveNum();
                if (loveGrade == MarryConstant.MARRY_LOVE_GRADE_MAX) {
                    //等级已满，不能升级了
                    throw new CheckException("情缘等级已满，不能升级了");
                }
                //根据等级获取配置表
                LoveSettingExample loveSettingExample = new LoveSettingExample();
                loveSettingExample.createCriteria().andGradeEqualTo(loveGrade);
                List<LoveSetting> loveSettingList = loveSettingMapper.selectByExample(loveSettingExample);
                if (loveSettingList.size() > 0) {
                    LoveSetting loveSetting = loveSettingList.get(0);
                    loveSettingExample = new LoveSettingExample();
                    loveSettingExample.createCriteria().andGradeEqualTo(loveGrade + 1);
                    List<LoveSetting> loveSettingList2 = loveSettingMapper.selectByExample(loveSettingExample);
                    if (loveSettingList2.size() > 0) {
                        LoveSetting loveSetting2 = loveSettingList2.get(0);
                        int nextLoveNum = loveSetting2.getLoveNum();
                        Map<String, Integer> map = getPerproty(loveSetting2);
                        json.put("propertyAllList", map);
                        json.put("gradeNum", loveGrade + 1);
                        json.put("nextLoveNum", nextLoveNum);
                    }

                    int consumeLoveNum = loveSetting.getLoveNum();

                    if (loveNum > consumeLoveNum) {
                        loveNum = loveNum - consumeLoveNum;
                        json.put("loveNum", loveNum);

                        loveGrade = loveGrade + 1;
                        loveTreeInfo.setLoveGrade(loveGrade);
                        loveTreeInfo.setLoveNum(loveNum);
                        loveTreeInfoMapper.updateByPrimaryKey(loveTreeInfo);
                    } else {
                        //情缘值不足
                        throw new CheckException("情缘值不足!");
                    }
                } else {
                    throw new CheckException("数据异常");
                }
            }
        }
        return json;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updateLoveTree(String userId, String engagementId) throws Exception {
        JSONObject json = new JSONObject();
        LoveTreeExample loveTreeExample = new LoveTreeExample();
        loveTreeExample.createCriteria().andEngagementIdEqualTo(engagementId);
        List<LoveTree> list = loveTreeMapper.selectByExample(loveTreeExample);
        if (list.size() > 0) {
            LoveTree loveTree = list.get(0);
            int num = 0;
            int newExpNum = 0;
            if (CommonUtil.getChance(MarryConstant.MARRY_LOVE_CRIT_NUM)) {
                num = createRandomNum(700, 500);
            } else {
                num = createRandomNum(350, 250);
            }
            int exp = loveTree.getExpNum();
            int expLimit = loveTree.getExpLimit();
            String otherId = new String();

            LoveTreeSettingExample loveTreeSettingExample = new LoveTreeSettingExample();
            loveTreeSettingExample.createCriteria().andGradeEqualTo(loveTree.getLoveTreeGrade());
            List<LoveTreeSetting> list2 = loveTreeSettingMapper.selectByExample(loveTreeSettingExample);
            if (list2.size() > 0) {
/*				Map<String, Integer> map = getPerproty(list2.get(0));
                json.put("propertyAllList", map);*/
                loveTree.setExpLimit(list2.get(0).getExpLimit());
            }

            LoveTreeInfoExample loveTreeInfoExample = new LoveTreeInfoExample();
            loveTreeInfoExample.createCriteria().andLoveTreeIdEqualTo(loveTree.getId());
            List<LoveTreeInfo> list3 = loveTreeInfoMapper.selectByExample(loveTreeInfoExample);
            if (list3.size() > 0) {
                LoveTreeInfo loveTreeInfo = list3.get(0);
                if (userId.equals(loveTreeInfo.getUserId())) {
                    otherId = loveTreeInfo.getOtherId();
                } else {
                    otherId = loveTreeInfo.getUserId();
                }
            }

            UserGoods userGoods = userGoodsService.getGoodsCount(userId, MarryConstant.MARRY_CONSUMABLES_STONE);
            if (userGoods == null) {
                throw new CheckException("心意石不存在");
            }
            if (userGoods.getAmount() > 0) {
                if (expLimit == exp) {
                    throw new CheckException("经验值已满无法增加，请突破后再增加！");
                } else if (expLimit - exp > num) {
                    loveTree.setExpNum(exp + num);
                    newExpNum = exp + num;
                } else {
                    loveTree.setExpNum(expLimit);
                    newExpNum = expLimit;
                }

                loveTreeMapper.updateByPrimaryKey(loveTree);

                JSONObject json2 = new JSONObject();
                json2.put("type", "updateLoveTreeProperty");
                json2.put("code", 0);
                json2.put("msgtype", 1);
                json2.put("exp", newExpNum);
                messageService.sendMessageToSomeBody(otherId, json2.toString());

                subUserMoney(userId, MarryConstant.MARRY_CONSUMABLES_STONE, 1);
                ReceiveGoldVo receiveGoldVo = new ReceiveGoldVo();
                receiveGoldVo.setAmount(userGoods.getAmount());
                receiveGoldVo.setGoodsId(Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_STONE));
                receiveGoldVo.setId(MarryConstant.MARRY_CONSUMABLES_STONE);
                receiveGoldVo.setType(BuildingConstant.Prop);

                json.put("result", receiveGoldVo);
                json.put("expNum", newExpNum);
                return json;
            } else {
                throw new CheckException("心意石不足");
            }
        } else {
            throw new CheckException("数据异常");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject breakLoveTree(String userId, String engagementId) throws Exception {
        JSONObject json = new JSONObject();
        LoveTreeExample loveTreeExample = new LoveTreeExample();
        loveTreeExample.createCriteria().andEngagementIdEqualTo(engagementId);
        List<LoveTree> list = loveTreeMapper.selectByExample(loveTreeExample);
        if (list.size() > 0) {
            LoveTree loveTree = list.get(0);
            int exp = loveTree.getExpNum();
            int expLimit = loveTree.getExpLimit();
            String otherId = new String();
            if (loveTree.getLoveTreeGrade() == MarryConstant.MARRY_TREE_GRADE_MAX) {
                throw new CheckException("已达到最高等级！");
            }
            UserGoods userGoods = userGoodsService.getGoodsCount(userId, MarryConstant.MARRY_CONSUMABLES_LOVE_STONE);
            if (userGoods == null) {
                //余额不足
                throw new CheckException("情意石不足！");
            }
            if (userGoods.getAmount() > 0) {
                if (expLimit == exp && expLimit > 0) {
                    int grade = loveTree.getLoveTreeGrade() + 1;
                    loveTree.setLoveTreeGrade(grade);
                    loveTree.setExpNum(0);

                    int newExpLimit = 0;

                    //查询出新的等级下的最高经验值
                    LoveTreeSettingExample loveTreeSettingExample = new LoveTreeSettingExample();
                    loveTreeSettingExample.createCriteria().andGradeEqualTo(grade);
                    List<LoveTreeSetting> loveTreeSettingList = loveTreeSettingMapper.selectByExample(loveTreeSettingExample);
                    if (loveTreeSettingList.size() > 0) {
                        newExpLimit = loveTreeSettingList.get(0).getExpLimit();
                        Map<String, Integer> map = getPerproty(loveTreeSettingList.get(0));
                        json.put("propertyAllList", map);
                        loveTree.setExpLimit(loveTreeSettingList.get(0).getExpLimit());
                    }
                    loveTreeMapper.updateByPrimaryKey(loveTree);

                    LoveTreeInfoExample loveTreeInfoExample = new LoveTreeInfoExample();
                    loveTreeInfoExample.createCriteria().andLoveTreeIdEqualTo(loveTree.getId());
                    List<LoveTreeInfo> list3 = loveTreeInfoMapper.selectByExample(loveTreeInfoExample);
                    if (list3.size() > 0) {
                        LoveTreeInfo loveTreeInfo = list3.get(0);
                        if (userId.equals(loveTreeInfo.getUserId())) {
                            otherId = loveTreeInfo.getOtherId();
                        } else {
                            otherId = loveTreeInfo.getUserId();
                        }
                    }

                    JSONObject json2 = new JSONObject();
                    json2.put("type", "updateLoveTreeProperty");
                    json2.put("code", 0);
                    json2.put("msgtype", 2);
                    messageService.sendMessageToSomeBody(otherId, json2.toString());

                    if (grade % 10 == 0 && grade != 0) {
                        int taskId = TreelovelvlEnum.getTaskId(grade);
                        friendService.taskBranchFinish(taskId, userId);
                    }

                    //消耗情义石
                    subUserMoney(userId, MarryConstant.MARRY_CONSUMABLES_LOVE_STONE, 1);
                    ReceiveGoldVo receiveGoldVo = new ReceiveGoldVo();
                    receiveGoldVo.setAmount(userGoods.getAmount());
                    receiveGoldVo.setGoodsId(Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_LOVE_STONE));
                    receiveGoldVo.setId(MarryConstant.MARRY_CONSUMABLES_LOVE_STONE);
                    receiveGoldVo.setType(BuildingConstant.Prop);

                    json.put("code", 0);
                    json.put("result", receiveGoldVo);
                    json.put("treeGrade", grade);
                    json.put("newExpLimit", newExpLimit);
                    return json;
                } else {
                    throw new CheckException("经验值未满！");
                }
            } else {
                throw new CheckException("情意石不足！");
            }
        } else {
            throw new CheckException("数据错误！");
        }
    }

    /**
     * 离婚
     *
     * @param userId
     * @param engagementId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int divorce(String userId, String engagementId) {
        EngagementLogExample engagementLogExample = new EngagementLogExample();
        engagementLogExample.createCriteria().andIdEqualTo(engagementId);
        List<EngagementLog> engagementLogList = engagementLogMapper.selectByExample(engagementLogExample);
        if (engagementLogList.size() > 0) {
            EngagementLog engagementLog = engagementLogList.get(0);
            if (engagementLog.getMarryStatus() == MarryConstant.MARRY_SUCCESS) {
                engagementLog.setMarryStatus(MarryConstant.MARRY_OVERDUE);
            } else if (engagementLog.getMarryStatus() == MarryConstant.MARRY_TRUE) {
                engagementLog.setMarryStatus(MarryConstant.MARRY_FALSE);
            }

            engagementLogMapper.updateByPrimaryKey(engagementLog);
            String otherId = new String();
            if (engagementLog.getUserId().equals(userId)) {
                otherId = engagementLog.getOtherId();
            } else {
                otherId = engagementLog.getUserId();
            }

            EngagementCancleLog engagementCancleLog = new EngagementCancleLog();
            engagementCancleLog.setCreatedTime(new Date(System.currentTimeMillis()));
            engagementCancleLog.setUserId(userId);
            engagementCancleLog.setOtherId(otherId);
            engagementCancleLog.setId(UUIDUtil.generateUUID());
            engagementCancleLogMapper.insert(engagementCancleLog);

            String loveTreeId = new String();

            LoveTreeExample loveTreeExample = new LoveTreeExample();
            loveTreeExample.createCriteria().andEngagementIdEqualTo(engagementId);
            List<LoveTree> list = loveTreeMapper.selectByExample(loveTreeExample);
            if (list.size() > 0) {
                LoveTree loveTree = list.get(0);
                loveTreeId = loveTree.getId();
                loveTreeMapper.deleteByPrimaryKey(loveTreeId);
            }
            //删除角色配偶的信息
            userRoleService.deleteMarrySpouse(userId);
            userRoleService.deleteMarrySpouse(otherId);
            /*  五个等级的结婚戒指 10001 同心戒指 10002	比翼双飞	10003 约定三生 10004 此情不渝 10005	海枯石烂	 10006 天荒地老*/
            //根据loveTreeId查出男女各自的情缘以及戒指记录
            LoveTreeInfoExample loveTreeInfoExample = new LoveTreeInfoExample();
            LoveTreeInfoExample.Criteria criteria = loveTreeInfoExample.createCriteria();
            LoveTreeInfoExample.Criteria criteria2 = loveTreeInfoExample.createCriteria();
            criteria.andEngagementIdEqualTo(engagementId).andLoveTreeIdEqualTo(loveTreeId).andUserIdEqualTo(userId);
            criteria2.andEngagementIdEqualTo(engagementId).andLoveTreeIdEqualTo(loveTreeId).andUserIdEqualTo(otherId);
            loveTreeInfoExample.or(criteria2);
            List<LoveTreeInfo> loveTreeInfoList = loveTreeInfoMapper.selectByExample(loveTreeInfoExample);
            if (loveTreeInfoList.size() > 0) {
                for (LoveTreeInfo loveTreeInfo : loveTreeInfoList) {
//					String ringId = loveTreeInfo.getWeddingRingId();
                    //然后删除双方戒指装备，需要调用额外的接口

                    loveTreeInfoMapper.deleteByPrimaryKey(loveTreeInfo.getId());
                    System.out.println("当前删除的人：" + loveTreeInfo.getUserId());
                }
            }
            UserRole userRole = userRoleService.getUserRoleByUserId(userId);
            UserMessage userMessage = new UserMessage();
            userMessage.setId(UUIDUtil.generateUUID());
            userMessage.setSenderId(userId);
            userMessage.setReceiverId(otherId);
            userMessage.setMessageBody(userRole.getUserName() + "和您离婚了。。。。");
            userMessage.setType(MessageConstant.TYPE_MARRY_CANCLE);
            userMessageService.addUserMessage(userMessage);

            // 删除配偶用户
            userRoleService.deleteMarryStatus(userId);
            userRoleService.deleteMarryStatus(otherId);

            UserGoods userGoods = userGoodsService.deleteWeddingRingByUserIdAndGoodsId(userId);
            Map goodsMap = ObjectUtil.getGoodsMap(userGoods.getGoodsId(), null, MarryConstant.MARRY_ADD_TYPE_EQUIP, 1, null);
            Map map = ObjectUtil.getGoodsWebSock("delete", goodsMap);
            UserAdornEquip userFightingCapacity = userGoodsService.getUserFightingCapacity(userId);
            map.put("roleFight", userFightingCapacity.getAlwaysFighting());
            messageService.sendMessageToSomeBody(userId, JSONObject.toJSONString(map));

            UserGoods otherUserGoods = userGoodsService.deleteWeddingRingByUserIdAndGoodsId(otherId);
            Map otherGoodsMap = ObjectUtil.getGoodsMap(otherUserGoods.getGoodsId(), null, MarryConstant.MARRY_ADD_TYPE_EQUIP, 1, null);
            Map otherMap = ObjectUtil.getGoodsWebSock("delete", otherGoodsMap);
            UserAdornEquip otherUserFightingCapacity = userGoodsService.getUserFightingCapacity(otherId);
            otherMap.put("roleFight", otherUserFightingCapacity.getAlwaysFighting());
            messageService.sendMessageToSomeBody(otherId, JSONObject.toJSONString(otherMap));

            // 更新用户婚房
            buildingService.openMarriageByUserId(userId, BuildingConstant.BUILDING_OPEN_FALSE);
            buildingService.openMarriageByUserId(otherId, BuildingConstant.BUILDING_OPEN_FALSE);
            return 0;
        } else {
            return -1;
        }

    }

    /**
     * 巡游
     *
     * @param userId
     * @param cruiseType
     * @return
     */
    @Override
    public JSONObject cruise(String userId, int cruiseType) {
        JSONObject json = new JSONObject();
        //判断订婚是否已经举行完婚礼。还要修改角色状态
        EngagementLogExample engagementLogExample = new EngagementLogExample();
        Criteria criteria = engagementLogExample.createCriteria();
        Criteria criteria2 = engagementLogExample.createCriteria();
        criteria.andUserIdEqualTo(userId).andMarryStatusEqualTo(MarryConstant.MARRY_SUCCESS);
        criteria2.andOtherIdEqualTo(userId).andMarryStatusEqualTo(MarryConstant.MARRY_SUCCESS);
        engagementLogExample.or(criteria2);
        engagementLogExample.setOrderByClause(" created_time DESC ");
        List<EngagementLog> list = engagementLogMapper.selectByExample(engagementLogExample);
        //是否订婚成功
        if (list.size() > 0) {
            EngagementLog engagementLog = list.get(0);
            String engagementId = engagementLog.getId();
//            String otherId = engagementLog.getOtherId();

            //幸福值
            int happinessNum = 0;
//				Date date = new Date(System.currentTimeMillis());
            //订婚成功的，查询对应的结婚记录，没有就可以结婚，有的话就判断是不是重复结婚，然后判断物品是否足够
            MarryLogExample marryLogExample = new MarryLogExample();
            MarryLogExample.Criteria marryLogcriteria = marryLogExample.createCriteria();
            MarryLogExample.Criteria marryLogcriteria2 = marryLogExample.createCriteria();
            marryLogcriteria.andEngagementIdEqualTo(engagementId).andUserIdEqualTo(userId);
            marryLogcriteria2.andEngagementIdEqualTo(engagementId).andOtherIdEqualTo(userId);
            marryLogExample.setOrderByClause(" created_time DESC ");
            marryLogExample.or(marryLogcriteria2);
            List<MarryLog> marryLogList = marryLogMapper.selectByExample(marryLogExample);

            //有了记录
            if (marryLogList.size() > 0) {
                if (cruiseType == MarryConstant.MARRY_CRUISE_GENERAL) {
                    UserGoods userGoods2 = userGoodsService.getGoodsCount(userId, MarryConstant.MARRY_CONSUMABLES_DIAMOND);
                    int count2 = userGoods2.getAmount();
                    if (count2 < MarryConstant.MARRY_CRUISE_GENERAL_NUM) {
                        throw new CheckException("钻石不足");
                    } else {
                        this.cruiseByUserId(engagementLog.getUserId(), engagementLog.getOtherId(), engagementId, cruiseType, MarryConstant.MARRY_CRUISE_GENERAL_NUM, MarryConstant.MARRY_CONSUMABLES_DIAMOND);
                        happinessNum = MarryConstant.MARRY_CRUISE_GENERAL_HAPPINESS_NUM;

                        ReceiveGoldVo receiveGoldVo = new ReceiveGoldVo();
                        receiveGoldVo.setAmount(count2 - MarryConstant.MARRY_CRUISE_GENERAL_NUM);
                        receiveGoldVo.setGoodsId(Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_DIAMOND));
                        receiveGoldVo.setId(MarryConstant.MARRY_CONSUMABLES_DIAMOND);
                        receiveGoldVo.setType(BuildingConstant.Prop);
                        json.put("result", receiveGoldVo);
                    }
                } else if (cruiseType == MarryConstant.MARRY_CRUISE_ROMANCE) {
                    UserGoods userGoods2 = userGoodsService.getGoodsCount(userId, MarryConstant.MARRY_CONSUMABLES_DIAMOND);
                    int count2 = userGoods2.getAmount();
                    if (count2 < MarryConstant.MARRY_CRUISE_ROMANCE_NUM) {
                        throw new CheckException("钻石不足");
                    } else {
                        this.cruiseByUserId(engagementLog.getUserId(), engagementLog.getOtherId(), engagementId, cruiseType, MarryConstant.MARRY_CRUISE_ROMANCE_NUM, MarryConstant.MARRY_CONSUMABLES_DIAMOND);
                        happinessNum = MarryConstant.MARRY_CRUISE_ROMANCE_HAPPINESS_NUM;

                        ReceiveGoldVo receiveGoldVo = new ReceiveGoldVo();
                        receiveGoldVo.setAmount(count2 - MarryConstant.MARRY_CRUISE_ROMANCE_NUM);
                        receiveGoldVo.setGoodsId(Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_DIAMOND));
                        receiveGoldVo.setId(MarryConstant.MARRY_CONSUMABLES_DIAMOND);
                        receiveGoldVo.setType(BuildingConstant.Prop);
                        json.put("result", receiveGoldVo);
                    }
                } else if (cruiseType == MarryConstant.MARRY_CRUISE_LUXURY) {
                    UserGoods userGoods2 = userGoodsService.getGoodsCount(userId, MarryConstant.MARRY_CONSUMABLES_DIAMOND);
                    int count2 = userGoods2.getAmount();
                    if (count2 < MarryConstant.MARRY_CRUISE_LUXURY_NUM) {
                        throw new CheckException("钻石不足");
                    } else {
                        this.cruiseByUserId(engagementLog.getUserId(), engagementLog.getOtherId(), engagementId, cruiseType, MarryConstant.MARRY_CRUISE_LUXURY_NUM, MarryConstant.MARRY_CONSUMABLES_DIAMOND);
                        happinessNum = MarryConstant.MARRY_CRUISE_LUXURY_HAPPINESS_NUM;

                        ReceiveGoldVo receiveGoldVo = new ReceiveGoldVo();
                        receiveGoldVo.setAmount(count2 - MarryConstant.MARRY_CRUISE_LUXURY_NUM);
                        receiveGoldVo.setGoodsId(Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_DIAMOND));
                        receiveGoldVo.setId(MarryConstant.MARRY_CONSUMABLES_DIAMOND);
                        receiveGoldVo.setType(BuildingConstant.Prop);
                        json.put("result", receiveGoldVo);
                    }
                }
                addHappinessByUserId(userId, happinessNum);
                friendService.taskBranchFinish(MarryConstant.MARRY_TASK_BRANCH_NUM3, userId);
                return json;
            } else {
                throw new CheckException("没有结婚不能巡游");
            }
        } else {
            throw new CheckException("没有结婚不能巡游");
        }
    }

    /**
     * 巡游通用处理办法
     *
     * @param userId       求婚人
     * @param otherId      被求婚人
     * @param engagementId 订婚ID
     * @param cruiseType   巡游类型
     * @param Amount       消耗物品1数量
     * @param GoodsId      消耗物品1物品ID
     * @return
     */
    public int cruiseByUserId(String userId, String otherId, String engagementId, int cruiseType, int amount, String goodsId) {
        //消耗钻石
        subUserMoney(userId, goodsId, amount);
        UserRole userRole = userRoleService.getUserRoleByUserId(userId);
        UserRole otherRole = userRoleService.getUserRoleByUserId(otherId);
        String boyName = new String();
        String girlName = new String();
        if (userRole.getSex() == MarryConstant.MARRY_BOY_TYPE) {
            boyName = userRole.getUserName();
            girlName = otherRole.getUserName();
        } else {
            boyName = otherRole.getUserName();
            girlName = userRole.getUserName();
        }
        //加入全服通知列表，进行全服通知
        //{"type":"weddingCruise", "code":0, "weddingType":"2","boyName":"123456", "girlName":"123456"}
        messageService.sendWeddingCruiseToSomeBody(cruiseType, boyName, girlName);
        return 0;
    }

    /**
     * 给指定的用户增加幸福值
     *
     * @param userId
     * @param otherId 配偶ID
     * @return
     */
    @Override
    public int addHappinessByUserId(String userId, int num) {
        LoveTreeInfoExample loveTreeInfoExample = new LoveTreeInfoExample();
        loveTreeInfoExample.createCriteria().andUserIdEqualTo(userId);
        List<LoveTreeInfo> list = loveTreeInfoMapper.selectByExample(loveTreeInfoExample);
        if (list.size() > 0) {
            LoveTreeInfo loveTreeInfo = list.get(0);
            loveTreeInfo.setHappinessNum(loveTreeInfo.getHappinessNum() + num);
            loveTreeInfoMapper.updateByPrimaryKey(loveTreeInfo);
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 给指定的用户增加情缘值
     *
     * @param userId
     * @param num
     * @return
     */
    @Override
    public int addLoveByUserId(String userId, int num) {
        LoveTreeInfoExample loveTreeInfoExample = new LoveTreeInfoExample();
        loveTreeInfoExample.createCriteria().andUserIdEqualTo(userId);
        List<LoveTreeInfo> list = loveTreeInfoMapper.selectByExample(loveTreeInfoExample);
        if (list.size() > 0) {
            //此处没做幂等性处理
            LoveTreeInfo loveTreeInfo = list.get(0);
            loveTreeInfo.setLoveNum(loveTreeInfo.getLoveNum() + num);
            loveTreeInfoMapper.updateByPrimaryKey(loveTreeInfo);
            return 0;
        } else {
            return -1;
        }
    }

    public int createRandomNum(int max, int min) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    @Override
    public JSONObject getLoveInfoByUserId(String userId, String engagementId) throws Exception {
        JSONObject json = new JSONObject();
        LoveTreeInfoExample loveTreeInfoExample = new LoveTreeInfoExample();
        loveTreeInfoExample.createCriteria().andEngagementIdEqualTo(engagementId).andUserIdEqualTo(userId);
        List<LoveTreeInfo> list = loveTreeInfoMapper.selectByExample(loveTreeInfoExample);

        if (list.size() > 0) {
            LoveTreeInfo loveTreeInfo = list.get(0);
            LoveInfoVo loveInfoVo = new LoveInfoVo();
            LoveSettingExample loveSettingExample = new LoveSettingExample();
            loveSettingExample.createCriteria().andGradeEqualTo(loveTreeInfo.getLoveGrade());
            List<LoveSetting> list2 = loveSettingMapper.selectByExample(loveSettingExample);
            LoveClassSettingExample loveClassSettingExample = new LoveClassSettingExample();
            int grade = loveTreeInfo.getLoveGrade();
            int n = (int) (grade / 10 % 10) * 10;
            loveClassSettingExample.createCriteria().andGradeEqualTo(n);
            List<LoveClassSetting> list3 = loveClassSettingMapper.selectByExample(loveClassSettingExample);
            Map<String, Integer> list4 = getPerproty(list2.get(0));
            loveInfoVo.setEngagementId(engagementId);
            loveInfoVo.setUserId(userId);
            loveInfoVo.setLoveNum(loveTreeInfo.getLoveNum());
            loveInfoVo.setGradeNum(grade);
            loveInfoVo.setNextLoveNum(list2.get(0).getLoveNum().longValue());
            loveInfoVo.setPropertyAllList(list4);
            if (list3.size() > 0) {
                LoveClassSetting loveClassSetting = list3.get(0);

                loveInfoVo.setProperty1(loveClassSetting.getProperty1());
                loveInfoVo.setProperty2(loveClassSetting.getProperty2());
                loveInfoVo.setProperty3(loveClassSetting.getProperty3());
                loveInfoVo.setProperty4(loveClassSetting.getProperty4());
                loveInfoVo.setProperty5(loveClassSetting.getProperty5());
                loveInfoVo.setProperty6(loveClassSetting.getProperty6());
                loveInfoVo.setProperty7(loveClassSetting.getProperty7());
                loveInfoVo.setProperty8(loveClassSetting.getProperty8());
                loveInfoVo.setProperty9(loveClassSetting.getProperty9());
                loveInfoVo.setProperty10(loveClassSetting.getProperty10());
            }
            json.put("loveInfoVo", loveInfoVo);
            json.put("code", 0);
        } else {
            json.put("code", -1);
        }
        return json;
    }

    /**
     * 通过反射获取任意对象中的游戏属性值，
     * 拼装成客户端通用格式
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public Map<String, Integer> getPerproty(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        /*List<String> list = new ArrayList<String>();*/
        Map<String, Integer> map = new HashMap<>();
        Class<? extends Object> object = obj.getClass();
        Method[] ms = object.getMethods();
        for (int i = 0; i < ms.length; i++) {
            //生命加成
            if (ms[i].getName().equals("getVitality")) {
                int num = (int) ms[i].invoke(obj);
                map.put("5", num);
                //物理攻击加成
            } else if (ms[i].getName().equals("getAttack")) {
                int num = (int) ms[i].invoke(obj);
                map.put("1", num);
                //物理防御加成
            } else if (ms[i].getName().equals("getPdef")) {
                int num = (int) ms[i].invoke(obj);
                map.put("3", num);
                //魔法防御加成
            } else if (ms[i].getName().equals("getMagdef")) {
                int num = (int) ms[i].invoke(obj);
                map.put("4", num);
                //暴击加成
            } else if (ms[i].getName().equals("getCrit")) {
                int num = (int) ms[i].invoke(obj);
                map.put("8", num);
                //闪避加成
            } else if (ms[i].getName().equals("getDodge")) {
                int num = (int) ms[i].invoke(obj);
                map.put("7", num);
                //命中加成
            } else if (ms[i].getName().equals("getHitRate")) {
                int num = (int) ms[i].invoke(obj);
                map.put("6", num);
                //抗暴击加成
            } else if (ms[i].getName().equals("getDefenseCrit")) {
                int num = (int) ms[i].invoke(obj);
                map.put("9", num);
                //物理攻击减免
            } else if (ms[i].getName().equals("getPdre")) {
                int num = (int) ms[i].invoke(obj);
                map.put("10", num);
                //魔法伤害减免
            } else if (ms[i].getName().equals("getMagdre")) {
                int num = (int) ms[i].invoke(obj);
                map.put("11", num);
                //法术攻击加成
            } else if (ms[i].getName().equals("getSpellAttacks")) {
                int num = (int) ms[i].invoke(obj);
                map.put("2", num);
            }
        }
        return map;
    }

    public JSONObject judgeMarryStatus(String userId) throws Exception {
        JSONObject json = new JSONObject();
        UserGoods userGoods = userGoodsService.getGoodsCount(userId, MarryConstant.MARRY_CONSUMABLES_STONE);
        json.put("generalHeartNum", MarryConstant.MARRY_CONSUMABLES_STONE_NUM);
        json.put("generalNum", MarryConstant.MARRY_CONSUMABLES_COIN_NUM);
        json.put("generalHappinessNum", MarryConstant.MARRY_GENERAL_JOYFUL_NUM);
        json.put("generalJoyfulNum", MarryConstant.MARRY_ROMANCE_JOYFUL_NUM);

        json.put("romanceNum", MarryConstant.MARRY_CONSUMABLES_DIAMOND_NUM);
        json.put("romanceHappinessNum", MarryConstant.MARRY_HAPPINESS_ROMANCE_NUM);
        json.put("romanceJoyfulNum", MarryConstant.MARRY_ROMANCE_JOYFUL_NUM);
        json.put("romanceHeartNum", MarryConstant.MARRY_CONSUMABLES_STONE_NUM);

        json.put("luxuryNum", MarryConstant.MARRY_CONSUMABLES_DIAMOND_2NUM);
        json.put("luxuryHappinessNum", MarryConstant.MARRY_HAPPINESS_LUXURY_NUM);
        json.put("luxuryJoyfulNum", MarryConstant.MARRY_ROMANCE_JOYFUL_NUM);
        json.put("luxuryHeartNum", MarryConstant.MARRY_CONSUMABLES_STONE_NUM);

        if (userGoods == null) {
            json.put("generalStatus", 2);
            json.put("romanceStatus", 2);
            json.put("luxuryStatus", 2);

            return json;
        }
        int count = userGoods.getAmount();
        UserGoods userGoods2 = userGoodsService.getGoodsCount(userId, MarryConstant.MARRY_CONSUMABLES_COIN);
        int count2 = 0;
        if (userGoods2 != null) {
            count2 = userGoods2.getAmount();
        }

        UserGoods userGoods3 = userGoodsService.getGoodsCount(userId, MarryConstant.MARRY_CONSUMABLES_DIAMOND);
        int count3 = 0;
        if (userGoods3 != null) {
            count3 = userGoods3.getAmount();
        }

        EngagementLogExample engagementLogExample = new EngagementLogExample();
        Criteria criteria = engagementLogExample.createCriteria();
        Criteria criteria2 = engagementLogExample.createCriteria();
        criteria.andUserIdEqualTo(userId).andMarryStatusEqualTo(MarryConstant.MARRY_TRUE);
        criteria2.andOtherIdEqualTo(userId).andMarryStatusEqualTo(MarryConstant.MARRY_TRUE);
        engagementLogExample.setOrderByClause(" created_time DESC ");
        engagementLogExample.or(criteria2);
        List<EngagementLog> list = engagementLogMapper.selectByExample(engagementLogExample);
        //是否订婚成功
        if (list.size() > 0) {
            EngagementLog engagementLog = list.get(0);
            String engagementId = engagementLog.getId();

            //订婚成功的，查询对应的结婚记录，没有就可以结婚，有的话就判断是不是重复结婚，然后判断物品是否足够
            MarryLogExample marryLogExample = new MarryLogExample();
            MarryLogExample.Criteria marryLogcriteria = marryLogExample.createCriteria();
            MarryLogExample.Criteria marryLogcriteria2 = marryLogExample.createCriteria();
            marryLogcriteria.andEngagementIdEqualTo(list.get(0).getId()).andUserIdEqualTo(userId);
            marryLogcriteria2.andEngagementIdEqualTo(list.get(0).getId()).andOtherIdEqualTo(userId);
            marryLogExample.setOrderByClause(" created_time DESC ");
            marryLogExample.or(marryLogcriteria2);
            List<MarryLog> marryLogList = marryLogMapper.selectByExample(marryLogExample);

            Map<String, Object> map = new HashMap<>(16);
            Map<String, Object> map2 = new HashMap<>(16);
            if (marryLogList.size() == 0) {
                if (count < MarryConstant.MARRY_CONSUMABLES_STONE_NUM || count2 < MarryConstant.MARRY_CONSUMABLES_COIN_NUM) {
                    json.put("generalStatus", 2);
                } else {
                    json.put("generalStatus", 1);
                }
                if (count < MarryConstant.MARRY_CONSUMABLES_STONE_NUM || count3 < MarryConstant.MARRY_CONSUMABLES_DIAMOND_NUM) {
                    json.put("romanceStatus", 2);
                } else {
                    json.put("romanceStatus", 1);
                }
                if (count < MarryConstant.MARRY_CONSUMABLES_STONE_NUM || count3 < MarryConstant.MARRY_CONSUMABLES_DIAMOND_2NUM) {
                    json.put("luxuryStatus", 2);
                } else {
                    json.put("luxuryStatus", 1);
                }

                json.put("engagementId", engagementId);
                return json;
            } else {
                //豪华婚礼双方可以各举办一次，普通和浪漫婚礼只能由一方举办一次
                for (MarryLog marryLog : marryLogList) {
                    map.put(new StringBuilder(marryLog.getEngagementId()).append(marryLog.getUserId()).append(String.valueOf(marryLog.getMarryType())).
                            toString(), marryLog);
                    map2.put(new StringBuilder(marryLog.getEngagementId()).append(String.valueOf(marryLog.getMarryType())).toString(), marryLog);
                }
                //判断豪华婚礼
                if (map.containsKey(new StringBuilder(engagementId).append(userId).append(String.valueOf(MarryConstant.MARRY_TYPE_LUXURY)).toString())) {
                    json.put("luxuryStatus", 2);
                } else {
                    if (count < MarryConstant.MARRY_CONSUMABLES_STONE_NUM || count3 < MarryConstant.MARRY_CONSUMABLES_DIAMOND_2NUM) {
                        json.put("luxuryStatus", 2);
                    } else {
                        json.put("luxuryStatus", 1);
                    }
                }
                if (map2.containsKey(new StringBuilder(engagementId).append(MarryConstant.MARRY_TYPE_GENERAL).toString())) {
                    json.put("romanceStatus", 2);
                } else {
                    if (count < MarryConstant.MARRY_CONSUMABLES_STONE_NUM || count2 < MarryConstant.MARRY_CONSUMABLES_COIN_NUM) {
                        json.put("romanceStatus", 2);
                    } else {
                        json.put("romanceStatus", 1);
                    }
                }
                if (map2.containsKey(new StringBuilder(engagementId).append(MarryConstant.MARRY_TYPE_ROMANCE).toString())) {
                    json.put("generalStatus", 2);
                } else {
                    if (count < MarryConstant.MARRY_CONSUMABLES_STONE_NUM || count3 < MarryConstant.MARRY_CONSUMABLES_DIAMOND_NUM) {
                        json.put("generalStatus", 2);
                    } else {
                        json.put("generalStatus", 1);
                    }
                }

                json.put("engagementId", engagementId);
                return json;
            }
        } else {
            json.put("generalStatus", 2);
            json.put("romanceStatus", 2);
            json.put("luxuryStatus", 2);

            return json;
        }
    }


    public JSONObject judgeCruise(String userId) {
        JSONObject json = new JSONObject();
        EngagementLogExample engagementLogExample = new EngagementLogExample();
        Criteria criteria = engagementLogExample.createCriteria();
        Criteria criteria2 = engagementLogExample.createCriteria();
        criteria.andUserIdEqualTo(userId).andMarryStatusEqualTo(MarryConstant.MARRY_TRUE);
        criteria2.andOtherIdEqualTo(userId).andMarryStatusEqualTo(MarryConstant.MARRY_TRUE);
        engagementLogExample.or(criteria2);
        engagementLogExample.setOrderByClause(" created_time DESC ");
        List<EngagementLog> list = engagementLogMapper.selectByExample(engagementLogExample);
        //是否订婚成功
        if (list.size() > 0) {
            String engagementId = list.get(0).getId();
            //订婚成功的，查询对应的结婚记录，没有就可以结婚，有的话就判断是不是重复结婚，然后判断物品是否足够
            MarryLogExample marryLogExample = new MarryLogExample();
            MarryLogExample.Criteria marryLogcriteria = marryLogExample.createCriteria();
            MarryLogExample.Criteria marryLogcriteria2 = marryLogExample.createCriteria();
            marryLogcriteria.andEngagementIdEqualTo(engagementId).andUserIdEqualTo(userId);
            marryLogcriteria2.andEngagementIdEqualTo(engagementId).andOtherIdEqualTo(userId);
            marryLogExample.setOrderByClause(" created_time DESC ");
            marryLogExample.or(marryLogcriteria2);
            List<MarryLog> marryLogList = marryLogMapper.selectByExample(marryLogExample);

            UserGoods userGoods2 = userGoodsService.getGoodsCount(userId, MarryConstant.MARRY_CONSUMABLES_DIAMOND);
            int count2 = userGoods2.getAmount();
            //有了记录
            if (marryLogList.size() > 0) {

                if (count2 < MarryConstant.MARRY_CRUISE_GENERAL_NUM) {
                    json.put("cruiseGeneralStatus", 2);
                } else {
                    json.put("cruiseGeneralStatus", 1);
                }
                if (count2 < MarryConstant.MARRY_CRUISE_ROMANCE_NUM) {
                    json.put("cruiseRomanceStatus", 2);
                } else {
                    json.put("cruiseRomanceStatus", 1);
                }
                if (count2 < MarryConstant.MARRY_CRUISE_LUXURY_NUM) {
                    json.put("cruiseLuxuryStatus", 2);
                } else {
                    json.put("cruiseLuxuryStatus", 1);
                }
                return json;
            } else {
                json.put("cruiseLuxuryStatus", 2);
                json.put("cruiseRomanceStatus", 2);
                json.put("cruiseGeneralStatus", 2);
                json.put("cruiseGeneralNum", MarryConstant.MARRY_CRUISE_GENERAL_NUM);
                json.put("cruiseGeneralLoveNum", MarryConstant.MARRY_CRUISE_GENERAL_HAPPINESS_NUM);
                json.put("cruiseRomanceNum", MarryConstant.MARRY_CRUISE_ROMANCE_NUM);
                json.put("cruiseRomanceLoveNum", MarryConstant.MARRY_CRUISE_ROMANCE_HAPPINESS_NUM);
                json.put("cruiseLuxuryNum", MarryConstant.MARRY_CRUISE_LUXURY_NUM);
                json.put("cruiseLuxuryLoveNum", MarryConstant.MARRY_CRUISE_LUXURY_HAPPINESS_NUM);
                return json;
            }
        } else {
            json.put("cruiseLuxuryStatus", 2);
            json.put("cruiseRomanceStatus", 2);
            json.put("cruiseGeneralStatus", 2);
            json.put("cruiseGeneralNum", MarryConstant.MARRY_CRUISE_GENERAL_NUM);
            json.put("cruiseGeneralLoveNum", MarryConstant.MARRY_CRUISE_GENERAL_HAPPINESS_NUM);
            json.put("cruiseRomanceNum", MarryConstant.MARRY_CRUISE_ROMANCE_NUM);
            json.put("cruiseRomanceLoveNum", MarryConstant.MARRY_CRUISE_ROMANCE_HAPPINESS_NUM);
            json.put("cruiseLuxuryNum", MarryConstant.MARRY_CRUISE_LUXURY_NUM);
            json.put("cruiseLuxuryLoveNum", MarryConstant.MARRY_CRUISE_LUXURY_HAPPINESS_NUM);
            return json;
        }
    }

    @Override
    public int bookWedding() {
        Date date = new Date(System.currentTimeMillis());
        int i = 0;
        List<MarryLogVo> marryLogList = marryLogExtMapper.marryLogVoListForWedding(date, MarryConstant.MARRY_TYPE_LUXURY_BOOK);
        for (MarryLogVo marryLogVo : marryLogList) {
            EngagementLog engagementLog = engagementLogMapper.selectByPrimaryKey(marryLogVo.getEngagementId());
            if (engagementLog == null) {
                continue;
            }
            if (engagementLog.getMarryStatus() == MarryConstant.MARRY_OVERDUE || engagementLog.getMarryStatus() == MarryConstant.MARRY_FALSE) {
//                订婚状态：0：待定；1：成功；2：失败;3:订婚失效(已离婚);4:成功举行婚礼
                continue;
            }
            String userId = marryLogVo.getUserId();
            String otherId = marryLogVo.getOtherId();
            User user = userService.findByUserId(userId);
            User other = userService.findByUserId(otherId);

            String marryId = marryLogVo.getId();
            String otherUserName = marryLogVo.getOtherName();
            String userName = marryLogVo.getUserName();

            JSONObject json = new JSONObject();
            json.put("type", "weddingBegin");
            json.put("userName", otherUserName);
            json.put("code", 0);
            json.put("marryId", marryId);
            if (user.getStatus() == MarryConstant.MARRY_ON_LINE) {
                log.info("JOINBOOKWEDDING" + marryId + "  " + userId);
                log.info(redisService.get("JOINBOOKWEDDING" + marryId + userId) + "");
                if (!redisService.exists("JOINBOOKWEDDING" + marryId + userId)) {
                    messageService.sendMessageToSomeBody(userId, json.toString());
                    i++;
                }
            }

            JSONObject json2 = new JSONObject();
            json2.put("type", "weddingBegin");
            json2.put("userName", userName);
            json2.put("code", 0);
            json2.put("marryId", marryId);
            if (other.getStatus() == MarryConstant.MARRY_ON_LINE) {
                if (!redisService.exists("JOINBOOKWEDDING" + marryId + otherId)) {
                    messageService.sendMessageToSomeBody(otherId, json2.toString());
                    i++;
                }
            }
        }
        return i;
    }

    @Override
    public int resetLoveTreeInfo() {
        LoveTreeInfoExample loveTreeInfoExample = new LoveTreeInfoExample();
        List<LoveTreeInfo> list = loveTreeInfoMapper.selectByExample(loveTreeInfoExample);
        int i = 0;
        if (list.size() > 0) {
            for (LoveTreeInfo loveTreeInfo : list) {
                loveTreeInfo = list.get(i);
                loveTreeInfo.setPlayJokesNum(MarryConstant.MARRY_SCENE_TOAST_NUM);
                loveTreeInfo.setWeddingNum(MarryConstant.MARRY_SCENE_TOAST_NUM);
                loveTreeInfoMapper.updateByPrimaryKey(loveTreeInfo);
                i++;
            }
        }
        return i;
    }

    @Override
    public MarryLogVo getWeddingInfo(String marryId) {
        MarryLogVo marryLogVo = marryLogExtMapper.getMarryLogVoById(marryId);
        return marryLogVo;
    }

    @Override
    public LoveTreeInfo getLoveTreeInfoObject(String loveTreeId, String userId) {
        LoveTreeInfoExample loveTreeInfoExample = new LoveTreeInfoExample();
        loveTreeInfoExample.createCriteria().andUserIdEqualTo(userId).andLoveTreeIdEqualTo(loveTreeId);
        List<LoveTreeInfo> list = loveTreeInfoMapper.selectByExample(loveTreeInfoExample);
        if (list.size() > 0) {
            LoveTreeInfo loveTreeInfo = list.get(0);
            return loveTreeInfo;
        } else {
            return null;
        }
    }

    @Override
    public int minusHappinessByUserId(String userId, int num, String ringId) {
        LoveTreeInfoExample loveTreeInfoExample = new LoveTreeInfoExample();
        loveTreeInfoExample.createCriteria().andUserIdEqualTo(userId);
        List<LoveTreeInfo> list = loveTreeInfoMapper.selectByExample(loveTreeInfoExample);
        if (list.size() > 0) {
            LoveTreeInfo loveTreeInfo = list.get(0);
            loveTreeInfo.setHappinessNum(loveTreeInfo.getHappinessNum() - num);
            if (StringUtil.isNotBlank(ringId)) {
                loveTreeInfo.setWeddingRingGrade(loveTreeInfo.getWeddingRingGrade() + 1);
                loveTreeInfo.setWeddingRingId(ringId);
            }
            loveTreeInfoMapper.updateByPrimaryKey(loveTreeInfo);
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public JSONObject updateRing(String userId, String engagementId) {
        JSONObject json = new JSONObject();
        return json;
    }

    @Override
    public void leaveWeddingRoom(String userId, String id) {
        redisService.remove("WEDDING" + id + userId);
        redisService.remove("WEDDINGID" + userId);
        Set<String> friendkeys = redisService.likePattern("WEDDING" + id + "*");
        for (String userKey : friendkeys) {
            String userId3 = (String) redisService.get(userKey);
            JSONObject json = new JSONObject();
            json.put("type", "leaveWeddingRoom");
            json.put("code", 0);
            json.put("userId", userId);
            messageService.sendMessageToSomeBody(userId3, json.toString());
        }
    }

    @Override
    public void kickLeaveWeddingRoom() {
        MarryLogExample marryLogExample = new MarryLogExample();
        marryLogExample.createCriteria().andEndTimeLessThan(new Date(System.currentTimeMillis()));
        List<MarryLog> list = marryLogMapper.selectByExample(marryLogExample);
        JSONObject json = new JSONObject();
        if (list.size() > 0) {
            for (MarryLog marryLog : list) {
                String marryId = marryLog.getId();
                Set<String> friendkeys = redisService.likePattern("MARRYFRIEND" + marryId + "*");//好友席，八位
                Set<String> keys = redisService.likePattern("MARRY" + marryId + "*");//观礼席，20位
                String userId1 = marryLog.getUserId();
                String otherId = marryLog.getOtherId();
                json.put("type", "weddingEnd");
                json.put("code", 0);
                if (friendkeys.size() > 0 || keys.size() > 0) {
                    messageService.sendMessageToSomeBody(userId1, json.toString());
                    messageService.sendMessageToSomeBody(otherId, json.toString());
                }

                if (friendkeys.size() > 0) {
                    //判断缓存是否存在，如果存在默认为婚礼记录还没清除
                    for (String friendkey : friendkeys) {
                        String userId = friendkey.replace("MARRYFRIEND" + marryId, "");
                        messageService.sendMessageToSomeBody(userId, json.toString());
                        redisService.remove(friendkey);
                    }
                }
                if (keys.size() > 0) {
                    //判断缓存是否存在，如果存在默认为婚礼记录还没清除
                    for (String key : keys) {
                        String userId = key.replace("MARRY" + marryId, "");
                        messageService.sendMessageToSomeBody(userId, json.toString());
                        redisService.remove(key);
                    }
                }
            }
        }
    }

}
