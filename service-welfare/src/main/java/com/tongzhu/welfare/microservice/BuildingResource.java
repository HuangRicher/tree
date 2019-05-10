package com.tongzhu.welfare.microservice;

import java.util.List;

import com.tongzhu.welfare.vo.BuildingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tongzhu.welfare.MQ.MsgProducer;
import com.tongzhu.welfare.service.BuildingService;
import com.tongzhu.welfare.vo.BuildingGoldVo;

@RestController
@RequestMapping("/buildingResource")
public class BuildingResource {
	
	@Autowired
	BuildingService buildingService;
	
    @Autowired
    private MsgProducer helloSender;
	
    /**
     * 初始化用户建筑信息
     * @param userId
     */
    @PostMapping("/initBuildingUser/{userId}")
    public void initBuildingUser(@PathVariable("userId") String userId){
    	buildingService.initBuildingUser(userId);
    }
    
	/**
	 * 根据用户ID获取建筑信息列表
	 * @param userId  用户ID
	 * @return  List<BuildingGoldVo> 
	 */
    @PostMapping("/getGoldStatusByUserId/{userId}")
    public List<BuildingGoldVo> getGoldStatusByUserId(@PathVariable("userId") String userId){
        return buildingService.getGoldStatusByUserId(userId);
    }

	/**
	 * 根据用户ID获取建筑信息列表
	 * @param userId  用户ID
	 * @return  List<BuildingVo>
	 */
	@PostMapping("/getBuildingByUserId/{userId}")
	public List<BuildingVo> getBuildingByUserId(@PathVariable("userId") String userId){
		return buildingService.getbuildingByUserId(userId);
	}

	/**
	 * 根据用户ID获取建筑信息列表
	 * @param userId  用户ID (String userId)
	 * @return  List<BuildingGoldVo> 
	 */
    @PostMapping("/updateBuildingUser/{userId}")
    public void updateBuildingUser(@PathVariable("userId") String userId){
         buildingService.updateBuildingUser(userId);
    }
    
	/**
	 * 获取树屋升级所需人气，娱乐，环境值
	 * @param treeGrade  树屋当前等级
	 * @return  BuildingVo 
	 */
    @PostMapping("/getTreeHouseByUserId/{treeGrade}")
    public BuildingVo getTreeHouseByUserId(@PathVariable("treeGrade") int treeGrade){
       return  buildingService.getTreeHouseByUserId(treeGrade);
    }
    
	/**
	 * 通知发生变动
	 * @param content   为json的string,目前需要传userId
	 * @return  BuildingVo 
	 */
    @PostMapping("/sendMsg/{content}")
    public void sendMsg(@PathVariable("content") String content){
         helloSender.sendMsg(content);
    }
    
}
