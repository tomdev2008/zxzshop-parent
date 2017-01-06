package com.wangmeng.mybatis;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wangmeng.common.utils.Reflections;
import com.wangmeng.filter.ClauseGroupVerb;
import com.wangmeng.filter.CriterionVerb;
import com.wangmeng.filter.XClause;
import com.wangmeng.query.ABaseDaoFilter;


/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： FriendlyException          <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月7日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  条件调用帮助
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class CriterionHelper {

	private static final Log log = LogFactory.getLog(CriterionHelper.class); 
	
    /**
     * 首字符大写
     * @param data
     * @return
     */
    private static String capitalizeFirstLetter ( String data )
    {
        String firstLetter = data.substring(0,1).toUpperCase();
        String restLetters = data.substring(1);
        return firstLetter + restLetters;
    }
	
	/**
	 * 条件调用实现
	 * 
	 * @param criteria 可以是一个Example或一个Criteria，
	 * 					如果是Example且设置了XClause为root类型且clause的children非空，则可使用子clause的作为子条件 XXX 待测试
	 * @param clause
	 */
	public static void push(Object criteria, XClause clause){
		if(criteria instanceof ABaseDaoFilter){
			if(criteria != null && clause != null){
				if(clause.isRoot() && clause.hasChildren() && clause.getRootVerb()!=null){
					//当有子条件的时候
					if(clause.getRootVerb().equalsIgnoreCase(ClauseGroupVerb.OR.name())){
						pushInner(((ABaseDaoFilter<?>)criteria).or(), clause.getChildren().toArray(new XClause[0]));
					}else{
						pushInner(((ABaseDaoFilter<?>)criteria).getScalarExistedCriteria(), clause.getChildren().toArray(new XClause[0]));
					}
				}else{
					if(!clause.isRoot() && clause.hasChildren()){
						pushInner(((ABaseDaoFilter<?>)criteria).getScalarExistedCriteria(), clause);
					}
				}
			}
		}else{
			pushInner(criteria, clause);
		}
	}

	/**
	 * 内部条件调用方法
	 * @param criteria
	 * @param clauses
	 */
	private static void pushInner(Object criteria, XClause... clauses){
		if(criteria != null && clauses != null && clauses.length>0){
			for(XClause clause : clauses){
				if (clause.isNatived()) {
					evalNativedCriteria(criteria, clause.getDesc());
				}else{
					if(clause.getObj() == null 
							|| (!(
								 clause.getVerb().equalsIgnoreCase(CriterionVerb.ISNOTNULL.name()) 
								 || clause.getVerb().equalsIgnoreCase(CriterionVerb.ISNULL.name()) 
								 ) && clause.getDesc() == null) 
							|| ((clause.getDesc() instanceof String) && (clause.getDesc().toString().trim().length() == 0))){ 
						 return;
					} 
					CriterionVerb verb = CriterionVerb.valueOf(clause.getVerb());
					switch(verb){
					case EQ:
						evalEqCriteria(criteria, clause.getObj(), clause.getDesc());
						break;
					case GE:
						evalGeCriteria(criteria, clause.getObj(), clause.getDesc());
						break;
					case GT:
						evalGtCriteria(criteria, clause.getObj(), clause.getDesc());
						break;
					case LE:
						evalLeCriteria(criteria, clause.getObj(), clause.getDesc());
						break;
					case LLIKE:
						evalLeftLikeCriteria(criteria, clause.getObj(), clause.getDesc());
						break;
					case NOTLLKIE:
						evalNotLeftLikeCriteria(criteria, clause.getObj(), clause.getDesc());
						break;
					case RLIKE:
						evalRightLikeCriteria(criteria, clause.getObj(), clause.getDesc());
						break;
					case NOTRLKIE:
						evalNotRightLikeCriteria(criteria, clause.getObj(), clause.getDesc());
						break;
					case LIKE:
						evalLikeCriteria(criteria, clause.getObj(), clause.getDesc());
						break;
					case NOTLKIE:
						evalNotLikeCriteria(criteria, clause.getObj(), clause.getDesc());
						break;
					case LT:
						evalLtCriteria(criteria, clause.getObj(), clause.getDesc());
						break;
					case BETWEEN:
						evalBetweenCriteria(criteria, clause.getObj(), clause.getDesc());
						break;
					case IN:
						evalInCriteria(criteria, clause.getObj(), clause.getDesc());
						break;
					case ISNOTNULL:
						evalIsNotNullCriteria(criteria, clause.getObj());
						break;
					case ISNULL:
						evalIsNullCriteria(criteria, clause.getObj());
						break;
					case NOTBETWEEN:
						evalNotBetweenCriteria(criteria, clause.getObj(), clause.getDesc());
						break;
					case NOTEQ:
						evalNotEqCriteria(criteria, clause.getObj(), clause.getDesc());
						break;
					case NOTIN:
						evalNotInCriteria(criteria, clause.getObj(), clause.getDesc());
						break;
					default:
						evalEqCriteria(criteria, clause.getObj(), clause.getDesc());
						break;
				}
				}
				
				
			}
		}
	}

	private static void evalNativedCriteria(Object criteria, Object desc) {
		if(desc != null){
			String mtd = "addCriterion";
			try {
				Method mtdV = Reflections.getAccessibleMethodSimple(criteria, mtd, desc.getClass());
				if (mtdV!=null) {
					mtdV.invoke(criteria, desc);
				}else{
					log.error("evalNativedCriteria cannot find method addCriterion with para type: " + desc.getClass());
				}
			} catch (Exception e) {
				log.error("evalNativedCriteria "+criteria.getClass()+"."+mtd, e);
			} 
		}
	}

	/**
	 * not like %xx%
	 * @param criteria
	 * @param obj
	 * @param desc
	 */
	private static void evalNotLikeCriteria(Object criteria, String obj, Object desc) {
		if(desc != null){
			String mtd = "and"+capitalizeFirstLetter(obj)+"NotLike";
			try {
				Reflections.findMethodS(criteria.getClass(), mtd).invoke(criteria, "%"+desc+"%");
			} catch (Exception e) {
				log.error("evalNotLikeCriteria "+criteria.getClass()+"."+mtd, e);

			} 
		}
	}
	
	/**
	 * not like %xx
	 * @param criteria
	 * @param obj
	 * @param desc
	 */
	private static void evalNotLeftLikeCriteria(Object criteria, String obj, Object desc) {
		if(desc != null){
			String mtd = "and"+capitalizeFirstLetter(obj)+"NotLike";
			try {
				Reflections.findMethodS(criteria.getClass(), mtd).invoke(criteria, "%"+desc);
			} catch (Exception e) {
				log.error("evalNotLeftLikeCriteria "+criteria.getClass()+"."+mtd, e);

			} 
		}
	}
	
	/**
	 * not like xx%
	 * @param criteria
	 * @param obj
	 * @param desc
	 */
	private static void evalNotRightLikeCriteria(Object criteria, String obj, Object desc) {
		if(desc != null){
			String mtd = "and"+capitalizeFirstLetter(obj)+"NotLike";
			try {
				Reflections.findMethodS(criteria.getClass(), mtd).invoke(criteria, desc+"%");
			} catch (Exception e) {
				log.error("evalNotRightLikeCriteria "+criteria.getClass()+"."+mtd, e);

			} 
		}
	}

	/**
	 * not in (x1, x2)
	 * @param criteria
	 * @param obj
	 * @param desc
	 */
	private static void evalNotInCriteria(Object criteria, String obj, Object desc) {
		if(desc != null && desc instanceof List<?>){
			String mtd = "and"+capitalizeFirstLetter(obj)+"NotIn";
			try {
				Reflections.findMethodS(criteria.getClass(), mtd).invoke(criteria, desc);
			} catch (Exception e) {
				log.error("evalNotInCriteria "+criteria.getClass()+"."+mtd, e);

			} 
		}
		
	}

	/**
	 * <>
	 * @param criteria
	 * @param obj
	 * @param desc
	 */
	private static void evalNotEqCriteria(Object criteria, String obj, Object desc) {
		if(desc != null){
			String mtd = "and"+capitalizeFirstLetter(obj)+"NotEqualTo";
			try {
				Reflections.findMethodS(criteria.getClass(), mtd).invoke(criteria, desc);
			} catch (Exception e) {
				log.error("evalNotEqCriteria "+criteria.getClass()+"."+mtd, e);
				//e.printStackTrace();
			} 
		}
	}

	/**
	 * not between (x1, x2)
	 * @param criteria
	 * @param obj
	 * @param desc
	 */
	private static void evalNotBetweenCriteria(Object criteria, String obj, Object desc) {
		if(desc != null && ArrayUtils.getLength(desc) == 1){
			Object obj0 = Array.get(desc, 0);
			Object obj1 = Array.get(desc, 1);
			String mtd = "and"+capitalizeFirstLetter(obj)+"NotBetween";
			try {
				Reflections.findMethodS(criteria.getClass(), mtd).invoke(criteria, obj0, obj1);
			} catch (Exception e) {
				log.error("evalNotBetweenCriteria "+criteria.getClass()+"."+mtd, e);

			} 
		}
	}

	/**
	 * is null
	 * @param criteria
	 * @param obj
	 */
	private static void evalIsNullCriteria(Object criteria, String obj) {
		String mtd = "and"+capitalizeFirstLetter(obj)+"IsNull";
		try {
			Reflections.findMethodS(criteria.getClass(), mtd).invoke(criteria);
		} catch (Exception e) {
			log.error("evalIsNullCriteria "+criteria.getClass()+"."+mtd, e);

		} 
	}

	/**
	 * is not null
	 * @param criteria
	 * @param obj
	 */
	private static void evalIsNotNullCriteria(Object criteria, String obj) {
		String mtd = "and"+capitalizeFirstLetter(obj)+"IsNotNull";
		try {
			Reflections.findMethodS(criteria.getClass(), mtd).invoke(criteria);
		} catch (Exception e) {
			log.error("evalIsNotNullCriteria "+criteria.getClass()+"."+mtd, e);

		} 
	}

	/**
	 * in (x1, x2, x3)
	 * @param criteria
	 * @param obj
	 * @param desc
	 */
	private static void evalInCriteria(Object criteria, String obj, Object desc) {
		if(desc != null && desc instanceof List<?>){
			String mtd = "and"+capitalizeFirstLetter(obj)+"In";
			try {
				Reflections.findMethodS(criteria.getClass(), mtd).invoke(criteria, desc);
			} catch (Exception e) {
				log.error("evalInCriteria "+criteria.getClass()+"."+mtd, e);

			} 
		}
	}

	/**
	 * between x1, x2
	 * @param criteria
	 * @param obj
	 * @param desc
	 */
	private static void evalBetweenCriteria(Object criteria, String obj, Object desc) {
		if(desc != null && ArrayUtils.getLength(desc) == 1){
			Object obj0 = Array.get(desc, 0);
			Object obj1 = Array.get(desc, 1);
			String mtd = "and"+capitalizeFirstLetter(obj)+"Between";
			try {
				Reflections.findMethodS(criteria.getClass(), mtd).invoke(criteria, obj0, obj1);
			} catch (Exception e) {
				log.error("evalBetweenCriteria "+criteria.getClass()+"."+mtd, e);

			} 
		}
	}
 

	/**
	 * <
	 * @param criteria
	 * @param obj
	 * @param desc
	 */
	private static void evalLtCriteria(Object criteria, String obj, Object desc) {
		if(desc != null){
			String mtd = "and"+capitalizeFirstLetter(obj)+"LessThan";
			try {
				Reflections.findMethodS(criteria.getClass(), mtd).invoke(criteria, desc);
			} catch (Exception e) {
				log.error("evalLtCriteria "+criteria.getClass()+"."+mtd, e);

			} 
		}
	}

	/**
	 * like %xx%
	 * @param criteria
	 * @param obj
	 * @param desc
	 */
	private static void evalLikeCriteria(Object criteria, String obj, Object desc) {
		if(desc != null){
			String mtd = "and"+capitalizeFirstLetter(obj)+"Like";
			try {
				Reflections.findMethodS(criteria.getClass(), mtd).invoke(criteria, "%"+desc+"%");
			} catch (Exception e) {
				log.error("evalLikeCriteria "+criteria.getClass()+"."+mtd, e);

			} 
		}
	}
	
	/**
	 * like %xx
	 * @param criteria
	 * @param obj
	 * @param desc
	 */
	private static void evalLeftLikeCriteria(Object criteria, String obj, Object desc) {
		if(desc != null){
			String mtd = "and"+capitalizeFirstLetter(obj)+"Like";
			try {
				Reflections.findMethodS(criteria.getClass(), mtd).invoke(criteria, "%"+desc);
			} catch (Exception e) {
				log.error("evalLeftLikeCriteria "+criteria.getClass()+"."+mtd, e);

			} 
		}
	}
	
	/**
	 * like xx%
	 * @param criteria
	 * @param obj
	 * @param desc
	 */
	private static void evalRightLikeCriteria(Object criteria, String obj, Object desc) {
		if(desc != null){
			String mtd = "and"+capitalizeFirstLetter(obj)+"Like";
			try {
				Reflections.findMethodS(criteria.getClass(), mtd).invoke(criteria, desc+"%");
			} catch (Exception e) {
				log.error("evalRightLikeCriteria "+criteria.getClass()+"."+mtd, e);

			} 
		}
	}

	/**
	 * <=
	 * @param criteria
	 * @param obj
	 * @param desc
	 */
	private static void evalLeCriteria(Object criteria, String obj, Object desc) {
		if(desc != null){
			String mtd = "and"+capitalizeFirstLetter(obj)+"LessThanOrEqualTo";
			try {
				Reflections.findMethodS(criteria.getClass(), mtd).invoke(criteria, desc);
			} catch (Exception e) {
				log.error("evalLeCriteria "+criteria.getClass()+"."+mtd, e);
			} 
		}
	}

	/**
	 * >
	 * @param criteria
	 * @param obj
	 * @param desc
	 */
	private static void evalGtCriteria(Object criteria, String obj, Object desc) {
		if(desc != null){
			String mtd = "and"+capitalizeFirstLetter(obj)+"GreaterThan";
			try {
				Reflections.findMethodS(criteria.getClass(), mtd).invoke(criteria, desc);
			} catch (Exception e) {
				log.error("evalGtCriteria "+criteria.getClass()+"."+mtd, e);
			} 
		}
	}

	/**
	 * >=
	 * @param criteria
	 * @param obj
	 * @param desc
	 */
	private static void evalGeCriteria(Object criteria, String obj, Object desc) {
		if(desc != null){
			String mtd = "and"+capitalizeFirstLetter(obj)+"GreaterThanOrEqualTo";
			try {
				Reflections.findMethodS(criteria.getClass(), mtd).invoke(criteria, desc);
			} catch (Exception e) {
				log.error("evalGeCriteria "+criteria.getClass()+"."+mtd, e);
			} 
		}
	}

	/**
	 *  =
	 *  
	 * @param criteria
	 * @param obj
	 * @param desc
	 */
	private static void evalEqCriteria(Object criteria, String obj, Object desc) {
		if(desc != null){
			String mtd = "and"+capitalizeFirstLetter(obj)+"EqualTo";
			try {
				Reflections.findMethodS(criteria.getClass(), mtd).invoke(criteria, desc);
			} catch (Exception e) {
				log.error("evalEqCriteria "+criteria.getClass()+"."+mtd, e);
				//e.printStackTrace();
			} 
		}
	}
	
}
