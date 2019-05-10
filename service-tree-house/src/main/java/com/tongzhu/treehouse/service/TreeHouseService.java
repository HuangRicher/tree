package com.tongzhu.treehouse.service;

import com.tongzhu.except.CheckException;
import com.tongzhu.treehouse.domain.UserGoods;
import com.tongzhu.treehouse.model.TreeHouse;
import com.tongzhu.treehouse.model.TreeHouseExample;
import com.tongzhu.treehouse.service.vo.TreeHouseCultureVO;
import com.tongzhu.treehouse.service.vo.TreeHouseWorkerVO;

import java.util.List;


public interface TreeHouseService {


	void add(TreeHouse treeHouse);

	/**
	 * 树屋升级
	 * @param treeHouseGrade
	 * @param userId
	 */
	void upgrade(int treeHouseGrade, String userId);

	/**
	 * 培养树屋升级 --培养消耗阳光 突破消耗雨露
	 * @param userId  用户Id
	 * @param treeHouseId
	 * @param cultureCount 培养次数
	 */
	List<UserGoods> cultureTreeHouse(String userId, String treeHouseId, int cultureCount);


	TreeHouse getById(String id);

	/**
	 * 获取树屋培养显示详情
	 * @param userId
	 * @return
	 */
	TreeHouseCultureVO getCultureTreeHouseDetail(String userId);


	/**
	 * 获取宅友升级显示详情
	 * @param userId
	 * @return
	 */
	TreeHouseWorkerVO getCultureForMoreWorkerDetail(String userId);


	/**
	 * 培养宅友上限升级
	 * @param userId   用户Id
	 * @param treeHouseId   树屋Id
	 * @param cultureCount   培养次数
	 */
	List<UserGoods> cultureForMoreRoom(String userId, String treeHouseId, int cultureCount)throws CheckException;

	/**
	 * 升级树屋并更新树屋培养值
	 * @param treeHouseId  树屋Id
	 * @param count  增减数量   大于0 为增加  小于0 为减少
	 */
	void updateTressHouseCulture(String treeHouseId, int count,List<UserGoods> userGoods) throws CheckException;


	/**
	 * 更新树屋繁荣度
	 * @param treeHouseId  树屋Id
	 * @param count     增减数量   大于0 为增加  小于0 为减少
	 */
	void updateTressHouseFlourishingRateByTreeHouseId(String treeHouseId, int count) throws CheckException;


	/**
	 * 更新树屋繁荣度
	 * @param userId  用户Id
	 * @param count     增减数量   大于0 为增加  小于0 为减少
	 */
	void updateTressHouseFlourishingRateByUserId(String userId, int count) throws CheckException;


	/**
	 * 查找用户的树屋
	 * @param userId
	 * @return
	 */
	TreeHouse findByUserId(String userId);


	/**
	 * 删除用户树屋编号最大的房间
	 * @param userId
	 */
	void deleteFinalRoom(String userId);


	/**
	 * 升级树屋
	 * @param treeHouseId   树屋ID
	 * @param level  等级
	 */
	void updateTreeHouseLevel(String treeHouseId, int level);

	/**
	 * 多条件更新
	 * @param treeHouse
	 * @param example
	 */
    void updateTreeHouse(TreeHouse treeHouse, TreeHouseExample example);


    /**
	 * 更新树屋繁荣度
	 * @param treeHouseId  树屋Id
	 * @param count     增减数量   大于0 为增加  小于0 为减少
	 */
	//void updateTreeHouseBreakValue(String treeHouseId, int count);


	/**
	 * 进入树屋
	 * @param userId
	 * @param treeHouseUserId
	 */
	void comeInTreeHouse(String userId, String treeHouseUserId) throws CheckException;


	/**
	 * 退出树屋
	 * @param userId
	 * @param treeHouseUserId
	 */
	void outTreeHouse(String userId,String treeHouseUserId) throws CheckException;


	/**
	 * 更新树屋人气值
	 * @param treeHouseUserId
	 * @param count
	 */
	void addAmbienceCount(String treeHouseUserId, Integer count);

	/**
	 * 更新树屋娱乐值
	 * @param treeHouseUserId
	 * @param count
	 */
	int updateAmusementCount(String treeHouseUserId,Integer count);

	/**
	 * 更新树屋环境值
	 * @param treeHouseUserId
	 * @param count
	 */
	int updateEnvironmentCount(String treeHouseUserId,Integer count);

	void updateTreeHouse(String treeHouseId, int amuseCount);

    void cancelAllNoSpeak(String userId);

	void allNoSpeak(String userId);

	/**
	 * 减少树屋人气，环境，娱乐值
	 * @param treeHouse
	 * @return
	 */
    TreeHouse subByUserId(TreeHouse treeHouse);
}
