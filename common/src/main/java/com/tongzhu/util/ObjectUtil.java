package com.tongzhu.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Administrator on 2018/11/5 0005.
 */
public class ObjectUtil {

    /**
     * @param obj
     * @param bo        true 保留
     * @param attribute
     */
    public static void setObjectFieldsEmpty(Object obj, boolean bo, String... attribute) {
        // 对obj反射

        List<Field> fieldList = new ArrayList<>();
        Class tempClass = obj.getClass();
        List<Method> objmethods = Arrays.asList(tempClass.getDeclaredMethods());
        List<Method> arrList = new ArrayList(objmethods);
        while (tempClass != null) {//当父类为null的时候说明到达了最上层的父类(Object类).
            fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
            if (tempClass.isInstance(new Object())) {
                break;
            }
            Method[] tempmethods = tempClass.getDeclaredMethods();
            if (tempmethods != null && tempmethods.length > 0) {
                arrList.addAll(Arrays.asList(tempmethods));
            }
        }

        Map objMeMap = new HashMap();
        a:
        for (int i = 0; i < arrList.size(); i++) {
            Method method = arrList.get(i);
            String name = method.getName();
            String substring = name.substring(3, name.length());
            b:
            for (int j = 0; j < attribute.length; j++) {
                if (bo) {
                    if (substring.equals(upperCase(attribute[j]))) {
                        continue a;
                    }
                    if (j == attribute.length - 1) {
                        objMeMap.put(name, method);
                    }
                } else {
                    if (substring.equals(upperCase(attribute[j]))) {
                        objMeMap.put(name, method);
                    }
                }

            }
        }
        for (int i = 0; i < arrList.size(); i++) {
            {
                String methodName = arrList.get(i).getName();
                if (methodName != null && methodName.startsWith("get")) {
                    try {
                        Object returnObj = arrList.get(i).invoke(obj,
                                new Object[0]);
                        Method setmethod = (Method) objMeMap.get("set"
                                + methodName.split("get")[1]);
                        if (returnObj != null) {
                            returnObj = null;
                        }
                        if (setmethod != null) {
                            setmethod.invoke(obj, returnObj);
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public static void setObjectFieldsEmpty(Object obj, String... attribute) {
        // 对obj反射
        List<Field> fieldList = new ArrayList<>();
        Class tempClass = obj.getClass();
        List<Method> objmethods = Arrays.asList(tempClass.getDeclaredMethods());
        List<Method> arrList = new ArrayList(objmethods);
        while (tempClass != null) {//当父类为null的时候说明到达了最上层的父类(Object类).
            fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
            if (tempClass.isInstance(new Object())) {
                break;
            }
            Method[] tempmethods = tempClass.getDeclaredMethods();
            if (tempmethods != null && tempmethods.length > 0) {
                arrList.addAll(Arrays.asList(tempmethods));
            }
        }
        Map objMeMap = new HashMap();
        a:
        for (int i = 0; i < arrList.size(); i++) {
            Method method = arrList.get(i);
            String name = method.getName();
            String substring = name.substring(3, name.length());
            b:
            for (int j = 0; j < attribute.length; j++) {
                if (substring.equals(upperCase(attribute[j]))) {
                    continue a;
                }
            }
            objMeMap.put(name, method);
        }
        for (int i = 0; i < arrList.size(); i++) {
            {
                String methodName = arrList.get(i).getName();
                if (methodName != null && methodName.startsWith("get")) {
                    try {
                        Object returnObj = arrList.get(i).invoke(obj,
                                new Object[0]);
                        Method setmethod = (Method) objMeMap.get("set"
                                + methodName.split("get")[1]);
                        if (returnObj != null) {
                            returnObj = null;
                        }
                        if (setmethod != null) {
                            setmethod.invoke(obj, returnObj);
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public static String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    public static Map getGoodsMap(String id, Object goodsId, int type, int amount, Integer enchantlvl) {
        Map map = new HashMap();
        map.put("id", id);
        map.put("goodsId", goodsId);
        map.put("type", type);
        map.put("amount", amount);
        map.put("enchantlvl", enchantlvl);
        return map;
    }

    public static Map getRedTipMap(String message) {
        Map<String, Object> object = new HashMap<>();
        List list = new ArrayList();
        list.add(message + ":1");
        object.put("models", list);
        object.put("type", "redTip");
        object.put("code", "0");
        return object;
    }

    public static Map getGoodsWebSock(String operation, Map goodsMap) {
        Map map = new HashMap();
        map.put("type", "weddingRing");
        map.put("code", 0);
        map.put("operation", operation);
        map.put("goods", goodsMap);
        return map;
    }
}
