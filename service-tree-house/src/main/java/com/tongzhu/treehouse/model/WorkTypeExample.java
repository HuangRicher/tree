package com.tongzhu.treehouse.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WorkTypeExample implements Serializable {
    /**
     * tz_work_type
     */
    protected String orderByClause;

    /**
     * tz_work_type
     */
    protected boolean distinct;

    /**
     * tz_work_type
     */
    protected List<Criteria> oredCriteria;

    /**
     * tz_work_type
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * @mbggenerated 2018-09-05
     */
    public WorkTypeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     *
     * @mbggenerated 2018-09-05
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     *
     * @mbggenerated 2018-09-05
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     *
     * @mbggenerated 2018-09-05
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     *
     * @mbggenerated 2018-09-05
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     *
     * @mbggenerated 2018-09-05
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * @mbggenerated 2018-09-05
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * @mbggenerated 2018-09-05
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *
     * @mbggenerated 2018-09-05
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     *
     * @mbggenerated 2018-09-05
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *
     * @mbggenerated 2018-09-05
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * tz_work_type 2018-09-05
     */
    protected abstract static class GeneratedCriteria implements Serializable {
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

        public Criteria andWorkTypeNameIsNull() {
            addCriterion("work_type_name is null");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNameIsNotNull() {
            addCriterion("work_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNameEqualTo(String value) {
            addCriterion("work_type_name =", value, "workTypeName");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNameNotEqualTo(String value) {
            addCriterion("work_type_name <>", value, "workTypeName");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNameGreaterThan(String value) {
            addCriterion("work_type_name >", value, "workTypeName");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("work_type_name >=", value, "workTypeName");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNameLessThan(String value) {
            addCriterion("work_type_name <", value, "workTypeName");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNameLessThanOrEqualTo(String value) {
            addCriterion("work_type_name <=", value, "workTypeName");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNameLike(String value) {
            addCriterion("work_type_name like", value, "workTypeName");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNameNotLike(String value) {
            addCriterion("work_type_name not like", value, "workTypeName");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNameIn(List<String> values) {
            addCriterion("work_type_name in", values, "workTypeName");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNameNotIn(List<String> values) {
            addCriterion("work_type_name not in", values, "workTypeName");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNameBetween(String value1, String value2) {
            addCriterion("work_type_name between", value1, value2, "workTypeName");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNameNotBetween(String value1, String value2) {
            addCriterion("work_type_name not between", value1, value2, "workTypeName");
            return (Criteria) this;
        }

        public Criteria andOrderIsNull() {
            addCriterion("order_ is null");
            return (Criteria) this;
        }

        public Criteria andOrderIsNotNull() {
            addCriterion("order_ is not null");
            return (Criteria) this;
        }

        public Criteria andOrderEqualTo(Integer value) {
            addCriterion("order_ =", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderNotEqualTo(Integer value) {
            addCriterion("order_ <>", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderGreaterThan(Integer value) {
            addCriterion("order_ >", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_ >=", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderLessThan(Integer value) {
            addCriterion("order_ <", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderLessThanOrEqualTo(Integer value) {
            addCriterion("order_ <=", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderIn(List<Integer> values) {
            addCriterion("order_ in", values, "order");
            return (Criteria) this;
        }

        public Criteria andOrderNotIn(List<Integer> values) {
            addCriterion("order_ not in", values, "order");
            return (Criteria) this;
        }

        public Criteria andOrderBetween(Integer value1, Integer value2) {
            addCriterion("order_ between", value1, value2, "order");
            return (Criteria) this;
        }

        public Criteria andOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("order_ not between", value1, value2, "order");
            return (Criteria) this;
        }

        public Criteria andUnlockLevelIsNull() {
            addCriterion("unlock_level is null");
            return (Criteria) this;
        }

        public Criteria andUnlockLevelIsNotNull() {
            addCriterion("unlock_level is not null");
            return (Criteria) this;
        }

        public Criteria andUnlockLevelEqualTo(Integer value) {
            addCriterion("unlock_level =", value, "unlockLevel");
            return (Criteria) this;
        }

        public Criteria andUnlockLevelNotEqualTo(Integer value) {
            addCriterion("unlock_level <>", value, "unlockLevel");
            return (Criteria) this;
        }

        public Criteria andUnlockLevelGreaterThan(Integer value) {
            addCriterion("unlock_level >", value, "unlockLevel");
            return (Criteria) this;
        }

        public Criteria andUnlockLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("unlock_level >=", value, "unlockLevel");
            return (Criteria) this;
        }

        public Criteria andUnlockLevelLessThan(Integer value) {
            addCriterion("unlock_level <", value, "unlockLevel");
            return (Criteria) this;
        }

        public Criteria andUnlockLevelLessThanOrEqualTo(Integer value) {
            addCriterion("unlock_level <=", value, "unlockLevel");
            return (Criteria) this;
        }

        public Criteria andUnlockLevelIn(List<Integer> values) {
            addCriterion("unlock_level in", values, "unlockLevel");
            return (Criteria) this;
        }

        public Criteria andUnlockLevelNotIn(List<Integer> values) {
            addCriterion("unlock_level not in", values, "unlockLevel");
            return (Criteria) this;
        }

        public Criteria andUnlockLevelBetween(Integer value1, Integer value2) {
            addCriterion("unlock_level between", value1, value2, "unlockLevel");
            return (Criteria) this;
        }

        public Criteria andUnlockLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("unlock_level not between", value1, value2, "unlockLevel");
            return (Criteria) this;
        }
    }

    /**
     * tz_work_type
     */
    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    /**
     * tz_work_type 2018-09-05
     */
    public static class Criterion implements Serializable {
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