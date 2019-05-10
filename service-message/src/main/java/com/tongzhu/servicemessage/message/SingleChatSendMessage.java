package com.tongzhu.servicemessage.message;

import java.io.Serializable;

public class SingleChatSendMessage extends WorldChatMessage implements Serializable {
    private String receiveUserId;

    public String getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(String receiveUserId) {
        this.receiveUserId = receiveUserId;
    }
}
