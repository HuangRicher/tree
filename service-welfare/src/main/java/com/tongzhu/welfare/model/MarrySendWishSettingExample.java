package com.tongzhu.welfare.model;

import java.util.ArrayList;
import java.util.List;

public class MarrySendWishSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MarrySendWishSettingExample() {
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

        public Criteria andGradeIsNull() {
            addCriterion("grade is null");
            return (Criteria) this;
        }

        public Criteria andGradeIsNotNull() {
            addCriterion("grade is not null");
            return (Criteria) this;
        }

        public Criteria andGradeEqualTo(Integer value) {
            addCriterion("grade =", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotEqualTo(Integer value) {
            addCriterion("grade <>", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThan(Integer value) {
            addCriterion("grade >", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("grade >=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThan(Integer value) {
            addCriterion("grade <", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThanOrEqualTo(Integer value) {
            addCriterion("grade <=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeIn(List<Integer> values) {
            addCriterion("grade in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotIn(List<Integer> values) {
            addCriterion("grade not in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeBetween(Integer value1, Integer value2) {
            addCriterion("grade between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("grade not between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andSendWishIsNull() {
            addCriterion("send_wish is null");
            return (Criteria) this;
        }

        public Criteria andSendWishIsNotNull() {
            addCriterion("send_wish is not null");
            return (Criteria) this;
        }

        public Criteria andSendWishEqualTo(Integer value) {
            addCriterion("send_wish =", value, "sendWish");
            return (Criteria) this;
        }

        public Criteria andSendWishNotEqualTo(Integer value) {
            addCriterion("send_wish <>", value, "sendWish");
            return (Criteria) this;
        }

        public Criteria andSendWishGreaterThan(Integer value) {
            addCriterion("send_wish >", value, "sendWish");
            return (Criteria) this;
        }

        public Criteria andSendWishGreaterThanOrEqualTo(Integer value) {
            addCriterion("send_wish >=", value, "sendWish");
            return (Criteria) this;
        }

        public Criteria andSendWishLessThan(Integer value) {
            addCriterion("send_wish <", value, "sendWish");
            return (Criteria) this;
        }

        public Criteria andSendWishLessThanOrEqualTo(Integer value) {
            addCriterion("send_wish <=", value, "sendWish");
            return (Criteria) this;
        }

        public Criteria andSendWishIn(List<Integer> values) {
            addCriterion("send_wish in", values, "sendWish");
            return (Criteria) this;
        }

        public Criteria andSendWishNotIn(List<Integer> values) {
            addCriterion("send_wish not in", values, "sendWish");
            return (Criteria) this;
        }

        public Criteria andSendWishBetween(Integer value1, Integer value2) {
            addCriterion("send_wish between", value1, value2, "sendWish");
            return (Criteria) this;
        }

        public Criteria andSendWishNotBetween(Integer value1, Integer value2) {
            addCriterion("send_wish not between", value1, value2, "sendWish");
            return (Criteria) this;
        }

        public Criteria andToastIsNull() {
            addCriterion("toast is null");
            return (Criteria) this;
        }

        public Criteria andToastIsNotNull() {
            addCriterion("toast is not null");
            return (Criteria) this;
        }

        public Criteria andToastEqualTo(Integer value) {
            addCriterion("toast =", value, "toast");
            return (Criteria) this;
        }

        public Criteria andToastNotEqualTo(Integer value) {
            addCriterion("toast <>", value, "toast");
            return (Criteria) this;
        }

        public Criteria andToastGreaterThan(Integer value) {
            addCriterion("toast >", value, "toast");
            return (Criteria) this;
        }

        public Criteria andToastGreaterThanOrEqualTo(Integer value) {
            addCriterion("toast >=", value, "toast");
            return (Criteria) this;
        }

        public Criteria andToastLessThan(Integer value) {
            addCriterion("toast <", value, "toast");
            return (Criteria) this;
        }

        public Criteria andToastLessThanOrEqualTo(Integer value) {
            addCriterion("toast <=", value, "toast");
            return (Criteria) this;
        }

        public Criteria andToastIn(List<Integer> values) {
            addCriterion("toast in", values, "toast");
            return (Criteria) this;
        }

        public Criteria andToastNotIn(List<Integer> values) {
            addCriterion("toast not in", values, "toast");
            return (Criteria) this;
        }

        public Criteria andToastBetween(Integer value1, Integer value2) {
            addCriterion("toast between", value1, value2, "toast");
            return (Criteria) this;
        }

        public Criteria andToastNotBetween(Integer value1, Integer value2) {
            addCriterion("toast not between", value1, value2, "toast");
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