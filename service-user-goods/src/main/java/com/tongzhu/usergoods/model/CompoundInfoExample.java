package com.tongzhu.usergoods.model;

import java.util.ArrayList;
import java.util.List;

public class CompoundInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CompoundInfoExample() {
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

        public Criteria andBasicItemsIsNull() {
            addCriterion("basic_items is null");
            return (Criteria) this;
        }

        public Criteria andBasicItemsIsNotNull() {
            addCriterion("basic_items is not null");
            return (Criteria) this;
        }

        public Criteria andBasicItemsEqualTo(String value) {
            addCriterion("basic_items =", value, "basicItems");
            return (Criteria) this;
        }

        public Criteria andBasicItemsNotEqualTo(String value) {
            addCriterion("basic_items <>", value, "basicItems");
            return (Criteria) this;
        }

        public Criteria andBasicItemsGreaterThan(String value) {
            addCriterion("basic_items >", value, "basicItems");
            return (Criteria) this;
        }

        public Criteria andBasicItemsGreaterThanOrEqualTo(String value) {
            addCriterion("basic_items >=", value, "basicItems");
            return (Criteria) this;
        }

        public Criteria andBasicItemsLessThan(String value) {
            addCriterion("basic_items <", value, "basicItems");
            return (Criteria) this;
        }

        public Criteria andBasicItemsLessThanOrEqualTo(String value) {
            addCriterion("basic_items <=", value, "basicItems");
            return (Criteria) this;
        }

        public Criteria andBasicItemsLike(String value) {
            addCriterion("basic_items like", value, "basicItems");
            return (Criteria) this;
        }

        public Criteria andBasicItemsNotLike(String value) {
            addCriterion("basic_items not like", value, "basicItems");
            return (Criteria) this;
        }

        public Criteria andBasicItemsIn(List<String> values) {
            addCriterion("basic_items in", values, "basicItems");
            return (Criteria) this;
        }

        public Criteria andBasicItemsNotIn(List<String> values) {
            addCriterion("basic_items not in", values, "basicItems");
            return (Criteria) this;
        }

        public Criteria andBasicItemsBetween(String value1, String value2) {
            addCriterion("basic_items between", value1, value2, "basicItems");
            return (Criteria) this;
        }

        public Criteria andBasicItemsNotBetween(String value1, String value2) {
            addCriterion("basic_items not between", value1, value2, "basicItems");
            return (Criteria) this;
        }

        public Criteria andCompositeIsNull() {
            addCriterion("composite is null");
            return (Criteria) this;
        }

        public Criteria andCompositeIsNotNull() {
            addCriterion("composite is not null");
            return (Criteria) this;
        }

        public Criteria andCompositeEqualTo(String value) {
            addCriterion("composite =", value, "composite");
            return (Criteria) this;
        }

        public Criteria andCompositeNotEqualTo(String value) {
            addCriterion("composite <>", value, "composite");
            return (Criteria) this;
        }

        public Criteria andCompositeGreaterThan(String value) {
            addCriterion("composite >", value, "composite");
            return (Criteria) this;
        }

        public Criteria andCompositeGreaterThanOrEqualTo(String value) {
            addCriterion("composite >=", value, "composite");
            return (Criteria) this;
        }

        public Criteria andCompositeLessThan(String value) {
            addCriterion("composite <", value, "composite");
            return (Criteria) this;
        }

        public Criteria andCompositeLessThanOrEqualTo(String value) {
            addCriterion("composite <=", value, "composite");
            return (Criteria) this;
        }

        public Criteria andCompositeLike(String value) {
            addCriterion("composite like", value, "composite");
            return (Criteria) this;
        }

        public Criteria andCompositeNotLike(String value) {
            addCriterion("composite not like", value, "composite");
            return (Criteria) this;
        }

        public Criteria andCompositeIn(List<String> values) {
            addCriterion("composite in", values, "composite");
            return (Criteria) this;
        }

        public Criteria andCompositeNotIn(List<String> values) {
            addCriterion("composite not in", values, "composite");
            return (Criteria) this;
        }

        public Criteria andCompositeBetween(String value1, String value2) {
            addCriterion("composite between", value1, value2, "composite");
            return (Criteria) this;
        }

        public Criteria andCompositeNotBetween(String value1, String value2) {
            addCriterion("composite not between", value1, value2, "composite");
            return (Criteria) this;
        }

