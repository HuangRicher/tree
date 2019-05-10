package com.tongzhu.treehouse.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TreeHousePurchaseHistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TreeHousePurchaseHistoryExample() {
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

        public Criteria andCreationStartDateIsNull() {
            addCriterion("creation_start_date is null");
            return (Criteria) this;
        }

        public Criteria andCreationStartDateIsNotNull() {
            addCriterion("creation_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreationStartDateEqualTo(Date value) {
            addCriterion("creation_start_date =", value, "creationStartDate");
            return (Criteria) this;
        }

        public Criteria andCreationStartDateNotEqualTo(Date value) {
            addCriterion("creation_start_date <>", value, "creationStartDate");
            return (Criteria) this;
        }

        public Criteria andCreationStartDateGreaterThan(Date value) {
            addCriterion("creation_start_date >", value, "creationStartDate");
            return (Criteria) this;
        }

        public Criteria andCreationStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("creation_start_date >=", value, "creationStartDate");
            return (Criteria) this;
        }

        public Criteria andCreationStartDateLessThan(Date value) {
            addCriterion("creation_start_date <", value, "creationStartDate");
            return (Criteria) this;
        }

        public Criteria andCreationStartDateLessThanOrEqualTo(Date value) {
            addCriterion("creation_start_date <=", value, "creationStartDate");
            return (Criteria) this;
        }

        public Criteria andCreationStartDateIn(List<Date> values) {
            addCriterion("creation_start_date in", values, "creationStartDate");
            return (Criteria) this;
        }

        public Criteria andCreationStartDateNotIn(List<Date> values) {
            addCriterion("creation_start_date not in", values, "creationStartDate");
            return (Criteria) this;
        }

        public Criteria andCreationStartDateBetween(Date value1, Date value2) {
            addCriterion("creation_start_date between", value1, value2, "creationStartDate");
            return (Criteria) this;
        }

        public Criteria andCreationStartDateNotBetween(Date value1, Date value2) {
            addCriterion("creation_start_date not between", value1, value2, "creationStartDate");
            return (Criteria) this;
        }

        public Criteria andRecomposeEndDateIsNull() {
            addCriterion("recompose_end_date is null");
            return (Criteria) this;
        }

        public Criteria andRecomposeEndDateIsNotNull() {
            addCriterion("recompose_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andRecomposeEndDateEqualTo(Date value) {
            addCriterion("recompose_end_date =", value, "recomposeEndDate");
            return (Criteria) this;
        }

        public Criteria andRecomposeEndDateNotEqualTo(Date value) {
            addCriterion("recompose_end_date <>", value, "recomposeEndDate");
            return (Criteria) this;
        }

        public Criteria andRecomposeEndDateGreaterThan(Date value) {
            addCriterion("recompose_end_date >", value, "recomposeEndDate");
            return (Criteria) this;
        }

        public Criteria andRecomposeEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("recompose_end_date >=", value, "recomposeEndDate");
            return (Criteria) this;
        }

        public Criteria andRecomposeEndDateLessThan(Date value) {
            addCriterion("recompose_end_date <", value, "recomposeEndDate");
            return (Criteria) this;
        }

        public Criteria andRecomposeEndDateLessThanOrEqualTo(Date value) {
            addCriterion("recompose_end_date <=", value, "recomposeEndDate");
            return (Criteria) this;
        }

        public Criteria andRecomposeEndDateIn(List<Date> values) {
            addCriterion("recompose_end_date in", values, "recomposeEndDate");
            return (Criteria) this;
        }

        public Criteria andRecomposeEndDateNotIn(List<Date> values) {
            addCriterion("recompose_end_date not in", values, "recomposeEndDate");
            return (Criteria) this;
        }

        public Criteria andRecomposeEndDateBetween(Date value1, Date value2) {
            addCriterion("recompose_end_date between", value1, value2, "recomposeEndDate");
            return (Criteria) this;
        }

        public Criteria andRecomposeEndDateNotBetween(Date value1, Date value2) {
            addCriterion("recompose_end_date not between", value1, value2, "recomposeEndDate");
            return (Criteria) this;
        }

        public Criteria andSellUserIdIsNull() {
            addCriterion("sell_user_id is null");
            return (Criteria) this;
        }

        public Criteria andSellUserIdIsNotNull() {
            addCriterion("sell_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andSellUserIdEqualTo(String value) {
            addCriterion("sell_user_id =", value, "sellUserId");
            return (Criteria) this;
        }

        public Criteria andSellUserIdNotEqualTo(String value) {
            addCriterion("sell_user_id <>", value, "sellUserId");
            return (Criteria) this;
        }

        public Criteria andSellUserIdGreaterThan(String value) {
            addCriterion("sell_user_id >", value, "sellUserId");
            return (Criteria) this;
        }

        public Criteria andSellUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("sell_user_id >=", value, "sellUserId");
            return (Criteria) this;
        }

        public Criteria andSellUserIdLessThan(String value) {
            addCriterion("sell_user_id <", value, "sellUserId");
            return (Criteria) this;
        }

        public Criteria andSellUserIdLessThanOrEqualTo(String value) {
            addCriterion("sell_user_id <=", value, "sellUserId");
            return (Criteria) this;
        }

        public Criteria andSellUserIdLike(String value) {
            addCriterion("sell_user_id like", value, "sellUserId");
            return (Criteria) this;
        }

        public Criteria andSellUserIdNotLike(String value) {
            addCriterion("sell_user_id not like", value, "sellUserId");
            return (Criteria) this;
        }

        public Criteria andSellUserIdIn(List<String> values) {
            addCriterion("sell_user_id in", values, "sellUserId");
            return (Criteria) this;
        }

        public Criteria andSellUserIdNotIn(List<String> values) {
            addCriterion("sell_user_id not in", values, "sellUserId");
            return (Criteria) this;
        }

        public Criteria andSellUserIdBetween(String value1, String value2) {
            addCriterion("sell_user_id between", value1, value2, "sellUserId");
            return (Criteria) this;
        }

        public Criteria andSellUserIdNotBetween(String value1, String value2) {
            addCriterion("sell_user_id not between", value1, value2, "sellUserId");
            return (Criteria) this;
        }

        public Criteria andMonetaryAmountIsNull() {
            addCriterion("monetary_amount is null");
            return (Criteria) this;
        }

        public Criteria andMonetaryAmountIsNotNull() {
            addCriterion("monetary_amount is not null");
            return (Criteria) this;
        }

        public Criteria andMonetaryAmountEqualTo(Integer value) {
            addCriterion("monetary_amount =", value, "monetaryAmount");
            return (Criteria) this;
        }

        public Criteria andMonetaryAmountNotEqualTo(Integer value) {
            addCriterion("monetary_amount <>", value, "monetaryAmount");
            return (Criteria) this;
        }

        public Criteria andMonetaryAmountGreaterThan(Integer value) {
            addCriterion("monetary_amount >", value, "monetaryAmount");
            return (Criteria) this;
        }

        public Criteria andMonetaryAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("monetary_amount >=", value, "monetaryAmount");
            return (Criteria) this;
        }

        public Criteria andMonetaryAmountLessThan(Integer value) {
            addCriterion("monetary_amount <", value, "monetaryAmount");
            return (Criteria) this;
        }

        public Criteria andMonetaryAmountLessThanOrEqualTo(Integer value) {
            addCriterion("monetary_amount <=", value, "monetaryAmount");
            return (Criteria) this;
        }

        public Criteria andMonetaryAmountIn(List<Integer> values) {
            addCriterion("monetary_amount in", values, "monetaryAmount");
            return (Criteria) this;
        }

        public Criteria andMonetaryAmountNotIn(List<Integer> values) {
            addCriterion("monetary_amount not in", values, "monetaryAmount");
            return (Criteria) this;
        }

        public Criteria andMonetaryAmountBetween(Integer value1, Integer value2) {
            addCriterion("monetary_amount between", value1, value2, "monetaryAmount");
            return (Criteria) this;
        }

        public Criteria andMonetaryAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("monetary_amount not between", value1, value2, "monetaryAmount");
            return (Criteria) this;
        }

        public Criteria andSellingPriceIsNull() {
            addCriterion("selling_price is null");
            return (Criteria) this;
        }

        public Criteria andSellingPriceIsNotNull() {
            addCriterion("selling_price is not null");
            return (Criteria) this;
        }

        public Criteria andSellingPriceEqualTo(Integer value) {
            addCriterion("selling_price =", value, "sellingPrice");
            return (Criteria) this;
        }

        public Criteria andSellingPriceNotEqualTo(Integer value) {
            addCriterion("selling_price <>", value, "sellingPrice");
            return (Criteria) this;
        }

        public Criteria andSellingPriceGreaterThan(Integer value) {
            addCriterion("selling_price >", value, "sellingPrice");
            return (Criteria) this;
        }

        public Criteria andSellingPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("selling_price >=", value, "sellingPrice");
            return (Criteria) this;
        }

        public Criteria andSellingPriceLessThan(Integer value) {
            addCriterion("selling_price <", value, "sellingPrice");
            return (Criteria) this;
        }

        public Criteria andSellingPriceLessThanOrEqualTo(Integer value) {
            addCriterion("selling_price <=", value, "sellingPrice");
            return (Criteria) this;
        }

        public Criteria andSellingPriceIn(List<Integer> values) {
            addCriterion("selling_price in", values, "sellingPrice");
            return (Criteria) this;
        }

        public Criteria andSellingPriceNotIn(List<Integer> values) {
            addCriterion("selling_price not in", values, "sellingPrice");
            return (Criteria) this;
        }

        public Criteria andSellingPriceBetween(Integer value1, Integer value2) {
            addCriterion("selling_price between", value1, value2, "sellingPrice");
            return (Criteria) this;
        }

        public Criteria andSellingPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("selling_price not between", value1, value2, "sellingPrice");
            return (Criteria) this;
        }

        public Criteria andPurchaserUserIdIsNull() {
            addCriterion("purchaser_user_id is null");
            return (Criteria) this;
        }

        public Criteria andPurchaserUserIdIsNotNull() {
            addCriterion("purchaser_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaserUserIdEqualTo(String value) {
            addCriterion("purchaser_user_id =", value, "purchaserUserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserUserIdNotEqualTo(String value) {
            addCriterion("purchaser_user_id <>", value, "purchaserUserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserUserIdGreaterThan(String value) {
            addCriterion("purchaser_user_id >", value, "purchaserUserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("purchaser_user_id >=", value, "purchaserUserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserUserIdLessThan(String value) {
            addCriterion("purchaser_user_id <", value, "purchaserUserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserUserIdLessThanOrEqualTo(String value) {
            addCriterion("purchaser_user_id <=", value, "purchaserUserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserUserIdLike(String value) {
            addCriterion("purchaser_user_id like", value, "purchaserUserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserUserIdNotLike(String value) {
            addCriterion("purchaser_user_id not like", value, "purchaserUserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserUserIdIn(List<String> values) {
            addCriterion("purchaser_user_id in", values, "purchaserUserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserUserIdNotIn(List<String> values) {
            addCriterion("purchaser_user_id not in", values, "purchaserUserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserUserIdBetween(String value1, String value2) {
            addCriterion("purchaser_user_id between", value1, value2, "purchaserUserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserUserIdNotBetween(String value1, String value2) {
            addCriterion("purchaser_user_id not between", value1, value2, "purchaserUserId");
            return (Criteria) this;
        }

        public Criteria andBargainorUserIdIsNull() {
            addCriterion("bargainor_user_id is null");
            return (Criteria) this;
        }

        public Criteria andBargainorUserIdIsNotNull() {
            addCriterion("bargainor_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andBargainorUserIdEqualTo(String value) {
            addCriterion("bargainor_user_id =", value, "bargainorUserId");
            return (Criteria) this;
        }

        public Criteria andBargainorUserIdNotEqualTo(String value) {
            addCriterion("bargainor_user_id <>", value, "bargainorUserId");
            return (Criteria) this;
        }

        public Criteria andBargainorUserIdGreaterThan(String value) {
            addCriterion("bargainor_user_id >", value, "bargainorUserId");
            return (Criteria) this;
        }

        public Criteria andBargainorUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("bargainor_user_id >=", value, "bargainorUserId");
            return (Criteria) this;
        }

        public Criteria andBargainorUserIdLessThan(String value) {
            addCriterion("bargainor_user_id <", value, "bargainorUserId");
            return (Criteria) this;
        }

        public Criteria andBargainorUserIdLessThanOrEqualTo(String value) {
            addCriterion("bargainor_user_id <=", value, "bargainorUserId");
            return (Criteria) this;
        }

        public Criteria andBargainorUserIdLike(String value) {
            addCriterion("bargainor_user_id like", value, "bargainorUserId");
            return (Criteria) this;
        }

        public Criteria andBargainorUserIdNotLike(String value) {
            addCriterion("bargainor_user_id not like", value, "bargainorUserId");
            return (Criteria) this;
        }

        public Criteria andBargainorUserIdIn(List<String> values) {
            addCriterion("bargainor_user_id in", values, "bargainorUserId");
            return (Criteria) this;
        }

        public Criteria andBargainorUserIdNotIn(List<String> values) {
            addCriterion("bargainor_user_id not in", values, "bargainorUserId");
            return (Criteria) this;
        }

        public Criteria andBargainorUserIdBetween(String value1, String value2) {
            addCriterion("bargainor_user_id between", value1, value2, "bargainorUserId");
            return (Criteria) this;
        }

        public Criteria andBargainorUserIdNotBetween(String value1, String value2) {
            addCriterion("bargainor_user_id not between", value1, value2, "bargainorUserId");
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