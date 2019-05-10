package com.tongzhu.user.model;

import java.util.ArrayList;
import java.util.List;

public class FightCopyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FightCopyExample() {
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

        public Criteria andCopyNameIsNull() {
            addCriterion("copy_name is null");
            return (Criteria) this;
        }

        public Criteria andCopyNameIsNotNull() {
            addCriterion("copy_name is not null");
            return (Criteria) this;
        }

        public Criteria andCopyNameEqualTo(String value) {
            addCriterion("copy_name =", value, "copyName");
            return (Criteria) this;
        }

        public Criteria andCopyNameNotEqualTo(String value) {
            addCriterion("copy_name <>", value, "copyName");
            return (Criteria) this;
        }

        public Criteria andCopyNameGreaterThan(String value) {
            addCriterion("copy_name >", value, "copyName");
            return (Criteria) this;
        }

        public Criteria andCopyNameGreaterThanOrEqualTo(String value) {
            addCriterion("copy_name >=", value, "copyName");
            return (Criteria) this;
        }

        public Criteria andCopyNameLessThan(String value) {
            addCriterion("copy_name <", value, "copyName");
            return (Criteria) this;
        }

        public Criteria andCopyNameLessThanOrEqualTo(String value) {
            addCriterion("copy_name <=", value, "copyName");
            return (Criteria) this;
        }

        public Criteria andCopyNameLike(String value) {
            addCriterion("copy_name like", value, "copyName");
            return (Criteria) this;
        }

        public Criteria andCopyNameNotLike(String value) {
            addCriterion("copy_name not like", value, "copyName");
            return (Criteria) this;
        }

        public Criteria andCopyNameIn(List<String> values) {
            addCriterion("copy_name in", values, "copyName");
            return (Criteria) this;
        }

        public Criteria andCopyNameNotIn(List<String> values) {
            addCriterion("copy_name not in", values, "copyName");
            return (Criteria) this;
        }

        public Criteria andCopyNameBetween(String value1, String value2) {
            addCriterion("copy_name between", value1, value2, "copyName");
            return (Criteria) this;
        }

        public Criteria andCopyNameNotBetween(String value1, String value2) {
            addCriterion("copy_name not between", value1, value2, "copyName");
            return (Criteria) this;
        }

        public Criteria andCommonCountIsNull() {
            addCriterion("common_count is null");
            return (Criteria) this;
        }

        public Criteria andCommonCountIsNotNull() {
            addCriterion("common_count is not null");
            return (Criteria) this;
        }

        public Criteria andCommonCountEqualTo(Integer value) {
            addCriterion("common_count =", value, "commonCount");
            return (Criteria) this;
        }

        public Criteria andCommonCountNotEqualTo(Integer value) {
            addCriterion("common_count <>", value, "commonCount");
            return (Criteria) this;
        }

        public Criteria andCommonCountGreaterThan(Integer value) {
            addCriterion("common_count >", value, "commonCount");
            return (Criteria) this;
        }

