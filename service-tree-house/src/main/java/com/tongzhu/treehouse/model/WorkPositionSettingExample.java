package com.tongzhu.treehouse.model;

import java.util.ArrayList;
import java.util.List;

public class WorkPositionSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WorkPositionSettingExample() {
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

        public Criteria andPositionOrderIsNull() {
            addCriterion("position_order is null");
            return (Criteria) this;
        }

        public Criteria andPositionOrderIsNotNull() {
            addCriterion("position_order is not null");
            return (Criteria) this;
        }

        public Criteria andPositionOrderEqualTo(Integer value) {
            addCriterion("position_order =", value, "positionOrder");
            return (Criteria) this;
        }

        public Criteria andPositionOrderNotEqualTo(Integer value) {
            addCriterion("position_order <>", value, "positionOrder");
            return (Criteria) this;
        }

        public Criteria andPositionOrderGreaterThan(Integer value) {
            addCriterion("position_order >", value, "positionOrder");
            return (Criteria) this;
        }

        public Criteria andPositionOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("position_order >=", value, "positionOrder");
            return (Criteria) this;
        }

        public Criteria andPositionOrderLessThan(Integer value) {
            addCriterion("position_order <", value, "positionOrder");
            return (Criteria) this;
        }

        public Criteria andPositionOrderLessThanOrEqualTo(Integer value) {
            addCriterion("position_order <=", value, "positionOrder");
            return (Criteria) this;
        }

        public Criteria andPositionOrderIn(List<Integer> values) {
            addCriterion("position_order in", values, "positionOrder");
            return (Criteria) this;
        }

        public Criteria andPositionOrderNotIn(List<Integer> values) {
            addCriterion("position_order not in", values, "positionOrder");
            return (Criteria) this;
        }

        public Criteria andPositionOrderBetween(Integer value1, Integer value2) {
            addCriterion("position_order between", value1, value2, "positionOrder");
            return (Criteria) this;
        }

        public Criteria andPositionOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("position_order not between", value1, value2, "positionOrder");
            return (Criteria) this;
        }

        public Criteria andUnlockGoodsIsNull() {
            addCriterion("unlock_goods is null");
            return (Criteria) this;
        }

        public Criteria andUnlockGoodsIsNotNull() {
            addCriterion("unlock_goods is not null");
            return (Criteria) this;
        }

        public Criteria andUnlockGoodsEqualTo(String value) {
            addCriterion("unlock_goods =", value, "unlockGoods");
            return (Criteria) this;
        }

        public Criteria andUnlockGoodsNotEqualTo(String value) {
            addCriterion("unlock_goods <>", value, "unlockGoods");
            return (Criteria) this;
        }

        public Criteria andUnlockGoodsGreaterThan(String value) {
            addCriterion("unlock_goods >", value, "unlockGoods");
            return (Criteria) this;
        }

        public Criteria andUnlockGoodsGreaterThanOrEqualTo(String value) {
            addCriterion("unlock_goods >=", value, "unlockGoods");
            return (Criteria) this;
        }

        public Criteria andUnlockGoodsLessThan(String value) {
            addCriterion("unlock_goods <", value, "unlockGoods");
            return (Criteria) this;
        }

        public Criteria andUnlockGoodsLessThanOrEqualTo(String value) {
            addCriterion("unlock_goods <=", value, "unlockGoods");
            return (Criteria) this;
        }

        public Criteria andUnlockGoodsLike(String value) {
            addCriterion("unlock_goods like", value, "unlockGoods");
            return (Criteria) this;
        }

        public Criteria andUnlockGoodsNotLike(String value) {
            addCriterion("unlock_goods not like", value, "unlockGoods");
            return (Criteria) this;
        }

        public Criteria andUnlockGoodsIn(List<String> values) {
            addCriterion("unlock_goods in", values, "unlockGoods");
            return (Criteria) this;
        }

        public Criteria andUnlockGoodsNotIn(List<String> values) {
            addCriterion("unlock_goods not in", values, "unlockGoods");
            return (Criteria) this;
        }

        public Criteria andUnlockGoodsBetween(String value1, String value2) {
            addCriterion("unlock_goods between", value1, value2, "unlockGoods");
            return (Criteria) this;
        }

        public Criteria andUnlockGoodsNotBetween(String value1, String value2) {
            addCriterion("unlock_goods not between", value1, value2, "unlockGoods");
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