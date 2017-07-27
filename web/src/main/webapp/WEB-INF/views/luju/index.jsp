<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/header.jsp"%>

<body class="hold-transition login-page" style="background-image:url(${contextPath}/static/images/bg.jpg)">
<div class="login-box" style="filter:alpha(Opacity=80);-moz-opacity:0.5;opacity: 0.8;">
    <div class="login-logo">
        <%--<a href="http://www.com.com.sytlj.com"><b>沈阳铁路局</b></a>--%>
    </div>
    <div class="login-box-body">
        <p class="login-box-msg" style="font-size: 20px;">客运一体化命令辅助解读系统</p>
        <form action="${contextPath}/luju/login.action" method="post" id="loginForm">
            <div class="form-group has-feedback">
                <input type="text" class="form-control" name="loginName" placeholder="用户名">
                <%--<span class="glyphicon glyphicon-user form-control-feedback"></span>--%>
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" name="password" placeholder="密码">
                <%--<span class="glyphicon glyphicon-lock form-control-feedback"></span>--%>
            </div>
            <div class="row">
                <div class="col-xs-4" style="float: right;">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
                </div>
            </div>
        </form>
    </div>
</div>

<%@ include file="/WEB-INF/include/javascript.jsp"%>

<script src="${contextPath}/static/plugins/iCheck/icheck.min.js"></script>
<script>
    $(function () {
        // 记录登陆的checkbox
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });

        // 登陆表单的前台验证
        $("#loginForm").validate({
            rules: {
                loginName: {
                    required: true
                },
                password: {
                    required: true
                }
            },
            messages: {
                loginName: {
                    required: "请填写登录名"
                },
                password: {
                    required: "请填写登录密码"
                }
            }
        });
    });
</script>

<%@ include file="/WEB-INF/include/loginFooter.jsp"%>

