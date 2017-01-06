package com.wangmeng.web.core.velocity;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.velocity.VelocityView;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

/**
 *  自定义VelocityViewResolver
 * 　　　自定义常量
 * @author yikuide
 *
 */
public class XVelocityViewResolver extends VelocityViewResolver {

	public XVelocityViewResolver() {
		super();
	}
	
	private boolean exportServletContextPath = true;
	
	private String ctxPathName = "ctxPath";

	public String getCtxPathName() {
		return ctxPathName;
	}


	public void setCtxPathName(String ctxPathName) {
		this.ctxPathName = ctxPathName;
	}


	public boolean isExportServletContextPath() {
		return exportServletContextPath;
	}


	public void setExportServletContextPath(boolean exportServletContextPath) {
		this.exportServletContextPath = exportServletContextPath;
	}
	
	private Map<String, Object> exportConstants;

	public Map<String, Object> getExportConstants() {
		return exportConstants;
	}

	public void setExportConstants(Map<String, Object> exportConstants) {
		this.exportConstants = exportConstants;
	}

	@Override
	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		VelocityView view = (VelocityView) super.buildView(viewName);
		if(exportServletContextPath){
			//根路径常量
			ServletContext sc = getServletContext();
			view.addStaticAttribute(ctxPathName, sc.getContextPath());
		}
		//自定义常量
		if(exportConstants != null && exportConstants.size()>0){
			Iterator<String> ks = exportConstants.keySet().iterator();
			while(ks.hasNext()){
				String k = ks.next();
				view.addStaticAttribute(k, exportConstants.get(k));
			}
		}
		return view;
	}
}
