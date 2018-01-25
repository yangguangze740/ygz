<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/header.jsp"%>

<body class="hold-transition skin-blue sidebar-mini sidebar-collapse">
<div class="wrapper">

    <header class="main-header">
        <!-- 头部内容 -->
        <nav class="navbar" style="margin-left: 0px;">
            <div class="navbar">
                <ul class="nav navbar-nav">
                    <li><a href="${contextPath}/luju/xxPlan.action">下行</a></li>
                    <li><a href="${contextPath}/luju/zyPlan.action">下到</a></li>
                    <li><a href="${contextPath}/luju/xzPlan.action">下直</a></li>
                    <li><a href="${contextPath}/luju/xfPlan.action">下发</a></li>
                </ul>
                <ul class="nav navbar-nav" style="margin-left: 50px;">
                    <li><a href="${contextPath}/luju/sxPlan.action"><strong>上行</strong></a></li>
                    <li><a href="${contextPath}/luju/sdPlan.action">上到</a></li>
                    <li><a href="${contextPath}/luju/szPlan.action">上直</a></li>
                    <li><a href="${contextPath}/luju/sfPlan.action">上发</a></li>
                </ul>
                <ul class="nav navbar-nav" style="margin-left: 50px;">
                    <li><a href="#"><strong>全站</strong></a></li>
                </ul>
                <ul class="nav navbar-nav" style="float: right;">
                    <li><a href="${contextPath}/luju/statistics.action">查询</a></li>
                    <li style="float: right;">

                        <a href="#" class="dropdown-toggle" id="nowDay">日期：
                        </a>
                    </li>
                    <script>
                        document.getElementById("nowDay").innerHTML = new Date().toLocaleDateString();
                    </script>
                </ul>
            </div>
        </nav>
    </header>

    <!-- 中心内容 -->
    <div class="content-wrapper" style="margin-left: 0px !important;">
        <section class="content">
            <div class="row">
                <%-- 需要审验的命令数据 --%>
                <div class="col-md-8">
                    <%-- 设定作业计划box背景色--%>

                        <div class="form-group">
                            <label>请选择时间：</label>
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <div class="col-md-4">
                                    <input type="text" class="form-control pull-right" id="datepicker">
                                </div>
                            </div>
                        </div>

                    <div class="box box-success" style="background-color: #F5F5F5;">
                        <div class="box-header with-border">
                            <h3 class="box-title">裕国站计划交叉进路查询</h3>
                        </div>



                        <div class="box-body no-padding">
                            <table class="table" id="StatisticsTable">
                                <thead>
                                <tr style="text-align: center;">
                                    <th>时间</th>
                                    <th>车次1</th>
                                    <th>车次2</th>
                                </tr>
                                </thead>
                                <tbody id="StatisticsTableTbody">
                                <c:forEach items="${listNow}" var="record" varStatus="status">
                                    <tr style="text-align: center;">
                                        <td>${record.logTime}</td>
                                        <td>${record.data1}</td>
                                        <td>${record.data2}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-md-4"></div>
                <div class="col-md-4"></div>
                <div class="col-md-4"></div>
                <div class="col-md-4"></div>
            </div>

            <%-- 弹出modal --%>
        </section>
    </div>

    <%@ include file="/WEB-INF/include/javascript.jsp"%>
    <script type="text/javascript">

        $('#datepicker').datepicker({
            autoclose: true,
            format: 'yyyy-mm-dd'
        });

        $('#datepicker').change(function () {

            var dateWithChange = $(this).val();
            console.log(dateWithChange);
            var postData = {
                "dateWithChange" : dateWithChange
            }

            $.ajax({
                type:'post',
                contentType:'application/x-www-form-urlencoded',
                data:postData,
                dataType:'json',
                url:'${contextPath}/luju/time.action',
                success:function (result) {
                    console.log(result);
                    $("#StatisticsTableTbody").empty();

                    $.each(result, function(i, value) {
                        var logTime = value.logTime;
                        var data1 = value.data1;
                        var data2 = value.data2;

                        $("#StatisticsTableTbody").append(
                            "<tr style=\"text-align: center;\">"+
                            "<td>" +logTime+ "</td>"+
                            "<td>" +data1+ "</td>"+
                            "<td>" +data2+ "</td>"
                        );
                    })
                }
            })
        });
    </script>

    <!-- 底部栏 -->
    <footer class="main-footer" style="margin-left: 0px !important;">
        <div class="pull-right hidden-xs">
            <b>版本</b> 0.1.2
        </div>
        <strong><a href="${contextPath}/about/index.action">版本历史</a></strong>
    </footer>
</div>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="${contextPath}/static/other/html5shiv.min.js"></script>
<script src="${contextPath}/static/other/respond.min.js"></script>
<![endif]-->
</body>
</html>
