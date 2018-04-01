<%--
  Created by IntelliJ IDEA.
  User: 云淡风轻
  Date: 2018/3/27
  Time: 1:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>


<!DOCTYPE html>
<html>
<head>
    <title></title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</head>
<body>
<!--导航栏部分-->
<jsp:include page="include/header.jsp"/>
<!-- 中间主体 -->
<div class="container" id="content">
    <div class="row">
        <jsp:include page="productMenu.jsp"></jsp:include>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 style="text-align: center;">修改商品信息</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="/editProductInfo" id="editfrom" method="post">
                        <div class="form-group">
                            <label for="id" class="col-sm-2 control-label">序号</label>
                            <div class="col
                            -sm-10">
                                <input type="text" class="form-control" id="id" name="id" placeholder="序号" value="${product.id}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">姓名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="name" name="name" placeholder="请输入姓名" value="${product.name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="type" class="col-sm-2 control-label">类型</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="type" name="type" placeholder="请输入类型" value="${product.type}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="picture" class="col-sm-2 control-label">图片链接</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="picture" name="picture" placeholder="请输入图片链接" value="${product.picture}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description" class="col-sm-2 control-label">描述</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="description" name="description" placeholder="请输入描述" value="${product.description}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="area" class="col-sm-2 control-label">产地</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="area" name="area" placeholder="请输入产地" value="${product.area}">
                            </div>
                        </div>
<%--                        <div class="form-group">
                            <label for="state" class="col-sm-2 control-label">状态</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="state" name="state" placeholder="请输入状态" value="${product.state}">
                            </div>
                        </div>--%>
                        <div class="form-group" style="text-align: center">
                            <button class="btn btn-default" type="submit">提交</button>
                            <button class="btn btn-default" type="reset">重置</button>
                        </div>
                    </form>
                </div>

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
