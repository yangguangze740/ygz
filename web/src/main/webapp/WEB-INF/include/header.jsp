<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <title>裕国站计划推演系统</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="shortcut icon" href="${contextPath}/static/images/sytlj.ico" />
    <link rel="stylesheet" href="${contextPath}/static/bs/css/bootstrap.min.css">
    <link rel="stylesheet" href="${contextPath}/static/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${contextPath}/static/ionicons/css/ionicons.min.css">
    <%--<link rel="stylesheet" href="${contextPath}/static/plugins/iCheck/square/blue.css">--%>
    <link rel="stylesheet" href="${contextPath}/static/plugins/daterangepicker/daterangepicker.css">
    <link rel="stylesheet" href="${contextPath}/static/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" href="${contextPath}/static/plugins/iCheck/all.css">
    <link rel="stylesheet" href="${contextPath}/static/plugins/colorpicker/bootstrap-colorpicker.min.css">
    <link rel="stylesheet" href="${contextPath}/static/plugins/timepicker/bootstrap-timepicker.min.css">
    <link rel="stylesheet" href="${contextPath}/static/plugins/select2/select2.min.css">
    <link rel="stylesheet" href="${contextPath}/static/plugins/fullcalendar/fullcalendar.min.css">
    <link rel="stylesheet" href="${contextPath}/static/plugins/fullcalendar/fullcalendar.print.min.css" media="print">
    <link rel="stylesheet" href="${contextPath}/static/adminlte/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${contextPath}/static/adminlte/css/skins/_all-skins.min.css">

    <style type="text/css">
        label.error {
            color: red;
        }

        table tr th {
            text-align: center;
            vertical-align: middle !important;
        }
    </style>
</head>