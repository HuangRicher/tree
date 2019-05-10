package com.tongzhu.welfare.model;

import java.util.ArrayList;
import java.util.List;

public class BuildingTreeSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BuildingTreeSettingExample() {
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

        public Criteria andAmbienceCountIsNull() {
            addCriterion("ambience_count is null");
            return (Criteria) this;
        }

        public Criteria andAmbienceCountIsNotNull() {
            addCriterion("ambience_count is not null");
            return (Criteria) this;
        }

        public Criteria andAmbienceCountEqualTo(Long value) {
            addCriterion("ambience_count =", value, "ambienceCount");
            return (Criteria) this;
        }

        public Criteria andAmbienceCountNotEqualTo(Long value) {
            addCriterion("ambience_count <>", value, "ambienceCount");
            return (Criteria) this;
        }

        public Criteria andAmbienceCountGreaterThan(Long value) {
            addCriterion("ambience_count >", value, "ambienceCount");
            return (Criteria) this;
        }

        public Criteria andAmbienceCountGreaterThanOrEqualTo(Long value) {
            addCriterion("ambience_count >=", value, "ambienceCount");
            return (Criteria) this;
        }

        public Criteria andAmbienceCountLessThan(Long value) {
            addCriterion("ambience_count <", value, "ambienceCount");
            return (Criteria) this;
        }

        public Criteria andAmbienceCountLessThanOrEqualTo(Long value) {
            addCriterion("ambience_count <=", value, "ambienceCount");
            return (Criteria) this;
        }

        public Criteria andAmbienceCountIn(List<Long> values) {
            addCriterion("ambience_count in", values, "ambienceCount");
            return (Criteria) this;
        }

        public Criteria andAmbienceCountNotIn(List<Long> values) {
            addCriterion("ambience_count not in", values, "ambienceCount");
            return (Criteria) this;
        }

        public Criteria andAmbienceCountBetween(Long value1, Long value2) {
            addCriterion("ambience_count between", value1, value2, "ambienceCount");
            return (Criteria) this;
        }

        public Criteria andAmbienceCountNotBetween(Long value1, Long value2) {
            addCriterion("ambience_count not between", value1, value2, "ambienceCount");
            return (Criteria) this;
        }

        public Criteria andAmusementCountIsNull() {
            addCriterion("amusement_count is null");
            return (Criteria) this;
        }

        public Criteria andAmusementCountIsNotNull() {
            addCriterion("amusement_count is not null");
            return (Criteria) this;
        }

        public Criteria andAmusementCountEqualTo(Long value) {
            addCriterion("amusement_count =", value, "amusementCount");
            return (Criteria) this;
        }

        public Criteria andAmusementCountNotEqualTo(Long value) {
            addCriterion("amusement_count <>", value, "amusementCount");
            return (Criteria) this;
        }

        public Criteria andAmusementCountGreaterThan(Long value) {
            addCriterion("amusement_count >", value, "amusementCount");
            return (Criteria) this;
        }

        public Criteria andAmusementCountGreaterThanOrEqualTo(Long value) {
            addCriterion("amusement_count >=", value, "amusementCount");
            return (Criteria) this;
        }

        public Criteria andAmusementCountLessThan(Long value) {
            addCriterion("amusement_count <", value, "amusementCount");
            return (Criteria) this;
        }

        public Criteria andAmusementCountLessThanOrEqualTo(Long value) {
            addCriterion("amusement_count <=", value, "amusementCount");
            return (Criteria) this;
        }

        public Criteria andAmusementCountIn(List<Long> values) {
            addCriterion("amusement_count in", values, "amusementCount");
            return (Criteria) this;
        }

        public Criteria andAmusementCountNotIn(List<Long> values) {
            addCriterion("amusement_count not in", values, "amusementCount");
            return (Criteria) this;
        }

        public Criteria andAmusementCountBetween(Long value1, Long value2) {
            addCriterion("amusement_count between", value1, value2, "amusementCount");
            return (Criteria) this;
        }

        public Criteria andAmusementCountNotBetween(Long value1, Long value2) {
            addCriterion("amusement_count not between", value1, value2, "amusementCount");
            return (Criteria) this;
        }

        public Criteria andEnvironmentCountIsNull() {
            addCriterion("environment_count is null");
            return (Criteria) this;
        }

        public Criteria andEnvironmentCountIsNotNull() {
            addCriterion("environment_count is not null");
            return (Criteria) this;
        }

        public Criteria andEnvironmentCountEqualTo(Long value) {
            addCriterion("environment_count =", value, "environmentCount");
            return (Criteria) this;
        }

        public Criteria andEnvironmentCountNotEqualTo(Long value) {
            addCriterion("environment_count <>", value, "environmentCount");
            return (Criteria) this;
        }

        public Criteria andEnvironmentCountGreaterThan(Long value) {
            addCriterion("environment_count >", value, "environmentCount");
            return (Criteria) this;
        }

