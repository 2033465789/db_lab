<%@page import="javabeans.DormitoryInfo"%>
<%@page import="javabeans.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jstl -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
function logOut() {
    $.ajax({
        url: "<%=request.getContextPath()%>/LogOut",
        type: "post",
        success: function (resp, status) {
            dealResponse(resp, status, "注销失败");
        }
    });
}
</script>
<nav class="navbar navbar-expand-sm bg-light">
	<a class="navbar-brand" href="<%=request.getContextPath()%>">PiaoPiao</a>
	<ul class="navbar-nav">
		<!-- 失物招领 -->
		<li class="dropdown nav-item"><a href="" class="dropdown-toggle nav-link"
			data-toggle="dropdown">失物招领</a>
			<div class="dropdown-menu">
				<a href="<%=request.getContextPath()%>/finder" class="dropdown-item">我捡到物品</a> <a
					href="<%=request.getContextPath()%>/loster" class="dropdown-item">我丢失物品</a>
			</div></li>
		<li class="dropdown nav-item"><a href="" class="dropdown-toggle nav-link"
			data-toggle="dropdown">资源共享</a>
			<div class="dropdown-menu">
				<a href="<%=request.getContextPath()%>/upload_resource" class="dropdown-item">上传资源</a> <a
					href="<%=request.getContextPath()%>/DownloadResource" class="dropdown-item">下载资源</a>
			</div></li>
		<li class="nav-item"><a href="<%= request.getContextPath() %>/Website" class="nav-link">常用网址</a></li>
		<c:if test="${empty user}">
			<li class="nav-item"><a href="<%= request.getContextPath() %>/signup.html" class="nav-link">注册</a></li>
			<li class="nav-item"><a onclick="show_login()" class="nav-link">登录</a></li>
		</c:if>
		<c:if test="${not empty user}">
			<li class="dropdown nav-item"><a href="" class="dropdown-toggle nav-link"
				data-toggle="dropdown">账户</a>
				<div class="dropdown-menu">
					<a href="<%= request.getContextPath() %>/user_center" class="dropdown-item">个人中心</a>
					<c:if test="${user.hasBasePermission()}">
						<a href="<%=request.getContextPath()%>/admin/AdministratorCenter" class="dropdown-item">网站后台</a>
					</c:if>
					<a onclick="logOut()" class="dropdown-item">注销</a>
				</div></li>
		</c:if>
	</ul>
</nav>
