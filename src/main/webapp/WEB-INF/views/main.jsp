<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
	  <meta charset="utf-8">
	  <meta http-equiv="X-UA-Compatible" content="IE=edge">
	  <meta name="viewport" content="width=device-width, initial-scale=1">
	  <title>商品</title>
	  <link href="${cp}/css/bootstrap.min.css" rel="stylesheet">
	  <link href="${cp}/css/style.css" rel="stylesheet">

	  <script src="${cp}/js/jquery.min.js" type="text/javascript"></script>
	  <script src="${cp}/js/bootstrap.min.js" type="text/javascript"></script>
	  <script src="${cp}/js/layer.js" type="text/javascript"></script>
    <!--[if lt IE 9]>
      <script src="${cp}/js/html5shiv.min.js"></script>
      <script src="${cp}/js/js/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <!--导航栏部分-->
	<jsp:include page="include/header.jsp"/>


	<!-- 中间内容 -->
	<div class="container-fluid">

		<div class="row">
			<!-- 控制栏 -->
			<jsp:include page="productMenu.jsp"></jsp:include>
			<!-- 控制内容 -->
			<div class="col-sm-10 ">
				<div class="jumbotron">
					<h1>欢迎来到ipanel商城</h1>
					<p>ipanel商城是一个非常优秀的购物平台，你可以在这里买到物美价廉的东西，虽然它只是一个大作业，233333</p>
				</div>

			</div>
		</div>
	</div>
	<div class="container" id="footer">
		<div class="row">
			<div class="col-md-12"></div>
		</div>
	</div>


  </body>
</html>