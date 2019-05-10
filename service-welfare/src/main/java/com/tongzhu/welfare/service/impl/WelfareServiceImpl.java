package com.tongzhu.welfare.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.tongzhu.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.tongzhu.except.CheckException;
import com.tongzhu.welfare.constant.BuildingConstant;
import com.tongzhu.welfare.constant.WelfareConstant;
import com.tongzhu.welfare.domain.User;
import com.tongzhu.welfare.domain.UserGoods;
import com.tongzhu.welfare.mapper.LoginLogMapper;
import com.tongzhu.welfare.mapper.WelfareBoxSettingMapper;
import com.tongzhu.welfare.mapper.WelfareDaySettingMapper;
import com.tongzhu.welfare.mapper.WelfareMonthSettingMapper;
import com.tongzhu.welfare.mapper.WelfareReceiveDayLogMapper;
import com.tongzhu.welfare.mapper.WelfareReceiveMonthLogMapper;
import com.tongzhu.welfare.model.LoginLog;
import com.tongzhu.welfare.model.LoginLogExample;
import com.tongzhu.welfare.model.WelfareBoxSetting;
import com.tongzhu.welfare.model.WelfareBoxSettingExample;
import com.tongzhu.welfare.model.WelfareDaySetting;
import com.tongzhu.welfare.model.WelfareDaySettingExample;
import com.tongzhu.welfare.model.WelfareMonthSetting;
import com.tongzhu.welfare.model.WelfareMonthSettingExample;
import com.tongzhu.welfare.model.WelfareReceiveDayLog;
import com.tongzhu.welfare.model.WelfareReceiveDayLogExample;
import com.tongzhu.welfare.model.WelfareReceiveMonthLog;
import com.tongzhu.welfare.model.WelfareReceiveMonthLogExample;
import com.tongzhu.welfare.service.UserGoodsService;
import com.tongzhu.welfare.service.UserService;
import com.tongzhu.welfare.service.WelfareService;
import com.tongzhu.welfare.vo.ReceiveGoldVo;
import com.tongzhu.welfare.vo.WelfareVo;

@Service
public class WelfareServiceImpl implements WelfareService {

    @Autowired
    WelfareDaySettingMapper welfareDaySettingMapper;
    @Autowired
    WelfareReceiveDayLogMapper welfareReceiveDayLogMapper;
    @Autowired
    WelfareMonthSettingMapper welfareMonthSettingMapper;
    @Autowired
    WelfareReceiveMonthLogMapper welfareReceiveMonthLogMapper;
    @Autowired
    WelfareBoxSettingMapper welfareBoxSettingMapper;
    @Autowired
    LoginLogMapper loginLogMapper;
    @Autowired
    UserGoodsService userGoodsService;
    @Autowired
    UserService userService;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static String formatDate(Date date) throws ParseException {
        synchronized (sdf) {
            return sdf.format(date);
        }
    }

