package com.tongzhu.fishing.po;

import java.io.Serializable;

public class DialPO implements Serializable {

    private String userId;

    private String friendsId;

    private String dropCoinsJson;

    private String configJson;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendsId() {
        return friendsId;
    }

    public void setFriendsId(String friendsId) {
        this.friendsId = friendsId;
    }

    public String getConfigJson() {
        return configJson;
    }

    public void setConfigJson(String configJson) {
        this.configJson = configJson;
    }

    public String getDropCoinsJson() {
        return dropCoinsJson;
    }

    public void setDropCoinsJson(String dropCoinsJson) {
        this.dropCoinsJson = dropCoinsJson;
    }
}
