package com.tongzhu.servicemessage.message;

import java.io.Serializable;

public class SingleChatMessage extends RootMessage implements Serializable {

    private String receiver;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
