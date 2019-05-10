package com.tongzhu.servicemessage.util;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.netty.util.internal.PlatformDependent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class GlobalUserUtil {

    //保存全局的  连接上服务器的客户
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    //public static Map<String, String> users=new ConcurrentHashMap<>();

    public static ChannelGroup loginUsers = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static ConcurrentMap<String, Channel> serverChannels = PlatformDependent.newConcurrentHashMap();
}
