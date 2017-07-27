<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

    <!-- 中心内容 -->
    <div class="content-wrapper">
    <section class="content" >
        <div class="row">
            <%-- 需要审验的命令数据 --%>
            <div class="col-md-10">
                <%-- 设定作业计划box背景色--%>
                <div class="box box-primary" style="background-color: #F5F5F5;">
                    <div class="box-header with-border">
                        <h3 class="box-title">作业计划</h3>
                    </div>
                    <div class="box-body">
                        <table class="table" id="zuoyejihuaTable">
                            <thread>
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
                                    <%--<td>进路</td>--%>
                                </tr>
                            </thread>
                                <tbody>
                                    <c:forEach items="${list}" var="record" varStatus="status">
                                        <c:if test="${record.color == 1}">
                                            <tr style="background-color: #ff9900; text-align: center;" id="${record.TRAIN_NUM}">
                                                <td>${status.index + 1}</td>
                                                <td>${record.TRAIN_NUM}</td>
                                                <td>${record.jcType}</td>
                                                <td>${record.jcStartTime}</td>
                                                <td>${record.TIME}</td>
                                                <td>${record.NODE_FOUR_WAY}</td>
                                                <td>${record.TRACK_NUM}</td>
                                                <td></td>
                                                <td>${record.jcImportant}</td>
                                                <td>${record.jcJSL}</td>
                                                    <%--<td>${list.jcDCH}</td>--%>
                                            </tr>
                                        </c:if>
                                        <c:if test="${record.color == 2}">
                                            <tr style="background-color: #ff2222; text-align: center;" id="${record.TRAIN_NUM}">
                                                <td>${status.index + 1}</td>
                                                <td>${record.TRAIN_NUM}</td>
                                                <td>${record.jcType}</td>
                                                <td>${record.jcStartTime}</td>
                                                <td>${record.TIME}</td>
                                                <td>${record.NODE_FOUR_WAY}</td>
                                                <td>${record.TRACK_NUM}</td>
                                                <td></td>
                                                <td>${record.jcImportant}</td>
                                                <td>${record.jcJSL}</td>
                                                    <%--<td>${list.jcDCH}</td>--%>
                                            </tr>
                                        </c:if>
                                        <c:if test="${record.color == 0}">
                                            <tr style="text-align: center;" id="${record.TRAIN_NUM}">
                                                <td>${status.index + 1}</td>
                                                <td>${record.TRAIN_NUM}</td>
                                                <td>${record.jcType}</td>
                                                <td>${record.jcStartTime}</td>
                                                <td>${record.TIME}</td>
                                                <td>${record.NODE_FOUR_WAY}</td>
                                                <td>${record.TRACK_NUM}</td>
                                                <td></td>
                                                <td>${record.jcImportant}</td>
                                                <td>${record.jcJSL}</td>
                                                    <%--<td>${list.jcDCH}</td>--%>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                    </tr>
                                </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">冲突数据</h3>
                    </div>
                    <div class="box-body">
                        <table class="table" id="conflictTable">
                            <c:forEach items="${list}" var="record">
                                <tr>
                                    <td value="${record.TRAIN_NUM}" id="${record.TRAIN_NUM}">${record.TRAIN_NUM}</td>
                                </tr>
                            </c:forEach>
                        </table>
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

                if (bg == "#ff9900") {
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
               url: "${contextPath}",
               success: function(result) {
                   clearOldColor();

                   $.each(result.list, function (index, value) {
                      $("#" + value).css("background-color", "#ff9900");
                   });
               }
           });
       });
    });
</script>

<%@ include file="/WEB-INF/include/footer.jsp"%>