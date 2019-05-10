package com.tongzhu.fishing.util;


import com.tongzhu.fishing.constant.FishingConstant;
import com.tongzhu.fishing.domain.UserGoods;
import com.tongzhu.fishing.model.FishBaseModel;
import com.tongzhu.fishing.pond.BaseFishPond;
import com.tongzhu.fishing.pond.RareFishPond;
import com.tongzhu.fishing.pond.SeniorFishPond;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 钓鱼工具类
 * @author Administrator
 *
 */
public class  FishingUtil {
	
	/**
	 * 钓鱼方法
	 * @author 海乐乐
	 * @date 2018年8月10日
	 * @param pond 鱼塘
	 * @param count 倍率
	 * @return 获得的钓鱼记录(ID：数量)
	 */
	public static List<UserGoods> getFishing(BaseFishPond pond, int count){
		List<UserGoods> list = new ArrayList<UserGoods>();
		UserGoods entity;
		for (int i = 0; i < count; i++) {
			FishBaseModel model = getPrize(pond.getList(),pond.getQuality());//获取钓到的鱼
//			FishBaseModel model = lottery(pond);//获取钓到的鱼
			entity = new UserGoods();
			entity.setAmount(1);
			entity.setGoodsId(model.getId());
			list.add(entity);
		}
		return list;
	}


	/** 重新写抽奖方法
	 * @author zzy
	 * @date 2018年9月27日
	 */
//	public static FishBaseModel lottery(BaseFishPond pond){
//
//		try{
//			FishBaseModel fishBaseModel = FishpondConfig.getInstance().extractPrize(pond.getQuality());
//			return fishBaseModel;
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return null;
//	}

	/**
	 * 获取鱼塘(刷新鱼塘)
	 * @author 海乐乐
	 * @return 
	 * @date 2018年8月10日
	 */
	public static BaseFishPond getFishPond(){
		List<BaseFishPond> list = new ArrayList<BaseFishPond>();
		list.add(new RareFishPond());
		list.add(new SeniorFishPond());//添加两个鱼塘
        int random = -1;
        try{
            //计算总权重
            double sumWeight = 0;
            for(BaseFishPond p : list){
                sumWeight += p.getChance();
            }
            System.out.println("鱼塘权重总值为:"+sumWeight);
            //产生随机数
            double randomNumber;
            randomNumber = Math.random();
            //根据随机数在所有奖品分布的区域并确定所抽奖品
            double d1 = 0;
            double d2 = 0;          
            for(int i=0;i<list.size();i++){
                d2 += Double.parseDouble(String.valueOf(list.get(i).getChance()))/sumWeight;
                if(i==0){
                    d1 = 0;
                }else{
                    d1 +=Double.parseDouble(String.valueOf(list.get(i-1).getChance()))/sumWeight;
                }
                if(randomNumber >= d1 && randomNumber <= d2){
                    random = i;
                    break;
                }
            }
        }catch(Exception e){
            System.out.println("生成抽奖随机数出错，出错原因：" +e.getMessage());
        }
        System.out.println("抽到的为:"+list.get(random).getQuality());
        
		return list.get(random);
	}
	/**获取当前时段
	 * @return 当前上午还是下午 (1:下午 / 0 ：上午)
	 * @author 海乐乐
	 * @date 2018年8月17日
	 */
	static SimpleDateFormat sdf = new SimpleDateFormat("HH");
	public static  Integer getDateInterval(){
		String format = sdf.format(new Date());
		int h = Integer.parseInt(format);
		int result = 0;
		if(h >= 12 && h <= 18){
			result = 1;
		}
		return result;
	}
	
	/**
	 * 钓鱼抽奖(根据概率进行权重设定)
	 * @param pools 奖池(中间包含权重数据)
	 * @param quality 渔场品质
	 * @return
	 */
	public static FishBaseModel getPrize(List<FishBaseModel> pools,Integer quality){
        System.out.println("抽奖开始");		
        //DecimalFormat df = new DecimalFormat("######0.00");  
        int random = -1;
        try{
            //计算总权重
            double sumWeight = 0;            
            for (FishBaseModel item : pools) {
            	Integer weight = 0;
            	if(quality == FishingConstant.FISHING_GROUND_QUALITY_ORDINARY){//根据渔场品质得到不同的权重概率
            		weight = item.getOrdinary();
            	}else if(quality == FishingConstant.FISHING_GROUND_QUALITY_SENIOR){
            		weight =item.getSenior();
            	}else if(quality == FishingConstant.FISHING_GROUND_QUALITY_RARE){
            		weight = item.getRare();
            	}
            	item.setWeight(weight);
                sumWeight += weight;
			}
            System.out.println("生成的权重为："+sumWeight);
            System.out.println("当前的鱼塘品质为："+quality);
            //产生随机数
            double randomNumber;
            randomNumber = Math.random();
            //System.out.println("生成的随机数为:"+randomNumber);
            //根据随机数在所有奖品分布的区域并确定所抽奖品
            double d1 = 0;
            double d2 = 0;          
            for(int i=0;i<pools.size();i++){
                d2 += Double.parseDouble(String.valueOf(pools.get(i).getWeight()))/sumWeight;
                if(i==0){
                    d1 = 0;
                }else{
                    d1 +=Double.parseDouble(String.valueOf(pools.get(i-1).getWeight()))/sumWeight;
                }
                if(randomNumber >= d1 && randomNumber <= d2){
                    random = i;
                    break;
                }
            }
        }catch(Exception e){
            System.out.println("生成抽奖随机数出错，出错原因：" +e.getMessage());
        }        
        System.out.println("奖品:"+pools.get(random).getName());
        return pools.get(random);
	}
	/**
	 * 判断服务器当前时间是否在鱼汛时间
	 * @author 海乐乐
	 * @date 2018年8月10日
	 */
	public static int isFishFlood(){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String format = sdf.format(new Date());
		String[] split = format.split(":");//根据时间戳来判断当时时间是否在鱼汛时间
		int result = -1;
		if(split[0].equals(FishingConstant.FISHING_FISH_FLOOD_DATE_1)||split[0].equals(FishingConstant.FISHING_FISH_FLOOD_DATE_2)){//如果在鱼汛时间
			int mm = Integer.parseInt(split[1]);
			if(mm <= 30){//如果当前时间在鱼汛时间，计算鱼汛剩余时间的时间戳
				result = ( (30 - mm) * 60 ) + Integer.parseInt(split[2]);
			}
		}
		return result;
	}

	
}
