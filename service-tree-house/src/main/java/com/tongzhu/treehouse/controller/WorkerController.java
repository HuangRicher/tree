package com.tongzhu.treehouse.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.common.Pager;
import com.tongzhu.treehouse.constant.StatusConstant;
import com.tongzhu.treehouse.constant.WorkerConstant;
import com.tongzhu.treehouse.domain.FriendDetail;
import com.tongzhu.treehouse.domain.User;
import com.tongzhu.treehouse.domain.UserGoods;
import com.tongzhu.except.CheckException;
import com.tongzhu.treehouse.model.TreeHouseRoom;
import com.tongzhu.treehouse.po.WorkerPO;
import com.tongzhu.treehouse.service.TreeHousePurchaseHistoryService;
import com.tongzhu.treehouse.service.TreeHouseRoomService;
import com.tongzhu.treehouse.service.UserService;
import com.tongzhu.treehouse.service.vo.TreeHousePurchaseHistoryVO;
import com.tongzhu.treehouse.service.vo.UserFriendDetailVO;
import com.tongzhu.treehouse.service.vo.UserVO;
import com.tongzhu.util.DateComputeUtil;
import com.tongzhu.util.UUIDUtil;
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

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 宅友系统
 */
@Api(value="宅友controller",tags={"宅友系统相关API"})
@RestController
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    private TreeHouseRoomService treeHouseRoomService;

    @Autowired
    private UserService userService;

    @Autowired
    private TreeHousePurchaseHistoryService treeHousePurchaseHistoryService;




    /**
     * 购买宅友
     * @param workerPO
     *          --userId  用户ID
     *          --workerId 宅友ID
     * @return
     */
    @ApiOperation(value = "购买宅友",notes = "请求参数说明 [userId 用户ID][workerId 宅友ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [goodsId 物品ID] [amount 数量]")
    })
    @PostMapping("/buy")
    public BaseReturn buyWorker(@RequestBody WorkerPO workerPO){
        if(StringUtils.isBlank(workerPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
        if(StringUtils.isBlank(workerPO.getWorkerId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"宅友ID为空！");

        LocalDateTime time=LocalDateTime.now();
        TreeHouseRoom treeHouseRoom=new TreeHouseRoom();
        treeHouseRoom.setId(UUIDUtil.generateUUID());
        treeHouseRoom.setStatus(StatusConstant.USER_WORKER_IDLE);
        treeHouseRoom.setWorkerId(workerPO.getWorkerId());
        treeHouseRoom.setIsGameFriend(false);
        treeHouseRoom.setIsWxFriend(false);
        treeHouseRoom.setProtectStartDate(DateComputeUtil.localDateTimeToDate(time));
        treeHouseRoom.setProtectEndDate(DateComputeUtil.localDateTimeToDate(time.plusHours(WorkerConstant.PROTECT_FREE_HOUR_2)));
        try {
            UserGoods userGoods=treeHouseRoomService.updateForBuyWorker(workerPO.getUserId(),treeHouseRoom);
            return new BaseReturn("购买成功！",userGoods);
        }catch (HystrixRuntimeException e){
            JSONObject object=JSON.parseObject(e.getCause().getMessage().substring(e.getCause().getMessage().indexOf("content")+9));
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,object.getString("message"));
        }catch(CheckException e){
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,e.getMessage());
        }

    }


    /**
     * 释放宅友
     * @param workerPO
     *          --userId  用户ID
     *          --workerId 宅友ID
     * @return
     */
    @ApiOperation(value = "释放宅友",notes = "请求参数说明 [userId 用户ID][workerId 宅友ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [workerCount 宅友数量]")
    })
    @PostMapping("/releaseWorker")
    public BaseReturn releaseWorker(@RequestBody WorkerPO workerPO){
        if(StringUtils.isBlank(workerPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
        if(StringUtils.isBlank(workerPO.getWorkerId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"宅友ID为空！");

        treeHouseRoomService.releaseWorker(workerPO.getUserId(),workerPO.getWorkerId());
        List<FriendDetail> list=treeHouseRoomService.findWorkersList(workerPO.getUserId());
        int count=treeHouseRoomService.countRoomsByUserId(workerPO.getUserId());
        Map<String, Object> map = new HashMap<>();
        map.put("workerCount", list.size()+"/"+count);
        return new BaseReturn("释放宅友成功！",map);
    }


    /**
     * 降低宅友身价（减10%）
     * @param workerPO
     *          --userId  用户ID
     *          --workerId 宅友ID
     * @return
     */
    @ApiOperation(value = "降低宅友身价（减10%）",notes = "请求参数说明 [userId 用户ID] [workerId 宅友ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [sellingPrice 宅友身价] [userId 宅友用户ID] [canReduceSellingPrice 能否降低宅友身价] ")
    })
    @PostMapping("/reduceWorkerPrice")
    public BaseReturn reduceWorkerPrice(@RequestBody WorkerPO workerPO){
        if(StringUtils.isBlank(workerPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
        if(StringUtils.isBlank(workerPO.getWorkerId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"宅友ID为空！");

        try {
            User user=userService.reduceSellingPrice(workerPO.getUserId(),workerPO.getWorkerId());
            TreeHouseRoom room=treeHouseRoomService.findWorkersByUserIdAndWorkerId(workerPO.getUserId(),workerPO.getWorkerId());
            UserFriendDetailVO worker=new UserFriendDetailVO();
            if(room!=null){
                worker.setUserId(user.getUserId());
                worker.setSellingPrice(user.getSellingPrice());
                worker.setCanReduceSellingPrice(true);
                if(room.getReducePriceDate()!=null &&
                        DateComputeUtil.DateToLocalDate(room.getReducePriceDate()).equals(DateComputeUtil.DateToLocalDate(new Date())))
                    worker.setCanReduceSellingPrice(WorkerConstant.CAN_NOT_REDUCE_SELLING_PRICE);

            }
            return new BaseReturn("降低宅友身价成功!" ,worker);
        }catch (HystrixRuntimeException e){
            JSONObject object=JSON.parseObject(e.getCause().getMessage().substring(e.getCause().getMessage().indexOf("content")+9));
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,object.getString("message"));
        }
    }


    /**
     * 保护宅友
     * @param workerPO
     *          --userId  用户ID
     *          --workerId 宅友ID
     *          --protectHours 保护小时数
     * @return
     */
    @ApiOperation(value = "保护宅友",notes = "请求参数说明 [userId 要查找的好友用户ID] [workerId 宅友ID] [protectHours 保护小时数]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [goods:id 物品ID][goods:amount 物品剩余数量] " +
                    "[worker:userId 宅友用户ID][worker:protectTimeDetail 保护剩余时间]")
    })
    @PostMapping("/protectWorker")
    public BaseReturn protectWorker(@RequestBody WorkerPO workerPO){
        if(StringUtils.isBlank(workerPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
        if(StringUtils.isBlank(workerPO.getWorkerId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"宅友ID为空！");
        if(workerPO.getProtectHours()==0 || !(workerPO.getProtectHours()==WorkerConstant.PROTECT_HOUR_6 ||
                workerPO.getProtectHours()==WorkerConstant.PROTECT_HOUR_24 ||
                workerPO.getProtectHours()==WorkerConstant.PROTECT_HOUR_72))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"保护时间不对！");
        TreeHouseRoom checkRoom=treeHouseRoomService.findWorkersByUserIdAndWorkerId(workerPO.getUserId(),workerPO.getWorkerId());
        if(checkRoom.getProtectEndDate()!=null){
            Instant instant = checkRoom.getProtectEndDate().toInstant();
            ZoneId zone = ZoneId.systemDefault();
            LocalDateTime loginDateTime = LocalDateTime.ofInstant(instant, zone);
            LocalDateTime currentDateTime=LocalDateTime.now();
            Duration duration=Duration.between(loginDateTime,currentDateTime);
            if(duration.toHours()<=0) return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"该宅友处于保护状态，稍后再操作！");
        }

        UserGoods goods=treeHouseRoomService.protectWorker(workerPO.getUserId(),workerPO.getWorkerId(),workerPO.getProtectHours());
        TreeHouseRoom room=treeHouseRoomService.findWorkersByUserIdAndWorkerId(workerPO.getUserId(),workerPO.getWorkerId());
        UserFriendDetailVO worker=new UserFriendDetailVO();
        if(room!=null){
            worker.setUserId(room.getWorkerId());
            worker.setProtectTimeDetail(DateComputeUtil.getRemainderTimeOfProtect(room.getProtectEndDate()));
        }
        Map<String,Object> goodsMap=new HashMap<>();
        goodsMap.put("id",goods.getGoodsId());
        goodsMap.put("amount",goods.getAmount());
        Map<String,Object> result=new HashMap<>();
        result.put("goods",goodsMap);
        result.put("worker",worker);

        return new BaseReturn("保护宅友成功！",result);
    }



    /**
     * 推荐宅友列表
     * @param workerPO
     * @return
     */
    @ApiOperation(value = "推荐宅友列表",notes = "请求参数说明 [userId 用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [userId 用户ID] [userName 用户名字] [portraitUrl 头像url][status 1:工作中 0：空闲中][isGameFriend 是否为好友]" +
                    "[sellingPrice 身价]")
    })
    @PostMapping("/recommendToBuyWorkList")
    public BaseReturn recommendToBuyWorkList(@RequestBody WorkerPO workerPO){
        if(StringUtils.isBlank(workerPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空!");
        List<UserVO> list=treeHouseRoomService.recommendToBuyList(workerPO.getUserId());
        return new BaseReturn("查询成功！",list);
    }

    /**
     * 获取宅友消费记录
     * @param workerPO
     * @return
     */
    @ApiOperation(value = "获取宅友消费记录",notes = "请求参数说明 [userId 用户ID][pageNum 页号] [pageSize 每页大小]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [creationTime 购买时间] [sellUserId  被卖宅友id] [sellName  被卖宅友名称]" +
                    "[bargainorUserId  出售宅友玩家id] [bargainorName   出售宅友玩家名称]  [purchaserUserId    购买宅友玩家id]" +
                    " [purchaserUserName  购买宅友玩家名称 ]  [monetaryAmount  购买宅友金币数量]  [sellingPrice  被购买后身价] " +
                    " [type 记录类型 0-宅友被买走 1-自由身被别人买走 2-有主人被买走] [id 消息记录主键 唯一标识]" +
                    "[currentPage 当前页码] [pageSize 每页记录数] [pageTotal 总页数] [recordTotal 总记录数]")
    })
    @PostMapping("/purchaseHistoryList")
    public BaseReturn purchaseHistoryList(@RequestBody WorkerPO workerPO){
        if(StringUtils.isBlank(workerPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空!");
        if(workerPO.getPageNum()==0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"页码为0！");
        if(workerPO.getPageSize()==0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"每页大小为0!");

        Pager<TreeHousePurchaseHistoryVO> page=treeHousePurchaseHistoryService.findByPage(workerPO.getUserId(),workerPO.getPageNum(),workerPO.getPageSize());
        return new BaseReturn("查询成功！",page);
    }

    /**
     * 删除宅友消息记录
     * @param workerPO
     * @return
     */
    @ApiOperation(value = "删除宅友消息记录",notes = "请求参数说明 [id 消息id  获取宅友消费记录接口中返回]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [success true 成功] [code 0 成功] [msg 返回信息]")
    })
    @PostMapping("/delPurchaseHistory")
    public BaseReturn delPurchaseHistory(@RequestBody WorkerPO workerPO){
        if(StringUtils.isBlank(workerPO.getId()))
            return  new BaseReturn(BaseReturnCode.PARAMS_ERROR,"ID为空!");

        int i = treeHousePurchaseHistoryService.delPurchaseHistory(workerPO.getId());
        if(i <=0) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"文件状态有误！");
        }
        return new BaseReturn("删除成功！");
    }


}
