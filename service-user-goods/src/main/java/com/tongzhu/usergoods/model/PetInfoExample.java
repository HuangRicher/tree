package com.tongzhu.usergoods.model;

import java.util.ArrayList;
import java.util.List;

public class PetInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PetInfoExample() {
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

        public Criteria andPetIdIsNull() {
            addCriterion("pet_id is null");
            return (Criteria) this;
        }

        public Criteria andPetIdIsNotNull() {
            addCriterion("pet_id is not null");
            return (Criteria) this;
        }

        public Criteria andPetIdEqualTo(String value) {
            addCriterion("pet_id =", value, "petId");
            return (Criteria) this;
        }

        public Criteria andPetIdNotEqualTo(String value) {
            addCriterion("pet_id <>", value, "petId");
            return (Criteria) this;
        }

        public Criteria andPetIdGreaterThan(String value) {
            addCriterion("pet_id >", value, "petId");
            return (Criteria) this;
        }

        public Criteria andPetIdGreaterThanOrEqualTo(String value) {
            addCriterion("pet_id >=", value, "petId");
            return (Criteria) this;
        }

        public Criteria andPetIdLessThan(String value) {
            addCriterion("pet_id <", value, "petId");
            return (Criteria) this;
        }

        public Criteria andPetIdLessThanOrEqualTo(String value) {
            addCriterion("pet_id <=", value, "petId");
            return (Criteria) this;
        }

        public Criteria andPetIdLike(String value) {
            addCriterion("pet_id like", value, "petId");
            return (Criteria) this;
        }

        public Criteria andPetIdNotLike(String value) {
            addCriterion("pet_id not like", value, "petId");
            return (Criteria) this;
        }

        public Criteria andPetIdIn(List<String> values) {
            addCriterion("pet_id in", values, "petId");
            return (Criteria) this;
        }

        public Criteria andPetIdNotIn(List<String> values) {
            addCriterion("pet_id not in", values, "petId");
            return (Criteria) this;
        }

        public Criteria andPetIdBetween(String value1, String value2) {
            addCriterion("pet_id between", value1, value2, "petId");
            return (Criteria) this;
        }

