package com.tongzhu.friend.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.friend.constant.TaskConstant;
import com.tongzhu.friend.domain.PropInfo;
import com.tongzhu.friend.domain.User;
import com.tongzhu.friend.domain.UserGoods;
import com.tongzhu.friend.domain.UserRole;
import com.tongzhu.friend.model.*;
import com.tongzhu.friend.po.TaskPO;
import com.tongzhu.friend.service.*;
import com.tongzhu.friend.service.vo.TaskActivityVO;
import com.tongzhu.util.ObjectUtil;
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

/**
 * Created by Administrator on 2018/10/31 0031.
 */

@Api(value = "任务controller", tags = {"任务相关API"})
@RestController
@RequestMapping("/taskController")
public class TaskController {

    @Autowired
    private DailyTaskInfoService dailyTaskInfoService;

    @Autowired
    private DailyTaskRecordService dailyTaskRecordService;

    @Autowired
    private TaskActivityRecordService taskActivityRecordService;

    @Autowired
    private TaskActivityRewardsService taskActivityRewardsService;

    @Autowired
    private UserService userService;

    @Autowired
    private PropService propService;

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private ArsenalService arsenalService;

    @Autowired
    private DailyTaskExpInfoService dailyTaskExpInfoService;

    @Autowired
    private RoleService roleService;


    @Autowired
    private TaskBranchService taskBranchService;

    @Autowired
    private TaskBranchRecordService taskBranchRecordService;

