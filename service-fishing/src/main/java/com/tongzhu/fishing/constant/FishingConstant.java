package com.tongzhu.fishing.constant;

import com.tongzhu.fishing.util.FisheryUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * 钓鱼常量类
 *
 * @author 海乐乐
 * @date 2018年8月9日
 */
public class FishingConstant {

    public static final String TUESDAY = "12";

    public static final String WEDNESDAY = "18";

    /**
     * 鱼汛刷新时间(30分钟)
     **/
    public static final Integer FISHING_FLOOD_DATETIME = 30 * 60;


    /**
     * 普通渔场
     **/
    public static final Integer FISHING_GROUND_QUALITY_ORDINARY = 1;
    /**
     * 高级渔场
     **/
    public static final Integer FISHING_GROUND_QUALITY_SENIOR = 2;
    /**
     * 稀有渔场
     **/
    public static final Integer FISHING_GROUND_QUALITY_RARE = 3;


    /**
     * 普通渔场的概率权重
     **/
    public static final Integer FISHING_GROUND_ORDINARY_CHANCE = 50;
    /**
     * 高级渔场的概率权重
     **/
    public static final Integer FISHING_GROUND_SENIOR_CHANCE = 40;
    /**
     * 稀有渔场的概率权重
     **/
    public static final Integer FISHING_GROUND_RARE_CHANCE = 10;


    /**
     * 鱼汛时间 12点
     **/
    public static final String FISHING_FISH_FLOOD_DATE_1 = "12";
    /**
     * 鱼汛时间 19点
     **/
    public static final String FISHING_FISH_FLOOD_DATE_2 = "19";


    /**
     * 物品价值类型 : 金币
     **/
    public static final Integer FISHING_GOODS_TYPE_MOENY = 1;

    /**
     * 鱼种价值类型 : 雨露
     **/
    public static final Integer FISHING_GOODS_TYPE_DEW = 2;

    /**
     * 鱼种价值类型 : 精华
     **/
    public static final Integer FISHING_GOODS_TYPE_ESSENCE = 1;

    /**
     * 鱼种价值类型 : 钓鱼抽奖券
     **/
    public static final Integer FISHING_GOODS_TYPE_LOTTERY = 2;

    /**
     * 鱼种价值类型 : 海螺
     **/
    public static final Integer FISHING_GOODS_TYPE_CONCH = 1;

    /**
     * 鱼种价值类型 : 木头
     **/
    public static final Integer FISHING_GOODS_TYPE_WOOD = 2;

    public static Map<String, Integer> qualityMap = new HashMap<>();

    public static Integer qualityWeight = 0;

    /**
     * 钓鱼任务编码
     */
    public static Integer TASK_TYPE_DY = 5;

    private static volatile FishingConstant singleton = null;

    private FishingConstant() {
        int record = 0;
//        qualityMap.put(FisheryUtil.getProbabilityList(record, record + FISHING_GROUND_ORDINARY_CHANCE).toString()
//                .replace("[", "-").replace("]", "") + ",", FISHING_GROUND_QUALITY_ORDINARY);
//        record += FISHING_GROUND_ORDINARY_CHANCE;
        qualityMap.put(FisheryUtil.getProbabilityList(record, record + FISHING_GROUND_SENIOR_CHANCE).toString()
                .replace("[", "-").replace("]", "") + ",", FISHING_GROUND_QUALITY_SENIOR);
        record += FISHING_GROUND_SENIOR_CHANCE;
        qualityMap.put(FisheryUtil.getProbabilityList(record, record + FISHING_GROUND_RARE_CHANCE).toString()
                .replace("[", "-").replace("]", "") + ",", FISHING_GROUND_QUALITY_RARE);
        record += FISHING_GROUND_RARE_CHANCE;
        qualityWeight = FISHING_GROUND_RARE_CHANCE + FISHING_GROUND_SENIOR_CHANCE ;
    }

    public static FishingConstant getSingleton() {
        if (singleton == null) {
            synchronized (FishingConstant.class) {
                if (singleton == null) {
                    singleton = new FishingConstant();
                }
            }
        }
        return singleton;
    }

    public  Integer getQuality() {
        int randomNumber = new Random().nextInt(qualityWeight);
        Iterator it = qualityMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();
            if (entry.getKey().contains(randomNumber + ",")) {
                return entry.getValue();
            }
        }
        return null;
    }
}
