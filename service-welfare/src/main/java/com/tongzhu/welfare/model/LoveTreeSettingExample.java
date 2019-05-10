package com.tongzhu.welfare.model;

import java.util.ArrayList;
import java.util.List;

public class LoveTreeSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LoveTreeSettingExample() {
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

        public Criteria andExpLimitIsNull() {
            addCriterion("exp_limit is null");
            return (Criteria) this;
        }

        public Criteria andExpLimitIsNotNull() {
            addCriterion("exp_limit is not null");
            return (Criteria) this;
        }

        public Criteria andExpLimitEqualTo(Integer value) {
            addCriterion("exp_limit =", value, "expLimit");
            return (Criteria) this;
        }

        public Criteria andExpLimitNotEqualTo(Integer value) {
            addCriterion("exp_limit <>", value, "expLimit");
            return (Criteria) this;
        }

        public Criteria andExpLimitGreaterThan(Integer value) {
            addCriterion("exp_limit >", value, "expLimit");
            return (Criteria) this;
        }

        public Criteria andExpLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("exp_limit >=", value, "expLimit");
            return (Criteria) this;
        }

        public Criteria andExpLimitLessThan(Integer value) {
            addCriterion("exp_limit <", value, "expLimit");
            return (Criteria) this;
        }

        public Criteria andExpLimitLessThanOrEqualTo(Integer value) {
            addCriterion("exp_limit <=", value, "expLimit");
            return (Criteria) this;
        }

        public Criteria andExpLimitIn(List<Integer> values) {
            addCriterion("exp_limit in", values, "expLimit");
            return (Criteria) this;
        }

        public Criteria andExpLimitNotIn(List<Integer> values) {
            addCriterion("exp_limit not in", values, "expLimit");
            return (Criteria) this;
        }

        public Criteria andExpLimitBetween(Integer value1, Integer value2) {
            addCriterion("exp_limit between", value1, value2, "expLimit");
            return (Criteria) this;
        }

        public Criteria andExpLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("exp_limit not between", value1, value2, "expLimit");
            return (Criteria) this;
        }

        public Criteria andAttackIsNull() {
            addCriterion("attack is null");
            return (Criteria) this;
        }

        public Criteria andAttackIsNotNull() {
            addCriterion("attack is not null");
            return (Criteria) this;
        }

        public Criteria andAttackEqualTo(Integer value) {
            addCriterion("attack =", value, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackNotEqualTo(Integer value) {
            addCriterion("attack <>", value, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackGreaterThan(Integer value) {
            addCriterion("attack >", value, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackGreaterThanOrEqualTo(Integer value) {
            addCriterion("attack >=", value, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackLessThan(Integer value) {
            addCriterion("attack <", value, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackLessThanOrEqualTo(Integer value) {
            addCriterion("attack <=", value, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackIn(List<Integer> values) {
            addCriterion("attack in", values, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackNotIn(List<Integer> values) {
            addCriterion("attack not in", values, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackBetween(Integer value1, Integer value2) {
            addCriterion("attack between", value1, value2, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackNotBetween(Integer value1, Integer value2) {
            addCriterion("attack not between", value1, value2, "attack");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksIsNull() {
            addCriterion("spell_attacks is null");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksIsNotNull() {
            addCriterion("spell_attacks is not null");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksEqualTo(Integer value) {
            addCriterion("spell_attacks =", value, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksNotEqualTo(Integer value) {
            addCriterion("spell_attacks <>", value, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksGreaterThan(Integer value) {
            addCriterion("spell_attacks >", value, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksGreaterThanOrEqualTo(Integer value) {
            addCriterion("spell_attacks >=", value, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksLessThan(Integer value) {
            addCriterion("spell_attacks <", value, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksLessThanOrEqualTo(Integer value) {
            addCriterion("spell_attacks <=", value, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksIn(List<Integer> values) {
            addCriterion("spell_attacks in", values, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksNotIn(List<Integer> values) {
            addCriterion("spell_attacks not in", values, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksBetween(Integer value1, Integer value2) {
            addCriterion("spell_attacks between", value1, value2, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksNotBetween(Integer value1, Integer value2) {
            addCriterion("spell_attacks not between", value1, value2, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andPdefIsNull() {
            addCriterion("pdef is null");
            return (Criteria) this;
        }

        public Criteria andPdefIsNotNull() {
            addCriterion("pdef is not null");
            return (Criteria) this;
        }

        public Criteria andPdefEqualTo(Integer value) {
            addCriterion("pdef =", value, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefNotEqualTo(Integer value) {
            addCriterion("pdef <>", value, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefGreaterThan(Integer value) {
            addCriterion("pdef >", value, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefGreaterThanOrEqualTo(Integer value) {
            addCriterion("pdef >=", value, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefLessThan(Integer value) {
            addCriterion("pdef <", value, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefLessThanOrEqualTo(Integer value) {
            addCriterion("pdef <=", value, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefIn(List<Integer> values) {
            addCriterion("pdef in", values, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefNotIn(List<Integer> values) {
            addCriterion("pdef not in", values, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefBetween(Integer value1, Integer value2) {
            addCriterion("pdef between", value1, value2, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefNotBetween(Integer value1, Integer value2) {
            addCriterion("pdef not between", value1, value2, "pdef");
            return (Criteria) this;
        }

        public Criteria andMagdefIsNull() {
            addCriterion("magdef is null");
            return (Criteria) this;
        }

        public Criteria andMagdefIsNotNull() {
            addCriterion("magdef is not null");
            return (Criteria) this;
        }

        public Criteria andMagdefEqualTo(Integer value) {
            addCriterion("magdef =", value, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefNotEqualTo(Integer value) {
            addCriterion("magdef <>", value, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefGreaterThan(Integer value) {
            addCriterion("magdef >", value, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefGreaterThanOrEqualTo(Integer value) {
            addCriterion("magdef >=", value, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefLessThan(Integer value) {
            addCriterion("magdef <", value, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefLessThanOrEqualTo(Integer value) {
            addCriterion("magdef <=", value, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefIn(List<Integer> values) {
            addCriterion("magdef in", values, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefNotIn(List<Integer> values) {
            addCriterion("magdef not in", values, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefBetween(Integer value1, Integer value2) {
            addCriterion("magdef between", value1, value2, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefNotBetween(Integer value1, Integer value2) {
            addCriterion("magdef not between", value1, value2, "magdef");
            return (Criteria) this;
        }

        public Criteria andVitalityIsNull() {
            addCriterion("vitality is null");
            return (Criteria) this;
        }

        public Criteria andVitalityIsNotNull() {
            addCriterion("vitality is not null");
            return (Criteria) this;
        }

        public Criteria andVitalityEqualTo(Integer value) {
            addCriterion("vitality =", value, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityNotEqualTo(Integer value) {
            addCriterion("vitality <>", value, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityGreaterThan(Integer value) {
            addCriterion("vitality >", value, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityGreaterThanOrEqualTo(Integer value) {
            addCriterion("vitality >=", value, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityLessThan(Integer value) {
            addCriterion("vitality <", value, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityLessThanOrEqualTo(Integer value) {
            addCriterion("vitality <=", value, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityIn(List<Integer> values) {
            addCriterion("vitality in", values, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityNotIn(List<Integer> values) {
            addCriterion("vitality not in", values, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityBetween(Integer value1, Integer value2) {
            addCriterion("vitality between", value1, value2, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityNotBetween(Integer value1, Integer value2) {
            addCriterion("vitality not between", value1, value2, "vitality");
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