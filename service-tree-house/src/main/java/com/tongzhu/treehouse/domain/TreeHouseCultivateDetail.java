package com.tongzhu.treehouse.domain;

import com.tongzhu.treehouse.mapper.vo.TreeHouseVisitorDO;
import com.tongzhu.treehouse.model.TreeHouseFlowerpot;
import com.tongzhu.treehouse.model.TreeHouseFurniture;

import java.io.Serializable;
import java.util.List;

public class TreeHouseCultivateDetail implements Serializable {

    private String treeHouseId;

    private String treeHouseUserName;

    //人气
    private Integer ambienceCount;

    //娱乐
    private Integer amusementCount;

    //环境
    private Integer environmentCount;

    //升下一级所需人气
    private Integer ambienceCountFull;

    //升下一级所需娱乐
    private Integer amusementCountFull;

    //升下一级所需环境
    private Integer environmentCountFull;

    //花盆
    private List<TreeHouseFlowerpot> flowerpots;

    //花盆
    private List<TreeHouseFlowerpotDO> flowerpotDOS;

    //家具
    private List<TreeHouseFurniture> furnitures;

    //游客
    private List<TreeHouseVisitorDO> visitors;
    private boolean canSpeak;

    //装修风格
    private Integer style;

    public String getTreeHouseId() {
        return treeHouseId;
    }

    public void setTreeHouseId(String treeHouseId) {
        this.treeHouseId = treeHouseId;
    }

    public Integer getAmbienceCount() {
        return ambienceCount;
    }

    public void setAmbienceCount(Integer ambienceCount) {
        this.ambienceCount = ambienceCount;
    }

    public Integer getAmusementCount() {
        return amusementCount;
    }

    public void setAmusementCount(Integer amusementCount) {
        this.amusementCount = amusementCount;
    }

    public Integer getEnvironmentCount() {
        return environmentCount;
    }

    public void setEnvironmentCount(Integer environmentCount) {
        this.environmentCount = environmentCount;
    }

    public List<TreeHouseFlowerpot> getFlowerpots() {
        return flowerpots;
    }

    public void setFlowerpots(List<TreeHouseFlowerpot> flowerpots) {
        this.flowerpots = flowerpots;
    }

    public List<TreeHouseFurniture> getFurnitures() {
        return furnitures;
    }

    public void setFurnitures(List<TreeHouseFurniture> furnitures) {
        this.furnitures = furnitures;
    }

    public List<TreeHouseVisitorDO> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<TreeHouseVisitorDO> visitors) {
        this.visitors = visitors;
    }

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public String getTreeHouseUserName() {
        return treeHouseUserName;
    }

    public void setTreeHouseUserName(String treeHouseUserName) {
        this.treeHouseUserName = treeHouseUserName;
    }

    public Integer getAmbienceCountFull() {
        return ambienceCountFull;
    }

    public void setAmbienceCountFull(Integer ambienceCountFull) {
        this.ambienceCountFull = ambienceCountFull;
    }

    public Integer getAmusementCountFull() {
        return amusementCountFull;
    }

    public void setAmusementCountFull(Integer amusementCountFull) {
        this.amusementCountFull = amusementCountFull;
    }

    public Integer getEnvironmentCountFull() {
        return environmentCountFull;
    }

    public void setEnvironmentCountFull(Integer environmentCountFull) {
        this.environmentCountFull = environmentCountFull;
    }

    public boolean isCanSpeak() {
        return canSpeak;
    }

    public void setCanSpeak(boolean canSpeak) {
        this.canSpeak = canSpeak;
    }

    public List<TreeHouseFlowerpotDO> getFlowerpotDOS() {
        return flowerpotDOS;
    }

    public void setFlowerpotDOS(List<TreeHouseFlowerpotDO> flowerpotDOS) {
        this.flowerpotDOS = flowerpotDOS;
    }
}
