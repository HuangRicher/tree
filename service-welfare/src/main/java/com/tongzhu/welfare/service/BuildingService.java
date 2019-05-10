package com.tongzhu.welfare.service;

import java.io.IOException;
import java.util.List;

import com.tongzhu.welfare.vo.BuildingGoldVo;
import com.tongzhu.welfare.vo.BuildingVo;
import com.tongzhu.welfare.vo.ReceiveGoldVo;
import org.springframework.web.multipart.MultipartFile;

public interface BuildingService {

	/**
	 * 根据用户ID获取建筑信息列表
	 * @param userId  用户ID
	 * @return  List<BuildingVo> 
	 */
	List<BuildingVo> getbuildingByUserId(String userId);
	
	/**
	 * 根据用户ID获取建筑金币信息列表
	 * @param userId  用户ID
	 * @return  List<BuildingVo> 
	 */
	List<BuildingGoldVo> getGoldStatusByUserId(String userId);
	
	/**
	 * 初始化用户建筑信息
	 * @param userId  用户ID
	 */
	void initBuildingUser(String userId);

	/**
	 * 对用户指定的建筑类型进行升级,返回升级所需时间
	 * @param userId  用户ID
	 * @param buildingType  建筑类型
	 * @return  升级后的用户信息  BuildingUser
	 * @throws Exception 
	 */
	Integer upgradeBuildingTime(String userId, int buildingType,int userGrade) throws Exception;

	/**
	 * 对用户指定的建筑领取金币
	 * @param userId
	 * @param buildingType
	 * @return
	 * @throws Exception 
	 */
	ReceiveGoldVo receiveGoldByUserId(String userId, int buildingType) throws Exception;
	
	/**
	 * 对用户指定的建筑类型进行数据上的升级
	 * @param userId  用户ID
	 * @param buildingType  建筑类型
	 * @return  升级后的用户信息  BuildingUser
	 * @throws Exception 
	 */
	Integer upgradeBuildingByUserId(String userId, int buildingType)throws Exception;
	
	/**
	 * 定时处理升级建筑的数据修改
	 */
	Integer upgradeBuildingTask()throws NumberFormatException, Exception;

	/**
	 * 等级达到指定时开启建筑
	 * @param userId
	 */
	void updateBuildingUser(String userId);

	/**
	 * 建筑信息缓存
	 */
	void cacheBuildingSettingInfo();
	
	/**
	 * 增加建筑生产金币损失记录
	 */
	void addBuildingReduceLog(String userId);

	/**
	 * 开启婚房
	 * @param userId
	 */
	void openMarriageByUserId(String userId,Integer buildingOpen);

	/**
	 * 判断建筑的状态
	 * @param userId
	 * @param buildingType
	 * @return
	 */
	int jugeStatusByUserId(String userId, int buildingType);

	/**
	 * 获取树屋下一等级所需要的条件
	 * @param treeGrade
	 * @return
	 */
	BuildingVo getTreeHouseByUserId(int treeGrade);

	/**
	 * 判断建筑是否有可升级的
	 * @param userId
	 */
	void judgeBuildingByUserId(String userId);

	/**
	 * 导入金库配置表
	 * @param fileName
	 * @param file
	 * @return
	 */
	boolean addBuildingCoffersSetting(String fileName, MultipartFile file) throws IOException;

	/**
	 * 导入福利社表
	 * @param fileName
	 * @param file
	 * @return
	 */
	boolean addBuildingWelfareSetting(String fileName, MultipartFile file) throws IOException;

	/**
	 * 导入教堂表
	 * @param fileName
	 * @param file
	 * @return
	 */
	boolean addBuildingChurchSetting(String fileName, MultipartFile file) throws IOException;
	/**
	 * 导入铁匠铺表
	 * @param fileName
	 * @param file
	 * @return
	 */
	boolean addBuildingSmithySetting(String fileName, MultipartFile file) throws IOException;

	/**
	 * 导入酒馆表
	 * @param fileName
	 * @param file
	 * @return
	 */
	boolean addBuildingWineshopSetting(String fileName, MultipartFile file) throws IOException;

	/**
	 * 导入雕像表
	 * @param fileName
	 * @param file
	 * @return
	 */
	boolean addBuildingStatueSetting(String fileName, MultipartFile file) throws IOException;

	/**
	 * 导入宠物店表
	 * @param fileName
	 * @param file
	 * @return
	 */
	boolean addBuildingPetshopSetting(String fileName, MultipartFile file) throws IOException;
}
