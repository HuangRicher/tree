package com.tongzhu.user.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserMailSingleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserMailSingleExample() {
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

        public Criteria andReceiverIdIsNull() {
            addCriterion("receiver_id is null");
            return (Criteria) this;
        }

        public Criteria andReceiverIdIsNotNull() {
            addCriterion("receiver_id is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverIdEqualTo(String value) {
            addCriterion("receiver_id =", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdNotEqualTo(String value) {
            addCriterion("receiver_id <>", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdGreaterThan(String value) {
            addCriterion("receiver_id >", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_id >=", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdLessThan(String value) {
            addCriterion("receiver_id <", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdLessThanOrEqualTo(String value) {
            addCriterion("receiver_id <=", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdLike(String value) {
            addCriterion("receiver_id like", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdNotLike(String value) {
            addCriterion("receiver_id not like", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdIn(List<String> values) {
            addCriterion("receiver_id in", values, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdNotIn(List<String> values) {
            addCriterion("receiver_id not in", values, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdBetween(String value1, String value2) {
            addCriterion("receiver_id between", value1, value2, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdNotBetween(String value1, String value2) {
            addCriterion("receiver_id not between", value1, value2, "receiverId");
            return (Criteria) this;
        }

        public Criteria andSenderIdIsNull() {
            addCriterion("sender_id is null");
            return (Criteria) this;
        }

        public Criteria andSenderIdIsNotNull() {
            addCriterion("sender_id is not null");
            return (Criteria) this;
        }

        public Criteria andSenderIdEqualTo(String value) {
            addCriterion("sender_id =", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdNotEqualTo(String value) {
            addCriterion("sender_id <>", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdGreaterThan(String value) {
            addCriterion("sender_id >", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdGreaterThanOrEqualTo(String value) {
            addCriterion("sender_id >=", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdLessThan(String value) {
            addCriterion("sender_id <", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdLessThanOrEqualTo(String value) {
            addCriterion("sender_id <=", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdLike(String value) {
            addCriterion("sender_id like", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdNotLike(String value) {
            addCriterion("sender_id not like", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdIn(List<String> values) {
            addCriterion("sender_id in", values, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdNotIn(List<String> values) {
            addCriterion("sender_id not in", values, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdBetween(String value1, String value2) {
            addCriterion("sender_id between", value1, value2, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdNotBetween(String value1, String value2) {
            addCriterion("sender_id not between", value1, value2, "senderId");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNull() {
            addCriterion("send_time is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("send_time is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(Date value) {
            addCriterion("send_time =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(Date value) {
            addCriterion("send_time <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(Date value) {
            addCriterion("send_time >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("send_time >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(Date value) {
            addCriterion("send_time <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(Date value) {
            addCriterion("send_time <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<Date> values) {
            addCriterion("send_time in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<Date> values) {
            addCriterion("send_time not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(Date value1, Date value2) {
            addCriterion("send_time between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(Date value1, Date value2) {
            addCriterion("send_time not between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIsNull() {
            addCriterion("expire_time is null");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIsNotNull() {
            addCriterion("expire_time is not null");
            return (Criteria) this;
        }

        public Criteria andExpireTimeEqualTo(Date value) {
            addCriterion("expire_time =", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotEqualTo(Date value) {
            addCriterion("expire_time <>", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeGreaterThan(Date value) {
            addCriterion("expire_time >", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("expire_time >=", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeLessThan(Date value) {
            addCriterion("expire_time <", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeLessThanOrEqualTo(Date value) {
            addCriterion("expire_time <=", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIn(List<Date> values) {
            addCriterion("expire_time in", values, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotIn(List<Date> values) {
            addCriterion("expire_time not in", values, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeBetween(Date value1, Date value2) {
            addCriterion("expire_time between", value1, value2, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotBetween(Date value1, Date value2) {
            addCriterion("expire_time not between", value1, value2, "expireTime");
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

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andAccessoryIsNull() {
            addCriterion("accessory is null");
            return (Criteria) this;
        }

        public Criteria andAccessoryIsNotNull() {
            addCriterion("accessory is not null");
            return (Criteria) this;
        }

        public Criteria andAccessoryEqualTo(String value) {
            addCriterion("accessory =", value, "accessory");
            return (Criteria) this;
        }

        public Criteria andAccessoryNotEqualTo(String value) {
            addCriterion("accessory <>", value, "accessory");
            return (Criteria) this;
        }

        public Criteria andAccessoryGreaterThan(String value) {
            addCriterion("accessory >", value, "accessory");
            return (Criteria) this;
        }

        public Criteria andAccessoryGreaterThanOrEqualTo(String value) {
            addCriterion("accessory >=", value, "accessory");
            return (Criteria) this;
        }

        public Criteria andAccessoryLessThan(String value) {
            addCriterion("accessory <", value, "accessory");
            return (Criteria) this;
        }

        public Criteria andAccessoryLessThanOrEqualTo(String value) {
            addCriterion("accessory <=", value, "accessory");
            return (Criteria) this;
        }

        public Criteria andAccessoryLike(String value) {
            addCriterion("accessory like", value, "accessory");
            return (Criteria) this;
        }

        public Criteria andAccessoryNotLike(String value) {
            addCriterion("accessory not like", value, "accessory");
            return (Criteria) this;
        }

        public Criteria andAccessoryIn(List<String> values) {
            addCriterion("accessory in", values, "accessory");
            return (Criteria) this;
        }

        public Criteria andAccessoryNotIn(List<String> values) {
            addCriterion("accessory not in", values, "accessory");
            return (Criteria) this;
        }

        public Criteria andAccessoryBetween(String value1, String value2) {
            addCriterion("accessory between", value1, value2, "accessory");
            return (Criteria) this;
        }

        public Criteria andAccessoryNotBetween(String value1, String value2) {
            addCriterion("accessory not between", value1, value2, "accessory");
            return (Criteria) this;
        }

        public Criteria andReadIsNull() {
            addCriterion("read_ is null");
            return (Criteria) this;
        }

        public Criteria andReadIsNotNull() {
            addCriterion("read_ is not null");
            return (Criteria) this;
        }

        public Criteria andReadEqualTo(Integer value) {
            addCriterion("read_ =", value, "read");
            return (Criteria) this;
        }

        public Criteria andReadNotEqualTo(Integer value) {
            addCriterion("read_ <>", value, "read");
            return (Criteria) this;
        }

        public Criteria andReadGreaterThan(Integer value) {
            addCriterion("read_ >", value, "read");
            return (Criteria) this;
        }

        public Criteria andReadGreaterThanOrEqualTo(Integer value) {
            addCriterion("read_ >=", value, "read");
            return (Criteria) this;
        }

        public Criteria andReadLessThan(Integer value) {
            addCriterion("read_ <", value, "read");
            return (Criteria) this;
        }

        public Criteria andReadLessThanOrEqualTo(Integer value) {
            addCriterion("read_ <=", value, "read");
            return (Criteria) this;
        }

        public Criteria andReadIn(List<Integer> values) {
            addCriterion("read_ in", values, "read");
            return (Criteria) this;
        }

        public Criteria andReadNotIn(List<Integer> values) {
            addCriterion("read_ not in", values, "read");
            return (Criteria) this;
        }

        public Criteria andReadBetween(Integer value1, Integer value2) {
            addCriterion("read_ between", value1, value2, "read");
            return (Criteria) this;
        }

        public Criteria andReadNotBetween(Integer value1, Integer value2) {
            addCriterion("read_ not between", value1, value2, "read");
            return (Criteria) this;
        }

        public Criteria andReceiveIsNull() {
            addCriterion("receive is null");
            return (Criteria) this;
        }

        public Criteria andReceiveIsNotNull() {
            addCriterion("receive is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveEqualTo(Integer value) {
            addCriterion("receive =", value, "receive");
            return (Criteria) this;
        }

        public Criteria andReceiveNotEqualTo(Integer value) {
            addCriterion("receive <>", value, "receive");
            return (Criteria) this;
        }

        public Criteria andReceiveGreaterThan(Integer value) {
            addCriterion("receive >", value, "receive");
            return (Criteria) this;
        }

        public Criteria andReceiveGreaterThanOrEqualTo(Integer value) {
            addCriterion("receive >=", value, "receive");
            return (Criteria) this;
        }

        public Criteria andReceiveLessThan(Integer value) {
            addCriterion("receive <", value, "receive");
            return (Criteria) this;
        }

        public Criteria andReceiveLessThanOrEqualTo(Integer value) {
            addCriterion("receive <=", value, "receive");
            return (Criteria) this;
        }

        public Criteria andReceiveIn(List<Integer> values) {
            addCriterion("receive in", values, "receive");
            return (Criteria) this;
        }

        public Criteria andReceiveNotIn(List<Integer> values) {
            addCriterion("receive not in", values, "receive");
            return (Criteria) this;
        }

        public Criteria andReceiveBetween(Integer value1, Integer value2) {
            addCriterion("receive between", value1, value2, "receive");
            return (Criteria) this;
        }

        public Criteria andReceiveNotBetween(Integer value1, Integer value2) {
            addCriterion("receive not between", value1, value2, "receive");
            return (Criteria) this;
        }

        public Criteria andSwiftAddressIsNull() {
            addCriterion("swift_address is null");
            return (Criteria) this;
        }

        public Criteria andSwiftAddressIsNotNull() {
            addCriterion("swift_address is not null");
            return (Criteria) this;
        }

        public Criteria andSwiftAddressEqualTo(Integer value) {
            addCriterion("swift_address =", value, "swiftAddress");
            return (Criteria) this;
        }

        public Criteria andSwiftAddressNotEqualTo(Integer value) {
            addCriterion("swift_address <>", value, "swiftAddress");
            return (Criteria) this;
        }

        public Criteria andSwiftAddressGreaterThan(Integer value) {
            addCriterion("swift_address >", value, "swiftAddress");
            return (Criteria) this;
        }

        public Criteria andSwiftAddressGreaterThanOrEqualTo(Integer value) {
            addCriterion("swift_address >=", value, "swiftAddress");
            return (Criteria) this;
        }

        public Criteria andSwiftAddressLessThan(Integer value) {
            addCriterion("swift_address <", value, "swiftAddress");
            return (Criteria) this;
        }

        public Criteria andSwiftAddressLessThanOrEqualTo(Integer value) {
            addCriterion("swift_address <=", value, "swiftAddress");
            return (Criteria) this;
        }

        public Criteria andSwiftAddressIn(List<Integer> values) {
            addCriterion("swift_address in", values, "swiftAddress");
            return (Criteria) this;
        }

        public Criteria andSwiftAddressNotIn(List<Integer> values) {
            addCriterion("swift_address not in", values, "swiftAddress");
            return (Criteria) this;
        }

        public Criteria andSwiftAddressBetween(Integer value1, Integer value2) {
            addCriterion("swift_address between", value1, value2, "swiftAddress");
            return (Criteria) this;
        }

        public Criteria andSwiftAddressNotBetween(Integer value1, Integer value2) {
            addCriterion("swift_address not between", value1, value2, "swiftAddress");
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