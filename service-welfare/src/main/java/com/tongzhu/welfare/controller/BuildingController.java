package com.tongzhu.welfare.controller;

import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.welfare.constant.BuildingConstant;
import com.tongzhu.welfare.domain.TreeHouse;
import com.tongzhu.welfare.domain.User;
import com.tongzhu.welfare.po.BuildingPo;
import com.tongzhu.welfare.service.BuildingService;
import com.tongzhu.welfare.service.TreeHouseService;
import com.tongzhu.welfare.service.UserService;
import com.tongzhu.welfare.vo.BuildingVo;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 建筑系统相关Controller
 *
 * @author 吴恒斌
 * @date 2018年8月30日
 */
@Api(value = "建筑系统controller", tags = {"建筑系统API"})
@RestController
@RequestMapping(value = "/building")
public class BuildingController {

    private static Logger log = LoggerFactory.getLogger(BuildingController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private TreeHouseService treeHouseService;
    /**
     * 根据userId查询建筑相关信息
     * @param buildingPo
     * @return
     */
    @ApiOperation(value = "根据userId查找所有建筑信息",notes = "请求参数说明 [userId 用户ID]",response = BuildingVo.class)
    @PostMapping("/searchBuildingByUserId")
    public BaseReturn searchBuildingByUserId(@RequestBody BuildingPo buildingPo){
        if(StringUtils.isBlank(buildingPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
    	String userId =  buildingPo.getUserId();
        User userDetail=userService.findByUserId(userId);
        if(userDetail!=null){
        	List<BuildingVo> list = new ArrayList<>();
        	list = buildingService.getbuildingByUserId(userId);
            TreeHouse treeHouse = treeHouseService.findByUserId(userId);
            Map<String,Object> map =  new HashMap<>();
            map.put("list",list);
            map.put("ambienceCount",treeHouse.getAmbienceCount());
            map.put("amusementCount",treeHouse.getAmusementCount());
            map.put("environmentCount",treeHouse.getEnvironmentCount());
            return new BaseReturn("查找建筑信息成功！",map);
        }else{
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"未查询到该名玩家的建筑信息，请重新输入ID！");
        }
    }
    
    /**
     * 根据userId查询建筑相关信息
     * @param buildingPo
     * @return
     */
    @ApiOperation(value = "初始化方法",notes = "请求参数说明 [userId 用户ID]",response = BuildingVo.class)
    @PostMapping("/init")
    public BaseReturn init(@RequestBody BuildingPo buildingPo){
        if(StringUtils.isBlank(buildingPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
    	String userId =  buildingPo.getUserId();
    	buildingService.initBuildingUser(userId);
        return new BaseReturn("查找建筑信息成功！");
    }
    
    /**
     * 根据userId查询建筑相关信息
     * @param buildingPo
     * @return
     */
    @ApiOperation(value = "缓存建筑信息",notes = "请求参数说明 [userId 用户ID]",response = BuildingVo.class)
    @PostMapping("/initBuilding")
    public BaseReturn initBuilding(@RequestBody BuildingPo buildingPo){
        if(StringUtils.isBlank(buildingPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
    	String userId =  buildingPo.getUserId();
    	buildingService.cacheBuildingSettingInfo();
        return new BaseReturn("缓存建筑信息成功！");
    }
    
    /**
     * 根据userId升级建筑返回升级时间
     * @param buildingPo
     * @return
     * @throws Exception 
     *///给指定类型的建筑进行升级，传用户ID以及建筑类型，需要处理升级后的功能性变化，第一个是金币数
    @ApiOperation(value = "根据userId升级建筑返回升级时间",notes = "请求参数说明 [userId 用户ID][buildingType 建筑类型]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[updateTime 升级该建筑所需时间单位为秒]")
    })
    @PostMapping("/upgradeBuildingTime")
    public BaseReturn upgradeBuildingTime(@RequestBody BuildingPo buildingPo){
        if(StringUtils.isBlank(buildingPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
        if(StringUtils.isBlank(String.valueOf(buildingPo.getBuildingType())))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"建筑类型！");
        
    	String userId =  buildingPo.getUserId();
    	int buildingType =  buildingPo.getBuildingType();
        User userDetail=userService.findByUserId(userId);
        try {
            if(userDetail!=null){
//            	BuildingUser buildingUser = new BuildingUser();
            	int userGrade = userDetail.getGrade();
            	Integer updateTime = buildingService.upgradeBuildingTime(userId,buildingType,userGrade);
            	Map<String, Integer> map = new HashMap<>();
            	map.put("updateTime", updateTime);
            	map.put("buildingStatus", BuildingConstant.BUILDING_UPDATE_TRUE);
        		return new BaseReturn("建筑升级成功！",map);
            }else{
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"未查询到该名玩家的建筑信息，请重新输入ID！");
            }
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseReturn(BaseReturnCode.PROCESS_ERROR,e.getMessage());
		}
    }
    
    /**
     * 根据userId升级建筑数据
     * @param biuldingPO
     * @return
     * @throws Exception 
     *///给指定类型的建筑进行升级，传用户ID以及建筑类型，需要处理升级后的功能性变化，第一个是金币数
/*    @ApiOperation(value = "根据userId升级建筑",notes = "请求参数说明 [userId 用户ID][buildingType 建筑类型]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[updateTime 升级该建筑所需时间单位为秒] [buildingName 建筑名称] [buildingGrade 建筑等级] " +
                    "[buildingStatus 建筑状态 ：1：开启 2：未开启 ] [buildingDetailed 建筑描述][buildingType 建筑类型：1：树屋；"+
                    " 2：金库；3：福利社；4：婚房；5：教堂；6：铁匠铺；7：宠物店；8：酒馆；9：雕像；] " +
                    "[buildingGold 建筑出产金币数量] [buildingUpgrade 建筑升级所需金币数]")
    })
    @PostMapping("/upgradeBuildingByUserId")
    public BaseReturn upgradeBuildingByUserId(@RequestBody BuildingPo buildingPo){
        if(StringUtils.isBlank(buildingPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
        if(StringUtils.isBlank(String.valueOf(buildingPo.getBuildingType())))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"建筑类型！");
        
    	String userId =  buildingPo.getUserId();
    	int buildingType =  buildingPo.getBuildingType();
        User userDetail=userService.findByUserId(userId);
        try {
            if(userDetail!=null){
            	buildingService.upgradeBuildingByUserId(userId,buildingType);
                return new BaseReturn("升级建筑成功!");
            }else{
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"账户金币不足以升级，请检查余额！");
            }
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"账户金币不足以升级，请检查余额！");
		}

    }*/
    
    
    /**
     * 根据userId领取建筑出产的金币
     * @param buildingPo
     * @return
     *///获取建筑物金币，增加用户的金币数量的，需要另外计算每个建筑物的金币数量状态，用于被收集
    @ApiOperation(value = "根据userId领取建筑出产的金币",notes = "请求参数说明 [userId 用户ID][buildingType 建筑类型]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[userId 用户ID] [buildingName 建筑名称] [buildingGrade 建筑等级] " +
                    "[buildingStatus 建筑状态 ：1：开启 2：未开启 ] [buildingDetailed 建筑描述][buildingType 建筑类型：1：树屋；"+
                    " 2：金库；3：福利社；4：婚房；5：教堂；6：铁匠铺；7：宠物店；8：酒馆；9：雕像；] " +
                    "[buildingGold 建筑出产金币数量] [buildingUpgrade 建筑升级所需金币数]")
    })
    @PostMapping("/receiveGoldByUserId")
    public BaseReturn receiveGoldByUserId(@RequestBody BuildingPo buildingPo){
        if(StringUtils.isBlank(buildingPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
        if(buildingPo.getBuildingType() == null || buildingPo.getBuildingType() == 0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"建筑类型错误！");
    	String userId =  buildingPo.getUserId();
    	int buildingType =  buildingPo.getBuildingType();
        User userDetail=userService.findByUserId(userId);
        if(userDetail!=null){
//        	BuildingUser buildingUser = new BuildingUser();
//        	int bewteen = 0;
        	try {
        		ReceiveGoldVo ReceiveGoldVo = buildingService.receiveGoldByUserId(userId,buildingType);
				 return new BaseReturn("领取建筑出产的金币成功！",ReceiveGoldVo);
			} catch (Exception e) {
				e.printStackTrace();
				return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"领取建筑出产的金币失败！");
			}
        }else{
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"当前金币不足最低领取数，请稍候再试！");
        }
    }
    
}
