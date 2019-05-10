package com.tongzhu.user.util;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;

import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsSenderUtil {

    private static final Integer appid=1400193145;
    private static final String appkey="3754e779573d969dafd05e795b9a7331";
    // 短信模板ID，需要在短信应用中申请
    private static final Integer templateId = 292808;
    // 签名
    private static final String smsSign = "袋娱网络";
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(14[5,7])|(17[0,1,3,5,6,7,8]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static void send(String phoneNumber){
        try {
            String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.send(0, "86", phoneNumber,
                    "【袋娱网络】您的登陆验证码为："+verifyCode, "", "");
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
    }

    public static String sendWithTemplate(String phoneNumber){
        try {
            String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
            String[] params = {verifyCode,"1"};
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber,
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.println(result);
            return verifyCode;
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
        return null;
    }
}
