package com.tongzhu.welfare.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.constant.GoodsConstant;
import com.tongzhu.welfare.domain.Friend;
import com.tongzhu.welfare.domain.User;
import com.tongzhu.welfare.po.MallPo;
import com.tongzhu.welfare.po.SendFlowersPo;
import com.tongzhu.welfare.service.FriendService;
import com.tongzhu.welfare.service.MessageService;
import com.tongzhu.welfare.service.SendFlowersService;
import com.tongzhu.welfare.service.UserService;
import com.tongzhu.welfare.vo.ReceiveGoldVo;
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

import java.util.HashMap;
import java.util.Map;

/**
 * 送花系统相关Controller
 *
 * @author 吴恒斌
 * @date 2018年11月10日
 */
@Api(value = "送花系统controller", tags = {"送花系统API"})
@RestController
@RequestMapping(value = "/building")
public class FlowersController {

    private static Logger log = LoggerFactory.getLogger(FlowersController.class);


    @Autowired
    private UserService userService;
    @Autowired
    private SendFlowersService sendFlowersService;
    @Autowired
    private FriendService friendService;
    @Autowired
    private MessageService messageService;



    /**
     * 给指定的用户进行送花
     * @param mallPo
     * @return
     */
    @ApiOperation(value = "给指定的用户进行送花",notes = "请求参数说明 [userId 用户ID] [receiveId  收花人ID] [goodsId  花的道具ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[intimacy 亲密度]")
    })
    @PostMapping("/sendFlowers")
    public BaseReturn sendFlowers(@RequestBody SendFlowersPo sendFlowersPo){
        if(StringUtils.isBlank(sendFlowersPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
        if (sendFlowersPo.getGoodsId() == 0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "送花goodsId！");
        if(StringUtils.isBlank(sendFlowersPo.getReceiveId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "收花人ID为空!");
    	String userId =  sendFlowersPo.getUserId();
    	int goodsId = sendFlowersPo.getGoodsId();
    	String receiveId =  sendFlowersPo.getReceiveId();
        User userDetail=userService.findByUserId(userId);
        
        if(userDetail!=null){
        	Friend friend = friendService.checkIsMyFriend(sendFlowersPo.getUserId(),sendFlowersPo.getReceiveId());
            if(friend!=null){
            	int i = sendFlowersService.sendFlowersById(userId, receiveId, goodsId,true);
            	if(i == -1){
            		return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"背包鲜花数量不足！");
            	}
            	JSONObject json = new JSONObject();
            	json.put("intimacy", i);
                if(GoodsConstant.FLOWER_999.equals(goodsId)){
                    Map<String,Object> msg=new HashMap<>();
                    msg.put("code",0);
                    msg.put("type","sendFlowerEffect");
                    messageService.sendMessageToSomeBody(receiveId, JSON.toJSONString(msg));
                }
            	return new BaseReturn("送花成功！",json);
            }else {
            	int i = sendFlowersService.sendFlowersById(userId, receiveId, goodsId,false);
            	JSONObject json = new JSONObject();
            	json.put("intimacy", i);
            	if(GoodsConstant.FLOWER_999.equals(goodsId)){
                    Map<String,Object> msg=new HashMap<>();
                    msg.put("code",0);
                    msg.put("type","sendFlowerEffect");
                    messageService.sendMessageToSomeBody(receiveId, JSON.toJSONString(msg));
                }
            	return new BaseReturn("送花成功！",json);
            }
        }else{
        	log.debug("sendFlowers:未查询到该名玩家的信息，请重新输入ID！");
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"未查询到该名玩家的信息，请重新输入ID！");
        }
    }
    
    /**
     * 快速购买花
     * @param mallPo
     * @return
     */
    @ApiOperation(value = "下单购买",notes = "请求参数说明 [userId 用户ID] [num 数量] [goodsId  花的道具ID]",response = ReceiveGoldVo.class)
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[goodsId 物品的表ID][amount 数量][type 物类型:1:道具；2：装备；3：武器]")
    })
    @PostMapping("/flowersPay")
    public BaseReturn flowersPay(@RequestBody MallPo mallPo){
        if(StringUtils.isBlank(mallPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
        if(StringUtils.isBlank(String.valueOf(mallPo.getGoodsId())))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"商品Id为空！");
    	String userId =  mallPo.getUserId();
    	int goodsId = mallPo.getGoodsId();
    	int num = mallPo.getNum();
        User userDetail=userService.findByUserId(userId);
        if(userDetail!=null){
        	try {
        		ReceiveGoldVo receiveGoldVo = sendFlowersService.flowersPay(userId,goodsId,num);
        		if(receiveGoldVo.getAmount() > 0){
        			return new BaseReturn("购买商品成功！",receiveGoldVo);
        		}else{
        			return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"钻石余额不足！");
        		}
			} catch (Exception e) {
				e.printStackTrace();
				log.debug("flowers:"+e.toString());
				return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"钻石余额不足！");
			}
        }else{
        	log.debug("flowers:未查询到该名玩家的信息，请重新输入ID！");
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"未查询到该名玩家的信息，请重新输入ID！");
        }
    }
}
