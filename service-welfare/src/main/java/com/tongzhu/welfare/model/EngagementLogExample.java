package com.tongzhu.welfare.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EngagementLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EngagementLogExample() {
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

        public Criteria andOtherIdIsNull() {
            addCriterion("other_id is null");
            return (Criteria) this;
        }

        public Criteria andOtherIdIsNotNull() {
            addCriterion("other_id is not null");
            return (Criteria) this;
        }

        public Criteria andOtherIdEqualTo(String value) {
            addCriterion("other_id =", value, "otherId");
            return (Criteria) this;
        }

        public Criteria andOtherIdNotEqualTo(String value) {
            addCriterion("other_id <>", value, "otherId");
            return (Criteria) this;
        }

        public Criteria andOtherIdGreaterThan(String value) {
            addCriterion("other_id >", value, "otherId");
            return (Criteria) this;
        }

        public Criteria andOtherIdGreaterThanOrEqualTo(String value) {
            addCriterion("other_id >=", value, "otherId");
            return (Criteria) this;
        }

        public Criteria andOtherIdLessThan(String value) {
            addCriterion("other_id <", value, "otherId");
            return (Criteria) this;
        }

        public Criteria andOtherIdLessThanOrEqualTo(String value) {
            addCriterion("other_id <=", value, "otherId");
            return (Criteria) this;
        }

        public Criteria andOtherIdLike(String value) {
            addCriterion("other_id like", value, "otherId");
            return (Criteria) this;
        }

        public Criteria andOtherIdNotLike(String value) {
            addCriterion("other_id not like", value, "otherId");
            return (Criteria) this;
        }

        public Criteria andOtherIdIn(List<String> values) {
            addCriterion("other_id in", values, "otherId");
            return (Criteria) this;
        }

        public Criteria andOtherIdNotIn(List<String> values) {
            addCriterion("other_id not in", values, "otherId");
            return (Criteria) this;
        }

        public Criteria andOtherIdBetween(String value1, String value2) {
            addCriterion("other_id between", value1, value2, "otherId");
            return (Criteria) this;
        }

        public Criteria andOtherIdNotBetween(String value1, String value2) {
            addCriterion("other_id not between", value1, value2, "otherId");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("created_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("created_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Date value) {
            addCriterion("created_time =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Date value) {
            addCriterion("created_time <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Date value) {
            addCriterion("created_time >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("created_time >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Date value) {
            addCriterion("created_time <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("created_time <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Date> values) {
            addCriterion("created_time in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Date> values) {
            addCriterion("created_time not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Date value1, Date value2) {
            addCriterion("created_time between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("created_time not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andMarryStatusIsNull() {
            addCriterion("marry_status is null");
            return (Criteria) this;
        }

        public Criteria andMarryStatusIsNotNull() {
            addCriterion("marry_status is not null");
            return (Criteria) this;
        }

        public Criteria andMarryStatusEqualTo(Integer value) {
            addCriterion("marry_status =", value, "marryStatus");
            return (Criteria) this;
        }

        public Criteria andMarryStatusNotEqualTo(Integer value) {
            addCriterion("marry_status <>", value, "marryStatus");
            return (Criteria) this;
        }

        public Criteria andMarryStatusGreaterThan(Integer value) {
            addCriterion("marry_status >", value, "marryStatus");
            return (Criteria) this;
        }

        public Criteria andMarryStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("marry_status >=", value, "marryStatus");
            return (Criteria) this;
        }

        public Criteria andMarryStatusLessThan(Integer value) {
            addCriterion("marry_status <", value, "marryStatus");
            return (Criteria) this;
        }

        public Criteria andMarryStatusLessThanOrEqualTo(Integer value) {
            addCriterion("marry_status <=", value, "marryStatus");
            return (Criteria) this;
        }

        public Criteria andMarryStatusIn(List<Integer> values) {
            addCriterion("marry_status in", values, "marryStatus");
            return (Criteria) this;
        }

        public Criteria andMarryStatusNotIn(List<Integer> values) {
            addCriterion("marry_status not in", values, "marryStatus");
            return (Criteria) this;
        }

        public Criteria andMarryStatusBetween(Integer value1, Integer value2) {
            addCriterion("marry_status between", value1, value2, "marryStatus");
            return (Criteria) this;
        }

        public Criteria andMarryStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("marry_status not between", value1, value2, "marryStatus");
            return (Criteria) this;
        }

        public Criteria andUserRingIdIsNull() {
            addCriterion("user_ring_id is null");
            return (Criteria) this;
        }

        public Criteria andUserRingIdIsNotNull() {
            addCriterion("user_ring_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserRingIdEqualTo(String value) {
            addCriterion("user_ring_id =", value, "userRingId");
            return (Criteria) this;
        }

        public Criteria andUserRingIdNotEqualTo(String value) {
            addCriterion("user_ring_id <>", value, "userRingId");
            return (Criteria) this;
        }

        public Criteria andUserRingIdGreaterThan(String value) {
            addCriterion("user_ring_id >", value, "userRingId");
            return (Criteria) this;
        }

        public Criteria andUserRingIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_ring_id >=", value, "userRingId");
            return (Criteria) this;
        }

        public Criteria andUserRingIdLessThan(String value) {
            addCriterion("user_ring_id <", value, "userRingId");
            return (Criteria) this;
        }

        public Criteria andUserRingIdLessThanOrEqualTo(String value) {
            addCriterion("user_ring_id <=", value, "userRingId");
            return (Criteria) this;
        }

        public Criteria andUserRingIdLike(String value) {
            addCriterion("user_ring_id like", value, "userRingId");
            return (Criteria) this;
        }

        public Criteria andUserRingIdNotLike(String value) {
            addCriterion("user_ring_id not like", value, "userRingId");
            return (Criteria) this;
        }

        public Criteria andUserRingIdIn(List<String> values) {
            addCriterion("user_ring_id in", values, "userRingId");
            return (Criteria) this;
        }

        public Criteria andUserRingIdNotIn(List<String> values) {
            addCriterion("user_ring_id not in", values, "userRingId");
            return (Criteria) this;
        }

        public Criteria andUserRingIdBetween(String value1, String value2) {
            addCriterion("user_ring_id between", value1, value2, "userRingId");
            return (Criteria) this;
        }

        public Criteria andUserRingIdNotBetween(String value1, String value2) {
            addCriterion("user_ring_id not between", value1, value2, "userRingId");
            return (Criteria) this;
        }

        public Criteria andOtherRingIdIsNull() {
            addCriterion("other_ring_id is null");
            return (Criteria) this;
        }

        public Criteria andOtherRingIdIsNotNull() {
            addCriterion("other_ring_id is not null");
            return (Criteria) this;
        }

        public Criteria andOtherRingIdEqualTo(String value) {
            addCriterion("other_ring_id =", value, "otherRingId");
            return (Criteria) this;
        }

        public Criteria andOtherRingIdNotEqualTo(String value) {
            addCriterion("other_ring_id <>", value, "otherRingId");
            return (Criteria) this;
        }

        public Criteria andOtherRingIdGreaterThan(String value) {
            addCriterion("other_ring_id >", value, "otherRingId");
            return (Criteria) this;
        }

        public Criteria andOtherRingIdGreaterThanOrEqualTo(String value) {
            addCriterion("other_ring_id >=", value, "otherRingId");
            return (Criteria) this;
        }

        public Criteria andOtherRingIdLessThan(String value) {
            addCriterion("other_ring_id <", value, "otherRingId");
            return (Criteria) this;
        }

        public Criteria andOtherRingIdLessThanOrEqualTo(String value) {
            addCriterion("other_ring_id <=", value, "otherRingId");
            return (Criteria) this;
        }

        public Criteria andOtherRingIdLike(String value) {
            addCriterion("other_ring_id like", value, "otherRingId");
            return (Criteria) this;
        }

        public Criteria andOtherRingIdNotLike(String value) {
            addCriterion("other_ring_id not like", value, "otherRingId");
            return (Criteria) this;
        }

        public Criteria andOtherRingIdIn(List<String> values) {
            addCriterion("other_ring_id in", values, "otherRingId");
            return (Criteria) this;
        }

        public Criteria andOtherRingIdNotIn(List<String> values) {
            addCriterion("other_ring_id not in", values, "otherRingId");
            return (Criteria) this;
        }

        public Criteria andOtherRingIdBetween(String value1, String value2) {
            addCriterion("other_ring_id between", value1, value2, "otherRingId");
            return (Criteria) this;
        }

        public Criteria andOtherRingIdNotBetween(String value1, String value2) {
            addCriterion("other_ring_id not between", value1, value2, "otherRingId");
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