package com.tongzhu.treehouse.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.treehouse.constant.StatusConstant;
import com.tongzhu.treehouse.constant.WorkPositionConstant;
import com.tongzhu.treehouse.domain.UserGoods;
import com.tongzhu.treehouse.model.TreeHouseRoom;
import com.tongzhu.treehouse.model.UserWorkPosition;
import com.tongzhu.treehouse.po.WorkPositionPO;
import com.tongzhu.treehouse.po.WorkTypePO;
import com.tongzhu.treehouse.service.TreeHouseRoomService;
import com.tongzhu.treehouse.service.UserWorkPositionService;
import com.tongzhu.treehouse.service.UserWorkTypeService;
import com.tongzhu.treehouse.service.WorkTypeService;
import com.tongzhu.treehouse.service.vo.UserWorkTypeVO;
import com.tongzhu.treehouse.service.vo.UserWorkerVO;
import com.tongzhu.treehouse.service.vo.WorkPositionVO;
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
 * 打工系统
 */
@RestController
@RequestMapping("/work")
@Api(value="打工controller",tags={"打工相关API"})
public class WorkController {

    private final int WORKER_LEAVED=21000; //宅友已离开


    @Autowired
    private UserWorkTypeService userWorkTypeService;

    @Autowired
    private WorkTypeService workTypeService;

    @Autowired
    private UserWorkPositionService userWorkPositionService;

    @Autowired
    private TreeHouseRoomService userWorkerService;

    @Autowired
    private TreeHouseRoomService treeHouseRoomService;