    /**
     * 查询好友当前任务详情
     *
     * @return
     */
    @ApiOperation(value = "查询好友当前任务详情", notes = "请求参数说明 [userId 用户ID]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "{dailyTaskList 任务列表详情  [userId 用户id] [dailyTaskId 任务编号]" +
                    "[progress 任务进展][status 任务是否已经完成 1 未完成 0 完成][receiveAward 是否已经领取过奖励 1 未领取 0 已领取]}" +
                    " {taskActivityList 活跃宝箱详情 [id 宝箱id] [userId 用户id] [status 活跃宝箱开启情况 0 已开启  1未开启]" +
                    "[liveness 到达此活跃度就可以开启宝箱]} [livenessSun 当前活跃度]")
    })
    @PostMapping("/taskDetails")
    public BaseReturn taskDetails(@RequestBody TaskPO taskPO) {
        if (StringUtils.isEmpty(taskPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        }

        User byUserId = userService.findByUserId(taskPO.getUserId());
        if (byUserId == null) {

            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户不存在！");
        }
        dailyTaskRecordService.detectionTask(taskPO.getUserId());
        taskActivityRecordService.detectionTaskActivity(taskPO.getUserId());


//        List<DailyTaskVO> dailyTaskVOList = dailyTaskRecordService.getDailyTaskVOList(taskPO.getUserId());
//        for (DailyTaskVO dailyTaskVO:dailyTaskVOList) {
//            dailyTaskVO.setAwardJson(JSON.parseObject(dailyTaskVO.getAward()));
//            ObjectUtil.setObjectFieldsEmpty(dailyTaskVO,false,"type","updateDate","level","award");
//        }
        List<DailyTaskRecord> dailyTaskRecordList = dailyTaskRecordService.getDailyTaskRecordList(taskPO.getUserId());
        for (DailyTaskRecord dailyTaskRecord : dailyTaskRecordList) {
            ObjectUtil.setObjectFieldsEmpty(dailyTaskRecord, false, "id", "goal", "updateDate", "finishDate");
        }
        List<TaskActivityVO> taskActivityList = taskActivityRecordService.getTaskActivityList(taskPO.getUserId(), null);
        for (TaskActivityVO taskActivityVO : taskActivityList) {
            ObjectUtil.setObjectFieldsEmpty(taskActivityVO, false, "activityRewardsId", "award", "updateDate", "performance");
        }
        Integer livenessSun = dailyTaskInfoService.getUserLiveness(byUserId.getUserId());

        Map<String, Object> map = new HashMap<>();
        map.put("taskActivityList", taskActivityList);
        map.put("dailyTaskList", dailyTaskRecordList);
        map.put("livenessSun", livenessSun > 100 ? 100 : livenessSun);
        return new BaseReturn("查询成功", map);
    }

    /**
     * 领取任务奖励
     *
     * @return
     */
    @ApiOperation(value = "领取任务奖励", notes = "请求参数说明 [dailyTaskId 任务id] [userId 用户id]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "{goods 任务奖励} {livenessSun 活跃度}{ userLevel 角色等级 currentExp 当前经验值 roleLevel 当前等级 fullExp 下一级经验")
    })
    @PostMapping("/receiveAward")
    @Transactional(rollbackFor = Exception.class)
    public BaseReturn receiveAward(@RequestBody TaskPO taskPO) {
        if (taskPO.getDailyTaskId() == 0) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "任务id为空！");
        }
        if (StringUtils.isEmpty(taskPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户id为空！");
        }

        User user = userService.findByUserId(taskPO.getUserId());
        if (user == null) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户不存在！");
        }
        DailyTaskRecord dailyTaskRecord = dailyTaskRecordService.getDailyTaskRecordByUserIdAndDailyTaskId(taskPO.getUserId(), taskPO.getDailyTaskId());
        if (dailyTaskRecord == null) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "数据异常，请联系管理员");
        }
        if (TaskConstant.TASK_STATUS_SUCCEED != dailyTaskRecord.getStatus()) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "任务未成功，不能领取奖励！");
        }
        if (TaskConstant.TASK_RECEIVE_AWARD_YES == dailyTaskRecord.getReceiveAward()) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "任务奖励已经领取！");
        }
        dailyTaskRecord.setReceiveAward(TaskConstant.TASK_RECEIVE_AWARD_YES);
        dailyTaskRecord.setUpdateDate(new Date());
        dailyTaskRecordService.update(dailyTaskRecord);

        Map retMap = new HashMap();

        // 获取当前用户任务活跃度
        Integer livenessSun = dailyTaskInfoService.getUserLiveness(dailyTaskRecord.getUserId());
        List<TaskActivityRecord> taskActivityList = taskActivityRecordService.getTaskActivityRecordListByLiveness(taskPO.getUserId(), livenessSun);
        for (TaskActivityRecord taskActivityRecord : taskActivityList) {
            if (taskActivityRecord.getPerformance() != TaskConstant.TASK_ACTIVITY_PERFORMANCE_YES) {
                taskActivityRecord.setPerformance(TaskConstant.TASK_ACTIVITY_PERFORMANCE_YES);
                taskActivityRecord.setUpdateDate(new Date());
                taskActivityRecordService.update(taskActivityRecord);
            }
        }
        DailyTaskInfo dailyTaskInfoById = dailyTaskInfoService.getDailyTaskInfoById(dailyTaskRecord.getDailyTaskId());
        String award = dailyTaskInfoById.getAward();
        JSONObject jsonObject = JSON.parseObject(award);
        JSONObject prop = jsonObject.getJSONObject("prop");
//        JSONObject equipment = jsonObject.getJSONObject("equipment");
//        JSONObject arsenal = jsonObject.getJSONObject("arsenal");
        if (prop != null) {
            List list = new ArrayList();
            for (Object goodsId : prop.keySet()) {
                PropInfo propInfo = propService.getPropInfo((String) goodsId);
                if (propInfo == null) {
                    continue;
                }
                UserGoods userGoods = userGoodsService.addUserGoodsSingle(dailyTaskRecord.getUserId(), goodsId + "", (Integer) prop.get(goodsId));
                Map goodsMap = ObjectUtil.getGoodsMap(goodsId + "", goodsId, 1, (Integer) prop.get(goodsId), null);
                list.add(goodsMap);
            }
            retMap.put("goods", list);
        }


