package com.tongzhu.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.common.Pager;
import com.tongzhu.user.constant.MailConstant;
import com.tongzhu.user.domain.*;
import com.tongzhu.user.model.UserMailAll;
import com.tongzhu.user.model.UserMailAllDetails;
import com.tongzhu.user.model.UserMailSingle;
import com.tongzhu.user.po.MailPO;
import com.tongzhu.user.service.*;
import com.tongzhu.user.service.vo.UserMailSingleVO;
import com.tongzhu.util.DateUtil;
import com.tongzhu.util.ObjectUtil;
import com.tongzhu.util.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@RequestMapping("/mail")
@Api(value = "用户邮件controller", tags = {"用户邮件相关API"})
public class MailController {

    @Autowired
    private UserMailSingleService userMailSingleService;

    @Autowired
    private ArsenalService arsenalService;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private PropService propService;

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private UserMailAllService userMailAllService;

    @Autowired
    private UserMailAllDetailsService userMailAllDetailsService;

    @Autowired
    private UserMessageService userMessageService;

    @ApiOperation(value = "发送邮件", notes = "请求参数：[userId 用户Id] [title 邮件标题] [receiverId 邮件接受者][senderId 发送者id][time 邮件过期时间 秒]" +
            "[content 邮件内容 ] [accessory 邮件附件]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明")
    })
    @PostMapping("/addUserMailSingle")
    public BaseReturn addUserMailSingle(@RequestBody MailPO mailPO) {
        if (StringUtils.isEmpty(mailPO.getReceiverId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "receiverId不能为空");
        }
        if (StringUtils.isEmpty(mailPO.getTitle())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "title不能为空");
        }
        UserMailSingle userMailSingle = new UserMailSingle();
        userMailSingle.setId(UUIDUtil.generateUUID());
        userMailSingle.setReceiverId(mailPO.getReceiverId());
        userMailSingle.setSenderId(mailPO.getSenderId());
        userMailSingle.setSendTime(new Date());
        userMailSingle.setExpireTime(DateUtil.computingTime(1296000)); // 十五天
        userMailSingle.setType(mailPO.getType());
        userMailSingle.setTitle(mailPO.getTitle());
        userMailSingle.setContent(mailPO.getContent());
        userMailSingle.setAccessory(mailPO.getAccessory());
        userMailSingle.setRead(MailConstant.MAIL_READ_NO);
        userMailSingle.setReceive(MailConstant.MAIL_RECEIVE_NO);
        userMailSingleService.insertSelective(userMailSingle);
        return new BaseReturn("操作成功！");
    }

    @ApiOperation(value = "用户查看邮件", notes = "请求参数：[Id 用户邮件id] [userId 用户Id] [mailType 邮件类型 0 全体邮件 1单个用户邮件]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明")
    })
    @PostMapping("/checkMailSingle")
    public BaseReturn checkMailSingle(@RequestBody MailPO mailPO) {
        if (StringUtils.isEmpty(mailPO.getId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "id不能为空");
        }

        if (mailPO.getMailType() == MailConstant.MAIL_TYPE_ALL) {
            UserMailAllDetails userMailAllDetails = userMailAllDetailsService.getUserMailAllDetails(mailPO.getUserId(), mailPO.getId());
            if (userMailAllDetails == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "邮件不存在！");
            }
            userMailAllDetails.setRead(MailConstant.MAIL_READ_YES);
            userMailAllDetailsService.update(userMailAllDetails);
        } else {
            UserMailSingle userMailSingle = userMailSingleService.selectByPrimaryKey(mailPO.getId());
            if (userMailSingle == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "邮件不存在！");
            }
            userMailSingle.setRead(MailConstant.MAIL_READ_YES);
            userMailSingleService.update(userMailSingle);
        }
        return new BaseReturn("邮件查看成功！");
    }


    @ApiOperation(value = "获取邮箱附件奖励", notes = "请求参数：[userId 用户Id][id 邮件id] [mailType 邮件类型 0 全体邮件 1单个用户邮件]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明 ")
    })
    @PostMapping("/receiveAccessory")
    @Transactional(rollbackFor = Exception.class)
    public BaseReturn receiveAccessory(@RequestBody MailPO mailPO) {
        if (StringUtils.isEmpty(mailPO.getId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "id不能为空");
        }
        if (StringUtils.isEmpty(mailPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "userId 不能为空");
        }


        String accessory;
        if (mailPO.getMailType() == MailConstant.MAIL_TYPE_ALL) {
            UserMailAllDetails userMailAllDetails = userMailAllDetailsService.getUserMailAllDetails(mailPO.getUserId(), mailPO.getId());
            if (userMailAllDetails == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "邮件不存在");
            }
            if (userMailAllDetails.getReceive() == MailConstant.MAIL_RECEIVE_YES) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "附件已经领取了");
            }
            UserMailAll userMailAllById = userMailAllService.getUserMailAllById(userMailAllDetails.getMailallId());
            accessory = userMailAllById.getAccessory();

        } else {
            UserMailSingle userMailSingle = userMailSingleService.selectByPrimaryKey(mailPO.getId());
            if (userMailSingle == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "邮件不存在！");
            }

            if (userMailSingle.getReceive() == MailConstant.MAIL_RECEIVE_YES) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "附件已经领取了");
            }
            accessory = userMailSingle.getAccessory();
        }
        List<String> arrayList = new ArrayList<>();


        JSONObject jsonObject = JSON.parseObject(accessory);
        JSONObject prop = jsonObject.getJSONObject("prop");
        JSONObject equipment = jsonObject.getJSONObject("equipment");
        JSONObject arsenal = jsonObject.getJSONObject("arsenal");
        int count = 0;
        if (prop != null) {
            for (Object goodsId : prop.keySet()) {
                PropInfo propInfo = propService.getPropInfo((String) goodsId);
                if (propInfo == null) {
                    continue;
                }
                arrayList.add((String) goodsId);
            }
        }
        if (equipment != null) {
            for (Object goodsId : equipment.keySet()) {
                EquipmentInfo equipmentInfo = equipmentService.getEquipmentInfoByIdAndOriginal((String) goodsId);
                if (equipmentInfo == null) {
                    continue;
                }
                count += (Integer) equipment.get(goodsId);
            }
        }
        int backpackSpace = 0;
        if (arrayList.size() > 0) {
            backpackSpace = userGoodsService.queryBackpackSpace(mailPO.getUserId(), arrayList);
        }
        if (backpackSpace - count < 0) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "背包空间不足");
        }

        if (mailPO.getMailType() == MailConstant.MAIL_TYPE_ALL) {
            UserMailAllDetails userMailAllDetails = userMailAllDetailsService.getUserMailAllDetails(mailPO.getUserId(), mailPO.getId());
            userMailAllDetails.setReceive(MailConstant.MAIL_RECEIVE_YES);
            userMailAllDetailsService.update(userMailAllDetails);
        } else {
            UserMailSingle userMailSingle = userMailSingleService.selectByPrimaryKey(mailPO.getId());
            userMailSingle.setReceive(MailConstant.MAIL_RECEIVE_YES);
            userMailSingleService.update(userMailSingle);
        }
        List goodsList = new ArrayList();
        if (prop != null) {
            for (Object goodsId : prop.keySet()) {
                PropInfo propInfo = propService.getPropInfo((String) goodsId);
                if (propInfo == null) {
                    continue;
                }
                userGoodsService.addUserGoodsSingle(mailPO.getUserId(), (String) goodsId, (Integer) prop.get(goodsId));
                Map map = ObjectUtil.getGoodsMap(propInfo.getId(), propInfo.getId(), 1, (Integer) prop.get(goodsId), null);
                goodsList.add(map);

            }
        }
        if (equipment != null) {
            for (Object goodsId : equipment.keySet()) {
                EquipmentInfo equipmentInfo = equipmentService.getEquipmentInfoByIdAndOriginal((String) goodsId);
                if (equipmentInfo == null) {
                    continue;
                }
                userGoodsService.addEquipAndWeapon(mailPO.getUserId(), 2, equipmentInfo.getId() + "");
                Map map = ObjectUtil.getGoodsMap(equipmentInfo.getIntensifyId(), equipmentInfo.getId(), 2, (Integer) equipment.get(goodsId), 0);
                goodsList.add(map);
            }
        }
        if (arsenal != null) {
            for (Object goodsId : arsenal.keySet()) {
                ArsenalInfo arsenalInfo = arsenalService.getArsenalInfoByIdAndOriginal((String) goodsId);
                if (arsenalInfo == null) {
                    continue;
                }
                userGoodsService.addEquipAndWeapon(mailPO.getUserId(), 3, arsenalInfo.getId() + "");
                Map map = ObjectUtil.getGoodsMap(arsenalInfo.getIntensifyId(), arsenalInfo.getId(), 3, (Integer) arsenal.get(goodsId), 0);
                goodsList.add(map);
            }
        }

        return new BaseReturn("领取附件成功", goodsList);
    }


    @ApiOperation(value = "获取用户邮件", notes = "请求参数：[userId 用户Id] [pageNum 页码] [pageSize 每页大小]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明 [id 邮件id][title 邮件标题][content 邮件内容][accessory 附件] [read 是否阅读 0 未阅读 1 已阅读]" +
                    "[receive 是否已经领取附件奖励 0 未领取 1 已领取][swiftAddress 邮件内容传送快捷地址 ][mailType 邮件类型 0 全体邮件 1 单个用户邮件] [sendTime 发送时间] {accessoryList type 0 道具 1 装备 2 武器 amount 数量 }")
    })
    @PostMapping("/allMail")
    public BaseReturn allMail(@RequestBody MailPO mailPO) {
        if (StringUtils.isEmpty(mailPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "userId 不能为空");
        }
        if (mailPO.getPageNum() == 0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "页码为0！");
        if (mailPO.getPageSize() == 0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "每页大小为0!");
        Pager<UserMailSingleVO> userMailSinglePager = userMailSingleService.selectUserMailSingleVOList(mailPO.getUserId(), mailPO.getPageNum(), mailPO.getPageSize(), MailConstant.MAIL_RECEIVE_NO);
        for (UserMailSingleVO userMailSingleVO : userMailSinglePager.getContent()) {

            JSONObject jsonObject = JSON.parseObject(userMailSingleVO.getAccessory());
            JSONObject prop = jsonObject.getJSONObject("prop");
            JSONObject equipment = jsonObject.getJSONObject("equipment");
            JSONObject arsenal = jsonObject.getJSONObject("arsenal");

            if (prop != null) {
                for (Object o : prop.keySet()) {
                    Object values = prop.get(o);
                    Map map = new HashMap();
                    map.put("id", o);
                    map.put("amount", values);
                    map.put("type", 1);
                    userMailSingleVO.getAccessoryList().add(map);
                }
            }

            if (equipment != null) {
                for (Object o : equipment.keySet()) {
                    Object values = equipment.get(o);
                    Map map = new HashMap();
                    map.put("id", o);
                    map.put("amount", values);
                    map.put("type", 2);
                    userMailSingleVO.getAccessoryList().add(map);
                }
            }

            if (arsenal != null) {
                for (Object o : arsenal.keySet()) {
                    Object values = arsenal.get(o);
                    Map map = new HashMap();
                    map.put("id", o);
                    map.put("amount", values);
                    map.put("type", 3);
                    userMailSingleVO.getAccessoryList().add(map);
                }
            }

            ObjectUtil.setObjectFieldsEmpty(userMailSingleVO, "read", "receive", "content", "title", "mailType", "id", "accessoryList", "sendTime");
        }
        Map map = new HashMap();
        int messageRow = userMessageService.userMessageRow(mailPO.getUserId());
        int i = userMailSingleService.userMailMessage(mailPO.getUserId());
        map.put("message", messageRow > 0?1:0);
        map.put("mail", i > 0?1:0);
        map.put("pager",userMailSinglePager);
        return new BaseReturn("查询成功", map);
    }


    @ApiOperation(value = "删除邮件", notes = "请求参数：[userId 用户Id] [id 邮件id][mailType 邮件类型]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明 ")
    })
    @PostMapping("/deleteMail")
    public BaseReturn deleteMail(@RequestBody MailPO mailPO) {
        if (StringUtils.isEmpty(mailPO.getId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "id 不能为空");
        }
        if (mailPO.getMailType() == MailConstant.MAIL_TYPE_ALL) {
            userMailAllDetailsService.delete(mailPO.getId(), mailPO.getUserId());
        } else {
            userMailSingleService.delete(mailPO.getId());
        }

        return new BaseReturn("操作成功");
    }

    @ApiOperation(value = "增加全体邮件模板", notes = "请求参数：[userId 用户Id] [title 邮件标题] [receiverId 邮件接受者][senderId 发送者id][time 邮件过期时间 秒]" +
            "[content 邮件内容 ] [accessory 邮件附件]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明")
    })
    @PostMapping("/addUserMailAll")
    public BaseReturn addUserMailAll(@RequestBody MailPO mailPO) {
        if (StringUtils.isEmpty(mailPO.getReceiverId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "receiverId不能为空");
        }
        if (StringUtils.isEmpty(mailPO.getTitle())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "title不能为空");
        }
        UserMailAll userMailAll = new UserMailAll();
        userMailAll.setId(UUIDUtil.generateUUID());
        userMailAll.setAccessory(mailPO.getAccessory());
        userMailAll.setContent(mailPO.getContent());
        userMailAll.setCreationTime(new Date());
        userMailAll.setExpireTime(DateUtil.computingTime(1296000));
        userMailAll.setSwiftAddress(mailPO.getSwiftAddress());
        userMailAll.setTitle(mailPO.getTitle());
        userMailAll.setStatus(MailConstant.MAIL_STATUS_NORMAL);
        userMailAllService.insert(userMailAll);
        return new BaseReturn("操作成功！");
    }

    @ApiOperation(value = "删除全体邮件模板", notes = "请求参数：[userId 用户Id] [id 邮件id]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明")
    })
    @PostMapping("/deleteUserMailAll")
    public BaseReturn deleteUserMailAll(@RequestBody MailPO mailPO) {
        if (StringUtils.isEmpty(mailPO.getId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "id不能为空");
        }
        UserMailAll userMailAll = userMailAllService.getUserMailAllById(mailPO.getId());
        if (userMailAll == null) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "邮件模板不存在");
        }
        userMailAll.setStatus(MailConstant.MAIL_STATUS_DELETE);
        userMailAllService.delete(mailPO.getId());
        return new BaseReturn("操作成功！");
    }

    @ApiOperation(value = "一键领取所有邮件", notes = "请求参数：[userId 用户Id]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明")
    })
    @PostMapping("/receiveAccessoryAll")
    @Transactional
    public BaseReturn receiveAccessoryAll(@RequestBody MailPO mailPO) {
        if (StringUtils.isEmpty(mailPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "userId不能为空");
        }

        Pager<UserMailSingleVO> userMailSinglePager = userMailSingleService.selectUserMailSingleVOList(mailPO.getUserId(), 1, 999, MailConstant.MAIL_RECEIVE_NO);
        if (userMailSinglePager.getContent().size() <= 0) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "附件已经全部领取");
        }
        int count = 0;
        List<String> arrayList = new ArrayList<>();
        List<UserGoods> userGoodsList = new ArrayList<>();
        Map<String, Integer> equipmentInfoAndArsenalMap = new HashMap<>();
        for (UserMailSingleVO userMailSingleVO : userMailSinglePager.getContent()) {
            if (userMailSingleVO == null) {
                continue;
            }
            if (userMailSingleVO.getReceive() == MailConstant.MAIL_RECEIVE_YES) {
                continue;
            }
            String accessory = userMailSingleVO.getAccessory();
            JSONObject jsonObject = JSON.parseObject(accessory);
            JSONObject prop = jsonObject.getJSONObject("prop");
            JSONObject equipment = jsonObject.getJSONObject("equipment");
            JSONObject arsenal = jsonObject.getJSONObject("arsenal");
            if (prop != null) {
                for (Object goodsId : prop.keySet()) {
                    PropInfo propInfo = propService.getPropInfo((String) goodsId);
                    if (propInfo == null) {
                        continue;
                    }
                    arrayList.add((String) goodsId);
                    userGoodsList.add(new UserGoods((String) goodsId, (Integer) prop.get(goodsId)));

                }
            }
            if (equipment != null) {
                for (Object goodsId : equipment.keySet()) {
                    EquipmentInfo equipmentInfo = equipmentService.getEquipmentInfoByIdAndOriginal((String) goodsId);
                    if (equipmentInfo == null) {
                        continue;
                    }
                    count += (Integer) equipment.get(goodsId);
                    equipmentInfoAndArsenalMap.put((String) goodsId, 2);
                }
            }
            if (arsenal != null) {
                for (Object goodsId : arsenal.keySet()) {
                    ArsenalInfo arsenalInfo = arsenalService.getArsenalInfoByIdAndOriginal((String) goodsId);
                    if (arsenalInfo == null) {
                        continue;
                    }
                    equipmentInfoAndArsenalMap.put((String) goodsId, 3);
                }
            }
        }
        int backpackSpace = 0;
        if (arrayList.size() > 0) {
            backpackSpace = userGoodsService.queryBackpackSpace(mailPO.getUserId(), arrayList);
        }
        if (backpackSpace - count < 0) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "背包空间不足");
        }
        List goodsList = new ArrayList();
        List<UserGoods> list = userGoodsService.addUserGoods(mailPO.getUserId(), userGoodsList);
        for (UserGoods userGoods : list) {
            Map goodsMap = ObjectUtil.getGoodsMap(userGoods.getGoodsId() + "", userGoods.getGoodsId(), 1, userGoods.getAmount(), null);
            goodsList.add(goodsMap);
        }
        List<Map> maps = userGoodsService.addEquipAndWeaponList(mailPO.getUserId(), equipmentInfoAndArsenalMap);
        goodsList.addAll(maps);

        userMailSingleService.receiveAccessoryAll(mailPO.getUserId());

        return new BaseReturn("操作成功！", goodsList);
    }
}
