<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/header.jsp"%>

<body class="skin-blue sidebar-mini sidebar-collapse" style="height: auto;">
<div class="wrapper" style="height: auto;">

<script src="${contextPath}/static/plugins/echarts/echarts.min.js"></script>
<header class="main-header">
    <!-- 头部内容 -->
    <nav class="navbar" style="margin-left: 0px;">
        <div class="navbar">
            <ul class="nav navbar-nav">
                <li><a href="${contextPath}/luju/xxPlan.action"><strong>下行</strong></a></li>
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
                <li><a href="${contextPath}/luju/statistics/index.action"><strong>统计</strong></a></li>
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

<!-- 内容主体 -->

<div class="content-wrapper" style="margin-left: 0px !important;">
    <div class="content-wrapper">
        <section class="content">
            <div class="row">
                <div class="col-lg-3 col-xs-6">
                    <div class="small-box bg-red">
                        <div class="inner">
                            <h3>${error}</h3>
                            <p>错办进路</p>
                            <p></p>
                        </div>
                        <div class="icon">
                            <i class="fa fa-exclamation"></i>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-xs-6">
                    <div class="small-box bg-aqua">
                        <div class="inner">
                            <h3>${better}</h3>
                            <p>优先进路</p>
                        </div>
                        <div class="icon">
                            <i class="fa fa-file-text-o"></i>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-xs-6">
                    <div class="small-box bg-green">
                        <div class="inner">
                            <h3>${jlConflict}</h3>
                            <p>交叉进路</p>
                        </div>
                        <div class="icon">
                            <i class="fa fa-hourglass-2"></i>
                        </div>
                    </div>
                </div>
                <div class="col-md-12" >
                    <h3>一个月内的报修量与维修量统计折线图</h3>
                    <div class="box box-primary">
                        <div class="box-body">
                            <div class="chart">
                                <div id="monthConflict" style="height:250px"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="box-body no-padding">
                    <table class="table" id="zuoyejihuaTable">
                        <thead>
                        <tr style="text-align: center;">
                            <th>序号</th>
                            <th>记录时间</th>
                            <th>车次1</th>
                            <th>车次2</th>
                            <th>进路冲突</th>
                            <th>用户</th>
                            <th>细则</th>
                        </tr>
                        </thead>
                        <tbody id="showDataTbody">
                        <c:forEach items="${allList}" var="record" varStatus="status">
                            <tr>
                                <td>${status.index + 1}</td>
                                <td>${record.logTime}</td>
                                <td>${record.data1}</td>
                                <td>${record.data2}</td>
                                <td>${record.type}</td>
                                <td>${record.user}</td>
                                <td>${record.details}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
    </div>
</div>

<%@ include file="/WEB-INF/include/javascript.jsp"%>
<script>


    var monthForLineChart = echarts.init(document.getElementById('monthConflict'));

    var optionMonthConflict = {
        title: {
            text: '进路冲突统计面板'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['冲突数量']
        },
        toolbox: {
            show: true,
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                dataView: {readOnly: false},
                magicType: {type: ['line', 'bar']},
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis:  {
            type: 'category',
            show: true,
            axisLabel:{interval: 0},
            boundaryGap: false,
            data: []
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value}'
            }
        },
        series: [
            {
                name:'冲突数量',
                type:'line',
                data:[],
                markPoint: {
                    data: [
                        {name: '周最低', value: -2, xAxis: 1, yAxis: -1.5}
                    ]
                }
            }
        ]
    };

    $(function () {
        $.ajax({
            type: 'post',
            contentType: 'application/json',
            dataType: 'json',
            url: '${contextPath}/luju/statistics/all.action',
            success: function (result) {
                $.each(result.monthConflict, function (i, item) {
                    optionMonthConflict.xAxis.data.push(item.logTime);
                    optionMonthConflict.series[0].data.push(item.value);
                });

                monthForLineChart.setOption(optionMonthConflict);
            }
        })
    })

</script>

<!-- 底部栏 -->
<footer class="main-footer" style="margin-left: 0px !important;">
    <div class="pull-right hidden-xs">
        <b>版本</b> 0.3.5
    </div>
    <strong><a href="${contextPath}/about/index.action">版本历史</a></strong>
</footer>
</div>


