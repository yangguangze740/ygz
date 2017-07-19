<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/header.jsp"%>

<body class="hold-transition skin-blue sidebar-mini sidebar-collapse">
<div class="wrapper">
    <header class="main-header">
        <!-- 左上角标题 -->
        <a href="${contextPath}" class="logo" style="padding-left: 0; padding-right: 0;">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini">
                <b>沈</b>
            </span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo" style="padding-left: 0; padding-right: 0;">
                裕国站
                <small>调度命令解读系统</small>
            </span>
        </a>

        <!-- 头部内容 -->
        <nav class="navbar navbar-static-top">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button" id="closeNav">
                <span class="sr-only">切换</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>

            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li class="dropdown tasks-menu">
                        <a href="#" class="dropdown-toggle" id="nowDay">

                        </a>
                    </li>
                        <script>
                            document.getElementById("nowDay").innerHTML = new Date().toLocaleDateString();
                        </script>
                    <!-- User Account: style can be found in dropdown.less -->
                    <li class="dropdown user user-menu">

                    </li>
                    <!-- Control Sidebar Toggle Button -->
                    <li>
                        <a href="#" data-toggle="control-sidebar"></a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <li class="header">命令解读</li>
                <li><a href="${contextPath}/command/record/entering/index.action"><i class="fa fa-file-code-o" aria-hidden="true"></i> <span>命令解读</span></a></li>
                <li class="header">系统设置</li>
                <li><a href="${contextPath}/admin/station/route.action"><i class="fa fa-university" aria-hidden="true"></i> <span>车站管理</span></a></li>
            </ul>
        </section>
    </aside>

