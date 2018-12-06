<%@page import="java.net.URLDecoder"%>
<%@page import="javabeans.DormitoryInfo"%>
<%@page import="javabeans.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="pageModules/header.jsp"%>
<script type="text/javascript" src="MY-JS/main.js"></script>
<title>首页</title>
</head>
<body>
	<c:if test="${ not empty main_info}">
		<script type="text/javascript">
            showNoticeMSG('${main_info}');
        </script>
	</c:if>
	<div class="container">
		<div class="row">
			<div class="col-md-9 col-sm-12 col-xs-12">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<jsp:include page="mynav.jsp"></jsp:include>
				</div>

				<div class="col-md-12 col-sm-12 col-xs-12">
					<div id="find"
						class="col-md-3 col-sm-12 col-xs-12 module-container"
						onclick="location.href='finder'" style="background-color: red;">
						<div class="text-center">
							<span class="label label-success">我捡到物品</span>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12" style="width: 100%">
							<img src="staticImages/lost_find.jpg"
								style="height: 80%; width: 100%;">
						</div>
					</div>

					<div id="find"
						class="col-md-3 col-sm-12 col-xs-12 module-container"
						onclick="location.href='loster'" style="background-color: green;">
						<div class="text-center">
							<span class="label label-success">我丢失物品</span>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12" style="width: 100%">
							<img src="staticImages/lost_find.jpg"
								style="height: 80%; width: 100%;">
						</div>
					</div>

					<div id="shared"
						class="col-md-3 col-sm-12 col-xs-12 module-container"
						onclick="location.href='upload_resource'"
						style="background-color: blue;">
						<div class="text-center">
							<span class="label label-success">上传资源</span>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12" style="width: 100%">
							<img src="staticImages/download.jpg"
								style="height: 80%; width: 100%;">
						</div>
					</div>
				</div>

				<div class="col-md-12 col-sm-12 col-xs-12">
					<div id="shared"
						class="col-md-3 col-sm-12 col-xs-12 module-container"
						onclick="location.href='DownloadResource'"
						style="background-color: orange;">
						<div class="text-center">
							<span class="label label-success">资源下载</span>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12" style="width: 100%">
							<img src="staticImages/download.jpg"
								style="height: 80%; width: 100%;">
						</div>
					</div>

					<div id="useful_website"
						class="col-md-3 col-sm-12 col-xs-12 module-container"
						onclick="location.href='Website'"
						style="background-color: rgb(0, 255, 255);">
						<div class="text-center">
							<span class="label label-success">常用网站</span>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12" style="width: 100%">
							<img src="staticImages/download.jpg"
								style="height: 80%; width: 100%;">
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-3 col-sm-12 col-xs-12">
				<div class="text-center margin-top-bottom-10px">
					<span class="label label-success">最近捡到的物品</span>
				</div>
				<div id="recently-lost-goods" class="text-center to-x-center"></div>
			</div>
		</div>
	</div>
</body>
</html>