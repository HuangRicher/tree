package com.tongzhu.treehouse.model;

import java.util.ArrayList;
import java.util.List;

public class TreeHouseLevelSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TreeHouseLevelSettingExample() {
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

        public Criteria andCultureValueIsNull() {
            addCriterion("culture_value is null");
            return (Criteria) this;
        }

        public Criteria andCultureValueIsNotNull() {
            addCriterion("culture_value is not null");
            return (Criteria) this;
        }

        public Criteria andCultureValueEqualTo(Integer value) {
            addCriterion("culture_value =", value, "cultureValue");
            return (Criteria) this;
        }

        public Criteria andCultureValueNotEqualTo(Integer value) {
            addCriterion("culture_value <>", value, "cultureValue");
            return (Criteria) this;
        }

        public Criteria andCultureValueGreaterThan(Integer value) {
            addCriterion("culture_value >", value, "cultureValue");
            return (Criteria) this;
        }

        public Criteria andCultureValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("culture_value >=", value, "cultureValue");
            return (Criteria) this;
        }

        public Criteria andCultureValueLessThan(Integer value) {
            addCriterion("culture_value <", value, "cultureValue");
            return (Criteria) this;
        }

        public Criteria andCultureValueLessThanOrEqualTo(Integer value) {
            addCriterion("culture_value <=", value, "cultureValue");
            return (Criteria) this;
        }

        public Criteria andCultureValueIn(List<Integer> values) {
            addCriterion("culture_value in", values, "cultureValue");
            return (Criteria) this;
        }

        public Criteria andCultureValueNotIn(List<Integer> values) {
            addCriterion("culture_value not in", values, "cultureValue");
            return (Criteria) this;
        }

        public Criteria andCultureValueBetween(Integer value1, Integer value2) {
            addCriterion("culture_value between", value1, value2, "cultureValue");
            return (Criteria) this;
        }

        public Criteria andCultureValueNotBetween(Integer value1, Integer value2) {
            addCriterion("culture_value not between", value1, value2, "cultureValue");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIdIsNull() {
            addCriterion("work_type_id is null");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIdIsNotNull() {
            addCriterion("work_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIdEqualTo(Integer value) {
            addCriterion("work_type_id =", value, "workTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIdNotEqualTo(Integer value) {
            addCriterion("work_type_id <>", value, "workTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIdGreaterThan(Integer value) {
            addCriterion("work_type_id >", value, "workTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("work_type_id >=", value, "workTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIdLessThan(Integer value) {
            addCriterion("work_type_id <", value, "workTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("work_type_id <=", value, "workTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIdIn(List<Integer> values) {
            addCriterion("work_type_id in", values, "workTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIdNotIn(List<Integer> values) {
            addCriterion("work_type_id not in", values, "workTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("work_type_id between", value1, value2, "workTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("work_type_id not between", value1, value2, "workTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLevelIsNull() {
            addCriterion("work_type_level is null");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLevelIsNotNull() {
            addCriterion("work_type_level is not null");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLevelEqualTo(Integer value) {
            addCriterion("work_type_level =", value, "workTypeLevel");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLevelNotEqualTo(Integer value) {
            addCriterion("work_type_level <>", value, "workTypeLevel");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLevelGreaterThan(Integer value) {
            addCriterion("work_type_level >", value, "workTypeLevel");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("work_type_level >=", value, "workTypeLevel");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLevelLessThan(Integer value) {
            addCriterion("work_type_level <", value, "workTypeLevel");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLevelLessThanOrEqualTo(Integer value) {
            addCriterion("work_type_level <=", value, "workTypeLevel");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLevelIn(List<Integer> values) {
            addCriterion("work_type_level in", values, "workTypeLevel");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLevelNotIn(List<Integer> values) {
            addCriterion("work_type_level not in", values, "workTypeLevel");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLevelBetween(Integer value1, Integer value2) {
            addCriterion("work_type_level between", value1, value2, "workTypeLevel");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("work_type_level not between", value1, value2, "workTypeLevel");
            return (Criteria) this;
        }

        public Criteria andExperienceMinIsNull() {
            addCriterion("experience_min is null");
            return (Criteria) this;
        }

        public Criteria andExperienceMinIsNotNull() {
            addCriterion("experience_min is not null");
            return (Criteria) this;
        }

        public Criteria andExperienceMinEqualTo(Integer value) {
            addCriterion("experience_min =", value, "experienceMin");
            return (Criteria) this;
        }

        public Criteria andExperienceMinNotEqualTo(Integer value) {
            addCriterion("experience_min <>", value, "experienceMin");
            return (Criteria) this;
        }

        public Criteria andExperienceMinGreaterThan(Integer value) {
            addCriterion("experience_min >", value, "experienceMin");
            return (Criteria) this;
        }

        public Criteria andExperienceMinGreaterThanOrEqualTo(Integer value) {
            addCriterion("experience_min >=", value, "experienceMin");
            return (Criteria) this;
        }

        public Criteria andExperienceMinLessThan(Integer value) {
            addCriterion("experience_min <", value, "experienceMin");
            return (Criteria) this;
        }

        public Criteria andExperienceMinLessThanOrEqualTo(Integer value) {
            addCriterion("experience_min <=", value, "experienceMin");
            return (Criteria) this;
        }

        public Criteria andExperienceMinIn(List<Integer> values) {
            addCriterion("experience_min in", values, "experienceMin");
            return (Criteria) this;
        }

        public Criteria andExperienceMinNotIn(List<Integer> values) {
            addCriterion("experience_min not in", values, "experienceMin");
            return (Criteria) this;
        }

        public Criteria andExperienceMinBetween(Integer value1, Integer value2) {
            addCriterion("experience_min between", value1, value2, "experienceMin");
            return (Criteria) this;
        }

        public Criteria andExperienceMinNotBetween(Integer value1, Integer value2) {
            addCriterion("experience_min not between", value1, value2, "experienceMin");
            return (Criteria) this;
        }

        public Criteria andExperienceMaxIsNull() {
            addCriterion("experience_max is null");
            return (Criteria) this;
        }

        public Criteria andExperienceMaxIsNotNull() {
            addCriterion("experience_max is not null");
            return (Criteria) this;
        }

        public Criteria andExperienceMaxEqualTo(Integer value) {
            addCriterion("experience_max =", value, "experienceMax");
            return (Criteria) this;
        }

        public Criteria andExperienceMaxNotEqualTo(Integer value) {
            addCriterion("experience_max <>", value, "experienceMax");
            return (Criteria) this;
        }

        public Criteria andExperienceMaxGreaterThan(Integer value) {
            addCriterion("experience_max >", value, "experienceMax");
            return (Criteria) this;
        }

        public Criteria andExperienceMaxGreaterThanOrEqualTo(Integer value) {
            addCriterion("experience_max >=", value, "experienceMax");
            return (Criteria) this;
        }

        public Criteria andExperienceMaxLessThan(Integer value) {
            addCriterion("experience_max <", value, "experienceMax");
            return (Criteria) this;
        }

        public Criteria andExperienceMaxLessThanOrEqualTo(Integer value) {
            addCriterion("experience_max <=", value, "experienceMax");
            return (Criteria) this;
        }

        public Criteria andExperienceMaxIn(List<Integer> values) {
            addCriterion("experience_max in", values, "experienceMax");
            return (Criteria) this;
        }

        public Criteria andExperienceMaxNotIn(List<Integer> values) {
            addCriterion("experience_max not in", values, "experienceMax");
            return (Criteria) this;
        }

        public Criteria andExperienceMaxBetween(Integer value1, Integer value2) {
            addCriterion("experience_max between", value1, value2, "experienceMax");
            return (Criteria) this;
        }

        public Criteria andExperienceMaxNotBetween(Integer value1, Integer value2) {
            addCriterion("experience_max not between", value1, value2, "experienceMax");
            return (Criteria) this;
        }

        public Criteria andUpgradeConsumeGoodsIsNull() {
            addCriterion("upgrade_consume_goods is null");
            return (Criteria) this;
        }

        public Criteria andUpgradeConsumeGoodsIsNotNull() {
            addCriterion("upgrade_consume_goods is not null");
            return (Criteria) this;
        }

        public Criteria andUpgradeConsumeGoodsEqualTo(String value) {
            addCriterion("upgrade_consume_goods =", value, "upgradeConsumeGoods");
            return (Criteria) this;
        }

        public Criteria andUpgradeConsumeGoodsNotEqualTo(String value) {
            addCriterion("upgrade_consume_goods <>", value, "upgradeConsumeGoods");
            return (Criteria) this;
        }

        public Criteria andUpgradeConsumeGoodsGreaterThan(String value) {
            addCriterion("upgrade_consume_goods >", value, "upgradeConsumeGoods");
            return (Criteria) this;
        }

        public Criteria andUpgradeConsumeGoodsGreaterThanOrEqualTo(String value) {
            addCriterion("upgrade_consume_goods >=", value, "upgradeConsumeGoods");
            return (Criteria) this;
        }

        public Criteria andUpgradeConsumeGoodsLessThan(String value) {
            addCriterion("upgrade_consume_goods <", value, "upgradeConsumeGoods");
            return (Criteria) this;
        }

        public Criteria andUpgradeConsumeGoodsLessThanOrEqualTo(String value) {
            addCriterion("upgrade_consume_goods <=", value, "upgradeConsumeGoods");
            return (Criteria) this;
        }

        public Criteria andUpgradeConsumeGoodsLike(String value) {
            addCriterion("upgrade_consume_goods like", value, "upgradeConsumeGoods");
            return (Criteria) this;
        }

        public Criteria andUpgradeConsumeGoodsNotLike(String value) {
            addCriterion("upgrade_consume_goods not like", value, "upgradeConsumeGoods");
            return (Criteria) this;
        }

        public Criteria andUpgradeConsumeGoodsIn(List<String> values) {
            addCriterion("upgrade_consume_goods in", values, "upgradeConsumeGoods");
            return (Criteria) this;
        }

        public Criteria andUpgradeConsumeGoodsNotIn(List<String> values) {
            addCriterion("upgrade_consume_goods not in", values, "upgradeConsumeGoods");
            return (Criteria) this;
        }

        public Criteria andUpgradeConsumeGoodsBetween(String value1, String value2) {
            addCriterion("upgrade_consume_goods between", value1, value2, "upgradeConsumeGoods");
            return (Criteria) this;
        }

        public Criteria andUpgradeConsumeGoodsNotBetween(String value1, String value2) {
            addCriterion("upgrade_consume_goods not between", value1, value2, "upgradeConsumeGoods");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardIsNull() {
            addCriterion("upgrade_award is null");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardIsNotNull() {
            addCriterion("upgrade_award is not null");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardEqualTo(String value) {
            addCriterion("upgrade_award =", value, "upgradeAward");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardNotEqualTo(String value) {
            addCriterion("upgrade_award <>", value, "upgradeAward");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardGreaterThan(String value) {
            addCriterion("upgrade_award >", value, "upgradeAward");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardGreaterThanOrEqualTo(String value) {
            addCriterion("upgrade_award >=", value, "upgradeAward");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardLessThan(String value) {
            addCriterion("upgrade_award <", value, "upgradeAward");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardLessThanOrEqualTo(String value) {
            addCriterion("upgrade_award <=", value, "upgradeAward");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardLike(String value) {
            addCriterion("upgrade_award like", value, "upgradeAward");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardNotLike(String value) {
            addCriterion("upgrade_award not like", value, "upgradeAward");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardIn(List<String> values) {
            addCriterion("upgrade_award in", values, "upgradeAward");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardNotIn(List<String> values) {
            addCriterion("upgrade_award not in", values, "upgradeAward");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardBetween(String value1, String value2) {
            addCriterion("upgrade_award between", value1, value2, "upgradeAward");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardNotBetween(String value1, String value2) {
            addCriterion("upgrade_award not between", value1, value2, "upgradeAward");
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