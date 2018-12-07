<%@page import="javabeans.SharedResource"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="pageModules/header.jsp"%>
<script type="text/javascript" src="MY-JS/sharedResource.js"></script>

<title>资源共享</title>
</head>
<body>
	<c:if test="${shared eq null}">
		<script type="text/javascript">
			location.href = 'DownloadResource';
		</script>
	</c:if>
	<c:if test="${itemCount eq 0}">
		<script type="text/javascript">
			showNoticeMSG("暂无资源");
		</script>
	</c:if>
	<div class="container">
		<!--  导航栏 -->
		<jsp:include page="mynav.jsp"></jsp:include>
		<div class="row">
			<!-- 搜索栏 -->
			<div class="col-md-6 col-md-offset-3 col-sm-10 col-sm-offset-1">
				<form role="form">
					<div class="input-group">
						<input style='display: none'> <input type="text" class="form-control" id="search-info"
							placeholder="资源信息"> <span class="input-group-btn"><input type="button"
							class="btn btn-search" value="搜索" onclick="search()"></span>
					</div>
				</form>
			</div>
			<!-- 文件显示栏 -->
			<div class="col-md-12 col-sm-12">
				<c:forEach var="e" items="${shared}">
					<c:choose>
						<c:when test="${e.fileType eq 'image'}">
							<c:set var="type" value="图片"></c:set>
						</c:when>
						<c:when test="${e.fileType eq 'application'}">
							<c:set var="type" value="应用"></c:set>
						</c:when>
						<c:when test="${e.fileType eq 'resource'}">
							<c:set var="type" value="资源"></c:set>
						</c:when>
						<c:when test="${e.fileType eq 'video'}">
							<c:set var="type" value="音频"></c:set>
						</c:when>
						<c:otherwise>
							<c:set var="type" value="其他"></c:set>
						</c:otherwise>
					</c:choose>
					<div class="file-container col-md-5 col-sm-12 text-center">
						<div data-file="${e.sid}">
							<div class="col-md-12 col-sm-12 margin-top-bottom-10px" style="overflow: auto;">
								<span class="label label-file-name margin-top-bottom-10px">${ e.fileName}</span>
							</div>
							<div class="col-md-3 col-sm-3 margin-top-bottom-10px">
								<span class="label label-info">文件类型:${type}</span>
							</div>
							<div class="col-md-4 col-sm-3 margin-top-bottom-10px">
								<span class="label label-warning">上传作者:${e.uploadUser}</span>
							</div>
							<div class="col-md-3 col-sm-3 margin-top-bottom-10px">
								<span class="label label-info">上传时间:${e.uploadTime}</span>
							</div>
							<div class="col-md-12 col-sm-12 margin-top-bottom-10px">
								<div class="file-desc margin-top-bottom-10px">${e.fileDesc}</div>
							</div>
						</div>
						<div class="col-md-12 col-sm-12 margin-top-bottom-10px">
							<a type="button" href="sharedResource/${e.fileName}" class="btn btn-download">立即下载</a>
						</div>
					</div>
				</c:forEach>
				<div class="text-center col-md-12">
					<c:if test="${itemCount/pageSize>0 }">
						<ul class="pagination text-center">
							<c:if test="${not empty page and page > 1  }">
								<li><a href="<%=contextPath%>/DownloadResource?page=${page-1}">上一页 </a></li>
							</c:if>
							<c:forEach var="i" begin="1" end="${itemCount/pageSize+1}">
								<c:if test="${page == i}">
									<li class="active"><a>${i}</a></li>
								</c:if>
								<c:if test="${page !=i}">
									<li><a href="<%=contextPath%>/DownloadResource?page=${i}">${i}</a></li>
								</c:if>
							</c:forEach>
							<c:if test="${page <= itemCount/pageSize }">
								<li><a href="<%=contextPath%>/DownloadResource?page=${page+1}">下一页 </a></li>
							</c:if>
						</ul>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>