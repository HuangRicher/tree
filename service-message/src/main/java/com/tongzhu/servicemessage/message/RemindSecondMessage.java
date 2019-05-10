package com.tongzhu.servicemessage.message;

import com.tongzhu.servicemessage.message.response.RedTipResponsePacket;

import java.io.Serializable;

public class RemindSecondMessage extends RedTipResponsePacket implements Serializable {
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
