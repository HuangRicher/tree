package com.tongzhu.user.model;

public class GameNPC {
    private Integer id;

    private String name;

    private Integer head;

    private String portraitUrl;

    private Integer levelAdd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getHead() {
        return head;
    }

    public void setHead(Integer head) {
        this.head = head;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl == null ? null : portraitUrl.trim();
    }

    public Integer getLevelAdd() {
        return levelAdd;
    }

    public void setLevelAdd(Integer levelAdd) {
        this.levelAdd = levelAdd;
    }
}