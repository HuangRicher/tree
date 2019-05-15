package com.tongzhu.treehousegateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import com.tongzhu.treehousegateway.cache.UserAccessCache;
import com.tongzhu.treehousegateway.redis.RedisService;
import com.tongzhu.treehousegateway.util.AESOperator;
import com.tongzhu.util.EscapeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;


@Component
public class PreFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(PreFilter.class);

    @Autowired
    private RedisService redisService;



    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //log.info("{} >>> {}", request.getMethod(), request.getRequestURL());
        try {
            StringBuffer method=request.getRequestURL();

            if(method.indexOf("/api-user/wxLogin/roleLogin")!=-1 ||
                    method.indexOf("/api-user/wxLogin/wxLogin")!=-1 ||
                    method.indexOf("/api-user/wxLogin/testLogin")!=-1 ||
                    method.indexOf("/api-user/wxLogin/register")!=-1 ||
                    method.indexOf("/api-user/wxLogin/resetPassword")!=-1 ||
                    method.indexOf("/api-user/userRole/addUserRole")!=-1||
                    method.indexOf("/api-user/wxLogin/getCheckCode")!=-1||
                    method.indexOf("/api-user/wxLogin/wxLoginGame")!=-1||
                    method.indexOf("/api-user/wxLogin/systemMaintenance")!=-1){
                /*String requestBody=new MyRequestWrapper(request).getBody();
                JSONObject object= JSON.parseObject(requestBody);
                if(StringUtils.isBlank(object.getString("account"))) {
                    log.warn("异常登陆！");
                    ctx.setSendZuulResponse(false);
                    ctx.setResponseStatusCode(401);
                    HttpServletResponse response=ctx.getResponse();
                    response.setHeader("Content-type", "text/html;charset=UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter pw = response.getWriter();
                    pw.write("异常登陆！");
                }*/
            }else{
                String requestBody=new MyRequestWrapper(request).getBody();
                if (StringUtils.isNotBlank(requestBody)) {
                    JSONObject object= JSON.parseObject(requestBody);
                    if(StringUtils.isBlank(object.getString("token")) ) {
                        log.warn("登陆超时！");
                        ctx.setSendZuulResponse(false);
                        ctx.setResponseStatusCode(401);
                        HttpServletResponse response=ctx.getResponse();
                        response.setHeader("Content-type", "text/html;charset=UTF-8");
                        response.setCharacterEncoding("UTF-8");
                        PrintWriter pw = response.getWriter();
                        pw.write("登陆超时,请登录！");
                    }else{
                        String account=AESOperator.getInstance().decrypt(object.getString("token"));
                        if(StringUtils.isNotBlank(account) && account.split("_").length==3){
                            String token=(String)redisService.get(account.split("_")[0]);
                            if(!token.equals(object.getString("token"))){
                                log.warn("登陆超时！");
                                ctx.setSendZuulResponse(false);
                                ctx.setResponseStatusCode(401);
                                HttpServletResponse response=ctx.getResponse();
                                response.setHeader("Content-type", "text/html;charset=UTF-8");
                                response.setCharacterEncoding("UTF-8");
                                PrintWriter pw = response.getWriter();
                                pw.write("登陆超时,请登录！");
                            }
                        }else{
                            log.warn("登陆超时！");
                            ctx.setSendZuulResponse(false);
                            ctx.setResponseStatusCode(401);
                            HttpServletResponse response=ctx.getResponse();
                            response.setHeader("Content-type", "text/html;charset=UTF-8");
                            response.setCharacterEncoding("UTF-8");
                            PrintWriter pw = response.getWriter();
                            pw.write("登陆超时,请登录！");
                        }
                    }
                    String account=AESOperator.getInstance().decrypt(object.getString("token"));
                    if(UserAccessCache.users.containsKey(account)){
                        log.warn("用户频繁请求！");
                        ctx.setSendZuulResponse(false);
                        ctx.setResponseStatusCode(5000);
                        HttpServletResponse response=ctx.getResponse();
                        response.setHeader("Content-type", "text/html;charset=UTF-8");
                        response.setCharacterEncoding("UTF-8");
                        PrintWriter pw = response.getWriter();
                        pw.write("请求太频繁！");
                    }else{
                        UserAccessCache.users.put(account,account);
                        HttpServletResponse response=ctx.getResponse();
                        response.setHeader("SWSD-ID",account);
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }


    private void decode(){
        // 获取到request
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        // 获取请求参数name
        String name = "";
        try {
            // 请求方法
            String method = request.getMethod();
            log.info(String.format("%s >>> %s", method, request.getRequestURL().toString()));
            // 获取请求的输入流
            InputStream in = request.getInputStream();
            String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
            // 如果body为空初始化为空json
            if (StringUtils.isBlank(body)) {
                body = "{}";
            }
            log.info("body" + body);
            // 转化成json
            JSONObject json = JSON.parseObject(body);
            // get方法和post、put方法处理方式不同
            if ("GET".equals(method)) {
                // 获取请求参数name
                name = request.getParameter("name");
                if (name != null) {
                    // 关键步骤，一定要get一下,下面才能取到值requestQueryParams
                    request.getParameterMap();
                    Map<String, List<String>> requestQueryParams = ctx.getRequestQueryParams();
                    if (requestQueryParams == null) {
                        requestQueryParams = new HashMap<>();
                    }
                    List<String> arrayList = new ArrayList<>();
                    String aes_decodedStr = EscapeUtil.unescape(name);
                    arrayList.add(aes_decodedStr + "");
                    requestQueryParams.put("decodename", arrayList);
                    ctx.setRequestQueryParams(requestQueryParams);
                }
            }// post和put需重写HttpServletRequestWrapper
            else if ("POST".equals(method) || "PUT".equals(method)) {
                // 获取请求参数name
                name = json.getString("name");
                if (name != null) {
                    String aes_decodedStr = EscapeUtil.unescape(name);
                    log.info("解密：" + aes_decodedStr);
                    // 把解密之后的参数放到json里
                    json.put("decodename", aes_decodedStr);
                    String newBody = json.toString();
                    log.info("newBody" + newBody);
                    final byte[] reqBodyBytes = newBody.getBytes();
                    // 重写上下文的HttpServletRequestWrapper
                    ctx.setRequest(new HttpServletRequestWrapper(request) {
                        @Override
                        public ServletInputStream getInputStream() throws IOException {
                            return new ServletInputStreamWrapper(reqBodyBytes);
                        }
                        @Override
                        public int getContentLength() {
                            return reqBodyBytes.length;
                        }

                        @Override
                        public long getContentLengthLong() {
                            return reqBodyBytes.length;
                        }
                    });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}