package com.tongzhu.user.model;

import java.util.ArrayList;
import java.util.List;

public class ExplorationSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExplorationSettingExample() {
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

        public Criteria andExplorationIdIsNull() {
            addCriterion("exploration_id is null");
            return (Criteria) this;
        }

        public Criteria andExplorationIdIsNotNull() {
            addCriterion("exploration_id is not null");
            return (Criteria) this;
        }

        public Criteria andExplorationIdEqualTo(Integer value) {
            addCriterion("exploration_id =", value, "explorationId");
            return (Criteria) this;
        }

        public Criteria andExplorationIdNotEqualTo(Integer value) {
            addCriterion("exploration_id <>", value, "explorationId");
            return (Criteria) this;
        }

        public Criteria andExplorationIdGreaterThan(Integer value) {
            addCriterion("exploration_id >", value, "explorationId");
            return (Criteria) this;
        }

        public Criteria andExplorationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("exploration_id >=", value, "explorationId");
            return (Criteria) this;
        }

        public Criteria andExplorationIdLessThan(Integer value) {
            addCriterion("exploration_id <", value, "explorationId");
            return (Criteria) this;
        }

        public Criteria andExplorationIdLessThanOrEqualTo(Integer value) {
            addCriterion("exploration_id <=", value, "explorationId");
            return (Criteria) this;
        }

        public Criteria andExplorationIdIn(List<Integer> values) {
            addCriterion("exploration_id in", values, "explorationId");
            return (Criteria) this;
        }

        public Criteria andExplorationIdNotIn(List<Integer> values) {
            addCriterion("exploration_id not in", values, "explorationId");
            return (Criteria) this;
        }

        public Criteria andExplorationIdBetween(Integer value1, Integer value2) {
            addCriterion("exploration_id between", value1, value2, "explorationId");
            return (Criteria) this;
        }

        public Criteria andExplorationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("exploration_id not between", value1, value2, "explorationId");
            return (Criteria) this;
        }

        public Criteria andPassIdIsNull() {
            addCriterion("pass_id is null");
            return (Criteria) this;
        }

        public Criteria andPassIdIsNotNull() {
            addCriterion("pass_id is not null");
            return (Criteria) this;
        }

        public Criteria andPassIdEqualTo(Integer value) {
            addCriterion("pass_id =", value, "passId");
            return (Criteria) this;
        }

        public Criteria andPassIdNotEqualTo(Integer value) {
            addCriterion("pass_id <>", value, "passId");
            return (Criteria) this;
        }

        public Criteria andPassIdGreaterThan(Integer value) {
            addCriterion("pass_id >", value, "passId");
            return (Criteria) this;
        }

        public Criteria andPassIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("pass_id >=", value, "passId");
            return (Criteria) this;
        }

        public Criteria andPassIdLessThan(Integer value) {
            addCriterion("pass_id <", value, "passId");
            return (Criteria) this;
        }

        public Criteria andPassIdLessThanOrEqualTo(Integer value) {
            addCriterion("pass_id <=", value, "passId");
            return (Criteria) this;
        }

        public Criteria andPassIdIn(List<Integer> values) {
            addCriterion("pass_id in", values, "passId");
            return (Criteria) this;
        }

        public Criteria andPassIdNotIn(List<Integer> values) {
            addCriterion("pass_id not in", values, "passId");
            return (Criteria) this;
        }

        public Criteria andPassIdBetween(Integer value1, Integer value2) {
            addCriterion("pass_id between", value1, value2, "passId");
            return (Criteria) this;
        }

        public Criteria andPassIdNotBetween(Integer value1, Integer value2) {
            addCriterion("pass_id not between", value1, value2, "passId");
            return (Criteria) this;
        }

        public Criteria andFirstExpIsNull() {
            addCriterion("first_exp is null");
            return (Criteria) this;
        }

        public Criteria andFirstExpIsNotNull() {
            addCriterion("first_exp is not null");
            return (Criteria) this;
        }

        public Criteria andFirstExpEqualTo(Integer value) {
            addCriterion("first_exp =", value, "firstExp");
            return (Criteria) this;
        }

        public Criteria andFirstExpNotEqualTo(Integer value) {
            addCriterion("first_exp <>", value, "firstExp");
            return (Criteria) this;
        }

        public Criteria andFirstExpGreaterThan(Integer value) {
            addCriterion("first_exp >", value, "firstExp");
            return (Criteria) this;
        }

        public Criteria andFirstExpGreaterThanOrEqualTo(Integer value) {
            addCriterion("first_exp >=", value, "firstExp");
            return (Criteria) this;
        }

        public Criteria andFirstExpLessThan(Integer value) {
            addCriterion("first_exp <", value, "firstExp");
            return (Criteria) this;
        }

        public Criteria andFirstExpLessThanOrEqualTo(Integer value) {
            addCriterion("first_exp <=", value, "firstExp");
            return (Criteria) this;
        }

        public Criteria andFirstExpIn(List<Integer> values) {
            addCriterion("first_exp in", values, "firstExp");
            return (Criteria) this;
        }

        public Criteria andFirstExpNotIn(List<Integer> values) {
            addCriterion("first_exp not in", values, "firstExp");
            return (Criteria) this;
        }

        public Criteria andFirstExpBetween(Integer value1, Integer value2) {
            addCriterion("first_exp between", value1, value2, "firstExp");
            return (Criteria) this;
        }

        public Criteria andFirstExpNotBetween(Integer value1, Integer value2) {
            addCriterion("first_exp not between", value1, value2, "firstExp");
            return (Criteria) this;
        }

        public Criteria andNexExpIsNull() {
            addCriterion("nex_exp is null");
            return (Criteria) this;
        }

        public Criteria andNexExpIsNotNull() {
            addCriterion("nex_exp is not null");
            return (Criteria) this;
        }

        public Criteria andNexExpEqualTo(Integer value) {
            addCriterion("nex_exp =", value, "nexExp");
            return (Criteria) this;
        }

        public Criteria andNexExpNotEqualTo(Integer value) {
            addCriterion("nex_exp <>", value, "nexExp");
            return (Criteria) this;
        }

        public Criteria andNexExpGreaterThan(Integer value) {
            addCriterion("nex_exp >", value, "nexExp");
            return (Criteria) this;
        }

        public Criteria andNexExpGreaterThanOrEqualTo(Integer value) {
            addCriterion("nex_exp >=", value, "nexExp");
            return (Criteria) this;
        }

        public Criteria andNexExpLessThan(Integer value) {
            addCriterion("nex_exp <", value, "nexExp");
            return (Criteria) this;
        }

        public Criteria andNexExpLessThanOrEqualTo(Integer value) {
            addCriterion("nex_exp <=", value, "nexExp");
            return (Criteria) this;
        }

        public Criteria andNexExpIn(List<Integer> values) {
            addCriterion("nex_exp in", values, "nexExp");
            return (Criteria) this;
        }

        public Criteria andNexExpNotIn(List<Integer> values) {
            addCriterion("nex_exp not in", values, "nexExp");
            return (Criteria) this;
        }

        public Criteria andNexExpBetween(Integer value1, Integer value2) {
            addCriterion("nex_exp between", value1, value2, "nexExp");
            return (Criteria) this;
        }

        public Criteria andNexExpNotBetween(Integer value1, Integer value2) {
            addCriterion("nex_exp not between", value1, value2, "nexExp");
            return (Criteria) this;
        }

        public Criteria andTreasureBoxIsNull() {
            addCriterion("treasure_box is null");
            return (Criteria) this;
        }

        public Criteria andTreasureBoxIsNotNull() {
            addCriterion("treasure_box is not null");
            return (Criteria) this;
        }

        public Criteria andTreasureBoxEqualTo(Integer value) {
            addCriterion("treasure_box =", value, "treasureBox");
            return (Criteria) this;
        }

        public Criteria andTreasureBoxNotEqualTo(Integer value) {
            addCriterion("treasure_box <>", value, "treasureBox");
            return (Criteria) this;
        }

        public Criteria andTreasureBoxGreaterThan(Integer value) {
            addCriterion("treasure_box >", value, "treasureBox");
            return (Criteria) this;
        }

        public Criteria andTreasureBoxGreaterThanOrEqualTo(Integer value) {
            addCriterion("treasure_box >=", value, "treasureBox");
            return (Criteria) this;
        }

        public Criteria andTreasureBoxLessThan(Integer value) {
            addCriterion("treasure_box <", value, "treasureBox");
            return (Criteria) this;
        }

        public Criteria andTreasureBoxLessThanOrEqualTo(Integer value) {
            addCriterion("treasure_box <=", value, "treasureBox");
            return (Criteria) this;
        }

        public Criteria andTreasureBoxIn(List<Integer> values) {
            addCriterion("treasure_box in", values, "treasureBox");
            return (Criteria) this;
        }

        public Criteria andTreasureBoxNotIn(List<Integer> values) {
            addCriterion("treasure_box not in", values, "treasureBox");
            return (Criteria) this;
        }

        public Criteria andTreasureBoxBetween(Integer value1, Integer value2) {
            addCriterion("treasure_box between", value1, value2, "treasureBox");
            return (Criteria) this;
        }

        public Criteria andTreasureBoxNotBetween(Integer value1, Integer value2) {
            addCriterion("treasure_box not between", value1, value2, "treasureBox");
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