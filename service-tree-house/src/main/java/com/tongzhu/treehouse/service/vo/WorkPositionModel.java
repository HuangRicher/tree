package com.tongzhu.treehouse.service.vo;

import com.tongzhu.treehouse.domain.UserWorkPosition;

import java.io.Serializable;

/**
 * 工位扩展类
 */
public class WorkPositionModel extends UserWorkPosition implements Serializable {

    private String portraitUrl;		//头像图片

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }
}
