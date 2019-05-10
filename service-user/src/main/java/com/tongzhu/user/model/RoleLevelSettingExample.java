package com.tongzhu.user.model;

import java.util.ArrayList;
import java.util.List;

public class RoleLevelSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RoleLevelSettingExample() {
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

        public Criteria andNextLevelIsNull() {
            addCriterion("next_level is null");
            return (Criteria) this;
        }

        public Criteria andNextLevelIsNotNull() {
            addCriterion("next_level is not null");
            return (Criteria) this;
        }

        public Criteria andNextLevelEqualTo(Integer value) {
            addCriterion("next_level =", value, "nextLevel");
            return (Criteria) this;
        }

        public Criteria andNextLevelNotEqualTo(Integer value) {
            addCriterion("next_level <>", value, "nextLevel");
            return (Criteria) this;
        }

        public Criteria andNextLevelGreaterThan(Integer value) {
            addCriterion("next_level >", value, "nextLevel");
            return (Criteria) this;
        }

        public Criteria andNextLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("next_level >=", value, "nextLevel");
            return (Criteria) this;
        }

        public Criteria andNextLevelLessThan(Integer value) {
            addCriterion("next_level <", value, "nextLevel");
            return (Criteria) this;
        }

        public Criteria andNextLevelLessThanOrEqualTo(Integer value) {
            addCriterion("next_level <=", value, "nextLevel");
            return (Criteria) this;
        }

        public Criteria andNextLevelIn(List<Integer> values) {
            addCriterion("next_level in", values, "nextLevel");
            return (Criteria) this;
        }

        public Criteria andNextLevelNotIn(List<Integer> values) {
            addCriterion("next_level not in", values, "nextLevel");
            return (Criteria) this;
        }

        public Criteria andNextLevelBetween(Integer value1, Integer value2) {
            addCriterion("next_level between", value1, value2, "nextLevel");
            return (Criteria) this;
        }

        public Criteria andNextLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("next_level not between", value1, value2, "nextLevel");
            return (Criteria) this;
        }

        public Criteria andExpIsNull() {
            addCriterion("exp is null");
            return (Criteria) this;
        }

        public Criteria andExpIsNotNull() {
            addCriterion("exp is not null");
            return (Criteria) this;
        }

        public Criteria andExpEqualTo(Long value) {
            addCriterion("exp =", value, "exp");
            return (Criteria) this;
        }

        public Criteria andExpNotEqualTo(Long value) {
            addCriterion("exp <>", value, "exp");
            return (Criteria) this;
        }

        public Criteria andExpGreaterThan(Long value) {
            addCriterion("exp >", value, "exp");
            return (Criteria) this;
        }

        public Criteria andExpGreaterThanOrEqualTo(Long value) {
            addCriterion("exp >=", value, "exp");
            return (Criteria) this;
        }

        public Criteria andExpLessThan(Long value) {
            addCriterion("exp <", value, "exp");
            return (Criteria) this;
        }

        public Criteria andExpLessThanOrEqualTo(Long value) {
            addCriterion("exp <=", value, "exp");
            return (Criteria) this;
        }

        public Criteria andExpIn(List<Long> values) {
            addCriterion("exp in", values, "exp");
            return (Criteria) this;
        }

        public Criteria andExpNotIn(List<Long> values) {
            addCriterion("exp not in", values, "exp");
            return (Criteria) this;
        }

        public Criteria andExpBetween(Long value1, Long value2) {
            addCriterion("exp between", value1, value2, "exp");
            return (Criteria) this;
        }

        public Criteria andExpNotBetween(Long value1, Long value2) {
            addCriterion("exp not between", value1, value2, "exp");
            return (Criteria) this;
        }

        public Criteria andWsHpIsNull() {
            addCriterion("ws_hp is null");
            return (Criteria) this;
        }

        public Criteria andWsHpIsNotNull() {
            addCriterion("ws_hp is not null");
            return (Criteria) this;
        }

        public Criteria andWsHpEqualTo(Integer value) {
            addCriterion("ws_hp =", value, "wsHp");
            return (Criteria) this;
        }

        public Criteria andWsHpNotEqualTo(Integer value) {
            addCriterion("ws_hp <>", value, "wsHp");
            return (Criteria) this;
        }

        public Criteria andWsHpGreaterThan(Integer value) {
            addCriterion("ws_hp >", value, "wsHp");
            return (Criteria) this;
        }

        public Criteria andWsHpGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_hp >=", value, "wsHp");
            return (Criteria) this;
        }

        public Criteria andWsHpLessThan(Integer value) {
            addCriterion("ws_hp <", value, "wsHp");
            return (Criteria) this;
        }

        public Criteria andWsHpLessThanOrEqualTo(Integer value) {
            addCriterion("ws_hp <=", value, "wsHp");
            return (Criteria) this;
        }

        public Criteria andWsHpIn(List<Integer> values) {
            addCriterion("ws_hp in", values, "wsHp");
            return (Criteria) this;
        }

        public Criteria andWsHpNotIn(List<Integer> values) {
            addCriterion("ws_hp not in", values, "wsHp");
            return (Criteria) this;
        }

        public Criteria andWsHpBetween(Integer value1, Integer value2) {
            addCriterion("ws_hp between", value1, value2, "wsHp");
            return (Criteria) this;
        }

        public Criteria andWsHpNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_hp not between", value1, value2, "wsHp");
            return (Criteria) this;
        }

        public Criteria andWsPhAtkIsNull() {
            addCriterion("ws_ph_atk is null");
            return (Criteria) this;
        }

        public Criteria andWsPhAtkIsNotNull() {
            addCriterion("ws_ph_atk is not null");
            return (Criteria) this;
        }

        public Criteria andWsPhAtkEqualTo(Integer value) {
            addCriterion("ws_ph_atk =", value, "wsPhAtk");
            return (Criteria) this;
        }

        public Criteria andWsPhAtkNotEqualTo(Integer value) {
            addCriterion("ws_ph_atk <>", value, "wsPhAtk");
            return (Criteria) this;
        }

        public Criteria andWsPhAtkGreaterThan(Integer value) {
            addCriterion("ws_ph_atk >", value, "wsPhAtk");
            return (Criteria) this;
        }

        public Criteria andWsPhAtkGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_ph_atk >=", value, "wsPhAtk");
            return (Criteria) this;
        }

        public Criteria andWsPhAtkLessThan(Integer value) {
            addCriterion("ws_ph_atk <", value, "wsPhAtk");
            return (Criteria) this;
        }

        public Criteria andWsPhAtkLessThanOrEqualTo(Integer value) {
            addCriterion("ws_ph_atk <=", value, "wsPhAtk");
            return (Criteria) this;
        }

        public Criteria andWsPhAtkIn(List<Integer> values) {
            addCriterion("ws_ph_atk in", values, "wsPhAtk");
            return (Criteria) this;
        }

        public Criteria andWsPhAtkNotIn(List<Integer> values) {
            addCriterion("ws_ph_atk not in", values, "wsPhAtk");
            return (Criteria) this;
        }

        public Criteria andWsPhAtkBetween(Integer value1, Integer value2) {
            addCriterion("ws_ph_atk between", value1, value2, "wsPhAtk");
            return (Criteria) this;
        }

        public Criteria andWsPhAtkNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_ph_atk not between", value1, value2, "wsPhAtk");
            return (Criteria) this;
        }

        public Criteria andWsMfAtkIsNull() {
            addCriterion("ws_mf_atk is null");
            return (Criteria) this;
        }

        public Criteria andWsMfAtkIsNotNull() {
            addCriterion("ws_mf_atk is not null");
            return (Criteria) this;
        }

        public Criteria andWsMfAtkEqualTo(Integer value) {
            addCriterion("ws_mf_atk =", value, "wsMfAtk");
            return (Criteria) this;
        }

        public Criteria andWsMfAtkNotEqualTo(Integer value) {
            addCriterion("ws_mf_atk <>", value, "wsMfAtk");
            return (Criteria) this;
        }

        public Criteria andWsMfAtkGreaterThan(Integer value) {
            addCriterion("ws_mf_atk >", value, "wsMfAtk");
            return (Criteria) this;
        }

        public Criteria andWsMfAtkGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_mf_atk >=", value, "wsMfAtk");
            return (Criteria) this;
        }

        public Criteria andWsMfAtkLessThan(Integer value) {
            addCriterion("ws_mf_atk <", value, "wsMfAtk");
            return (Criteria) this;
        }

        public Criteria andWsMfAtkLessThanOrEqualTo(Integer value) {
            addCriterion("ws_mf_atk <=", value, "wsMfAtk");
            return (Criteria) this;
        }

        public Criteria andWsMfAtkIn(List<Integer> values) {
            addCriterion("ws_mf_atk in", values, "wsMfAtk");
            return (Criteria) this;
        }

        public Criteria andWsMfAtkNotIn(List<Integer> values) {
            addCriterion("ws_mf_atk not in", values, "wsMfAtk");
            return (Criteria) this;
        }

        public Criteria andWsMfAtkBetween(Integer value1, Integer value2) {
            addCriterion("ws_mf_atk between", value1, value2, "wsMfAtk");
            return (Criteria) this;
        }

        public Criteria andWsMfAtkNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_mf_atk not between", value1, value2, "wsMfAtk");
            return (Criteria) this;
        }

        public Criteria andWsPhDefIsNull() {
            addCriterion("ws_ph_def is null");
            return (Criteria) this;
        }

        public Criteria andWsPhDefIsNotNull() {
            addCriterion("ws_ph_def is not null");
            return (Criteria) this;
        }

        public Criteria andWsPhDefEqualTo(Integer value) {
            addCriterion("ws_ph_def =", value, "wsPhDef");
            return (Criteria) this;
        }

        public Criteria andWsPhDefNotEqualTo(Integer value) {
            addCriterion("ws_ph_def <>", value, "wsPhDef");
            return (Criteria) this;
        }

        public Criteria andWsPhDefGreaterThan(Integer value) {
            addCriterion("ws_ph_def >", value, "wsPhDef");
            return (Criteria) this;
        }

        public Criteria andWsPhDefGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_ph_def >=", value, "wsPhDef");
            return (Criteria) this;
        }

        public Criteria andWsPhDefLessThan(Integer value) {
            addCriterion("ws_ph_def <", value, "wsPhDef");
            return (Criteria) this;
        }

        public Criteria andWsPhDefLessThanOrEqualTo(Integer value) {
            addCriterion("ws_ph_def <=", value, "wsPhDef");
            return (Criteria) this;
        }

        public Criteria andWsPhDefIn(List<Integer> values) {
            addCriterion("ws_ph_def in", values, "wsPhDef");
            return (Criteria) this;
        }

        public Criteria andWsPhDefNotIn(List<Integer> values) {
            addCriterion("ws_ph_def not in", values, "wsPhDef");
            return (Criteria) this;
        }

        public Criteria andWsPhDefBetween(Integer value1, Integer value2) {
            addCriterion("ws_ph_def between", value1, value2, "wsPhDef");
            return (Criteria) this;
        }

        public Criteria andWsPhDefNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_ph_def not between", value1, value2, "wsPhDef");
            return (Criteria) this;
        }

        public Criteria andWsMfDefIsNull() {
            addCriterion("ws_mf_def is null");
            return (Criteria) this;
        }

        public Criteria andWsMfDefIsNotNull() {
            addCriterion("ws_mf_def is not null");
            return (Criteria) this;
        }

        public Criteria andWsMfDefEqualTo(Integer value) {
            addCriterion("ws_mf_def =", value, "wsMfDef");
            return (Criteria) this;
        }

        public Criteria andWsMfDefNotEqualTo(Integer value) {
            addCriterion("ws_mf_def <>", value, "wsMfDef");
            return (Criteria) this;
        }

        public Criteria andWsMfDefGreaterThan(Integer value) {
            addCriterion("ws_mf_def >", value, "wsMfDef");
            return (Criteria) this;
        }

        public Criteria andWsMfDefGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_mf_def >=", value, "wsMfDef");
            return (Criteria) this;
        }

        public Criteria andWsMfDefLessThan(Integer value) {
            addCriterion("ws_mf_def <", value, "wsMfDef");
            return (Criteria) this;
        }

        public Criteria andWsMfDefLessThanOrEqualTo(Integer value) {
            addCriterion("ws_mf_def <=", value, "wsMfDef");
            return (Criteria) this;
        }

        public Criteria andWsMfDefIn(List<Integer> values) {
            addCriterion("ws_mf_def in", values, "wsMfDef");
            return (Criteria) this;
        }

        public Criteria andWsMfDefNotIn(List<Integer> values) {
            addCriterion("ws_mf_def not in", values, "wsMfDef");
            return (Criteria) this;
        }

        public Criteria andWsMfDefBetween(Integer value1, Integer value2) {
            addCriterion("ws_mf_def between", value1, value2, "wsMfDef");
            return (Criteria) this;
        }

        public Criteria andWsMfDefNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_mf_def not between", value1, value2, "wsMfDef");
            return (Criteria) this;
        }

        public Criteria andWsAccuracyIsNull() {
            addCriterion("ws_accuracy is null");
            return (Criteria) this;
        }

        public Criteria andWsAccuracyIsNotNull() {
            addCriterion("ws_accuracy is not null");
            return (Criteria) this;
        }

        public Criteria andWsAccuracyEqualTo(Integer value) {
            addCriterion("ws_accuracy =", value, "wsAccuracy");
            return (Criteria) this;
        }

        public Criteria andWsAccuracyNotEqualTo(Integer value) {
            addCriterion("ws_accuracy <>", value, "wsAccuracy");
            return (Criteria) this;
        }

        public Criteria andWsAccuracyGreaterThan(Integer value) {
            addCriterion("ws_accuracy >", value, "wsAccuracy");
            return (Criteria) this;
        }

        public Criteria andWsAccuracyGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_accuracy >=", value, "wsAccuracy");
            return (Criteria) this;
        }

        public Criteria andWsAccuracyLessThan(Integer value) {
            addCriterion("ws_accuracy <", value, "wsAccuracy");
            return (Criteria) this;
        }

        public Criteria andWsAccuracyLessThanOrEqualTo(Integer value) {
            addCriterion("ws_accuracy <=", value, "wsAccuracy");
            return (Criteria) this;
        }

        public Criteria andWsAccuracyIn(List<Integer> values) {
            addCriterion("ws_accuracy in", values, "wsAccuracy");
            return (Criteria) this;
        }

        public Criteria andWsAccuracyNotIn(List<Integer> values) {
            addCriterion("ws_accuracy not in", values, "wsAccuracy");
            return (Criteria) this;
        }

        public Criteria andWsAccuracyBetween(Integer value1, Integer value2) {
            addCriterion("ws_accuracy between", value1, value2, "wsAccuracy");
            return (Criteria) this;
        }

        public Criteria andWsAccuracyNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_accuracy not between", value1, value2, "wsAccuracy");
            return (Criteria) this;
        }

        public Criteria andWsMissIsNull() {
            addCriterion("ws_miss is null");
            return (Criteria) this;
        }

        public Criteria andWsMissIsNotNull() {
            addCriterion("ws_miss is not null");
            return (Criteria) this;
        }

        public Criteria andWsMissEqualTo(Integer value) {
            addCriterion("ws_miss =", value, "wsMiss");
            return (Criteria) this;
        }

        public Criteria andWsMissNotEqualTo(Integer value) {
            addCriterion("ws_miss <>", value, "wsMiss");
            return (Criteria) this;
        }

        public Criteria andWsMissGreaterThan(Integer value) {
            addCriterion("ws_miss >", value, "wsMiss");
            return (Criteria) this;
        }

        public Criteria andWsMissGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_miss >=", value, "wsMiss");
            return (Criteria) this;
        }

        public Criteria andWsMissLessThan(Integer value) {
            addCriterion("ws_miss <", value, "wsMiss");
            return (Criteria) this;
        }

        public Criteria andWsMissLessThanOrEqualTo(Integer value) {
            addCriterion("ws_miss <=", value, "wsMiss");
            return (Criteria) this;
        }

        public Criteria andWsMissIn(List<Integer> values) {
            addCriterion("ws_miss in", values, "wsMiss");
            return (Criteria) this;
        }

        public Criteria andWsMissNotIn(List<Integer> values) {
            addCriterion("ws_miss not in", values, "wsMiss");
            return (Criteria) this;
        }

        public Criteria andWsMissBetween(Integer value1, Integer value2) {
            addCriterion("ws_miss between", value1, value2, "wsMiss");
            return (Criteria) this;
        }

        public Criteria andWsMissNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_miss not between", value1, value2, "wsMiss");
            return (Criteria) this;
        }

        public Criteria andWsCriticalIsNull() {
            addCriterion("ws_critical is null");
            return (Criteria) this;
        }

        public Criteria andWsCriticalIsNotNull() {
            addCriterion("ws_critical is not null");
            return (Criteria) this;
        }

        public Criteria andWsCriticalEqualTo(Integer value) {
            addCriterion("ws_critical =", value, "wsCritical");
            return (Criteria) this;
        }

        public Criteria andWsCriticalNotEqualTo(Integer value) {
            addCriterion("ws_critical <>", value, "wsCritical");
            return (Criteria) this;
        }

        public Criteria andWsCriticalGreaterThan(Integer value) {
            addCriterion("ws_critical >", value, "wsCritical");
            return (Criteria) this;
        }

        public Criteria andWsCriticalGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_critical >=", value, "wsCritical");
            return (Criteria) this;
        }

        public Criteria andWsCriticalLessThan(Integer value) {
            addCriterion("ws_critical <", value, "wsCritical");
            return (Criteria) this;
        }

        public Criteria andWsCriticalLessThanOrEqualTo(Integer value) {
            addCriterion("ws_critical <=", value, "wsCritical");
            return (Criteria) this;
        }

        public Criteria andWsCriticalIn(List<Integer> values) {
            addCriterion("ws_critical in", values, "wsCritical");
            return (Criteria) this;
        }

        public Criteria andWsCriticalNotIn(List<Integer> values) {
            addCriterion("ws_critical not in", values, "wsCritical");
            return (Criteria) this;
        }

        public Criteria andWsCriticalBetween(Integer value1, Integer value2) {
            addCriterion("ws_critical between", value1, value2, "wsCritical");
            return (Criteria) this;
        }

        public Criteria andWsCriticalNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_critical not between", value1, value2, "wsCritical");
            return (Criteria) this;
        }

        public Criteria andWsDcriticalIsNull() {
            addCriterion("ws_dcritical is null");
            return (Criteria) this;
        }

        public Criteria andWsDcriticalIsNotNull() {
            addCriterion("ws_dcritical is not null");
            return (Criteria) this;
        }

        public Criteria andWsDcriticalEqualTo(Integer value) {
            addCriterion("ws_dcritical =", value, "wsDcritical");
            return (Criteria) this;
        }

        public Criteria andWsDcriticalNotEqualTo(Integer value) {
            addCriterion("ws_dcritical <>", value, "wsDcritical");
            return (Criteria) this;
        }

        public Criteria andWsDcriticalGreaterThan(Integer value) {
            addCriterion("ws_dcritical >", value, "wsDcritical");
            return (Criteria) this;
        }

        public Criteria andWsDcriticalGreaterThanOrEqualTo(Integer value) {
            addCriterion("ws_dcritical >=", value, "wsDcritical");
            return (Criteria) this;
        }

        public Criteria andWsDcriticalLessThan(Integer value) {
            addCriterion("ws_dcritical <", value, "wsDcritical");
            return (Criteria) this;
        }

        public Criteria andWsDcriticalLessThanOrEqualTo(Integer value) {
            addCriterion("ws_dcritical <=", value, "wsDcritical");
            return (Criteria) this;
        }

        public Criteria andWsDcriticalIn(List<Integer> values) {
            addCriterion("ws_dcritical in", values, "wsDcritical");
            return (Criteria) this;
        }

        public Criteria andWsDcriticalNotIn(List<Integer> values) {
            addCriterion("ws_dcritical not in", values, "wsDcritical");
            return (Criteria) this;
        }

        public Criteria andWsDcriticalBetween(Integer value1, Integer value2) {
            addCriterion("ws_dcritical between", value1, value2, "wsDcritical");
            return (Criteria) this;
        }

        public Criteria andWsDcriticalNotBetween(Integer value1, Integer value2) {
            addCriterion("ws_dcritical not between", value1, value2, "wsDcritical");
            return (Criteria) this;
        }

        public Criteria andCkHpIsNull() {
            addCriterion("ck_hp is null");
            return (Criteria) this;
        }

        public Criteria andCkHpIsNotNull() {
            addCriterion("ck_hp is not null");
            return (Criteria) this;
        }

        public Criteria andCkHpEqualTo(Integer value) {
            addCriterion("ck_hp =", value, "ckHp");
            return (Criteria) this;
        }

        public Criteria andCkHpNotEqualTo(Integer value) {
            addCriterion("ck_hp <>", value, "ckHp");
            return (Criteria) this;
        }

        public Criteria andCkHpGreaterThan(Integer value) {
            addCriterion("ck_hp >", value, "ckHp");
            return (Criteria) this;
        }

        public Criteria andCkHpGreaterThanOrEqualTo(Integer value) {
            addCriterion("ck_hp >=", value, "ckHp");
            return (Criteria) this;
        }

        public Criteria andCkHpLessThan(Integer value) {
            addCriterion("ck_hp <", value, "ckHp");
            return (Criteria) this;
        }

        public Criteria andCkHpLessThanOrEqualTo(Integer value) {
            addCriterion("ck_hp <=", value, "ckHp");
            return (Criteria) this;
        }

        public Criteria andCkHpIn(List<Integer> values) {
            addCriterion("ck_hp in", values, "ckHp");
            return (Criteria) this;
        }

        public Criteria andCkHpNotIn(List<Integer> values) {
            addCriterion("ck_hp not in", values, "ckHp");
            return (Criteria) this;
        }

        public Criteria andCkHpBetween(Integer value1, Integer value2) {
            addCriterion("ck_hp between", value1, value2, "ckHp");
            return (Criteria) this;
        }

        public Criteria andCkHpNotBetween(Integer value1, Integer value2) {
            addCriterion("ck_hp not between", value1, value2, "ckHp");
            return (Criteria) this;
        }

        public Criteria andCkPhAtkIsNull() {
            addCriterion("ck_ph_atk is null");
            return (Criteria) this;
        }

        public Criteria andCkPhAtkIsNotNull() {
            addCriterion("ck_ph_atk is not null");
            return (Criteria) this;
        }

        public Criteria andCkPhAtkEqualTo(Integer value) {
            addCriterion("ck_ph_atk =", value, "ckPhAtk");
            return (Criteria) this;
        }

        public Criteria andCkPhAtkNotEqualTo(Integer value) {
            addCriterion("ck_ph_atk <>", value, "ckPhAtk");
            return (Criteria) this;
        }

        public Criteria andCkPhAtkGreaterThan(Integer value) {
            addCriterion("ck_ph_atk >", value, "ckPhAtk");
            return (Criteria) this;
        }

        public Criteria andCkPhAtkGreaterThanOrEqualTo(Integer value) {
            addCriterion("ck_ph_atk >=", value, "ckPhAtk");
            return (Criteria) this;
        }

        public Criteria andCkPhAtkLessThan(Integer value) {
            addCriterion("ck_ph_atk <", value, "ckPhAtk");
            return (Criteria) this;
        }

        public Criteria andCkPhAtkLessThanOrEqualTo(Integer value) {
            addCriterion("ck_ph_atk <=", value, "ckPhAtk");
            return (Criteria) this;
        }

        public Criteria andCkPhAtkIn(List<Integer> values) {
            addCriterion("ck_ph_atk in", values, "ckPhAtk");
            return (Criteria) this;
        }

        public Criteria andCkPhAtkNotIn(List<Integer> values) {
            addCriterion("ck_ph_atk not in", values, "ckPhAtk");
            return (Criteria) this;
        }

        public Criteria andCkPhAtkBetween(Integer value1, Integer value2) {
            addCriterion("ck_ph_atk between", value1, value2, "ckPhAtk");
            return (Criteria) this;
        }

        public Criteria andCkPhAtkNotBetween(Integer value1, Integer value2) {
            addCriterion("ck_ph_atk not between", value1, value2, "ckPhAtk");
            return (Criteria) this;
        }

        public Criteria andCkMfAtkIsNull() {
            addCriterion("ck_mf_atk is null");
            return (Criteria) this;
        }

        public Criteria andCkMfAtkIsNotNull() {
            addCriterion("ck_mf_atk is not null");
            return (Criteria) this;
        }

        public Criteria andCkMfAtkEqualTo(Integer value) {
            addCriterion("ck_mf_atk =", value, "ckMfAtk");
            return (Criteria) this;
        }

        public Criteria andCkMfAtkNotEqualTo(Integer value) {
            addCriterion("ck_mf_atk <>", value, "ckMfAtk");
            return (Criteria) this;
        }

        public Criteria andCkMfAtkGreaterThan(Integer value) {
            addCriterion("ck_mf_atk >", value, "ckMfAtk");
            return (Criteria) this;
        }

        public Criteria andCkMfAtkGreaterThanOrEqualTo(Integer value) {
            addCriterion("ck_mf_atk >=", value, "ckMfAtk");
            return (Criteria) this;
        }

        public Criteria andCkMfAtkLessThan(Integer value) {
            addCriterion("ck_mf_atk <", value, "ckMfAtk");
            return (Criteria) this;
        }

        public Criteria andCkMfAtkLessThanOrEqualTo(Integer value) {
            addCriterion("ck_mf_atk <=", value, "ckMfAtk");
            return (Criteria) this;
        }

        public Criteria andCkMfAtkIn(List<Integer> values) {
            addCriterion("ck_mf_atk in", values, "ckMfAtk");
            return (Criteria) this;
        }

        public Criteria andCkMfAtkNotIn(List<Integer> values) {
            addCriterion("ck_mf_atk not in", values, "ckMfAtk");
            return (Criteria) this;
        }

        public Criteria andCkMfAtkBetween(Integer value1, Integer value2) {
            addCriterion("ck_mf_atk between", value1, value2, "ckMfAtk");
            return (Criteria) this;
        }

        public Criteria andCkMfAtkNotBetween(Integer value1, Integer value2) {
            addCriterion("ck_mf_atk not between", value1, value2, "ckMfAtk");
            return (Criteria) this;
        }

        public Criteria andCkPhDefIsNull() {
            addCriterion("ck_ph_def is null");
            return (Criteria) this;
        }

        public Criteria andCkPhDefIsNotNull() {
            addCriterion("ck_ph_def is not null");
            return (Criteria) this;
        }

        public Criteria andCkPhDefEqualTo(Integer value) {
            addCriterion("ck_ph_def =", value, "ckPhDef");
            return (Criteria) this;
        }

        public Criteria andCkPhDefNotEqualTo(Integer value) {
            addCriterion("ck_ph_def <>", value, "ckPhDef");
            return (Criteria) this;
        }

        public Criteria andCkPhDefGreaterThan(Integer value) {
            addCriterion("ck_ph_def >", value, "ckPhDef");
            return (Criteria) this;
        }

        public Criteria andCkPhDefGreaterThanOrEqualTo(Integer value) {
            addCriterion("ck_ph_def >=", value, "ckPhDef");
            return (Criteria) this;
        }

        public Criteria andCkPhDefLessThan(Integer value) {
            addCriterion("ck_ph_def <", value, "ckPhDef");
            return (Criteria) this;
        }

        public Criteria andCkPhDefLessThanOrEqualTo(Integer value) {
            addCriterion("ck_ph_def <=", value, "ckPhDef");
            return (Criteria) this;
        }

        public Criteria andCkPhDefIn(List<Integer> values) {
            addCriterion("ck_ph_def in", values, "ckPhDef");
            return (Criteria) this;
        }

        public Criteria andCkPhDefNotIn(List<Integer> values) {
            addCriterion("ck_ph_def not in", values, "ckPhDef");
            return (Criteria) this;
        }

        public Criteria andCkPhDefBetween(Integer value1, Integer value2) {
            addCriterion("ck_ph_def between", value1, value2, "ckPhDef");
            return (Criteria) this;
        }

        public Criteria andCkPhDefNotBetween(Integer value1, Integer value2) {
            addCriterion("ck_ph_def not between", value1, value2, "ckPhDef");
            return (Criteria) this;
        }

        public Criteria andCkMfDefIsNull() {
            addCriterion("ck_mf_def is null");
            return (Criteria) this;
        }

        public Criteria andCkMfDefIsNotNull() {
            addCriterion("ck_mf_def is not null");
            return (Criteria) this;
        }

        public Criteria andCkMfDefEqualTo(Integer value) {
            addCriterion("ck_mf_def =", value, "ckMfDef");
            return (Criteria) this;
        }

        public Criteria andCkMfDefNotEqualTo(Integer value) {
            addCriterion("ck_mf_def <>", value, "ckMfDef");
            return (Criteria) this;
        }

        public Criteria andCkMfDefGreaterThan(Integer value) {
            addCriterion("ck_mf_def >", value, "ckMfDef");
            return (Criteria) this;
        }

        public Criteria andCkMfDefGreaterThanOrEqualTo(Integer value) {
            addCriterion("ck_mf_def >=", value, "ckMfDef");
            return (Criteria) this;
        }

        public Criteria andCkMfDefLessThan(Integer value) {
            addCriterion("ck_mf_def <", value, "ckMfDef");
            return (Criteria) this;
        }

        public Criteria andCkMfDefLessThanOrEqualTo(Integer value) {
            addCriterion("ck_mf_def <=", value, "ckMfDef");
            return (Criteria) this;
        }

        public Criteria andCkMfDefIn(List<Integer> values) {
            addCriterion("ck_mf_def in", values, "ckMfDef");
            return (Criteria) this;
        }

        public Criteria andCkMfDefNotIn(List<Integer> values) {
            addCriterion("ck_mf_def not in", values, "ckMfDef");
            return (Criteria) this;
        }

        public Criteria andCkMfDefBetween(Integer value1, Integer value2) {
            addCriterion("ck_mf_def between", value1, value2, "ckMfDef");
            return (Criteria) this;
        }

        public Criteria andCkMfDefNotBetween(Integer value1, Integer value2) {
            addCriterion("ck_mf_def not between", value1, value2, "ckMfDef");
            return (Criteria) this;
        }

        public Criteria andCkAccuracyIsNull() {
            addCriterion("ck_accuracy is null");
            return (Criteria) this;
        }

        public Criteria andCkAccuracyIsNotNull() {
            addCriterion("ck_accuracy is not null");
            return (Criteria) this;
        }

        public Criteria andCkAccuracyEqualTo(Integer value) {
            addCriterion("ck_accuracy =", value, "ckAccuracy");
            return (Criteria) this;
        }

        public Criteria andCkAccuracyNotEqualTo(Integer value) {
            addCriterion("ck_accuracy <>", value, "ckAccuracy");
            return (Criteria) this;
        }

        public Criteria andCkAccuracyGreaterThan(Integer value) {
            addCriterion("ck_accuracy >", value, "ckAccuracy");
            return (Criteria) this;
        }

        public Criteria andCkAccuracyGreaterThanOrEqualTo(Integer value) {
            addCriterion("ck_accuracy >=", value, "ckAccuracy");
            return (Criteria) this;
        }

        public Criteria andCkAccuracyLessThan(Integer value) {
            addCriterion("ck_accuracy <", value, "ckAccuracy");
            return (Criteria) this;
        }

        public Criteria andCkAccuracyLessThanOrEqualTo(Integer value) {
            addCriterion("ck_accuracy <=", value, "ckAccuracy");
            return (Criteria) this;
        }

        public Criteria andCkAccuracyIn(List<Integer> values) {
            addCriterion("ck_accuracy in", values, "ckAccuracy");
            return (Criteria) this;
        }

        public Criteria andCkAccuracyNotIn(List<Integer> values) {
            addCriterion("ck_accuracy not in", values, "ckAccuracy");
            return (Criteria) this;
        }

        public Criteria andCkAccuracyBetween(Integer value1, Integer value2) {
            addCriterion("ck_accuracy between", value1, value2, "ckAccuracy");
            return (Criteria) this;
        }

        public Criteria andCkAccuracyNotBetween(Integer value1, Integer value2) {
            addCriterion("ck_accuracy not between", value1, value2, "ckAccuracy");
            return (Criteria) this;
        }

        public Criteria andCkMissIsNull() {
            addCriterion("ck_miss is null");
            return (Criteria) this;
        }

        public Criteria andCkMissIsNotNull() {
            addCriterion("ck_miss is not null");
            return (Criteria) this;
        }

        public Criteria andCkMissEqualTo(Integer value) {
            addCriterion("ck_miss =", value, "ckMiss");
            return (Criteria) this;
        }

        public Criteria andCkMissNotEqualTo(Integer value) {
            addCriterion("ck_miss <>", value, "ckMiss");
            return (Criteria) this;
        }

        public Criteria andCkMissGreaterThan(Integer value) {
            addCriterion("ck_miss >", value, "ckMiss");
            return (Criteria) this;
        }

        public Criteria andCkMissGreaterThanOrEqualTo(Integer value) {
            addCriterion("ck_miss >=", value, "ckMiss");
            return (Criteria) this;
        }

        public Criteria andCkMissLessThan(Integer value) {
            addCriterion("ck_miss <", value, "ckMiss");
            return (Criteria) this;
        }

        public Criteria andCkMissLessThanOrEqualTo(Integer value) {
            addCriterion("ck_miss <=", value, "ckMiss");
            return (Criteria) this;
        }

        public Criteria andCkMissIn(List<Integer> values) {
            addCriterion("ck_miss in", values, "ckMiss");
            return (Criteria) this;
        }

        public Criteria andCkMissNotIn(List<Integer> values) {
            addCriterion("ck_miss not in", values, "ckMiss");
            return (Criteria) this;
        }

        public Criteria andCkMissBetween(Integer value1, Integer value2) {
            addCriterion("ck_miss between", value1, value2, "ckMiss");
            return (Criteria) this;
        }

        public Criteria andCkMissNotBetween(Integer value1, Integer value2) {
            addCriterion("ck_miss not between", value1, value2, "ckMiss");
            return (Criteria) this;
        }

        public Criteria andCkCriticalIsNull() {
            addCriterion("ck_critical is null");
            return (Criteria) this;
        }

        public Criteria andCkCriticalIsNotNull() {
            addCriterion("ck_critical is not null");
            return (Criteria) this;
        }

        public Criteria andCkCriticalEqualTo(Integer value) {
            addCriterion("ck_critical =", value, "ckCritical");
            return (Criteria) this;
        }

        public Criteria andCkCriticalNotEqualTo(Integer value) {
            addCriterion("ck_critical <>", value, "ckCritical");
            return (Criteria) this;
        }

        public Criteria andCkCriticalGreaterThan(Integer value) {
            addCriterion("ck_critical >", value, "ckCritical");
            return (Criteria) this;
        }

        public Criteria andCkCriticalGreaterThanOrEqualTo(Integer value) {
            addCriterion("ck_critical >=", value, "ckCritical");
            return (Criteria) this;
        }

        public Criteria andCkCriticalLessThan(Integer value) {
            addCriterion("ck_critical <", value, "ckCritical");
            return (Criteria) this;
        }

        public Criteria andCkCriticalLessThanOrEqualTo(Integer value) {
            addCriterion("ck_critical <=", value, "ckCritical");
            return (Criteria) this;
        }

        public Criteria andCkCriticalIn(List<Integer> values) {
            addCriterion("ck_critical in", values, "ckCritical");
            return (Criteria) this;
        }

        public Criteria andCkCriticalNotIn(List<Integer> values) {
            addCriterion("ck_critical not in", values, "ckCritical");
            return (Criteria) this;
        }

        public Criteria andCkCriticalBetween(Integer value1, Integer value2) {
            addCriterion("ck_critical between", value1, value2, "ckCritical");
            return (Criteria) this;
        }

        public Criteria andCkCriticalNotBetween(Integer value1, Integer value2) {
            addCriterion("ck_critical not between", value1, value2, "ckCritical");
            return (Criteria) this;
        }

        public Criteria andCkDcriticalIsNull() {
            addCriterion("ck_dcritical is null");
            return (Criteria) this;
        }

        public Criteria andCkDcriticalIsNotNull() {
            addCriterion("ck_dcritical is not null");
            return (Criteria) this;
        }

        public Criteria andCkDcriticalEqualTo(Integer value) {
            addCriterion("ck_dcritical =", value, "ckDcritical");
            return (Criteria) this;
        }

        public Criteria andCkDcriticalNotEqualTo(Integer value) {
            addCriterion("ck_dcritical <>", value, "ckDcritical");
            return (Criteria) this;
        }

        public Criteria andCkDcriticalGreaterThan(Integer value) {
            addCriterion("ck_dcritical >", value, "ckDcritical");
            return (Criteria) this;
        }

        public Criteria andCkDcriticalGreaterThanOrEqualTo(Integer value) {
            addCriterion("ck_dcritical >=", value, "ckDcritical");
            return (Criteria) this;
        }

        public Criteria andCkDcriticalLessThan(Integer value) {
            addCriterion("ck_dcritical <", value, "ckDcritical");
            return (Criteria) this;
        }

        public Criteria andCkDcriticalLessThanOrEqualTo(Integer value) {
            addCriterion("ck_dcritical <=", value, "ckDcritical");
            return (Criteria) this;
        }

        public Criteria andCkDcriticalIn(List<Integer> values) {
            addCriterion("ck_dcritical in", values, "ckDcritical");
            return (Criteria) this;
        }

        public Criteria andCkDcriticalNotIn(List<Integer> values) {
            addCriterion("ck_dcritical not in", values, "ckDcritical");
            return (Criteria) this;
        }

        public Criteria andCkDcriticalBetween(Integer value1, Integer value2) {
            addCriterion("ck_dcritical between", value1, value2, "ckDcritical");
            return (Criteria) this;
        }

        public Criteria andCkDcriticalNotBetween(Integer value1, Integer value2) {
            addCriterion("ck_dcritical not between", value1, value2, "ckDcritical");
            return (Criteria) this;
        }

        public Criteria andGjHpIsNull() {
            addCriterion("gj_hp is null");
            return (Criteria) this;
        }

        public Criteria andGjHpIsNotNull() {
            addCriterion("gj_hp is not null");
            return (Criteria) this;
        }

        public Criteria andGjHpEqualTo(Integer value) {
            addCriterion("gj_hp =", value, "gjHp");
            return (Criteria) this;
        }

        public Criteria andGjHpNotEqualTo(Integer value) {
            addCriterion("gj_hp <>", value, "gjHp");
            return (Criteria) this;
        }

        public Criteria andGjHpGreaterThan(Integer value) {
            addCriterion("gj_hp >", value, "gjHp");
            return (Criteria) this;
        }

        public Criteria andGjHpGreaterThanOrEqualTo(Integer value) {
            addCriterion("gj_hp >=", value, "gjHp");
            return (Criteria) this;
        }

        public Criteria andGjHpLessThan(Integer value) {
            addCriterion("gj_hp <", value, "gjHp");
            return (Criteria) this;
        }

        public Criteria andGjHpLessThanOrEqualTo(Integer value) {
            addCriterion("gj_hp <=", value, "gjHp");
            return (Criteria) this;
        }

        public Criteria andGjHpIn(List<Integer> values) {
            addCriterion("gj_hp in", values, "gjHp");
            return (Criteria) this;
        }

        public Criteria andGjHpNotIn(List<Integer> values) {
            addCriterion("gj_hp not in", values, "gjHp");
            return (Criteria) this;
        }

        public Criteria andGjHpBetween(Integer value1, Integer value2) {
            addCriterion("gj_hp between", value1, value2, "gjHp");
            return (Criteria) this;
        }

        public Criteria andGjHpNotBetween(Integer value1, Integer value2) {
            addCriterion("gj_hp not between", value1, value2, "gjHp");
            return (Criteria) this;
        }

        public Criteria andGjPhAtkIsNull() {
            addCriterion("gj_ph_atk is null");
            return (Criteria) this;
        }

        public Criteria andGjPhAtkIsNotNull() {
            addCriterion("gj_ph_atk is not null");
            return (Criteria) this;
        }

        public Criteria andGjPhAtkEqualTo(Integer value) {
            addCriterion("gj_ph_atk =", value, "gjPhAtk");
            return (Criteria) this;
        }

        public Criteria andGjPhAtkNotEqualTo(Integer value) {
            addCriterion("gj_ph_atk <>", value, "gjPhAtk");
            return (Criteria) this;
        }

        public Criteria andGjPhAtkGreaterThan(Integer value) {
            addCriterion("gj_ph_atk >", value, "gjPhAtk");
            return (Criteria) this;
        }

        public Criteria andGjPhAtkGreaterThanOrEqualTo(Integer value) {
            addCriterion("gj_ph_atk >=", value, "gjPhAtk");
            return (Criteria) this;
        }

        public Criteria andGjPhAtkLessThan(Integer value) {
            addCriterion("gj_ph_atk <", value, "gjPhAtk");
            return (Criteria) this;
        }

        public Criteria andGjPhAtkLessThanOrEqualTo(Integer value) {
            addCriterion("gj_ph_atk <=", value, "gjPhAtk");
            return (Criteria) this;
        }

        public Criteria andGjPhAtkIn(List<Integer> values) {
            addCriterion("gj_ph_atk in", values, "gjPhAtk");
            return (Criteria) this;
        }

        public Criteria andGjPhAtkNotIn(List<Integer> values) {
            addCriterion("gj_ph_atk not in", values, "gjPhAtk");
            return (Criteria) this;
        }

        public Criteria andGjPhAtkBetween(Integer value1, Integer value2) {
            addCriterion("gj_ph_atk between", value1, value2, "gjPhAtk");
            return (Criteria) this;
        }

        public Criteria andGjPhAtkNotBetween(Integer value1, Integer value2) {
            addCriterion("gj_ph_atk not between", value1, value2, "gjPhAtk");
            return (Criteria) this;
        }

        public Criteria andGjMfAtkIsNull() {
            addCriterion("gj_mf_atk is null");
            return (Criteria) this;
        }

        public Criteria andGjMfAtkIsNotNull() {
            addCriterion("gj_mf_atk is not null");
            return (Criteria) this;
        }

        public Criteria andGjMfAtkEqualTo(Integer value) {
            addCriterion("gj_mf_atk =", value, "gjMfAtk");
            return (Criteria) this;
        }

        public Criteria andGjMfAtkNotEqualTo(Integer value) {
            addCriterion("gj_mf_atk <>", value, "gjMfAtk");
            return (Criteria) this;
        }

        public Criteria andGjMfAtkGreaterThan(Integer value) {
            addCriterion("gj_mf_atk >", value, "gjMfAtk");
            return (Criteria) this;
        }

        public Criteria andGjMfAtkGreaterThanOrEqualTo(Integer value) {
            addCriterion("gj_mf_atk >=", value, "gjMfAtk");
            return (Criteria) this;
        }

        public Criteria andGjMfAtkLessThan(Integer value) {
            addCriterion("gj_mf_atk <", value, "gjMfAtk");
            return (Criteria) this;
        }

        public Criteria andGjMfAtkLessThanOrEqualTo(Integer value) {
            addCriterion("gj_mf_atk <=", value, "gjMfAtk");
            return (Criteria) this;
        }

        public Criteria andGjMfAtkIn(List<Integer> values) {
            addCriterion("gj_mf_atk in", values, "gjMfAtk");
            return (Criteria) this;
        }

        public Criteria andGjMfAtkNotIn(List<Integer> values) {
            addCriterion("gj_mf_atk not in", values, "gjMfAtk");
            return (Criteria) this;
        }

        public Criteria andGjMfAtkBetween(Integer value1, Integer value2) {
            addCriterion("gj_mf_atk between", value1, value2, "gjMfAtk");
            return (Criteria) this;
        }

        public Criteria andGjMfAtkNotBetween(Integer value1, Integer value2) {
            addCriterion("gj_mf_atk not between", value1, value2, "gjMfAtk");
            return (Criteria) this;
        }

        public Criteria andGjPhDefIsNull() {
            addCriterion("gj_ph_def is null");
            return (Criteria) this;
        }

        public Criteria andGjPhDefIsNotNull() {
            addCriterion("gj_ph_def is not null");
            return (Criteria) this;
        }

        public Criteria andGjPhDefEqualTo(Integer value) {
            addCriterion("gj_ph_def =", value, "gjPhDef");
            return (Criteria) this;
        }

        public Criteria andGjPhDefNotEqualTo(Integer value) {
            addCriterion("gj_ph_def <>", value, "gjPhDef");
            return (Criteria) this;
        }

        public Criteria andGjPhDefGreaterThan(Integer value) {
            addCriterion("gj_ph_def >", value, "gjPhDef");
            return (Criteria) this;
        }

        public Criteria andGjPhDefGreaterThanOrEqualTo(Integer value) {
            addCriterion("gj_ph_def >=", value, "gjPhDef");
            return (Criteria) this;
        }

        public Criteria andGjPhDefLessThan(Integer value) {
            addCriterion("gj_ph_def <", value, "gjPhDef");
            return (Criteria) this;
        }

        public Criteria andGjPhDefLessThanOrEqualTo(Integer value) {
            addCriterion("gj_ph_def <=", value, "gjPhDef");
            return (Criteria) this;
        }

        public Criteria andGjPhDefIn(List<Integer> values) {
            addCriterion("gj_ph_def in", values, "gjPhDef");
            return (Criteria) this;
        }

        public Criteria andGjPhDefNotIn(List<Integer> values) {
            addCriterion("gj_ph_def not in", values, "gjPhDef");
            return (Criteria) this;
        }

        public Criteria andGjPhDefBetween(Integer value1, Integer value2) {
            addCriterion("gj_ph_def between", value1, value2, "gjPhDef");
            return (Criteria) this;
        }

        public Criteria andGjPhDefNotBetween(Integer value1, Integer value2) {
            addCriterion("gj_ph_def not between", value1, value2, "gjPhDef");
            return (Criteria) this;
        }

        public Criteria andGjMfDefIsNull() {
            addCriterion("gj_mf_def is null");
            return (Criteria) this;
        }

        public Criteria andGjMfDefIsNotNull() {
            addCriterion("gj_mf_def is not null");
            return (Criteria) this;
        }

        public Criteria andGjMfDefEqualTo(Integer value) {
            addCriterion("gj_mf_def =", value, "gjMfDef");
            return (Criteria) this;
        }

        public Criteria andGjMfDefNotEqualTo(Integer value) {
            addCriterion("gj_mf_def <>", value, "gjMfDef");
            return (Criteria) this;
        }

        public Criteria andGjMfDefGreaterThan(Integer value) {
            addCriterion("gj_mf_def >", value, "gjMfDef");
            return (Criteria) this;
        }

        public Criteria andGjMfDefGreaterThanOrEqualTo(Integer value) {
            addCriterion("gj_mf_def >=", value, "gjMfDef");
            return (Criteria) this;
        }

        public Criteria andGjMfDefLessThan(Integer value) {
            addCriterion("gj_mf_def <", value, "gjMfDef");
            return (Criteria) this;
        }

        public Criteria andGjMfDefLessThanOrEqualTo(Integer value) {
            addCriterion("gj_mf_def <=", value, "gjMfDef");
            return (Criteria) this;
        }

        public Criteria andGjMfDefIn(List<Integer> values) {
            addCriterion("gj_mf_def in", values, "gjMfDef");
            return (Criteria) this;
        }

        public Criteria andGjMfDefNotIn(List<Integer> values) {
            addCriterion("gj_mf_def not in", values, "gjMfDef");
            return (Criteria) this;
        }

        public Criteria andGjMfDefBetween(Integer value1, Integer value2) {
            addCriterion("gj_mf_def between", value1, value2, "gjMfDef");
            return (Criteria) this;
        }

        public Criteria andGjMfDefNotBetween(Integer value1, Integer value2) {
            addCriterion("gj_mf_def not between", value1, value2, "gjMfDef");
            return (Criteria) this;
        }

        public Criteria andGjAccuracyIsNull() {
            addCriterion("gj_accuracy is null");
            return (Criteria) this;
        }

        public Criteria andGjAccuracyIsNotNull() {
            addCriterion("gj_accuracy is not null");
            return (Criteria) this;
        }

        public Criteria andGjAccuracyEqualTo(Integer value) {
            addCriterion("gj_accuracy =", value, "gjAccuracy");
            return (Criteria) this;
        }

        public Criteria andGjAccuracyNotEqualTo(Integer value) {
            addCriterion("gj_accuracy <>", value, "gjAccuracy");
            return (Criteria) this;
        }

        public Criteria andGjAccuracyGreaterThan(Integer value) {
            addCriterion("gj_accuracy >", value, "gjAccuracy");
            return (Criteria) this;
        }

        public Criteria andGjAccuracyGreaterThanOrEqualTo(Integer value) {
            addCriterion("gj_accuracy >=", value, "gjAccuracy");
            return (Criteria) this;
        }

        public Criteria andGjAccuracyLessThan(Integer value) {
            addCriterion("gj_accuracy <", value, "gjAccuracy");
            return (Criteria) this;
        }

        public Criteria andGjAccuracyLessThanOrEqualTo(Integer value) {
            addCriterion("gj_accuracy <=", value, "gjAccuracy");
            return (Criteria) this;
        }

        public Criteria andGjAccuracyIn(List<Integer> values) {
            addCriterion("gj_accuracy in", values, "gjAccuracy");
            return (Criteria) this;
        }

        public Criteria andGjAccuracyNotIn(List<Integer> values) {
            addCriterion("gj_accuracy not in", values, "gjAccuracy");
            return (Criteria) this;
        }

        public Criteria andGjAccuracyBetween(Integer value1, Integer value2) {
            addCriterion("gj_accuracy between", value1, value2, "gjAccuracy");
            return (Criteria) this;
        }

        public Criteria andGjAccuracyNotBetween(Integer value1, Integer value2) {
            addCriterion("gj_accuracy not between", value1, value2, "gjAccuracy");
            return (Criteria) this;
        }

        public Criteria andGjMissIsNull() {
            addCriterion("gj_miss is null");
            return (Criteria) this;
        }

        public Criteria andGjMissIsNotNull() {
            addCriterion("gj_miss is not null");
            return (Criteria) this;
        }

        public Criteria andGjMissEqualTo(Integer value) {
            addCriterion("gj_miss =", value, "gjMiss");
            return (Criteria) this;
        }

        public Criteria andGjMissNotEqualTo(Integer value) {
            addCriterion("gj_miss <>", value, "gjMiss");
            return (Criteria) this;
        }

        public Criteria andGjMissGreaterThan(Integer value) {
            addCriterion("gj_miss >", value, "gjMiss");
            return (Criteria) this;
        }

        public Criteria andGjMissGreaterThanOrEqualTo(Integer value) {
            addCriterion("gj_miss >=", value, "gjMiss");
            return (Criteria) this;
        }

        public Criteria andGjMissLessThan(Integer value) {
            addCriterion("gj_miss <", value, "gjMiss");
            return (Criteria) this;
        }

        public Criteria andGjMissLessThanOrEqualTo(Integer value) {
            addCriterion("gj_miss <=", value, "gjMiss");
            return (Criteria) this;
        }

        public Criteria andGjMissIn(List<Integer> values) {
            addCriterion("gj_miss in", values, "gjMiss");
            return (Criteria) this;
        }

        public Criteria andGjMissNotIn(List<Integer> values) {
            addCriterion("gj_miss not in", values, "gjMiss");
            return (Criteria) this;
        }

        public Criteria andGjMissBetween(Integer value1, Integer value2) {
            addCriterion("gj_miss between", value1, value2, "gjMiss");
            return (Criteria) this;
        }

        public Criteria andGjMissNotBetween(Integer value1, Integer value2) {
            addCriterion("gj_miss not between", value1, value2, "gjMiss");
            return (Criteria) this;
        }

        public Criteria andGjCriticalIsNull() {
            addCriterion("gj_critical is null");
            return (Criteria) this;
        }

        public Criteria andGjCriticalIsNotNull() {
            addCriterion("gj_critical is not null");
            return (Criteria) this;
        }

        public Criteria andGjCriticalEqualTo(Integer value) {
            addCriterion("gj_critical =", value, "gjCritical");
            return (Criteria) this;
        }

        public Criteria andGjCriticalNotEqualTo(Integer value) {
            addCriterion("gj_critical <>", value, "gjCritical");
            return (Criteria) this;
        }

        public Criteria andGjCriticalGreaterThan(Integer value) {
            addCriterion("gj_critical >", value, "gjCritical");
            return (Criteria) this;
        }

        public Criteria andGjCriticalGreaterThanOrEqualTo(Integer value) {
            addCriterion("gj_critical >=", value, "gjCritical");
            return (Criteria) this;
        }

        public Criteria andGjCriticalLessThan(Integer value) {
            addCriterion("gj_critical <", value, "gjCritical");
            return (Criteria) this;
        }

        public Criteria andGjCriticalLessThanOrEqualTo(Integer value) {
            addCriterion("gj_critical <=", value, "gjCritical");
            return (Criteria) this;
        }

        public Criteria andGjCriticalIn(List<Integer> values) {
            addCriterion("gj_critical in", values, "gjCritical");
            return (Criteria) this;
        }

        public Criteria andGjCriticalNotIn(List<Integer> values) {
            addCriterion("gj_critical not in", values, "gjCritical");
            return (Criteria) this;
        }

        public Criteria andGjCriticalBetween(Integer value1, Integer value2) {
            addCriterion("gj_critical between", value1, value2, "gjCritical");
            return (Criteria) this;
        }

        public Criteria andGjCriticalNotBetween(Integer value1, Integer value2) {
            addCriterion("gj_critical not between", value1, value2, "gjCritical");
            return (Criteria) this;
        }

        public Criteria andGjDcriticalIsNull() {
            addCriterion("gj_dcritical is null");
            return (Criteria) this;
        }

        public Criteria andGjDcriticalIsNotNull() {
            addCriterion("gj_dcritical is not null");
            return (Criteria) this;
        }

        public Criteria andGjDcriticalEqualTo(Integer value) {
            addCriterion("gj_dcritical =", value, "gjDcritical");
            return (Criteria) this;
        }

        public Criteria andGjDcriticalNotEqualTo(Integer value) {
            addCriterion("gj_dcritical <>", value, "gjDcritical");
            return (Criteria) this;
        }

        public Criteria andGjDcriticalGreaterThan(Integer value) {
            addCriterion("gj_dcritical >", value, "gjDcritical");
            return (Criteria) this;
        }

        public Criteria andGjDcriticalGreaterThanOrEqualTo(Integer value) {
            addCriterion("gj_dcritical >=", value, "gjDcritical");
            return (Criteria) this;
        }

        public Criteria andGjDcriticalLessThan(Integer value) {
            addCriterion("gj_dcritical <", value, "gjDcritical");
            return (Criteria) this;
        }

        public Criteria andGjDcriticalLessThanOrEqualTo(Integer value) {
            addCriterion("gj_dcritical <=", value, "gjDcritical");
            return (Criteria) this;
        }

        public Criteria andGjDcriticalIn(List<Integer> values) {
            addCriterion("gj_dcritical in", values, "gjDcritical");
            return (Criteria) this;
        }

        public Criteria andGjDcriticalNotIn(List<Integer> values) {
            addCriterion("gj_dcritical not in", values, "gjDcritical");
            return (Criteria) this;
        }

        public Criteria andGjDcriticalBetween(Integer value1, Integer value2) {
            addCriterion("gj_dcritical between", value1, value2, "gjDcritical");
            return (Criteria) this;
        }

        public Criteria andGjDcriticalNotBetween(Integer value1, Integer value2) {
            addCriterion("gj_dcritical not between", value1, value2, "gjDcritical");
            return (Criteria) this;
        }

        public Criteria andFsHpIsNull() {
            addCriterion("fs_hp is null");
            return (Criteria) this;
        }

        public Criteria andFsHpIsNotNull() {
            addCriterion("fs_hp is not null");
            return (Criteria) this;
        }

        public Criteria andFsHpEqualTo(Integer value) {
            addCriterion("fs_hp =", value, "fsHp");
            return (Criteria) this;
        }

        public Criteria andFsHpNotEqualTo(Integer value) {
            addCriterion("fs_hp <>", value, "fsHp");
            return (Criteria) this;
        }

        public Criteria andFsHpGreaterThan(Integer value) {
            addCriterion("fs_hp >", value, "fsHp");
            return (Criteria) this;
        }

        public Criteria andFsHpGreaterThanOrEqualTo(Integer value) {
            addCriterion("fs_hp >=", value, "fsHp");
            return (Criteria) this;
        }

        public Criteria andFsHpLessThan(Integer value) {
            addCriterion("fs_hp <", value, "fsHp");
            return (Criteria) this;
        }

        public Criteria andFsHpLessThanOrEqualTo(Integer value) {
            addCriterion("fs_hp <=", value, "fsHp");
            return (Criteria) this;
        }

        public Criteria andFsHpIn(List<Integer> values) {
            addCriterion("fs_hp in", values, "fsHp");
            return (Criteria) this;
        }

        public Criteria andFsHpNotIn(List<Integer> values) {
            addCriterion("fs_hp not in", values, "fsHp");
            return (Criteria) this;
        }

        public Criteria andFsHpBetween(Integer value1, Integer value2) {
            addCriterion("fs_hp between", value1, value2, "fsHp");
            return (Criteria) this;
        }

        public Criteria andFsHpNotBetween(Integer value1, Integer value2) {
            addCriterion("fs_hp not between", value1, value2, "fsHp");
            return (Criteria) this;
        }

        public Criteria andFsPhAtkIsNull() {
            addCriterion("fs_ph_atk is null");
            return (Criteria) this;
        }

        public Criteria andFsPhAtkIsNotNull() {
            addCriterion("fs_ph_atk is not null");
            return (Criteria) this;
        }

        public Criteria andFsPhAtkEqualTo(Integer value) {
            addCriterion("fs_ph_atk =", value, "fsPhAtk");
            return (Criteria) this;
        }

        public Criteria andFsPhAtkNotEqualTo(Integer value) {
            addCriterion("fs_ph_atk <>", value, "fsPhAtk");
            return (Criteria) this;
        }

        public Criteria andFsPhAtkGreaterThan(Integer value) {
            addCriterion("fs_ph_atk >", value, "fsPhAtk");
            return (Criteria) this;
        }

        public Criteria andFsPhAtkGreaterThanOrEqualTo(Integer value) {
            addCriterion("fs_ph_atk >=", value, "fsPhAtk");
            return (Criteria) this;
        }

        public Criteria andFsPhAtkLessThan(Integer value) {
            addCriterion("fs_ph_atk <", value, "fsPhAtk");
            return (Criteria) this;
        }

        public Criteria andFsPhAtkLessThanOrEqualTo(Integer value) {
            addCriterion("fs_ph_atk <=", value, "fsPhAtk");
            return (Criteria) this;
        }

        public Criteria andFsPhAtkIn(List<Integer> values) {
            addCriterion("fs_ph_atk in", values, "fsPhAtk");
            return (Criteria) this;
        }

        public Criteria andFsPhAtkNotIn(List<Integer> values) {
            addCriterion("fs_ph_atk not in", values, "fsPhAtk");
            return (Criteria) this;
        }

        public Criteria andFsPhAtkBetween(Integer value1, Integer value2) {
            addCriterion("fs_ph_atk between", value1, value2, "fsPhAtk");
            return (Criteria) this;
        }

        public Criteria andFsPhAtkNotBetween(Integer value1, Integer value2) {
            addCriterion("fs_ph_atk not between", value1, value2, "fsPhAtk");
            return (Criteria) this;
        }

        public Criteria andFsMfAtkIsNull() {
            addCriterion("fs_mf_atk is null");
            return (Criteria) this;
        }

        public Criteria andFsMfAtkIsNotNull() {
            addCriterion("fs_mf_atk is not null");
            return (Criteria) this;
        }

        public Criteria andFsMfAtkEqualTo(Integer value) {
            addCriterion("fs_mf_atk =", value, "fsMfAtk");
            return (Criteria) this;
        }

        public Criteria andFsMfAtkNotEqualTo(Integer value) {
            addCriterion("fs_mf_atk <>", value, "fsMfAtk");
            return (Criteria) this;
        }

        public Criteria andFsMfAtkGreaterThan(Integer value) {
            addCriterion("fs_mf_atk >", value, "fsMfAtk");
            return (Criteria) this;
        }

        public Criteria andFsMfAtkGreaterThanOrEqualTo(Integer value) {
            addCriterion("fs_mf_atk >=", value, "fsMfAtk");
            return (Criteria) this;
        }

        public Criteria andFsMfAtkLessThan(Integer value) {
            addCriterion("fs_mf_atk <", value, "fsMfAtk");
            return (Criteria) this;
        }

        public Criteria andFsMfAtkLessThanOrEqualTo(Integer value) {
            addCriterion("fs_mf_atk <=", value, "fsMfAtk");
            return (Criteria) this;
        }

        public Criteria andFsMfAtkIn(List<Integer> values) {
            addCriterion("fs_mf_atk in", values, "fsMfAtk");
            return (Criteria) this;
        }

        public Criteria andFsMfAtkNotIn(List<Integer> values) {
            addCriterion("fs_mf_atk not in", values, "fsMfAtk");
            return (Criteria) this;
        }

        public Criteria andFsMfAtkBetween(Integer value1, Integer value2) {
            addCriterion("fs_mf_atk between", value1, value2, "fsMfAtk");
            return (Criteria) this;
        }

        public Criteria andFsMfAtkNotBetween(Integer value1, Integer value2) {
            addCriterion("fs_mf_atk not between", value1, value2, "fsMfAtk");
            return (Criteria) this;
        }

        public Criteria andFsPhDefIsNull() {
            addCriterion("fs_ph_def is null");
            return (Criteria) this;
        }

        public Criteria andFsPhDefIsNotNull() {
            addCriterion("fs_ph_def is not null");
            return (Criteria) this;
        }

        public Criteria andFsPhDefEqualTo(Integer value) {
            addCriterion("fs_ph_def =", value, "fsPhDef");
            return (Criteria) this;
        }

        public Criteria andFsPhDefNotEqualTo(Integer value) {
            addCriterion("fs_ph_def <>", value, "fsPhDef");
            return (Criteria) this;
        }

        public Criteria andFsPhDefGreaterThan(Integer value) {
            addCriterion("fs_ph_def >", value, "fsPhDef");
            return (Criteria) this;
        }

        public Criteria andFsPhDefGreaterThanOrEqualTo(Integer value) {
            addCriterion("fs_ph_def >=", value, "fsPhDef");
            return (Criteria) this;
        }

        public Criteria andFsPhDefLessThan(Integer value) {
            addCriterion("fs_ph_def <", value, "fsPhDef");
            return (Criteria) this;
        }

        public Criteria andFsPhDefLessThanOrEqualTo(Integer value) {
            addCriterion("fs_ph_def <=", value, "fsPhDef");
            return (Criteria) this;
        }

        public Criteria andFsPhDefIn(List<Integer> values) {
            addCriterion("fs_ph_def in", values, "fsPhDef");
            return (Criteria) this;
        }

        public Criteria andFsPhDefNotIn(List<Integer> values) {
            addCriterion("fs_ph_def not in", values, "fsPhDef");
            return (Criteria) this;
        }

        public Criteria andFsPhDefBetween(Integer value1, Integer value2) {
            addCriterion("fs_ph_def between", value1, value2, "fsPhDef");
            return (Criteria) this;
        }

        public Criteria andFsPhDefNotBetween(Integer value1, Integer value2) {
            addCriterion("fs_ph_def not between", value1, value2, "fsPhDef");
            return (Criteria) this;
        }

        public Criteria andFsMfDefIsNull() {
            addCriterion("fs_mf_def is null");
            return (Criteria) this;
        }

        public Criteria andFsMfDefIsNotNull() {
            addCriterion("fs_mf_def is not null");
            return (Criteria) this;
        }

        public Criteria andFsMfDefEqualTo(Integer value) {
            addCriterion("fs_mf_def =", value, "fsMfDef");
            return (Criteria) this;
        }

        public Criteria andFsMfDefNotEqualTo(Integer value) {
            addCriterion("fs_mf_def <>", value, "fsMfDef");
            return (Criteria) this;
        }

        public Criteria andFsMfDefGreaterThan(Integer value) {
            addCriterion("fs_mf_def >", value, "fsMfDef");
            return (Criteria) this;
        }

        public Criteria andFsMfDefGreaterThanOrEqualTo(Integer value) {
            addCriterion("fs_mf_def >=", value, "fsMfDef");
            return (Criteria) this;
        }

        public Criteria andFsMfDefLessThan(Integer value) {
            addCriterion("fs_mf_def <", value, "fsMfDef");
            return (Criteria) this;
        }

        public Criteria andFsMfDefLessThanOrEqualTo(Integer value) {
            addCriterion("fs_mf_def <=", value, "fsMfDef");
            return (Criteria) this;
        }

        public Criteria andFsMfDefIn(List<Integer> values) {
            addCriterion("fs_mf_def in", values, "fsMfDef");
            return (Criteria) this;
        }

        public Criteria andFsMfDefNotIn(List<Integer> values) {
            addCriterion("fs_mf_def not in", values, "fsMfDef");
            return (Criteria) this;
        }

        public Criteria andFsMfDefBetween(Integer value1, Integer value2) {
            addCriterion("fs_mf_def between", value1, value2, "fsMfDef");
            return (Criteria) this;
        }

        public Criteria andFsMfDefNotBetween(Integer value1, Integer value2) {
            addCriterion("fs_mf_def not between", value1, value2, "fsMfDef");
            return (Criteria) this;
        }

        public Criteria andFsAccuracyIsNull() {
            addCriterion("fs_accuracy is null");
            return (Criteria) this;
        }

        public Criteria andFsAccuracyIsNotNull() {
            addCriterion("fs_accuracy is not null");
            return (Criteria) this;
        }

        public Criteria andFsAccuracyEqualTo(Integer value) {
            addCriterion("fs_accuracy =", value, "fsAccuracy");
            return (Criteria) this;
        }

        public Criteria andFsAccuracyNotEqualTo(Integer value) {
            addCriterion("fs_accuracy <>", value, "fsAccuracy");
            return (Criteria) this;
        }

        public Criteria andFsAccuracyGreaterThan(Integer value) {
            addCriterion("fs_accuracy >", value, "fsAccuracy");
            return (Criteria) this;
        }

        public Criteria andFsAccuracyGreaterThanOrEqualTo(Integer value) {
            addCriterion("fs_accuracy >=", value, "fsAccuracy");
            return (Criteria) this;
        }

        public Criteria andFsAccuracyLessThan(Integer value) {
            addCriterion("fs_accuracy <", value, "fsAccuracy");
            return (Criteria) this;
        }

        public Criteria andFsAccuracyLessThanOrEqualTo(Integer value) {
            addCriterion("fs_accuracy <=", value, "fsAccuracy");
            return (Criteria) this;
        }

        public Criteria andFsAccuracyIn(List<Integer> values) {
            addCriterion("fs_accuracy in", values, "fsAccuracy");
            return (Criteria) this;
        }

        public Criteria andFsAccuracyNotIn(List<Integer> values) {
            addCriterion("fs_accuracy not in", values, "fsAccuracy");
            return (Criteria) this;
        }

        public Criteria andFsAccuracyBetween(Integer value1, Integer value2) {
            addCriterion("fs_accuracy between", value1, value2, "fsAccuracy");
            return (Criteria) this;
        }

        public Criteria andFsAccuracyNotBetween(Integer value1, Integer value2) {
            addCriterion("fs_accuracy not between", value1, value2, "fsAccuracy");
            return (Criteria) this;
        }

        public Criteria andFsMissIsNull() {
            addCriterion("fs_miss is null");
            return (Criteria) this;
        }

        public Criteria andFsMissIsNotNull() {
            addCriterion("fs_miss is not null");
            return (Criteria) this;
        }

        public Criteria andFsMissEqualTo(Integer value) {
            addCriterion("fs_miss =", value, "fsMiss");
            return (Criteria) this;
        }

        public Criteria andFsMissNotEqualTo(Integer value) {
            addCriterion("fs_miss <>", value, "fsMiss");
            return (Criteria) this;
        }

        public Criteria andFsMissGreaterThan(Integer value) {
            addCriterion("fs_miss >", value, "fsMiss");
            return (Criteria) this;
        }

        public Criteria andFsMissGreaterThanOrEqualTo(Integer value) {
            addCriterion("fs_miss >=", value, "fsMiss");
            return (Criteria) this;
        }

        public Criteria andFsMissLessThan(Integer value) {
            addCriterion("fs_miss <", value, "fsMiss");
            return (Criteria) this;
        }

        public Criteria andFsMissLessThanOrEqualTo(Integer value) {
            addCriterion("fs_miss <=", value, "fsMiss");
            return (Criteria) this;
        }

        public Criteria andFsMissIn(List<Integer> values) {
            addCriterion("fs_miss in", values, "fsMiss");
            return (Criteria) this;
        }

        public Criteria andFsMissNotIn(List<Integer> values) {
            addCriterion("fs_miss not in", values, "fsMiss");
            return (Criteria) this;
        }

        public Criteria andFsMissBetween(Integer value1, Integer value2) {
            addCriterion("fs_miss between", value1, value2, "fsMiss");
            return (Criteria) this;
        }

        public Criteria andFsMissNotBetween(Integer value1, Integer value2) {
            addCriterion("fs_miss not between", value1, value2, "fsMiss");
            return (Criteria) this;
        }

        public Criteria andFsCriticalIsNull() {
            addCriterion("fs_critical is null");
            return (Criteria) this;
        }

        public Criteria andFsCriticalIsNotNull() {
            addCriterion("fs_critical is not null");
            return (Criteria) this;
        }

        public Criteria andFsCriticalEqualTo(Integer value) {
            addCriterion("fs_critical =", value, "fsCritical");
            return (Criteria) this;
        }

        public Criteria andFsCriticalNotEqualTo(Integer value) {
            addCriterion("fs_critical <>", value, "fsCritical");
            return (Criteria) this;
        }

        public Criteria andFsCriticalGreaterThan(Integer value) {
            addCriterion("fs_critical >", value, "fsCritical");
            return (Criteria) this;
        }

        public Criteria andFsCriticalGreaterThanOrEqualTo(Integer value) {
            addCriterion("fs_critical >=", value, "fsCritical");
            return (Criteria) this;
        }

        public Criteria andFsCriticalLessThan(Integer value) {
            addCriterion("fs_critical <", value, "fsCritical");
            return (Criteria) this;
        }

        public Criteria andFsCriticalLessThanOrEqualTo(Integer value) {
            addCriterion("fs_critical <=", value, "fsCritical");
            return (Criteria) this;
        }

        public Criteria andFsCriticalIn(List<Integer> values) {
            addCriterion("fs_critical in", values, "fsCritical");
            return (Criteria) this;
        }

        public Criteria andFsCriticalNotIn(List<Integer> values) {
            addCriterion("fs_critical not in", values, "fsCritical");
            return (Criteria) this;
        }

        public Criteria andFsCriticalBetween(Integer value1, Integer value2) {
            addCriterion("fs_critical between", value1, value2, "fsCritical");
            return (Criteria) this;
        }

        public Criteria andFsCriticalNotBetween(Integer value1, Integer value2) {
            addCriterion("fs_critical not between", value1, value2, "fsCritical");
            return (Criteria) this;
        }

        public Criteria andFsDcriticalIsNull() {
            addCriterion("fs_dcritical is null");
            return (Criteria) this;
        }

        public Criteria andFsDcriticalIsNotNull() {
            addCriterion("fs_dcritical is not null");
            return (Criteria) this;
        }

        public Criteria andFsDcriticalEqualTo(Integer value) {
            addCriterion("fs_dcritical =", value, "fsDcritical");
            return (Criteria) this;
        }

        public Criteria andFsDcriticalNotEqualTo(Integer value) {
            addCriterion("fs_dcritical <>", value, "fsDcritical");
            return (Criteria) this;
        }

        public Criteria andFsDcriticalGreaterThan(Integer value) {
            addCriterion("fs_dcritical >", value, "fsDcritical");
            return (Criteria) this;
        }

        public Criteria andFsDcriticalGreaterThanOrEqualTo(Integer value) {
            addCriterion("fs_dcritical >=", value, "fsDcritical");
            return (Criteria) this;
        }

        public Criteria andFsDcriticalLessThan(Integer value) {
            addCriterion("fs_dcritical <", value, "fsDcritical");
            return (Criteria) this;
        }

        public Criteria andFsDcriticalLessThanOrEqualTo(Integer value) {
            addCriterion("fs_dcritical <=", value, "fsDcritical");
            return (Criteria) this;
        }

        public Criteria andFsDcriticalIn(List<Integer> values) {
            addCriterion("fs_dcritical in", values, "fsDcritical");
            return (Criteria) this;
        }

        public Criteria andFsDcriticalNotIn(List<Integer> values) {
            addCriterion("fs_dcritical not in", values, "fsDcritical");
            return (Criteria) this;
        }

        public Criteria andFsDcriticalBetween(Integer value1, Integer value2) {
            addCriterion("fs_dcritical between", value1, value2, "fsDcritical");
            return (Criteria) this;
        }

        public Criteria andFsDcriticalNotBetween(Integer value1, Integer value2) {
            addCriterion("fs_dcritical not between", value1, value2, "fsDcritical");
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