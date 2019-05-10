package com.tongzhu.user.model;

import java.util.ArrayList;
import java.util.List;

public class MonsterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MonsterExample() {
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

        public Criteria andMonsterIdIsNull() {
            addCriterion("monster_id is null");
            return (Criteria) this;
        }

        public Criteria andMonsterIdIsNotNull() {
            addCriterion("monster_id is not null");
            return (Criteria) this;
        }

        public Criteria andMonsterIdEqualTo(String value) {
            addCriterion("monster_id =", value, "monsterId");
            return (Criteria) this;
        }

        public Criteria andMonsterIdNotEqualTo(String value) {
            addCriterion("monster_id <>", value, "monsterId");
            return (Criteria) this;
        }

        public Criteria andMonsterIdGreaterThan(String value) {
            addCriterion("monster_id >", value, "monsterId");
            return (Criteria) this;
        }

        public Criteria andMonsterIdGreaterThanOrEqualTo(String value) {
            addCriterion("monster_id >=", value, "monsterId");
            return (Criteria) this;
        }

        public Criteria andMonsterIdLessThan(String value) {
            addCriterion("monster_id <", value, "monsterId");
            return (Criteria) this;
        }

        public Criteria andMonsterIdLessThanOrEqualTo(String value) {
            addCriterion("monster_id <=", value, "monsterId");
            return (Criteria) this;
        }

        public Criteria andMonsterIdLike(String value) {
            addCriterion("monster_id like", value, "monsterId");
            return (Criteria) this;
        }

        public Criteria andMonsterIdNotLike(String value) {
            addCriterion("monster_id not like", value, "monsterId");
            return (Criteria) this;
        }

        public Criteria andMonsterIdIn(List<String> values) {
            addCriterion("monster_id in", values, "monsterId");
            return (Criteria) this;
        }

        public Criteria andMonsterIdNotIn(List<String> values) {
            addCriterion("monster_id not in", values, "monsterId");
            return (Criteria) this;
        }

        public Criteria andMonsterIdBetween(String value1, String value2) {
            addCriterion("monster_id between", value1, value2, "monsterId");
            return (Criteria) this;
        }

