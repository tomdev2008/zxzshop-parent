package com.wangmeng.common.pagination;

import java.util.List;
import java.util.Map;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： PageView          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Nov 22, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  分页元数据
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class PageView<T> implements IPageView<T> {
	
	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 7560588552323451692L;
	
	private boolean hasError;
	
	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	/**
	 * 构造器
	 */
	public PageView() {
		super();
		this.fuzzPagination = 0;
		this.dataMode = 0;
	}
	
	/**
	 * 构造器
	 *@param dataMode 数据模式
	 */
	public PageView(short dataMode) {
		super();
		this.dataMode = dataMode;
	}

	/**
	 * 构造器
	 * @param fuzzPagination 分页模式
	 * @param dataMode 数据模式
	 */
	public PageView(short fuzzPagination, short dataMode) {
		super();
		this.fuzzPagination = fuzzPagination;
		this.dataMode = dataMode;
	}
	
	/**
	 * 
	 * @param pageSize
	 * @param pageNo
	 * @param total
	 */
	public PageView(int pageSize, int pageNo, int total) {
		super();
		this.fuzzPagination = 0;
		this.dataMode = 0;
		this.push(pageSize, pageNo, total);
	}

	/**
	 * 分页模式
	 *  0 : 常规分页模式, 即有总页数，总记录数等 
	 *  1 : 模糊分页模式, 无总页数，总记录数等，只保留上一页，下一页等一些简要分页数据
	 */
	private short fuzzPagination = 0;


	public short getFuzzPagination() {
		return fuzzPagination;
	}

	public void setFuzzPagination(short fuzzPagination) {
		this.fuzzPagination = fuzzPagination;
	}
	
	/**
	 * 排序建议
	 */
	private String orders;
	
	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

	/**
	* 数据模式
	*/
	private short dataMode = 0;

	/**
	 * 数据集
	 */
	private List<T> dataList;
	
	/**
	 * 单个数据
	 */
	private Object dataSingle;
	
	/**
	* 数据集内容
	* 用于JSON等其他方式返回的数据集
	*/
	private String dataListPlainContent;

	/**
	 * 分页大小
	 */
	private int pageSize;
	
	/**
	 * 总页数
	 */
	private int totalPage;
	
	/**
	 * 总记录数
	 */
	private int totalRs;
	
	/**
	 * 当前页面索引
	 */
	private int pageNo;
	
	/**
	 * 原始请求页面索引
	 */
	private int reqPageNo;

	/**
	 * 当前页记录索引开始
	 */
	private int rsIndexFrom;
	
	/**
	 * 当前页记录索引结束
	 */
	private int rsIndexTo;
	
    /**
     * 显示在页面开始记录号，从1开始
     */
	private int viewStart;

    /**
     * 显示在页面结束记录号
     */
    private int viewEnd;
	
	/**
	 * 是否有第一页
	 */
	private boolean hasFirstPage;
	
	/**
	 * 是否有最末页
	 */
	private boolean hasLastPage;
	
    /**
     * 是否有上一页的开关
     */
	private boolean hasPrevious;
    
    /**
     * 是否有下一页的开关
     */
    private boolean hasNext;
    
    /**
     * 上一页的页码
     */
    private int previousPageNo;
    
    /**
     * 下一页的页码
     */
    private int nextPageNo;
    
	private int rsOffset;
	
	public void setRsOffset(int rsOffset) {
		this.rsOffset = rsOffset;
	}

	public int getRsOffset() {
		return rsOffset;
	}
    
    public int getViewStart() {
		return viewStart;
	}

	public void setViewStart(int viewStart) {
		this.viewStart = viewStart;
	}

	public int getViewEnd() {
		return viewEnd;
	}

	public void setViewEnd(int viewEnd) {
		this.viewEnd = viewEnd;
	}

	public boolean isHasPrevious() {
		return hasPrevious;
	}

	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public int getPreviousPageNo() {
		return previousPageNo;
	}

	public void setPreviousPageNo(int previousPageNo) {
		this.previousPageNo = previousPageNo;
	}

	public int getNextPageNo() {
		return nextPageNo;
	}

	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}

	public int getLastPageNo() {
		return lastPageNo;
	}

	public void setLastPageNo(int lastPageNo) {
		this.lastPageNo = lastPageNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
     * 最后页的页码
     */
    private int lastPageNo;

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public Object getDataSingle() {
		return dataSingle;
	}

	public void setDataSingle(Object dataSingle) {
		this.dataSingle = dataSingle;
	}

	public String getDataListPlainContent() {
		return dataListPlainContent;
	}

	public void setDataListPlainContent(String dataListPlainContent) {
		this.dataListPlainContent = dataListPlainContent;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRs() {
		return totalRs;
	}

	public void setTotalRs(int totalRs) {
		this.totalRs = totalRs;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageIndex) {
		this.pageNo = pageIndex;
	}

	public int getRsIndexFrom() {
		return rsIndexFrom;
	}

	public void setRsIndexFrom(int rsIndexFrom) {
		this.rsIndexFrom = rsIndexFrom;
	}

	public int getRsIndexTo() {
		return rsIndexTo;
	}

	public void setRsIndexTo(int rsIndexTo) {
		this.rsIndexTo = rsIndexTo;
	}

	public boolean isHasFirstPage() {
		return hasFirstPage;
	}

	public void setHasFirstPage(boolean hasFirstPage) {
		this.hasFirstPage = hasFirstPage;
	}

	public boolean isHasLastPage() {
		return hasLastPage;
	}

	public void setHasLastPage(boolean hasLastPage) {
		this.hasLastPage = hasLastPage;
	}

	public void setDataMode(short dataMode) {
		this.dataMode = dataMode;
	}

	public short getDataMode() {
		return dataMode;
	}

	public void push(int pageSize, int pageNo, int total) {
        this.pageSize = pageSize;
        this.totalRs = total;
        this.pageNo = pageNo;
        
        if (0 == total){
        	pageNo = 0;
        }
        else{
            autoCalculate();
        }
	}

	/**
	 * 计算分页元数据
	 */
	private void autoCalculate() {
		if(0 == pageSize){
    		pageSize = defaultPageSize;
    	}

        totalPage = (totalRs + pageSize - 1) / pageSize;
        
        if(pageNo<=0){
        	pageNo = 1;
        }
        if(pageNo>totalPage){
            this.reqPageNo = pageNo;
        	pageNo = totalPage;
        }
        
        rsIndexFrom = (pageNo - 1) * pageSize;
        
        //rsOffset = rsIndexFrom + 1;
        rsOffset = rsIndexFrom;
        
        rsIndexTo = rsIndexFrom + pageSize - 1;
        if (rsIndexTo > totalRs) {
            rsIndexTo = totalRs;
        }
        viewStart = rsIndexFrom + 1;
        viewEnd = rsIndexTo + 1;
        
        if (1 == pageNo) {
            hasPrevious = false;
            previousPageNo = 1;
        } else {
            hasPrevious = true;
            previousPageNo = pageNo - 1;
        }
        if (pageNo == totalPage) {
            hasNext = false;
            nextPageNo = totalPage;
        } else {
            hasNext = true;
            nextPageNo = pageNo + 1;
        }
        if(totalPage>0){
        	lastPageNo = totalPage;
        }else{
        	totalPage = 0;
        }
	}
	
	/**
	 * 时间戳
	 */
	private String timestamp;

	/**
	 * @return
	 * @see com.twioo.common.IPageView#getTimestamp()
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 * @see com.twioo.common.IPageView#setTimestamp(java.lang.String)
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	private Long timestampLong;

	/**
	 * @return
	 */
	public Long getTimestampLong() {
		return timestampLong;
	}

	/**
	 * @param timestampLong
	 */
	public void setTimestampLong(Long timestampLong) {
		this.timestampLong = timestampLong;
	}
	
	/**
	 * 附加数据集
	 */
	private List<?> dataListPlus;

	/**
	 * @return
	 * @see com.twioo.common.IPageView#getDataListPlus()
	 */
	public List<?> getDataListPlus() {
		return dataListPlus;
	}

	/**
	 * @param dataListPlus
	 * @see com.twioo.common.IPageView#setDataListPlus(java.util.List)
	 */
	public void setDataListPlus(List<?> dataListPlus) {
		this.dataListPlus = dataListPlus;
	}
	
	public int getReqPageNo() {
		return reqPageNo;
	}
	
	/**
	 * 附加MAP
	 */
	private Map<?, ?> dataMapPlus;

	public Map<?, ?> getDataMapPlus() {
		return dataMapPlus;
	}

	public void setDataMapPlus(Map<?, ?> dataMapPlus) {
		this.dataMapPlus = dataMapPlus;
	}
 
}
