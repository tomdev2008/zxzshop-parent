package com.wangmeng.brands.filter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wangmeng.query.ABaseDaoFilter;

/**
 *  品牌申请表  mapper 操作过滤器
 *   对应表： 
 *    wm_brandsapply_t
 *
 * @mbggenerated
 */
public class BrandsapplyExample extends ABaseDaoFilter<BrandsapplyExample.Criteria> { 

    /**
     * orderByClause
     * 对应表： 
     *   wm_brandsapply_t
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * distinct
     * 对应表： 
     *   wm_brandsapply_t
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * oredCriteria
     * 对应表： 
     *   wm_brandsapply_t
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     *  对应表:  wm_brandsapply_t
     *
     * @mbggenerated
     */
    public BrandsapplyExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public Criteria getScalarExistedCriteria() {
        if(oredCriteria!=null && oredCriteria.size()>0){
            	return oredCriteria.get(0);
        }
        return or();
    }

    /**
     *  对应表:  wm_brandsapply_t
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     *  对应表:  wm_brandsapply_t
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     *  对应表:  wm_brandsapply_t
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     *  对应表:  wm_brandsapply_t
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     *  对应表:  wm_brandsapply_t
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *  对应表:  wm_brandsapply_t
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *  对应表:  wm_brandsapply_t
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *  对应表:  wm_brandsapply_t
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
     *  对应表:  wm_brandsapply_t
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *  对应表:  wm_brandsapply_t
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     *  品牌申请表
     *   对应表： 
     *    wm_brandsapply_t
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

        public Criteria andUserIdIsNull() {
            addCriterion("UserId is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("UserId is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("UserId =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("UserId <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("UserId >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("UserId >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("UserId <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("UserId <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("UserId in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("UserId not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("UserId between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("UserId not between", value1, value2, "userId");
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

        public Criteria andBrandNameIsNull() {
            addCriterion("BrandName is null");
            return (Criteria) this;
        }

        public Criteria andBrandNameIsNotNull() {
            addCriterion("BrandName is not null");
            return (Criteria) this;
        }

        public Criteria andBrandNameEqualTo(String value) {
            addCriterion("BrandName =", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotEqualTo(String value) {
            addCriterion("BrandName <>", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameGreaterThan(String value) {
            addCriterion("BrandName >", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameGreaterThanOrEqualTo(String value) {
            addCriterion("BrandName >=", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameLessThan(String value) {
            addCriterion("BrandName <", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameLessThanOrEqualTo(String value) {
            addCriterion("BrandName <=", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameLike(String value) {
            addCriterion("BrandName like", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotLike(String value) {
            addCriterion("BrandName not like", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameIn(List<String> values) {
            addCriterion("BrandName in", values, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotIn(List<String> values) {
            addCriterion("BrandName not in", values, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameBetween(String value1, String value2) {
            addCriterion("BrandName between", value1, value2, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotBetween(String value1, String value2) {
            addCriterion("BrandName not between", value1, value2, "brandName");
            return (Criteria) this;
        }

        public Criteria andLogoIsNull() {
            addCriterion("Logo is null");
            return (Criteria) this;
        }

        public Criteria andLogoIsNotNull() {
            addCriterion("Logo is not null");
            return (Criteria) this;
        }

        public Criteria andLogoEqualTo(String value) {
            addCriterion("Logo =", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotEqualTo(String value) {
            addCriterion("Logo <>", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThan(String value) {
            addCriterion("Logo >", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThanOrEqualTo(String value) {
            addCriterion("Logo >=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThan(String value) {
            addCriterion("Logo <", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThanOrEqualTo(String value) {
            addCriterion("Logo <=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLike(String value) {
            addCriterion("Logo like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotLike(String value) {
            addCriterion("Logo not like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoIn(List<String> values) {
            addCriterion("Logo in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotIn(List<String> values) {
            addCriterion("Logo not in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoBetween(String value1, String value2) {
            addCriterion("Logo between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotBetween(String value1, String value2) {
            addCriterion("Logo not between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("Description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("Description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("Description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("Description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("Description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("Description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("Description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("Description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("Description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("Description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("Description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("Description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("Description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("Description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andAuthCertificateIsNull() {
            addCriterion("AuthCertificate is null");
            return (Criteria) this;
        }

        public Criteria andAuthCertificateIsNotNull() {
            addCriterion("AuthCertificate is not null");
            return (Criteria) this;
        }

        public Criteria andAuthCertificateEqualTo(String value) {
            addCriterion("AuthCertificate =", value, "authCertificate");
            return (Criteria) this;
        }

        public Criteria andAuthCertificateNotEqualTo(String value) {
            addCriterion("AuthCertificate <>", value, "authCertificate");
            return (Criteria) this;
        }

        public Criteria andAuthCertificateGreaterThan(String value) {
            addCriterion("AuthCertificate >", value, "authCertificate");
            return (Criteria) this;
        }

        public Criteria andAuthCertificateGreaterThanOrEqualTo(String value) {
            addCriterion("AuthCertificate >=", value, "authCertificate");
            return (Criteria) this;
        }

        public Criteria andAuthCertificateLessThan(String value) {
            addCriterion("AuthCertificate <", value, "authCertificate");
            return (Criteria) this;
        }

        public Criteria andAuthCertificateLessThanOrEqualTo(String value) {
            addCriterion("AuthCertificate <=", value, "authCertificate");
            return (Criteria) this;
        }

        public Criteria andAuthCertificateLike(String value) {
            addCriterion("AuthCertificate like", value, "authCertificate");
            return (Criteria) this;
        }

        public Criteria andAuthCertificateNotLike(String value) {
            addCriterion("AuthCertificate not like", value, "authCertificate");
            return (Criteria) this;
        }

        public Criteria andAuthCertificateIn(List<String> values) {
            addCriterion("AuthCertificate in", values, "authCertificate");
            return (Criteria) this;
        }

        public Criteria andAuthCertificateNotIn(List<String> values) {
            addCriterion("AuthCertificate not in", values, "authCertificate");
            return (Criteria) this;
        }

        public Criteria andAuthCertificateBetween(String value1, String value2) {
            addCriterion("AuthCertificate between", value1, value2, "authCertificate");
            return (Criteria) this;
        }

        public Criteria andAuthCertificateNotBetween(String value1, String value2) {
            addCriterion("AuthCertificate not between", value1, value2, "authCertificate");
            return (Criteria) this;
        }

        public Criteria andApplyModeIsNull() {
            addCriterion("ApplyMode is null");
            return (Criteria) this;
        }

        public Criteria andApplyModeIsNotNull() {
            addCriterion("ApplyMode is not null");
            return (Criteria) this;
        }

        public Criteria andApplyModeEqualTo(Integer value) {
            addCriterion("ApplyMode =", value, "applyMode");
            return (Criteria) this;
        }

        public Criteria andApplyModeNotEqualTo(Integer value) {
            addCriterion("ApplyMode <>", value, "applyMode");
            return (Criteria) this;
        }

        public Criteria andApplyModeGreaterThan(Integer value) {
            addCriterion("ApplyMode >", value, "applyMode");
            return (Criteria) this;
        }

        public Criteria andApplyModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ApplyMode >=", value, "applyMode");
            return (Criteria) this;
        }

        public Criteria andApplyModeLessThan(Integer value) {
            addCriterion("ApplyMode <", value, "applyMode");
            return (Criteria) this;
        }

        public Criteria andApplyModeLessThanOrEqualTo(Integer value) {
            addCriterion("ApplyMode <=", value, "applyMode");
            return (Criteria) this;
        }

        public Criteria andApplyModeIn(List<Integer> values) {
            addCriterion("ApplyMode in", values, "applyMode");
            return (Criteria) this;
        }

        public Criteria andApplyModeNotIn(List<Integer> values) {
            addCriterion("ApplyMode not in", values, "applyMode");
            return (Criteria) this;
        }

        public Criteria andApplyModeBetween(Integer value1, Integer value2) {
            addCriterion("ApplyMode between", value1, value2, "applyMode");
            return (Criteria) this;
        }

        public Criteria andApplyModeNotBetween(Integer value1, Integer value2) {
            addCriterion("ApplyMode not between", value1, value2, "applyMode");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("Remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("Remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("Remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("Remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("Remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("Remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("Remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("Remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("Remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("Remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("Remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("Remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("Remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("Remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andApplyDateIsNull() {
            addCriterion("ApplyDate is null");
            return (Criteria) this;
        }

        public Criteria andApplyDateIsNotNull() {
            addCriterion("ApplyDate is not null");
            return (Criteria) this;
        }

        public Criteria andApplyDateEqualTo(Timestamp value) {
            addCriterion("ApplyDate =", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotEqualTo(Timestamp value) {
            addCriterion("ApplyDate <>", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateGreaterThan(Timestamp value) {
            addCriterion("ApplyDate >", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("ApplyDate >=", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLessThan(Timestamp value) {
            addCriterion("ApplyDate <", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("ApplyDate <=", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateIn(List<Timestamp> values) {
            addCriterionForJDBCTimestamp("ApplyDate in", values, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotIn(List<Timestamp> values) {
            addCriterionForJDBCTimestamp("ApplyDate not in", values, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("ApplyDate between", value1, value2, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("ApplyDate not between", value1, value2, "applyDate");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsIsNull() {
            addCriterion("CategoryIds is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsIsNotNull() {
            addCriterion("CategoryIds is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsEqualTo(String value) {
            addCriterion("CategoryIds =", value, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsNotEqualTo(String value) {
            addCriterion("CategoryIds <>", value, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsGreaterThan(String value) {
            addCriterion("CategoryIds >", value, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsGreaterThanOrEqualTo(String value) {
            addCriterion("CategoryIds >=", value, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsLessThan(String value) {
            addCriterion("CategoryIds <", value, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsLessThanOrEqualTo(String value) {
            addCriterion("CategoryIds <=", value, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsLike(String value) {
            addCriterion("CategoryIds like", value, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsNotLike(String value) {
            addCriterion("CategoryIds not like", value, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsIn(List<String> values) {
            addCriterion("CategoryIds in", values, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsNotIn(List<String> values) {
            addCriterion("CategoryIds not in", values, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsBetween(String value1, String value2) {
            addCriterion("CategoryIds between", value1, value2, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsNotBetween(String value1, String value2) {
            addCriterion("CategoryIds not between", value1, value2, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNull() {
            addCriterion("AuditStatus is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNotNull() {
            addCriterion("AuditStatus is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusEqualTo(Integer value) {
            addCriterion("AuditStatus =", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotEqualTo(Integer value) {
            addCriterion("AuditStatus <>", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThan(Integer value) {
            addCriterion("AuditStatus >", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("AuditStatus >=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThan(Integer value) {
            addCriterion("AuditStatus <", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThanOrEqualTo(Integer value) {
            addCriterion("AuditStatus <=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIn(List<Integer> values) {
            addCriterion("AuditStatus in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotIn(List<Integer> values) {
            addCriterion("AuditStatus not in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusBetween(Integer value1, Integer value2) {
            addCriterion("AuditStatus between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("AuditStatus not between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonIsNull() {
            addCriterion("RefuseReason is null");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonIsNotNull() {
            addCriterion("RefuseReason is not null");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonEqualTo(String value) {
            addCriterion("RefuseReason =", value, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonNotEqualTo(String value) {
            addCriterion("RefuseReason <>", value, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonGreaterThan(String value) {
            addCriterion("RefuseReason >", value, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonGreaterThanOrEqualTo(String value) {
            addCriterion("RefuseReason >=", value, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonLessThan(String value) {
            addCriterion("RefuseReason <", value, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonLessThanOrEqualTo(String value) {
            addCriterion("RefuseReason <=", value, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonLike(String value) {
            addCriterion("RefuseReason like", value, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonNotLike(String value) {
            addCriterion("RefuseReason not like", value, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonIn(List<String> values) {
            addCriterion("RefuseReason in", values, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonNotIn(List<String> values) {
            addCriterion("RefuseReason not in", values, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonBetween(String value1, String value2) {
            addCriterion("RefuseReason between", value1, value2, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonNotBetween(String value1, String value2) {
            addCriterion("RefuseReason not between", value1, value2, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andCategoryNamesIsNull() {
            addCriterion("CategoryNames is null");
            return (Criteria) this;
        }

        public Criteria andCategoryNamesIsNotNull() {
            addCriterion("CategoryNames is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryNamesEqualTo(String value) {
            addCriterion("CategoryNames =", value, "categoryNames");
            return (Criteria) this;
        }

        public Criteria andCategoryNamesNotEqualTo(String value) {
            addCriterion("CategoryNames <>", value, "categoryNames");
            return (Criteria) this;
        }

        public Criteria andCategoryNamesGreaterThan(String value) {
            addCriterion("CategoryNames >", value, "categoryNames");
            return (Criteria) this;
        }

        public Criteria andCategoryNamesGreaterThanOrEqualTo(String value) {
            addCriterion("CategoryNames >=", value, "categoryNames");
            return (Criteria) this;
        }

        public Criteria andCategoryNamesLessThan(String value) {
            addCriterion("CategoryNames <", value, "categoryNames");
            return (Criteria) this;
        }

        public Criteria andCategoryNamesLessThanOrEqualTo(String value) {
            addCriterion("CategoryNames <=", value, "categoryNames");
            return (Criteria) this;
        }

        public Criteria andCategoryNamesLike(String value) {
            addCriterion("CategoryNames like", value, "categoryNames");
            return (Criteria) this;
        }

        public Criteria andCategoryNamesNotLike(String value) {
            addCriterion("CategoryNames not like", value, "categoryNames");
            return (Criteria) this;
        }

        public Criteria andCategoryNamesIn(List<String> values) {
            addCriterion("CategoryNames in", values, "categoryNames");
            return (Criteria) this;
        }

        public Criteria andCategoryNamesNotIn(List<String> values) {
            addCriterion("CategoryNames not in", values, "categoryNames");
            return (Criteria) this;
        }

        public Criteria andCategoryNamesBetween(String value1, String value2) {
            addCriterion("CategoryNames between", value1, value2, "categoryNames");
            return (Criteria) this;
        }

        public Criteria andCategoryNamesNotBetween(String value1, String value2) {
            addCriterion("CategoryNames not between", value1, value2, "categoryNames");
            return (Criteria) this;
        }
    }

    /**
     * 品牌申请表
     *  domain 对应表：wm_brandsapply_t
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria { 


        protected Criteria() {
            super();
        }
    }

    /**
     *  品牌申请表
     *   对应表： 
     *    wm_brandsapply_t
     *
     * @mbggenerated
     */
    public static class Criterion implements java.io.Serializable{ 

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