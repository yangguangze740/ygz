<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/header.jsp"%>

<body class="hold-transition skin-blue sidebar-mini sidebar-collapse">
<div class="wrapper">

    <header class="main-header">
        <!-- 头部内容 -->
        <nav class="navbar" style="margin-left: 0px;">
            <div class="navbar">
                <ul class="nav navbar-nav">
                    <li><a href="#">下到</a></li>
                    <li><a href="#">下直</a></li>
                    <li><a href="#">下发</a></li>
                    <li><a href="#">下行</a></li>
                    <li><a href="#">上到</a></li>
                    <li><a href="#">上直</a></li>
                    <li><a href="#">上发</a></li>
                    <li><a href="#">上行</a></li>
                    <li><a href="#">全站</a></li>
                    <li style="float: right;">
                        <a href="#" class="dropdown-toggle" id="nowDay">
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
                    <div class="box box-success" style="background-color: #F5F5F5;">
                        <div class="box-header with-border">
                            <h3 class="box-title">裕国站下行到达场作业计划推演</h3>
                        </div>
                        <div class="box-body no-padding">
                            <table class="table" id="zuoyejihuaTable">
                                <thead>
                                <tr style="text-align: center;">
                                    <th>序号</th>
                                    <th>车次</th>
                                    <th>作业类别</th>
                                    <th>开始时间</th>
                                    <th>结束时间</th>
                                    <th>源</th>
                                    <th>目的</th>
                                    <th>调机</th>
                                    <th>重点事项</th>
                                    <th>记事</th>
                                    <th>进路</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${dcSet}" var="record" varStatus="status">
                                        <tr>
                                            <td>${status.index + 1}</td>
                                            <td>${record.dcNumber}</td>
                                            <td>${record.dcType}</td>
                                            <td>${record.dcStartTime}</td>
                                            <td>${record.dcEndTime}</td>
                                            <td></td>
                                            <td>${record.dcDestination}</td>
                                            <td></td>
                                            <td><select class="form-control" name="zyPlan">
                                                <option value="0">请选择状态</option>
                                                <option value="6">已完成</option>
                                            </select></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                </c:forEach>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="box box-danger" style="background-color: #F5F5F5;">
                        <div class="box-header with-border">
                            <h3 class="box-title">超长、超限 错办进路</h3>
                        </div>
                        <div class="box-body">
                            <table class="table" id="conflict4ErrorTable">
                                <tr>
                                    <td>
                                        2&nbsp行超长4、5道接车
                                    </td>
                                    <td>
                                        <div style="text-align:right;">
                                            <button type="button" class="btn btn-warning">撤销</button>
                                            <button type="button" class="btn btn-danger" value="cd">调整</button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        8&nbsp行超限2、5道接车
                                    </td>
                                    <td>
                                        <div style="text-align:right;">
                                            <button type="button" class="btn btn-warning">撤销</button>
                                            <button type="button" class="btn btn-danger" value="cd">调整</button>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="box box-warning" style="background-color: #F5F5F5;">
                        <div class="box-header with-border">
                            <h3 class="box-title">禁峰 交叉进路</h3>
                        </div>
                        <div class="box-body">
                            <table class="table" id="conflict4CCCXTable">
                                <tr>
                                    <td>
                                        10&nbsp行禁峰优先2、3、4道接车
                                    </td>
                                    <td>
                                        <div style="text-align:right;">
                                            <button type="button" class="btn btn-warning">撤销</button>
                                            <button type="button" class="btn btn-danger" value="cd">调整</button>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="box box-warning" style="background-color: #F5F5F5;">
                        <div class="box-header with-border">
                            <h3 class="box-title">分区交叉进路</h3>
                        </div>
                        <div class="box-body">
                            <table class="table" id="conflict4TwoTable">
                                <tr>
                                    <td>
                                        2&nbsp;行分区错误
                                    </td>
                                    <td>
                                        <div style="text-align:right;">
                                            <button type="button" class="btn btn-warning">撤销</button>
                                            <button type="button" class="btn btn-danger" value="cd">调整</button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        11&nbsp;行分区错误
                                    </td>
                                    <td>
                                        <div style="text-align:right;">
                                            <button type="button" class="btn btn-warning">撤销</button>
                                            <button type="button" class="btn btn-danger" value="cd">调整</button>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="box box-warning" style="background-color: #F5F5F5;">
                        <div class="box-header with-border">
                            <h3 class="box-title">计划交叉进路</h3>
                        </div>
                        <div class="box-body">
                            <table class="table" id="conflictTable">
                                <tr>
                                    <td>
                                        2、5&nbsp;行进路交叉
                                    </td>
                                    <td>
                                        <div style="text-align:right;">
                                            <button type="button" class="btn btn-warning">撤销</button>
                                            <button type="button" class="btn btn-danger" value="cd">调整</button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        3、9&nbsp;行进路交叉
                                    </td>
                                    <td>
                                        <div style="text-align:right;">
                                            <button type="button" class="btn btn-warning">撤销</button>
                                            <button type="button" class="btn btn-danger" value="cd">调整</button>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

            <%-- 弹出modal --%>
            <div class="modal fade" id="noteModal">
                <div class="modal-dialog" role="dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">修改意见</h4>
                        </div>
                        <div class="modal-body" id="noteModalContent">
                            <div class="form-group">
                                <label>请填写意见</label>
                                <textarea class="form-control" rows="3" placeholder="请输入意见......"></textarea>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-success" data-dismiss="modal">提交至场调</button>
                            <button type="button" class="btn btn-success" data-dismiss="modal">提交至站调</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <%@ include file="/WEB-INF/include/javascript.jsp"%>

    <script type="text/javascript">
        $(function () {
            // 颜色rgb转换为16进制
            function RGBToHex(rgb){
                rgb = rgb.match(/^rgba?[\s+]?\([\s+]?(\d+)[\s+]?,[\s+]?(\d+)[\s+]?,[\s+]?(\d+)[\s+]?/i);
                return (rgb && rgb.length === 4) ? "#" +
                ("0" + parseInt(rgb[1],10).toString(16)).slice(-2) +
                ("0" + parseInt(rgb[2],10).toString(16)).slice(-2) +
                ("0" + parseInt(rgb[3],10).toString(16)).slice(-2) : '';
            }

            // 清空上一次冲突选择后的颜色
            function clearOldColor() {
                $("#zuoyejihuaTable tr").each(function (index, value) {
                    var bg = RGBToHex($(value).css("background-color"));

                    if (bg == "#FFFF00") {
                        $(value).css("background-color", "#F5F5F5");
                    }
                });
            }

            $("#conflictTable tr td").click(function () {
                console.log($(this).attr("value"));
                console.log($(this).attr("id"));

                var postData = {

                };
                $.ajax({
                    type: "post",
                    contentType: 'application/x-www-form-urlencoded',
                    data: postData,
                    dateType: 'json',
                    url: "${contextPath}/",
                    success: function(result) {
                        clearOldColor();

                        $.each(result.list, function (index, value) {
                            $("#" + value).css("background-color", "#FFFF00");
                        });
                    }
                });
            });

            // 调整按钮modal打开
            $("button[value='cd']").click(function () {
                $("#noteModal").modal();
            });
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