//        if (equipment != null) {
//            for (Object goodsId : equipment.keySet()) {
//                EquipmentInfo equipmentInfo = equipmentService.getEquipmentInfo((String) goodsId);
//                if (equipmentInfo == null) {
//                    continue;
//                }
//                for (int i = 0; i < (Integer) arsenal.get(goodsId); i++) {
//                    UserGoods userGoods = new UserGoods();
//                    userGoods.setId(UUIDUtil.generateUUID());
//                    userGoods.setAmount((Integer) equipment.get(goodsId));
//                    userGoods.setCreateDate(new Date());
//                    userGoods.setGoodsId(goodsId + "");
//                    userGoods.setStatus(0);
//                    userGoods.setSettingPosition(0);
//                    userGoods.setType(2);
//                    userGoods.setUserId(dailyTaskRecord.getUserId());
//                    userGoods.setUpdateDate(new Date());
//                    if (equipmentInfo.getTime() != null && equipmentInfo.getTime() > 0) {
//                        userGoods.setSurplusDate(DateUtil.computingTime(equipmentInfo.getTime()));
//                    }
//                    userGoodsService.insertUserGoods(userGoods);
//                }
//            }
//        }
//        if (arsenal != null) {
//            for (Object goodsId : arsenal.keySet()) {
//                ArsenalInfo arsenalInfo = arsenalService.getArsenalInfo((String) goodsId);
//                if (arsenalInfo == null) {
//                    continue;
//                }
//                for (int i = 0; i < (Integer) arsenal.get(goodsId); i++) {
//
//                    UserGoods userGoods = new UserGoods();
//                    userGoods.setId(UUIDUtil.generateUUID());
//                    userGoods.setAmount((Integer) arsenal.get(goodsId));
//                    userGoods.setCreateDate(new Date());
//                    userGoods.setGoodsId(goodsId + "");
//                    userGoods.setStatus(0);
//                    userGoods.setSettingPosition(4);
//                    userGoods.setType(3);
//                    userGoods.setUserId(dailyTaskRecord.getUserId());
//                    userGoods.setUpdateDate(new Date());
//                    if (arsenalInfo.getTime() != null && arsenalInfo.getTime() > 0) {
//                        userGoods.setSurplusDate(DateUtil.computingTime(arsenalInfo.getTime()));
//                    }
//                    userGoodsService.insertUserGoods(userGoods);
//                }
//            }
//        }
        UserRole userRoleByUserId = roleService.getUserRoleByUserId(taskPO.getUserId());
        if (userRoleByUserId == null) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户角色不存在！");
        }
//        Integer exp = 0;
//        DailyTaskExpInfo dailyTaskExpInfo = dailyTaskExpInfoService.getDailyTaskExpInfo(userRoleByUserId.getRoleLevel());
//        if (dailyTaskExpInfo != null) {
//            if (dailyTaskInfoById.getLevel() == TaskConstant.TASK_PRIMARY_EXP) {
//                exp = dailyTaskExpInfo.getPrimaryExp();
//            } else if (dailyTaskInfoById.getLevel() == TaskConstant.TASK_INTERMEDIATE_EXP) {
//                exp = dailyTaskExpInfo.getIntermediateExp();
//            } else if (dailyTaskInfoById.getLevel() == TaskConstant.TASK_ADVANCED_EXP) {
//                exp = dailyTaskExpInfo.getAdvancedExp();
//            } else {
//                return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "任务配置错误！");
//            }
//        }
//        Map<String, Object> userLevel = userService.updateRoleExp(taskPO.getUserId(), exp);
        retMap.put("livenessSun", livenessSun > 100 ? 100 : livenessSun);
