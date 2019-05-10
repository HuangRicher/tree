package com.tongzhu.servicemessage.message.request;

import java.io.Serializable;

public class RequestPacket implements Serializable {


    // 登陆:login   聊天:chat
    private String type;

    //消息体
    private String content;

    //群聊:roomChat  世界聊:worldChat
    private String chatWay;

    public RequestPacket() {
    }

    public RequestPacket(String type, String content, String chatWay) {
        this.type = type;
        this.content = content;
        this.chatWay = chatWay;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChatWay() {
        return chatWay;
    }

    public void setChatWay(String chatWay) {
        this.chatWay = chatWay;
    }

}
