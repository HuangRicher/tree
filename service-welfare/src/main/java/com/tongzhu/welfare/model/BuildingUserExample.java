package com.tongzhu.welfare.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BuildingUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BuildingUserExample() {
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

        public Criteria andBuildingIdIsNull() {
            addCriterion("building_id is null");
            return (Criteria) this;
        }

        public Criteria andBuildingIdIsNotNull() {
            addCriterion("building_id is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingIdEqualTo(Integer value) {
            addCriterion("building_id =", value, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdNotEqualTo(Integer value) {
            addCriterion("building_id <>", value, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdGreaterThan(Integer value) {
            addCriterion("building_id >", value, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("building_id >=", value, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdLessThan(Integer value) {
            addCriterion("building_id <", value, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdLessThanOrEqualTo(Integer value) {
            addCriterion("building_id <=", value, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdIn(List<Integer> values) {
            addCriterion("building_id in", values, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdNotIn(List<Integer> values) {
            addCriterion("building_id not in", values, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdBetween(Integer value1, Integer value2) {
            addCriterion("building_id between", value1, value2, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdNotBetween(Integer value1, Integer value2) {
            addCriterion("building_id not between", value1, value2, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingNameIsNull() {
            addCriterion("building_name is null");
            return (Criteria) this;
        }

        public Criteria andBuildingNameIsNotNull() {
            addCriterion("building_name is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingNameEqualTo(String value) {
            addCriterion("building_name =", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameNotEqualTo(String value) {
            addCriterion("building_name <>", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameGreaterThan(String value) {
            addCriterion("building_name >", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameGreaterThanOrEqualTo(String value) {
            addCriterion("building_name >=", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameLessThan(String value) {
            addCriterion("building_name <", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameLessThanOrEqualTo(String value) {
            addCriterion("building_name <=", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameLike(String value) {
            addCriterion("building_name like", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameNotLike(String value) {
            addCriterion("building_name not like", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameIn(List<String> values) {
            addCriterion("building_name in", values, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameNotIn(List<String> values) {
            addCriterion("building_name not in", values, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameBetween(String value1, String value2) {
            addCriterion("building_name between", value1, value2, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameNotBetween(String value1, String value2) {
            addCriterion("building_name not between", value1, value2, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingGradeIsNull() {
            addCriterion("building_grade is null");
            return (Criteria) this;
        }

        public Criteria andBuildingGradeIsNotNull() {
            addCriterion("building_grade is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingGradeEqualTo(Integer value) {
            addCriterion("building_grade =", value, "buildingGrade");
            return (Criteria) this;
        }

        public Criteria andBuildingGradeNotEqualTo(Integer value) {
            addCriterion("building_grade <>", value, "buildingGrade");
            return (Criteria) this;
        }

        public Criteria andBuildingGradeGreaterThan(Integer value) {
            addCriterion("building_grade >", value, "buildingGrade");
            return (Criteria) this;
        }

        public Criteria andBuildingGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("building_grade >=", value, "buildingGrade");
            return (Criteria) this;
        }

        public Criteria andBuildingGradeLessThan(Integer value) {
            addCriterion("building_grade <", value, "buildingGrade");
            return (Criteria) this;
        }

        public Criteria andBuildingGradeLessThanOrEqualTo(Integer value) {
            addCriterion("building_grade <=", value, "buildingGrade");
            return (Criteria) this;
        }

        public Criteria andBuildingGradeIn(List<Integer> values) {
            addCriterion("building_grade in", values, "buildingGrade");
            return (Criteria) this;
        }

        public Criteria andBuildingGradeNotIn(List<Integer> values) {
            addCriterion("building_grade not in", values, "buildingGrade");
            return (Criteria) this;
        }

        public Criteria andBuildingGradeBetween(Integer value1, Integer value2) {
            addCriterion("building_grade between", value1, value2, "buildingGrade");
            return (Criteria) this;
        }

        public Criteria andBuildingGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("building_grade not between", value1, value2, "buildingGrade");
            return (Criteria) this;
        }

        public Criteria andBuildingStatusIsNull() {
            addCriterion("building_status is null");
            return (Criteria) this;
        }

        public Criteria andBuildingStatusIsNotNull() {
            addCriterion("building_status is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingStatusEqualTo(Integer value) {
            addCriterion("building_status =", value, "buildingStatus");
            return (Criteria) this;
        }

        public Criteria andBuildingStatusNotEqualTo(Integer value) {
            addCriterion("building_status <>", value, "buildingStatus");
            return (Criteria) this;
        }

        public Criteria andBuildingStatusGreaterThan(Integer value) {
            addCriterion("building_status >", value, "buildingStatus");
            return (Criteria) this;
        }

        public Criteria andBuildingStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("building_status >=", value, "buildingStatus");
            return (Criteria) this;
        }

        public Criteria andBuildingStatusLessThan(Integer value) {
            addCriterion("building_status <", value, "buildingStatus");
            return (Criteria) this;
        }

        public Criteria andBuildingStatusLessThanOrEqualTo(Integer value) {
            addCriterion("building_status <=", value, "buildingStatus");
            return (Criteria) this;
        }

        public Criteria andBuildingStatusIn(List<Integer> values) {
            addCriterion("building_status in", values, "buildingStatus");
            return (Criteria) this;
        }

        public Criteria andBuildingStatusNotIn(List<Integer> values) {
            addCriterion("building_status not in", values, "buildingStatus");
            return (Criteria) this;
        }

        public Criteria andBuildingStatusBetween(Integer value1, Integer value2) {
            addCriterion("building_status between", value1, value2, "buildingStatus");
            return (Criteria) this;
        }

        public Criteria andBuildingStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("building_status not between", value1, value2, "buildingStatus");
            return (Criteria) this;
        }

        public Criteria andBuildingGoldIsNull() {
            addCriterion("building_gold is null");
            return (Criteria) this;
        }

        public Criteria andBuildingGoldIsNotNull() {
            addCriterion("building_gold is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingGoldEqualTo(Integer value) {
            addCriterion("building_gold =", value, "buildingGold");
            return (Criteria) this;
        }

        public Criteria andBuildingGoldNotEqualTo(Integer value) {
            addCriterion("building_gold <>", value, "buildingGold");
            return (Criteria) this;
        }

        public Criteria andBuildingGoldGreaterThan(Integer value) {
            addCriterion("building_gold >", value, "buildingGold");
            return (Criteria) this;
        }

        public Criteria andBuildingGoldGreaterThanOrEqualTo(Integer value) {
            addCriterion("building_gold >=", value, "buildingGold");
            return (Criteria) this;
        }

        public Criteria andBuildingGoldLessThan(Integer value) {
            addCriterion("building_gold <", value, "buildingGold");
            return (Criteria) this;
        }

        public Criteria andBuildingGoldLessThanOrEqualTo(Integer value) {
            addCriterion("building_gold <=", value, "buildingGold");
            return (Criteria) this;
        }

        public Criteria andBuildingGoldIn(List<Integer> values) {
            addCriterion("building_gold in", values, "buildingGold");
            return (Criteria) this;
        }

        public Criteria andBuildingGoldNotIn(List<Integer> values) {
            addCriterion("building_gold not in", values, "buildingGold");
            return (Criteria) this;
        }

        public Criteria andBuildingGoldBetween(Integer value1, Integer value2) {
            addCriterion("building_gold between", value1, value2, "buildingGold");
            return (Criteria) this;
        }

        public Criteria andBuildingGoldNotBetween(Integer value1, Integer value2) {
            addCriterion("building_gold not between", value1, value2, "buildingGold");
            return (Criteria) this;
        }

        public Criteria andBuildingUpgradeIsNull() {
            addCriterion("building_upgrade is null");
            return (Criteria) this;
        }

        public Criteria andBuildingUpgradeIsNotNull() {
            addCriterion("building_upgrade is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingUpgradeEqualTo(Integer value) {
            addCriterion("building_upgrade =", value, "buildingUpgrade");
            return (Criteria) this;
        }

        public Criteria andBuildingUpgradeNotEqualTo(Integer value) {
            addCriterion("building_upgrade <>", value, "buildingUpgrade");
            return (Criteria) this;
        }

        public Criteria andBuildingUpgradeGreaterThan(Integer value) {
            addCriterion("building_upgrade >", value, "buildingUpgrade");
            return (Criteria) this;
        }

        public Criteria andBuildingUpgradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("building_upgrade >=", value, "buildingUpgrade");
            return (Criteria) this;
        }

        public Criteria andBuildingUpgradeLessThan(Integer value) {
            addCriterion("building_upgrade <", value, "buildingUpgrade");
            return (Criteria) this;
        }

        public Criteria andBuildingUpgradeLessThanOrEqualTo(Integer value) {
            addCriterion("building_upgrade <=", value, "buildingUpgrade");
            return (Criteria) this;
        }

        public Criteria andBuildingUpgradeIn(List<Integer> values) {
            addCriterion("building_upgrade in", values, "buildingUpgrade");
            return (Criteria) this;
        }

        public Criteria andBuildingUpgradeNotIn(List<Integer> values) {
            addCriterion("building_upgrade not in", values, "buildingUpgrade");
            return (Criteria) this;
        }

        public Criteria andBuildingUpgradeBetween(Integer value1, Integer value2) {
            addCriterion("building_upgrade between", value1, value2, "buildingUpgrade");
            return (Criteria) this;
        }

        public Criteria andBuildingUpgradeNotBetween(Integer value1, Integer value2) {
            addCriterion("building_upgrade not between", value1, value2, "buildingUpgrade");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailedIsNull() {
            addCriterion("building_detailed is null");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailedIsNotNull() {
            addCriterion("building_detailed is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailedEqualTo(String value) {
            addCriterion("building_detailed =", value, "buildingDetailed");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailedNotEqualTo(String value) {
            addCriterion("building_detailed <>", value, "buildingDetailed");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailedGreaterThan(String value) {
            addCriterion("building_detailed >", value, "buildingDetailed");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailedGreaterThanOrEqualTo(String value) {
            addCriterion("building_detailed >=", value, "buildingDetailed");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailedLessThan(String value) {
            addCriterion("building_detailed <", value, "buildingDetailed");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailedLessThanOrEqualTo(String value) {
            addCriterion("building_detailed <=", value, "buildingDetailed");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailedLike(String value) {
            addCriterion("building_detailed like", value, "buildingDetailed");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailedNotLike(String value) {
            addCriterion("building_detailed not like", value, "buildingDetailed");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailedIn(List<String> values) {
            addCriterion("building_detailed in", values, "buildingDetailed");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailedNotIn(List<String> values) {
            addCriterion("building_detailed not in", values, "buildingDetailed");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailedBetween(String value1, String value2) {
            addCriterion("building_detailed between", value1, value2, "buildingDetailed");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailedNotBetween(String value1, String value2) {
            addCriterion("building_detailed not between", value1, value2, "buildingDetailed");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeIsNull() {
            addCriterion("building_type is null");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeIsNotNull() {
            addCriterion("building_type is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeEqualTo(Integer value) {
            addCriterion("building_type =", value, "buildingType");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeNotEqualTo(Integer value) {
            addCriterion("building_type <>", value, "buildingType");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeGreaterThan(Integer value) {
            addCriterion("building_type >", value, "buildingType");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("building_type >=", value, "buildingType");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeLessThan(Integer value) {
            addCriterion("building_type <", value, "buildingType");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeLessThanOrEqualTo(Integer value) {
            addCriterion("building_type <=", value, "buildingType");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeIn(List<Integer> values) {
            addCriterion("building_type in", values, "buildingType");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeNotIn(List<Integer> values) {
            addCriterion("building_type not in", values, "buildingType");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeBetween(Integer value1, Integer value2) {
            addCriterion("building_type between", value1, value2, "buildingType");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("building_type not between", value1, value2, "buildingType");
            return (Criteria) this;
        }

        public Criteria andBiuldingOutputMinIsNull() {
            addCriterion("biulding_output_min is null");
            return (Criteria) this;
        }

        public Criteria andBiuldingOutputMinIsNotNull() {
            addCriterion("biulding_output_min is not null");
            return (Criteria) this;
        }

        public Criteria andBiuldingOutputMinEqualTo(Integer value) {
            addCriterion("biulding_output_min =", value, "biuldingOutputMin");
            return (Criteria) this;
        }

        public Criteria andBiuldingOutputMinNotEqualTo(Integer value) {
            addCriterion("biulding_output_min <>", value, "biuldingOutputMin");
            return (Criteria) this;
        }

        public Criteria andBiuldingOutputMinGreaterThan(Integer value) {
            addCriterion("biulding_output_min >", value, "biuldingOutputMin");
            return (Criteria) this;
        }

        public Criteria andBiuldingOutputMinGreaterThanOrEqualTo(Integer value) {
            addCriterion("biulding_output_min >=", value, "biuldingOutputMin");
            return (Criteria) this;
        }

        public Criteria andBiuldingOutputMinLessThan(Integer value) {
            addCriterion("biulding_output_min <", value, "biuldingOutputMin");
            return (Criteria) this;
        }

        public Criteria andBiuldingOutputMinLessThanOrEqualTo(Integer value) {
            addCriterion("biulding_output_min <=", value, "biuldingOutputMin");
            return (Criteria) this;
        }

        public Criteria andBiuldingOutputMinIn(List<Integer> values) {
            addCriterion("biulding_output_min in", values, "biuldingOutputMin");
            return (Criteria) this;
        }

        public Criteria andBiuldingOutputMinNotIn(List<Integer> values) {
            addCriterion("biulding_output_min not in", values, "biuldingOutputMin");
            return (Criteria) this;
        }

        public Criteria andBiuldingOutputMinBetween(Integer value1, Integer value2) {
            addCriterion("biulding_output_min between", value1, value2, "biuldingOutputMin");
            return (Criteria) this;
        }

        public Criteria andBiuldingOutputMinNotBetween(Integer value1, Integer value2) {
            addCriterion("biulding_output_min not between", value1, value2, "biuldingOutputMin");
            return (Criteria) this;
        }

        public Criteria andBuildingOutputMaxIsNull() {
            addCriterion("building_output_max is null");
            return (Criteria) this;
        }

        public Criteria andBuildingOutputMaxIsNotNull() {
            addCriterion("building_output_max is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingOutputMaxEqualTo(Integer value) {
            addCriterion("building_output_max =", value, "buildingOutputMax");
            return (Criteria) this;
        }

        public Criteria andBuildingOutputMaxNotEqualTo(Integer value) {
            addCriterion("building_output_max <>", value, "buildingOutputMax");
            return (Criteria) this;
        }

        public Criteria andBuildingOutputMaxGreaterThan(Integer value) {
            addCriterion("building_output_max >", value, "buildingOutputMax");
            return (Criteria) this;
        }

        public Criteria andBuildingOutputMaxGreaterThanOrEqualTo(Integer value) {
            addCriterion("building_output_max >=", value, "buildingOutputMax");
            return (Criteria) this;
        }

        public Criteria andBuildingOutputMaxLessThan(Integer value) {
            addCriterion("building_output_max <", value, "buildingOutputMax");
            return (Criteria) this;
        }

        public Criteria andBuildingOutputMaxLessThanOrEqualTo(Integer value) {
            addCriterion("building_output_max <=", value, "buildingOutputMax");
            return (Criteria) this;
        }

        public Criteria andBuildingOutputMaxIn(List<Integer> values) {
            addCriterion("building_output_max in", values, "buildingOutputMax");
            return (Criteria) this;
        }

        public Criteria andBuildingOutputMaxNotIn(List<Integer> values) {
            addCriterion("building_output_max not in", values, "buildingOutputMax");
            return (Criteria) this;
        }

        public Criteria andBuildingOutputMaxBetween(Integer value1, Integer value2) {
            addCriterion("building_output_max between", value1, value2, "buildingOutputMax");
            return (Criteria) this;
        }

        public Criteria andBuildingOutputMaxNotBetween(Integer value1, Integer value2) {
            addCriterion("building_output_max not between", value1, value2, "buildingOutputMax");
            return (Criteria) this;
        }

        public Criteria andBuildingOutputRateIsNull() {
            addCriterion("building_output_rate is null");
            return (Criteria) this;
        }

        public Criteria andBuildingOutputRateIsNotNull() {
            addCriterion("building_output_rate is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingOutputRateEqualTo(Integer value) {
            addCriterion("building_output_rate =", value, "buildingOutputRate");
            return (Criteria) this;
        }

        public Criteria andBuildingOutputRateNotEqualTo(Integer value) {
            addCriterion("building_output_rate <>", value, "buildingOutputRate");
            return (Criteria) this;
        }

        public Criteria andBuildingOutputRateGreaterThan(Integer value) {
            addCriterion("building_output_rate >", value, "buildingOutputRate");
            return (Criteria) this;
        }

        public Criteria andBuildingOutputRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("building_output_rate >=", value, "buildingOutputRate");
            return (Criteria) this;
        }

        public Criteria andBuildingOutputRateLessThan(Integer value) {
            addCriterion("building_output_rate <", value, "buildingOutputRate");
            return (Criteria) this;
        }

        public Criteria andBuildingOutputRateLessThanOrEqualTo(Integer value) {
            addCriterion("building_output_rate <=", value, "buildingOutputRate");
            return (Criteria) this;
        }

        public Criteria andBuildingOutputRateIn(List<Integer> values) {
            addCriterion("building_output_rate in", values, "buildingOutputRate");
            return (Criteria) this;
        }

        public Criteria andBuildingOutputRateNotIn(List<Integer> values) {
            addCriterion("building_output_rate not in", values, "buildingOutputRate");
            return (Criteria) this;
        }

        public Criteria andBuildingOutputRateBetween(Integer value1, Integer value2) {
            addCriterion("building_output_rate between", value1, value2, "buildingOutputRate");
            return (Criteria) this;
        }

        public Criteria andBuildingOutputRateNotBetween(Integer value1, Integer value2) {
            addCriterion("building_output_rate not between", value1, value2, "buildingOutputRate");
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

        public Criteria andUpdateEndTimeIsNull() {
            addCriterion("update_end_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateEndTimeIsNotNull() {
            addCriterion("update_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateEndTimeEqualTo(Date value) {
            addCriterion("update_end_time =", value, "updateEndTime");
            return (Criteria) this;
        }

        public Criteria andUpdateEndTimeNotEqualTo(Date value) {
            addCriterion("update_end_time <>", value, "updateEndTime");
            return (Criteria) this;
        }

        public Criteria andUpdateEndTimeGreaterThan(Date value) {
            addCriterion("update_end_time >", value, "updateEndTime");
            return (Criteria) this;
        }

        public Criteria andUpdateEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_end_time >=", value, "updateEndTime");
            return (Criteria) this;
        }

        public Criteria andUpdateEndTimeLessThan(Date value) {
            addCriterion("update_end_time <", value, "updateEndTime");
            return (Criteria) this;
        }

        public Criteria andUpdateEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_end_time <=", value, "updateEndTime");
            return (Criteria) this;
        }

        public Criteria andUpdateEndTimeIn(List<Date> values) {
            addCriterion("update_end_time in", values, "updateEndTime");
            return (Criteria) this;
        }

        public Criteria andUpdateEndTimeNotIn(List<Date> values) {
            addCriterion("update_end_time not in", values, "updateEndTime");
            return (Criteria) this;
        }

        public Criteria andUpdateEndTimeBetween(Date value1, Date value2) {
            addCriterion("update_end_time between", value1, value2, "updateEndTime");
            return (Criteria) this;
        }

        public Criteria andUpdateEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_end_time not between", value1, value2, "updateEndTime");
            return (Criteria) this;
        }

        public Criteria andBuildingOpenIsNull() {
            addCriterion("building_open is null");
            return (Criteria) this;
        }

        public Criteria andBuildingOpenIsNotNull() {
            addCriterion("building_open is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingOpenEqualTo(Integer value) {
            addCriterion("building_open =", value, "buildingOpen");
            return (Criteria) this;
        }

        public Criteria andBuildingOpenNotEqualTo(Integer value) {
            addCriterion("building_open <>", value, "buildingOpen");
            return (Criteria) this;
        }

        public Criteria andBuildingOpenGreaterThan(Integer value) {
            addCriterion("building_open >", value, "buildingOpen");
            return (Criteria) this;
        }

        public Criteria andBuildingOpenGreaterThanOrEqualTo(Integer value) {
            addCriterion("building_open >=", value, "buildingOpen");
            return (Criteria) this;
        }

        public Criteria andBuildingOpenLessThan(Integer value) {
            addCriterion("building_open <", value, "buildingOpen");
            return (Criteria) this;
        }

        public Criteria andBuildingOpenLessThanOrEqualTo(Integer value) {
            addCriterion("building_open <=", value, "buildingOpen");
            return (Criteria) this;
        }

        public Criteria andBuildingOpenIn(List<Integer> values) {
            addCriterion("building_open in", values, "buildingOpen");
            return (Criteria) this;
        }

        public Criteria andBuildingOpenNotIn(List<Integer> values) {
            addCriterion("building_open not in", values, "buildingOpen");
            return (Criteria) this;
        }

        public Criteria andBuildingOpenBetween(Integer value1, Integer value2) {
            addCriterion("building_open between", value1, value2, "buildingOpen");
            return (Criteria) this;
        }

        public Criteria andBuildingOpenNotBetween(Integer value1, Integer value2) {
            addCriterion("building_open not between", value1, value2, "buildingOpen");
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