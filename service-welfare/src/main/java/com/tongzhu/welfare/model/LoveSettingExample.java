package com.tongzhu.welfare.model;

import java.util.ArrayList;
import java.util.List;

public class LoveSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LoveSettingExample() {
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

        public Criteria andLoveNumIsNull() {
            addCriterion("love_num is null");
            return (Criteria) this;
        }

        public Criteria andLoveNumIsNotNull() {
            addCriterion("love_num is not null");
            return (Criteria) this;
        }

        public Criteria andLoveNumEqualTo(Integer value) {
            addCriterion("love_num =", value, "loveNum");
            return (Criteria) this;
        }

        public Criteria andLoveNumNotEqualTo(Integer value) {
            addCriterion("love_num <>", value, "loveNum");
            return (Criteria) this;
        }

        public Criteria andLoveNumGreaterThan(Integer value) {
            addCriterion("love_num >", value, "loveNum");
            return (Criteria) this;
        }

        public Criteria andLoveNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("love_num >=", value, "loveNum");
            return (Criteria) this;
        }

        public Criteria andLoveNumLessThan(Integer value) {
            addCriterion("love_num <", value, "loveNum");
            return (Criteria) this;
        }

        public Criteria andLoveNumLessThanOrEqualTo(Integer value) {
            addCriterion("love_num <=", value, "loveNum");
            return (Criteria) this;
        }

        public Criteria andLoveNumIn(List<Integer> values) {
            addCriterion("love_num in", values, "loveNum");
            return (Criteria) this;
        }

        public Criteria andLoveNumNotIn(List<Integer> values) {
            addCriterion("love_num not in", values, "loveNum");
            return (Criteria) this;
        }

        public Criteria andLoveNumBetween(Integer value1, Integer value2) {
            addCriterion("love_num between", value1, value2, "loveNum");
            return (Criteria) this;
        }

        public Criteria andLoveNumNotBetween(Integer value1, Integer value2) {
            addCriterion("love_num not between", value1, value2, "loveNum");
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

        public Criteria andCritIsNull() {
            addCriterion("crit is null");
            return (Criteria) this;
        }

        public Criteria andCritIsNotNull() {
            addCriterion("crit is not null");
            return (Criteria) this;
        }

        public Criteria andCritEqualTo(Integer value) {
            addCriterion("crit =", value, "crit");
            return (Criteria) this;
        }

        public Criteria andCritNotEqualTo(Integer value) {
            addCriterion("crit <>", value, "crit");
            return (Criteria) this;
        }

        public Criteria andCritGreaterThan(Integer value) {
            addCriterion("crit >", value, "crit");
            return (Criteria) this;
        }

        public Criteria andCritGreaterThanOrEqualTo(Integer value) {
            addCriterion("crit >=", value, "crit");
            return (Criteria) this;
        }

        public Criteria andCritLessThan(Integer value) {
            addCriterion("crit <", value, "crit");
            return (Criteria) this;
        }

        public Criteria andCritLessThanOrEqualTo(Integer value) {
            addCriterion("crit <=", value, "crit");
            return (Criteria) this;
        }

        public Criteria andCritIn(List<Integer> values) {
            addCriterion("crit in", values, "crit");
            return (Criteria) this;
        }

        public Criteria andCritNotIn(List<Integer> values) {
            addCriterion("crit not in", values, "crit");
            return (Criteria) this;
        }

        public Criteria andCritBetween(Integer value1, Integer value2) {
            addCriterion("crit between", value1, value2, "crit");
            return (Criteria) this;
        }

        public Criteria andCritNotBetween(Integer value1, Integer value2) {
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

        public Criteria andDodgeEqualTo(Integer value) {
            addCriterion("dodge =", value, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeNotEqualTo(Integer value) {
            addCriterion("dodge <>", value, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeGreaterThan(Integer value) {
            addCriterion("dodge >", value, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("dodge >=", value, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeLessThan(Integer value) {
            addCriterion("dodge <", value, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeLessThanOrEqualTo(Integer value) {
            addCriterion("dodge <=", value, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeIn(List<Integer> values) {
            addCriterion("dodge in", values, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeNotIn(List<Integer> values) {
            addCriterion("dodge not in", values, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeBetween(Integer value1, Integer value2) {
            addCriterion("dodge between", value1, value2, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeNotBetween(Integer value1, Integer value2) {
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

        public Criteria andHitRateEqualTo(Integer value) {
            addCriterion("hit_rate =", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateNotEqualTo(Integer value) {
            addCriterion("hit_rate <>", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateGreaterThan(Integer value) {
            addCriterion("hit_rate >", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("hit_rate >=", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateLessThan(Integer value) {
            addCriterion("hit_rate <", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateLessThanOrEqualTo(Integer value) {
            addCriterion("hit_rate <=", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateIn(List<Integer> values) {
            addCriterion("hit_rate in", values, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateNotIn(List<Integer> values) {
            addCriterion("hit_rate not in", values, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateBetween(Integer value1, Integer value2) {
            addCriterion("hit_rate between", value1, value2, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateNotBetween(Integer value1, Integer value2) {
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

        public Criteria andDefenseCritEqualTo(Integer value) {
            addCriterion("defense_crit =", value, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritNotEqualTo(Integer value) {
            addCriterion("defense_crit <>", value, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritGreaterThan(Integer value) {
            addCriterion("defense_crit >", value, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritGreaterThanOrEqualTo(Integer value) {
            addCriterion("defense_crit >=", value, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritLessThan(Integer value) {
            addCriterion("defense_crit <", value, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritLessThanOrEqualTo(Integer value) {
            addCriterion("defense_crit <=", value, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritIn(List<Integer> values) {
            addCriterion("defense_crit in", values, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritNotIn(List<Integer> values) {
            addCriterion("defense_crit not in", values, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritBetween(Integer value1, Integer value2) {
            addCriterion("defense_crit between", value1, value2, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritNotBetween(Integer value1, Integer value2) {
            addCriterion("defense_crit not between", value1, value2, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andPdreIsNull() {
            addCriterion("pdre is null");
            return (Criteria) this;
        }

        public Criteria andPdreIsNotNull() {
            addCriterion("pdre is not null");
            return (Criteria) this;
        }

        public Criteria andPdreEqualTo(Integer value) {
            addCriterion("pdre =", value, "pdre");
            return (Criteria) this;
        }

        public Criteria andPdreNotEqualTo(Integer value) {
            addCriterion("pdre <>", value, "pdre");
            return (Criteria) this;
        }

        public Criteria andPdreGreaterThan(Integer value) {
            addCriterion("pdre >", value, "pdre");
            return (Criteria) this;
        }

        public Criteria andPdreGreaterThanOrEqualTo(Integer value) {
            addCriterion("pdre >=", value, "pdre");
            return (Criteria) this;
        }

        public Criteria andPdreLessThan(Integer value) {
            addCriterion("pdre <", value, "pdre");
            return (Criteria) this;
        }

        public Criteria andPdreLessThanOrEqualTo(Integer value) {
            addCriterion("pdre <=", value, "pdre");
            return (Criteria) this;
        }

        public Criteria andPdreIn(List<Integer> values) {
            addCriterion("pdre in", values, "pdre");
            return (Criteria) this;
        }

        public Criteria andPdreNotIn(List<Integer> values) {
            addCriterion("pdre not in", values, "pdre");
            return (Criteria) this;
        }

        public Criteria andPdreBetween(Integer value1, Integer value2) {
            addCriterion("pdre between", value1, value2, "pdre");
            return (Criteria) this;
        }

        public Criteria andPdreNotBetween(Integer value1, Integer value2) {
            addCriterion("pdre not between", value1, value2, "pdre");
            return (Criteria) this;
        }

        public Criteria andMagdreIsNull() {
            addCriterion("magdre is null");
            return (Criteria) this;
        }

        public Criteria andMagdreIsNotNull() {
            addCriterion("magdre is not null");
            return (Criteria) this;
        }

        public Criteria andMagdreEqualTo(Integer value) {
            addCriterion("magdre =", value, "magdre");
            return (Criteria) this;
        }

        public Criteria andMagdreNotEqualTo(Integer value) {
            addCriterion("magdre <>", value, "magdre");
            return (Criteria) this;
        }

        public Criteria andMagdreGreaterThan(Integer value) {
            addCriterion("magdre >", value, "magdre");
            return (Criteria) this;
        }

        public Criteria andMagdreGreaterThanOrEqualTo(Integer value) {
            addCriterion("magdre >=", value, "magdre");
            return (Criteria) this;
        }

        public Criteria andMagdreLessThan(Integer value) {
            addCriterion("magdre <", value, "magdre");
            return (Criteria) this;
        }

        public Criteria andMagdreLessThanOrEqualTo(Integer value) {
            addCriterion("magdre <=", value, "magdre");
            return (Criteria) this;
        }

        public Criteria andMagdreIn(List<Integer> values) {
            addCriterion("magdre in", values, "magdre");
            return (Criteria) this;
        }

        public Criteria andMagdreNotIn(List<Integer> values) {
            addCriterion("magdre not in", values, "magdre");
            return (Criteria) this;
        }

        public Criteria andMagdreBetween(Integer value1, Integer value2) {
            addCriterion("magdre between", value1, value2, "magdre");
            return (Criteria) this;
        }

        public Criteria andMagdreNotBetween(Integer value1, Integer value2) {
            addCriterion("magdre not between", value1, value2, "magdre");
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