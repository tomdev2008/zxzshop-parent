
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<jsp:useBean id="image" scope="page" class="com.wangmeng.action.index.makeCertPic" />

<%
String str=image.getCertPic(0,0,response.getOutputStream());
// 将认证码存入SESSION
session.setAttribute("certCode", str);
out.clear();
out = pageContext.pushBody();

%>

