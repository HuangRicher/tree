package com.tongzhu.fishing.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FishInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FishInfoExample() {
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

        public Criteria andCommonProbabilityIsNull() {
            addCriterion("common_probability is null");
            return (Criteria) this;
        }

        public Criteria andCommonProbabilityIsNotNull() {
            addCriterion("common_probability is not null");
            return (Criteria) this;
        }

        public Criteria andCommonProbabilityEqualTo(Integer value) {
            addCriterion("common_probability =", value, "commonProbability");
            return (Criteria) this;
        }

        public Criteria andCommonProbabilityNotEqualTo(Integer value) {
            addCriterion("common_probability <>", value, "commonProbability");
            return (Criteria) this;
        }

        public Criteria andCommonProbabilityGreaterThan(Integer value) {
            addCriterion("common_probability >", value, "commonProbability");
            return (Criteria) this;
        }

        public Criteria andCommonProbabilityGreaterThanOrEqualTo(Integer value) {
            addCriterion("common_probability >=", value, "commonProbability");
            return (Criteria) this;
        }

        public Criteria andCommonProbabilityLessThan(Integer value) {
            addCriterion("common_probability <", value, "commonProbability");
            return (Criteria) this;
        }

        public Criteria andCommonProbabilityLessThanOrEqualTo(Integer value) {
            addCriterion("common_probability <=", value, "commonProbability");
            return (Criteria) this;
        }

        public Criteria andCommonProbabilityIn(List<Integer> values) {
            addCriterion("common_probability in", values, "commonProbability");
            return (Criteria) this;
        }

        public Criteria andCommonProbabilityNotIn(List<Integer> values) {
            addCriterion("common_probability not in", values, "commonProbability");
            return (Criteria) this;
        }

        public Criteria andCommonProbabilityBetween(Integer value1, Integer value2) {
            addCriterion("common_probability between", value1, value2, "commonProbability");
            return (Criteria) this;
        }

        public Criteria andCommonProbabilityNotBetween(Integer value1, Integer value2) {
            addCriterion("common_probability not between", value1, value2, "commonProbability");
            return (Criteria) this;
        }

        public Criteria andSeniorProbabilityIsNull() {
            addCriterion("senior_probability is null");
            return (Criteria) this;
        }

        public Criteria andSeniorProbabilityIsNotNull() {
            addCriterion("senior_probability is not null");
            return (Criteria) this;
        }

        public Criteria andSeniorProbabilityEqualTo(Integer value) {
            addCriterion("senior_probability =", value, "seniorProbability");
            return (Criteria) this;
        }

        public Criteria andSeniorProbabilityNotEqualTo(Integer value) {
            addCriterion("senior_probability <>", value, "seniorProbability");
            return (Criteria) this;
        }

        public Criteria andSeniorProbabilityGreaterThan(Integer value) {
            addCriterion("senior_probability >", value, "seniorProbability");
            return (Criteria) this;
        }

        public Criteria andSeniorProbabilityGreaterThanOrEqualTo(Integer value) {
            addCriterion("senior_probability >=", value, "seniorProbability");
            return (Criteria) this;
        }

        public Criteria andSeniorProbabilityLessThan(Integer value) {
            addCriterion("senior_probability <", value, "seniorProbability");
            return (Criteria) this;
        }

        public Criteria andSeniorProbabilityLessThanOrEqualTo(Integer value) {
            addCriterion("senior_probability <=", value, "seniorProbability");
            return (Criteria) this;
        }

        public Criteria andSeniorProbabilityIn(List<Integer> values) {
            addCriterion("senior_probability in", values, "seniorProbability");
            return (Criteria) this;
        }

        public Criteria andSeniorProbabilityNotIn(List<Integer> values) {
            addCriterion("senior_probability not in", values, "seniorProbability");
            return (Criteria) this;
        }

        public Criteria andSeniorProbabilityBetween(Integer value1, Integer value2) {
            addCriterion("senior_probability between", value1, value2, "seniorProbability");
            return (Criteria) this;
        }

        public Criteria andSeniorProbabilityNotBetween(Integer value1, Integer value2) {
            addCriterion("senior_probability not between", value1, value2, "seniorProbability");
            return (Criteria) this;
        }

        public Criteria andRareProbabilityIsNull() {
            addCriterion("rare_probability is null");
            return (Criteria) this;
        }

        public Criteria andRareProbabilityIsNotNull() {
            addCriterion("rare_probability is not null");
            return (Criteria) this;
        }

        public Criteria andRareProbabilityEqualTo(Integer value) {
            addCriterion("rare_probability =", value, "rareProbability");
            return (Criteria) this;
        }

        public Criteria andRareProbabilityNotEqualTo(Integer value) {
            addCriterion("rare_probability <>", value, "rareProbability");
            return (Criteria) this;
        }

        public Criteria andRareProbabilityGreaterThan(Integer value) {
            addCriterion("rare_probability >", value, "rareProbability");
            return (Criteria) this;
        }

        public Criteria andRareProbabilityGreaterThanOrEqualTo(Integer value) {
            addCriterion("rare_probability >=", value, "rareProbability");
            return (Criteria) this;
        }

        public Criteria andRareProbabilityLessThan(Integer value) {
            addCriterion("rare_probability <", value, "rareProbability");
            return (Criteria) this;
        }

        public Criteria andRareProbabilityLessThanOrEqualTo(Integer value) {
            addCriterion("rare_probability <=", value, "rareProbability");
            return (Criteria) this;
        }

        public Criteria andRareProbabilityIn(List<Integer> values) {
            addCriterion("rare_probability in", values, "rareProbability");
            return (Criteria) this;
        }

        public Criteria andRareProbabilityNotIn(List<Integer> values) {
            addCriterion("rare_probability not in", values, "rareProbability");
            return (Criteria) this;
        }

        public Criteria andRareProbabilityBetween(Integer value1, Integer value2) {
            addCriterion("rare_probability between", value1, value2, "rareProbability");
            return (Criteria) this;
        }

        public Criteria andRareProbabilityNotBetween(Integer value1, Integer value2) {
            addCriterion("rare_probability not between", value1, value2, "rareProbability");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andRewardIsNull() {
            addCriterion("reward is null");
            return (Criteria) this;
        }

        public Criteria andRewardIsNotNull() {
            addCriterion("reward is not null");
            return (Criteria) this;
        }

        public Criteria andRewardEqualTo(String value) {
            addCriterion("reward =", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardNotEqualTo(String value) {
            addCriterion("reward <>", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardGreaterThan(String value) {
            addCriterion("reward >", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardGreaterThanOrEqualTo(String value) {
            addCriterion("reward >=", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardLessThan(String value) {
            addCriterion("reward <", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardLessThanOrEqualTo(String value) {
            addCriterion("reward <=", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardLike(String value) {
            addCriterion("reward like", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardNotLike(String value) {
            addCriterion("reward not like", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardIn(List<String> values) {
            addCriterion("reward in", values, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardNotIn(List<String> values) {
            addCriterion("reward not in", values, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardBetween(String value1, String value2) {
            addCriterion("reward between", value1, value2, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardNotBetween(String value1, String value2) {
            addCriterion("reward not between", value1, value2, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardProbabilityIsNull() {
            addCriterion("reward_probability is null");
            return (Criteria) this;
        }

        public Criteria andRewardProbabilityIsNotNull() {
            addCriterion("reward_probability is not null");
            return (Criteria) this;
        }

        public Criteria andRewardProbabilityEqualTo(String value) {
            addCriterion("reward_probability =", value, "rewardProbability");
            return (Criteria) this;
        }

        public Criteria andRewardProbabilityNotEqualTo(String value) {
            addCriterion("reward_probability <>", value, "rewardProbability");
            return (Criteria) this;
        }

        public Criteria andRewardProbabilityGreaterThan(String value) {
            addCriterion("reward_probability >", value, "rewardProbability");
            return (Criteria) this;
        }

        public Criteria andRewardProbabilityGreaterThanOrEqualTo(String value) {
            addCriterion("reward_probability >=", value, "rewardProbability");
            return (Criteria) this;
        }

        public Criteria andRewardProbabilityLessThan(String value) {
            addCriterion("reward_probability <", value, "rewardProbability");
            return (Criteria) this;
        }

        public Criteria andRewardProbabilityLessThanOrEqualTo(String value) {
            addCriterion("reward_probability <=", value, "rewardProbability");
            return (Criteria) this;
        }

        public Criteria andRewardProbabilityLike(String value) {
            addCriterion("reward_probability like", value, "rewardProbability");
            return (Criteria) this;
        }

        public Criteria andRewardProbabilityNotLike(String value) {
            addCriterion("reward_probability not like", value, "rewardProbability");
            return (Criteria) this;
        }

        public Criteria andRewardProbabilityIn(List<String> values) {
            addCriterion("reward_probability in", values, "rewardProbability");
            return (Criteria) this;
        }

        public Criteria andRewardProbabilityNotIn(List<String> values) {
            addCriterion("reward_probability not in", values, "rewardProbability");
            return (Criteria) this;
        }

        public Criteria andRewardProbabilityBetween(String value1, String value2) {
            addCriterion("reward_probability between", value1, value2, "rewardProbability");
            return (Criteria) this;
        }

        public Criteria andRewardProbabilityNotBetween(String value1, String value2) {
            addCriterion("reward_probability not between", value1, value2, "rewardProbability");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andBoutiqueIsNull() {
            addCriterion("boutique is null");
            return (Criteria) this;
        }

        public Criteria andBoutiqueIsNotNull() {
            addCriterion("boutique is not null");
            return (Criteria) this;
        }

        public Criteria andBoutiqueEqualTo(Integer value) {
            addCriterion("boutique =", value, "boutique");
            return (Criteria) this;
        }

        public Criteria andBoutiqueNotEqualTo(Integer value) {
            addCriterion("boutique <>", value, "boutique");
            return (Criteria) this;
        }

        public Criteria andBoutiqueGreaterThan(Integer value) {
            addCriterion("boutique >", value, "boutique");
            return (Criteria) this;
        }

        public Criteria andBoutiqueGreaterThanOrEqualTo(Integer value) {
            addCriterion("boutique >=", value, "boutique");
            return (Criteria) this;
        }

        public Criteria andBoutiqueLessThan(Integer value) {
            addCriterion("boutique <", value, "boutique");
            return (Criteria) this;
        }

        public Criteria andBoutiqueLessThanOrEqualTo(Integer value) {
            addCriterion("boutique <=", value, "boutique");
            return (Criteria) this;
        }

        public Criteria andBoutiqueIn(List<Integer> values) {
            addCriterion("boutique in", values, "boutique");
            return (Criteria) this;
        }

        public Criteria andBoutiqueNotIn(List<Integer> values) {
            addCriterion("boutique not in", values, "boutique");
            return (Criteria) this;
        }

        public Criteria andBoutiqueBetween(Integer value1, Integer value2) {
            addCriterion("boutique between", value1, value2, "boutique");
            return (Criteria) this;
        }

        public Criteria andBoutiqueNotBetween(Integer value1, Integer value2) {
            addCriterion("boutique not between", value1, value2, "boutique");
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