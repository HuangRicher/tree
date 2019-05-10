package com.tongzhu.user.model;

import java.util.ArrayList;
import java.util.List;

public class FightRankingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FightRankingExample() {
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

        public Criteria andRankingIsNull() {
            addCriterion("ranking is null");
            return (Criteria) this;
        }

        public Criteria andRankingIsNotNull() {
            addCriterion("ranking is not null");
            return (Criteria) this;
        }

        public Criteria andRankingEqualTo(Integer value) {
            addCriterion("ranking =", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingNotEqualTo(Integer value) {
            addCriterion("ranking <>", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingGreaterThan(Integer value) {
            addCriterion("ranking >", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingGreaterThanOrEqualTo(Integer value) {
            addCriterion("ranking >=", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingLessThan(Integer value) {
            addCriterion("ranking <", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingLessThanOrEqualTo(Integer value) {
            addCriterion("ranking <=", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingIn(List<Integer> values) {
            addCriterion("ranking in", values, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingNotIn(List<Integer> values) {
            addCriterion("ranking not in", values, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingBetween(Integer value1, Integer value2) {
            addCriterion("ranking between", value1, value2, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingNotBetween(Integer value1, Integer value2) {
            addCriterion("ranking not between", value1, value2, "ranking");
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

        public Criteria andFightYearIsNull() {
            addCriterion("fight_year is null");
            return (Criteria) this;
        }

        public Criteria andFightYearIsNotNull() {
            addCriterion("fight_year is not null");
            return (Criteria) this;
        }

        public Criteria andFightYearEqualTo(Integer value) {
            addCriterion("fight_year =", value, "fightYear");
            return (Criteria) this;
        }

        public Criteria andFightYearNotEqualTo(Integer value) {
            addCriterion("fight_year <>", value, "fightYear");
            return (Criteria) this;
        }

        public Criteria andFightYearGreaterThan(Integer value) {
            addCriterion("fight_year >", value, "fightYear");
            return (Criteria) this;
        }

        public Criteria andFightYearGreaterThanOrEqualTo(Integer value) {
            addCriterion("fight_year >=", value, "fightYear");
            return (Criteria) this;
        }

        public Criteria andFightYearLessThan(Integer value) {
            addCriterion("fight_year <", value, "fightYear");
            return (Criteria) this;
        }

        public Criteria andFightYearLessThanOrEqualTo(Integer value) {
            addCriterion("fight_year <=", value, "fightYear");
            return (Criteria) this;
        }

        public Criteria andFightYearIn(List<Integer> values) {
            addCriterion("fight_year in", values, "fightYear");
            return (Criteria) this;
        }

        public Criteria andFightYearNotIn(List<Integer> values) {
            addCriterion("fight_year not in", values, "fightYear");
            return (Criteria) this;
        }

        public Criteria andFightYearBetween(Integer value1, Integer value2) {
            addCriterion("fight_year between", value1, value2, "fightYear");
            return (Criteria) this;
        }

        public Criteria andFightYearNotBetween(Integer value1, Integer value2) {
            addCriterion("fight_year not between", value1, value2, "fightYear");
            return (Criteria) this;
        }

        public Criteria andFightSeasonIsNull() {
            addCriterion("fight_season is null");
            return (Criteria) this;
        }

        public Criteria andFightSeasonIsNotNull() {
            addCriterion("fight_season is not null");
            return (Criteria) this;
        }

        public Criteria andFightSeasonEqualTo(Integer value) {
            addCriterion("fight_season =", value, "fightSeason");
            return (Criteria) this;
        }

        public Criteria andFightSeasonNotEqualTo(Integer value) {
            addCriterion("fight_season <>", value, "fightSeason");
            return (Criteria) this;
        }

        public Criteria andFightSeasonGreaterThan(Integer value) {
            addCriterion("fight_season >", value, "fightSeason");
            return (Criteria) this;
        }

        public Criteria andFightSeasonGreaterThanOrEqualTo(Integer value) {
            addCriterion("fight_season >=", value, "fightSeason");
            return (Criteria) this;
        }

        public Criteria andFightSeasonLessThan(Integer value) {
            addCriterion("fight_season <", value, "fightSeason");
            return (Criteria) this;
        }

        public Criteria andFightSeasonLessThanOrEqualTo(Integer value) {
            addCriterion("fight_season <=", value, "fightSeason");
            return (Criteria) this;
        }

        public Criteria andFightSeasonIn(List<Integer> values) {
            addCriterion("fight_season in", values, "fightSeason");
            return (Criteria) this;
        }

        public Criteria andFightSeasonNotIn(List<Integer> values) {
            addCriterion("fight_season not in", values, "fightSeason");
            return (Criteria) this;
        }

        public Criteria andFightSeasonBetween(Integer value1, Integer value2) {
            addCriterion("fight_season between", value1, value2, "fightSeason");
            return (Criteria) this;
        }

        public Criteria andFightSeasonNotBetween(Integer value1, Integer value2) {
            addCriterion("fight_season not between", value1, value2, "fightSeason");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Integer value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Integer value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Integer value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Integer value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Integer value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Integer> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Integer> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Integer value1, Integer value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andHonorIsNull() {
            addCriterion("honor is null");
            return (Criteria) this;
        }

        public Criteria andHonorIsNotNull() {
            addCriterion("honor is not null");
            return (Criteria) this;
        }

        public Criteria andHonorEqualTo(Integer value) {
            addCriterion("honor =", value, "honor");
            return (Criteria) this;
        }

        public Criteria andHonorNotEqualTo(Integer value) {
            addCriterion("honor <>", value, "honor");
            return (Criteria) this;
        }

        public Criteria andHonorGreaterThan(Integer value) {
            addCriterion("honor >", value, "honor");
            return (Criteria) this;
        }

        public Criteria andHonorGreaterThanOrEqualTo(Integer value) {
            addCriterion("honor >=", value, "honor");
            return (Criteria) this;
        }

        public Criteria andHonorLessThan(Integer value) {
            addCriterion("honor <", value, "honor");
            return (Criteria) this;
        }

        public Criteria andHonorLessThanOrEqualTo(Integer value) {
            addCriterion("honor <=", value, "honor");
            return (Criteria) this;
        }

        public Criteria andHonorIn(List<Integer> values) {
            addCriterion("honor in", values, "honor");
            return (Criteria) this;
        }

        public Criteria andHonorNotIn(List<Integer> values) {
            addCriterion("honor not in", values, "honor");
            return (Criteria) this;
        }

        public Criteria andHonorBetween(Integer value1, Integer value2) {
            addCriterion("honor between", value1, value2, "honor");
            return (Criteria) this;
        }

        public Criteria andHonorNotBetween(Integer value1, Integer value2) {
            addCriterion("honor not between", value1, value2, "honor");
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