package com.tongzhu.user.model;

public class ExplorationSetting {
    private String id;

    private Integer explorationId;

    private Integer passId;

    private Integer firstExp;

    private Integer nexExp;

    private Integer treasureBox;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getExplorationId() {
        return explorationId;
    }

    public void setExplorationId(Integer explorationId) {
        this.explorationId = explorationId;
    }

    public Integer getPassId() {
        return passId;
    }

    public void setPassId(Integer passId) {
        this.passId = passId;
    }

    public Integer getFirstExp() {
        return firstExp;
    }

    public void setFirstExp(Integer firstExp) {
        this.firstExp = firstExp;
    }

    public Integer getNexExp() {
        return nexExp;
    }

    public void setNexExp(Integer nexExp) {
        this.nexExp = nexExp;
    }

    public Integer getTreasureBox() {
        return treasureBox;
    }

    public void setTreasureBox(Integer treasureBox) {
        this.treasureBox = treasureBox;
    }
}