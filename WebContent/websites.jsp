<%@page import="java.util.HashSet"%>
<%@page import="javabeans.User"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="javabeans.WebInfo"%>
<%@page import="java.util.LinkedList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="commons/header.jsp"%>
<script type="text/javascript" src="MY-JS/websites.js"></script>
<title>常用网站</title>
</head>
<body>
	<c:if test="${empty webInfos}">
		<script type="text/javascript">
			showNoticeMSG('没有网站信息');
		</script>
	</c:if>
	<div class="container">
		<jsp:include page="commons/mynav.jsp"></jsp:include>
		<div class="row">
			<c:if test="${not empty user && user.hasBasePermission()}">
				<!-- 管理员组件 -->
				<jsp:include page="commons/admin_operate.html"></jsp:include>
				<!-- 显示模块 -->
				<div id="show col-md-12">
					<div id="admin-operate" class="col-md-12  col-xs-12 text-center">
						<div class="col-md-10 col-md-offset-1 col-xs-12">
							<span class="label label-success">后台管理</span>
							<ul class="nav nav-tabs text-center">
								<li><a data-operate="delete">删除常用网站</a></li>
								<li><a data-operate="add">增加常用网站</a></li>
							</ul>
						</div>
					</div>
				</div>
			</c:if>
			<div id="website-info" class="col-md-12 text-center"></div>
		</div>
		<c:forEach var="web" items="${webInfos}">
			<div class='col-xs-6 col-md-3' role="item">
				<div class='col-xs-12 text-center'>
					<img src='<%=contextPath%>/${web.imgURL}' class='img-thumbnail image-rounded img-responsive' />
				</div>
				<div class='col-xs-12'>
					<h4 class='text-center'>${web.webName}</h4>
				</div>
				<div class='webDesc col-xs-12'>
					<textarea class='margin-top-bottom-10px full-width' rows="5" disabled="disabled">${web.webDesc}</textarea>
				</div>
				<div class='text- center col-xs-12'>
					<a target="_blank" type='button'
						class='margin-top-bottom-10px btn btn-info col-md-12 col-xs-12' href='${web.aimURL}'>立即前往</a>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>