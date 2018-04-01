<%--
  Created by IntelliJ IDEA.
  User: 云淡风轻
  Date: 2018/3/27
  Time: 0:29
  To change this template use File | Settings | File Templates.
--%>
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
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 class="col-md-5">ipanel商城商品管理</h1>
                        <button class="btn btn-default col-md-2" style="margin-top: 20px" onClick="location.href='/addProduct'">
                            添加商品信息
                            <sapn class="glyphicon glyphicon-plus"/>
                        </button>

                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>姓名</th>
                        <th>类型</th>
                        <th>图片链接</th>
                        <th>描述</th>
                        <th>产地</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach  items="${productList}" var="item">
                        <tr>
                            <td>${item.id}</td>
                            <td>${item.name}</td>
                            <td>${item.type}</td>
                            <td>${item.picture}</td>
                            <td>${item.description}</td>
                            <td>${item.area}</td>
                            <td>${item.state}</td>
                            <td>
                                <button class="btn btn-default btn-xs btn-info" onClick="location.href='/editProduct?id=${item.id}'">修改</button>
                                <%--<button class="btn btn-default btn-xs btn-danger btn-primary" onClick="location.href='/deleteProduct?id=${item.id}'">删除</button>--%>
                                <button class="btn btn-default btn-xs btn-info" onClick="location.href='/postProduct?id=${item.id}'">发布</button>
                                <button class="btn btn-default btn-xs btn-info" onClick="location.href='/downProduct?id=${item.id}'">下架</button>
                                <button class="btn btn-default btn-xs btn-danger btn-primary" onClick="location.href='/deleteProduct?id=${item.id}'">删除</button>
                                <!--弹出框-->
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="panel-footer">
                    <c:if test="${pagingVO != null}">
                        <nav style="text-align: center">
                            <ul class="pagination">
                                <li><a href="/admin/showStudent?page=${pagingVO.upPageNo}">&laquo;上一页</a></li>
                                <li class="active"><a href="">${pagingVO.curentPageNo}</a></li>
                                <c:if test="${pagingVO.curentPageNo+1 <= pagingVO.totalCount}">
                                    <li><a href="/admin/showStudent?page=${pagingVO.curentPageNo+1}">${pagingVO.curentPageNo+1}</a></li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+2 <= pagingVO.totalCount}">
                                    <li><a href="/admin/showStudent?page=${pagingVO.curentPageNo+2}">${pagingVO.curentPageNo+2}</a></li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+3 <= pagingVO.totalCount}">
                                    <li><a href="/admin/showStudent?page=${pagingVO.curentPageNo+3}">${pagingVO.curentPageNo+3}</a></li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+4 <= pagingVO.totalCount}">
                                    <li><a href="/admin/showStudent?page=${pagingVO.curentPageNo+4}">${pagingVO.curentPageNo+4}</a></li>
                                </c:if>
                                <li><a href="/admin/showStudent?page=${pagingVO.totalCount}">最后一页&raquo;</a></li>
                            </ul>
                        </nav>
                    </c:if>
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