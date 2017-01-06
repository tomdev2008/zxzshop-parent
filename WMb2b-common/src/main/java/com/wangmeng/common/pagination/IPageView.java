package com.wangmeng.common.pagination;

import java.util.List;
import java.util.Map;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： IPageView          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Nov 22, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 * 分页元数据接口
 * 用于少数据量的数据分页
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface IPageView<T> extends java.io.Serializable {
	
	/**
	 * 默认分页每页记录数
	 */
	public static final int defaultPageSize = 15;
	
	/**
	 * 取得排序
	 * @return
	 */
	public abstract String getOrders();

	/**
	 * 设置排序
	 * @param orders
	 */
	public abstract void setOrders(String orders);
	
	/**
	 * 设置数据模式
	 * 0: dataList
	 * 1: dataListPlainContent
	 * 2: dataList & dataListPlainContent
	 * @param dataMode
	 */
	public abstract void setDataMode(short dataMode);
	
	/**
	 * 取得数据模式
	 * @return
	 */
	public abstract short getDataMode();

	/**
	 * 设置是否有最末页
	 * @param hasLastPage
	 */
	public abstract void setHasLastPage(boolean hasLastPage);

	/**
	 * 是否有最末页
	 * @return
	 */
	public abstract boolean isHasLastPage();

	/**
	 * 设置是否有第一页
	 * @param hasFirstPage
	 */
	public abstract void setHasFirstPage(boolean hasFirstPage);

	/**
	 * 是否有第一页
	 * @return
	 */
	public abstract boolean isHasFirstPage();

	/**
	 * 设置当前页记录索引结束
	 * @param rsIndexTo
	 */
	public abstract void setRsIndexTo(int rsIndexTo);

	/**
	 * 取当前页记录索引结束
	 * @return
	 */
	public abstract int getRsIndexTo();

	/**
	 * 设置当前页记录索引开始
	 * @param rsIndexFrom
	 */
	public abstract void setRsIndexFrom(int rsIndexFrom);

	/**
	 * 取当前页记录索引开始
	 * @return
	 */
	public abstract int getRsIndexFrom();

	/**
	 * 设置当前页面索引
	 * @param pageIndex
	 */
	public abstract void setPageNo(int pageIndex);

	/**
	 * 取当前页面索引
	 * @return
	 */
	public abstract int getPageNo();
	
	/**
	 * 获取原始请求的页面
	 * @return
	 */
	public abstract int getReqPageNo();
	

	/**
	 * 设置总记录数
	 * @param totalRs
	 */
	public abstract void setTotalRs(int totalRs);

	/**
	 * 取总记录数
	 * @return
	 */
	public abstract int getTotalRs();

	/**
	 * 设置总页数
	 * @param totalPage
	 */
	public abstract void setTotalPage(int totalPage);

	/**
	 * 取总页数
	 * @return
	 */
	public abstract int getTotalPage();

	/**
	 * 设置分页大小
	 * @param pageSize
	 */
	public abstract void setPageSize(int pageSize);

	/**
	 * 取分页大小
	 * @return
	 */
	public abstract int getPageSize();

	/**
	 * 设置数据集
	 * @param dataList
	 */
	public abstract void setDataList(List<T> dataList);

	/**
	 * 取数据集
	 * @return
	 */
	public abstract List<T> getDataList();
	
	/**
	 * 设置附加数据集
	 * @param dataList
	 */
	public abstract void setDataListPlus(List<?> dataList);

	/**
	 * 取附加数据集
	 * @return
	 */
	public abstract List<?> getDataListPlus();
	
	/**
	 * 设置附加数据集MAP
	 * @param map
	 */
	public abstract void setDataMapPlus(Map<?, ?> map);

	/**
	 * 取附加数据集MAP
	 * @return
	 */
	public abstract Map<?, ?> getDataMapPlus();
	
	/**
	 * 取数据集内容
	 * 用于JSON等其他方式返回的数据集
	 * @return
	 */
	public abstract String getDataListPlainContent();
	
	/**
	 * 设置数据集内容
	 * 用于JSON等其他方式返回的数据集
	 * @param dataListPlainContent
	 */
	public abstract void setDataListPlainContent(String dataListPlainContent);
	
	/**
	 * 计算分页数据
	 * @param pageSize
	 * @param pageNo
	 * @param total
	 */
	public abstract void push(int pageSize, int pageNo, int total);
	
	/**
	 * 取得时间戳
	 * @return
	 */
	public abstract String getTimestamp();
	
	/**
	 * 设置时间戳
	 * @param timestamp
	 */
	public abstract void setTimestamp(String timestamp);
	
	/**
	 * 取得时间戳
	 * @return
	 */
	public abstract Long getTimestampLong();
	
	/**
	 * 设置时间戳
	 * @param timestamp
	 */
	public abstract void setTimestampLong(Long timestampLong);
	
	/**
	 * 设置当前页记录Offset
	 * @param rsIndexFrom
	 */
	public abstract void setRsOffset(int rsOffset);
	
	/**
	 * 取当前页记录Offset
	 * @return
	 */
	public abstract int getRsOffset();

	void setError(String error);

	String getError();

	void setHasError(boolean hasError);

	boolean isHasError();	
	
}
