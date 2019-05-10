package com.tongzhu.treehouse.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TreeHouseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TreeHouseExample() {
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

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andCultureValueIsNull() {
            addCriterion("culture_value is null");
            return (Criteria) this;
        }

        public Criteria andCultureValueIsNotNull() {
            addCriterion("culture_value is not null");
            return (Criteria) this;
        }

        public Criteria andCultureValueEqualTo(Integer value) {
            addCriterion("culture_value =", value, "cultureValue");
            return (Criteria) this;
        }

        public Criteria andCultureValueNotEqualTo(Integer value) {
            addCriterion("culture_value <>", value, "cultureValue");
            return (Criteria) this;
        }

        public Criteria andCultureValueGreaterThan(Integer value) {
            addCriterion("culture_value >", value, "cultureValue");
            return (Criteria) this;
        }

        public Criteria andCultureValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("culture_value >=", value, "cultureValue");
            return (Criteria) this;
        }

        public Criteria andCultureValueLessThan(Integer value) {
            addCriterion("culture_value <", value, "cultureValue");
            return (Criteria) this;
        }

        public Criteria andCultureValueLessThanOrEqualTo(Integer value) {
            addCriterion("culture_value <=", value, "cultureValue");
            return (Criteria) this;
        }

        public Criteria andCultureValueIn(List<Integer> values) {
            addCriterion("culture_value in", values, "cultureValue");
            return (Criteria) this;
        }

        public Criteria andCultureValueNotIn(List<Integer> values) {
            addCriterion("culture_value not in", values, "cultureValue");
            return (Criteria) this;
        }

        public Criteria andCultureValueBetween(Integer value1, Integer value2) {
            addCriterion("culture_value between", value1, value2, "cultureValue");
            return (Criteria) this;
        }

        public Criteria andCultureValueNotBetween(Integer value1, Integer value2) {
            addCriterion("culture_value not between", value1, value2, "cultureValue");
            return (Criteria) this;
        }

        public Criteria andBreakValueIsNull() {
            addCriterion("break_value is null");
            return (Criteria) this;
        }

        public Criteria andBreakValueIsNotNull() {
            addCriterion("break_value is not null");
            return (Criteria) this;
        }

        public Criteria andBreakValueEqualTo(Integer value) {
            addCriterion("break_value =", value, "breakValue");
            return (Criteria) this;
        }

        public Criteria andBreakValueNotEqualTo(Integer value) {
            addCriterion("break_value <>", value, "breakValue");
            return (Criteria) this;
        }

        public Criteria andBreakValueGreaterThan(Integer value) {
            addCriterion("break_value >", value, "breakValue");
            return (Criteria) this;
        }

        public Criteria andBreakValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("break_value >=", value, "breakValue");
            return (Criteria) this;
        }

        public Criteria andBreakValueLessThan(Integer value) {
            addCriterion("break_value <", value, "breakValue");
            return (Criteria) this;
        }

        public Criteria andBreakValueLessThanOrEqualTo(Integer value) {
            addCriterion("break_value <=", value, "breakValue");
            return (Criteria) this;
        }

        public Criteria andBreakValueIn(List<Integer> values) {
            addCriterion("break_value in", values, "breakValue");
            return (Criteria) this;
        }

        public Criteria andBreakValueNotIn(List<Integer> values) {
            addCriterion("break_value not in", values, "breakValue");
            return (Criteria) this;
        }

        public Criteria andBreakValueBetween(Integer value1, Integer value2) {
            addCriterion("break_value between", value1, value2, "breakValue");
            return (Criteria) this;
        }

        public Criteria andBreakValueNotBetween(Integer value1, Integer value2) {
            addCriterion("break_value not between", value1, value2, "breakValue");
            return (Criteria) this;
        }

        public Criteria andFlourishingRateIsNull() {
            addCriterion("flourishing_rate is null");
            return (Criteria) this;
        }

        public Criteria andFlourishingRateIsNotNull() {
            addCriterion("flourishing_rate is not null");
            return (Criteria) this;
        }

        public Criteria andFlourishingRateEqualTo(Integer value) {
            addCriterion("flourishing_rate =", value, "flourishingRate");
            return (Criteria) this;
        }

        public Criteria andFlourishingRateNotEqualTo(Integer value) {
            addCriterion("flourishing_rate <>", value, "flourishingRate");
            return (Criteria) this;
        }

        public Criteria andFlourishingRateGreaterThan(Integer value) {
            addCriterion("flourishing_rate >", value, "flourishingRate");
            return (Criteria) this;
        }

        public Criteria andFlourishingRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("flourishing_rate >=", value, "flourishingRate");
            return (Criteria) this;
        }

        public Criteria andFlourishingRateLessThan(Integer value) {
            addCriterion("flourishing_rate <", value, "flourishingRate");
            return (Criteria) this;
        }

        public Criteria andFlourishingRateLessThanOrEqualTo(Integer value) {
            addCriterion("flourishing_rate <=", value, "flourishingRate");
            return (Criteria) this;
        }

        public Criteria andFlourishingRateIn(List<Integer> values) {
            addCriterion("flourishing_rate in", values, "flourishingRate");
            return (Criteria) this;
        }

        public Criteria andFlourishingRateNotIn(List<Integer> values) {
            addCriterion("flourishing_rate not in", values, "flourishingRate");
            return (Criteria) this;
        }

        public Criteria andFlourishingRateBetween(Integer value1, Integer value2) {
            addCriterion("flourishing_rate between", value1, value2, "flourishingRate");
            return (Criteria) this;
        }

        public Criteria andFlourishingRateNotBetween(Integer value1, Integer value2) {
            addCriterion("flourishing_rate not between", value1, value2, "flourishingRate");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("start_date is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("start_date is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(Date value) {
            addCriterion("start_date =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(Date value) {
            addCriterion("start_date <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(Date value) {
            addCriterion("start_date >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("start_date >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(Date value) {
            addCriterion("start_date <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(Date value) {
            addCriterion("start_date <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<Date> values) {
            addCriterion("start_date in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<Date> values) {
            addCriterion("start_date not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(Date value1, Date value2) {
            addCriterion("start_date between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(Date value1, Date value2) {
            addCriterion("start_date not between", value1, value2, "startDate");
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

        public Criteria andAmbienceCountIsNull() {
            addCriterion("ambience_count is null");
            return (Criteria) this;
        }

        public Criteria andAmbienceCountIsNotNull() {
            addCriterion("ambience_count is not null");
            return (Criteria) this;
        }

        public Criteria andAmbienceCountEqualTo(Integer value) {
            addCriterion("ambience_count =", value, "ambienceCount");
            return (Criteria) this;
        }

        public Criteria andAmbienceCountNotEqualTo(Integer value) {
            addCriterion("ambience_count <>", value, "ambienceCount");
            return (Criteria) this;
        }

        public Criteria andAmbienceCountGreaterThan(Integer value) {
            addCriterion("ambience_count >", value, "ambienceCount");
            return (Criteria) this;
        }

        public Criteria andAmbienceCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("ambience_count >=", value, "ambienceCount");
            return (Criteria) this;
        }

        public Criteria andAmbienceCountLessThan(Integer value) {
            addCriterion("ambience_count <", value, "ambienceCount");
            return (Criteria) this;
        }

        public Criteria andAmbienceCountLessThanOrEqualTo(Integer value) {
            addCriterion("ambience_count <=", value, "ambienceCount");
            return (Criteria) this;
        }

        public Criteria andAmbienceCountIn(List<Integer> values) {
            addCriterion("ambience_count in", values, "ambienceCount");
            return (Criteria) this;
        }

        public Criteria andAmbienceCountNotIn(List<Integer> values) {
            addCriterion("ambience_count not in", values, "ambienceCount");
            return (Criteria) this;
        }

        public Criteria andAmbienceCountBetween(Integer value1, Integer value2) {
            addCriterion("ambience_count between", value1, value2, "ambienceCount");
            return (Criteria) this;
        }

        public Criteria andAmbienceCountNotBetween(Integer value1, Integer value2) {
            addCriterion("ambience_count not between", value1, value2, "ambienceCount");
            return (Criteria) this;
        }

        public Criteria andAmusementCountIsNull() {
            addCriterion("amusement_count is null");
            return (Criteria) this;
        }

        public Criteria andAmusementCountIsNotNull() {
            addCriterion("amusement_count is not null");
            return (Criteria) this;
        }

        public Criteria andAmusementCountEqualTo(Integer value) {
            addCriterion("amusement_count =", value, "amusementCount");
            return (Criteria) this;
        }

        public Criteria andAmusementCountNotEqualTo(Integer value) {
            addCriterion("amusement_count <>", value, "amusementCount");
            return (Criteria) this;
        }

        public Criteria andAmusementCountGreaterThan(Integer value) {
            addCriterion("amusement_count >", value, "amusementCount");
            return (Criteria) this;
        }

        public Criteria andAmusementCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("amusement_count >=", value, "amusementCount");
            return (Criteria) this;
        }

        public Criteria andAmusementCountLessThan(Integer value) {
            addCriterion("amusement_count <", value, "amusementCount");
            return (Criteria) this;
        }

        public Criteria andAmusementCountLessThanOrEqualTo(Integer value) {
            addCriterion("amusement_count <=", value, "amusementCount");
            return (Criteria) this;
        }

        public Criteria andAmusementCountIn(List<Integer> values) {
            addCriterion("amusement_count in", values, "amusementCount");
            return (Criteria) this;
        }

        public Criteria andAmusementCountNotIn(List<Integer> values) {
            addCriterion("amusement_count not in", values, "amusementCount");
            return (Criteria) this;
        }

        public Criteria andAmusementCountBetween(Integer value1, Integer value2) {
            addCriterion("amusement_count between", value1, value2, "amusementCount");
            return (Criteria) this;
        }

        public Criteria andAmusementCountNotBetween(Integer value1, Integer value2) {
            addCriterion("amusement_count not between", value1, value2, "amusementCount");
            return (Criteria) this;
        }

        public Criteria andEnvironmentCountIsNull() {
            addCriterion("environment_count is null");
            return (Criteria) this;
        }

        public Criteria andEnvironmentCountIsNotNull() {
            addCriterion("environment_count is not null");
            return (Criteria) this;
        }

        public Criteria andEnvironmentCountEqualTo(Integer value) {
            addCriterion("environment_count =", value, "environmentCount");
            return (Criteria) this;
        }

        public Criteria andEnvironmentCountNotEqualTo(Integer value) {
            addCriterion("environment_count <>", value, "environmentCount");
            return (Criteria) this;
        }

        public Criteria andEnvironmentCountGreaterThan(Integer value) {
            addCriterion("environment_count >", value, "environmentCount");
            return (Criteria) this;
        }

        public Criteria andEnvironmentCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("environment_count >=", value, "environmentCount");
            return (Criteria) this;
        }

        public Criteria andEnvironmentCountLessThan(Integer value) {
            addCriterion("environment_count <", value, "environmentCount");
            return (Criteria) this;
        }

        public Criteria andEnvironmentCountLessThanOrEqualTo(Integer value) {
            addCriterion("environment_count <=", value, "environmentCount");
            return (Criteria) this;
        }

        public Criteria andEnvironmentCountIn(List<Integer> values) {
            addCriterion("environment_count in", values, "environmentCount");
            return (Criteria) this;
        }

        public Criteria andEnvironmentCountNotIn(List<Integer> values) {
            addCriterion("environment_count not in", values, "environmentCount");
            return (Criteria) this;
        }

        public Criteria andEnvironmentCountBetween(Integer value1, Integer value2) {
            addCriterion("environment_count between", value1, value2, "environmentCount");
            return (Criteria) this;
        }

        public Criteria andEnvironmentCountNotBetween(Integer value1, Integer value2) {
            addCriterion("environment_count not between", value1, value2, "environmentCount");
            return (Criteria) this;
        }

        public Criteria andStyleIsNull() {
            addCriterion("style_ is null");
            return (Criteria) this;
        }

        public Criteria andStyleIsNotNull() {
            addCriterion("style_ is not null");
            return (Criteria) this;
        }

        public Criteria andStyleEqualTo(Integer value) {
            addCriterion("style_ =", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleNotEqualTo(Integer value) {
            addCriterion("style_ <>", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleGreaterThan(Integer value) {
            addCriterion("style_ >", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleGreaterThanOrEqualTo(Integer value) {
            addCriterion("style_ >=", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleLessThan(Integer value) {
            addCriterion("style_ <", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleLessThanOrEqualTo(Integer value) {
            addCriterion("style_ <=", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleIn(List<Integer> values) {
            addCriterion("style_ in", values, "style");
            return (Criteria) this;
        }

        public Criteria andStyleNotIn(List<Integer> values) {
            addCriterion("style_ not in", values, "style");
            return (Criteria) this;
        }

        public Criteria andStyleBetween(Integer value1, Integer value2) {
            addCriterion("style_ between", value1, value2, "style");
            return (Criteria) this;
        }

        public Criteria andStyleNotBetween(Integer value1, Integer value2) {
            addCriterion("style_ not between", value1, value2, "style");
            return (Criteria) this;
        }

        public Criteria andCanSpeakIsNull() {
            addCriterion("can_speak is null");
            return (Criteria) this;
        }

        public Criteria andCanSpeakIsNotNull() {
            addCriterion("can_speak is not null");
            return (Criteria) this;
        }

        public Criteria andCanSpeakEqualTo(Boolean value) {
            addCriterion("can_speak =", value, "canSpeak");
            return (Criteria) this;
        }

        public Criteria andCanSpeakNotEqualTo(Boolean value) {
            addCriterion("can_speak <>", value, "canSpeak");
            return (Criteria) this;
        }

        public Criteria andCanSpeakGreaterThan(Boolean value) {
            addCriterion("can_speak >", value, "canSpeak");
            return (Criteria) this;
        }

        public Criteria andCanSpeakGreaterThanOrEqualTo(Boolean value) {
            addCriterion("can_speak >=", value, "canSpeak");
            return (Criteria) this;
        }

        public Criteria andCanSpeakLessThan(Boolean value) {
            addCriterion("can_speak <", value, "canSpeak");
            return (Criteria) this;
        }

        public Criteria andCanSpeakLessThanOrEqualTo(Boolean value) {
            addCriterion("can_speak <=", value, "canSpeak");
            return (Criteria) this;
        }

        public Criteria andCanSpeakIn(List<Boolean> values) {
            addCriterion("can_speak in", values, "canSpeak");
            return (Criteria) this;
        }

        public Criteria andCanSpeakNotIn(List<Boolean> values) {
            addCriterion("can_speak not in", values, "canSpeak");
            return (Criteria) this;
        }

        public Criteria andCanSpeakBetween(Boolean value1, Boolean value2) {
            addCriterion("can_speak between", value1, value2, "canSpeak");
            return (Criteria) this;
        }

        public Criteria andCanSpeakNotBetween(Boolean value1, Boolean value2) {
            addCriterion("can_speak not between", value1, value2, "canSpeak");
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