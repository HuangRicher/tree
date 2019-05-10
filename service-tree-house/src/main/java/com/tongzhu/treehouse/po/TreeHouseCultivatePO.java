package com.tongzhu.treehouse.po;

import com.tongzhu.treehouse.model.TreeHouseFurniture;

import java.io.Serializable;
import java.util.List;

public class TreeHouseCultivatePO implements Serializable {

    private String id;

    private String inviteeUserId;

    private String targetUserId;

    private String outUserId;

    private String treeHouseUserId;

    private String treeHouseId;

    private String userId;

    private String inviterId;

    private String goodsId;

    private String flowerpotId;

    private Integer style;

    private String  locationId;

    private Integer type;

    private Integer grade;

    private Integer workType;

    private String workerUserId;

    private Integer location;

    private String workId;

    private List<TreeHouseFurniture> furnitures;

    public List<TreeHouseFurniture> getFurnitures() {
        return furnitures;
    }

    public void setFurnitures(List<TreeHouseFurniture> furnitures) {
        this.furnitures = furnitures;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getFlowerpotId() {
        return flowerpotId;
    }

    public void setFlowerpotId(String flowerpotId) {
        this.flowerpotId = flowerpotId;
    }

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getInviterId() {
        return inviterId;
    }

    public void setInviterId(String inviterId) {
        this.inviterId = inviterId;
    }

    public String getTreeHouseUserId() {
        return treeHouseUserId;
    }

    public void setTreeHouseUserId(String treeHouseUserId) {
        this.treeHouseUserId = treeHouseUserId;
    }

    public String getTreeHouseId() {
        return treeHouseId;
    }

    public void setTreeHouseId(String treeHouseId) {
        this.treeHouseId = treeHouseId;
    }

    public String getInviteeUserId() {
        return inviteeUserId;
    }

    public void setInviteeUserId(String inviteeUserId) {
        this.inviteeUserId = inviteeUserId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOutUserId() {
        return outUserId;
    }

    public void setOutUserId(String outUserId) {
        this.outUserId = outUserId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(String targetUserId) {
        this.targetUserId = targetUserId;
    }

    public Integer getWorkType() {
        return workType;
    }

    public void setWorkType(Integer workType) {
        this.workType = workType;
    }

    public String getWorkerUserId() {
        return workerUserId;
    }

    public void setWorkerUserId(String workerUserId) {
        this.workerUserId = workerUserId;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }
}
