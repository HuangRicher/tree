package com.tongzhu.fishing.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserActivityPropsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserActivityPropsExample() {
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

        public Criteria andActivityIdIsNull() {
            addCriterion("activity_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNotNull() {
            addCriterion("activity_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityIdEqualTo(Integer value) {
            addCriterion("activity_id =", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotEqualTo(Integer value) {
            addCriterion("activity_id <>", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThan(Integer value) {
            addCriterion("activity_id >", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("activity_id >=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThan(Integer value) {
            addCriterion("activity_id <", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThanOrEqualTo(Integer value) {
            addCriterion("activity_id <=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdIn(List<Integer> values) {
            addCriterion("activity_id in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotIn(List<Integer> values) {
            addCriterion("activity_id not in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdBetween(Integer value1, Integer value2) {
            addCriterion("activity_id between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("activity_id not between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andGamePropsNameIsNull() {
            addCriterion("game_props_name is null");
            return (Criteria) this;
        }

        public Criteria andGamePropsNameIsNotNull() {
            addCriterion("game_props_name is not null");
            return (Criteria) this;
        }

        public Criteria andGamePropsNameEqualTo(String value) {
            addCriterion("game_props_name =", value, "gamePropsName");
            return (Criteria) this;
        }

        public Criteria andGamePropsNameNotEqualTo(String value) {
            addCriterion("game_props_name <>", value, "gamePropsName");
            return (Criteria) this;
        }

        public Criteria andGamePropsNameGreaterThan(String value) {
            addCriterion("game_props_name >", value, "gamePropsName");
            return (Criteria) this;
        }

        public Criteria andGamePropsNameGreaterThanOrEqualTo(String value) {
            addCriterion("game_props_name >=", value, "gamePropsName");
            return (Criteria) this;
        }

        public Criteria andGamePropsNameLessThan(String value) {
            addCriterion("game_props_name <", value, "gamePropsName");
            return (Criteria) this;
        }

        public Criteria andGamePropsNameLessThanOrEqualTo(String value) {
            addCriterion("game_props_name <=", value, "gamePropsName");
            return (Criteria) this;
        }

        public Criteria andGamePropsNameLike(String value) {
            addCriterion("game_props_name like", value, "gamePropsName");
            return (Criteria) this;
        }

        public Criteria andGamePropsNameNotLike(String value) {
            addCriterion("game_props_name not like", value, "gamePropsName");
            return (Criteria) this;
        }

        public Criteria andGamePropsNameIn(List<String> values) {
            addCriterion("game_props_name in", values, "gamePropsName");
            return (Criteria) this;
        }

        public Criteria andGamePropsNameNotIn(List<String> values) {
            addCriterion("game_props_name not in", values, "gamePropsName");
            return (Criteria) this;
        }

        public Criteria andGamePropsNameBetween(String value1, String value2) {
            addCriterion("game_props_name between", value1, value2, "gamePropsName");
            return (Criteria) this;
        }

        public Criteria andGamePropsNameNotBetween(String value1, String value2) {
            addCriterion("game_props_name not between", value1, value2, "gamePropsName");
            return (Criteria) this;
        }

        public Criteria andDropProbabilityIsNull() {
            addCriterion("drop_probability is null");
            return (Criteria) this;
        }

        public Criteria andDropProbabilityIsNotNull() {
            addCriterion("drop_probability is not null");
            return (Criteria) this;
        }

        public Criteria andDropProbabilityEqualTo(Integer value) {
            addCriterion("drop_probability =", value, "dropProbability");
            return (Criteria) this;
        }

        public Criteria andDropProbabilityNotEqualTo(Integer value) {
            addCriterion("drop_probability <>", value, "dropProbability");
            return (Criteria) this;
        }

        public Criteria andDropProbabilityGreaterThan(Integer value) {
            addCriterion("drop_probability >", value, "dropProbability");
            return (Criteria) this;
        }

        public Criteria andDropProbabilityGreaterThanOrEqualTo(Integer value) {
            addCriterion("drop_probability >=", value, "dropProbability");
            return (Criteria) this;
        }

        public Criteria andDropProbabilityLessThan(Integer value) {
            addCriterion("drop_probability <", value, "dropProbability");
            return (Criteria) this;
        }

        public Criteria andDropProbabilityLessThanOrEqualTo(Integer value) {
            addCriterion("drop_probability <=", value, "dropProbability");
            return (Criteria) this;
        }

        public Criteria andDropProbabilityIn(List<Integer> values) {
            addCriterion("drop_probability in", values, "dropProbability");
            return (Criteria) this;
        }

        public Criteria andDropProbabilityNotIn(List<Integer> values) {
            addCriterion("drop_probability not in", values, "dropProbability");
            return (Criteria) this;
        }

        public Criteria andDropProbabilityBetween(Integer value1, Integer value2) {
            addCriterion("drop_probability between", value1, value2, "dropProbability");
            return (Criteria) this;
        }

        public Criteria andDropProbabilityNotBetween(Integer value1, Integer value2) {
            addCriterion("drop_probability not between", value1, value2, "dropProbability");
            return (Criteria) this;
        }

        public Criteria andIntroduceIsNull() {
            addCriterion("introduce is null");
            return (Criteria) this;
        }

        public Criteria andIntroduceIsNotNull() {
            addCriterion("introduce is not null");
            return (Criteria) this;
        }

        public Criteria andIntroduceEqualTo(String value) {
            addCriterion("introduce =", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotEqualTo(String value) {
            addCriterion("introduce <>", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThan(String value) {
            addCriterion("introduce >", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("introduce >=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThan(String value) {
            addCriterion("introduce <", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThanOrEqualTo(String value) {
            addCriterion("introduce <=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLike(String value) {
            addCriterion("introduce like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotLike(String value) {
            addCriterion("introduce not like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceIn(List<String> values) {
            addCriterion("introduce in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotIn(List<String> values) {
            addCriterion("introduce not in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceBetween(String value1, String value2) {
            addCriterion("introduce between", value1, value2, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotBetween(String value1, String value2) {
            addCriterion("introduce not between", value1, value2, "introduce");
            return (Criteria) this;
        }

        public Criteria andGamePropsPictureIsNull() {
            addCriterion("game_props_picture is null");
            return (Criteria) this;
        }

        public Criteria andGamePropsPictureIsNotNull() {
            addCriterion("game_props_picture is not null");
            return (Criteria) this;
        }

        public Criteria andGamePropsPictureEqualTo(String value) {
            addCriterion("game_props_picture =", value, "gamePropsPicture");
            return (Criteria) this;
        }

        public Criteria andGamePropsPictureNotEqualTo(String value) {
            addCriterion("game_props_picture <>", value, "gamePropsPicture");
            return (Criteria) this;
        }

        public Criteria andGamePropsPictureGreaterThan(String value) {
            addCriterion("game_props_picture >", value, "gamePropsPicture");
            return (Criteria) this;
        }

        public Criteria andGamePropsPictureGreaterThanOrEqualTo(String value) {
            addCriterion("game_props_picture >=", value, "gamePropsPicture");
            return (Criteria) this;
        }

        public Criteria andGamePropsPictureLessThan(String value) {
            addCriterion("game_props_picture <", value, "gamePropsPicture");
            return (Criteria) this;
        }

        public Criteria andGamePropsPictureLessThanOrEqualTo(String value) {
            addCriterion("game_props_picture <=", value, "gamePropsPicture");
            return (Criteria) this;
        }

        public Criteria andGamePropsPictureLike(String value) {
            addCriterion("game_props_picture like", value, "gamePropsPicture");
            return (Criteria) this;
        }

        public Criteria andGamePropsPictureNotLike(String value) {
            addCriterion("game_props_picture not like", value, "gamePropsPicture");
            return (Criteria) this;
        }

        public Criteria andGamePropsPictureIn(List<String> values) {
            addCriterion("game_props_picture in", values, "gamePropsPicture");
            return (Criteria) this;
        }

        public Criteria andGamePropsPictureNotIn(List<String> values) {
            addCriterion("game_props_picture not in", values, "gamePropsPicture");
            return (Criteria) this;
        }

        public Criteria andGamePropsPictureBetween(String value1, String value2) {
            addCriterion("game_props_picture between", value1, value2, "gamePropsPicture");
            return (Criteria) this;
        }

        public Criteria andGamePropsPictureNotBetween(String value1, String value2) {
            addCriterion("game_props_picture not between", value1, value2, "gamePropsPicture");
            return (Criteria) this;
        }

        public Criteria andCreationStartDateIsNull() {
            addCriterion("creation_start_date is null");
            return (Criteria) this;
        }

        public Criteria andCreationStartDateIsNotNull() {
            addCriterion("creation_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreationStartDateEqualTo(Date value) {
            addCriterion("creation_start_date =", value, "creationStartDate");
            return (Criteria) this;
        }

        public Criteria andCreationStartDateNotEqualTo(Date value) {
            addCriterion("creation_start_date <>", value, "creationStartDate");
            return (Criteria) this;
        }

        public Criteria andCreationStartDateGreaterThan(Date value) {
            addCriterion("creation_start_date >", value, "creationStartDate");
            return (Criteria) this;
        }

        public Criteria andCreationStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("creation_start_date >=", value, "creationStartDate");
            return (Criteria) this;
        }

        public Criteria andCreationStartDateLessThan(Date value) {
            addCriterion("creation_start_date <", value, "creationStartDate");
            return (Criteria) this;
        }

        public Criteria andCreationStartDateLessThanOrEqualTo(Date value) {
            addCriterion("creation_start_date <=", value, "creationStartDate");
            return (Criteria) this;
        }

        public Criteria andCreationStartDateIn(List<Date> values) {
            addCriterion("creation_start_date in", values, "creationStartDate");
            return (Criteria) this;
        }

        public Criteria andCreationStartDateNotIn(List<Date> values) {
            addCriterion("creation_start_date not in", values, "creationStartDate");
            return (Criteria) this;
        }

        public Criteria andCreationStartDateBetween(Date value1, Date value2) {
            addCriterion("creation_start_date between", value1, value2, "creationStartDate");
            return (Criteria) this;
        }

        public Criteria andCreationStartDateNotBetween(Date value1, Date value2) {
            addCriterion("creation_start_date not between", value1, value2, "creationStartDate");
            return (Criteria) this;
        }

        public Criteria andRecomposeEndDateIsNull() {
            addCriterion("recompose_end_date is null");
            return (Criteria) this;
        }

        public Criteria andRecomposeEndDateIsNotNull() {
            addCriterion("recompose_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andRecomposeEndDateEqualTo(Date value) {
            addCriterion("recompose_end_date =", value, "recomposeEndDate");
            return (Criteria) this;
        }

        public Criteria andRecomposeEndDateNotEqualTo(Date value) {
            addCriterion("recompose_end_date <>", value, "recomposeEndDate");
            return (Criteria) this;
        }

        public Criteria andRecomposeEndDateGreaterThan(Date value) {
            addCriterion("recompose_end_date >", value, "recomposeEndDate");
            return (Criteria) this;
        }

        public Criteria andRecomposeEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("recompose_end_date >=", value, "recomposeEndDate");
            return (Criteria) this;
        }

        public Criteria andRecomposeEndDateLessThan(Date value) {
            addCriterion("recompose_end_date <", value, "recomposeEndDate");
            return (Criteria) this;
        }

        public Criteria andRecomposeEndDateLessThanOrEqualTo(Date value) {
            addCriterion("recompose_end_date <=", value, "recomposeEndDate");
            return (Criteria) this;
        }

        public Criteria andRecomposeEndDateIn(List<Date> values) {
            addCriterion("recompose_end_date in", values, "recomposeEndDate");
            return (Criteria) this;
        }

        public Criteria andRecomposeEndDateNotIn(List<Date> values) {
            addCriterion("recompose_end_date not in", values, "recomposeEndDate");
            return (Criteria) this;
        }

        public Criteria andRecomposeEndDateBetween(Date value1, Date value2) {
            addCriterion("recompose_end_date between", value1, value2, "recomposeEndDate");
            return (Criteria) this;
        }

        public Criteria andRecomposeEndDateNotBetween(Date value1, Date value2) {
            addCriterion("recompose_end_date not between", value1, value2, "recomposeEndDate");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCommonFisheryProbabilityIsNull() {
            addCriterion("common_fishery_probability is null");
            return (Criteria) this;
        }

        public Criteria andCommonFisheryProbabilityIsNotNull() {
            addCriterion("common_fishery_probability is not null");
            return (Criteria) this;
        }

        public Criteria andCommonFisheryProbabilityEqualTo(Integer value) {
            addCriterion("common_fishery_probability =", value, "commonFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andCommonFisheryProbabilityNotEqualTo(Integer value) {
            addCriterion("common_fishery_probability <>", value, "commonFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andCommonFisheryProbabilityGreaterThan(Integer value) {
            addCriterion("common_fishery_probability >", value, "commonFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andCommonFisheryProbabilityGreaterThanOrEqualTo(Integer value) {
            addCriterion("common_fishery_probability >=", value, "commonFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andCommonFisheryProbabilityLessThan(Integer value) {
            addCriterion("common_fishery_probability <", value, "commonFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andCommonFisheryProbabilityLessThanOrEqualTo(Integer value) {
            addCriterion("common_fishery_probability <=", value, "commonFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andCommonFisheryProbabilityIn(List<Integer> values) {
            addCriterion("common_fishery_probability in", values, "commonFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andCommonFisheryProbabilityNotIn(List<Integer> values) {
            addCriterion("common_fishery_probability not in", values, "commonFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andCommonFisheryProbabilityBetween(Integer value1, Integer value2) {
            addCriterion("common_fishery_probability between", value1, value2, "commonFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andCommonFisheryProbabilityNotBetween(Integer value1, Integer value2) {
            addCriterion("common_fishery_probability not between", value1, value2, "commonFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andSeniorFisheryProbabilityIsNull() {
            addCriterion("senior_fishery_probability is null");
            return (Criteria) this;
        }

        public Criteria andSeniorFisheryProbabilityIsNotNull() {
            addCriterion("senior_fishery_probability is not null");
            return (Criteria) this;
        }

        public Criteria andSeniorFisheryProbabilityEqualTo(Integer value) {
            addCriterion("senior_fishery_probability =", value, "seniorFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andSeniorFisheryProbabilityNotEqualTo(Integer value) {
            addCriterion("senior_fishery_probability <>", value, "seniorFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andSeniorFisheryProbabilityGreaterThan(Integer value) {
            addCriterion("senior_fishery_probability >", value, "seniorFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andSeniorFisheryProbabilityGreaterThanOrEqualTo(Integer value) {
            addCriterion("senior_fishery_probability >=", value, "seniorFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andSeniorFisheryProbabilityLessThan(Integer value) {
            addCriterion("senior_fishery_probability <", value, "seniorFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andSeniorFisheryProbabilityLessThanOrEqualTo(Integer value) {
            addCriterion("senior_fishery_probability <=", value, "seniorFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andSeniorFisheryProbabilityIn(List<Integer> values) {
            addCriterion("senior_fishery_probability in", values, "seniorFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andSeniorFisheryProbabilityNotIn(List<Integer> values) {
            addCriterion("senior_fishery_probability not in", values, "seniorFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andSeniorFisheryProbabilityBetween(Integer value1, Integer value2) {
            addCriterion("senior_fishery_probability between", value1, value2, "seniorFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andSeniorFisheryProbabilityNotBetween(Integer value1, Integer value2) {
            addCriterion("senior_fishery_probability not between", value1, value2, "seniorFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andRareFisheryProbabilityIsNull() {
            addCriterion("rare_fishery_probability is null");
            return (Criteria) this;
        }

        public Criteria andRareFisheryProbabilityIsNotNull() {
            addCriterion("rare_fishery_probability is not null");
            return (Criteria) this;
        }

        public Criteria andRareFisheryProbabilityEqualTo(Integer value) {
            addCriterion("rare_fishery_probability =", value, "rareFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andRareFisheryProbabilityNotEqualTo(Integer value) {
            addCriterion("rare_fishery_probability <>", value, "rareFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andRareFisheryProbabilityGreaterThan(Integer value) {
            addCriterion("rare_fishery_probability >", value, "rareFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andRareFisheryProbabilityGreaterThanOrEqualTo(Integer value) {
            addCriterion("rare_fishery_probability >=", value, "rareFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andRareFisheryProbabilityLessThan(Integer value) {
            addCriterion("rare_fishery_probability <", value, "rareFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andRareFisheryProbabilityLessThanOrEqualTo(Integer value) {
            addCriterion("rare_fishery_probability <=", value, "rareFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andRareFisheryProbabilityIn(List<Integer> values) {
            addCriterion("rare_fishery_probability in", values, "rareFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andRareFisheryProbabilityNotIn(List<Integer> values) {
            addCriterion("rare_fishery_probability not in", values, "rareFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andRareFisheryProbabilityBetween(Integer value1, Integer value2) {
            addCriterion("rare_fishery_probability between", value1, value2, "rareFisheryProbability");
            return (Criteria) this;
        }

        public Criteria andRareFisheryProbabilityNotBetween(Integer value1, Integer value2) {
            addCriterion("rare_fishery_probability not between", value1, value2, "rareFisheryProbability");
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