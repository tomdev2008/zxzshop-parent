package com.wangmeng.sys.legacy.filter;

import java.util.ArrayList;
import java.util.List;

import com.wangmeng.query.ABaseDaoFilter;

/**
 *   对应表： 
 *    wm_sys_role_power
 *
 * @mbggenerated
 */
public class SysRolePowerExample extends ABaseDaoFilter<SysRolePowerExample.Criteria> { 
 
    /**
     * oredCriteria
     * 对应表： 
     *   wm_sys_role_power
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     *  对应表:  wm_sys_role_power
     *
     * @mbggenerated
     */
    public SysRolePowerExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public Criteria getScalarExistedCriteria() {
        if(oredCriteria!=null && oredCriteria.size()>0){
            	return oredCriteria.get(0);
        }
        return or();
    }

    /**
     *  对应表:  wm_sys_role_power
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *  对应表:  wm_sys_role_power
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *  对应表:  wm_sys_role_power
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *  对应表:  wm_sys_role_power
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
     *  对应表:  wm_sys_role_power
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *  对应表:  wm_sys_role_power
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     *   对应表： 
     *    wm_sys_role_power
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

        public Criteria andRoleidIsNull() {
            addCriterion("roleid is null");
            return (Criteria) this;
        }

        public Criteria andRoleidIsNotNull() {
            addCriterion("roleid is not null");
            return (Criteria) this;
        }

        public Criteria andRoleidEqualTo(Long value) {
            addCriterion("roleid =", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotEqualTo(Long value) {
            addCriterion("roleid <>", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidGreaterThan(Long value) {
            addCriterion("roleid >", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidGreaterThanOrEqualTo(Long value) {
            addCriterion("roleid >=", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidLessThan(Long value) {
            addCriterion("roleid <", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidLessThanOrEqualTo(Long value) {
            addCriterion("roleid <=", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidIn(List<Long> values) {
            addCriterion("roleid in", values, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotIn(List<Long> values) {
            addCriterion("roleid not in", values, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidBetween(Long value1, Long value2) {
            addCriterion("roleid between", value1, value2, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotBetween(Long value1, Long value2) {
            addCriterion("roleid not between", value1, value2, "roleid");
            return (Criteria) this;
        }

        public Criteria andPoweridIsNull() {
            addCriterion("powerid is null");
            return (Criteria) this;
        }

        public Criteria andPoweridIsNotNull() {
            addCriterion("powerid is not null");
            return (Criteria) this;
        }

        public Criteria andPoweridEqualTo(Long value) {
            addCriterion("powerid =", value, "powerid");
            return (Criteria) this;
        }

        public Criteria andPoweridNotEqualTo(Long value) {
            addCriterion("powerid <>", value, "powerid");
            return (Criteria) this;
        }

        public Criteria andPoweridGreaterThan(Long value) {
            addCriterion("powerid >", value, "powerid");
            return (Criteria) this;
        }

        public Criteria andPoweridGreaterThanOrEqualTo(Long value) {
            addCriterion("powerid >=", value, "powerid");
            return (Criteria) this;
        }

        public Criteria andPoweridLessThan(Long value) {
            addCriterion("powerid <", value, "powerid");
            return (Criteria) this;
        }

        public Criteria andPoweridLessThanOrEqualTo(Long value) {
            addCriterion("powerid <=", value, "powerid");
            return (Criteria) this;
        }

        public Criteria andPoweridIn(List<Long> values) {
            addCriterion("powerid in", values, "powerid");
            return (Criteria) this;
        }

        public Criteria andPoweridNotIn(List<Long> values) {
            addCriterion("powerid not in", values, "powerid");
            return (Criteria) this;
        }

        public Criteria andPoweridBetween(Long value1, Long value2) {
            addCriterion("powerid between", value1, value2, "powerid");
            return (Criteria) this;
        }

        public Criteria andPoweridNotBetween(Long value1, Long value2) {
            addCriterion("powerid not between", value1, value2, "powerid");
            return (Criteria) this;
        }

        public Criteria andXuidIsNull() {
            addCriterion("xuid is null");
            return (Criteria) this;
        }

        public Criteria andXuidIsNotNull() {
            addCriterion("xuid is not null");
            return (Criteria) this;
        }

        public Criteria andXuidEqualTo(String value) {
            addCriterion("xuid =", value, "xuid");
            return (Criteria) this;
        }

        public Criteria andXuidNotEqualTo(String value) {
            addCriterion("xuid <>", value, "xuid");
            return (Criteria) this;
        }

        public Criteria andXuidGreaterThan(String value) {
            addCriterion("xuid >", value, "xuid");
            return (Criteria) this;
        }

        public Criteria andXuidGreaterThanOrEqualTo(String value) {
            addCriterion("xuid >=", value, "xuid");
            return (Criteria) this;
        }

        public Criteria andXuidLessThan(String value) {
            addCriterion("xuid <", value, "xuid");
            return (Criteria) this;
        }

        public Criteria andXuidLessThanOrEqualTo(String value) {
            addCriterion("xuid <=", value, "xuid");
            return (Criteria) this;
        }

        public Criteria andXuidLike(String value) {
            addCriterion("xuid like", value, "xuid");
            return (Criteria) this;
        }

        public Criteria andXuidNotLike(String value) {
            addCriterion("xuid not like", value, "xuid");
            return (Criteria) this;
        }

        public Criteria andXuidIn(List<String> values) {
            addCriterion("xuid in", values, "xuid");
            return (Criteria) this;
        }

        public Criteria andXuidNotIn(List<String> values) {
            addCriterion("xuid not in", values, "xuid");
            return (Criteria) this;
        }

        public Criteria andXuidBetween(String value1, String value2) {
            addCriterion("xuid between", value1, value2, "xuid");
            return (Criteria) this;
        }

        public Criteria andXuidNotBetween(String value1, String value2) {
            addCriterion("xuid not between", value1, value2, "xuid");
            return (Criteria) this;
        }
    }

    /**
     *  domain 对应表：wm_sys_role_power
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria { 


        protected Criteria() {
            super();
        }
    }

    /**
     *   对应表： 
     *    wm_sys_role_power
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