        public Criteria andMonsterIdNotBetween(String value1, String value2) {
            addCriterion("monster_id not between", value1, value2, "monsterId");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level_ is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level_ is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("level_ =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("level_ <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("level_ >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("level_ >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("level_ <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("level_ <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("level_ in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("level_ not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("level_ between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("level_ not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name_ is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name_ is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name_ =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name_ <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name_ >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name_ >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name_ <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name_ <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name_ like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name_ not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name_ in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name_ not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name_ between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name_ not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andHeadIsNull() {
            addCriterion("head_ is null");
            return (Criteria) this;
        }

        public Criteria andHeadIsNotNull() {
            addCriterion("head_ is not null");
            return (Criteria) this;
        }

        public Criteria andHeadEqualTo(String value) {
            addCriterion("head_ =", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadNotEqualTo(String value) {
            addCriterion("head_ <>", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadGreaterThan(String value) {
            addCriterion("head_ >", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadGreaterThanOrEqualTo(String value) {
            addCriterion("head_ >=", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadLessThan(String value) {
            addCriterion("head_ <", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadLessThanOrEqualTo(String value) {
            addCriterion("head_ <=", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadLike(String value) {
            addCriterion("head_ like", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadNotLike(String value) {
            addCriterion("head_ not like", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadIn(List<String> values) {
            addCriterion("head_ in", values, "head");
            return (Criteria) this;
        }

        public Criteria andHeadNotIn(List<String> values) {
            addCriterion("head_ not in", values, "head");
            return (Criteria) this;
        }

        public Criteria andHeadBetween(String value1, String value2) {
            addCriterion("head_ between", value1, value2, "head");
            return (Criteria) this;
        }

        public Criteria andHeadNotBetween(String value1, String value2) {
            addCriterion("head_ not between", value1, value2, "head");
            return (Criteria) this;
        }

        public Criteria andModeIsNull() {
            addCriterion("mode_ is null");
            return (Criteria) this;
        }

        public Criteria andModeIsNotNull() {
            addCriterion("mode_ is not null");
            return (Criteria) this;
        }

        public Criteria andModeEqualTo(String value) {
            addCriterion("mode_ =", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotEqualTo(String value) {
            addCriterion("mode_ <>", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeGreaterThan(String value) {
            addCriterion("mode_ >", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeGreaterThanOrEqualTo(String value) {
            addCriterion("mode_ >=", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeLessThan(String value) {
            addCriterion("mode_ <", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeLessThanOrEqualTo(String value) {
            addCriterion("mode_ <=", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeLike(String value) {
            addCriterion("mode_ like", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotLike(String value) {
            addCriterion("mode_ not like", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeIn(List<String> values) {
            addCriterion("mode_ in", values, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotIn(List<String> values) {
            addCriterion("mode_ not in", values, "mode");
            return (Criteria) this;
        }

        public Criteria andModeBetween(String value1, String value2) {
            addCriterion("mode_ between", value1, value2, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotBetween(String value1, String value2) {
            addCriterion("mode_ not between", value1, value2, "mode");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type_ is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type_ is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type_ =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type_ <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type_ >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type_ >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type_ <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type_ <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type_ in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type_ not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type_ between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type_ not between", value1, value2, "type");
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

        public Criteria andPhAtkEqualTo(Float value) {
            addCriterion("ph_atk =", value, "phAtk");
            return (Criteria) this;
        }

        public Criteria andPhAtkNotEqualTo(Float value) {
            addCriterion("ph_atk <>", value, "phAtk");
            return (Criteria) this;
        }

        public Criteria andPhAtkGreaterThan(Float value) {
            addCriterion("ph_atk >", value, "phAtk");
            return (Criteria) this;
        }

        public Criteria andPhAtkGreaterThanOrEqualTo(Float value) {
            addCriterion("ph_atk >=", value, "phAtk");
            return (Criteria) this;
        }

        public Criteria andPhAtkLessThan(Float value) {
            addCriterion("ph_atk <", value, "phAtk");
            return (Criteria) this;
        }

        public Criteria andPhAtkLessThanOrEqualTo(Float value) {
            addCriterion("ph_atk <=", value, "phAtk");
            return (Criteria) this;
        }

        public Criteria andPhAtkIn(List<Float> values) {
            addCriterion("ph_atk in", values, "phAtk");
            return (Criteria) this;
        }

        public Criteria andPhAtkNotIn(List<Float> values) {
            addCriterion("ph_atk not in", values, "phAtk");
            return (Criteria) this;
        }

        public Criteria andPhAtkBetween(Float value1, Float value2) {
            addCriterion("ph_atk between", value1, value2, "phAtk");
            return (Criteria) this;
        }

        public Criteria andPhAtkNotBetween(Float value1, Float value2) {
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

        public Criteria andMfAtkEqualTo(Float value) {
            addCriterion("mf_atk =", value, "mfAtk");
            return (Criteria) this;
        }

        public Criteria andMfAtkNotEqualTo(Float value) {
            addCriterion("mf_atk <>", value, "mfAtk");
            return (Criteria) this;
        }

        public Criteria andMfAtkGreaterThan(Float value) {
            addCriterion("mf_atk >", value, "mfAtk");
            return (Criteria) this;
        }

        public Criteria andMfAtkGreaterThanOrEqualTo(Float value) {
            addCriterion("mf_atk >=", value, "mfAtk");
            return (Criteria) this;
        }

        public Criteria andMfAtkLessThan(Float value) {
            addCriterion("mf_atk <", value, "mfAtk");
            return (Criteria) this;
        }

        public Criteria andMfAtkLessThanOrEqualTo(Float value) {
            addCriterion("mf_atk <=", value, "mfAtk");
            return (Criteria) this;
        }

        public Criteria andMfAtkIn(List<Float> values) {
            addCriterion("mf_atk in", values, "mfAtk");
            return (Criteria) this;
        }

        public Criteria andMfAtkNotIn(List<Float> values) {
            addCriterion("mf_atk not in", values, "mfAtk");
            return (Criteria) this;
        }

        public Criteria andMfAtkBetween(Float value1, Float value2) {
            addCriterion("mf_atk between", value1, value2, "mfAtk");
            return (Criteria) this;
        }

        public Criteria andMfAtkNotBetween(Float value1, Float value2) {
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

        public Criteria andPhDefEqualTo(Float value) {
            addCriterion("ph_def =", value, "phDef");
            return (Criteria) this;
        }

        public Criteria andPhDefNotEqualTo(Float value) {
            addCriterion("ph_def <>", value, "phDef");
            return (Criteria) this;
        }

        public Criteria andPhDefGreaterThan(Float value) {
            addCriterion("ph_def >", value, "phDef");
            return (Criteria) this;
        }

        public Criteria andPhDefGreaterThanOrEqualTo(Float value) {
            addCriterion("ph_def >=", value, "phDef");
            return (Criteria) this;
        }

        public Criteria andPhDefLessThan(Float value) {
            addCriterion("ph_def <", value, "phDef");
            return (Criteria) this;
        }

        public Criteria andPhDefLessThanOrEqualTo(Float value) {
            addCriterion("ph_def <=", value, "phDef");
            return (Criteria) this;
        }

        public Criteria andPhDefIn(List<Float> values) {
            addCriterion("ph_def in", values, "phDef");
            return (Criteria) this;
        }

        public Criteria andPhDefNotIn(List<Float> values) {
            addCriterion("ph_def not in", values, "phDef");
            return (Criteria) this;
        }

        public Criteria andPhDefBetween(Float value1, Float value2) {
            addCriterion("ph_def between", value1, value2, "phDef");
            return (Criteria) this;
        }

        public Criteria andPhDefNotBetween(Float value1, Float value2) {
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

        public Criteria andMfDefEqualTo(Float value) {
            addCriterion("mf_def =", value, "mfDef");
            return (Criteria) this;
        }

        public Criteria andMfDefNotEqualTo(Float value) {
            addCriterion("mf_def <>", value, "mfDef");
            return (Criteria) this;
        }

        public Criteria andMfDefGreaterThan(Float value) {
            addCriterion("mf_def >", value, "mfDef");
            return (Criteria) this;
        }

        public Criteria andMfDefGreaterThanOrEqualTo(Float value) {
            addCriterion("mf_def >=", value, "mfDef");
            return (Criteria) this;
        }

        public Criteria andMfDefLessThan(Float value) {
            addCriterion("mf_def <", value, "mfDef");
            return (Criteria) this;
        }

        public Criteria andMfDefLessThanOrEqualTo(Float value) {
            addCriterion("mf_def <=", value, "mfDef");
            return (Criteria) this;
        }

        public Criteria andMfDefIn(List<Float> values) {
            addCriterion("mf_def in", values, "mfDef");
            return (Criteria) this;
        }

        public Criteria andMfDefNotIn(List<Float> values) {
            addCriterion("mf_def not in", values, "mfDef");
            return (Criteria) this;
        }

        public Criteria andMfDefBetween(Float value1, Float value2) {
            addCriterion("mf_def between", value1, value2, "mfDef");
            return (Criteria) this;
        }

        public Criteria andMfDefNotBetween(Float value1, Float value2) {
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

        public Criteria andHpEqualTo(Float value) {
            addCriterion("hp_ =", value, "hp");
            return (Criteria) this;
        }

        public Criteria andHpNotEqualTo(Float value) {
            addCriterion("hp_ <>", value, "hp");
            return (Criteria) this;
        }

        public Criteria andHpGreaterThan(Float value) {
            addCriterion("hp_ >", value, "hp");
            return (Criteria) this;
        }

        public Criteria andHpGreaterThanOrEqualTo(Float value) {
            addCriterion("hp_ >=", value, "hp");
            return (Criteria) this;
        }

        public Criteria andHpLessThan(Float value) {
            addCriterion("hp_ <", value, "hp");
            return (Criteria) this;
        }

        public Criteria andHpLessThanOrEqualTo(Float value) {
            addCriterion("hp_ <=", value, "hp");
            return (Criteria) this;
        }

        public Criteria andHpIn(List<Float> values) {
            addCriterion("hp_ in", values, "hp");
            return (Criteria) this;
        }

        public Criteria andHpNotIn(List<Float> values) {
            addCriterion("hp_ not in", values, "hp");
            return (Criteria) this;
        }

        public Criteria andHpBetween(Float value1, Float value2) {
            addCriterion("hp_ between", value1, value2, "hp");
            return (Criteria) this;
        }

        public Criteria andHpNotBetween(Float value1, Float value2) {
            addCriterion("hp_ not between", value1, value2, "hp");
            return (Criteria) this;
        }

        public Criteria andAccuracyIsNull() {
            addCriterion("accuracy is null");
            return (Criteria) this;
        }

        public Criteria andAccuracyIsNotNull() {
            addCriterion("accuracy is not null");
            return (Criteria) this;
        }

        public Criteria andAccuracyEqualTo(Float value) {
            addCriterion("accuracy =", value, "accuracy");
            return (Criteria) this;
        }

        public Criteria andAccuracyNotEqualTo(Float value) {
            addCriterion("accuracy <>", value, "accuracy");
            return (Criteria) this;
        }

        public Criteria andAccuracyGreaterThan(Float value) {
            addCriterion("accuracy >", value, "accuracy");
            return (Criteria) this;
        }

        public Criteria andAccuracyGreaterThanOrEqualTo(Float value) {
            addCriterion("accuracy >=", value, "accuracy");
            return (Criteria) this;
        }

        public Criteria andAccuracyLessThan(Float value) {
            addCriterion("accuracy <", value, "accuracy");
            return (Criteria) this;
        }

        public Criteria andAccuracyLessThanOrEqualTo(Float value) {
            addCriterion("accuracy <=", value, "accuracy");
            return (Criteria) this;
        }

        public Criteria andAccuracyIn(List<Float> values) {
            addCriterion("accuracy in", values, "accuracy");
            return (Criteria) this;
        }

        public Criteria andAccuracyNotIn(List<Float> values) {
            addCriterion("accuracy not in", values, "accuracy");
            return (Criteria) this;
        }

        public Criteria andAccuracyBetween(Float value1, Float value2) {
            addCriterion("accuracy between", value1, value2, "accuracy");
            return (Criteria) this;
        }

        public Criteria andAccuracyNotBetween(Float value1, Float value2) {
            addCriterion("accuracy not between", value1, value2, "accuracy");
            return (Criteria) this;
        }

        public Criteria andMissIsNull() {
            addCriterion("miss is null");
            return (Criteria) this;
        }

        public Criteria andMissIsNotNull() {
            addCriterion("miss is not null");
            return (Criteria) this;
        }

        public Criteria andMissEqualTo(Float value) {
            addCriterion("miss =", value, "miss");
            return (Criteria) this;
        }

        public Criteria andMissNotEqualTo(Float value) {
            addCriterion("miss <>", value, "miss");
            return (Criteria) this;
        }

        public Criteria andMissGreaterThan(Float value) {
            addCriterion("miss >", value, "miss");
            return (Criteria) this;
        }

        public Criteria andMissGreaterThanOrEqualTo(Float value) {
            addCriterion("miss >=", value, "miss");
            return (Criteria) this;
        }

        public Criteria andMissLessThan(Float value) {
            addCriterion("miss <", value, "miss");
            return (Criteria) this;
        }

        public Criteria andMissLessThanOrEqualTo(Float value) {
            addCriterion("miss <=", value, "miss");
            return (Criteria) this;
        }

        public Criteria andMissIn(List<Float> values) {
            addCriterion("miss in", values, "miss");
            return (Criteria) this;
        }

        public Criteria andMissNotIn(List<Float> values) {
            addCriterion("miss not in", values, "miss");
            return (Criteria) this;
        }

        public Criteria andMissBetween(Float value1, Float value2) {
            addCriterion("miss between", value1, value2, "miss");
            return (Criteria) this;
        }

        public Criteria andMissNotBetween(Float value1, Float value2) {
            addCriterion("miss not between", value1, value2, "miss");
            return (Criteria) this;
        }

        public Criteria andCriticalIsNull() {
            addCriterion("critical is null");
            return (Criteria) this;
        }

        public Criteria andCriticalIsNotNull() {
            addCriterion("critical is not null");
            return (Criteria) this;
        }

        public Criteria andCriticalEqualTo(Float value) {
            addCriterion("critical =", value, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalNotEqualTo(Float value) {
            addCriterion("critical <>", value, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalGreaterThan(Float value) {
            addCriterion("critical >", value, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalGreaterThanOrEqualTo(Float value) {
            addCriterion("critical >=", value, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalLessThan(Float value) {
            addCriterion("critical <", value, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalLessThanOrEqualTo(Float value) {
            addCriterion("critical <=", value, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalIn(List<Float> values) {
            addCriterion("critical in", values, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalNotIn(List<Float> values) {
            addCriterion("critical not in", values, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalBetween(Float value1, Float value2) {
            addCriterion("critical between", value1, value2, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalNotBetween(Float value1, Float value2) {
            addCriterion("critical not between", value1, value2, "critical");
            return (Criteria) this;
        }

        public Criteria andDcriticalIsNull() {
            addCriterion("dcritical is null");
            return (Criteria) this;
        }

        public Criteria andDcriticalIsNotNull() {
            addCriterion("dcritical is not null");
            return (Criteria) this;
        }

        public Criteria andDcriticalEqualTo(Float value) {
            addCriterion("dcritical =", value, "dcritical");
            return (Criteria) this;
        }

        public Criteria andDcriticalNotEqualTo(Float value) {
            addCriterion("dcritical <>", value, "dcritical");
            return (Criteria) this;
        }

        public Criteria andDcriticalGreaterThan(Float value) {
            addCriterion("dcritical >", value, "dcritical");
            return (Criteria) this;
        }

        public Criteria andDcriticalGreaterThanOrEqualTo(Float value) {
            addCriterion("dcritical >=", value, "dcritical");
            return (Criteria) this;
        }

        public Criteria andDcriticalLessThan(Float value) {
            addCriterion("dcritical <", value, "dcritical");
            return (Criteria) this;
        }

        public Criteria andDcriticalLessThanOrEqualTo(Float value) {
            addCriterion("dcritical <=", value, "dcritical");
            return (Criteria) this;
        }

        public Criteria andDcriticalIn(List<Float> values) {
            addCriterion("dcritical in", values, "dcritical");
            return (Criteria) this;
        }

        public Criteria andDcriticalNotIn(List<Float> values) {
            addCriterion("dcritical not in", values, "dcritical");
            return (Criteria) this;
        }

        public Criteria andDcriticalBetween(Float value1, Float value2) {
            addCriterion("dcritical between", value1, value2, "dcritical");
            return (Criteria) this;
        }

        public Criteria andDcriticalNotBetween(Float value1, Float value2) {
            addCriterion("dcritical not between", value1, value2, "dcritical");
            return (Criteria) this;
        }

        public Criteria andSkill1IdIsNull() {
            addCriterion("skill1_id is null");
            return (Criteria) this;
        }

        public Criteria andSkill1IdIsNotNull() {
            addCriterion("skill1_id is not null");
            return (Criteria) this;
        }

        public Criteria andSkill1IdEqualTo(Integer value) {
            addCriterion("skill1_id =", value, "skill1Id");
            return (Criteria) this;
        }

        public Criteria andSkill1IdNotEqualTo(Integer value) {
            addCriterion("skill1_id <>", value, "skill1Id");
            return (Criteria) this;
        }

        public Criteria andSkill1IdGreaterThan(Integer value) {
            addCriterion("skill1_id >", value, "skill1Id");
            return (Criteria) this;
        }

        public Criteria andSkill1IdGreaterThanOrEqualTo(Integer value) {
            addCriterion("skill1_id >=", value, "skill1Id");
            return (Criteria) this;
        }

        public Criteria andSkill1IdLessThan(Integer value) {
            addCriterion("skill1_id <", value, "skill1Id");
            return (Criteria) this;
        }

        public Criteria andSkill1IdLessThanOrEqualTo(Integer value) {
            addCriterion("skill1_id <=", value, "skill1Id");
            return (Criteria) this;
        }

        public Criteria andSkill1IdIn(List<Integer> values) {
            addCriterion("skill1_id in", values, "skill1Id");
            return (Criteria) this;
        }

        public Criteria andSkill1IdNotIn(List<Integer> values) {
            addCriterion("skill1_id not in", values, "skill1Id");
            return (Criteria) this;
        }

        public Criteria andSkill1IdBetween(Integer value1, Integer value2) {
            addCriterion("skill1_id between", value1, value2, "skill1Id");
            return (Criteria) this;
        }

        public Criteria andSkill1IdNotBetween(Integer value1, Integer value2) {
            addCriterion("skill1_id not between", value1, value2, "skill1Id");
            return (Criteria) this;
        }

        public Criteria andSkill1LvIsNull() {
            addCriterion("skill1_lv is null");
            return (Criteria) this;
        }

        public Criteria andSkill1LvIsNotNull() {
            addCriterion("skill1_lv is not null");
            return (Criteria) this;
        }

        public Criteria andSkill1LvEqualTo(Integer value) {
            addCriterion("skill1_lv =", value, "skill1Lv");
            return (Criteria) this;
        }

        public Criteria andSkill1LvNotEqualTo(Integer value) {
            addCriterion("skill1_lv <>", value, "skill1Lv");
            return (Criteria) this;
        }

        public Criteria andSkill1LvGreaterThan(Integer value) {
            addCriterion("skill1_lv >", value, "skill1Lv");
            return (Criteria) this;
        }

        public Criteria andSkill1LvGreaterThanOrEqualTo(Integer value) {
            addCriterion("skill1_lv >=", value, "skill1Lv");
            return (Criteria) this;
        }

        public Criteria andSkill1LvLessThan(Integer value) {
            addCriterion("skill1_lv <", value, "skill1Lv");
            return (Criteria) this;
        }

        public Criteria andSkill1LvLessThanOrEqualTo(Integer value) {
            addCriterion("skill1_lv <=", value, "skill1Lv");
            return (Criteria) this;
        }

        public Criteria andSkill1LvIn(List<Integer> values) {
            addCriterion("skill1_lv in", values, "skill1Lv");
            return (Criteria) this;
        }

        public Criteria andSkill1LvNotIn(List<Integer> values) {
            addCriterion("skill1_lv not in", values, "skill1Lv");
            return (Criteria) this;
        }

        public Criteria andSkill1LvBetween(Integer value1, Integer value2) {
            addCriterion("skill1_lv between", value1, value2, "skill1Lv");
            return (Criteria) this;
        }

        public Criteria andSkill1LvNotBetween(Integer value1, Integer value2) {
            addCriterion("skill1_lv not between", value1, value2, "skill1Lv");
            return (Criteria) this;
        }

        public Criteria andSkill2IdIsNull() {
            addCriterion("skill2_id is null");
            return (Criteria) this;
        }

        public Criteria andSkill2IdIsNotNull() {
            addCriterion("skill2_id is not null");
            return (Criteria) this;
        }

        public Criteria andSkill2IdEqualTo(Integer value) {
            addCriterion("skill2_id =", value, "skill2Id");
            return (Criteria) this;
        }

        public Criteria andSkill2IdNotEqualTo(Integer value) {
            addCriterion("skill2_id <>", value, "skill2Id");
            return (Criteria) this;
        }

        public Criteria andSkill2IdGreaterThan(Integer value) {
            addCriterion("skill2_id >", value, "skill2Id");
            return (Criteria) this;
        }

        public Criteria andSkill2IdGreaterThanOrEqualTo(Integer value) {
            addCriterion("skill2_id >=", value, "skill2Id");
            return (Criteria) this;
        }

        public Criteria andSkill2IdLessThan(Integer value) {
            addCriterion("skill2_id <", value, "skill2Id");
            return (Criteria) this;
        }

        public Criteria andSkill2IdLessThanOrEqualTo(Integer value) {
            addCriterion("skill2_id <=", value, "skill2Id");
            return (Criteria) this;
        }

        public Criteria andSkill2IdIn(List<Integer> values) {
            addCriterion("skill2_id in", values, "skill2Id");
            return (Criteria) this;
        }

        public Criteria andSkill2IdNotIn(List<Integer> values) {
            addCriterion("skill2_id not in", values, "skill2Id");
            return (Criteria) this;
        }

        public Criteria andSkill2IdBetween(Integer value1, Integer value2) {
            addCriterion("skill2_id between", value1, value2, "skill2Id");
            return (Criteria) this;
        }

        public Criteria andSkill2IdNotBetween(Integer value1, Integer value2) {
            addCriterion("skill2_id not between", value1, value2, "skill2Id");
            return (Criteria) this;
        }

        public Criteria andSkill2LvIsNull() {
            addCriterion("skill2_lv is null");
            return (Criteria) this;
        }

        public Criteria andSkill2LvIsNotNull() {
            addCriterion("skill2_lv is not null");
            return (Criteria) this;
        }

        public Criteria andSkill2LvEqualTo(Integer value) {
            addCriterion("skill2_lv =", value, "skill2Lv");
            return (Criteria) this;
        }

        public Criteria andSkill2LvNotEqualTo(Integer value) {
            addCriterion("skill2_lv <>", value, "skill2Lv");
            return (Criteria) this;
        }

        public Criteria andSkill2LvGreaterThan(Integer value) {
            addCriterion("skill2_lv >", value, "skill2Lv");
            return (Criteria) this;
        }

        public Criteria andSkill2LvGreaterThanOrEqualTo(Integer value) {
            addCriterion("skill2_lv >=", value, "skill2Lv");
            return (Criteria) this;
        }

        public Criteria andSkill2LvLessThan(Integer value) {
            addCriterion("skill2_lv <", value, "skill2Lv");
            return (Criteria) this;
        }

        public Criteria andSkill2LvLessThanOrEqualTo(Integer value) {
            addCriterion("skill2_lv <=", value, "skill2Lv");
            return (Criteria) this;
        }

        public Criteria andSkill2LvIn(List<Integer> values) {
            addCriterion("skill2_lv in", values, "skill2Lv");
            return (Criteria) this;
        }

        public Criteria andSkill2LvNotIn(List<Integer> values) {
            addCriterion("skill2_lv not in", values, "skill2Lv");
            return (Criteria) this;
        }

        public Criteria andSkill2LvBetween(Integer value1, Integer value2) {
            addCriterion("skill2_lv between", value1, value2, "skill2Lv");
            return (Criteria) this;
        }

        public Criteria andSkill2LvNotBetween(Integer value1, Integer value2) {
            addCriterion("skill2_lv not between", value1, value2, "skill2Lv");
            return (Criteria) this;
        }

        public Criteria andSkill3IdIsNull() {
            addCriterion("skill3_id is null");
            return (Criteria) this;
        }

        public Criteria andSkill3IdIsNotNull() {
            addCriterion("skill3_id is not null");
            return (Criteria) this;
        }

        public Criteria andSkill3IdEqualTo(Integer value) {
            addCriterion("skill3_id =", value, "skill3Id");
            return (Criteria) this;
        }

        public Criteria andSkill3IdNotEqualTo(Integer value) {
            addCriterion("skill3_id <>", value, "skill3Id");
            return (Criteria) this;
        }

        public Criteria andSkill3IdGreaterThan(Integer value) {
            addCriterion("skill3_id >", value, "skill3Id");
            return (Criteria) this;
        }

        public Criteria andSkill3IdGreaterThanOrEqualTo(Integer value) {
            addCriterion("skill3_id >=", value, "skill3Id");
            return (Criteria) this;
        }

        public Criteria andSkill3IdLessThan(Integer value) {
            addCriterion("skill3_id <", value, "skill3Id");
            return (Criteria) this;
        }

        public Criteria andSkill3IdLessThanOrEqualTo(Integer value) {
            addCriterion("skill3_id <=", value, "skill3Id");
            return (Criteria) this;
        }

        public Criteria andSkill3IdIn(List<Integer> values) {
            addCriterion("skill3_id in", values, "skill3Id");
            return (Criteria) this;
        }

        public Criteria andSkill3IdNotIn(List<Integer> values) {
            addCriterion("skill3_id not in", values, "skill3Id");
            return (Criteria) this;
        }

        public Criteria andSkill3IdBetween(Integer value1, Integer value2) {
            addCriterion("skill3_id between", value1, value2, "skill3Id");
            return (Criteria) this;
        }

        public Criteria andSkill3IdNotBetween(Integer value1, Integer value2) {
            addCriterion("skill3_id not between", value1, value2, "skill3Id");
            return (Criteria) this;
        }

        public Criteria andSkill3LvIsNull() {
            addCriterion("skill3_lv is null");
            return (Criteria) this;
        }

        public Criteria andSkill3LvIsNotNull() {
            addCriterion("skill3_lv is not null");
            return (Criteria) this;
        }

        public Criteria andSkill3LvEqualTo(Integer value) {
            addCriterion("skill3_lv =", value, "skill3Lv");
            return (Criteria) this;
        }

        public Criteria andSkill3LvNotEqualTo(Integer value) {
            addCriterion("skill3_lv <>", value, "skill3Lv");
            return (Criteria) this;
        }

        public Criteria andSkill3LvGreaterThan(Integer value) {
            addCriterion("skill3_lv >", value, "skill3Lv");
            return (Criteria) this;
        }

        public Criteria andSkill3LvGreaterThanOrEqualTo(Integer value) {
            addCriterion("skill3_lv >=", value, "skill3Lv");
            return (Criteria) this;
        }

        public Criteria andSkill3LvLessThan(Integer value) {
            addCriterion("skill3_lv <", value, "skill3Lv");
            return (Criteria) this;
        }

        public Criteria andSkill3LvLessThanOrEqualTo(Integer value) {
            addCriterion("skill3_lv <=", value, "skill3Lv");
            return (Criteria) this;
        }

        public Criteria andSkill3LvIn(List<Integer> values) {
            addCriterion("skill3_lv in", values, "skill3Lv");
            return (Criteria) this;
        }

        public Criteria andSkill3LvNotIn(List<Integer> values) {
            addCriterion("skill3_lv not in", values, "skill3Lv");
            return (Criteria) this;
        }

        public Criteria andSkill3LvBetween(Integer value1, Integer value2) {
            addCriterion("skill3_lv between", value1, value2, "skill3Lv");
            return (Criteria) this;
        }

        public Criteria andSkill3LvNotBetween(Integer value1, Integer value2) {
            addCriterion("skill3_lv not between", value1, value2, "skill3Lv");
            return (Criteria) this;
        }

        public Criteria andRealHurtIsNull() {
            addCriterion("real_hurt is null");
            return (Criteria) this;
        }

        public Criteria andRealHurtIsNotNull() {
            addCriterion("real_hurt is not null");
            return (Criteria) this;
        }

        public Criteria andRealHurtEqualTo(Float value) {
            addCriterion("real_hurt =", value, "realHurt");
            return (Criteria) this;
        }

        public Criteria andRealHurtNotEqualTo(Float value) {
            addCriterion("real_hurt <>", value, "realHurt");
            return (Criteria) this;
        }

        public Criteria andRealHurtGreaterThan(Float value) {
            addCriterion("real_hurt >", value, "realHurt");
            return (Criteria) this;
        }

        public Criteria andRealHurtGreaterThanOrEqualTo(Float value) {
            addCriterion("real_hurt >=", value, "realHurt");
            return (Criteria) this;
        }

        public Criteria andRealHurtLessThan(Float value) {
            addCriterion("real_hurt <", value, "realHurt");
            return (Criteria) this;
        }

        public Criteria andRealHurtLessThanOrEqualTo(Float value) {
            addCriterion("real_hurt <=", value, "realHurt");
            return (Criteria) this;
        }

        public Criteria andRealHurtIn(List<Float> values) {
            addCriterion("real_hurt in", values, "realHurt");
            return (Criteria) this;
        }

        public Criteria andRealHurtNotIn(List<Float> values) {
            addCriterion("real_hurt not in", values, "realHurt");
            return (Criteria) this;
        }

        public Criteria andRealHurtBetween(Float value1, Float value2) {
            addCriterion("real_hurt between", value1, value2, "realHurt");
            return (Criteria) this;
        }

        public Criteria andRealHurtNotBetween(Float value1, Float value2) {
            addCriterion("real_hurt not between", value1, value2, "realHurt");
            return (Criteria) this;
        }

        public Criteria andDropIsNull() {
            addCriterion("drop_ is null");
            return (Criteria) this;
        }

        public Criteria andDropIsNotNull() {
            addCriterion("drop_ is not null");
            return (Criteria) this;
        }

        public Criteria andDropEqualTo(String value) {
            addCriterion("drop_ =", value, "drop");
            return (Criteria) this;
        }

        public Criteria andDropNotEqualTo(String value) {
            addCriterion("drop_ <>", value, "drop");
            return (Criteria) this;
        }

        public Criteria andDropGreaterThan(String value) {
            addCriterion("drop_ >", value, "drop");
            return (Criteria) this;
        }

        public Criteria andDropGreaterThanOrEqualTo(String value) {
            addCriterion("drop_ >=", value, "drop");
            return (Criteria) this;
        }

        public Criteria andDropLessThan(String value) {
            addCriterion("drop_ <", value, "drop");
            return (Criteria) this;
        }

        public Criteria andDropLessThanOrEqualTo(String value) {
            addCriterion("drop_ <=", value, "drop");
            return (Criteria) this;
        }

        public Criteria andDropLike(String value) {
            addCriterion("drop_ like", value, "drop");
            return (Criteria) this;
        }

        public Criteria andDropNotLike(String value) {
            addCriterion("drop_ not like", value, "drop");
            return (Criteria) this;
        }

        public Criteria andDropIn(List<String> values) {
            addCriterion("drop_ in", values, "drop");
            return (Criteria) this;
        }

        public Criteria andDropNotIn(List<String> values) {
            addCriterion("drop_ not in", values, "drop");
            return (Criteria) this;
        }

        public Criteria andDropBetween(String value1, String value2) {
            addCriterion("drop_ between", value1, value2, "drop");
            return (Criteria) this;
        }

        public Criteria andDropNotBetween(String value1, String value2) {
            addCriterion("drop_ not between", value1, value2, "drop");
            return (Criteria) this;
        }

        public Criteria andTalkIsNull() {
            addCriterion("talk_ is null");
            return (Criteria) this;
        }

        public Criteria andTalkIsNotNull() {
            addCriterion("talk_ is not null");
            return (Criteria) this;
        }

        public Criteria andTalkEqualTo(String value) {
            addCriterion("talk_ =", value, "talk");
            return (Criteria) this;
        }

        public Criteria andTalkNotEqualTo(String value) {
            addCriterion("talk_ <>", value, "talk");
            return (Criteria) this;
        }

        public Criteria andTalkGreaterThan(String value) {
            addCriterion("talk_ >", value, "talk");
            return (Criteria) this;
        }

        public Criteria andTalkGreaterThanOrEqualTo(String value) {
            addCriterion("talk_ >=", value, "talk");
            return (Criteria) this;
        }

        public Criteria andTalkLessThan(String value) {
            addCriterion("talk_ <", value, "talk");
            return (Criteria) this;
        }

        public Criteria andTalkLessThanOrEqualTo(String value) {
            addCriterion("talk_ <=", value, "talk");
            return (Criteria) this;
        }

        public Criteria andTalkLike(String value) {
            addCriterion("talk_ like", value, "talk");
            return (Criteria) this;
        }

        public Criteria andTalkNotLike(String value) {
            addCriterion("talk_ not like", value, "talk");
            return (Criteria) this;
        }

        public Criteria andTalkIn(List<String> values) {
            addCriterion("talk_ in", values, "talk");
            return (Criteria) this;
        }

        public Criteria andTalkNotIn(List<String> values) {
            addCriterion("talk_ not in", values, "talk");
            return (Criteria) this;
        }

        public Criteria andTalkBetween(String value1, String value2) {
            addCriterion("talk_ between", value1, value2, "talk");
            return (Criteria) this;
        }

        public Criteria andTalkNotBetween(String value1, String value2) {
            addCriterion("talk_ not between", value1, value2, "talk");
            return (Criteria) this;
        }

        public Criteria andLimitIsNull() {
            addCriterion("limit_ is null");
            return (Criteria) this;
        }

        public Criteria andLimitIsNotNull() {
            addCriterion("limit_ is not null");
            return (Criteria) this;
        }

        public Criteria andLimitEqualTo(Integer value) {
            addCriterion("limit_ =", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitNotEqualTo(Integer value) {
            addCriterion("limit_ <>", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitGreaterThan(Integer value) {
            addCriterion("limit_ >", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("limit_ >=", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitLessThan(Integer value) {
            addCriterion("limit_ <", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitLessThanOrEqualTo(Integer value) {
            addCriterion("limit_ <=", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitIn(List<Integer> values) {
            addCriterion("limit_ in", values, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitNotIn(List<Integer> values) {
            addCriterion("limit_ not in", values, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitBetween(Integer value1, Integer value2) {
            addCriterion("limit_ between", value1, value2, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("limit_ not between", value1, value2, "limit");
            return (Criteria) this;
        }

        public Criteria andRoleTypeIsNull() {
            addCriterion("role_type is null");
            return (Criteria) this;
        }

        public Criteria andRoleTypeIsNotNull() {
            addCriterion("role_type is not null");
            return (Criteria) this;
        }

        public Criteria andRoleTypeEqualTo(Integer value) {
            addCriterion("role_type =", value, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeNotEqualTo(Integer value) {
            addCriterion("role_type <>", value, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeGreaterThan(Integer value) {
            addCriterion("role_type >", value, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("role_type >=", value, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeLessThan(Integer value) {
            addCriterion("role_type <", value, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeLessThanOrEqualTo(Integer value) {
            addCriterion("role_type <=", value, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeIn(List<Integer> values) {
            addCriterion("role_type in", values, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeNotIn(List<Integer> values) {
            addCriterion("role_type not in", values, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeBetween(Integer value1, Integer value2) {
            addCriterion("role_type between", value1, value2, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("role_type not between", value1, value2, "roleType");
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