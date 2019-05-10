package com.tongzhu.friend.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tongzhu.common.Pager;
import com.tongzhu.friend.constant.FriendConstant;
import com.tongzhu.friend.constant.StatusConstant;
import com.tongzhu.friend.constant.TreeHouseConstant;
import com.tongzhu.friend.domain.User;
import com.tongzhu.friend.domain.UserDetail;
import com.tongzhu.friend.mapper.FriendMapper;
import com.tongzhu.friend.mapper.ext.FriendExtMapper;
import com.tongzhu.friend.mapper.vo.FriendDO;
import com.tongzhu.friend.mapper.vo.FriendVO;
import com.tongzhu.friend.model.Friend;
import com.tongzhu.friend.model.FriendExample;
import com.tongzhu.friend.service.FriendService;
import com.tongzhu.friend.service.TreeHouseRoomService;
import com.tongzhu.friend.service.UserService;
import com.tongzhu.friend.service.vo.FriendDetailVO;
import com.tongzhu.util.DateComputeUtil;
import com.tongzhu.util.UUIDUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 好友操作
 */
@Service
//@CacheConfig(cacheNames="friend")
public class FriendServiceImpl implements FriendService {

    private Logger log=Logger.getLogger(FriendServiceImpl.class);

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private FriendExtMapper friendExtMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private TreeHouseRoomService treeHouseRoomService;



    /**
     * 分页查找好友（按拥有的金币排序）
     * @param userId
     */
    @Override
    public Pager<FriendDetailVO> findFriendByPage(String userId, int pageNumber, int pageSize) {
        Page<FriendVO> page=PageHelper.startPage(pageNumber, pageSize);
        friendExtMapper.selectFriends(userId, StatusConstant.FRIEND_CONFIRMED_AGREE);
        List<FriendDetailVO> list=new ArrayList<>(page.getResult().size());
        dealUserFriendList(userId,page.getResult(),list);
        return new Pager(pageNumber,pageSize,page.getPages(),page.getTotal(),list);
    }


    /**
     * 查找好友列表
     * @param userId
     * @return
     */
    //@Cacheable(key="#p0")
    @Override
    public List<FriendDO> findFriendList(String userId) {
        //List<FriendVO> userFriendList=friendExtMapper.selectFriends(userId,StatusConstant.FRIEND_CONFIRMED_AGREE);

        //List<FriendDetailVO> list=new ArrayList<>(userFriendList.size());
        //dealUserFriendList(userId,userFriendList,list);
        List<FriendDO> list=friendExtMapper.selectMyFriendList(userId);
        return list;
    }

    /**
     * 根据userId 查询出要查询好友的信息
     * @param searchUser
     * @return 好友信息
     */
    @Override
    public List<UserDetail> searchUserByUserIdOrName(String searchUser) {
        return userService.getUserByUserIdOrName(searchUser);
    }

    /**
     * 请求添加好友
     * @param userId
     * @return
     */
    @Override
    public int requestAddFriend(String userId,String friendId) {
        Friend friend=new Friend();
        friend.setId(UUIDUtil.generateUUID());
        friend.setCreateDate(new Date());
        friend.setUserId(userId);
        friend.setFriendsId(friendId);
        friend.setStatus(StatusConstant.FRIEND_TO_BE_CONFIRMED);
        return friendMapper.insertSelective(friend);
    }

    /**
     * 查找待确认的好友请求数
     * @param friendId
     * @return
     */
    //@CachePut(key="#p0+'-count'")
    @Override
    public int countFriendToBeConfirmByFriendId(String friendId) {
        FriendExample example=new FriendExample();
        example.createCriteria().andFriendsIdEqualTo(friendId).andStatusEqualTo(StatusConstant.FRIEND_TO_BE_CONFIRMED);
        return friendMapper.countByExample(example);
    }

    /**
     * 确认加好友请求（同意、拒绝）
     * @param userId
     * @param friendId
     * @param type 同意：1  拒绝：2
     * @return
     */
    @Transactional
    @Override
    public FriendDetailVO confirmAddFriend(String userId, String friendId, int type) {
        FriendDetailVO userFriendDetailVO=new FriendDetailVO();
        FriendExample example=new FriendExample();
        example.createCriteria().andUserIdEqualTo(friendId).andFriendsIdEqualTo(userId);
        if(type== FriendConstant.TYPE_AGREE){
            Friend friend=new Friend();
            friend.setStatus(StatusConstant.FRIEND_CONFIRMED_AGREE);
            friendMapper.updateByExampleSelective(friend,example);
            treeHouseRoomService.updateIsGameFriend(userId,friendId, TreeHouseConstant.IS_GAME_FRIEND);

            //返回
            User user=userService.findByUserId(friendId);
            userFriendDetailVO.setUserId(friendId);
            userFriendDetailVO.setPortraitUrl(user.getPortraitUrl());
            userFriendDetailVO.setName(user.getName());
            //在线状态  离线时显示上次登录时间，不超过一天的显示具体小时数（不足一小时显示“刚刚”），超过1天的显示具体天数，超过七天的显示七天前
            userFriendDetailVO.setStatus(user.getStatus());
            userFriendDetailVO.setIntimacy(0);
            userFriendDetailVO.setRoleId(user.getRoleId());
            userFriendDetailVO.setRoleLevel(user.getRoleLevel());
            userFriendDetailVO.setSex(user.getSex());
            return userFriendDetailVO;

        }
        if(type==FriendConstant.TYPE_DISAGREE){
            friendMapper.deleteByExample(example);
            return null;
        }

        return null;
    }


