package com.tongzhu.servicemessage.message.response;

import java.io.Serializable;

public class ResponsePacket implements Serializable {

    // 0正常  20000：异常
    private Integer code;

    // 登陆:login   聊天:chat
    private String type;

    //消息体
    private String content;

    //群聊:roomChat  世界聊:worldChat
    private String chatWay;

    private String id;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
