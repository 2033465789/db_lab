<%@page import="javabeans.DormitoryInfo"%>
<%@page import="javabeans.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 公共头部 -->
<%@ include file="commons/header.jsp"%>
<!-- 页面js文件 -->
<script type="text/javascript" src="MY-JS/finder.js"></script>
<title>提交拾取物品</title>
</head>
<body>
	<c:if test="${empty user}">
		<script type="text/javascript">
            show_login();
        </script>
	</c:if>

	<div class="container">
		<jsp:include page="commons/mynav.jsp"></jsp:include>
		<div class="row">
			<div class="col-md-12 col-sm-12">
				<form id="good-info" role="form">
					<div
						class="form-grounp margin-top-bottom-10px col-md-10 col-md-offset-1">
						<span class="label label-warning">物品信息</span>
					</div>
					<div
						class="input-group form-group col-md-10 col-md-offset-1 col-sm-12">
						<span class="input-group-addon">数字信息</span> <input type="number"
							class="form-control" id="number-info"
							placeholder="学号/证件号/其他有助于失主找到的数字信息">
					</div>

					<div
						class="input-group form-group col-md-10 col-md-offset-1 col-sm-12">
						<span class="input-group-addon">失主姓名</span><input type="text"
							class="form-control" id="loster-name" placeholder="失主姓名">
					</div>

					<div
						class="input-group form-group col-md-10 col-md-offset-1 col-sm-12">
						<span class="input-group-addon">物品描述</span><input type="text"
							class="form-control" id="good-desc"
							placeholder="请填写物品颜色/种类/尺寸等物品详细描述，空格间隔">
					</div>
					<div
						class="input-group form-group col-md-10 col-md-offset-1 col-sm-12">
						<span class=" input-group-addon">拾取地点</span><input type="text"
							class="form-control" id="found-addr" placeholder="请填写捡到该物品的详细地址">
					</div>

					<div class="form-group col-md-10 col-md-offset-1">
						<span class="label label-warning">为了方便失主与您取得联系，请填写以下信息</span>
					</div>

					<div
						class="input-group form-group col-md-10 col-md-offset-1 col-sm-12">
						<label for="finder-name" class="input-group-addon">你的姓名</label><input
							type="text" class="form-control" id="finder-name"
							placeholder="你的名字">
					</div>

					<div
						class="input-group form-group col-md-10 col-md-offset-1 col-sm-12">
						<label for="finder-phone" class="input-group-addon">联系电话</label><input
							type="number" class="form-control" id="finder-phone"
							placeholder="你的联系电话">
					</div>

					<div
						class="input-group form-group dropdown col-md-10 col-md-offset-1 col-sm-12">

						<span class="input-group-btn dropdown-toggle"
							data-toggle="dropdown">
							<button class="btn btn-default" id="connect-way">
								其他方式<b class="caret"></b>
							</button>
						</span> <input type="text" class="form-control" id="finder-QQorWX"
							placeholder="你的QQ或者微信">

						<!-- 下拉菜单内容 -->
						<ul class="dropdown-menu">
							<li><a id="choice-qq">QQ</a></li>
							<li class="divider"></li>
							<li><a id="choice-wx">微信</a></li>
						</ul>
					</div>

					<div class="hide">
						<!-- 隐藏的文件框 -->
						<input type="file" id="good-image"
							accept="image/gif,image/jpeg,image/jpg,image/png" />
					</div>

					<div
						class="form-group input-group col-md-10 col-md-offset-1 col-sm-12">

						<div>
							<img src="" id="image-show" class="img-responsive img-rounded">
						</div>

						<!-- 显示的文件选择 -->
						<input type="text" class="form-control col-sm-12"
							placeholder="请选择上传文件" readonly="readonly" id="image-path"><span
							class="input-group-btn"><button type="button"
								class="btn btn-info" onclick="$('#good-image').click()">浏览</button></span>
					</div>

					<div class="col-md-10 col-md-offset-1 col-sm-12">
						<input type="button" class="btn btn-success" style="width: 100%"
							id="submit" value="提交" />
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>