    /**
     * 查找好友申请列表
     * @param userId
     */
    @Override
    public List<FriendDO> findToBeConfirmFriend(String userId) {
        List<FriendDO> list=friendExtMapper.selectFriendListToConfirm(userId);
        return list;
    }

    /**
     * 分页查询好友申请列表
     * @param userId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public Pager<FriendDetailVO> findToBeConfirmFriendByPage(String userId, int pageNumber, int pageSize) {
        FriendExample example=new FriendExample();
        example.createCriteria().andFriendsIdEqualTo(userId).andStatusEqualTo(StatusConstant.FRIEND_TO_BE_CONFIRMED);
        Page<Friend> page=PageHelper.startPage(pageNumber,pageSize);
        friendMapper.selectByExample(example);
        List<FriendDetailVO> list=new ArrayList<>();
        //dealUserFriendListForBeConfirmFriend(page.getResult(),list);
        return new Pager(pageNumber,pageSize,page.getPages(),page.getTotal(),list);
    }


    /**
     * 统计好友数
     * @param userId
     * @return
     */
    @Override
    public int countFriends(String userId) {
        FriendExample example=new FriendExample();
        FriendExample.Criteria criteria1=example.createCriteria();
        criteria1.andUserIdEqualTo(userId).andStatusEqualTo(StatusConstant.FRIEND_CONFIRMED_AGREE);
        FriendExample.Criteria criteria2=example.createCriteria();
        criteria2.andFriendsIdEqualTo(userId).andStatusEqualTo(StatusConstant.FRIEND_CONFIRMED_AGREE);
        example.or(criteria2);
        return friendMapper.countByExample(example);
    }



    /**
     * 删除好友
     * @param userId 用户ID
     * @param friendId 好友用户ID
     */
    @Transactional
    //@CacheEvict(key="#p0+'-'+#p1")
    @Override
    public void deleteMyFriend(String userId, String friendId) {
        FriendExample example=new FriendExample();
        FriendExample.Criteria criteria1=example.createCriteria();
        criteria1.andUserIdEqualTo(userId).andFriendsIdEqualTo(friendId);
        FriendExample.Criteria criteria2=example.createCriteria();
        criteria2.andUserIdEqualTo(friendId).andFriendsIdEqualTo(userId);
        example.or(criteria2);
        friendMapper.deleteByExample(example);

        //treeHouseRoomService.updateIsGameFriend(userId,friendId,false);

    }


    /**
     * 查找xx是否为我的好友
     * @param userId 用户ID
     * @param searchUser  好友ID 或用户名
     * @return
     */
    @Override
    public Friend checkIsMyFriend(String userId, String searchUser) {
        return friendExtMapper.checkIsMyFriend(userId,searchUser);
    }


    /**
     * 查找xx是否为我正在申请的好友
     * @param userId 用户ID
     * @param friendId  好友ID
     * @return
     */
    @Override
    public Friend checkIsApplyMyFriend(String userId, String friendId) {
        FriendExample example=new FriendExample();
        FriendExample.Criteria criteria=example.createCriteria();
        criteria.andUserIdEqualTo(userId).andFriendsIdEqualTo(friendId).andStatusEqualTo(StatusConstant.FRIEND_TO_BE_CONFIRMED);
        FriendExample.Criteria criteria1=example.createCriteria();
        criteria1.andFriendsIdEqualTo(userId).andUserIdEqualTo(friendId).andStatusEqualTo(StatusConstant.FRIEND_TO_BE_CONFIRMED);
        example.or(criteria1);
        List<Friend> friendList=friendMapper.selectByExample(example);
        if(friendList!=null && friendList.size()>0){
            return friendList.get(0);
        }
        return null;
    }


