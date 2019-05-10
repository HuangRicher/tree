package com.tongzhu.user.model;

import java.util.ArrayList;
import java.util.List;

public class NPCAttributeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NPCAttributeExample() {
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

        public Criteria andLevelIdIsNull() {
            addCriterion("level_id is null");
            return (Criteria) this;
        }

        public Criteria andLevelIdIsNotNull() {
            addCriterion("level_id is not null");
            return (Criteria) this;
        }

        public Criteria andLevelIdEqualTo(Integer value) {
            addCriterion("level_id =", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdNotEqualTo(Integer value) {
            addCriterion("level_id <>", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdGreaterThan(Integer value) {
            addCriterion("level_id >", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("level_id >=", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdLessThan(Integer value) {
            addCriterion("level_id <", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdLessThanOrEqualTo(Integer value) {
            addCriterion("level_id <=", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdIn(List<Integer> values) {
            addCriterion("level_id in", values, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdNotIn(List<Integer> values) {
            addCriterion("level_id not in", values, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdBetween(Integer value1, Integer value2) {
            addCriterion("level_id between", value1, value2, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("level_id not between", value1, value2, "levelId");
            return (Criteria) this;
        }

        public Criteria andPhAtkIsNull() {
            addCriterion("ph_atk is null");
            return (Criteria) this;
        }

        public Criteria andPhAtkIsNotNull() {
            addCriterion("ph_atk is not null");
            return (Criteria) this;
        }

        public Criteria andPhAtkEqualTo(Integer value) {
            addCriterion("ph_atk =", value, "phAtk");
            return (Criteria) this;
        }

        public Criteria andPhAtkNotEqualTo(Integer value) {
            addCriterion("ph_atk <>", value, "phAtk");
            return (Criteria) this;
        }

        public Criteria andPhAtkGreaterThan(Integer value) {
            addCriterion("ph_atk >", value, "phAtk");
            return (Criteria) this;
        }

        public Criteria andPhAtkGreaterThanOrEqualTo(Integer value) {
            addCriterion("ph_atk >=", value, "phAtk");
            return (Criteria) this;
        }

        public Criteria andPhAtkLessThan(Integer value) {
            addCriterion("ph_atk <", value, "phAtk");
            return (Criteria) this;
        }

        public Criteria andPhAtkLessThanOrEqualTo(Integer value) {
            addCriterion("ph_atk <=", value, "phAtk");
            return (Criteria) this;
        }

        public Criteria andPhAtkIn(List<Integer> values) {
            addCriterion("ph_atk in", values, "phAtk");
            return (Criteria) this;
        }

        public Criteria andPhAtkNotIn(List<Integer> values) {
            addCriterion("ph_atk not in", values, "phAtk");
            return (Criteria) this;
        }

        public Criteria andPhAtkBetween(Integer value1, Integer value2) {
            addCriterion("ph_atk between", value1, value2, "phAtk");
            return (Criteria) this;
        }

        public Criteria andPhAtkNotBetween(Integer value1, Integer value2) {
            addCriterion("ph_atk not between", value1, value2, "phAtk");
            return (Criteria) this;
        }

        public Criteria andMfAtkIsNull() {
            addCriterion("mf_atk is null");
            return (Criteria) this;
        }

        public Criteria andMfAtkIsNotNull() {
            addCriterion("mf_atk is not null");
            return (Criteria) this;
        }

        public Criteria andMfAtkEqualTo(Integer value) {
            addCriterion("mf_atk =", value, "mfAtk");
            return (Criteria) this;
        }

        public Criteria andMfAtkNotEqualTo(Integer value) {
            addCriterion("mf_atk <>", value, "mfAtk");
            return (Criteria) this;
        }

        public Criteria andMfAtkGreaterThan(Integer value) {
            addCriterion("mf_atk >", value, "mfAtk");
            return (Criteria) this;
        }

        public Criteria andMfAtkGreaterThanOrEqualTo(Integer value) {
            addCriterion("mf_atk >=", value, "mfAtk");
            return (Criteria) this;
        }

        public Criteria andMfAtkLessThan(Integer value) {
            addCriterion("mf_atk <", value, "mfAtk");
            return (Criteria) this;
        }

        public Criteria andMfAtkLessThanOrEqualTo(Integer value) {
            addCriterion("mf_atk <=", value, "mfAtk");
            return (Criteria) this;
        }

        public Criteria andMfAtkIn(List<Integer> values) {
            addCriterion("mf_atk in", values, "mfAtk");
            return (Criteria) this;
        }

        public Criteria andMfAtkNotIn(List<Integer> values) {
            addCriterion("mf_atk not in", values, "mfAtk");
            return (Criteria) this;
        }

        public Criteria andMfAtkBetween(Integer value1, Integer value2) {
            addCriterion("mf_atk between", value1, value2, "mfAtk");
            return (Criteria) this;
        }

        public Criteria andMfAtkNotBetween(Integer value1, Integer value2) {
            addCriterion("mf_atk not between", value1, value2, "mfAtk");
            return (Criteria) this;
        }

        public Criteria andPhDefIsNull() {
            addCriterion("ph_def is null");
            return (Criteria) this;
        }

        public Criteria andPhDefIsNotNull() {
            addCriterion("ph_def is not null");
            return (Criteria) this;
        }

        public Criteria andPhDefEqualTo(Integer value) {
            addCriterion("ph_def =", value, "phDef");
            return (Criteria) this;
        }

        public Criteria andPhDefNotEqualTo(Integer value) {
            addCriterion("ph_def <>", value, "phDef");
            return (Criteria) this;
        }

        public Criteria andPhDefGreaterThan(Integer value) {
            addCriterion("ph_def >", value, "phDef");
            return (Criteria) this;
        }

        public Criteria andPhDefGreaterThanOrEqualTo(Integer value) {
            addCriterion("ph_def >=", value, "phDef");
            return (Criteria) this;
        }

        public Criteria andPhDefLessThan(Integer value) {
            addCriterion("ph_def <", value, "phDef");
            return (Criteria) this;
        }

        public Criteria andPhDefLessThanOrEqualTo(Integer value) {
            addCriterion("ph_def <=", value, "phDef");
            return (Criteria) this;
        }

        public Criteria andPhDefIn(List<Integer> values) {
            addCriterion("ph_def in", values, "phDef");
            return (Criteria) this;
        }

        public Criteria andPhDefNotIn(List<Integer> values) {
            addCriterion("ph_def not in", values, "phDef");
            return (Criteria) this;
        }

        public Criteria andPhDefBetween(Integer value1, Integer value2) {
            addCriterion("ph_def between", value1, value2, "phDef");
            return (Criteria) this;
        }

        public Criteria andPhDefNotBetween(Integer value1, Integer value2) {
            addCriterion("ph_def not between", value1, value2, "phDef");
            return (Criteria) this;
        }

        public Criteria andMfDefIsNull() {
            addCriterion("mf_def is null");
            return (Criteria) this;
        }

        public Criteria andMfDefIsNotNull() {
            addCriterion("mf_def is not null");
            return (Criteria) this;
        }

        public Criteria andMfDefEqualTo(Integer value) {
            addCriterion("mf_def =", value, "mfDef");
            return (Criteria) this;
        }

        public Criteria andMfDefNotEqualTo(Integer value) {
            addCriterion("mf_def <>", value, "mfDef");
            return (Criteria) this;
        }

        public Criteria andMfDefGreaterThan(Integer value) {
            addCriterion("mf_def >", value, "mfDef");
            return (Criteria) this;
        }

        public Criteria andMfDefGreaterThanOrEqualTo(Integer value) {
            addCriterion("mf_def >=", value, "mfDef");
            return (Criteria) this;
        }

        public Criteria andMfDefLessThan(Integer value) {
            addCriterion("mf_def <", value, "mfDef");
            return (Criteria) this;
        }

        public Criteria andMfDefLessThanOrEqualTo(Integer value) {
            addCriterion("mf_def <=", value, "mfDef");
            return (Criteria) this;
        }

        public Criteria andMfDefIn(List<Integer> values) {
            addCriterion("mf_def in", values, "mfDef");
            return (Criteria) this;
        }

        public Criteria andMfDefNotIn(List<Integer> values) {
            addCriterion("mf_def not in", values, "mfDef");
            return (Criteria) this;
        }

        public Criteria andMfDefBetween(Integer value1, Integer value2) {
            addCriterion("mf_def between", value1, value2, "mfDef");
            return (Criteria) this;
        }

        public Criteria andMfDefNotBetween(Integer value1, Integer value2) {
            addCriterion("mf_def not between", value1, value2, "mfDef");
            return (Criteria) this;
        }

        public Criteria andHpIsNull() {
            addCriterion("hp_ is null");
            return (Criteria) this;
        }

        public Criteria andHpIsNotNull() {
            addCriterion("hp_ is not null");
            return (Criteria) this;
        }

        public Criteria andHpEqualTo(Integer value) {
            addCriterion("hp_ =", value, "hp");
            return (Criteria) this;
        }

        public Criteria andHpNotEqualTo(Integer value) {
            addCriterion("hp_ <>", value, "hp");
            return (Criteria) this;
        }

        public Criteria andHpGreaterThan(Integer value) {
            addCriterion("hp_ >", value, "hp");
            return (Criteria) this;
        }

        public Criteria andHpGreaterThanOrEqualTo(Integer value) {
            addCriterion("hp_ >=", value, "hp");
            return (Criteria) this;
        }

        public Criteria andHpLessThan(Integer value) {
            addCriterion("hp_ <", value, "hp");
            return (Criteria) this;
        }

        public Criteria andHpLessThanOrEqualTo(Integer value) {
            addCriterion("hp_ <=", value, "hp");
            return (Criteria) this;
        }

        public Criteria andHpIn(List<Integer> values) {
            addCriterion("hp_ in", values, "hp");
            return (Criteria) this;
        }

        public Criteria andHpNotIn(List<Integer> values) {
            addCriterion("hp_ not in", values, "hp");
            return (Criteria) this;
        }

        public Criteria andHpBetween(Integer value1, Integer value2) {
            addCriterion("hp_ between", value1, value2, "hp");
            return (Criteria) this;
        }

        public Criteria andHpNotBetween(Integer value1, Integer value2) {
            addCriterion("hp_ not between", value1, value2, "hp");
            return (Criteria) this;
        }

        public Criteria andAccuracyIsNull() {
            addCriterion("accuracy_ is null");
            return (Criteria) this;
        }

        public Criteria andAccuracyIsNotNull() {
            addCriterion("accuracy_ is not null");
            return (Criteria) this;
        }

        public Criteria andAccuracyEqualTo(Integer value) {
            addCriterion("accuracy_ =", value, "accuracy");
            return (Criteria) this;
        }

        public Criteria andAccuracyNotEqualTo(Integer value) {
            addCriterion("accuracy_ <>", value, "accuracy");
            return (Criteria) this;
        }

        public Criteria andAccuracyGreaterThan(Integer value) {
            addCriterion("accuracy_ >", value, "accuracy");
            return (Criteria) this;
        }

        public Criteria andAccuracyGreaterThanOrEqualTo(Integer value) {
            addCriterion("accuracy_ >=", value, "accuracy");
            return (Criteria) this;
        }

        public Criteria andAccuracyLessThan(Integer value) {
            addCriterion("accuracy_ <", value, "accuracy");
            return (Criteria) this;
        }

        public Criteria andAccuracyLessThanOrEqualTo(Integer value) {
            addCriterion("accuracy_ <=", value, "accuracy");
            return (Criteria) this;
        }

        public Criteria andAccuracyIn(List<Integer> values) {
            addCriterion("accuracy_ in", values, "accuracy");
            return (Criteria) this;
        }

        public Criteria andAccuracyNotIn(List<Integer> values) {
            addCriterion("accuracy_ not in", values, "accuracy");
            return (Criteria) this;
        }

        public Criteria andAccuracyBetween(Integer value1, Integer value2) {
            addCriterion("accuracy_ between", value1, value2, "accuracy");
            return (Criteria) this;
        }

        public Criteria andAccuracyNotBetween(Integer value1, Integer value2) {
            addCriterion("accuracy_ not between", value1, value2, "accuracy");
            return (Criteria) this;
        }

        public Criteria andMissIsNull() {
            addCriterion("miss_ is null");
            return (Criteria) this;
        }

        public Criteria andMissIsNotNull() {
            addCriterion("miss_ is not null");
            return (Criteria) this;
        }

        public Criteria andMissEqualTo(Integer value) {
            addCriterion("miss_ =", value, "miss");
            return (Criteria) this;
        }

        public Criteria andMissNotEqualTo(Integer value) {
            addCriterion("miss_ <>", value, "miss");
            return (Criteria) this;
        }

        public Criteria andMissGreaterThan(Integer value) {
            addCriterion("miss_ >", value, "miss");
            return (Criteria) this;
        }

        public Criteria andMissGreaterThanOrEqualTo(Integer value) {
            addCriterion("miss_ >=", value, "miss");
            return (Criteria) this;
        }

        public Criteria andMissLessThan(Integer value) {
            addCriterion("miss_ <", value, "miss");
            return (Criteria) this;
        }

        public Criteria andMissLessThanOrEqualTo(Integer value) {
            addCriterion("miss_ <=", value, "miss");
            return (Criteria) this;
        }

        public Criteria andMissIn(List<Integer> values) {
            addCriterion("miss_ in", values, "miss");
            return (Criteria) this;
        }

        public Criteria andMissNotIn(List<Integer> values) {
            addCriterion("miss_ not in", values, "miss");
            return (Criteria) this;
        }

        public Criteria andMissBetween(Integer value1, Integer value2) {
            addCriterion("miss_ between", value1, value2, "miss");
            return (Criteria) this;
        }

        public Criteria andMissNotBetween(Integer value1, Integer value2) {
            addCriterion("miss_ not between", value1, value2, "miss");
            return (Criteria) this;
        }

        public Criteria andCriticalIsNull() {
            addCriterion("critical_ is null");
            return (Criteria) this;
        }

        public Criteria andCriticalIsNotNull() {
            addCriterion("critical_ is not null");
            return (Criteria) this;
        }

        public Criteria andCriticalEqualTo(Integer value) {
            addCriterion("critical_ =", value, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalNotEqualTo(Integer value) {
            addCriterion("critical_ <>", value, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalGreaterThan(Integer value) {
            addCriterion("critical_ >", value, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalGreaterThanOrEqualTo(Integer value) {
            addCriterion("critical_ >=", value, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalLessThan(Integer value) {
            addCriterion("critical_ <", value, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalLessThanOrEqualTo(Integer value) {
            addCriterion("critical_ <=", value, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalIn(List<Integer> values) {
            addCriterion("critical_ in", values, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalNotIn(List<Integer> values) {
            addCriterion("critical_ not in", values, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalBetween(Integer value1, Integer value2) {
            addCriterion("critical_ between", value1, value2, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalNotBetween(Integer value1, Integer value2) {
            addCriterion("critical_ not between", value1, value2, "critical");
            return (Criteria) this;
        }

        public Criteria andDcriticalIsNull() {
            addCriterion("dcritical_ is null");
            return (Criteria) this;
        }

        public Criteria andDcriticalIsNotNull() {
            addCriterion("dcritical_ is not null");
            return (Criteria) this;
        }

        public Criteria andDcriticalEqualTo(Integer value) {
            addCriterion("dcritical_ =", value, "dcritical");
            return (Criteria) this;
        }

        public Criteria andDcriticalNotEqualTo(Integer value) {
            addCriterion("dcritical_ <>", value, "dcritical");
            return (Criteria) this;
        }

        public Criteria andDcriticalGreaterThan(Integer value) {
            addCriterion("dcritical_ >", value, "dcritical");
            return (Criteria) this;
        }

        public Criteria andDcriticalGreaterThanOrEqualTo(Integer value) {
            addCriterion("dcritical_ >=", value, "dcritical");
            return (Criteria) this;
        }

        public Criteria andDcriticalLessThan(Integer value) {
            addCriterion("dcritical_ <", value, "dcritical");
            return (Criteria) this;
        }

        public Criteria andDcriticalLessThanOrEqualTo(Integer value) {
            addCriterion("dcritical_ <=", value, "dcritical");
            return (Criteria) this;
        }

        public Criteria andDcriticalIn(List<Integer> values) {
            addCriterion("dcritical_ in", values, "dcritical");
            return (Criteria) this;
        }

        public Criteria andDcriticalNotIn(List<Integer> values) {
            addCriterion("dcritical_ not in", values, "dcritical");
            return (Criteria) this;
        }

        public Criteria andDcriticalBetween(Integer value1, Integer value2) {
            addCriterion("dcritical_ between", value1, value2, "dcritical");
            return (Criteria) this;
        }

        public Criteria andDcriticalNotBetween(Integer value1, Integer value2) {
            addCriterion("dcritical_ not between", value1, value2, "dcritical");
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