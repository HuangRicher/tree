package com.tongzhu.welfare.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoveTreeInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LoveTreeInfoExample() {
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

        public Criteria andLoveNumIsNull() {
            addCriterion("love_num is null");
            return (Criteria) this;
        }

        public Criteria andLoveNumIsNotNull() {
            addCriterion("love_num is not null");
            return (Criteria) this;
        }

        public Criteria andLoveNumEqualTo(Long value) {
            addCriterion("love_num =", value, "loveNum");
            return (Criteria) this;
        }

        public Criteria andLoveNumNotEqualTo(Long value) {
            addCriterion("love_num <>", value, "loveNum");
            return (Criteria) this;
        }

        public Criteria andLoveNumGreaterThan(Long value) {
            addCriterion("love_num >", value, "loveNum");
            return (Criteria) this;
        }

        public Criteria andLoveNumGreaterThanOrEqualTo(Long value) {
            addCriterion("love_num >=", value, "loveNum");
            return (Criteria) this;
        }

        public Criteria andLoveNumLessThan(Long value) {
            addCriterion("love_num <", value, "loveNum");
            return (Criteria) this;
        }

        public Criteria andLoveNumLessThanOrEqualTo(Long value) {
            addCriterion("love_num <=", value, "loveNum");
            return (Criteria) this;
        }

        public Criteria andLoveNumIn(List<Long> values) {
            addCriterion("love_num in", values, "loveNum");
            return (Criteria) this;
        }

        public Criteria andLoveNumNotIn(List<Long> values) {
            addCriterion("love_num not in", values, "loveNum");
            return (Criteria) this;
        }

        public Criteria andLoveNumBetween(Long value1, Long value2) {
            addCriterion("love_num between", value1, value2, "loveNum");
            return (Criteria) this;
        }

        public Criteria andLoveNumNotBetween(Long value1, Long value2) {
            addCriterion("love_num not between", value1, value2, "loveNum");
            return (Criteria) this;
        }

        public Criteria andLoveGradeIsNull() {
            addCriterion("love_grade is null");
            return (Criteria) this;
        }

        public Criteria andLoveGradeIsNotNull() {
            addCriterion("love_grade is not null");
            return (Criteria) this;
        }

        public Criteria andLoveGradeEqualTo(Integer value) {
            addCriterion("love_grade =", value, "loveGrade");
            return (Criteria) this;
        }

        public Criteria andLoveGradeNotEqualTo(Integer value) {
            addCriterion("love_grade <>", value, "loveGrade");
            return (Criteria) this;
        }

        public Criteria andLoveGradeGreaterThan(Integer value) {
            addCriterion("love_grade >", value, "loveGrade");
            return (Criteria) this;
        }

        public Criteria andLoveGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("love_grade >=", value, "loveGrade");
            return (Criteria) this;
        }

        public Criteria andLoveGradeLessThan(Integer value) {
            addCriterion("love_grade <", value, "loveGrade");
            return (Criteria) this;
        }

        public Criteria andLoveGradeLessThanOrEqualTo(Integer value) {
            addCriterion("love_grade <=", value, "loveGrade");
            return (Criteria) this;
        }

        public Criteria andLoveGradeIn(List<Integer> values) {
            addCriterion("love_grade in", values, "loveGrade");
            return (Criteria) this;
        }

        public Criteria andLoveGradeNotIn(List<Integer> values) {
            addCriterion("love_grade not in", values, "loveGrade");
            return (Criteria) this;
        }

        public Criteria andLoveGradeBetween(Integer value1, Integer value2) {
            addCriterion("love_grade between", value1, value2, "loveGrade");
            return (Criteria) this;
        }

        public Criteria andLoveGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("love_grade not between", value1, value2, "loveGrade");
            return (Criteria) this;
        }

        public Criteria andHappinessNumIsNull() {
            addCriterion("happiness_num is null");
            return (Criteria) this;
        }

        public Criteria andHappinessNumIsNotNull() {
            addCriterion("happiness_num is not null");
            return (Criteria) this;
        }

        public Criteria andHappinessNumEqualTo(Long value) {
            addCriterion("happiness_num =", value, "happinessNum");
            return (Criteria) this;
        }

        public Criteria andHappinessNumNotEqualTo(Long value) {
            addCriterion("happiness_num <>", value, "happinessNum");
            return (Criteria) this;
        }

        public Criteria andHappinessNumGreaterThan(Long value) {
            addCriterion("happiness_num >", value, "happinessNum");
            return (Criteria) this;
        }

        public Criteria andHappinessNumGreaterThanOrEqualTo(Long value) {
            addCriterion("happiness_num >=", value, "happinessNum");
            return (Criteria) this;
        }

        public Criteria andHappinessNumLessThan(Long value) {
            addCriterion(" happiness_num <", value, "happinessNum");
            return (Criteria) this;
        }

        public Criteria andHappinessNumLessThanOrEqualTo(Long value) {
            addCriterion("happiness_num <=", value, "happinessNum");
            return (Criteria) this;
        }

        public Criteria andHappinessNumIn(List<Long> values) {
            addCriterion("happiness_num in", values, "happinessNum");
            return (Criteria) this;
        }

        public Criteria andHappinessNumNotIn(List<Long> values) {
            addCriterion("happiness_num not in", values, "happinessNum");
            return (Criteria) this;
        }

        public Criteria andHappinessNumBetween(Long value1, Long value2) {
            addCriterion("happiness_num between", value1, value2, "happinessNum");
            return (Criteria) this;
        }

        public Criteria andHappinessNumNotBetween(Long value1, Long value2) {
            addCriterion("happiness_num not between", value1, value2, "happinessNum");
            return (Criteria) this;
        }

        public Criteria andWeddingRingGradeIsNull() {
            addCriterion("wedding_ring_grade is null");
            return (Criteria) this;
        }

        public Criteria andWeddingRingGradeIsNotNull() {
            addCriterion("wedding_ring_grade is not null");
            return (Criteria) this;
        }

        public Criteria andWeddingRingGradeEqualTo(Integer value) {
            addCriterion("wedding_ring_grade =", value, "weddingRingGrade");
            return (Criteria) this;
        }

        public Criteria andWeddingRingGradeNotEqualTo(Integer value) {
            addCriterion("wedding_ring_grade <>", value, "weddingRingGrade");
            return (Criteria) this;
        }

        public Criteria andWeddingRingGradeGreaterThan(Integer value) {
            addCriterion("wedding_ring_grade >", value, "weddingRingGrade");
            return (Criteria) this;
        }

        public Criteria andWeddingRingGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("wedding_ring_grade >=", value, "weddingRingGrade");
            return (Criteria) this;
        }

        public Criteria andWeddingRingGradeLessThan(Integer value) {
            addCriterion("wedding_ring_grade <", value, "weddingRingGrade");
            return (Criteria) this;
        }

        public Criteria andWeddingRingGradeLessThanOrEqualTo(Integer value) {
            addCriterion("wedding_ring_grade <=", value, "weddingRingGrade");
            return (Criteria) this;
        }

        public Criteria andWeddingRingGradeIn(List<Integer> values) {
            addCriterion("wedding_ring_grade in", values, "weddingRingGrade");
            return (Criteria) this;
        }

        public Criteria andWeddingRingGradeNotIn(List<Integer> values) {
            addCriterion("wedding_ring_grade not in", values, "weddingRingGrade");
            return (Criteria) this;
        }

        public Criteria andWeddingRingGradeBetween(Integer value1, Integer value2) {
            addCriterion("wedding_ring_grade between", value1, value2, "weddingRingGrade");
            return (Criteria) this;
        }

        public Criteria andWeddingRingGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("wedding_ring_grade not between", value1, value2, "weddingRingGrade");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdIsNull() {
            addCriterion("love_tree_id is null");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdIsNotNull() {
            addCriterion("love_tree_id is not null");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdEqualTo(String value) {
            addCriterion("love_tree_id =", value, "loveTreeId");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdNotEqualTo(String value) {
            addCriterion("love_tree_id <>", value, "loveTreeId");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdGreaterThan(String value) {
            addCriterion("love_tree_id >", value, "loveTreeId");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdGreaterThanOrEqualTo(String value) {
            addCriterion("love_tree_id >=", value, "loveTreeId");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdLessThan(String value) {
            addCriterion("love_tree_id <", value, "loveTreeId");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdLessThanOrEqualTo(String value) {
            addCriterion("love_tree_id <=", value, "loveTreeId");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdLike(String value) {
            addCriterion("love_tree_id like", value, "loveTreeId");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdNotLike(String value) {
            addCriterion("love_tree_id not like", value, "loveTreeId");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdIn(List<String> values) {
            addCriterion("love_tree_id in", values, "loveTreeId");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdNotIn(List<String> values) {
            addCriterion("love_tree_id not in", values, "loveTreeId");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdBetween(String value1, String value2) {
            addCriterion("love_tree_id between", value1, value2, "loveTreeId");
            return (Criteria) this;
        }

        public Criteria andLoveTreeIdNotBetween(String value1, String value2) {
            addCriterion("love_tree_id not between", value1, value2, "loveTreeId");
            return (Criteria) this;
        }

        public Criteria andWeddingRingIdIsNull() {
            addCriterion("wedding_ring_id is null");
            return (Criteria) this;
        }

        public Criteria andWeddingRingIdIsNotNull() {
            addCriterion("wedding_ring_id is not null");
            return (Criteria) this;
        }

        public Criteria andWeddingRingIdEqualTo(String value) {
            addCriterion("wedding_ring_id =", value, "weddingRingId");
            return (Criteria) this;
        }

        public Criteria andWeddingRingIdNotEqualTo(String value) {
            addCriterion("wedding_ring_id <>", value, "weddingRingId");
            return (Criteria) this;
        }

        public Criteria andWeddingRingIdGreaterThan(String value) {
            addCriterion("wedding_ring_id >", value, "weddingRingId");
            return (Criteria) this;
        }

        public Criteria andWeddingRingIdGreaterThanOrEqualTo(String value) {
            addCriterion("wedding_ring_id >=", value, "weddingRingId");
            return (Criteria) this;
        }

        public Criteria andWeddingRingIdLessThan(String value) {
            addCriterion("wedding_ring_id <", value, "weddingRingId");
            return (Criteria) this;
        }

        public Criteria andWeddingRingIdLessThanOrEqualTo(String value) {
            addCriterion("wedding_ring_id <=", value, "weddingRingId");
            return (Criteria) this;
        }

        public Criteria andWeddingRingIdLike(String value) {
            addCriterion("wedding_ring_id like", value, "weddingRingId");
            return (Criteria) this;
        }

        public Criteria andWeddingRingIdNotLike(String value) {
            addCriterion("wedding_ring_id not like", value, "weddingRingId");
            return (Criteria) this;
        }

        public Criteria andWeddingRingIdIn(List<String> values) {
            addCriterion("wedding_ring_id in", values, "weddingRingId");
            return (Criteria) this;
        }

        public Criteria andWeddingRingIdNotIn(List<String> values) {
            addCriterion("wedding_ring_id not in", values, "weddingRingId");
            return (Criteria) this;
        }

        public Criteria andWeddingRingIdBetween(String value1, String value2) {
            addCriterion("wedding_ring_id between", value1, value2, "weddingRingId");
            return (Criteria) this;
        }

        public Criteria andWeddingRingIdNotBetween(String value1, String value2) {
            addCriterion("wedding_ring_id not between", value1, value2, "weddingRingId");
            return (Criteria) this;
        }

        public Criteria andPropertiesIsNull() {
            addCriterion("properties is null");
            return (Criteria) this;
        }

        public Criteria andPropertiesIsNotNull() {
            addCriterion("properties is not null");
            return (Criteria) this;
        }

        public Criteria andPropertiesEqualTo(String value) {
            addCriterion("properties =", value, "properties");
            return (Criteria) this;
        }

        public Criteria andPropertiesNotEqualTo(String value) {
            addCriterion("properties <>", value, "properties");
            return (Criteria) this;
        }

        public Criteria andPropertiesGreaterThan(String value) {
            addCriterion("properties >", value, "properties");
            return (Criteria) this;
        }

        public Criteria andPropertiesGreaterThanOrEqualTo(String value) {
            addCriterion("properties >=", value, "properties");
            return (Criteria) this;
        }

        public Criteria andPropertiesLessThan(String value) {
            addCriterion("properties <", value, "properties");
            return (Criteria) this;
        }

        public Criteria andPropertiesLessThanOrEqualTo(String value) {
            addCriterion("properties <=", value, "properties");
            return (Criteria) this;
        }

        public Criteria andPropertiesLike(String value) {
            addCriterion("properties like", value, "properties");
            return (Criteria) this;
        }

        public Criteria andPropertiesNotLike(String value) {
            addCriterion("properties not like", value, "properties");
            return (Criteria) this;
        }

        public Criteria andPropertiesIn(List<String> values) {
            addCriterion("properties in", values, "properties");
            return (Criteria) this;
        }

        public Criteria andPropertiesNotIn(List<String> values) {
            addCriterion("properties not in", values, "properties");
            return (Criteria) this;
        }

        public Criteria andPropertiesBetween(String value1, String value2) {
            addCriterion("properties between", value1, value2, "properties");
            return (Criteria) this;
        }

        public Criteria andPropertiesNotBetween(String value1, String value2) {
            addCriterion("properties not between", value1, value2, "properties");
            return (Criteria) this;
        }

        public Criteria andPlayJokesNumIsNull() {
            addCriterion("play_jokes_num is null");
            return (Criteria) this;
        }

        public Criteria andPlayJokesNumIsNotNull() {
            addCriterion("play_jokes_num is not null");
            return (Criteria) this;
        }

        public Criteria andPlayJokesNumEqualTo(Integer value) {
            addCriterion("play_jokes_num =", value, "playJokesNum");
            return (Criteria) this;
        }

        public Criteria andPlayJokesNumNotEqualTo(Integer value) {
            addCriterion("play_jokes_num <>", value, "playJokesNum");
            return (Criteria) this;
        }

        public Criteria andPlayJokesNumGreaterThan(Integer value) {
            addCriterion("play_jokes_num >", value, "playJokesNum");
            return (Criteria) this;
        }

        public Criteria andPlayJokesNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("play_jokes_num >=", value, "playJokesNum");
            return (Criteria) this;
        }

        public Criteria andPlayJokesNumLessThan(Integer value) {
            addCriterion("play_jokes_num <", value, "playJokesNum");
            return (Criteria) this;
        }

        public Criteria andPlayJokesNumLessThanOrEqualTo(Integer value) {
            addCriterion("play_jokes_num <=", value, "playJokesNum");
            return (Criteria) this;
        }

        public Criteria andPlayJokesNumIn(List<Integer> values) {
            addCriterion("play_jokes_num in", values, "playJokesNum");
            return (Criteria) this;
        }

        public Criteria andPlayJokesNumNotIn(List<Integer> values) {
            addCriterion("play_jokes_num not in", values, "playJokesNum");
            return (Criteria) this;
        }

        public Criteria andPlayJokesNumBetween(Integer value1, Integer value2) {
            addCriterion("play_jokes_num between", value1, value2, "playJokesNum");
            return (Criteria) this;
        }

        public Criteria andPlayJokesNumNotBetween(Integer value1, Integer value2) {
            addCriterion("play_jokes_num not between", value1, value2, "playJokesNum");
            return (Criteria) this;
        }

        public Criteria andWeddingNumIsNull() {
            addCriterion("wedding_num is null");
            return (Criteria) this;
        }

        public Criteria andWeddingNumIsNotNull() {
            addCriterion("wedding_num is not null");
            return (Criteria) this;
        }

        public Criteria andWeddingNumEqualTo(Integer value) {
            addCriterion("wedding_num =", value, "weddingNum");
            return (Criteria) this;
        }

        public Criteria andWeddingNumNotEqualTo(Integer value) {
            addCriterion("wedding_num <>", value, "weddingNum");
            return (Criteria) this;
        }

        public Criteria andWeddingNumGreaterThan(Integer value) {
            addCriterion("wedding_num >", value, "weddingNum");
            return (Criteria) this;
        }

        public Criteria andWeddingNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("wedding_num >=", value, "weddingNum");
            return (Criteria) this;
        }

        public Criteria andWeddingNumLessThan(Integer value) {
            addCriterion("wedding_num <", value, "weddingNum");
            return (Criteria) this;
        }

        public Criteria andWeddingNumLessThanOrEqualTo(Integer value) {
            addCriterion("wedding_num <=", value, "weddingNum");
            return (Criteria) this;
        }

        public Criteria andWeddingNumIn(List<Integer> values) {
            addCriterion("wedding_num in", values, "weddingNum");
            return (Criteria) this;
        }

        public Criteria andWeddingNumNotIn(List<Integer> values) {
            addCriterion("wedding_num not in", values, "weddingNum");
            return (Criteria) this;
        }

        public Criteria andWeddingNumBetween(Integer value1, Integer value2) {
            addCriterion("wedding_num between", value1, value2, "weddingNum");
            return (Criteria) this;
        }

        public Criteria andWeddingNumNotBetween(Integer value1, Integer value2) {
            addCriterion("wedding_num not between", value1, value2, "weddingNum");
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