package com.tongzhu.fishing.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FishingSecretOperationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FishingSecretOperationExample() {
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

        public Criteria andNumberFishingIsNull() {
            addCriterion("number_fishing is null");
            return (Criteria) this;
        }

        public Criteria andNumberFishingIsNotNull() {
            addCriterion("number_fishing is not null");
            return (Criteria) this;
        }

        public Criteria andNumberFishingEqualTo(Integer value) {
            addCriterion("number_fishing =", value, "numberFishing");
            return (Criteria) this;
        }

        public Criteria andNumberFishingNotEqualTo(Integer value) {
            addCriterion("number_fishing <>", value, "numberFishing");
            return (Criteria) this;
        }

        public Criteria andNumberFishingGreaterThan(Integer value) {
            addCriterion("number_fishing >", value, "numberFishing");
            return (Criteria) this;
        }

        public Criteria andNumberFishingGreaterThanOrEqualTo(Integer value) {
            addCriterion("number_fishing >=", value, "numberFishing");
            return (Criteria) this;
        }

        public Criteria andNumberFishingLessThan(Integer value) {
            addCriterion("number_fishing <", value, "numberFishing");
            return (Criteria) this;
        }

        public Criteria andNumberFishingLessThanOrEqualTo(Integer value) {
            addCriterion("number_fishing <=", value, "numberFishing");
            return (Criteria) this;
        }

        public Criteria andNumberFishingIn(List<Integer> values) {
            addCriterion("number_fishing in", values, "numberFishing");
            return (Criteria) this;
        }

        public Criteria andNumberFishingNotIn(List<Integer> values) {
            addCriterion("number_fishing not in", values, "numberFishing");
            return (Criteria) this;
        }

        public Criteria andNumberFishingBetween(Integer value1, Integer value2) {
            addCriterion("number_fishing between", value1, value2, "numberFishing");
            return (Criteria) this;
        }

        public Criteria andNumberFishingNotBetween(Integer value1, Integer value2) {
            addCriterion("number_fishing not between", value1, value2, "numberFishing");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andCommonFishAwardsIsNull() {
            addCriterion("common_fish_awards is null");
            return (Criteria) this;
        }

        public Criteria andCommonFishAwardsIsNotNull() {
            addCriterion("common_fish_awards is not null");
            return (Criteria) this;
        }

        public Criteria andCommonFishAwardsEqualTo(String value) {
            addCriterion("common_fish_awards =", value, "commonFishAwards");
            return (Criteria) this;
        }

        public Criteria andCommonFishAwardsNotEqualTo(String value) {
            addCriterion("common_fish_awards <>", value, "commonFishAwards");
            return (Criteria) this;
        }

        public Criteria andCommonFishAwardsGreaterThan(String value) {
            addCriterion("common_fish_awards >", value, "commonFishAwards");
            return (Criteria) this;
        }

        public Criteria andCommonFishAwardsGreaterThanOrEqualTo(String value) {
            addCriterion("common_fish_awards >=", value, "commonFishAwards");
            return (Criteria) this;
        }

        public Criteria andCommonFishAwardsLessThan(String value) {
            addCriterion("common_fish_awards <", value, "commonFishAwards");
            return (Criteria) this;
        }

        public Criteria andCommonFishAwardsLessThanOrEqualTo(String value) {
            addCriterion("common_fish_awards <=", value, "commonFishAwards");
            return (Criteria) this;
        }

        public Criteria andCommonFishAwardsLike(String value) {
            addCriterion("common_fish_awards like", value, "commonFishAwards");
            return (Criteria) this;
        }

        public Criteria andCommonFishAwardsNotLike(String value) {
            addCriterion("common_fish_awards not like", value, "commonFishAwards");
            return (Criteria) this;
        }

        public Criteria andCommonFishAwardsIn(List<String> values) {
            addCriterion("common_fish_awards in", values, "commonFishAwards");
            return (Criteria) this;
        }

        public Criteria andCommonFishAwardsNotIn(List<String> values) {
            addCriterion("common_fish_awards not in", values, "commonFishAwards");
            return (Criteria) this;
        }

        public Criteria andCommonFishAwardsBetween(String value1, String value2) {
            addCriterion("common_fish_awards between", value1, value2, "commonFishAwards");
            return (Criteria) this;
        }

        public Criteria andCommonFishAwardsNotBetween(String value1, String value2) {
            addCriterion("common_fish_awards not between", value1, value2, "commonFishAwards");
            return (Criteria) this;
        }

        public Criteria andCommonTotalIsNull() {
            addCriterion("common_total is null");
            return (Criteria) this;
        }

        public Criteria andCommonTotalIsNotNull() {
            addCriterion("common_total is not null");
            return (Criteria) this;
        }

        public Criteria andCommonTotalEqualTo(Integer value) {
            addCriterion("common_total =", value, "commonTotal");
            return (Criteria) this;
        }

        public Criteria andCommonTotalNotEqualTo(Integer value) {
            addCriterion("common_total <>", value, "commonTotal");
            return (Criteria) this;
        }

        public Criteria andCommonTotalGreaterThan(Integer value) {
            addCriterion("common_total >", value, "commonTotal");
            return (Criteria) this;
        }

        public Criteria andCommonTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("common_total >=", value, "commonTotal");
            return (Criteria) this;
        }

        public Criteria andCommonTotalLessThan(Integer value) {
            addCriterion("common_total <", value, "commonTotal");
            return (Criteria) this;
        }

        public Criteria andCommonTotalLessThanOrEqualTo(Integer value) {
            addCriterion("common_total <=", value, "commonTotal");
            return (Criteria) this;
        }

        public Criteria andCommonTotalIn(List<Integer> values) {
            addCriterion("common_total in", values, "commonTotal");
            return (Criteria) this;
        }

        public Criteria andCommonTotalNotIn(List<Integer> values) {
            addCriterion("common_total not in", values, "commonTotal");
            return (Criteria) this;
        }

        public Criteria andCommonTotalBetween(Integer value1, Integer value2) {
            addCriterion("common_total between", value1, value2, "commonTotal");
            return (Criteria) this;
        }

        public Criteria andCommonTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("common_total not between", value1, value2, "commonTotal");
            return (Criteria) this;
        }

        public Criteria andSeniorFishAwardsIsNull() {
            addCriterion("senior_fish_awards is null");
            return (Criteria) this;
        }

        public Criteria andSeniorFishAwardsIsNotNull() {
            addCriterion("senior_fish_awards is not null");
            return (Criteria) this;
        }

        public Criteria andSeniorFishAwardsEqualTo(String value) {
            addCriterion("senior_fish_awards =", value, "seniorFishAwards");
            return (Criteria) this;
        }

        public Criteria andSeniorFishAwardsNotEqualTo(String value) {
            addCriterion("senior_fish_awards <>", value, "seniorFishAwards");
            return (Criteria) this;
        }

        public Criteria andSeniorFishAwardsGreaterThan(String value) {
            addCriterion("senior_fish_awards >", value, "seniorFishAwards");
            return (Criteria) this;
        }

        public Criteria andSeniorFishAwardsGreaterThanOrEqualTo(String value) {
            addCriterion("senior_fish_awards >=", value, "seniorFishAwards");
            return (Criteria) this;
        }

        public Criteria andSeniorFishAwardsLessThan(String value) {
            addCriterion("senior_fish_awards <", value, "seniorFishAwards");
            return (Criteria) this;
        }

        public Criteria andSeniorFishAwardsLessThanOrEqualTo(String value) {
            addCriterion("senior_fish_awards <=", value, "seniorFishAwards");
            return (Criteria) this;
        }

        public Criteria andSeniorFishAwardsLike(String value) {
            addCriterion("senior_fish_awards like", value, "seniorFishAwards");
            return (Criteria) this;
        }

        public Criteria andSeniorFishAwardsNotLike(String value) {
            addCriterion("senior_fish_awards not like", value, "seniorFishAwards");
            return (Criteria) this;
        }

        public Criteria andSeniorFishAwardsIn(List<String> values) {
            addCriterion("senior_fish_awards in", values, "seniorFishAwards");
            return (Criteria) this;
        }

        public Criteria andSeniorFishAwardsNotIn(List<String> values) {
            addCriterion("senior_fish_awards not in", values, "seniorFishAwards");
            return (Criteria) this;
        }

        public Criteria andSeniorFishAwardsBetween(String value1, String value2) {
            addCriterion("senior_fish_awards between", value1, value2, "seniorFishAwards");
            return (Criteria) this;
        }

        public Criteria andSeniorFishAwardsNotBetween(String value1, String value2) {
            addCriterion("senior_fish_awards not between", value1, value2, "seniorFishAwards");
            return (Criteria) this;
        }

        public Criteria andSeniorTotalIsNull() {
            addCriterion("senior_total is null");
            return (Criteria) this;
        }

        public Criteria andSeniorTotalIsNotNull() {
            addCriterion("senior_total is not null");
            return (Criteria) this;
        }

        public Criteria andSeniorTotalEqualTo(Integer value) {
            addCriterion("senior_total =", value, "seniorTotal");
            return (Criteria) this;
        }

        public Criteria andSeniorTotalNotEqualTo(Integer value) {
            addCriterion("senior_total <>", value, "seniorTotal");
            return (Criteria) this;
        }

        public Criteria andSeniorTotalGreaterThan(Integer value) {
            addCriterion("senior_total >", value, "seniorTotal");
            return (Criteria) this;
        }

        public Criteria andSeniorTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("senior_total >=", value, "seniorTotal");
            return (Criteria) this;
        }

        public Criteria andSeniorTotalLessThan(Integer value) {
            addCriterion("senior_total <", value, "seniorTotal");
            return (Criteria) this;
        }

        public Criteria andSeniorTotalLessThanOrEqualTo(Integer value) {
            addCriterion("senior_total <=", value, "seniorTotal");
            return (Criteria) this;
        }

        public Criteria andSeniorTotalIn(List<Integer> values) {
            addCriterion("senior_total in", values, "seniorTotal");
            return (Criteria) this;
        }

        public Criteria andSeniorTotalNotIn(List<Integer> values) {
            addCriterion("senior_total not in", values, "seniorTotal");
            return (Criteria) this;
        }

        public Criteria andSeniorTotalBetween(Integer value1, Integer value2) {
            addCriterion("senior_total between", value1, value2, "seniorTotal");
            return (Criteria) this;
        }

        public Criteria andSeniorTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("senior_total not between", value1, value2, "seniorTotal");
            return (Criteria) this;
        }

        public Criteria andRareFishAwardsIsNull() {
            addCriterion("rare_fish_awards is null");
            return (Criteria) this;
        }

        public Criteria andRareFishAwardsIsNotNull() {
            addCriterion("rare_fish_awards is not null");
            return (Criteria) this;
        }

        public Criteria andRareFishAwardsEqualTo(String value) {
            addCriterion("rare_fish_awards =", value, "rareFishAwards");
            return (Criteria) this;
        }

        public Criteria andRareFishAwardsNotEqualTo(String value) {
            addCriterion("rare_fish_awards <>", value, "rareFishAwards");
            return (Criteria) this;
        }

        public Criteria andRareFishAwardsGreaterThan(String value) {
            addCriterion("rare_fish_awards >", value, "rareFishAwards");
            return (Criteria) this;
        }

        public Criteria andRareFishAwardsGreaterThanOrEqualTo(String value) {
            addCriterion("rare_fish_awards >=", value, "rareFishAwards");
            return (Criteria) this;
        }

        public Criteria andRareFishAwardsLessThan(String value) {
            addCriterion("rare_fish_awards <", value, "rareFishAwards");
            return (Criteria) this;
        }

        public Criteria andRareFishAwardsLessThanOrEqualTo(String value) {
            addCriterion("rare_fish_awards <=", value, "rareFishAwards");
            return (Criteria) this;
        }

        public Criteria andRareFishAwardsLike(String value) {
            addCriterion("rare_fish_awards like", value, "rareFishAwards");
            return (Criteria) this;
        }

        public Criteria andRareFishAwardsNotLike(String value) {
            addCriterion("rare_fish_awards not like", value, "rareFishAwards");
            return (Criteria) this;
        }

        public Criteria andRareFishAwardsIn(List<String> values) {
            addCriterion("rare_fish_awards in", values, "rareFishAwards");
            return (Criteria) this;
        }

        public Criteria andRareFishAwardsNotIn(List<String> values) {
            addCriterion("rare_fish_awards not in", values, "rareFishAwards");
            return (Criteria) this;
        }

        public Criteria andRareFishAwardsBetween(String value1, String value2) {
            addCriterion("rare_fish_awards between", value1, value2, "rareFishAwards");
            return (Criteria) this;
        }

        public Criteria andRareFishAwardsNotBetween(String value1, String value2) {
            addCriterion("rare_fish_awards not between", value1, value2, "rareFishAwards");
            return (Criteria) this;
        }

        public Criteria andRareTotalIsNull() {
            addCriterion("rare_total is null");
            return (Criteria) this;
        }

        public Criteria andRareTotalIsNotNull() {
            addCriterion("rare_total is not null");
            return (Criteria) this;
        }

        public Criteria andRareTotalEqualTo(Integer value) {
            addCriterion("rare_total =", value, "rareTotal");
            return (Criteria) this;
        }

        public Criteria andRareTotalNotEqualTo(Integer value) {
            addCriterion("rare_total <>", value, "rareTotal");
            return (Criteria) this;
        }

        public Criteria andRareTotalGreaterThan(Integer value) {
            addCriterion("rare_total >", value, "rareTotal");
            return (Criteria) this;
        }

        public Criteria andRareTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("rare_total >=", value, "rareTotal");
            return (Criteria) this;
        }

        public Criteria andRareTotalLessThan(Integer value) {
            addCriterion("rare_total <", value, "rareTotal");
            return (Criteria) this;
        }

        public Criteria andRareTotalLessThanOrEqualTo(Integer value) {
            addCriterion("rare_total <=", value, "rareTotal");
            return (Criteria) this;
        }

        public Criteria andRareTotalIn(List<Integer> values) {
            addCriterion("rare_total in", values, "rareTotal");
            return (Criteria) this;
        }

        public Criteria andRareTotalNotIn(List<Integer> values) {
            addCriterion("rare_total not in", values, "rareTotal");
            return (Criteria) this;
        }

        public Criteria andRareTotalBetween(Integer value1, Integer value2) {
            addCriterion("rare_total between", value1, value2, "rareTotal");
            return (Criteria) this;
        }

        public Criteria andRareTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("rare_total not between", value1, value2, "rareTotal");
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