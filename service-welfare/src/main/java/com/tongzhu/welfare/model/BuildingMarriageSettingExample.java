package com.tongzhu.welfare.model;

import java.util.ArrayList;
import java.util.List;

public class BuildingMarriageSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BuildingMarriageSettingExample() {
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

        public Criteria andUpgradeGoldIsNull() {
            addCriterion("upgrade_gold is null");
            return (Criteria) this;
        }

        public Criteria andUpgradeGoldIsNotNull() {
            addCriterion("upgrade_gold is not null");
            return (Criteria) this;
        }

        public Criteria andUpgradeGoldEqualTo(Integer value) {
            addCriterion("upgrade_gold =", value, "upgradeGold");
            return (Criteria) this;
        }

        public Criteria andUpgradeGoldNotEqualTo(Integer value) {
            addCriterion("upgrade_gold <>", value, "upgradeGold");
            return (Criteria) this;
        }

        public Criteria andUpgradeGoldGreaterThan(Integer value) {
            addCriterion("upgrade_gold >", value, "upgradeGold");
            return (Criteria) this;
        }

        public Criteria andUpgradeGoldGreaterThanOrEqualTo(Integer value) {
            addCriterion("upgrade_gold >=", value, "upgradeGold");
            return (Criteria) this;
        }

        public Criteria andUpgradeGoldLessThan(Integer value) {
            addCriterion("upgrade_gold <", value, "upgradeGold");
            return (Criteria) this;
        }

        public Criteria andUpgradeGoldLessThanOrEqualTo(Integer value) {
            addCriterion("upgrade_gold <=", value, "upgradeGold");
            return (Criteria) this;
        }

        public Criteria andUpgradeGoldIn(List<Integer> values) {
            addCriterion("upgrade_gold in", values, "upgradeGold");
            return (Criteria) this;
        }

        public Criteria andUpgradeGoldNotIn(List<Integer> values) {
            addCriterion("upgrade_gold not in", values, "upgradeGold");
            return (Criteria) this;
        }

        public Criteria andUpgradeGoldBetween(Integer value1, Integer value2) {
            addCriterion("upgrade_gold between", value1, value2, "upgradeGold");
            return (Criteria) this;
        }

        public Criteria andUpgradeGoldNotBetween(Integer value1, Integer value2) {
            addCriterion("upgrade_gold not between", value1, value2, "upgradeGold");
            return (Criteria) this;
        }

        public Criteria andUpgradeTimeIsNull() {
            addCriterion("upgrade_time is null");
            return (Criteria) this;
        }

        public Criteria andUpgradeTimeIsNotNull() {
            addCriterion("upgrade_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpgradeTimeEqualTo(Integer value) {
            addCriterion("upgrade_time =", value, "upgradeTime");
            return (Criteria) this;
        }

        public Criteria andUpgradeTimeNotEqualTo(Integer value) {
            addCriterion("upgrade_time <>", value, "upgradeTime");
            return (Criteria) this;
        }

        public Criteria andUpgradeTimeGreaterThan(Integer value) {
            addCriterion("upgrade_time >", value, "upgradeTime");
            return (Criteria) this;
        }

        public Criteria andUpgradeTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("upgrade_time >=", value, "upgradeTime");
            return (Criteria) this;
        }

        public Criteria andUpgradeTimeLessThan(Integer value) {
            addCriterion("upgrade_time <", value, "upgradeTime");
            return (Criteria) this;
        }

        public Criteria andUpgradeTimeLessThanOrEqualTo(Integer value) {
            addCriterion("upgrade_time <=", value, "upgradeTime");
            return (Criteria) this;
        }

        public Criteria andUpgradeTimeIn(List<Integer> values) {
            addCriterion("upgrade_time in", values, "upgradeTime");
            return (Criteria) this;
        }

        public Criteria andUpgradeTimeNotIn(List<Integer> values) {
            addCriterion("upgrade_time not in", values, "upgradeTime");
            return (Criteria) this;
        }

