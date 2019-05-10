package com.tongzhu.servicemessage.util;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

public class SslUtil {

    public static SSLContext createSSLContext(String type , String path , String password) throws Exception {
        KeyStore ks = KeyStore.getInstance(type); /// "JKS"
        InputStream ksInputStream = new FileInputStream(path); /// 证书存放地址
        ks.load(ksInputStream, password.toCharArray());
        //KeyManagerFactory充当基于密钥内容源的密钥管理器的工厂。
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());//getDefaultAlgorithm:获取默认的 KeyManagerFactory 算法名称。
        kmf.init(ks, password.toCharArray());
        //SSLContext的实例表示安全套接字协议的实现，它充当用于安全套接字工厂或 SSLEngine 的工厂。
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), null, null);

        return sslContext;
    }

    public static SslContext createSslContext(String type , String fileName , String password)throws Exception{
        KeyManagerFactory keyManagerFactory = null;
        KeyStore keyStore = KeyStore.getInstance(type);//JKS
        InputStream stream = SslUtil.class.getClassLoader().getResourceAsStream(fileName); //读取文件
        //keyStore.load(new FileInputStream(path), password.toCharArray());
        keyStore.load(stream, password.toCharArray());
        keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        keyManagerFactory.init(keyStore,password.toCharArray());
        SslContext sslContext = SslContextBuilder.forServer(keyManagerFactory).build();
        return sslContext;
    }
}
