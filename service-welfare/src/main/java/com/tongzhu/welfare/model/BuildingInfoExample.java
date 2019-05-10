package com.tongzhu.welfare.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BuildingInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BuildingInfoExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andDetailedIsNull() {
            addCriterion("detailed is null");
            return (Criteria) this;
        }

        public Criteria andDetailedIsNotNull() {
            addCriterion("detailed is not null");
            return (Criteria) this;
        }

        public Criteria andDetailedEqualTo(String value) {
            addCriterion("detailed =", value, "detailed");
            return (Criteria) this;
        }

        public Criteria andDetailedNotEqualTo(String value) {
            addCriterion("detailed <>", value, "detailed");
            return (Criteria) this;
        }

        public Criteria andDetailedGreaterThan(String value) {
            addCriterion("detailed >", value, "detailed");
            return (Criteria) this;
        }

        public Criteria andDetailedGreaterThanOrEqualTo(String value) {
            addCriterion("detailed >=", value, "detailed");
            return (Criteria) this;
        }

        public Criteria andDetailedLessThan(String value) {
            addCriterion("detailed <", value, "detailed");
            return (Criteria) this;
        }

        public Criteria andDetailedLessThanOrEqualTo(String value) {
            addCriterion("detailed <=", value, "detailed");
            return (Criteria) this;
        }

        public Criteria andDetailedLike(String value) {
            addCriterion("detailed like", value, "detailed");
            return (Criteria) this;
        }

        public Criteria andDetailedNotLike(String value) {
            addCriterion("detailed not like", value, "detailed");
            return (Criteria) this;
        }

        public Criteria andDetailedIn(List<String> values) {
            addCriterion("detailed in", values, "detailed");
            return (Criteria) this;
        }

        public Criteria andDetailedNotIn(List<String> values) {
            addCriterion("detailed not in", values, "detailed");
            return (Criteria) this;
        }

        public Criteria andDetailedBetween(String value1, String value2) {
            addCriterion("detailed between", value1, value2, "detailed");
            return (Criteria) this;
        }

        public Criteria andDetailedNotBetween(String value1, String value2) {
            addCriterion("detailed not between", value1, value2, "detailed");
            return (Criteria) this;
        }

        public Criteria andOpenGradeIsNull() {
            addCriterion("open_grade is null");
            return (Criteria) this;
        }

        public Criteria andOpenGradeIsNotNull() {
            addCriterion("open_grade is not null");
            return (Criteria) this;
        }

        public Criteria andOpenGradeEqualTo(Integer value) {
            addCriterion("open_grade =", value, "openGrade");
            return (Criteria) this;
        }

        public Criteria andOpenGradeNotEqualTo(Integer value) {
            addCriterion("open_grade <>", value, "openGrade");
            return (Criteria) this;
        }

        public Criteria andOpenGradeGreaterThan(Integer value) {
            addCriterion("open_grade >", value, "openGrade");
            return (Criteria) this;
        }

        public Criteria andOpenGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("open_grade >=", value, "openGrade");
            return (Criteria) this;
        }

        public Criteria andOpenGradeLessThan(Integer value) {
            addCriterion("open_grade <", value, "openGrade");
            return (Criteria) this;
        }

        public Criteria andOpenGradeLessThanOrEqualTo(Integer value) {
            addCriterion("open_grade <=", value, "openGrade");
            return (Criteria) this;
        }

        public Criteria andOpenGradeIn(List<Integer> values) {
            addCriterion("open_grade in", values, "openGrade");
            return (Criteria) this;
        }

        public Criteria andOpenGradeNotIn(List<Integer> values) {
            addCriterion("open_grade not in", values, "openGrade");
            return (Criteria) this;
        }

        public Criteria andOpenGradeBetween(Integer value1, Integer value2) {
            addCriterion("open_grade between", value1, value2, "openGrade");
            return (Criteria) this;
        }

        public Criteria andOpenGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("open_grade not between", value1, value2, "openGrade");
            return (Criteria) this;
        }

        public Criteria andOpenConditionIsNull() {
            addCriterion("open_condition is null");
            return (Criteria) this;
        }

        public Criteria andOpenConditionIsNotNull() {
            addCriterion("open_condition is not null");
            return (Criteria) this;
        }

        public Criteria andOpenConditionEqualTo(Integer value) {
            addCriterion("open_condition =", value, "openCondition");
            return (Criteria) this;
        }

        public Criteria andOpenConditionNotEqualTo(Integer value) {
            addCriterion("open_condition <>", value, "openCondition");
            return (Criteria) this;
        }

        public Criteria andOpenConditionGreaterThan(Integer value) {
            addCriterion("open_condition >", value, "openCondition");
            return (Criteria) this;
        }

        public Criteria andOpenConditionGreaterThanOrEqualTo(Integer value) {
            addCriterion("open_condition >=", value, "openCondition");
            return (Criteria) this;
        }

        public Criteria andOpenConditionLessThan(Integer value) {
            addCriterion("open_condition <", value, "openCondition");
            return (Criteria) this;
        }

        public Criteria andOpenConditionLessThanOrEqualTo(Integer value) {
            addCriterion("open_condition <=", value, "openCondition");
            return (Criteria) this;
        }

        public Criteria andOpenConditionIn(List<Integer> values) {
            addCriterion("open_condition in", values, "openCondition");
            return (Criteria) this;
        }

        public Criteria andOpenConditionNotIn(List<Integer> values) {
            addCriterion("open_condition not in", values, "openCondition");
            return (Criteria) this;
        }

        public Criteria andOpenConditionBetween(Integer value1, Integer value2) {
            addCriterion("open_condition between", value1, value2, "openCondition");
            return (Criteria) this;
        }

        public Criteria andOpenConditionNotBetween(Integer value1, Integer value2) {
            addCriterion("open_condition not between", value1, value2, "openCondition");
            return (Criteria) this;
        }

        public Criteria andOutputGoldIsNull() {
            addCriterion("output_gold is null");
            return (Criteria) this;
        }

        public Criteria andOutputGoldIsNotNull() {
            addCriterion("output_gold is not null");
            return (Criteria) this;
        }

        public Criteria andOutputGoldEqualTo(Integer value) {
            addCriterion("output_gold =", value, "outputGold");
            return (Criteria) this;
        }

        public Criteria andOutputGoldNotEqualTo(Integer value) {
            addCriterion("output_gold <>", value, "outputGold");
            return (Criteria) this;
        }

        public Criteria andOutputGoldGreaterThan(Integer value) {
            addCriterion("output_gold >", value, "outputGold");
            return (Criteria) this;
        }

        public Criteria andOutputGoldGreaterThanOrEqualTo(Integer value) {
            addCriterion("output_gold >=", value, "outputGold");
            return (Criteria) this;
        }

        public Criteria andOutputGoldLessThan(Integer value) {
            addCriterion("output_gold <", value, "outputGold");
            return (Criteria) this;
        }

        public Criteria andOutputGoldLessThanOrEqualTo(Integer value) {
            addCriterion("output_gold <=", value, "outputGold");
            return (Criteria) this;
        }

        public Criteria andOutputGoldIn(List<Integer> values) {
            addCriterion("output_gold in", values, "outputGold");
            return (Criteria) this;
        }

        public Criteria andOutputGoldNotIn(List<Integer> values) {
            addCriterion("output_gold not in", values, "outputGold");
            return (Criteria) this;
        }

        public Criteria andOutputGoldBetween(Integer value1, Integer value2) {
            addCriterion("output_gold between", value1, value2, "outputGold");
            return (Criteria) this;
        }

        public Criteria andOutputGoldNotBetween(Integer value1, Integer value2) {
            addCriterion("output_gold not between", value1, value2, "outputGold");
            return (Criteria) this;
        }

        public Criteria andHavePlacedIsNull() {
            addCriterion("have_placed is null");
            return (Criteria) this;
        }

        public Criteria andHavePlacedIsNotNull() {
            addCriterion("have_placed is not null");
            return (Criteria) this;
        }

        public Criteria andHavePlacedEqualTo(Integer value) {
            addCriterion("have_placed =", value, "havePlaced");
            return (Criteria) this;
        }

        public Criteria andHavePlacedNotEqualTo(Integer value) {
            addCriterion("have_placed <>", value, "havePlaced");
            return (Criteria) this;
        }

        public Criteria andHavePlacedGreaterThan(Integer value) {
            addCriterion("have_placed >", value, "havePlaced");
            return (Criteria) this;
        }

        public Criteria andHavePlacedGreaterThanOrEqualTo(Integer value) {
            addCriterion("have_placed >=", value, "havePlaced");
            return (Criteria) this;
        }

        public Criteria andHavePlacedLessThan(Integer value) {
            addCriterion("have_placed <", value, "havePlaced");
            return (Criteria) this;
        }

        public Criteria andHavePlacedLessThanOrEqualTo(Integer value) {
            addCriterion("have_placed <=", value, "havePlaced");
            return (Criteria) this;
        }

        public Criteria andHavePlacedIn(List<Integer> values) {
            addCriterion("have_placed in", values, "havePlaced");
            return (Criteria) this;
        }

        public Criteria andHavePlacedNotIn(List<Integer> values) {
            addCriterion("have_placed not in", values, "havePlaced");
            return (Criteria) this;
        }

        public Criteria andHavePlacedBetween(Integer value1, Integer value2) {
            addCriterion("have_placed between", value1, value2, "havePlaced");
            return (Criteria) this;
        }

        public Criteria andHavePlacedNotBetween(Integer value1, Integer value2) {
            addCriterion("have_placed not between", value1, value2, "havePlaced");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
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