package com.tongzhu.servicemessage.message.response;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class RedTipResponsePacket implements Serializable {
    private String type;
    private List<String> models;
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getModels() {
        return models;
    }

    public void setModels(List<String> models) {
        this.models = models;
    }
}
