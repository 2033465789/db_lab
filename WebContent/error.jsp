<%@ page import="java.io.ByteArrayOutputStream"%>
<%@ page import="java.io.PrintStream"%>

<%@ page isErrorPage="true"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<html>
<head>
<%@ include file="pageModules/header.jsp"%>
<title>出错啦</title>
</head>
<body class="container">
	<div class="col-md-12">
		<div class="col-md-12">
			<h1>
				<span class="tabel tabel-success">${info}</span>
			</h1>
		</div>
		<div class="col-md-6 col-md-offset-3 col-sm-12 text-center">
			<img src="staticImages/error.jpg">
		</div>
	</div>
</body>
</html>
