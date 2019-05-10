package com.tongzhu.treehouse.model;

import java.util.ArrayList;
import java.util.List;

public class FlowerSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FlowerSettingExample() {
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

        public Criteria andMoneyAwardIsNull() {
            addCriterion("money_award is null");
            return (Criteria) this;
        }

        public Criteria andMoneyAwardIsNotNull() {
            addCriterion("money_award is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyAwardEqualTo(Integer value) {
            addCriterion("money_award =", value, "moneyAward");
            return (Criteria) this;
        }

        public Criteria andMoneyAwardNotEqualTo(Integer value) {
            addCriterion("money_award <>", value, "moneyAward");
            return (Criteria) this;
        }

        public Criteria andMoneyAwardGreaterThan(Integer value) {
            addCriterion("money_award >", value, "moneyAward");
            return (Criteria) this;
        }

        public Criteria andMoneyAwardGreaterThanOrEqualTo(Integer value) {
            addCriterion("money_award >=", value, "moneyAward");
            return (Criteria) this;
        }

        public Criteria andMoneyAwardLessThan(Integer value) {
            addCriterion("money_award <", value, "moneyAward");
            return (Criteria) this;
        }

        public Criteria andMoneyAwardLessThanOrEqualTo(Integer value) {
            addCriterion("money_award <=", value, "moneyAward");
            return (Criteria) this;
        }

        public Criteria andMoneyAwardIn(List<Integer> values) {
            addCriterion("money_award in", values, "moneyAward");
            return (Criteria) this;
        }

        public Criteria andMoneyAwardNotIn(List<Integer> values) {
            addCriterion("money_award not in", values, "moneyAward");
            return (Criteria) this;
        }

        public Criteria andMoneyAwardBetween(Integer value1, Integer value2) {
            addCriterion("money_award between", value1, value2, "moneyAward");
            return (Criteria) this;
        }

        public Criteria andMoneyAwardNotBetween(Integer value1, Integer value2) {
            addCriterion("money_award not between", value1, value2, "moneyAward");
            return (Criteria) this;
        }

        public Criteria andEnviromentAwardIsNull() {
            addCriterion("enviroment_award is null");
            return (Criteria) this;
        }

        public Criteria andEnviromentAwardIsNotNull() {
            addCriterion("enviroment_award is not null");
            return (Criteria) this;
        }

        public Criteria andEnviromentAwardEqualTo(Integer value) {
            addCriterion("enviroment_award =", value, "enviromentAward");
            return (Criteria) this;
        }

        public Criteria andEnviromentAwardNotEqualTo(Integer value) {
            addCriterion("enviroment_award <>", value, "enviromentAward");
            return (Criteria) this;
        }

        public Criteria andEnviromentAwardGreaterThan(Integer value) {
            addCriterion("enviroment_award >", value, "enviromentAward");
            return (Criteria) this;
        }

        public Criteria andEnviromentAwardGreaterThanOrEqualTo(Integer value) {
            addCriterion("enviroment_award >=", value, "enviromentAward");
            return (Criteria) this;
        }

        public Criteria andEnviromentAwardLessThan(Integer value) {
            addCriterion("enviroment_award <", value, "enviromentAward");
            return (Criteria) this;
        }

        public Criteria andEnviromentAwardLessThanOrEqualTo(Integer value) {
            addCriterion("enviroment_award <=", value, "enviromentAward");
            return (Criteria) this;
        }

        public Criteria andEnviromentAwardIn(List<Integer> values) {
            addCriterion("enviroment_award in", values, "enviromentAward");
            return (Criteria) this;
        }

        public Criteria andEnviromentAwardNotIn(List<Integer> values) {
            addCriterion("enviroment_award not in", values, "enviromentAward");
            return (Criteria) this;
        }

        public Criteria andEnviromentAwardBetween(Integer value1, Integer value2) {
            addCriterion("enviroment_award between", value1, value2, "enviromentAward");
            return (Criteria) this;
        }

        public Criteria andEnviromentAwardNotBetween(Integer value1, Integer value2) {
            addCriterion("enviroment_award not between", value1, value2, "enviromentAward");
            return (Criteria) this;
        }

        public Criteria andOtherAwardIsNull() {
            addCriterion("other_award is null");
            return (Criteria) this;
        }

        public Criteria andOtherAwardIsNotNull() {
            addCriterion("other_award is not null");
            return (Criteria) this;
        }

        public Criteria andOtherAwardEqualTo(String value) {
            addCriterion("other_award =", value, "otherAward");
            return (Criteria) this;
        }

        public Criteria andOtherAwardNotEqualTo(String value) {
            addCriterion("other_award <>", value, "otherAward");
            return (Criteria) this;
        }

        public Criteria andOtherAwardGreaterThan(String value) {
            addCriterion("other_award >", value, "otherAward");
            return (Criteria) this;
        }

        public Criteria andOtherAwardGreaterThanOrEqualTo(String value) {
            addCriterion("other_award >=", value, "otherAward");
            return (Criteria) this;
        }

        public Criteria andOtherAwardLessThan(String value) {
            addCriterion("other_award <", value, "otherAward");
            return (Criteria) this;
        }

        public Criteria andOtherAwardLessThanOrEqualTo(String value) {
            addCriterion("other_award <=", value, "otherAward");
            return (Criteria) this;
        }

        public Criteria andOtherAwardLike(String value) {
            addCriterion("other_award like", value, "otherAward");
            return (Criteria) this;
        }

        public Criteria andOtherAwardNotLike(String value) {
            addCriterion("other_award not like", value, "otherAward");
            return (Criteria) this;
        }

        public Criteria andOtherAwardIn(List<String> values) {
            addCriterion("other_award in", values, "otherAward");
            return (Criteria) this;
        }

        public Criteria andOtherAwardNotIn(List<String> values) {
            addCriterion("other_award not in", values, "otherAward");
            return (Criteria) this;
        }

        public Criteria andOtherAwardBetween(String value1, String value2) {
            addCriterion("other_award between", value1, value2, "otherAward");
            return (Criteria) this;
        }

        public Criteria andOtherAwardNotBetween(String value1, String value2) {
            addCriterion("other_award not between", value1, value2, "otherAward");
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