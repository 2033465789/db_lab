<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
<%@ include file="commons/header.jsp"%>

<script type="text/javascript" src="MY-JS/all_find.js"></script>
<title>所有捡到的物品</title>
</head>
<body class="container">
	
	<c:if test="${allFind == null}">
		<script type="text/javascript">
			location.href = "AllFind";
		</script>
	</c:if>

	<c:if test="${allFind.size() == 0}">
		<script type="text/javascript">
			showNoticeMSG("你暂未捡到任何物品");
		</script>
	</c:if>

	<div class="col-md-12 col-sm-12">
		<jsp:include page="commons/mynav.jsp"></jsp:include>
		<div class="col-md-12 col-sm-12">
			<table class="table table-hover">
				<thead>
					<tr>
						<td>失主姓名</td>
						<td>物品描述</td>
						<td>物品图片</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${allFind}">
						<tr>
							<td>${item.losterName}</td>
							<td>${item.goodDesc}</td>
							<td><img src="${item.imagePath}"
								class="img-thumbnail image-rounded img-responsive show-image">
							</td>
							<td><button type="button" class="btn btn-danger"
									onclick="deleteGood('${item.id}')">删除</button></td>
							<td><button type="button" class="btn btn-mybtn"
									onclick="findLoster('${item.id}')">已成功找到失主</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
