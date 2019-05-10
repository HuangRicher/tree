package com.tongzhu.servicemessage.message;

import java.io.Serializable;

public class ChatSendMessage implements Serializable {

    private  Integer code;
    private String type;
    private String sender;
    private String content;
    private String chatWay;

    public ChatSendMessage() {
    }

    public ChatSendMessage(String type,String sender, String content) {
        this.type = type;
        this.sender = sender;
        this.content = content;
    }
    public ChatSendMessage(String type,String chatWay, String sender, String content,Integer code) {
        this.type = type;
        this.sender = sender;
        this.content = content;
        this.chatWay=chatWay;
        this.code=code;
    }


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getChatWay() {
        return chatWay;
    }

    public void setChatWay(String chatWay) {
        this.chatWay = chatWay;
    }
}
