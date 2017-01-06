<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'ErpInfo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="../../../css/common.css" type="text/css"></link>
<link rel="stylesheet" href="../../../css/core.css" type="text/css"></link>
<link rel="stylesheet" href="../../../css/style.css" type="text/css"></link>
</head>

<body>
	<div class="user-set userset-lcol form border-box">
		<div class="InquiryInfo">
			<h3 class="table-hd">询价信息</h3>
			
			<div class="item">
				<span class="label">${SheetName}号：${InquiryNo} (${IsPic})</span>
				<div class="fl"></div>
			</div>
			<div class="item">
				<span class="label">工程名称：${InquiryTitle}</span>
				<div class="fl"></div>
			</div>
			<div class="item">
				<span class="label">询价时间：${SubmitTime}</span>
				<div class="fl"></div>
			</div>			
		</div>
		<div class="UserInfo">
			<h3 class="table-hd">用户信息</h3>
			<div class="item">
				<span class="label">单位名称：${CompanyName}</span>
				<div class="fl"></div>
			</div>
			<div class="item">
				<span class="label">用户名称：${UserName}</span>
				<div class="fl"></div>
			</div>
			<div class="item">
				<span class="label">联系电话：${CellPhone}</span>
				<div class="fl"></div>
			</div>

		</div>
		<div>
			<h3 class="table-hd">提示信息</h3>
			<div class="item">
				<span class="label"><textArea
						style="width:220px;height:120px">${SheetName}已上传成功，请您告诉我希望几分钟内回复您的报价？</textArea>
				</span>
			</div>
		</div>
	</div>

</body>
</html>
