package com.tongzhu.servicemessage.message;

import java.io.Serializable;

public class ReceivedTrumpetMessage extends RootMessage implements Serializable {

    private String senderName;


    public ReceivedTrumpetMessage(String type, String content, String senderName) {
        super(type, content);
        this.senderName = senderName;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
