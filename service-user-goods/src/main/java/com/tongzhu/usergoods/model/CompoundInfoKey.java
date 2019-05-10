package com.tongzhu.usergoods.model;

public class CompoundInfoKey {
    private String basicItems;

    private String composite;

    public String getBasicItems() {
        return basicItems;
    }

    public void setBasicItems(String basicItems) {
        this.basicItems = basicItems == null ? null : basicItems.trim();
    }

    public String getComposite() {
        return composite;
    }

    public void setComposite(String composite) {
        this.composite = composite == null ? null : composite.trim();
    }
}