        public Criteria andBasicItemsAmountIsNull() {
            addCriterion("basic_items_amount is null");
            return (Criteria) this;
        }

        public Criteria andBasicItemsAmountIsNotNull() {
            addCriterion("basic_items_amount is not null");
            return (Criteria) this;
        }

        public Criteria andBasicItemsAmountEqualTo(Integer value) {
            addCriterion("basic_items_amount =", value, "basicItemsAmount");
            return (Criteria) this;
        }

        public Criteria andBasicItemsAmountNotEqualTo(Integer value) {
            addCriterion("basic_items_amount <>", value, "basicItemsAmount");
            return (Criteria) this;
        }

        public Criteria andBasicItemsAmountGreaterThan(Integer value) {
            addCriterion("basic_items_amount >", value, "basicItemsAmount");
            return (Criteria) this;
        }

        public Criteria andBasicItemsAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("basic_items_amount >=", value, "basicItemsAmount");
            return (Criteria) this;
        }

        public Criteria andBasicItemsAmountLessThan(Integer value) {
            addCriterion("basic_items_amount <", value, "basicItemsAmount");
            return (Criteria) this;
        }

        public Criteria andBasicItemsAmountLessThanOrEqualTo(Integer value) {
            addCriterion("basic_items_amount <=", value, "basicItemsAmount");
            return (Criteria) this;
        }

        public Criteria andBasicItemsAmountIn(List<Integer> values) {
            addCriterion("basic_items_amount in", values, "basicItemsAmount");
            return (Criteria) this;
        }

        public Criteria andBasicItemsAmountNotIn(List<Integer> values) {
            addCriterion("basic_items_amount not in", values, "basicItemsAmount");
            return (Criteria) this;
        }

        public Criteria andBasicItemsAmountBetween(Integer value1, Integer value2) {
            addCriterion("basic_items_amount between", value1, value2, "basicItemsAmount");
            return (Criteria) this;
        }

        public Criteria andBasicItemsAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("basic_items_amount not between", value1, value2, "basicItemsAmount");
            return (Criteria) this;
        }

        public Criteria andCompositeTypeIsNull() {
            addCriterion("composite_type is null");
            return (Criteria) this;
        }

        public Criteria andCompositeTypeIsNotNull() {
            addCriterion("composite_type is not null");
            return (Criteria) this;
        }

        public Criteria andCompositeTypeEqualTo(Integer value) {
            addCriterion("composite_type =", value, "compositeType");
            return (Criteria) this;
        }

        public Criteria andCompositeTypeNotEqualTo(Integer value) {
            addCriterion("composite_type <>", value, "compositeType");
            return (Criteria) this;
        }

        public Criteria andCompositeTypeGreaterThan(Integer value) {
            addCriterion("composite_type >", value, "compositeType");
            return (Criteria) this;
        }

        public Criteria andCompositeTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("composite_type >=", value, "compositeType");
            return (Criteria) this;
        }

        public Criteria andCompositeTypeLessThan(Integer value) {
            addCriterion("composite_type <", value, "compositeType");
            return (Criteria) this;
        }

        public Criteria andCompositeTypeLessThanOrEqualTo(Integer value) {
            addCriterion("composite_type <=", value, "compositeType");
            return (Criteria) this;
        }

        public Criteria andCompositeTypeIn(List<Integer> values) {
            addCriterion("composite_type in", values, "compositeType");
            return (Criteria) this;
        }

        public Criteria andCompositeTypeNotIn(List<Integer> values) {
            addCriterion("composite_type not in", values, "compositeType");
            return (Criteria) this;
        }

        public Criteria andCompositeTypeBetween(Integer value1, Integer value2) {
            addCriterion("composite_type between", value1, value2, "compositeType");
            return (Criteria) this;
        }

        public Criteria andCompositeTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("composite_type not between", value1, value2, "compositeType");
            return (Criteria) this;
        }

        public Criteria andCompositeAmountIsNull() {
            addCriterion("composite_amount is null");
            return (Criteria) this;
        }

        public Criteria andCompositeAmountIsNotNull() {
            addCriterion("composite_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCompositeAmountEqualTo(Integer value) {
            addCriterion("composite_amount =", value, "compositeAmount");
            return (Criteria) this;
        }

        public Criteria andCompositeAmountNotEqualTo(Integer value) {
            addCriterion("composite_amount <>", value, "compositeAmount");
            return (Criteria) this;
        }

        public Criteria andCompositeAmountGreaterThan(Integer value) {
            addCriterion("composite_amount >", value, "compositeAmount");
            return (Criteria) this;
        }

        public Criteria andCompositeAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("composite_amount >=", value, "compositeAmount");
            return (Criteria) this;
        }

        public Criteria andCompositeAmountLessThan(Integer value) {
            addCriterion("composite_amount <", value, "compositeAmount");
            return (Criteria) this;
        }

        public Criteria andCompositeAmountLessThanOrEqualTo(Integer value) {
            addCriterion("composite_amount <=", value, "compositeAmount");
            return (Criteria) this;
        }

        public Criteria andCompositeAmountIn(List<Integer> values) {
            addCriterion("composite_amount in", values, "compositeAmount");
            return (Criteria) this;
        }

        public Criteria andCompositeAmountNotIn(List<Integer> values) {
            addCriterion("composite_amount not in", values, "compositeAmount");
            return (Criteria) this;
        }

        public Criteria andCompositeAmountBetween(Integer value1, Integer value2) {
            addCriterion("composite_amount between", value1, value2, "compositeAmount");
            return (Criteria) this;
        }

        public Criteria andCompositeAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("composite_amount not between", value1, value2, "compositeAmount");
            return (Criteria) this;
        }

        public Criteria andCostIsNull() {
            addCriterion("cost is null");
            return (Criteria) this;
        }

        public Criteria andCostIsNotNull() {
            addCriterion("cost is not null");
            return (Criteria) this;
        }

        public Criteria andCostEqualTo(String value) {
            addCriterion("cost =", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostNotEqualTo(String value) {
            addCriterion("cost <>", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostGreaterThan(String value) {
            addCriterion("cost >", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostGreaterThanOrEqualTo(String value) {
            addCriterion("cost >=", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostLessThan(String value) {
            addCriterion("cost <", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostLessThanOrEqualTo(String value) {
            addCriterion("cost <=", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostLike(String value) {
            addCriterion("cost like", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostNotLike(String value) {
            addCriterion("cost not like", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostIn(List<String> values) {
            addCriterion("cost in", values, "cost");
            return (Criteria) this;
        }

        public Criteria andCostNotIn(List<String> values) {
            addCriterion("cost not in", values, "cost");
            return (Criteria) this;
        }

        public Criteria andCostBetween(String value1, String value2) {
            addCriterion("cost between", value1, value2, "cost");
            return (Criteria) this;
        }

        public Criteria andCostNotBetween(String value1, String value2) {
            addCriterion("cost not between", value1, value2, "cost");
            return (Criteria) this;
        }

        public Criteria andCostAmountIsNull() {
            addCriterion("cost_amount is null");
            return (Criteria) this;
        }

        public Criteria andCostAmountIsNotNull() {
            addCriterion("cost_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCostAmountEqualTo(Integer value) {
            addCriterion("cost_amount =", value, "costAmount");
            return (Criteria) this;
        }

        public Criteria andCostAmountNotEqualTo(Integer value) {
            addCriterion("cost_amount <>", value, "costAmount");
            return (Criteria) this;
        }

        public Criteria andCostAmountGreaterThan(Integer value) {
            addCriterion("cost_amount >", value, "costAmount");
            return (Criteria) this;
        }

        public Criteria andCostAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("cost_amount >=", value, "costAmount");
            return (Criteria) this;
        }

        public Criteria andCostAmountLessThan(Integer value) {
            addCriterion("cost_amount <", value, "costAmount");
            return (Criteria) this;
        }

        public Criteria andCostAmountLessThanOrEqualTo(Integer value) {
            addCriterion("cost_amount <=", value, "costAmount");
            return (Criteria) this;
        }

        public Criteria andCostAmountIn(List<Integer> values) {
            addCriterion("cost_amount in", values, "costAmount");
            return (Criteria) this;
        }

        public Criteria andCostAmountNotIn(List<Integer> values) {
            addCriterion("cost_amount not in", values, "costAmount");
            return (Criteria) this;
        }

        public Criteria andCostAmountBetween(Integer value1, Integer value2) {
            addCriterion("cost_amount between", value1, value2, "costAmount");
            return (Criteria) this;
        }

        public Criteria andCostAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("cost_amount not between", value1, value2, "costAmount");
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