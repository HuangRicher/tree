package com.tongzhu.user.model;

import java.util.ArrayList;
import java.util.List;

public class FightFriendExpSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FightFriendExpSettingExample() {
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

        public Criteria andRoleLevelIsNull() {
            addCriterion("role_level is null");
            return (Criteria) this;
        }

        public Criteria andRoleLevelIsNotNull() {
            addCriterion("role_level is not null");
            return (Criteria) this;
        }

        public Criteria andRoleLevelEqualTo(Integer value) {
            addCriterion("role_level =", value, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelNotEqualTo(Integer value) {
            addCriterion("role_level <>", value, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelGreaterThan(Integer value) {
            addCriterion("role_level >", value, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("role_level >=", value, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelLessThan(Integer value) {
            addCriterion("role_level <", value, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelLessThanOrEqualTo(Integer value) {
            addCriterion("role_level <=", value, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelIn(List<Integer> values) {
            addCriterion("role_level in", values, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelNotIn(List<Integer> values) {
            addCriterion("role_level not in", values, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelBetween(Integer value1, Integer value2) {
            addCriterion("role_level between", value1, value2, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("role_level not between", value1, value2, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andDayMaxIsNull() {
            addCriterion("day_max is null");
            return (Criteria) this;
        }

        public Criteria andDayMaxIsNotNull() {
            addCriterion("day_max is not null");
            return (Criteria) this;
        }

        public Criteria andDayMaxEqualTo(Integer value) {
            addCriterion("day_max =", value, "dayMax");
            return (Criteria) this;
        }

        public Criteria andDayMaxNotEqualTo(Integer value) {
            addCriterion("day_max <>", value, "dayMax");
            return (Criteria) this;
        }

        public Criteria andDayMaxGreaterThan(Integer value) {
            addCriterion("day_max >", value, "dayMax");
            return (Criteria) this;
        }

        public Criteria andDayMaxGreaterThanOrEqualTo(Integer value) {
            addCriterion("day_max >=", value, "dayMax");
            return (Criteria) this;
        }

        public Criteria andDayMaxLessThan(Integer value) {
            addCriterion("day_max <", value, "dayMax");
            return (Criteria) this;
        }

        public Criteria andDayMaxLessThanOrEqualTo(Integer value) {
            addCriterion("day_max <=", value, "dayMax");
            return (Criteria) this;
        }

        public Criteria andDayMaxIn(List<Integer> values) {
            addCriterion("day_max in", values, "dayMax");
            return (Criteria) this;
        }

        public Criteria andDayMaxNotIn(List<Integer> values) {
            addCriterion("day_max not in", values, "dayMax");
            return (Criteria) this;
        }

        public Criteria andDayMaxBetween(Integer value1, Integer value2) {
            addCriterion("day_max between", value1, value2, "dayMax");
            return (Criteria) this;
        }

        public Criteria andDayMaxNotBetween(Integer value1, Integer value2) {
            addCriterion("day_max not between", value1, value2, "dayMax");
            return (Criteria) this;
        }

        public Criteria andSuccessExpIsNull() {
            addCriterion("success_exp is null");
            return (Criteria) this;
        }

        public Criteria andSuccessExpIsNotNull() {
            addCriterion("success_exp is not null");
            return (Criteria) this;
        }

        public Criteria andSuccessExpEqualTo(Integer value) {
            addCriterion("success_exp =", value, "successExp");
            return (Criteria) this;
        }

        public Criteria andSuccessExpNotEqualTo(Integer value) {
            addCriterion("success_exp <>", value, "successExp");
            return (Criteria) this;
        }

        public Criteria andSuccessExpGreaterThan(Integer value) {
            addCriterion("success_exp >", value, "successExp");
            return (Criteria) this;
        }

        public Criteria andSuccessExpGreaterThanOrEqualTo(Integer value) {
            addCriterion("success_exp >=", value, "successExp");
            return (Criteria) this;
        }

        public Criteria andSuccessExpLessThan(Integer value) {
            addCriterion("success_exp <", value, "successExp");
            return (Criteria) this;
        }

        public Criteria andSuccessExpLessThanOrEqualTo(Integer value) {
            addCriterion("success_exp <=", value, "successExp");
            return (Criteria) this;
        }

        public Criteria andSuccessExpIn(List<Integer> values) {
            addCriterion("success_exp in", values, "successExp");
            return (Criteria) this;
        }

        public Criteria andSuccessExpNotIn(List<Integer> values) {
            addCriterion("success_exp not in", values, "successExp");
            return (Criteria) this;
        }

        public Criteria andSuccessExpBetween(Integer value1, Integer value2) {
            addCriterion("success_exp between", value1, value2, "successExp");
            return (Criteria) this;
        }

        public Criteria andSuccessExpNotBetween(Integer value1, Integer value2) {
            addCriterion("success_exp not between", value1, value2, "successExp");
            return (Criteria) this;
        }

        public Criteria andFailExpIsNull() {
            addCriterion("fail_exp is null");
            return (Criteria) this;
        }

        public Criteria andFailExpIsNotNull() {
            addCriterion("fail_exp is not null");
            return (Criteria) this;
        }

        public Criteria andFailExpEqualTo(Integer value) {
            addCriterion("fail_exp =", value, "failExp");
            return (Criteria) this;
        }

        public Criteria andFailExpNotEqualTo(Integer value) {
            addCriterion("fail_exp <>", value, "failExp");
            return (Criteria) this;
        }

        public Criteria andFailExpGreaterThan(Integer value) {
            addCriterion("fail_exp >", value, "failExp");
            return (Criteria) this;
        }

        public Criteria andFailExpGreaterThanOrEqualTo(Integer value) {
            addCriterion("fail_exp >=", value, "failExp");
            return (Criteria) this;
        }

        public Criteria andFailExpLessThan(Integer value) {
            addCriterion("fail_exp <", value, "failExp");
            return (Criteria) this;
        }

        public Criteria andFailExpLessThanOrEqualTo(Integer value) {
            addCriterion("fail_exp <=", value, "failExp");
            return (Criteria) this;
        }

        public Criteria andFailExpIn(List<Integer> values) {
            addCriterion("fail_exp in", values, "failExp");
            return (Criteria) this;
        }

        public Criteria andFailExpNotIn(List<Integer> values) {
            addCriterion("fail_exp not in", values, "failExp");
            return (Criteria) this;
        }

        public Criteria andFailExpBetween(Integer value1, Integer value2) {
            addCriterion("fail_exp between", value1, value2, "failExp");
            return (Criteria) this;
        }

        public Criteria andFailExpNotBetween(Integer value1, Integer value2) {
            addCriterion("fail_exp not between", value1, value2, "failExp");
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