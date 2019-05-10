package com.tongzhu.treehouse.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TreeHouseRoomExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TreeHouseRoomExample() {
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

        public Criteria andTreeHouseIdIsNull() {
            addCriterion("tree_house_Id is null");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdIsNotNull() {
            addCriterion("tree_house_Id is not null");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdEqualTo(String value) {
            addCriterion("tree_house_Id =", value, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdNotEqualTo(String value) {
            addCriterion("tree_house_Id <>", value, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdGreaterThan(String value) {
            addCriterion("tree_house_Id >", value, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdGreaterThanOrEqualTo(String value) {
            addCriterion("tree_house_Id >=", value, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdLessThan(String value) {
            addCriterion("tree_house_Id <", value, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdLessThanOrEqualTo(String value) {
            addCriterion("tree_house_Id <=", value, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdLike(String value) {
            addCriterion("tree_house_Id like", value, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdNotLike(String value) {
            addCriterion("tree_house_Id not like", value, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdIn(List<String> values) {
            addCriterion("tree_house_Id in", values, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdNotIn(List<String> values) {
            addCriterion("tree_house_Id not in", values, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdBetween(String value1, String value2) {
            addCriterion("tree_house_Id between", value1, value2, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andTreeHouseIdNotBetween(String value1, String value2) {
            addCriterion("tree_house_Id not between", value1, value2, "treeHouseId");
            return (Criteria) this;
        }

        public Criteria andRoomIdIsNull() {
            addCriterion("room_id is null");
            return (Criteria) this;
        }

        public Criteria andRoomIdIsNotNull() {
            addCriterion("room_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoomIdEqualTo(Integer value) {
            addCriterion("room_id =", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotEqualTo(Integer value) {
            addCriterion("room_id <>", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdGreaterThan(Integer value) {
            addCriterion("room_id >", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("room_id >=", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLessThan(Integer value) {
            addCriterion("room_id <", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLessThanOrEqualTo(Integer value) {
            addCriterion("room_id <=", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdIn(List<Integer> values) {
            addCriterion("room_id in", values, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotIn(List<Integer> values) {
            addCriterion("room_id not in", values, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdBetween(Integer value1, Integer value2) {
            addCriterion("room_id between", value1, value2, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotBetween(Integer value1, Integer value2) {
            addCriterion("room_id not between", value1, value2, "roomId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdIsNull() {
            addCriterion("worker_id is null");
            return (Criteria) this;
        }

        public Criteria andWorkerIdIsNotNull() {
            addCriterion("worker_id is not null");
            return (Criteria) this;
        }

        public Criteria andWorkerIdEqualTo(String value) {
            addCriterion("worker_id =", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdNotEqualTo(String value) {
            addCriterion("worker_id <>", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdGreaterThan(String value) {
            addCriterion("worker_id >", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdGreaterThanOrEqualTo(String value) {
            addCriterion("worker_id >=", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdLessThan(String value) {
            addCriterion("worker_id <", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdLessThanOrEqualTo(String value) {
            addCriterion("worker_id <=", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdLike(String value) {
            addCriterion("worker_id like", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdNotLike(String value) {
            addCriterion("worker_id not like", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdIn(List<String> values) {
            addCriterion("worker_id in", values, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdNotIn(List<String> values) {
            addCriterion("worker_id not in", values, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdBetween(String value1, String value2) {
            addCriterion("worker_id between", value1, value2, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdNotBetween(String value1, String value2) {
            addCriterion("worker_id not between", value1, value2, "workerId");
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

        public Criteria andStatusIsNull() {
            addCriterion("status_ is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status_ is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status_ =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status_ <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status_ >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status_ >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status_ <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status_ <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status_ in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status_ not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status_ between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status_ not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andProtectStartDateIsNull() {
            addCriterion("protect_start_date is null");
            return (Criteria) this;
        }

        public Criteria andProtectStartDateIsNotNull() {
            addCriterion("protect_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andProtectStartDateEqualTo(Date value) {
            addCriterion("protect_start_date =", value, "protectStartDate");
            return (Criteria) this;
        }

        public Criteria andProtectStartDateNotEqualTo(Date value) {
            addCriterion("protect_start_date <>", value, "protectStartDate");
            return (Criteria) this;
        }

        public Criteria andProtectStartDateGreaterThan(Date value) {
            addCriterion("protect_start_date >", value, "protectStartDate");
            return (Criteria) this;
        }

        public Criteria andProtectStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("protect_start_date >=", value, "protectStartDate");
            return (Criteria) this;
        }

        public Criteria andProtectStartDateLessThan(Date value) {
            addCriterion("protect_start_date <", value, "protectStartDate");
            return (Criteria) this;
        }

        public Criteria andProtectStartDateLessThanOrEqualTo(Date value) {
            addCriterion("protect_start_date <=", value, "protectStartDate");
            return (Criteria) this;
        }

        public Criteria andProtectStartDateIn(List<Date> values) {
            addCriterion("protect_start_date in", values, "protectStartDate");
            return (Criteria) this;
        }

        public Criteria andProtectStartDateNotIn(List<Date> values) {
            addCriterion("protect_start_date not in", values, "protectStartDate");
            return (Criteria) this;
        }

        public Criteria andProtectStartDateBetween(Date value1, Date value2) {
            addCriterion("protect_start_date between", value1, value2, "protectStartDate");
            return (Criteria) this;
        }

        public Criteria andProtectStartDateNotBetween(Date value1, Date value2) {
            addCriterion("protect_start_date not between", value1, value2, "protectStartDate");
            return (Criteria) this;
        }

        public Criteria andProtectEndDateIsNull() {
            addCriterion("protect_end_date is null");
            return (Criteria) this;
        }

        public Criteria andProtectEndDateIsNotNull() {
            addCriterion("protect_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andProtectEndDateEqualTo(Date value) {
            addCriterion("protect_end_date =", value, "protectEndDate");
            return (Criteria) this;
        }

        public Criteria andProtectEndDateNotEqualTo(Date value) {
            addCriterion("protect_end_date <>", value, "protectEndDate");
            return (Criteria) this;
        }

        public Criteria andProtectEndDateGreaterThan(Date value) {
            addCriterion("protect_end_date >", value, "protectEndDate");
            return (Criteria) this;
        }

        public Criteria andProtectEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("protect_end_date >=", value, "protectEndDate");
            return (Criteria) this;
        }

        public Criteria andProtectEndDateLessThan(Date value) {
            addCriterion("protect_end_date <", value, "protectEndDate");
            return (Criteria) this;
        }

        public Criteria andProtectEndDateLessThanOrEqualTo(Date value) {
            addCriterion("protect_end_date <=", value, "protectEndDate");
            return (Criteria) this;
        }

        public Criteria andProtectEndDateIn(List<Date> values) {
            addCriterion("protect_end_date in", values, "protectEndDate");
            return (Criteria) this;
        }

        public Criteria andProtectEndDateNotIn(List<Date> values) {
            addCriterion("protect_end_date not in", values, "protectEndDate");
            return (Criteria) this;
        }

        public Criteria andProtectEndDateBetween(Date value1, Date value2) {
            addCriterion("protect_end_date between", value1, value2, "protectEndDate");
            return (Criteria) this;
        }

        public Criteria andProtectEndDateNotBetween(Date value1, Date value2) {
            addCriterion("protect_end_date not between", value1, value2, "protectEndDate");
            return (Criteria) this;
        }

        public Criteria andReducePriceDateIsNull() {
            addCriterion("reduce_price_date is null");
            return (Criteria) this;
        }

        public Criteria andReducePriceDateIsNotNull() {
            addCriterion("reduce_price_date is not null");
            return (Criteria) this;
        }

        public Criteria andReducePriceDateEqualTo(Date value) {
            addCriterion("reduce_price_date =", value, "reducePriceDate");
            return (Criteria) this;
        }

        public Criteria andReducePriceDateNotEqualTo(Date value) {
            addCriterion("reduce_price_date <>", value, "reducePriceDate");
            return (Criteria) this;
        }

        public Criteria andReducePriceDateGreaterThan(Date value) {
            addCriterion("reduce_price_date >", value, "reducePriceDate");
            return (Criteria) this;
        }

        public Criteria andReducePriceDateGreaterThanOrEqualTo(Date value) {
            addCriterion("reduce_price_date >=", value, "reducePriceDate");
            return (Criteria) this;
        }

        public Criteria andReducePriceDateLessThan(Date value) {
            addCriterion("reduce_price_date <", value, "reducePriceDate");
            return (Criteria) this;
        }

        public Criteria andReducePriceDateLessThanOrEqualTo(Date value) {
            addCriterion("reduce_price_date <=", value, "reducePriceDate");
            return (Criteria) this;
        }

        public Criteria andReducePriceDateIn(List<Date> values) {
            addCriterion("reduce_price_date in", values, "reducePriceDate");
            return (Criteria) this;
        }

        public Criteria andReducePriceDateNotIn(List<Date> values) {
            addCriterion("reduce_price_date not in", values, "reducePriceDate");
            return (Criteria) this;
        }

        public Criteria andReducePriceDateBetween(Date value1, Date value2) {
            addCriterion("reduce_price_date between", value1, value2, "reducePriceDate");
            return (Criteria) this;
        }

        public Criteria andReducePriceDateNotBetween(Date value1, Date value2) {
            addCriterion("reduce_price_date not between", value1, value2, "reducePriceDate");
            return (Criteria) this;
        }

        public Criteria andIsWxFriendIsNull() {
            addCriterion("is_wx_friend is null");
            return (Criteria) this;
        }

        public Criteria andIsWxFriendIsNotNull() {
            addCriterion("is_wx_friend is not null");
            return (Criteria) this;
        }

        public Criteria andIsWxFriendEqualTo(Boolean value) {
            addCriterion("is_wx_friend =", value, "isWxFriend");
            return (Criteria) this;
        }

        public Criteria andIsWxFriendNotEqualTo(Boolean value) {
            addCriterion("is_wx_friend <>", value, "isWxFriend");
            return (Criteria) this;
        }

        public Criteria andIsWxFriendGreaterThan(Boolean value) {
            addCriterion("is_wx_friend >", value, "isWxFriend");
            return (Criteria) this;
        }

        public Criteria andIsWxFriendGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_wx_friend >=", value, "isWxFriend");
            return (Criteria) this;
        }

        public Criteria andIsWxFriendLessThan(Boolean value) {
            addCriterion("is_wx_friend <", value, "isWxFriend");
            return (Criteria) this;
        }

        public Criteria andIsWxFriendLessThanOrEqualTo(Boolean value) {
            addCriterion("is_wx_friend <=", value, "isWxFriend");
            return (Criteria) this;
        }

        public Criteria andIsWxFriendIn(List<Boolean> values) {
            addCriterion("is_wx_friend in", values, "isWxFriend");
            return (Criteria) this;
        }

        public Criteria andIsWxFriendNotIn(List<Boolean> values) {
            addCriterion("is_wx_friend not in", values, "isWxFriend");
            return (Criteria) this;
        }

        public Criteria andIsWxFriendBetween(Boolean value1, Boolean value2) {
            addCriterion("is_wx_friend between", value1, value2, "isWxFriend");
            return (Criteria) this;
        }

        public Criteria andIsWxFriendNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_wx_friend not between", value1, value2, "isWxFriend");
            return (Criteria) this;
        }

        public Criteria andIsGameFriendIsNull() {
            addCriterion("is_game_friend is null");
            return (Criteria) this;
        }

        public Criteria andIsGameFriendIsNotNull() {
            addCriterion("is_game_friend is not null");
            return (Criteria) this;
        }

        public Criteria andIsGameFriendEqualTo(Boolean value) {
            addCriterion("is_game_friend =", value, "isGameFriend");
            return (Criteria) this;
        }

        public Criteria andIsGameFriendNotEqualTo(Boolean value) {
            addCriterion("is_game_friend <>", value, "isGameFriend");
            return (Criteria) this;
        }

        public Criteria andIsGameFriendGreaterThan(Boolean value) {
            addCriterion("is_game_friend >", value, "isGameFriend");
            return (Criteria) this;
        }

        public Criteria andIsGameFriendGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_game_friend >=", value, "isGameFriend");
            return (Criteria) this;
        }

        public Criteria andIsGameFriendLessThan(Boolean value) {
            addCriterion("is_game_friend <", value, "isGameFriend");
            return (Criteria) this;
        }

        public Criteria andIsGameFriendLessThanOrEqualTo(Boolean value) {
            addCriterion("is_game_friend <=", value, "isGameFriend");
            return (Criteria) this;
        }

        public Criteria andIsGameFriendIn(List<Boolean> values) {
            addCriterion("is_game_friend in", values, "isGameFriend");
            return (Criteria) this;
        }

        public Criteria andIsGameFriendNotIn(List<Boolean> values) {
            addCriterion("is_game_friend not in", values, "isGameFriend");
            return (Criteria) this;
        }

        public Criteria andIsGameFriendBetween(Boolean value1, Boolean value2) {
            addCriterion("is_game_friend between", value1, value2, "isGameFriend");
            return (Criteria) this;
        }

        public Criteria andIsGameFriendNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_game_friend not between", value1, value2, "isGameFriend");
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