package com.tongzhu.user.controller.vo;

import com.tongzhu.user.model.UserMessage;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/3/27 0027.
 */
public class UserMessageVO extends UserMessage implements Serializable {
    private String senderName;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
