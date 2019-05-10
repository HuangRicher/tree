package com.tongzhu.user.po;

import java.io.Serializable;

public class NewCopyFightPO implements Serializable {
    public Integer copyId;
    public String userId;

    public Integer getCopyId() {
        return copyId;
    }

    public void setCopyId(Integer copyId) {
        this.copyId = copyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
