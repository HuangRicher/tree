package com.tongzhu.fishing.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserActivityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserActivityExample() {
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

        public Criteria andActivitiesNameIsNull() {
            addCriterion("activities_name is null");
            return (Criteria) this;
        }

        public Criteria andActivitiesNameIsNotNull() {
            addCriterion("activities_name is not null");
            return (Criteria) this;
        }

        public Criteria andActivitiesNameEqualTo(String value) {
            addCriterion("activities_name =", value, "activitiesName");
            return (Criteria) this;
        }

        public Criteria andActivitiesNameNotEqualTo(String value) {
            addCriterion("activities_name <>", value, "activitiesName");
            return (Criteria) this;
        }

        public Criteria andActivitiesNameGreaterThan(String value) {
            addCriterion("activities_name >", value, "activitiesName");
            return (Criteria) this;
        }

        public Criteria andActivitiesNameGreaterThanOrEqualTo(String value) {
            addCriterion("activities_name >=", value, "activitiesName");
            return (Criteria) this;
        }

        public Criteria andActivitiesNameLessThan(String value) {
            addCriterion("activities_name <", value, "activitiesName");
            return (Criteria) this;
        }

        public Criteria andActivitiesNameLessThanOrEqualTo(String value) {
            addCriterion("activities_name <=", value, "activitiesName");
            return (Criteria) this;
        }

        public Criteria andActivitiesNameLike(String value) {
            addCriterion("activities_name like", value, "activitiesName");
            return (Criteria) this;
        }

        public Criteria andActivitiesNameNotLike(String value) {
            addCriterion("activities_name not like", value, "activitiesName");
            return (Criteria) this;
        }

        public Criteria andActivitiesNameIn(List<String> values) {
            addCriterion("activities_name in", values, "activitiesName");
            return (Criteria) this;
        }

        public Criteria andActivitiesNameNotIn(List<String> values) {
            addCriterion("activities_name not in", values, "activitiesName");
            return (Criteria) this;
        }

        public Criteria andActivitiesNameBetween(String value1, String value2) {
            addCriterion("activities_name between", value1, value2, "activitiesName");
            return (Criteria) this;
        }

        public Criteria andActivitiesNameNotBetween(String value1, String value2) {
            addCriterion("activities_name not between", value1, value2, "activitiesName");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andActivitiesPictureIsNull() {
            addCriterion("activities_picture is null");
            return (Criteria) this;
        }

        public Criteria andActivitiesPictureIsNotNull() {
            addCriterion("activities_picture is not null");
            return (Criteria) this;
        }

        public Criteria andActivitiesPictureEqualTo(String value) {
            addCriterion("activities_picture =", value, "activitiesPicture");
            return (Criteria) this;
        }

        public Criteria andActivitiesPictureNotEqualTo(String value) {
            addCriterion("activities_picture <>", value, "activitiesPicture");
            return (Criteria) this;
        }

        public Criteria andActivitiesPictureGreaterThan(String value) {
            addCriterion("activities_picture >", value, "activitiesPicture");
            return (Criteria) this;
        }

        public Criteria andActivitiesPictureGreaterThanOrEqualTo(String value) {
            addCriterion("activities_picture >=", value, "activitiesPicture");
            return (Criteria) this;
        }

        public Criteria andActivitiesPictureLessThan(String value) {
            addCriterion("activities_picture <", value, "activitiesPicture");
            return (Criteria) this;
        }

        public Criteria andActivitiesPictureLessThanOrEqualTo(String value) {
            addCriterion("activities_picture <=", value, "activitiesPicture");
            return (Criteria) this;
        }

        public Criteria andActivitiesPictureLike(String value) {
            addCriterion("activities_picture like", value, "activitiesPicture");
            return (Criteria) this;
        }

        public Criteria andActivitiesPictureNotLike(String value) {
            addCriterion("activities_picture not like", value, "activitiesPicture");
            return (Criteria) this;
        }

        public Criteria andActivitiesPictureIn(List<String> values) {
            addCriterion("activities_picture in", values, "activitiesPicture");
            return (Criteria) this;
        }

        public Criteria andActivitiesPictureNotIn(List<String> values) {
            addCriterion("activities_picture not in", values, "activitiesPicture");
            return (Criteria) this;
        }

        public Criteria andActivitiesPictureBetween(String value1, String value2) {
            addCriterion("activities_picture between", value1, value2, "activitiesPicture");
            return (Criteria) this;
        }

        public Criteria andActivitiesPictureNotBetween(String value1, String value2) {
            addCriterion("activities_picture not between", value1, value2, "activitiesPicture");
            return (Criteria) this;
        }

        public Criteria andActivitiesIntroductionIsNull() {
            addCriterion("activities_introduction is null");
            return (Criteria) this;
        }

        public Criteria andActivitiesIntroductionIsNotNull() {
            addCriterion("activities_introduction is not null");
            return (Criteria) this;
        }

        public Criteria andActivitiesIntroductionEqualTo(String value) {
            addCriterion("activities_introduction =", value, "activitiesIntroduction");
            return (Criteria) this;
        }

        public Criteria andActivitiesIntroductionNotEqualTo(String value) {
            addCriterion("activities_introduction <>", value, "activitiesIntroduction");
            return (Criteria) this;
        }

        public Criteria andActivitiesIntroductionGreaterThan(String value) {
            addCriterion("activities_introduction >", value, "activitiesIntroduction");
            return (Criteria) this;
        }

        public Criteria andActivitiesIntroductionGreaterThanOrEqualTo(String value) {
            addCriterion("activities_introduction >=", value, "activitiesIntroduction");
            return (Criteria) this;
        }

        public Criteria andActivitiesIntroductionLessThan(String value) {
            addCriterion("activities_introduction <", value, "activitiesIntroduction");
            return (Criteria) this;
        }

        public Criteria andActivitiesIntroductionLessThanOrEqualTo(String value) {
            addCriterion("activities_introduction <=", value, "activitiesIntroduction");
            return (Criteria) this;
        }

        public Criteria andActivitiesIntroductionLike(String value) {
            addCriterion("activities_introduction like", value, "activitiesIntroduction");
            return (Criteria) this;
        }

        public Criteria andActivitiesIntroductionNotLike(String value) {
            addCriterion("activities_introduction not like", value, "activitiesIntroduction");
            return (Criteria) this;
        }

        public Criteria andActivitiesIntroductionIn(List<String> values) {
            addCriterion("activities_introduction in", values, "activitiesIntroduction");
            return (Criteria) this;
        }

        public Criteria andActivitiesIntroductionNotIn(List<String> values) {
            addCriterion("activities_introduction not in", values, "activitiesIntroduction");
            return (Criteria) this;
        }

        public Criteria andActivitiesIntroductionBetween(String value1, String value2) {
            addCriterion("activities_introduction between", value1, value2, "activitiesIntroduction");
            return (Criteria) this;
        }

        public Criteria andActivitiesIntroductionNotBetween(String value1, String value2) {
            addCriterion("activities_introduction not between", value1, value2, "activitiesIntroduction");
            return (Criteria) this;
        }

        public Criteria andFallingPositionIsNull() {
            addCriterion("falling_position is null");
            return (Criteria) this;
        }

        public Criteria andFallingPositionIsNotNull() {
            addCriterion("falling_position is not null");
            return (Criteria) this;
        }

        public Criteria andFallingPositionEqualTo(String value) {
            addCriterion("falling_position =", value, "fallingPosition");
            return (Criteria) this;
        }

        public Criteria andFallingPositionNotEqualTo(String value) {
            addCriterion("falling_position <>", value, "fallingPosition");
            return (Criteria) this;
        }

        public Criteria andFallingPositionGreaterThan(String value) {
            addCriterion("falling_position >", value, "fallingPosition");
            return (Criteria) this;
        }

        public Criteria andFallingPositionGreaterThanOrEqualTo(String value) {
            addCriterion("falling_position >=", value, "fallingPosition");
            return (Criteria) this;
        }

        public Criteria andFallingPositionLessThan(String value) {
            addCriterion("falling_position <", value, "fallingPosition");
            return (Criteria) this;
        }

        public Criteria andFallingPositionLessThanOrEqualTo(String value) {
            addCriterion("falling_position <=", value, "fallingPosition");
            return (Criteria) this;
        }

        public Criteria andFallingPositionLike(String value) {
            addCriterion("falling_position like", value, "fallingPosition");
            return (Criteria) this;
        }

        public Criteria andFallingPositionNotLike(String value) {
            addCriterion("falling_position not like", value, "fallingPosition");
            return (Criteria) this;
        }

        public Criteria andFallingPositionIn(List<String> values) {
            addCriterion("falling_position in", values, "fallingPosition");
            return (Criteria) this;
        }

        public Criteria andFallingPositionNotIn(List<String> values) {
            addCriterion("falling_position not in", values, "fallingPosition");
            return (Criteria) this;
        }

        public Criteria andFallingPositionBetween(String value1, String value2) {
            addCriterion("falling_position between", value1, value2, "fallingPosition");
            return (Criteria) this;
        }

        public Criteria andFallingPositionNotBetween(String value1, String value2) {
            addCriterion("falling_position not between", value1, value2, "fallingPosition");
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