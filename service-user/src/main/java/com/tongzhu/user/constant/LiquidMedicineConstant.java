package com.tongzhu.user.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 药水效果类
 */
public class LiquidMedicineConstant {

    public static final Map<String,Float> map = new HashMap();
//    public static final Map<String,Map<String,Float>> map = new HashMap();
//    public static final String LIFE="life";
//    public static final String ATTACK="attack";
//    public static final String DEFEND="defend";
//    public static final String ACCURACY="accuracy";
//    public static final String MISS="miss";
//    public static final String CRITICAL="critical";
//    public static final String DISCRITICAL="disCritical";


    public static final String LIFE="11001";
    public static final String ATTACK="11002";
    public static final String DEFEND="11003";
    public static final String ACCURACY="11004";
    public static final String MISS="11005";
    public static final String CRITICAL="11006";
    public static final String DISCRITICAL="11007";
    static {
        // 生命药水  战斗中生命值上限+10%
//        Map<String,Float> attribute1=new HashMap<>();
//        attribute1.put("life",0.1f);
        map.put("11001", 0.1f);

        //攻击药水  战斗中攻击力+5%
//        Map<String,Float> attribute2=new HashMap<>();
//        attribute2.put("attack",0.05f);
        map.put("11002", 0.05f);

        //防御药水  战斗中防御力+5%
//        Map<String,Float> attribute3=new HashMap<>();
//        attribute3.put("defend",0.05f);
        map.put("11003", 0.05f);

        //命中药水  战斗中命中+10%
//        Map<String,Float> attribute4=new HashMap<>();
//        attribute4.put("accuracy",0.1f);
        map.put("11004", 0.1f);

        //闪避药水  战斗中闪避+10%
//        Map<String,Float> attribute5=new HashMap<>();
//        attribute5.put("miss",0.1f);
        map.put("11005", 0.1f);

        //暴击药水  战斗中暴击+10%
//        Map<String,Float> attribute6=new HashMap<>();
//        attribute6.put("critical",0.1f);
        map.put("11006", 0.1f);

        //抗暴击药水  战斗中抗暴击+10%
//        Map<String,Float> attribute7=new HashMap<>();
//        attribute7.put("disCritical",0.1f);
        map.put("11007", 0.1f);
    }

    public static Float getAttrByGoodsId(String goodsId){
        return map.get(goodsId);
    }

}
