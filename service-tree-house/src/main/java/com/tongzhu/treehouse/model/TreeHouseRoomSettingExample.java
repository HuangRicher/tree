package com.tongzhu.treehouse.model;

import java.util.ArrayList;
import java.util.List;

public class TreeHouseRoomSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TreeHouseRoomSettingExample() {
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

        public Criteria andWorkerCountIsNull() {
            addCriterion("worker_count is null");
            return (Criteria) this;
        }

        public Criteria andWorkerCountIsNotNull() {
            addCriterion("worker_count is not null");
            return (Criteria) this;
        }

        public Criteria andWorkerCountEqualTo(Integer value) {
            addCriterion("worker_count =", value, "workerCount");
            return (Criteria) this;
        }

        public Criteria andWorkerCountNotEqualTo(Integer value) {
            addCriterion("worker_count <>", value, "workerCount");
            return (Criteria) this;
        }

        public Criteria andWorkerCountGreaterThan(Integer value) {
            addCriterion("worker_count >", value, "workerCount");
            return (Criteria) this;
        }

        public Criteria andWorkerCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("worker_count >=", value, "workerCount");
            return (Criteria) this;
        }

        public Criteria andWorkerCountLessThan(Integer value) {
            addCriterion("worker_count <", value, "workerCount");
            return (Criteria) this;
        }

        public Criteria andWorkerCountLessThanOrEqualTo(Integer value) {
            addCriterion("worker_count <=", value, "workerCount");
            return (Criteria) this;
        }

        public Criteria andWorkerCountIn(List<Integer> values) {
            addCriterion("worker_count in", values, "workerCount");
            return (Criteria) this;
        }

        public Criteria andWorkerCountNotIn(List<Integer> values) {
            addCriterion("worker_count not in", values, "workerCount");
            return (Criteria) this;
        }

        public Criteria andWorkerCountBetween(Integer value1, Integer value2) {
            addCriterion("worker_count between", value1, value2, "workerCount");
            return (Criteria) this;
        }

        public Criteria andWorkerCountNotBetween(Integer value1, Integer value2) {
            addCriterion("worker_count not between", value1, value2, "workerCount");
            return (Criteria) this;
        }

        public Criteria andExperienceMinIsNull() {
            addCriterion("experience_min is null");
            return (Criteria) this;
        }

        public Criteria andExperienceMinIsNotNull() {
            addCriterion("experience_min is not null");
            return (Criteria) this;
        }

        public Criteria andExperienceMinEqualTo(Integer value) {
            addCriterion("experience_min =", value, "experienceMin");
            return (Criteria) this;
        }

        public Criteria andExperienceMinNotEqualTo(Integer value) {
            addCriterion("experience_min <>", value, "experienceMin");
            return (Criteria) this;
        }

        public Criteria andExperienceMinGreaterThan(Integer value) {
            addCriterion("experience_min >", value, "experienceMin");
            return (Criteria) this;
        }

        public Criteria andExperienceMinGreaterThanOrEqualTo(Integer value) {
            addCriterion("experience_min >=", value, "experienceMin");
            return (Criteria) this;
        }

        public Criteria andExperienceMinLessThan(Integer value) {
            addCriterion("experience_min <", value, "experienceMin");
            return (Criteria) this;
        }

        public Criteria andExperienceMinLessThanOrEqualTo(Integer value) {
            addCriterion("experience_min <=", value, "experienceMin");
            return (Criteria) this;
        }

        public Criteria andExperienceMinIn(List<Integer> values) {
            addCriterion("experience_min in", values, "experienceMin");
            return (Criteria) this;
        }

        public Criteria andExperienceMinNotIn(List<Integer> values) {
            addCriterion("experience_min not in", values, "experienceMin");
            return (Criteria) this;
        }

        public Criteria andExperienceMinBetween(Integer value1, Integer value2) {
            addCriterion("experience_min between", value1, value2, "experienceMin");
            return (Criteria) this;
        }

        public Criteria andExperienceMinNotBetween(Integer value1, Integer value2) {
            addCriterion("experience_min not between", value1, value2, "experienceMin");
            return (Criteria) this;
        }

        public Criteria andExperienceMaxIsNull() {
            addCriterion("experience_max is null");
            return (Criteria) this;
        }

        public Criteria andExperienceMaxIsNotNull() {
            addCriterion("experience_max is not null");
            return (Criteria) this;
        }

        public Criteria andExperienceMaxEqualTo(Integer value) {
            addCriterion("experience_max =", value, "experienceMax");
            return (Criteria) this;
        }

        public Criteria andExperienceMaxNotEqualTo(Integer value) {
            addCriterion("experience_max <>", value, "experienceMax");
            return (Criteria) this;
        }

        public Criteria andExperienceMaxGreaterThan(Integer value) {
            addCriterion("experience_max >", value, "experienceMax");
            return (Criteria) this;
        }

        public Criteria andExperienceMaxGreaterThanOrEqualTo(Integer value) {
            addCriterion("experience_max >=", value, "experienceMax");
            return (Criteria) this;
        }

        public Criteria andExperienceMaxLessThan(Integer value) {
            addCriterion("experience_max <", value, "experienceMax");
            return (Criteria) this;
        }

        public Criteria andExperienceMaxLessThanOrEqualTo(Integer value) {
            addCriterion("experience_max <=", value, "experienceMax");
            return (Criteria) this;
        }

        public Criteria andExperienceMaxIn(List<Integer> values) {
            addCriterion("experience_max in", values, "experienceMax");
            return (Criteria) this;
        }

        public Criteria andExperienceMaxNotIn(List<Integer> values) {
            addCriterion("experience_max not in", values, "experienceMax");
            return (Criteria) this;
        }

        public Criteria andExperienceMaxBetween(Integer value1, Integer value2) {
            addCriterion("experience_max between", value1, value2, "experienceMax");
            return (Criteria) this;
        }

        public Criteria andExperienceMaxNotBetween(Integer value1, Integer value2) {
            addCriterion("experience_max not between", value1, value2, "experienceMax");
            return (Criteria) this;
        }

        public Criteria andFlourishingRateIsNull() {
            addCriterion("flourishing_rate is null");
            return (Criteria) this;
        }

        public Criteria andFlourishingRateIsNotNull() {
            addCriterion("flourishing_rate is not null");
            return (Criteria) this;
        }

        public Criteria andFlourishingRateEqualTo(Integer value) {
            addCriterion("flourishing_rate =", value, "flourishingRate");
            return (Criteria) this;
        }

        public Criteria andFlourishingRateNotEqualTo(Integer value) {
            addCriterion("flourishing_rate <>", value, "flourishingRate");
            return (Criteria) this;
        }

        public Criteria andFlourishingRateGreaterThan(Integer value) {
            addCriterion("flourishing_rate >", value, "flourishingRate");
            return (Criteria) this;
        }

        public Criteria andFlourishingRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("flourishing_rate >=", value, "flourishingRate");
            return (Criteria) this;
        }

        public Criteria andFlourishingRateLessThan(Integer value) {
            addCriterion("flourishing_rate <", value, "flourishingRate");
            return (Criteria) this;
        }

        public Criteria andFlourishingRateLessThanOrEqualTo(Integer value) {
            addCriterion("flourishing_rate <=", value, "flourishingRate");
            return (Criteria) this;
        }

        public Criteria andFlourishingRateIn(List<Integer> values) {
            addCriterion("flourishing_rate in", values, "flourishingRate");
            return (Criteria) this;
        }

        public Criteria andFlourishingRateNotIn(List<Integer> values) {
            addCriterion("flourishing_rate not in", values, "flourishingRate");
            return (Criteria) this;
        }

        public Criteria andFlourishingRateBetween(Integer value1, Integer value2) {
            addCriterion("flourishing_rate between", value1, value2, "flourishingRate");
            return (Criteria) this;
        }

        public Criteria andFlourishingRateNotBetween(Integer value1, Integer value2) {
            addCriterion("flourishing_rate not between", value1, value2, "flourishingRate");
            return (Criteria) this;
        }

        public Criteria andDayUseIsNull() {
            addCriterion("day_use is null");
            return (Criteria) this;
        }

        public Criteria andDayUseIsNotNull() {
            addCriterion("day_use is not null");
            return (Criteria) this;
        }

        public Criteria andDayUseEqualTo(Integer value) {
            addCriterion("day_use =", value, "dayUse");
            return (Criteria) this;
        }

        public Criteria andDayUseNotEqualTo(Integer value) {
            addCriterion("day_use <>", value, "dayUse");
            return (Criteria) this;
        }

        public Criteria andDayUseGreaterThan(Integer value) {
            addCriterion("day_use >", value, "dayUse");
            return (Criteria) this;
        }

        public Criteria andDayUseGreaterThanOrEqualTo(Integer value) {
            addCriterion("day_use >=", value, "dayUse");
            return (Criteria) this;
        }

        public Criteria andDayUseLessThan(Integer value) {
            addCriterion("day_use <", value, "dayUse");
            return (Criteria) this;
        }

        public Criteria andDayUseLessThanOrEqualTo(Integer value) {
            addCriterion("day_use <=", value, "dayUse");
            return (Criteria) this;
        }

        public Criteria andDayUseIn(List<Integer> values) {
            addCriterion("day_use in", values, "dayUse");
            return (Criteria) this;
        }

        public Criteria andDayUseNotIn(List<Integer> values) {
            addCriterion("day_use not in", values, "dayUse");
            return (Criteria) this;
        }

        public Criteria andDayUseBetween(Integer value1, Integer value2) {
            addCriterion("day_use between", value1, value2, "dayUse");
            return (Criteria) this;
        }

        public Criteria andDayUseNotBetween(Integer value1, Integer value2) {
            addCriterion("day_use not between", value1, value2, "dayUse");
            return (Criteria) this;
        }

        public Criteria andConsumeGoodsIsNull() {
            addCriterion("consume_goods is null");
            return (Criteria) this;
        }

        public Criteria andConsumeGoodsIsNotNull() {
            addCriterion("consume_goods is not null");
            return (Criteria) this;
        }

        public Criteria andConsumeGoodsEqualTo(String value) {
            addCriterion("consume_goods =", value, "consumeGoods");
            return (Criteria) this;
        }

        public Criteria andConsumeGoodsNotEqualTo(String value) {
            addCriterion("consume_goods <>", value, "consumeGoods");
            return (Criteria) this;
        }

        public Criteria andConsumeGoodsGreaterThan(String value) {
            addCriterion("consume_goods >", value, "consumeGoods");
            return (Criteria) this;
        }

        public Criteria andConsumeGoodsGreaterThanOrEqualTo(String value) {
            addCriterion("consume_goods >=", value, "consumeGoods");
            return (Criteria) this;
        }

        public Criteria andConsumeGoodsLessThan(String value) {
            addCriterion("consume_goods <", value, "consumeGoods");
            return (Criteria) this;
        }

        public Criteria andConsumeGoodsLessThanOrEqualTo(String value) {
            addCriterion("consume_goods <=", value, "consumeGoods");
            return (Criteria) this;
        }

        public Criteria andConsumeGoodsLike(String value) {
            addCriterion("consume_goods like", value, "consumeGoods");
            return (Criteria) this;
        }

        public Criteria andConsumeGoodsNotLike(String value) {
            addCriterion("consume_goods not like", value, "consumeGoods");
            return (Criteria) this;
        }

        public Criteria andConsumeGoodsIn(List<String> values) {
            addCriterion("consume_goods in", values, "consumeGoods");
            return (Criteria) this;
        }

        public Criteria andConsumeGoodsNotIn(List<String> values) {
            addCriterion("consume_goods not in", values, "consumeGoods");
            return (Criteria) this;
        }

        public Criteria andConsumeGoodsBetween(String value1, String value2) {
            addCriterion("consume_goods between", value1, value2, "consumeGoods");
            return (Criteria) this;
        }

        public Criteria andConsumeGoodsNotBetween(String value1, String value2) {
            addCriterion("consume_goods not between", value1, value2, "consumeGoods");
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