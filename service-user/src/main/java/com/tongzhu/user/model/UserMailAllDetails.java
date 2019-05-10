package com.tongzhu.user.model;

public class UserMailAllDetails extends UserMailAllDetailsKey {
    private Integer read;

    private Integer receive;

    private Integer status;

    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
    }

    public Integer getReceive() {
        return receive;
    }

    public void setReceive(Integer receive) {
        this.receive = receive;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}