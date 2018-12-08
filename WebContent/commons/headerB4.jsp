
<%@ page errorPage="error.jsp"%>
<!-- jstl -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 新 Bootstrap4 核心 CSS 文件 -->
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>

<!-- popper.min.js 用于弹窗、提示、下拉菜单 -->
<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>

<!-- 最新的 Bootstrap4 核心 JavaScript 文件 -->
<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="../JQuery/jquery.growl.css">
<script type="text/javascript" src="../JQuery/jquery.growl.js"></script>

<link rel="stylesheet" href="../MY-CSS/static.css">
<script type="text/javascript" src="../MY-JS/public.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<%
	String contextPath = request.getContextPath();
%>

<c:if test="${not empty alert}">
	<script>
		$(function() {
			showNoticeMSG('${alert}');
		});
	</script>
</c:if>