package com.tongzhu.user.service.vo;

import com.tongzhu.user.model.ExplorationUser;

import java.io.Serializable;
import java.util.List;

public class ExplorationUserVO implements Serializable {

    private Integer explorationId;

    private Integer status;

    private List<ExplorationUser> items;

    public Integer getExplorationId() {
        return explorationId;
    }

    public void setExplorationId(Integer explorationId) {
        this.explorationId = explorationId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ExplorationUser> getItems() {
        return items;
    }

    public void setItems(List<ExplorationUser> items) {
        this.items = items;
    }
}
