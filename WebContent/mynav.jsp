<%@page import="javabeans.DormitoryInfo"%>
<%@page import="javabeans.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jstl -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-default" role="navigation">
	<div class="navber-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#my-collapse">
			<span class="sr-only"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>
		<a href="main" class="navbar-brand text-center">PiaoPiao</a>
	</div>

	<div class="collapse navbar-collapse" id="my-collapse">
		<ul class="nav navbar-nav">
			<!-- 失物招领 -->
			<li class="dropdown"><a href="" class="dropdown-toggle" data-toggle="dropdown">失物招领 <b
					class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="finder">我捡到物品</a></li>
					<li><a href="loster">我丢失物品</a></li>
				</ul></li>
			<li class="dropdown"><a href="#" class="dropdown-toogle" data-toggle="dropdown">资源共享 <b
					class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="upload_resource" id="upload-resource">上传资源</a></li>
					<li><a href="DownloadResource" id="download-resource">下载资源</a></li>
				</ul></li>
			<li><a href="Website">常用网址</a></li>
		</ul>
		<c:if test="${empty user}">
			<ul class="nav navbar-nav navbar-right margin-right-5px">
				<li><a href="signup.html"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
				<li><a onclick="show_login()"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
			</ul>
		</c:if>
		<c:if test="${not empty user}">
			<ul class="nav navbar-nav navbar-right margin-right-5px">
				<li class="dropdown"><a class="dropdown-toggle nav-background" data-toggle="dropdown">
						<span class="glyphicon glyphicon-user"></span> 账户<b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="user_center" target="_blank">个人中心</a></li>
						<c:if test="${user.hasBasePermission()}">
							<li><a id="admin-operation-center" href="AdministratorCenter">网站后台</a></li>
						</c:if>
						<li class="divider"></li>
						<li><a id="log-out" onclick="logOut()">注销</a></li>
					</ul></li>
			</ul>
		</c:if>
	</div>
</nav>
