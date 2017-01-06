package com.wangmeng.parser.json;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： TypeInference          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月10日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 * 泛型类型推断
 * 
 * 类似 fasterxml jackson TypeReference
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public abstract class TypeInference<T> implements Comparable<TypeInference<T>> {
	
	protected final Type _type;
	
	protected TypeInference() {
		Type superClass = getClass().getGenericSuperclass();
		if (superClass instanceof Class<?>) { // sanity check, should never happen
			throw new IllegalArgumentException(
					"Internal error: TypeReference constructed without actual type information");
		}
		/*
		 * 22-Dec-2008, tatu: Not sure if this case is safe -- I suspect it is
		 * possible to make it fail? But let's deal with specific case when we
		 * know an actual use case, and thereby suitable workarounds for valid
		 * case(s) and/or error to throw on invalid one(s).
		 */
		_type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
	}

	public Type getType() {
		return _type;
	}

	/**
	 * The only reason we define this method (and require implementation of
	 * <code>Comparable</code>) is to prevent constructing a reference without
	 * type information.
	 */
	public int compareTo(TypeInference<T> o) {
		// just need an implementation, not a good one... hence:
		return 0;
	}
}