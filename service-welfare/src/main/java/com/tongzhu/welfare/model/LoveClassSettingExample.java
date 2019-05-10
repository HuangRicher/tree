package com.tongzhu.welfare.model;

import java.util.ArrayList;
import java.util.List;

public class LoveClassSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LoveClassSettingExample() {
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

        public Criteria andGradeIsNull() {
            addCriterion("grade is null");
            return (Criteria) this;
        }

        public Criteria andGradeIsNotNull() {
            addCriterion("grade is not null");
            return (Criteria) this;
        }

        public Criteria andGradeEqualTo(Integer value) {
            addCriterion("grade =", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotEqualTo(Integer value) {
            addCriterion("grade <>", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThan(Integer value) {
            addCriterion("grade >", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("grade >=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThan(Integer value) {
            addCriterion("grade <", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThanOrEqualTo(Integer value) {
            addCriterion("grade <=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeIn(List<Integer> values) {
            addCriterion("grade in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotIn(List<Integer> values) {
            addCriterion("grade not in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeBetween(Integer value1, Integer value2) {
            addCriterion("grade between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("grade not between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andProperty1IsNull() {
            addCriterion("property1 is null");
            return (Criteria) this;
        }

        public Criteria andProperty1IsNotNull() {
            addCriterion("property1 is not null");
            return (Criteria) this;
        }

        public Criteria andProperty1EqualTo(Integer value) {
            addCriterion("property1 =", value, "property1");
            return (Criteria) this;
        }

        public Criteria andProperty1NotEqualTo(Integer value) {
            addCriterion("property1 <>", value, "property1");
            return (Criteria) this;
        }

        public Criteria andProperty1GreaterThan(Integer value) {
            addCriterion("property1 >", value, "property1");
            return (Criteria) this;
        }

        public Criteria andProperty1GreaterThanOrEqualTo(Integer value) {
            addCriterion("property1 >=", value, "property1");
            return (Criteria) this;
        }

        public Criteria andProperty1LessThan(Integer value) {
            addCriterion("property1 <", value, "property1");
            return (Criteria) this;
        }

        public Criteria andProperty1LessThanOrEqualTo(Integer value) {
            addCriterion("property1 <=", value, "property1");
            return (Criteria) this;
        }

        public Criteria andProperty1In(List<Integer> values) {
            addCriterion("property1 in", values, "property1");
            return (Criteria) this;
        }

        public Criteria andProperty1NotIn(List<Integer> values) {
            addCriterion("property1 not in", values, "property1");
            return (Criteria) this;
        }

        public Criteria andProperty1Between(Integer value1, Integer value2) {
            addCriterion("property1 between", value1, value2, "property1");
            return (Criteria) this;
        }

        public Criteria andProperty1NotBetween(Integer value1, Integer value2) {
            addCriterion("property1 not between", value1, value2, "property1");
            return (Criteria) this;
        }

        public Criteria andProperty2IsNull() {
            addCriterion("property2 is null");
            return (Criteria) this;
        }

        public Criteria andProperty2IsNotNull() {
            addCriterion("property2 is not null");
            return (Criteria) this;
        }

        public Criteria andProperty2EqualTo(Integer value) {
            addCriterion("property2 =", value, "property2");
            return (Criteria) this;
        }

        public Criteria andProperty2NotEqualTo(Integer value) {
            addCriterion("property2 <>", value, "property2");
            return (Criteria) this;
        }

        public Criteria andProperty2GreaterThan(Integer value) {
            addCriterion("property2 >", value, "property2");
            return (Criteria) this;
        }

        public Criteria andProperty2GreaterThanOrEqualTo(Integer value) {
            addCriterion("property2 >=", value, "property2");
            return (Criteria) this;
        }

        public Criteria andProperty2LessThan(Integer value) {
            addCriterion("property2 <", value, "property2");
            return (Criteria) this;
        }

        public Criteria andProperty2LessThanOrEqualTo(Integer value) {
            addCriterion("property2 <=", value, "property2");
            return (Criteria) this;
        }

        public Criteria andProperty2In(List<Integer> values) {
            addCriterion("property2 in", values, "property2");
            return (Criteria) this;
        }

        public Criteria andProperty2NotIn(List<Integer> values) {
            addCriterion("property2 not in", values, "property2");
            return (Criteria) this;
        }

        public Criteria andProperty2Between(Integer value1, Integer value2) {
            addCriterion("property2 between", value1, value2, "property2");
            return (Criteria) this;
        }

        public Criteria andProperty2NotBetween(Integer value1, Integer value2) {
            addCriterion("property2 not between", value1, value2, "property2");
            return (Criteria) this;
        }

        public Criteria andProperty3IsNull() {
            addCriterion("property3 is null");
            return (Criteria) this;
        }

        public Criteria andProperty3IsNotNull() {
            addCriterion("property3 is not null");
            return (Criteria) this;
        }

        public Criteria andProperty3EqualTo(Integer value) {
            addCriterion("property3 =", value, "property3");
            return (Criteria) this;
        }

        public Criteria andProperty3NotEqualTo(Integer value) {
            addCriterion("property3 <>", value, "property3");
            return (Criteria) this;
        }

        public Criteria andProperty3GreaterThan(Integer value) {
            addCriterion("property3 >", value, "property3");
            return (Criteria) this;
        }

        public Criteria andProperty3GreaterThanOrEqualTo(Integer value) {
            addCriterion("property3 >=", value, "property3");
            return (Criteria) this;
        }

        public Criteria andProperty3LessThan(Integer value) {
            addCriterion("property3 <", value, "property3");
            return (Criteria) this;
        }

        public Criteria andProperty3LessThanOrEqualTo(Integer value) {
            addCriterion("property3 <=", value, "property3");
            return (Criteria) this;
        }

        public Criteria andProperty3In(List<Integer> values) {
            addCriterion("property3 in", values, "property3");
            return (Criteria) this;
        }

        public Criteria andProperty3NotIn(List<Integer> values) {
            addCriterion("property3 not in", values, "property3");
            return (Criteria) this;
        }

        public Criteria andProperty3Between(Integer value1, Integer value2) {
            addCriterion("property3 between", value1, value2, "property3");
            return (Criteria) this;
        }

        public Criteria andProperty3NotBetween(Integer value1, Integer value2) {
            addCriterion("property3 not between", value1, value2, "property3");
            return (Criteria) this;
        }

        public Criteria andProperty4IsNull() {
            addCriterion("property4 is null");
            return (Criteria) this;
        }

        public Criteria andProperty4IsNotNull() {
            addCriterion("property4 is not null");
            return (Criteria) this;
        }

        public Criteria andProperty4EqualTo(Integer value) {
            addCriterion("property4 =", value, "property4");
            return (Criteria) this;
        }

        public Criteria andProperty4NotEqualTo(Integer value) {
            addCriterion("property4 <>", value, "property4");
            return (Criteria) this;
        }

        public Criteria andProperty4GreaterThan(Integer value) {
            addCriterion("property4 >", value, "property4");
            return (Criteria) this;
        }

        public Criteria andProperty4GreaterThanOrEqualTo(Integer value) {
            addCriterion("property4 >=", value, "property4");
            return (Criteria) this;
        }

        public Criteria andProperty4LessThan(Integer value) {
            addCriterion("property4 <", value, "property4");
            return (Criteria) this;
        }

        public Criteria andProperty4LessThanOrEqualTo(Integer value) {
            addCriterion("property4 <=", value, "property4");
            return (Criteria) this;
        }

        public Criteria andProperty4In(List<Integer> values) {
            addCriterion("property4 in", values, "property4");
            return (Criteria) this;
        }

        public Criteria andProperty4NotIn(List<Integer> values) {
            addCriterion("property4 not in", values, "property4");
            return (Criteria) this;
        }

        public Criteria andProperty4Between(Integer value1, Integer value2) {
            addCriterion("property4 between", value1, value2, "property4");
            return (Criteria) this;
        }

        public Criteria andProperty4NotBetween(Integer value1, Integer value2) {
            addCriterion("property4 not between", value1, value2, "property4");
            return (Criteria) this;
        }

        public Criteria andProperty5IsNull() {
            addCriterion("property5 is null");
            return (Criteria) this;
        }

        public Criteria andProperty5IsNotNull() {
            addCriterion("property5 is not null");
            return (Criteria) this;
        }

        public Criteria andProperty5EqualTo(Integer value) {
            addCriterion("property5 =", value, "property5");
            return (Criteria) this;
        }

        public Criteria andProperty5NotEqualTo(Integer value) {
            addCriterion("property5 <>", value, "property5");
            return (Criteria) this;
        }

        public Criteria andProperty5GreaterThan(Integer value) {
            addCriterion("property5 >", value, "property5");
            return (Criteria) this;
        }

        public Criteria andProperty5GreaterThanOrEqualTo(Integer value) {
            addCriterion("property5 >=", value, "property5");
            return (Criteria) this;
        }

        public Criteria andProperty5LessThan(Integer value) {
            addCriterion("property5 <", value, "property5");
            return (Criteria) this;
        }

        public Criteria andProperty5LessThanOrEqualTo(Integer value) {
            addCriterion("property5 <=", value, "property5");
            return (Criteria) this;
        }

        public Criteria andProperty5In(List<Integer> values) {
            addCriterion("property5 in", values, "property5");
            return (Criteria) this;
        }

        public Criteria andProperty5NotIn(List<Integer> values) {
            addCriterion("property5 not in", values, "property5");
            return (Criteria) this;
        }

        public Criteria andProperty5Between(Integer value1, Integer value2) {
            addCriterion("property5 between", value1, value2, "property5");
            return (Criteria) this;
        }

        public Criteria andProperty5NotBetween(Integer value1, Integer value2) {
            addCriterion("property5 not between", value1, value2, "property5");
            return (Criteria) this;
        }

        public Criteria andProperty6IsNull() {
            addCriterion("property6 is null");
            return (Criteria) this;
        }

        public Criteria andProperty6IsNotNull() {
            addCriterion("property6 is not null");
            return (Criteria) this;
        }

        public Criteria andProperty6EqualTo(Integer value) {
            addCriterion("property6 =", value, "property6");
            return (Criteria) this;
        }

        public Criteria andProperty6NotEqualTo(Integer value) {
            addCriterion("property6 <>", value, "property6");
            return (Criteria) this;
        }

        public Criteria andProperty6GreaterThan(Integer value) {
            addCriterion("property6 >", value, "property6");
            return (Criteria) this;
        }

        public Criteria andProperty6GreaterThanOrEqualTo(Integer value) {
            addCriterion("property6 >=", value, "property6");
            return (Criteria) this;
        }

        public Criteria andProperty6LessThan(Integer value) {
            addCriterion("property6 <", value, "property6");
            return (Criteria) this;
        }

        public Criteria andProperty6LessThanOrEqualTo(Integer value) {
            addCriterion("property6 <=", value, "property6");
            return (Criteria) this;
        }

        public Criteria andProperty6In(List<Integer> values) {
            addCriterion("property6 in", values, "property6");
            return (Criteria) this;
        }

        public Criteria andProperty6NotIn(List<Integer> values) {
            addCriterion("property6 not in", values, "property6");
            return (Criteria) this;
        }

        public Criteria andProperty6Between(Integer value1, Integer value2) {
            addCriterion("property6 between", value1, value2, "property6");
            return (Criteria) this;
        }

        public Criteria andProperty6NotBetween(Integer value1, Integer value2) {
            addCriterion("property6 not between", value1, value2, "property6");
            return (Criteria) this;
        }

        public Criteria andProperty7IsNull() {
            addCriterion("property7 is null");
            return (Criteria) this;
        }

        public Criteria andProperty7IsNotNull() {
            addCriterion("property7 is not null");
            return (Criteria) this;
        }

        public Criteria andProperty7EqualTo(Integer value) {
            addCriterion("property7 =", value, "property7");
            return (Criteria) this;
        }

        public Criteria andProperty7NotEqualTo(Integer value) {
            addCriterion("property7 <>", value, "property7");
            return (Criteria) this;
        }

        public Criteria andProperty7GreaterThan(Integer value) {
            addCriterion("property7 >", value, "property7");
            return (Criteria) this;
        }

        public Criteria andProperty7GreaterThanOrEqualTo(Integer value) {
            addCriterion("property7 >=", value, "property7");
            return (Criteria) this;
        }

        public Criteria andProperty7LessThan(Integer value) {
            addCriterion("property7 <", value, "property7");
            return (Criteria) this;
        }

        public Criteria andProperty7LessThanOrEqualTo(Integer value) {
            addCriterion("property7 <=", value, "property7");
            return (Criteria) this;
        }

        public Criteria andProperty7In(List<Integer> values) {
            addCriterion("property7 in", values, "property7");
            return (Criteria) this;
        }

        public Criteria andProperty7NotIn(List<Integer> values) {
            addCriterion("property7 not in", values, "property7");
            return (Criteria) this;
        }

        public Criteria andProperty7Between(Integer value1, Integer value2) {
            addCriterion("property7 between", value1, value2, "property7");
            return (Criteria) this;
        }

        public Criteria andProperty7NotBetween(Integer value1, Integer value2) {
            addCriterion("property7 not between", value1, value2, "property7");
            return (Criteria) this;
        }

        public Criteria andProperty8IsNull() {
            addCriterion("property8 is null");
            return (Criteria) this;
        }

        public Criteria andProperty8IsNotNull() {
            addCriterion("property8 is not null");
            return (Criteria) this;
        }

        public Criteria andProperty8EqualTo(Integer value) {
            addCriterion("property8 =", value, "property8");
            return (Criteria) this;
        }

        public Criteria andProperty8NotEqualTo(Integer value) {
            addCriterion("property8 <>", value, "property8");
            return (Criteria) this;
        }

        public Criteria andProperty8GreaterThan(Integer value) {
            addCriterion("property8 >", value, "property8");
            return (Criteria) this;
        }

        public Criteria andProperty8GreaterThanOrEqualTo(Integer value) {
            addCriterion("property8 >=", value, "property8");
            return (Criteria) this;
        }

        public Criteria andProperty8LessThan(Integer value) {
            addCriterion("property8 <", value, "property8");
            return (Criteria) this;
        }

        public Criteria andProperty8LessThanOrEqualTo(Integer value) {
            addCriterion("property8 <=", value, "property8");
            return (Criteria) this;
        }

        public Criteria andProperty8In(List<Integer> values) {
            addCriterion("property8 in", values, "property8");
            return (Criteria) this;
        }

        public Criteria andProperty8NotIn(List<Integer> values) {
            addCriterion("property8 not in", values, "property8");
            return (Criteria) this;
        }

        public Criteria andProperty8Between(Integer value1, Integer value2) {
            addCriterion("property8 between", value1, value2, "property8");
            return (Criteria) this;
        }

        public Criteria andProperty8NotBetween(Integer value1, Integer value2) {
            addCriterion("property8 not between", value1, value2, "property8");
            return (Criteria) this;
        }

        public Criteria andProperty9IsNull() {
            addCriterion("property9 is null");
            return (Criteria) this;
        }

        public Criteria andProperty9IsNotNull() {
            addCriterion("property9 is not null");
            return (Criteria) this;
        }

        public Criteria andProperty9EqualTo(Integer value) {
            addCriterion("property9 =", value, "property9");
            return (Criteria) this;
        }

        public Criteria andProperty9NotEqualTo(Integer value) {
            addCriterion("property9 <>", value, "property9");
            return (Criteria) this;
        }

        public Criteria andProperty9GreaterThan(Integer value) {
            addCriterion("property9 >", value, "property9");
            return (Criteria) this;
        }

        public Criteria andProperty9GreaterThanOrEqualTo(Integer value) {
            addCriterion("property9 >=", value, "property9");
            return (Criteria) this;
        }

        public Criteria andProperty9LessThan(Integer value) {
            addCriterion("property9 <", value, "property9");
            return (Criteria) this;
        }

        public Criteria andProperty9LessThanOrEqualTo(Integer value) {
            addCriterion("property9 <=", value, "property9");
            return (Criteria) this;
        }

        public Criteria andProperty9In(List<Integer> values) {
            addCriterion("property9 in", values, "property9");
            return (Criteria) this;
        }

        public Criteria andProperty9NotIn(List<Integer> values) {
            addCriterion("property9 not in", values, "property9");
            return (Criteria) this;
        }

        public Criteria andProperty9Between(Integer value1, Integer value2) {
            addCriterion("property9 between", value1, value2, "property9");
            return (Criteria) this;
        }

        public Criteria andProperty9NotBetween(Integer value1, Integer value2) {
            addCriterion("property9 not between", value1, value2, "property9");
            return (Criteria) this;
        }

        public Criteria andProperty10IsNull() {
            addCriterion("property10 is null");
            return (Criteria) this;
        }

        public Criteria andProperty10IsNotNull() {
            addCriterion("property10 is not null");
            return (Criteria) this;
        }

        public Criteria andProperty10EqualTo(Integer value) {
            addCriterion("property10 =", value, "property10");
            return (Criteria) this;
        }

        public Criteria andProperty10NotEqualTo(Integer value) {
            addCriterion("property10 <>", value, "property10");
            return (Criteria) this;
        }

        public Criteria andProperty10GreaterThan(Integer value) {
            addCriterion("property10 >", value, "property10");
            return (Criteria) this;
        }

        public Criteria andProperty10GreaterThanOrEqualTo(Integer value) {
            addCriterion("property10 >=", value, "property10");
            return (Criteria) this;
        }

        public Criteria andProperty10LessThan(Integer value) {
            addCriterion("property10 <", value, "property10");
            return (Criteria) this;
        }

        public Criteria andProperty10LessThanOrEqualTo(Integer value) {
            addCriterion("property10 <=", value, "property10");
            return (Criteria) this;
        }

        public Criteria andProperty10In(List<Integer> values) {
            addCriterion("property10 in", values, "property10");
            return (Criteria) this;
        }

        public Criteria andProperty10NotIn(List<Integer> values) {
            addCriterion("property10 not in", values, "property10");
            return (Criteria) this;
        }

        public Criteria andProperty10Between(Integer value1, Integer value2) {
            addCriterion("property10 between", value1, value2, "property10");
            return (Criteria) this;
        }

        public Criteria andProperty10NotBetween(Integer value1, Integer value2) {
            addCriterion("property10 not between", value1, value2, "property10");
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