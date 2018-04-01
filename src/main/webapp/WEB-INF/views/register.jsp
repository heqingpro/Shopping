<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>商城</title>
      <link href="${cp}/css/bootstrap.min.css" rel="stylesheet">
      <link href="${cp}/css/style.css" rel="stylesheet">

      <script src="${cp}/js/jquery.min.js" type="text/javascript"></script>
      <script src="${cp}/js/bootstrap.min.js" type="text/javascript"></script>
      <script src="${cp}/js/layer.js" type="text/javascript"></script>
    <!--[if lt IE 9]>
      <script src="${cp}/js/html5shiv.min.js"></script>
      <script src="${cp}/js/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <!--导航栏部分-->
    <jsp:include page="include/header.jsp"/>

    <!-- 中间内容 -->
    <div class="container-fluid">
        <h1 class="title center">注册</h1>
        <br/>
        <div class="col-sm-offset-2 col-md-offest-2">
            <!-- 表单输入 -->
            <div  class="form-horizontal">
                <div class="form-group">
                    <label for="inputUserName" class="col-sm-2 col-md-2 control-label">用户名</label>
                    <div class="col-sm-6 col-md-6">
                        <input type="text" class="form-control" id="inputUserName" placeholder="请输入数字"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="col-sm-2 col-md-2 control-label">密码</label>
                    <div class="col-sm-6 col-md-6">
                        <input type="email" class="form-control" id="inputPassword" placeholder="请输入正确"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputNickname" class="col-sm-2 col-md-2 control-label">昵称</label>
                    <div class="col-sm-6 col-md-6">
                        <input type="text" class="form-control" id="inputNickname" placeholder="高帅富" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="man" class="col-sm-2 col-md-2 control-label">性别</label>
                    <div class="col-sm-6 col-md-6">
                        <label class="radio-inline">
                            <input type="radio" id="man" value="option1"> 男
                        </label>
                        <label class="radio-inline">
                            <input type="radio" id="woman" value="option2"> 女
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-6">
                        <button class="btn btn-lg btn-primary btn-block" type="submit" onclick="startRegister()">注册</button>
                    </div>
                </div>
            </div>
            <br/>
        </div>
    </div>

    <!--尾部-->
    <jsp:include page="include/foot.jsp"/>
    <script type="text/javascript">
        function startRegister() {
            var loading = layer.load(0);
            var user = {};
            user.userName = document.getElementById("inputUserName").value;
            user.password = document.getElementById("inputPassword").value;
            user.nickName = document.getElementById("inputNickname").value;
            user.sex = 0;
            if(document.getElementById("woman").checked)
                user.sex = 1;
            if(user.userName == ''){
                layer.msg('用户名不能为空',{icon:2});
                return;
            }
            else if(user.userName.length >= 12){
                layer.msg('用户名长度不能超过12个字符',{icon:2});
                return;
            }
            if(user.nickName == ''){
                layer.msg('昵称不能为空',{icon:2});
                return;
            }
            else if(user.nickName.length >= 15){
                layer.msg('用户名长度不能超过15个字符',{icon:2});
                return;
            }
            else if(user.password == ''){
                layer.msg('密码不能为空',{icon:2});
                return;
            }
            else if(user.password.length>= 20){
                layer.msg('密码长度不能超过20个字符',{icon:2});
                return;
            }
            var registerResult = null;
            $.ajax({
                async : false, //设置同步
                type : 'POST',
                url : '${cp}/doRegister',
                data : user,
                dataType : 'json',
                success : function(result) {
                    registerResult = result.result;
                },
                error : function(result) {
                    layer.alert('查询用户错误');
                }
            });
            if(registerResult == 'success'){
                layer.close(loading);
                layer.msg('注册成功',{icon:1});
                window.location.href="${cp}/login";
            }
            else if(registerResult == 'nameExist'){
                layer.close(loading);
                layer.msg('这个用户名已经被占用啦！',{icon:2});
            }
            else if(registerResult == 'fail'){
                layer.close(loading);
                layer.msg('服务器异常',{icon:2});
            }
        }
    </script>
  </body>
</html>