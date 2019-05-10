package com.tongzhu.treehouse.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.treehouse.constant.TreeHouseConstant;
import com.tongzhu.treehouse.domain.UserGoods;
import com.tongzhu.treehouse.po.TreeHousePO;
import com.tongzhu.treehouse.service.TreeHouseService;
import com.tongzhu.treehouse.service.vo.TreeHouseCultureVO;
import com.tongzhu.treehouse.service.vo.TreeHouseWorkerVO;
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

@RestController
@RequestMapping(value = "/treeHouse")
@Api(value="树屋controller",tags={"树屋相关API"})
public class TreeHouseController {

	@Autowired
	private TreeHouseService treeHouseService;




	/**
	 * 培养树屋升级  --消耗阳光 金币
	 * @param treeHousePO
	 * 			-- userId  用户ID
	 * 			-- treeHouseId  树屋ID
	 * 			-- cultureCount  培养次数
	 * @return
	 */
	@ApiOperation(value = "培养树屋升级",notes = "请求参数说明 [userId 用户ID]   [treeHouseId 树屋ID]  [cultureCount 培养次数]")
	@ApiResponses({
			@ApiResponse(code=200,message="返回参数说明 [level 树屋级别] [type  1:培养  2：突破] [nextValue 达到下个等级的经验值]" +
					" [currentValue 当前经验值] [treeHouseId  树屋ID] [goods:id 物品id,goods:amount 物品数量]")
	})
	@PostMapping("/cultureTreeHouse")
	public BaseReturn cultureTreeHouse(@RequestBody TreeHousePO treeHousePO){
	    if(StringUtils.isBlank(treeHousePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
        if(StringUtils.isBlank(treeHousePO.getTreeHouseId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"树屋ID为空！");
        if(treeHousePO.getCultureCount()==0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"培养次数为0！");

        Map<String,Object> dataMap=new HashMap<>();
		if(TreeHouseConstant.CULTURE_TYPE_ONE==treeHousePO.getCultureCount() || TreeHouseConstant.CULTURE_TYPE_TEN==treeHousePO.getCultureCount()){
			try {
				List<UserGoods> userGoods=treeHouseService.cultureTreeHouse(treeHousePO.getUserId(),treeHousePO.getTreeHouseId(), treeHousePO.getCultureCount());
				if(userGoods!=null){
                    dataMap.put("goods",userGoods);
                }
			}catch (HystrixRuntimeException e){
				JSONObject object=JSON.parseObject(e.getCause().getMessage().substring(e.getCause().getMessage().indexOf("content")+9));
				return new BaseReturn(BaseReturnCode.PROCESS_ERROR,object.getString("message"));
			}

		}else{
			return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"培养次数不对！");
		}
		TreeHouseCultureVO cultureVO=treeHouseService.getCultureTreeHouseDetail(treeHousePO.getUserId());
		dataMap.put("cultureDetail",cultureVO);
		return new BaseReturn("培养成功！",dataMap);
	}


	/**
	 * 培养好友上限升级   --消耗木材 金币
	 * @param   treeHousePO
	 *          --userId    用户ID
	 *          --treeHouseId  树屋ID
	 *          --cultureCount  培养次数
	 * @return
	 */
	@ApiOperation(value = "培养好友上限升级",notes = "培养好友上限升级请求参数[userId 用户ID] [treeHouseId 树屋ID][cultureCount 培养次数]")
	@ApiResponses({
			@ApiResponse(code=200,message="返回参数说明[workerCount 宅友上限] [flourishingRate 繁荣度]")
	})
	@PostMapping("/cultureForMoreWorker")
	public BaseReturn cultureForMoreWorker(@RequestBody TreeHousePO treeHousePO){
        if(StringUtils.isBlank(treeHousePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
        if(StringUtils.isBlank(treeHousePO.getTreeHouseId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"树屋ID为空！");
        if(treeHousePO.getCultureCount()==0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"培养次数为0！");

        Map<String,Object> dataMap=new HashMap<>();
		if(TreeHouseConstant.CULTURE_TYPE_ONE==treeHousePO.getCultureCount() || TreeHouseConstant.CULTURE_TYPE_TEN==treeHousePO.getCultureCount()){
			try {
                List<UserGoods> userGoods=treeHouseService.cultureForMoreRoom(treeHousePO.getUserId(),treeHousePO.getTreeHouseId(),treeHousePO.getCultureCount());
                if(userGoods!=null){
                    dataMap.put("goods",userGoods);
                }
			}catch (HystrixRuntimeException e){
				JSONObject object=JSON.parseObject(e.getCause().getMessage().substring(e.getCause().getMessage().indexOf("content")+9));
				return new BaseReturn(BaseReturnCode.PROCESS_ERROR,object.getString("message"));
			}
		}else{
			return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"培养次数不对！");
		}
		TreeHouseWorkerVO workerVOS=treeHouseService.getCultureForMoreWorkerDetail(treeHousePO.getUserId());
        dataMap.put("cultureDetail",workerVOS);
		return new BaseReturn("培养好友上限升级成功！",dataMap);
	}


	/**
	 * 树屋培养显示详情
	 * @param treeHousePO
	 *        	--userId    用户ID
	 * @return
	 */
	@ApiOperation(value = "树屋培养显示详情",notes = "树屋培养显示详情 参数 [userId 用户ID]")
	@ApiResponses({
			@ApiResponse(code=200,message="返回参数说明 [level 树屋级别] [type  1:培养  2：突破] " +
					"[nextValue 达到下个等级的经验值] [currentValue 当前经验值] [treeHouseId  树屋ID]" +
                    "[upgradeConsumeGoods 消耗物品] [id 物品ID] [amount 物品数量] [upgradeAward 升级奖励]")
	})
	@PostMapping("/cultureTreeHouseDetail")
	public BaseReturn cultureTreeHouseDetail(@RequestBody TreeHousePO treeHousePO){
        if(StringUtils.isBlank(treeHousePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");

		TreeHouseCultureVO cultureVO=treeHouseService.getCultureTreeHouseDetail(treeHousePO.getUserId());
		return new BaseReturn("操作成功！",cultureVO);
	}


	/**
	 * 宅友上限显示详情
	 * @param treeHousePO
	 *        	--treeHouseId  树屋ID
	 *        	--userId    用户ID
	 * @return
	 */
	@ApiOperation(value = "宅友上限显示详情",notes = "宅友上限显示详情请求参数：[userId 用户ID]")
	@PostMapping("/cultureForMoreWorkerDetail")
	@ApiResponses({
			@ApiResponse(code=200,message="返回参数说明 [workerCount 宅友上限] [nextValue 达到下个等级的繁荣度] [currentValue 当前繁荣度]" +
                    "[consumeGoods 消耗物品] [consumeGoods:id 物品Id] [consumeGoods:amount 物品数量]")
	})
	public BaseReturn cultureForMoreWorkerDetail(@RequestBody TreeHousePO treeHousePO){
        if(StringUtils.isBlank(treeHousePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");

		TreeHouseWorkerVO workerVOS=treeHouseService.getCultureForMoreWorkerDetail(treeHousePO.getUserId());
		return new BaseReturn("操作成功！",workerVOS);
	}

}
