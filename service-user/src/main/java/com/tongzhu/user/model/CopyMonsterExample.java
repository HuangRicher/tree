package com.tongzhu.user.model;

import java.util.ArrayList;
import java.util.List;

public class CopyMonsterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CopyMonsterExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCopyIdIsNull() {
            addCriterion("copy_id is null");
            return (Criteria) this;
        }

        public Criteria andCopyIdIsNotNull() {
            addCriterion("copy_id is not null");
            return (Criteria) this;
        }

        public Criteria andCopyIdEqualTo(Integer value) {
            addCriterion("copy_id =", value, "copyId");
            return (Criteria) this;
        }

        public Criteria andCopyIdNotEqualTo(Integer value) {
            addCriterion("copy_id <>", value, "copyId");
            return (Criteria) this;
        }

        public Criteria andCopyIdGreaterThan(Integer value) {
            addCriterion("copy_id >", value, "copyId");
            return (Criteria) this;
        }

        public Criteria andCopyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("copy_id >=", value, "copyId");
            return (Criteria) this;
        }

        public Criteria andCopyIdLessThan(Integer value) {
            addCriterion("copy_id <", value, "copyId");
            return (Criteria) this;
        }

        public Criteria andCopyIdLessThanOrEqualTo(Integer value) {
            addCriterion("copy_id <=", value, "copyId");
            return (Criteria) this;
        }

        public Criteria andCopyIdIn(List<Integer> values) {
            addCriterion("copy_id in", values, "copyId");
            return (Criteria) this;
        }

        public Criteria andCopyIdNotIn(List<Integer> values) {
            addCriterion("copy_id not in", values, "copyId");
            return (Criteria) this;
        }

        public Criteria andCopyIdBetween(Integer value1, Integer value2) {
            addCriterion("copy_id between", value1, value2, "copyId");
            return (Criteria) this;
        }

        public Criteria andCopyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("copy_id not between", value1, value2, "copyId");
            return (Criteria) this;
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

        public Criteria andGroupIdIsNull() {
            addCriterion("group_id is null");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNotNull() {
            addCriterion("group_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIdEqualTo(Integer value) {
            addCriterion("group_id =", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotEqualTo(Integer value) {
            addCriterion("group_id <>", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThan(Integer value) {
            addCriterion("group_id >", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_id >=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThan(Integer value) {
            addCriterion("group_id <", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("group_id <=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIn(List<Integer> values) {
            addCriterion("group_id in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotIn(List<Integer> values) {
            addCriterion("group_id not in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("group_id between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("group_id not between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andLimitIsNull() {
            addCriterion("limit is null");
            return (Criteria) this;
        }

        public Criteria andLimitIsNotNull() {
            addCriterion("limit is not null");
            return (Criteria) this;
        }

        public Criteria andLimitEqualTo(Integer value) {
            addCriterion("limit =", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitNotEqualTo(Integer value) {
            addCriterion("limit <>", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitGreaterThan(Integer value) {
            addCriterion("limit >", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("limit >=", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitLessThan(Integer value) {
            addCriterion("limit <", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitLessThanOrEqualTo(Integer value) {
            addCriterion("limit <=", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitIn(List<Integer> values) {
            addCriterion("limit in", values, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitNotIn(List<Integer> values) {
            addCriterion("limit not in", values, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitBetween(Integer value1, Integer value2) {
            addCriterion("limit between", value1, value2, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("limit not between", value1, value2, "limit");
            return (Criteria) this;
        }

        public Criteria andRandomStatusIsNull() {
            addCriterion("random_status is null");
            return (Criteria) this;
        }

        public Criteria andRandomStatusIsNotNull() {
            addCriterion("random_status is not null");
            return (Criteria) this;
        }

        public Criteria andRandomStatusEqualTo(Integer value) {
            addCriterion("random_status =", value, "randomStatus");
            return (Criteria) this;
        }

        public Criteria andRandomStatusNotEqualTo(Integer value) {
            addCriterion("random_status <>", value, "randomStatus");
            return (Criteria) this;
        }

        public Criteria andRandomStatusGreaterThan(Integer value) {
            addCriterion("random_status >", value, "randomStatus");
            return (Criteria) this;
        }

        public Criteria andRandomStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("random_status >=", value, "randomStatus");
            return (Criteria) this;
        }

        public Criteria andRandomStatusLessThan(Integer value) {
            addCriterion("random_status <", value, "randomStatus");
            return (Criteria) this;
        }

        public Criteria andRandomStatusLessThanOrEqualTo(Integer value) {
            addCriterion("random_status <=", value, "randomStatus");
            return (Criteria) this;
        }

        public Criteria andRandomStatusIn(List<Integer> values) {
            addCriterion("random_status in", values, "randomStatus");
            return (Criteria) this;
        }

        public Criteria andRandomStatusNotIn(List<Integer> values) {
            addCriterion("random_status not in", values, "randomStatus");
            return (Criteria) this;
        }

        public Criteria andRandomStatusBetween(Integer value1, Integer value2) {
            addCriterion("random_status between", value1, value2, "randomStatus");
            return (Criteria) this;
        }

        public Criteria andRandomStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("random_status not between", value1, value2, "randomStatus");
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