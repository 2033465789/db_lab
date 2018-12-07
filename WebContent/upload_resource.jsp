<%@page import="javabeans.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="pageModules/header.jsp"%>

<script type="text/javascript" src="MY-JS/sharedResource.js"></script>
<title>上传文件</title>
</head>
<body>
	<c:if test="${empty user}">
		<script type="text/javascript">
			show_login();
		</script>
	</c:if>
	<div class="full-screen-transport" id="uploading"></div>
	<div class="container">
		<jsp:include page="mynav.jsp"></jsp:include>
		<div class="row">
			<form role="from" class="bs-example bs-example-form">
				<div class="form-group col-md-12  col-sm-12">
					<div>
						<span class="label label-info">文件类型</span>
					</div>
					<div class="btn-group ">
						<button type="button" id="image" class="btn btn-default" data-type="image">图片</button>
						<button type="button" id="video" class="btn btn-default" data-type="video">音频</button>
						<button type="button" id="application" class="btn btn-default" data-type="application">软件</button>
						<button type="button" id="resource" class="btn btn-default" data-type="resource">资料</button>
					</div>
				</div>
				<div class="form-group col-md-12 col-sm-12">
					<span class="label label-info">文件描述</span>
					<textarea class="form-control" rows="5" id="fileDesc"></textarea>
				</div>

				<div class="hide">
					<input type="file" class="form-control" class="element" id="upload-file" required="required">
				</div>
				<div class="form-group input-group dropdown col-md-12 col-sm-12">
					<!-- 显示的文件选择 -->
					<input type="text" class="form-control" placeholder="请选择上传文件" readonly="readonly"
						id="file-path"><span class="input-group-btn"><button type="button"
							class="btn btn-info" onclick="$('#upload-file').click()">浏览</button></span>
				</div>
				<div class="form-group col-md-12 col-sm-12">
					<input type="button" class="form-control btn btn-success" value="提交" id="submit">
				</div>
			</form>
		</div>
		<!-- 上传时进度条 -->
		<div class="alert alert-success col-md-8 col-md-offset2 col-sm-12" style="width: 0px"
			id="progress">
			<div class="progress progress-striped active">
				<div id="progress-bar" class="progress-bar progress-bar-success" role="progressbar"
					aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>
			</div>
		</div>
	</div>
</body>
</html>