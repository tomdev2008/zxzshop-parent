<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.wangmeng.action.ueditor.ActionEnter_s"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%

    request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");
	
	String rootPath = application.getRealPath( "/" );
	
	out.write( new ActionEnter_s( request, rootPath ).exec() );
	
%>