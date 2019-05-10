package com.tongzhu.usergoods.model;

import java.util.ArrayList;
import java.util.List;

public class UserAdornEquipExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserAdornEquipExample() {
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

        public Criteria andHeadIsNull() {
            addCriterion("head is null");
            return (Criteria) this;
        }

        public Criteria andHeadIsNotNull() {
            addCriterion("head is not null");
            return (Criteria) this;
        }

        public Criteria andHeadEqualTo(String value) {
            addCriterion("head =", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadNotEqualTo(String value) {
            addCriterion("head <>", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadGreaterThan(String value) {
            addCriterion("head >", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadGreaterThanOrEqualTo(String value) {
            addCriterion("head >=", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadLessThan(String value) {
            addCriterion("head <", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadLessThanOrEqualTo(String value) {
            addCriterion("head <=", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadLike(String value) {
            addCriterion("head like", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadNotLike(String value) {
            addCriterion("head not like", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadIn(List<String> values) {
            addCriterion("head in", values, "head");
            return (Criteria) this;
        }

        public Criteria andHeadNotIn(List<String> values) {
            addCriterion("head not in", values, "head");
            return (Criteria) this;
        }

        public Criteria andHeadBetween(String value1, String value2) {
            addCriterion("head between", value1, value2, "head");
            return (Criteria) this;
        }

        public Criteria andHeadNotBetween(String value1, String value2) {
            addCriterion("head not between", value1, value2, "head");
            return (Criteria) this;
        }

        public Criteria andClothingIsNull() {
            addCriterion("clothing is null");
            return (Criteria) this;
        }

        public Criteria andClothingIsNotNull() {
            addCriterion("clothing is not null");
            return (Criteria) this;
        }

        public Criteria andClothingEqualTo(String value) {
            addCriterion("clothing =", value, "clothing");
            return (Criteria) this;
        }

        public Criteria andClothingNotEqualTo(String value) {
            addCriterion("clothing <>", value, "clothing");
            return (Criteria) this;
        }

        public Criteria andClothingGreaterThan(String value) {
            addCriterion("clothing >", value, "clothing");
            return (Criteria) this;
        }

        public Criteria andClothingGreaterThanOrEqualTo(String value) {
            addCriterion("clothing >=", value, "clothing");
            return (Criteria) this;
        }

        public Criteria andClothingLessThan(String value) {
            addCriterion("clothing <", value, "clothing");
            return (Criteria) this;
        }

        public Criteria andClothingLessThanOrEqualTo(String value) {
            addCriterion("clothing <=", value, "clothing");
            return (Criteria) this;
        }

        public Criteria andClothingLike(String value) {
            addCriterion("clothing like", value, "clothing");
            return (Criteria) this;
        }

        public Criteria andClothingNotLike(String value) {
            addCriterion("clothing not like", value, "clothing");
            return (Criteria) this;
        }

        public Criteria andClothingIn(List<String> values) {
            addCriterion("clothing in", values, "clothing");
            return (Criteria) this;
        }

        public Criteria andClothingNotIn(List<String> values) {
            addCriterion("clothing not in", values, "clothing");
            return (Criteria) this;
        }

        public Criteria andClothingBetween(String value1, String value2) {
            addCriterion("clothing between", value1, value2, "clothing");
            return (Criteria) this;
        }

        public Criteria andClothingNotBetween(String value1, String value2) {
            addCriterion("clothing not between", value1, value2, "clothing");
            return (Criteria) this;
        }

        public Criteria andTrousersIsNull() {
            addCriterion("trousers is null");
            return (Criteria) this;
        }

        public Criteria andTrousersIsNotNull() {
            addCriterion("trousers is not null");
            return (Criteria) this;
        }

        public Criteria andTrousersEqualTo(String value) {
            addCriterion("trousers =", value, "trousers");
            return (Criteria) this;
        }

        public Criteria andTrousersNotEqualTo(String value) {
            addCriterion("trousers <>", value, "trousers");
            return (Criteria) this;
        }

        public Criteria andTrousersGreaterThan(String value) {
            addCriterion("trousers >", value, "trousers");
            return (Criteria) this;
        }

        public Criteria andTrousersGreaterThanOrEqualTo(String value) {
            addCriterion("trousers >=", value, "trousers");
            return (Criteria) this;
        }

        public Criteria andTrousersLessThan(String value) {
            addCriterion("trousers <", value, "trousers");
            return (Criteria) this;
        }

        public Criteria andTrousersLessThanOrEqualTo(String value) {
            addCriterion("trousers <=", value, "trousers");
            return (Criteria) this;
        }

        public Criteria andTrousersLike(String value) {
            addCriterion("trousers like", value, "trousers");
            return (Criteria) this;
        }

        public Criteria andTrousersNotLike(String value) {
            addCriterion("trousers not like", value, "trousers");
            return (Criteria) this;
        }

        public Criteria andTrousersIn(List<String> values) {
            addCriterion("trousers in", values, "trousers");
            return (Criteria) this;
        }

        public Criteria andTrousersNotIn(List<String> values) {
            addCriterion("trousers not in", values, "trousers");
            return (Criteria) this;
        }

        public Criteria andTrousersBetween(String value1, String value2) {
            addCriterion("trousers between", value1, value2, "trousers");
            return (Criteria) this;
        }

        public Criteria andTrousersNotBetween(String value1, String value2) {
            addCriterion("trousers not between", value1, value2, "trousers");
            return (Criteria) this;
        }

        public Criteria andShoeIsNull() {
            addCriterion("shoe is null");
            return (Criteria) this;
        }

        public Criteria andShoeIsNotNull() {
            addCriterion("shoe is not null");
            return (Criteria) this;
        }

        public Criteria andShoeEqualTo(String value) {
            addCriterion("shoe =", value, "shoe");
            return (Criteria) this;
        }

        public Criteria andShoeNotEqualTo(String value) {
            addCriterion("shoe <>", value, "shoe");
            return (Criteria) this;
        }

        public Criteria andShoeGreaterThan(String value) {
            addCriterion("shoe >", value, "shoe");
            return (Criteria) this;
        }

        public Criteria andShoeGreaterThanOrEqualTo(String value) {
            addCriterion("shoe >=", value, "shoe");
            return (Criteria) this;
        }

        public Criteria andShoeLessThan(String value) {
            addCriterion("shoe <", value, "shoe");
            return (Criteria) this;
        }

        public Criteria andShoeLessThanOrEqualTo(String value) {
            addCriterion("shoe <=", value, "shoe");
            return (Criteria) this;
        }

        public Criteria andShoeLike(String value) {
            addCriterion("shoe like", value, "shoe");
            return (Criteria) this;
        }

        public Criteria andShoeNotLike(String value) {
            addCriterion("shoe not like", value, "shoe");
            return (Criteria) this;
        }

        public Criteria andShoeIn(List<String> values) {
            addCriterion("shoe in", values, "shoe");
            return (Criteria) this;
        }

        public Criteria andShoeNotIn(List<String> values) {
            addCriterion("shoe not in", values, "shoe");
            return (Criteria) this;
        }

        public Criteria andShoeBetween(String value1, String value2) {
            addCriterion("shoe between", value1, value2, "shoe");
            return (Criteria) this;
        }

        public Criteria andShoeNotBetween(String value1, String value2) {
            addCriterion("shoe not between", value1, value2, "shoe");
            return (Criteria) this;
        }

        public Criteria andFashionIsNull() {
            addCriterion("fashion is null");
            return (Criteria) this;
        }

        public Criteria andFashionIsNotNull() {
            addCriterion("fashion is not null");
            return (Criteria) this;
        }

        public Criteria andFashionEqualTo(String value) {
            addCriterion("fashion =", value, "fashion");
            return (Criteria) this;
        }

        public Criteria andFashionNotEqualTo(String value) {
            addCriterion("fashion <>", value, "fashion");
            return (Criteria) this;
        }

        public Criteria andFashionGreaterThan(String value) {
            addCriterion("fashion >", value, "fashion");
            return (Criteria) this;
        }

        public Criteria andFashionGreaterThanOrEqualTo(String value) {
            addCriterion("fashion >=", value, "fashion");
            return (Criteria) this;
        }

        public Criteria andFashionLessThan(String value) {
            addCriterion("fashion <", value, "fashion");
            return (Criteria) this;
        }

        public Criteria andFashionLessThanOrEqualTo(String value) {
            addCriterion("fashion <=", value, "fashion");
            return (Criteria) this;
        }

        public Criteria andFashionLike(String value) {
            addCriterion("fashion like", value, "fashion");
            return (Criteria) this;
        }

        public Criteria andFashionNotLike(String value) {
            addCriterion("fashion not like", value, "fashion");
            return (Criteria) this;
        }

        public Criteria andFashionIn(List<String> values) {
            addCriterion("fashion in", values, "fashion");
            return (Criteria) this;
        }

        public Criteria andFashionNotIn(List<String> values) {
            addCriterion("fashion not in", values, "fashion");
            return (Criteria) this;
        }

        public Criteria andFashionBetween(String value1, String value2) {
            addCriterion("fashion between", value1, value2, "fashion");
            return (Criteria) this;
        }

        public Criteria andFashionNotBetween(String value1, String value2) {
            addCriterion("fashion not between", value1, value2, "fashion");
            return (Criteria) this;
        }

        public Criteria andWeddingRingIsNull() {
            addCriterion("wedding_ring is null");
            return (Criteria) this;
        }

        public Criteria andWeddingRingIsNotNull() {
            addCriterion("wedding_ring is not null");
            return (Criteria) this;
        }

        public Criteria andWeddingRingEqualTo(String value) {
            addCriterion("wedding_ring =", value, "weddingRing");
            return (Criteria) this;
        }

        public Criteria andWeddingRingNotEqualTo(String value) {
            addCriterion("wedding_ring <>", value, "weddingRing");
            return (Criteria) this;
        }

        public Criteria andWeddingRingGreaterThan(String value) {
            addCriterion("wedding_ring >", value, "weddingRing");
            return (Criteria) this;
        }

        public Criteria andWeddingRingGreaterThanOrEqualTo(String value) {
            addCriterion("wedding_ring >=", value, "weddingRing");
            return (Criteria) this;
        }

        public Criteria andWeddingRingLessThan(String value) {
            addCriterion("wedding_ring <", value, "weddingRing");
            return (Criteria) this;
        }

        public Criteria andWeddingRingLessThanOrEqualTo(String value) {
            addCriterion("wedding_ring <=", value, "weddingRing");
            return (Criteria) this;
        }

        public Criteria andWeddingRingLike(String value) {
            addCriterion("wedding_ring like", value, "weddingRing");
            return (Criteria) this;
        }

        public Criteria andWeddingRingNotLike(String value) {
            addCriterion("wedding_ring not like", value, "weddingRing");
            return (Criteria) this;
        }

        public Criteria andWeddingRingIn(List<String> values) {
            addCriterion("wedding_ring in", values, "weddingRing");
            return (Criteria) this;
        }

        public Criteria andWeddingRingNotIn(List<String> values) {
            addCriterion("wedding_ring not in", values, "weddingRing");
            return (Criteria) this;
        }

        public Criteria andWeddingRingBetween(String value1, String value2) {
            addCriterion("wedding_ring between", value1, value2, "weddingRing");
            return (Criteria) this;
        }

        public Criteria andWeddingRingNotBetween(String value1, String value2) {
            addCriterion("wedding_ring not between", value1, value2, "weddingRing");
            return (Criteria) this;
        }

        public Criteria andWeaponIsNull() {
            addCriterion("weapon is null");
            return (Criteria) this;
        }

        public Criteria andWeaponIsNotNull() {
            addCriterion("weapon is not null");
            return (Criteria) this;
        }

        public Criteria andWeaponEqualTo(String value) {
            addCriterion("weapon =", value, "weapon");
            return (Criteria) this;
        }

        public Criteria andWeaponNotEqualTo(String value) {
            addCriterion("weapon <>", value, "weapon");
            return (Criteria) this;
        }

        public Criteria andWeaponGreaterThan(String value) {
            addCriterion("weapon >", value, "weapon");
            return (Criteria) this;
        }

        public Criteria andWeaponGreaterThanOrEqualTo(String value) {
            addCriterion("weapon >=", value, "weapon");
            return (Criteria) this;
        }

        public Criteria andWeaponLessThan(String value) {
            addCriterion("weapon <", value, "weapon");
            return (Criteria) this;
        }

        public Criteria andWeaponLessThanOrEqualTo(String value) {
            addCriterion("weapon <=", value, "weapon");
            return (Criteria) this;
        }

        public Criteria andWeaponLike(String value) {
            addCriterion("weapon like", value, "weapon");
            return (Criteria) this;
        }

        public Criteria andWeaponNotLike(String value) {
            addCriterion("weapon not like", value, "weapon");
            return (Criteria) this;
        }

        public Criteria andWeaponIn(List<String> values) {
            addCriterion("weapon in", values, "weapon");
            return (Criteria) this;
        }

        public Criteria andWeaponNotIn(List<String> values) {
            addCriterion("weapon not in", values, "weapon");
            return (Criteria) this;
        }

        public Criteria andWeaponBetween(String value1, String value2) {
            addCriterion("weapon between", value1, value2, "weapon");
            return (Criteria) this;
        }

        public Criteria andWeaponNotBetween(String value1, String value2) {
            addCriterion("weapon not between", value1, value2, "weapon");
            return (Criteria) this;
        }

        public Criteria andRingIsNull() {
            addCriterion("ring is null");
            return (Criteria) this;
        }

        public Criteria andRingIsNotNull() {
            addCriterion("ring is not null");
            return (Criteria) this;
        }

        public Criteria andRingEqualTo(String value) {
            addCriterion("ring =", value, "ring");
            return (Criteria) this;
        }

        public Criteria andRingNotEqualTo(String value) {
            addCriterion("ring <>", value, "ring");
            return (Criteria) this;
        }

        public Criteria andRingGreaterThan(String value) {
            addCriterion("ring >", value, "ring");
            return (Criteria) this;
        }

        public Criteria andRingGreaterThanOrEqualTo(String value) {
            addCriterion("ring >=", value, "ring");
            return (Criteria) this;
        }

        public Criteria andRingLessThan(String value) {
            addCriterion("ring <", value, "ring");
            return (Criteria) this;
        }

        public Criteria andRingLessThanOrEqualTo(String value) {
            addCriterion("ring <=", value, "ring");
            return (Criteria) this;
        }

        public Criteria andRingLike(String value) {
            addCriterion("ring like", value, "ring");
            return (Criteria) this;
        }

        public Criteria andRingNotLike(String value) {
            addCriterion("ring not like", value, "ring");
            return (Criteria) this;
        }

        public Criteria andRingIn(List<String> values) {
            addCriterion("ring in", values, "ring");
            return (Criteria) this;
        }

        public Criteria andRingNotIn(List<String> values) {
            addCriterion("ring not in", values, "ring");
            return (Criteria) this;
        }

        public Criteria andRingBetween(String value1, String value2) {
            addCriterion("ring between", value1, value2, "ring");
            return (Criteria) this;
        }

        public Criteria andRingNotBetween(String value1, String value2) {
            addCriterion("ring not between", value1, value2, "ring");
            return (Criteria) this;
        }

        public Criteria andCuffIsNull() {
            addCriterion("cuff is null");
            return (Criteria) this;
        }

        public Criteria andCuffIsNotNull() {
            addCriterion("cuff is not null");
            return (Criteria) this;
        }

        public Criteria andCuffEqualTo(String value) {
            addCriterion("cuff =", value, "cuff");
            return (Criteria) this;
        }

        public Criteria andCuffNotEqualTo(String value) {
            addCriterion("cuff <>", value, "cuff");
            return (Criteria) this;
        }

        public Criteria andCuffGreaterThan(String value) {
            addCriterion("cuff >", value, "cuff");
            return (Criteria) this;
        }

        public Criteria andCuffGreaterThanOrEqualTo(String value) {
            addCriterion("cuff >=", value, "cuff");
            return (Criteria) this;
        }

        public Criteria andCuffLessThan(String value) {
            addCriterion("cuff <", value, "cuff");
            return (Criteria) this;
        }

        public Criteria andCuffLessThanOrEqualTo(String value) {
            addCriterion("cuff <=", value, "cuff");
            return (Criteria) this;
        }

        public Criteria andCuffLike(String value) {
            addCriterion("cuff like", value, "cuff");
            return (Criteria) this;
        }

        public Criteria andCuffNotLike(String value) {
            addCriterion("cuff not like", value, "cuff");
            return (Criteria) this;
        }

        public Criteria andCuffIn(List<String> values) {
            addCriterion("cuff in", values, "cuff");
            return (Criteria) this;
        }

        public Criteria andCuffNotIn(List<String> values) {
            addCriterion("cuff not in", values, "cuff");
            return (Criteria) this;
        }

        public Criteria andCuffBetween(String value1, String value2) {
            addCriterion("cuff between", value1, value2, "cuff");
            return (Criteria) this;
        }

        public Criteria andCuffNotBetween(String value1, String value2) {
            addCriterion("cuff not between", value1, value2, "cuff");
            return (Criteria) this;
        }

        public Criteria andNecklaceIsNull() {
            addCriterion("necklace is null");
            return (Criteria) this;
        }

        public Criteria andNecklaceIsNotNull() {
            addCriterion("necklace is not null");
            return (Criteria) this;
        }

        public Criteria andNecklaceEqualTo(String value) {
            addCriterion("necklace =", value, "necklace");
            return (Criteria) this;
        }

        public Criteria andNecklaceNotEqualTo(String value) {
            addCriterion("necklace <>", value, "necklace");
            return (Criteria) this;
        }

        public Criteria andNecklaceGreaterThan(String value) {
            addCriterion("necklace >", value, "necklace");
            return (Criteria) this;
        }

        public Criteria andNecklaceGreaterThanOrEqualTo(String value) {
            addCriterion("necklace >=", value, "necklace");
            return (Criteria) this;
        }

        public Criteria andNecklaceLessThan(String value) {
            addCriterion("necklace <", value, "necklace");
            return (Criteria) this;
        }

        public Criteria andNecklaceLessThanOrEqualTo(String value) {
            addCriterion("necklace <=", value, "necklace");
            return (Criteria) this;
        }

        public Criteria andNecklaceLike(String value) {
            addCriterion("necklace like", value, "necklace");
            return (Criteria) this;
        }

        public Criteria andNecklaceNotLike(String value) {
            addCriterion("necklace not like", value, "necklace");
            return (Criteria) this;
        }

        public Criteria andNecklaceIn(List<String> values) {
            addCriterion("necklace in", values, "necklace");
            return (Criteria) this;
        }

        public Criteria andNecklaceNotIn(List<String> values) {
            addCriterion("necklace not in", values, "necklace");
            return (Criteria) this;
        }

        public Criteria andNecklaceBetween(String value1, String value2) {
            addCriterion("necklace between", value1, value2, "necklace");
            return (Criteria) this;
        }

        public Criteria andNecklaceNotBetween(String value1, String value2) {
            addCriterion("necklace not between", value1, value2, "necklace");
            return (Criteria) this;
        }

        public Criteria andJewelryIsNull() {
            addCriterion("jewelry is null");
            return (Criteria) this;
        }

        public Criteria andJewelryIsNotNull() {
            addCriterion("jewelry is not null");
            return (Criteria) this;
        }

        public Criteria andJewelryEqualTo(String value) {
            addCriterion("jewelry =", value, "jewelry");
            return (Criteria) this;
        }

        public Criteria andJewelryNotEqualTo(String value) {
            addCriterion("jewelry <>", value, "jewelry");
            return (Criteria) this;
        }

        public Criteria andJewelryGreaterThan(String value) {
            addCriterion("jewelry >", value, "jewelry");
            return (Criteria) this;
        }

        public Criteria andJewelryGreaterThanOrEqualTo(String value) {
            addCriterion("jewelry >=", value, "jewelry");
            return (Criteria) this;
        }

        public Criteria andJewelryLessThan(String value) {
            addCriterion("jewelry <", value, "jewelry");
            return (Criteria) this;
        }

        public Criteria andJewelryLessThanOrEqualTo(String value) {
            addCriterion("jewelry <=", value, "jewelry");
            return (Criteria) this;
        }

        public Criteria andJewelryLike(String value) {
            addCriterion("jewelry like", value, "jewelry");
            return (Criteria) this;
        }

        public Criteria andJewelryNotLike(String value) {
            addCriterion("jewelry not like", value, "jewelry");
            return (Criteria) this;
        }

        public Criteria andJewelryIn(List<String> values) {
            addCriterion("jewelry in", values, "jewelry");
            return (Criteria) this;
        }

        public Criteria andJewelryNotIn(List<String> values) {
            addCriterion("jewelry not in", values, "jewelry");
            return (Criteria) this;
        }

        public Criteria andJewelryBetween(String value1, String value2) {
            addCriterion("jewelry between", value1, value2, "jewelry");
            return (Criteria) this;
        }

        public Criteria andJewelryNotBetween(String value1, String value2) {
            addCriterion("jewelry not between", value1, value2, "jewelry");
            return (Criteria) this;
        }

        public Criteria andAlwaysFightingIsNull() {
            addCriterion("always_fighting is null");
            return (Criteria) this;
        }

        public Criteria andAlwaysFightingIsNotNull() {
            addCriterion("always_fighting is not null");
            return (Criteria) this;
        }

        public Criteria andAlwaysFightingEqualTo(Double value) {
            addCriterion("always_fighting =", value, "alwaysFighting");
            return (Criteria) this;
        }

        public Criteria andAlwaysFightingNotEqualTo(Double value) {
            addCriterion("always_fighting <>", value, "alwaysFighting");
            return (Criteria) this;
        }

        public Criteria andAlwaysFightingGreaterThan(Double value) {
            addCriterion("always_fighting >", value, "alwaysFighting");
            return (Criteria) this;
        }

        public Criteria andAlwaysFightingGreaterThanOrEqualTo(Double value) {
            addCriterion("always_fighting >=", value, "alwaysFighting");
            return (Criteria) this;
        }

        public Criteria andAlwaysFightingLessThan(Double value) {
            addCriterion("always_fighting <", value, "alwaysFighting");
            return (Criteria) this;
        }

        public Criteria andAlwaysFightingLessThanOrEqualTo(Double value) {
            addCriterion("always_fighting <=", value, "alwaysFighting");
            return (Criteria) this;
        }

        public Criteria andAlwaysFightingIn(List<Double> values) {
            addCriterion("always_fighting in", values, "alwaysFighting");
            return (Criteria) this;
        }

        public Criteria andAlwaysFightingNotIn(List<Double> values) {
            addCriterion("always_fighting not in", values, "alwaysFighting");
            return (Criteria) this;
        }

        public Criteria andAlwaysFightingBetween(Double value1, Double value2) {
            addCriterion("always_fighting between", value1, value2, "alwaysFighting");
            return (Criteria) this;
        }

        public Criteria andAlwaysFightingNotBetween(Double value1, Double value2) {
            addCriterion("always_fighting not between", value1, value2, "alwaysFighting");
            return (Criteria) this;
        }

        public Criteria andVitalityIsNull() {
            addCriterion("vitality is null");
            return (Criteria) this;
        }

        public Criteria andVitalityIsNotNull() {
            addCriterion("vitality is not null");
            return (Criteria) this;
        }

        public Criteria andVitalityEqualTo(Double value) {
            addCriterion("vitality =", value, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityNotEqualTo(Double value) {
            addCriterion("vitality <>", value, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityGreaterThan(Double value) {
            addCriterion("vitality >", value, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityGreaterThanOrEqualTo(Double value) {
            addCriterion("vitality >=", value, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityLessThan(Double value) {
            addCriterion("vitality <", value, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityLessThanOrEqualTo(Double value) {
            addCriterion("vitality <=", value, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityIn(List<Double> values) {
            addCriterion("vitality in", values, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityNotIn(List<Double> values) {
            addCriterion("vitality not in", values, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityBetween(Double value1, Double value2) {
            addCriterion("vitality between", value1, value2, "vitality");
            return (Criteria) this;
        }

        public Criteria andVitalityNotBetween(Double value1, Double value2) {
            addCriterion("vitality not between", value1, value2, "vitality");
            return (Criteria) this;
        }

        public Criteria andAttackIsNull() {
            addCriterion("attack is null");
            return (Criteria) this;
        }

        public Criteria andAttackIsNotNull() {
            addCriterion("attack is not null");
            return (Criteria) this;
        }

        public Criteria andAttackEqualTo(Double value) {
            addCriterion("attack =", value, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackNotEqualTo(Double value) {
            addCriterion("attack <>", value, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackGreaterThan(Double value) {
            addCriterion("attack >", value, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackGreaterThanOrEqualTo(Double value) {
            addCriterion("attack >=", value, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackLessThan(Double value) {
            addCriterion("attack <", value, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackLessThanOrEqualTo(Double value) {
            addCriterion("attack <=", value, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackIn(List<Double> values) {
            addCriterion("attack in", values, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackNotIn(List<Double> values) {
            addCriterion("attack not in", values, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackBetween(Double value1, Double value2) {
            addCriterion("attack between", value1, value2, "attack");
            return (Criteria) this;
        }

        public Criteria andAttackNotBetween(Double value1, Double value2) {
            addCriterion("attack not between", value1, value2, "attack");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksIsNull() {
            addCriterion("spell_attacks is null");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksIsNotNull() {
            addCriterion("spell_attacks is not null");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksEqualTo(Double value) {
            addCriterion("spell_attacks =", value, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksNotEqualTo(Double value) {
            addCriterion("spell_attacks <>", value, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksGreaterThan(Double value) {
            addCriterion("spell_attacks >", value, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksGreaterThanOrEqualTo(Double value) {
            addCriterion("spell_attacks >=", value, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksLessThan(Double value) {
            addCriterion("spell_attacks <", value, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksLessThanOrEqualTo(Double value) {
            addCriterion("spell_attacks <=", value, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksIn(List<Double> values) {
            addCriterion("spell_attacks in", values, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksNotIn(List<Double> values) {
            addCriterion("spell_attacks not in", values, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksBetween(Double value1, Double value2) {
            addCriterion("spell_attacks between", value1, value2, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andSpellAttacksNotBetween(Double value1, Double value2) {
            addCriterion("spell_attacks not between", value1, value2, "spellAttacks");
            return (Criteria) this;
        }

        public Criteria andPdefIsNull() {
            addCriterion("pdef is null");
            return (Criteria) this;
        }

        public Criteria andPdefIsNotNull() {
            addCriterion("pdef is not null");
            return (Criteria) this;
        }

        public Criteria andPdefEqualTo(Double value) {
            addCriterion("pdef =", value, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefNotEqualTo(Double value) {
            addCriterion("pdef <>", value, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefGreaterThan(Double value) {
            addCriterion("pdef >", value, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefGreaterThanOrEqualTo(Double value) {
            addCriterion("pdef >=", value, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefLessThan(Double value) {
            addCriterion("pdef <", value, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefLessThanOrEqualTo(Double value) {
            addCriterion("pdef <=", value, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefIn(List<Double> values) {
            addCriterion("pdef in", values, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefNotIn(List<Double> values) {
            addCriterion("pdef not in", values, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefBetween(Double value1, Double value2) {
            addCriterion("pdef between", value1, value2, "pdef");
            return (Criteria) this;
        }

        public Criteria andPdefNotBetween(Double value1, Double value2) {
            addCriterion("pdef not between", value1, value2, "pdef");
            return (Criteria) this;
        }

        public Criteria andMagdefIsNull() {
            addCriterion("magdef is null");
            return (Criteria) this;
        }

        public Criteria andMagdefIsNotNull() {
            addCriterion("magdef is not null");
            return (Criteria) this;
        }

        public Criteria andMagdefEqualTo(Double value) {
            addCriterion("magdef =", value, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefNotEqualTo(Double value) {
            addCriterion("magdef <>", value, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefGreaterThan(Double value) {
            addCriterion("magdef >", value, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefGreaterThanOrEqualTo(Double value) {
            addCriterion("magdef >=", value, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefLessThan(Double value) {
            addCriterion("magdef <", value, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefLessThanOrEqualTo(Double value) {
            addCriterion("magdef <=", value, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefIn(List<Double> values) {
            addCriterion("magdef in", values, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefNotIn(List<Double> values) {
            addCriterion("magdef not in", values, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefBetween(Double value1, Double value2) {
            addCriterion("magdef between", value1, value2, "magdef");
            return (Criteria) this;
        }

        public Criteria andMagdefNotBetween(Double value1, Double value2) {
            addCriterion("magdef not between", value1, value2, "magdef");
            return (Criteria) this;
        }

        public Criteria andCritIsNull() {
            addCriterion("crit is null");
            return (Criteria) this;
        }

        public Criteria andCritIsNotNull() {
            addCriterion("crit is not null");
            return (Criteria) this;
        }

        public Criteria andCritEqualTo(Double value) {
            addCriterion("crit =", value, "crit");
            return (Criteria) this;
        }

        public Criteria andCritNotEqualTo(Double value) {
            addCriterion("crit <>", value, "crit");
            return (Criteria) this;
        }

        public Criteria andCritGreaterThan(Double value) {
            addCriterion("crit >", value, "crit");
            return (Criteria) this;
        }

        public Criteria andCritGreaterThanOrEqualTo(Double value) {
            addCriterion("crit >=", value, "crit");
            return (Criteria) this;
        }

        public Criteria andCritLessThan(Double value) {
            addCriterion("crit <", value, "crit");
            return (Criteria) this;
        }

        public Criteria andCritLessThanOrEqualTo(Double value) {
            addCriterion("crit <=", value, "crit");
            return (Criteria) this;
        }

        public Criteria andCritIn(List<Double> values) {
            addCriterion("crit in", values, "crit");
            return (Criteria) this;
        }

        public Criteria andCritNotIn(List<Double> values) {
            addCriterion("crit not in", values, "crit");
            return (Criteria) this;
        }

        public Criteria andCritBetween(Double value1, Double value2) {
            addCriterion("crit between", value1, value2, "crit");
            return (Criteria) this;
        }

        public Criteria andCritNotBetween(Double value1, Double value2) {
            addCriterion("crit not between", value1, value2, "crit");
            return (Criteria) this;
        }

        public Criteria andDodgeIsNull() {
            addCriterion("dodge is null");
            return (Criteria) this;
        }

        public Criteria andDodgeIsNotNull() {
            addCriterion("dodge is not null");
            return (Criteria) this;
        }

        public Criteria andDodgeEqualTo(Double value) {
            addCriterion("dodge =", value, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeNotEqualTo(Double value) {
            addCriterion("dodge <>", value, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeGreaterThan(Double value) {
            addCriterion("dodge >", value, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeGreaterThanOrEqualTo(Double value) {
            addCriterion("dodge >=", value, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeLessThan(Double value) {
            addCriterion("dodge <", value, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeLessThanOrEqualTo(Double value) {
            addCriterion("dodge <=", value, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeIn(List<Double> values) {
            addCriterion("dodge in", values, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeNotIn(List<Double> values) {
            addCriterion("dodge not in", values, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeBetween(Double value1, Double value2) {
            addCriterion("dodge between", value1, value2, "dodge");
            return (Criteria) this;
        }

        public Criteria andDodgeNotBetween(Double value1, Double value2) {
            addCriterion("dodge not between", value1, value2, "dodge");
            return (Criteria) this;
        }

        public Criteria andHitRateIsNull() {
            addCriterion("hit_rate is null");
            return (Criteria) this;
        }

        public Criteria andHitRateIsNotNull() {
            addCriterion("hit_rate is not null");
            return (Criteria) this;
        }

        public Criteria andHitRateEqualTo(Double value) {
            addCriterion("hit_rate =", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateNotEqualTo(Double value) {
            addCriterion("hit_rate <>", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateGreaterThan(Double value) {
            addCriterion("hit_rate >", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateGreaterThanOrEqualTo(Double value) {
            addCriterion("hit_rate >=", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateLessThan(Double value) {
            addCriterion("hit_rate <", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateLessThanOrEqualTo(Double value) {
            addCriterion("hit_rate <=", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateIn(List<Double> values) {
            addCriterion("hit_rate in", values, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateNotIn(List<Double> values) {
            addCriterion("hit_rate not in", values, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateBetween(Double value1, Double value2) {
            addCriterion("hit_rate between", value1, value2, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateNotBetween(Double value1, Double value2) {
            addCriterion("hit_rate not between", value1, value2, "hitRate");
            return (Criteria) this;
        }

        public Criteria andDefenseCritIsNull() {
            addCriterion("defense_crit is null");
            return (Criteria) this;
        }

        public Criteria andDefenseCritIsNotNull() {
            addCriterion("defense_crit is not null");
            return (Criteria) this;
        }

        public Criteria andDefenseCritEqualTo(Double value) {
            addCriterion("defense_crit =", value, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritNotEqualTo(Double value) {
            addCriterion("defense_crit <>", value, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritGreaterThan(Double value) {
            addCriterion("defense_crit >", value, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritGreaterThanOrEqualTo(Double value) {
            addCriterion("defense_crit >=", value, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritLessThan(Double value) {
            addCriterion("defense_crit <", value, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritLessThanOrEqualTo(Double value) {
            addCriterion("defense_crit <=", value, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritIn(List<Double> values) {
            addCriterion("defense_crit in", values, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritNotIn(List<Double> values) {
            addCriterion("defense_crit not in", values, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritBetween(Double value1, Double value2) {
            addCriterion("defense_crit between", value1, value2, "defenseCrit");
            return (Criteria) this;
        }

        public Criteria andDefenseCritNotBetween(Double value1, Double value2) {
            addCriterion("defense_crit not between", value1, value2, "defenseCrit");
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