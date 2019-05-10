package com.tongzhu.usergoods.model;

import java.util.ArrayList;
import java.util.List;

public class ForgeInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ForgeInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andEnchantlvlIsNull() {
            addCriterion("enchantlvl is null");
            return (Criteria) this;
        }

        public Criteria andEnchantlvlIsNotNull() {
            addCriterion("enchantlvl is not null");
            return (Criteria) this;
        }

        public Criteria andEnchantlvlEqualTo(Integer value) {
            addCriterion("enchantlvl =", value, "enchantlvl");
            return (Criteria) this;
        }

        public Criteria andEnchantlvlNotEqualTo(Integer value) {
            addCriterion("enchantlvl <>", value, "enchantlvl");
            return (Criteria) this;
        }

        public Criteria andEnchantlvlGreaterThan(Integer value) {
            addCriterion("enchantlvl >", value, "enchantlvl");
            return (Criteria) this;
        }

        public Criteria andEnchantlvlGreaterThanOrEqualTo(Integer value) {
            addCriterion("enchantlvl >=", value, "enchantlvl");
            return (Criteria) this;
        }

        public Criteria andEnchantlvlLessThan(Integer value) {
            addCriterion("enchantlvl <", value, "enchantlvl");
            return (Criteria) this;
        }

        public Criteria andEnchantlvlLessThanOrEqualTo(Integer value) {
            addCriterion("enchantlvl <=", value, "enchantlvl");
            return (Criteria) this;
        }

        public Criteria andEnchantlvlIn(List<Integer> values) {
            addCriterion("enchantlvl in", values, "enchantlvl");
            return (Criteria) this;
        }

        public Criteria andEnchantlvlNotIn(List<Integer> values) {
            addCriterion("enchantlvl not in", values, "enchantlvl");
            return (Criteria) this;
        }

        public Criteria andEnchantlvlBetween(Integer value1, Integer value2) {
            addCriterion("enchantlvl between", value1, value2, "enchantlvl");
            return (Criteria) this;
        }

        public Criteria andEnchantlvlNotBetween(Integer value1, Integer value2) {
            addCriterion("enchantlvl not between", value1, value2, "enchantlvl");
            return (Criteria) this;
        }

        public Criteria andSuccessRateIsNull() {
            addCriterion("success_rate is null");
            return (Criteria) this;
        }

        public Criteria andSuccessRateIsNotNull() {
            addCriterion("success_rate is not null");
            return (Criteria) this;
        }

        public Criteria andSuccessRateEqualTo(Double value) {
            addCriterion("success_rate =", value, "successRate");
            return (Criteria) this;
        }

        public Criteria andSuccessRateNotEqualTo(Double value) {
            addCriterion("success_rate <>", value, "successRate");
            return (Criteria) this;
        }

        public Criteria andSuccessRateGreaterThan(Double value) {
            addCriterion("success_rate >", value, "successRate");
            return (Criteria) this;
        }

        public Criteria andSuccessRateGreaterThanOrEqualTo(Double value) {
            addCriterion("success_rate >=", value, "successRate");
            return (Criteria) this;
        }

        public Criteria andSuccessRateLessThan(Double value) {
            addCriterion("success_rate <", value, "successRate");
            return (Criteria) this;
        }

        public Criteria andSuccessRateLessThanOrEqualTo(Double value) {
            addCriterion("success_rate <=", value, "successRate");
            return (Criteria) this;
        }

        public Criteria andSuccessRateIn(List<Double> values) {
            addCriterion("success_rate in", values, "successRate");
            return (Criteria) this;
        }

        public Criteria andSuccessRateNotIn(List<Double> values) {
            addCriterion("success_rate not in", values, "successRate");
            return (Criteria) this;
        }

        public Criteria andSuccessRateBetween(Double value1, Double value2) {
            addCriterion("success_rate between", value1, value2, "successRate");
            return (Criteria) this;
        }

        public Criteria andSuccessRateNotBetween(Double value1, Double value2) {
            addCriterion("success_rate not between", value1, value2, "successRate");
            return (Criteria) this;
        }

        public Criteria andStrongFossilIsNull() {
            addCriterion("strong_fossil is null");
            return (Criteria) this;
        }

        public Criteria andStrongFossilIsNotNull() {
            addCriterion("strong_fossil is not null");
            return (Criteria) this;
        }

        public Criteria andStrongFossilEqualTo(String value) {
            addCriterion("strong_fossil =", value, "strongFossil");
            return (Criteria) this;
        }

        public Criteria andStrongFossilNotEqualTo(String value) {
            addCriterion("strong_fossil <>", value, "strongFossil");
            return (Criteria) this;
        }

        public Criteria andStrongFossilGreaterThan(String value) {
            addCriterion("strong_fossil >", value, "strongFossil");
            return (Criteria) this;
        }

        public Criteria andStrongFossilGreaterThanOrEqualTo(String value) {
            addCriterion("strong_fossil >=", value, "strongFossil");
            return (Criteria) this;
        }

        public Criteria andStrongFossilLessThan(String value) {
            addCriterion("strong_fossil <", value, "strongFossil");
            return (Criteria) this;
        }

        public Criteria andStrongFossilLessThanOrEqualTo(String value) {
            addCriterion("strong_fossil <=", value, "strongFossil");
            return (Criteria) this;
        }

        public Criteria andStrongFossilLike(String value) {
            addCriterion("strong_fossil like", value, "strongFossil");
            return (Criteria) this;
        }

        public Criteria andStrongFossilNotLike(String value) {
            addCriterion("strong_fossil not like", value, "strongFossil");
            return (Criteria) this;
        }

        public Criteria andStrongFossilIn(List<String> values) {
            addCriterion("strong_fossil in", values, "strongFossil");
            return (Criteria) this;
        }

        public Criteria andStrongFossilNotIn(List<String> values) {
            addCriterion("strong_fossil not in", values, "strongFossil");
            return (Criteria) this;
        }

        public Criteria andStrongFossilBetween(String value1, String value2) {
            addCriterion("strong_fossil between", value1, value2, "strongFossil");
            return (Criteria) this;
        }

        public Criteria andStrongFossilNotBetween(String value1, String value2) {
            addCriterion("strong_fossil not between", value1, value2, "strongFossil");
            return (Criteria) this;
        }

        public Criteria andStrongFossilNumberIsNull() {
            addCriterion("strong_fossil_number is null");
            return (Criteria) this;
        }

        public Criteria andStrongFossilNumberIsNotNull() {
            addCriterion("strong_fossil_number is not null");
            return (Criteria) this;
        }

        public Criteria andStrongFossilNumberEqualTo(Integer value) {
            addCriterion("strong_fossil_number =", value, "strongFossilNumber");
            return (Criteria) this;
        }

        public Criteria andStrongFossilNumberNotEqualTo(Integer value) {
            addCriterion("strong_fossil_number <>", value, "strongFossilNumber");
            return (Criteria) this;
        }

        public Criteria andStrongFossilNumberGreaterThan(Integer value) {
            addCriterion("strong_fossil_number >", value, "strongFossilNumber");
            return (Criteria) this;
        }

        public Criteria andStrongFossilNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("strong_fossil_number >=", value, "strongFossilNumber");
            return (Criteria) this;
        }

        public Criteria andStrongFossilNumberLessThan(Integer value) {
            addCriterion("strong_fossil_number <", value, "strongFossilNumber");
            return (Criteria) this;
        }

        public Criteria andStrongFossilNumberLessThanOrEqualTo(Integer value) {
            addCriterion("strong_fossil_number <=", value, "strongFossilNumber");
            return (Criteria) this;
        }

        public Criteria andStrongFossilNumberIn(List<Integer> values) {
            addCriterion("strong_fossil_number in", values, "strongFossilNumber");
            return (Criteria) this;
        }

        public Criteria andStrongFossilNumberNotIn(List<Integer> values) {
            addCriterion("strong_fossil_number not in", values, "strongFossilNumber");
            return (Criteria) this;
        }

        public Criteria andStrongFossilNumberBetween(Integer value1, Integer value2) {
            addCriterion("strong_fossil_number between", value1, value2, "strongFossilNumber");
            return (Criteria) this;
        }

        public Criteria andStrongFossilNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("strong_fossil_number not between", value1, value2, "strongFossilNumber");
            return (Criteria) this;
        }

        public Criteria andConsumeIsNull() {
            addCriterion("consume is null");
            return (Criteria) this;
        }

        public Criteria andConsumeIsNotNull() {
            addCriterion("consume is not null");
            return (Criteria) this;
        }

        public Criteria andConsumeEqualTo(String value) {
            addCriterion("consume =", value, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeNotEqualTo(String value) {
            addCriterion("consume <>", value, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeGreaterThan(String value) {
            addCriterion("consume >", value, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeGreaterThanOrEqualTo(String value) {
            addCriterion("consume >=", value, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeLessThan(String value) {
            addCriterion("consume <", value, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeLessThanOrEqualTo(String value) {
            addCriterion("consume <=", value, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeLike(String value) {
            addCriterion("consume like", value, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeNotLike(String value) {
            addCriterion("consume not like", value, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeIn(List<String> values) {
            addCriterion("consume in", values, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeNotIn(List<String> values) {
            addCriterion("consume not in", values, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeBetween(String value1, String value2) {
            addCriterion("consume between", value1, value2, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeNotBetween(String value1, String value2) {
            addCriterion("consume not between", value1, value2, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeAmountIsNull() {
            addCriterion("consume_amount is null");
            return (Criteria) this;
        }

        public Criteria andConsumeAmountIsNotNull() {
            addCriterion("consume_amount is not null");
            return (Criteria) this;
        }

        public Criteria andConsumeAmountEqualTo(Integer value) {
            addCriterion("consume_amount =", value, "consumeAmount");
            return (Criteria) this;
        }

        public Criteria andConsumeAmountNotEqualTo(Integer value) {
            addCriterion("consume_amount <>", value, "consumeAmount");
            return (Criteria) this;
        }

        public Criteria andConsumeAmountGreaterThan(Integer value) {
            addCriterion("consume_amount >", value, "consumeAmount");
            return (Criteria) this;
        }

        public Criteria andConsumeAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("consume_amount >=", value, "consumeAmount");
            return (Criteria) this;
        }

        public Criteria andConsumeAmountLessThan(Integer value) {
            addCriterion("consume_amount <", value, "consumeAmount");
            return (Criteria) this;
        }

        public Criteria andConsumeAmountLessThanOrEqualTo(Integer value) {
            addCriterion("consume_amount <=", value, "consumeAmount");
            return (Criteria) this;
        }

        public Criteria andConsumeAmountIn(List<Integer> values) {
            addCriterion("consume_amount in", values, "consumeAmount");
            return (Criteria) this;
        }

        public Criteria andConsumeAmountNotIn(List<Integer> values) {
            addCriterion("consume_amount not in", values, "consumeAmount");
            return (Criteria) this;
        }

        public Criteria andConsumeAmountBetween(Integer value1, Integer value2) {
            addCriterion("consume_amount between", value1, value2, "consumeAmount");
            return (Criteria) this;
        }

        public Criteria andConsumeAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("consume_amount not between", value1, value2, "consumeAmount");
            return (Criteria) this;
        }

        public Criteria andRefundConsumeIsNull() {
            addCriterion("refund_consume is null");
            return (Criteria) this;
        }

        public Criteria andRefundConsumeIsNotNull() {
            addCriterion("refund_consume is not null");
            return (Criteria) this;
        }

        public Criteria andRefundConsumeEqualTo(Integer value) {
            addCriterion("refund_consume =", value, "refundConsume");
            return (Criteria) this;
        }

        public Criteria andRefundConsumeNotEqualTo(Integer value) {
            addCriterion("refund_consume <>", value, "refundConsume");
            return (Criteria) this;
        }

        public Criteria andRefundConsumeGreaterThan(Integer value) {
            addCriterion("refund_consume >", value, "refundConsume");
            return (Criteria) this;
        }

        public Criteria andRefundConsumeGreaterThanOrEqualTo(Integer value) {
            addCriterion("refund_consume >=", value, "refundConsume");
            return (Criteria) this;
        }

        public Criteria andRefundConsumeLessThan(Integer value) {
            addCriterion("refund_consume <", value, "refundConsume");
            return (Criteria) this;
        }

        public Criteria andRefundConsumeLessThanOrEqualTo(Integer value) {
            addCriterion("refund_consume <=", value, "refundConsume");
            return (Criteria) this;
        }

        public Criteria andRefundConsumeIn(List<Integer> values) {
            addCriterion("refund_consume in", values, "refundConsume");
            return (Criteria) this;
        }

        public Criteria andRefundConsumeNotIn(List<Integer> values) {
            addCriterion("refund_consume not in", values, "refundConsume");
            return (Criteria) this;
        }

        public Criteria andRefundConsumeBetween(Integer value1, Integer value2) {
            addCriterion("refund_consume between", value1, value2, "refundConsume");
            return (Criteria) this;
        }

        public Criteria andRefundConsumeNotBetween(Integer value1, Integer value2) {
            addCriterion("refund_consume not between", value1, value2, "refundConsume");
            return (Criteria) this;
        }

        public Criteria andSafeguardIsNull() {
            addCriterion("safeguard is null");
            return (Criteria) this;
        }

        public Criteria andSafeguardIsNotNull() {
            addCriterion("safeguard is not null");
            return (Criteria) this;
        }

        public Criteria andSafeguardEqualTo(String value) {
            addCriterion("safeguard =", value, "safeguard");
            return (Criteria) this;
        }

        public Criteria andSafeguardNotEqualTo(String value) {
            addCriterion("safeguard <>", value, "safeguard");
            return (Criteria) this;
        }

        public Criteria andSafeguardGreaterThan(String value) {
            addCriterion("safeguard >", value, "safeguard");
            return (Criteria) this;
        }

        public Criteria andSafeguardGreaterThanOrEqualTo(String value) {
            addCriterion("safeguard >=", value, "safeguard");
            return (Criteria) this;
        }

        public Criteria andSafeguardLessThan(String value) {
            addCriterion("safeguard <", value, "safeguard");
            return (Criteria) this;
        }

        public Criteria andSafeguardLessThanOrEqualTo(String value) {
            addCriterion("safeguard <=", value, "safeguard");
            return (Criteria) this;
        }

        public Criteria andSafeguardLike(String value) {
            addCriterion("safeguard like", value, "safeguard");
            return (Criteria) this;
        }

        public Criteria andSafeguardNotLike(String value) {
            addCriterion("safeguard not like", value, "safeguard");
            return (Criteria) this;
        }

        public Criteria andSafeguardIn(List<String> values) {
            addCriterion("safeguard in", values, "safeguard");
            return (Criteria) this;
        }

        public Criteria andSafeguardNotIn(List<String> values) {
            addCriterion("safeguard not in", values, "safeguard");
            return (Criteria) this;
        }

        public Criteria andSafeguardBetween(String value1, String value2) {
            addCriterion("safeguard between", value1, value2, "safeguard");
            return (Criteria) this;
        }

        public Criteria andSafeguardNotBetween(String value1, String value2) {
            addCriterion("safeguard not between", value1, value2, "safeguard");
            return (Criteria) this;
        }

        public Criteria andSafeguardNumberIsNull() {
            addCriterion("safeguard_number is null");
            return (Criteria) this;
        }

        public Criteria andSafeguardNumberIsNotNull() {
            addCriterion("safeguard_number is not null");
            return (Criteria) this;
        }

        public Criteria andSafeguardNumberEqualTo(Integer value) {
            addCriterion("safeguard_number =", value, "safeguardNumber");
            return (Criteria) this;
        }

        public Criteria andSafeguardNumberNotEqualTo(Integer value) {
            addCriterion("safeguard_number <>", value, "safeguardNumber");
            return (Criteria) this;
        }

        public Criteria andSafeguardNumberGreaterThan(Integer value) {
            addCriterion("safeguard_number >", value, "safeguardNumber");
            return (Criteria) this;
        }

        public Criteria andSafeguardNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("safeguard_number >=", value, "safeguardNumber");
            return (Criteria) this;
        }

        public Criteria andSafeguardNumberLessThan(Integer value) {
            addCriterion("safeguard_number <", value, "safeguardNumber");
            return (Criteria) this;
        }

        public Criteria andSafeguardNumberLessThanOrEqualTo(Integer value) {
            addCriterion("safeguard_number <=", value, "safeguardNumber");
            return (Criteria) this;
        }

        public Criteria andSafeguardNumberIn(List<Integer> values) {
            addCriterion("safeguard_number in", values, "safeguardNumber");
            return (Criteria) this;
        }

        public Criteria andSafeguardNumberNotIn(List<Integer> values) {
            addCriterion("safeguard_number not in", values, "safeguardNumber");
            return (Criteria) this;
        }

        public Criteria andSafeguardNumberBetween(Integer value1, Integer value2) {
            addCriterion("safeguard_number between", value1, value2, "safeguardNumber");
            return (Criteria) this;
        }

        public Criteria andSafeguardNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("safeguard_number not between", value1, value2, "safeguardNumber");
            return (Criteria) this;
        }

        public Criteria andSucceedIsNull() {
            addCriterion("succeed is null");
            return (Criteria) this;
        }

        public Criteria andSucceedIsNotNull() {
            addCriterion("succeed is not null");
            return (Criteria) this;
        }

        public Criteria andSucceedEqualTo(Integer value) {
            addCriterion("succeed =", value, "succeed");
            return (Criteria) this;
        }

        public Criteria andSucceedNotEqualTo(Integer value) {
            addCriterion("succeed <>", value, "succeed");
            return (Criteria) this;
        }

        public Criteria andSucceedGreaterThan(Integer value) {
            addCriterion("succeed >", value, "succeed");
            return (Criteria) this;
        }

        public Criteria andSucceedGreaterThanOrEqualTo(Integer value) {
            addCriterion("succeed >=", value, "succeed");
            return (Criteria) this;
        }

        public Criteria andSucceedLessThan(Integer value) {
            addCriterion("succeed <", value, "succeed");
            return (Criteria) this;
        }

        public Criteria andSucceedLessThanOrEqualTo(Integer value) {
            addCriterion("succeed <=", value, "succeed");
            return (Criteria) this;
        }

        public Criteria andSucceedIn(List<Integer> values) {
            addCriterion("succeed in", values, "succeed");
            return (Criteria) this;
        }

        public Criteria andSucceedNotIn(List<Integer> values) {
            addCriterion("succeed not in", values, "succeed");
            return (Criteria) this;
        }

        public Criteria andSucceedBetween(Integer value1, Integer value2) {
            addCriterion("succeed between", value1, value2, "succeed");
            return (Criteria) this;
        }

        public Criteria andSucceedNotBetween(Integer value1, Integer value2) {
            addCriterion("succeed not between", value1, value2, "succeed");
            return (Criteria) this;
        }

        public Criteria andFailureIsNull() {
            addCriterion("failure is null");
            return (Criteria) this;
        }

        public Criteria andFailureIsNotNull() {
            addCriterion("failure is not null");
            return (Criteria) this;
        }

        public Criteria andFailureEqualTo(Integer value) {
            addCriterion("failure =", value, "failure");
            return (Criteria) this;
        }

        public Criteria andFailureNotEqualTo(Integer value) {
            addCriterion("failure <>", value, "failure");
            return (Criteria) this;
        }

        public Criteria andFailureGreaterThan(Integer value) {
            addCriterion("failure >", value, "failure");
            return (Criteria) this;
        }

        public Criteria andFailureGreaterThanOrEqualTo(Integer value) {
            addCriterion("failure >=", value, "failure");
            return (Criteria) this;
        }

        public Criteria andFailureLessThan(Integer value) {
            addCriterion("failure <", value, "failure");
            return (Criteria) this;
        }

        public Criteria andFailureLessThanOrEqualTo(Integer value) {
            addCriterion("failure <=", value, "failure");
            return (Criteria) this;
        }

        public Criteria andFailureIn(List<Integer> values) {
            addCriterion("failure in", values, "failure");
            return (Criteria) this;
        }

        public Criteria andFailureNotIn(List<Integer> values) {
            addCriterion("failure not in", values, "failure");
            return (Criteria) this;
        }

        public Criteria andFailureBetween(Integer value1, Integer value2) {
            addCriterion("failure between", value1, value2, "failure");
            return (Criteria) this;
        }

        public Criteria andFailureNotBetween(Integer value1, Integer value2) {
            addCriterion("failure not between", value1, value2, "failure");
            return (Criteria) this;
        }

        public Criteria andReinforceScaleIsNull() {
            addCriterion("reinforce_scale is null");
            return (Criteria) this;
        }

        public Criteria andReinforceScaleIsNotNull() {
            addCriterion("reinforce_scale is not null");
            return (Criteria) this;
        }

        public Criteria andReinforceScaleEqualTo(Double value) {
            addCriterion("reinforce_scale =", value, "reinforceScale");
            return (Criteria) this;
        }

        public Criteria andReinforceScaleNotEqualTo(Double value) {
            addCriterion("reinforce_scale <>", value, "reinforceScale");
            return (Criteria) this;
        }

        public Criteria andReinforceScaleGreaterThan(Double value) {
            addCriterion("reinforce_scale >", value, "reinforceScale");
            return (Criteria) this;
        }

        public Criteria andReinforceScaleGreaterThanOrEqualTo(Double value) {
            addCriterion("reinforce_scale >=", value, "reinforceScale");
            return (Criteria) this;
        }

        public Criteria andReinforceScaleLessThan(Double value) {
            addCriterion("reinforce_scale <", value, "reinforceScale");
            return (Criteria) this;
        }

        public Criteria andReinforceScaleLessThanOrEqualTo(Double value) {
            addCriterion("reinforce_scale <=", value, "reinforceScale");
            return (Criteria) this;
        }

        public Criteria andReinforceScaleIn(List<Double> values) {
            addCriterion("reinforce_scale in", values, "reinforceScale");
            return (Criteria) this;
        }

        public Criteria andReinforceScaleNotIn(List<Double> values) {
            addCriterion("reinforce_scale not in", values, "reinforceScale");
            return (Criteria) this;
        }

        public Criteria andReinforceScaleBetween(Double value1, Double value2) {
            addCriterion("reinforce_scale between", value1, value2, "reinforceScale");
            return (Criteria) this;
        }

        public Criteria andReinforceScaleNotBetween(Double value1, Double value2) {
            addCriterion("reinforce_scale not between", value1, value2, "reinforceScale");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}