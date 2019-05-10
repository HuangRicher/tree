package com.tongzhu.welfare.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoveTreeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LoveTreeExample() {
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

        public Criteria andLoveTreeGradeIsNull() {
            addCriterion("love_tree_grade is null");
            return (Criteria) this;
        }

        public Criteria andLoveTreeGradeIsNotNull() {
            addCriterion("love_tree_grade is not null");
            return (Criteria) this;
        }

        public Criteria andLoveTreeGradeEqualTo(Integer value) {
            addCriterion("love_tree_grade =", value, "loveTreeGrade");
            return (Criteria) this;
        }

        public Criteria andLoveTreeGradeNotEqualTo(Integer value) {
            addCriterion("love_tree_grade <>", value, "loveTreeGrade");
            return (Criteria) this;
        }

        public Criteria andLoveTreeGradeGreaterThan(Integer value) {
            addCriterion("love_tree_grade >", value, "loveTreeGrade");
            return (Criteria) this;
        }

        public Criteria andLoveTreeGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("love_tree_grade >=", value, "loveTreeGrade");
            return (Criteria) this;
        }

        public Criteria andLoveTreeGradeLessThan(Integer value) {
            addCriterion("love_tree_grade <", value, "loveTreeGrade");
            return (Criteria) this;
        }

        public Criteria andLoveTreeGradeLessThanOrEqualTo(Integer value) {
            addCriterion("love_tree_grade <=", value, "loveTreeGrade");
            return (Criteria) this;
        }

        public Criteria andLoveTreeGradeIn(List<Integer> values) {
            addCriterion("love_tree_grade in", values, "loveTreeGrade");
            return (Criteria) this;
        }

        public Criteria andLoveTreeGradeNotIn(List<Integer> values) {
            addCriterion("love_tree_grade not in", values, "loveTreeGrade");
            return (Criteria) this;
        }

        public Criteria andLoveTreeGradeBetween(Integer value1, Integer value2) {
            addCriterion("love_tree_grade between", value1, value2, "loveTreeGrade");
            return (Criteria) this;
        }

        public Criteria andLoveTreeGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("love_tree_grade not between", value1, value2, "loveTreeGrade");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andExpLimitIsNull() {
            addCriterion("exp_limit is null");
            return (Criteria) this;
        }

        public Criteria andExpLimitIsNotNull() {
            addCriterion("exp_limit is not null");
            return (Criteria) this;
        }

        public Criteria andExpLimitEqualTo(Integer value) {
            addCriterion("exp_limit =", value, "expLimit");
            return (Criteria) this;
        }

        public Criteria andExpLimitNotEqualTo(Integer value) {
            addCriterion("exp_limit <>", value, "expLimit");
            return (Criteria) this;
        }

        public Criteria andExpLimitGreaterThan(Integer value) {
            addCriterion("exp_limit >", value, "expLimit");
            return (Criteria) this;
        }

        public Criteria andExpLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("exp_limit >=", value, "expLimit");
            return (Criteria) this;
        }

        public Criteria andExpLimitLessThan(Integer value) {
            addCriterion("exp_limit <", value, "expLimit");
            return (Criteria) this;
        }

        public Criteria andExpLimitLessThanOrEqualTo(Integer value) {
            addCriterion("exp_limit <=", value, "expLimit");
            return (Criteria) this;
        }

        public Criteria andExpLimitIn(List<Integer> values) {
            addCriterion("exp_limit in", values, "expLimit");
            return (Criteria) this;
        }

        public Criteria andExpLimitNotIn(List<Integer> values) {
            addCriterion("exp_limit not in", values, "expLimit");
            return (Criteria) this;
        }

        public Criteria andExpLimitBetween(Integer value1, Integer value2) {
            addCriterion("exp_limit between", value1, value2, "expLimit");
            return (Criteria) this;
        }

        public Criteria andExpLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("exp_limit not between", value1, value2, "expLimit");
            return (Criteria) this;
        }

        public Criteria andExpNumIsNull() {
            addCriterion("exp_num is null");
            return (Criteria) this;
        }

        public Criteria andExpNumIsNotNull() {
            addCriterion("exp_num is not null");
            return (Criteria) this;
        }

        public Criteria andExpNumEqualTo(Integer value) {
            addCriterion("exp_num =", value, "expNum");
            return (Criteria) this;
        }

        public Criteria andExpNumNotEqualTo(Integer value) {
            addCriterion("exp_num <>", value, "expNum");
            return (Criteria) this;
        }

        public Criteria andExpNumGreaterThan(Integer value) {
            addCriterion("exp_num >", value, "expNum");
            return (Criteria) this;
        }

        public Criteria andExpNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("exp_num >=", value, "expNum");
            return (Criteria) this;
        }

        public Criteria andExpNumLessThan(Integer value) {
            addCriterion("exp_num <", value, "expNum");
            return (Criteria) this;
        }

        public Criteria andExpNumLessThanOrEqualTo(Integer value) {
            addCriterion("exp_num <=", value, "expNum");
            return (Criteria) this;
        }

        public Criteria andExpNumIn(List<Integer> values) {
            addCriterion("exp_num in", values, "expNum");
            return (Criteria) this;
        }

        public Criteria andExpNumNotIn(List<Integer> values) {
            addCriterion("exp_num not in", values, "expNum");
            return (Criteria) this;
        }

        public Criteria andExpNumBetween(Integer value1, Integer value2) {
            addCriterion("exp_num between", value1, value2, "expNum");
            return (Criteria) this;
        }

        public Criteria andExpNumNotBetween(Integer value1, Integer value2) {
            addCriterion("exp_num not between", value1, value2, "expNum");
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