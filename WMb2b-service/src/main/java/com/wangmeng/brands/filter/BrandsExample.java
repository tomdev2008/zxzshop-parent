package com.wangmeng.brands.filter;

import java.util.ArrayList;
import java.util.List;

import com.wangmeng.query.ABaseDaoFilter;

/**
 *  品牌表  mapper 操作过滤器
 *   对应表： 
 *    wm_brands_t
 *
 * @mbggenerated
 */
public class BrandsExample extends ABaseDaoFilter<BrandsExample.Criteria> { 

    /**
     * orderByClause
     * 对应表： 
     *   wm_brands_t
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * distinct
     * 对应表： 
     *   wm_brands_t
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * oredCriteria
     * 对应表： 
     *   wm_brands_t
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     *  对应表:  wm_brands_t
     *
     * @mbggenerated
     */
    public BrandsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public Criteria getScalarExistedCriteria() {
        if(oredCriteria!=null && oredCriteria.size()>0){
            	return oredCriteria.get(0);
        }
        return or();
    }

    /**
     *  对应表:  wm_brands_t
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     *  对应表:  wm_brands_t
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     *  对应表:  wm_brands_t
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     *  对应表:  wm_brands_t
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     *  对应表:  wm_brands_t
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *  对应表:  wm_brands_t
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *  对应表:  wm_brands_t
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *  对应表:  wm_brands_t
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
     *  对应表:  wm_brands_t
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *  对应表:  wm_brands_t
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     *  品牌表
     *   对应表： 
     *    wm_brands_t
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

        public Criteria andNameIsNull() {
            addCriterion("Name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("Name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("Name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("Name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("Name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("Name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("Name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("Name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("Name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("Name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("Name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("Name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("Name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("Name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andDisplaySequenceIsNull() {
            addCriterion("DisplaySequence is null");
            return (Criteria) this;
        }

        public Criteria andDisplaySequenceIsNotNull() {
            addCriterion("DisplaySequence is not null");
            return (Criteria) this;
        }

        public Criteria andDisplaySequenceEqualTo(Long value) {
            addCriterion("DisplaySequence =", value, "displaySequence");
            return (Criteria) this;
        }

        public Criteria andDisplaySequenceNotEqualTo(Long value) {
            addCriterion("DisplaySequence <>", value, "displaySequence");
            return (Criteria) this;
        }

        public Criteria andDisplaySequenceGreaterThan(Long value) {
            addCriterion("DisplaySequence >", value, "displaySequence");
            return (Criteria) this;
        }

        public Criteria andDisplaySequenceGreaterThanOrEqualTo(Long value) {
            addCriterion("DisplaySequence >=", value, "displaySequence");
            return (Criteria) this;
        }

        public Criteria andDisplaySequenceLessThan(Long value) {
            addCriterion("DisplaySequence <", value, "displaySequence");
            return (Criteria) this;
        }

        public Criteria andDisplaySequenceLessThanOrEqualTo(Long value) {
            addCriterion("DisplaySequence <=", value, "displaySequence");
            return (Criteria) this;
        }

        public Criteria andDisplaySequenceIn(List<Long> values) {
            addCriterion("DisplaySequence in", values, "displaySequence");
            return (Criteria) this;
        }

        public Criteria andDisplaySequenceNotIn(List<Long> values) {
            addCriterion("DisplaySequence not in", values, "displaySequence");
            return (Criteria) this;
        }

        public Criteria andDisplaySequenceBetween(Long value1, Long value2) {
            addCriterion("DisplaySequence between", value1, value2, "displaySequence");
            return (Criteria) this;
        }

        public Criteria andDisplaySequenceNotBetween(Long value1, Long value2) {
            addCriterion("DisplaySequence not between", value1, value2, "displaySequence");
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

        public Criteria andRewriteNameIsNull() {
            addCriterion("RewriteName is null");
            return (Criteria) this;
        }

        public Criteria andRewriteNameIsNotNull() {
            addCriterion("RewriteName is not null");
            return (Criteria) this;
        }

        public Criteria andRewriteNameEqualTo(String value) {
            addCriterion("RewriteName =", value, "rewriteName");
            return (Criteria) this;
        }

        public Criteria andRewriteNameNotEqualTo(String value) {
            addCriterion("RewriteName <>", value, "rewriteName");
            return (Criteria) this;
        }

        public Criteria andRewriteNameGreaterThan(String value) {
            addCriterion("RewriteName >", value, "rewriteName");
            return (Criteria) this;
        }

        public Criteria andRewriteNameGreaterThanOrEqualTo(String value) {
            addCriterion("RewriteName >=", value, "rewriteName");
            return (Criteria) this;
        }

        public Criteria andRewriteNameLessThan(String value) {
            addCriterion("RewriteName <", value, "rewriteName");
            return (Criteria) this;
        }

        public Criteria andRewriteNameLessThanOrEqualTo(String value) {
            addCriterion("RewriteName <=", value, "rewriteName");
            return (Criteria) this;
        }

        public Criteria andRewriteNameLike(String value) {
            addCriterion("RewriteName like", value, "rewriteName");
            return (Criteria) this;
        }

        public Criteria andRewriteNameNotLike(String value) {
            addCriterion("RewriteName not like", value, "rewriteName");
            return (Criteria) this;
        }

        public Criteria andRewriteNameIn(List<String> values) {
            addCriterion("RewriteName in", values, "rewriteName");
            return (Criteria) this;
        }

        public Criteria andRewriteNameNotIn(List<String> values) {
            addCriterion("RewriteName not in", values, "rewriteName");
            return (Criteria) this;
        }

        public Criteria andRewriteNameBetween(String value1, String value2) {
            addCriterion("RewriteName between", value1, value2, "rewriteName");
            return (Criteria) this;
        }

        public Criteria andRewriteNameNotBetween(String value1, String value2) {
            addCriterion("RewriteName not between", value1, value2, "rewriteName");
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

        public Criteria andMetaTitleIsNull() {
            addCriterion("MetaTitle is null");
            return (Criteria) this;
        }

        public Criteria andMetaTitleIsNotNull() {
            addCriterion("MetaTitle is not null");
            return (Criteria) this;
        }

        public Criteria andMetaTitleEqualTo(String value) {
            addCriterion("MetaTitle =", value, "metaTitle");
            return (Criteria) this;
        }

        public Criteria andMetaTitleNotEqualTo(String value) {
            addCriterion("MetaTitle <>", value, "metaTitle");
            return (Criteria) this;
        }

        public Criteria andMetaTitleGreaterThan(String value) {
            addCriterion("MetaTitle >", value, "metaTitle");
            return (Criteria) this;
        }

        public Criteria andMetaTitleGreaterThanOrEqualTo(String value) {
            addCriterion("MetaTitle >=", value, "metaTitle");
            return (Criteria) this;
        }

        public Criteria andMetaTitleLessThan(String value) {
            addCriterion("MetaTitle <", value, "metaTitle");
            return (Criteria) this;
        }

        public Criteria andMetaTitleLessThanOrEqualTo(String value) {
            addCriterion("MetaTitle <=", value, "metaTitle");
            return (Criteria) this;
        }

        public Criteria andMetaTitleLike(String value) {
            addCriterion("MetaTitle like", value, "metaTitle");
            return (Criteria) this;
        }

        public Criteria andMetaTitleNotLike(String value) {
            addCriterion("MetaTitle not like", value, "metaTitle");
            return (Criteria) this;
        }

        public Criteria andMetaTitleIn(List<String> values) {
            addCriterion("MetaTitle in", values, "metaTitle");
            return (Criteria) this;
        }

        public Criteria andMetaTitleNotIn(List<String> values) {
            addCriterion("MetaTitle not in", values, "metaTitle");
            return (Criteria) this;
        }

        public Criteria andMetaTitleBetween(String value1, String value2) {
            addCriterion("MetaTitle between", value1, value2, "metaTitle");
            return (Criteria) this;
        }

        public Criteria andMetaTitleNotBetween(String value1, String value2) {
            addCriterion("MetaTitle not between", value1, value2, "metaTitle");
            return (Criteria) this;
        }

        public Criteria andMetaDescrIsNull() {
            addCriterion("MetaDescr is null");
            return (Criteria) this;
        }

        public Criteria andMetaDescrIsNotNull() {
            addCriterion("MetaDescr is not null");
            return (Criteria) this;
        }

        public Criteria andMetaDescrEqualTo(String value) {
            addCriterion("MetaDescr =", value, "metaDescr");
            return (Criteria) this;
        }

        public Criteria andMetaDescrNotEqualTo(String value) {
            addCriterion("MetaDescr <>", value, "metaDescr");
            return (Criteria) this;
        }

        public Criteria andMetaDescrGreaterThan(String value) {
            addCriterion("MetaDescr >", value, "metaDescr");
            return (Criteria) this;
        }

        public Criteria andMetaDescrGreaterThanOrEqualTo(String value) {
            addCriterion("MetaDescr >=", value, "metaDescr");
            return (Criteria) this;
        }

        public Criteria andMetaDescrLessThan(String value) {
            addCriterion("MetaDescr <", value, "metaDescr");
            return (Criteria) this;
        }

        public Criteria andMetaDescrLessThanOrEqualTo(String value) {
            addCriterion("MetaDescr <=", value, "metaDescr");
            return (Criteria) this;
        }

        public Criteria andMetaDescrLike(String value) {
            addCriterion("MetaDescr like", value, "metaDescr");
            return (Criteria) this;
        }

        public Criteria andMetaDescrNotLike(String value) {
            addCriterion("MetaDescr not like", value, "metaDescr");
            return (Criteria) this;
        }

        public Criteria andMetaDescrIn(List<String> values) {
            addCriterion("MetaDescr in", values, "metaDescr");
            return (Criteria) this;
        }

        public Criteria andMetaDescrNotIn(List<String> values) {
            addCriterion("MetaDescr not in", values, "metaDescr");
            return (Criteria) this;
        }

        public Criteria andMetaDescrBetween(String value1, String value2) {
            addCriterion("MetaDescr between", value1, value2, "metaDescr");
            return (Criteria) this;
        }

        public Criteria andMetaDescrNotBetween(String value1, String value2) {
            addCriterion("MetaDescr not between", value1, value2, "metaDescr");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsIsNull() {
            addCriterion("MetaKeywords is null");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsIsNotNull() {
            addCriterion("MetaKeywords is not null");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsEqualTo(String value) {
            addCriterion("MetaKeywords =", value, "metaKeywords");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsNotEqualTo(String value) {
            addCriterion("MetaKeywords <>", value, "metaKeywords");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsGreaterThan(String value) {
            addCriterion("MetaKeywords >", value, "metaKeywords");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsGreaterThanOrEqualTo(String value) {
            addCriterion("MetaKeywords >=", value, "metaKeywords");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsLessThan(String value) {
            addCriterion("MetaKeywords <", value, "metaKeywords");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsLessThanOrEqualTo(String value) {
            addCriterion("MetaKeywords <=", value, "metaKeywords");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsLike(String value) {
            addCriterion("MetaKeywords like", value, "metaKeywords");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsNotLike(String value) {
            addCriterion("MetaKeywords not like", value, "metaKeywords");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsIn(List<String> values) {
            addCriterion("MetaKeywords in", values, "metaKeywords");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsNotIn(List<String> values) {
            addCriterion("MetaKeywords not in", values, "metaKeywords");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsBetween(String value1, String value2) {
            addCriterion("MetaKeywords between", value1, value2, "metaKeywords");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsNotBetween(String value1, String value2) {
            addCriterion("MetaKeywords not between", value1, value2, "metaKeywords");
            return (Criteria) this;
        }

        public Criteria andIsRecommendIsNull() {
            addCriterion("IsRecommend is null");
            return (Criteria) this;
        }

        public Criteria andIsRecommendIsNotNull() {
            addCriterion("IsRecommend is not null");
            return (Criteria) this;
        }

        public Criteria andIsRecommendEqualTo(Boolean value) {
            addCriterion("IsRecommend =", value, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendNotEqualTo(Boolean value) {
            addCriterion("IsRecommend <>", value, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendGreaterThan(Boolean value) {
            addCriterion("IsRecommend >", value, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IsRecommend >=", value, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendLessThan(Boolean value) {
            addCriterion("IsRecommend <", value, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendLessThanOrEqualTo(Boolean value) {
            addCriterion("IsRecommend <=", value, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendIn(List<Boolean> values) {
            addCriterion("IsRecommend in", values, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendNotIn(List<Boolean> values) {
            addCriterion("IsRecommend not in", values, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendBetween(Boolean value1, Boolean value2) {
            addCriterion("IsRecommend between", value1, value2, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IsRecommend not between", value1, value2, "isRecommend");
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

        public Criteria andIsIndexShowIsNull() {
            addCriterion("IsIndexShow is null");
            return (Criteria) this;
        }

        public Criteria andIsIndexShowIsNotNull() {
            addCriterion("IsIndexShow is not null");
            return (Criteria) this;
        }

        public Criteria andIsIndexShowEqualTo(Boolean value) {
            addCriterion("IsIndexShow =", value, "isIndexShow");
            return (Criteria) this;
        }

        public Criteria andIsIndexShowNotEqualTo(Boolean value) {
            addCriterion("IsIndexShow <>", value, "isIndexShow");
            return (Criteria) this;
        }

        public Criteria andIsIndexShowGreaterThan(Boolean value) {
            addCriterion("IsIndexShow >", value, "isIndexShow");
            return (Criteria) this;
        }

        public Criteria andIsIndexShowGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IsIndexShow >=", value, "isIndexShow");
            return (Criteria) this;
        }

        public Criteria andIsIndexShowLessThan(Boolean value) {
            addCriterion("IsIndexShow <", value, "isIndexShow");
            return (Criteria) this;
        }

        public Criteria andIsIndexShowLessThanOrEqualTo(Boolean value) {
            addCriterion("IsIndexShow <=", value, "isIndexShow");
            return (Criteria) this;
        }

        public Criteria andIsIndexShowIn(List<Boolean> values) {
            addCriterion("IsIndexShow in", values, "isIndexShow");
            return (Criteria) this;
        }

        public Criteria andIsIndexShowNotIn(List<Boolean> values) {
            addCriterion("IsIndexShow not in", values, "isIndexShow");
            return (Criteria) this;
        }

        public Criteria andIsIndexShowBetween(Boolean value1, Boolean value2) {
            addCriterion("IsIndexShow between", value1, value2, "isIndexShow");
            return (Criteria) this;
        }

        public Criteria andIsIndexShowNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IsIndexShow not between", value1, value2, "isIndexShow");
            return (Criteria) this;
        }
    }

    /**
     * 品牌表
     *  domain 对应表：wm_brands_t
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria { 


        protected Criteria() {
            super();
        }
    }

    /**
     *  品牌表
     *   对应表： 
     *    wm_brands_t
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