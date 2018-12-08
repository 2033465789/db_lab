
<%@ page errorPage="error.jsp"%>
<!-- jstl -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="MY-CSS/static.css">

<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>

<script type="text/javascript" src="bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="MY-JS/public.js"></script>

<link rel="stylesheet" href="JQuery/jquery.growl.css">
<script type="text/javascript" src="JQuery/jquery.growl.js"></script>

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