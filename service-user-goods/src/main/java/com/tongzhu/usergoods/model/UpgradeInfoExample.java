package com.tongzhu.usergoods.model;

import java.util.ArrayList;
import java.util.List;

public class UpgradeInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UpgradeInfoExample() {
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

        public Criteria andUpgradeIdIsNull() {
            addCriterion("upgrade_id is null");
            return (Criteria) this;
        }

        public Criteria andUpgradeIdIsNotNull() {
            addCriterion("upgrade_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpgradeIdEqualTo(String value) {
            addCriterion("upgrade_id =", value, "upgradeId");
            return (Criteria) this;
        }

        public Criteria andUpgradeIdNotEqualTo(String value) {
            addCriterion("upgrade_id <>", value, "upgradeId");
            return (Criteria) this;
        }

        public Criteria andUpgradeIdGreaterThan(String value) {
            addCriterion("upgrade_id >", value, "upgradeId");
            return (Criteria) this;
        }

        public Criteria andUpgradeIdGreaterThanOrEqualTo(String value) {
            addCriterion("upgrade_id >=", value, "upgradeId");
            return (Criteria) this;
        }

        public Criteria andUpgradeIdLessThan(String value) {
            addCriterion("upgrade_id <", value, "upgradeId");
            return (Criteria) this;
        }

        public Criteria andUpgradeIdLessThanOrEqualTo(String value) {
            addCriterion("upgrade_id <=", value, "upgradeId");
            return (Criteria) this;
        }

        public Criteria andUpgradeIdLike(String value) {
            addCriterion("upgrade_id like", value, "upgradeId");
            return (Criteria) this;
        }

        public Criteria andUpgradeIdNotLike(String value) {
            addCriterion("upgrade_id not like", value, "upgradeId");
            return (Criteria) this;
        }

        public Criteria andUpgradeIdIn(List<String> values) {
            addCriterion("upgrade_id in", values, "upgradeId");
            return (Criteria) this;
        }

        public Criteria andUpgradeIdNotIn(List<String> values) {
            addCriterion("upgrade_id not in", values, "upgradeId");
            return (Criteria) this;
        }

        public Criteria andUpgradeIdBetween(String value1, String value2) {
            addCriterion("upgrade_id between", value1, value2, "upgradeId");
            return (Criteria) this;
        }

        public Criteria andUpgradeIdNotBetween(String value1, String value2) {
            addCriterion("upgrade_id not between", value1, value2, "upgradeId");
            return (Criteria) this;
        }

        public Criteria andUpgradeAmountIsNull() {
            addCriterion("upgrade_amount is null");
            return (Criteria) this;
        }

        public Criteria andUpgradeAmountIsNotNull() {
            addCriterion("upgrade_amount is not null");
            return (Criteria) this;
        }

        public Criteria andUpgradeAmountEqualTo(Integer value) {
            addCriterion("upgrade_amount =", value, "upgradeAmount");
            return (Criteria) this;
        }

        public Criteria andUpgradeAmountNotEqualTo(Integer value) {
            addCriterion("upgrade_amount <>", value, "upgradeAmount");
            return (Criteria) this;
        }

        public Criteria andUpgradeAmountGreaterThan(Integer value) {
            addCriterion("upgrade_amount >", value, "upgradeAmount");
            return (Criteria) this;
        }

        public Criteria andUpgradeAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("upgrade_amount >=", value, "upgradeAmount");
            return (Criteria) this;
        }

        public Criteria andUpgradeAmountLessThan(Integer value) {
            addCriterion("upgrade_amount <", value, "upgradeAmount");
            return (Criteria) this;
        }

        public Criteria andUpgradeAmountLessThanOrEqualTo(Integer value) {
            addCriterion("upgrade_amount <=", value, "upgradeAmount");
            return (Criteria) this;
        }

        public Criteria andUpgradeAmountIn(List<Integer> values) {
            addCriterion("upgrade_amount in", values, "upgradeAmount");
            return (Criteria) this;
        }

        public Criteria andUpgradeAmountNotIn(List<Integer> values) {
            addCriterion("upgrade_amount not in", values, "upgradeAmount");
            return (Criteria) this;
        }

        public Criteria andUpgradeAmountBetween(Integer value1, Integer value2) {
            addCriterion("upgrade_amount between", value1, value2, "upgradeAmount");
            return (Criteria) this;
        }

        public Criteria andUpgradeAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("upgrade_amount not between", value1, value2, "upgradeAmount");
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andWearPositionIsNull() {
            addCriterion("wear_position is null");
            return (Criteria) this;
        }

        public Criteria andWearPositionIsNotNull() {
            addCriterion("wear_position is not null");
            return (Criteria) this;
        }

        public Criteria andWearPositionEqualTo(String value) {
            addCriterion("wear_position =", value, "wearPosition");
            return (Criteria) this;
        }

        public Criteria andWearPositionNotEqualTo(String value) {
            addCriterion("wear_position <>", value, "wearPosition");
            return (Criteria) this;
        }

        public Criteria andWearPositionGreaterThan(String value) {
            addCriterion("wear_position >", value, "wearPosition");
            return (Criteria) this;
        }

        public Criteria andWearPositionGreaterThanOrEqualTo(String value) {
            addCriterion("wear_position >=", value, "wearPosition");
            return (Criteria) this;
        }

        public Criteria andWearPositionLessThan(String value) {
            addCriterion("wear_position <", value, "wearPosition");
            return (Criteria) this;
        }

        public Criteria andWearPositionLessThanOrEqualTo(String value) {
            addCriterion("wear_position <=", value, "wearPosition");
            return (Criteria) this;
        }

        public Criteria andWearPositionLike(String value) {
            addCriterion("wear_position like", value, "wearPosition");
            return (Criteria) this;
        }

        public Criteria andWearPositionNotLike(String value) {
            addCriterion("wear_position not like", value, "wearPosition");
            return (Criteria) this;
        }

        public Criteria andWearPositionIn(List<String> values) {
            addCriterion("wear_position in", values, "wearPosition");
            return (Criteria) this;
        }

        public Criteria andWearPositionNotIn(List<String> values) {
            addCriterion("wear_position not in", values, "wearPosition");
            return (Criteria) this;
        }

        public Criteria andWearPositionBetween(String value1, String value2) {
            addCriterion("wear_position between", value1, value2, "wearPosition");
            return (Criteria) this;
        }

        public Criteria andWearPositionNotBetween(String value1, String value2) {
            addCriterion("wear_position not between", value1, value2, "wearPosition");
            return (Criteria) this;
        }

        public Criteria andQualityIsNull() {
            addCriterion("quality is null");
            return (Criteria) this;
        }

        public Criteria andQualityIsNotNull() {
            addCriterion("quality is not null");
            return (Criteria) this;
        }

        public Criteria andQualityEqualTo(Integer value) {
            addCriterion("quality =", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityNotEqualTo(Integer value) {
            addCriterion("quality <>", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityGreaterThan(Integer value) {
            addCriterion("quality >", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityGreaterThanOrEqualTo(Integer value) {
            addCriterion("quality >=", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityLessThan(Integer value) {
            addCriterion("quality <", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityLessThanOrEqualTo(Integer value) {
            addCriterion("quality <=", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityIn(List<Integer> values) {
            addCriterion("quality in", values, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityNotIn(List<Integer> values) {
            addCriterion("quality not in", values, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityBetween(Integer value1, Integer value2) {
            addCriterion("quality between", value1, value2, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityNotBetween(Integer value1, Integer value2) {
            addCriterion("quality not between", value1, value2, "quality");
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