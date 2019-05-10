package com.tongzhu.usergoods.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PropInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PropInfoExample() {
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

        public Criteria andQualityIsNull() {
            addCriterion("quality is null");
            return (Criteria) this;
        }

        public Criteria andQualityIsNotNull() {
            addCriterion("quality is not null");
            return (Criteria) this;
        }

        public Criteria andQualityEqualTo(Integer value) {
            addCriterion("quality =", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityNotEqualTo(Integer value) {
            addCriterion("quality <>", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityGreaterThan(Integer value) {
            addCriterion("quality >", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityGreaterThanOrEqualTo(Integer value) {
            addCriterion("quality >=", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityLessThan(Integer value) {
            addCriterion("quality <", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityLessThanOrEqualTo(Integer value) {
            addCriterion("quality <=", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityIn(List<Integer> values) {
            addCriterion("quality in", values, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityNotIn(List<Integer> values) {
            addCriterion("quality not in", values, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityBetween(Integer value1, Integer value2) {
            addCriterion("quality between", value1, value2, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityNotBetween(Integer value1, Integer value2) {
            addCriterion("quality not between", value1, value2, "quality");
            return (Criteria) this;
        }

        public Criteria andFightingCapacityIsNull() {
            addCriterion("fighting_capacity is null");
            return (Criteria) this;
        }

        public Criteria andFightingCapacityIsNotNull() {
            addCriterion("fighting_capacity is not null");
            return (Criteria) this;
        }

        public Criteria andFightingCapacityEqualTo(Double value) {
            addCriterion("fighting_capacity =", value, "fightingCapacity");
            return (Criteria) this;
        }

        public Criteria andFightingCapacityNotEqualTo(Double value) {
            addCriterion("fighting_capacity <>", value, "fightingCapacity");
            return (Criteria) this;
        }

        public Criteria andFightingCapacityGreaterThan(Double value) {
            addCriterion("fighting_capacity >", value, "fightingCapacity");
            return (Criteria) this;
        }

        public Criteria andFightingCapacityGreaterThanOrEqualTo(Double value) {
            addCriterion("fighting_capacity >=", value, "fightingCapacity");
            return (Criteria) this;
        }

        public Criteria andFightingCapacityLessThan(Double value) {
            addCriterion("fighting_capacity <", value, "fightingCapacity");
            return (Criteria) this;
        }

        public Criteria andFightingCapacityLessThanOrEqualTo(Double value) {
            addCriterion("fighting_capacity <=", value, "fightingCapacity");
            return (Criteria) this;
        }

        public Criteria andFightingCapacityIn(List<Double> values) {
            addCriterion("fighting_capacity in", values, "fightingCapacity");
            return (Criteria) this;
        }

        public Criteria andFightingCapacityNotIn(List<Double> values) {
            addCriterion("fighting_capacity not in", values, "fightingCapacity");
            return (Criteria) this;
        }

        public Criteria andFightingCapacityBetween(Double value1, Double value2) {
            addCriterion("fighting_capacity between", value1, value2, "fightingCapacity");
            return (Criteria) this;
        }

        public Criteria andFightingCapacityNotBetween(Double value1, Double value2) {
            addCriterion("fighting_capacity not between", value1, value2, "fightingCapacity");
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

        public Criteria andOccupationIsNull() {
            addCriterion("occupation is null");
            return (Criteria) this;
        }

        public Criteria andOccupationIsNotNull() {
            addCriterion("occupation is not null");
            return (Criteria) this;
        }

        public Criteria andOccupationEqualTo(Integer value) {
            addCriterion("occupation =", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationNotEqualTo(Integer value) {
            addCriterion("occupation <>", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationGreaterThan(Integer value) {
            addCriterion("occupation >", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationGreaterThanOrEqualTo(Integer value) {
            addCriterion("occupation >=", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationLessThan(Integer value) {
            addCriterion("occupation <", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationLessThanOrEqualTo(Integer value) {
            addCriterion("occupation <=", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationIn(List<Integer> values) {
            addCriterion("occupation in", values, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationNotIn(List<Integer> values) {
            addCriterion("occupation not in", values, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationBetween(Integer value1, Integer value2) {
            addCriterion("occupation between", value1, value2, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationNotBetween(Integer value1, Integer value2) {
            addCriterion("occupation not between", value1, value2, "occupation");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(Integer value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(Integer value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(Integer value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(Integer value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(Integer value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(Integer value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<Integer> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<Integer> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(Integer value1, Integer value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(Integer value1, Integer value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSceneIsNull() {
            addCriterion("scene is null");
            return (Criteria) this;
        }

        public Criteria andSceneIsNotNull() {
            addCriterion("scene is not null");
            return (Criteria) this;
        }

        public Criteria andSceneEqualTo(Integer value) {
            addCriterion("scene =", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneNotEqualTo(Integer value) {
            addCriterion("scene <>", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneGreaterThan(Integer value) {
            addCriterion("scene >", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneGreaterThanOrEqualTo(Integer value) {
            addCriterion("scene >=", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneLessThan(Integer value) {
            addCriterion("scene <", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneLessThanOrEqualTo(Integer value) {
            addCriterion("scene <=", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneIn(List<Integer> values) {
            addCriterion("scene in", values, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneNotIn(List<Integer> values) {
            addCriterion("scene not in", values, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneBetween(Integer value1, Integer value2) {
            addCriterion("scene between", value1, value2, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneNotBetween(Integer value1, Integer value2) {
            addCriterion("scene not between", value1, value2, "scene");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andAcquiringWayIsNull() {
            addCriterion("acquiring_way is null");
            return (Criteria) this;
        }

        public Criteria andAcquiringWayIsNotNull() {
            addCriterion("acquiring_way is not null");
            return (Criteria) this;
        }

        public Criteria andAcquiringWayEqualTo(String value) {
            addCriterion("acquiring_way =", value, "acquiringWay");
            return (Criteria) this;
        }

        public Criteria andAcquiringWayNotEqualTo(String value) {
            addCriterion("acquiring_way <>", value, "acquiringWay");
            return (Criteria) this;
        }

        public Criteria andAcquiringWayGreaterThan(String value) {
            addCriterion("acquiring_way >", value, "acquiringWay");
            return (Criteria) this;
        }

        public Criteria andAcquiringWayGreaterThanOrEqualTo(String value) {
            addCriterion("acquiring_way >=", value, "acquiringWay");
            return (Criteria) this;
        }

        public Criteria andAcquiringWayLessThan(String value) {
            addCriterion("acquiring_way <", value, "acquiringWay");
            return (Criteria) this;
        }

        public Criteria andAcquiringWayLessThanOrEqualTo(String value) {
            addCriterion("acquiring_way <=", value, "acquiringWay");
            return (Criteria) this;
        }

        public Criteria andAcquiringWayLike(String value) {
            addCriterion("acquiring_way like", value, "acquiringWay");
            return (Criteria) this;
        }

        public Criteria andAcquiringWayNotLike(String value) {
            addCriterion("acquiring_way not like", value, "acquiringWay");
            return (Criteria) this;
        }

        public Criteria andAcquiringWayIn(List<String> values) {
            addCriterion("acquiring_way in", values, "acquiringWay");
            return (Criteria) this;
        }

        public Criteria andAcquiringWayNotIn(List<String> values) {
            addCriterion("acquiring_way not in", values, "acquiringWay");
            return (Criteria) this;
        }

        public Criteria andAcquiringWayBetween(String value1, String value2) {
            addCriterion("acquiring_way between", value1, value2, "acquiringWay");
            return (Criteria) this;
        }

        public Criteria andAcquiringWayNotBetween(String value1, String value2) {
            addCriterion("acquiring_way not between", value1, value2, "acquiringWay");
            return (Criteria) this;
        }

        public Criteria andExpirationTimeIsNull() {
            addCriterion("expiration_time is null");
            return (Criteria) this;
        }

        public Criteria andExpirationTimeIsNotNull() {
            addCriterion("expiration_time is not null");
            return (Criteria) this;
        }

        public Criteria andExpirationTimeEqualTo(Date value) {
            addCriterion("expiration_time =", value, "expirationTime");
            return (Criteria) this;
        }

        public Criteria andExpirationTimeNotEqualTo(Date value) {
            addCriterion("expiration_time <>", value, "expirationTime");
            return (Criteria) this;
        }

        public Criteria andExpirationTimeGreaterThan(Date value) {
            addCriterion("expiration_time >", value, "expirationTime");
            return (Criteria) this;
        }

        public Criteria andExpirationTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("expiration_time >=", value, "expirationTime");
            return (Criteria) this;
        }

        public Criteria andExpirationTimeLessThan(Date value) {
            addCriterion("expiration_time <", value, "expirationTime");
            return (Criteria) this;
        }

        public Criteria andExpirationTimeLessThanOrEqualTo(Date value) {
            addCriterion("expiration_time <=", value, "expirationTime");
            return (Criteria) this;
        }

        public Criteria andExpirationTimeIn(List<Date> values) {
            addCriterion("expiration_time in", values, "expirationTime");
            return (Criteria) this;
        }

        public Criteria andExpirationTimeNotIn(List<Date> values) {
            addCriterion("expiration_time not in", values, "expirationTime");
            return (Criteria) this;
        }

        public Criteria andExpirationTimeBetween(Date value1, Date value2) {
            addCriterion("expiration_time between", value1, value2, "expirationTime");
            return (Criteria) this;
        }

        public Criteria andExpirationTimeNotBetween(Date value1, Date value2) {
            addCriterion("expiration_time not between", value1, value2, "expirationTime");
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

        public Criteria andConversionPropIdIsNull() {
            addCriterion("conversion_prop_id is null");
            return (Criteria) this;
        }

        public Criteria andConversionPropIdIsNotNull() {
            addCriterion("conversion_prop_id is not null");
            return (Criteria) this;
        }

        public Criteria andConversionPropIdEqualTo(String value) {
            addCriterion("conversion_prop_id =", value, "conversionPropId");
            return (Criteria) this;
        }

        public Criteria andConversionPropIdNotEqualTo(String value) {
            addCriterion("conversion_prop_id <>", value, "conversionPropId");
            return (Criteria) this;
        }

        public Criteria andConversionPropIdGreaterThan(String value) {
            addCriterion("conversion_prop_id >", value, "conversionPropId");
            return (Criteria) this;
        }

        public Criteria andConversionPropIdGreaterThanOrEqualTo(String value) {
            addCriterion("conversion_prop_id >=", value, "conversionPropId");
            return (Criteria) this;
        }

        public Criteria andConversionPropIdLessThan(String value) {
            addCriterion("conversion_prop_id <", value, "conversionPropId");
            return (Criteria) this;
        }

        public Criteria andConversionPropIdLessThanOrEqualTo(String value) {
            addCriterion("conversion_prop_id <=", value, "conversionPropId");
            return (Criteria) this;
        }

        public Criteria andConversionPropIdLike(String value) {
            addCriterion("conversion_prop_id like", value, "conversionPropId");
            return (Criteria) this;
        }

        public Criteria andConversionPropIdNotLike(String value) {
            addCriterion("conversion_prop_id not like", value, "conversionPropId");
            return (Criteria) this;
        }

        public Criteria andConversionPropIdIn(List<String> values) {
            addCriterion("conversion_prop_id in", values, "conversionPropId");
            return (Criteria) this;
        }

        public Criteria andConversionPropIdNotIn(List<String> values) {
            addCriterion("conversion_prop_id not in", values, "conversionPropId");
            return (Criteria) this;
        }

        public Criteria andConversionPropIdBetween(String value1, String value2) {
            addCriterion("conversion_prop_id between", value1, value2, "conversionPropId");
            return (Criteria) this;
        }

        public Criteria andConversionPropIdNotBetween(String value1, String value2) {
            addCriterion("conversion_prop_id not between", value1, value2, "conversionPropId");
            return (Criteria) this;
        }

        public Criteria andConversionAmountIsNull() {
            addCriterion("conversion_amount is null");
            return (Criteria) this;
        }

        public Criteria andConversionAmountIsNotNull() {
            addCriterion("conversion_amount is not null");
            return (Criteria) this;
        }

        public Criteria andConversionAmountEqualTo(Integer value) {
            addCriterion("conversion_amount =", value, "conversionAmount");
            return (Criteria) this;
        }

        public Criteria andConversionAmountNotEqualTo(Integer value) {
            addCriterion("conversion_amount <>", value, "conversionAmount");
            return (Criteria) this;
        }

        public Criteria andConversionAmountGreaterThan(Integer value) {
            addCriterion("conversion_amount >", value, "conversionAmount");
            return (Criteria) this;
        }

        public Criteria andConversionAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("conversion_amount >=", value, "conversionAmount");
            return (Criteria) this;
        }

        public Criteria andConversionAmountLessThan(Integer value) {
            addCriterion("conversion_amount <", value, "conversionAmount");
            return (Criteria) this;
        }

        public Criteria andConversionAmountLessThanOrEqualTo(Integer value) {
            addCriterion("conversion_amount <=", value, "conversionAmount");
            return (Criteria) this;
        }

        public Criteria andConversionAmountIn(List<Integer> values) {
            addCriterion("conversion_amount in", values, "conversionAmount");
            return (Criteria) this;
        }

        public Criteria andConversionAmountNotIn(List<Integer> values) {
            addCriterion("conversion_amount not in", values, "conversionAmount");
            return (Criteria) this;
        }

        public Criteria andConversionAmountBetween(Integer value1, Integer value2) {
            addCriterion("conversion_amount between", value1, value2, "conversionAmount");
            return (Criteria) this;
        }

        public Criteria andConversionAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("conversion_amount not between", value1, value2, "conversionAmount");
            return (Criteria) this;
        }

        public Criteria andIconidIsNull() {
            addCriterion("iconid is null");
            return (Criteria) this;
        }

        public Criteria andIconidIsNotNull() {
            addCriterion("iconid is not null");
            return (Criteria) this;
        }

        public Criteria andIconidEqualTo(String value) {
            addCriterion("iconid =", value, "iconid");
            return (Criteria) this;
        }

        public Criteria andIconidNotEqualTo(String value) {
            addCriterion("iconid <>", value, "iconid");
            return (Criteria) this;
        }

        public Criteria andIconidGreaterThan(String value) {
            addCriterion("iconid >", value, "iconid");
            return (Criteria) this;
        }

        public Criteria andIconidGreaterThanOrEqualTo(String value) {
            addCriterion("iconid >=", value, "iconid");
            return (Criteria) this;
        }

        public Criteria andIconidLessThan(String value) {
            addCriterion("iconid <", value, "iconid");
            return (Criteria) this;
        }

        public Criteria andIconidLessThanOrEqualTo(String value) {
            addCriterion("iconid <=", value, "iconid");
            return (Criteria) this;
        }

        public Criteria andIconidLike(String value) {
            addCriterion("iconid like", value, "iconid");
            return (Criteria) this;
        }

        public Criteria andIconidNotLike(String value) {
            addCriterion("iconid not like", value, "iconid");
            return (Criteria) this;
        }

        public Criteria andIconidIn(List<String> values) {
            addCriterion("iconid in", values, "iconid");
            return (Criteria) this;
        }

        public Criteria andIconidNotIn(List<String> values) {
            addCriterion("iconid not in", values, "iconid");
            return (Criteria) this;
        }

        public Criteria andIconidBetween(String value1, String value2) {
            addCriterion("iconid between", value1, value2, "iconid");
            return (Criteria) this;
        }

        public Criteria andIconidNotBetween(String value1, String value2) {
            addCriterion("iconid not between", value1, value2, "iconid");
            return (Criteria) this;
        }

        public Criteria andRoleIsNull() {
            addCriterion("role is null");
            return (Criteria) this;
        }

        public Criteria andRoleIsNotNull() {
            addCriterion("role is not null");
            return (Criteria) this;
        }

        public Criteria andRoleEqualTo(String value) {
            addCriterion("role =", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotEqualTo(String value) {
            addCriterion("role <>", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleGreaterThan(String value) {
            addCriterion("role >", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleGreaterThanOrEqualTo(String value) {
            addCriterion("role >=", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLessThan(String value) {
            addCriterion("role <", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLessThanOrEqualTo(String value) {
            addCriterion("role <=", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLike(String value) {
            addCriterion("role like", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotLike(String value) {
            addCriterion("role not like", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleIn(List<String> values) {
            addCriterion("role in", values, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotIn(List<String> values) {
            addCriterion("role not in", values, "role");
            return (Criteria) this;
        }

        public Criteria andRoleBetween(String value1, String value2) {
            addCriterion("role between", value1, value2, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotBetween(String value1, String value2) {
            addCriterion("role not between", value1, value2, "role");
            return (Criteria) this;
        }

        public Criteria andLogRecordIsNull() {
            addCriterion("log_record is null");
            return (Criteria) this;
        }

        public Criteria andLogRecordIsNotNull() {
            addCriterion("log_record is not null");
            return (Criteria) this;
        }

        public Criteria andLogRecordEqualTo(Integer value) {
            addCriterion("log_record =", value, "logRecord");
            return (Criteria) this;
        }

        public Criteria andLogRecordNotEqualTo(Integer value) {
            addCriterion("log_record <>", value, "logRecord");
            return (Criteria) this;
        }

        public Criteria andLogRecordGreaterThan(Integer value) {
            addCriterion("log_record >", value, "logRecord");
            return (Criteria) this;
        }

        public Criteria andLogRecordGreaterThanOrEqualTo(Integer value) {
            addCriterion("log_record >=", value, "logRecord");
            return (Criteria) this;
        }

        public Criteria andLogRecordLessThan(Integer value) {
            addCriterion("log_record <", value, "logRecord");
            return (Criteria) this;
        }

        public Criteria andLogRecordLessThanOrEqualTo(Integer value) {
            addCriterion("log_record <=", value, "logRecord");
            return (Criteria) this;
        }

        public Criteria andLogRecordIn(List<Integer> values) {
            addCriterion("log_record in", values, "logRecord");
            return (Criteria) this;
        }

        public Criteria andLogRecordNotIn(List<Integer> values) {
            addCriterion("log_record not in", values, "logRecord");
            return (Criteria) this;
        }

        public Criteria andLogRecordBetween(Integer value1, Integer value2) {
            addCriterion("log_record between", value1, value2, "logRecord");
            return (Criteria) this;
        }

        public Criteria andLogRecordNotBetween(Integer value1, Integer value2) {
            addCriterion("log_record not between", value1, value2, "logRecord");
            return (Criteria) this;
        }

        public Criteria andStorageIsNull() {
            addCriterion("storage is null");
            return (Criteria) this;
        }

        public Criteria andStorageIsNotNull() {
            addCriterion("storage is not null");
            return (Criteria) this;
        }

        public Criteria andStorageEqualTo(Integer value) {
            addCriterion("storage =", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageNotEqualTo(Integer value) {
            addCriterion("storage <>", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageGreaterThan(Integer value) {
            addCriterion("storage >", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageGreaterThanOrEqualTo(Integer value) {
            addCriterion("storage >=", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageLessThan(Integer value) {
            addCriterion("storage <", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageLessThanOrEqualTo(Integer value) {
            addCriterion("storage <=", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageIn(List<Integer> values) {
            addCriterion("storage in", values, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageNotIn(List<Integer> values) {
            addCriterion("storage not in", values, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageBetween(Integer value1, Integer value2) {
            addCriterion("storage between", value1, value2, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageNotBetween(Integer value1, Integer value2) {
            addCriterion("storage not between", value1, value2, "storage");
            return (Criteria) this;
        }

        public Criteria andBindingIsNull() {
            addCriterion("binding is null");
            return (Criteria) this;
        }

        public Criteria andBindingIsNotNull() {
            addCriterion("binding is not null");
            return (Criteria) this;
        }

        public Criteria andBindingEqualTo(Integer value) {
            addCriterion("binding =", value, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingNotEqualTo(Integer value) {
            addCriterion("binding <>", value, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingGreaterThan(Integer value) {
            addCriterion("binding >", value, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingGreaterThanOrEqualTo(Integer value) {
            addCriterion("binding >=", value, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingLessThan(Integer value) {
            addCriterion("binding <", value, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingLessThanOrEqualTo(Integer value) {
            addCriterion("binding <=", value, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingIn(List<Integer> values) {
            addCriterion("binding in", values, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingNotIn(List<Integer> values) {
            addCriterion("binding not in", values, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingBetween(Integer value1, Integer value2) {
            addCriterion("binding between", value1, value2, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingNotBetween(Integer value1, Integer value2) {
            addCriterion("binding not between", value1, value2, "binding");
            return (Criteria) this;
        }

        public Criteria andDealIsNull() {
            addCriterion("deal is null");
            return (Criteria) this;
        }

        public Criteria andDealIsNotNull() {
            addCriterion("deal is not null");
            return (Criteria) this;
        }

        public Criteria andDealEqualTo(Integer value) {
            addCriterion("deal =", value, "deal");
            return (Criteria) this;
        }

        public Criteria andDealNotEqualTo(Integer value) {
            addCriterion("deal <>", value, "deal");
            return (Criteria) this;
        }

        public Criteria andDealGreaterThan(Integer value) {
            addCriterion("deal >", value, "deal");
            return (Criteria) this;
        }

        public Criteria andDealGreaterThanOrEqualTo(Integer value) {
            addCriterion("deal >=", value, "deal");
            return (Criteria) this;
        }

        public Criteria andDealLessThan(Integer value) {
            addCriterion("deal <", value, "deal");
            return (Criteria) this;
        }

        public Criteria andDealLessThanOrEqualTo(Integer value) {
            addCriterion("deal <=", value, "deal");
            return (Criteria) this;
        }

        public Criteria andDealIn(List<Integer> values) {
            addCriterion("deal in", values, "deal");
            return (Criteria) this;
        }

        public Criteria andDealNotIn(List<Integer> values) {
            addCriterion("deal not in", values, "deal");
            return (Criteria) this;
        }

        public Criteria andDealBetween(Integer value1, Integer value2) {
            addCriterion("deal between", value1, value2, "deal");
            return (Criteria) this;
        }

        public Criteria andDealNotBetween(Integer value1, Integer value2) {
            addCriterion("deal not between", value1, value2, "deal");
            return (Criteria) this;
        }

        public Criteria andSellIsNull() {
            addCriterion("sell is null");
            return (Criteria) this;
        }

        public Criteria andSellIsNotNull() {
            addCriterion("sell is not null");
            return (Criteria) this;
        }

        public Criteria andSellEqualTo(Integer value) {
            addCriterion("sell =", value, "sell");
            return (Criteria) this;
        }

        public Criteria andSellNotEqualTo(Integer value) {
            addCriterion("sell <>", value, "sell");
            return (Criteria) this;
        }

        public Criteria andSellGreaterThan(Integer value) {
            addCriterion("sell >", value, "sell");
            return (Criteria) this;
        }

        public Criteria andSellGreaterThanOrEqualTo(Integer value) {
            addCriterion("sell >=", value, "sell");
            return (Criteria) this;
        }

        public Criteria andSellLessThan(Integer value) {
            addCriterion("sell <", value, "sell");
            return (Criteria) this;
        }

        public Criteria andSellLessThanOrEqualTo(Integer value) {
            addCriterion("sell <=", value, "sell");
            return (Criteria) this;
        }

        public Criteria andSellIn(List<Integer> values) {
            addCriterion("sell in", values, "sell");
            return (Criteria) this;
        }

        public Criteria andSellNotIn(List<Integer> values) {
            addCriterion("sell not in", values, "sell");
            return (Criteria) this;
        }

        public Criteria andSellBetween(Integer value1, Integer value2) {
            addCriterion("sell between", value1, value2, "sell");
            return (Criteria) this;
        }

        public Criteria andSellNotBetween(Integer value1, Integer value2) {
            addCriterion("sell not between", value1, value2, "sell");
            return (Criteria) this;
        }

        public Criteria andDestroyIsNull() {
            addCriterion("destroy is null");
            return (Criteria) this;
        }

        public Criteria andDestroyIsNotNull() {
            addCriterion("destroy is not null");
            return (Criteria) this;
        }

        public Criteria andDestroyEqualTo(Integer value) {
            addCriterion("destroy =", value, "destroy");
            return (Criteria) this;
        }

        public Criteria andDestroyNotEqualTo(Integer value) {
            addCriterion("destroy <>", value, "destroy");
            return (Criteria) this;
        }

        public Criteria andDestroyGreaterThan(Integer value) {
            addCriterion("destroy >", value, "destroy");
            return (Criteria) this;
        }

        public Criteria andDestroyGreaterThanOrEqualTo(Integer value) {
            addCriterion("destroy >=", value, "destroy");
            return (Criteria) this;
        }

        public Criteria andDestroyLessThan(Integer value) {
            addCriterion("destroy <", value, "destroy");
            return (Criteria) this;
        }

        public Criteria andDestroyLessThanOrEqualTo(Integer value) {
            addCriterion("destroy <=", value, "destroy");
            return (Criteria) this;
        }

        public Criteria andDestroyIn(List<Integer> values) {
            addCriterion("destroy in", values, "destroy");
            return (Criteria) this;
        }

        public Criteria andDestroyNotIn(List<Integer> values) {
            addCriterion("destroy not in", values, "destroy");
            return (Criteria) this;
        }

        public Criteria andDestroyBetween(Integer value1, Integer value2) {
            addCriterion("destroy between", value1, value2, "destroy");
            return (Criteria) this;
        }

        public Criteria andDestroyNotBetween(Integer value1, Integer value2) {
            addCriterion("destroy not between", value1, value2, "destroy");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Integer value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Integer value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Integer value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Integer value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Integer value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Integer> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Integer> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Integer value1, Integer value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("time not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andProfessionIsNull() {
            addCriterion("profession is null");
            return (Criteria) this;
        }

        public Criteria andProfessionIsNotNull() {
            addCriterion("profession is not null");
            return (Criteria) this;
        }

        public Criteria andProfessionEqualTo(String value) {
            addCriterion("profession =", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionNotEqualTo(String value) {
            addCriterion("profession <>", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionGreaterThan(String value) {
            addCriterion("profession >", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionGreaterThanOrEqualTo(String value) {
            addCriterion("profession >=", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionLessThan(String value) {
            addCriterion("profession <", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionLessThanOrEqualTo(String value) {
            addCriterion("profession <=", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionLike(String value) {
            addCriterion("profession like", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionNotLike(String value) {
            addCriterion("profession not like", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionIn(List<String> values) {
            addCriterion("profession in", values, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionNotIn(List<String> values) {
            addCriterion("profession not in", values, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionBetween(String value1, String value2) {
            addCriterion("profession between", value1, value2, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionNotBetween(String value1, String value2) {
            addCriterion("profession not between", value1, value2, "profession");
            return (Criteria) this;
        }

        public Criteria andUseIsNull() {
            addCriterion("use_ is null");
            return (Criteria) this;
        }

        public Criteria andUseIsNotNull() {
            addCriterion("use_ is not null");
            return (Criteria) this;
        }

        public Criteria andUseEqualTo(Integer value) {
            addCriterion("use_ =", value, "use");
            return (Criteria) this;
        }

        public Criteria andUseNotEqualTo(Integer value) {
            addCriterion("use_ <>", value, "use");
            return (Criteria) this;
        }

        public Criteria andUseGreaterThan(Integer value) {
            addCriterion("use_ >", value, "use");
            return (Criteria) this;
        }

        public Criteria andUseGreaterThanOrEqualTo(Integer value) {
            addCriterion("use_ >=", value, "use");
            return (Criteria) this;
        }

        public Criteria andUseLessThan(Integer value) {
            addCriterion("use_ <", value, "use");
            return (Criteria) this;
        }

        public Criteria andUseLessThanOrEqualTo(Integer value) {
            addCriterion("use_ <=", value, "use");
            return (Criteria) this;
        }

        public Criteria andUseIn(List<Integer> values) {
            addCriterion("use_ in", values, "use");
            return (Criteria) this;
        }

        public Criteria andUseNotIn(List<Integer> values) {
            addCriterion("use_ not in", values, "use");
            return (Criteria) this;
        }

        public Criteria andUseBetween(Integer value1, Integer value2) {
            addCriterion("use_ between", value1, value2, "use");
            return (Criteria) this;
        }

        public Criteria andUseNotBetween(Integer value1, Integer value2) {
            addCriterion("use_ not between", value1, value2, "use");
            return (Criteria) this;
        }

        public Criteria andVitalityIsNull() {
            addCriterion("vitality is null");
            return (Criteria) this;
        }

        public Criteria andVitalityIsNotNull() {
            addCriterion("vitality is not null");
            return (Criteria) this;
        }

        public Criteria andVitalityEqualTo(Double value) {
            addCriterion("vitality =", value, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityNotEqualTo(Double value) {
            addCriterion("vitality <>", value, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityGreaterThan(Double value) {
            addCriterion("vitality >", value, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityGreaterThanOrEqualTo(Double value) {
            addCriterion("vitality >=", value, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityLessThan(Double value) {
            addCriterion("vitality <", value, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityLessThanOrEqualTo(Double value) {
            addCriterion("vitality <=", value, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityIn(List<Double> values) {
            addCriterion("vitality in", values, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityNotIn(List<Double> values) {
            addCriterion("vitality not in", values, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityBetween(Double value1, Double value2) {
            addCriterion("vitality between", value1, value2, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityNotBetween(Double value1, Double value2) {
            addCriterion("vitality not between", value1, value2, "vitality");
            return (Criteria) this;
        }

        public Criteria andAttackIsNull() {
            addCriterion("attack is null");
            return (Criteria) this;
        }

        public Criteria andAttackIsNotNull() {
            addCriterion("attack is not null");
            return (Criteria) this;
        }

        public Criteria andAttackEqualTo(Double value) {
            addCriterion("attack =", value, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackNotEqualTo(Double value) {
            addCriterion("attack <>", value, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackGreaterThan(Double value) {
            addCriterion("attack >", value, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackGreaterThanOrEqualTo(Double value) {
            addCriterion("attack >=", value, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackLessThan(Double value) {
            addCriterion("attack <", value, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackLessThanOrEqualTo(Double value) {
            addCriterion("attack <=", value, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackIn(List<Double> values) {
            addCriterion("attack in", values, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackNotIn(List<Double> values) {
            addCriterion("attack not in", values, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackBetween(Double value1, Double value2) {
            addCriterion("attack between", value1, value2, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackNotBetween(Double value1, Double value2) {
            addCriterion("attack not between", value1, value2, "attack");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksIsNull() {
            addCriterion("spell_attacks is null");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksIsNotNull() {
            addCriterion("spell_attacks is not null");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksEqualTo(Double value) {
            addCriterion("spell_attacks =", value, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksNotEqualTo(Double value) {
            addCriterion("spell_attacks <>", value, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksGreaterThan(Double value) {
            addCriterion("spell_attacks >", value, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksGreaterThanOrEqualTo(Double value) {
            addCriterion("spell_attacks >=", value, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksLessThan(Double value) {
            addCriterion("spell_attacks <", value, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksLessThanOrEqualTo(Double value) {
            addCriterion("spell_attacks <=", value, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksIn(List<Double> values) {
            addCriterion("spell_attacks in", values, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksNotIn(List<Double> values) {
            addCriterion("spell_attacks not in", values, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksBetween(Double value1, Double value2) {
            addCriterion("spell_attacks between", value1, value2, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksNotBetween(Double value1, Double value2) {
            addCriterion("spell_attacks not between", value1, value2, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andPdefIsNull() {
            addCriterion("pdef is null");
            return (Criteria) this;
        }

        public Criteria andPdefIsNotNull() {
            addCriterion("pdef is not null");
            return (Criteria) this;
        }

        public Criteria andPdefEqualTo(Double value) {
            addCriterion("pdef =", value, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefNotEqualTo(Double value) {
            addCriterion("pdef <>", value, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefGreaterThan(Double value) {
            addCriterion("pdef >", value, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefGreaterThanOrEqualTo(Double value) {
            addCriterion("pdef >=", value, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefLessThan(Double value) {
            addCriterion("pdef <", value, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefLessThanOrEqualTo(Double value) {
            addCriterion("pdef <=", value, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefIn(List<Double> values) {
            addCriterion("pdef in", values, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefNotIn(List<Double> values) {
            addCriterion("pdef not in", values, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefBetween(Double value1, Double value2) {
            addCriterion("pdef between", value1, value2, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefNotBetween(Double value1, Double value2) {
            addCriterion("pdef not between", value1, value2, "pdef");
            return (Criteria) this;
        }

        public Criteria andMagdefIsNull() {
            addCriterion("magdef is null");
            return (Criteria) this;
        }

        public Criteria andMagdefIsNotNull() {
            addCriterion("magdef is not null");
            return (Criteria) this;
        }

        public Criteria andMagdefEqualTo(Double value) {
            addCriterion("magdef =", value, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefNotEqualTo(Double value) {
            addCriterion("magdef <>", value, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefGreaterThan(Double value) {
            addCriterion("magdef >", value, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefGreaterThanOrEqualTo(Double value) {
            addCriterion("magdef >=", value, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefLessThan(Double value) {
            addCriterion("magdef <", value, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefLessThanOrEqualTo(Double value) {
            addCriterion("magdef <=", value, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefIn(List<Double> values) {
            addCriterion("magdef in", values, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefNotIn(List<Double> values) {
            addCriterion("magdef not in", values, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefBetween(Double value1, Double value2) {
            addCriterion("magdef between", value1, value2, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefNotBetween(Double value1, Double value2) {
            addCriterion("magdef not between", value1, value2, "magdef");
            return (Criteria) this;
        }

        public Criteria andCritIsNull() {
            addCriterion("crit is null");
            return (Criteria) this;
        }

        public Criteria andCritIsNotNull() {
            addCriterion("crit is not null");
            return (Criteria) this;
        }

        public Criteria andCritEqualTo(Double value) {
            addCriterion("crit =", value, "crit");
            return (Criteria) this;
        }

        public Criteria andCritNotEqualTo(Double value) {
            addCriterion("crit <>", value, "crit");
            return (Criteria) this;
        }

        public Criteria andCritGreaterThan(Double value) {
            addCriterion("crit >", value, "crit");
            return (Criteria) this;
        }

        public Criteria andCritGreaterThanOrEqualTo(Double value) {
            addCriterion("crit >=", value, "crit");
            return (Criteria) this;
        }

        public Criteria andCritLessThan(Double value) {
            addCriterion("crit <", value, "crit");
            return (Criteria) this;
        }

        public Criteria andCritLessThanOrEqualTo(Double value) {
            addCriterion("crit <=", value, "crit");
            return (Criteria) this;
        }

        public Criteria andCritIn(List<Double> values) {
            addCriterion("crit in", values, "crit");
            return (Criteria) this;
        }

        public Criteria andCritNotIn(List<Double> values) {
            addCriterion("crit not in", values, "crit");
            return (Criteria) this;
        }

        public Criteria andCritBetween(Double value1, Double value2) {
            addCriterion("crit between", value1, value2, "crit");
            return (Criteria) this;
        }

        public Criteria andCritNotBetween(Double value1, Double value2) {
            addCriterion("crit not between", value1, value2, "crit");
            return (Criteria) this;
        }

        public Criteria andDodgeIsNull() {
            addCriterion("dodge is null");
            return (Criteria) this;
        }

        public Criteria andDodgeIsNotNull() {
            addCriterion("dodge is not null");
            return (Criteria) this;
        }

        public Criteria andDodgeEqualTo(Double value) {
            addCriterion("dodge =", value, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeNotEqualTo(Double value) {
            addCriterion("dodge <>", value, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeGreaterThan(Double value) {
            addCriterion("dodge >", value, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeGreaterThanOrEqualTo(Double value) {
            addCriterion("dodge >=", value, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeLessThan(Double value) {
            addCriterion("dodge <", value, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeLessThanOrEqualTo(Double value) {
            addCriterion("dodge <=", value, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeIn(List<Double> values) {
            addCriterion("dodge in", values, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeNotIn(List<Double> values) {
            addCriterion("dodge not in", values, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeBetween(Double value1, Double value2) {
            addCriterion("dodge between", value1, value2, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeNotBetween(Double value1, Double value2) {
            addCriterion("dodge not between", value1, value2, "dodge");
            return (Criteria) this;
        }

        public Criteria andHitRateIsNull() {
            addCriterion("hit_rate is null");
            return (Criteria) this;
        }

        public Criteria andHitRateIsNotNull() {
            addCriterion("hit_rate is not null");
            return (Criteria) this;
        }

        public Criteria andHitRateEqualTo(Double value) {
            addCriterion("hit_rate =", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateNotEqualTo(Double value) {
            addCriterion("hit_rate <>", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateGreaterThan(Double value) {
            addCriterion("hit_rate >", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateGreaterThanOrEqualTo(Double value) {
            addCriterion("hit_rate >=", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateLessThan(Double value) {
            addCriterion("hit_rate <", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateLessThanOrEqualTo(Double value) {
            addCriterion("hit_rate <=", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateIn(List<Double> values) {
            addCriterion("hit_rate in", values, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateNotIn(List<Double> values) {
            addCriterion("hit_rate not in", values, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateBetween(Double value1, Double value2) {
            addCriterion("hit_rate between", value1, value2, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateNotBetween(Double value1, Double value2) {
            addCriterion("hit_rate not between", value1, value2, "hitRate");
            return (Criteria) this;
        }

        public Criteria andDefenseCritIsNull() {
            addCriterion("defense_crit is null");
            return (Criteria) this;
        }

        public Criteria andDefenseCritIsNotNull() {
            addCriterion("defense_crit is not null");
            return (Criteria) this;
        }

        public Criteria andDefenseCritEqualTo(Double value) {
            addCriterion("defense_crit =", value, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritNotEqualTo(Double value) {
            addCriterion("defense_crit <>", value, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritGreaterThan(Double value) {
            addCriterion("defense_crit >", value, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritGreaterThanOrEqualTo(Double value) {
            addCriterion("defense_crit >=", value, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritLessThan(Double value) {
            addCriterion("defense_crit <", value, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritLessThanOrEqualTo(Double value) {
            addCriterion("defense_crit <=", value, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritIn(List<Double> values) {
            addCriterion("defense_crit in", values, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritNotIn(List<Double> values) {
            addCriterion("defense_crit not in", values, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritBetween(Double value1, Double value2) {
            addCriterion("defense_crit between", value1, value2, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritNotBetween(Double value1, Double value2) {
            addCriterion("defense_crit not between", value1, value2, "defenseCrit");
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