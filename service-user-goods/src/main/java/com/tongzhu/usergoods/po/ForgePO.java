package com.tongzhu.usergoods.po;

/**
 * Created by Administrator on 2018/11/20 0020.
 */
public class ForgePO {

    private String id;

    private String userId;

    private String strongFossil;

    private String safeguard;

    private Integer consume;

    private String transferId;

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStrongFossil() {
        return strongFossil;
    }

    public void setStrongFossil(String strongFossil) {
        this.strongFossil = strongFossil;
    }

    public String getSafeguard() {
        return safeguard;
    }

    public void setSafeguard(String safeguard) {
        this.safeguard = safeguard;
    }

    public Integer getConsume() {
        return consume;
    }

    public void setConsume(Integer consume) {
        this.consume = consume;
    }
}
