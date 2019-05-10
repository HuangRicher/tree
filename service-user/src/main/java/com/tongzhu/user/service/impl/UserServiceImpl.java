package com.tongzhu.user.service.impl;


import com.tongzhu.constant.HTTPUrlConstant;
import com.tongzhu.user.constant.StatusConstant;
import com.tongzhu.user.constant.WorkerConstant;
import com.tongzhu.user.domain.*;
import com.tongzhu.except.CheckException;
import com.tongzhu.user.mapper.RoleMapper;
import com.tongzhu.user.mapper.UserMapper;
import com.tongzhu.user.mapper.UserRoleTitleMapper;
import com.tongzhu.user.mapper.ext.UserExtMapper;
import com.tongzhu.user.mapper.vo.UserRankingVO;
import com.tongzhu.user.model.*;
import com.tongzhu.user.po.GenerateUserPO;
import com.tongzhu.user.po.SkillPO;
import com.tongzhu.user.redis.DistributedLockCallback;
import com.tongzhu.user.redis.DistributedLockTemplate;
import com.tongzhu.user.redis.RedisService;
import com.tongzhu.user.service.*;
import com.tongzhu.user.service.vo.UserDetailVO;
import com.tongzhu.util.DateComputeUtil;
import com.tongzhu.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserExtMapper userExtMapper;

	@Autowired
	private TreeHouseRoomService treeHouseRoomService;

	@Autowired
	private TreeHouseService treeHouseService;

	@Autowired
	private UserGoodsService userGoodsService;

	@Resource
	DistributedLockTemplate distributedLockTemplate;

	@Autowired
	private RedisService redisService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private BuildingService buildingService;

	@Autowired
	private UserSkillService userSkillService;

	@Autowired
	private ArsenalService arsenalService;

	@Autowired
	private PropService propService;


	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private UserRoleTitleMapper userRoleTitleMapper;

	@Autowired
	private AccountService accountService;

	@Autowired
	private RoleLevelSettingService roleLevelSettingService;

	@Autowired
	private TreeHouseFurnitureService treeHouseFurnitureService;

	@Autowired
	private FriendService friendService;





	public User findByUserId(String userId) {
		UserExample example=new UserExample();
		example.createCriteria().andUserIdEqualTo(userId);
		List<User> userList=userMapper.selectByExample(example);
		if(userList!=null && userList.size()>0){
			return userList.get(0);
		}
		return null;
	}

	@Override
	public void updateUser(User user) {
		user.setUpdateDate(new Date());
		userMapper.updateByPrimaryKeySelective(user);
	}


	/**
	 * 初始化用户信息(生成用户基本信息)
	 */
	@Transactional
	@Override
	public User initUser(String byUserId) {
		User user = new User();
		user.setId(UUIDUtil.generateUUID());
		user.setUserId(byUserId);
		user.setGrade(1);
		user.setVip(0);
		user.setSex(2);
		user.setName(byUserId+"玩家");
		user.setStatus(StatusConstant.USER_ONLINE);
		user.setCreateDate(new Date());
		user.setPortraitUrl("http://t-1.tuzhan.com/22039f8151a9/c-1/l/2012/09/26/14/a9a77e5d21b84e8399020e5f5aa207b0.jpg");
		user.setLoginDate(new Date());
		user.setSellingPrice(500);
		user.setAutograph("这家伙很懒什么都没留");
		userMapper.insertSelective(user);
		//生成背包信息
		userGoodsService.initUserGoods(user.getUserId());
		//创建树屋
		TreeHouse treeHouse = new TreeHouse();
		treeHouse.setUserId(byUserId);
		treeHouse.setCultureValue(0);
		treeHouse.setId(byUserId);
		treeHouse.setCreateDate(new Date());
		treeHouse.setFlourishingRate(0);
		treeHouse.setBreakValue(0);
		treeHouse.setLevel(1);
		treeHouse.setStartDate(new Date());
		treeHouseService.add(treeHouse);
		
		buildingService.initBuildingUser(byUserId);
		return user;
	}

	/**
	 * 降低宅友身价
	 * @param userId
	 * @param workerId
	 */
	@Transactional
	@Override
	public User reduceSellingPrice(String userId, String workerId) throws CheckException {
		TreeHouseRoom room=treeHouseRoomService.findWorkersByUserIdAndWorkerId(userId,workerId);
		if(room!=null){
			if(room.getReducePriceDate()!=null &&
					DateComputeUtil.DateToLocalDate(room.getReducePriceDate()).equals(DateComputeUtil.DateToLocalDate(new Date())))
				throw new CheckException("在同一玩家手中，同一个宅友每天只能操作一次!");
			User user=this.findByUserId(workerId);
			if(user!=null){
				User u=new User();
				u.setId(user.getId());
				u.setSellingPrice((int)(user.getSellingPrice()* WorkerConstant.REDUCE_PRICE_RATE));
				userMapper.updateByPrimaryKeySelective(u);
				treeHouseRoomService.updateReducePriceDate(userId,workerId);
				u.setUserId(user.getUserId());
				return u;
			}
		}else{
			throw new CheckException("不存在该宅友！");
		}
		return null;
	}


	/**
	 * 随机查询可购买的陌生玩家
	 * @param minSellingPrice 身价范围--小
	 * @param maxSellingPrice 身价范围--大
	 * @param userId 用户ID
	 * @param currentDateTime 当前时间
	 * @param currentDate 当前日期
	 * @param exchangeCount 用户被转让的次数
	 * @param count 数量
	 * @return
	 */
	@Override
	public List<User> selectUserForBuyByRand(int minSellingPrice, int maxSellingPrice, String userId,
											 Date currentDateTime, Date currentDate, int exchangeCount,int count) {
		return userExtMapper.selectUserForBuyByRand(minSellingPrice,maxSellingPrice,userId,currentDateTime,
				currentDate,exchangeCount,count);
	}


	/**
	 * 随机查询可购买的好友
	 * @param userId 用户ID
	 * @param status 好友状态
	 * @param currentDateTime 当前时间
	 * @param currentDate 当前日期
	 * @param exchangeCount 转让次数
	 * @param sellingPrice 用户拥有金币的数量
	 * @param count 查询记录数量
	 * @return
	 */
	@Override
	public List<User> selectFriendsForBuyByRand(String userId, int status, Date currentDateTime, Date currentDate,
												  int exchangeCount, int sellingPrice, int count) {
		return userExtMapper.selectFriendsForBuyByRand(userId,status,currentDateTime,currentDate, exchangeCount,sellingPrice,count);
	}

	/**
	 * 推荐好友列表 (5 男 5 女）
	 * @param userId
	 * @return
	 */
	@Override
	public List<User> selectRecommendFriends(String userId) {
		UserRole user=userRoleService.getByUserId(userId);
		if(user==null)
			return null;
		List<User> result=new ArrayList<>();
		int roleLevel=user.getRoleLevel();
		int i=1;
		List<User> userList1=userExtMapper.selectRecommendFriends(userId,roleLevel,1,5);
		while (true){
			if(userList1.isEmpty() || userList1.size()<5){
				if(roleLevel-i<0){
					i=1;
					result.addAll(userList1);
					break;
				}else{
					userList1=userExtMapper.selectRecommendFriendsOther(userId,roleLevel-i,roleLevel+i,1,5);
					i++;
					continue;
				}
			}else {
				result.addAll(userList1);
				i=1;
				break;
			}
		}
		List<User> userList2=userExtMapper.selectRecommendFriends(userId,roleLevel,2,5);
		while (true){
			if(userList2.isEmpty() || userList2.size()<5){
				if(roleLevel-i<0){
					result.addAll(userList2);
					break;
				}else{
					userList2=userExtMapper.selectRecommendFriendsOther(userId,roleLevel-i,roleLevel+i,2,5);
					i++;
					continue;
				}
			}else{
				result.addAll(userList2);
				break;
			}
		}
		return result;
	}

	@Override
	public User recommendFriendByOppositeSex(String userId) {
		UserRole userRole=userRoleService.getByUserId(userId);
		if(userRole==null)
			return null;
		User user=null;
		if(1==userRole.getSex()){
			user=userExtMapper.recommendFriendByOppositeSex(userId,2);
		}
		if(2==userRole.getSex()){
			user=userExtMapper.recommendFriendByOppositeSex(userId,1);
		}
		return user;
	}

	@Override
	public User selectUserForFightRankingByRand(String userId) {
		return userExtMapper.selectOneForFightRankingByRand(userId);
	}

	/**
	 * 身价排名榜
	 * @param count 查询的记录数量
	 * @return
	 */
	@Override
	public List<UserRankingVO> findForRankingListAboutSellingPrice(int count) {
		return userExtMapper.selectForSellingPriceRanklingList(count);
	}


	/**
	 * 富豪榜（金币数量）
	 * @param goodsId 物品Id-金币
	 * @param count 查询的记录数量
	 * @return
	 */
	@Override
	public List<UserRankingVO> findForRankingListAboutMoney(String goodsId, int count) {
		return userExtMapper.selectForMoneyRanklingList(goodsId,count);
	}


	/**
	 * 获取玩家信息
	 * @param userId 用户ID
	 * @return
	 */
	@Override
	public UserDetailVO getUserDetail(String userId) {
		User user=this.findByUserId(userId);
		if(user!=null){
			UserDetailVO userDetail=new UserDetailVO();
			userDetail.setUserId(user.getUserId());
			userDetail.setUserName(user.getName());
			userDetail.setPortraitUrl(user.getPortraitUrl());
			userDetail.setSellingPrice(user.getSellingPrice());
			userDetail.setAutograph(user.getAutograph());
			return userDetail;
		}
		return null;
	}

	@Override
	public User initWxUser(String openid, int sex, String nickName, String province, String city, String country, String headImgUrl, String unionId) {
		User user = new User();
		user.setId(UUIDUtil.generateUUID());
		user.setOpenId(openid);
		user.setGrade(1);
		user.setVip(0);
		user.setName(nickName);
		user.setSex(sex);
		user.setProvince(province);
		user.setCity(city);
		user.setCountry(country);
		user.setStatus(StatusConstant.USER_OFFLINE);
		user.setCreateDate(new Date());
		user.setPortraitUrl(headImgUrl);
		user.setLoginDate(new Date());
		user.setSellingPrice(500);
		user.setUnionId(unionId);
		user.setAutograph("这家伙很懒什么都没留");
		userMapper.insertSelective(user);
		return user;
	}

	@Override
	public User findByOpenId(String openid) {
		UserExample example=new UserExample();
		example.createCriteria().andOpenIdEqualTo(openid);
		List<User> userList=userMapper.selectByExample(example);
		if(userList!=null && userList.size()>0){
			return userList.get(0);
		}
		return null;
	}

	/**
	 * 修改宅友身价
	 * @param userId
	 * @param sellingPrice
	 */
	@Override
	public void updateUserSellingPrice(String userId, Integer sellingPrice) {
		User user=new User();
		user.setSellingPrice(sellingPrice);
		UserExample example=new UserExample();
		example.createCriteria().andUserIdEqualTo(userId);
		userMapper.updateByExampleSelective(user,example);
	}

	/**
	 * 统计所有用户数
	 * @return
	 */
	@Override
	public int countAllUser() {
		UserExample example=new UserExample();
		return userMapper.countByExample(example);
	}

	@Transactional
	@Override
	public void addUserRole(String userId, String roleName, Integer roleId, Integer roleSex, String account) {
		int roleLevel=1;
		User user=new User();
		user.setName(roleName);
		user.setRoleId(roleId);
		user.setGrade(roleLevel);
		if(redisService.get(account+"header")!=null){
			user.setPortraitUrl((String)redisService.get(account+"header"));
		}else{
			user.setPortraitUrl(roleId+"");
		}
		UserExample example=new UserExample();
		example.createCriteria().andUserIdEqualTo(userId);
		userMapper.updateByExampleSelective(user,example);
		UserRole userRole=new UserRole();
		userRole.setUserId(userId);
		userRole.setRoleTitle(3);
		userRole.setUserName(roleName);
		userRole.setRoleId(roleId);
		userRole.setRoleLevel(roleLevel);
		userRole.setSex(roleSex);
		userRoleService.add(userRole);

		UserRoleTitle userRoleTitle=new UserRoleTitle();
		userRoleTitle.setRoleTitle(3);
		userRoleTitle.setUserId(userId);
		userRoleTitleMapper.insert(userRoleTitle);

		RoleLevelSetting setting=roleLevelSettingService.getByLevel(roleLevel);
		PropInfo propInfo = new PropInfo();
		userRoleService.computePropInfo(propInfo,setting,userRole);
		userGoodsService.addUserAdornEquipAttribute(userId,propInfo.getVitality(),propInfo.getAttack(),
				propInfo.getSpellAttacks(),propInfo.getPdef(),propInfo.getMagdef(),
				propInfo.getCrit(),propInfo.getDodge(),propInfo.getHitRate(),propInfo.getDefenseCrit());

		//----初始化数据
		List<Integer> skillList_ws=Arrays.asList(23,11);
		List<Integer> skillList_ck=Arrays.asList(25,8);
		List<Integer> skillList_gj=Arrays.asList(26,2);
		List<Integer> skillList_fs=Arrays.asList(24,14);
		List<String> weaponList_ws= Arrays.asList("110001");
		List<String> weaponList_ck= Arrays.asList("120001");
		List<String> weaponList_gj= Arrays.asList("130001");
		List<String> weaponList_fs= Arrays.asList("140001");

		List<String> equipList_ws=Arrays.asList("1100","2100","3100","4100","5100","6100","7100","8100");
		List<String> equipList_ck=Arrays.asList("1200","2200","3200","4200","5200","6200","7200","8200");
		List<String> equipList_gj=Arrays.asList("1300","2300","3300","4300","5300","6300","7300","8300");
		List<String> equipList_fs=Arrays.asList("1400","2400","3400","4400","5400","6400","7400","8400");

		//28008需要手动布置 28021已布置 28032已布置 28044已布置 28056已布置 28068已布置
		Map<String,Integer>  furnitureMap=new HashMap<>();
		furnitureMap.put("28008",0);
		furnitureMap.put("28021",1);
		furnitureMap.put("28032",1);
		furnitureMap.put("28044",1);
		furnitureMap.put("28056",1);
		furnitureMap.put("28068",1);
		treeHouseFurnitureService.addMultiFurniture(userId, furnitureMap);

		Map<Integer,List<String>> map=new HashMap<>();
		if(roleId==1 || roleId==2){
			for(Integer skillId:skillList_ws){
				UserSkill skill=new UserSkill();
				skill.setUserId(userId);
				skill.setId(UUIDUtil.generateUUID());
				skill.setLevel(1);
				skill.setSkillId(skillId);
				userSkillService.add(skill);
			}
			map.put(2,equipList_ws);
			map.put(3,weaponList_ws);
		}
		if(roleId==3 || roleId==4){
			for(Integer skillId:skillList_ck){
				UserSkill skill=new UserSkill();
				skill.setUserId(userId);
				skill.setId(UUIDUtil.generateUUID());
				skill.setLevel(1);
				skill.setSkillId(skillId);
				userSkillService.add(skill);
			}
			map.put(2,equipList_ck);
			map.put(3,weaponList_ck);
		}
		if(roleId==5 || roleId==6){
			for(Integer skillId:skillList_gj){
				UserSkill skill=new UserSkill();
				skill.setUserId(userId);
				skill.setId(UUIDUtil.generateUUID());
				skill.setLevel(1);
				skill.setSkillId(skillId);
				userSkillService.add(skill);
			}
			map.put(2,equipList_gj);
			map.put(3,weaponList_gj);
		}
		if(roleId==7 || roleId==8){
			for(Integer skillId:skillList_fs){
				UserSkill skill=new UserSkill();
				skill.setUserId(userId);
				skill.setId(UUIDUtil.generateUUID());
				skill.setLevel(1);
				skill.setSkillId(skillId);
				userSkillService.add(skill);
			}
			map.put(2,equipList_fs);
			map.put(3,weaponList_fs);
		}
		userGoodsService.addUseMultiEquipAndWeapon(userId,map);
		List<User> userList=this.findNPCUser(Arrays.asList("1","2","3","4","5","6","7","8","9"));
		if(!CollectionUtils.isEmpty(userList)){
			for(User us:userList){
				Friend friend=new Friend();
				friend.setCreateDate(new Date());
				friend.setFriendsId(us.getUserId());
				friend.setId(UUIDUtil.generateUUID());
				friend.setStatus(1);
				friend.setType(2);
				if(us.getUserId().equals("1")){
					friend.setIntimacy(15);
				}else if(us.getUserId().equals("2")){
					friend.setIntimacy(10);
				}else if(us.getUserId().equals("3")) {
					friend.setIntimacy(5);
				}else{
					friend.setIntimacy(0);
				}

				friend.setUserId(userId);
				friendService.addFriend(friend);
			}
		}
		//redisService.remove(account+"header");
	}

	@Transactional
	@Override
	public void addUserRole(String userId, String roleName, Integer roleId, Integer roleLevel, List<SkillPO> skillList, List<String> weaponIds){
		User user=new User();
		user.setName(roleName);
		user.setRoleId(roleId);
		user.setGrade(roleLevel);
		UserExample example=new UserExample();
		example.createCriteria().andUserIdEqualTo(userId);
		userMapper.updateByExampleSelective(user,example);
		Role role =  roleMapper.selectByPrimaryKey(roleId);
		UserRole userRole=new UserRole();
		userRole.setUserId(userId);
		userRole.setUserName(roleName);
		userRole.setRoleId(roleId);
		userRole.setRoleLevel(roleLevel);
		userRole.setSex(role.getSex());
		userRoleService.add(userRole);

		//----测试数据
		for(SkillPO map:skillList){
			UserSkill skill=new UserSkill();
			skill.setUserId(userId);
			skill.setId(UUIDUtil.generateUUID());
			skill.setLevel(map.getLevel());
			skill.setSkillId(map.getSkillId());
			userSkillService.add(skill);
		}
		for(String id:weaponIds){
			arsenalService.addGoodsWeapon(userId,id);
		}
	}

	/**
	 * 给微信注册用户添加userId,并初始化数据。
	 * @param account
	 * @param roleSex
	 * @param roleId
	 * @return
	 */
	@Transactional
	@Override
	public User addUserId(String account, Integer roleSex, Integer roleId) {

		String userId=distributedLockTemplate.lock(new DistributedLockCallback<String>(){
			@Override
			public String process() {
				String userIds="100101";
				if(redisService.get("generate-userId")==null){
					redisService.set("generate-userId",userIds);
				}else {
					userIds=(String)redisService.get("generate-userId");
					userIds=""+(Integer.valueOf(userIds)+1);
					redisService.set("generate-userId",userIds);
				}
				return userIds;
			}
			@Override
			public String getLockName() {
				return "lockForUserId";
			}
		});


		User user=new User();
		user.setId(UUIDUtil.generateUUID());
		user.setGrade(1);
		user.setVip(0);
		user.setStatus(StatusConstant.USER_OFFLINE);
		user.setCreateDate(new Date());
		user.setPortraitUrl("");
		user.setLoginDate(new Date());
		user.setSellingPrice(500);
		user.setAutograph("这家伙很懒什么都没留");
		user.setUserId(userId);
		user.setSex(roleSex);
		userMapper.insertSelective(user);

		accountService.addAccountUser(account,roleId,userId);

		//生成背包信息
		userGoodsService.initUserGoods(user.getUserId());
		//创建树屋
		TreeHouse treeHouse = new TreeHouse();
		treeHouse.setUserId(userId);
		treeHouse.setCultureValue(0);
		treeHouse.setId(userId);
		treeHouse.setCreateDate(new Date());
		treeHouse.setFlourishingRate(0);
		treeHouse.setBreakValue(0);
		treeHouse.setLevel(1);
		treeHouse.setCanSpeak(true);
		treeHouse.setStartDate(new Date());
		treeHouseService.add(treeHouse);
		buildingService.initBuildingUser(userId);
		return user;
	}

	@Override
	public void updateUserByUserId(User user) {
		UserExample example=new UserExample();
		example.createCriteria().andUserIdEqualTo(user.getUserId());
		user.setUserId(null);
		userMapper.updateByExampleSelective(user,example);
	}

	@Override
	public List<UserRankingVO> findForRankingListAboutRoleLevel(int count) {
		return userExtMapper.findForRankingListAboutRoleLevel(count);
	}

	@Override
	public List<UserRankingVO> findForRankingListAboutCharmNum(int count) {
		return userExtMapper.findForRankingListAboutCharmNum(count);
	}

	@Override
	public List<UserRankingVO> findForRankingListAboutAlwaysFighting(int count) {
		return userExtMapper.findForRankingListAboutAlwaysFighting(count);
	}

	@Override
	public List<UserDetailVO> getUserByUserIdOrName(String searchUser) {
		List<User> users=userExtMapper.selectByUserIdOrName(searchUser);
		List<UserDetailVO> list=new ArrayList<>();
		if(!CollectionUtils.isEmpty(users)){
			for(User user:users){
				UserDetailVO userDetail=new UserDetailVO();
				userDetail.setUserId(user.getUserId());
				userDetail.setUserName(user.getName());
				userDetail.setSex(user.getSex());
				userDetail.setRoleId(user.getRoleId());
				userDetail.setRoleLevel(user.getGrade());
				userDetail.setPortraitUrl(user.getPortraitUrl());
				list.add(userDetail);
			}
			return list;
		}
		return null;
	}

	@Override
	public List<UserRankingVO> findForRankingListAboutPet(int count) {
		return userExtMapper.findForRankingListAboutPet(count);
	}

	@Override
	public void generateNewUser(GenerateUserPO generateUserPO) {
		Account account=accountService.getByAccount(generateUserPO.getAccount());
		if(account==null)
			accountService.add(generateUserPO.getAccount(),generateUserPO.getPassword());
		Role role =userRoleService.getByRoleId(generateUserPO.getRoleId());
		User byUserId = this.addUserId(generateUserPO.getAccount(),role.getSex(), generateUserPO.getRoleId(),generateUserPO);
		this.addUserRole(byUserId.getUserId(),generateUserPO.getRoleName(),generateUserPO.getRoleId(), role.getSex(),generateUserPO);
	}


	private void addUserRole(String userId, String roleName, Integer roleId, Integer roleSex,GenerateUserPO generateUserPO) {
		User user=new User();
		user.setName(roleName);
		user.setRoleId(roleId);
		user.setGrade(generateUserPO.getRoleLevel());
		UserExample example=new UserExample();
		example.createCriteria().andUserIdEqualTo(userId);
		userMapper.updateByExampleSelective(user,example);
		UserRole userRole=new UserRole();
		userRole.setUserId(userId);
		userRole.setUserName(roleName);
		userRole.setRoleId(roleId);
		userRole.setRoleLevel(generateUserPO.getRoleLevel());
		userRole.setSex(roleSex);
		userRoleService.add(userRole);

		UserRoleTitle userRoleTitle=new UserRoleTitle();
		userRoleTitle.setRoleTitle(3);
		userRoleTitle.setUserId(userId);
		userRoleTitleMapper.insert(userRoleTitle);

		RoleLevelSetting setting=roleLevelSettingService.getByLevel(generateUserPO.getRoleLevel());
		PropInfo propInfo = new PropInfo();
		userRoleService.computePropInfo(propInfo,setting,userRole);
		userGoodsService.addUserAdornEquipAttribute(userId,propInfo.getVitality(),propInfo.getAttack(),
				propInfo.getSpellAttacks(),propInfo.getPdef(),propInfo.getMagdef(),
				propInfo.getCrit(),propInfo.getDodge(),propInfo.getHitRate(),propInfo.getDefenseCrit());

		//----测试数据
		List<Integer> skillList=generateUserPO.getSkillIds();//Arrays.asList(1,2,3,4,5,22,24,25,26,27);
		List<String> weaponList=generateUserPO.getWeaponList();// Arrays.asList("110010","110011","110012","110013","110014","110015","110016","110017","110018","110019","110020");

		List<UserGoods> propList=generateUserPO.getPropList();//Arrays.asList("11801:1","12352:2","18730:3","28208:1","28209:1","11405:1");

		List<String> equipList=generateUserPO.getEquipmentList();//Arrays.asList("1110","2110","3110","4110","5110","6110","7110","8110");

		for(Integer skillId:skillList){
			UserSkill skill=new UserSkill();
			skill.setUserId(userId);
			skill.setId(UUIDUtil.generateUUID());
			skill.setLevel(1);
			skill.setSkillId(skillId);
			userSkillService.add(skill);
		}

		List<UserGoods> goodsList=new ArrayList<>();
		for(UserGoods prop:propList){
			UserGoods goods=new UserGoods();
			goods.setGoodsId(prop.getGoodsId()+"");
			goods.setAmount(prop.getAmount());
			goodsList.add(goods);
		}
		userGoodsService.addUserGoods(userId,goodsList);
		if(roleId==1 || roleId==2){
			for(String equipId:equipList){
				userGoodsService.addEquipAndWeapon(userId,2,equipId);
			}
			for(String weaponId:weaponList){
				userGoodsService.addEquipAndWeapon(userId,3,weaponId);
			}
		}
	}

	private User addUserId(String account, Integer roleSex, Integer roleId,GenerateUserPO generateUserPO) {

		String userId=distributedLockTemplate.lock(new DistributedLockCallback<String>(){
			@Override
			public String process() {
				String userIds="100101";
				if(redisService.get("generate-userId")==null){
					redisService.set("generate-userId",userIds);
				}else {
					userIds=(String)redisService.get("generate-userId");
					userIds=""+(Integer.valueOf(userIds)+1);
					redisService.set("generate-userId",userIds);
				}
				return userIds;
			}
			@Override
			public String getLockName() {
				return "lockForUserId";
			}
		});


		User user=new User();
		user.setId(UUIDUtil.generateUUID());
		user.setGrade(1);
		user.setVip(0);
		user.setStatus(StatusConstant.USER_OFFLINE);
		user.setCreateDate(new Date());
		user.setPortraitUrl("");
		user.setLoginDate(new Date());
		user.setSellingPrice(500);
		user.setAutograph("这家伙很懒什么都没留");
		user.setUserId(userId);
		user.setSex(roleSex);
		userMapper.insertSelective(user);

		accountService.addAccountUser(account,roleId,userId);

		//生成背包信息
		userGoodsService.initUserGoods(user.getUserId());
		//创建树屋
		TreeHouse treeHouse = new TreeHouse();
		treeHouse.setUserId(userId);
		treeHouse.setCultureValue(0);
		treeHouse.setId(userId);
		treeHouse.setCreateDate(new Date());
		treeHouse.setFlourishingRate(0);
		treeHouse.setBreakValue(0);
		treeHouse.setLevel(1);
		treeHouse.setEnvironmentCount(generateUserPO.getEnvironmentCount());
		treeHouse.setAmbienceCount(generateUserPO.getHumanCount());
		treeHouse.setAmusementCount(generateUserPO.getAmusementCount());
		treeHouse.setStartDate(new Date());
		treeHouseService.add(treeHouse);
		buildingService.initBuildingUser(userId);
		return user;
	}

	@Override
	public void addUser(User user) {
		userMapper.insertSelective(user);
	}

	@Override
	public List<User> findNPCUser(List<String> userIds){
		UserExample example=new UserExample();
		example.createCriteria().andUserIdIn(userIds);
		return userMapper.selectByExample(example);
	}

	@Override
	public void deleteUserByUserId(String s) {
		UserExample example=new UserExample();
		example.createCriteria().andUserIdEqualTo(s);
		userMapper.deleteByExample(example);
	}
}
