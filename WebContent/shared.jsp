<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
<%@ include file="commons/header.jsp"%>

<script type="text/javascript" src="MY-JS/all_shared.js"></script>
<title>所有捡到的物品</title>
</head>
<body class="container">
	<c:if test="${empty user}">
		<script type="text/javascript">
			show_login();
		</script>
	</c:if>
	<c:if test="${allShared == null}">
		<script type="text/javascript">
			location.href = "allShared";
		</script>
	</c:if>

	<c:if test="${allShared.size() == 0}">
		<script type="text/javascript">
			showNoticeMSG("你暂未分享任何资源");
		</script>
	</c:if>

	<div class="col-md-12 col-sm-12">
		<jsp:include page="commons/mynav.jsp"></jsp:include>
		<div class="col-md-12 col-sm-12">
			<table class="table table-hover">
				<thead>
					<tr>
						<!-- private String fileName, uploadUser, uploadTime, filePath,
						fileType, fileDesc; private long id; -->
						<td>序号</td>
						<td>文件名</td>
						<td>上传时间</td>
						<td>文件类型</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${allShared}">
						<tr>
							<td>${item.sid}</td>
							<td>${item.fileName}</td>
							<td>${item.uploadTime}</td>
							<td>${item.fileType}</td>
							<td><button type="button" class="btn btn-danger " onclick="deleteShared('${item.sid}')">删除</button></td>
							<td><button type="button" class="btn btn-mybtn" onclick="alterShared('${item.sid}')">修改</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
