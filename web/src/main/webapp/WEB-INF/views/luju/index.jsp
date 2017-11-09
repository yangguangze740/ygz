<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/header.jsp"%>

<body class="login-page" style="background-image:url(${contextPath}/static/images/bg.jpg); width: auto;height: auto;">

        <div class="login-logo" style="margin-bottom: 0px;margin-top: 7%">
            <h2 class="login-box-msg" style="font-size: 40px;padding-top: 8px;color: #fff">裕国站作业计划推演平台</h2>
        </div>

        <div class="login-box" style="filter:alpha(Opacity=80);-moz-opacity:0.5;opacity:0.9;margin-top:1%">
            <div class="login-box-body" style="border: 1px solid #e9ecef ;border-radius: 22px;height: 240px;box-shadow:0px 0px 15px #5394cf;">
                <div style="height: 25px"></div>

                <form action="${contextPath}/luju/login.action" method="post" id="loginForm" novalidate="novalidate">
                    <div class="form-group has-feedback" style="margin-bottom: 8px;margin-left: 4%;width:90%;height: 40px;">
                        <p style="float: left;margin-top: 8px">账号：</p>
                        <input type="text" class="form-control valid" name="loginName" placeholder="用户名" aria-required="true" aria-invalid="false" style="width:84%;height: 90%;float: right;border-radius:2px">
                        <%--<span class="glyphicon glyphicon-user form-control-feedback"></span>--%>
                    </div>
                    <div class="form-group has-feedback" style="margin-bottom: 3px;margin-left: 4%;width:90%;height: 40px;">
                        <p style="float: left;margin-top: 8px">密码：</p>
                        <input type="password" class="form-control valid" name="password" placeholder="密码" aria-required="true" aria-invalid="false" style="width:84%;height: 90%;float: right;border-radius:2px">
                        <%--<span class="glyphicon glyphicon-lock form-control-feedback"></span>--%>
                    </div>
                    <div class="row">
                        <div class="col-xs-11" style="margin-left: 3.5%;margin-top: 20px;">
                            <button type="submit" class="btn btn-primary btn-block btn-flat" style="height: 43px;border-radius:40px;font-size: 18px">登 录</button>
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