        public Criteria andEnvironmentCountGreaterThanOrEqualTo(Long value) {
            addCriterion("environment_count >=", value, "environmentCount");
            return (Criteria) this;
        }

        public Criteria andEnvironmentCountLessThan(Long value) {
            addCriterion("environment_count <", value, "environmentCount");
            return (Criteria) this;
        }

        public Criteria andEnvironmentCountLessThanOrEqualTo(Long value) {
            addCriterion("environment_count <=", value, "environmentCount");
            return (Criteria) this;
        }

        public Criteria andEnvironmentCountIn(List<Long> values) {
            addCriterion("environment_count in", values, "environmentCount");
            return (Criteria) this;
        }

        public Criteria andEnvironmentCountNotIn(List<Long> values) {
            addCriterion("environment_count not in", values, "environmentCount");
            return (Criteria) this;
        }

        public Criteria andEnvironmentCountBetween(Long value1, Long value2) {
            addCriterion("environment_count between", value1, value2, "environmentCount");
            return (Criteria) this;
        }

        public Criteria andEnvironmentCountNotBetween(Long value1, Long value2) {
            addCriterion("environment_count not between", value1, value2, "environmentCount");
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

        public Criteria andContainNumIsNull() {
            addCriterion("contain_num is null");
            return (Criteria) this;
        }

        public Criteria andContainNumIsNotNull() {
            addCriterion("contain_num is not null");
            return (Criteria) this;
        }

        public Criteria andContainNumEqualTo(Integer value) {
            addCriterion("contain_num =", value, "containNum");
            return (Criteria) this;
        }

        public Criteria andContainNumNotEqualTo(Integer value) {
            addCriterion("contain_num <>", value, "containNum");
            return (Criteria) this;
        }

        public Criteria andContainNumGreaterThan(Integer value) {
            addCriterion("contain_num >", value, "containNum");
            return (Criteria) this;
        }

        public Criteria andContainNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("contain_num >=", value, "containNum");
            return (Criteria) this;
        }

        public Criteria andContainNumLessThan(Integer value) {
            addCriterion("contain_num <", value, "containNum");
            return (Criteria) this;
        }

        public Criteria andContainNumLessThanOrEqualTo(Integer value) {
            addCriterion("contain_num <=", value, "containNum");
            return (Criteria) this;
        }

        public Criteria andContainNumIn(List<Integer> values) {
            addCriterion("contain_num in", values, "containNum");
            return (Criteria) this;
        }

        public Criteria andContainNumNotIn(List<Integer> values) {
            addCriterion("contain_num not in", values, "containNum");
            return (Criteria) this;
        }

        public Criteria andContainNumBetween(Integer value1, Integer value2) {
            addCriterion("contain_num between", value1, value2, "containNum");
            return (Criteria) this;
        }

        public Criteria andContainNumNotBetween(Integer value1, Integer value2) {
            addCriterion("contain_num not between", value1, value2, "containNum");
            return (Criteria) this;
        }

        public Criteria andContainThingIsNull() {
            addCriterion("contain_thing is null");
            return (Criteria) this;
        }

        public Criteria andContainThingIsNotNull() {
            addCriterion("contain_thing is not null");
            return (Criteria) this;
        }

        public Criteria andContainThingEqualTo(Integer value) {
            addCriterion("contain_thing =", value, "containThing");
            return (Criteria) this;
        }

        public Criteria andContainThingNotEqualTo(Integer value) {
            addCriterion("contain_thing <>", value, "containThing");
            return (Criteria) this;
        }

        public Criteria andContainThingGreaterThan(Integer value) {
            addCriterion("contain_thing >", value, "containThing");
            return (Criteria) this;
        }

        public Criteria andContainThingGreaterThanOrEqualTo(Integer value) {
            addCriterion("contain_thing >=", value, "containThing");
            return (Criteria) this;
        }

        public Criteria andContainThingLessThan(Integer value) {
            addCriterion("contain_thing <", value, "containThing");
            return (Criteria) this;
        }

        public Criteria andContainThingLessThanOrEqualTo(Integer value) {
            addCriterion("contain_thing <=", value, "containThing");
            return (Criteria) this;
        }

        public Criteria andContainThingIn(List<Integer> values) {
            addCriterion("contain_thing in", values, "containThing");
            return (Criteria) this;
        }

        public Criteria andContainThingNotIn(List<Integer> values) {
            addCriterion("contain_thing not in", values, "containThing");
            return (Criteria) this;
        }

        public Criteria andContainThingBetween(Integer value1, Integer value2) {
            addCriterion("contain_thing between", value1, value2, "containThing");
            return (Criteria) this;
        }

        public Criteria andContainThingNotBetween(Integer value1, Integer value2) {
            addCriterion("contain_thing not between", value1, value2, "containThing");
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