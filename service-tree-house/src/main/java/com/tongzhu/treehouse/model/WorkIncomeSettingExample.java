package com.tongzhu.treehouse.model;

import java.util.ArrayList;
import java.util.List;

public class WorkIncomeSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WorkIncomeSettingExample() {
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

        public Criteria andPositionIdIsNull() {
            addCriterion("position_id is null");
            return (Criteria) this;
        }

        public Criteria andPositionIdIsNotNull() {
            addCriterion("position_id is not null");
            return (Criteria) this;
        }

        public Criteria andPositionIdEqualTo(Integer value) {
            addCriterion("position_id =", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdNotEqualTo(Integer value) {
            addCriterion("position_id <>", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdGreaterThan(Integer value) {
            addCriterion("position_id >", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("position_id >=", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdLessThan(Integer value) {
            addCriterion("position_id <", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdLessThanOrEqualTo(Integer value) {
            addCriterion("position_id <=", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdIn(List<Integer> values) {
            addCriterion("position_id in", values, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdNotIn(List<Integer> values) {
            addCriterion("position_id not in", values, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdBetween(Integer value1, Integer value2) {
            addCriterion("position_id between", value1, value2, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("position_id not between", value1, value2, "positionId");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLevelIsNull() {
            addCriterion("work_type_level is null");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLevelIsNotNull() {
            addCriterion("work_type_level is not null");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLevelEqualTo(Integer value) {
            addCriterion("work_type_level =", value, "workTypeLevel");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLevelNotEqualTo(Integer value) {
            addCriterion("work_type_level <>", value, "workTypeLevel");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLevelGreaterThan(Integer value) {
            addCriterion("work_type_level >", value, "workTypeLevel");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("work_type_level >=", value, "workTypeLevel");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLevelLessThan(Integer value) {
            addCriterion("work_type_level <", value, "workTypeLevel");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLevelLessThanOrEqualTo(Integer value) {
            addCriterion("work_type_level <=", value, "workTypeLevel");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLevelIn(List<Integer> values) {
            addCriterion("work_type_level in", values, "workTypeLevel");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLevelNotIn(List<Integer> values) {
            addCriterion("work_type_level not in", values, "workTypeLevel");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLevelBetween(Integer value1, Integer value2) {
            addCriterion("work_type_level between", value1, value2, "workTypeLevel");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("work_type_level not between", value1, value2, "workTypeLevel");
            return (Criteria) this;
        }

        public Criteria andIncomeGoodsIsNull() {
            addCriterion("income_goods is null");
            return (Criteria) this;
        }

        public Criteria andIncomeGoodsIsNotNull() {
            addCriterion("income_goods is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeGoodsEqualTo(String value) {
            addCriterion("income_goods =", value, "incomeGoods");
            return (Criteria) this;
        }

        public Criteria andIncomeGoodsNotEqualTo(String value) {
            addCriterion("income_goods <>", value, "incomeGoods");
            return (Criteria) this;
        }

        public Criteria andIncomeGoodsGreaterThan(String value) {
            addCriterion("income_goods >", value, "incomeGoods");
            return (Criteria) this;
        }

        public Criteria andIncomeGoodsGreaterThanOrEqualTo(String value) {
            addCriterion("income_goods >=", value, "incomeGoods");
            return (Criteria) this;
        }

        public Criteria andIncomeGoodsLessThan(String value) {
            addCriterion("income_goods <", value, "incomeGoods");
            return (Criteria) this;
        }

        public Criteria andIncomeGoodsLessThanOrEqualTo(String value) {
            addCriterion("income_goods <=", value, "incomeGoods");
            return (Criteria) this;
        }

        public Criteria andIncomeGoodsLike(String value) {
            addCriterion("income_goods like", value, "incomeGoods");
            return (Criteria) this;
        }

        public Criteria andIncomeGoodsNotLike(String value) {
            addCriterion("income_goods not like", value, "incomeGoods");
            return (Criteria) this;
        }

        public Criteria andIncomeGoodsIn(List<String> values) {
            addCriterion("income_goods in", values, "incomeGoods");
            return (Criteria) this;
        }

        public Criteria andIncomeGoodsNotIn(List<String> values) {
            addCriterion("income_goods not in", values, "incomeGoods");
            return (Criteria) this;
        }

        public Criteria andIncomeGoodsBetween(String value1, String value2) {
            addCriterion("income_goods between", value1, value2, "incomeGoods");
            return (Criteria) this;
        }

        public Criteria andIncomeGoodsNotBetween(String value1, String value2) {
            addCriterion("income_goods not between", value1, value2, "incomeGoods");
            return (Criteria) this;
        }

        public Criteria andGameFriendMoreCountIsNull() {
            addCriterion("game_friend_more_count is null");
            return (Criteria) this;
        }

        public Criteria andGameFriendMoreCountIsNotNull() {
            addCriterion("game_friend_more_count is not null");
            return (Criteria) this;
        }

        public Criteria andGameFriendMoreCountEqualTo(Float value) {
            addCriterion("game_friend_more_count =", value, "gameFriendMoreCount");
            return (Criteria) this;
        }

        public Criteria andGameFriendMoreCountNotEqualTo(Float value) {
            addCriterion("game_friend_more_count <>", value, "gameFriendMoreCount");
            return (Criteria) this;
        }

        public Criteria andGameFriendMoreCountGreaterThan(Float value) {
            addCriterion("game_friend_more_count >", value, "gameFriendMoreCount");
            return (Criteria) this;
        }

        public Criteria andGameFriendMoreCountGreaterThanOrEqualTo(Float value) {
            addCriterion("game_friend_more_count >=", value, "gameFriendMoreCount");
            return (Criteria) this;
        }

        public Criteria andGameFriendMoreCountLessThan(Float value) {
            addCriterion("game_friend_more_count <", value, "gameFriendMoreCount");
            return (Criteria) this;
        }

        public Criteria andGameFriendMoreCountLessThanOrEqualTo(Float value) {
            addCriterion("game_friend_more_count <=", value, "gameFriendMoreCount");
            return (Criteria) this;
        }

        public Criteria andGameFriendMoreCountIn(List<Float> values) {
            addCriterion("game_friend_more_count in", values, "gameFriendMoreCount");
            return (Criteria) this;
        }

        public Criteria andGameFriendMoreCountNotIn(List<Float> values) {
            addCriterion("game_friend_more_count not in", values, "gameFriendMoreCount");
            return (Criteria) this;
        }

        public Criteria andGameFriendMoreCountBetween(Float value1, Float value2) {
            addCriterion("game_friend_more_count between", value1, value2, "gameFriendMoreCount");
            return (Criteria) this;
        }

        public Criteria andGameFriendMoreCountNotBetween(Float value1, Float value2) {
            addCriterion("game_friend_more_count not between", value1, value2, "gameFriendMoreCount");
            return (Criteria) this;
        }

        public Criteria andWxFriendMoreCountIsNull() {
            addCriterion("wx_friend_more_count is null");
            return (Criteria) this;
        }

        public Criteria andWxFriendMoreCountIsNotNull() {
            addCriterion("wx_friend_more_count is not null");
            return (Criteria) this;
        }

        public Criteria andWxFriendMoreCountEqualTo(Float value) {
            addCriterion("wx_friend_more_count =", value, "wxFriendMoreCount");
            return (Criteria) this;
        }

        public Criteria andWxFriendMoreCountNotEqualTo(Float value) {
            addCriterion("wx_friend_more_count <>", value, "wxFriendMoreCount");
            return (Criteria) this;
        }

        public Criteria andWxFriendMoreCountGreaterThan(Float value) {
            addCriterion("wx_friend_more_count >", value, "wxFriendMoreCount");
            return (Criteria) this;
        }

        public Criteria andWxFriendMoreCountGreaterThanOrEqualTo(Float value) {
            addCriterion("wx_friend_more_count >=", value, "wxFriendMoreCount");
            return (Criteria) this;
        }

        public Criteria andWxFriendMoreCountLessThan(Float value) {
            addCriterion("wx_friend_more_count <", value, "wxFriendMoreCount");
            return (Criteria) this;
        }

        public Criteria andWxFriendMoreCountLessThanOrEqualTo(Float value) {
            addCriterion("wx_friend_more_count <=", value, "wxFriendMoreCount");
            return (Criteria) this;
        }

        public Criteria andWxFriendMoreCountIn(List<Float> values) {
            addCriterion("wx_friend_more_count in", values, "wxFriendMoreCount");
            return (Criteria) this;
        }

        public Criteria andWxFriendMoreCountNotIn(List<Float> values) {
            addCriterion("wx_friend_more_count not in", values, "wxFriendMoreCount");
            return (Criteria) this;
        }

        public Criteria andWxFriendMoreCountBetween(Float value1, Float value2) {
            addCriterion("wx_friend_more_count between", value1, value2, "wxFriendMoreCount");
            return (Criteria) this;
        }

        public Criteria andWxFriendMoreCountNotBetween(Float value1, Float value2) {
            addCriterion("wx_friend_more_count not between", value1, value2, "wxFriendMoreCount");
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