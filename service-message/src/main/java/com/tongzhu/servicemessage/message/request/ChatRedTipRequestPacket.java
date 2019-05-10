package com.tongzhu.servicemessage.message.request;

import java.io.Serializable;

public class ChatRedTipRequestPacket extends RequestPacket implements Serializable {
    // chat , mail
    private String requestType;

    private Integer code;

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
