package com.tongzhu.treehouse.model;

import java.util.ArrayList;
import java.util.List;

public class TreeHouseVisitorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TreeHouseVisitorExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdIsNull() {
            addCriterion("tree_house_id is null");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdIsNotNull() {
            addCriterion("tree_house_id is not null");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdEqualTo(String value) {
            addCriterion("tree_house_id =", value, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdNotEqualTo(String value) {
            addCriterion("tree_house_id <>", value, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdGreaterThan(String value) {
            addCriterion("tree_house_id >", value, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdGreaterThanOrEqualTo(String value) {
            addCriterion("tree_house_id >=", value, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdLessThan(String value) {
            addCriterion("tree_house_id <", value, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdLessThanOrEqualTo(String value) {
            addCriterion("tree_house_id <=", value, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdLike(String value) {
            addCriterion("tree_house_id like", value, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdNotLike(String value) {
            addCriterion("tree_house_id not like", value, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdIn(List<String> values) {
            addCriterion("tree_house_id in", values, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdNotIn(List<String> values) {
            addCriterion("tree_house_id not in", values, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdBetween(String value1, String value2) {
            addCriterion("tree_house_id between", value1, value2, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdNotBetween(String value1, String value2) {
            addCriterion("tree_house_id not between", value1, value2, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andVisitorIdIsNull() {
            addCriterion("visitor_id is null");
            return (Criteria) this;
        }

        public Criteria andVisitorIdIsNotNull() {
            addCriterion("visitor_id is not null");
            return (Criteria) this;
        }

        public Criteria andVisitorIdEqualTo(String value) {
            addCriterion("visitor_id =", value, "visitorId");
            return (Criteria) this;
        }

        public Criteria andVisitorIdNotEqualTo(String value) {
            addCriterion("visitor_id <>", value, "visitorId");
            return (Criteria) this;
        }

        public Criteria andVisitorIdGreaterThan(String value) {
            addCriterion("visitor_id >", value, "visitorId");
            return (Criteria) this;
        }

        public Criteria andVisitorIdGreaterThanOrEqualTo(String value) {
            addCriterion("visitor_id >=", value, "visitorId");
            return (Criteria) this;
        }

        public Criteria andVisitorIdLessThan(String value) {
            addCriterion("visitor_id <", value, "visitorId");
            return (Criteria) this;
        }

        public Criteria andVisitorIdLessThanOrEqualTo(String value) {
            addCriterion("visitor_id <=", value, "visitorId");
            return (Criteria) this;
        }

        public Criteria andVisitorIdLike(String value) {
            addCriterion("visitor_id like", value, "visitorId");
            return (Criteria) this;
        }

        public Criteria andVisitorIdNotLike(String value) {
            addCriterion("visitor_id not like", value, "visitorId");
            return (Criteria) this;
        }

        public Criteria andVisitorIdIn(List<String> values) {
            addCriterion("visitor_id in", values, "visitorId");
            return (Criteria) this;
        }

        public Criteria andVisitorIdNotIn(List<String> values) {
            addCriterion("visitor_id not in", values, "visitorId");
            return (Criteria) this;
        }

        public Criteria andVisitorIdBetween(String value1, String value2) {
            addCriterion("visitor_id between", value1, value2, "visitorId");
            return (Criteria) this;
        }

        public Criteria andVisitorIdNotBetween(String value1, String value2) {
            addCriterion("visitor_id not between", value1, value2, "visitorId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status_ is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status_ is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status_ =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status_ <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status_ >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status_ >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status_ <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status_ <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status_ in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status_ not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status_ between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status_ not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCanSpeakIsNull() {
            addCriterion("can_speak is null");
            return (Criteria) this;
        }

        public Criteria andCanSpeakIsNotNull() {
            addCriterion("can_speak is not null");
            return (Criteria) this;
        }

        public Criteria andCanSpeakEqualTo(Boolean value) {
            addCriterion("can_speak =", value, "canSpeak");
            return (Criteria) this;
        }

        public Criteria andCanSpeakNotEqualTo(Boolean value) {
            addCriterion("can_speak <>", value, "canSpeak");
            return (Criteria) this;
        }

        public Criteria andCanSpeakGreaterThan(Boolean value) {
            addCriterion("can_speak >", value, "canSpeak");
            return (Criteria) this;
        }

        public Criteria andCanSpeakGreaterThanOrEqualTo(Boolean value) {
            addCriterion("can_speak >=", value, "canSpeak");
            return (Criteria) this;
        }

        public Criteria andCanSpeakLessThan(Boolean value) {
            addCriterion("can_speak <", value, "canSpeak");
            return (Criteria) this;
        }

        public Criteria andCanSpeakLessThanOrEqualTo(Boolean value) {
            addCriterion("can_speak <=", value, "canSpeak");
            return (Criteria) this;
        }

        public Criteria andCanSpeakIn(List<Boolean> values) {
            addCriterion("can_speak in", values, "canSpeak");
            return (Criteria) this;
        }

        public Criteria andCanSpeakNotIn(List<Boolean> values) {
            addCriterion("can_speak not in", values, "canSpeak");
            return (Criteria) this;
        }

        public Criteria andCanSpeakBetween(Boolean value1, Boolean value2) {
            addCriterion("can_speak between", value1, value2, "canSpeak");
            return (Criteria) this;
        }

        public Criteria andCanSpeakNotBetween(Boolean value1, Boolean value2) {
            addCriterion("can_speak not between", value1, value2, "canSpeak");
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