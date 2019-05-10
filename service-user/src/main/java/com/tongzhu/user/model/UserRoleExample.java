package com.tongzhu.user.model;

import java.util.ArrayList;
import java.util.List;

public class UserRoleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserRoleExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNull() {
            addCriterion("role_id is null");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNotNull() {
            addCriterion("role_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoleIdEqualTo(Integer value) {
            addCriterion("role_id =", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotEqualTo(Integer value) {
            addCriterion("role_id <>", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThan(Integer value) {
            addCriterion("role_id >", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("role_id >=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThan(Integer value) {
            addCriterion("role_id <", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThanOrEqualTo(Integer value) {
            addCriterion("role_id <=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIn(List<Integer> values) {
            addCriterion("role_id in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotIn(List<Integer> values) {
            addCriterion("role_id not in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdBetween(Integer value1, Integer value2) {
            addCriterion("role_id between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("role_id not between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andSpouseIsNull() {
            addCriterion("spouse is null");
            return (Criteria) this;
        }

        public Criteria andSpouseIsNotNull() {
            addCriterion("spouse is not null");
            return (Criteria) this;
        }

        public Criteria andSpouseEqualTo(String value) {
            addCriterion("spouse =", value, "spouse");
            return (Criteria) this;
        }

        public Criteria andSpouseNotEqualTo(String value) {
            addCriterion("spouse <>", value, "spouse");
            return (Criteria) this;
        }

        public Criteria andSpouseGreaterThan(String value) {
            addCriterion("spouse >", value, "spouse");
            return (Criteria) this;
        }

        public Criteria andSpouseGreaterThanOrEqualTo(String value) {
            addCriterion("spouse >=", value, "spouse");
            return (Criteria) this;
        }

        public Criteria andSpouseLessThan(String value) {
            addCriterion("spouse <", value, "spouse");
            return (Criteria) this;
        }

        public Criteria andSpouseLessThanOrEqualTo(String value) {
            addCriterion("spouse <=", value, "spouse");
            return (Criteria) this;
        }

        public Criteria andSpouseLike(String value) {
            addCriterion("spouse like", value, "spouse");
            return (Criteria) this;
        }

        public Criteria andSpouseNotLike(String value) {
            addCriterion("spouse not like", value, "spouse");
            return (Criteria) this;
        }

        public Criteria andSpouseIn(List<String> values) {
            addCriterion("spouse in", values, "spouse");
            return (Criteria) this;
        }

        public Criteria andSpouseNotIn(List<String> values) {
            addCriterion("spouse not in", values, "spouse");
            return (Criteria) this;
        }

        public Criteria andSpouseBetween(String value1, String value2) {
            addCriterion("spouse between", value1, value2, "spouse");
            return (Criteria) this;
        }

        public Criteria andSpouseNotBetween(String value1, String value2) {
            addCriterion("spouse not between", value1, value2, "spouse");
            return (Criteria) this;
        }

        public Criteria andExperienceIsNull() {
            addCriterion("experience is null");
            return (Criteria) this;
        }

        public Criteria andExperienceIsNotNull() {
            addCriterion("experience is not null");
            return (Criteria) this;
        }

        public Criteria andExperienceEqualTo(Long value) {
            addCriterion("experience =", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceNotEqualTo(Long value) {
            addCriterion("experience <>", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceGreaterThan(Long value) {
            addCriterion("experience >", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceGreaterThanOrEqualTo(Long value) {
            addCriterion("experience >=", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceLessThan(Long value) {
            addCriterion("experience <", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceLessThanOrEqualTo(Long value) {
            addCriterion("experience <=", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceIn(List<Long> values) {
            addCriterion("experience in", values, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceNotIn(List<Long> values) {
            addCriterion("experience not in", values, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceBetween(Long value1, Long value2) {
            addCriterion("experience between", value1, value2, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceNotBetween(Long value1, Long value2) {
            addCriterion("experience not between", value1, value2, "experience");
            return (Criteria) this;
        }

        public Criteria andBloodIsNull() {
            addCriterion("blood is null");
            return (Criteria) this;
        }

        public Criteria andBloodIsNotNull() {
            addCriterion("blood is not null");
            return (Criteria) this;
        }

        public Criteria andBloodEqualTo(Integer value) {
            addCriterion("blood =", value, "blood");
            return (Criteria) this;
        }

        public Criteria andBloodNotEqualTo(Integer value) {
            addCriterion("blood <>", value, "blood");
            return (Criteria) this;
        }

        public Criteria andBloodGreaterThan(Integer value) {
            addCriterion("blood >", value, "blood");
            return (Criteria) this;
        }

        public Criteria andBloodGreaterThanOrEqualTo(Integer value) {
            addCriterion("blood >=", value, "blood");
            return (Criteria) this;
        }

        public Criteria andBloodLessThan(Integer value) {
            addCriterion("blood <", value, "blood");
            return (Criteria) this;
        }

        public Criteria andBloodLessThanOrEqualTo(Integer value) {
            addCriterion("blood <=", value, "blood");
            return (Criteria) this;
        }

        public Criteria andBloodIn(List<Integer> values) {
            addCriterion("blood in", values, "blood");
            return (Criteria) this;
        }

        public Criteria andBloodNotIn(List<Integer> values) {
            addCriterion("blood not in", values, "blood");
            return (Criteria) this;
        }

        public Criteria andBloodBetween(Integer value1, Integer value2) {
            addCriterion("blood between", value1, value2, "blood");
            return (Criteria) this;
        }

        public Criteria andBloodNotBetween(Integer value1, Integer value2) {
            addCriterion("blood not between", value1, value2, "blood");
            return (Criteria) this;
        }

        public Criteria andSociatyIsNull() {
            addCriterion("sociaty is null");
            return (Criteria) this;
        }

        public Criteria andSociatyIsNotNull() {
            addCriterion("sociaty is not null");
            return (Criteria) this;
        }

        public Criteria andSociatyEqualTo(String value) {
            addCriterion("sociaty =", value, "sociaty");
            return (Criteria) this;
        }

        public Criteria andSociatyNotEqualTo(String value) {
            addCriterion("sociaty <>", value, "sociaty");
            return (Criteria) this;
        }

        public Criteria andSociatyGreaterThan(String value) {
            addCriterion("sociaty >", value, "sociaty");
            return (Criteria) this;
        }

        public Criteria andSociatyGreaterThanOrEqualTo(String value) {
            addCriterion("sociaty >=", value, "sociaty");
            return (Criteria) this;
        }

        public Criteria andSociatyLessThan(String value) {
            addCriterion("sociaty <", value, "sociaty");
            return (Criteria) this;
        }

        public Criteria andSociatyLessThanOrEqualTo(String value) {
            addCriterion("sociaty <=", value, "sociaty");
            return (Criteria) this;
        }

        public Criteria andSociatyLike(String value) {
            addCriterion("sociaty like", value, "sociaty");
            return (Criteria) this;
        }

        public Criteria andSociatyNotLike(String value) {
            addCriterion("sociaty not like", value, "sociaty");
            return (Criteria) this;
        }

        public Criteria andSociatyIn(List<String> values) {
            addCriterion("sociaty in", values, "sociaty");
            return (Criteria) this;
        }

        public Criteria andSociatyNotIn(List<String> values) {
            addCriterion("sociaty not in", values, "sociaty");
            return (Criteria) this;
        }

        public Criteria andSociatyBetween(String value1, String value2) {
            addCriterion("sociaty between", value1, value2, "sociaty");
            return (Criteria) this;
        }

        public Criteria andSociatyNotBetween(String value1, String value2) {
            addCriterion("sociaty not between", value1, value2, "sociaty");
            return (Criteria) this;
        }

        public Criteria andRoleTitleIsNull() {
            addCriterion("role_title is null");
            return (Criteria) this;
        }

        public Criteria andRoleTitleIsNotNull() {
            addCriterion("role_title is not null");
            return (Criteria) this;
        }

        public Criteria andRoleTitleEqualTo(Integer value) {
            addCriterion("role_title =", value, "roleTitle");
            return (Criteria) this;
        }

        public Criteria andRoleTitleNotEqualTo(Integer value) {
            addCriterion("role_title <>", value, "roleTitle");
            return (Criteria) this;
        }

        public Criteria andRoleTitleGreaterThan(Integer value) {
            addCriterion("role_title >", value, "roleTitle");
            return (Criteria) this;
        }

        public Criteria andRoleTitleGreaterThanOrEqualTo(Integer value) {
            addCriterion("role_title >=", value, "roleTitle");
            return (Criteria) this;
        }

        public Criteria andRoleTitleLessThan(Integer value) {
            addCriterion("role_title <", value, "roleTitle");
            return (Criteria) this;
        }

        public Criteria andRoleTitleLessThanOrEqualTo(Integer value) {
            addCriterion("role_title <=", value, "roleTitle");
            return (Criteria) this;
        }

        public Criteria andRoleTitleIn(List<Integer> values) {
            addCriterion("role_title in", values, "roleTitle");
            return (Criteria) this;
        }

        public Criteria andRoleTitleNotIn(List<Integer> values) {
            addCriterion("role_title not in", values, "roleTitle");
            return (Criteria) this;
        }

        public Criteria andRoleTitleBetween(Integer value1, Integer value2) {
            addCriterion("role_title between", value1, value2, "roleTitle");
            return (Criteria) this;
        }

        public Criteria andRoleTitleNotBetween(Integer value1, Integer value2) {
            addCriterion("role_title not between", value1, value2, "roleTitle");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
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

        public Criteria andCharmNumIsNull() {
            addCriterion("charm_num is null");
            return (Criteria) this;
        }

        public Criteria andCharmNumIsNotNull() {
            addCriterion("charm_num is not null");
            return (Criteria) this;
        }

        public Criteria andCharmNumEqualTo(Integer value) {
            addCriterion("charm_num =", value, "charmNum");
            return (Criteria) this;
        }

        public Criteria andCharmNumNotEqualTo(Integer value) {
            addCriterion("charm_num <>", value, "charmNum");
            return (Criteria) this;
        }

        public Criteria andCharmNumGreaterThan(Integer value) {
            addCriterion("charm_num >", value, "charmNum");
            return (Criteria) this;
        }

        public Criteria andCharmNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("charm_num >=", value, "charmNum");
            return (Criteria) this;
        }

        public Criteria andCharmNumLessThan(Integer value) {
            addCriterion("charm_num <", value, "charmNum");
            return (Criteria) this;
        }

        public Criteria andCharmNumLessThanOrEqualTo(Integer value) {
            addCriterion("charm_num <=", value, "charmNum");
            return (Criteria) this;
        }

        public Criteria andCharmNumIn(List<Integer> values) {
            addCriterion("charm_num in", values, "charmNum");
            return (Criteria) this;
        }

        public Criteria andCharmNumNotIn(List<Integer> values) {
            addCriterion("charm_num not in", values, "charmNum");
            return (Criteria) this;
        }

        public Criteria andCharmNumBetween(Integer value1, Integer value2) {
            addCriterion("charm_num between", value1, value2, "charmNum");
            return (Criteria) this;
        }

        public Criteria andCharmNumNotBetween(Integer value1, Integer value2) {
            addCriterion("charm_num not between", value1, value2, "charmNum");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(Integer value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(Integer value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(Integer value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(Integer value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(Integer value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(Integer value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<Integer> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<Integer> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(Integer value1, Integer value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(Integer value1, Integer value2) {
            addCriterion("sex not between", value1, value2, "sex");
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