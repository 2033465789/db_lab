<%@ page import="javabeans.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="pageModules/header.jsp"%>

<script type="text/javascript" src="MY-JS/admin.js"></script>

<title>网站管理</title>
</head>
<body>

	<c:if test="${empty user}">
		<jsp:forward page="DownloadResource"></jsp:forward>
	</c:if>

	<c:if test="${user.userPermission <=1 }">
		<script type="text/javascript">
            $.growl.notice({
                title : "",
                message : "你没有足够的权限"
            });
        </script>
	</c:if>

	<c:if
		test="${empty dbStatus || empty sharedCache_size || empty goodsCache_size}">
		<jsp:forward page="AdministratorCenter"></jsp:forward>
	</c:if>

	<c:choose>
		<c:when test="${dbStatus eq true}">
			<c:set var="dbStatus" value="开放连接中......"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="dbStatus" value="拒接所有连接......"></c:set>
		</c:otherwise>
	</c:choose>

	<div class="container">
		<jsp:include page="mynav.jsp" />
		<div class="row">
			<div id="db-op" class="col-md-12 admin-module">
				<h3>
					<span>数据库状态:${dbStatus}</span>
				</h3>
				<button id="close-dbconn" class="btn btn-danger col-md-6 col-sm-12">禁止数据库连接</button>
				<button id="open-dbconn" class="btn btn-warning col-md-6 col-sm-12">开放数据库连接</button>
			</div>

			<div id="db-op" class="col-md-12 admin-module">
				<h3>
					<span class="col-md-12 col-sm-12">资源分享模块缓存目前的大小:${sharedCache_size }</span>
				</h3>
				<h3>
					<span class="col-md-12 col-sm-12">失物招领模块缓存目前的大小:${goodsCache_size}</span>
				</h3>
				<h3>
					<span class="col-md-12 col-sm-12">评论模块缓存目前的大小:${commentCache_size}</span>
				</h3>
				<h3>
					<span class="col-md-12 col-sm-12">网站访问人次:${visiteNum}</span>
				</h3>
				<button id="flush-cache" class="btn btn-success col-md-12 col-sm-12">将缓存数据写入数据库</button>
			</div>
		</div>
	</div>
</body>
</html>