        public Criteria andUpgradeTimeBetween(Integer value1, Integer value2) {
            addCriterion("upgrade_time between", value1, value2, "upgradeTime");
            return (Criteria) this;
        }

        public Criteria andUpgradeTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("upgrade_time not between", value1, value2, "upgradeTime");
            return (Criteria) this;
        }

        public Criteria andPlacedNumIsNull() {
            addCriterion("placed_num is null");
            return (Criteria) this;
        }

        public Criteria andPlacedNumIsNotNull() {
            addCriterion("placed_num is not null");
            return (Criteria) this;
        }

        public Criteria andPlacedNumEqualTo(Integer value) {
            addCriterion("placed_num =", value, "placedNum");
            return (Criteria) this;
        }

        public Criteria andPlacedNumNotEqualTo(Integer value) {
            addCriterion("placed_num <>", value, "placedNum");
            return (Criteria) this;
        }

        public Criteria andPlacedNumGreaterThan(Integer value) {
            addCriterion("placed_num >", value, "placedNum");
            return (Criteria) this;
        }

        public Criteria andPlacedNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("placed_num >=", value, "placedNum");
            return (Criteria) this;
        }

        public Criteria andPlacedNumLessThan(Integer value) {
            addCriterion("placed_num <", value, "placedNum");
            return (Criteria) this;
        }

        public Criteria andPlacedNumLessThanOrEqualTo(Integer value) {
            addCriterion("placed_num <=", value, "placedNum");
            return (Criteria) this;
        }

        public Criteria andPlacedNumIn(List<Integer> values) {
            addCriterion("placed_num in", values, "placedNum");
            return (Criteria) this;
        }

        public Criteria andPlacedNumNotIn(List<Integer> values) {
            addCriterion("placed_num not in", values, "placedNum");
            return (Criteria) this;
        }

        public Criteria andPlacedNumBetween(Integer value1, Integer value2) {
            addCriterion("placed_num between", value1, value2, "placedNum");
            return (Criteria) this;
        }

        public Criteria andPlacedNumNotBetween(Integer value1, Integer value2) {
            addCriterion("placed_num not between", value1, value2, "placedNum");
            return (Criteria) this;
        }

        public Criteria andRoomGradeIsNull() {
            addCriterion("room_grade is null");
            return (Criteria) this;
        }

        public Criteria andRoomGradeIsNotNull() {
            addCriterion("room_grade is not null");
            return (Criteria) this;
        }

        public Criteria andRoomGradeEqualTo(Integer value) {
            addCriterion("room_grade =", value, "roomGrade");
            return (Criteria) this;
        }

        public Criteria andRoomGradeNotEqualTo(Integer value) {
            addCriterion("room_grade <>", value, "roomGrade");
            return (Criteria) this;
        }

        public Criteria andRoomGradeGreaterThan(Integer value) {
            addCriterion("room_grade >", value, "roomGrade");
            return (Criteria) this;
        }

        public Criteria andRoomGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("room_grade >=", value, "roomGrade");
            return (Criteria) this;
        }

        public Criteria andRoomGradeLessThan(Integer value) {
            addCriterion("room_grade <", value, "roomGrade");
            return (Criteria) this;
        }

        public Criteria andRoomGradeLessThanOrEqualTo(Integer value) {
            addCriterion("room_grade <=", value, "roomGrade");
            return (Criteria) this;
        }

        public Criteria andRoomGradeIn(List<Integer> values) {
            addCriterion("room_grade in", values, "roomGrade");
            return (Criteria) this;
        }

        public Criteria andRoomGradeNotIn(List<Integer> values) {
            addCriterion("room_grade not in", values, "roomGrade");
            return (Criteria) this;
        }

        public Criteria andRoomGradeBetween(Integer value1, Integer value2) {
            addCriterion("room_grade between", value1, value2, "roomGrade");
            return (Criteria) this;
        }

        public Criteria andRoomGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("room_grade not between", value1, value2, "roomGrade");
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