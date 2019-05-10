package com.tongzhu.servicemessage.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongzhu.constant.RedisKey;
import com.tongzhu.servicemessage.action.ActionMapUtil;
import com.tongzhu.servicemessage.attribute.Attributes;
import com.tongzhu.servicemessage.constant.ActionConstant;
import com.tongzhu.servicemessage.constant.MessageConstant;
import com.tongzhu.servicemessage.message.RootMessage;
import com.tongzhu.servicemessage.message.SingleChatMessage;
import com.tongzhu.servicemessage.redis.RedisService;
import com.tongzhu.servicemessage.service.OperationService;
import com.tongzhu.servicemessage.session.Session;
import com.tongzhu.servicemessage.util.AESOperator;
import com.tongzhu.servicemessage.util.GlobalUserUtil;
import com.tongzhu.servicemessage.util.SensitiveWordFilterUtil;
import com.tongzhu.util.UUIDUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MyChannelHandler extends SimpleChannelInboundHandler<Object> {


    private static final Logger logger = LoggerFactory.getLogger(MyChannelHandler.class);

    private static final String URI = "message";

    private WebSocketServerHandshaker handshaker ;

    private OperationService operationService;

    private RedisService redisService;

    public MyChannelHandler() {}

    public MyChannelHandler(OperationService operationService,RedisService redisService) {
        this.operationService = operationService;
        this.redisService=redisService;
    }


    /**
     * 连接上服务器
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        logger.info("【handlerAdded】====>"+ctx.channel().id().asLongText());
        GlobalUserUtil.channels.add(ctx.channel());
    }

    /**
     * 断开连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        logger.info("【handlerRemoved】====>"+ctx.channel().id().asLongText());
        if(ctx.channel().attr(Attributes.SESSION).get()!=null){
            ActionMapUtil.invoke(ActionConstant.LOGOUT,ctx);
        } else{
            GlobalUserUtil.channels.remove(ctx.channel());
        }
    }

    /**
     * 连接异常   需要关闭相关资源
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("【系统异常】======>"+cause.toString());
        ctx.channel().close();
        ctx.close();
    }

    /**
     * 活跃的通道  也可以当作用户连接上客户端进行使用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("【channelActive】=====>"+ctx.channel());
    }

    /**
     * 不活跃的通道  就说明用户失去连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    }

    /**
     * 这里只要完成 flush
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * 这里是保持服务器与客户端长连接  进行心跳检测 避免连接断开
     * @param ctx
     * @param evt
     * @throws Exception
     */
    /*@Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent stateEvent = (IdleStateEvent) evt;
            PingWebSocketFrame ping = new PingWebSocketFrame();
            switch (stateEvent.state()){
                //读空闲（服务器端）
                case READER_IDLE:
                    logger.info("【"+ctx.channel().remoteAddress()+"】读空闲（服务器端）");
                    ctx.writeAndFlush(ping);
                    break;
                    //写空闲（客户端）
                case WRITER_IDLE:
                    logger.info("【"+ctx.channel().remoteAddress()+"】写空闲（客户端）");
                    ctx.writeAndFlush(ping);
                    break;
                case ALL_IDLE:
                    logger.info("【"+ctx.channel().remoteAddress()+"】读写空闲");
                    break;
            }
        }
    }*/

    /**
     * websocket消息处理
     * @param ctx
     * @param msg
     */
    private void doHandlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame msg) {
        //判断msg 是哪一种类型  分别做出不同的反应
        if(msg instanceof CloseWebSocketFrame){
            logger.info("【关闭】");
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) msg);
            return ;
        }
        if(msg instanceof PingWebSocketFrame){
            logger.info("【ping】");
            PongWebSocketFrame pong = new PongWebSocketFrame(msg.content().retain());
            ctx.channel().writeAndFlush(pong);
            return ;
        }
        if(msg instanceof PongWebSocketFrame){
            logger.info("【pong】");
            PingWebSocketFrame ping = new PingWebSocketFrame(msg.content().retain());
            ctx.channel().writeAndFlush(ping);
            return ;
        }
        if(!(msg instanceof TextWebSocketFrame)){
            logger.info("【不支持二进制】");
            throw new UnsupportedOperationException("不支持二进制");
        }
        //可以对消息进行处理
        //群发
        String messageBody=((TextWebSocketFrame) msg).text();
        if(StringUtils.isNotBlank(messageBody)){
            try {
                JSONObject message= JSON.parseObject(messageBody);
                if(message.get("type")!=null && StringUtils.isNotBlank(message.getString("type"))){
                    if(message.get("token")!=null ){
                        if(StringUtils.isNotBlank(message.getString("token"))){
                            String account=AESOperator.getInstance().decrypt(message.getString("token"));
                            String userIdCC=account.split("_")[1];
                            GlobalUserUtil.loginUsers.add(ctx.channel());
                            if(GlobalUserUtil.serverChannels.get(userIdCC)!=null){
                                Map<String,Object> kickOut=new HashMap<>();
                                kickOut.put("type","kickOut");
                                kickOut.put("code",0);
                                GlobalUserUtil.serverChannels.get(userIdCC).writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(kickOut)));
                                if(GlobalUserUtil.serverChannels.get(userIdCC).attr(Attributes.SESSION).get()!=null)
                                    ActionMapUtil.invoke(ActionConstant.KICKOUT_CLEAR_DATA,GlobalUserUtil.serverChannels.get(userIdCC));
                            }
                            Session session=new Session(message.getString("token"),userIdCC,new Date());
                            ctx.channel().attr(Attributes.SESSION).set(session);
                            GlobalUserUtil.serverChannels.put(userIdCC,ctx.channel());
                            ActionMapUtil.invoke(ActionConstant.LOGIN,ctx,userIdCC);
                        }else{
                            Map<String,Object> map=new HashMap<>();
                            map.put("type",MessageConstant.LOGIN);
                            map.put("code",90000);
                            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(map)));
                        }
                    }
                    if(message.get("content")!=null && StringUtils.isNotBlank(message.getString("content"))){
                        switch (message.getString("type")){
                            case MessageConstant.HEART:
                                Map<String,Object> map=new HashMap<>();
                                map.put("type",MessageConstant.HEART);
                                map.put("code",0);
                                ctx.channel().writeAndFlush(JSON.toJSONString(map));
                                break;
                            case MessageConstant.CHAT:
                                if(StringUtils.isNotBlank(message.getString("chatWay"))){
                                    SensitiveWordFilterUtil filter=new SensitiveWordFilterUtil();
                                    switch (message.getString("chatWay")){
                                        case MessageConstant.WORLDCHAT:
                                            RootMessage sendMsg=JSON.parseObject(messageBody,RootMessage.class);
                                            Session session=ctx.channel().attr(Attributes.SESSION).get();
                                            String currentUserId=ctx.channel().attr(Attributes.SESSION).get().getUserId();
                                            ActionMapUtil.invoke(ActionConstant.WORLD_CHAT,ctx,currentUserId,filter.replaceSensitiveWord(sendMsg.getContent(),1,"*"));
                                            break;
                                        case MessageConstant.SINGLECHAT:
                                            SingleChatMessage singleMsg=JSON.parseObject(messageBody,SingleChatMessage.class);
                                            String singleUserId=ctx.channel().attr(Attributes.SESSION).get().getUserId();
                                            ActionMapUtil.invoke(ActionConstant.SINGLE_CHAT,ctx.channel(),singleUserId,filter.replaceSensitiveWord(singleMsg.getContent(),1,"*"),singleMsg.getReceiver());
                                            break;
                                        case MessageConstant.READ:
                                            if(StringUtils.isNotBlank(message.getString("content"))){
                                                ActionMapUtil.invoke(ActionConstant.CHAT_READ,ctx,message.getString("content"));
                                            }
                                            break;
                                        case MessageConstant.TRUMPET:
                                            RootMessage trumpetMessage=JSON.parseObject(messageBody,RootMessage.class);
                                            String trumpetUserId=ctx.channel().attr(Attributes.SESSION).get().getUserId();
                                            ActionMapUtil.invoke(ActionConstant.SEND_TRUMPET,ctx,trumpetUserId, filter.replaceSensitiveWord(trumpetMessage.getContent(),1,"*"));
                                            break;
                                        case MessageConstant.WEDDINGCHAT:
                                            String wedChatUserId=ctx.channel().attr(Attributes.SESSION).get().getUserId();
                                            if(StringUtils.isNotBlank(message.getString("content"))){
                                                ActionMapUtil.invoke(ActionConstant.CHAT_WEDDING,ctx,wedChatUserId,message.getString("content"));
                                            }
                                            break;
                                        case MessageConstant.WEDDINGROOMCHAT:
                                            String wedRoomChatUserId=ctx.channel().attr(Attributes.SESSION).get().getUserId();
                                            if(StringUtils.isNotBlank(message.getString("content"))){
                                                ActionMapUtil.invoke(ActionConstant.CHAT_WEDDING_ROOM,ctx,wedRoomChatUserId,message.getString("content"),message.getString("receiver"));
                                            }
                                            break;
                                        case MessageConstant.CHAT_IN_TREE_HOUSE:
                                            String treeHouseChatUserId=ctx.channel().attr(Attributes.SESSION).get().getUserId();
                                            ActionMapUtil.invoke(ActionConstant.SEND_TREE_HOUSE,ctx,treeHouseChatUserId,message.getString("content"));
                                            break;
                                    }
                                }
                                break;

                            case MessageConstant.OPERATION:
                                String currentUserId=ctx.channel().attr(Attributes.SESSION).get().getUserId();
                                String method=message.getString("method");
                                String userId=message.getString("treeHouse");
                                if(method.equals(ActionConstant.COME_IN_TREE_HOUSE)){
                                    ActionMapUtil.invoke(ActionConstant.COMEIN_TREEHOUSE,ctx,currentUserId, userId);
                                }
                                if(method.equals(ActionConstant.LEAVE_TREE_HOUSE)){
                                    ActionMapUtil.invoke(ActionConstant.OUT_TREEHOUSE,ctx.channel(),currentUserId, userId);
                                }
                                if(method.equals(ActionConstant.NO_SPEAK_TREE_HOUSE)){
                                    ActionMapUtil.invoke(ActionConstant.NO_SPEAK_IN_TREE_HOUSE,ctx,currentUserId, userId);
                                }
                                break;
                            case MessageConstant.NOTICE:
                                break;
                        }
                    }
                }
                if(message.get("type")!=null && StringUtils.isNotBlank(message.getString("type")) &&
                        message.get("requestType")!=null && StringUtils.isNotBlank(message.getString("requestType"))){
                    switch (message.getString("requestType")){
                        case MessageConstant.CHAT:
                            String currentUserId=ctx.channel().attr(Attributes.SESSION).get().getUserId();
                            ActionMapUtil.invoke(ActionConstant.CHAT_RED_TIP,ctx, currentUserId);
                            break;
                    }

                }
            }catch (Exception e){
                logger.info(ctx.channel().id().asLongText()+"非法登陆发送");
                JSONObject object=new JSONObject();
                object.put("code",10000);
                object.put("content","消息格式错误");
                ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(object)));
                //ctx.close();
            }
        }
    }


    /**
     * wetsocket第一次连接握手
     * @param ctx
     * @param msg
     */
    private void doHandlerHttpRequest(ChannelHandlerContext ctx, HttpRequest msg) {
        // http 解码失败
        if(!msg.decoderResult().isSuccess() || (!"websocket".equals(msg.headers().get("Upgrade")))){
            sendHttpResponse(ctx, (FullHttpRequest) msg,new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
        }
        //可以获取msg的uri来判断
        String uri = msg.uri();
        if(!uri.substring(1).equals(URI)){
            ctx.close();
        }
        ctx.channel().attr(AttributeKey.valueOf("type")).set(uri);
        //可以通过url获取其他参数
        WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory(
                "ws://"+msg.headers().get("Host")+"/"+URI,null,false
        );
        handshaker = factory.newHandshaker(msg);
        if(handshaker == null){
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        }
        //进行连接
        handshaker.handshake(ctx.channel(), (FullHttpRequest) msg);
        //可以做其他处理
    }

    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        // 返回应答给客户端
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!HttpUtil.isKeepAlive(req) || res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    /**
     * 收发消息处理
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof HttpRequest){
            doHandlerHttpRequest(ctx,(HttpRequest) msg);
        }else if(msg instanceof WebSocketFrame){
            doHandlerWebSocketFrame(ctx,(WebSocketFrame) msg);
        }
    }
}
