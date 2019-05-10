package com.tongzhu.welfare.domain;

import java.io.Serializable;

public class ChatSendMessage implements Serializable {

    public ChatSendMessage() {
    }

    public ChatSendMessage(String type, String ring, String userName,int code) {
        this.type = type;
        this.ring = ring;
        this.userName = userName;
        this.code = code;
    }

    private String type;
    private Integer code;
    private String ring;
    private String userName;
    	
	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("ChatSendMessage{");
		buffer.append("type='").append(type).append("'");
		buffer.append("ring='").append(ring).append("'");
		buffer.append("userName='").append(userName).append("'");
		buffer.append("code='").append(code).append("'");
		buffer.append("}");
		return buffer.toString();
	}
    	
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

	public String getRing() {
		return ring;
	}

	public void setRing(String ring) {
		this.ring = ring;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    
    
}
