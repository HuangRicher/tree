package com.tongzhu.treehouse.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tongzhu.constant.GoodsConstant;
import com.tongzhu.common.Pager;
import com.tongzhu.treehouse.constant.*;
import com.tongzhu.treehouse.domain.FriendDetail;
import com.tongzhu.treehouse.domain.User;
import com.tongzhu.treehouse.domain.UserGoods;
import com.tongzhu.treehouse.domain.UserMessage;
import com.tongzhu.except.CheckException;
import com.tongzhu.treehouse.mapper.TreeHouseRoomMapper;
import com.tongzhu.treehouse.mapper.ext.TreeHouseRoomExtMapper;
import com.tongzhu.treehouse.mapper.vo.TreeHouseRoomCountVO;
import com.tongzhu.treehouse.mapper.vo.TreeHouseRoomDO;
import com.tongzhu.treehouse.model.TreeHouseRoom;
import com.tongzhu.treehouse.model.TreeHouseRoomExample;
import com.tongzhu.treehouse.model.UserWorkPosition;
import com.tongzhu.treehouse.redis.RedisService;
import com.tongzhu.treehouse.service.*;
import com.tongzhu.treehouse.service.vo.UserFriendDetailVO;
import com.tongzhu.treehouse.service.vo.UserVO;
import com.tongzhu.treehouse.service.vo.UserWorkerVO;
import com.tongzhu.util.DateComputeUtil;
import com.tongzhu.util.RandomUtil;
import com.tongzhu.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TreeHouseRoomServiceImpl implements TreeHouseRoomService {

    @Autowired
    private TreeHouseRoomMapper treeHouseRoomMapper;

    @Autowired
    private TreeHouseRoomExtMapper treeHouseRoomExtMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private TreeHouseService treeHouseService;

    @Autowired
    private UserWorkPositionService userWorkPositionService;

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private TreeHousePurchaseHistoryService treeHousePurchaseHistoryService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private TaskService taskService;


    @Override
    public int add(TreeHouseRoom userWorker) {
        return treeHouseRoomMapper.insertSelective(userWorker);
    }

    /**
     * 查找当前用户的宅友列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<UserWorkerVO> findWorkersByUserId(String userId) {
        TreeHouseRoomExample example = new TreeHouseRoomExample();
        example.createCriteria().andUserIdEqualTo(userId).andWorkerIdIsNotNull();
        example.setOrderByClause(" status_ asc");
        List<TreeHouseRoom> workerList = treeHouseRoomMapper.selectByExample(example);
        List<UserWorkerVO> userWorkerModelList = new ArrayList<>();
        for (TreeHouseRoom worker : workerList) {
            UserWorkerVO model = new UserWorkerVO();
            User user = userService.findByUserId(worker.getWorkerId());
            UserWorkPosition position = userWorkPositionService.findByUserIdAndWorkerId(worker.getUserId(), worker.getWorkerId());
            if (position != null) {
                model.setWorkTypeId(position.getWorkTypeId());
            }
            model.setGameFriend(worker.getIsGameFriend());
            model.setName(user.getName());
            model.setPortraitUrl(user.getPortraitUrl());
            model.setWXFriend(worker.getIsWxFriend());
            model.setUserId(worker.getWorkerId());
            model.setStatus(worker.getStatus());

            userWorkerModelList.add(model);
        }
        return userWorkerModelList;
    }

    @Override
    public UserWorkerVO findWorkerByUserIdAndWorkerId(String userId, String workerId) {
        TreeHouseRoomExample example = new TreeHouseRoomExample();
        example.createCriteria().andUserIdEqualTo(userId).andWorkerIdEqualTo(workerId);
        List<TreeHouseRoom> workerList = treeHouseRoomMapper.selectByExample(example);
        if (!workerList.isEmpty()) {
            TreeHouseRoom worker = workerList.get(0);
            UserWorkerVO model = new UserWorkerVO();
            User user = userService.findByUserId(worker.getWorkerId());
            UserWorkPosition position = userWorkPositionService.findByUserIdAndWorkerId(worker.getUserId(), worker.getWorkerId());
            if (position != null) {
                model.setWorkTypeId(position.getWorkTypeId());
            }
            model.setGameFriend(worker.getIsGameFriend());
            model.setName(user.getName());
            model.setPortraitUrl(user.getPortraitUrl());
            model.setWXFriend(worker.getIsWxFriend());
            model.setUserId(worker.getWorkerId());
            model.setStatus(worker.getStatus());
            return model;
        }
        return null;
    }

    /**
     * 空闲宅友列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<UserWorkerVO> findIdleWorkersByUserId(String userId) {
        TreeHouseRoomExample example = new TreeHouseRoomExample();
        example.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(TreeHouseRoomConstant.STATUS_IDLE).andWorkerIdIsNotNull();
        List<TreeHouseRoom> workerList = treeHouseRoomMapper.selectByExample(example);
        List<UserWorkerVO> userWorkerModelList = new ArrayList<>();
        for (TreeHouseRoom worker : workerList) {
            UserWorkerVO model = new UserWorkerVO();
            User user = userService.findByUserId(worker.getWorkerId());
            UserWorkPosition position = userWorkPositionService.findByUserIdAndWorkerId(worker.getUserId(), worker.getWorkerId());
            if (position != null) {
                model.setWorkTypeId(position.getWorkTypeId());
            }
            model.setGameFriend(worker.getIsGameFriend());
            model.setName(user.getName());
            model.setPortraitUrl(user.getPortraitUrl());
            model.setWXFriend(worker.getIsWxFriend());
            model.setUserId(worker.getWorkerId());

            userWorkerModelList.add(model);
        }
        return userWorkerModelList;
    }

    @Override
    public TreeHouseRoom checkWorkIsIdle(String userId, String workerId) {
        TreeHouseRoomExample example = new TreeHouseRoomExample();
        example.createCriteria().andUserIdEqualTo(userId).andWorkerIdEqualTo(workerId).andStatusEqualTo(TreeHouseRoomConstant.STATUS_IDLE);
        List<TreeHouseRoom> treeHouseRoomList = treeHouseRoomMapper.selectByExample(example);
        if (!treeHouseRoomList.isEmpty()) {
            return treeHouseRoomList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void makeWorkerIdle(String userId, String workerId) {
        TreeHouseRoomExample example = new TreeHouseRoomExample();
        example.createCriteria().andUserIdEqualTo(userId).andWorkerIdEqualTo(workerId);
        TreeHouseRoom room = new TreeHouseRoom();
        room.setStatus(TreeHouseRoomConstant.STATUS_IDLE);
        treeHouseRoomMapper.updateByExampleSelective(room, example);
    }


    /**
     * 查找xx用户宅友用户ID为xx的宅友
     *
     * @param userId
     * @param workerId
     * @return
     */
    @Override
    public TreeHouseRoom findWorkersByUserIdAndWorkerId(String userId, String workerId) {
        TreeHouseRoomExample example = new TreeHouseRoomExample();
        example.createCriteria().andUserIdEqualTo(userId).andWorkerIdEqualTo(workerId);
        List<TreeHouseRoom> treeHouseRoomList = treeHouseRoomMapper.selectByExample(example);
        if (!treeHouseRoomList.isEmpty()) {
            return treeHouseRoomList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public TreeHouseRoom checkWorkerIsWorking(String userId, String workerId) {
        TreeHouseRoomExample example = new TreeHouseRoomExample();
        example.createCriteria().andUserIdEqualTo(userId).andWorkerIdEqualTo(workerId).andStatusEqualTo(TreeHouseRoomConstant.STATUS_WORKING);
        List<TreeHouseRoom> treeHouseRoomList = treeHouseRoomMapper.selectByExample(example);
        if (!treeHouseRoomList.isEmpty()) {
            return treeHouseRoomList.get(0);
        } else {
            return null;
        }
    }


    /**
     * 给宅友派工
     *
     * @param userId
     * @param workerId
     */
    @Override
    public void updateByWorkIdForStatus(String userId, int workTypeId, String workerId) {
        TreeHouseRoom worker = new TreeHouseRoom();
        worker.setStatus(TreeHouseRoomConstant.STATUS_WORKING);
        worker.setWorkTypeId(workTypeId);
        TreeHouseRoomExample example = new TreeHouseRoomExample();
        example.createCriteria().andUserIdEqualTo(userId).andWorkerIdEqualTo(workerId);
        treeHouseRoomMapper.updateByExampleSelective(worker, example);
    }

    /**
     * 派工更换宅友
     *
     * @param userId
     * @param workerId
     */
    @Override
    public void updateByWorkIdForStatusChange(String userId, int workTypeId, String oldWorkerId, String workerId) {

        TreeHouseRoom oldWorker = new TreeHouseRoom();
        oldWorker.setStatus(TreeHouseRoomConstant.STATUS_IDLE);
        TreeHouseRoomExample exampleOld = new TreeHouseRoomExample();
        exampleOld.createCriteria().andUserIdEqualTo(userId).andWorkerIdEqualTo(oldWorkerId);
        treeHouseRoomMapper.updateByExampleSelective(oldWorker, exampleOld);

        TreeHouseRoom newWorker = new TreeHouseRoom();
        newWorker.setStatus(StatusConstant.USER_WORKER_ASSIGN);
        TreeHouseRoomExample exampleNew = new TreeHouseRoomExample();
        exampleNew.createCriteria().andUserIdEqualTo(userId).andWorkerIdEqualTo(workerId);
        treeHouseRoomMapper.updateByExampleSelective(newWorker, exampleNew);
    }

    @Override
    public List<TreeHouseRoomCountVO> countByUserId() {
        return treeHouseRoomExtMapper.countRoomsByUserId();
    }


    @Override
    public void deleteFinalRoom(String userId) {
        treeHouseService.deleteFinalRoom(userId);
    }

    /**
     * 查找树屋所拥有的房间
     *
     * @param id
     * @return
     */
    @Override
    public List<TreeHouseRoom> findRoomsByTreeHouseId(String id) {
        TreeHouseRoomExample example = new TreeHouseRoomExample();
        example.createCriteria().andTreeHouseIdEqualTo(id);
        example.setOrderByClause(" room_id DESC ");
        return treeHouseRoomMapper.selectByExample(example);
    }

    /**
     * 查找当前用户的宅友数量
     *
     * @param userId
     * @return
     */
    @Override
    public int countWorkersByUserId(String userId) {
        TreeHouseRoomExample example = new TreeHouseRoomExample();
        example.createCriteria().andUserIdEqualTo(userId).andWorkerIdIsNotNull();
        return treeHouseRoomMapper.countByExample(example);
    }

    /**
     * 查找当前用户拥有的宅友（按拥有的身价排序）
     *
     * @param userId
     * @return
     */
    @Override
    public List<TreeHouseRoomDO> findWorkersListByUserId(String userId) {
        return treeHouseRoomExtMapper.findWorkersListByUserIdOrderBySellingPrice(userId);
    }

    @Override
    public Page<TreeHouseRoom> findWorkersListByUserIdForPage(String userId, int pageNumber, int pageSize) {
        TreeHouseRoomExample example = new TreeHouseRoomExample();
        example.createCriteria().andUserIdEqualTo(userId).andWorkerIdIsNotNull();
        Page<TreeHouseRoom> page = PageHelper.startPage(pageNumber, pageSize);
        treeHouseRoomMapper.selectByExample(example);
        return page;
    }

    /**
     * 分页查找当前用户的宅友列表
     *
     * @param userId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public Pager<UserFriendDetailVO> findWorkersByPage(String userId, int pageNumber, int pageSize) {
        Page<TreeHouseRoom> page = this.findWorkersListByUserIdForPage(userId, pageNumber, pageSize);
        List<UserFriendDetailVO> userFriendDetailVOList = new ArrayList<>();

        return new Pager(pageNumber, pageSize, page.getPages(), page.getTotal(), userFriendDetailVOList);
    }

    /**
     * 保护宅友（消耗钻石）
     *
     * @param userId
     * @param workerId
     * @param hours
     * @return
     */
    @Transactional
    @Override
    public UserGoods protectWorker(String userId, String workerId, int hours) {
        int jewelCount = 0;
        if (hours == WorkerConstant.PROTECT_HOUR_6)
            jewelCount = WorkerConstant.PROTECT_6_CONSUME;
        if (hours == WorkerConstant.PROTECT_HOUR_24)
            jewelCount = WorkerConstant.PROTECT_24_CONSUME;
        if (hours == WorkerConstant.PROTECT_HOUR_72)
            jewelCount = WorkerConstant.PROTECT_72_CONSUME;
        LocalDateTime current = LocalDateTime.now();
        LocalDateTime afterCurrent = current.plusHours(hours);
        TreeHouseRoom room = new TreeHouseRoom();
        room.setProtectStartDate(DateComputeUtil.localDateTimeToDate(current));
        room.setProtectEndDate(DateComputeUtil.localDateTimeToDate(afterCurrent));
        room.setStatus(StatusConstant.USER_WORKER_PROTECT);

        TreeHouseRoomExample example = new TreeHouseRoomExample();
        example.createCriteria().andUserIdEqualTo(userId).andWorkerIdEqualTo(workerId);
        treeHouseRoomMapper.updateByExampleSelective(room, example);

        List<UserGoods> goodsList = new ArrayList<>();
        UserGoods map = new UserGoods();
        map.setGoodsId(GoodsConstant.GOODS_JEWEL);
        map.setAmount(jewelCount);
        goodsList.add(map);
        List<UserGoods> goods = userGoodsService.subUserGoods(userId, goodsList);
        if (goods != null && !goods.isEmpty())
            return goods.get(0);
        else
            return null;
    }

    /**
     * 购买宅友   --消耗宅友同等身价的金币
     *
     * @param treeHouseRoom
     */
    @Transactional
    @Override
    public UserGoods updateForBuyWorker(String userId, TreeHouseRoom treeHouseRoom) {
        User worker = userService.findByUserId(treeHouseRoom.getWorkerId());
        //减少当前用户金币数量
        List<UserGoods> goodsList1 = new ArrayList<>();
        UserGoods map1 = new UserGoods();
        map1.setGoodsId(GoodsConstant.GOODS_MONEY);
        map1.setAmount(worker.getSellingPrice());
        goodsList1.add(map1);
        List<UserGoods> userGoods = userGoodsService.subUserGoods(userId, goodsList1);
        TreeHouseRoom room = this.checkIsWorkerByWorkerId(treeHouseRoom.getWorkerId());
        if (room != null && room.getProtectEndDate() != null) {
            Instant instant = room.getProtectEndDate().toInstant();
            ZoneId zone = ZoneId.systemDefault();
            LocalDateTime loginDateTime = LocalDateTime.ofInstant(instant, zone);
            LocalDateTime currentDateTime = LocalDateTime.now();
            Duration duration = Duration.between(loginDateTime, currentDateTime);
            if (duration.toMillis() <= 0)
                throw new CheckException("该宅友已经被买走了！");
        }
        if (room != null) {
            //给宅友主人增加金币
            List<UserGoods> goodsList = new ArrayList<>();
            UserGoods map = new UserGoods();
            map.setGoodsId(GoodsConstant.GOODS_MONEY);
            map.setAmount(worker.getSellingPrice());
            goodsList.add(map);
            userGoodsService.addUserGoods(room.getUserId(), goodsList);

            //记录原工位产出
            if (room.getStatus().equals(StatusConstant.USER_WORKER_ASSIGN)) {
                userWorkPositionService.updateByWorkerIdForLeave(room.getWorkerId());
            }

            //清除原主人的宅友的数据
            room.setWorkerId(null);
            room.setWorkTypeId(null);
            room.setStatus(StatusConstant.USER_WORKER_IDLE);
            room.setProtectStartDate(null);
            room.setProtectEndDate(null);
            room.setReducePriceDate(null);
            room.setIsGameFriend(false);
            room.setIsWxFriend(false);
            treeHouseRoomMapper.updateByPrimaryKey(room);


            //给宅友主人发消息 给宅友自己发消息 给购买人发消息
            if (StringUtils.isNotBlank(room.getUserId())) {

                User user = userService.findByUserId(userId);
                UserMessage userMessage = new UserMessage();
                userMessage.setCreateDate(new Date());
                userMessage.setId(UUIDUtil.generateUUID());
                userMessage.setStatus(MessageConstant.STATUS_REMOVE);
                userMessage.setSenderId(userId);  // 发送者id
                userMessage.setReceiverId(room.getUserId());  // 接受者id
                userMessage.setType(MessageConstant.TYPE_WORKER_BUY);
                userMessage.setTitle("宅友被买走了");
                userMessage.setMessageBody("<color=#8AE1F9>   您的宅友</c><color=#FFC360>" + worker.getName() +
                        "</color><color=#8AE1F9>于 " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "被</color><color=#FFC360> " + user.getName() +
                        "</color><color=#8AE1F9>以</c><color=#EBF31F>" + worker.getSellingPrice() +
                        "</color><color=#8AE1F9>金币的价格买走了</color>");
                UserMessage userMessageTO = new UserMessage();
                userMessageTO.setCreateDate(new Date());
                userMessageTO.setId(UUIDUtil.generateUUID());
                userMessageTO.setStatus(MessageConstant.STATUS_REMOVE);
                userMessageTO.setSenderId(userId);  // 发送者id
                userMessageTO.setReceiverId(worker.getUserId());  // 接受者id
                userMessageTO.setType(MessageConstant.TYPE_HASMASTER_BUY);
                userMessageTO.setTitle("换主人了");
                userMessageTO.setMessageBody("<color=#8AE1F9>" +
                        "  您于</c><color=#FFC360>" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "被</color><color=#8AE1F9>" + user.getName() + "</color><color=#8AE1F9>以</c>" +
                        " <color=#EBF31F>" + worker.getSellingPrice() + "</color><color=#8AE1F9>金币的价格买走了</color>");
                userService.addUserMessage(userMessageTO);
                userService.addUserMessage(userMessage);
            }
        } else {
            //自由身，给宅友自己增加金币
            List<UserGoods> goodsList = new ArrayList<>();
            UserGoods map = new UserGoods();
            map.setGoodsId(GoodsConstant.GOODS_MONEY);
            map.setAmount(worker.getSellingPrice());
            goodsList.add(map);
            userGoodsService.addUserGoods(worker.getUserId(), goodsList);
//
//            //给宅友发消息 给购买人发消息  1-我是自由身，我被别人买走了，成别人的宅友了
            User user = userService.findByUserId(userId);
            UserMessage userMessage = new UserMessage();
            userMessage.setCreateDate(new Date());
            userMessage.setId(UUIDUtil.generateUUID());
            userMessage.setStatus(MessageConstant.STATUS_REMOVE);
            userMessage.setSenderId(userId);  // 发送者id
            userMessage.setReceiverId(worker.getUserId());  // 接受者id
            userMessage.setType(MessageConstant.TYPE_FREE_BUY);
            userMessage.setTitle("有主人了");
            userMessage.setMessageBody("<color=#8AE1F9>  您于</c><color=#FFC360>" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "被</color><color=#8AE1F9>" + user.getName() + "</color><color=#8AE1F9>以</c>" +
                    " <color=#EBF31F>" + worker.getSellingPrice() + "</color><color=#8AE1F9>金币的价格买走了</color>");
            userService.addUserMessage(userMessage);

        }


        synchronized (userId.intern()) {
            TreeHouseRoomExample example = new TreeHouseRoomExample();
            example.createCriteria().andUserIdEqualTo(userId).andWorkerIdIsNull();
            example.setOrderByClause(" room_id ASC");
            List<TreeHouseRoom> rooms = treeHouseRoomMapper.selectByExample(example);
            if (!rooms.isEmpty()) {
                if (!userId.equals(treeHouseRoom.getWorkerId())) {
                    treeHouseRoom.setId(rooms.get(0).getId());
                    treeHouseRoomMapper.updateByPrimaryKeySelective(treeHouseRoom);
                }
                //每次被转让会增加宅友身价
                int afterSellingPrice = worker.getSellingPrice();
                if (redisService.get(worker.getUserId() + "_buyCount") == null) {
                    afterSellingPrice = (int) Math.ceil(afterSellingPrice * Math.pow(1d, 1.05d));
                    redisService.set(worker.getUserId() + "_buyCount", 1);
                } else {
                    int byCount = (int) redisService.get(worker.getUserId() + "_buyCount");
                    afterSellingPrice = (int) Math.ceil(afterSellingPrice * Math.pow(Double.valueOf(byCount + ""), 1.05d));
                }
                userService.updateUserSellingPrice(worker.getUserId(), afterSellingPrice > 30000 ? 30000 : afterSellingPrice);
            } else {
                throw new CheckException("没有足够的房间存放宅友！");
            }
        }
        if (!userGoods.isEmpty()) {
            taskService.taskProgress(TreeHouseRoomConstant.TASK_TYPE_ZR, 1, userId);
            return userGoods.get(0);
        } else
            return null;
    }

    /**
     * 更新降低宅友身价操作的时间
     *
     * @param userId
     * @param workerId
     */
    @Override
    public void updateReducePriceDate(String userId, String workerId) {
        TreeHouseRoom room = new TreeHouseRoom();
        room.setReducePriceDate(new Date());
        TreeHouseRoomExample example = new TreeHouseRoomExample();
        example.createCriteria().andUserIdEqualTo(userId).andWorkerIdEqualTo(workerId);
        treeHouseRoomMapper.updateByExampleSelective(room, example);
    }

    /**
     * 释放宅友
     *
     * @param userId
     * @param workerId
     */
    @Transactional
    @Override
    public void releaseWorker(String userId, String workerId) {
        UserWorkPosition position = userWorkPositionService.findByUserIdAndWorkerId(userId, workerId);
        int lastTime = 0;
        if (position != null && position.getStartDate() != null) {
            Instant instant = position.getStartDate().toInstant();
            ZoneId zone = ZoneId.systemDefault();
            LocalDateTime loginDateTime = LocalDateTime.ofInstant(instant, zone);
            LocalDateTime currentDateTime = LocalDateTime.now();
            Duration duration = Duration.between(currentDateTime, loginDateTime);
            lastTime = (int) duration.toMinutes() * 60;
        }
        treeHouseRoomExtMapper.updateForReleaseWorker(userId, workerId);
        userWorkPositionService.updateForReleaseWorker(userId, workerId, lastTime, new Date());
    }

    /**
     * 购买宅友推荐列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<UserVO> recommendToBuyList(String userId) {
        List<UserVO> list = new ArrayList<>();
        UserGoods goods = userGoodsService.getGoodsCount(userId, GoodsConstant.GOODS_MONEY);
        User user = userService.findByUserId(userId);
        if (goods != null && user != null) {
            int count = RandomUtil.random(3, 5);
            List<User> friendModels = userService.selectFriendsForBuyByRand(userId, StatusConstant.FRIEND_CONFIRMED_AGREE,
                    WorkerConstant.MAX_EXCHANGE_COUNT, goods.getAmount(), count);

            for (User us : friendModels) {
                TreeHouseRoom room = this.checkIsWorkerByWorkerId(us.getUserId());
                UserVO userVO = new UserVO();
                userVO.setUserId(us.getUserId());
                userVO.setUserName(us.getName());
                userVO.setPortraitUrl(us.getPortraitUrl());
                userVO.setSellingPrice(us.getSellingPrice());
                userVO.setGameFriend(true);
                if (room == null) {
                    userVO.setStatus(TreeHouseRoomConstant.WORK_STATUS_IDLE);
                } else {
                    userVO.setStatus(TreeHouseRoomConstant.WORK_STATUS_WORKING);
                }
                list.add(userVO);
            }
            //如果符合条件的好友数量不足则用陌生玩家代替，如果与玩家身价相近的玩家不足则将身价浮动范围逐步扩大（每次10%），直到补足数量为止
            float in = 1 - 0.1f;
            float inm = 1 + 0.1f;
            int i = 1;
            int min = (int) (user.getSellingPrice() * in);
            int max = (int) (user.getSellingPrice() * inm);
            int userCount = userService.countAllUser();
            while (true) {
                if (goods.getAmount() < min && (in - 0.1 * i) > 0) {
                    min = (int) (user.getSellingPrice() * (in - 0.1 * i));
                    i++;
                    continue;
                }
                if (goods.getAmount() >= min && goods.getAmount() <= max) {
                    max = goods.getAmount();
                }
                List<User> userList = userService.selectUserForBuyByRand(min, max, userId,
                        WorkerConstant.MAX_EXCHANGE_COUNT, WorkerConstant.RECOMMEND_COUNT - friendModels.size());
                if (userList != null && userList.size() < WorkerConstant.RECOMMEND_COUNT - friendModels.size() && (in - 0.1 * i) > 0) {
                    min = (int) (user.getSellingPrice() * (in - 0.1 * i));
                    max = (int) (user.getSellingPrice() * (inm + 0.1 * i));
                    i++;
                    if (userList.size() < userCount) {
                        continue;
                    }
                }
                if (userList != null) {
                    for (User us : userList) {
                        TreeHouseRoom room = this.checkIsWorkerByWorkerId(us.getUserId());
                        UserVO userVO = new UserVO();
                        userVO.setUserId(us.getUserId());
                        userVO.setUserName(us.getName());
                        userVO.setPortraitUrl(us.getPortraitUrl());
                        userVO.setSellingPrice(us.getSellingPrice());
                        userVO.setGameFriend(false);
                        if (room == null) {
                            userVO.setStatus(TreeHouseRoomConstant.WORK_STATUS_IDLE);
                        } else {
                            userVO.setStatus(TreeHouseRoomConstant.WORK_STATUS_WORKING);
                        }
                        list.add(userVO);
                    }
                }
                break;
            }

        }
        return list;
    }

    @Override
    public void updateIsGameFriend(String userId, String friendId, Boolean value) {
        TreeHouseRoomExample example = new TreeHouseRoomExample();
        TreeHouseRoomExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId).andWorkerIdEqualTo(friendId);

        TreeHouseRoomExample.Criteria criteria1 = example.createCriteria();
        criteria1.andUserIdEqualTo(friendId).andWorkerIdEqualTo(userId);
        example.or(criteria1);

        TreeHouseRoom room = new TreeHouseRoom();
        room.setIsGameFriend(value);

        treeHouseRoomMapper.updateByExampleSelective(room, example);
    }


    /**
     * 查找当前用户的宅友上限（树屋房间总数）
     *
     * @param userId
     * @return
     */
    @Override
    public int countRoomsByUserId(String userId) {
        TreeHouseRoomExample example = new TreeHouseRoomExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return treeHouseRoomMapper.countByExample(example);
    }

    /**
     * 判断xx用户是否为他人的宅友
     *
     * @param userId
     * @return
     */
    @Override
    public TreeHouseRoom checkIsWorkerByWorkerId(String userId) {
        TreeHouseRoomExample example = new TreeHouseRoomExample();
        example.createCriteria().andWorkerIdEqualTo(userId);
        List<TreeHouseRoom> rooms = treeHouseRoomMapper.selectByExample(example);
        if (!rooms.isEmpty()) {
            return rooms.get(0);
        }
        return null;
    }

    /**
     * 查找当前用户的宅友列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<FriendDetail> findWorkersList(String userId) {
        List<TreeHouseRoomDO> workerList = this.findWorkersListByUserId(userId);
        List<FriendDetail> userFriendDetailVOList = new ArrayList<>();
        dealWorkerList(workerList, userFriendDetailVOList);

        return userFriendDetailVOList;
    }

    @Override
    public List<FriendDetail> findFriendWorkersListWithSelf(String userId, String friendUserId) {
        List<TreeHouseRoomDO> workerList = this.findWorkersListByUserId(friendUserId);
        //第一条记录显示当前用户
        TreeHouseRoom worker = this.findWorkersByUserIdAndWorkerId(userId, friendUserId);
        List<FriendDetail> userFriendDetailVOList = new ArrayList<>();
        if (worker == null) {
            User user = userService.findByUserId(friendUserId);
            FriendDetail userDetail = new FriendDetail();
            userDetail.setSellingPrice(user.getSellingPrice());
            userDetail.setUserId(user.getUserId());
            userDetail.setPortraitUrl(user.getPortraitUrl());
            userDetail.setUserName(user.getName());
            userDetail.setIsGameFriend(false);
            //在线状态  离线时显示上次登录时间，不超过一天的显示具体小时数（不足一小时显示“刚刚”），超过1天的显示具体天数，超过七天的显示七天前
            String onlineState = "";
            if (user.getStatus() != null && user.getStatus() == StatusConstant.USER_ONLINE) {
                onlineState = "在线";
            }
            if (user.getStatus() != null && user.getStatus() == StatusConstant.USER_OFFLINE && user.getLoginDate() != null) {
                onlineState = DateComputeUtil.compareOfflineTime(user.getLoginDate());
            }
            userDetail.setOnlineState(onlineState);

            userDetail.setCanReduceSellingPrice(WorkerConstant.CAN_REDUCE_SELLING_PRICE);
            TreeHouseRoom room = this.checkIsWorkerByWorkerId(friendUserId);
            if (room != null) {
                if (room.getReducePriceDate() != null &&
                        DateComputeUtil.DateToLocalDate(room.getReducePriceDate()).equals(DateComputeUtil.DateToLocalDate(new Date())))
                    userDetail.setCanReduceSellingPrice(WorkerConstant.CAN_NOT_REDUCE_SELLING_PRICE);
                if (room.getProtectEndDate() != null) {
                    userDetail.setProtectTimeDetail(DateComputeUtil.getRemainderTimeOfProtect(room.getProtectEndDate()));
                }
                if (room.getStatus() == StatusConstant.USER_WORKER_ASSIGN) {
                    userDetail.setStatus(StatusConstant.USER_WORKER_ASSIGN);
                } else {
                    userDetail.setStatus(StatusConstant.USER_WORKER_IDLE);
                }
            } else {
                userDetail.setStatus(StatusConstant.USER_WORKER_IDLE);
            }
            userFriendDetailVOList.add(userDetail);
        }
        dealWorkerList(workerList, userFriendDetailVOList);

        return userFriendDetailVOList;
    }

    private void dealWorkerList(List<TreeHouseRoomDO> workerList, List<FriendDetail> userFriendDetailVOList) {
        for (TreeHouseRoomDO worker : workerList) {
            FriendDetail userDetail = new FriendDetail();
            userDetail.setSellingPrice(worker.getSellingPrice());
            userDetail.setUserId(worker.getWorkerId());
            userDetail.setPortraitUrl(worker.getPortraitUrl());
            userDetail.setUserName(worker.getUserName());
            userDetail.setIsGameFriend(worker.getIsGameFriend());
            //在线状态  离线时显示上次登录时间，不超过一天的显示具体小时数（不足一小时显示“刚刚”），超过1天的显示具体天数，超过七天的显示七天前
            String onlineState = "";
            if (worker.getUserStatus() != null && worker.getUserStatus() == StatusConstant.USER_ONLINE) {
                onlineState = "在线";
            }
            if (worker.getUserStatus() != null && worker.getUserStatus() == StatusConstant.USER_OFFLINE && worker.getLoginDate() != null) {
                onlineState = DateComputeUtil.compareOfflineTime(worker.getLoginDate());
            }
            userDetail.setOnlineState(onlineState);

            userDetail.setCanReduceSellingPrice(WorkerConstant.CAN_REDUCE_SELLING_PRICE);
            if (worker.getReducePriceDate() != null &&
                    DateComputeUtil.DateToLocalDate(worker.getReducePriceDate()).equals(DateComputeUtil.DateToLocalDate(new Date())))
                userDetail.setCanReduceSellingPrice(WorkerConstant.CAN_NOT_REDUCE_SELLING_PRICE);
            if (worker.getProtectEndDate() != null) {
                userDetail.setProtectTimeDetail(DateComputeUtil.getRemainderTimeOfProtect(worker.getProtectEndDate()));
            }

            if (worker.getStatus() == StatusConstant.USER_WORKER_ASSIGN) {
                userDetail.setStatus(StatusConstant.USER_WORKER_ASSIGN);
            } else {
                userDetail.setStatus(StatusConstant.USER_WORKER_IDLE);
            }
            userFriendDetailVOList.add(userDetail);
        }
    }

}
