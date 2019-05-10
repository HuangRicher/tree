package com.tongzhu.user.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongzhu.constant.HTTPUrlConstant;
import com.tongzhu.user.constant.LoginConstant;
import com.tongzhu.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.hibernate.validator.internal.util.privilegedactions.GetMethod;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http客户端
 */
public class HttpClientUtil {

    /**
     * post请求传输map数据
     *
     * @param url url地址
     * @param map map数据
     * @param encoding 编码方式
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String sendPostDataByMap(String url, Map<String, String> map, String encoding) throws ClientProtocolException, IOException {
        String result = "";
        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        // 装填参数
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        // 设置参数到请求对象中
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, encoding));

        // 设置header信息
        // 指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        // 执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = httpClient.execute(httpPost);
        // 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        }
        // 释放链接
        response.close();

        return result;
    }

    /**
     * post请求传输json数据
     *
     * @param url url地址
     * @param json json数据
     * @param encoding 编码方式
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String sendPostDataByJson(String url, String json, String encoding) throws ClientProtocolException, IOException {
        String result = "";

        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        // 设置参数到请求对象中
        StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        stringEntity.setContentEncoding("utf-8");
        httpPost.setEntity(stringEntity);

        // 执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = httpClient.execute(httpPost);

        // 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        }
        // 释放链接
        response.close();

        return result;
    }

    /**
     * get请求传输数据
     *
     * @param url
     * @param encoding
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String sendGetData(String url, String encoding) throws ClientProtocolException, IOException {
        String result = "";

        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建get方式请求对象
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Content-type", "application/json");
        // 通过请求对象获取响应对象
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        }
        // 释放链接
        response.close();

        return result;
    }


    public static void testSendPostDataByMap() throws ClientProtocolException, IOException {
        String url = "http://localhost:8080/httpService/sendPostDataByMap";
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "wyj");
        map.put("city", "南京");
        String body = sendPostDataByMap(url, map, "utf-8");
        System.out.println("响应结果：" + body);
    }

    public static String sendPostDataByMap(String url,Map<String,String> param) throws Exception {
        String body = sendPostDataByMap(url, param, "utf-8");
        return body;
    }


    public static void testSendPostDataByJson() throws ClientProtocolException, IOException {
        String url = "http://localhost:8080/httpService/sendPostDataByJson";
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "wyj");
        map.put("city", "南京");
        String body = sendPostDataByJson(url, JSON.toJSONString(map), "utf-8");
        System.out.println("响应结果：" + body);
    }

    public static String  sendPostDataByJson(String url, Map<String, String> map) throws Exception {
        String body = sendPostDataByJson(url, JSON.toJSONString(map), "utf-8");
        if(StringUtils.isNotBlank(body)){
            JSONObject object=JSON.parseObject(body);
            String header=UUIDUtil.generateUUID()+"jpg";
            if(object.getString("imageLink")!=null) {
                getPic(object.getString("imageLink"),header);
                header=object.getString("imageLink");
            }
            return header;
        }else{
            return null;
        }

    }

    public static void testSendGetData() throws ClientProtocolException, IOException {
        String url = "http://localhost:8080/httpService/sendGetData?name=wyj&city=南京";
        String body = sendGetData(url, "utf-8");
        System.out.println("响应结果：" + body);
    }

    /*public static void main(String[] args) throws Exception {

        //
        //testSendGetData();

        //
        //testSendPostDataByJson();

        //
        //testSendPostDataByMap();


        Map<String,String> param=new HashMap<>();
        param.put("username","accountB11");
        param.put("password","ctj123456");
        param.put("scret", LoginConstant.TZ_KEY);
        *//*JSONObject object=sendPostDataByJson("http://www.tzsd8.com/ageUser",param);
        if(object.getString("imageLink")!=null) {
            getPic(object.getString("imageLink"));
        }*//*
    }*/

    private static void getPic(String url,String header) throws Exception{
        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建get方式请求对象
        HttpGet httpGet = new HttpGet(url);
        //httpGet.addHeader("Content-type", "application/json");
        // 通过请求对象获取响应对象
        CloseableHttpResponse response = httpClient.execute(httpGet);

        if(response.getStatusLine().getStatusCode() == 200) {
            //得到实体
            HttpEntity entity = response.getEntity();
            byte[] data = EntityUtils.toByteArray(entity);
            //图片存入磁盘
            FileOutputStream fos = new FileOutputStream("/mnt/head/"+ header);
            fos.write(data);
            fos.close();
            System.out.println("图片下载成功!!!!");
        }
    }

}
