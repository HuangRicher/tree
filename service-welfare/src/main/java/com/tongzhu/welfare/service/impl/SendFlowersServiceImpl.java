package com.tongzhu.welfare.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.tongzhu.welfare.constant.BuildingConstant;
import com.tongzhu.welfare.constant.MallConstant;
import com.tongzhu.welfare.constant.MarryConstant;
import com.tongzhu.welfare.constant.SendFlowersConstant;
import com.tongzhu.welfare.domain.User;
import com.tongzhu.welfare.domain.UserGoods;
import com.tongzhu.welfare.domain.UserRole;
import com.tongzhu.welfare.mapper.MallMapper;
import com.tongzhu.welfare.mapper.SendFlowersMapper;
import com.tongzhu.welfare.model.Mall;
import com.tongzhu.welfare.model.MallExample;
import com.tongzhu.welfare.model.SendFlowers;
import com.tongzhu.welfare.service.FriendService;
import com.tongzhu.welfare.service.MarryService;
import com.tongzhu.welfare.service.MessageService;
import com.tongzhu.welfare.service.SendFlowersService;
import com.tongzhu.welfare.service.TaskService;
import com.tongzhu.welfare.service.UserGoodsService;
import com.tongzhu.welfare.service.UserRoleService;
import com.tongzhu.welfare.service.UserService;
import com.tongzhu.welfare.vo.ReceiveGoldVo;

@Service
public class SendFlowersServiceImpl implements SendFlowersService {

	@Autowired
	private SendFlowersMapper sendFlowersMapper;
	@Autowired
	private FriendService friendService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private UserGoodsService userGoodsService;
	@Autowired
	private UserService userService;
	@Autowired
	private MallMapper  mallMapper;
	@Autowired
	private MarryService marryService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private TaskService taskService;
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int sendFlowersById(String userId, String receiveId, int goodsId,boolean b) {
		//增加送花记录
		SendFlowers sendFlowers = new SendFlowers();
		sendFlowers.setCreatedTime(new Date(System.currentTimeMillis()));
		if(b){
			sendFlowers.setIsFriend(SendFlowersConstant.FLOWERS_FRIEND_FALSE);
		}else{
			sendFlowers.setIsFriend(SendFlowersConstant.FLOWERS_FRIEND_TRUE);
		}
		sendFlowers.setReceiveId(receiveId);
		sendFlowers.setSengId(userId);
		int num = 1;
		if(goodsId == 11901){
			num = SendFlowersConstant.FLOWERS_NUM_ONE;
		}else if(goodsId == 11902){
			num = SendFlowersConstant.FLOWERS_NUM_NINE;
		}else if(goodsId == 11903){
			num = SendFlowersConstant.FLOWERS_NUM_NINENINE;
		}else{
			num = SendFlowersConstant.FLOWERS_NUM_NINENINENINE;
		}
		sendFlowers.setNum(num);
		sendFlowersMapper.insert(sendFlowers);
		
		UserGoods userGoods = userGoodsService.getGoodsCount(userId, String.valueOf(goodsId));
		if(userGoods.getAmount() < 1){
			return -1;
		}
		
		List<UserGoods> goodsList = new ArrayList<UserGoods>();
		UserGoods userGoods2 = new UserGoods();
		userGoods2.setId(userGoods.getId());
		userGoods2.setAmount(1);
		userGoods2.setGoodsId(String.valueOf(goodsId));
		goodsList.add(userGoods2);
		userGoodsService.subUserGoods(userId, goodsList);
		
		UserRole userRole = userRoleService.getUserRoleByUserId(userId);
		String otherId = userRole.getSpouse();
		if(receiveId.equals(otherId)){
			//TODO 增加情缘值
			marryService.addLoveByUserId(userId, num*5);
		}
		int i = 0;
		//判断是否好友，进而增加亲密度
		if(b){
			//增加亲密度
			friendService.updateIntimacy(userId, receiveId, num);
			i = num;
		}
		//TODO 增加魅力值
		userRoleService.addcharmNum(receiveId, num);
		taskService.taskProgress(SendFlowersConstant.TASK_TYPE_SH, num, userId);
		if( num == 999 ){ 
			//如果花朵的数量为999，将触发全局广播
			User userDetail = userService.findByUserId(userId);
			User userDetail2 = userService.findByUserId(receiveId);
			StringBuffer massage = new StringBuffer();
			massage.append(userDetail.getName()).append("向").append(userDetail2.getName()).append("赠送999朵玫瑰花，太浪漫了！！！");
			messageService.sendBroadcastToAllBody(massage.toString());
		}
		return i;
	}

    @Override
    public ReceiveGoldVo flowersPay(String userId, int goodsId, int num) throws Exception {
        MallExample mallExample = new MallExample();
        mallExample.createCriteria().andStatusEqualTo(MallConstant.MALL_STATUS_GOODSON).andGoodsIdEqualTo(goodsId);
        mallExample.setOrderByClause(" 'publish_time' desc");
        List<Mall> list = mallMapper.selectByExample(mallExample);
        if (list.size() > 0) {
            Mall mall = list.get(0);
            BigDecimal price = mall.getPrice();
            int total = (int) (price.intValue() * num);
            UserGoods goldCount = userGoodsService.getGoodsCount(userId, "50002");
            if (goldCount.getAmount() > total) {
                //先扣钱，然后增加背包物品
                UserGoods subuserGoods = new UserGoods();
                subuserGoods.setAmount(total);
                subuserGoods.setGoodsId(MarryConstant.MARRY_CONSUMABLES_DIAMOND);
                List<UserGoods> goodsList = new ArrayList<>();
                goodsList.add(subuserGoods);
                userGoodsService.subUserGoods(userId, goodsList);

                UserGoods adduserGoods = new UserGoods();
                adduserGoods.setAmount(num);
                adduserGoods.setGoodsId(String.valueOf(goodsId));
                List<UserGoods> addgoodsList = new ArrayList<>();
                addgoodsList.add(adduserGoods);
                userGoodsService.addUserGoods(userId, addgoodsList);

                ReceiveGoldVo receiveGoldVo = new ReceiveGoldVo();
                receiveGoldVo.setAmount(goldCount.getAmount() - total);
                receiveGoldVo.setGoodsId(50002);
                receiveGoldVo.setId("50002");
                receiveGoldVo.setType(BuildingConstant.Prop);
                return receiveGoldVo;
            } else {
                throw new Exception("钻石余额不足已购买该物品!");
            }
        }
        ReceiveGoldVo receiveGoldVo = new ReceiveGoldVo();
        return receiveGoldVo;
    }

}
