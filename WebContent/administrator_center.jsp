<%@ page import="javabeans.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./pageModules/headerB4.jsp"%>
<script type="text/javascript" src="MY-JS/admin.js"></script>
<title>网站管理</title>
</head>
<body>
	<c:if test="${empty user}">
		<jsp:forward page="DownloadResource"></jsp:forward>
	</c:if>
	<c:if test="${user.userPermission <=1 }">
		<script type="text/javascript">
			showWarningMSG("你没有足够的权限");
		</script>
	</c:if>
	<c:if test="${user.userPermission > 1}">
		<c:if test="${empty dbStatus || empty sharedCache_size || empty goodsCache_size}">
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
			<jsp:include page="mynavB4.jsp" />
			<div class="row">
				<div id="db-op" class="col-12 admin-module">
					<h3>
						<span>数据库状态:${dbStatus}</span>
					</h3>
					<button id="close-dbconn" class="btn btn-danger col-5">禁止数据库连接</button>
					<button id="open-dbconn" class="btn btn-warning col-5">开放数据库连接</button>
				</div>
				<div id="db-op" class="col-12 admin-module">
					<h3>
						<span class="col-12">资源分享模块缓存目前的大小:${sharedCache_size }</span>
					</h3>
					<h3>
						<span class="col-12">失物招领模块缓存目前的大小:${goodsCache_size}</span>
					</h3>
					<h3>
						<span class="col-12">评论模块缓存目前的大小:${commentCache_size}</span>
					</h3>
					<h3>
						<span class="col-12">网站访问人次:${visiteNum}</span>
					</h3>
					<button id="flush-cache" class="btn btn-success col-12">将缓存数据写入数据库</button>
				</div>
				<div id="shared-op" class="col-12 admin-module">
					<ul class="nav nav-tabs">
						<li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#shared">分享的资源管理</a></li>
						<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#comment">所有评论管理</a></li>
						<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#lost">失物招领管理</a></li>
					</ul>
					<!-- Tab panes -->
					<div class="tab-content">
						<div class="tab-pane active container" id="shared">
							<table class="table table-striped table-hover">
								<thead class="thead-dark">
									<tr>
										<td>文件名</td>
										<td>上传用户</td>
										<td>上传时间</td>
										<td>文件类型</td>
										<td>文件描述</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${shared}">
										<tr class="auto_overflow">
											<td>${item.fileName}</td>
											<td>${item.uploadUser}</td>
											<td>${item.uploadTime}</td>
											<td>${item.fileType}</td>
											<td>${item.fileDesc}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="tab-pane container" id="comment">
							<table class="table table-striped table-hover">
								<thead class="thead-dark">
									<tr class="auto_overflow">
										<td>评论用户</td>
										<td>评论的文件的id</td>
										<td>评论内容</td>
										<td>评论时间</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${comments}">
										<tr class="auto_overflow">
											<td>${item.uid}</td>
											<td>${item.sid}</td>
											<td>${item.content}</td>
											<td>${item.createTime}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="tab-pane container" id="lost"></div>
					</div>
				</div>
			</div>
		</div>
	</c:if>
</body>
</html>
