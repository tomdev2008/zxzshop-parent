package com.wangmeng.sys.filter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wangmeng.query.ABaseDaoFilter;

/**
 *  操作日志说明  mapper 操作过滤器
 *   对应表： 
 *    wm_oprationlog_t
 *
 * @mbggenerated
 */
public class OprationlogExample extends ABaseDaoFilter<OprationlogExample.Criteria> { 
 
    /**
     * oredCriteria
     * 对应表： 
     *   wm_oprationlog_t
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     *  对应表:  wm_oprationlog_t
     *
     * @mbggenerated
     */
    public OprationlogExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public Criteria getScalarExistedCriteria() {
        if(oredCriteria!=null && oredCriteria.size()>0){
            	return oredCriteria.get(0);
        }
        return or();
    }
 
    /**
     *  对应表:  wm_oprationlog_t
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *  对应表:  wm_oprationlog_t
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *  对应表:  wm_oprationlog_t
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *  对应表:  wm_oprationlog_t
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     *  对应表:  wm_oprationlog_t
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *  对应表:  wm_oprationlog_t
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     *  操作日志说明
     *   对应表： 
     *    wm_oprationlog_t
     *
     * @mbggenerated
     */
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

        public void addNativeCriterion(String condition) {
            addCriterion(condition);
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

        protected void addCriterionForJDBCTimestamp(String condition, Timestamp value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, value, property);
        }

        protected void addCriterionForJDBCTimestamp(String condition, List<Timestamp> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Timestamp> timestampList = new ArrayList<java.sql.Timestamp>();
            Iterator<java.sql.Timestamp> iter = values.iterator();
            while (iter.hasNext()) {
                timestampList.add(iter.next());
            }
            addCriterion(condition, timestampList, property);
        }

