    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <%@ include file="/WEB-INF/include/nav.jsp"%>

    <!-- 中心内容 -->
    <div class="content-wrapper">
    <section class="content" >
    <div class="row">
    <%-- 需要审验的命令数据 --%>
    <div class="col-md-10">
    <table class="table">
    <thread>
    <tr>
        <td>序号</td>
        <td>车次</td>
        <td>作业类别</td>
        <td>开始时间</td>
        <td>结束时间</td>
        <td>源</td>
        <td>目的</td>
        <td>调机</td>
        <td>重点事项</td>
        <td>记事</td>
        <%--<td>进路</td>--%>
    </tr>
    </thread>
    <tbody
        <c:forEach items="${list}" var="list" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${list.TRAIN_NUM}</td>
                <td>${list.jcType}</td>
                <td>${list.jcStartTime}</td>
                <td>${list.TIME}</td>
                <td>${list.NODE_FOUR_WAY}</td>
                <td>${list.TRACK_NUM}</td>
                <td></td>
                <td>${list.jcImportant}</td>
                <td>${list.jcJSL}</td>
                <%--<td>${list.jcDCH}</td>--%>
            </tr>
        </c:forEach>
        </tr>
    </tbody>
    </table>
    </div>
    </div>
    </section>

</div>
        <!--
        <div class="col-md-6">
            <%-- 识别出的和本站相关的命令项table --%>
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">已解读命令</h3>
                </div>
                <div class="box-body">
                    <table class="table table-bordered">
                        <tr>
                            <th>发令日期</th>
                            <th>命令号</th>
                            <th>命令项号</th>
                            <th style="width: 50%;">命令内容</th>
                            <th>车次</th>
                            <th>班次</th>
                            <th class="commandState">状态</th>
                            <th>操作</th>
                        </tr>
                    </table>
                </div>
            </div>
        </section>
    </div>

<%@ include file="/WEB-INF/include/javascript.jsp"%>

<%@ include file="/WEB-INF/include/footer.jsp"%>