    private void dealUserFriendList(String userId,List<FriendVO> friendVOList,List<FriendDetailVO> list){
        for(FriendVO friend:friendVOList){
            String friendId=friend.getFriendsId();
            if (userId.equals(friend.getFriendsId())) {
                friendId=friend.getUserId();
            }
            User user=userService.findByUserId(friendId);
            FriendDetailVO userDetail=new FriendDetailVO();
            userDetail.setUserId(friendId);
            userDetail.setPortraitUrl(user.getPortraitUrl());
            userDetail.setName(user.getName());
            //在线状态  离线时显示上次登录时间，不超过一天的显示具体小时数（不足一小时显示“刚刚”），超过1天的显示具体天数，超过七天的显示七天前
            String onlineState="";
            if(user.getStatus()!=null && user.getStatus()==StatusConstant.USER_ONLINE){
                onlineState="在线";
            }
            if(user.getStatus()!=null && user.getStatus()==StatusConstant.USER_OFFLINE && user.getLoginDate()!=null){
                onlineState= DateComputeUtil.compareOfflineTime(user.getLoginDate());
            }
            userDetail.setOnlineState(onlineState);
            userDetail.setSellingPrice(friend.getAmount());
            list.add(userDetail);
        }
    }

    private void dealUserFriendListForBeConfirmFriend(List<FriendVO> userFriends,List<FriendDetailVO> list){
        for(FriendVO friend:userFriends){
            User user=userService.findByUserId(friend.getUserId());
            FriendDetailVO userDetail=new FriendDetailVO();
            userDetail.setUserId(friend.getUserId());
            userDetail.setPortraitUrl(user.getPortraitUrl());
            userDetail.setName(user.getName());
            //在线状态  离线时显示上次登录时间，不超过一天的显示具体小时数（不足一小时显示“刚刚”），超过1天的显示具体天数，超过七天的显示七天前
            String onlineState="";
            if(user.getStatus()!=null && user.getStatus()==StatusConstant.USER_ONLINE){
                onlineState="在线";
            }
            if(user.getStatus()!=null && user.getStatus()==StatusConstant.USER_OFFLINE && user.getLoginDate()!=null){
                onlineState= DateComputeUtil.compareOfflineTime(user.getLoginDate());
            }
            userDetail.setOnlineState(onlineState);
            userDetail.setSellingPrice(friend.getAmount());
            list.add(userDetail);
        }
    }

    @Transactional(rollbackFor = Exception.class)
	@Override
	public void updateIntimacy(String userId, String friendId, int num) {
        FriendExample example=new FriendExample();
        FriendExample.Criteria criteria1=example.createCriteria();
        criteria1.andUserIdEqualTo(userId).andFriendsIdEqualTo(friendId);
        FriendExample.Criteria criteria2=example.createCriteria();
        criteria2.andFriendsIdEqualTo(userId).andUserIdEqualTo(friendId);
        example.or(criteria2);
        List<Friend> friendList=friendMapper.selectByExample(example);
        if(friendList!=null && friendList.size()>0){
        	Friend friend = friendList.get(0);
        	friend.setIntimacy(friend.getIntimacy() + num);
        	friend.setUpdateDate(new Date(System.currentTimeMillis()));
        	friendMapper.updateByPrimaryKey(friend);
        }
	}
    
	@Override
	public Integer getIntimacy(String userId, String friendId) {
        FriendExample example=new FriendExample();
        FriendExample.Criteria criteria=example.createCriteria();
        FriendExample.Criteria criteria2=example.createCriteria();
        criteria.andUserIdEqualTo(userId).andFriendsIdEqualTo(friendId);
        criteria2.andUserIdEqualTo(friendId).andFriendsIdEqualTo(userId);
        example.or(criteria2);
        List<Friend> friendList=friendMapper.selectByExample(example);
        if(friendList!=null && friendList.size()>0){
        	Friend friend = friendList.get(0);
        	return friend.getIntimacy();
        }else{
        	return -1;
        }
	}

    @Override
    public void updateFightStatus(String userId, String friendId, Integer status) {
        Friend friend=new Friend();
        friend.setFightStatus(status);

        FriendExample example=new FriendExample();
        FriendExample.Criteria criteria1=example.createCriteria();
        criteria1.andUserIdEqualTo(userId).andFriendsIdEqualTo(friendId);
        FriendExample.Criteria criteria2=example.createCriteria();
        criteria2.andFriendsIdEqualTo(userId).andUserIdEqualTo(friendId);
        example.or(criteria2);
        friendMapper.updateByExampleSelective(friend,example);
    }

    @Override
    public void resetFightStatus() {
        Friend friend=new Friend();
        friend.setFightStatus(0);
        FriendExample example=new FriendExample();
        friendMapper.updateByExampleSelective(friend,example);
    }

    @Override
    public void addFriend(Friend friend) {
        friend.setId(UUIDUtil.generateUUID());
        friendMapper.insertSelective(friend);
    }
}
