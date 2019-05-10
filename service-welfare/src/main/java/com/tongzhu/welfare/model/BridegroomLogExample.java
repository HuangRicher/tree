package com.tongzhu.welfare.model;

import java.util.ArrayList;
import java.util.List;

public class BridegroomLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BridegroomLogExample() {
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

        public Criteria andBridegroomDayIsNull() {
            addCriterion("bridegroom_day is null");
            return (Criteria) this;
        }

        public Criteria andBridegroomDayIsNotNull() {
            addCriterion("bridegroom_day is not null");
            return (Criteria) this;
        }

        public Criteria andBridegroomDayEqualTo(String value) {
            addCriterion("bridegroom_day =", value, "bridegroomDay");
            return (Criteria) this;
        }

        public Criteria andBridegroomDayNotEqualTo(String value) {
            addCriterion("bridegroom_day <>", value, "bridegroomDay");
            return (Criteria) this;
        }

        public Criteria andBridegroomDayGreaterThan(String value) {
            addCriterion("bridegroom_day >", value, "bridegroomDay");
            return (Criteria) this;
        }

        public Criteria andBridegroomDayGreaterThanOrEqualTo(String value) {
            addCriterion("bridegroom_day >=", value, "bridegroomDay");
            return (Criteria) this;
        }

        public Criteria andBridegroomDayLessThan(String value) {
            addCriterion("bridegroom_day <", value, "bridegroomDay");
            return (Criteria) this;
        }

        public Criteria andBridegroomDayLessThanOrEqualTo(String value) {
            addCriterion("bridegroom_day <=", value, "bridegroomDay");
            return (Criteria) this;
        }

        public Criteria andBridegroomDayLike(String value) {
            addCriterion("bridegroom_day like", value, "bridegroomDay");
            return (Criteria) this;
        }

        public Criteria andBridegroomDayNotLike(String value) {
            addCriterion("bridegroom_day not like", value, "bridegroomDay");
            return (Criteria) this;
        }

        public Criteria andBridegroomDayIn(List<String> values) {
            addCriterion("bridegroom_day in", values, "bridegroomDay");
            return (Criteria) this;
        }

        public Criteria andBridegroomDayNotIn(List<String> values) {
            addCriterion("bridegroom_day not in", values, "bridegroomDay");
            return (Criteria) this;
        }

        public Criteria andBridegroomDayBetween(String value1, String value2) {
            addCriterion("bridegroom_day between", value1, value2, "bridegroomDay");
            return (Criteria) this;
        }

        public Criteria andBridegroomDayNotBetween(String value1, String value2) {
            addCriterion("bridegroom_day not between", value1, value2, "bridegroomDay");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdIsNull() {
            addCriterion("love_tree_id is null");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdIsNotNull() {
            addCriterion("love_tree_id is not null");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdEqualTo(String value) {
            addCriterion("love_tree_id =", value, "loveTreeId");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdNotEqualTo(String value) {
            addCriterion("love_tree_id <>", value, "loveTreeId");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdGreaterThan(String value) {
            addCriterion("love_tree_id >", value, "loveTreeId");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdGreaterThanOrEqualTo(String value) {
            addCriterion("love_tree_id >=", value, "loveTreeId");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdLessThan(String value) {
            addCriterion("love_tree_id <", value, "loveTreeId");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdLessThanOrEqualTo(String value) {
            addCriterion("love_tree_id <=", value, "loveTreeId");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdLike(String value) {
            addCriterion("love_tree_id like", value, "loveTreeId");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdNotLike(String value) {
            addCriterion("love_tree_id not like", value, "loveTreeId");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdIn(List<String> values) {
            addCriterion("love_tree_id in", values, "loveTreeId");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdNotIn(List<String> values) {
            addCriterion("love_tree_id not in", values, "loveTreeId");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdBetween(String value1, String value2) {
            addCriterion("love_tree_id between", value1, value2, "loveTreeId");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdNotBetween(String value1, String value2) {
            addCriterion("love_tree_id not between", value1, value2, "loveTreeId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
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