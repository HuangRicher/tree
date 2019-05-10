package com.tongzhu.welfare.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MarryLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MarryLogExample() {
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

        public Criteria andMarryTypeIsNull() {
            addCriterion("marry_type is null");
            return (Criteria) this;
        }

        public Criteria andMarryTypeIsNotNull() {
            addCriterion("marry_type is not null");
            return (Criteria) this;
        }

        public Criteria andMarryTypeEqualTo(Integer value) {
            addCriterion("marry_type =", value, "marryType");
            return (Criteria) this;
        }

        public Criteria andMarryTypeNotEqualTo(Integer value) {
            addCriterion("marry_type <>", value, "marryType");
            return (Criteria) this;
        }

        public Criteria andMarryTypeGreaterThan(Integer value) {
            addCriterion("marry_type >", value, "marryType");
            return (Criteria) this;
        }

        public Criteria andMarryTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("marry_type >=", value, "marryType");
            return (Criteria) this;
        }

        public Criteria andMarryTypeLessThan(Integer value) {
            addCriterion("marry_type <", value, "marryType");
            return (Criteria) this;
        }

        public Criteria andMarryTypeLessThanOrEqualTo(Integer value) {
            addCriterion("marry_type <=", value, "marryType");
            return (Criteria) this;
        }

        public Criteria andMarryTypeIn(List<Integer> values) {
            addCriterion("marry_type in", values, "marryType");
            return (Criteria) this;
        }

        public Criteria andMarryTypeNotIn(List<Integer> values) {
            addCriterion("marry_type not in", values, "marryType");
            return (Criteria) this;
        }

        public Criteria andMarryTypeBetween(Integer value1, Integer value2) {
            addCriterion("marry_type between", value1, value2, "marryType");
            return (Criteria) this;
        }

        public Criteria andMarryTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("marry_type not between", value1, value2, "marryType");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNull() {
            addCriterion("begin_time is null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNotNull() {
            addCriterion("begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeEqualTo(Date value) {
            addCriterion("begin_time =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(Date value) {
            addCriterion("begin_time <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(Date value) {
            addCriterion("begin_time >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("begin_time >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(Date value) {
            addCriterion("begin_time <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("begin_time <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<Date> values) {
            addCriterion("begin_time in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<Date> values) {
            addCriterion("begin_time not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(Date value1, Date value2) {
            addCriterion("begin_time between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("begin_time not between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEngagementIdIsNull() {
            addCriterion("engagement_id is null");
            return (Criteria) this;
        }

        public Criteria andEngagementIdIsNotNull() {
            addCriterion("engagement_id is not null");
            return (Criteria) this;
        }

        public Criteria andEngagementIdEqualTo(String value) {
            addCriterion("engagement_id =", value, "engagementId");
            return (Criteria) this;
        }

        public Criteria andEngagementIdNotEqualTo(String value) {
            addCriterion("engagement_id <>", value, "engagementId");
            return (Criteria) this;
        }

        public Criteria andEngagementIdGreaterThan(String value) {
            addCriterion("engagement_id >", value, "engagementId");
            return (Criteria) this;
        }

        public Criteria andEngagementIdGreaterThanOrEqualTo(String value) {
            addCriterion("engagement_id >=", value, "engagementId");
            return (Criteria) this;
        }

        public Criteria andEngagementIdLessThan(String value) {
            addCriterion("engagement_id <", value, "engagementId");
            return (Criteria) this;
        }

        public Criteria andEngagementIdLessThanOrEqualTo(String value) {
            addCriterion("engagement_id <=", value, "engagementId");
            return (Criteria) this;
        }

        public Criteria andEngagementIdLike(String value) {
            addCriterion("engagement_id like", value, "engagementId");
            return (Criteria) this;
        }

        public Criteria andEngagementIdNotLike(String value) {
            addCriterion("engagement_id not like", value, "engagementId");
            return (Criteria) this;
        }

        public Criteria andEngagementIdIn(List<String> values) {
            addCriterion("engagement_id in", values, "engagementId");
            return (Criteria) this;
        }

        public Criteria andEngagementIdNotIn(List<String> values) {
            addCriterion("engagement_id not in", values, "engagementId");
            return (Criteria) this;
        }

        public Criteria andEngagementIdBetween(String value1, String value2) {
            addCriterion("engagement_id between", value1, value2, "engagementId");
            return (Criteria) this;
        }

        public Criteria andEngagementIdNotBetween(String value1, String value2) {
            addCriterion("engagement_id not between", value1, value2, "engagementId");
            return (Criteria) this;
        }

        public Criteria andUserJoyfulNumIsNull() {
            addCriterion("user_joyful_num is null");
            return (Criteria) this;
        }

        public Criteria andUserJoyfulNumIsNotNull() {
            addCriterion("user_joyful_num is not null");
            return (Criteria) this;
        }

        public Criteria andUserJoyfulNumEqualTo(Integer value) {
            addCriterion("user_joyful_num =", value, "userJoyfulNum");
            return (Criteria) this;
        }

        public Criteria andUserJoyfulNumNotEqualTo(Integer value) {
            addCriterion("user_joyful_num <>", value, "userJoyfulNum");
            return (Criteria) this;
        }

        public Criteria andUserJoyfulNumGreaterThan(Integer value) {
            addCriterion("user_joyful_num >", value, "userJoyfulNum");
            return (Criteria) this;
        }

        public Criteria andUserJoyfulNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_joyful_num >=", value, "userJoyfulNum");
            return (Criteria) this;
        }

        public Criteria andUserJoyfulNumLessThan(Integer value) {
            addCriterion("user_joyful_num <", value, "userJoyfulNum");
            return (Criteria) this;
        }

        public Criteria andUserJoyfulNumLessThanOrEqualTo(Integer value) {
            addCriterion("user_joyful_num <=", value, "userJoyfulNum");
            return (Criteria) this;
        }

        public Criteria andUserJoyfulNumIn(List<Integer> values) {
            addCriterion("user_joyful_num in", values, "userJoyfulNum");
            return (Criteria) this;
        }

        public Criteria andUserJoyfulNumNotIn(List<Integer> values) {
            addCriterion("user_joyful_num not in", values, "userJoyfulNum");
            return (Criteria) this;
        }

        public Criteria andUserJoyfulNumBetween(Integer value1, Integer value2) {
            addCriterion("user_joyful_num between", value1, value2, "userJoyfulNum");
            return (Criteria) this;
        }

        public Criteria andUserJoyfulNumNotBetween(Integer value1, Integer value2) {
            addCriterion("user_joyful_num not between", value1, value2, "userJoyfulNum");
            return (Criteria) this;
        }

        public Criteria andOtherJoyfulNumIsNull() {
            addCriterion("other_joyful_num is null");
            return (Criteria) this;
        }

        public Criteria andOtherJoyfulNumIsNotNull() {
            addCriterion("other_joyful_num is not null");
            return (Criteria) this;
        }

        public Criteria andOtherJoyfulNumEqualTo(Integer value) {
            addCriterion("other_joyful_num =", value, "otherJoyfulNum");
            return (Criteria) this;
        }

        public Criteria andOtherJoyfulNumNotEqualTo(Integer value) {
            addCriterion("other_joyful_num <>", value, "otherJoyfulNum");
            return (Criteria) this;
        }

        public Criteria andOtherJoyfulNumGreaterThan(Integer value) {
            addCriterion("other_joyful_num >", value, "otherJoyfulNum");
            return (Criteria) this;
        }

        public Criteria andOtherJoyfulNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("other_joyful_num >=", value, "otherJoyfulNum");
            return (Criteria) this;
        }

        public Criteria andOtherJoyfulNumLessThan(Integer value) {
            addCriterion("other_joyful_num <", value, "otherJoyfulNum");
            return (Criteria) this;
        }

        public Criteria andOtherJoyfulNumLessThanOrEqualTo(Integer value) {
            addCriterion("other_joyful_num <=", value, "otherJoyfulNum");
            return (Criteria) this;
        }

        public Criteria andOtherJoyfulNumIn(List<Integer> values) {
            addCriterion("other_joyful_num in", values, "otherJoyfulNum");
            return (Criteria) this;
        }

        public Criteria andOtherJoyfulNumNotIn(List<Integer> values) {
            addCriterion("other_joyful_num not in", values, "otherJoyfulNum");
            return (Criteria) this;
        }

        public Criteria andOtherJoyfulNumBetween(Integer value1, Integer value2) {
            addCriterion("other_joyful_num between", value1, value2, "otherJoyfulNum");
            return (Criteria) this;
        }

        public Criteria andOtherJoyfulNumNotBetween(Integer value1, Integer value2) {
            addCriterion("other_joyful_num not between", value1, value2, "otherJoyfulNum");
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