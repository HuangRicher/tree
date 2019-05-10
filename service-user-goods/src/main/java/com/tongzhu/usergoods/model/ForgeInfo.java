package com.tongzhu.usergoods.model;

public class ForgeInfo {
    private Integer id;

    private Integer enchantlvl;

    private Double successRate;

    private String strongFossil;

    private Integer strongFossilNumber;

    private String consume;

    private Integer consumeAmount;

    private Integer refundConsume;

    private String safeguard;

    private Integer safeguardNumber;

    private Integer succeed;

    private Integer failure;

    private Double reinforceScale;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEnchantlvl() {
        return enchantlvl;
    }

    public void setEnchantlvl(Integer enchantlvl) {
        this.enchantlvl = enchantlvl;
    }

    public Double getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(Double successRate) {
        this.successRate = successRate;
    }

    public String getStrongFossil() {
        return strongFossil;
    }

    public void setStrongFossil(String strongFossil) {
        this.strongFossil = strongFossil == null ? null : strongFossil.trim();
    }

    public Integer getStrongFossilNumber() {
        return strongFossilNumber;
    }

    public void setStrongFossilNumber(Integer strongFossilNumber) {
        this.strongFossilNumber = strongFossilNumber;
    }

    public String getConsume() {
        return consume;
    }

    public void setConsume(String consume) {
        this.consume = consume == null ? null : consume.trim();
    }

    public Integer getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(Integer consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public Integer getRefundConsume() {
        return refundConsume;
    }

    public void setRefundConsume(Integer refundConsume) {
        this.refundConsume = refundConsume;
    }

    public String getSafeguard() {
        return safeguard;
    }

    public void setSafeguard(String safeguard) {
        this.safeguard = safeguard == null ? null : safeguard.trim();
    }

    public Integer getSafeguardNumber() {
        return safeguardNumber;
    }

    public void setSafeguardNumber(Integer safeguardNumber) {
        this.safeguardNumber = safeguardNumber;
    }

    public Integer getSucceed() {
        return succeed;
    }

    public void setSucceed(Integer succeed) {
        this.succeed = succeed;
    }

    public Integer getFailure() {
        return failure;
    }

    public void setFailure(Integer failure) {
        this.failure = failure;
    }

    public Double getReinforceScale() {
        return reinforceScale;
    }

    public void setReinforceScale(Double reinforceScale) {
        this.reinforceScale = reinforceScale;
    }
}