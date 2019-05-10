package com.tongzhu.user.util;

import com.tongzhu.user.domain.ArsenalInfo;
import com.tongzhu.user.domain.ArsenalInfoVO;
import com.tongzhu.user.model.Skill;
import com.tongzhu.util.RandomUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRoleUpgradeUtil {
    public static Integer getSkill(Integer roleLevel,List<Skill> skillList){
        //3级技能奖池	1
        //技能id	概率
        //2	    0.2
        //8	    0.2
        //10	0.2
        //14	0.2
        //27	0.2

        //8级技能奖池	1
        //技能id	概率
        //2	    0.2
        //8	    0.2
        //10	0.2
        //14	0.2
        //27	0.2
        Map<Integer,Float> data=new HashMap<>();
        float totalData=0;
        if(3==roleLevel || 8==roleLevel){
            for(Skill skill:skillList){
                switch (skill.getId()){
                    case 2:
                        data.put(2,0.2f);
                        totalData+=0.2f;
                        break;
                    case 8:
                        data.put(8,0.2f);
                        totalData+=0.2f;
                        break;
                    case 10:
                        data.put(10,0.2f);
                        totalData+=0.2f;
                        break;
                    case 14:
                        data.put(14,0.2f);
                        totalData+=0.2f;
                        break;
                    case 27:
                        data.put(27,0.2f);
                        totalData+=0.2f;
                        break;
                }
            }
        }

        //13级技能奖池	1
        //技能id	概率
        //1	    0.1
        //2	    0.1
        //4	    0.1
        //8	    0.1
        //10	0.1
        //12	0.1
        //14	0.1
        //16	0.05
        //21	0.05
        //27	0.1
        //28	0.05
        //29	0.05
        if(13==roleLevel){
            for(Skill skill:skillList){
                switch (skill.getId()){
                    case 1:
                        data.put(1,0.1f);
                        totalData+=0.1f;
                        break;
                    case 2:
                        data.put(2,0.1f);
                        totalData+=0.1f;
                        break;
                    case 4:
                        data.put(4,0.1f);
                        totalData+=0.1f;
                        break;
                    case 8:
                        data.put(8,0.1f);
                        totalData+=0.1f;
                        break;
                    case 10:
                        data.put(10,0.1f);
                        totalData+=0.1f;
                        break;
                    case 12:
                        data.put(12,0.1f);
                        totalData+=0.1f;
                        break;
                    case 14:
                        data.put(14,0.1f);
                        totalData+=0.1f;
                        break;
                    case 16:
                        data.put(16,0.05f);
                        totalData+=0.05f;
                        break;
                    case 21:
                        data.put(21,0.05f);
                        totalData+=0.05f;
                        break;
                    case 27:
                        data.put(27,0.1f);
                        totalData+=0.1f;
                    case 28:
                        data.put(28,0.05f);
                        totalData+=0.05f;
                        break;
                    case 29:
                        data.put(29,0.05f);
                        totalData+=0.05f;
                        break;
                }
            }
        }


        //18级技能奖池	1
        //技能id	概率
        //1	    0.1
        //2	    0.05
        //4	    0.1
        //8	    0.05
        //10	0.05
        //12	0.1
        //14	0.05
        //16	0.1
        //21	0.1
        //27	0.1
        //28	0.1
        //29	0.1
        if(18==roleLevel){
            for(Skill skill:skillList){
                switch (skill.getId()){
                    case 1:
                        data.put(1,0.1f);
                        totalData+=0.1f;
                        break;
                    case 2:
                        data.put(2,0.05f);
                        totalData+=0.05f;
                        break;
                    case 4:
                        data.put(4,0.1f);
                        totalData+=0.1f;
                        break;
                    case 8:
                        data.put(8,0.05f);
                        totalData+=0.05f;
                        break;
                    case 10:
                        data.put(10,0.05f);
                        totalData+=0.05f;
                        break;
                    case 12:
                        data.put(12,0.1f);
                        totalData+=0.1f;
                        break;
                    case 14:
                        data.put(14,0.05f);
                        totalData+=0.05f;
                        break;
                    case 16:
                        data.put(16,0.1f);
                        totalData+=0.1f;
                        break;
                    case 21:
                        data.put(21,0.1f);
                        totalData+=0.1f;
                        break;
                    case 27:
                        data.put(27,0.1f);
                        totalData+=0.1f;
                    case 28:
                        data.put(28,0.1f);
                        totalData+=0.1f;
                        break;
                    case 29:
                        data.put(29,0.1f);
                        totalData+=0.1f;
                        break;
                }
            }
        }


        //23级技能奖池	2
        //技能id	概率
        //1	    0.05
        //3	    0.05
        //4	    0.05
        //5	    0.05
        //9	    0.05
        //11	0.05
        //12	0.1
        //13	0.05
        //16	0.1
        //21	0.1
        //28	0.1
        //29	0.1
        //31	0.05
        //37	0.05
        //38	0.05
        if(23==roleLevel){
            for(Skill skill:skillList){
                switch (skill.getId()){
                    case 1:
                        data.put(1,0.05f);
                        totalData+=0.05f;
                        break;
                    case 3:
                        data.put(3,0.05f);
                        totalData+=0.05f;
                        break;
                    case 4:
                        data.put(4,0.05f);
                        totalData+=0.05f;
                        break;
                    case 5:
                        data.put(5,0.05f);
                        totalData+=0.05f;
                        break;
                    case 9:
                        data.put(9,0.05f);
                        totalData+=0.05f;
                        break;
                    case 11:
                        data.put(11,0.05f);
                        totalData+=0.05f;
                        break;
                    case 12:
                        data.put(12,0.1f);
                        totalData+=0.1f;
                        break;
                    case 13:
                        data.put(13,0.05f);
                        totalData+=0.05f;
                        break;
                    case 16:
                        data.put(16,0.1f);
                        totalData+=0.1f;
                        break;
                    case 21:
                        data.put(21,0.1f);
                        totalData+=0.1f;
                        break;
                    case 28:
                        data.put(28,0.1f);
                        totalData+=0.1f;
                        break;
                    case 29:
                        data.put(29,0.1f);
                        totalData+=0.1f;
                        break;
                    case 31:
                        data.put(31,0.05f);
                        totalData+=0.05f;
                        break;
                    case 37:
                        data.put(37,0.05f);
                        totalData+=0.05f;
                        break;
                    case 38:
                        data.put(38,0.05f);
                        totalData+=0.05f;
                        break;
                }
            }
        }



        //28级技能奖池	2
        //技能id	概率
        //1	    0.05
        //3	    0.1
        //4	    0.05
        //5	    0.1
        //9	    0.1
        //11	0.1
        //12	0.05
        //13	0.1
        //16	0.05
        //21	0.05
        //28	0.05
        //29	0.05
        //31	0.05
        //37	0.05
        //38	0.05
        if(28==roleLevel){
            for(Skill skill:skillList){
                switch (skill.getId()){
                    case 1:
                        data.put(1,0.05f);
                        totalData+=0.05f;
                        break;
                    case 3:
                        data.put(3,0.1f);
                        totalData+=0.1f;
                        break;
                    case 4:
                        data.put(4,0.05f);
                        totalData+=0.05f;
                        break;
                    case 5:
                        data.put(5,0.1f);
                        totalData+=0.1f;
                        break;
                    case 9:
                        data.put(9,0.1f);
                        totalData+=0.1f;
                        break;
                    case 11:
                        data.put(11,0.1f);
                        totalData+=0.1f;
                        break;
                    case 12:
                        data.put(12,0.05f);
                        totalData+=0.05f;
                        break;
                    case 13:
                        data.put(13,0.1f);
                        totalData+=0.1f;
                        break;
                    case 16:
                        data.put(16,0.05f);
                        totalData+=0.05f;
                        break;
                    case 21:
                        data.put(21,0.05f);
                        totalData+=0.05f;
                        break;
                    case 28:
                        data.put(28,0.05f);
                        totalData+=0.05f;
                        break;
                    case 29:
                        data.put(29,0.05f);
                        totalData+=0.05f;
                        break;
                    case 31:
                        data.put(31,0.05f);
                        totalData+=0.05f;
                        break;
                    case 37:
                        data.put(37,0.05f);
                        totalData+=0.05f;
                        break;
                    case 38:
                        data.put(38,0.05f);
                        totalData+=0.05f;
                        break;
                }
            }
        }

        //33级技能奖池	3
        //技能id	概率
        //3	    0.05
        //5	    0.05
        //6	    0.05
        //9	    0.1
        //11	0.1
        //13	0.1
        //17	0.05
        //20	0.05
        //22	0.05
        //31	0.1
        //32	0.05
        //35	0.05
        //36	0.05
        //37	0.05
        //38	0.05
        //40	0.05
        if(33==roleLevel){
            for(Skill skill:skillList){
                switch (skill.getId()){
                    case 3:
                        data.put(3,0.05f);
                        totalData+=0.05f;
                        break;
                    case 5:
                        data.put(5,0.05f);
                        totalData+=0.05f;
                        break;
                    case 6:
                        data.put(6,0.05f);
                        totalData+=0.05f;
                        break;
                    case 9:
                        data.put(9,0.1f);
                        totalData+=0.1f;
                        break;
                    case 11:
                        data.put(11,0.1f);
                        totalData+=0.1f;
                        break;
                    case 13:
                        data.put(13,0.1f);
                        totalData+=0.1f;
                        break;
                    case 17:
                        data.put(17,0.05f);
                        totalData+=0.05f;
                        break;
                    case 20:
                        data.put(20,0.05f);
                        totalData+=0.05f;
                        break;
                    case 22:
                        data.put(22,0.05f);
                        totalData+=0.05f;
                        break;
                    case 31:
                        data.put(31,0.1f);
                        totalData+=0.1f;
                        break;
                    case 32:
                        data.put(32,0.05f);
                        totalData+=0.05f;
                        break;
                    case 35:
                        data.put(35,0.05f);
                        totalData+=0.05f;
                        break;
                    case 36:
                        data.put(36,0.05f);
                        totalData+=0.05f;
                        break;
                    case 37:
                        data.put(37,0.05f);
                        totalData+=0.05f;
                        break;
                    case 38:
                        data.put(38,0.05f);
                        totalData+=0.05f;
                        break;
                    case 40:
                        data.put(40,0.05f);
                        totalData+=0.05f;
                        break;
                }
            }
        }



        //38级技能奖池	3
        //技能id	概率
        //3	    0.05
        //5	    0.05
        //6	    0.1
        //9	    0.05
        //11	0.05
        //13	0.05
        //17	0.1
        //20	0.05
        //22	0.05
        //31	0.05
        //32	0.05
        //35	0.05
        //36	0.05
        //37	0.1
        //38	0.1
        //40	0.05
        if(38==roleLevel){
            for(Skill skill:skillList){
                switch (skill.getId()){
                    case 3:
                        data.put(3,0.05f);
                        totalData+=0.05f;
                        break;
                    case 5:
                        data.put(5,0.05f);
                        totalData+=0.05f;
                        break;
                    case 6:
                        data.put(6,0.1f);
                        totalData+=0.1f;
                        break;
                    case 9:
                        data.put(9,0.05f);
                        totalData+=0.05f;
                        break;
                    case 11:
                        data.put(11,0.05f);
                        totalData+=0.05f;
                        break;
                    case 13:
                        data.put(13,0.05f);
                        totalData+=0.05f;
                        break;
                    case 17:
                        data.put(17,0.1f);
                        totalData+=0.1f;
                        break;
                    case 20:
                        data.put(20,0.05f);
                        totalData+=0.05f;
                        break;
                    case 22:
                        data.put(22,0.05f);
                        totalData+=0.05f;
                        break;
                    case 31:
                        data.put(31,0.05f);
                        totalData+=0.05f;
                        break;
                    case 32:
                        data.put(32,0.05f);
                        totalData+=0.05f;
                        break;
                    case 35:
                        data.put(35,0.05f);
                        totalData+=0.05f;
                        break;
                    case 36:
                        data.put(36,0.05f);
                        totalData+=0.05f;
                        break;
                    case 37:
                        data.put(37,0.1f);
                        totalData+=0.1f;
                        break;
                    case 38:
                        data.put(38,0.1f);
                        totalData+=0.1f;
                        break;
                    case 40:
                        data.put(40,0.05f);
                        totalData+=0.05f;
                        break;
                }
            }
        }


        //43级技能奖池	4
        //技能id	概率
        //6	    0.05
        //7	    0.05
        //15	0.05
        //17	0.05
        //18	0.05
        //19	0.05
        //20	0.1
        //22	0.1
        //30	0.05
        //32	0.1
        //33	0.05
        //34	0.05
        //35	0.1
        //36	0.05
        //39	0.05
        //40	0.05
        if(43==roleLevel){
            for(Skill skill:skillList){
                switch (skill.getId()){
                    case 6:
                        data.put(6,0.05f);
                        totalData+=0.05f;
                        break;
                    case 7:
                        data.put(7,0.05f);
                        totalData+=0.05f;
                        break;
                    case 15:
                        data.put(15,0.05f);
                        totalData+=0.05f;
                        break;
                    case 17:
                        data.put(17,0.05f);
                        totalData+=0.05f;
                        break;
                    case 18:
                        data.put(18,0.05f);
                        totalData+=0.05f;
                        break;
                    case 19:
                        data.put(19,0.05f);
                        totalData+=0.05f;
                        break;
                    case 20:
                        data.put(20,0.1f);
                        totalData+=0.1f;
                        break;
                    case 22:
                        data.put(22,0.1f);
                        totalData+=0.1f;
                        break;
                    case 30:
                        data.put(30,0.05f);
                        totalData+=0.05f;
                        break;
                    case 32:
                        data.put(32,0.1f);
                        totalData+=0.1f;
                        break;
                    case 33:
                        data.put(33,0.05f);
                        totalData+=0.05f;
                        break;
                    case 34:
                        data.put(34,0.05f);
                        totalData+=0.05f;
                        break;
                    case 35:
                        data.put(35,0.1f);
                        totalData+=0.1f;
                        break;
                    case 36:
                        data.put(36,0.05f);
                        totalData+=0.05f;
                        break;
                    case 39:
                        data.put(39,0.05f);
                        totalData+=0.05f;
                        break;
                    case 40:
                        data.put(40,0.05f);
                        totalData+=0.05f;
                        break;
                }
            }
        }


        //48级技能奖池	4
        //技能id	概率
        //6	    0.05
        //7	    0.1
        //15	0.1
        //17	0.05
        //18	0.05
        //19	0.05
        //20	0.05
        //22	0.05
        //30	0.05
        //32	0.05
        //33	0.05
        //34	0.05
        //35	0.05
        //36	0.1
        //39	0.05
        //40	0.1
        if(48==roleLevel){
            for(Skill skill:skillList){
                switch (skill.getId()){
                    case 6:
                        data.put(6,0.05f);
                        totalData+=0.05f;
                        break;
                    case 7:
                        data.put(7,0.1f);
                        totalData+=0.1f;
                        break;
                    case 15:
                        data.put(15,0.1f);
                        totalData+=0.1f;
                        break;
                    case 17:
                        data.put(17,0.05f);
                        totalData+=0.05f;
                        break;
                    case 18:
                        data.put(18,0.05f);
                        totalData+=0.05f;
                        break;
                    case 19:
                        data.put(19,0.05f);
                        totalData+=0.05f;
                        break;
                    case 20:
                        data.put(20,0.05f);
                        totalData+=0.05f;
                        break;
                    case 22:
                        data.put(22,0.05f);
                        totalData+=0.05f;
                        break;
                    case 30:
                        data.put(30,0.05f);
                        totalData+=0.05f;
                        break;
                    case 32:
                        data.put(32,0.05f);
                        totalData+=0.05f;
                        break;
                    case 33:
                        data.put(33,0.05f);
                        totalData+=0.05f;
                        break;
                    case 34:
                        data.put(34,0.05f);
                        totalData+=0.05f;
                        break;
                    case 35:
                        data.put(35,0.05f);
                        totalData+=0.05f;
                        break;
                    case 36:
                        data.put(36,0.1f);
                        totalData+=0.1f;
                        break;
                    case 39:
                        data.put(39,0.05f);
                        totalData+=0.05f;
                        break;
                    case 40:
                        data.put(40,0.1f);
                        totalData+=0.1f;
                        break;
                }
            }
        }
        if(totalData>0){
            int rate=RandomUtil.random(1,(int)(totalData*1000));
            int currentRate=0;
            System.out.println(totalData*1000);
            for(Map.Entry<Integer,Float> entry:data.entrySet()){
                currentRate+=(int)(entry.getValue()*1000);
                System.out.println("skill_rate:"+rate+",,currentRate:"+currentRate);
                if(currentRate>=rate){
                    return entry.getKey();
                }
            }
            return null;
        }else{
            return null;
        }
    }



    public static String getWeapon(Integer roleId, Integer roleLevel, List<ArsenalInfo> weaponList){
        Map<Integer,Float> data=new HashMap<>();
        float totalData=0;

        //卫士武器奖池
        //武器id	概率
        //110002	0.12
        //110003	0.12
        //110004	0.12
        //110005	0.08
        //110006	0.08
        //110007	0.08
        //110008	0.06
        //110009	0.06
        //110010	0.06
        //110011	0.04
        //110012	0.04
        //110013	0.04
        //110014	0.02
        //110015	0.02
        //110016	0.02
        //110017	0.01
        //110018	0.01
        //110019	0.01
        //110020	0.004
        //110021	0.003
        //110022	0.003
        if(",2,7,12,17,22,27,32,37,42,47,".contains(","+roleLevel+",") && (1==roleId || 2==roleId)){
            for(ArsenalInfo arsenalInfo:weaponList){
                switch (arsenalInfo.getId()){
                    case 110002:
                        data.put(110002,0.12f);
                        totalData+=0.12f;
                        break;
                    case 110003:
                        data.put(110003,0.12f);
                        totalData+=0.12f;
                        break;
                    case 110004:
                        data.put(110004,0.12f);
                        totalData+=0.12f;
                        break;
                    case 110005:
                        data.put(110005,0.08f);
                        totalData+=0.08f;
                        break;
                    case 110006:
                        data.put(110006,0.08f);
                        totalData+=0.08f;
                        break;
                    case 110007:
                        data.put(110007,0.08f);
                        totalData+=0.08f;
                        break;
                    case 110008:
                        data.put(110008,0.06f);
                        totalData+=0.06f;
                        break;
                    case 110009:
                        data.put(110009,0.06f);
                        totalData+=0.06f;
                        break;
                    case 110010:
                        data.put(110010,0.06f);
                        totalData+=0.06f;
                        break;
                    case 110011:
                        data.put(110011,0.04f);
                        totalData+=0.04f;
                        break;
                    case 110012:
                        data.put(110012,0.04f);
                        totalData+=0.04f;
                        break;
                    case 110013:
                        data.put(110013,0.04f);
                        totalData+=0.04f;
                        break;
                    case 110014:
                        data.put(110014,0.02f);
                        totalData+=0.02f;
                        break;
                    case 110015:
                        data.put(110015,0.02f);
                        totalData+=0.02f;
                        break;
                    case 110016:
                        data.put(110016,0.02f);
                        totalData+=0.02f;
                        break;
                    case 110017:
                        data.put(110017,0.01f);
                        totalData+=0.01f;
                        break;
                    case 110018:
                        data.put(110018,0.01f);
                        totalData+=0.01f;
                        break;
                    case 110019:
                        data.put(110019,0.01f);
                        totalData+=0.01f;
                        break;
                    case 110020:
                        data.put(110020,0.004f);
                        totalData+=0.004f;
                        break;
                    case 110021:
                        data.put(110021,0.003f);
                        totalData+=0.003f;
                        break;
                    case 110022:
                        data.put(110022,0.003f);
                        totalData+=0.003f;
                        break;
                }
            }
        }

        //刺客武器奖池
        //武器id	概率
        //120002	0.12
        //120003	0.12
        //120004	0.12
        //120005	0.08
        //120006	0.08
        //120007	0.08
        //120008	0.06
        //120009	0.06
        //120010	0.06
        //120011	0.04
        //120012	0.04
        //120013	0.04
        //120014	0.02
        //120015	0.02
        //120016	0.02
        //120017	0.01
        //120018	0.01
        //120019	0.01
        //120020	0.004
        //120021	0.003
        //120022	0.003
        if(",2,7,12,17,22,27,32,37,42,47,".contains(","+roleLevel+",") && (3==roleId || 4==roleId)){
            for(ArsenalInfo arsenalInfo:weaponList){
                switch (arsenalInfo.getId()){
                    case 120002:
                        data.put(120002,0.12f);
                        totalData+=0.12f;
                        break;
                    case 120003:
                        data.put(120003,0.12f);
                        totalData+=0.12f;
                        break;
                    case 120004:
                        data.put(120004,0.12f);
                        totalData+=0.12f;
                        break;
                    case 120005:
                        data.put(120005,0.08f);
                        totalData+=0.08f;
                        break;
                    case 120006:
                        data.put(120006,0.08f);
                        totalData+=0.08f;
                        break;
                    case 120007:
                        data.put(120007,0.08f);
                        totalData+=0.08f;
                        break;
                    case 120008:
                        data.put(120008,0.06f);
                        totalData+=0.06f;
                        break;
                    case 120009:
                        data.put(120009,0.06f);
                        totalData+=0.06f;
                        break;
                    case 120010:
                        data.put(120010,0.06f);
                        totalData+=0.06f;
                        break;
                    case 120011:
                        data.put(120011,0.04f);
                        totalData+=0.04f;
                        break;
                    case 120012:
                        data.put(120012,0.04f);
                        totalData+=0.04f;
                        break;
                    case 120013:
                        data.put(120013,0.04f);
                        totalData+=0.04f;
                        break;
                    case 120014:
                        data.put(120014,0.02f);
                        totalData+=0.02f;
                        break;
                    case 120015:
                        data.put(120015,0.02f);
                        totalData+=0.02f;
                        break;
                    case 120016:
                        data.put(120016,0.02f);
                        totalData+=0.02f;
                        break;
                    case 120017:
                        data.put(120017,0.01f);
                        totalData+=0.01f;
                        break;
                    case 120018:
                        data.put(120018,0.01f);
                        totalData+=0.01f;
                        break;
                    case 120019:
                        data.put(120019,0.01f);
                        totalData+=0.01f;
                        break;
                    case 120020:
                        data.put(120020,0.004f);
                        totalData+=0.004f;
                        break;
                    case 120021:
                        data.put(120021,0.003f);
                        totalData+=0.003f;
                        break;
                    case 120022:
                        data.put(120022,0.003f);
                        totalData+=0.003f;
                        break;
                }
            }
        }

        //工匠武器奖池
        //武器id	概率
        //130002	0.12
        //130003	0.12
        //130004	0.12
        //130005	0.08
        //130006	0.08
        //130007	0.08
        //130008	0.06
        //130009	0.06
        //130010	0.06
        //130011	0.04
        //130012	0.04
        //130013	0.04
        //130014	0.02
        //130015	0.02
        //130016	0.02
        //130017	0.01
        //130018	0.01
        //130019	0.01
        //130020	0.004
        //130021	0.003
        //130022	0.003
        if(",2,7,12,17,22,27,32,37,42,47,".contains(","+roleLevel+",") && (5==roleId || 6==roleId)){
            for(ArsenalInfo arsenalInfo:weaponList){
                switch (arsenalInfo.getId()){
                    case 130002:
                        data.put(130002,0.12f);
                        totalData+=0.12f;
                        break;
                    case 130003:
                        data.put(130003,0.12f);
                        totalData+=0.12f;
                        break;
                    case 130004:
                        data.put(130004,0.12f);
                        totalData+=0.12f;
                        break;
                    case 130005:
                        data.put(130005,0.08f);
                        totalData+=0.08f;
                        break;
                    case 130006:
                        data.put(130006,0.08f);
                        totalData+=0.08f;
                        break;
                    case 130007:
                        data.put(130007,0.08f);
                        totalData+=0.08f;
                        break;
                    case 130008:
                        data.put(130008,0.06f);
                        totalData+=0.06f;
                        break;
                    case 130009:
                        data.put(130009,0.06f);
                        totalData+=0.06f;
                        break;
                    case 130010:
                        data.put(130010,0.06f);
                        totalData+=0.06f;
                        break;
                    case 130011:
                        data.put(130011,0.04f);
                        totalData+=0.04f;
                        break;
                    case 130012:
                        data.put(130012,0.04f);
                        totalData+=0.04f;
                        break;
                    case 130013:
                        data.put(130013,0.04f);
                        totalData+=0.04f;
                        break;
                    case 130014:
                        data.put(130014,0.02f);
                        totalData+=0.02f;
                        break;
                    case 130015:
                        data.put(130015,0.02f);
                        totalData+=0.02f;
                        break;
                    case 130016:
                        data.put(130016,0.02f);
                        totalData+=0.02f;
                        break;
                    case 130017:
                        data.put(130017,0.01f);
                        totalData+=0.01f;
                        break;
                    case 130018:
                        data.put(130018,0.01f);
                        totalData+=0.01f;
                        break;
                    case 130019:
                        data.put(130019,0.01f);
                        totalData+=0.01f;
                        break;
                    case 130020:
                        data.put(130020,0.004f);
                        totalData+=0.004f;
                        break;
                    case 130021:
                        data.put(130021,0.003f);
                        totalData+=0.003f;
                        break;
                    case 130022:
                        data.put(130022,0.003f);
                        totalData+=0.003f;
                        break;
                }
            }
        }

        //法师武器奖池
        //武器id	概率
        //140002	0.12
        //140003	0.12
        //140004	0.12
        //140005	0.08
        //140006	0.08
        //140007	0.08
        //140008	0.06
        //140009	0.06
        //140010	0.06
        //140011	0.04
        //140012	0.04
        //140013	0.04
        //140014	0.02
        //140015	0.02
        //140016	0.02
        //140017	0.01
        //140018	0.01
        //140019	0.01
        //140020	0.004
        //140021	0.003
        //140022	0.003
        if(",2,7,12,17,22,27,32,37,42,47,".contains(","+roleLevel+",") && (7==roleId || 8==roleId)){
            for(ArsenalInfo arsenalInfo:weaponList){
                switch (arsenalInfo.getId()){
                    case 140002:
                        data.put(140002,0.12f);
                        totalData+=0.12f;
                        break;
                    case 140003:
                        data.put(140003,0.12f);
                        totalData+=0.12f;
                        break;
                    case 140004:
                        data.put(140004,0.12f);
                        totalData+=0.12f;
                        break;
                    case 140005:
                        data.put(140005,0.08f);
                        totalData+=0.08f;
                        break;
                    case 140006:
                        data.put(140006,0.08f);
                        totalData+=0.08f;
                        break;
                    case 140007:
                        data.put(140007,0.08f);
                        totalData+=0.08f;
                        break;
                    case 140008:
                        data.put(140008,0.06f);
                        totalData+=0.06f;
                        break;
                    case 140009:
                        data.put(140009,0.06f);
                        totalData+=0.06f;
                        break;
                    case 140010:
                        data.put(140010,0.06f);
                        totalData+=0.06f;
                        break;
                    case 140011:
                        data.put(140011,0.04f);
                        totalData+=0.04f;
                        break;
                    case 140012:
                        data.put(140012,0.04f);
                        totalData+=0.04f;
                        break;
                    case 140013:
                        data.put(140013,0.04f);
                        totalData+=0.04f;
                        break;
                    case 140014:
                        data.put(140014,0.02f);
                        totalData+=0.02f;
                        break;
                    case 140015:
                        data.put(140015,0.02f);
                        totalData+=0.02f;
                        break;
                    case 140016:
                        data.put(140016,0.02f);
                        totalData+=0.02f;
                        break;
                    case 140017:
                        data.put(140017,0.01f);
                        totalData+=0.01f;
                        break;
                    case 140018:
                        data.put(140018,0.01f);
                        totalData+=0.01f;
                        break;
                    case 140019:
                        data.put(140019,0.01f);
                        totalData+=0.01f;
                        break;
                    case 140020:
                        data.put(140020,0.004f);
                        totalData+=0.004f;
                        break;
                    case 140021:
                        data.put(140021,0.003f);
                        totalData+=0.003f;
                        break;
                    case 140022:
                        data.put(140022,0.003f);
                        totalData+=0.003f;
                        break;
                }
            }
        }
        if(totalData>0){
            int rate=RandomUtil.random(1,(int)(totalData*1000));
            int currentRate=0;
            System.out.println(totalData*1000);
            for(Map.Entry<Integer,Float> entry:data.entrySet()){
                currentRate+=(int)(entry.getValue()*1000);
                System.out.println("rate:"+rate+" ,,currentRate:"+currentRate);
                if(currentRate>=rate){
                    return entry.getKey()+"";
                }
            }
            return null;
        }else{
            return null;
        }
    }
}