        public Criteria andPetIdNotBetween(String value1, String value2) {
            addCriterion("pet_id not between", value1, value2, "petId");
            return (Criteria) this;
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andQualityIsNull() {
            addCriterion("quality is null");
            return (Criteria) this;
        }

        public Criteria andQualityIsNotNull() {
            addCriterion("quality is not null");
            return (Criteria) this;
        }

        public Criteria andQualityEqualTo(Integer value) {
            addCriterion("quality =", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityNotEqualTo(Integer value) {
            addCriterion("quality <>", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityGreaterThan(Integer value) {
            addCriterion("quality >", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityGreaterThanOrEqualTo(Integer value) {
            addCriterion("quality >=", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityLessThan(Integer value) {
            addCriterion("quality <", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityLessThanOrEqualTo(Integer value) {
            addCriterion("quality <=", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityIn(List<Integer> values) {
            addCriterion("quality in", values, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityNotIn(List<Integer> values) {
            addCriterion("quality not in", values, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityBetween(Integer value1, Integer value2) {
            addCriterion("quality between", value1, value2, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityNotBetween(Integer value1, Integer value2) {
            addCriterion("quality not between", value1, value2, "quality");
            return (Criteria) this;
        }

        public Criteria andIconIsNull() {
            addCriterion("icon is null");
            return (Criteria) this;
        }

        public Criteria andIconIsNotNull() {
            addCriterion("icon is not null");
            return (Criteria) this;
        }

        public Criteria andIconEqualTo(String value) {
            addCriterion("icon =", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotEqualTo(String value) {
            addCriterion("icon <>", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThan(String value) {
            addCriterion("icon >", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThanOrEqualTo(String value) {
            addCriterion("icon >=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThan(String value) {
            addCriterion("icon <", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThanOrEqualTo(String value) {
            addCriterion("icon <=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLike(String value) {
            addCriterion("icon like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotLike(String value) {
            addCriterion("icon not like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconIn(List<String> values) {
            addCriterion("icon in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotIn(List<String> values) {
            addCriterion("icon not in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconBetween(String value1, String value2) {
            addCriterion("icon between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotBetween(String value1, String value2) {
            addCriterion("icon not between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andOverdueIsNull() {
            addCriterion("overdue is null");
            return (Criteria) this;
        }

        public Criteria andOverdueIsNotNull() {
            addCriterion("overdue is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueEqualTo(Integer value) {
            addCriterion("overdue =", value, "overdue");
            return (Criteria) this;
        }

        public Criteria andOverdueNotEqualTo(Integer value) {
            addCriterion("overdue <>", value, "overdue");
            return (Criteria) this;
        }

        public Criteria andOverdueGreaterThan(Integer value) {
            addCriterion("overdue >", value, "overdue");
            return (Criteria) this;
        }

        public Criteria andOverdueGreaterThanOrEqualTo(Integer value) {
            addCriterion("overdue >=", value, "overdue");
            return (Criteria) this;
        }

        public Criteria andOverdueLessThan(Integer value) {
            addCriterion("overdue <", value, "overdue");
            return (Criteria) this;
        }

        public Criteria andOverdueLessThanOrEqualTo(Integer value) {
            addCriterion("overdue <=", value, "overdue");
            return (Criteria) this;
        }

        public Criteria andOverdueIn(List<Integer> values) {
            addCriterion("overdue in", values, "overdue");
            return (Criteria) this;
        }

        public Criteria andOverdueNotIn(List<Integer> values) {
            addCriterion("overdue not in", values, "overdue");
            return (Criteria) this;
        }

        public Criteria andOverdueBetween(Integer value1, Integer value2) {
            addCriterion("overdue between", value1, value2, "overdue");
            return (Criteria) this;
        }

        public Criteria andOverdueNotBetween(Integer value1, Integer value2) {
            addCriterion("overdue not between", value1, value2, "overdue");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Integer value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Integer value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Integer value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Integer value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Integer value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Integer> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Integer> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Integer value1, Integer value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("time not between", value1, value2, "time");
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

        public Criteria andVitalityEqualTo(Double value) {
            addCriterion("vitality =", value, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityNotEqualTo(Double value) {
            addCriterion("vitality <>", value, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityGreaterThan(Double value) {
            addCriterion("vitality >", value, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityGreaterThanOrEqualTo(Double value) {
            addCriterion("vitality >=", value, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityLessThan(Double value) {
            addCriterion("vitality <", value, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityLessThanOrEqualTo(Double value) {
            addCriterion("vitality <=", value, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityIn(List<Double> values) {
            addCriterion("vitality in", values, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityNotIn(List<Double> values) {
            addCriterion("vitality not in", values, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityBetween(Double value1, Double value2) {
            addCriterion("vitality between", value1, value2, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityNotBetween(Double value1, Double value2) {
            addCriterion("vitality not between", value1, value2, "vitality");
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

        public Criteria andAttackEqualTo(Double value) {
            addCriterion("attack =", value, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackNotEqualTo(Double value) {
            addCriterion("attack <>", value, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackGreaterThan(Double value) {
            addCriterion("attack >", value, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackGreaterThanOrEqualTo(Double value) {
            addCriterion("attack >=", value, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackLessThan(Double value) {
            addCriterion("attack <", value, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackLessThanOrEqualTo(Double value) {
            addCriterion("attack <=", value, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackIn(List<Double> values) {
            addCriterion("attack in", values, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackNotIn(List<Double> values) {
            addCriterion("attack not in", values, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackBetween(Double value1, Double value2) {
            addCriterion("attack between", value1, value2, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackNotBetween(Double value1, Double value2) {
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

        public Criteria andSpellAttacksEqualTo(Double value) {
            addCriterion("spell_attacks =", value, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksNotEqualTo(Double value) {
            addCriterion("spell_attacks <>", value, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksGreaterThan(Double value) {
            addCriterion("spell_attacks >", value, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksGreaterThanOrEqualTo(Double value) {
            addCriterion("spell_attacks >=", value, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksLessThan(Double value) {
            addCriterion("spell_attacks <", value, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksLessThanOrEqualTo(Double value) {
            addCriterion("spell_attacks <=", value, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksIn(List<Double> values) {
            addCriterion("spell_attacks in", values, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksNotIn(List<Double> values) {
            addCriterion("spell_attacks not in", values, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksBetween(Double value1, Double value2) {
            addCriterion("spell_attacks between", value1, value2, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksNotBetween(Double value1, Double value2) {
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

        public Criteria andPdefEqualTo(Double value) {
            addCriterion("pdef =", value, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefNotEqualTo(Double value) {
            addCriterion("pdef <>", value, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefGreaterThan(Double value) {
            addCriterion("pdef >", value, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefGreaterThanOrEqualTo(Double value) {
            addCriterion("pdef >=", value, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefLessThan(Double value) {
            addCriterion("pdef <", value, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefLessThanOrEqualTo(Double value) {
            addCriterion("pdef <=", value, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefIn(List<Double> values) {
            addCriterion("pdef in", values, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefNotIn(List<Double> values) {
            addCriterion("pdef not in", values, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefBetween(Double value1, Double value2) {
            addCriterion("pdef between", value1, value2, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefNotBetween(Double value1, Double value2) {
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

        public Criteria andMagdefEqualTo(Double value) {
            addCriterion("magdef =", value, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefNotEqualTo(Double value) {
            addCriterion("magdef <>", value, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefGreaterThan(Double value) {
            addCriterion("magdef >", value, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefGreaterThanOrEqualTo(Double value) {
            addCriterion("magdef >=", value, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefLessThan(Double value) {
            addCriterion("magdef <", value, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefLessThanOrEqualTo(Double value) {
            addCriterion("magdef <=", value, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefIn(List<Double> values) {
            addCriterion("magdef in", values, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefNotIn(List<Double> values) {
            addCriterion("magdef not in", values, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefBetween(Double value1, Double value2) {
            addCriterion("magdef between", value1, value2, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefNotBetween(Double value1, Double value2) {
            addCriterion("magdef not between", value1, value2, "magdef");
            return (Criteria) this;
        }

        public Criteria andCritIsNull() {
            addCriterion("crit is null");
            return (Criteria) this;
        }

        public Criteria andCritIsNotNull() {
            addCriterion("crit is not null");
            return (Criteria) this;
        }

        public Criteria andCritEqualTo(Double value) {
            addCriterion("crit =", value, "crit");
            return (Criteria) this;
        }

        public Criteria andCritNotEqualTo(Double value) {
            addCriterion("crit <>", value, "crit");
            return (Criteria) this;
        }

        public Criteria andCritGreaterThan(Double value) {
            addCriterion("crit >", value, "crit");
            return (Criteria) this;
        }

        public Criteria andCritGreaterThanOrEqualTo(Double value) {
            addCriterion("crit >=", value, "crit");
            return (Criteria) this;
        }

        public Criteria andCritLessThan(Double value) {
            addCriterion("crit <", value, "crit");
            return (Criteria) this;
        }

        public Criteria andCritLessThanOrEqualTo(Double value) {
            addCriterion("crit <=", value, "crit");
            return (Criteria) this;
        }

        public Criteria andCritIn(List<Double> values) {
            addCriterion("crit in", values, "crit");
            return (Criteria) this;
        }

        public Criteria andCritNotIn(List<Double> values) {
            addCriterion("crit not in", values, "crit");
            return (Criteria) this;
        }

        public Criteria andCritBetween(Double value1, Double value2) {
            addCriterion("crit between", value1, value2, "crit");
            return (Criteria) this;
        }

        public Criteria andCritNotBetween(Double value1, Double value2) {
            addCriterion("crit not between", value1, value2, "crit");
            return (Criteria) this;
        }

        public Criteria andDodgeIsNull() {
            addCriterion("dodge is null");
            return (Criteria) this;
        }

        public Criteria andDodgeIsNotNull() {
            addCriterion("dodge is not null");
            return (Criteria) this;
        }

        public Criteria andDodgeEqualTo(Double value) {
            addCriterion("dodge =", value, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeNotEqualTo(Double value) {
            addCriterion("dodge <>", value, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeGreaterThan(Double value) {
            addCriterion("dodge >", value, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeGreaterThanOrEqualTo(Double value) {
            addCriterion("dodge >=", value, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeLessThan(Double value) {
            addCriterion("dodge <", value, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeLessThanOrEqualTo(Double value) {
            addCriterion("dodge <=", value, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeIn(List<Double> values) {
            addCriterion("dodge in", values, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeNotIn(List<Double> values) {
            addCriterion("dodge not in", values, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeBetween(Double value1, Double value2) {
            addCriterion("dodge between", value1, value2, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeNotBetween(Double value1, Double value2) {
            addCriterion("dodge not between", value1, value2, "dodge");
            return (Criteria) this;
        }

        public Criteria andHitRateIsNull() {
            addCriterion("hit_rate is null");
            return (Criteria) this;
        }

        public Criteria andHitRateIsNotNull() {
            addCriterion("hit_rate is not null");
            return (Criteria) this;
        }

        public Criteria andHitRateEqualTo(Double value) {
            addCriterion("hit_rate =", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateNotEqualTo(Double value) {
            addCriterion("hit_rate <>", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateGreaterThan(Double value) {
            addCriterion("hit_rate >", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateGreaterThanOrEqualTo(Double value) {
            addCriterion("hit_rate >=", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateLessThan(Double value) {
            addCriterion("hit_rate <", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateLessThanOrEqualTo(Double value) {
            addCriterion("hit_rate <=", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateIn(List<Double> values) {
            addCriterion("hit_rate in", values, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateNotIn(List<Double> values) {
            addCriterion("hit_rate not in", values, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateBetween(Double value1, Double value2) {
            addCriterion("hit_rate between", value1, value2, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateNotBetween(Double value1, Double value2) {
            addCriterion("hit_rate not between", value1, value2, "hitRate");
            return (Criteria) this;
        }

        public Criteria andDefenseCritIsNull() {
            addCriterion("defense_crit is null");
            return (Criteria) this;
        }

        public Criteria andDefenseCritIsNotNull() {
            addCriterion("defense_crit is not null");
            return (Criteria) this;
        }

        public Criteria andDefenseCritEqualTo(Double value) {
            addCriterion("defense_crit =", value, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritNotEqualTo(Double value) {
            addCriterion("defense_crit <>", value, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritGreaterThan(Double value) {
            addCriterion("defense_crit >", value, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritGreaterThanOrEqualTo(Double value) {
            addCriterion("defense_crit >=", value, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritLessThan(Double value) {
            addCriterion("defense_crit <", value, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritLessThanOrEqualTo(Double value) {
            addCriterion("defense_crit <=", value, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritIn(List<Double> values) {
            addCriterion("defense_crit in", values, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritNotIn(List<Double> values) {
            addCriterion("defense_crit not in", values, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritBetween(Double value1, Double value2) {
            addCriterion("defense_crit between", value1, value2, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritNotBetween(Double value1, Double value2) {
            addCriterion("defense_crit not between", value1, value2, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andFightingCapacityIsNull() {
            addCriterion("fighting_capacity is null");
            return (Criteria) this;
        }

        public Criteria andFightingCapacityIsNotNull() {
            addCriterion("fighting_capacity is not null");
            return (Criteria) this;
        }

        public Criteria andFightingCapacityEqualTo(Double value) {
            addCriterion("fighting_capacity =", value, "fightingCapacity");
            return (Criteria) this;
        }

        public Criteria andFightingCapacityNotEqualTo(Double value) {
            addCriterion("fighting_capacity <>", value, "fightingCapacity");
            return (Criteria) this;
        }

        public Criteria andFightingCapacityGreaterThan(Double value) {
            addCriterion("fighting_capacity >", value, "fightingCapacity");
            return (Criteria) this;
        }

        public Criteria andFightingCapacityGreaterThanOrEqualTo(Double value) {
            addCriterion("fighting_capacity >=", value, "fightingCapacity");
            return (Criteria) this;
        }

        public Criteria andFightingCapacityLessThan(Double value) {
            addCriterion("fighting_capacity <", value, "fightingCapacity");
            return (Criteria) this;
        }

        public Criteria andFightingCapacityLessThanOrEqualTo(Double value) {
            addCriterion("fighting_capacity <=", value, "fightingCapacity");
            return (Criteria) this;
        }

        public Criteria andFightingCapacityIn(List<Double> values) {
            addCriterion("fighting_capacity in", values, "fightingCapacity");
            return (Criteria) this;
        }

        public Criteria andFightingCapacityNotIn(List<Double> values) {
            addCriterion("fighting_capacity not in", values, "fightingCapacity");
            return (Criteria) this;
        }

        public Criteria andFightingCapacityBetween(Double value1, Double value2) {
            addCriterion("fighting_capacity between", value1, value2, "fightingCapacity");
            return (Criteria) this;
        }

        public Criteria andFightingCapacityNotBetween(Double value1, Double value2) {
            addCriterion("fighting_capacity not between", value1, value2, "fightingCapacity");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andVitalityAdditionIsNull() {
            addCriterion("vitality_addition is null");
            return (Criteria) this;
        }

        public Criteria andVitalityAdditionIsNotNull() {
            addCriterion("vitality_addition is not null");
            return (Criteria) this;
        }

        public Criteria andVitalityAdditionEqualTo(Double value) {
            addCriterion("vitality_addition =", value, "vitalityAddition");
            return (Criteria) this;
        }

        public Criteria andVitalityAdditionNotEqualTo(Double value) {
            addCriterion("vitality_addition <>", value, "vitalityAddition");
            return (Criteria) this;
        }

        public Criteria andVitalityAdditionGreaterThan(Double value) {
            addCriterion("vitality_addition >", value, "vitalityAddition");
            return (Criteria) this;
        }

        public Criteria andVitalityAdditionGreaterThanOrEqualTo(Double value) {
            addCriterion("vitality_addition >=", value, "vitalityAddition");
            return (Criteria) this;
        }

        public Criteria andVitalityAdditionLessThan(Double value) {
            addCriterion("vitality_addition <", value, "vitalityAddition");
            return (Criteria) this;
        }

        public Criteria andVitalityAdditionLessThanOrEqualTo(Double value) {
            addCriterion("vitality_addition <=", value, "vitalityAddition");
            return (Criteria) this;
        }

        public Criteria andVitalityAdditionIn(List<Double> values) {
            addCriterion("vitality_addition in", values, "vitalityAddition");
            return (Criteria) this;
        }

        public Criteria andVitalityAdditionNotIn(List<Double> values) {
            addCriterion("vitality_addition not in", values, "vitalityAddition");
            return (Criteria) this;
        }

        public Criteria andVitalityAdditionBetween(Double value1, Double value2) {
            addCriterion("vitality_addition between", value1, value2, "vitalityAddition");
            return (Criteria) this;
        }

        public Criteria andVitalityAdditionNotBetween(Double value1, Double value2) {
            addCriterion("vitality_addition not between", value1, value2, "vitalityAddition");
            return (Criteria) this;
        }

        public Criteria andAttackAdditionIsNull() {
            addCriterion("attack_addition is null");
            return (Criteria) this;
        }

        public Criteria andAttackAdditionIsNotNull() {
            addCriterion("attack_addition is not null");
            return (Criteria) this;
        }

        public Criteria andAttackAdditionEqualTo(Double value) {
            addCriterion("attack_addition =", value, "attackAddition");
            return (Criteria) this;
        }

        public Criteria andAttackAdditionNotEqualTo(Double value) {
            addCriterion("attack_addition <>", value, "attackAddition");
            return (Criteria) this;
        }

        public Criteria andAttackAdditionGreaterThan(Double value) {
            addCriterion("attack_addition >", value, "attackAddition");
            return (Criteria) this;
        }

        public Criteria andAttackAdditionGreaterThanOrEqualTo(Double value) {
            addCriterion("attack_addition >=", value, "attackAddition");
            return (Criteria) this;
        }

        public Criteria andAttackAdditionLessThan(Double value) {
            addCriterion("attack_addition <", value, "attackAddition");
            return (Criteria) this;
        }

        public Criteria andAttackAdditionLessThanOrEqualTo(Double value) {
            addCriterion("attack_addition <=", value, "attackAddition");
            return (Criteria) this;
        }

        public Criteria andAttackAdditionIn(List<Double> values) {
            addCriterion("attack_addition in", values, "attackAddition");
            return (Criteria) this;
        }

        public Criteria andAttackAdditionNotIn(List<Double> values) {
            addCriterion("attack_addition not in", values, "attackAddition");
            return (Criteria) this;
        }

        public Criteria andAttackAdditionBetween(Double value1, Double value2) {
            addCriterion("attack_addition between", value1, value2, "attackAddition");
            return (Criteria) this;
        }

        public Criteria andAttackAdditionNotBetween(Double value1, Double value2) {
            addCriterion("attack_addition not between", value1, value2, "attackAddition");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksAdditionIsNull() {
            addCriterion("spell_attacks_addition is null");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksAdditionIsNotNull() {
            addCriterion("spell_attacks_addition is not null");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksAdditionEqualTo(Double value) {
            addCriterion("spell_attacks_addition =", value, "spellAttacksAddition");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksAdditionNotEqualTo(Double value) {
            addCriterion("spell_attacks_addition <>", value, "spellAttacksAddition");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksAdditionGreaterThan(Double value) {
            addCriterion("spell_attacks_addition >", value, "spellAttacksAddition");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksAdditionGreaterThanOrEqualTo(Double value) {
            addCriterion("spell_attacks_addition >=", value, "spellAttacksAddition");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksAdditionLessThan(Double value) {
            addCriterion("spell_attacks_addition <", value, "spellAttacksAddition");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksAdditionLessThanOrEqualTo(Double value) {
            addCriterion("spell_attacks_addition <=", value, "spellAttacksAddition");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksAdditionIn(List<Double> values) {
            addCriterion("spell_attacks_addition in", values, "spellAttacksAddition");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksAdditionNotIn(List<Double> values) {
            addCriterion("spell_attacks_addition not in", values, "spellAttacksAddition");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksAdditionBetween(Double value1, Double value2) {
            addCriterion("spell_attacks_addition between", value1, value2, "spellAttacksAddition");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksAdditionNotBetween(Double value1, Double value2) {
            addCriterion("spell_attacks_addition not between", value1, value2, "spellAttacksAddition");
            return (Criteria) this;
        }

        public Criteria andPdefAdditionIsNull() {
            addCriterion("pdef_addition is null");
            return (Criteria) this;
        }

        public Criteria andPdefAdditionIsNotNull() {
            addCriterion("pdef_addition is not null");
            return (Criteria) this;
        }

        public Criteria andPdefAdditionEqualTo(Double value) {
            addCriterion("pdef_addition =", value, "pdefAddition");
            return (Criteria) this;
        }

        public Criteria andPdefAdditionNotEqualTo(Double value) {
            addCriterion("pdef_addition <>", value, "pdefAddition");
            return (Criteria) this;
        }

        public Criteria andPdefAdditionGreaterThan(Double value) {
            addCriterion("pdef_addition >", value, "pdefAddition");
            return (Criteria) this;
        }

        public Criteria andPdefAdditionGreaterThanOrEqualTo(Double value) {
            addCriterion("pdef_addition >=", value, "pdefAddition");
            return (Criteria) this;
        }

        public Criteria andPdefAdditionLessThan(Double value) {
            addCriterion("pdef_addition <", value, "pdefAddition");
            return (Criteria) this;
        }

        public Criteria andPdefAdditionLessThanOrEqualTo(Double value) {
            addCriterion("pdef_addition <=", value, "pdefAddition");
            return (Criteria) this;
        }

        public Criteria andPdefAdditionIn(List<Double> values) {
            addCriterion("pdef_addition in", values, "pdefAddition");
            return (Criteria) this;
        }

        public Criteria andPdefAdditionNotIn(List<Double> values) {
            addCriterion("pdef_addition not in", values, "pdefAddition");
            return (Criteria) this;
        }

        public Criteria andPdefAdditionBetween(Double value1, Double value2) {
            addCriterion("pdef_addition between", value1, value2, "pdefAddition");
            return (Criteria) this;
        }

        public Criteria andPdefAdditionNotBetween(Double value1, Double value2) {
            addCriterion("pdef_addition not between", value1, value2, "pdefAddition");
            return (Criteria) this;
        }

        public Criteria andMagdefAdditionIsNull() {
            addCriterion("magdef_addition is null");
            return (Criteria) this;
        }

        public Criteria andMagdefAdditionIsNotNull() {
            addCriterion("magdef_addition is not null");
            return (Criteria) this;
        }

        public Criteria andMagdefAdditionEqualTo(Double value) {
            addCriterion("magdef_addition =", value, "magdefAddition");
            return (Criteria) this;
        }

        public Criteria andMagdefAdditionNotEqualTo(Double value) {
            addCriterion("magdef_addition <>", value, "magdefAddition");
            return (Criteria) this;
        }

        public Criteria andMagdefAdditionGreaterThan(Double value) {
            addCriterion("magdef_addition >", value, "magdefAddition");
            return (Criteria) this;
        }

        public Criteria andMagdefAdditionGreaterThanOrEqualTo(Double value) {
            addCriterion("magdef_addition >=", value, "magdefAddition");
            return (Criteria) this;
        }

        public Criteria andMagdefAdditionLessThan(Double value) {
            addCriterion("magdef_addition <", value, "magdefAddition");
            return (Criteria) this;
        }

        public Criteria andMagdefAdditionLessThanOrEqualTo(Double value) {
            addCriterion("magdef_addition <=", value, "magdefAddition");
            return (Criteria) this;
        }

        public Criteria andMagdefAdditionIn(List<Double> values) {
            addCriterion("magdef_addition in", values, "magdefAddition");
            return (Criteria) this;
        }

        public Criteria andMagdefAdditionNotIn(List<Double> values) {
            addCriterion("magdef_addition not in", values, "magdefAddition");
            return (Criteria) this;
        }

        public Criteria andMagdefAdditionBetween(Double value1, Double value2) {
            addCriterion("magdef_addition between", value1, value2, "magdefAddition");
            return (Criteria) this;
        }

        public Criteria andMagdefAdditionNotBetween(Double value1, Double value2) {
            addCriterion("magdef_addition not between", value1, value2, "magdefAddition");
            return (Criteria) this;
        }

        public Criteria andCritAdditionIsNull() {
            addCriterion("crit_addition is null");
            return (Criteria) this;
        }

        public Criteria andCritAdditionIsNotNull() {
            addCriterion("crit_addition is not null");
            return (Criteria) this;
        }

        public Criteria andCritAdditionEqualTo(Double value) {
            addCriterion("crit_addition =", value, "critAddition");
            return (Criteria) this;
        }

        public Criteria andCritAdditionNotEqualTo(Double value) {
            addCriterion("crit_addition <>", value, "critAddition");
            return (Criteria) this;
        }

        public Criteria andCritAdditionGreaterThan(Double value) {
            addCriterion("crit_addition >", value, "critAddition");
            return (Criteria) this;
        }

        public Criteria andCritAdditionGreaterThanOrEqualTo(Double value) {
            addCriterion("crit_addition >=", value, "critAddition");
            return (Criteria) this;
        }

        public Criteria andCritAdditionLessThan(Double value) {
            addCriterion("crit_addition <", value, "critAddition");
            return (Criteria) this;
        }

        public Criteria andCritAdditionLessThanOrEqualTo(Double value) {
            addCriterion("crit_addition <=", value, "critAddition");
            return (Criteria) this;
        }

        public Criteria andCritAdditionIn(List<Double> values) {
            addCriterion("crit_addition in", values, "critAddition");
            return (Criteria) this;
        }

        public Criteria andCritAdditionNotIn(List<Double> values) {
            addCriterion("crit_addition not in", values, "critAddition");
            return (Criteria) this;
        }

        public Criteria andCritAdditionBetween(Double value1, Double value2) {
            addCriterion("crit_addition between", value1, value2, "critAddition");
            return (Criteria) this;
        }

        public Criteria andCritAdditionNotBetween(Double value1, Double value2) {
            addCriterion("crit_addition not between", value1, value2, "critAddition");
            return (Criteria) this;
        }

        public Criteria andDodgeAdditionIsNull() {
            addCriterion("dodge_addition is null");
            return (Criteria) this;
        }

        public Criteria andDodgeAdditionIsNotNull() {
            addCriterion("dodge_addition is not null");
            return (Criteria) this;
        }

        public Criteria andDodgeAdditionEqualTo(Double value) {
            addCriterion("dodge_addition =", value, "dodgeAddition");
            return (Criteria) this;
        }

        public Criteria andDodgeAdditionNotEqualTo(Double value) {
            addCriterion("dodge_addition <>", value, "dodgeAddition");
            return (Criteria) this;
        }

        public Criteria andDodgeAdditionGreaterThan(Double value) {
            addCriterion("dodge_addition >", value, "dodgeAddition");
            return (Criteria) this;
        }

        public Criteria andDodgeAdditionGreaterThanOrEqualTo(Double value) {
            addCriterion("dodge_addition >=", value, "dodgeAddition");
            return (Criteria) this;
        }

        public Criteria andDodgeAdditionLessThan(Double value) {
            addCriterion("dodge_addition <", value, "dodgeAddition");
            return (Criteria) this;
        }

        public Criteria andDodgeAdditionLessThanOrEqualTo(Double value) {
            addCriterion("dodge_addition <=", value, "dodgeAddition");
            return (Criteria) this;
        }

        public Criteria andDodgeAdditionIn(List<Double> values) {
            addCriterion("dodge_addition in", values, "dodgeAddition");
            return (Criteria) this;
        }

        public Criteria andDodgeAdditionNotIn(List<Double> values) {
            addCriterion("dodge_addition not in", values, "dodgeAddition");
            return (Criteria) this;
        }

        public Criteria andDodgeAdditionBetween(Double value1, Double value2) {
            addCriterion("dodge_addition between", value1, value2, "dodgeAddition");
            return (Criteria) this;
        }

        public Criteria andDodgeAdditionNotBetween(Double value1, Double value2) {
            addCriterion("dodge_addition not between", value1, value2, "dodgeAddition");
            return (Criteria) this;
        }

        public Criteria andHitRateAdditionIsNull() {
            addCriterion("hit_rate_addition is null");
            return (Criteria) this;
        }

        public Criteria andHitRateAdditionIsNotNull() {
            addCriterion("hit_rate_addition is not null");
            return (Criteria) this;
        }

        public Criteria andHitRateAdditionEqualTo(Double value) {
            addCriterion("hit_rate_addition =", value, "hitRateAddition");
            return (Criteria) this;
        }

        public Criteria andHitRateAdditionNotEqualTo(Double value) {
            addCriterion("hit_rate_addition <>", value, "hitRateAddition");
            return (Criteria) this;
        }

        public Criteria andHitRateAdditionGreaterThan(Double value) {
            addCriterion("hit_rate_addition >", value, "hitRateAddition");
            return (Criteria) this;
        }

        public Criteria andHitRateAdditionGreaterThanOrEqualTo(Double value) {
            addCriterion("hit_rate_addition >=", value, "hitRateAddition");
            return (Criteria) this;
        }

        public Criteria andHitRateAdditionLessThan(Double value) {
            addCriterion("hit_rate_addition <", value, "hitRateAddition");
            return (Criteria) this;
        }

        public Criteria andHitRateAdditionLessThanOrEqualTo(Double value) {
            addCriterion("hit_rate_addition <=", value, "hitRateAddition");
            return (Criteria) this;
        }

        public Criteria andHitRateAdditionIn(List<Double> values) {
            addCriterion("hit_rate_addition in", values, "hitRateAddition");
            return (Criteria) this;
        }

        public Criteria andHitRateAdditionNotIn(List<Double> values) {
            addCriterion("hit_rate_addition not in", values, "hitRateAddition");
            return (Criteria) this;
        }

        public Criteria andHitRateAdditionBetween(Double value1, Double value2) {
            addCriterion("hit_rate_addition between", value1, value2, "hitRateAddition");
            return (Criteria) this;
        }

        public Criteria andHitRateAdditionNotBetween(Double value1, Double value2) {
            addCriterion("hit_rate_addition not between", value1, value2, "hitRateAddition");
            return (Criteria) this;
        }

        public Criteria andDefenseCritAdditionIsNull() {
            addCriterion("defense_crit_addition is null");
            return (Criteria) this;
        }

        public Criteria andDefenseCritAdditionIsNotNull() {
            addCriterion("defense_crit_addition is not null");
            return (Criteria) this;
        }

        public Criteria andDefenseCritAdditionEqualTo(Double value) {
            addCriterion("defense_crit_addition =", value, "defenseCritAddition");
            return (Criteria) this;
        }

        public Criteria andDefenseCritAdditionNotEqualTo(Double value) {
            addCriterion("defense_crit_addition <>", value, "defenseCritAddition");
            return (Criteria) this;
        }

        public Criteria andDefenseCritAdditionGreaterThan(Double value) {
            addCriterion("defense_crit_addition >", value, "defenseCritAddition");
            return (Criteria) this;
        }

        public Criteria andDefenseCritAdditionGreaterThanOrEqualTo(Double value) {
            addCriterion("defense_crit_addition >=", value, "defenseCritAddition");
            return (Criteria) this;
        }

        public Criteria andDefenseCritAdditionLessThan(Double value) {
            addCriterion("defense_crit_addition <", value, "defenseCritAddition");
            return (Criteria) this;
        }

        public Criteria andDefenseCritAdditionLessThanOrEqualTo(Double value) {
            addCriterion("defense_crit_addition <=", value, "defenseCritAddition");
            return (Criteria) this;
        }

        public Criteria andDefenseCritAdditionIn(List<Double> values) {
            addCriterion("defense_crit_addition in", values, "defenseCritAddition");
            return (Criteria) this;
        }

        public Criteria andDefenseCritAdditionNotIn(List<Double> values) {
            addCriterion("defense_crit_addition not in", values, "defenseCritAddition");
            return (Criteria) this;
        }

        public Criteria andDefenseCritAdditionBetween(Double value1, Double value2) {
            addCriterion("defense_crit_addition between", value1, value2, "defenseCritAddition");
            return (Criteria) this;
        }

        public Criteria andDefenseCritAdditionNotBetween(Double value1, Double value2) {
            addCriterion("defense_crit_addition not between", value1, value2, "defenseCritAddition");
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