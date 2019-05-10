package com.tongzhu.usergoods.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EquipmentInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EquipmentInfoExample() {
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

        public Criteria andIntensifyIdIsNull() {
            addCriterion("intensify_id is null");
            return (Criteria) this;
        }

        public Criteria andIntensifyIdIsNotNull() {
            addCriterion("intensify_id is not null");
            return (Criteria) this;
        }

        public Criteria andIntensifyIdEqualTo(String value) {
            addCriterion("intensify_id =", value, "intensifyId");
            return (Criteria) this;
        }

        public Criteria andIntensifyIdNotEqualTo(String value) {
            addCriterion("intensify_id <>", value, "intensifyId");
            return (Criteria) this;
        }

        public Criteria andIntensifyIdGreaterThan(String value) {
            addCriterion("intensify_id >", value, "intensifyId");
            return (Criteria) this;
        }

        public Criteria andIntensifyIdGreaterThanOrEqualTo(String value) {
            addCriterion("intensify_id >=", value, "intensifyId");
            return (Criteria) this;
        }

        public Criteria andIntensifyIdLessThan(String value) {
            addCriterion("intensify_id <", value, "intensifyId");
            return (Criteria) this;
        }

        public Criteria andIntensifyIdLessThanOrEqualTo(String value) {
            addCriterion("intensify_id <=", value, "intensifyId");
            return (Criteria) this;
        }

        public Criteria andIntensifyIdLike(String value) {
            addCriterion("intensify_id like", value, "intensifyId");
            return (Criteria) this;
        }

        public Criteria andIntensifyIdNotLike(String value) {
            addCriterion("intensify_id not like", value, "intensifyId");
            return (Criteria) this;
        }

        public Criteria andIntensifyIdIn(List<String> values) {
            addCriterion("intensify_id in", values, "intensifyId");
            return (Criteria) this;
        }

        public Criteria andIntensifyIdNotIn(List<String> values) {
            addCriterion("intensify_id not in", values, "intensifyId");
            return (Criteria) this;
        }

        public Criteria andIntensifyIdBetween(String value1, String value2) {
            addCriterion("intensify_id between", value1, value2, "intensifyId");
            return (Criteria) this;
        }

        public Criteria andIntensifyIdNotBetween(String value1, String value2) {
            addCriterion("intensify_id not between", value1, value2, "intensifyId");
            return (Criteria) this;
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

        public Criteria andSuitIdIsNull() {
            addCriterion("suit_id is null");
            return (Criteria) this;
        }

        public Criteria andSuitIdIsNotNull() {
            addCriterion("suit_id is not null");
            return (Criteria) this;
        }

        public Criteria andSuitIdEqualTo(Integer value) {
            addCriterion("suit_id =", value, "suitId");
            return (Criteria) this;
        }

        public Criteria andSuitIdNotEqualTo(Integer value) {
            addCriterion("suit_id <>", value, "suitId");
            return (Criteria) this;
        }

        public Criteria andSuitIdGreaterThan(Integer value) {
            addCriterion("suit_id >", value, "suitId");
            return (Criteria) this;
        }

        public Criteria andSuitIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("suit_id >=", value, "suitId");
            return (Criteria) this;
        }

        public Criteria andSuitIdLessThan(Integer value) {
            addCriterion("suit_id <", value, "suitId");
            return (Criteria) this;
        }

        public Criteria andSuitIdLessThanOrEqualTo(Integer value) {
            addCriterion("suit_id <=", value, "suitId");
            return (Criteria) this;
        }

        public Criteria andSuitIdIn(List<Integer> values) {
            addCriterion("suit_id in", values, "suitId");
            return (Criteria) this;
        }

        public Criteria andSuitIdNotIn(List<Integer> values) {
            addCriterion("suit_id not in", values, "suitId");
            return (Criteria) this;
        }

        public Criteria andSuitIdBetween(Integer value1, Integer value2) {
            addCriterion("suit_id between", value1, value2, "suitId");
            return (Criteria) this;
        }

        public Criteria andSuitIdNotBetween(Integer value1, Integer value2) {
            addCriterion("suit_id not between", value1, value2, "suitId");
            return (Criteria) this;
        }

        public Criteria andSuitNameIsNull() {
            addCriterion("suit_name is null");
            return (Criteria) this;
        }

        public Criteria andSuitNameIsNotNull() {
            addCriterion("suit_name is not null");
            return (Criteria) this;
        }

        public Criteria andSuitNameEqualTo(String value) {
            addCriterion("suit_name =", value, "suitName");
            return (Criteria) this;
        }

        public Criteria andSuitNameNotEqualTo(String value) {
            addCriterion("suit_name <>", value, "suitName");
            return (Criteria) this;
        }

        public Criteria andSuitNameGreaterThan(String value) {
            addCriterion("suit_name >", value, "suitName");
            return (Criteria) this;
        }

        public Criteria andSuitNameGreaterThanOrEqualTo(String value) {
            addCriterion("suit_name >=", value, "suitName");
            return (Criteria) this;
        }

        public Criteria andSuitNameLessThan(String value) {
            addCriterion("suit_name <", value, "suitName");
            return (Criteria) this;
        }

        public Criteria andSuitNameLessThanOrEqualTo(String value) {
            addCriterion("suit_name <=", value, "suitName");
            return (Criteria) this;
        }

        public Criteria andSuitNameLike(String value) {
            addCriterion("suit_name like", value, "suitName");
            return (Criteria) this;
        }

        public Criteria andSuitNameNotLike(String value) {
            addCriterion("suit_name not like", value, "suitName");
            return (Criteria) this;
        }

        public Criteria andSuitNameIn(List<String> values) {
            addCriterion("suit_name in", values, "suitName");
            return (Criteria) this;
        }

        public Criteria andSuitNameNotIn(List<String> values) {
            addCriterion("suit_name not in", values, "suitName");
            return (Criteria) this;
        }

        public Criteria andSuitNameBetween(String value1, String value2) {
            addCriterion("suit_name between", value1, value2, "suitName");
            return (Criteria) this;
        }

        public Criteria andSuitNameNotBetween(String value1, String value2) {
            addCriterion("suit_name not between", value1, value2, "suitName");
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

        public Criteria andInlayIsNull() {
            addCriterion("inlay is null");
            return (Criteria) this;
        }

        public Criteria andInlayIsNotNull() {
            addCriterion("inlay is not null");
            return (Criteria) this;
        }

        public Criteria andInlayEqualTo(Integer value) {
            addCriterion("inlay =", value, "inlay");
            return (Criteria) this;
        }

        public Criteria andInlayNotEqualTo(Integer value) {
            addCriterion("inlay <>", value, "inlay");
            return (Criteria) this;
        }

        public Criteria andInlayGreaterThan(Integer value) {
            addCriterion("inlay >", value, "inlay");
            return (Criteria) this;
        }

        public Criteria andInlayGreaterThanOrEqualTo(Integer value) {
            addCriterion("inlay >=", value, "inlay");
            return (Criteria) this;
        }

        public Criteria andInlayLessThan(Integer value) {
            addCriterion("inlay <", value, "inlay");
            return (Criteria) this;
        }

        public Criteria andInlayLessThanOrEqualTo(Integer value) {
            addCriterion("inlay <=", value, "inlay");
            return (Criteria) this;
        }

        public Criteria andInlayIn(List<Integer> values) {
            addCriterion("inlay in", values, "inlay");
            return (Criteria) this;
        }

        public Criteria andInlayNotIn(List<Integer> values) {
            addCriterion("inlay not in", values, "inlay");
            return (Criteria) this;
        }

        public Criteria andInlayBetween(Integer value1, Integer value2) {
            addCriterion("inlay between", value1, value2, "inlay");
            return (Criteria) this;
        }

        public Criteria andInlayNotBetween(Integer value1, Integer value2) {
            addCriterion("inlay not between", value1, value2, "inlay");
            return (Criteria) this;
        }

        public Criteria andWearPositionIsNull() {
            addCriterion("wear_position is null");
            return (Criteria) this;
        }

        public Criteria andWearPositionIsNotNull() {
            addCriterion("wear_position is not null");
            return (Criteria) this;
        }

        public Criteria andWearPositionEqualTo(Integer value) {
            addCriterion("wear_position =", value, "wearPosition");
            return (Criteria) this;
        }

        public Criteria andWearPositionNotEqualTo(Integer value) {
            addCriterion("wear_position <>", value, "wearPosition");
            return (Criteria) this;
        }

        public Criteria andWearPositionGreaterThan(Integer value) {
            addCriterion("wear_position >", value, "wearPosition");
            return (Criteria) this;
        }

        public Criteria andWearPositionGreaterThanOrEqualTo(Integer value) {
            addCriterion("wear_position >=", value, "wearPosition");
            return (Criteria) this;
        }

        public Criteria andWearPositionLessThan(Integer value) {
            addCriterion("wear_position <", value, "wearPosition");
            return (Criteria) this;
        }

        public Criteria andWearPositionLessThanOrEqualTo(Integer value) {
            addCriterion("wear_position <=", value, "wearPosition");
            return (Criteria) this;
        }

        public Criteria andWearPositionIn(List<Integer> values) {
            addCriterion("wear_position in", values, "wearPosition");
            return (Criteria) this;
        }

        public Criteria andWearPositionNotIn(List<Integer> values) {
            addCriterion("wear_position not in", values, "wearPosition");
            return (Criteria) this;
        }

        public Criteria andWearPositionBetween(Integer value1, Integer value2) {
            addCriterion("wear_position between", value1, value2, "wearPosition");
            return (Criteria) this;
        }

        public Criteria andWearPositionNotBetween(Integer value1, Integer value2) {
            addCriterion("wear_position not between", value1, value2, "wearPosition");
            return (Criteria) this;
        }

        public Criteria andOriginalIsNull() {
            addCriterion("original is null");
            return (Criteria) this;
        }

        public Criteria andOriginalIsNotNull() {
            addCriterion("original is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalEqualTo(Integer value) {
            addCriterion("original =", value, "original");
            return (Criteria) this;
        }

        public Criteria andOriginalNotEqualTo(Integer value) {
            addCriterion("original <>", value, "original");
            return (Criteria) this;
        }

        public Criteria andOriginalGreaterThan(Integer value) {
            addCriterion("original >", value, "original");
            return (Criteria) this;
        }

        public Criteria andOriginalGreaterThanOrEqualTo(Integer value) {
            addCriterion("original >=", value, "original");
            return (Criteria) this;
        }

        public Criteria andOriginalLessThan(Integer value) {
            addCriterion("original <", value, "original");
            return (Criteria) this;
        }

        public Criteria andOriginalLessThanOrEqualTo(Integer value) {
            addCriterion("original <=", value, "original");
            return (Criteria) this;
        }

        public Criteria andOriginalIn(List<Integer> values) {
            addCriterion("original in", values, "original");
            return (Criteria) this;
        }

        public Criteria andOriginalNotIn(List<Integer> values) {
            addCriterion("original not in", values, "original");
            return (Criteria) this;
        }

        public Criteria andOriginalBetween(Integer value1, Integer value2) {
            addCriterion("original between", value1, value2, "original");
            return (Criteria) this;
        }

        public Criteria andOriginalNotBetween(Integer value1, Integer value2) {
            addCriterion("original not between", value1, value2, "original");
            return (Criteria) this;
        }

        public Criteria andGuardiansMaleIsNull() {
            addCriterion("guardians_male is null");
            return (Criteria) this;
        }

        public Criteria andGuardiansMaleIsNotNull() {
            addCriterion("guardians_male is not null");
            return (Criteria) this;
        }

        public Criteria andGuardiansMaleEqualTo(String value) {
            addCriterion("guardians_male =", value, "guardiansMale");
            return (Criteria) this;
        }

        public Criteria andGuardiansMaleNotEqualTo(String value) {
            addCriterion("guardians_male <>", value, "guardiansMale");
            return (Criteria) this;
        }

        public Criteria andGuardiansMaleGreaterThan(String value) {
            addCriterion("guardians_male >", value, "guardiansMale");
            return (Criteria) this;
        }

        public Criteria andGuardiansMaleGreaterThanOrEqualTo(String value) {
            addCriterion("guardians_male >=", value, "guardiansMale");
            return (Criteria) this;
        }

        public Criteria andGuardiansMaleLessThan(String value) {
            addCriterion("guardians_male <", value, "guardiansMale");
            return (Criteria) this;
        }

        public Criteria andGuardiansMaleLessThanOrEqualTo(String value) {
            addCriterion("guardians_male <=", value, "guardiansMale");
            return (Criteria) this;
        }

        public Criteria andGuardiansMaleLike(String value) {
            addCriterion("guardians_male like", value, "guardiansMale");
            return (Criteria) this;
        }

        public Criteria andGuardiansMaleNotLike(String value) {
            addCriterion("guardians_male not like", value, "guardiansMale");
            return (Criteria) this;
        }

        public Criteria andGuardiansMaleIn(List<String> values) {
            addCriterion("guardians_male in", values, "guardiansMale");
            return (Criteria) this;
        }

        public Criteria andGuardiansMaleNotIn(List<String> values) {
            addCriterion("guardians_male not in", values, "guardiansMale");
            return (Criteria) this;
        }

        public Criteria andGuardiansMaleBetween(String value1, String value2) {
            addCriterion("guardians_male between", value1, value2, "guardiansMale");
            return (Criteria) this;
        }

        public Criteria andGuardiansMaleNotBetween(String value1, String value2) {
            addCriterion("guardians_male not between", value1, value2, "guardiansMale");
            return (Criteria) this;
        }

        public Criteria andGuardiansFemaleIsNull() {
            addCriterion("guardians_female is null");
            return (Criteria) this;
        }

        public Criteria andGuardiansFemaleIsNotNull() {
            addCriterion("guardians_female is not null");
            return (Criteria) this;
        }

        public Criteria andGuardiansFemaleEqualTo(String value) {
            addCriterion("guardians_female =", value, "guardiansFemale");
            return (Criteria) this;
        }

        public Criteria andGuardiansFemaleNotEqualTo(String value) {
            addCriterion("guardians_female <>", value, "guardiansFemale");
            return (Criteria) this;
        }

        public Criteria andGuardiansFemaleGreaterThan(String value) {
            addCriterion("guardians_female >", value, "guardiansFemale");
            return (Criteria) this;
        }

        public Criteria andGuardiansFemaleGreaterThanOrEqualTo(String value) {
            addCriterion("guardians_female >=", value, "guardiansFemale");
            return (Criteria) this;
        }

        public Criteria andGuardiansFemaleLessThan(String value) {
            addCriterion("guardians_female <", value, "guardiansFemale");
            return (Criteria) this;
        }

        public Criteria andGuardiansFemaleLessThanOrEqualTo(String value) {
            addCriterion("guardians_female <=", value, "guardiansFemale");
            return (Criteria) this;
        }

        public Criteria andGuardiansFemaleLike(String value) {
            addCriterion("guardians_female like", value, "guardiansFemale");
            return (Criteria) this;
        }

        public Criteria andGuardiansFemaleNotLike(String value) {
            addCriterion("guardians_female not like", value, "guardiansFemale");
            return (Criteria) this;
        }

        public Criteria andGuardiansFemaleIn(List<String> values) {
            addCriterion("guardians_female in", values, "guardiansFemale");
            return (Criteria) this;
        }

        public Criteria andGuardiansFemaleNotIn(List<String> values) {
            addCriterion("guardians_female not in", values, "guardiansFemale");
            return (Criteria) this;
        }

        public Criteria andGuardiansFemaleBetween(String value1, String value2) {
            addCriterion("guardians_female between", value1, value2, "guardiansFemale");
            return (Criteria) this;
        }

        public Criteria andGuardiansFemaleNotBetween(String value1, String value2) {
            addCriterion("guardians_female not between", value1, value2, "guardiansFemale");
            return (Criteria) this;
        }

        public Criteria andAssassinMaleIsNull() {
            addCriterion("assassin_male is null");
            return (Criteria) this;
        }

        public Criteria andAssassinMaleIsNotNull() {
            addCriterion("assassin_male is not null");
            return (Criteria) this;
        }

        public Criteria andAssassinMaleEqualTo(String value) {
            addCriterion("assassin_male =", value, "assassinMale");
            return (Criteria) this;
        }

        public Criteria andAssassinMaleNotEqualTo(String value) {
            addCriterion("assassin_male <>", value, "assassinMale");
            return (Criteria) this;
        }

        public Criteria andAssassinMaleGreaterThan(String value) {
            addCriterion("assassin_male >", value, "assassinMale");
            return (Criteria) this;
        }

        public Criteria andAssassinMaleGreaterThanOrEqualTo(String value) {
            addCriterion("assassin_male >=", value, "assassinMale");
            return (Criteria) this;
        }

        public Criteria andAssassinMaleLessThan(String value) {
            addCriterion("assassin_male <", value, "assassinMale");
            return (Criteria) this;
        }

        public Criteria andAssassinMaleLessThanOrEqualTo(String value) {
            addCriterion("assassin_male <=", value, "assassinMale");
            return (Criteria) this;
        }

        public Criteria andAssassinMaleLike(String value) {
            addCriterion("assassin_male like", value, "assassinMale");
            return (Criteria) this;
        }

        public Criteria andAssassinMaleNotLike(String value) {
            addCriterion("assassin_male not like", value, "assassinMale");
            return (Criteria) this;
        }

        public Criteria andAssassinMaleIn(List<String> values) {
            addCriterion("assassin_male in", values, "assassinMale");
            return (Criteria) this;
        }

        public Criteria andAssassinMaleNotIn(List<String> values) {
            addCriterion("assassin_male not in", values, "assassinMale");
            return (Criteria) this;
        }

        public Criteria andAssassinMaleBetween(String value1, String value2) {
            addCriterion("assassin_male between", value1, value2, "assassinMale");
            return (Criteria) this;
        }

        public Criteria andAssassinMaleNotBetween(String value1, String value2) {
            addCriterion("assassin_male not between", value1, value2, "assassinMale");
            return (Criteria) this;
        }

        public Criteria andAssassinFemaleIsNull() {
            addCriterion("assassin_female is null");
            return (Criteria) this;
        }

        public Criteria andAssassinFemaleIsNotNull() {
            addCriterion("assassin_female is not null");
            return (Criteria) this;
        }

        public Criteria andAssassinFemaleEqualTo(String value) {
            addCriterion("assassin_female =", value, "assassinFemale");
            return (Criteria) this;
        }

        public Criteria andAssassinFemaleNotEqualTo(String value) {
            addCriterion("assassin_female <>", value, "assassinFemale");
            return (Criteria) this;
        }

        public Criteria andAssassinFemaleGreaterThan(String value) {
            addCriterion("assassin_female >", value, "assassinFemale");
            return (Criteria) this;
        }

        public Criteria andAssassinFemaleGreaterThanOrEqualTo(String value) {
            addCriterion("assassin_female >=", value, "assassinFemale");
            return (Criteria) this;
        }

        public Criteria andAssassinFemaleLessThan(String value) {
            addCriterion("assassin_female <", value, "assassinFemale");
            return (Criteria) this;
        }

        public Criteria andAssassinFemaleLessThanOrEqualTo(String value) {
            addCriterion("assassin_female <=", value, "assassinFemale");
            return (Criteria) this;
        }

        public Criteria andAssassinFemaleLike(String value) {
            addCriterion("assassin_female like", value, "assassinFemale");
            return (Criteria) this;
        }

        public Criteria andAssassinFemaleNotLike(String value) {
            addCriterion("assassin_female not like", value, "assassinFemale");
            return (Criteria) this;
        }

        public Criteria andAssassinFemaleIn(List<String> values) {
            addCriterion("assassin_female in", values, "assassinFemale");
            return (Criteria) this;
        }

        public Criteria andAssassinFemaleNotIn(List<String> values) {
            addCriterion("assassin_female not in", values, "assassinFemale");
            return (Criteria) this;
        }

        public Criteria andAssassinFemaleBetween(String value1, String value2) {
            addCriterion("assassin_female between", value1, value2, "assassinFemale");
            return (Criteria) this;
        }

        public Criteria andAssassinFemaleNotBetween(String value1, String value2) {
            addCriterion("assassin_female not between", value1, value2, "assassinFemale");
            return (Criteria) this;
        }

        public Criteria andCraftsMaleIsNull() {
            addCriterion("crafts_male is null");
            return (Criteria) this;
        }

        public Criteria andCraftsMaleIsNotNull() {
            addCriterion("crafts_male is not null");
            return (Criteria) this;
        }

        public Criteria andCraftsMaleEqualTo(String value) {
            addCriterion("crafts_male =", value, "craftsMale");
            return (Criteria) this;
        }

        public Criteria andCraftsMaleNotEqualTo(String value) {
            addCriterion("crafts_male <>", value, "craftsMale");
            return (Criteria) this;
        }

        public Criteria andCraftsMaleGreaterThan(String value) {
            addCriterion("crafts_male >", value, "craftsMale");
            return (Criteria) this;
        }

        public Criteria andCraftsMaleGreaterThanOrEqualTo(String value) {
            addCriterion("crafts_male >=", value, "craftsMale");
            return (Criteria) this;
        }

        public Criteria andCraftsMaleLessThan(String value) {
            addCriterion("crafts_male <", value, "craftsMale");
            return (Criteria) this;
        }

        public Criteria andCraftsMaleLessThanOrEqualTo(String value) {
            addCriterion("crafts_male <=", value, "craftsMale");
            return (Criteria) this;
        }

        public Criteria andCraftsMaleLike(String value) {
            addCriterion("crafts_male like", value, "craftsMale");
            return (Criteria) this;
        }

        public Criteria andCraftsMaleNotLike(String value) {
            addCriterion("crafts_male not like", value, "craftsMale");
            return (Criteria) this;
        }

        public Criteria andCraftsMaleIn(List<String> values) {
            addCriterion("crafts_male in", values, "craftsMale");
            return (Criteria) this;
        }

        public Criteria andCraftsMaleNotIn(List<String> values) {
            addCriterion("crafts_male not in", values, "craftsMale");
            return (Criteria) this;
        }

        public Criteria andCraftsMaleBetween(String value1, String value2) {
            addCriterion("crafts_male between", value1, value2, "craftsMale");
            return (Criteria) this;
        }

        public Criteria andCraftsMaleNotBetween(String value1, String value2) {
            addCriterion("crafts_male not between", value1, value2, "craftsMale");
            return (Criteria) this;
        }

        public Criteria andCraftsFemaleIsNull() {
            addCriterion("crafts_female is null");
            return (Criteria) this;
        }

        public Criteria andCraftsFemaleIsNotNull() {
            addCriterion("crafts_female is not null");
            return (Criteria) this;
        }

        public Criteria andCraftsFemaleEqualTo(String value) {
            addCriterion("crafts_female =", value, "craftsFemale");
            return (Criteria) this;
        }

        public Criteria andCraftsFemaleNotEqualTo(String value) {
            addCriterion("crafts_female <>", value, "craftsFemale");
            return (Criteria) this;
        }

        public Criteria andCraftsFemaleGreaterThan(String value) {
            addCriterion("crafts_female >", value, "craftsFemale");
            return (Criteria) this;
        }

        public Criteria andCraftsFemaleGreaterThanOrEqualTo(String value) {
            addCriterion("crafts_female >=", value, "craftsFemale");
            return (Criteria) this;
        }

        public Criteria andCraftsFemaleLessThan(String value) {
            addCriterion("crafts_female <", value, "craftsFemale");
            return (Criteria) this;
        }

        public Criteria andCraftsFemaleLessThanOrEqualTo(String value) {
            addCriterion("crafts_female <=", value, "craftsFemale");
            return (Criteria) this;
        }

        public Criteria andCraftsFemaleLike(String value) {
            addCriterion("crafts_female like", value, "craftsFemale");
            return (Criteria) this;
        }

        public Criteria andCraftsFemaleNotLike(String value) {
            addCriterion("crafts_female not like", value, "craftsFemale");
            return (Criteria) this;
        }

        public Criteria andCraftsFemaleIn(List<String> values) {
            addCriterion("crafts_female in", values, "craftsFemale");
            return (Criteria) this;
        }

        public Criteria andCraftsFemaleNotIn(List<String> values) {
            addCriterion("crafts_female not in", values, "craftsFemale");
            return (Criteria) this;
        }

        public Criteria andCraftsFemaleBetween(String value1, String value2) {
            addCriterion("crafts_female between", value1, value2, "craftsFemale");
            return (Criteria) this;
        }

        public Criteria andCraftsFemaleNotBetween(String value1, String value2) {
            addCriterion("crafts_female not between", value1, value2, "craftsFemale");
            return (Criteria) this;
        }

        public Criteria andMasterMaleIsNull() {
            addCriterion("master_male is null");
            return (Criteria) this;
        }

        public Criteria andMasterMaleIsNotNull() {
            addCriterion("master_male is not null");
            return (Criteria) this;
        }

        public Criteria andMasterMaleEqualTo(String value) {
            addCriterion("master_male =", value, "masterMale");
            return (Criteria) this;
        }

        public Criteria andMasterMaleNotEqualTo(String value) {
            addCriterion("master_male <>", value, "masterMale");
            return (Criteria) this;
        }

        public Criteria andMasterMaleGreaterThan(String value) {
            addCriterion("master_male >", value, "masterMale");
            return (Criteria) this;
        }

        public Criteria andMasterMaleGreaterThanOrEqualTo(String value) {
            addCriterion("master_male >=", value, "masterMale");
            return (Criteria) this;
        }

        public Criteria andMasterMaleLessThan(String value) {
            addCriterion("master_male <", value, "masterMale");
            return (Criteria) this;
        }

        public Criteria andMasterMaleLessThanOrEqualTo(String value) {
            addCriterion("master_male <=", value, "masterMale");
            return (Criteria) this;
        }

        public Criteria andMasterMaleLike(String value) {
            addCriterion("master_male like", value, "masterMale");
            return (Criteria) this;
        }

        public Criteria andMasterMaleNotLike(String value) {
            addCriterion("master_male not like", value, "masterMale");
            return (Criteria) this;
        }

        public Criteria andMasterMaleIn(List<String> values) {
            addCriterion("master_male in", values, "masterMale");
            return (Criteria) this;
        }

        public Criteria andMasterMaleNotIn(List<String> values) {
            addCriterion("master_male not in", values, "masterMale");
            return (Criteria) this;
        }

        public Criteria andMasterMaleBetween(String value1, String value2) {
            addCriterion("master_male between", value1, value2, "masterMale");
            return (Criteria) this;
        }

        public Criteria andMasterMaleNotBetween(String value1, String value2) {
            addCriterion("master_male not between", value1, value2, "masterMale");
            return (Criteria) this;
        }

        public Criteria andMasterFemaleIsNull() {
            addCriterion("master_female is null");
            return (Criteria) this;
        }

        public Criteria andMasterFemaleIsNotNull() {
            addCriterion("master_female is not null");
            return (Criteria) this;
        }

        public Criteria andMasterFemaleEqualTo(String value) {
            addCriterion("master_female =", value, "masterFemale");
            return (Criteria) this;
        }

        public Criteria andMasterFemaleNotEqualTo(String value) {
            addCriterion("master_female <>", value, "masterFemale");
            return (Criteria) this;
        }

        public Criteria andMasterFemaleGreaterThan(String value) {
            addCriterion("master_female >", value, "masterFemale");
            return (Criteria) this;
        }

        public Criteria andMasterFemaleGreaterThanOrEqualTo(String value) {
            addCriterion("master_female >=", value, "masterFemale");
            return (Criteria) this;
        }

        public Criteria andMasterFemaleLessThan(String value) {
            addCriterion("master_female <", value, "masterFemale");
            return (Criteria) this;
        }

        public Criteria andMasterFemaleLessThanOrEqualTo(String value) {
            addCriterion("master_female <=", value, "masterFemale");
            return (Criteria) this;
        }

        public Criteria andMasterFemaleLike(String value) {
            addCriterion("master_female like", value, "masterFemale");
            return (Criteria) this;
        }

        public Criteria andMasterFemaleNotLike(String value) {
            addCriterion("master_female not like", value, "masterFemale");
            return (Criteria) this;
        }

        public Criteria andMasterFemaleIn(List<String> values) {
            addCriterion("master_female in", values, "masterFemale");
            return (Criteria) this;
        }

        public Criteria andMasterFemaleNotIn(List<String> values) {
            addCriterion("master_female not in", values, "masterFemale");
            return (Criteria) this;
        }

        public Criteria andMasterFemaleBetween(String value1, String value2) {
            addCriterion("master_female between", value1, value2, "masterFemale");
            return (Criteria) this;
        }

        public Criteria andMasterFemaleNotBetween(String value1, String value2) {
            addCriterion("master_female not between", value1, value2, "masterFemale");
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

        public Criteria andEnchantlvlIsNull() {
            addCriterion("enchantlvl is null");
            return (Criteria) this;
        }

        public Criteria andEnchantlvlIsNotNull() {
            addCriterion("enchantlvl is not null");
            return (Criteria) this;
        }

        public Criteria andEnchantlvlEqualTo(Integer value) {
            addCriterion("enchantlvl =", value, "enchantlvl");
            return (Criteria) this;
        }

        public Criteria andEnchantlvlNotEqualTo(Integer value) {
            addCriterion("enchantlvl <>", value, "enchantlvl");
            return (Criteria) this;
        }

        public Criteria andEnchantlvlGreaterThan(Integer value) {
            addCriterion("enchantlvl >", value, "enchantlvl");
            return (Criteria) this;
        }

        public Criteria andEnchantlvlGreaterThanOrEqualTo(Integer value) {
            addCriterion("enchantlvl >=", value, "enchantlvl");
            return (Criteria) this;
        }

        public Criteria andEnchantlvlLessThan(Integer value) {
            addCriterion("enchantlvl <", value, "enchantlvl");
            return (Criteria) this;
        }

        public Criteria andEnchantlvlLessThanOrEqualTo(Integer value) {
            addCriterion("enchantlvl <=", value, "enchantlvl");
            return (Criteria) this;
        }

        public Criteria andEnchantlvlIn(List<Integer> values) {
            addCriterion("enchantlvl in", values, "enchantlvl");
            return (Criteria) this;
        }

        public Criteria andEnchantlvlNotIn(List<Integer> values) {
            addCriterion("enchantlvl not in", values, "enchantlvl");
            return (Criteria) this;
        }

        public Criteria andEnchantlvlBetween(Integer value1, Integer value2) {
            addCriterion("enchantlvl between", value1, value2, "enchantlvl");
            return (Criteria) this;
        }

        public Criteria andEnchantlvlNotBetween(Integer value1, Integer value2) {
            addCriterion("enchantlvl not between", value1, value2, "enchantlvl");
            return (Criteria) this;
        }

        public Criteria andVitalityIntensifyIsNull() {
            addCriterion("vitality_intensify is null");
            return (Criteria) this;
        }

        public Criteria andVitalityIntensifyIsNotNull() {
            addCriterion("vitality_intensify is not null");
            return (Criteria) this;
        }

        public Criteria andVitalityIntensifyEqualTo(Double value) {
            addCriterion("vitality_intensify =", value, "vitalityIntensify");
            return (Criteria) this;
        }

        public Criteria andVitalityIntensifyNotEqualTo(Double value) {
            addCriterion("vitality_intensify <>", value, "vitalityIntensify");
            return (Criteria) this;
        }

        public Criteria andVitalityIntensifyGreaterThan(Double value) {
            addCriterion("vitality_intensify >", value, "vitalityIntensify");
            return (Criteria) this;
        }

        public Criteria andVitalityIntensifyGreaterThanOrEqualTo(Double value) {
            addCriterion("vitality_intensify >=", value, "vitalityIntensify");
            return (Criteria) this;
        }

        public Criteria andVitalityIntensifyLessThan(Double value) {
            addCriterion("vitality_intensify <", value, "vitalityIntensify");
            return (Criteria) this;
        }

        public Criteria andVitalityIntensifyLessThanOrEqualTo(Double value) {
            addCriterion("vitality_intensify <=", value, "vitalityIntensify");
            return (Criteria) this;
        }

        public Criteria andVitalityIntensifyIn(List<Double> values) {
            addCriterion("vitality_intensify in", values, "vitalityIntensify");
            return (Criteria) this;
        }

        public Criteria andVitalityIntensifyNotIn(List<Double> values) {
            addCriterion("vitality_intensify not in", values, "vitalityIntensify");
            return (Criteria) this;
        }

        public Criteria andVitalityIntensifyBetween(Double value1, Double value2) {
            addCriterion("vitality_intensify between", value1, value2, "vitalityIntensify");
            return (Criteria) this;
        }

        public Criteria andVitalityIntensifyNotBetween(Double value1, Double value2) {
            addCriterion("vitality_intensify not between", value1, value2, "vitalityIntensify");
            return (Criteria) this;
        }

        public Criteria andAttackIntensifyIsNull() {
            addCriterion("attack_intensify is null");
            return (Criteria) this;
        }

        public Criteria andAttackIntensifyIsNotNull() {
            addCriterion("attack_intensify is not null");
            return (Criteria) this;
        }

        public Criteria andAttackIntensifyEqualTo(Double value) {
            addCriterion("attack_intensify =", value, "attackIntensify");
            return (Criteria) this;
        }

        public Criteria andAttackIntensifyNotEqualTo(Double value) {
            addCriterion("attack_intensify <>", value, "attackIntensify");
            return (Criteria) this;
        }

        public Criteria andAttackIntensifyGreaterThan(Double value) {
            addCriterion("attack_intensify >", value, "attackIntensify");
            return (Criteria) this;
        }

        public Criteria andAttackIntensifyGreaterThanOrEqualTo(Double value) {
            addCriterion("attack_intensify >=", value, "attackIntensify");
            return (Criteria) this;
        }

        public Criteria andAttackIntensifyLessThan(Double value) {
            addCriterion("attack_intensify <", value, "attackIntensify");
            return (Criteria) this;
        }

        public Criteria andAttackIntensifyLessThanOrEqualTo(Double value) {
            addCriterion("attack_intensify <=", value, "attackIntensify");
            return (Criteria) this;
        }

        public Criteria andAttackIntensifyIn(List<Double> values) {
            addCriterion("attack_intensify in", values, "attackIntensify");
            return (Criteria) this;
        }

        public Criteria andAttackIntensifyNotIn(List<Double> values) {
            addCriterion("attack_intensify not in", values, "attackIntensify");
            return (Criteria) this;
        }

        public Criteria andAttackIntensifyBetween(Double value1, Double value2) {
            addCriterion("attack_intensify between", value1, value2, "attackIntensify");
            return (Criteria) this;
        }

        public Criteria andAttackIntensifyNotBetween(Double value1, Double value2) {
            addCriterion("attack_intensify not between", value1, value2, "attackIntensify");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksIntensifyIsNull() {
            addCriterion("spell_attacks_intensify is null");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksIntensifyIsNotNull() {
            addCriterion("spell_attacks_intensify is not null");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksIntensifyEqualTo(Double value) {
            addCriterion("spell_attacks_intensify =", value, "spellAttacksIntensify");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksIntensifyNotEqualTo(Double value) {
            addCriterion("spell_attacks_intensify <>", value, "spellAttacksIntensify");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksIntensifyGreaterThan(Double value) {
            addCriterion("spell_attacks_intensify >", value, "spellAttacksIntensify");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksIntensifyGreaterThanOrEqualTo(Double value) {
            addCriterion("spell_attacks_intensify >=", value, "spellAttacksIntensify");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksIntensifyLessThan(Double value) {
            addCriterion("spell_attacks_intensify <", value, "spellAttacksIntensify");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksIntensifyLessThanOrEqualTo(Double value) {
            addCriterion("spell_attacks_intensify <=", value, "spellAttacksIntensify");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksIntensifyIn(List<Double> values) {
            addCriterion("spell_attacks_intensify in", values, "spellAttacksIntensify");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksIntensifyNotIn(List<Double> values) {
            addCriterion("spell_attacks_intensify not in", values, "spellAttacksIntensify");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksIntensifyBetween(Double value1, Double value2) {
            addCriterion("spell_attacks_intensify between", value1, value2, "spellAttacksIntensify");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksIntensifyNotBetween(Double value1, Double value2) {
            addCriterion("spell_attacks_intensify not between", value1, value2, "spellAttacksIntensify");
            return (Criteria) this;
        }

        public Criteria andPdefIntensifyIsNull() {
            addCriterion("pdef_intensify is null");
            return (Criteria) this;
        }

        public Criteria andPdefIntensifyIsNotNull() {
            addCriterion("pdef_intensify is not null");
            return (Criteria) this;
        }

        public Criteria andPdefIntensifyEqualTo(Double value) {
            addCriterion("pdef_intensify =", value, "pdefIntensify");
            return (Criteria) this;
        }

        public Criteria andPdefIntensifyNotEqualTo(Double value) {
            addCriterion("pdef_intensify <>", value, "pdefIntensify");
            return (Criteria) this;
        }

        public Criteria andPdefIntensifyGreaterThan(Double value) {
            addCriterion("pdef_intensify >", value, "pdefIntensify");
            return (Criteria) this;
        }

        public Criteria andPdefIntensifyGreaterThanOrEqualTo(Double value) {
            addCriterion("pdef_intensify >=", value, "pdefIntensify");
            return (Criteria) this;
        }

        public Criteria andPdefIntensifyLessThan(Double value) {
            addCriterion("pdef_intensify <", value, "pdefIntensify");
            return (Criteria) this;
        }

        public Criteria andPdefIntensifyLessThanOrEqualTo(Double value) {
            addCriterion("pdef_intensify <=", value, "pdefIntensify");
            return (Criteria) this;
        }

        public Criteria andPdefIntensifyIn(List<Double> values) {
            addCriterion("pdef_intensify in", values, "pdefIntensify");
            return (Criteria) this;
        }

        public Criteria andPdefIntensifyNotIn(List<Double> values) {
            addCriterion("pdef_intensify not in", values, "pdefIntensify");
            return (Criteria) this;
        }

        public Criteria andPdefIntensifyBetween(Double value1, Double value2) {
            addCriterion("pdef_intensify between", value1, value2, "pdefIntensify");
            return (Criteria) this;
        }

        public Criteria andPdefIntensifyNotBetween(Double value1, Double value2) {
            addCriterion("pdef_intensify not between", value1, value2, "pdefIntensify");
            return (Criteria) this;
        }

        public Criteria andMagdefIntensifyIsNull() {
            addCriterion("magdef_intensify is null");
            return (Criteria) this;
        }

        public Criteria andMagdefIntensifyIsNotNull() {
            addCriterion("magdef_intensify is not null");
            return (Criteria) this;
        }

        public Criteria andMagdefIntensifyEqualTo(Double value) {
            addCriterion("magdef_intensify =", value, "magdefIntensify");
            return (Criteria) this;
        }

        public Criteria andMagdefIntensifyNotEqualTo(Double value) {
            addCriterion("magdef_intensify <>", value, "magdefIntensify");
            return (Criteria) this;
        }

        public Criteria andMagdefIntensifyGreaterThan(Double value) {
            addCriterion("magdef_intensify >", value, "magdefIntensify");
            return (Criteria) this;
        }

        public Criteria andMagdefIntensifyGreaterThanOrEqualTo(Double value) {
            addCriterion("magdef_intensify >=", value, "magdefIntensify");
            return (Criteria) this;
        }

        public Criteria andMagdefIntensifyLessThan(Double value) {
            addCriterion("magdef_intensify <", value, "magdefIntensify");
            return (Criteria) this;
        }

        public Criteria andMagdefIntensifyLessThanOrEqualTo(Double value) {
            addCriterion("magdef_intensify <=", value, "magdefIntensify");
            return (Criteria) this;
        }

        public Criteria andMagdefIntensifyIn(List<Double> values) {
            addCriterion("magdef_intensify in", values, "magdefIntensify");
            return (Criteria) this;
        }

        public Criteria andMagdefIntensifyNotIn(List<Double> values) {
            addCriterion("magdef_intensify not in", values, "magdefIntensify");
            return (Criteria) this;
        }

        public Criteria andMagdefIntensifyBetween(Double value1, Double value2) {
            addCriterion("magdef_intensify between", value1, value2, "magdefIntensify");
            return (Criteria) this;
        }

        public Criteria andMagdefIntensifyNotBetween(Double value1, Double value2) {
            addCriterion("magdef_intensify not between", value1, value2, "magdefIntensify");
            return (Criteria) this;
        }

        public Criteria andCritIntensifyIsNull() {
            addCriterion("crit_intensify is null");
            return (Criteria) this;
        }

        public Criteria andCritIntensifyIsNotNull() {
            addCriterion("crit_intensify is not null");
            return (Criteria) this;
        }

        public Criteria andCritIntensifyEqualTo(Double value) {
            addCriterion("crit_intensify =", value, "critIntensify");
            return (Criteria) this;
        }

        public Criteria andCritIntensifyNotEqualTo(Double value) {
            addCriterion("crit_intensify <>", value, "critIntensify");
            return (Criteria) this;
        }

        public Criteria andCritIntensifyGreaterThan(Double value) {
            addCriterion("crit_intensify >", value, "critIntensify");
            return (Criteria) this;
        }

        public Criteria andCritIntensifyGreaterThanOrEqualTo(Double value) {
            addCriterion("crit_intensify >=", value, "critIntensify");
            return (Criteria) this;
        }

        public Criteria andCritIntensifyLessThan(Double value) {
            addCriterion("crit_intensify <", value, "critIntensify");
            return (Criteria) this;
        }

        public Criteria andCritIntensifyLessThanOrEqualTo(Double value) {
            addCriterion("crit_intensify <=", value, "critIntensify");
            return (Criteria) this;
        }

        public Criteria andCritIntensifyIn(List<Double> values) {
            addCriterion("crit_intensify in", values, "critIntensify");
            return (Criteria) this;
        }

        public Criteria andCritIntensifyNotIn(List<Double> values) {
            addCriterion("crit_intensify not in", values, "critIntensify");
            return (Criteria) this;
        }

        public Criteria andCritIntensifyBetween(Double value1, Double value2) {
            addCriterion("crit_intensify between", value1, value2, "critIntensify");
            return (Criteria) this;
        }

        public Criteria andCritIntensifyNotBetween(Double value1, Double value2) {
            addCriterion("crit_intensify not between", value1, value2, "critIntensify");
            return (Criteria) this;
        }

        public Criteria andDodgeIntensifyIsNull() {
            addCriterion("dodge_intensify is null");
            return (Criteria) this;
        }

        public Criteria andDodgeIntensifyIsNotNull() {
            addCriterion("dodge_intensify is not null");
            return (Criteria) this;
        }

        public Criteria andDodgeIntensifyEqualTo(Double value) {
            addCriterion("dodge_intensify =", value, "dodgeIntensify");
            return (Criteria) this;
        }

        public Criteria andDodgeIntensifyNotEqualTo(Double value) {
            addCriterion("dodge_intensify <>", value, "dodgeIntensify");
            return (Criteria) this;
        }

        public Criteria andDodgeIntensifyGreaterThan(Double value) {
            addCriterion("dodge_intensify >", value, "dodgeIntensify");
            return (Criteria) this;
        }

        public Criteria andDodgeIntensifyGreaterThanOrEqualTo(Double value) {
            addCriterion("dodge_intensify >=", value, "dodgeIntensify");
            return (Criteria) this;
        }

        public Criteria andDodgeIntensifyLessThan(Double value) {
            addCriterion("dodge_intensify <", value, "dodgeIntensify");
            return (Criteria) this;
        }

        public Criteria andDodgeIntensifyLessThanOrEqualTo(Double value) {
            addCriterion("dodge_intensify <=", value, "dodgeIntensify");
            return (Criteria) this;
        }

        public Criteria andDodgeIntensifyIn(List<Double> values) {
            addCriterion("dodge_intensify in", values, "dodgeIntensify");
            return (Criteria) this;
        }

        public Criteria andDodgeIntensifyNotIn(List<Double> values) {
            addCriterion("dodge_intensify not in", values, "dodgeIntensify");
            return (Criteria) this;
        }

        public Criteria andDodgeIntensifyBetween(Double value1, Double value2) {
            addCriterion("dodge_intensify between", value1, value2, "dodgeIntensify");
            return (Criteria) this;
        }

        public Criteria andDodgeIntensifyNotBetween(Double value1, Double value2) {
            addCriterion("dodge_intensify not between", value1, value2, "dodgeIntensify");
            return (Criteria) this;
        }

        public Criteria andHitRateIntensifyIsNull() {
            addCriterion("hit_rate_intensify is null");
            return (Criteria) this;
        }

        public Criteria andHitRateIntensifyIsNotNull() {
            addCriterion("hit_rate_intensify is not null");
            return (Criteria) this;
        }

        public Criteria andHitRateIntensifyEqualTo(Double value) {
            addCriterion("hit_rate_intensify =", value, "hitRateIntensify");
            return (Criteria) this;
        }

        public Criteria andHitRateIntensifyNotEqualTo(Double value) {
            addCriterion("hit_rate_intensify <>", value, "hitRateIntensify");
            return (Criteria) this;
        }

        public Criteria andHitRateIntensifyGreaterThan(Double value) {
            addCriterion("hit_rate_intensify >", value, "hitRateIntensify");
            return (Criteria) this;
        }

        public Criteria andHitRateIntensifyGreaterThanOrEqualTo(Double value) {
            addCriterion("hit_rate_intensify >=", value, "hitRateIntensify");
            return (Criteria) this;
        }

        public Criteria andHitRateIntensifyLessThan(Double value) {
            addCriterion("hit_rate_intensify <", value, "hitRateIntensify");
            return (Criteria) this;
        }

        public Criteria andHitRateIntensifyLessThanOrEqualTo(Double value) {
            addCriterion("hit_rate_intensify <=", value, "hitRateIntensify");
            return (Criteria) this;
        }

        public Criteria andHitRateIntensifyIn(List<Double> values) {
            addCriterion("hit_rate_intensify in", values, "hitRateIntensify");
            return (Criteria) this;
        }

        public Criteria andHitRateIntensifyNotIn(List<Double> values) {
            addCriterion("hit_rate_intensify not in", values, "hitRateIntensify");
            return (Criteria) this;
        }

        public Criteria andHitRateIntensifyBetween(Double value1, Double value2) {
            addCriterion("hit_rate_intensify between", value1, value2, "hitRateIntensify");
            return (Criteria) this;
        }

        public Criteria andHitRateIntensifyNotBetween(Double value1, Double value2) {
            addCriterion("hit_rate_intensify not between", value1, value2, "hitRateIntensify");
            return (Criteria) this;
        }

        public Criteria andDefenseCritIntensifyIsNull() {
            addCriterion("defense_crit_intensify is null");
            return (Criteria) this;
        }

        public Criteria andDefenseCritIntensifyIsNotNull() {
            addCriterion("defense_crit_intensify is not null");
            return (Criteria) this;
        }

        public Criteria andDefenseCritIntensifyEqualTo(Double value) {
            addCriterion("defense_crit_intensify =", value, "defenseCritIntensify");
            return (Criteria) this;
        }

        public Criteria andDefenseCritIntensifyNotEqualTo(Double value) {
            addCriterion("defense_crit_intensify <>", value, "defenseCritIntensify");
            return (Criteria) this;
        }

        public Criteria andDefenseCritIntensifyGreaterThan(Double value) {
            addCriterion("defense_crit_intensify >", value, "defenseCritIntensify");
            return (Criteria) this;
        }

        public Criteria andDefenseCritIntensifyGreaterThanOrEqualTo(Double value) {
            addCriterion("defense_crit_intensify >=", value, "defenseCritIntensify");
            return (Criteria) this;
        }

        public Criteria andDefenseCritIntensifyLessThan(Double value) {
            addCriterion("defense_crit_intensify <", value, "defenseCritIntensify");
            return (Criteria) this;
        }

        public Criteria andDefenseCritIntensifyLessThanOrEqualTo(Double value) {
            addCriterion("defense_crit_intensify <=", value, "defenseCritIntensify");
            return (Criteria) this;
        }

        public Criteria andDefenseCritIntensifyIn(List<Double> values) {
            addCriterion("defense_crit_intensify in", values, "defenseCritIntensify");
            return (Criteria) this;
        }

        public Criteria andDefenseCritIntensifyNotIn(List<Double> values) {
            addCriterion("defense_crit_intensify not in", values, "defenseCritIntensify");
            return (Criteria) this;
        }

        public Criteria andDefenseCritIntensifyBetween(Double value1, Double value2) {
            addCriterion("defense_crit_intensify between", value1, value2, "defenseCritIntensify");
            return (Criteria) this;
        }

        public Criteria andDefenseCritIntensifyNotBetween(Double value1, Double value2) {
            addCriterion("defense_crit_intensify not between", value1, value2, "defenseCritIntensify");
            return (Criteria) this;
        }

        public Criteria andGemRhombusIsNull() {
            addCriterion("gem_rhombus is null");
            return (Criteria) this;
        }

        public Criteria andGemRhombusIsNotNull() {
            addCriterion("gem_rhombus is not null");
            return (Criteria) this;
        }

        public Criteria andGemRhombusEqualTo(String value) {
            addCriterion("gem_rhombus =", value, "gemRhombus");
            return (Criteria) this;
        }

        public Criteria andGemRhombusNotEqualTo(String value) {
            addCriterion("gem_rhombus <>", value, "gemRhombus");
            return (Criteria) this;
        }

        public Criteria andGemRhombusGreaterThan(String value) {
            addCriterion("gem_rhombus >", value, "gemRhombus");
            return (Criteria) this;
        }

        public Criteria andGemRhombusGreaterThanOrEqualTo(String value) {
            addCriterion("gem_rhombus >=", value, "gemRhombus");
            return (Criteria) this;
        }

        public Criteria andGemRhombusLessThan(String value) {
            addCriterion("gem_rhombus <", value, "gemRhombus");
            return (Criteria) this;
        }

        public Criteria andGemRhombusLessThanOrEqualTo(String value) {
            addCriterion("gem_rhombus <=", value, "gemRhombus");
            return (Criteria) this;
        }

        public Criteria andGemRhombusLike(String value) {
            addCriterion("gem_rhombus like", value, "gemRhombus");
            return (Criteria) this;
        }

        public Criteria andGemRhombusNotLike(String value) {
            addCriterion("gem_rhombus not like", value, "gemRhombus");
            return (Criteria) this;
        }

        public Criteria andGemRhombusIn(List<String> values) {
            addCriterion("gem_rhombus in", values, "gemRhombus");
            return (Criteria) this;
        }

        public Criteria andGemRhombusNotIn(List<String> values) {
            addCriterion("gem_rhombus not in", values, "gemRhombus");
            return (Criteria) this;
        }

        public Criteria andGemRhombusBetween(String value1, String value2) {
            addCriterion("gem_rhombus between", value1, value2, "gemRhombus");
            return (Criteria) this;
        }

        public Criteria andGemRhombusNotBetween(String value1, String value2) {
            addCriterion("gem_rhombus not between", value1, value2, "gemRhombus");
            return (Criteria) this;
        }

        public Criteria andGemRoundnessIsNull() {
            addCriterion("gem_roundness is null");
            return (Criteria) this;
        }

        public Criteria andGemRoundnessIsNotNull() {
            addCriterion("gem_roundness is not null");
            return (Criteria) this;
        }

        public Criteria andGemRoundnessEqualTo(String value) {
            addCriterion("gem_roundness =", value, "gemRoundness");
            return (Criteria) this;
        }

        public Criteria andGemRoundnessNotEqualTo(String value) {
            addCriterion("gem_roundness <>", value, "gemRoundness");
            return (Criteria) this;
        }

        public Criteria andGemRoundnessGreaterThan(String value) {
            addCriterion("gem_roundness >", value, "gemRoundness");
            return (Criteria) this;
        }

        public Criteria andGemRoundnessGreaterThanOrEqualTo(String value) {
            addCriterion("gem_roundness >=", value, "gemRoundness");
            return (Criteria) this;
        }

        public Criteria andGemRoundnessLessThan(String value) {
            addCriterion("gem_roundness <", value, "gemRoundness");
            return (Criteria) this;
        }

        public Criteria andGemRoundnessLessThanOrEqualTo(String value) {
            addCriterion("gem_roundness <=", value, "gemRoundness");
            return (Criteria) this;
        }

        public Criteria andGemRoundnessLike(String value) {
            addCriterion("gem_roundness like", value, "gemRoundness");
            return (Criteria) this;
        }

        public Criteria andGemRoundnessNotLike(String value) {
            addCriterion("gem_roundness not like", value, "gemRoundness");
            return (Criteria) this;
        }

        public Criteria andGemRoundnessIn(List<String> values) {
            addCriterion("gem_roundness in", values, "gemRoundness");
            return (Criteria) this;
        }

        public Criteria andGemRoundnessNotIn(List<String> values) {
            addCriterion("gem_roundness not in", values, "gemRoundness");
            return (Criteria) this;
        }

        public Criteria andGemRoundnessBetween(String value1, String value2) {
            addCriterion("gem_roundness between", value1, value2, "gemRoundness");
            return (Criteria) this;
        }

        public Criteria andGemRoundnessNotBetween(String value1, String value2) {
            addCriterion("gem_roundness not between", value1, value2, "gemRoundness");
            return (Criteria) this;
        }

        public Criteria andGemHexagonIsNull() {
            addCriterion("gem_hexagon is null");
            return (Criteria) this;
        }

        public Criteria andGemHexagonIsNotNull() {
            addCriterion("gem_hexagon is not null");
            return (Criteria) this;
        }

        public Criteria andGemHexagonEqualTo(String value) {
            addCriterion("gem_hexagon =", value, "gemHexagon");
            return (Criteria) this;
        }

        public Criteria andGemHexagonNotEqualTo(String value) {
            addCriterion("gem_hexagon <>", value, "gemHexagon");
            return (Criteria) this;
        }

        public Criteria andGemHexagonGreaterThan(String value) {
            addCriterion("gem_hexagon >", value, "gemHexagon");
            return (Criteria) this;
        }

        public Criteria andGemHexagonGreaterThanOrEqualTo(String value) {
            addCriterion("gem_hexagon >=", value, "gemHexagon");
            return (Criteria) this;
        }

        public Criteria andGemHexagonLessThan(String value) {
            addCriterion("gem_hexagon <", value, "gemHexagon");
            return (Criteria) this;
        }

        public Criteria andGemHexagonLessThanOrEqualTo(String value) {
            addCriterion("gem_hexagon <=", value, "gemHexagon");
            return (Criteria) this;
        }

        public Criteria andGemHexagonLike(String value) {
            addCriterion("gem_hexagon like", value, "gemHexagon");
            return (Criteria) this;
        }

        public Criteria andGemHexagonNotLike(String value) {
            addCriterion("gem_hexagon not like", value, "gemHexagon");
            return (Criteria) this;
        }

        public Criteria andGemHexagonIn(List<String> values) {
            addCriterion("gem_hexagon in", values, "gemHexagon");
            return (Criteria) this;
        }

        public Criteria andGemHexagonNotIn(List<String> values) {
            addCriterion("gem_hexagon not in", values, "gemHexagon");
            return (Criteria) this;
        }

        public Criteria andGemHexagonBetween(String value1, String value2) {
            addCriterion("gem_hexagon between", value1, value2, "gemHexagon");
            return (Criteria) this;
        }

        public Criteria andGemHexagonNotBetween(String value1, String value2) {
            addCriterion("gem_hexagon not between", value1, value2, "gemHexagon");
            return (Criteria) this;
        }

        public Criteria andVitalityGemIsNull() {
            addCriterion("vitality_gem is null");
            return (Criteria) this;
        }

        public Criteria andVitalityGemIsNotNull() {
            addCriterion("vitality_gem is not null");
            return (Criteria) this;
        }

        public Criteria andVitalityGemEqualTo(Double value) {
            addCriterion("vitality_gem =", value, "vitalityGem");
            return (Criteria) this;
        }

        public Criteria andVitalityGemNotEqualTo(Double value) {
            addCriterion("vitality_gem <>", value, "vitalityGem");
            return (Criteria) this;
        }

        public Criteria andVitalityGemGreaterThan(Double value) {
            addCriterion("vitality_gem >", value, "vitalityGem");
            return (Criteria) this;
        }

        public Criteria andVitalityGemGreaterThanOrEqualTo(Double value) {
            addCriterion("vitality_gem >=", value, "vitalityGem");
            return (Criteria) this;
        }

        public Criteria andVitalityGemLessThan(Double value) {
            addCriterion("vitality_gem <", value, "vitalityGem");
            return (Criteria) this;
        }

        public Criteria andVitalityGemLessThanOrEqualTo(Double value) {
            addCriterion("vitality_gem <=", value, "vitalityGem");
            return (Criteria) this;
        }

        public Criteria andVitalityGemIn(List<Double> values) {
            addCriterion("vitality_gem in", values, "vitalityGem");
            return (Criteria) this;
        }

        public Criteria andVitalityGemNotIn(List<Double> values) {
            addCriterion("vitality_gem not in", values, "vitalityGem");
            return (Criteria) this;
        }

        public Criteria andVitalityGemBetween(Double value1, Double value2) {
            addCriterion("vitality_gem between", value1, value2, "vitalityGem");
            return (Criteria) this;
        }

        public Criteria andVitalityGemNotBetween(Double value1, Double value2) {
            addCriterion("vitality_gem not between", value1, value2, "vitalityGem");
            return (Criteria) this;
        }

        public Criteria andAttackGemIsNull() {
            addCriterion("attack_gem is null");
            return (Criteria) this;
        }

        public Criteria andAttackGemIsNotNull() {
            addCriterion("attack_gem is not null");
            return (Criteria) this;
        }

        public Criteria andAttackGemEqualTo(Double value) {
            addCriterion("attack_gem =", value, "attackGem");
            return (Criteria) this;
        }

        public Criteria andAttackGemNotEqualTo(Double value) {
            addCriterion("attack_gem <>", value, "attackGem");
            return (Criteria) this;
        }

        public Criteria andAttackGemGreaterThan(Double value) {
            addCriterion("attack_gem >", value, "attackGem");
            return (Criteria) this;
        }

        public Criteria andAttackGemGreaterThanOrEqualTo(Double value) {
            addCriterion("attack_gem >=", value, "attackGem");
            return (Criteria) this;
        }

        public Criteria andAttackGemLessThan(Double value) {
            addCriterion("attack_gem <", value, "attackGem");
            return (Criteria) this;
        }

        public Criteria andAttackGemLessThanOrEqualTo(Double value) {
            addCriterion("attack_gem <=", value, "attackGem");
            return (Criteria) this;
        }

        public Criteria andAttackGemIn(List<Double> values) {
            addCriterion("attack_gem in", values, "attackGem");
            return (Criteria) this;
        }

        public Criteria andAttackGemNotIn(List<Double> values) {
            addCriterion("attack_gem not in", values, "attackGem");
            return (Criteria) this;
        }

        public Criteria andAttackGemBetween(Double value1, Double value2) {
            addCriterion("attack_gem between", value1, value2, "attackGem");
            return (Criteria) this;
        }

        public Criteria andAttackGemNotBetween(Double value1, Double value2) {
            addCriterion("attack_gem not between", value1, value2, "attackGem");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksGemIsNull() {
            addCriterion("spell_attacks_gem is null");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksGemIsNotNull() {
            addCriterion("spell_attacks_gem is not null");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksGemEqualTo(Double value) {
            addCriterion("spell_attacks_gem =", value, "spellAttacksGem");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksGemNotEqualTo(Double value) {
            addCriterion("spell_attacks_gem <>", value, "spellAttacksGem");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksGemGreaterThan(Double value) {
            addCriterion("spell_attacks_gem >", value, "spellAttacksGem");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksGemGreaterThanOrEqualTo(Double value) {
            addCriterion("spell_attacks_gem >=", value, "spellAttacksGem");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksGemLessThan(Double value) {
            addCriterion("spell_attacks_gem <", value, "spellAttacksGem");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksGemLessThanOrEqualTo(Double value) {
            addCriterion("spell_attacks_gem <=", value, "spellAttacksGem");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksGemIn(List<Double> values) {
            addCriterion("spell_attacks_gem in", values, "spellAttacksGem");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksGemNotIn(List<Double> values) {
            addCriterion("spell_attacks_gem not in", values, "spellAttacksGem");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksGemBetween(Double value1, Double value2) {
            addCriterion("spell_attacks_gem between", value1, value2, "spellAttacksGem");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksGemNotBetween(Double value1, Double value2) {
            addCriterion("spell_attacks_gem not between", value1, value2, "spellAttacksGem");
            return (Criteria) this;
        }

        public Criteria andPdefGemIsNull() {
            addCriterion("pdef_gem is null");
            return (Criteria) this;
        }

        public Criteria andPdefGemIsNotNull() {
            addCriterion("pdef_gem is not null");
            return (Criteria) this;
        }

        public Criteria andPdefGemEqualTo(Double value) {
            addCriterion("pdef_gem =", value, "pdefGem");
            return (Criteria) this;
        }

        public Criteria andPdefGemNotEqualTo(Double value) {
            addCriterion("pdef_gem <>", value, "pdefGem");
            return (Criteria) this;
        }

        public Criteria andPdefGemGreaterThan(Double value) {
            addCriterion("pdef_gem >", value, "pdefGem");
            return (Criteria) this;
        }

        public Criteria andPdefGemGreaterThanOrEqualTo(Double value) {
            addCriterion("pdef_gem >=", value, "pdefGem");
            return (Criteria) this;
        }

        public Criteria andPdefGemLessThan(Double value) {
            addCriterion("pdef_gem <", value, "pdefGem");
            return (Criteria) this;
        }

        public Criteria andPdefGemLessThanOrEqualTo(Double value) {
            addCriterion("pdef_gem <=", value, "pdefGem");
            return (Criteria) this;
        }

        public Criteria andPdefGemIn(List<Double> values) {
            addCriterion("pdef_gem in", values, "pdefGem");
            return (Criteria) this;
        }

        public Criteria andPdefGemNotIn(List<Double> values) {
            addCriterion("pdef_gem not in", values, "pdefGem");
            return (Criteria) this;
        }

        public Criteria andPdefGemBetween(Double value1, Double value2) {
            addCriterion("pdef_gem between", value1, value2, "pdefGem");
            return (Criteria) this;
        }

        public Criteria andPdefGemNotBetween(Double value1, Double value2) {
            addCriterion("pdef_gem not between", value1, value2, "pdefGem");
            return (Criteria) this;
        }

        public Criteria andMagdefGemIsNull() {
            addCriterion("magdef_gem is null");
            return (Criteria) this;
        }

        public Criteria andMagdefGemIsNotNull() {
            addCriterion("magdef_gem is not null");
            return (Criteria) this;
        }

        public Criteria andMagdefGemEqualTo(Double value) {
            addCriterion("magdef_gem =", value, "magdefGem");
            return (Criteria) this;
        }

        public Criteria andMagdefGemNotEqualTo(Double value) {
            addCriterion("magdef_gem <>", value, "magdefGem");
            return (Criteria) this;
        }

        public Criteria andMagdefGemGreaterThan(Double value) {
            addCriterion("magdef_gem >", value, "magdefGem");
            return (Criteria) this;
        }

        public Criteria andMagdefGemGreaterThanOrEqualTo(Double value) {
            addCriterion("magdef_gem >=", value, "magdefGem");
            return (Criteria) this;
        }

        public Criteria andMagdefGemLessThan(Double value) {
            addCriterion("magdef_gem <", value, "magdefGem");
            return (Criteria) this;
        }

        public Criteria andMagdefGemLessThanOrEqualTo(Double value) {
            addCriterion("magdef_gem <=", value, "magdefGem");
            return (Criteria) this;
        }

        public Criteria andMagdefGemIn(List<Double> values) {
            addCriterion("magdef_gem in", values, "magdefGem");
            return (Criteria) this;
        }

        public Criteria andMagdefGemNotIn(List<Double> values) {
            addCriterion("magdef_gem not in", values, "magdefGem");
            return (Criteria) this;
        }

        public Criteria andMagdefGemBetween(Double value1, Double value2) {
            addCriterion("magdef_gem between", value1, value2, "magdefGem");
            return (Criteria) this;
        }

        public Criteria andMagdefGemNotBetween(Double value1, Double value2) {
            addCriterion("magdef_gem not between", value1, value2, "magdefGem");
            return (Criteria) this;
        }

        public Criteria andCritGemIsNull() {
            addCriterion("crit_gem is null");
            return (Criteria) this;
        }

        public Criteria andCritGemIsNotNull() {
            addCriterion("crit_gem is not null");
            return (Criteria) this;
        }

        public Criteria andCritGemEqualTo(Double value) {
            addCriterion("crit_gem =", value, "critGem");
            return (Criteria) this;
        }

        public Criteria andCritGemNotEqualTo(Double value) {
            addCriterion("crit_gem <>", value, "critGem");
            return (Criteria) this;
        }

        public Criteria andCritGemGreaterThan(Double value) {
            addCriterion("crit_gem >", value, "critGem");
            return (Criteria) this;
        }

        public Criteria andCritGemGreaterThanOrEqualTo(Double value) {
            addCriterion("crit_gem >=", value, "critGem");
            return (Criteria) this;
        }

        public Criteria andCritGemLessThan(Double value) {
            addCriterion("crit_gem <", value, "critGem");
            return (Criteria) this;
        }

        public Criteria andCritGemLessThanOrEqualTo(Double value) {
            addCriterion("crit_gem <=", value, "critGem");
            return (Criteria) this;
        }

        public Criteria andCritGemIn(List<Double> values) {
            addCriterion("crit_gem in", values, "critGem");
            return (Criteria) this;
        }

        public Criteria andCritGemNotIn(List<Double> values) {
            addCriterion("crit_gem not in", values, "critGem");
            return (Criteria) this;
        }

        public Criteria andCritGemBetween(Double value1, Double value2) {
            addCriterion("crit_gem between", value1, value2, "critGem");
            return (Criteria) this;
        }

        public Criteria andCritGemNotBetween(Double value1, Double value2) {
            addCriterion("crit_gem not between", value1, value2, "critGem");
            return (Criteria) this;
        }

        public Criteria andDodgeGemIsNull() {
            addCriterion("dodge_gem is null");
            return (Criteria) this;
        }

        public Criteria andDodgeGemIsNotNull() {
            addCriterion("dodge_gem is not null");
            return (Criteria) this;
        }

        public Criteria andDodgeGemEqualTo(Double value) {
            addCriterion("dodge_gem =", value, "dodgeGem");
            return (Criteria) this;
        }

        public Criteria andDodgeGemNotEqualTo(Double value) {
            addCriterion("dodge_gem <>", value, "dodgeGem");
            return (Criteria) this;
        }

        public Criteria andDodgeGemGreaterThan(Double value) {
            addCriterion("dodge_gem >", value, "dodgeGem");
            return (Criteria) this;
        }

        public Criteria andDodgeGemGreaterThanOrEqualTo(Double value) {
            addCriterion("dodge_gem >=", value, "dodgeGem");
            return (Criteria) this;
        }

        public Criteria andDodgeGemLessThan(Double value) {
            addCriterion("dodge_gem <", value, "dodgeGem");
            return (Criteria) this;
        }

        public Criteria andDodgeGemLessThanOrEqualTo(Double value) {
            addCriterion("dodge_gem <=", value, "dodgeGem");
            return (Criteria) this;
        }

        public Criteria andDodgeGemIn(List<Double> values) {
            addCriterion("dodge_gem in", values, "dodgeGem");
            return (Criteria) this;
        }

        public Criteria andDodgeGemNotIn(List<Double> values) {
            addCriterion("dodge_gem not in", values, "dodgeGem");
            return (Criteria) this;
        }

        public Criteria andDodgeGemBetween(Double value1, Double value2) {
            addCriterion("dodge_gem between", value1, value2, "dodgeGem");
            return (Criteria) this;
        }

        public Criteria andDodgeGemNotBetween(Double value1, Double value2) {
            addCriterion("dodge_gem not between", value1, value2, "dodgeGem");
            return (Criteria) this;
        }

        public Criteria andHitRateGemIsNull() {
            addCriterion("hit_rate_gem is null");
            return (Criteria) this;
        }

        public Criteria andHitRateGemIsNotNull() {
            addCriterion("hit_rate_gem is not null");
            return (Criteria) this;
        }

        public Criteria andHitRateGemEqualTo(Double value) {
            addCriterion("hit_rate_gem =", value, "hitRateGem");
            return (Criteria) this;
        }

        public Criteria andHitRateGemNotEqualTo(Double value) {
            addCriterion("hit_rate_gem <>", value, "hitRateGem");
            return (Criteria) this;
        }

        public Criteria andHitRateGemGreaterThan(Double value) {
            addCriterion("hit_rate_gem >", value, "hitRateGem");
            return (Criteria) this;
        }

        public Criteria andHitRateGemGreaterThanOrEqualTo(Double value) {
            addCriterion("hit_rate_gem >=", value, "hitRateGem");
            return (Criteria) this;
        }

        public Criteria andHitRateGemLessThan(Double value) {
            addCriterion("hit_rate_gem <", value, "hitRateGem");
            return (Criteria) this;
        }

        public Criteria andHitRateGemLessThanOrEqualTo(Double value) {
            addCriterion("hit_rate_gem <=", value, "hitRateGem");
            return (Criteria) this;
        }

        public Criteria andHitRateGemIn(List<Double> values) {
            addCriterion("hit_rate_gem in", values, "hitRateGem");
            return (Criteria) this;
        }

        public Criteria andHitRateGemNotIn(List<Double> values) {
            addCriterion("hit_rate_gem not in", values, "hitRateGem");
            return (Criteria) this;
        }

        public Criteria andHitRateGemBetween(Double value1, Double value2) {
            addCriterion("hit_rate_gem between", value1, value2, "hitRateGem");
            return (Criteria) this;
        }

        public Criteria andHitRateGemNotBetween(Double value1, Double value2) {
            addCriterion("hit_rate_gem not between", value1, value2, "hitRateGem");
            return (Criteria) this;
        }

        public Criteria andDefenseCritGemIsNull() {
            addCriterion("defense_crit_gem is null");
            return (Criteria) this;
        }

        public Criteria andDefenseCritGemIsNotNull() {
            addCriterion("defense_crit_gem is not null");
            return (Criteria) this;
        }

        public Criteria andDefenseCritGemEqualTo(Double value) {
            addCriterion("defense_crit_gem =", value, "defenseCritGem");
            return (Criteria) this;
        }

        public Criteria andDefenseCritGemNotEqualTo(Double value) {
            addCriterion("defense_crit_gem <>", value, "defenseCritGem");
            return (Criteria) this;
        }

        public Criteria andDefenseCritGemGreaterThan(Double value) {
            addCriterion("defense_crit_gem >", value, "defenseCritGem");
            return (Criteria) this;
        }

        public Criteria andDefenseCritGemGreaterThanOrEqualTo(Double value) {
            addCriterion("defense_crit_gem >=", value, "defenseCritGem");
            return (Criteria) this;
        }

        public Criteria andDefenseCritGemLessThan(Double value) {
            addCriterion("defense_crit_gem <", value, "defenseCritGem");
            return (Criteria) this;
        }

        public Criteria andDefenseCritGemLessThanOrEqualTo(Double value) {
            addCriterion("defense_crit_gem <=", value, "defenseCritGem");
            return (Criteria) this;
        }

        public Criteria andDefenseCritGemIn(List<Double> values) {
            addCriterion("defense_crit_gem in", values, "defenseCritGem");
            return (Criteria) this;
        }

        public Criteria andDefenseCritGemNotIn(List<Double> values) {
            addCriterion("defense_crit_gem not in", values, "defenseCritGem");
            return (Criteria) this;
        }

        public Criteria andDefenseCritGemBetween(Double value1, Double value2) {
            addCriterion("defense_crit_gem between", value1, value2, "defenseCritGem");
            return (Criteria) this;
        }

        public Criteria andDefenseCritGemNotBetween(Double value1, Double value2) {
            addCriterion("defense_crit_gem not between", value1, value2, "defenseCritGem");
            return (Criteria) this;
        }

        public Criteria andUpgradeIsNull() {
            addCriterion("upgrade is null");
            return (Criteria) this;
        }

        public Criteria andUpgradeIsNotNull() {
            addCriterion("upgrade is not null");
            return (Criteria) this;
        }

        public Criteria andUpgradeEqualTo(Integer value) {
            addCriterion("upgrade =", value, "upgrade");
            return (Criteria) this;
        }

        public Criteria andUpgradeNotEqualTo(Integer value) {
            addCriterion("upgrade <>", value, "upgrade");
            return (Criteria) this;
        }

        public Criteria andUpgradeGreaterThan(Integer value) {
            addCriterion("upgrade >", value, "upgrade");
            return (Criteria) this;
        }

        public Criteria andUpgradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("upgrade >=", value, "upgrade");
            return (Criteria) this;
        }

        public Criteria andUpgradeLessThan(Integer value) {
            addCriterion("upgrade <", value, "upgrade");
            return (Criteria) this;
        }

        public Criteria andUpgradeLessThanOrEqualTo(Integer value) {
            addCriterion("upgrade <=", value, "upgrade");
            return (Criteria) this;
        }

        public Criteria andUpgradeIn(List<Integer> values) {
            addCriterion("upgrade in", values, "upgrade");
            return (Criteria) this;
        }

        public Criteria andUpgradeNotIn(List<Integer> values) {
            addCriterion("upgrade not in", values, "upgrade");
            return (Criteria) this;
        }

        public Criteria andUpgradeBetween(Integer value1, Integer value2) {
            addCriterion("upgrade between", value1, value2, "upgrade");
            return (Criteria) this;
        }

        public Criteria andUpgradeNotBetween(Integer value1, Integer value2) {
            addCriterion("upgrade not between", value1, value2, "upgrade");
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