package com.tongzhu.servicemessage.message;

import java.io.Serializable;

public class Packet implements Serializable {

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