    @Override
    public List<WelfareVo> getRewardsListByUserId(String userId, User userDetail) {
        WelfareDaySettingExample example = new WelfareDaySettingExample();
        //从day奖励设置表中取出奖励列表
        List<WelfareDaySetting> welfareDaySettings = welfareDaySettingMapper.selectByExample(example);
        List<WelfareVo> list2 = new ArrayList<>();
        for (WelfareDaySetting welfareDaySetting : welfareDaySettings) {
            WelfareReceiveDayLogExample welfareReceiveDayLogExample = new WelfareReceiveDayLogExample();
            welfareReceiveDayLogExample.createCriteria().andUserIdEqualTo(userId).andWeekEqualTo(DateUtil.getWeek()).andYearEqualTo(DateUtil.getYear()).andRewardsDayEqualTo(welfareDaySetting.getDateNum());
            List<WelfareReceiveDayLog> welfareReceiveDayLogs = welfareReceiveDayLogMapper.selectByExample(welfareReceiveDayLogExample);
            JSONObject json = (JSONObject) JSON.parse(welfareDaySetting.getRewardsContent());
            String amount = json.getString("amount");//奖励的数量
            String goodsId = json.getString("goodsId");
            WelfareVo welfareVo = new WelfareVo();
            welfareVo.setUserId(userId);
            welfareVo.setWelfareId((welfareDaySetting.getId()));
            welfareVo.setDateNum((welfareDaySetting.getDateNum()));
            welfareVo.setRewardsType((welfareDaySetting.getDateNum()));
            welfareVo.setRewardsNum(Integer.valueOf(amount));
            welfareVo.setGoodsId(Integer.valueOf(goodsId));
            if (welfareReceiveDayLogs.size() > 0) {
                WelfareReceiveDayLog welfareReceiveDayLog = welfareReceiveDayLogs.get(0);
                if (welfareReceiveDayLog.getReceiveAward() == 0) {
                    welfareVo.setIsReceive(WelfareConstant.WELFARE_REWARDS_RECEIVE_NULL);
                } else {
                    welfareVo.setIsReceive(WelfareConstant.WELFARE_REWARDS_RECEIVE_ALREADY);
                }
            } else {
                welfareVo.setIsReceive(WelfareConstant.WELFARE_REWARDS_RECEIVE_NOT);
            }
            list2.add(welfareVo);
        }
        return list2;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ReceiveGoldVo receiveRewardsByUserId(String userId, int dateNum) {
        WelfareReceiveDayLogExample welfareReceiveDayLogExample = new WelfareReceiveDayLogExample();
        welfareReceiveDayLogExample.createCriteria().andUserIdEqualTo(userId).andYearEqualTo(DateUtil.getYear()).andWeekEqualTo(DateUtil.getWeek()).andRewardsDayEqualTo(dateNum);
        List<WelfareReceiveDayLog> welfareReceiveDayLogs = welfareReceiveDayLogMapper.selectByExample(welfareReceiveDayLogExample);
        if (welfareReceiveDayLogs.size() > 0) {
            WelfareReceiveDayLog welfareReceiveDayLog = welfareReceiveDayLogs.get(0);
            if (welfareReceiveDayLog.getReceiveAward() == 0) {
                WelfareDaySettingExample example = new WelfareDaySettingExample();
                example.createCriteria().andDateNumEqualTo(dateNum);
                List<WelfareDaySetting> list = welfareDaySettingMapper.selectByExample(example);
                WelfareDaySetting welfareDaySetting;
                if (list.size() > 0) {
                    welfareDaySetting = list.get(0);
                } else {
                    throw new CheckException("数据异常");
                }
                String jsonStr = welfareDaySetting.getRewardsContent();
                JSONObject json = JSON.parseObject(jsonStr);
                int goodsId = json.getIntValue("goodsId");
                int num = json.getIntValue("amount");
                addUserMoney(userId, goodsId, num);
                welfareReceiveDayLog.setReceiveAward(1);
                welfareReceiveDayLogMapper.updateByPrimaryKeySelective(welfareReceiveDayLog);
                ReceiveGoldVo receiveGoldVo = new ReceiveGoldVo();
                receiveGoldVo.setAmount(num);
                receiveGoldVo.setGoodsId(goodsId);
                receiveGoldVo.setId(String.valueOf(goodsId));
                receiveGoldVo.setType(BuildingConstant.Prop);
                return receiveGoldVo;
            } else {
                throw new CheckException("请不要重复领取");
            }
        } else {
            throw new CheckException("还未登录哦");
        }
//        WelfareReceiveDayLogExample example2 = new WelfareReceiveDayLogExample();
//        WelfareDaySettingExample example = new WelfareDaySettingExample();
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date(System.currentTimeMillis()));
//        cal.add(Calendar.DAY_OF_YEAR, -7);
//        Date beforDay = cal.getTime();
//        example2.createCriteria().andUserIdEqualTo(userId).andReceiveTimeGreaterThanOrEqualTo(beforDay);
//        example2.setOrderByClause("'receive_time' desc");
//        List<WelfareReceiveDayLog> dayLoglist = welfareReceiveDayLogMapper.selectByExample(example2);
//        List<Integer> list3 = new ArrayList<>();
//        for (WelfareReceiveDayLog receiveDayLog : dayLoglist) {
//            int dateNum2 = receiveDayLog.getRewardsDay();
//            list3.add(dateNum2);
//        }
//        example.createCriteria().andDateNumEqualTo(dateNum);
//        List<WelfareDaySetting> list = welfareDaySettingMapper.selectByExample(example);
//        WelfareDaySetting welfareDaySetting = new WelfareDaySetting();
//        if (list.size() > 0) {
//            welfareDaySetting = list.get(0);
//        }
//
//        if (!list3.contains(dateNum)) {//判断是否重复领取，不重复的情况下新增一条领取记录
//            WelfareReceiveDayLog welfareReceiveDayLog = new WelfareReceiveDayLog();
//            welfareReceiveDayLog.setReceiveTime(new Date(System.currentTimeMillis()));
//            welfareReceiveDayLog.setRewardsDay(dateNum);
//            welfareReceiveDayLog.setUserId(userId);
//            welfareReceiveDayLog.setRewardsType(welfareDaySetting.getRewardsType());
//            welfareReceiveDayLog.setRewardsContent(welfareDaySetting.getRewardsContent());
//            welfareReceiveDayLogMapper.insert(welfareReceiveDayLog);
//
//            String jsonStr = welfareDaySetting.getRewardsContent();
//            JSONObject json = JSON.parseObject(jsonStr);
//            int goodsId = json.getIntValue("goodsId");
//            int num = json.getIntValue("amount");
//
//            // 往指定用户的背包添加物品（开箱）
//            addUserMoney(userId, goodsId, num);
//            ReceiveGoldVo receiveGoldVo = new ReceiveGoldVo();
//            receiveGoldVo.setAmount(num);
//            receiveGoldVo.setGoodsId(goodsId);
//            receiveGoldVo.setId(String.valueOf(goodsId));
//            receiveGoldVo.setType(BuildingConstant.Prop);
//            return receiveGoldVo;
//        } else {
//            throw new CheckException("请不要重复领取");
//        }
    }

