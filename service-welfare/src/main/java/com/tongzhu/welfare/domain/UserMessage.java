package com.tongzhu.welfare.domain;

import java.io.Serializable;
import java.util.Date;

public class UserMessage implements Serializable {
    private String id;

    private String receiverId;

    private String senderId;

    private String messageBody;

    private Integer status;
    
    private Integer type;

    private Date createDate;

    private Date surplusDate;

    
    
    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId == null ? null : receiverId.trim();
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId == null ? null : senderId.trim();
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody == null ? null : messageBody.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getSurplusDate() {
        return surplusDate;
    }

    public void setSurplusDate(Date surplusDate) {
        this.surplusDate = surplusDate;
    }
}