package com.tongzhu.treehouse.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserWorkPositionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserWorkPositionExample() {
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

        public Criteria andWorkPositionIdIsNull() {
            addCriterion("work_position_id is null");
            return (Criteria) this;
        }

        public Criteria andWorkPositionIdIsNotNull() {
            addCriterion("work_position_id is not null");
            return (Criteria) this;
        }

        public Criteria andWorkPositionIdEqualTo(String value) {
            addCriterion("work_position_id =", value, "workPositionId");
            return (Criteria) this;
        }

        public Criteria andWorkPositionIdNotEqualTo(String value) {
            addCriterion("work_position_id <>", value, "workPositionId");
            return (Criteria) this;
        }

        public Criteria andWorkPositionIdGreaterThan(String value) {
            addCriterion("work_position_id >", value, "workPositionId");
            return (Criteria) this;
        }

        public Criteria andWorkPositionIdGreaterThanOrEqualTo(String value) {
            addCriterion("work_position_id >=", value, "workPositionId");
            return (Criteria) this;
        }

        public Criteria andWorkPositionIdLessThan(String value) {
            addCriterion("work_position_id <", value, "workPositionId");
            return (Criteria) this;
        }

        public Criteria andWorkPositionIdLessThanOrEqualTo(String value) {
            addCriterion("work_position_id <=", value, "workPositionId");
            return (Criteria) this;
        }

        public Criteria andWorkPositionIdLike(String value) {
            addCriterion("work_position_id like", value, "workPositionId");
            return (Criteria) this;
        }

        public Criteria andWorkPositionIdNotLike(String value) {
            addCriterion("work_position_id not like", value, "workPositionId");
            return (Criteria) this;
        }

        public Criteria andWorkPositionIdIn(List<String> values) {
            addCriterion("work_position_id in", values, "workPositionId");
            return (Criteria) this;
        }

        public Criteria andWorkPositionIdNotIn(List<String> values) {
            addCriterion("work_position_id not in", values, "workPositionId");
            return (Criteria) this;
        }

        public Criteria andWorkPositionIdBetween(String value1, String value2) {
            addCriterion("work_position_id between", value1, value2, "workPositionId");
            return (Criteria) this;
        }

        public Criteria andWorkPositionIdNotBetween(String value1, String value2) {
            addCriterion("work_position_id not between", value1, value2, "workPositionId");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIdIsNull() {
            addCriterion("work_type_id is null");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIdIsNotNull() {
            addCriterion("work_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIdEqualTo(Integer value) {
            addCriterion("work_type_id =", value, "workTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIdNotEqualTo(Integer value) {
            addCriterion("work_type_id <>", value, "workTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIdGreaterThan(Integer value) {
            addCriterion("work_type_id >", value, "workTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("work_type_id >=", value, "workTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIdLessThan(Integer value) {
            addCriterion("work_type_id <", value, "workTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("work_type_id <=", value, "workTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIdIn(List<Integer> values) {
            addCriterion("work_type_id in", values, "workTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIdNotIn(List<Integer> values) {
            addCriterion("work_type_id not in", values, "workTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("work_type_id between", value1, value2, "workTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("work_type_id not between", value1, value2, "workTypeId");
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

        public Criteria andWorkerIdIsNull() {
            addCriterion("worker_id is null");
            return (Criteria) this;
        }

        public Criteria andWorkerIdIsNotNull() {
            addCriterion("worker_id is not null");
            return (Criteria) this;
        }

        public Criteria andWorkerIdEqualTo(String value) {
            addCriterion("worker_id =", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdNotEqualTo(String value) {
            addCriterion("worker_id <>", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdGreaterThan(String value) {
            addCriterion("worker_id >", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdGreaterThanOrEqualTo(String value) {
            addCriterion("worker_id >=", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdLessThan(String value) {
            addCriterion("worker_id <", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdLessThanOrEqualTo(String value) {
            addCriterion("worker_id <=", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdLike(String value) {
            addCriterion("worker_id like", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdNotLike(String value) {
            addCriterion("worker_id not like", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdIn(List<String> values) {
            addCriterion("worker_id in", values, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdNotIn(List<String> values) {
            addCriterion("worker_id not in", values, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdBetween(String value1, String value2) {
            addCriterion("worker_id between", value1, value2, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdNotBetween(String value1, String value2) {
            addCriterion("worker_id not between", value1, value2, "workerId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status_ is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status_ is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status_ =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status_ <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status_ >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status_ >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status_ <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status_ <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status_ in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status_ not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status_ between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status_ not between", value1, value2, "status");
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

        public Criteria andStopDateIsNull() {
            addCriterion("stop_date is null");
            return (Criteria) this;
        }

        public Criteria andStopDateIsNotNull() {
            addCriterion("stop_date is not null");
            return (Criteria) this;
        }

        public Criteria andStopDateEqualTo(Date value) {
            addCriterion("stop_date =", value, "stopDate");
            return (Criteria) this;
        }

        public Criteria andStopDateNotEqualTo(Date value) {
            addCriterion("stop_date <>", value, "stopDate");
            return (Criteria) this;
        }

        public Criteria andStopDateGreaterThan(Date value) {
            addCriterion("stop_date >", value, "stopDate");
            return (Criteria) this;
        }

        public Criteria andStopDateGreaterThanOrEqualTo(Date value) {
            addCriterion("stop_date >=", value, "stopDate");
            return (Criteria) this;
        }

        public Criteria andStopDateLessThan(Date value) {
            addCriterion("stop_date <", value, "stopDate");
            return (Criteria) this;
        }

        public Criteria andStopDateLessThanOrEqualTo(Date value) {
            addCriterion("stop_date <=", value, "stopDate");
            return (Criteria) this;
        }

        public Criteria andStopDateIn(List<Date> values) {
            addCriterion("stop_date in", values, "stopDate");
            return (Criteria) this;
        }

        public Criteria andStopDateNotIn(List<Date> values) {
            addCriterion("stop_date not in", values, "stopDate");
            return (Criteria) this;
        }

        public Criteria andStopDateBetween(Date value1, Date value2) {
            addCriterion("stop_date between", value1, value2, "stopDate");
            return (Criteria) this;
        }

        public Criteria andStopDateNotBetween(Date value1, Date value2) {
            addCriterion("stop_date not between", value1, value2, "stopDate");
            return (Criteria) this;
        }

        public Criteria andLastTimeIsNull() {
            addCriterion("last_time is null");
            return (Criteria) this;
        }

        public Criteria andLastTimeIsNotNull() {
            addCriterion("last_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastTimeEqualTo(Integer value) {
            addCriterion("last_time =", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeNotEqualTo(Integer value) {
            addCriterion("last_time <>", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeGreaterThan(Integer value) {
            addCriterion("last_time >", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("last_time >=", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeLessThan(Integer value) {
            addCriterion("last_time <", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeLessThanOrEqualTo(Integer value) {
            addCriterion("last_time <=", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeIn(List<Integer> values) {
            addCriterion("last_time in", values, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeNotIn(List<Integer> values) {
            addCriterion("last_time not in", values, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeBetween(Integer value1, Integer value2) {
            addCriterion("last_time between", value1, value2, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("last_time not between", value1, value2, "lastTime");
            return (Criteria) this;
        }

        public Criteria andWorkStatusIsNull() {
            addCriterion("work_status is null");
            return (Criteria) this;
        }

        public Criteria andWorkStatusIsNotNull() {
            addCriterion("work_status is not null");
            return (Criteria) this;
        }

        public Criteria andWorkStatusEqualTo(Integer value) {
            addCriterion("work_status =", value, "workStatus");
            return (Criteria) this;
        }

        public Criteria andWorkStatusNotEqualTo(Integer value) {
            addCriterion("work_status <>", value, "workStatus");
            return (Criteria) this;
        }

        public Criteria andWorkStatusGreaterThan(Integer value) {
            addCriterion("work_status >", value, "workStatus");
            return (Criteria) this;
        }

        public Criteria andWorkStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("work_status >=", value, "workStatus");
            return (Criteria) this;
        }

        public Criteria andWorkStatusLessThan(Integer value) {
            addCriterion("work_status <", value, "workStatus");
            return (Criteria) this;
        }

        public Criteria andWorkStatusLessThanOrEqualTo(Integer value) {
            addCriterion("work_status <=", value, "workStatus");
            return (Criteria) this;
        }

        public Criteria andWorkStatusIn(List<Integer> values) {
            addCriterion("work_status in", values, "workStatus");
            return (Criteria) this;
        }

        public Criteria andWorkStatusNotIn(List<Integer> values) {
            addCriterion("work_status not in", values, "workStatus");
            return (Criteria) this;
        }

        public Criteria andWorkStatusBetween(Integer value1, Integer value2) {
            addCriterion("work_status between", value1, value2, "workStatus");
            return (Criteria) this;
        }

        public Criteria andWorkStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("work_status not between", value1, value2, "workStatus");
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