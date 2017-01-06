package com.wangmeng.common.pagination;

import com.wangmeng.contants.Constant;
import com.wangmeng.model.AbstractSerializable;

/**
 * 分页对象
 * @author 朱飞 
 * [2016-10-11下午2:01:51] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
public class PageInfo extends AbstractSerializable {

    private static final long serialVersionUID = 587754556498974978L;
    
    //每一页显示多少,默认显示10条数据
    private int pageSize = 10 ;
    //总页数
    private int totalPage=1;
    //总记录数
    private int totalResult;
    //当前页数
    private int currentPage = 1;
    
    //下一页的第一个数据
    private int offSet;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		this.offSet = (this.currentPage == 0 ?0:this.currentPage -1) * pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

//	public void evalTotalPage() {
//		if (pageSize<=0) {
//			pageSize = 10;
//		}
//		int _totalPage = 0;
//		if(totalResult % pageSize == 0){
//			_totalPage = totalResult/pageSize;
//		}else{
//			_totalPage = totalResult/pageSize+1;
//		}
//		this.totalPage = _totalPage;
//	}
	public int getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int page) {
		this.currentPage = page;
		setOffSetByCurrentPage(page);
	}

	public int getOffSet() {
		return offSet;
	}

	public void setOffSet(int offSet) {
		this.offSet = offSet;
	}

	public void setOffSetByCurrentPage(int page){
		this.currentPage = page;
		if(pageSize == 0){
			pageSize = Constant.PAGESHOWSIZE;
		}
		if(page == 0){
			page = 1;
		}
		this.offSet = (page-1) * pageSize;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PageInfo [pageSize=");
		builder.append(pageSize);
		builder.append(", totalPage=");
		builder.append(totalPage);
		builder.append(", totalResult=");
		builder.append(totalResult);
		builder.append(", currentPage=");
		builder.append(currentPage);
		builder.append(", offSet=");
		builder.append(offSet);
		builder.append("]");
		return builder.toString();
	}
}