    public void addUserMoney(String userId, Integer goodsId, Integer amount) {
        UserGoods userGoods = new UserGoods();
        userGoods.setAmount(amount);
        userGoods.setGoodsId(String.valueOf(goodsId));
        List<UserGoods> goodsList = new ArrayList<>();
        goodsList.add(userGoods);
        userGoodsService.addUserGoods(userId, goodsList);
    }

    @Override
    public List<WelfareVo> getMonthRewardsListByUserId(String userId, User userDetail) {
        WelfareMonthSettingExample example = new WelfareMonthSettingExample();
        WelfareReceiveMonthLogExample example2 = new WelfareReceiveMonthLogExample();
        WelfareVo welfareVo = new WelfareVo();
        //从月奖励设置表中取出奖励列表，目前没做条件校验
        List<WelfareMonthSetting> list = welfareMonthSettingMapper.selectByExample(example);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(System.currentTimeMillis()));
        int month = cal.get(Calendar.MONTH) + 1;//月份
        int days = getDaysByUserId(userId);//当前月登陆过多少天

        // 获取出当前月内的领奖记录
        example2.createCriteria().andUserIdEqualTo(userId).andCountDayEqualTo(month);
        example2.setOrderByClause("'receive_time' desc");
        List<WelfareReceiveMonthLog> MonthLoglist = welfareReceiveMonthLogMapper.selectByExample(example2);
        List<WelfareVo> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();

        //记录那些天数已经被领取过奖励
        for (WelfareReceiveMonthLog receiveMonthLog : MonthLoglist) {
            int dateNum = receiveMonthLog.getRewardsDay();
            list3.add(dateNum);
        }
        //对所有奖励天数进行状态判断
        for (WelfareMonthSetting welfareMonthSetting : list) {
            welfareVo = new WelfareVo();
            welfareVo.setDateNum(welfareMonthSetting.getDateNum());
            //判断该奖励是否已经被领取,或能否领取
            if (welfareMonthSetting.getDateNum() <= days) {//登陆天数大于可领取天数
                if (list3.contains(welfareMonthSetting.getDateNum())) {//判断是否已经领取
                    welfareVo.setIsReceive(WelfareConstant.WELFARE_REWARDS_RECEIVE_ALREADY);
                } else {
                    welfareVo.setIsReceive(WelfareConstant.WELFARE_REWARDS_RECEIVE_NULL);
                }
            } else {
                welfareVo.setIsReceive(WelfareConstant.WELFARE_REWARDS_RECEIVE_NOT);
            }
            JSONObject json = (JSONObject) JSON.parse(welfareMonthSetting.getRewardsContent());
            String amount = json.getString("amount");//奖励的数量
            String goodsId = json.getString("goodsId");
            welfareVo.setGoodsId(Integer.valueOf(goodsId));
            welfareVo.setRewardsNum(Integer.valueOf(amount));

            welfareVo.setRewardsType(welfareMonthSetting.getRewardsType());
            welfareVo.setUserId(userId);
            welfareVo.setWelfareId(welfareMonthSetting.getId());
            list2.add(welfareVo);
        }
        return list2;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ReceiveGoldVo receiveMonthRewardsByUserId(String userId, int dateNum) {
        WelfareReceiveMonthLogExample example2 = new WelfareReceiveMonthLogExample();
        WelfareMonthSettingExample example = new WelfareMonthSettingExample();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(System.currentTimeMillis()));
        int month = cal.get(Calendar.MONTH) + 1;
        example2.createCriteria().andUserIdEqualTo(userId).andCountDayEqualTo(month);
        example2.setOrderByClause("'receive_time' desc");
        List<WelfareReceiveMonthLog> MonthLoglist = welfareReceiveMonthLogMapper.selectByExample(example2);
        List<Integer> list3 = new ArrayList<>();
        for (WelfareReceiveMonthLog receiveMonthLog : MonthLoglist) {
            int dateNum2 = receiveMonthLog.getRewardsDay();
            list3.add(dateNum2);
        }
        example.createCriteria().andDateNumEqualTo(dateNum);
        List<WelfareMonthSetting> list = welfareMonthSettingMapper.selectByExample(example);
        WelfareMonthSetting welfareMonthSetting = new WelfareMonthSetting();
        if (list.size() > 0) {
            welfareMonthSetting = list.get(0);
        }

