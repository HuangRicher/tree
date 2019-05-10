package com.tongzhu.usergoods.model;

import java.util.ArrayList;
import java.util.List;

public class PropGiftInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PropGiftInfoExample() {
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

        public Criteria andPropIdIsNull() {
            addCriterion("prop_id is null");
            return (Criteria) this;
        }

        public Criteria andPropIdIsNotNull() {
            addCriterion("prop_id is not null");
            return (Criteria) this;
        }

        public Criteria andPropIdEqualTo(String value) {
            addCriterion("prop_id =", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdNotEqualTo(String value) {
            addCriterion("prop_id <>", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdGreaterThan(String value) {
            addCriterion("prop_id >", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdGreaterThanOrEqualTo(String value) {
            addCriterion("prop_id >=", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdLessThan(String value) {
            addCriterion("prop_id <", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdLessThanOrEqualTo(String value) {
            addCriterion("prop_id <=", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdLike(String value) {
            addCriterion("prop_id like", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdNotLike(String value) {
            addCriterion("prop_id not like", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdIn(List<String> values) {
            addCriterion("prop_id in", values, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdNotIn(List<String> values) {
            addCriterion("prop_id not in", values, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdBetween(String value1, String value2) {
            addCriterion("prop_id between", value1, value2, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdNotBetween(String value1, String value2) {
            addCriterion("prop_id not between", value1, value2, "propId");
            return (Criteria) this;
        }

        public Criteria andStdmodeAmountIsNull() {
            addCriterion("stdmode_amount is null");
            return (Criteria) this;
        }

        public Criteria andStdmodeAmountIsNotNull() {
            addCriterion("stdmode_amount is not null");
            return (Criteria) this;
        }

        public Criteria andStdmodeAmountEqualTo(Integer value) {
            addCriterion("stdmode_amount =", value, "stdmodeAmount");
            return (Criteria) this;
        }

        public Criteria andStdmodeAmountNotEqualTo(Integer value) {
            addCriterion("stdmode_amount <>", value, "stdmodeAmount");
            return (Criteria) this;
        }

        public Criteria andStdmodeAmountGreaterThan(Integer value) {
            addCriterion("stdmode_amount >", value, "stdmodeAmount");
            return (Criteria) this;
        }

        public Criteria andStdmodeAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("stdmode_amount >=", value, "stdmodeAmount");
            return (Criteria) this;
        }

        public Criteria andStdmodeAmountLessThan(Integer value) {
            addCriterion("stdmode_amount <", value, "stdmodeAmount");
            return (Criteria) this;
        }

        public Criteria andStdmodeAmountLessThanOrEqualTo(Integer value) {
            addCriterion("stdmode_amount <=", value, "stdmodeAmount");
            return (Criteria) this;
        }

        public Criteria andStdmodeAmountIn(List<Integer> values) {
            addCriterion("stdmode_amount in", values, "stdmodeAmount");
            return (Criteria) this;
        }

        public Criteria andStdmodeAmountNotIn(List<Integer> values) {
            addCriterion("stdmode_amount not in", values, "stdmodeAmount");
            return (Criteria) this;
        }

        public Criteria andStdmodeAmountBetween(Integer value1, Integer value2) {
            addCriterion("stdmode_amount between", value1, value2, "stdmodeAmount");
            return (Criteria) this;
        }

        public Criteria andStdmodeAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("stdmode_amount not between", value1, value2, "stdmodeAmount");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andVipLevelIsNull() {
            addCriterion("vip_level is null");
            return (Criteria) this;
        }

        public Criteria andVipLevelIsNotNull() {
            addCriterion("vip_level is not null");
            return (Criteria) this;
        }

        public Criteria andVipLevelEqualTo(Integer value) {
            addCriterion("vip_level =", value, "vipLevel");
            return (Criteria) this;
        }

        public Criteria andVipLevelNotEqualTo(Integer value) {
            addCriterion("vip_level <>", value, "vipLevel");
            return (Criteria) this;
        }

        public Criteria andVipLevelGreaterThan(Integer value) {
            addCriterion("vip_level >", value, "vipLevel");
            return (Criteria) this;
        }

        public Criteria andVipLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("vip_level >=", value, "vipLevel");
            return (Criteria) this;
        }

        public Criteria andVipLevelLessThan(Integer value) {
            addCriterion("vip_level <", value, "vipLevel");
            return (Criteria) this;
        }

        public Criteria andVipLevelLessThanOrEqualTo(Integer value) {
            addCriterion("vip_level <=", value, "vipLevel");
            return (Criteria) this;
        }

        public Criteria andVipLevelIn(List<Integer> values) {
            addCriterion("vip_level in", values, "vipLevel");
            return (Criteria) this;
        }

        public Criteria andVipLevelNotIn(List<Integer> values) {
            addCriterion("vip_level not in", values, "vipLevel");
            return (Criteria) this;
        }

        public Criteria andVipLevelBetween(Integer value1, Integer value2) {
            addCriterion("vip_level between", value1, value2, "vipLevel");
            return (Criteria) this;
        }

        public Criteria andVipLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("vip_level not between", value1, value2, "vipLevel");
            return (Criteria) this;
        }

        public Criteria andGiftBagItemsIsNull() {
            addCriterion("gift_bag_items is null");
            return (Criteria) this;
        }

        public Criteria andGiftBagItemsIsNotNull() {
            addCriterion("gift_bag_items is not null");
            return (Criteria) this;
        }

        public Criteria andGiftBagItemsEqualTo(String value) {
            addCriterion("gift_bag_items =", value, "giftBagItems");
            return (Criteria) this;
        }

        public Criteria andGiftBagItemsNotEqualTo(String value) {
            addCriterion("gift_bag_items <>", value, "giftBagItems");
            return (Criteria) this;
        }

        public Criteria andGiftBagItemsGreaterThan(String value) {
            addCriterion("gift_bag_items >", value, "giftBagItems");
            return (Criteria) this;
        }

        public Criteria andGiftBagItemsGreaterThanOrEqualTo(String value) {
            addCriterion("gift_bag_items >=", value, "giftBagItems");
            return (Criteria) this;
        }

        public Criteria andGiftBagItemsLessThan(String value) {
            addCriterion("gift_bag_items <", value, "giftBagItems");
            return (Criteria) this;
        }

        public Criteria andGiftBagItemsLessThanOrEqualTo(String value) {
            addCriterion("gift_bag_items <=", value, "giftBagItems");
            return (Criteria) this;
        }

        public Criteria andGiftBagItemsLike(String value) {
            addCriterion("gift_bag_items like", value, "giftBagItems");
            return (Criteria) this;
        }

        public Criteria andGiftBagItemsNotLike(String value) {
            addCriterion("gift_bag_items not like", value, "giftBagItems");
            return (Criteria) this;
        }

        public Criteria andGiftBagItemsIn(List<String> values) {
            addCriterion("gift_bag_items in", values, "giftBagItems");
            return (Criteria) this;
        }

        public Criteria andGiftBagItemsNotIn(List<String> values) {
            addCriterion("gift_bag_items not in", values, "giftBagItems");
            return (Criteria) this;
        }

        public Criteria andGiftBagItemsBetween(String value1, String value2) {
            addCriterion("gift_bag_items between", value1, value2, "giftBagItems");
            return (Criteria) this;
        }

        public Criteria andGiftBagItemsNotBetween(String value1, String value2) {
            addCriterion("gift_bag_items not between", value1, value2, "giftBagItems");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
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