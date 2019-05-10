package com.tongzhu.servicemessage.message;

import java.io.Serializable;

public class OperateMessage extends RootMessage implements Serializable {
    private Integer code;

    public OperateMessage() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public OperateMessage(String type, String content, Integer code) {
        super(type, content);
        this.code = code;
    }
}
