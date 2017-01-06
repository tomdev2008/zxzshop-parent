package com.wangmeng.sys.filter;

import java.util.ArrayList;
import java.util.List;

import com.wangmeng.query.ABaseDaoFilter;

/**
 *  权限表  mapper 操作过滤器
 *   对应表： 
 *    wm_permission_t
 *
 * @mbggenerated
 */
public class PermissionExample extends ABaseDaoFilter<PermissionExample.Criteria> { 
 
    /**
     * oredCriteria
     * 对应表： 
     *   wm_permission_t
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     *  对应表:  wm_permission_t
     *
     * @mbggenerated
     */
    public PermissionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public Criteria getScalarExistedCriteria() {
        if(oredCriteria!=null && oredCriteria.size()>0){
            	return oredCriteria.get(0);
        }
        return or();
    }

    /**
     *  对应表:  wm_permission_t
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *  对应表:  wm_permission_t
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *  对应表:  wm_permission_t
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *  对应表:  wm_permission_t
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
     *  对应表:  wm_permission_t
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *  对应表:  wm_permission_t
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     *  权限表
     *   对应表： 
     *    wm_permission_t
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

        public Criteria andFatherPerCodeIsNull() {
            addCriterion("FatherPerCode is null");
            return (Criteria) this;
        }

        public Criteria andFatherPerCodeIsNotNull() {
            addCriterion("FatherPerCode is not null");
            return (Criteria) this;
        }

        public Criteria andFatherPerCodeEqualTo(String value) {
            addCriterion("FatherPerCode =", value, "fatherPerCode");
            return (Criteria) this;
        }

        public Criteria andFatherPerCodeNotEqualTo(String value) {
            addCriterion("FatherPerCode <>", value, "fatherPerCode");
            return (Criteria) this;
        }

        public Criteria andFatherPerCodeGreaterThan(String value) {
            addCriterion("FatherPerCode >", value, "fatherPerCode");
            return (Criteria) this;
        }

        public Criteria andFatherPerCodeGreaterThanOrEqualTo(String value) {
            addCriterion("FatherPerCode >=", value, "fatherPerCode");
            return (Criteria) this;
        }

        public Criteria andFatherPerCodeLessThan(String value) {
            addCriterion("FatherPerCode <", value, "fatherPerCode");
            return (Criteria) this;
        }

        public Criteria andFatherPerCodeLessThanOrEqualTo(String value) {
            addCriterion("FatherPerCode <=", value, "fatherPerCode");
            return (Criteria) this;
        }

        public Criteria andFatherPerCodeLike(String value) {
            addCriterion("FatherPerCode like", value, "fatherPerCode");
            return (Criteria) this;
        }

        public Criteria andFatherPerCodeNotLike(String value) {
            addCriterion("FatherPerCode not like", value, "fatherPerCode");
            return (Criteria) this;
        }

        public Criteria andFatherPerCodeIn(List<String> values) {
            addCriterion("FatherPerCode in", values, "fatherPerCode");
            return (Criteria) this;
        }

        public Criteria andFatherPerCodeNotIn(List<String> values) {
            addCriterion("FatherPerCode not in", values, "fatherPerCode");
            return (Criteria) this;
        }

        public Criteria andFatherPerCodeBetween(String value1, String value2) {
            addCriterion("FatherPerCode between", value1, value2, "fatherPerCode");
            return (Criteria) this;
        }

        public Criteria andFatherPerCodeNotBetween(String value1, String value2) {
            addCriterion("FatherPerCode not between", value1, value2, "fatherPerCode");
            return (Criteria) this;
        }

        public Criteria andPerNameIsNull() {
            addCriterion("PerName is null");
            return (Criteria) this;
        }

        public Criteria andPerNameIsNotNull() {
            addCriterion("PerName is not null");
            return (Criteria) this;
        }

        public Criteria andPerNameEqualTo(String value) {
            addCriterion("PerName =", value, "perName");
            return (Criteria) this;
        }

        public Criteria andPerNameNotEqualTo(String value) {
            addCriterion("PerName <>", value, "perName");
            return (Criteria) this;
        }

        public Criteria andPerNameGreaterThan(String value) {
            addCriterion("PerName >", value, "perName");
            return (Criteria) this;
        }

        public Criteria andPerNameGreaterThanOrEqualTo(String value) {
            addCriterion("PerName >=", value, "perName");
            return (Criteria) this;
        }

        public Criteria andPerNameLessThan(String value) {
            addCriterion("PerName <", value, "perName");
            return (Criteria) this;
        }

        public Criteria andPerNameLessThanOrEqualTo(String value) {
            addCriterion("PerName <=", value, "perName");
            return (Criteria) this;
        }

        public Criteria andPerNameLike(String value) {
            addCriterion("PerName like", value, "perName");
            return (Criteria) this;
        }

        public Criteria andPerNameNotLike(String value) {
            addCriterion("PerName not like", value, "perName");
            return (Criteria) this;
        }

        public Criteria andPerNameIn(List<String> values) {
            addCriterion("PerName in", values, "perName");
            return (Criteria) this;
        }

        public Criteria andPerNameNotIn(List<String> values) {
            addCriterion("PerName not in", values, "perName");
            return (Criteria) this;
        }

        public Criteria andPerNameBetween(String value1, String value2) {
            addCriterion("PerName between", value1, value2, "perName");
            return (Criteria) this;
        }

        public Criteria andPerNameNotBetween(String value1, String value2) {
            addCriterion("PerName not between", value1, value2, "perName");
            return (Criteria) this;
        }

        public Criteria andPerEnableIsNull() {
            addCriterion("PerEnable is null");
            return (Criteria) this;
        }

        public Criteria andPerEnableIsNotNull() {
            addCriterion("PerEnable is not null");
            return (Criteria) this;
        }

        public Criteria andPerEnableEqualTo(Integer value) {
            addCriterion("PerEnable =", value, "perEnable");
            return (Criteria) this;
        }

        public Criteria andPerEnableNotEqualTo(Integer value) {
            addCriterion("PerEnable <>", value, "perEnable");
            return (Criteria) this;
        }

        public Criteria andPerEnableGreaterThan(Integer value) {
            addCriterion("PerEnable >", value, "perEnable");
            return (Criteria) this;
        }

        public Criteria andPerEnableGreaterThanOrEqualTo(Integer value) {
            addCriterion("PerEnable >=", value, "perEnable");
            return (Criteria) this;
        }

        public Criteria andPerEnableLessThan(Integer value) {
            addCriterion("PerEnable <", value, "perEnable");
            return (Criteria) this;
        }

        public Criteria andPerEnableLessThanOrEqualTo(Integer value) {
            addCriterion("PerEnable <=", value, "perEnable");
            return (Criteria) this;
        }

        public Criteria andPerEnableIn(List<Integer> values) {
            addCriterion("PerEnable in", values, "perEnable");
            return (Criteria) this;
        }

        public Criteria andPerEnableNotIn(List<Integer> values) {
            addCriterion("PerEnable not in", values, "perEnable");
            return (Criteria) this;
        }

        public Criteria andPerEnableBetween(Integer value1, Integer value2) {
            addCriterion("PerEnable between", value1, value2, "perEnable");
            return (Criteria) this;
        }

        public Criteria andPerEnableNotBetween(Integer value1, Integer value2) {
            addCriterion("PerEnable not between", value1, value2, "perEnable");
            return (Criteria) this;
        }

        public Criteria andControllerNameIsNull() {
            addCriterion("ControllerName is null");
            return (Criteria) this;
        }

        public Criteria andControllerNameIsNotNull() {
            addCriterion("ControllerName is not null");
            return (Criteria) this;
        }

        public Criteria andControllerNameEqualTo(String value) {
            addCriterion("ControllerName =", value, "controllerName");
            return (Criteria) this;
        }

        public Criteria andControllerNameNotEqualTo(String value) {
            addCriterion("ControllerName <>", value, "controllerName");
            return (Criteria) this;
        }

        public Criteria andControllerNameGreaterThan(String value) {
            addCriterion("ControllerName >", value, "controllerName");
            return (Criteria) this;
        }

        public Criteria andControllerNameGreaterThanOrEqualTo(String value) {
            addCriterion("ControllerName >=", value, "controllerName");
            return (Criteria) this;
        }

        public Criteria andControllerNameLessThan(String value) {
            addCriterion("ControllerName <", value, "controllerName");
            return (Criteria) this;
        }

        public Criteria andControllerNameLessThanOrEqualTo(String value) {
            addCriterion("ControllerName <=", value, "controllerName");
            return (Criteria) this;
        }

        public Criteria andControllerNameLike(String value) {
            addCriterion("ControllerName like", value, "controllerName");
            return (Criteria) this;
        }

        public Criteria andControllerNameNotLike(String value) {
            addCriterion("ControllerName not like", value, "controllerName");
            return (Criteria) this;
        }

        public Criteria andControllerNameIn(List<String> values) {
            addCriterion("ControllerName in", values, "controllerName");
            return (Criteria) this;
        }

        public Criteria andControllerNameNotIn(List<String> values) {
            addCriterion("ControllerName not in", values, "controllerName");
            return (Criteria) this;
        }

        public Criteria andControllerNameBetween(String value1, String value2) {
            addCriterion("ControllerName between", value1, value2, "controllerName");
            return (Criteria) this;
        }

        public Criteria andControllerNameNotBetween(String value1, String value2) {
            addCriterion("ControllerName not between", value1, value2, "controllerName");
            return (Criteria) this;
        }

        public Criteria andUrlLinkIsNull() {
            addCriterion("UrlLink is null");
            return (Criteria) this;
        }

        public Criteria andUrlLinkIsNotNull() {
            addCriterion("UrlLink is not null");
            return (Criteria) this;
        }

        public Criteria andUrlLinkEqualTo(String value) {
            addCriterion("UrlLink =", value, "urlLink");
            return (Criteria) this;
        }

        public Criteria andUrlLinkNotEqualTo(String value) {
            addCriterion("UrlLink <>", value, "urlLink");
            return (Criteria) this;
        }

        public Criteria andUrlLinkGreaterThan(String value) {
            addCriterion("UrlLink >", value, "urlLink");
            return (Criteria) this;
        }

        public Criteria andUrlLinkGreaterThanOrEqualTo(String value) {
            addCriterion("UrlLink >=", value, "urlLink");
            return (Criteria) this;
        }

        public Criteria andUrlLinkLessThan(String value) {
            addCriterion("UrlLink <", value, "urlLink");
            return (Criteria) this;
        }

        public Criteria andUrlLinkLessThanOrEqualTo(String value) {
            addCriterion("UrlLink <=", value, "urlLink");
            return (Criteria) this;
        }

        public Criteria andUrlLinkLike(String value) {
            addCriterion("UrlLink like", value, "urlLink");
            return (Criteria) this;
        }

        public Criteria andUrlLinkNotLike(String value) {
            addCriterion("UrlLink not like", value, "urlLink");
            return (Criteria) this;
        }

        public Criteria andUrlLinkIn(List<String> values) {
            addCriterion("UrlLink in", values, "urlLink");
            return (Criteria) this;
        }

        public Criteria andUrlLinkNotIn(List<String> values) {
            addCriterion("UrlLink not in", values, "urlLink");
            return (Criteria) this;
        }

        public Criteria andUrlLinkBetween(String value1, String value2) {
            addCriterion("UrlLink between", value1, value2, "urlLink");
            return (Criteria) this;
        }

        public Criteria andUrlLinkNotBetween(String value1, String value2) {
            addCriterion("UrlLink not between", value1, value2, "urlLink");
            return (Criteria) this;
        }

        public Criteria andIconIsNull() {
            addCriterion("Icon is null");
            return (Criteria) this;
        }

        public Criteria andIconIsNotNull() {
            addCriterion("Icon is not null");
            return (Criteria) this;
        }

        public Criteria andIconEqualTo(String value) {
            addCriterion("Icon =", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotEqualTo(String value) {
            addCriterion("Icon <>", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThan(String value) {
            addCriterion("Icon >", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThanOrEqualTo(String value) {
            addCriterion("Icon >=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThan(String value) {
            addCriterion("Icon <", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThanOrEqualTo(String value) {
            addCriterion("Icon <=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLike(String value) {
            addCriterion("Icon like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotLike(String value) {
            addCriterion("Icon not like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconIn(List<String> values) {
            addCriterion("Icon in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotIn(List<String> values) {
            addCriterion("Icon not in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconBetween(String value1, String value2) {
            addCriterion("Icon between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotBetween(String value1, String value2) {
            addCriterion("Icon not between", value1, value2, "icon");
            return (Criteria) this;
        }
    }

    /**
     * 权限表
     *  domain 对应表：wm_permission_t
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria { 


        protected Criteria() {
            super();
        }
    }

    /**
     *  权限表
     *   对应表： 
     *    wm_permission_t
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