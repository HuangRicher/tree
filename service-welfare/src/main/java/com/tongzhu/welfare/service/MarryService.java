package com.tongzhu.welfare.service;

import com.alibaba.fastjson.JSONObject;
import com.tongzhu.common.Pager;
import com.tongzhu.welfare.model.LoveSetting;
import com.tongzhu.welfare.model.LoveTreeInfo;
import com.tongzhu.welfare.model.LoveTreeSetting;
import com.tongzhu.welfare.model.vo.MarryLogVo;
import com.tongzhu.welfare.model.vo.WeddingLogVo;

public interface MarryService {

	/**
	 * 发起求婚
	 * @param userId        发起人求婚的人
	 * @param otherUserId   被求婚人
	 * @return 				返回请求结果0请求成功，-1不是好友，-2不是异性，-3亲密度不够，-4没有求婚戒指
	 */
	int engagementByUserId(String userId, String otherUserId);

	/**
	 * 处理求婚
	 * @param userId        发起人求婚的人
	 * @param otherUserId   被求婚人
	 * @param type  		求婚处理类型：1：接受求婚；2：拒绝求婚
	 * @return  返回请求结果0求婚成功，-1缺少求婚戒指，1拒绝求婚
	 */
	int receiveEngagementByUserId(String engagementLogId ,String userId, String otherUserId, int type);

	/**
	 * 取消订婚
	 * @param userId        发起人求婚的人
	 * @param userRingId    被摧毁的婚戒ID
	 * @return
	 */
	int cancleEngagementByUserId(String userId, String otherUserId);

	/**
	 * 根据用户ID获取教堂信息
	 * @param userId		用户ID
	 * @param churchType 
	 * @return
	 */
	JSONObject getMarryInfo(String userId, int churchType)throws Exception;

	/**
	 * 获取结婚记录信息婚礼结束后才会显示结婚信息
	 * @param userId        用户ID
	 * @param pageNum		页码
	 * @param pageSize		页面大小
	 * @return
	 */
	Pager<WeddingLogVo> getMarryInfoLog(String userId, int pageNum, int pageSize);

	/**
	 * 举行婚礼
	 * @param userId		用户ID
	 * @param marryType 
	 * @param marryDate 
	 * @return
	 * @throws Exception 
	 */
	JSONObject beginMarry(String userId, int marryType, Long marryDate) throws Exception;
	
	/**
	 * 撒喜糖
	 * @param userId       	用户ID  
	 * @param marryId 		婚礼ID 
	 * @return
	 */
	int andJoyful(String userId, String marryId);

	/**
	 * 根据marryId进入别人的婚礼
	 * @param userId       	用户ID  
	 * @param marryId 		婚礼ID 
	 * @return
	 */
	JSONObject joinWedding(String userId, String marryId);
	
	/**
	 * 离开别人的婚礼
	 * @param userId       	用户ID  
	 * @param marryId 		婚礼ID 
	 * @return
	 */
	void leaveWedding(String userId, String marryId);

	/**
	 * 发送祝福
	 * @param userId       	参加婚礼的用户ID  
	 * @param marryId 		婚礼ID 
	 * @return
	 */
	JSONObject sendWish(String userId, String marryId);

	/**
	 * 敬酒
	 * @param userId       	参加婚礼的用户ID  
	 * @param marryId 		婚礼ID 
	 * @return
	 */
	JSONObject toast(String userId, String marryId);

	/**
	 * 获取爱情树信息
	 * @param userId
	 * @param engagementId
	 * @return
	 * @throws Exception 
	 */
	JSONObject getLoveTreeInfo(String userId) throws Exception;

	/**
	 * 婚房活动：嬉闹
	 * @param userId
	 * @param engagementId
	 * @return
	 */
	JSONObject playJokes(String userId, String engagementId);

	/**
	 * 婚房活动：洞房
	 * @param userId
	 * @param engagementId
	 * @return
	 */
	JSONObject bridegroom(String userId, String engagementId);

	/**
	 * 获取升级戒指界面信息
	 * @param userId
	 * @param engagementId
	 * @return
	 */
	JSONObject updateRing(String userId, String engagementId);

	/**
	 * 升级情缘
	 * @param userId
	 * @param engagementId
	 * @return
	 * @throws Exception 
	 */
	JSONObject updateLove(String userId, String engagementId) throws Exception;

	/**
	 * 离婚
	 * @param userId
	 * @param engagementId
	 * @return
	 */
	int divorce(String userId, String engagementId);

	/**
	 * 巡游
	 * @param userId		用户ID
	 * @param cruiseType	巡游类型		
	 * @return
	 */
	JSONObject cruise(String userId, int cruiseType);

	/**
	 * 给指定用户增加情缘值
	 * @param userId
	 * @param num
	 * @return
	 */
	int addHappinessByUserId(String userId, int num);

	/**
	 * 给指定用户增加幸福值
	 * @param userId
	 * @param num
	 * @return
	 */
	int addLoveByUserId(String userId, int num);

	/**
	 * 钻石购买喜糖
	 * @param userId
	 * @param marryId
	 * @return
	 */
	JSONObject buyAndJoyful(String userId, String marryId);

	/**
	 * 升级爱情树经验
	 * @param userId
	 * @param engagementId
	 * @return
	 * @throws Exception 
	 */
	JSONObject updateLoveTree(String userId, String engagementId) throws Exception;

	/**
	 * 突破爱情树等级
	 * @param userId
	 * @param engagementId
	 * @return
	 * @throws Exception 
	 */
	JSONObject breakLoveTree(String userId, String engagementId) throws Exception;

	/**
	 * 获取情缘信息
	 * @param userId
	 * @return
	 * @throws Exception 
	 */
	JSONObject getLoveInfoByUserId(String userId,String engagementId) throws Exception;

	/**
	 * 获取情缘属性
	 * @param userId
	 * @return
	 */
	LoveSetting getLoveSetting(String userId);

	/**
	 * 获取爱情树属性
	 * @param userId
	 * @return
	 */
	LoveTreeSetting getLoveTreeSetting(String userId);

	/**
	 * 通知预约婚礼的开始
	 * @return
	 */
	int bookWedding();

	/**
	 * 根据ID获取婚礼信息
	 * @param marryId
	 * @return
	 */
	MarryLogVo getWeddingInfo(String marryId);

	/**
	 * 获取用户爱情树配置
	 * @param userId
	 * @return
	 */
	LoveTreeInfo getLoveTreeInfoObject(String loveTreeId,String userId);

	/**
	 * 给指定用户减少幸福值
	 * @param userId
	 * @param num
	 * @param ringId 	当该ID为null时，默认只进行减幸福值操作，不为空时，升级戒指
	 * @return
	 */
	int minusHappinessByUserId(String userId, int num,String ringId);

	/**
	 * 在婚礼进行时离开别人的婚礼
	 * @param userId
	 * @param marryId
	 */
	void leaveMarry(String userId, String marryId);

	/**
	 * 离开婚房
	 * @param userId
	 */
	void leaveWeddingRoom(String userId,String id);

	/**
	 * 重置洞房以及嬉闹次数
	 * @return
	 */
	int resetLoveTreeInfo();

	/**
	 * 自动踢出婚礼现场定时器服务
	 */
	void kickLeaveWeddingRoom();

}
