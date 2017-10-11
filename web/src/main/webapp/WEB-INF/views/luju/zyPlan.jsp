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
                                        <th>方向</th>
                                        <th>股道</th>
                                        <th>调别</th>
                                        <th>重点事项</th>
                                        <th>记事</th>
                                        <%--<th>进路</th>--%>
                                    </tr>
                                </thead>
                                <tbody id="showDataTbody">
                                    <c:forEach items="${allList}" var="record" varStatus="status">
                                        <c:set value="${record.selectList }" var="selectList"/>
                                        <tr style="text-align: center;" id="${record.dcId}" dcId ="${record.dcId}" dcTypeE = "${record.dcTypeE}" dcNumber = "${record.dcNumber}" dcType = "${record.dcType}" dcSource = "${record.dcSource}"  dcDestination = "${record.dcDestination}">
                                            <td>${status.index + 1}</td>
                                            <td>${record.dcNumber}</td>
                                            <td>${record.dcType}</td>
                                            <td>${record.dcStartTime}</td>
                                            <td>${record.dcEndTime}</td>
                                            <td>
                                                <c:if test="${record.dcSource == null}">
                                                    <select class="sourceUpdate form-control" style = "width:150px;">
                                                        <c:forEach items="${selectList}" var="recordSelect" varStatus="status">
                                                            <option id = "${recordSelect}" value ="${recordSelect}">${recordSelect}</option>
                                                        </c:forEach>
                                                    </select>
                                                </c:if>
                                                <c:if test="${record.dcSource != null && record.isUpdate == 1}">
                                                    <select class="sourceUpdate form-control" style = "width:150px;">
                                                        <c:forEach items="${selectList}" var="recordSelect" varStatus="status">
                                                            <option id = "${recordSelect}" value ="${recordSelect}">${recordSelect}</option>
                                                        </c:forEach>
                                                    </select>
                                                </c:if>
                                                <c:if test="${record.dcSource != null && record.isUpdate !=1}">
                                                    ${record.dcSource}
                                                </c:if>
                                            </td>
                                            <td>
                                                <c:if test="${record.dcDestination == null}">
                                                    <select class="destinationUpdate form-control" style = "width:150px; text-align: right;">
                                                        <c:forEach items="${selectList}" var="recordSelect" varStatus="status">
                                                            <option value ="${recordSelect}">${recordSelect}</option>
                                                        </c:forEach>
                                                    </select>
                                                </c:if>
                                                <c:if test="${record.dcDestination != null && record.isUpdate == 2}">
                                                    <select class="destinationUpdate form-control" style = "width:150px;">
                                                        <c:forEach items="${selectList}" var="recordSelect" varStatus="status">
                                                            <option id = "${recordSelect}" value ="${recordSelect}">${recordSelect}</option>
                                                        </c:forEach>
                                                    </select>
                                                </c:if>
                                                <c:if test="${record.dcDestination != null && record.isUpdate !=2}">
                                                    ${record.dcDestination}
                                                </c:if>
                                            </td>
                                            <td>${record.dcDj}</td>
                                            <td></td>
                                        </tr>
                                    </c:forEach>
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
                            <table class="table" id="conflict4CCCXTable">
                                <c:forEach items="${allList}" var="entry" varStatus="status">
                                    <c:if test="${(entry.sumHc > 84 && entry.sumHc <100) && (entry.dcDH.equals('03') || entry.dcDH.equals('04')) }">
                                        <tr dcId1="${entry.dcId}">
                                            <td>
                                                ${entry.dcNumber} ${entry.dcType}超长
                                            </td>
                                            <td>
                                                <div style="text-align:right;">
                                                    <button type="button" class="btn btn-warning" value="cx">撤销</button>
                                                    <button type="button" class="btn btn-danger" value="cd" >调整</button>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
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
                            <table class="table" id="conflict4JFTable">
                                <%--<tr>--%>
                                    <%--<td>--%>
                                        <%--10&nbsp行禁峰优先2、3、4道接车--%>
                                    <%--</td>--%>
                                    <%--<td>--%>
                                        <%--<div style="text-align:right;">--%>
                                            <%--<button type="button" class="btn btn-warning">撤销</button>--%>
                                            <%--<button type="button" class="btn btn-danger" value="cd">调整</button>--%>
                                        <%--</div>--%>
                                    <%--</td>--%>
                                <%--</tr>--%>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="box box-warning" style="background-color: #F5F5F5;">
                        <div class="box-header with-border">
                            <h3 class="box-title">分区 交叉进路</h3>
                        </div>
                        <div class="box-body">
                            <table class="table" id="conflict4TwoTable">
                                <%--<tr>--%>
                                    <%--<td>--%>
                                        <%--2&nbsp;行分区错误--%>
                                    <%--</td>--%>
                                    <%--<td>--%>
                                        <%--<div style="text-align:right;">--%>
                                            <%--<button type="button" class="btn btn-warning" value="cx">撤销</button>--%>
                                            <%--<button type="button" class="btn btn-danger" value="cd">调整</button>--%>
                                        <%--</div>--%>
                                    <%--</td>--%>
                                <%--</tr>--%>
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
                                <c:forEach items="${mapList}" var="map" varStatus="status">
                                    <c:forEach items="${map.value}" var="entry" varStatus="status">
                                        <c:set var = "k" value="${map.key}" />
                                        <c:set var = "length" value="${fn:length(k)}"/>
                                        <c:set var = "firstDcId" value="${fn:substring(k, 1, 37)}" />
                                        <c:set var = "firstDcNumber" value="${fn:substring(k, 38, length)}" />
                                            <tr dcId1="${firstDcId}" dcId2="${entry.dcId}" >
                                                <td> ${firstDcNumber} 与 ${entry.dcNumber} ${entry.dcType} 冲突</td>
                                                <td>
                                                    <div style="text-align:right;">
                                                        <button type="button" class="btn btn-warning" value="cx">撤销</button>
                                                        <button type="button" info1="${firstDcNumber}" info2="${entry.dcNumber}" info3="${entry.dcType}" class="btn btn-danger" value="cd">调整</button>
                                                    </div>
                                                </td>
                                            </tr>
                                    </c:forEach>
                                </c:forEach>
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
                        <form action="${contextPath}/luju/textarea.action" method="post">
                            <div class="modal-body" id="noteModalContent">
                                <div class="form-group">
                                    <label>请输入意见：</label>
                                    <textarea id="areaValue" class="form-control" rows="3" name="areaValue"></textarea>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <div class="form-group">
                                    <%--<div class="checkbox">--%>
                                        <%--<label>--%>
                                            <%--<h5>--%>
                                                <%--<input type="checkbox">--%>
                                                <%--提交至站调--%>
                                            <%--</h5>--%>
                                        <%--</label>--%>
                                        <%--<label>--%>
                                            <%--<h5>--%>
                                                <%--<input type="checkbox">--%>
                                                <%--提交至站调--%>
                                            <%--</h5>--%>
                                        <%--</label>--%>
                                    <%--</div>--%>
                                    <div class="box-footer">
                                        <button type="submit" class="btn btn-primary">提交</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <%@ include file="/WEB-INF/include/javascript.jsp"%>




    <script type="text/javascript">
        $(function () {
            $("#showDataTbody select.sourceUpdate").change(function() {
                // run
                var dcId = $(this).parent().parent().attr("dcId");
                var dcTypeE = $(this).parent().parent().attr("dcTypeE");
                var dcDestination = $(this).parent().parent().attr("dcDestination");
                var dcNumber = $(this).parent().parent().attr("dcNumber");
                var dcType = $(this).parent().parent().attr("dcType");
                var selectValue = $(this).val();
                var postData = {
                    dcId : dcId,
                    dcSource: selectValue,
                    dcTypeE: dcTypeE,
                    dcDestination: dcDestination,
                    dcNumber: dcNumber,
                    dcType: dcType
                }

                $.ajax({
                    type:'post',
                    contentType:'application/x-www-form-urlencoded',
                    data:postData,
                    dataType:'json',
                    url:'${contextPath}/luju/updateSource.action',
                    success:function (result) {
                        //console.log(result);
                        // result == Map<String,List<DcPlanInfo>>
                        $("#conflictTable").empty();
                        for (var key in result) {
                            $.each(result[key], function(i1, value2) {
                                var oneTrValue = value2.dcNumber;
                                var twoTrValue = value2.dcType;
                                var threeTrValue = value2.dcId;
                                console.log(key);
                                console.log(threeTrValue);
                                console.log(oneTrValue);
                                console.log(twoTrValue);
                                var strs= new Array(); //定义一数组
                                strs= key.split(" ");
                                var firstDcId = strs[1];
                                var firstDcNumber = strs[2];
                                var firstDcType = strs[3];
                                var processFirstDcid = strs[1].substring(1);
                                console.log(processFirstDcid);
                                console.log(firstDcNumber);
                                console.log(firstDcType);
                                $("#conflictTable").append(
                                    "<tr dcId1 = "+ processFirstDcid +" dcId2= "+threeTrValue +"><td>" + firstDcNumber +" "+ firstDcType + " 与 " +oneTrValue +" "+ twoTrValue+ " 冲突<td>\n" +
                                    "<div style=\"text-align:right;\">\n" +
                                    "<button type=\"button\" class=\"btn btn-warning\">撤销</button>\n" +
                                    "<button type=\"button\" class=\"btn btn-danger\" value=\"cd\">调整</button>\n" +
                                    "</div></td></tr>"
                                );
                            })
                        }
                        afterAppend();
                    }
                })
            })

            $("#showDataTbody select.destinationUpdate").change(function() {

                console.log($(this));
                // run

                var dcId = $(this).parent().parent().attr("dcId");
                var dcTypeE = $(this).parent().parent().attr("dcTypeE");
                var dcSource = $(this).parent().parent().attr("dcSource");
                var dcNumber = $(this).parent().parent().attr("dcNumber");
                var dcType = $(this).parent().parent().attr("dcType");
                var selectValue = $(this).val();
                var postData = {
                    dcId : dcId,
                    dcDestination: selectValue,
                    dcTypeE: dcTypeE,
                    dcSource: dcSource,
                    dcNumber: dcNumber,
                    dcType: dcType
                }

                $.ajax({
                    type:'post',
                    contentType:'application/x-www-form-urlencoded',
                    data:postData,
                    dataType:'json',
                    url:'${contextPath}/luju/updateDestination.action',
                    success:function (result) {
                        console.log(result);
                        // result == list<Map<String,List<DcPlanInfo>>>
                        $("#conflictTable").empty();
                        for (var key in result) {
                            $.each(result[key], function(i1, value2) {
                                var oneTrValue = value2.dcNumber;
                                var twoTrValue = value2.dcType;
                                var threeTrValue = value2.dcId;
                                console.log(key);
                                console.log(threeTrValue);
                                console.log(oneTrValue);
                                console.log(twoTrValue);
                                var strs= new Array(); //定义一数组
                                strs= key.split(" ");
                                var firstDcId = strs[1];
                                var firstDcNumber = strs[2];
                                var firstDcType = strs[3];
                                var processFirstDcid = strs[1].substring(1);
                                console.log(processFirstDcid);
                                console.log(firstDcNumber);
                                console.log(firstDcType);
                                $("#conflictTable").append(
                                    "<tr dcId1 = "+ processFirstDcid +" dcId2= "+threeTrValue +"><td>" + firstDcNumber +" "+ firstDcType + " 与 " +oneTrValue +" "+ twoTrValue+ " 冲突<td>\n" +
                                    "<div style=\"text-align:right;\">\n" +
                                    "<button type=\"button\" class=\"btn btn-warning\">撤销</button>\n" +
                                    "<button type=\"button\" class=\"btn btn-danger\" value=\"cd\">调整</button>\n" +
                                    "</div></td></tr>"
                                );
                            })
                        }
                        afterAppend();
                    }
                })
            })

            function zyPlan(condition) {
                function loadData(result) {

                }
                $.ajax({
                    type:'post',
                    contentType:'application/x-www-form-urlencoded',
                    data:condition,
                    dataType:'json',
                    url:'${contextPath}/luju/zyPlan.action',
                    success:function (result) {
                        loadData(result)
                    }
                })

            }
            // 页面自动刷新
            function webReFlash(){
                window.location="http://222.33.43.200:8080/ygz/luju/zyPlan.action";
            }

            window.setInterval(webReFlash, 1300000);

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
                $("#showDataTbody tr").each(function (index, value) {
                    var bg = RGBToHex($(value).css("background-color"));


                    if (bg == "#ffff00" || bg == "#ff0000") {
                        console.log(bg);
                        $(value).css("background-color", "#F5F5F5");
                    }
                });
            }

            $("#conflict4CCCXTable tr").click(function () {
                console.log("click")
                clearOldColor();

                var dcId1 = $(this).attr("dcId1");
                $("#" + dcId1).css("background-color", "#FF0000");

            });

            function afterAppend() {
                $("#conflictTable tr").click(function () {
                    console.log("click")
                    clearOldColor();

                    var dcId1 = $(this).attr("dcId1");
                    var dcId2 = $(this).attr("dcId2");
                    $("#" + dcId1).css("background-color", "#FFFF00");
                    $("#" + dcId2).css("background-color", "#FFFF00");
                });
            }

            $("#conflictTable tr").click(function () {
                console.log("click")
                clearOldColor();

                var dcId1 = $(this).attr("dcId1");
                var dcId2 = $(this).attr("dcId2");
                $("#" + dcId1).css("background-color", "#FFFF00");
                $("#" + dcId2).css("background-color", "#FFFF00");
            });

            // 调整按钮modal打开
            $("button[value='cd']").click(function () {

                var info1 = $(this).attr("info1");
                var info2 = $(this).attr("info2");
                var info3 = $(this).attr("info3");
                console.log('-----');
                console.log(info1);
                console.log(info2);
                console.log(info3);

                $("#areaValue").val(info1 +' 与 '+ info2+' '+ info3 +' '+ '冲突');
                $("#noteModal").modal();
            });

            // 撤销按钮
            $("button[value='cx']").click(function () {
                var dcId1 = $(this).attr("dcId1");
                var dcId2 = $(this).attr("dcId2");
                $("#" + dcId1).css("background-color", "#F5F5F5");
                $("#" + dcId2).css("background-color", "#F5F5F5");
            });
        })
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
