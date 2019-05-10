package com.tongzhu.treehouse.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TreeHouseFlowerpotExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TreeHouseFlowerpotExample() {
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

        public Criteria andTreeHouseIdIsNull() {
            addCriterion("tree_house_id is null");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdIsNotNull() {
            addCriterion("tree_house_id is not null");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdEqualTo(String value) {
            addCriterion("tree_house_id =", value, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdNotEqualTo(String value) {
            addCriterion("tree_house_id <>", value, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdGreaterThan(String value) {
            addCriterion("tree_house_id >", value, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdGreaterThanOrEqualTo(String value) {
            addCriterion("tree_house_id >=", value, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdLessThan(String value) {
            addCriterion("tree_house_id <", value, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdLessThanOrEqualTo(String value) {
            addCriterion("tree_house_id <=", value, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdLike(String value) {
            addCriterion("tree_house_id like", value, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdNotLike(String value) {
            addCriterion("tree_house_id not like", value, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdIn(List<String> values) {
            addCriterion("tree_house_id in", values, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdNotIn(List<String> values) {
            addCriterion("tree_house_id not in", values, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdBetween(String value1, String value2) {
            addCriterion("tree_house_id between", value1, value2, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdNotBetween(String value1, String value2) {
            addCriterion("tree_house_id not between", value1, value2, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNull() {
            addCriterion("goods_id is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNotNull() {
            addCriterion("goods_id is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdEqualTo(String value) {
            addCriterion("goods_id =", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotEqualTo(String value) {
            addCriterion("goods_id <>", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThan(String value) {
            addCriterion("goods_id >", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThanOrEqualTo(String value) {
            addCriterion("goods_id >=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThan(String value) {
            addCriterion("goods_id <", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThanOrEqualTo(String value) {
            addCriterion("goods_id <=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLike(String value) {
            addCriterion("goods_id like", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotLike(String value) {
            addCriterion("goods_id not like", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIn(List<String> values) {
            addCriterion("goods_id in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotIn(List<String> values) {
            addCriterion("goods_id not in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdBetween(String value1, String value2) {
            addCriterion("goods_id between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotBetween(String value1, String value2) {
            addCriterion("goods_id not between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andLockLevelIsNull() {
            addCriterion("lock_level is null");
            return (Criteria) this;
        }

        public Criteria andLockLevelIsNotNull() {
            addCriterion("lock_level is not null");
            return (Criteria) this;
        }

        public Criteria andLockLevelEqualTo(Integer value) {
            addCriterion("lock_level =", value, "lockLevel");
            return (Criteria) this;
        }

        public Criteria andLockLevelNotEqualTo(Integer value) {
            addCriterion("lock_level <>", value, "lockLevel");
            return (Criteria) this;
        }

        public Criteria andLockLevelGreaterThan(Integer value) {
            addCriterion("lock_level >", value, "lockLevel");
            return (Criteria) this;
        }

        public Criteria andLockLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("lock_level >=", value, "lockLevel");
            return (Criteria) this;
        }

        public Criteria andLockLevelLessThan(Integer value) {
            addCriterion("lock_level <", value, "lockLevel");
            return (Criteria) this;
        }

        public Criteria andLockLevelLessThanOrEqualTo(Integer value) {
            addCriterion("lock_level <=", value, "lockLevel");
            return (Criteria) this;
        }

        public Criteria andLockLevelIn(List<Integer> values) {
            addCriterion("lock_level in", values, "lockLevel");
            return (Criteria) this;
        }

        public Criteria andLockLevelNotIn(List<Integer> values) {
            addCriterion("lock_level not in", values, "lockLevel");
            return (Criteria) this;
        }

        public Criteria andLockLevelBetween(Integer value1, Integer value2) {
            addCriterion("lock_level between", value1, value2, "lockLevel");
            return (Criteria) this;
        }

        public Criteria andLockLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("lock_level not between", value1, value2, "lockLevel");
            return (Criteria) this;
        }

        public Criteria andLockStatusIsNull() {
            addCriterion("lock_status is null");
            return (Criteria) this;
        }

        public Criteria andLockStatusIsNotNull() {
            addCriterion("lock_status is not null");
            return (Criteria) this;
        }

        public Criteria andLockStatusEqualTo(Integer value) {
            addCriterion("lock_status =", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusNotEqualTo(Integer value) {
            addCriterion("lock_status <>", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusGreaterThan(Integer value) {
            addCriterion("lock_status >", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("lock_status >=", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusLessThan(Integer value) {
            addCriterion("lock_status <", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusLessThanOrEqualTo(Integer value) {
            addCriterion("lock_status <=", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusIn(List<Integer> values) {
            addCriterion("lock_status in", values, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusNotIn(List<Integer> values) {
            addCriterion("lock_status not in", values, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusBetween(Integer value1, Integer value2) {
            addCriterion("lock_status between", value1, value2, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("lock_status not between", value1, value2, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andSowingDateIsNull() {
            addCriterion("sowing_date is null");
            return (Criteria) this;
        }

        public Criteria andSowingDateIsNotNull() {
            addCriterion("sowing_date is not null");
            return (Criteria) this;
        }

        public Criteria andSowingDateEqualTo(Date value) {
            addCriterion("sowing_date =", value, "sowingDate");
            return (Criteria) this;
        }

        public Criteria andSowingDateNotEqualTo(Date value) {
            addCriterion("sowing_date <>", value, "sowingDate");
            return (Criteria) this;
        }

        public Criteria andSowingDateGreaterThan(Date value) {
            addCriterion("sowing_date >", value, "sowingDate");
            return (Criteria) this;
        }

        public Criteria andSowingDateGreaterThanOrEqualTo(Date value) {
            addCriterion("sowing_date >=", value, "sowingDate");
            return (Criteria) this;
        }

        public Criteria andSowingDateLessThan(Date value) {
            addCriterion("sowing_date <", value, "sowingDate");
            return (Criteria) this;
        }

        public Criteria andSowingDateLessThanOrEqualTo(Date value) {
            addCriterion("sowing_date <=", value, "sowingDate");
            return (Criteria) this;
        }

        public Criteria andSowingDateIn(List<Date> values) {
            addCriterion("sowing_date in", values, "sowingDate");
            return (Criteria) this;
        }

        public Criteria andSowingDateNotIn(List<Date> values) {
            addCriterion("sowing_date not in", values, "sowingDate");
            return (Criteria) this;
        }

        public Criteria andSowingDateBetween(Date value1, Date value2) {
            addCriterion("sowing_date between", value1, value2, "sowingDate");
            return (Criteria) this;
        }

        public Criteria andSowingDateNotBetween(Date value1, Date value2) {
            addCriterion("sowing_date not between", value1, value2, "sowingDate");
            return (Criteria) this;
        }

        public Criteria andWaterCountIsNull() {
            addCriterion("water_count is null");
            return (Criteria) this;
        }

        public Criteria andWaterCountIsNotNull() {
            addCriterion("water_count is not null");
            return (Criteria) this;
        }

        public Criteria andWaterCountEqualTo(Integer value) {
            addCriterion("water_count =", value, "waterCount");
            return (Criteria) this;
        }

        public Criteria andWaterCountNotEqualTo(Integer value) {
            addCriterion("water_count <>", value, "waterCount");
            return (Criteria) this;
        }

        public Criteria andWaterCountGreaterThan(Integer value) {
            addCriterion("water_count >", value, "waterCount");
            return (Criteria) this;
        }

        public Criteria andWaterCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("water_count >=", value, "waterCount");
            return (Criteria) this;
        }

        public Criteria andWaterCountLessThan(Integer value) {
            addCriterion("water_count <", value, "waterCount");
            return (Criteria) this;
        }

        public Criteria andWaterCountLessThanOrEqualTo(Integer value) {
            addCriterion("water_count <=", value, "waterCount");
            return (Criteria) this;
        }

        public Criteria andWaterCountIn(List<Integer> values) {
            addCriterion("water_count in", values, "waterCount");
            return (Criteria) this;
        }

        public Criteria andWaterCountNotIn(List<Integer> values) {
            addCriterion("water_count not in", values, "waterCount");
            return (Criteria) this;
        }

        public Criteria andWaterCountBetween(Integer value1, Integer value2) {
            addCriterion("water_count between", value1, value2, "waterCount");
            return (Criteria) this;
        }

        public Criteria andWaterCountNotBetween(Integer value1, Integer value2) {
            addCriterion("water_count not between", value1, value2, "waterCount");
            return (Criteria) this;
        }

        public Criteria andLastWaterDateIsNull() {
            addCriterion("last_water_date is null");
            return (Criteria) this;
        }

        public Criteria andLastWaterDateIsNotNull() {
            addCriterion("last_water_date is not null");
            return (Criteria) this;
        }

        public Criteria andLastWaterDateEqualTo(Date value) {
            addCriterion("last_water_date =", value, "lastWaterDate");
            return (Criteria) this;
        }

        public Criteria andLastWaterDateNotEqualTo(Date value) {
            addCriterion("last_water_date <>", value, "lastWaterDate");
            return (Criteria) this;
        }

        public Criteria andLastWaterDateGreaterThan(Date value) {
            addCriterion("last_water_date >", value, "lastWaterDate");
            return (Criteria) this;
        }

        public Criteria andLastWaterDateGreaterThanOrEqualTo(Date value) {
            addCriterion("last_water_date >=", value, "lastWaterDate");
            return (Criteria) this;
        }

        public Criteria andLastWaterDateLessThan(Date value) {
            addCriterion("last_water_date <", value, "lastWaterDate");
            return (Criteria) this;
        }

        public Criteria andLastWaterDateLessThanOrEqualTo(Date value) {
            addCriterion("last_water_date <=", value, "lastWaterDate");
            return (Criteria) this;
        }

        public Criteria andLastWaterDateIn(List<Date> values) {
            addCriterion("last_water_date in", values, "lastWaterDate");
            return (Criteria) this;
        }

        public Criteria andLastWaterDateNotIn(List<Date> values) {
            addCriterion("last_water_date not in", values, "lastWaterDate");
            return (Criteria) this;
        }

        public Criteria andLastWaterDateBetween(Date value1, Date value2) {
            addCriterion("last_water_date between", value1, value2, "lastWaterDate");
            return (Criteria) this;
        }

        public Criteria andLastWaterDateNotBetween(Date value1, Date value2) {
            addCriterion("last_water_date not between", value1, value2, "lastWaterDate");
            return (Criteria) this;
        }

        public Criteria andSpreadManureCountIsNull() {
            addCriterion("spread_manure_count is null");
            return (Criteria) this;
        }

        public Criteria andSpreadManureCountIsNotNull() {
            addCriterion("spread_manure_count is not null");
            return (Criteria) this;
        }

        public Criteria andSpreadManureCountEqualTo(Integer value) {
            addCriterion("spread_manure_count =", value, "spreadManureCount");
            return (Criteria) this;
        }

        public Criteria andSpreadManureCountNotEqualTo(Integer value) {
            addCriterion("spread_manure_count <>", value, "spreadManureCount");
            return (Criteria) this;
        }

        public Criteria andSpreadManureCountGreaterThan(Integer value) {
            addCriterion("spread_manure_count >", value, "spreadManureCount");
            return (Criteria) this;
        }

        public Criteria andSpreadManureCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("spread_manure_count >=", value, "spreadManureCount");
            return (Criteria) this;
        }

        public Criteria andSpreadManureCountLessThan(Integer value) {
            addCriterion("spread_manure_count <", value, "spreadManureCount");
            return (Criteria) this;
        }

        public Criteria andSpreadManureCountLessThanOrEqualTo(Integer value) {
            addCriterion("spread_manure_count <=", value, "spreadManureCount");
            return (Criteria) this;
        }

        public Criteria andSpreadManureCountIn(List<Integer> values) {
            addCriterion("spread_manure_count in", values, "spreadManureCount");
            return (Criteria) this;
        }

        public Criteria andSpreadManureCountNotIn(List<Integer> values) {
            addCriterion("spread_manure_count not in", values, "spreadManureCount");
            return (Criteria) this;
        }

        public Criteria andSpreadManureCountBetween(Integer value1, Integer value2) {
            addCriterion("spread_manure_count between", value1, value2, "spreadManureCount");
            return (Criteria) this;
        }

        public Criteria andSpreadManureCountNotBetween(Integer value1, Integer value2) {
            addCriterion("spread_manure_count not between", value1, value2, "spreadManureCount");
            return (Criteria) this;
        }

        public Criteria andLastSpreadManureDateIsNull() {
            addCriterion("last_spread_manure_date is null");
            return (Criteria) this;
        }

        public Criteria andLastSpreadManureDateIsNotNull() {
            addCriterion("last_spread_manure_date is not null");
            return (Criteria) this;
        }

        public Criteria andLastSpreadManureDateEqualTo(Date value) {
            addCriterion("last_spread_manure_date =", value, "lastSpreadManureDate");
            return (Criteria) this;
        }

        public Criteria andLastSpreadManureDateNotEqualTo(Date value) {
            addCriterion("last_spread_manure_date <>", value, "lastSpreadManureDate");
            return (Criteria) this;
        }

        public Criteria andLastSpreadManureDateGreaterThan(Date value) {
            addCriterion("last_spread_manure_date >", value, "lastSpreadManureDate");
            return (Criteria) this;
        }

        public Criteria andLastSpreadManureDateGreaterThanOrEqualTo(Date value) {
            addCriterion("last_spread_manure_date >=", value, "lastSpreadManureDate");
            return (Criteria) this;
        }

        public Criteria andLastSpreadManureDateLessThan(Date value) {
            addCriterion("last_spread_manure_date <", value, "lastSpreadManureDate");
            return (Criteria) this;
        }

        public Criteria andLastSpreadManureDateLessThanOrEqualTo(Date value) {
            addCriterion("last_spread_manure_date <=", value, "lastSpreadManureDate");
            return (Criteria) this;
        }

        public Criteria andLastSpreadManureDateIn(List<Date> values) {
            addCriterion("last_spread_manure_date in", values, "lastSpreadManureDate");
            return (Criteria) this;
        }

        public Criteria andLastSpreadManureDateNotIn(List<Date> values) {
            addCriterion("last_spread_manure_date not in", values, "lastSpreadManureDate");
            return (Criteria) this;
        }

        public Criteria andLastSpreadManureDateBetween(Date value1, Date value2) {
            addCriterion("last_spread_manure_date between", value1, value2, "lastSpreadManureDate");
            return (Criteria) this;
        }

        public Criteria andLastSpreadManureDateNotBetween(Date value1, Date value2) {
            addCriterion("last_spread_manure_date not between", value1, value2, "lastSpreadManureDate");
            return (Criteria) this;
        }

        public Criteria andFlowerStatusIsNull() {
            addCriterion("flower_status is null");
            return (Criteria) this;
        }

        public Criteria andFlowerStatusIsNotNull() {
            addCriterion("flower_status is not null");
            return (Criteria) this;
        }

        public Criteria andFlowerStatusEqualTo(Integer value) {
            addCriterion("flower_status =", value, "flowerStatus");
            return (Criteria) this;
        }

        public Criteria andFlowerStatusNotEqualTo(Integer value) {
            addCriterion("flower_status <>", value, "flowerStatus");
            return (Criteria) this;
        }

        public Criteria andFlowerStatusGreaterThan(Integer value) {
            addCriterion("flower_status >", value, "flowerStatus");
            return (Criteria) this;
        }

        public Criteria andFlowerStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("flower_status >=", value, "flowerStatus");
            return (Criteria) this;
        }

        public Criteria andFlowerStatusLessThan(Integer value) {
            addCriterion("flower_status <", value, "flowerStatus");
            return (Criteria) this;
        }

        public Criteria andFlowerStatusLessThanOrEqualTo(Integer value) {
            addCriterion("flower_status <=", value, "flowerStatus");
            return (Criteria) this;
        }

        public Criteria andFlowerStatusIn(List<Integer> values) {
            addCriterion("flower_status in", values, "flowerStatus");
            return (Criteria) this;
        }

        public Criteria andFlowerStatusNotIn(List<Integer> values) {
            addCriterion("flower_status not in", values, "flowerStatus");
            return (Criteria) this;
        }

        public Criteria andFlowerStatusBetween(Integer value1, Integer value2) {
            addCriterion("flower_status between", value1, value2, "flowerStatus");
            return (Criteria) this;
        }

        public Criteria andFlowerStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("flower_status not between", value1, value2, "flowerStatus");
            return (Criteria) this;
        }

        public Criteria andChangeDateIsNull() {
            addCriterion("change_date is null");
            return (Criteria) this;
        }

        public Criteria andChangeDateIsNotNull() {
            addCriterion("change_date is not null");
            return (Criteria) this;
        }

        public Criteria andChangeDateEqualTo(Date value) {
            addCriterion("change_date =", value, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateNotEqualTo(Date value) {
            addCriterion("change_date <>", value, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateGreaterThan(Date value) {
            addCriterion("change_date >", value, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateGreaterThanOrEqualTo(Date value) {
            addCriterion("change_date >=", value, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateLessThan(Date value) {
            addCriterion("change_date <", value, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateLessThanOrEqualTo(Date value) {
            addCriterion("change_date <=", value, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateIn(List<Date> values) {
            addCriterion("change_date in", values, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateNotIn(List<Date> values) {
            addCriterion("change_date not in", values, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateBetween(Date value1, Date value2) {
            addCriterion("change_date between", value1, value2, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateNotBetween(Date value1, Date value2) {
            addCriterion("change_date not between", value1, value2, "changeDate");
            return (Criteria) this;
        }

        public Criteria andPlantIdIsNull() {
            addCriterion("plant_id is null");
            return (Criteria) this;
        }

        public Criteria andPlantIdIsNotNull() {
            addCriterion("plant_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlantIdEqualTo(String value) {
            addCriterion("plant_id =", value, "plantId");
            return (Criteria) this;
        }

        public Criteria andPlantIdNotEqualTo(String value) {
            addCriterion("plant_id <>", value, "plantId");
            return (Criteria) this;
        }

        public Criteria andPlantIdGreaterThan(String value) {
            addCriterion("plant_id >", value, "plantId");
            return (Criteria) this;
        }

        public Criteria andPlantIdGreaterThanOrEqualTo(String value) {
            addCriterion("plant_id >=", value, "plantId");
            return (Criteria) this;
        }

        public Criteria andPlantIdLessThan(String value) {
            addCriterion("plant_id <", value, "plantId");
            return (Criteria) this;
        }

        public Criteria andPlantIdLessThanOrEqualTo(String value) {
            addCriterion("plant_id <=", value, "plantId");
            return (Criteria) this;
        }

        public Criteria andPlantIdLike(String value) {
            addCriterion("plant_id like", value, "plantId");
            return (Criteria) this;
        }

        public Criteria andPlantIdNotLike(String value) {
            addCriterion("plant_id not like", value, "plantId");
            return (Criteria) this;
        }

        public Criteria andPlantIdIn(List<String> values) {
            addCriterion("plant_id in", values, "plantId");
            return (Criteria) this;
        }

        public Criteria andPlantIdNotIn(List<String> values) {
            addCriterion("plant_id not in", values, "plantId");
            return (Criteria) this;
        }

        public Criteria andPlantIdBetween(String value1, String value2) {
            addCriterion("plant_id between", value1, value2, "plantId");
            return (Criteria) this;
        }

        public Criteria andPlantIdNotBetween(String value1, String value2) {
            addCriterion("plant_id not between", value1, value2, "plantId");
            return (Criteria) this;
        }

        public Criteria andDayGainCountIsNull() {
            addCriterion("day_gain_count is null");
            return (Criteria) this;
        }

        public Criteria andDayGainCountIsNotNull() {
            addCriterion("day_gain_count is not null");
            return (Criteria) this;
        }

        public Criteria andDayGainCountEqualTo(Integer value) {
            addCriterion("day_gain_count =", value, "dayGainCount");
            return (Criteria) this;
        }

        public Criteria andDayGainCountNotEqualTo(Integer value) {
            addCriterion("day_gain_count <>", value, "dayGainCount");
            return (Criteria) this;
        }

        public Criteria andDayGainCountGreaterThan(Integer value) {
            addCriterion("day_gain_count >", value, "dayGainCount");
            return (Criteria) this;
        }

        public Criteria andDayGainCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("day_gain_count >=", value, "dayGainCount");
            return (Criteria) this;
        }

        public Criteria andDayGainCountLessThan(Integer value) {
            addCriterion("day_gain_count <", value, "dayGainCount");
            return (Criteria) this;
        }

        public Criteria andDayGainCountLessThanOrEqualTo(Integer value) {
            addCriterion("day_gain_count <=", value, "dayGainCount");
            return (Criteria) this;
        }

        public Criteria andDayGainCountIn(List<Integer> values) {
            addCriterion("day_gain_count in", values, "dayGainCount");
            return (Criteria) this;
        }

        public Criteria andDayGainCountNotIn(List<Integer> values) {
            addCriterion("day_gain_count not in", values, "dayGainCount");
            return (Criteria) this;
        }

        public Criteria andDayGainCountBetween(Integer value1, Integer value2) {
            addCriterion("day_gain_count between", value1, value2, "dayGainCount");
            return (Criteria) this;
        }

        public Criteria andDayGainCountNotBetween(Integer value1, Integer value2) {
            addCriterion("day_gain_count not between", value1, value2, "dayGainCount");
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