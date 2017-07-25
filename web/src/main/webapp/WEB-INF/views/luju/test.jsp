<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 中心内容 -->
<div class="content-wrapper">
    <section class="content">
        <div class="row">
            <%-- 需要审验的命令数据 --%>
            <div class="col-md-9">
                <table class="table">
                    <thread>
                    <tr>
                        <td>序号</td>
                        <td>车次</td>
                        <td>开始时间</td>
                        <td>结束时间</td>
                        <td>作业类别</td>
                        <td>源</td>
                        <td>目的</td>
                        <td>调机</td>
                        <td>重点事项</td>
                        <td>记事</td>
                        <td>进路</td>
                    </tr>
                    </thread>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>41111</td>
                            <td>09:24</td>
                            <td>09:32</td>
                            <td>接车</td>
                            <td>马三家</td>
                            <td>XD04</td>
                            <td></td>
                            <td>优先2,3,4道接</td>
                            <td>禁峰</td>
                            <td>XD06;105；107；109；111；113；115；117；119；141；（103）；</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
</div>

<%@ include file="/WEB-INF/include/javascript.jsp"%>

<%@ include file="/WEB-INF/include/footer.jsp"%>