//        retMap.put("userLevel", userLevel);
        return new BaseReturn("领取成功", retMap);
    }


    /**
     * 开启活跃度宝箱
     *
     * @return
     */
    @ApiOperation(value = "开启活跃度宝箱", notes = "请求参数说明 [userId 用户ID] [id 活跃宝箱id]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "{goods 任务奖励}")
    })
    @PostMapping("/getTaskActivity")
    @Transactional(rollbackFor = Exception.class)
    public BaseReturn getTaskActivity(@RequestBody TaskPO taskPO) {
        if (StringUtils.isEmpty(taskPO.getId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "宝箱id为空！");
        }
        TaskActivityRecord taskActivityRecord = taskActivityRecordService.getTaskActivityRecord(taskPO.getId());
        if (taskActivityRecord == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "宝箱信息不存在");
        }
        if (TaskConstant.TASK_ACTIVITY_PERFORMANCE_YES != taskActivityRecord.getPerformance()) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "活跃度未达到宝箱开启条件！");
        }
        if (TaskConstant.TASK_ACTIVITY_STATUS_YES == taskActivityRecord.getStatus()) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "任务奖励已经领取！");
        }
        taskActivityRecord.setStatus(TaskConstant.TASK_ACTIVITY_STATUS_YES);
        taskActivityRecord.setUpdateDate(new Date());
        taskActivityRecordService.update(taskActivityRecord);
        Map retMap = new HashMap();
        // 获取当前用户任务活跃度
        TaskActivityRewards taskActivityRewards = taskActivityRewardsService.getTaskActivityRewards(taskActivityRecord.getActivityRewardsId());
        String award = taskActivityRewards.getAward();
        JSONObject jsonObject = JSON.parseObject(award);
        JSONObject prop = jsonObject.getJSONObject("prop");

        if (prop != null) {
            List list = new ArrayList();
            for (Object goodsId : prop.keySet()) {
                PropInfo propInfo = propService.getPropInfo(goodsId + "");
                if (propInfo == null) {
                    continue;
                }
                UserGoods userGoods = userGoodsService.addUserGoodsSingle(taskPO.getUserId(), goodsId + "", (Integer) prop.get(goodsId));
                Map goodsMap = ObjectUtil.getGoodsMap(goodsId + "", goodsId, 1, userGoods.getAmount(), null);
                list.add(goodsMap);
            }
            retMap.put("goods", list);
        }
        return new BaseReturn("领取成功", retMap);
    }

    /**
     * 获取支线任务
     *
     * @return
     */
    @ApiOperation(value = "获取支线任务", notes = "请求参数说明 [userId 用户ID]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "{}")
    })
    @PostMapping("/getTaskBranch")
    @Transactional(rollbackFor = Exception.class)
    public BaseReturn taskBranch(@RequestBody TaskPO taskPO) {

        UserRole userRole = roleService.getUserRoleByUserId(taskPO.getUserId());
        taskBranchService.initializationTask(taskPO.getUserId(), userRole.getRoleLevel());

        List<TaskBranchRecord> taskBranchRecords = taskBranchRecordService.getTaskBranchRecordUndoneList(taskPO.getUserId());
        for (TaskBranchRecord taskBranchRecord : taskBranchRecords) {
            ObjectUtil.setObjectFieldsEmpty(taskBranchRecord, false, "finishDate");
        }
        return new BaseReturn("查询成功", taskBranchRecords);
    }

    /**
     * 领取支线任务奖励
     *
     * @return
     */
    @ApiOperation(value = "领取支线任务奖励", notes = "请求参数说明 [dailyTaskId 任务id] [userId 用户id]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "{goods 任务奖励} {livenessSun 活跃度}{ userLevel 角色等级 currentExp 当前经验值 roleLevel 当前等级 fullExp 下一级经验")
    })
    @PostMapping("/gainBranchAward")
    @Transactional(rollbackFor = Exception.class)
    public BaseReturn gainBranchAward(@RequestBody TaskPO taskPO) {
        if (taskPO.getDailyTaskId() == 0) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "任务id为空！");
        }
        if (StringUtils.isEmpty(taskPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户id为空！");
        }

        User user = userService.findByUserId(taskPO.getUserId());
        if (user == null) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户不存在！");
        }
        TaskBranchRecord taskBranchRecord = taskBranchRecordService.getTaskBranchRecord(taskPO.getUserId(), taskPO.getDailyTaskId());
        if (taskBranchRecord == null) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "数据异常，请联系管理员");
        }
        if (TaskConstant.TASK_STATUS_SUCCEED != taskBranchRecord.getStatus()) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "任务未成功，不能领取奖励！");
        }
        if (TaskConstant.TASK_RECEIVE_AWARD_YES == taskBranchRecord.getReceiveAward()) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "任务奖励已经领取！");
        }
        taskBranchRecord.setReceiveAward(TaskConstant.TASK_RECEIVE_AWARD_YES);
        taskBranchRecord.setFinishDate(new Date());
        taskBranchRecordService.updateByPrimaryKeySelective(taskBranchRecord);


        TaskBranch taskBranch = taskBranchService.getTaskBranch(taskBranchRecord.getTaskId());
        if (taskBranch == null) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "数据异常！");
        }
        Map retMap = new HashMap();
        String award = taskBranch.getAward();
        JSONObject jsonObject = JSON.parseObject(award);
        JSONObject prop = jsonObject.getJSONObject("prop");
        if (prop != null) {
            List list = new ArrayList();
            for (Object goodsId : prop.keySet()) {
                PropInfo propInfo = propService.getPropInfo((String) goodsId);
                if (propInfo == null) {
                    continue;
                }
                UserGoods userGoods = userGoodsService.addUserGoodsSingle(taskPO.getUserId(), goodsId + "", (Integer) prop.get(goodsId));
                Map goodsMap = ObjectUtil.getGoodsMap(goodsId + "", goodsId, 1, userGoods.getAmount(), null);
                list.add(goodsMap);
            }
            retMap.put("goods", list);
        }

        TaskBranch taskBranchFront = taskBranchService.getTaskBranchByFront(taskBranch.getId());
        if (taskBranchFront != null) {
            TaskBranchRecord taskBranchRecordFront = new TaskBranchRecord();
            taskBranchRecordFront.setUserId(taskPO.getUserId());
            taskBranchRecordFront.setProgress(TaskConstant.TASK_PROGRESS_INITIALIZE);
            taskBranchRecordFront.setReceiveAward(TaskConstant.TASK_RECEIVE_AWARD_NO);
            taskBranchRecordFront.setStatus(TaskConstant.TASK_STATUS_UNFINISHED);
            taskBranchRecordFront.setGoal(taskBranchFront.getCondition());
            taskBranchRecordFront.setTaskId(taskBranchFront.getId());
            taskBranchRecordService.insertSelective(taskBranchRecordFront);
        }

        return new BaseReturn("领取成功", retMap);
    }


    /**
     * 领取支线任务奖励
     *
     * @return
     */
    @ApiOperation(value = "a", notes = "请求参数说明 [dailyTaskId 任务id] [userId 用户id]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "{goods 任务奖励} {livenessSun 活跃度}{ userLevel 角色等级 currentExp 当前经验值 roleLevel 当前等级 fullExp 下一级经验")
    })
    @PostMapping("/a")
    @Transactional(rollbackFor = Exception.class)
    public BaseReturn a(@RequestBody TaskPO taskPO) {
//        List<DailyTaskRecord> dailyTaskRecordList = dailyTaskRecordService.getDailyTaskRecordList("100001", TaskConstant.TASK_STATUS_SUCCEED, TaskConstant.TASK_RECEIVE_AWARD_NO);
        Map<String, Object> userLevel = userService.updateRoleExp(taskPO.getUserId(), taskPO.getDailyTaskId());
        return null;
    }


}
