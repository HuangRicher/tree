package com.tongzhu.friend.domain;

public class GameNPC {
    private Integer id;

    private String name;

    private Integer head;

    private Integer model;

    private Integer levelAdd;

    private Integer profession;

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

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public Integer getLevelAdd() {
        return levelAdd;
    }

    public void setLevelAdd(Integer levelAdd) {
        this.levelAdd = levelAdd;
    }

    public Integer getProfession() {
        return profession;
    }

    public void setProfession(Integer profession) {
        this.profession = profession;
    }
}