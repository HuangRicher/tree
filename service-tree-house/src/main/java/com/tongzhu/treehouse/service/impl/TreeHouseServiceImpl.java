package com.tongzhu.treehouse.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.tongzhu.constant.RedisKey;
import com.tongzhu.treehouse.constant.*;
import com.tongzhu.treehouse.domain.UserGoods;
import com.tongzhu.except.CheckException;
import com.tongzhu.treehouse.mapper.TreeHouseMapper;
import com.tongzhu.treehouse.mapper.ext.TreeHouseExtMapper;
import com.tongzhu.treehouse.model.*;
import com.tongzhu.treehouse.redis.RedisService;
import com.tongzhu.treehouse.service.*;
import com.tongzhu.treehouse.service.vo.TreeHouseCultureVO;
import com.tongzhu.treehouse.service.vo.TreeHouseWorkerVO;
import com.tongzhu.util.DateComputeUtil;
import com.tongzhu.util.RandomUtil;
import com.tongzhu.util.UUIDUtil;
import feign.FeignException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TreeHouseServiceImpl implements TreeHouseService{

	@Autowired
	private TreeHouseMapper treeHouseMapper;

	@Autowired
	private TreeHouseExtMapper treeHouseExtMapper;

	@Autowired
	private WorkTypeService workTypeService;

	@Autowired
	private UserWorkTypeService userWorkTypeService;

	@Autowired
	private TreeHouseLevelSettingService treeHouseLevelSettingService;

	@Autowired
	private TreeHouseRoomSettingService treeHouseRoomSettingService;

	@Autowired
	private UserGoodsService userGoodsService;

	@Autowired
	private TreeHouseRoomService treeHouseRoomService;

	@Autowired
	private UserWorkPositionService userWorkPositionService;

	@Autowired
	private RedisService redisService;

	@Autowired
	private TreeHouseVisitorService treeHouseVisitorService;

	@Autowired
	private TreeHouseFlowerpotService treeHouseFlowerpotService;

	@Autowired
	private ChatMessageService chatMessageService;







	@Transactional
	@Override
	public void add(TreeHouse treeHouse) {
		TreeHouseRoomSetting setting=treeHouseRoomSettingService.getByFlourishingRate(treeHouse.getFlourishingRate());
		if(setting.getWorkerCount()!=null && setting.getWorkerCount()>0){
			for(int i=1;i<=setting.getWorkerCount();i++){
				TreeHouseRoom treeHouseRoom=new TreeHouseRoom();
				treeHouseRoom.setId(UUIDUtil.generateUUID());
				treeHouseRoom.setStatus(StatusConstant.USER_WORKER_IDLE);
				treeHouseRoom.setUserId(treeHouse.getUserId());
				treeHouseRoom.setTreeHouseId(treeHouse.getId());
				treeHouseRoom.setRoomId(i);
				treeHouseRoomService.add(treeHouseRoom);
			}
		}
		treeHouseMapper.insertSelective(treeHouse);

		List<TreeHouseVisitor> visitorList=new ArrayList<>();
		for(int i=0;i<TreeHouseVisitorConstant.VISITOR_COUNT_DEFAULT;i++){
			TreeHouseVisitor visitor=new TreeHouseVisitor();
			visitor.setStatus(TreeHouseVisitorConstant.STATUS_ABLE);
			visitor.setId(UUIDUtil.generateUUID());
			visitor.setTreeHouseId(treeHouse.getUserId());
			visitor.setCanSpeak(true);
			visitorList.add(visitor);
		}
		treeHouseVisitorService.addBatch(visitorList);

		List<TreeHouseFlowerpot> flowerpotList=new ArrayList<>();
		for(int i=0;i<TreeHouseFlowerpotConstant.FLOWERPOT_COUNT_DEFAULT;i++){
			TreeHouseFlowerpot flowerpot=new TreeHouseFlowerpot();
			flowerpot.setId(UUIDUtil.generateUUID());
			flowerpot.setTreeHouseId(treeHouse.getUserId());
			flowerpot.setLockStatus(TreeHouseFlowerpotConstant.STATUS_UNLOCK);
			flowerpot.setLockLevel(0);
			switch (i){
				case 2:
					flowerpot.setLockStatus(TreeHouseFlowerpotConstant.STATUS_LOCK);
					flowerpot.setLockLevel(15);
					break;
				case 3:
					flowerpot.setLockStatus(TreeHouseFlowerpotConstant.STATUS_LOCK);
					flowerpot.setLockLevel(25);
					break;
				case 4:
					flowerpot.setLockStatus(TreeHouseFlowerpotConstant.STATUS_LOCK);
					flowerpot.setLockLevel(35);
					break;
				case 5:
					flowerpot.setLockStatus(TreeHouseFlowerpotConstant.STATUS_LOCK);
					flowerpot.setLockLevel(45);
					break;
				case 6:
					flowerpot.setLockStatus(TreeHouseFlowerpotConstant.STATUS_LOCK);
					flowerpot.setLockLevel(55);
					break;
				case 7:
					flowerpot.setLockStatus(TreeHouseFlowerpotConstant.STATUS_LOCK);
					flowerpot.setLockLevel(70);
					break;
			}
			flowerpotList.add(flowerpot);
		}
		treeHouseFlowerpotService.addBatch(flowerpotList);
	}

	/**
	 * 树屋升级
	 * @param treeHouseGrade
	 * @param userId
	 */
	@Override
	public void upgrade(int treeHouseGrade,String userId) {

		userWorkTypeService.upgradeWorkType(treeHouseGrade,userId);

	}

	/**
	 * 培养树屋升级 --培养消耗阳光 突破消耗雨露
	 * @param treeHouseId 树屋Id
	 * @param cultureCount 培养次数（一次一阳光）
	 */
	@Transactional
	@Override
	public List<UserGoods> cultureTreeHouse(String userId,String treeHouseId, int cultureCount) throws FeignException {
		TreeHouse treeHouse=this.getById(treeHouseId);
		TreeHouseLevelSetting setting=treeHouseLevelSettingService.findByTreeLevel(treeHouse.getLevel());
		if(setting!=null && setting.getCultureValue()!=null && setting.getCultureValue()>0){
				JSONArray array=JSONArray.parseArray(setting.getUpgradeConsumeGoods());
				List<UserGoods> goodsIdList=new ArrayList<>();
				for(int i=0;i<array.size();i++){
					UserGoods goods=new UserGoods();
					goods.setGoodsId(array.getJSONObject(i).getString("goodsId"));
					int amount=array.getJSONObject(i).getIntValue("amount");
					goods.setAmount(amount*cultureCount);
					goodsIdList.add(goods);
				}
				//减少用户背包物品消耗数量
				List<UserGoods> userGoodsList=userGoodsService.subUserGoods(userId, goodsIdList);
				//增加树屋培养值
				if(userGoodsList!=null){
					int sunshineCount= RandomUtil.RandomMultIntervalSumValue(setting.getExperienceMin(),setting.getExperienceMax(),cultureCount);
					List<UserGoods> userGoodsAward=new ArrayList<>();
					this.updateTressHouseCulture(treeHouseId,sunshineCount,userGoodsAward);
					userGoodsList.addAll(userGoodsAward);
					return userGoodsList;
				}
		}
		return null;
	}

	@Override
	public TreeHouse getById(String id) {
		return treeHouseMapper.selectByPrimaryKey(id);
	}


	/**
	 * 查询树屋培养显示详情
	 * @param userId 用户ID
	 * @return
	 */
	@Override
	public TreeHouseCultureVO getCultureTreeHouseDetail(String userId) {
		TreeHouse treeHouse=this.findByUserId(userId);
		if(treeHouse!=null){
			TreeHouseLevelSetting setting=treeHouseLevelSettingService.findByTreeLevel(treeHouse.getLevel());
			TreeHouseCultureVO houseCultureVO=new TreeHouseCultureVO();
			houseCultureVO.setLevel(treeHouse.getLevel());
			houseCultureVO.setTreeHouseId(treeHouse.getId());
			if(treeHouse.getLevel()<TreeHouseConstant.TREE_HOUSE_MAC_LEVEL){
				TreeHouseLevelSetting nextSetting=treeHouseLevelSettingService.findByTreeLevel(treeHouse.getLevel());
				houseCultureVO.setNextValue(nextSetting.getCultureValue());
			}
			houseCultureVO.setCurrentValue(treeHouse.getCultureValue());
			if(StringUtils.isNotBlank(setting.getUpgradeConsumeGoods())){
				ArrayList<UserGoods> userGoods = JSON.parseObject(setting.getUpgradeConsumeGoods(), new TypeReference<ArrayList<UserGoods>>() {});
				houseCultureVO.setUpgradeConsumeGoods(userGoods);
			}
			if(StringUtils.isNotBlank(setting.getUpgradeAward()) && treeHouse.getCultureValue()<setting.getCultureValue()){
				ArrayList<UserGoods> userGoods1 = JSON.parseObject(setting.getUpgradeAward(), new TypeReference<ArrayList<UserGoods>>() {});
				houseCultureVO.setUpgradeAward(userGoods1);
			}
			return houseCultureVO;
		}
		return null;
	}


	/**
	 * 宅友上限升级显示详情
	 * @param userId
	 * @return
	 */
	@Override
	public TreeHouseWorkerVO getCultureForMoreWorkerDetail(String userId) {
		TreeHouse treeHouse=this.findByUserId(userId);
		if(treeHouse!=null){
			TreeHouseWorkerVO workerVO=new TreeHouseWorkerVO();
			TreeHouseRoomSetting setting=treeHouseRoomSettingService.getByFlourishingRate(treeHouse.getFlourishingRate());
			TreeHouseRoomSetting nextSetting=treeHouseRoomSettingService.findByWorkerCount(setting.getWorkerCount()+1);
			workerVO.setWorkerCount(setting.getWorkerCount());
			workerVO.setCurrentValue(treeHouse.getFlourishingRate());
			workerVO.setNextValue(nextSetting.getFlourishingRate());
			ArrayList<UserGoods> students = JSON.parseObject(setting.getConsumeGoods(), new TypeReference<ArrayList<UserGoods>>() {});
			workerVO.setConsumeGoods(students);

			return workerVO;
		}
		return null;
	}


	/**
	 * 培养宅友上限升级    ----消耗木材
	 * @param userId   用户Id
	 * @param treeHouseId   树屋Id
	 * @param cultureCount   培养次数
	 */
	@Transactional
	@Override
	public List<UserGoods> cultureForMoreRoom(String userId, String treeHouseId, int cultureCount) throws RuntimeException{
		TreeHouse treeHouse=this.findByUserId(userId);
		if(treeHouse!=null){
			TreeHouseRoomSetting setting=treeHouseRoomSettingService.getByFlourishingRate(treeHouse.getFlourishingRate());
			if(setting!=null && StringUtils.isNotBlank(setting.getConsumeGoods())){
				JSONArray array=JSONArray.parseArray(setting.getConsumeGoods());
				List<UserGoods> goodsList=new ArrayList<>();
				for(int i=0;i<array.size();i++){
					JSONObject object=array.getJSONObject(i);
					UserGoods map=new UserGoods();
					map.setGoodsId(object.getString("goodsId"));
					map.setAmount(object.getIntValue("amount")*cultureCount);
					goodsList.add(map);
				}
				//减少账户背包物品消耗数量
				List<UserGoods> userGoods=userGoodsService.subUserGoods(userId,goodsList);
				if(userGoods!=null){
					//增加树屋繁荣度
					int flourishingRateCount= RandomUtil.RandomMultIntervalSumValue(setting.getExperienceMin(),setting.getExperienceMax(),cultureCount);
					this.updateTressHouseFlourishingRateByTreeHouseId(treeHouseId,flourishingRateCount);
					return userGoods;
				}
			}
		}
		return null;
	}


	/**
	 * 升级树屋并更新树屋培养值
	 * @param treeHouseId  树屋Id
	 * @param count  增减数量   大于0 为增加  小于0 为减少
	 */
	@Override
	public synchronized void updateTressHouseCulture(String treeHouseId, int count,List<UserGoods> userGoods)throws CheckException {
		if(count>0){
			//升级树屋等级
			int remainder=this.addLevelByCulture(treeHouseId,count,userGoods);
			TreeHouse treeHouse=new TreeHouse();
			treeHouse.setId(treeHouseId);
			treeHouse.setCultureValue(remainder);
			treeHouseMapper.updateByPrimaryKeySelective(treeHouse);
			//treeHouseExtMapper.updateTressHouseCulture(treeHouseId,remainder);
		}else{
			TreeHouse treeHouse=treeHouseMapper.selectByPrimaryKey(treeHouseId);
			if(treeHouse.getCultureValue()+count<0){
				throw new CheckException("培养值不足！");
			}else{
				treeHouseExtMapper.updateTressHouseCulture(treeHouseId,count);
			}
		}

	}


	/**
	 * 更新树屋繁荣度
	 * @param treeHouseId  树屋Id
	 * @param count     增减数量   大于0 为增加  小于0 为减少
	 */
	@Transactional
	@Override
	public synchronized void updateTressHouseFlourishingRateByTreeHouseId(String treeHouseId, int count) throws CheckException{
		TreeHouse treeHouse=treeHouseMapper.selectByPrimaryKey(treeHouseId);
		if(count>0){
			treeHouseExtMapper.updateTressHouseFlourishingRateByTreeHouseId(treeHouseId,count);
			TreeHouseRoomSetting setting=treeHouseRoomSettingService.getByFlourishingRate(treeHouse.getFlourishingRate()+count);
			List<TreeHouseRoom> rooms=treeHouseRoomService.findRoomsByTreeHouseId(treeHouse.getId());
			if(setting!=null && rooms!=null && rooms.size()>0 &&
					rooms.size()<TreeHouseConstant.TREE_HOUSE_ROOM_MAX_COUNT && setting.getWorkerCount()>rooms.size()){
				TreeHouseRoom treeHouseRoom=new TreeHouseRoom();
				treeHouseRoom.setId(UUIDUtil.generateUUID());
				treeHouseRoom.setStatus(StatusConstant.USER_WORKER_IDLE);
				treeHouseRoom.setUserId(treeHouse.getUserId());
				treeHouseRoom.setTreeHouseId(treeHouse.getId());
				treeHouseRoom.setRoomId(rooms.get(0).getRoomId()+1);
				treeHouseRoomService.add(treeHouseRoom);
			}
		}else{
			if(treeHouse.getFlourishingRate()+count<0){
				throw new CheckException("繁荣度不足！");
			}else {
				treeHouseExtMapper.updateTressHouseFlourishingRateByTreeHouseId(treeHouseId,count);
			}
		}

	}


	/**
	 * 更新树屋繁荣度
	 * @param userId  用户Id
	 * @param count     增减数量   大于0 为增加  小于0 为减少
	 */
	@Override
	public synchronized void updateTressHouseFlourishingRateByUserId(String userId, int count) throws CheckException {
		if(count>0){
			treeHouseExtMapper.updateTressHouseFlourishingRateByUserId(userId,count);
		}else{
			TreeHouseExample example=new TreeHouseExample();
			example.createCriteria().andUserIdEqualTo(userId);
			List<TreeHouse> treeHouses=treeHouseMapper.selectByExample(example);

			if(treeHouses!=null && treeHouses.size()>0 && treeHouses.get(0).getFlourishingRate()+count<0){
				throw new CheckException("繁荣度不足！");
			}else {
				treeHouseExtMapper.updateTressHouseFlourishingRateByUserId(userId,count);
			}
		}
	}

	@Override
	public TreeHouse findByUserId(String userId) {
		TreeHouseExample example=new TreeHouseExample();
		example.createCriteria().andUserIdEqualTo(userId);
		List<TreeHouse> houses=treeHouseMapper.selectByExample(example);
		if(houses!=null && houses.size()>0){
			return houses.get(0);
		}
		else return null;
	}

	/**
	 * 删除用户树屋编号最大的房间
	 * @param userId
	 */
	@Override
	public void deleteFinalRoom(String userId) {
		treeHouseExtMapper.deleteMaxRoomIdByUserId(userId);
	}

	@Override
	public void updateTreeHouseLevel(String treeHouseId, int level) {
		TreeHouse treeHouse=new TreeHouse();
		treeHouse.setId(treeHouseId);
		treeHouse.setLevel(level);
		treeHouseMapper.updateByPrimaryKeySelective(treeHouse);
		//树屋升到相应等级是解锁盆栽
		treeHouseFlowerpotService.unlockByTreeHouseLevel(treeHouseId,level);
	}

	@Override
	public void updateTreeHouse(TreeHouse treeHouse, TreeHouseExample example) {
		treeHouseMapper.updateByExampleSelective(treeHouse,example);
	}

	@Transactional
	@Override
	public void comeInTreeHouse(String userId, String treeHouseUserId) throws CheckException {
		int locationCount=treeHouseVisitorService.getCanUseVisitorLocation(treeHouseUserId);
		if(redisService.get(RedisKey.IN_TREE_HOUSE+treeHouseUserId)==null){
			if(!userId.equals(treeHouseUserId) && locationCount<=1){
				throw new CheckException("对方树屋人满为患，无法进入");
			}
			Map<String,Integer> users=new HashMap<>();
			users.put(userId,1);
			redisService.set(RedisKey.IN_TREE_HOUSE+treeHouseUserId,users);
			redisService.set(RedisKey.CHAT_TREE_HOUSE_USER+userId,treeHouseUserId);
			List<TreeHouseVisitor> visitorList=treeHouseVisitorService.findByTreeHouseId(treeHouseUserId);
			if(visitorList!=null && !visitorList.isEmpty()) {
				TreeHouseVisitor visitor = visitorList.get(0);
				visitor.setVisitorId(userId);
				treeHouseVisitorService.updateById(visitor);
			}
		}else{
			Map<String,Integer> users=(Map<String,Integer>)redisService.get(RedisKey.IN_TREE_HOUSE+treeHouseUserId);
			if(!userId.equals(treeHouseUserId) ) {
				if(users.containsKey(treeHouseUserId)&& locationCount<1+users.entrySet().size()){
					throw new CheckException("对方树屋人满为患，无法进入");
				}
				if(!users.containsKey(treeHouseUserId)&& locationCount<2+users.entrySet().size()){
					throw new CheckException("对方树屋人满为患，无法进入");
				}
			}
			if (users.containsKey(treeHouseUserId)) {
				if(users.size()>=TreeHouseVisitorConstant.VISITOR_COUNT_DEFAULT &&
						locationCount>=TreeHouseVisitorConstant.VISITOR_COUNT_DEFAULT &&
						!userId.equals(treeHouseUserId))
					throw new CheckException("对方树屋人满为患，无法进入");
			}else{
				if(users.size()>=TreeHouseVisitorConstant.VISITOR_COUNT_DEFAULT-1 &&
						locationCount>=TreeHouseVisitorConstant.VISITOR_COUNT_DEFAULT-1  &&
						!userId.equals(treeHouseUserId))
					throw new CheckException("对方树屋人满为患，无法进入");
			}
			users.put(userId,1);
			redisService.set(RedisKey.IN_TREE_HOUSE+treeHouseUserId,users);
			redisService.set(RedisKey.CHAT_TREE_HOUSE_USER+userId,treeHouseUserId);
			List<TreeHouseVisitor> visitorList=treeHouseVisitorService.findByTreeHouseId(treeHouseUserId);
			if(visitorList!=null && !visitorList.isEmpty()){
				TreeHouseVisitor visitor=visitorList.get(0);
				visitor.setVisitorId(userId);
				treeHouseVisitorService.updateById(visitor);
			}
		}
		synchronized (treeHouseUserId.intern()){
			Map<String,String> comeInTreeHouseCount=(Map<String,String>)redisService.get(RedisKey.DAY_INCOME_TREE_HOUSE_COUNT+treeHouseUserId);
			if(comeInTreeHouseCount==null){
				comeInTreeHouseCount=new HashMap<>();
				comeInTreeHouseCount.put(userId,"1");
				redisService.set(RedisKey.DAY_INCOME_TREE_HOUSE_COUNT+treeHouseUserId,comeInTreeHouseCount, DateComputeUtil.getSecondsNextEarlyMorning());
				treeHouseExtMapper.updateTreeHouseAmbienceCount(treeHouseUserId,320);
			}
			if(comeInTreeHouseCount!=null && !comeInTreeHouseCount.containsKey(userId) &&
					!userId.equals(treeHouseUserId) &&comeInTreeHouseCount.entrySet().size()<TreeHouseConstant.DAY_COME_IN_TREE_HOUSE_MAX) {
				comeInTreeHouseCount.put(userId,"1");
				redisService.set(RedisKey.DAY_INCOME_TREE_HOUSE_COUNT+treeHouseUserId,comeInTreeHouseCount, DateComputeUtil.getSecondsNextEarlyMorning());
				treeHouseExtMapper.updateTreeHouseAmbienceCount(treeHouseUserId,320);
			}
		}
	}

	@Override
	public void outTreeHouse(String userId, String treeHouseUserId) throws CheckException{
		if(redisService.get(RedisKey.IN_TREE_HOUSE+treeHouseUserId)!=null){
			Map<String,Long> users=(Map<String,Long>)redisService.get(RedisKey.IN_TREE_HOUSE+treeHouseUserId);
			Iterator<Map.Entry<String,Long>> iterator=users.entrySet().iterator();
			while (iterator.hasNext()){
				String key=iterator.next().getKey();
				if(key.equals(userId))
					iterator.remove();
			}
			redisService.set(RedisKey.IN_TREE_HOUSE+treeHouseUserId,users);
			List<TreeHouseVisitor> visitorList=treeHouseVisitorService.findByVisitorId(userId);
			if(!visitorList.isEmpty()){
				TreeHouseVisitor visitor=visitorList.get(0);
				visitor.setVisitorId(null);
				treeHouseVisitorService.updateByIdWithNull(visitor);
			}
		}
	}

	@Override
	public void addAmbienceCount(String treeHouseUserId, Integer count) {
		treeHouseExtMapper.updateTreeHouseAmbienceCount(treeHouseUserId,count);
	}

	@Override
	public synchronized int updateAmusementCount(String treeHouseUserId, Integer count) {
		int amount=0;
		TreeHouse treeHouse=this.findByUserId(treeHouseUserId);
		if(treeHouse!=null && count>0){
			amount=treeHouse.getAmusementCount()+count;
			treeHouse.setAmusementCount(amount);
			treeHouseMapper.updateByPrimaryKey(treeHouse);
		}
		return amount;
	}

	@Override
	public synchronized int updateEnvironmentCount(String treeHouseUserId, Integer count) {
		int amount=0;
		TreeHouse treeHouse=this.findByUserId(treeHouseUserId);
		if(treeHouse!=null && count>0){
			amount=treeHouse.getEnvironmentCount()+count;
			treeHouse.setEnvironmentCount(amount);
			treeHouseMapper.updateByPrimaryKey(treeHouse);
		}
		return amount;
	}


	/**
	 * 升级树屋
	 * @param treeHouseId
	 * @param count
	 * @return
	 */
	private int addLevelByCulture(String treeHouseId,int count,List<UserGoods> userGoods){
		TreeHouse treeHouse=this.getById(treeHouseId);
		int currentCulture=treeHouse.getCultureValue();
		int level=treeHouse.getLevel();
		int tempLevel=level;
		boolean check=true;
		int remainder=currentCulture+count;
		while (check){
			TreeHouseLevelSetting setting=treeHouseLevelSettingService.findByTreeLevel(level);
			//解锁或升级工种
			if(setting!=null && setting.getWorkTypeLevel()!=null && setting.getWorkTypeId()!=null &&
					setting.getWorkTypeLevel()>0 && setting.getWorkTypeId()>0 && level>tempLevel){
				if(setting.getWorkTypeLevel().equals(WorkTypeConstant.WORK_TYPE_LEVEL_ONE)){//解锁工种
					int workTypeId=setting.getWorkTypeId();
					WorkType workType=workTypeService.findByWorkTypeId(workTypeId);
					if(workType!=null){
						UserWorkType userWorkType=new UserWorkType();
						userWorkType.setLevel(WorkTypeConstant.WORK_TYPE_LEVEL_ONE);
						userWorkType.setId(UUIDUtil.generateUUID());
						userWorkType.setOrder(workType.getOrder());
						userWorkType.setUserId(treeHouse.getUserId());
						userWorkType.setStatus(StatusConstant.WORK_TYPE_UNLOCK);
						userWorkType.setUnlockLevel(workType.getUnlockLevel());
						userWorkType.setWorkTypeName(workType.getWorkTypeName());
						userWorkType.setWorkTypeId(workType.getId());
						userWorkTypeService.add(userWorkType);
						userWorkPositionService.initWorkPositionBatch(userWorkType.getWorkTypeId(),treeHouse.getUserId());
					}
				}else {//升级工种
					userWorkTypeService.upgradeLevel(treeHouse.getUserId(),setting.getWorkTypeId(),setting.getWorkTypeLevel());
				}
			}
			if(setting.getCultureValue()!=null && setting.getCultureValue()>0){
				if(remainder<setting.getCultureValue()){
					break;
				}else {
					remainder=remainder-setting.getCultureValue();
					level=level+1;
					JSONArray array=JSONArray.parseArray(setting.getUpgradeAward());
					List<UserGoods> goodsList=new ArrayList<>();
					for(int i=0;i<array.size();i++){
						JSONObject object=array.getJSONObject(i);
						UserGoods map=new UserGoods();
						map.setGoodsId(object.getString("goodsId"));
						map.setAmount(object.getIntValue("amount"));
						goodsList.add(map);
					}
					//增加账户背包物品数量
					List<UserGoods> goods=userGoodsService.addUserGoods(treeHouse.getUserId(),goodsList);
					if(goods!=null) userGoods.addAll(goods);
				}
			}
		}
		if(level>tempLevel){
			this.updateTreeHouseLevel(treeHouseId,level);
		}
		return remainder;
	}

	@Override
	public void updateTreeHouse(String treeHouseId, int amuseCount) {
		treeHouseExtMapper.updateTreeHouseAmusementCount(treeHouseId,amuseCount);
	}

	@Override
	public void cancelAllNoSpeak(String userId) {
		TreeHouse treeHouse=new TreeHouse();
		treeHouse.setId(userId);
		treeHouse.setCanSpeak(false);
		treeHouseMapper.updateByPrimaryKeySelective(treeHouse);
		String message="{\"type\":\"canSpeaking\", \"code\":0}";
		chatMessageService.sendMessageToAllInTreeHouse(userId,message);
	}

	@Override
	public void allNoSpeak(String userId) {
		TreeHouse treeHouse=new TreeHouse();
		treeHouse.setId(userId);
		treeHouse.setCanSpeak(true);
		treeHouseMapper.updateByPrimaryKeySelective(treeHouse);
		String message="{\"type\":\"noSpeaking\", \"code\":0}";
		chatMessageService.sendMessageToAllInTreeHouse(userId,message);
	}

	@Override
	public TreeHouse subByUserId(TreeHouse treeHouse) {
		TreeHouse house=this.findByUserId(treeHouse.getUserId());
		if(treeHouse.getAmbienceCount()>house.getAmbienceCount() ||
				treeHouse.getAmusementCount()>house.getAmusementCount() ||
				treeHouse.getEnvironmentCount()>house.getEnvironmentCount()){
			//throw new CheckException("条件不满足！");
			return null;
		}else{
			house.setEnvironmentCount(house.getEnvironmentCount()-treeHouse.getEnvironmentCount());
			house.setAmusementCount(house.getAmusementCount()-treeHouse.getAmusementCount());
			house.setAmbienceCount(house.getAmbienceCount()-treeHouse.getAmbienceCount());
			treeHouseMapper.updateByPrimaryKeySelective(house);
		}
		return house;
	}
}
