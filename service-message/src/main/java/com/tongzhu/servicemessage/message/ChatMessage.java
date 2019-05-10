package com.tongzhu.servicemessage.message;

import java.io.Serializable;

public class ChatMessage extends RootMessage implements Serializable {

    private String sender;

    public ChatMessage() {
    }

    public ChatMessage(String type, String content) {
        super(type, content);
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
