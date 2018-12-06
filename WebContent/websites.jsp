<%@page import="java.util.HashSet"%>
<%@page import="javabeans.User"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="javabeans.WebInfo"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="pageModules/header.jsp"%>
<script type="text/javascript" src="MY-JS/websites.js"></script>
<title>常用网站</title>
</head>
<body>

	<c:if test="${empty webInfos}">
		<script type="text/javascript">
            $.growl({
                title : "",
                message : "没有网站信息"
            });
        </script>
	</c:if>
	<div class="container">
		<jsp:include page="mynav.jsp"></jsp:include>
		<div class="row">
			<c:choose>
				<c:when test="${not empty user && user.hasBasePermission()}">
					<!-- 管理员组件 -->
					<jsp:include page="pageModules/admin_operate.html"></jsp:include>
					<!-- 显示模块 -->
					<div id="show col-md-12">
						<div id="admin-operate" class="col-md-3 text-center">
							<span class="label label-success">后台管理</span>
							<ul class="nav nav-tabs nav-stacked">
								<li><a data-operate="delete">删除常用网站</a></li>
								<li><a data-operate="add">增加常用网站</a></li>
								<li><a data-operate="alter">修改常用网站信息</a></li>
							</ul>
						</div>
					</div>
					<div id="website-info" class="col-md-9 text-center"></div>
				</c:when>
				<c:otherwise>
					<div id="website-info" class="col-md-12 text-center"></div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<c:forEach var="web" items="${webInfos}">
		<script type="text/javascript">
            loadWebsiteInfo('${web.aimURL}', '${web.imgURL}', '${web.webName}', '${web.webDesc}');
        </script>
	</c:forEach>

</body>
</html>