package com.tongzhu.user.model;

import java.util.ArrayList;
import java.util.List;

public class SkillSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SkillSettingExample() {
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

        public Criteria andSkillIdIsNull() {
            addCriterion("skill_id is null");
            return (Criteria) this;
        }

        public Criteria andSkillIdIsNotNull() {
            addCriterion("skill_id is not null");
            return (Criteria) this;
        }

        public Criteria andSkillIdEqualTo(Integer value) {
            addCriterion("skill_id =", value, "skillId");
            return (Criteria) this;
        }

        public Criteria andSkillIdNotEqualTo(Integer value) {
            addCriterion("skill_id <>", value, "skillId");
            return (Criteria) this;
        }

        public Criteria andSkillIdGreaterThan(Integer value) {
            addCriterion("skill_id >", value, "skillId");
            return (Criteria) this;
        }

        public Criteria andSkillIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("skill_id >=", value, "skillId");
            return (Criteria) this;
        }

        public Criteria andSkillIdLessThan(Integer value) {
            addCriterion("skill_id <", value, "skillId");
            return (Criteria) this;
        }

        public Criteria andSkillIdLessThanOrEqualTo(Integer value) {
            addCriterion("skill_id <=", value, "skillId");
            return (Criteria) this;
        }

        public Criteria andSkillIdIn(List<Integer> values) {
            addCriterion("skill_id in", values, "skillId");
            return (Criteria) this;
        }

        public Criteria andSkillIdNotIn(List<Integer> values) {
            addCriterion("skill_id not in", values, "skillId");
            return (Criteria) this;
        }

        public Criteria andSkillIdBetween(Integer value1, Integer value2) {
            addCriterion("skill_id between", value1, value2, "skillId");
            return (Criteria) this;
        }

        public Criteria andSkillIdNotBetween(Integer value1, Integer value2) {
            addCriterion("skill_id not between", value1, value2, "skillId");
            return (Criteria) this;
        }

        public Criteria andRoleLevelIsNull() {
            addCriterion("role_level is null");
            return (Criteria) this;
        }

        public Criteria andRoleLevelIsNotNull() {
            addCriterion("role_level is not null");
            return (Criteria) this;
        }

        public Criteria andRoleLevelEqualTo(Integer value) {
            addCriterion("role_level =", value, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelNotEqualTo(Integer value) {
            addCriterion("role_level <>", value, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelGreaterThan(Integer value) {
            addCriterion("role_level >", value, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("role_level >=", value, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelLessThan(Integer value) {
            addCriterion("role_level <", value, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelLessThanOrEqualTo(Integer value) {
            addCriterion("role_level <=", value, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelIn(List<Integer> values) {
            addCriterion("role_level in", values, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelNotIn(List<Integer> values) {
            addCriterion("role_level not in", values, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelBetween(Integer value1, Integer value2) {
            addCriterion("role_level between", value1, value2, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("role_level not between", value1, value2, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andSkillLevelIsNull() {
            addCriterion("skill_level is null");
            return (Criteria) this;
        }

        public Criteria andSkillLevelIsNotNull() {
            addCriterion("skill_level is not null");
            return (Criteria) this;
        }

        public Criteria andSkillLevelEqualTo(Integer value) {
            addCriterion("skill_level =", value, "skillLevel");
            return (Criteria) this;
        }

        public Criteria andSkillLevelNotEqualTo(Integer value) {
            addCriterion("skill_level <>", value, "skillLevel");
            return (Criteria) this;
        }

        public Criteria andSkillLevelGreaterThan(Integer value) {
            addCriterion("skill_level >", value, "skillLevel");
            return (Criteria) this;
        }

        public Criteria andSkillLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("skill_level >=", value, "skillLevel");
            return (Criteria) this;
        }

        public Criteria andSkillLevelLessThan(Integer value) {
            addCriterion("skill_level <", value, "skillLevel");
            return (Criteria) this;
        }

        public Criteria andSkillLevelLessThanOrEqualTo(Integer value) {
            addCriterion("skill_level <=", value, "skillLevel");
            return (Criteria) this;
        }

        public Criteria andSkillLevelIn(List<Integer> values) {
            addCriterion("skill_level in", values, "skillLevel");
            return (Criteria) this;
        }

        public Criteria andSkillLevelNotIn(List<Integer> values) {
            addCriterion("skill_level not in", values, "skillLevel");
            return (Criteria) this;
        }

        public Criteria andSkillLevelBetween(Integer value1, Integer value2) {
            addCriterion("skill_level between", value1, value2, "skillLevel");
            return (Criteria) this;
        }

        public Criteria andSkillLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("skill_level not between", value1, value2, "skillLevel");
            return (Criteria) this;
        }

        public Criteria andConsumeMoneyIsNull() {
            addCriterion("consume_money is null");
            return (Criteria) this;
        }

        public Criteria andConsumeMoneyIsNotNull() {
            addCriterion("consume_money is not null");
            return (Criteria) this;
        }

        public Criteria andConsumeMoneyEqualTo(Integer value) {
            addCriterion("consume_money =", value, "consumeMoney");
            return (Criteria) this;
        }

        public Criteria andConsumeMoneyNotEqualTo(Integer value) {
            addCriterion("consume_money <>", value, "consumeMoney");
            return (Criteria) this;
        }

        public Criteria andConsumeMoneyGreaterThan(Integer value) {
            addCriterion("consume_money >", value, "consumeMoney");
            return (Criteria) this;
        }

        public Criteria andConsumeMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("consume_money >=", value, "consumeMoney");
            return (Criteria) this;
        }

        public Criteria andConsumeMoneyLessThan(Integer value) {
            addCriterion("consume_money <", value, "consumeMoney");
            return (Criteria) this;
        }

        public Criteria andConsumeMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("consume_money <=", value, "consumeMoney");
            return (Criteria) this;
        }

        public Criteria andConsumeMoneyIn(List<Integer> values) {
            addCriterion("consume_money in", values, "consumeMoney");
            return (Criteria) this;
        }

        public Criteria andConsumeMoneyNotIn(List<Integer> values) {
            addCriterion("consume_money not in", values, "consumeMoney");
            return (Criteria) this;
        }

        public Criteria andConsumeMoneyBetween(Integer value1, Integer value2) {
            addCriterion("consume_money between", value1, value2, "consumeMoney");
            return (Criteria) this;
        }

        public Criteria andConsumeMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("consume_money not between", value1, value2, "consumeMoney");
            return (Criteria) this;
        }

        public Criteria andConsumeExperienceIsNull() {
            addCriterion("consume_experience is null");
            return (Criteria) this;
        }

        public Criteria andConsumeExperienceIsNotNull() {
            addCriterion("consume_experience is not null");
            return (Criteria) this;
        }

        public Criteria andConsumeExperienceEqualTo(Integer value) {
            addCriterion("consume_experience =", value, "consumeExperience");
            return (Criteria) this;
        }

        public Criteria andConsumeExperienceNotEqualTo(Integer value) {
            addCriterion("consume_experience <>", value, "consumeExperience");
            return (Criteria) this;
        }

        public Criteria andConsumeExperienceGreaterThan(Integer value) {
            addCriterion("consume_experience >", value, "consumeExperience");
            return (Criteria) this;
        }

        public Criteria andConsumeExperienceGreaterThanOrEqualTo(Integer value) {
            addCriterion("consume_experience >=", value, "consumeExperience");
            return (Criteria) this;
        }

        public Criteria andConsumeExperienceLessThan(Integer value) {
            addCriterion("consume_experience <", value, "consumeExperience");
            return (Criteria) this;
        }

        public Criteria andConsumeExperienceLessThanOrEqualTo(Integer value) {
            addCriterion("consume_experience <=", value, "consumeExperience");
            return (Criteria) this;
        }

        public Criteria andConsumeExperienceIn(List<Integer> values) {
            addCriterion("consume_experience in", values, "consumeExperience");
            return (Criteria) this;
        }

        public Criteria andConsumeExperienceNotIn(List<Integer> values) {
            addCriterion("consume_experience not in", values, "consumeExperience");
            return (Criteria) this;
        }

        public Criteria andConsumeExperienceBetween(Integer value1, Integer value2) {
            addCriterion("consume_experience between", value1, value2, "consumeExperience");
            return (Criteria) this;
        }

        public Criteria andConsumeExperienceNotBetween(Integer value1, Integer value2) {
            addCriterion("consume_experience not between", value1, value2, "consumeExperience");
            return (Criteria) this;
        }

        public Criteria andCoolingTimeIsNull() {
            addCriterion("cooling_time is null");
            return (Criteria) this;
        }

        public Criteria andCoolingTimeIsNotNull() {
            addCriterion("cooling_time is not null");
            return (Criteria) this;
        }

        public Criteria andCoolingTimeEqualTo(Integer value) {
            addCriterion("cooling_time =", value, "coolingTime");
            return (Criteria) this;
        }

        public Criteria andCoolingTimeNotEqualTo(Integer value) {
            addCriterion("cooling_time <>", value, "coolingTime");
            return (Criteria) this;
        }

        public Criteria andCoolingTimeGreaterThan(Integer value) {
            addCriterion("cooling_time >", value, "coolingTime");
            return (Criteria) this;
        }

        public Criteria andCoolingTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("cooling_time >=", value, "coolingTime");
            return (Criteria) this;
        }

        public Criteria andCoolingTimeLessThan(Integer value) {
            addCriterion("cooling_time <", value, "coolingTime");
            return (Criteria) this;
        }

        public Criteria andCoolingTimeLessThanOrEqualTo(Integer value) {
            addCriterion("cooling_time <=", value, "coolingTime");
            return (Criteria) this;
        }

        public Criteria andCoolingTimeIn(List<Integer> values) {
            addCriterion("cooling_time in", values, "coolingTime");
            return (Criteria) this;
        }

        public Criteria andCoolingTimeNotIn(List<Integer> values) {
            addCriterion("cooling_time not in", values, "coolingTime");
            return (Criteria) this;
        }

        public Criteria andCoolingTimeBetween(Integer value1, Integer value2) {
            addCriterion("cooling_time between", value1, value2, "coolingTime");
            return (Criteria) this;
        }

        public Criteria andCoolingTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("cooling_time not between", value1, value2, "coolingTime");
            return (Criteria) this;
        }

        public Criteria andProIsNull() {
            addCriterion("pro is null");
            return (Criteria) this;
        }

        public Criteria andProIsNotNull() {
            addCriterion("pro is not null");
            return (Criteria) this;
        }

        public Criteria andProEqualTo(String value) {
            addCriterion("pro =", value, "pro");
            return (Criteria) this;
        }

        public Criteria andProNotEqualTo(String value) {
            addCriterion("pro <>", value, "pro");
            return (Criteria) this;
        }

        public Criteria andProGreaterThan(String value) {
            addCriterion("pro >", value, "pro");
            return (Criteria) this;
        }

        public Criteria andProGreaterThanOrEqualTo(String value) {
            addCriterion("pro >=", value, "pro");
            return (Criteria) this;
        }

        public Criteria andProLessThan(String value) {
            addCriterion("pro <", value, "pro");
            return (Criteria) this;
        }

        public Criteria andProLessThanOrEqualTo(String value) {
            addCriterion("pro <=", value, "pro");
            return (Criteria) this;
        }

        public Criteria andProLike(String value) {
            addCriterion("pro like", value, "pro");
            return (Criteria) this;
        }

        public Criteria andProNotLike(String value) {
            addCriterion("pro not like", value, "pro");
            return (Criteria) this;
        }

        public Criteria andProIn(List<String> values) {
            addCriterion("pro in", values, "pro");
            return (Criteria) this;
        }

        public Criteria andProNotIn(List<String> values) {
            addCriterion("pro not in", values, "pro");
            return (Criteria) this;
        }

        public Criteria andProBetween(String value1, String value2) {
            addCriterion("pro between", value1, value2, "pro");
            return (Criteria) this;
        }

        public Criteria andProNotBetween(String value1, String value2) {
            addCriterion("pro not between", value1, value2, "pro");
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