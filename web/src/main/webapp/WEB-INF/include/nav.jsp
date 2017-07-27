<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/header.jsp"%>

<body class="hold-transition skin-blue sidebar-mini sidebar-collapse">

<div class="wrapper">
    <header class="main-header">
        <!-- 左上角标题 -->
        <a href="${contextPath}" class="logo" style="padding-left: 0; padding-right: 0;">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo">
                <b>裕</b>
            </span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo" style="padding-left: 0; padding-right: 0;">
                裕国站
                <small>调度命令解读系统</small>
            </span>
        </a>

        <!-- 头部内容 -->
        <nav class="navbar navbar-static-top">
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li class="dropdown tasks-menu">
                        <a href="#" class="dropdown-toggle" id="nowDay"></a>
                    </li>
                    <script>
                        document.getElementById("nowDay").innerHTML = new Date().toLocaleDateString();
                    </script>
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
                <li><a href="${contextPath}/luju/jcPlan.action"><i class="fa fa-file-code-o" aria-hidden="true"></i><span>接车计划</span></a></li>
            </ul>
        </section>
    </aside>