        protected void addCriterionForJDBCTimestamp(String condition, Timestamp value1, Timestamp value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, value1, value2, property);
        }

        public Criteria andIdIsNull() {
            addCriterion("Id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("Id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("Id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("Id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("Id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("Id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("Id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("Id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("Id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("Id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("Id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("Id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("UserId is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("UserId is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("UserId =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("UserId <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("UserId >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("UserId >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("UserId <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("UserId <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("UserId in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("UserId not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("UserId between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("UserId not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("UserName is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("UserName is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("UserName =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("UserName <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("UserName >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("UserName >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("UserName <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("UserName <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("UserName like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("UserName not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("UserName in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("UserName not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("UserName between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("UserName not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andPageNameIsNull() {
            addCriterion("PageName is null");
            return (Criteria) this;
        }

        public Criteria andPageNameIsNotNull() {
            addCriterion("PageName is not null");
            return (Criteria) this;
        }

        public Criteria andPageNameEqualTo(String value) {
            addCriterion("PageName =", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameNotEqualTo(String value) {
            addCriterion("PageName <>", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameGreaterThan(String value) {
            addCriterion("PageName >", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameGreaterThanOrEqualTo(String value) {
            addCriterion("PageName >=", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameLessThan(String value) {
            addCriterion("PageName <", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameLessThanOrEqualTo(String value) {
            addCriterion("PageName <=", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameLike(String value) {
            addCriterion("PageName like", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameNotLike(String value) {
            addCriterion("PageName not like", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameIn(List<String> values) {
            addCriterion("PageName in", values, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameNotIn(List<String> values) {
            addCriterion("PageName not in", values, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameBetween(String value1, String value2) {
            addCriterion("PageName between", value1, value2, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameNotBetween(String value1, String value2) {
            addCriterion("PageName not between", value1, value2, "pageName");
            return (Criteria) this;
        }

        public Criteria andActionIsNull() {
            addCriterion("Action is null");
            return (Criteria) this;
        }

        public Criteria andActionIsNotNull() {
            addCriterion("Action is not null");
            return (Criteria) this;
        }

        public Criteria andActionEqualTo(String value) {
            addCriterion("Action =", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotEqualTo(String value) {
            addCriterion("Action <>", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionGreaterThan(String value) {
            addCriterion("Action >", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionGreaterThanOrEqualTo(String value) {
            addCriterion("Action >=", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLessThan(String value) {
            addCriterion("Action <", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLessThanOrEqualTo(String value) {
            addCriterion("Action <=", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLike(String value) {
            addCriterion("Action like", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotLike(String value) {
            addCriterion("Action not like", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionIn(List<String> values) {
            addCriterion("Action in", values, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotIn(List<String> values) {
            addCriterion("Action not in", values, "action");
            return (Criteria) this;
        }

        public Criteria andActionBetween(String value1, String value2) {
            addCriterion("Action between", value1, value2, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotBetween(String value1, String value2) {
            addCriterion("Action not between", value1, value2, "action");
            return (Criteria) this;
        }

        public Criteria andActionDescriptIsNull() {
            addCriterion("ActionDescript is null");
            return (Criteria) this;
        }

        public Criteria andActionDescriptIsNotNull() {
            addCriterion("ActionDescript is not null");
            return (Criteria) this;
        }

        public Criteria andActionDescriptEqualTo(String value) {
            addCriterion("ActionDescript =", value, "actionDescript");
            return (Criteria) this;
        }

        public Criteria andActionDescriptNotEqualTo(String value) {
            addCriterion("ActionDescript <>", value, "actionDescript");
            return (Criteria) this;
        }

        public Criteria andActionDescriptGreaterThan(String value) {
            addCriterion("ActionDescript >", value, "actionDescript");
            return (Criteria) this;
        }

        public Criteria andActionDescriptGreaterThanOrEqualTo(String value) {
            addCriterion("ActionDescript >=", value, "actionDescript");
            return (Criteria) this;
        }

        public Criteria andActionDescriptLessThan(String value) {
            addCriterion("ActionDescript <", value, "actionDescript");
            return (Criteria) this;
        }

        public Criteria andActionDescriptLessThanOrEqualTo(String value) {
            addCriterion("ActionDescript <=", value, "actionDescript");
            return (Criteria) this;
        }

        public Criteria andActionDescriptLike(String value) {
            addCriterion("ActionDescript like", value, "actionDescript");
            return (Criteria) this;
        }

        public Criteria andActionDescriptNotLike(String value) {
            addCriterion("ActionDescript not like", value, "actionDescript");
            return (Criteria) this;
        }

        public Criteria andActionDescriptIn(List<String> values) {
            addCriterion("ActionDescript in", values, "actionDescript");
            return (Criteria) this;
        }

        public Criteria andActionDescriptNotIn(List<String> values) {
            addCriterion("ActionDescript not in", values, "actionDescript");
            return (Criteria) this;
        }

        public Criteria andActionDescriptBetween(String value1, String value2) {
            addCriterion("ActionDescript between", value1, value2, "actionDescript");
            return (Criteria) this;
        }

        public Criteria andActionDescriptNotBetween(String value1, String value2) {
            addCriterion("ActionDescript not between", value1, value2, "actionDescript");
            return (Criteria) this;
        }

        public Criteria andContentsIsNull() {
            addCriterion("Contents is null");
            return (Criteria) this;
        }

        public Criteria andContentsIsNotNull() {
            addCriterion("Contents is not null");
            return (Criteria) this;
        }

        public Criteria andContentsEqualTo(String value) {
            addCriterion("Contents =", value, "contents");
            return (Criteria) this;
        }

        public Criteria andContentsNotEqualTo(String value) {
            addCriterion("Contents <>", value, "contents");
            return (Criteria) this;
        }

        public Criteria andContentsGreaterThan(String value) {
            addCriterion("Contents >", value, "contents");
            return (Criteria) this;
        }

        public Criteria andContentsGreaterThanOrEqualTo(String value) {
            addCriterion("Contents >=", value, "contents");
            return (Criteria) this;
        }

        public Criteria andContentsLessThan(String value) {
            addCriterion("Contents <", value, "contents");
            return (Criteria) this;
        }

        public Criteria andContentsLessThanOrEqualTo(String value) {
            addCriterion("Contents <=", value, "contents");
            return (Criteria) this;
        }

        public Criteria andContentsLike(String value) {
            addCriterion("Contents like", value, "contents");
            return (Criteria) this;
        }

        public Criteria andContentsNotLike(String value) {
            addCriterion("Contents not like", value, "contents");
            return (Criteria) this;
        }

        public Criteria andContentsIn(List<String> values) {
            addCriterion("Contents in", values, "contents");
            return (Criteria) this;
        }

        public Criteria andContentsNotIn(List<String> values) {
            addCriterion("Contents not in", values, "contents");
            return (Criteria) this;
        }

        public Criteria andContentsBetween(String value1, String value2) {
            addCriterion("Contents between", value1, value2, "contents");
            return (Criteria) this;
        }

        public Criteria andContentsNotBetween(String value1, String value2) {
            addCriterion("Contents not between", value1, value2, "contents");
            return (Criteria) this;
        }

        public Criteria andOpereadateIsNull() {
            addCriterion("Opereadate is null");
            return (Criteria) this;
        }

        public Criteria andOpereadateIsNotNull() {
            addCriterion("Opereadate is not null");
            return (Criteria) this;
        }

        public Criteria andOpereadateEqualTo(Timestamp value) {
            addCriterion("Opereadate =", value, "opereadate");
            return (Criteria) this;
        }

        public Criteria andOpereadateNotEqualTo(Timestamp value) {
            addCriterion("Opereadate <>", value, "opereadate");
            return (Criteria) this;
        }

        public Criteria andOpereadateGreaterThan(Timestamp value) {
            addCriterion("Opereadate >", value, "opereadate");
            return (Criteria) this;
        }

        public Criteria andOpereadateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("Opereadate >=", value, "opereadate");
            return (Criteria) this;
        }

        public Criteria andOpereadateLessThan(Timestamp value) {
            addCriterion("Opereadate <", value, "opereadate");
            return (Criteria) this;
        }

        public Criteria andOpereadateLessThanOrEqualTo(Timestamp value) {
            addCriterion("Opereadate <=", value, "opereadate");
            return (Criteria) this;
        }

        public Criteria andOpereadateIn(List<Timestamp> values) {
            addCriterionForJDBCTimestamp("Opereadate in", values, "opereadate");
            return (Criteria) this;
        }

        public Criteria andOpereadateNotIn(List<Timestamp> values) {
            addCriterionForJDBCTimestamp("Opereadate not in", values, "opereadate");
            return (Criteria) this;
        }

        public Criteria andOpereadateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("Opereadate between", value1, value2, "opereadate");
            return (Criteria) this;
        }

        public Criteria andOpereadateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("Opereadate not between", value1, value2, "opereadate");
            return (Criteria) this;
        }

        public Criteria andIPAddressIsNull() {
            addCriterion("IPAddress is null");
            return (Criteria) this;
        }

        public Criteria andIPAddressIsNotNull() {
            addCriterion("IPAddress is not null");
            return (Criteria) this;
        }

        public Criteria andIPAddressEqualTo(String value) {
            addCriterion("IPAddress =", value, "iPAddress");
            return (Criteria) this;
        }

        public Criteria andIPAddressNotEqualTo(String value) {
            addCriterion("IPAddress <>", value, "iPAddress");
            return (Criteria) this;
        }

        public Criteria andIPAddressGreaterThan(String value) {
            addCriterion("IPAddress >", value, "iPAddress");
            return (Criteria) this;
        }

        public Criteria andIPAddressGreaterThanOrEqualTo(String value) {
            addCriterion("IPAddress >=", value, "iPAddress");
            return (Criteria) this;
        }

        public Criteria andIPAddressLessThan(String value) {
            addCriterion("IPAddress <", value, "iPAddress");
            return (Criteria) this;
        }

        public Criteria andIPAddressLessThanOrEqualTo(String value) {
            addCriterion("IPAddress <=", value, "iPAddress");
            return (Criteria) this;
        }

        public Criteria andIPAddressLike(String value) {
            addCriterion("IPAddress like", value, "iPAddress");
            return (Criteria) this;
        }

        public Criteria andIPAddressNotLike(String value) {
            addCriterion("IPAddress not like", value, "iPAddress");
            return (Criteria) this;
        }

        public Criteria andIPAddressIn(List<String> values) {
            addCriterion("IPAddress in", values, "iPAddress");
            return (Criteria) this;
        }

        public Criteria andIPAddressNotIn(List<String> values) {
            addCriterion("IPAddress not in", values, "iPAddress");
            return (Criteria) this;
        }

        public Criteria andIPAddressBetween(String value1, String value2) {
            addCriterion("IPAddress between", value1, value2, "iPAddress");
            return (Criteria) this;
        }

        public Criteria andIPAddressNotBetween(String value1, String value2) {
            addCriterion("IPAddress not between", value1, value2, "iPAddress");
            return (Criteria) this;
        }
    }

    /**
     * 操作日志说明
     *  domain 对应表：wm_oprationlog_t
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria { 


        protected Criteria() {
            super();
        }
    }

    /**
     *  操作日志说明
     *   对应表： 
     *    wm_oprationlog_t
     *
     * @mbggenerated
     */
    public static class Criterion implements java.io.Serializable {  

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