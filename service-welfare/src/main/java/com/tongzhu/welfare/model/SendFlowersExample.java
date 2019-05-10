package com.tongzhu.welfare.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SendFlowersExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SendFlowersExample() {
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

        public Criteria andSengIdIsNull() {
            addCriterion("seng_id is null");
            return (Criteria) this;
        }

        public Criteria andSengIdIsNotNull() {
            addCriterion("seng_id is not null");
            return (Criteria) this;
        }

        public Criteria andSengIdEqualTo(String value) {
            addCriterion("seng_id =", value, "sengId");
            return (Criteria) this;
        }

        public Criteria andSengIdNotEqualTo(String value) {
            addCriterion("seng_id <>", value, "sengId");
            return (Criteria) this;
        }

        public Criteria andSengIdGreaterThan(String value) {
            addCriterion("seng_id >", value, "sengId");
            return (Criteria) this;
        }

        public Criteria andSengIdGreaterThanOrEqualTo(String value) {
            addCriterion("seng_id >=", value, "sengId");
            return (Criteria) this;
        }

        public Criteria andSengIdLessThan(String value) {
            addCriterion("seng_id <", value, "sengId");
            return (Criteria) this;
        }

        public Criteria andSengIdLessThanOrEqualTo(String value) {
            addCriterion("seng_id <=", value, "sengId");
            return (Criteria) this;
        }

        public Criteria andSengIdLike(String value) {
            addCriterion("seng_id like", value, "sengId");
            return (Criteria) this;
        }

        public Criteria andSengIdNotLike(String value) {
            addCriterion("seng_id not like", value, "sengId");
            return (Criteria) this;
        }

        public Criteria andSengIdIn(List<String> values) {
            addCriterion("seng_id in", values, "sengId");
            return (Criteria) this;
        }

        public Criteria andSengIdNotIn(List<String> values) {
            addCriterion("seng_id not in", values, "sengId");
            return (Criteria) this;
        }

        public Criteria andSengIdBetween(String value1, String value2) {
            addCriterion("seng_id between", value1, value2, "sengId");
            return (Criteria) this;
        }

        public Criteria andSengIdNotBetween(String value1, String value2) {
            addCriterion("seng_id not between", value1, value2, "sengId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdIsNull() {
            addCriterion("receive_id is null");
            return (Criteria) this;
        }

        public Criteria andReceiveIdIsNotNull() {
            addCriterion("receive_id is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveIdEqualTo(String value) {
            addCriterion("receive_id =", value, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdNotEqualTo(String value) {
            addCriterion("receive_id <>", value, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdGreaterThan(String value) {
            addCriterion("receive_id >", value, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdGreaterThanOrEqualTo(String value) {
            addCriterion("receive_id >=", value, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdLessThan(String value) {
            addCriterion("receive_id <", value, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdLessThanOrEqualTo(String value) {
            addCriterion("receive_id <=", value, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdLike(String value) {
            addCriterion("receive_id like", value, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdNotLike(String value) {
            addCriterion("receive_id not like", value, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdIn(List<String> values) {
            addCriterion("receive_id in", values, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdNotIn(List<String> values) {
            addCriterion("receive_id not in", values, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdBetween(String value1, String value2) {
            addCriterion("receive_id between", value1, value2, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdNotBetween(String value1, String value2) {
            addCriterion("receive_id not between", value1, value2, "receiveId");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(Integer value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(Integer value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(Integer value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(Integer value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(Integer value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<Integer> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<Integer> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(Integer value1, Integer value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(Integer value1, Integer value2) {
            addCriterion("num not between", value1, value2, "num");
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

        public Criteria andIsFriendIsNull() {
            addCriterion("is_friend is null");
            return (Criteria) this;
        }

        public Criteria andIsFriendIsNotNull() {
            addCriterion("is_friend is not null");
            return (Criteria) this;
        }

        public Criteria andIsFriendEqualTo(Integer value) {
            addCriterion("is_friend =", value, "isFriend");
            return (Criteria) this;
        }

        public Criteria andIsFriendNotEqualTo(Integer value) {
            addCriterion("is_friend <>", value, "isFriend");
            return (Criteria) this;
        }

        public Criteria andIsFriendGreaterThan(Integer value) {
            addCriterion("is_friend >", value, "isFriend");
            return (Criteria) this;
        }

        public Criteria andIsFriendGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_friend >=", value, "isFriend");
            return (Criteria) this;
        }

        public Criteria andIsFriendLessThan(Integer value) {
            addCriterion("is_friend <", value, "isFriend");
            return (Criteria) this;
        }

        public Criteria andIsFriendLessThanOrEqualTo(Integer value) {
            addCriterion("is_friend <=", value, "isFriend");
            return (Criteria) this;
        }

        public Criteria andIsFriendIn(List<Integer> values) {
            addCriterion("is_friend in", values, "isFriend");
            return (Criteria) this;
        }

        public Criteria andIsFriendNotIn(List<Integer> values) {
            addCriterion("is_friend not in", values, "isFriend");
            return (Criteria) this;
        }

        public Criteria andIsFriendBetween(Integer value1, Integer value2) {
            addCriterion("is_friend between", value1, value2, "isFriend");
            return (Criteria) this;
        }

        public Criteria andIsFriendNotBetween(Integer value1, Integer value2) {
            addCriterion("is_friend not between", value1, value2, "isFriend");
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