<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
<%@ include file="commons/header.jsp"%>

<script type="text/javascript" src="MY-JS/sharedResource.js"></script>
</head>

<title>资源详情</title>

<body class="container">
	<c:if test="${empty file}">
		<jsp:forward page="DownloadResource"></jsp:forward>
	</c:if>
	<c:choose>
		<c:when test="${file.fileType eq 'image'}">
			<c:set var="type" value="图片"></c:set>
		</c:when>
		<c:when test="${file.fileType eq 'application'}">
			<c:set var="type" value="应用"></c:set>
		</c:when>
		<c:when test="${file.fileType eq 'resource'}">
			<c:set var="type" value="资源"></c:set>
		</c:when>
		<c:when test="${file.fileType eq 'voide'}">
			<c:set var="type" value="音频"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="type" value="其他"></c:set>
		</c:otherwise>
	</c:choose>

	<div class="row">
		<!-- 文件信息 -->
		<div class="text-center">
			<div data-file="${file.id}">
				<div class="col-md-12 col-sm-12 margin-top-bottom-10px">
					<span class="label label-file-name margin-top-bottom-10px">${ file.fileName}</span>
				</div>

				<div class="col-md-3 col-sm-3 margin-top-bottom-10px">
					<span class="label label-info">文件类型:${type}</span>
				</div>

				<div class="col-md-4 col-sm-3 margin-top-bottom-10px">
					<span class="label label-warning">上传作者:${file.uploadUser}</span>
				</div>

				<div class="col-md-3 col-sm-3 margin-top-bottom-10px">
					<span class="label label-info">上传时间:${file.uploadTime}</span>
				</div>

				<div class="col-md-12 col-sm-12 margin-top-bottom-10px">
					<div class="file-desc margin-top-bottom-10px">${file.fileDesc}</div>
				</div>
			</div>
			<!-- 下载按钮 -->
			<div class="col-md-12 col-sm-12 margin-top-bottom-10px">
				<a type="button" href="sharedResource/${file.fileName}"
					class="btn btn-download margin-top-bottom-10px">立即下载</a>
			</div>
		</div>
		<!-- 评论面板 -->
		<div id="comments-panel" class="col-md-12 col-sm-12">
			<span>评论数：${itemCount}</span>
			<table class="table table-hover col-md-12 col-sm-12">
				<c:forEach var="comment" items="${comments}">
					<tr class="comment-item" id="${comment.cid}">
						<td class="comment-user-info">
							<div>
								<span class="margin-right-10px"><b>${comment.uid}</b></span> <span>
									${comment.createTime}</span>
							</div> <span>${comment.content}</span>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="text-center col-md-12">
			<c:if test="${itemCount/pageSize>0 }">
				<ul class="pagination text-center">
					<c:if test="${not empty page and page > 1}">
						<li><a href="<%=contextPath%>/ShowFileDetails?id=${file.id}&page=${page-1}">上一页 </a></li>
					</c:if>
					<c:forEach var="i" begin="1" end="${itemCount/pageSize+1}">
						<c:if test="${page == i}">
							<li class="active"><a>${i}</a></li>
						</c:if>
						<c:if test="${page !=i}">
							<li><a href="<%=contextPath%>/ShowFileDetails?id=${file.id}&page=${i}">${i}</a></li>
						</c:if>
					</c:forEach>
					<c:if test="${page <= itemCount/pageSize">
						<li><a href="<%=contextPath%>/ShowFileDetails?id=${file.id}&page=${page+1}">下一页 </a></li>
					</c:if>
				</ul>
			</c:if>
		</div>
		<!-- 发表评论 -->
		<div class="col-md-12 col-sm-12">
			<textarea rows="4" class="margin-top-bottom-10px" id="comment-content" style="width: 100%"></textarea>
			<button type="button" class="col-md-12 col-sm-12 btn btn-success" style="width: 100%"
				id="make-comment">发表评论</button>
		</div>
	</div>
</body>
</html>
