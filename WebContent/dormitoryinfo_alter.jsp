<%@page import="javabeans.DormitoryInfo"%>
<%@page import="javabeans.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="pageModules/header.jsp"%>

<script type="text/javascript" src="MY-JS/alterDormitoryInfo.js"></script>
<title>修改个人信息</title>
</head>
<body>
	<c:if test="${empty user}">
		<script type="text/javascript">
			show_login();
		</script>
	</c:if>
	<div class="container margin-top-250">
		<div class="row">
			<form action="" role="form">
				<div class="form-group dropdown col-md-3">
					<button type="button" id="dormAddr"
						class="btn btn-default dopdown-toggle" data-toggle="dropdown">
						宿舍所在苑 <b class="caret"></b>
					</button>
					<ul class="dropdown-menu">
						<li><a onclick="chooseAddr(this)">楠苑</a></li>
						<li><a onclick="chooseAddr(this)">楸苑</a></li>
						<li><a onclick="chooseAddr(this)">桦苑</a></li>
						<li><a onclick="chooseAddr(this)">梓苑</a></li>
					</ul>
				</div>

				<div class="form-group dropdown col-md-3">
					<button type="button" name="userId" id="dormWhich"
						class="btn btn-default dropdown-toggle" data-toggle="dropdown">
						宿舍所在区 <b class="caret"></b>
					</button>

					<ul class="dropdown-menu">
						<li><a onclick="chooseWhich(this)"> A 区</a></li>
						<li><a onclick="chooseWhich(this)"> B 区</a></li>
						<li><a onclick="chooseWhich(this)"> C 区</a></li>
					</ul>
				</div>

				<div class="form-group input-group col-md-3">
					<span class="input-group-addon">宿舍门牌号</span><input type="text"
						name="DormTag" id="dormTag" class="form-control width-50"
						maxlength="4" placeholder="A***/B***/C***">
				</div>

				<div class="form-gounp">
					<input type="button" value="修改"
						class="form-control btn btn-success" onclick="alter()">
				</div>
			</form>
		</div>
	</div>
</body>
</html>