        if (!list3.contains(dateNum)) {//判断是否重复领取，不重复的情况下新增一条领取记录
            WelfareReceiveMonthLog welfareReceiveMonthLog = new WelfareReceiveMonthLog();
            welfareReceiveMonthLog.setReceiveTime(new Date(System.currentTimeMillis()));
            welfareReceiveMonthLog.setRewardsDay(dateNum);
            welfareReceiveMonthLog.setUserId(userId);
            welfareReceiveMonthLog.setRewardsType(welfareMonthSetting.getRewardsType());
            welfareReceiveMonthLog.setRewardsContent(welfareMonthSetting.getRewardsContent());
            welfareReceiveMonthLog.setCountDay(month);
            welfareReceiveMonthLogMapper.insert(welfareReceiveMonthLog);

            String jsonStr = welfareMonthSetting.getRewardsContent();
            JSONObject json = JSON.parseObject(jsonStr);
            int goodsId = json.getIntValue("goodsId");
            int num = json.getIntValue("amount");

            // 往指定用户的背包添加物品（开箱）
            addUserMoney(userId, goodsId, num);
            ReceiveGoldVo receiveGoldVo = new ReceiveGoldVo();
            receiveGoldVo.setAmount(num);
            receiveGoldVo.setGoodsId(goodsId);
            receiveGoldVo.setId(String.valueOf(goodsId));
            receiveGoldVo.setType(BuildingConstant.Prop);
            return receiveGoldVo;
        } else {
            throw new CheckException("请不要重复领取");
        }
    }

    //箱子类型：1：白银；2：黄金；3：绿色箱；4：蓝色箱；5：紫色箱；6：金色箱
    public String openBox(int boxType) {

        WelfareBoxSettingExample example = new WelfareBoxSettingExample();
        example.createCriteria().andBoxTypeEqualTo(boxType);
        WelfareBoxSetting welfareBoxSetting = welfareBoxSettingMapper.selectByExample(example).get(0);
        String rewards = welfareBoxSetting.getRewards();
        JSONArray arr = JSONObject.parseArray(rewards);
        Random random = new Random();
        //在5以内包含5随机生成一个数
        int index = random.nextInt(3) % (3 - 1 + 1) + 1;
        String goods = arr.get(index).toString();
        JSONObject object = JSONObject.parseObject(goods);
        String goodsId = object.get("goodsId").toString();
        return goodsId;
    }

    @Override
    public int getDaysByUserId(String userId) {
        //需要将这个接口改为连续登陆天数
        Calendar cale = null;
        cale = Calendar.getInstance();

        int num = 1;
        LoginLogExample loginLogExample = new LoginLogExample();
        loginLogExample.createCriteria().andUserIdEqualTo(userId);
        loginLogExample.setOrderByClause(" created_time DESC");
        PageHelper.startPage(1, 28);
        List<LoginLog> loginLogList = loginLogMapper.selectByExample(loginLogExample);
        for (int i = 0; i < loginLogList.size(); i++) {
            Date date = loginLogList.get(i).getCreatedTime();
            cale.setTime(date);
            cale.add(Calendar.DAY_OF_YEAR, -1);
            Date newdDate = cale.getTime();

            //计算出昨天的日期，再判断昨天是不是一样
            String dataStr = sdf.format(newdDate);
            if (i + 1 < loginLogList.size()) {
                Date date2 = loginLogList.get(i + 1).getCreatedTime();
                String dataStr2 = sdf.format(date2);
                if (dataStr.equals(dataStr2)) {
                    num++;
                    continue;
                } else {
                    break;
                }
            }
        }
        return num;
    }

    @Override
        public int addDaysByUserId(String userId) {
            // 增加
//        WelfareReceiveDayLog welfareReceiveDayLog = new WelfareReceiveDayLog();
            WelfareReceiveDayLogExample welfareReceiveDayLogExample = new WelfareReceiveDayLogExample();
            welfareReceiveDayLogExample.createCriteria().andUserIdEqualTo(userId).andYearEqualTo(DateUtil.getYear()).andWeekEqualTo(DateUtil.getWeek());
            List<WelfareReceiveDayLog> welfareReceiveDayLogs = welfareReceiveDayLogMapper.selectByExample(welfareReceiveDayLogExample);
            boolean bo = true;
        int max = 0;
        for (WelfareReceiveDayLog welfareReceiveDayLog : welfareReceiveDayLogs) {
            if (welfareReceiveDayLog.getRewardsDay() > max) {
                max = welfareReceiveDayLog.getRewardsDay();
            }
            if (DateUtil.isSameDate(welfareReceiveDayLog.getReceiveTime(), new Date())) {
                bo = false;
            }
        }
        if (bo) {
            WelfareReceiveDayLog welfareReceiveDayLog = new WelfareReceiveDayLog();
            welfareReceiveDayLog.setUserId(userId);
            welfareReceiveDayLog.setReceiveTime(new Date());
            welfareReceiveDayLog.setRewardsDay(max + 1);
            welfareReceiveDayLog.setReceiveAward(0);
            welfareReceiveDayLog.setWeek(DateUtil.getWeek());
            welfareReceiveDayLog.setYear(DateUtil.getYear());
            welfareReceiveDayLogMapper.insert(welfareReceiveDayLog);
        }

        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;
        int day = cale.get(Calendar.DATE);
        LoginLogExample loginLogExample = new LoginLogExample();
        loginLogExample.createCriteria().andUserIdEqualTo(userId).andMonthNumEqualTo(month).andYearNumEqualTo(year).andDayNumEqualTo(day);
        loginLogExample.setOrderByClause(" created_time DESC");
        List<LoginLog> loginLogList = loginLogMapper.selectByExample(loginLogExample);
        if (loginLogList.size() == 0) {
            LoginLog loginLog = new LoginLog();
            loginLog.setCreatedTime(new Date(System.currentTimeMillis()));
            loginLog.setDayNum(day);
            loginLog.setMonthNum(month);
            loginLog.setYearNum(year);
            loginLog.setUserId(userId);
            loginLogMapper.insert(loginLog);
            return 0;
        } else {
            return 0;
        }
    }

    @Override
    public int getWelfareStatusByUserId(String userId) {
        //查询该用户的福利领取情况
        int days = getDaysByUserId(userId);//当前月登陆过多少天
        int result = 0;
        WelfareMonthSettingExample example = new WelfareMonthSettingExample();
        WelfareReceiveMonthLogExample example2 = new WelfareReceiveMonthLogExample();
        //从月奖励设置表中取出奖励列表，目前没做条件校验
        List<WelfareMonthSetting> list = welfareMonthSettingMapper.selectByExample(example);
/*		Calendar cal = Calendar.getInstance(); 
        cal.setTime(new Date(System.currentTimeMillis()));
		int month = cal.get(Calendar.MONTH) + 1;//月份
*/
        //TODO  获取出当前月内的领奖记录
        example2.createCriteria().andUserIdEqualTo(userId);
        example2.setOrderByClause("'receive_time' desc");
        List<WelfareReceiveMonthLog> MonthLoglist = welfareReceiveMonthLogMapper.selectByExample(example2);
        List<Integer> list3 = new ArrayList<>();

        //记录那些天数已经被领取过奖励
        for (WelfareReceiveMonthLog receiveMonthLog : MonthLoglist) {
            int dateNum = receiveMonthLog.getRewardsDay();
            list3.add(dateNum);
        }
        //对所有奖励天数进行状态判断
        for (WelfareMonthSetting welfareMonthSetting : list) {
            //判断该奖励是否已经被领取,或能否领取
            if (welfareMonthSetting.getDateNum() <= days) {//登陆天数大于可领取天数
                if (list3.contains(welfareMonthSetting.getDateNum())) {//判断是否已经领取
                    continue;
                } else {
                    result = 0;
                    return 0;
                }
            }
        }

        User userDetail = userService.findByUserId(userId);
        WelfareDaySettingExample Dayexample = new WelfareDaySettingExample();
        WelfareReceiveDayLogExample Dayexample2 = new WelfareReceiveDayLogExample();
        //从day奖励设置表中取出奖励列表
        List<WelfareDaySetting> Daylist = welfareDaySettingMapper.selectByExample(Dayexample);
        Calendar Daycal = Calendar.getInstance();
        Daycal.setTime(new Date(System.currentTimeMillis()));
        Daycal.add(Calendar.DAY_OF_YEAR, -7);
        Date beforDay = Daycal.getTime();
        Long nowDate = System.currentTimeMillis();
        Date registerDate = userDetail.getCreateDate();
        Long newDate = registerDate.getTime();
        // 判断获取出某个用户七天内的领奖记录
        Dayexample2.createCriteria().andUserIdEqualTo(userId).andReceiveTimeGreaterThanOrEqualTo(beforDay);
        Dayexample2.setOrderByClause("'receive_time' desc");
        List<WelfareReceiveDayLog> dayLoglist = welfareReceiveDayLogMapper.selectByExample(Dayexample2);
        List<Integer> daylist3 = new ArrayList<>();
        int i = 1;
        if (dayLoglist.size() > 0) {//如果七天内有领取记录，
            WelfareReceiveDayLog welfareReceiveDayLog = dayLoglist.get(0);
            for (WelfareReceiveDayLog receiveDayLog : dayLoglist) {
                int dateNum = receiveDayLog.getRewardsDay();
                daylist3.add(dateNum);
            }
            Date receiveDate = welfareReceiveDayLog.getReceiveTime();
            int between = (int) (nowDate - receiveDate.getTime()) / (3600 * 1000 * 24) + 1;//可以领取奖励的天数
            for (int j = 1; Daylist.size() >= j; j++) {
                //判断该奖励是否已经被领取,或能否领取
                if (i <= between) {
                    if (daylist3.contains(i)) {//判断是否已经领取
                        result = 1;
                        i++;
                        continue;
                    } else {
                        result = 0;
                        return 0;
                    }
                }
                i++;
            }
        } else if ((nowDate - 7 * 24 * 3600 * 1000) > newDate) {
            return 0;
        } else {
            int between = (int) (nowDate - newDate) / (3600 * 1000 * 24) + 1;
            if (1 <= between) {
                result = 0;
                return 0;
            } else {
                result = 1;
            }
        }
        return result;
    }

    @Override
    public JSONObject getOfflineByUserId(String userId) {
        JSONObject json = new JSONObject();
        //TODO  判断昨天是否有登录记录，如果没有则触发离线
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(System.currentTimeMillis()));
        cal.add(Calendar.DAY_OF_YEAR, -7);
        Date beforDay = cal.getTime();
        LoginLogExample loginLogExample = new LoginLogExample();
        loginLogExample.createCriteria().andUserIdEqualTo(userId);
        loginLogExample.setOrderByClause(" created_time DESC");
        List<LoginLog> loginLogList = loginLogMapper.selectByExample(loginLogExample);
        if (loginLogList.size() > 0) {
            LoginLog loginLog = loginLogList.get(0);
            //先判断离线时间是否大于七天
            if (beforDay.getTime() > loginLog.getCreatedTime().getTime()) {
                //直接拥有七天满额的离线奖励
            } else if (System.currentTimeMillis() - loginLog.getCreatedTime().getTime() > 3600 * 24 * 1000) {
                //对一天的毫秒数取余算出离线天数,根据天数计算奖励
//				int days = (int)(System.currentTimeMillis() - loginLog.getCreatedTime().getTime())%(3600*24*1000);
            } else {

                //小于一天，不给与奖励
            }
            return json;
        }
        return json;
    }

}
