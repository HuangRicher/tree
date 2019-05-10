package com.tongzhu.user.model;

import java.util.ArrayList;
import java.util.List;

public class FightRankingSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FightRankingSettingExample() {
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

        public Criteria andRankIdIsNull() {
            addCriterion("rank_id is null");
            return (Criteria) this;
        }

        public Criteria andRankIdIsNotNull() {
            addCriterion("rank_id is not null");
            return (Criteria) this;
        }

        public Criteria andRankIdEqualTo(Integer value) {
            addCriterion("rank_id =", value, "rankId");
            return (Criteria) this;
        }

        public Criteria andRankIdNotEqualTo(Integer value) {
            addCriterion("rank_id <>", value, "rankId");
            return (Criteria) this;
        }

        public Criteria andRankIdGreaterThan(Integer value) {
            addCriterion("rank_id >", value, "rankId");
            return (Criteria) this;
        }

        public Criteria andRankIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("rank_id >=", value, "rankId");
            return (Criteria) this;
        }

        public Criteria andRankIdLessThan(Integer value) {
            addCriterion("rank_id <", value, "rankId");
            return (Criteria) this;
        }

        public Criteria andRankIdLessThanOrEqualTo(Integer value) {
            addCriterion("rank_id <=", value, "rankId");
            return (Criteria) this;
        }

        public Criteria andRankIdIn(List<Integer> values) {
            addCriterion("rank_id in", values, "rankId");
            return (Criteria) this;
        }

        public Criteria andRankIdNotIn(List<Integer> values) {
            addCriterion("rank_id not in", values, "rankId");
            return (Criteria) this;
        }

        public Criteria andRankIdBetween(Integer value1, Integer value2) {
            addCriterion("rank_id between", value1, value2, "rankId");
            return (Criteria) this;
        }

        public Criteria andRankIdNotBetween(Integer value1, Integer value2) {
            addCriterion("rank_id not between", value1, value2, "rankId");
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andMaxRankIsNull() {
            addCriterion("max_rank is null");
            return (Criteria) this;
        }

        public Criteria andMaxRankIsNotNull() {
            addCriterion("max_rank is not null");
            return (Criteria) this;
        }

        public Criteria andMaxRankEqualTo(Integer value) {
            addCriterion("max_rank =", value, "maxRank");
            return (Criteria) this;
        }

        public Criteria andMaxRankNotEqualTo(Integer value) {
            addCriterion("max_rank <>", value, "maxRank");
            return (Criteria) this;
        }

        public Criteria andMaxRankGreaterThan(Integer value) {
            addCriterion("max_rank >", value, "maxRank");
            return (Criteria) this;
        }

        public Criteria andMaxRankGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_rank >=", value, "maxRank");
            return (Criteria) this;
        }

        public Criteria andMaxRankLessThan(Integer value) {
            addCriterion("max_rank <", value, "maxRank");
            return (Criteria) this;
        }

        public Criteria andMaxRankLessThanOrEqualTo(Integer value) {
            addCriterion("max_rank <=", value, "maxRank");
            return (Criteria) this;
        }

        public Criteria andMaxRankIn(List<Integer> values) {
            addCriterion("max_rank in", values, "maxRank");
            return (Criteria) this;
        }

        public Criteria andMaxRankNotIn(List<Integer> values) {
            addCriterion("max_rank not in", values, "maxRank");
            return (Criteria) this;
        }

        public Criteria andMaxRankBetween(Integer value1, Integer value2) {
            addCriterion("max_rank between", value1, value2, "maxRank");
            return (Criteria) this;
        }

        public Criteria andMaxRankNotBetween(Integer value1, Integer value2) {
            addCriterion("max_rank not between", value1, value2, "maxRank");
            return (Criteria) this;
        }

        public Criteria andMinRankIsNull() {
            addCriterion("min_rank is null");
            return (Criteria) this;
        }

        public Criteria andMinRankIsNotNull() {
            addCriterion("min_rank is not null");
            return (Criteria) this;
        }

        public Criteria andMinRankEqualTo(Integer value) {
            addCriterion("min_rank =", value, "minRank");
            return (Criteria) this;
        }

        public Criteria andMinRankNotEqualTo(Integer value) {
            addCriterion("min_rank <>", value, "minRank");
            return (Criteria) this;
        }

        public Criteria andMinRankGreaterThan(Integer value) {
            addCriterion("min_rank >", value, "minRank");
            return (Criteria) this;
        }

        public Criteria andMinRankGreaterThanOrEqualTo(Integer value) {
            addCriterion("min_rank >=", value, "minRank");
            return (Criteria) this;
        }

        public Criteria andMinRankLessThan(Integer value) {
            addCriterion("min_rank <", value, "minRank");
            return (Criteria) this;
        }

        public Criteria andMinRankLessThanOrEqualTo(Integer value) {
            addCriterion("min_rank <=", value, "minRank");
            return (Criteria) this;
        }

        public Criteria andMinRankIn(List<Integer> values) {
            addCriterion("min_rank in", values, "minRank");
            return (Criteria) this;
        }

        public Criteria andMinRankNotIn(List<Integer> values) {
            addCriterion("min_rank not in", values, "minRank");
            return (Criteria) this;
        }

        public Criteria andMinRankBetween(Integer value1, Integer value2) {
            addCriterion("min_rank between", value1, value2, "minRank");
            return (Criteria) this;
        }

        public Criteria andMinRankNotBetween(Integer value1, Integer value2) {
            addCriterion("min_rank not between", value1, value2, "minRank");
            return (Criteria) this;
        }

        public Criteria andFirstRankIsNull() {
            addCriterion("first_rank is null");
            return (Criteria) this;
        }

        public Criteria andFirstRankIsNotNull() {
            addCriterion("first_rank is not null");
            return (Criteria) this;
        }

        public Criteria andFirstRankEqualTo(Integer value) {
            addCriterion("first_rank =", value, "firstRank");
            return (Criteria) this;
        }

        public Criteria andFirstRankNotEqualTo(Integer value) {
            addCriterion("first_rank <>", value, "firstRank");
            return (Criteria) this;
        }

        public Criteria andFirstRankGreaterThan(Integer value) {
            addCriterion("first_rank >", value, "firstRank");
            return (Criteria) this;
        }

        public Criteria andFirstRankGreaterThanOrEqualTo(Integer value) {
            addCriterion("first_rank >=", value, "firstRank");
            return (Criteria) this;
        }

        public Criteria andFirstRankLessThan(Integer value) {
            addCriterion("first_rank <", value, "firstRank");
            return (Criteria) this;
        }

        public Criteria andFirstRankLessThanOrEqualTo(Integer value) {
            addCriterion("first_rank <=", value, "firstRank");
            return (Criteria) this;
        }

        public Criteria andFirstRankIn(List<Integer> values) {
            addCriterion("first_rank in", values, "firstRank");
            return (Criteria) this;
        }

        public Criteria andFirstRankNotIn(List<Integer> values) {
            addCriterion("first_rank not in", values, "firstRank");
            return (Criteria) this;
        }

        public Criteria andFirstRankBetween(Integer value1, Integer value2) {
            addCriterion("first_rank between", value1, value2, "firstRank");
            return (Criteria) this;
        }

        public Criteria andFirstRankNotBetween(Integer value1, Integer value2) {
            addCriterion("first_rank not between", value1, value2, "firstRank");
            return (Criteria) this;
        }

        public Criteria andAwardMoneyDayIsNull() {
            addCriterion("award_money_day is null");
            return (Criteria) this;
        }

        public Criteria andAwardMoneyDayIsNotNull() {
            addCriterion("award_money_day is not null");
            return (Criteria) this;
        }

        public Criteria andAwardMoneyDayEqualTo(Integer value) {
            addCriterion("award_money_day =", value, "awardMoneyDay");
            return (Criteria) this;
        }

        public Criteria andAwardMoneyDayNotEqualTo(Integer value) {
            addCriterion("award_money_day <>", value, "awardMoneyDay");
            return (Criteria) this;
        }

        public Criteria andAwardMoneyDayGreaterThan(Integer value) {
            addCriterion("award_money_day >", value, "awardMoneyDay");
            return (Criteria) this;
        }

        public Criteria andAwardMoneyDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("award_money_day >=", value, "awardMoneyDay");
            return (Criteria) this;
        }

        public Criteria andAwardMoneyDayLessThan(Integer value) {
            addCriterion("award_money_day <", value, "awardMoneyDay");
            return (Criteria) this;
        }

        public Criteria andAwardMoneyDayLessThanOrEqualTo(Integer value) {
            addCriterion("award_money_day <=", value, "awardMoneyDay");
            return (Criteria) this;
        }

        public Criteria andAwardMoneyDayIn(List<Integer> values) {
            addCriterion("award_money_day in", values, "awardMoneyDay");
            return (Criteria) this;
        }

        public Criteria andAwardMoneyDayNotIn(List<Integer> values) {
            addCriterion("award_money_day not in", values, "awardMoneyDay");
            return (Criteria) this;
        }

        public Criteria andAwardMoneyDayBetween(Integer value1, Integer value2) {
            addCriterion("award_money_day between", value1, value2, "awardMoneyDay");
            return (Criteria) this;
        }

        public Criteria andAwardMoneyDayNotBetween(Integer value1, Integer value2) {
            addCriterion("award_money_day not between", value1, value2, "awardMoneyDay");
            return (Criteria) this;
        }

        public Criteria andAwardMoneySeasonIsNull() {
            addCriterion("award_money_season is null");
            return (Criteria) this;
        }

        public Criteria andAwardMoneySeasonIsNotNull() {
            addCriterion("award_money_season is not null");
            return (Criteria) this;
        }

        public Criteria andAwardMoneySeasonEqualTo(Integer value) {
            addCriterion("award_money_season =", value, "awardMoneySeason");
            return (Criteria) this;
        }

        public Criteria andAwardMoneySeasonNotEqualTo(Integer value) {
            addCriterion("award_money_season <>", value, "awardMoneySeason");
            return (Criteria) this;
        }

        public Criteria andAwardMoneySeasonGreaterThan(Integer value) {
            addCriterion("award_money_season >", value, "awardMoneySeason");
            return (Criteria) this;
        }

        public Criteria andAwardMoneySeasonGreaterThanOrEqualTo(Integer value) {
            addCriterion("award_money_season >=", value, "awardMoneySeason");
            return (Criteria) this;
        }

        public Criteria andAwardMoneySeasonLessThan(Integer value) {
            addCriterion("award_money_season <", value, "awardMoneySeason");
            return (Criteria) this;
        }

        public Criteria andAwardMoneySeasonLessThanOrEqualTo(Integer value) {
            addCriterion("award_money_season <=", value, "awardMoneySeason");
            return (Criteria) this;
        }

        public Criteria andAwardMoneySeasonIn(List<Integer> values) {
            addCriterion("award_money_season in", values, "awardMoneySeason");
            return (Criteria) this;
        }

        public Criteria andAwardMoneySeasonNotIn(List<Integer> values) {
            addCriterion("award_money_season not in", values, "awardMoneySeason");
            return (Criteria) this;
        }

        public Criteria andAwardMoneySeasonBetween(Integer value1, Integer value2) {
            addCriterion("award_money_season between", value1, value2, "awardMoneySeason");
            return (Criteria) this;
        }

        public Criteria andAwardMoneySeasonNotBetween(Integer value1, Integer value2) {
            addCriterion("award_money_season not between", value1, value2, "awardMoneySeason");
            return (Criteria) this;
        }

        public Criteria andAwardHonorSeasonIsNull() {
            addCriterion("award_honor_season is null");
            return (Criteria) this;
        }

        public Criteria andAwardHonorSeasonIsNotNull() {
            addCriterion("award_honor_season is not null");
            return (Criteria) this;
        }

        public Criteria andAwardHonorSeasonEqualTo(Integer value) {
            addCriterion("award_honor_season =", value, "awardHonorSeason");
            return (Criteria) this;
        }

        public Criteria andAwardHonorSeasonNotEqualTo(Integer value) {
            addCriterion("award_honor_season <>", value, "awardHonorSeason");
            return (Criteria) this;
        }

        public Criteria andAwardHonorSeasonGreaterThan(Integer value) {
            addCriterion("award_honor_season >", value, "awardHonorSeason");
            return (Criteria) this;
        }

        public Criteria andAwardHonorSeasonGreaterThanOrEqualTo(Integer value) {
            addCriterion("award_honor_season >=", value, "awardHonorSeason");
            return (Criteria) this;
        }

        public Criteria andAwardHonorSeasonLessThan(Integer value) {
            addCriterion("award_honor_season <", value, "awardHonorSeason");
            return (Criteria) this;
        }

        public Criteria andAwardHonorSeasonLessThanOrEqualTo(Integer value) {
            addCriterion("award_honor_season <=", value, "awardHonorSeason");
            return (Criteria) this;
        }

        public Criteria andAwardHonorSeasonIn(List<Integer> values) {
            addCriterion("award_honor_season in", values, "awardHonorSeason");
            return (Criteria) this;
        }

        public Criteria andAwardHonorSeasonNotIn(List<Integer> values) {
            addCriterion("award_honor_season not in", values, "awardHonorSeason");
            return (Criteria) this;
        }

        public Criteria andAwardHonorSeasonBetween(Integer value1, Integer value2) {
            addCriterion("award_honor_season between", value1, value2, "awardHonorSeason");
            return (Criteria) this;
        }

        public Criteria andAwardHonorSeasonNotBetween(Integer value1, Integer value2) {
            addCriterion("award_honor_season not between", value1, value2, "awardHonorSeason");
            return (Criteria) this;
        }

        public Criteria andAfterRankIdIsNull() {
            addCriterion("after_rank_id is null");
            return (Criteria) this;
        }

        public Criteria andAfterRankIdIsNotNull() {
            addCriterion("after_rank_id is not null");
            return (Criteria) this;
        }

        public Criteria andAfterRankIdEqualTo(Integer value) {
            addCriterion("after_rank_id =", value, "afterRankId");
            return (Criteria) this;
        }

        public Criteria andAfterRankIdNotEqualTo(Integer value) {
            addCriterion("after_rank_id <>", value, "afterRankId");
            return (Criteria) this;
        }

        public Criteria andAfterRankIdGreaterThan(Integer value) {
            addCriterion("after_rank_id >", value, "afterRankId");
            return (Criteria) this;
        }

        public Criteria andAfterRankIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("after_rank_id >=", value, "afterRankId");
            return (Criteria) this;
        }

        public Criteria andAfterRankIdLessThan(Integer value) {
            addCriterion("after_rank_id <", value, "afterRankId");
            return (Criteria) this;
        }

        public Criteria andAfterRankIdLessThanOrEqualTo(Integer value) {
            addCriterion("after_rank_id <=", value, "afterRankId");
            return (Criteria) this;
        }

        public Criteria andAfterRankIdIn(List<Integer> values) {
            addCriterion("after_rank_id in", values, "afterRankId");
            return (Criteria) this;
        }

        public Criteria andAfterRankIdNotIn(List<Integer> values) {
            addCriterion("after_rank_id not in", values, "afterRankId");
            return (Criteria) this;
        }

        public Criteria andAfterRankIdBetween(Integer value1, Integer value2) {
            addCriterion("after_rank_id between", value1, value2, "afterRankId");
            return (Criteria) this;
        }

        public Criteria andAfterRankIdNotBetween(Integer value1, Integer value2) {
            addCriterion("after_rank_id not between", value1, value2, "afterRankId");
            return (Criteria) this;
        }

        public Criteria andAfterGradeIsNull() {
            addCriterion("after_grade is null");
            return (Criteria) this;
        }

        public Criteria andAfterGradeIsNotNull() {
            addCriterion("after_grade is not null");
            return (Criteria) this;
        }

        public Criteria andAfterGradeEqualTo(Integer value) {
            addCriterion("after_grade =", value, "afterGrade");
            return (Criteria) this;
        }

        public Criteria andAfterGradeNotEqualTo(Integer value) {
            addCriterion("after_grade <>", value, "afterGrade");
            return (Criteria) this;
        }

        public Criteria andAfterGradeGreaterThan(Integer value) {
            addCriterion("after_grade >", value, "afterGrade");
            return (Criteria) this;
        }

        public Criteria andAfterGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("after_grade >=", value, "afterGrade");
            return (Criteria) this;
        }

        public Criteria andAfterGradeLessThan(Integer value) {
            addCriterion("after_grade <", value, "afterGrade");
            return (Criteria) this;
        }

        public Criteria andAfterGradeLessThanOrEqualTo(Integer value) {
            addCriterion("after_grade <=", value, "afterGrade");
            return (Criteria) this;
        }

        public Criteria andAfterGradeIn(List<Integer> values) {
            addCriterion("after_grade in", values, "afterGrade");
            return (Criteria) this;
        }

        public Criteria andAfterGradeNotIn(List<Integer> values) {
            addCriterion("after_grade not in", values, "afterGrade");
            return (Criteria) this;
        }

        public Criteria andAfterGradeBetween(Integer value1, Integer value2) {
            addCriterion("after_grade between", value1, value2, "afterGrade");
            return (Criteria) this;
        }

        public Criteria andAfterGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("after_grade not between", value1, value2, "afterGrade");
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