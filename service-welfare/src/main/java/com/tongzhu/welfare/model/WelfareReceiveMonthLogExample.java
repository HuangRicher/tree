package com.tongzhu.welfare.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WelfareReceiveMonthLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WelfareReceiveMonthLogExample() {
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

        public Criteria andRewardsTypeIsNull() {
            addCriterion("rewards_type is null");
            return (Criteria) this;
        }

        public Criteria andRewardsTypeIsNotNull() {
            addCriterion("rewards_type is not null");
            return (Criteria) this;
        }

        public Criteria andRewardsTypeEqualTo(Integer value) {
            addCriterion("rewards_type =", value, "rewardsType");
            return (Criteria) this;
        }

        public Criteria andRewardsTypeNotEqualTo(Integer value) {
            addCriterion("rewards_type <>", value, "rewardsType");
            return (Criteria) this;
        }

        public Criteria andRewardsTypeGreaterThan(Integer value) {
            addCriterion("rewards_type >", value, "rewardsType");
            return (Criteria) this;
        }

        public Criteria andRewardsTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("rewards_type >=", value, "rewardsType");
            return (Criteria) this;
        }

        public Criteria andRewardsTypeLessThan(Integer value) {
            addCriterion("rewards_type <", value, "rewardsType");
            return (Criteria) this;
        }

        public Criteria andRewardsTypeLessThanOrEqualTo(Integer value) {
            addCriterion("rewards_type <=", value, "rewardsType");
            return (Criteria) this;
        }

        public Criteria andRewardsTypeIn(List<Integer> values) {
            addCriterion("rewards_type in", values, "rewardsType");
            return (Criteria) this;
        }

        public Criteria andRewardsTypeNotIn(List<Integer> values) {
            addCriterion("rewards_type not in", values, "rewardsType");
            return (Criteria) this;
        }

        public Criteria andRewardsTypeBetween(Integer value1, Integer value2) {
            addCriterion("rewards_type between", value1, value2, "rewardsType");
            return (Criteria) this;
        }

        public Criteria andRewardsTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("rewards_type not between", value1, value2, "rewardsType");
            return (Criteria) this;
        }

        public Criteria andRewardsContentIsNull() {
            addCriterion("rewards_content is null");
            return (Criteria) this;
        }

        public Criteria andRewardsContentIsNotNull() {
            addCriterion("rewards_content is not null");
            return (Criteria) this;
        }

        public Criteria andRewardsContentEqualTo(String value) {
            addCriterion("rewards_content =", value, "rewardsContent");
            return (Criteria) this;
        }

        public Criteria andRewardsContentNotEqualTo(String value) {
            addCriterion("rewards_content <>", value, "rewardsContent");
            return (Criteria) this;
        }

        public Criteria andRewardsContentGreaterThan(String value) {
            addCriterion("rewards_content >", value, "rewardsContent");
            return (Criteria) this;
        }

        public Criteria andRewardsContentGreaterThanOrEqualTo(String value) {
            addCriterion("rewards_content >=", value, "rewardsContent");
            return (Criteria) this;
        }

        public Criteria andRewardsContentLessThan(String value) {
            addCriterion("rewards_content <", value, "rewardsContent");
            return (Criteria) this;
        }

        public Criteria andRewardsContentLessThanOrEqualTo(String value) {
            addCriterion("rewards_content <=", value, "rewardsContent");
            return (Criteria) this;
        }

        public Criteria andRewardsContentLike(String value) {
            addCriterion("rewards_content like", value, "rewardsContent");
            return (Criteria) this;
        }

        public Criteria andRewardsContentNotLike(String value) {
            addCriterion("rewards_content not like", value, "rewardsContent");
            return (Criteria) this;
        }

        public Criteria andRewardsContentIn(List<String> values) {
            addCriterion("rewards_content in", values, "rewardsContent");
            return (Criteria) this;
        }

        public Criteria andRewardsContentNotIn(List<String> values) {
            addCriterion("rewards_content not in", values, "rewardsContent");
            return (Criteria) this;
        }

        public Criteria andRewardsContentBetween(String value1, String value2) {
            addCriterion("rewards_content between", value1, value2, "rewardsContent");
            return (Criteria) this;
        }

        public Criteria andRewardsContentNotBetween(String value1, String value2) {
            addCriterion("rewards_content not between", value1, value2, "rewardsContent");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIsNull() {
            addCriterion("receive_time is null");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIsNotNull() {
            addCriterion("receive_time is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeEqualTo(Date value) {
            addCriterion("receive_time =", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotEqualTo(Date value) {
            addCriterion("receive_time <>", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeGreaterThan(Date value) {
            addCriterion("receive_time >", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("receive_time >=", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeLessThan(Date value) {
            addCriterion("receive_time <", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeLessThanOrEqualTo(Date value) {
            addCriterion("receive_time <=", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIn(List<Date> values) {
            addCriterion("receive_time in", values, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotIn(List<Date> values) {
            addCriterion("receive_time not in", values, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeBetween(Date value1, Date value2) {
            addCriterion("receive_time between", value1, value2, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotBetween(Date value1, Date value2) {
            addCriterion("receive_time not between", value1, value2, "receiveTime");
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

        public Criteria andLastTimeEqualTo(Date value) {
            addCriterion("last_time =", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeNotEqualTo(Date value) {
            addCriterion("last_time <>", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeGreaterThan(Date value) {
            addCriterion("last_time >", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_time >=", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeLessThan(Date value) {
            addCriterion("last_time <", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_time <=", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeIn(List<Date> values) {
            addCriterion("last_time in", values, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeNotIn(List<Date> values) {
            addCriterion("last_time not in", values, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeBetween(Date value1, Date value2) {
            addCriterion("last_time between", value1, value2, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_time not between", value1, value2, "lastTime");
            return (Criteria) this;
        }

        public Criteria andCountDayIsNull() {
            addCriterion("count_day is null");
            return (Criteria) this;
        }

        public Criteria andCountDayIsNotNull() {
            addCriterion("count_day is not null");
            return (Criteria) this;
        }

        public Criteria andCountDayEqualTo(Integer value) {
            addCriterion("count_day =", value, "countDay");
            return (Criteria) this;
        }

        public Criteria andCountDayNotEqualTo(Integer value) {
            addCriterion("count_day <>", value, "countDay");
            return (Criteria) this;
        }

        public Criteria andCountDayGreaterThan(Integer value) {
            addCriterion("count_day >", value, "countDay");
            return (Criteria) this;
        }

        public Criteria andCountDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("count_day >=", value, "countDay");
            return (Criteria) this;
        }

        public Criteria andCountDayLessThan(Integer value) {
            addCriterion("count_day <", value, "countDay");
            return (Criteria) this;
        }

        public Criteria andCountDayLessThanOrEqualTo(Integer value) {
            addCriterion("count_day <=", value, "countDay");
            return (Criteria) this;
        }

        public Criteria andCountDayIn(List<Integer> values) {
            addCriterion("count_day in", values, "countDay");
            return (Criteria) this;
        }

        public Criteria andCountDayNotIn(List<Integer> values) {
            addCriterion("count_day not in", values, "countDay");
            return (Criteria) this;
        }

        public Criteria andCountDayBetween(Integer value1, Integer value2) {
            addCriterion("count_day between", value1, value2, "countDay");
            return (Criteria) this;
        }

        public Criteria andCountDayNotBetween(Integer value1, Integer value2) {
            addCriterion("count_day not between", value1, value2, "countDay");
            return (Criteria) this;
        }

        public Criteria andRewardsDayIsNull() {
            addCriterion("rewards_day is null");
            return (Criteria) this;
        }

        public Criteria andRewardsDayIsNotNull() {
            addCriterion("rewards_day is not null");
            return (Criteria) this;
        }

        public Criteria andRewardsDayEqualTo(Integer value) {
            addCriterion("rewards_day =", value, "rewardsDay");
            return (Criteria) this;
        }

        public Criteria andRewardsDayNotEqualTo(Integer value) {
            addCriterion("rewards_day <>", value, "rewardsDay");
            return (Criteria) this;
        }

        public Criteria andRewardsDayGreaterThan(Integer value) {
            addCriterion("rewards_day >", value, "rewardsDay");
            return (Criteria) this;
        }

        public Criteria andRewardsDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("rewards_day >=", value, "rewardsDay");
            return (Criteria) this;
        }

        public Criteria andRewardsDayLessThan(Integer value) {
            addCriterion("rewards_day <", value, "rewardsDay");
            return (Criteria) this;
        }

        public Criteria andRewardsDayLessThanOrEqualTo(Integer value) {
            addCriterion("rewards_day <=", value, "rewardsDay");
            return (Criteria) this;
        }

        public Criteria andRewardsDayIn(List<Integer> values) {
            addCriterion("rewards_day in", values, "rewardsDay");
            return (Criteria) this;
        }

        public Criteria andRewardsDayNotIn(List<Integer> values) {
            addCriterion("rewards_day not in", values, "rewardsDay");
            return (Criteria) this;
        }

        public Criteria andRewardsDayBetween(Integer value1, Integer value2) {
            addCriterion("rewards_day between", value1, value2, "rewardsDay");
            return (Criteria) this;
        }

        public Criteria andRewardsDayNotBetween(Integer value1, Integer value2) {
            addCriterion("rewards_day not between", value1, value2, "rewardsDay");
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