        public Criteria andCommonCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("common_count >=", value, "commonCount");
            return (Criteria) this;
        }

        public Criteria andCommonCountLessThan(Integer value) {
            addCriterion("common_count <", value, "commonCount");
            return (Criteria) this;
        }

        public Criteria andCommonCountLessThanOrEqualTo(Integer value) {
            addCriterion("common_count <=", value, "commonCount");
            return (Criteria) this;
        }

        public Criteria andCommonCountIn(List<Integer> values) {
            addCriterion("common_count in", values, "commonCount");
            return (Criteria) this;
        }

        public Criteria andCommonCountNotIn(List<Integer> values) {
            addCriterion("common_count not in", values, "commonCount");
            return (Criteria) this;
        }

        public Criteria andCommonCountBetween(Integer value1, Integer value2) {
            addCriterion("common_count between", value1, value2, "commonCount");
            return (Criteria) this;
        }

        public Criteria andCommonCountNotBetween(Integer value1, Integer value2) {
            addCriterion("common_count not between", value1, value2, "commonCount");
            return (Criteria) this;
        }

        public Criteria andVipCountIsNull() {
            addCriterion("vip_count is null");
            return (Criteria) this;
        }

        public Criteria andVipCountIsNotNull() {
            addCriterion("vip_count is not null");
            return (Criteria) this;
        }

        public Criteria andVipCountEqualTo(Integer value) {
            addCriterion("vip_count =", value, "vipCount");
            return (Criteria) this;
        }

        public Criteria andVipCountNotEqualTo(Integer value) {
            addCriterion("vip_count <>", value, "vipCount");
            return (Criteria) this;
        }

        public Criteria andVipCountGreaterThan(Integer value) {
            addCriterion("vip_count >", value, "vipCount");
            return (Criteria) this;
        }

        public Criteria andVipCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("vip_count >=", value, "vipCount");
            return (Criteria) this;
        }

        public Criteria andVipCountLessThan(Integer value) {
            addCriterion("vip_count <", value, "vipCount");
            return (Criteria) this;
        }

        public Criteria andVipCountLessThanOrEqualTo(Integer value) {
            addCriterion("vip_count <=", value, "vipCount");
            return (Criteria) this;
        }

        public Criteria andVipCountIn(List<Integer> values) {
            addCriterion("vip_count in", values, "vipCount");
            return (Criteria) this;
        }

        public Criteria andVipCountNotIn(List<Integer> values) {
            addCriterion("vip_count not in", values, "vipCount");
            return (Criteria) this;
        }

        public Criteria andVipCountBetween(Integer value1, Integer value2) {
            addCriterion("vip_count between", value1, value2, "vipCount");
            return (Criteria) this;
        }

        public Criteria andVipCountNotBetween(Integer value1, Integer value2) {
            addCriterion("vip_count not between", value1, value2, "vipCount");
            return (Criteria) this;
        }

        public Criteria andMinRoleLevelIsNull() {
            addCriterion("min_role_level is null");
            return (Criteria) this;
        }

        public Criteria andMinRoleLevelIsNotNull() {
            addCriterion("min_role_level is not null");
            return (Criteria) this;
        }

        public Criteria andMinRoleLevelEqualTo(Integer value) {
            addCriterion("min_role_level =", value, "minRoleLevel");
            return (Criteria) this;
        }

        public Criteria andMinRoleLevelNotEqualTo(Integer value) {
            addCriterion("min_role_level <>", value, "minRoleLevel");
            return (Criteria) this;
        }

        public Criteria andMinRoleLevelGreaterThan(Integer value) {
            addCriterion("min_role_level >", value, "minRoleLevel");
            return (Criteria) this;
        }

        public Criteria andMinRoleLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("min_role_level >=", value, "minRoleLevel");
            return (Criteria) this;
        }

        public Criteria andMinRoleLevelLessThan(Integer value) {
            addCriterion("min_role_level <", value, "minRoleLevel");
            return (Criteria) this;
        }

        public Criteria andMinRoleLevelLessThanOrEqualTo(Integer value) {
            addCriterion("min_role_level <=", value, "minRoleLevel");
            return (Criteria) this;
        }

        public Criteria andMinRoleLevelIn(List<Integer> values) {
            addCriterion("min_role_level in", values, "minRoleLevel");
            return (Criteria) this;
        }

        public Criteria andMinRoleLevelNotIn(List<Integer> values) {
            addCriterion("min_role_level not in", values, "minRoleLevel");
            return (Criteria) this;
        }

        public Criteria andMinRoleLevelBetween(Integer value1, Integer value2) {
            addCriterion("min_role_level between", value1, value2, "minRoleLevel");
            return (Criteria) this;
        }

        public Criteria andMinRoleLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("min_role_level not between", value1, value2, "minRoleLevel");
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