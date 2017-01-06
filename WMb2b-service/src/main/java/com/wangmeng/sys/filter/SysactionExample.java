package com.wangmeng.sys.filter;

import java.util.ArrayList;
import java.util.List;

import com.wangmeng.query.ABaseDaoFilter;

/**
 *  功能操作权限表  mapper 操作过滤器
 *   对应表： 
 *    wm_sysaction_t
 *
 * @mbggenerated
 */
public class SysactionExample extends ABaseDaoFilter<SysactionExample.Criteria> { 
 

    /**
     * oredCriteria
     * 对应表： 
     *   wm_sysaction_t
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     *  对应表:  wm_sysaction_t
     *
     * @mbggenerated
     */
    public SysactionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public Criteria getScalarExistedCriteria() {
        if(oredCriteria!=null && oredCriteria.size()>0){
            	return oredCriteria.get(0);
        }
        return or();
    }

    /**
     *  对应表:  wm_sysaction_t
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *  对应表:  wm_sysaction_t
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *  对应表:  wm_sysaction_t
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *  对应表:  wm_sysaction_t
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
     *  对应表:  wm_sysaction_t
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *  对应表:  wm_sysaction_t
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     *  功能操作权限表
     *   对应表： 
     *    wm_sysaction_t
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

        public Criteria andPerCodeIsNull() {
            addCriterion("PerCode is null");
            return (Criteria) this;
        }

        public Criteria andPerCodeIsNotNull() {
            addCriterion("PerCode is not null");
            return (Criteria) this;
        }

        public Criteria andPerCodeEqualTo(String value) {
            addCriterion("PerCode =", value, "perCode");
            return (Criteria) this;
        }

        public Criteria andPerCodeNotEqualTo(String value) {
            addCriterion("PerCode <>", value, "perCode");
            return (Criteria) this;
        }

        public Criteria andPerCodeGreaterThan(String value) {
            addCriterion("PerCode >", value, "perCode");
            return (Criteria) this;
        }

        public Criteria andPerCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PerCode >=", value, "perCode");
            return (Criteria) this;
        }

        public Criteria andPerCodeLessThan(String value) {
            addCriterion("PerCode <", value, "perCode");
            return (Criteria) this;
        }

        public Criteria andPerCodeLessThanOrEqualTo(String value) {
            addCriterion("PerCode <=", value, "perCode");
            return (Criteria) this;
        }

        public Criteria andPerCodeLike(String value) {
            addCriterion("PerCode like", value, "perCode");
            return (Criteria) this;
        }

        public Criteria andPerCodeNotLike(String value) {
            addCriterion("PerCode not like", value, "perCode");
            return (Criteria) this;
        }

        public Criteria andPerCodeIn(List<String> values) {
            addCriterion("PerCode in", values, "perCode");
            return (Criteria) this;
        }

        public Criteria andPerCodeNotIn(List<String> values) {
            addCriterion("PerCode not in", values, "perCode");
            return (Criteria) this;
        }

        public Criteria andPerCodeBetween(String value1, String value2) {
            addCriterion("PerCode between", value1, value2, "perCode");
            return (Criteria) this;
        }

        public Criteria andPerCodeNotBetween(String value1, String value2) {
            addCriterion("PerCode not between", value1, value2, "perCode");
            return (Criteria) this;
        }

        public Criteria andDisplayNameIsNull() {
            addCriterion("DisplayName is null");
            return (Criteria) this;
        }

        public Criteria andDisplayNameIsNotNull() {
            addCriterion("DisplayName is not null");
            return (Criteria) this;
        }

        public Criteria andDisplayNameEqualTo(String value) {
            addCriterion("DisplayName =", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameNotEqualTo(String value) {
            addCriterion("DisplayName <>", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameGreaterThan(String value) {
            addCriterion("DisplayName >", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameGreaterThanOrEqualTo(String value) {
            addCriterion("DisplayName >=", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameLessThan(String value) {
            addCriterion("DisplayName <", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameLessThanOrEqualTo(String value) {
            addCriterion("DisplayName <=", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameLike(String value) {
            addCriterion("DisplayName like", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameNotLike(String value) {
            addCriterion("DisplayName not like", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameIn(List<String> values) {
            addCriterion("DisplayName in", values, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameNotIn(List<String> values) {
            addCriterion("DisplayName not in", values, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameBetween(String value1, String value2) {
            addCriterion("DisplayName between", value1, value2, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameNotBetween(String value1, String value2) {
            addCriterion("DisplayName not between", value1, value2, "displayName");
            return (Criteria) this;
        }

        public Criteria andActionNameIsNull() {
            addCriterion("ActionName is null");
            return (Criteria) this;
        }

        public Criteria andActionNameIsNotNull() {
            addCriterion("ActionName is not null");
            return (Criteria) this;
        }

        public Criteria andActionNameEqualTo(String value) {
            addCriterion("ActionName =", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameNotEqualTo(String value) {
            addCriterion("ActionName <>", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameGreaterThan(String value) {
            addCriterion("ActionName >", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameGreaterThanOrEqualTo(String value) {
            addCriterion("ActionName >=", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameLessThan(String value) {
            addCriterion("ActionName <", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameLessThanOrEqualTo(String value) {
            addCriterion("ActionName <=", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameLike(String value) {
            addCriterion("ActionName like", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameNotLike(String value) {
            addCriterion("ActionName not like", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameIn(List<String> values) {
            addCriterion("ActionName in", values, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameNotIn(List<String> values) {
            addCriterion("ActionName not in", values, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameBetween(String value1, String value2) {
            addCriterion("ActionName between", value1, value2, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameNotBetween(String value1, String value2) {
            addCriterion("ActionName not between", value1, value2, "actionName");
            return (Criteria) this;
        }

        public Criteria andIsContrallorIsNull() {
            addCriterion("IsContrallor is null");
            return (Criteria) this;
        }

        public Criteria andIsContrallorIsNotNull() {
            addCriterion("IsContrallor is not null");
            return (Criteria) this;
        }

        public Criteria andIsContrallorEqualTo(Integer value) {
            addCriterion("IsContrallor =", value, "isContrallor");
            return (Criteria) this;
        }

        public Criteria andIsContrallorNotEqualTo(Integer value) {
            addCriterion("IsContrallor <>", value, "isContrallor");
            return (Criteria) this;
        }

        public Criteria andIsContrallorGreaterThan(Integer value) {
            addCriterion("IsContrallor >", value, "isContrallor");
            return (Criteria) this;
        }

        public Criteria andIsContrallorGreaterThanOrEqualTo(Integer value) {
            addCriterion("IsContrallor >=", value, "isContrallor");
            return (Criteria) this;
        }

        public Criteria andIsContrallorLessThan(Integer value) {
            addCriterion("IsContrallor <", value, "isContrallor");
            return (Criteria) this;
        }

        public Criteria andIsContrallorLessThanOrEqualTo(Integer value) {
            addCriterion("IsContrallor <=", value, "isContrallor");
            return (Criteria) this;
        }

        public Criteria andIsContrallorIn(List<Integer> values) {
            addCriterion("IsContrallor in", values, "isContrallor");
            return (Criteria) this;
        }

        public Criteria andIsContrallorNotIn(List<Integer> values) {
            addCriterion("IsContrallor not in", values, "isContrallor");
            return (Criteria) this;
        }

        public Criteria andIsContrallorBetween(Integer value1, Integer value2) {
            addCriterion("IsContrallor between", value1, value2, "isContrallor");
            return (Criteria) this;
        }

        public Criteria andIsContrallorNotBetween(Integer value1, Integer value2) {
            addCriterion("IsContrallor not between", value1, value2, "isContrallor");
            return (Criteria) this;
        }
    }

    /**
     * 功能操作权限表
     *  domain 对应表：wm_sysaction_t
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria { 


        protected Criteria() {
            super();
        }
    }

    /**
     *  功能操作权限表
     *   对应表： 
     *    wm_sysaction_t
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