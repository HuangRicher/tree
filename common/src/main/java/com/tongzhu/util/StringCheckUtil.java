package com.tongzhu.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCheckUtil {

    /**
     * 判斷是否包含除數字，中文，英文外的特殊字符
     * @param str
     */
    public static boolean checkHaveEspecialCode(String str) {
        String regex="^[a-zA-Z0-9\u4E00-\u9FA5]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher match=pattern.matcher(str);
        return match.matches();
    }


}
