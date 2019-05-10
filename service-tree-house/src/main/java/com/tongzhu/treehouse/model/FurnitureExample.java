package com.tongzhu.treehouse.model;

import java.util.ArrayList;
import java.util.List;

public class FurnitureExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FurnitureExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name_ is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name_ is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name_ =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name_ <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name_ >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name_ >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name_ <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name_ <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name_ like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name_ not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name_ in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name_ not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name_ between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name_ not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(String value) {
            addCriterion("category_id =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(String value) {
            addCriterion("category_id <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(String value) {
            addCriterion("category_id >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("category_id >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(String value) {
            addCriterion("category_id <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("category_id <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLike(String value) {
            addCriterion("category_id like", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotLike(String value) {
            addCriterion("category_id not like", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<String> values) {
            addCriterion("category_id in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<String> values) {
            addCriterion("category_id not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(String value1, String value2) {
            addCriterion("category_id between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(String value1, String value2) {
            addCriterion("category_id not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andUseableTimeIsNull() {
            addCriterion("useable_time is null");
            return (Criteria) this;
        }

        public Criteria andUseableTimeIsNotNull() {
            addCriterion("useable_time is not null");
            return (Criteria) this;
        }

        public Criteria andUseableTimeEqualTo(Integer value) {
            addCriterion("useable_time =", value, "useableTime");
            return (Criteria) this;
        }

        public Criteria andUseableTimeNotEqualTo(Integer value) {
            addCriterion("useable_time <>", value, "useableTime");
            return (Criteria) this;
        }

        public Criteria andUseableTimeGreaterThan(Integer value) {
            addCriterion("useable_time >", value, "useableTime");
            return (Criteria) this;
        }

        public Criteria andUseableTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("useable_time >=", value, "useableTime");
            return (Criteria) this;
        }

        public Criteria andUseableTimeLessThan(Integer value) {
            addCriterion("useable_time <", value, "useableTime");
            return (Criteria) this;
        }

        public Criteria andUseableTimeLessThanOrEqualTo(Integer value) {
            addCriterion("useable_time <=", value, "useableTime");
            return (Criteria) this;
        }

        public Criteria andUseableTimeIn(List<Integer> values) {
            addCriterion("useable_time in", values, "useableTime");
            return (Criteria) this;
        }

        public Criteria andUseableTimeNotIn(List<Integer> values) {
            addCriterion("useable_time not in", values, "useableTime");
            return (Criteria) this;
        }

        public Criteria andUseableTimeBetween(Integer value1, Integer value2) {
            addCriterion("useable_time between", value1, value2, "useableTime");
            return (Criteria) this;
        }

        public Criteria andUseableTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("useable_time not between", value1, value2, "useableTime");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type_ is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type_ is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type_ =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type_ <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type_ >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type_ >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type_ <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type_ <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type_ in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type_ not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type_ between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type_ not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("location_ is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("location_ is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(Integer value) {
            addCriterion("location_ =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(Integer value) {
            addCriterion("location_ <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(Integer value) {
            addCriterion("location_ >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(Integer value) {
            addCriterion("location_ >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(Integer value) {
            addCriterion("location_ <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(Integer value) {
            addCriterion("location_ <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<Integer> values) {
            addCriterion("location_ in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<Integer> values) {
            addCriterion("location_ not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(Integer value1, Integer value2) {
            addCriterion("location_ between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(Integer value1, Integer value2) {
            addCriterion("location_ not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andOutputRateIsNull() {
            addCriterion("output_rate is null");
            return (Criteria) this;
        }

        public Criteria andOutputRateIsNotNull() {
            addCriterion("output_rate is not null");
            return (Criteria) this;
        }

        public Criteria andOutputRateEqualTo(Integer value) {
            addCriterion("output_rate =", value, "outputRate");
            return (Criteria) this;
        }

        public Criteria andOutputRateNotEqualTo(Integer value) {
            addCriterion("output_rate <>", value, "outputRate");
            return (Criteria) this;
        }

        public Criteria andOutputRateGreaterThan(Integer value) {
            addCriterion("output_rate >", value, "outputRate");
            return (Criteria) this;
        }

        public Criteria andOutputRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("output_rate >=", value, "outputRate");
            return (Criteria) this;
        }

        public Criteria andOutputRateLessThan(Integer value) {
            addCriterion("output_rate <", value, "outputRate");
            return (Criteria) this;
        }

        public Criteria andOutputRateLessThanOrEqualTo(Integer value) {
            addCriterion("output_rate <=", value, "outputRate");
            return (Criteria) this;
        }

        public Criteria andOutputRateIn(List<Integer> values) {
            addCriterion("output_rate in", values, "outputRate");
            return (Criteria) this;
        }

        public Criteria andOutputRateNotIn(List<Integer> values) {
            addCriterion("output_rate not in", values, "outputRate");
            return (Criteria) this;
        }

        public Criteria andOutputRateBetween(Integer value1, Integer value2) {
            addCriterion("output_rate between", value1, value2, "outputRate");
            return (Criteria) this;
        }

        public Criteria andOutputRateNotBetween(Integer value1, Integer value2) {
            addCriterion("output_rate not between", value1, value2, "outputRate");
            return (Criteria) this;
        }

        public Criteria andOutputValueIsNull() {
            addCriterion("output_value is null");
            return (Criteria) this;
        }

        public Criteria andOutputValueIsNotNull() {
            addCriterion("output_value is not null");
            return (Criteria) this;
        }

        public Criteria andOutputValueEqualTo(Integer value) {
            addCriterion("output_value =", value, "outputValue");
            return (Criteria) this;
        }

        public Criteria andOutputValueNotEqualTo(Integer value) {
            addCriterion("output_value <>", value, "outputValue");
            return (Criteria) this;
        }

        public Criteria andOutputValueGreaterThan(Integer value) {
            addCriterion("output_value >", value, "outputValue");
            return (Criteria) this;
        }

        public Criteria andOutputValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("output_value >=", value, "outputValue");
            return (Criteria) this;
        }

        public Criteria andOutputValueLessThan(Integer value) {
            addCriterion("output_value <", value, "outputValue");
            return (Criteria) this;
        }

        public Criteria andOutputValueLessThanOrEqualTo(Integer value) {
            addCriterion("output_value <=", value, "outputValue");
            return (Criteria) this;
        }

        public Criteria andOutputValueIn(List<Integer> values) {
            addCriterion("output_value in", values, "outputValue");
            return (Criteria) this;
        }

        public Criteria andOutputValueNotIn(List<Integer> values) {
            addCriterion("output_value not in", values, "outputValue");
            return (Criteria) this;
        }

        public Criteria andOutputValueBetween(Integer value1, Integer value2) {
            addCriterion("output_value between", value1, value2, "outputValue");
            return (Criteria) this;
        }

        public Criteria andOutputValueNotBetween(Integer value1, Integer value2) {
            addCriterion("output_value not between", value1, value2, "outputValue");
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