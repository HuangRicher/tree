package com.tongzhu.user.util;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class WXHttpClientUtil {

    private static Logger logger= LoggerFactory.getLogger(WXHttpClientUtil.class);


    private static final String APPID="wx715117c4bb00b931";
    private static final String SECRET="60c1762f09bd37c4534a532dd30a31f7";

    /**
     * 生成用于获取code的Url
     * @param redirectUrl
     * @param state
     * @return
     */
    public static String getRequestCodeUrl(String redirectUrl,String state) {
        return String.format("https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect",
                APPID, redirectUrl, "snsapi_login", state);
    }

    /**
     * 生成用于根据code获取access_token的Url
     * @param code
     * @return
     */
    public static String getRequestAccessTokenUrl(String code){
        return String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code",
                APPID, SECRET, code);
    }

    /**
     * 生成用于根据refreshToken获取refresh_token的Url
     * @param refreshToken
     * @return
     */
    public static String getRequestRefreshAccessTokenUrl(String refreshToken){
        return String.format("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s",
                APPID, refreshToken);
    }

    /**
     * 生成用于获取用户个人信息（UnionID机制）的Url
     * @param accessToken
     * @param openId
     * @return
     */
    public static String getRequestUserInfo(String accessToken,String openId){
        return String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN",
                accessToken, openId);
    }

    /**
     * 发送 get请求
     */
    public static String doGet(String uri) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // 创建httpget.
            HttpGet httpget = new HttpGet(uri);
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            //System.out.println("============="+response.toString());
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                // 打印响应状态
                //System.out.println(response.getStatusLine());
                if (entity != null) {
                    // 打印响应内容长度
                    //System.out.println("Response content length: " + entity.getContentLength());
                    // 返回响应内容
                    String result=new String(EntityUtils.toString(entity).getBytes("ISO-8859-1"), "UTF-8");
                    logger.info(result);
                    return result;
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
