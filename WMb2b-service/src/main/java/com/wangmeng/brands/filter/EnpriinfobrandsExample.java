package com.wangmeng.brands.filter;

import java.util.ArrayList;
import java.util.List;

import com.wangmeng.query.ABaseDaoFilter;

/**
 *  企业品牌表  mapper 操作过滤器
 *   对应表： 
 *    wm_enpriinfobrands_t
 *
 * @mbggenerated
 */
public class EnpriinfobrandsExample extends ABaseDaoFilter<EnpriinfobrandsExample.Criteria> { 

    /**
     * orderByClause
     * 对应表： 
     *   wm_enpriinfobrands_t
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * distinct
     * 对应表： 
     *   wm_enpriinfobrands_t
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * oredCriteria
     * 对应表： 
     *   wm_enpriinfobrands_t
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     *  对应表:  wm_enpriinfobrands_t
     *
     * @mbggenerated
     */
    public EnpriinfobrandsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public Criteria getScalarExistedCriteria() {
        if(oredCriteria!=null && oredCriteria.size()>0){
            	return oredCriteria.get(0);
        }
        return or();
    }

    /**
     *  对应表:  wm_enpriinfobrands_t
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     *  对应表:  wm_enpriinfobrands_t
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     *  对应表:  wm_enpriinfobrands_t
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     *  对应表:  wm_enpriinfobrands_t
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     *  对应表:  wm_enpriinfobrands_t
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *  对应表:  wm_enpriinfobrands_t
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *  对应表:  wm_enpriinfobrands_t
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *  对应表:  wm_enpriinfobrands_t
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
     *  对应表:  wm_enpriinfobrands_t
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *  对应表:  wm_enpriinfobrands_t
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     *  企业品牌表
     *   对应表： 
     *    wm_enpriinfobrands_t
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

        public Criteria andBrandIdIsNull() {
            addCriterion("BrandId is null");
            return (Criteria) this;
        }

        public Criteria andBrandIdIsNotNull() {
            addCriterion("BrandId is not null");
            return (Criteria) this;
        }

        public Criteria andBrandIdEqualTo(Long value) {
            addCriterion("BrandId =", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotEqualTo(Long value) {
            addCriterion("BrandId <>", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdGreaterThan(Long value) {
            addCriterion("BrandId >", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdGreaterThanOrEqualTo(Long value) {
            addCriterion("BrandId >=", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdLessThan(Long value) {
            addCriterion("BrandId <", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdLessThanOrEqualTo(Long value) {
            addCriterion("BrandId <=", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdIn(List<Long> values) {
            addCriterion("BrandId in", values, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotIn(List<Long> values) {
            addCriterion("BrandId not in", values, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdBetween(Long value1, Long value2) {
            addCriterion("BrandId between", value1, value2, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotBetween(Long value1, Long value2) {
            addCriterion("BrandId not between", value1, value2, "brandId");
            return (Criteria) this;
        }

        public Criteria andEnterPrInfoIdIsNull() {
            addCriterion("EnterPrInfoId is null");
            return (Criteria) this;
        }

        public Criteria andEnterPrInfoIdIsNotNull() {
            addCriterion("EnterPrInfoId is not null");
            return (Criteria) this;
        }

        public Criteria andEnterPrInfoIdEqualTo(Long value) {
            addCriterion("EnterPrInfoId =", value, "enterPrInfoId");
            return (Criteria) this;
        }

        public Criteria andEnterPrInfoIdNotEqualTo(Long value) {
            addCriterion("EnterPrInfoId <>", value, "enterPrInfoId");
            return (Criteria) this;
        }

        public Criteria andEnterPrInfoIdGreaterThan(Long value) {
            addCriterion("EnterPrInfoId >", value, "enterPrInfoId");
            return (Criteria) this;
        }

        public Criteria andEnterPrInfoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("EnterPrInfoId >=", value, "enterPrInfoId");
            return (Criteria) this;
        }

        public Criteria andEnterPrInfoIdLessThan(Long value) {
            addCriterion("EnterPrInfoId <", value, "enterPrInfoId");
            return (Criteria) this;
        }

        public Criteria andEnterPrInfoIdLessThanOrEqualTo(Long value) {
            addCriterion("EnterPrInfoId <=", value, "enterPrInfoId");
            return (Criteria) this;
        }

        public Criteria andEnterPrInfoIdIn(List<Long> values) {
            addCriterion("EnterPrInfoId in", values, "enterPrInfoId");
            return (Criteria) this;
        }

        public Criteria andEnterPrInfoIdNotIn(List<Long> values) {
            addCriterion("EnterPrInfoId not in", values, "enterPrInfoId");
            return (Criteria) this;
        }

        public Criteria andEnterPrInfoIdBetween(Long value1, Long value2) {
            addCriterion("EnterPrInfoId between", value1, value2, "enterPrInfoId");
            return (Criteria) this;
        }

        public Criteria andEnterPrInfoIdNotBetween(Long value1, Long value2) {
            addCriterion("EnterPrInfoId not between", value1, value2, "enterPrInfoId");
            return (Criteria) this;
        }
    }

    /**
     * 企业品牌表
     *  domain 对应表：wm_enpriinfobrands_t
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria { 


        protected Criteria() {
            super();
        }
    }

    /**
     *  企业品牌表
     *   对应表： 
     *    wm_enpriinfobrands_t
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