    /**
     * 获取工种列表
     * @param workTypePO
     *          --userId 用户Id
     * @return
     */
    @ApiOperation(value = "获取工种列表",notes = "获取工种列表  参数：[userId 用户Id]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[status  状态 0：锁住 1：解锁] [level 当前等级] [unlockLevel 解锁等级]" +
                    "[totalYieldRate 工种每分钟收益] [positionList:portraitUrl 头像URL] [positionList:status 工位状态 0： 锁定 1：解锁] " +
                    "[positionList:workStatus 工位工作状态 0：空闲 1：工作中] [order 工位序号1—5逐级上升][haveIncome 是否有收益][workTypeId 工种ID]")
    })
    @PostMapping("/workTypeList")
    public BaseReturn workTypeList(@RequestBody WorkTypePO workTypePO){
        if(StringUtils.isBlank(workTypePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空!");

        List<UserWorkTypeVO>  list=workTypeService.listUserWorkType(workTypePO.getUserId());
        return new BaseReturn("查询成功！",list);
    }



    /**
     * 手动解锁工位
     * @param positionPO
     *          --workPositionId 工位ID
     *          --userId 用户ID
     * @return
     */
    @ApiOperation(value = "手动解锁工位",notes = "手动解锁工位  参数：[userId 用户ID] [workPositionId 工位ID] ")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [goodsList:goodsId 物品ID] [goodsList:amount 物品数量]" +
                    "[workTypeDetail:status  状态 0：锁住 1：解锁] [workTypeDetail:level 当前等级] [workTypeDetail:unlockLevel 解锁等级]" +
                    "[workTypeDetail:totalYieldRate 工种每分钟收益] [workTypeDetail:positionList:portraitUrl 头像URL] " +
                    "[workTypeDetail:positionList:status 工位状态 0： 锁定 1：解锁] " +
                    "[workTypeDetail:positionList:workStatus 工位工作状态 0：空闲 1：工作中] [workTypeDetail:order 工位序号1—5逐级上升]" +
                    "[workTypeDetail:haveIncome 是否有收益][workTypeDetail:workTypeId 工种ID][position:userId 宅友用户ID] [position:workPositionId 工位ID]" +
                    "[position:portraitUrl 头像url] [position:status 工位状态 0：锁住 1：解锁]" +
                    "[position:workStatus 工位工作状态 0：空闲 1：工作中][position:incomeDetail 每分钟收益][position:userName 宅友名称]" +
                    "[position:gameFriend 是否为游戏好友][position:wxFriend 是否为微信好友][position:consume:amount 数量 可用时大于0]" +
                    "[position:consume:goodsId 可用时大于0]")
    })
    @PostMapping("/manualUnLock")
    public BaseReturn manualUnLock(@RequestBody WorkPositionPO positionPO){
        if(StringUtils.isBlank(positionPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空!");
        if(StringUtils.isBlank(positionPO.getWorkPositionId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "工位ID为空!");

        UserWorkPosition position=userWorkPositionService.findByWorkPositionId(positionPO.getWorkPositionId());
        if(position!=null ){
            //解锁消耗物品
            try {
                List<UserGoods> userGoods=userWorkPositionService.manualUnlock(positionPO.getUserId(),position.getWorkPositionId(),
                        position.getWorkTypeId(),position.getOrder());
                UserWorkTypeVO userWorkTypeVO=workTypeService.findUserWorkTypeByWorkTypeId(positionPO.getUserId(),position.getWorkTypeId());
                WorkPositionVO workPosition=userWorkPositionService.findUserWorkPositionByPositionId(positionPO.getUserId(),positionPO.getWorkPositionId());
                Map<String,Object> result=new HashMap<>();
                result.put("goodsList",userGoods);
                result.put("position",workPosition);
                result.put("workTypeDetail",userWorkTypeVO);
                return new BaseReturn("解锁成功!",result);
            }catch (HystrixRuntimeException e){
                JSONObject object=JSON.parseObject(e.getCause().getMessage().substring(e.getCause().getMessage().indexOf("content")+9));
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR,object.getString("message"));
            }
        }else {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"该工位不可解锁！");
        }
    }



    /**
     * 工位列表
     * @param workTypePO
     *          --userId 用户ID
     *          --workTypeId  工种ID
     * @return
     */
    @ApiOperation(value = "工位列表",notes = "工位列表请求参数：[userId 用户ID] [workTypeId  工种ID  1:矿洞  2：甜品   3：花店   4：当铺   5：酒楼] ")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[positionList 工位列表][workerList宅友列表] [positionList:userId 宅友用户ID]" +
                    "[positionList:workPositionId 工位ID] [positionList:userName 用户名称]" +
                    "[positionList:portraitUrl 头像url] [positionList:status 工位状态 0：锁住 1：解锁]" +
                    "[positionList:workStatus 工位工作状态 0：空闲 1：工作中][positionList:progress 进度][positionList:gameFriend 是否为游戏好友]" +
                    "[positionList:isWxFriend 是否为微信好友][positionList:incomeDetail 每分钟收益][positionList:consume:amount 数量 可用时大于0][positionList:consume:goodsId 可用时大于0]" +
                    "[workerList:userId 宅友ID][workerList:portraitUrl 头像url][workerList:name 名字][workerList:status 状态 0 ：空闲 2： 打工中]" +
                    "[workerList:workTypeId 工种ID] ")
    })
    @PostMapping("/workPositionList")
    public BaseReturn workPositionList(@RequestBody WorkTypePO workTypePO){
        if(StringUtils.isBlank(workTypePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
        if(workTypePO.getWorkTypeId()==0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"工种ID必填！");

        List<WorkPositionVO> workPositionModelList=userWorkPositionService.findListByUserIdAndWorkTypeId(workTypePO.getUserId(),workTypePO.getWorkTypeId());
        List<UserWorkerVO> workers=userWorkerService.findIdleWorkersByUserId(workTypePO.getUserId());
        Map<String,Object> result=new HashMap<>();
        result.put("positionList",workPositionModelList);
        result.put("workerList",workers);
        return new BaseReturn("查询成功!",result);
    }



    /**
     * 选择宅友派工
     * @param workPositionPO
     *          --userId  当前用户ID
     *          --workerId  宅友ID
     *          --workPositionId 工位ID
     *          --workTypeId 工种ID
     * @return
     */
    @ApiOperation(value = "选择宅友派工",notes = "选择宅友派工  参数：[userId  当前用户ID] [workerId  宅友ID]  [workPositionId 工位ID] [workTypeId 工种ID] ")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [code 21000:宅友已离开] " +
                    "[position:userId 宅友用户ID] [position:workPositionId 工位ID]" +
                    "[position:portraitUrl 头像url] [position:status 工位状态 0：锁住 1：解锁]" +
                    "[position:workStatus 工位工作状态 0：空闲 1：工作中][position:incomeDetail 每分钟收益][position:userName 宅友名称]" +
                    "[position:gameFriend 是否为游戏好友][position:wxfriend 是否为微信好友][position:consume:amount 数量 可用时大于0]" +
                    "[position:consume:goodsId 可用时大于0][workerList:userId 宅友ID][workerList:portraitUrl 头像url][workerList:name 名字]" +
                    "[workerList:status 状态 0:空闲 1:打工中][workerList:workTypeId 工种ID]" +
                    "[workTypeDetail:status 状态 0：锁住 1：解锁] [workTypeDetail:level 当前等级] [workTypeDetail:unlockLevel 解锁等级]" +
                    "[workTypeDetail:totalYieldRate 工种每分钟收益] [workTypeDetail:positionList:portraitUrl 头像URL] " +
                    "[workTypeDetail:positionList:status 工位状态 0： 锁定 1：解锁] [workTypeDetail:positionList:workStatus 工位工作状态 0：空闲 1：工作中] " +
                    "[workTypeDetail:haveIncome 是否有收益][workTypeDetail:workTypeId 工种ID]")
    })
    @PostMapping("/assignWork")
    public BaseReturn assignWork(@RequestBody WorkPositionPO workPositionPO){
        if(StringUtils.isBlank(workPositionPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空!");
        if(StringUtils.isBlank(workPositionPO.getWorkerId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "宅友ID为空!");
        if(StringUtils.isBlank(workPositionPO.getWorkPositionId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "工位ID为空!");
        if(workPositionPO.getWorkTypeId()==0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "工种ID为空!");

        //判断工位是否解锁
        UserWorkPosition position=userWorkPositionService.findByWorkPositionId(workPositionPO.getWorkPositionId());
        if(position!=null && position.getStatus().equals(StatusConstant.WORK_POSITION_LOCK))
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"工位锁定不可派工!");


        //判断当前宅友是否正可以派工
        TreeHouseRoom room=treeHouseRoomService.checkWorkIsIdle(workPositionPO.getUserId(),workPositionPO.getWorkerId());
        if(room==null){
            UserWorkerVO worker=userWorkerService.findWorkerByUserIdAndWorkerId(workPositionPO.getUserId(),workPositionPO.getWorkerId());
            WorkPositionVO workPosition=userWorkPositionService.findUserWorkPositionByPositionId(workPositionPO.getUserId(),workPositionPO.getWorkPositionId());
            UserWorkTypeVO userWorkTypeVO=workTypeService.findUserWorkTypeByWorkTypeId(workPositionPO.getUserId(),workPositionPO.getWorkTypeId());
            Map<String,Object> result=new HashMap<>();
            result.put("workerList",worker);
            result.put("position",workPosition);
            result.put("workTypeDetail",userWorkTypeVO);
            return new BaseReturn(this.WORKER_LEAVED,"该宅友已离开，请重新选择",result);
        }

        if (StringUtils.isBlank(position.getWorkerId())) {
            userWorkPositionService.assignWork(workPositionPO.getUserId(),workPositionPO.getWorkerId(),workPositionPO.getWorkPositionId());
            WorkPositionVO workPosition=userWorkPositionService.findUserWorkPositionByPositionId(workPositionPO.getUserId(),workPositionPO.getWorkPositionId());
            List<UserWorkerVO> workers=userWorkerService.findIdleWorkersByUserId(workPositionPO.getUserId());
            UserWorkTypeVO userWorkTypeVO=workTypeService.findUserWorkTypeByWorkTypeId(workPositionPO.getUserId(),workPositionPO.getWorkTypeId());
            Map<String,Object> result=new HashMap<>();
            result.put("position",workPosition);
            result.put("workerList",workers);
            result.put("workTypeDetail",userWorkTypeVO);
            return new BaseReturn("选择宅友派工成功!",result);
        }else{
            userWorkPositionService.assignWorkChange(workPositionPO.getUserId(),workPositionPO.getWorkerId(),workPositionPO.getWorkPositionId());
            WorkPositionVO workPosition=userWorkPositionService.findUserWorkPositionByPositionId(workPositionPO.getUserId(),workPositionPO.getWorkPositionId());
            List<UserWorkerVO> workers=userWorkerService.findIdleWorkersByUserId(workPositionPO.getUserId());
            UserWorkTypeVO userWorkTypeVO=workTypeService.findUserWorkTypeByWorkTypeId(workPositionPO.getUserId(),workPositionPO.getWorkTypeId());
            Map<String,Object> result=new HashMap<>();
            result.put("workTypeDetail",userWorkTypeVO);
            result.put("position",workPosition);
            result.put("workerList",workers);
            return new BaseReturn("更换宅友派工成功!",result);
        }

    }



    /**
     * 解除佣工
     * @param workPositionPO
     *          --userId  当前用户ID
     *          --workerId  宅友ID
     *          --workPositionId 工位ID
     *          --workTypeId 工种ID
     * @return
     */
    @ApiOperation(value = "解除佣工",notes = "解除佣工请求参数：[userId  当前用户ID] [workerId  宅友ID]  [workPositionId 工位ID] [workTypeId 工种ID] ")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 " +
                    "[position:userId 宅友用户ID] [position:workPositionId 工位ID]" +
                    "[position:portraitUrl 头像url] [position:status 工位状态 0：锁住 1：解锁]" +
                    "[position:workStatus 工位工作状态 0：空闲 1：工作中][position:incomeDetail 每分钟收益][position:userName 宅友名称]" +
                    "[position:gameFriend 是否为游戏好友][position:wxFriend 是否为微信好友][position:consume:amount 数量 可用时大于0]" +
                    "[position:consume:goodsId 可用时大于0][workerList:userId 宅友ID][workerList:portraitUrl 头像url][workerList:name 名字]" +
                    "[workerList:status 状态 0:空闲 1:打工中][workerList:workTypeId 工种ID]" +
                    "[workTypeDetail:status 状态 0：锁住 1：解锁] [workTypeDetail:level 当前等级] [workTypeDetail:unlockLevel 解锁等级]" +
                    "[workTypeDetail:totalYieldRate 工种每分钟收益] [workTypeDetail:positionList:portraitUrl 头像URL] " +
                    "[workTypeDetail:positionList:status 工位状态 0： 锁定 1：解锁] [workTypeDetail:positionList:workStatus 工位工作状态 0：空闲 1：工作中] " +
                    "[workTypeDetail:haveIncome 是否有收益][workTypeDetail:workTypeId 工种ID]")
    })
    @PostMapping("/makeWorkPositionIdle")
    public BaseReturn makeWorkPositionIdle(@RequestBody WorkPositionPO workPositionPO){
        if(StringUtils.isBlank(workPositionPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空!");
        if(StringUtils.isBlank(workPositionPO.getWorkerId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "宅友ID为空!");
        if(StringUtils.isBlank(workPositionPO.getWorkPositionId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "工位ID为空!");
        if(workPositionPO.getWorkTypeId()==0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "工种ID为空!");

        //判断工位是否解锁
        UserWorkPosition position=userWorkPositionService.findByWorkPositionId(workPositionPO.getWorkPositionId());
        if(position!=null && position.getStatus().equals(WorkPositionConstant.STATUS_LOCK))
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"非法操作!");

        TreeHouseRoom room=treeHouseRoomService.findWorkersByUserIdAndWorkerId(workPositionPO.getUserId(),workPositionPO.getWorkerId());
        if(room==null){
            WorkPositionVO workPosition=userWorkPositionService.findUserWorkPositionByPositionId(workPositionPO.getUserId(),workPositionPO.getWorkPositionId());
            UserWorkTypeVO userWorkTypeVO=workTypeService.findUserWorkTypeByWorkTypeId(workPositionPO.getUserId(),workPositionPO.getWorkTypeId());
            List<UserWorkerVO> workers=userWorkerService.findIdleWorkersByUserId(workPositionPO.getUserId());
            Map<String,Object> result=new HashMap<>();
            result.put("position",workPosition);
            result.put("workTypeDetail",userWorkTypeVO);
            result.put("workerList",workers);
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"该宅友已被买走!",result);
        }

            userWorkPositionService.makeWorkPositionIdle(workPositionPO.getUserId(),workPositionPO.getWorkerId());

            WorkPositionVO workPosition=userWorkPositionService.findUserWorkPositionByPositionId(workPositionPO.getUserId(),workPositionPO.getWorkPositionId());
            List<UserWorkerVO> workers=userWorkerService.findIdleWorkersByUserId(workPositionPO.getUserId());
            UserWorkTypeVO userWorkTypeVO=workTypeService.findUserWorkTypeByWorkTypeId(workPositionPO.getUserId(),workPositionPO.getWorkTypeId());
            Map<String,Object> result=new HashMap<>();
            result.put("workTypeDetail",userWorkTypeVO);
            result.put("position",workPosition);
            result.put("workerList",workers);
            return new BaseReturn("解除佣工成功!",result);
    }



    /**
     * 获取收益
     * @param workTypePO
     *          --userId 用户Id
     *          --workTypeId 工种ID
     * @return
     */
    @ApiOperation(value = "获取收益",notes = "获取收益  参数：[userId 用户Id] [workTypeId 工种ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[incomeList:goodsId 物品ID] [incomeList:amount 收益数量]" +
                    "[workTypeDetail:status 状态 0：锁住 1：解锁] [workTypeDetail:level 当前等级] [workTypeDetail:unlockLevel 解锁等级]" +
                    "[workTypeDetail:totalYieldRate 工种每分钟收益] [workTypeDetail:positionList:portraitUrl 头像URL]" +
                    "[workTypeDetail:positionList:status 工位状态 0： 锁定 1：解锁] [workTypeDetail:positionList:workStatus 工位工作状态 0：空闲 1：工作中]" +
                    "[workTypeDetail:haveIncome 是否有收益][workTypeDetail:workTypeId 工种ID]" +
                    "[workTypeDetail:userId 宅友ID][workTypeDetail:portraitUrl 头像url][workTypeDetail:name 名字][workTypeDetail:status 状态 0:空闲 1:打工中]")
    })
    @PostMapping("/getWorkIncome")
    public BaseReturn getWorkIncome(@RequestBody WorkTypePO workTypePO){
        if(StringUtils.isBlank(workTypePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空!");
        if(workTypePO.getWorkTypeId()==0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "工种ID为空!");

        List<UserGoods>incomes=userWorkTypeService.getWorkIncomeAll(workTypePO.getUserId(),workTypePO.getWorkTypeId());
        Map<String,Object> result=new HashMap<>();
        UserWorkTypeVO userWorkTypeVO=workTypeService.findUserWorkTypeByWorkTypeId(workTypePO.getUserId(),workTypePO.getWorkTypeId());
        result.put("workTypeDetail",userWorkTypeVO);
        result.put("incomeList",incomes);
        return new BaseReturn("获取收益成功!",result);
    }




}
