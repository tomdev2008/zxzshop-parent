package com.wangmeng.sys.filter;

import java.util.ArrayList;
import java.util.List;

import com.wangmeng.query.ABaseDaoFilter;

/**
 *  配置  mapper 操作过滤器
 *   对应表： 
 *    wm_sys_config
 *
 * @mbggenerated
 */
public class SysConfigExample extends ABaseDaoFilter<SysConfigExample.Criteria> { 

    /**
     * oredCriteria
     * 对应表： 
     *   wm_sys_config
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     *  对应表:  wm_sys_config
     *
     * @mbggenerated
     */
    public SysConfigExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public Criteria getScalarExistedCriteria() {
        if(oredCriteria!=null && oredCriteria.size()>0){
            	return oredCriteria.get(0);
        }
        return or();
    }

    /**
     *  对应表:  wm_sys_config
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *  对应表:  wm_sys_config
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *  对应表:  wm_sys_config
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *  对应表:  wm_sys_config
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
     *  对应表:  wm_sys_config
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *  对应表:  wm_sys_config
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     *  配置
     *   对应表： 
     *    wm_sys_config
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andItemCateIsNull() {
            addCriterion("item_cate is null");
            return (Criteria) this;
        }

        public Criteria andItemCateIsNotNull() {
            addCriterion("item_cate is not null");
            return (Criteria) this;
        }

        public Criteria andItemCateEqualTo(String value) {
            addCriterion("item_cate =", value, "itemCate");
            return (Criteria) this;
        }

        public Criteria andItemCateNotEqualTo(String value) {
            addCriterion("item_cate <>", value, "itemCate");
            return (Criteria) this;
        }

        public Criteria andItemCateGreaterThan(String value) {
            addCriterion("item_cate >", value, "itemCate");
            return (Criteria) this;
        }

        public Criteria andItemCateGreaterThanOrEqualTo(String value) {
            addCriterion("item_cate >=", value, "itemCate");
            return (Criteria) this;
        }

        public Criteria andItemCateLessThan(String value) {
            addCriterion("item_cate <", value, "itemCate");
            return (Criteria) this;
        }

        public Criteria andItemCateLessThanOrEqualTo(String value) {
            addCriterion("item_cate <=", value, "itemCate");
            return (Criteria) this;
        }

        public Criteria andItemCateLike(String value) {
            addCriterion("item_cate like", value, "itemCate");
            return (Criteria) this;
        }

        public Criteria andItemCateNotLike(String value) {
            addCriterion("item_cate not like", value, "itemCate");
            return (Criteria) this;
        }

        public Criteria andItemCateIn(List<String> values) {
            addCriterion("item_cate in", values, "itemCate");
            return (Criteria) this;
        }

        public Criteria andItemCateNotIn(List<String> values) {
            addCriterion("item_cate not in", values, "itemCate");
            return (Criteria) this;
        }

        public Criteria andItemCateBetween(String value1, String value2) {
            addCriterion("item_cate between", value1, value2, "itemCate");
            return (Criteria) this;
        }

        public Criteria andItemCateNotBetween(String value1, String value2) {
            addCriterion("item_cate not between", value1, value2, "itemCate");
            return (Criteria) this;
        }

        public Criteria andItemCodeIsNull() {
            addCriterion("item_code is null");
            return (Criteria) this;
        }

        public Criteria andItemCodeIsNotNull() {
            addCriterion("item_code is not null");
            return (Criteria) this;
        }

        public Criteria andItemCodeEqualTo(String value) {
            addCriterion("item_code =", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotEqualTo(String value) {
            addCriterion("item_code <>", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeGreaterThan(String value) {
            addCriterion("item_code >", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeGreaterThanOrEqualTo(String value) {
            addCriterion("item_code >=", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeLessThan(String value) {
            addCriterion("item_code <", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeLessThanOrEqualTo(String value) {
            addCriterion("item_code <=", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeLike(String value) {
            addCriterion("item_code like", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotLike(String value) {
            addCriterion("item_code not like", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeIn(List<String> values) {
            addCriterion("item_code in", values, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotIn(List<String> values) {
            addCriterion("item_code not in", values, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeBetween(String value1, String value2) {
            addCriterion("item_code between", value1, value2, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotBetween(String value1, String value2) {
            addCriterion("item_code not between", value1, value2, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNull() {
            addCriterion("item_name is null");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNotNull() {
            addCriterion("item_name is not null");
            return (Criteria) this;
        }

        public Criteria andItemNameEqualTo(String value) {
            addCriterion("item_name =", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotEqualTo(String value) {
            addCriterion("item_name <>", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThan(String value) {
            addCriterion("item_name >", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThanOrEqualTo(String value) {
            addCriterion("item_name >=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThan(String value) {
            addCriterion("item_name <", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThanOrEqualTo(String value) {
            addCriterion("item_name <=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLike(String value) {
            addCriterion("item_name like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotLike(String value) {
            addCriterion("item_name not like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameIn(List<String> values) {
            addCriterion("item_name in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotIn(List<String> values) {
            addCriterion("item_name not in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameBetween(String value1, String value2) {
            addCriterion("item_name between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotBetween(String value1, String value2) {
            addCriterion("item_name not between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemValueIsNull() {
            addCriterion("item_value is null");
            return (Criteria) this;
        }

        public Criteria andItemValueIsNotNull() {
            addCriterion("item_value is not null");
            return (Criteria) this;
        }

        public Criteria andItemValueEqualTo(String value) {
            addCriterion("item_value =", value, "itemValue");
            return (Criteria) this;
        }

        public Criteria andItemValueNotEqualTo(String value) {
            addCriterion("item_value <>", value, "itemValue");
            return (Criteria) this;
        }

        public Criteria andItemValueGreaterThan(String value) {
            addCriterion("item_value >", value, "itemValue");
            return (Criteria) this;
        }

        public Criteria andItemValueGreaterThanOrEqualTo(String value) {
            addCriterion("item_value >=", value, "itemValue");
            return (Criteria) this;
        }

        public Criteria andItemValueLessThan(String value) {
            addCriterion("item_value <", value, "itemValue");
            return (Criteria) this;
        }

        public Criteria andItemValueLessThanOrEqualTo(String value) {
            addCriterion("item_value <=", value, "itemValue");
            return (Criteria) this;
        }

        public Criteria andItemValueLike(String value) {
            addCriterion("item_value like", value, "itemValue");
            return (Criteria) this;
        }

        public Criteria andItemValueNotLike(String value) {
            addCriterion("item_value not like", value, "itemValue");
            return (Criteria) this;
        }

        public Criteria andItemValueIn(List<String> values) {
            addCriterion("item_value in", values, "itemValue");
            return (Criteria) this;
        }

        public Criteria andItemValueNotIn(List<String> values) {
            addCriterion("item_value not in", values, "itemValue");
            return (Criteria) this;
        }

        public Criteria andItemValueBetween(String value1, String value2) {
            addCriterion("item_value between", value1, value2, "itemValue");
            return (Criteria) this;
        }

        public Criteria andItemValueNotBetween(String value1, String value2) {
            addCriterion("item_value not between", value1, value2, "itemValue");
            return (Criteria) this;
        }

        public Criteria andItemTypeIsNull() {
            addCriterion("item_type is null");
            return (Criteria) this;
        }

        public Criteria andItemTypeIsNotNull() {
            addCriterion("item_type is not null");
            return (Criteria) this;
        }

        public Criteria andItemTypeEqualTo(String value) {
            addCriterion("item_type =", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotEqualTo(String value) {
            addCriterion("item_type <>", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeGreaterThan(String value) {
            addCriterion("item_type >", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeGreaterThanOrEqualTo(String value) {
            addCriterion("item_type >=", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeLessThan(String value) {
            addCriterion("item_type <", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeLessThanOrEqualTo(String value) {
            addCriterion("item_type <=", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeLike(String value) {
            addCriterion("item_type like", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotLike(String value) {
            addCriterion("item_type not like", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeIn(List<String> values) {
            addCriterion("item_type in", values, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotIn(List<String> values) {
            addCriterion("item_type not in", values, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeBetween(String value1, String value2) {
            addCriterion("item_type between", value1, value2, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotBetween(String value1, String value2) {
            addCriterion("item_type not between", value1, value2, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemInputsIsNull() {
            addCriterion("item_inputs is null");
            return (Criteria) this;
        }

        public Criteria andItemInputsIsNotNull() {
            addCriterion("item_inputs is not null");
            return (Criteria) this;
        }

        public Criteria andItemInputsEqualTo(String value) {
            addCriterion("item_inputs =", value, "itemInputs");
            return (Criteria) this;
        }

        public Criteria andItemInputsNotEqualTo(String value) {
            addCriterion("item_inputs <>", value, "itemInputs");
            return (Criteria) this;
        }

        public Criteria andItemInputsGreaterThan(String value) {
            addCriterion("item_inputs >", value, "itemInputs");
            return (Criteria) this;
        }

        public Criteria andItemInputsGreaterThanOrEqualTo(String value) {
            addCriterion("item_inputs >=", value, "itemInputs");
            return (Criteria) this;
        }

        public Criteria andItemInputsLessThan(String value) {
            addCriterion("item_inputs <", value, "itemInputs");
            return (Criteria) this;
        }

        public Criteria andItemInputsLessThanOrEqualTo(String value) {
            addCriterion("item_inputs <=", value, "itemInputs");
            return (Criteria) this;
        }

        public Criteria andItemInputsLike(String value) {
            addCriterion("item_inputs like", value, "itemInputs");
            return (Criteria) this;
        }

        public Criteria andItemInputsNotLike(String value) {
            addCriterion("item_inputs not like", value, "itemInputs");
            return (Criteria) this;
        }

        public Criteria andItemInputsIn(List<String> values) {
            addCriterion("item_inputs in", values, "itemInputs");
            return (Criteria) this;
        }

        public Criteria andItemInputsNotIn(List<String> values) {
            addCriterion("item_inputs not in", values, "itemInputs");
            return (Criteria) this;
        }

        public Criteria andItemInputsBetween(String value1, String value2) {
            addCriterion("item_inputs between", value1, value2, "itemInputs");
            return (Criteria) this;
        }

        public Criteria andItemInputsNotBetween(String value1, String value2) {
            addCriterion("item_inputs not between", value1, value2, "itemInputs");
            return (Criteria) this;
        }

        public Criteria andItemSortIsNull() {
            addCriterion("item_sort is null");
            return (Criteria) this;
        }

        public Criteria andItemSortIsNotNull() {
            addCriterion("item_sort is not null");
            return (Criteria) this;
        }

        public Criteria andItemSortEqualTo(Integer value) {
            addCriterion("item_sort =", value, "itemSort");
            return (Criteria) this;
        }

        public Criteria andItemSortNotEqualTo(Integer value) {
            addCriterion("item_sort <>", value, "itemSort");
            return (Criteria) this;
        }

        public Criteria andItemSortGreaterThan(Integer value) {
            addCriterion("item_sort >", value, "itemSort");
            return (Criteria) this;
        }

        public Criteria andItemSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("item_sort >=", value, "itemSort");
            return (Criteria) this;
        }

        public Criteria andItemSortLessThan(Integer value) {
            addCriterion("item_sort <", value, "itemSort");
            return (Criteria) this;
        }

        public Criteria andItemSortLessThanOrEqualTo(Integer value) {
            addCriterion("item_sort <=", value, "itemSort");
            return (Criteria) this;
        }

        public Criteria andItemSortIn(List<Integer> values) {
            addCriterion("item_sort in", values, "itemSort");
            return (Criteria) this;
        }

        public Criteria andItemSortNotIn(List<Integer> values) {
            addCriterion("item_sort not in", values, "itemSort");
            return (Criteria) this;
        }

        public Criteria andItemSortBetween(Integer value1, Integer value2) {
            addCriterion("item_sort between", value1, value2, "itemSort");
            return (Criteria) this;
        }

        public Criteria andItemSortNotBetween(Integer value1, Integer value2) {
            addCriterion("item_sort not between", value1, value2, "itemSort");
            return (Criteria) this;
        }

        public Criteria andItemTipIsNull() {
            addCriterion("item_tip is null");
            return (Criteria) this;
        }

        public Criteria andItemTipIsNotNull() {
            addCriterion("item_tip is not null");
            return (Criteria) this;
        }

        public Criteria andItemTipEqualTo(String value) {
            addCriterion("item_tip =", value, "itemTip");
            return (Criteria) this;
        }

        public Criteria andItemTipNotEqualTo(String value) {
            addCriterion("item_tip <>", value, "itemTip");
            return (Criteria) this;
        }

        public Criteria andItemTipGreaterThan(String value) {
            addCriterion("item_tip >", value, "itemTip");
            return (Criteria) this;
        }

        public Criteria andItemTipGreaterThanOrEqualTo(String value) {
            addCriterion("item_tip >=", value, "itemTip");
            return (Criteria) this;
        }

        public Criteria andItemTipLessThan(String value) {
            addCriterion("item_tip <", value, "itemTip");
            return (Criteria) this;
        }

        public Criteria andItemTipLessThanOrEqualTo(String value) {
            addCriterion("item_tip <=", value, "itemTip");
            return (Criteria) this;
        }

        public Criteria andItemTipLike(String value) {
            addCriterion("item_tip like", value, "itemTip");
            return (Criteria) this;
        }

        public Criteria andItemTipNotLike(String value) {
            addCriterion("item_tip not like", value, "itemTip");
            return (Criteria) this;
        }

        public Criteria andItemTipIn(List<String> values) {
            addCriterion("item_tip in", values, "itemTip");
            return (Criteria) this;
        }

        public Criteria andItemTipNotIn(List<String> values) {
            addCriterion("item_tip not in", values, "itemTip");
            return (Criteria) this;
        }

        public Criteria andItemTipBetween(String value1, String value2) {
            addCriterion("item_tip between", value1, value2, "itemTip");
            return (Criteria) this;
        }

        public Criteria andItemTipNotBetween(String value1, String value2) {
            addCriterion("item_tip not between", value1, value2, "itemTip");
            return (Criteria) this;
        }
    }

    /**
     * 配置
     *  domain 对应表：wm_sys_config
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria { 


        protected Criteria() {
            super();
        }
    }

    /**
     *  配置
     *   对应表： 
     *    wm_sys_config
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