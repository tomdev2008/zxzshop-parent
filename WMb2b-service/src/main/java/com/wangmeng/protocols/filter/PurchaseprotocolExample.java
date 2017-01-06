package com.wangmeng.protocols.filter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wangmeng.query.ABaseDaoFilter;

/**
 *  采购的协议信息  mapper 操作过滤器
 *   对应表： 
 *    wm_purchaseprotocol_t
 *
 * @mbggenerated
 */
public class PurchaseprotocolExample extends ABaseDaoFilter<PurchaseprotocolExample.Criteria> { 

    /**
     * oredCriteria
     * 对应表： 
     *   wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    public PurchaseprotocolExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public Criteria getScalarExistedCriteria() {
        if(oredCriteria!=null && oredCriteria.size()>0){
            	return oredCriteria.get(0);
        }
        return or();
    }
 
    /**
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *  对应表:  wm_purchaseprotocol_t
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
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     *  采购的协议信息
     *   对应表： 
     *    wm_purchaseprotocol_t
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

        public Criteria andPurchasenoIsNull() {
            addCriterion("PurchaseNo is null");
            return (Criteria) this;
        }

        public Criteria andPurchasenoIsNotNull() {
            addCriterion("PurchaseNo is not null");
            return (Criteria) this;
        }

        public Criteria andPurchasenoEqualTo(String value) {
            addCriterion("PurchaseNo =", value, "purchaseno");
            return (Criteria) this;
        }

        public Criteria andPurchasenoNotEqualTo(String value) {
            addCriterion("PurchaseNo <>", value, "purchaseno");
            return (Criteria) this;
        }

        public Criteria andPurchasenoGreaterThan(String value) {
            addCriterion("PurchaseNo >", value, "purchaseno");
            return (Criteria) this;
        }

        public Criteria andPurchasenoGreaterThanOrEqualTo(String value) {
            addCriterion("PurchaseNo >=", value, "purchaseno");
            return (Criteria) this;
        }

        public Criteria andPurchasenoLessThan(String value) {
            addCriterion("PurchaseNo <", value, "purchaseno");
            return (Criteria) this;
        }

        public Criteria andPurchasenoLessThanOrEqualTo(String value) {
            addCriterion("PurchaseNo <=", value, "purchaseno");
            return (Criteria) this;
        }

        public Criteria andPurchasenoLike(String value) {
            addCriterion("PurchaseNo like", value, "purchaseno");
            return (Criteria) this;
        }

        public Criteria andPurchasenoNotLike(String value) {
            addCriterion("PurchaseNo not like", value, "purchaseno");
            return (Criteria) this;
        }

        public Criteria andPurchasenoIn(List<String> values) {
            addCriterion("PurchaseNo in", values, "purchaseno");
            return (Criteria) this;
        }

        public Criteria andPurchasenoNotIn(List<String> values) {
            addCriterion("PurchaseNo not in", values, "purchaseno");
            return (Criteria) this;
        }

        public Criteria andPurchasenoBetween(String value1, String value2) {
            addCriterion("PurchaseNo between", value1, value2, "purchaseno");
            return (Criteria) this;
        }

        public Criteria andPurchasenoNotBetween(String value1, String value2) {
            addCriterion("PurchaseNo not between", value1, value2, "purchaseno");
            return (Criteria) this;
        }

        public Criteria andOrdernoIsNull() {
            addCriterion("OrderNo is null");
            return (Criteria) this;
        }

        public Criteria andOrdernoIsNotNull() {
            addCriterion("OrderNo is not null");
            return (Criteria) this;
        }

        public Criteria andOrdernoEqualTo(String value) {
            addCriterion("OrderNo =", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotEqualTo(String value) {
            addCriterion("OrderNo <>", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoGreaterThan(String value) {
            addCriterion("OrderNo >", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoGreaterThanOrEqualTo(String value) {
            addCriterion("OrderNo >=", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoLessThan(String value) {
            addCriterion("OrderNo <", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoLessThanOrEqualTo(String value) {
            addCriterion("OrderNo <=", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoLike(String value) {
            addCriterion("OrderNo like", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotLike(String value) {
            addCriterion("OrderNo not like", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoIn(List<String> values) {
            addCriterion("OrderNo in", values, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotIn(List<String> values) {
            addCriterion("OrderNo not in", values, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoBetween(String value1, String value2) {
            addCriterion("OrderNo between", value1, value2, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotBetween(String value1, String value2) {
            addCriterion("OrderNo not between", value1, value2, "orderno");
            return (Criteria) this;
        }

        public Criteria andProtocolnoIsNull() {
            addCriterion("ProtocolNo is null");
            return (Criteria) this;
        }

        public Criteria andProtocolnoIsNotNull() {
            addCriterion("ProtocolNo is not null");
            return (Criteria) this;
        }

        public Criteria andProtocolnoEqualTo(String value) {
            addCriterion("ProtocolNo =", value, "protocolno");
            return (Criteria) this;
        }

        public Criteria andProtocolnoNotEqualTo(String value) {
            addCriterion("ProtocolNo <>", value, "protocolno");
            return (Criteria) this;
        }

        public Criteria andProtocolnoGreaterThan(String value) {
            addCriterion("ProtocolNo >", value, "protocolno");
            return (Criteria) this;
        }

        public Criteria andProtocolnoGreaterThanOrEqualTo(String value) {
            addCriterion("ProtocolNo >=", value, "protocolno");
            return (Criteria) this;
        }

        public Criteria andProtocolnoLessThan(String value) {
            addCriterion("ProtocolNo <", value, "protocolno");
            return (Criteria) this;
        }

        public Criteria andProtocolnoLessThanOrEqualTo(String value) {
            addCriterion("ProtocolNo <=", value, "protocolno");
            return (Criteria) this;
        }

        public Criteria andProtocolnoLike(String value) {
            addCriterion("ProtocolNo like", value, "protocolno");
            return (Criteria) this;
        }

        public Criteria andProtocolnoNotLike(String value) {
            addCriterion("ProtocolNo not like", value, "protocolno");
            return (Criteria) this;
        }

        public Criteria andProtocolnoIn(List<String> values) {
            addCriterion("ProtocolNo in", values, "protocolno");
            return (Criteria) this;
        }

        public Criteria andProtocolnoNotIn(List<String> values) {
            addCriterion("ProtocolNo not in", values, "protocolno");
            return (Criteria) this;
        }

        public Criteria andProtocolnoBetween(String value1, String value2) {
            addCriterion("ProtocolNo between", value1, value2, "protocolno");
            return (Criteria) this;
        }

        public Criteria andProtocolnoNotBetween(String value1, String value2) {
            addCriterion("ProtocolNo not between", value1, value2, "protocolno");
            return (Criteria) this;
        }

        public Criteria andDocidIsNull() {
            addCriterion("DocId is null");
            return (Criteria) this;
        }

        public Criteria andDocidIsNotNull() {
            addCriterion("DocId is not null");
            return (Criteria) this;
        }

        public Criteria andDocidEqualTo(String value) {
            addCriterion("DocId =", value, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidNotEqualTo(String value) {
            addCriterion("DocId <>", value, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidGreaterThan(String value) {
            addCriterion("DocId >", value, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidGreaterThanOrEqualTo(String value) {
            addCriterion("DocId >=", value, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidLessThan(String value) {
            addCriterion("DocId <", value, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidLessThanOrEqualTo(String value) {
            addCriterion("DocId <=", value, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidLike(String value) {
            addCriterion("DocId like", value, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidNotLike(String value) {
            addCriterion("DocId not like", value, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidIn(List<String> values) {
            addCriterion("DocId in", values, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidNotIn(List<String> values) {
            addCriterion("DocId not in", values, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidBetween(String value1, String value2) {
            addCriterion("DocId between", value1, value2, "docid");
            return (Criteria) this;
        }

        public Criteria andDocidNotBetween(String value1, String value2) {
            addCriterion("DocId not between", value1, value2, "docid");
            return (Criteria) this;
        }

        public Criteria andProtocolnameIsNull() {
            addCriterion("ProtocolName is null");
            return (Criteria) this;
        }

        public Criteria andProtocolnameIsNotNull() {
            addCriterion("ProtocolName is not null");
            return (Criteria) this;
        }

        public Criteria andProtocolnameEqualTo(String value) {
            addCriterion("ProtocolName =", value, "protocolname");
            return (Criteria) this;
        }

        public Criteria andProtocolnameNotEqualTo(String value) {
            addCriterion("ProtocolName <>", value, "protocolname");
            return (Criteria) this;
        }

        public Criteria andProtocolnameGreaterThan(String value) {
            addCriterion("ProtocolName >", value, "protocolname");
            return (Criteria) this;
        }

        public Criteria andProtocolnameGreaterThanOrEqualTo(String value) {
            addCriterion("ProtocolName >=", value, "protocolname");
            return (Criteria) this;
        }

        public Criteria andProtocolnameLessThan(String value) {
            addCriterion("ProtocolName <", value, "protocolname");
            return (Criteria) this;
        }

        public Criteria andProtocolnameLessThanOrEqualTo(String value) {
            addCriterion("ProtocolName <=", value, "protocolname");
            return (Criteria) this;
        }

        public Criteria andProtocolnameLike(String value) {
            addCriterion("ProtocolName like", value, "protocolname");
            return (Criteria) this;
        }

        public Criteria andProtocolnameNotLike(String value) {
            addCriterion("ProtocolName not like", value, "protocolname");
            return (Criteria) this;
        }

        public Criteria andProtocolnameIn(List<String> values) {
            addCriterion("ProtocolName in", values, "protocolname");
            return (Criteria) this;
        }

        public Criteria andProtocolnameNotIn(List<String> values) {
            addCriterion("ProtocolName not in", values, "protocolname");
            return (Criteria) this;
        }

        public Criteria andProtocolnameBetween(String value1, String value2) {
            addCriterion("ProtocolName between", value1, value2, "protocolname");
            return (Criteria) this;
        }

        public Criteria andProtocolnameNotBetween(String value1, String value2) {
            addCriterion("ProtocolName not between", value1, value2, "protocolname");
            return (Criteria) this;
        }

        public Criteria andBuycompanyIsNull() {
            addCriterion("BuyCompany is null");
            return (Criteria) this;
        }

        public Criteria andBuycompanyIsNotNull() {
            addCriterion("BuyCompany is not null");
            return (Criteria) this;
        }

        public Criteria andBuycompanyEqualTo(Long value) {
            addCriterion("BuyCompany =", value, "buycompany");
            return (Criteria) this;
        }

        public Criteria andBuycompanyNotEqualTo(Long value) {
            addCriterion("BuyCompany <>", value, "buycompany");
            return (Criteria) this;
        }

        public Criteria andBuycompanyGreaterThan(Long value) {
            addCriterion("BuyCompany >", value, "buycompany");
            return (Criteria) this;
        }

        public Criteria andBuycompanyGreaterThanOrEqualTo(Long value) {
            addCriterion("BuyCompany >=", value, "buycompany");
            return (Criteria) this;
        }

        public Criteria andBuycompanyLessThan(Long value) {
            addCriterion("BuyCompany <", value, "buycompany");
            return (Criteria) this;
        }

        public Criteria andBuycompanyLessThanOrEqualTo(Long value) {
            addCriterion("BuyCompany <=", value, "buycompany");
            return (Criteria) this;
        }

        public Criteria andBuycompanyIn(List<Long> values) {
            addCriterion("BuyCompany in", values, "buycompany");
            return (Criteria) this;
        }

        public Criteria andBuycompanyNotIn(List<Long> values) {
            addCriterion("BuyCompany not in", values, "buycompany");
            return (Criteria) this;
        }

        public Criteria andBuycompanyBetween(Long value1, Long value2) {
            addCriterion("BuyCompany between", value1, value2, "buycompany");
            return (Criteria) this;
        }

        public Criteria andBuycompanyNotBetween(Long value1, Long value2) {
            addCriterion("BuyCompany not between", value1, value2, "buycompany");
            return (Criteria) this;
        }

        public Criteria andBuyuserIsNull() {
            addCriterion("BuyUser is null");
            return (Criteria) this;
        }

        public Criteria andBuyuserIsNotNull() {
            addCriterion("BuyUser is not null");
            return (Criteria) this;
        }

        public Criteria andBuyuserEqualTo(Long value) {
            addCriterion("BuyUser =", value, "buyuser");
            return (Criteria) this;
        }

        public Criteria andBuyuserNotEqualTo(Long value) {
            addCriterion("BuyUser <>", value, "buyuser");
            return (Criteria) this;
        }

        public Criteria andBuyuserGreaterThan(Long value) {
            addCriterion("BuyUser >", value, "buyuser");
            return (Criteria) this;
        }

        public Criteria andBuyuserGreaterThanOrEqualTo(Long value) {
            addCriterion("BuyUser >=", value, "buyuser");
            return (Criteria) this;
        }

        public Criteria andBuyuserLessThan(Long value) {
            addCriterion("BuyUser <", value, "buyuser");
            return (Criteria) this;
        }

        public Criteria andBuyuserLessThanOrEqualTo(Long value) {
            addCriterion("BuyUser <=", value, "buyuser");
            return (Criteria) this;
        }

        public Criteria andBuyuserIn(List<Long> values) {
            addCriterion("BuyUser in", values, "buyuser");
            return (Criteria) this;
        }

        public Criteria andBuyuserNotIn(List<Long> values) {
            addCriterion("BuyUser not in", values, "buyuser");
            return (Criteria) this;
        }

        public Criteria andBuyuserBetween(Long value1, Long value2) {
            addCriterion("BuyUser between", value1, value2, "buyuser");
            return (Criteria) this;
        }

        public Criteria andBuyuserNotBetween(Long value1, Long value2) {
            addCriterion("BuyUser not between", value1, value2, "buyuser");
            return (Criteria) this;
        }

        public Criteria andBuysignerIsNull() {
            addCriterion("BuySigner is null");
            return (Criteria) this;
        }

        public Criteria andBuysignerIsNotNull() {
            addCriterion("BuySigner is not null");
            return (Criteria) this;
        }

        public Criteria andBuysignerEqualTo(String value) {
            addCriterion("BuySigner =", value, "buysigner");
            return (Criteria) this;
        }

        public Criteria andBuysignerNotEqualTo(String value) {
            addCriterion("BuySigner <>", value, "buysigner");
            return (Criteria) this;
        }

        public Criteria andBuysignerGreaterThan(String value) {
            addCriterion("BuySigner >", value, "buysigner");
            return (Criteria) this;
        }

        public Criteria andBuysignerGreaterThanOrEqualTo(String value) {
            addCriterion("BuySigner >=", value, "buysigner");
            return (Criteria) this;
        }

        public Criteria andBuysignerLessThan(String value) {
            addCriterion("BuySigner <", value, "buysigner");
            return (Criteria) this;
        }

        public Criteria andBuysignerLessThanOrEqualTo(String value) {
            addCriterion("BuySigner <=", value, "buysigner");
            return (Criteria) this;
        }

        public Criteria andBuysignerLike(String value) {
            addCriterion("BuySigner like", value, "buysigner");
            return (Criteria) this;
        }

        public Criteria andBuysignerNotLike(String value) {
            addCriterion("BuySigner not like", value, "buysigner");
            return (Criteria) this;
        }

        public Criteria andBuysignerIn(List<String> values) {
            addCriterion("BuySigner in", values, "buysigner");
            return (Criteria) this;
        }

        public Criteria andBuysignerNotIn(List<String> values) {
            addCriterion("BuySigner not in", values, "buysigner");
            return (Criteria) this;
        }

        public Criteria andBuysignerBetween(String value1, String value2) {
            addCriterion("BuySigner between", value1, value2, "buysigner");
            return (Criteria) this;
        }

        public Criteria andBuysignerNotBetween(String value1, String value2) {
            addCriterion("BuySigner not between", value1, value2, "buysigner");
            return (Criteria) this;
        }

        public Criteria andSupplycompanyIsNull() {
            addCriterion("SupplyCompany is null");
            return (Criteria) this;
        }

        public Criteria andSupplycompanyIsNotNull() {
            addCriterion("SupplyCompany is not null");
            return (Criteria) this;
        }

        public Criteria andSupplycompanyEqualTo(Long value) {
            addCriterion("SupplyCompany =", value, "supplycompany");
            return (Criteria) this;
        }

        public Criteria andSupplycompanyNotEqualTo(Long value) {
            addCriterion("SupplyCompany <>", value, "supplycompany");
            return (Criteria) this;
        }

        public Criteria andSupplycompanyGreaterThan(Long value) {
            addCriterion("SupplyCompany >", value, "supplycompany");
            return (Criteria) this;
        }

        public Criteria andSupplycompanyGreaterThanOrEqualTo(Long value) {
            addCriterion("SupplyCompany >=", value, "supplycompany");
            return (Criteria) this;
        }

        public Criteria andSupplycompanyLessThan(Long value) {
            addCriterion("SupplyCompany <", value, "supplycompany");
            return (Criteria) this;
        }

        public Criteria andSupplycompanyLessThanOrEqualTo(Long value) {
            addCriterion("SupplyCompany <=", value, "supplycompany");
            return (Criteria) this;
        }

        public Criteria andSupplycompanyIn(List<Long> values) {
            addCriterion("SupplyCompany in", values, "supplycompany");
            return (Criteria) this;
        }

        public Criteria andSupplycompanyNotIn(List<Long> values) {
            addCriterion("SupplyCompany not in", values, "supplycompany");
            return (Criteria) this;
        }

        public Criteria andSupplycompanyBetween(Long value1, Long value2) {
            addCriterion("SupplyCompany between", value1, value2, "supplycompany");
            return (Criteria) this;
        }

        public Criteria andSupplycompanyNotBetween(Long value1, Long value2) {
            addCriterion("SupplyCompany not between", value1, value2, "supplycompany");
            return (Criteria) this;
        }

        public Criteria andSupplyuserIsNull() {
            addCriterion("SupplyUser is null");
            return (Criteria) this;
        }

        public Criteria andSupplyuserIsNotNull() {
            addCriterion("SupplyUser is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyuserEqualTo(Long value) {
            addCriterion("SupplyUser =", value, "supplyuser");
            return (Criteria) this;
        }

        public Criteria andSupplyuserNotEqualTo(Long value) {
            addCriterion("SupplyUser <>", value, "supplyuser");
            return (Criteria) this;
        }

        public Criteria andSupplyuserGreaterThan(Long value) {
            addCriterion("SupplyUser >", value, "supplyuser");
            return (Criteria) this;
        }

        public Criteria andSupplyuserGreaterThanOrEqualTo(Long value) {
            addCriterion("SupplyUser >=", value, "supplyuser");
            return (Criteria) this;
        }

        public Criteria andSupplyuserLessThan(Long value) {
            addCriterion("SupplyUser <", value, "supplyuser");
            return (Criteria) this;
        }

        public Criteria andSupplyuserLessThanOrEqualTo(Long value) {
            addCriterion("SupplyUser <=", value, "supplyuser");
            return (Criteria) this;
        }

        public Criteria andSupplyuserIn(List<Long> values) {
            addCriterion("SupplyUser in", values, "supplyuser");
            return (Criteria) this;
        }

        public Criteria andSupplyuserNotIn(List<Long> values) {
            addCriterion("SupplyUser not in", values, "supplyuser");
            return (Criteria) this;
        }

        public Criteria andSupplyuserBetween(Long value1, Long value2) {
            addCriterion("SupplyUser between", value1, value2, "supplyuser");
            return (Criteria) this;
        }

        public Criteria andSupplyuserNotBetween(Long value1, Long value2) {
            addCriterion("SupplyUser not between", value1, value2, "supplyuser");
            return (Criteria) this;
        }

        public Criteria andSupplysignerIsNull() {
            addCriterion("SupplySigner is null");
            return (Criteria) this;
        }

        public Criteria andSupplysignerIsNotNull() {
            addCriterion("SupplySigner is not null");
            return (Criteria) this;
        }

        public Criteria andSupplysignerEqualTo(String value) {
            addCriterion("SupplySigner =", value, "supplysigner");
            return (Criteria) this;
        }

        public Criteria andSupplysignerNotEqualTo(String value) {
            addCriterion("SupplySigner <>", value, "supplysigner");
            return (Criteria) this;
        }

        public Criteria andSupplysignerGreaterThan(String value) {
            addCriterion("SupplySigner >", value, "supplysigner");
            return (Criteria) this;
        }

        public Criteria andSupplysignerGreaterThanOrEqualTo(String value) {
            addCriterion("SupplySigner >=", value, "supplysigner");
            return (Criteria) this;
        }

        public Criteria andSupplysignerLessThan(String value) {
            addCriterion("SupplySigner <", value, "supplysigner");
            return (Criteria) this;
        }

        public Criteria andSupplysignerLessThanOrEqualTo(String value) {
            addCriterion("SupplySigner <=", value, "supplysigner");
            return (Criteria) this;
        }

        public Criteria andSupplysignerLike(String value) {
            addCriterion("SupplySigner like", value, "supplysigner");
            return (Criteria) this;
        }

        public Criteria andSupplysignerNotLike(String value) {
            addCriterion("SupplySigner not like", value, "supplysigner");
            return (Criteria) this;
        }

        public Criteria andSupplysignerIn(List<String> values) {
            addCriterion("SupplySigner in", values, "supplysigner");
            return (Criteria) this;
        }

        public Criteria andSupplysignerNotIn(List<String> values) {
            addCriterion("SupplySigner not in", values, "supplysigner");
            return (Criteria) this;
        }

        public Criteria andSupplysignerBetween(String value1, String value2) {
            addCriterion("SupplySigner between", value1, value2, "supplysigner");
            return (Criteria) this;
        }

        public Criteria andSupplysignerNotBetween(String value1, String value2) {
            addCriterion("SupplySigner not between", value1, value2, "supplysigner");
            return (Criteria) this;
        }

        public Criteria andInvoiceIsNull() {
            addCriterion("Invoice is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceIsNotNull() {
            addCriterion("Invoice is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceEqualTo(Byte value) {
            addCriterion("Invoice =", value, "invoice");
            return (Criteria) this;
        }

        public Criteria andInvoiceNotEqualTo(Byte value) {
            addCriterion("Invoice <>", value, "invoice");
            return (Criteria) this;
        }

        public Criteria andInvoiceGreaterThan(Byte value) {
            addCriterion("Invoice >", value, "invoice");
            return (Criteria) this;
        }

        public Criteria andInvoiceGreaterThanOrEqualTo(Byte value) {
            addCriterion("Invoice >=", value, "invoice");
            return (Criteria) this;
        }

        public Criteria andInvoiceLessThan(Byte value) {
            addCriterion("Invoice <", value, "invoice");
            return (Criteria) this;
        }

        public Criteria andInvoiceLessThanOrEqualTo(Byte value) {
            addCriterion("Invoice <=", value, "invoice");
            return (Criteria) this;
        }

        public Criteria andInvoiceIn(List<Byte> values) {
            addCriterion("Invoice in", values, "invoice");
            return (Criteria) this;
        }

        public Criteria andInvoiceNotIn(List<Byte> values) {
            addCriterion("Invoice not in", values, "invoice");
            return (Criteria) this;
        }

        public Criteria andInvoiceBetween(Byte value1, Byte value2) {
            addCriterion("Invoice between", value1, value2, "invoice");
            return (Criteria) this;
        }

        public Criteria andInvoiceNotBetween(Byte value1, Byte value2) {
            addCriterion("Invoice not between", value1, value2, "invoice");
            return (Criteria) this;
        }

        public Criteria andExpresswayIsNull() {
            addCriterion("ExpressWay is null");
            return (Criteria) this;
        }

        public Criteria andExpresswayIsNotNull() {
            addCriterion("ExpressWay is not null");
            return (Criteria) this;
        }

        public Criteria andExpresswayEqualTo(Byte value) {
            addCriterion("ExpressWay =", value, "expressway");
            return (Criteria) this;
        }

        public Criteria andExpresswayNotEqualTo(Byte value) {
            addCriterion("ExpressWay <>", value, "expressway");
            return (Criteria) this;
        }

        public Criteria andExpresswayGreaterThan(Byte value) {
            addCriterion("ExpressWay >", value, "expressway");
            return (Criteria) this;
        }

        public Criteria andExpresswayGreaterThanOrEqualTo(Byte value) {
            addCriterion("ExpressWay >=", value, "expressway");
            return (Criteria) this;
        }

        public Criteria andExpresswayLessThan(Byte value) {
            addCriterion("ExpressWay <", value, "expressway");
            return (Criteria) this;
        }

        public Criteria andExpresswayLessThanOrEqualTo(Byte value) {
            addCriterion("ExpressWay <=", value, "expressway");
            return (Criteria) this;
        }

        public Criteria andExpresswayIn(List<Byte> values) {
            addCriterion("ExpressWay in", values, "expressway");
            return (Criteria) this;
        }

        public Criteria andExpresswayNotIn(List<Byte> values) {
            addCriterion("ExpressWay not in", values, "expressway");
            return (Criteria) this;
        }

        public Criteria andExpresswayBetween(Byte value1, Byte value2) {
            addCriterion("ExpressWay between", value1, value2, "expressway");
            return (Criteria) this;
        }

        public Criteria andExpresswayNotBetween(Byte value1, Byte value2) {
            addCriterion("ExpressWay not between", value1, value2, "expressway");
            return (Criteria) this;
        }

        public Criteria andShiptoIsNull() {
            addCriterion("ShipTo is null");
            return (Criteria) this;
        }

        public Criteria andShiptoIsNotNull() {
            addCriterion("ShipTo is not null");
            return (Criteria) this;
        }

        public Criteria andShiptoEqualTo(String value) {
            addCriterion("ShipTo =", value, "shipto");
            return (Criteria) this;
        }

        public Criteria andShiptoNotEqualTo(String value) {
            addCriterion("ShipTo <>", value, "shipto");
            return (Criteria) this;
        }

        public Criteria andShiptoGreaterThan(String value) {
            addCriterion("ShipTo >", value, "shipto");
            return (Criteria) this;
        }

        public Criteria andShiptoGreaterThanOrEqualTo(String value) {
            addCriterion("ShipTo >=", value, "shipto");
            return (Criteria) this;
        }

        public Criteria andShiptoLessThan(String value) {
            addCriterion("ShipTo <", value, "shipto");
            return (Criteria) this;
        }

        public Criteria andShiptoLessThanOrEqualTo(String value) {
            addCriterion("ShipTo <=", value, "shipto");
            return (Criteria) this;
        }

        public Criteria andShiptoLike(String value) {
            addCriterion("ShipTo like", value, "shipto");
            return (Criteria) this;
        }

        public Criteria andShiptoNotLike(String value) {
            addCriterion("ShipTo not like", value, "shipto");
            return (Criteria) this;
        }

        public Criteria andShiptoIn(List<String> values) {
            addCriterion("ShipTo in", values, "shipto");
            return (Criteria) this;
        }

        public Criteria andShiptoNotIn(List<String> values) {
            addCriterion("ShipTo not in", values, "shipto");
            return (Criteria) this;
        }

        public Criteria andShiptoBetween(String value1, String value2) {
            addCriterion("ShipTo between", value1, value2, "shipto");
            return (Criteria) this;
        }

        public Criteria andShiptoNotBetween(String value1, String value2) {
            addCriterion("ShipTo not between", value1, value2, "shipto");
            return (Criteria) this;
        }

        public Criteria andReceivermobileIsNull() {
            addCriterion("ReceiverMobile is null");
            return (Criteria) this;
        }

        public Criteria andReceivermobileIsNotNull() {
            addCriterion("ReceiverMobile is not null");
            return (Criteria) this;
        }

        public Criteria andReceivermobileEqualTo(String value) {
            addCriterion("ReceiverMobile =", value, "receivermobile");
            return (Criteria) this;
        }

        public Criteria andReceivermobileNotEqualTo(String value) {
            addCriterion("ReceiverMobile <>", value, "receivermobile");
            return (Criteria) this;
        }

        public Criteria andReceivermobileGreaterThan(String value) {
            addCriterion("ReceiverMobile >", value, "receivermobile");
            return (Criteria) this;
        }

        public Criteria andReceivermobileGreaterThanOrEqualTo(String value) {
            addCriterion("ReceiverMobile >=", value, "receivermobile");
            return (Criteria) this;
        }

        public Criteria andReceivermobileLessThan(String value) {
            addCriterion("ReceiverMobile <", value, "receivermobile");
            return (Criteria) this;
        }

        public Criteria andReceivermobileLessThanOrEqualTo(String value) {
            addCriterion("ReceiverMobile <=", value, "receivermobile");
            return (Criteria) this;
        }

        public Criteria andReceivermobileLike(String value) {
            addCriterion("ReceiverMobile like", value, "receivermobile");
            return (Criteria) this;
        }

        public Criteria andReceivermobileNotLike(String value) {
            addCriterion("ReceiverMobile not like", value, "receivermobile");
            return (Criteria) this;
        }

        public Criteria andReceivermobileIn(List<String> values) {
            addCriterion("ReceiverMobile in", values, "receivermobile");
            return (Criteria) this;
        }

        public Criteria andReceivermobileNotIn(List<String> values) {
            addCriterion("ReceiverMobile not in", values, "receivermobile");
            return (Criteria) this;
        }

        public Criteria andReceivermobileBetween(String value1, String value2) {
            addCriterion("ReceiverMobile between", value1, value2, "receivermobile");
            return (Criteria) this;
        }

        public Criteria andReceivermobileNotBetween(String value1, String value2) {
            addCriterion("ReceiverMobile not between", value1, value2, "receivermobile");
            return (Criteria) this;
        }

        public Criteria andReceiveaddrIsNull() {
            addCriterion("ReceiveAddr is null");
            return (Criteria) this;
        }

        public Criteria andReceiveaddrIsNotNull() {
            addCriterion("ReceiveAddr is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveaddrEqualTo(String value) {
            addCriterion("ReceiveAddr =", value, "receiveaddr");
            return (Criteria) this;
        }

        public Criteria andReceiveaddrNotEqualTo(String value) {
            addCriterion("ReceiveAddr <>", value, "receiveaddr");
            return (Criteria) this;
        }

        public Criteria andReceiveaddrGreaterThan(String value) {
            addCriterion("ReceiveAddr >", value, "receiveaddr");
            return (Criteria) this;
        }

        public Criteria andReceiveaddrGreaterThanOrEqualTo(String value) {
            addCriterion("ReceiveAddr >=", value, "receiveaddr");
            return (Criteria) this;
        }

        public Criteria andReceiveaddrLessThan(String value) {
            addCriterion("ReceiveAddr <", value, "receiveaddr");
            return (Criteria) this;
        }

        public Criteria andReceiveaddrLessThanOrEqualTo(String value) {
            addCriterion("ReceiveAddr <=", value, "receiveaddr");
            return (Criteria) this;
        }

        public Criteria andReceiveaddrLike(String value) {
            addCriterion("ReceiveAddr like", value, "receiveaddr");
            return (Criteria) this;
        }

        public Criteria andReceiveaddrNotLike(String value) {
            addCriterion("ReceiveAddr not like", value, "receiveaddr");
            return (Criteria) this;
        }

        public Criteria andReceiveaddrIn(List<String> values) {
            addCriterion("ReceiveAddr in", values, "receiveaddr");
            return (Criteria) this;
        }

        public Criteria andReceiveaddrNotIn(List<String> values) {
            addCriterion("ReceiveAddr not in", values, "receiveaddr");
            return (Criteria) this;
        }

        public Criteria andReceiveaddrBetween(String value1, String value2) {
            addCriterion("ReceiveAddr between", value1, value2, "receiveaddr");
            return (Criteria) this;
        }

        public Criteria andReceiveaddrNotBetween(String value1, String value2) {
            addCriterion("ReceiveAddr not between", value1, value2, "receiveaddr");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("Status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("Status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("Status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("Status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("Status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("Status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("Status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("Status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("Status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("Status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("Status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("Status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andProtocolmodelIsNull() {
            addCriterion("ProtocolModel is null");
            return (Criteria) this;
        }

        public Criteria andProtocolmodelIsNotNull() {
            addCriterion("ProtocolModel is not null");
            return (Criteria) this;
        }

        public Criteria andProtocolmodelEqualTo(String value) {
            addCriterion("ProtocolModel =", value, "protocolmodel");
            return (Criteria) this;
        }

        public Criteria andProtocolmodelNotEqualTo(String value) {
            addCriterion("ProtocolModel <>", value, "protocolmodel");
            return (Criteria) this;
        }

        public Criteria andProtocolmodelGreaterThan(String value) {
            addCriterion("ProtocolModel >", value, "protocolmodel");
            return (Criteria) this;
        }

        public Criteria andProtocolmodelGreaterThanOrEqualTo(String value) {
            addCriterion("ProtocolModel >=", value, "protocolmodel");
            return (Criteria) this;
        }

        public Criteria andProtocolmodelLessThan(String value) {
            addCriterion("ProtocolModel <", value, "protocolmodel");
            return (Criteria) this;
        }

        public Criteria andProtocolmodelLessThanOrEqualTo(String value) {
            addCriterion("ProtocolModel <=", value, "protocolmodel");
            return (Criteria) this;
        }

        public Criteria andProtocolmodelLike(String value) {
            addCriterion("ProtocolModel like", value, "protocolmodel");
            return (Criteria) this;
        }

        public Criteria andProtocolmodelNotLike(String value) {
            addCriterion("ProtocolModel not like", value, "protocolmodel");
            return (Criteria) this;
        }

        public Criteria andProtocolmodelIn(List<String> values) {
            addCriterion("ProtocolModel in", values, "protocolmodel");
            return (Criteria) this;
        }

        public Criteria andProtocolmodelNotIn(List<String> values) {
            addCriterion("ProtocolModel not in", values, "protocolmodel");
            return (Criteria) this;
        }

        public Criteria andProtocolmodelBetween(String value1, String value2) {
            addCriterion("ProtocolModel between", value1, value2, "protocolmodel");
            return (Criteria) this;
        }

        public Criteria andProtocolmodelNotBetween(String value1, String value2) {
            addCriterion("ProtocolModel not between", value1, value2, "protocolmodel");
            return (Criteria) this;
        }

        public Criteria andProtocolfileIsNull() {
            addCriterion("ProtocolFile is null");
            return (Criteria) this;
        }

        public Criteria andProtocolfileIsNotNull() {
            addCriterion("ProtocolFile is not null");
            return (Criteria) this;
        }

        public Criteria andProtocolfileEqualTo(String value) {
            addCriterion("ProtocolFile =", value, "protocolfile");
            return (Criteria) this;
        }

        public Criteria andProtocolfileNotEqualTo(String value) {
            addCriterion("ProtocolFile <>", value, "protocolfile");
            return (Criteria) this;
        }

        public Criteria andProtocolfileGreaterThan(String value) {
            addCriterion("ProtocolFile >", value, "protocolfile");
            return (Criteria) this;
        }

        public Criteria andProtocolfileGreaterThanOrEqualTo(String value) {
            addCriterion("ProtocolFile >=", value, "protocolfile");
            return (Criteria) this;
        }

        public Criteria andProtocolfileLessThan(String value) {
            addCriterion("ProtocolFile <", value, "protocolfile");
            return (Criteria) this;
        }

        public Criteria andProtocolfileLessThanOrEqualTo(String value) {
            addCriterion("ProtocolFile <=", value, "protocolfile");
            return (Criteria) this;
        }

        public Criteria andProtocolfileLike(String value) {
            addCriterion("ProtocolFile like", value, "protocolfile");
            return (Criteria) this;
        }

        public Criteria andProtocolfileNotLike(String value) {
            addCriterion("ProtocolFile not like", value, "protocolfile");
            return (Criteria) this;
        }

        public Criteria andProtocolfileIn(List<String> values) {
            addCriterion("ProtocolFile in", values, "protocolfile");
            return (Criteria) this;
        }

        public Criteria andProtocolfileNotIn(List<String> values) {
            addCriterion("ProtocolFile not in", values, "protocolfile");
            return (Criteria) this;
        }

        public Criteria andProtocolfileBetween(String value1, String value2) {
            addCriterion("ProtocolFile between", value1, value2, "protocolfile");
            return (Criteria) this;
        }

        public Criteria andProtocolfileNotBetween(String value1, String value2) {
            addCriterion("ProtocolFile not between", value1, value2, "protocolfile");
            return (Criteria) this;
        }

        public Criteria andProtocolpictIsNull() {
            addCriterion("ProtocolPict is null");
            return (Criteria) this;
        }

        public Criteria andProtocolpictIsNotNull() {
            addCriterion("ProtocolPict is not null");
            return (Criteria) this;
        }

        public Criteria andProtocolpictEqualTo(String value) {
            addCriterion("ProtocolPict =", value, "protocolpict");
            return (Criteria) this;
        }

        public Criteria andProtocolpictNotEqualTo(String value) {
            addCriterion("ProtocolPict <>", value, "protocolpict");
            return (Criteria) this;
        }

        public Criteria andProtocolpictGreaterThan(String value) {
            addCriterion("ProtocolPict >", value, "protocolpict");
            return (Criteria) this;
        }

        public Criteria andProtocolpictGreaterThanOrEqualTo(String value) {
            addCriterion("ProtocolPict >=", value, "protocolpict");
            return (Criteria) this;
        }

        public Criteria andProtocolpictLessThan(String value) {
            addCriterion("ProtocolPict <", value, "protocolpict");
            return (Criteria) this;
        }

        public Criteria andProtocolpictLessThanOrEqualTo(String value) {
            addCriterion("ProtocolPict <=", value, "protocolpict");
            return (Criteria) this;
        }

        public Criteria andProtocolpictLike(String value) {
            addCriterion("ProtocolPict like", value, "protocolpict");
            return (Criteria) this;
        }

        public Criteria andProtocolpictNotLike(String value) {
            addCriterion("ProtocolPict not like", value, "protocolpict");
            return (Criteria) this;
        }

        public Criteria andProtocolpictIn(List<String> values) {
            addCriterion("ProtocolPict in", values, "protocolpict");
            return (Criteria) this;
        }

        public Criteria andProtocolpictNotIn(List<String> values) {
            addCriterion("ProtocolPict not in", values, "protocolpict");
            return (Criteria) this;
        }

        public Criteria andProtocolpictBetween(String value1, String value2) {
            addCriterion("ProtocolPict between", value1, value2, "protocolpict");
            return (Criteria) this;
        }

        public Criteria andProtocolpictNotBetween(String value1, String value2) {
            addCriterion("ProtocolPict not between", value1, value2, "protocolpict");
            return (Criteria) this;
        }

        public Criteria andBuyertimeIsNull() {
            addCriterion("BuyerTime is null");
            return (Criteria) this;
        }

        public Criteria andBuyertimeIsNotNull() {
            addCriterion("BuyerTime is not null");
            return (Criteria) this;
        }

        public Criteria andBuyertimeEqualTo(Timestamp value) {
            addCriterion("BuyerTime =", value, "buyertime");
            return (Criteria) this;
        }

        public Criteria andBuyertimeNotEqualTo(Timestamp value) {
            addCriterion("BuyerTime <>", value, "buyertime");
            return (Criteria) this;
        }

        public Criteria andBuyertimeGreaterThan(Timestamp value) {
            addCriterion("BuyerTime >", value, "buyertime");
            return (Criteria) this;
        }

        public Criteria andBuyertimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("BuyerTime >=", value, "buyertime");
            return (Criteria) this;
        }

        public Criteria andBuyertimeLessThan(Timestamp value) {
            addCriterion("BuyerTime <", value, "buyertime");
            return (Criteria) this;
        }

        public Criteria andBuyertimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("BuyerTime <=", value, "buyertime");
            return (Criteria) this;
        }

        public Criteria andBuyertimeIn(List<Timestamp> values) {
            addCriterionForJDBCTimestamp("BuyerTime in", values, "buyertime");
            return (Criteria) this;
        }

        public Criteria andBuyertimeNotIn(List<Timestamp> values) {
            addCriterionForJDBCTimestamp("BuyerTime not in", values, "buyertime");
            return (Criteria) this;
        }

        public Criteria andBuyertimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("BuyerTime between", value1, value2, "buyertime");
            return (Criteria) this;
        }

        public Criteria andBuyertimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("BuyerTime not between", value1, value2, "buyertime");
            return (Criteria) this;
        }

        public Criteria andSupplyertimeIsNull() {
            addCriterion("SupplyerTime is null");
            return (Criteria) this;
        }

        public Criteria andSupplyertimeIsNotNull() {
            addCriterion("SupplyerTime is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyertimeEqualTo(Timestamp value) {
            addCriterion("SupplyerTime =", value, "supplyertime");
            return (Criteria) this;
        }

        public Criteria andSupplyertimeNotEqualTo(Timestamp value) {
            addCriterion("SupplyerTime <>", value, "supplyertime");
            return (Criteria) this;
        }

        public Criteria andSupplyertimeGreaterThan(Timestamp value) {
            addCriterion("SupplyerTime >", value, "supplyertime");
            return (Criteria) this;
        }

        public Criteria andSupplyertimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("SupplyerTime >=", value, "supplyertime");
            return (Criteria) this;
        }

        public Criteria andSupplyertimeLessThan(Timestamp value) {
            addCriterion("SupplyerTime <", value, "supplyertime");
            return (Criteria) this;
        }

        public Criteria andSupplyertimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("SupplyerTime <=", value, "supplyertime");
            return (Criteria) this;
        }

        public Criteria andSupplyertimeIn(List<Timestamp> values) {
            addCriterionForJDBCTimestamp("SupplyerTime in", values, "supplyertime");
            return (Criteria) this;
        }

        public Criteria andSupplyertimeNotIn(List<Timestamp> values) {
            addCriterionForJDBCTimestamp("SupplyerTime not in", values, "supplyertime");
            return (Criteria) this;
        }

        public Criteria andSupplyertimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SupplyerTime between", value1, value2, "supplyertime");
            return (Criteria) this;
        }

        public Criteria andSupplyertimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SupplyerTime not between", value1, value2, "supplyertime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeIsNull() {
            addCriterion("FinishTime is null");
            return (Criteria) this;
        }

        public Criteria andFinishtimeIsNotNull() {
            addCriterion("FinishTime is not null");
            return (Criteria) this;
        }

        public Criteria andFinishtimeEqualTo(Timestamp value) {
            addCriterion("FinishTime =", value, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeNotEqualTo(Timestamp value) {
            addCriterion("FinishTime <>", value, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeGreaterThan(Timestamp value) {
            addCriterion("FinishTime >", value, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("FinishTime >=", value, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeLessThan(Timestamp value) {
            addCriterion("FinishTime <", value, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("FinishTime <=", value, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeIn(List<Timestamp> values) {
            addCriterionForJDBCTimestamp("FinishTime in", values, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeNotIn(List<Timestamp> values) {
            addCriterionForJDBCTimestamp("FinishTime not in", values, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("FinishTime between", value1, value2, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("FinishTime not between", value1, value2, "finishtime");
            return (Criteria) this;
        }
    }

    /**
     * 采购的协议信息
     *  domain 对应表：wm_purchaseprotocol_t
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria { 


        protected Criteria() {
            super();
        }
    }

    /**
     *  采购的协议信息
     *   对应表： 
     *    wm_purchaseprotocol_t
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