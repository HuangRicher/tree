package com.tongzhu.welfare.controller;

import com.alibaba.fastjson.JSONObject;
import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.welfare.domain.User;
import com.tongzhu.welfare.po.WelfarePo;
import com.tongzhu.welfare.service.UserService;
import com.tongzhu.welfare.service.WelfareService;
import com.tongzhu.welfare.vo.ReceiveGoldVo;
import com.tongzhu.welfare.vo.WelfareVo;
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

import java.util.List;

/**
 * 福利系统相关Controller
 *
 * @author 吴恒斌
 * @date 2018年8月30日
 */
@Api(value = "福利系统controller", tags = {"福利系统API"})
@RestController
@RequestMapping(value = "/welfare")
public class WelfareController {

//    private static Logger log = LoggerFactory.getLogger(WelfareController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private WelfareService welfareService;
    
    /**
     * 根据userId获取七天奖励列表情况
     *
     * @return
     */
    @ApiOperation(value = "获取奖励列表",notes = "请求参数说明 [userId 用户ID]",response = WelfareVo.class)
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明")
    })
    @PostMapping("/getRewardsListByUserId")
    public BaseReturn getRewardsListByUserId(@RequestBody WelfarePo welfarePo){
        if(StringUtils.isBlank(welfarePo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
        //根据用户ID获取最近的领奖情况，然后判断可领取的奖品以及已经领取的奖品，这里的奖励根据第一次登陆进行不断的循环
    	String userId =  welfarePo.getUserId();
        User userDetail=userService.findByUserId(userId);
        if(userDetail!=null){ 
        	List<WelfareVo> list = welfareService.getRewardsListByUserId(userId,userDetail);
        	List<WelfareVo> list2 = welfareService.getMonthRewardsListByUserId(userId,userDetail);
        	int days = welfareService.getDaysByUserId(userId);
        	JSONObject jsonObject = new JSONObject();
        	jsonObject.put("DayRewardsList", list);
        	jsonObject.put("MonthRewardsList", list2);
        	jsonObject.put("RewardsDays", days);
            return new BaseReturn("获取奖励列表成功！",jsonObject);
        }else{
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"未查询到该名玩家的建筑信息，请重新输入ID！");
        }
    }
   
    /**
     * 根据userId获取七天奖励（一次一个奖品）
     * @param 
     * @return
     */
    @ApiOperation(value = "点击领取七天奖励（一次一个奖品）",notes = "请求参数说明 [userId 用户ID][dataNum 领取福利的天数]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明")
    })
    @PostMapping("/receiveRewardsByUserId")
    public BaseReturn receiveRewardsByUserId(@RequestBody WelfarePo welfarePo){
        if(StringUtils.isBlank(welfarePo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
        if(StringUtils.isBlank(String.valueOf(welfarePo.getDataNum())))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"领取天数为空！");
    	String userId =  welfarePo.getUserId();
    	int dateNum =  welfarePo.getDataNum();
        User userDetail=userService.findByUserId(userId);
        if(userDetail!=null){
        	ReceiveGoldVo receiveGoldVo = welfareService.receiveRewardsByUserId(userId,dateNum);
        	if(Integer.valueOf(receiveGoldVo.getGoodsId()) == 0){
        		return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"该奖励已经领过，请不要重复获取奖励!");
        	}else{
        		return new BaseReturn("领取七天奖励成功!",receiveGoldVo);
        	}
        }else{
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"未查询到该名玩家的建筑信息，请重新输入ID！");
        }
    }
    
    /**
     * 根据userId点击获取月奖励
     * @param 
     * @return
     */
    @ApiOperation(value = "点击获取月奖励（一次一个奖品）",notes = "请求参数说明 [userId 用户ID][dataNum 领取福利的天数]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明")
    })
    @PostMapping("/receiveMonthRewardsByUserId")
    public BaseReturn receiveMonthRewardsByUserId(@RequestBody WelfarePo welfarePo){
        if(StringUtils.isBlank(welfarePo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
        if(StringUtils.isBlank(String.valueOf(welfarePo.getDataNum())))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"奖励ID为空！");
        //根据用户ID获取最近的领奖情况，然后判断可领取的奖品以及已经领取的奖品，这里的奖励根据第一次登陆进行不断的循环
    	String userId =  welfarePo.getUserId();
    	int dateNum =  welfarePo.getDataNum();
        User userDetail=userService.findByUserId(userId);
        if(userDetail!=null){
        	ReceiveGoldVo receiveGoldVo = welfareService.receiveMonthRewardsByUserId(userId,dateNum);
        	if(Integer.valueOf(receiveGoldVo.getGoodsId()) == 0){
        		return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"该奖励已经领过，请不要重复获取奖励!");
        	}
            return new BaseReturn("获取月奖励成功！",receiveGoldVo);
        }else{
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"未查询到该名玩家的信息，请重新输入ID！");
        }
    }

    /**
     * 判断当前用户是否有可领取的福利
     * @param
     * @return
     */
    @ApiOperation(value = "判断当前用户是否有可领取的福利",notes = "请求参数说明 [userId 用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明")
    })
    @PostMapping("/getWelfareStatusByUserId")
    public BaseReturn getWelfareStatusByUserId(@RequestBody WelfarePo welfarePo){
        if(StringUtils.isBlank(welfarePo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
    	String userId =  welfarePo.getUserId();
        User userDetail=userService.findByUserId(userId);
        if(userDetail!=null){
        	int result = welfareService.getWelfareStatusByUserId(userId);
    		return new BaseReturn("获取数据成功！",result);
        }else{
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"未查询到该名玩家的信息，请重新输入ID！");
        }
    }
    
    /**
     * 获取用户离线收益
     * @param
     * @return
     */
    @ApiOperation(value = "判断当前用户是否有可领取的福利",notes = "请求参数说明 [userId 用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明")
    })
    @PostMapping("/getOfflineByUserId")
    public BaseReturn getOfflineByUserId(@RequestBody WelfarePo welfarePo){
        if(StringUtils.isBlank(welfarePo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
    	String userId =  welfarePo.getUserId();
        User userDetail=userService.findByUserId(userId);
        if(userDetail!=null){
        	JSONObject result = welfareService.getOfflineByUserId(userId);
    		return new BaseReturn("获取数据成功！",result);
        }else{
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"未查询到该名玩家的信息，请重新输入ID！");
        }
    }
}
