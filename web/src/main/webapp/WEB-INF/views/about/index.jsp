<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 中心内容 -->
<div class="content-wrapper">
    <section class="content">
        <div class="row">
            <%-- 需要审验的命令数据 --%>
            <div class="col-md-9">
                <table class="table">
                    <tr>
                        <td>项目策划:</td>
                        <td>沈阳铁路局裕国站, 大连交通大学交通运输工程学院</td>
                    </tr>
                    <tr>
                        <td>程序编制:</td>
                        <td>大连交通大学56工作室</td>
                    </tr>
                    <tr>
                        <td>技术支持微信:</td>
                        <td><img src="${contextPath}/static/about/zszc.jpg"></td>
                    </tr>
                </table>
                <h1>v0.1.2 (2017.07.27)</h1>
                <h3>系统架构</h3>
                <h5>基础模块和结构建立</h5>
                <h5>完成接车计划数据导入和处理</h5>
                <h5>完成接车计划时间窗推演</h5>
                <h5>完成接车计划数据处理和演示</h5>
                <h5>完成接车计划禁峰、超长、超限冲突判定</h5>
                <h5>完成接车计划展示页面</h5>
                <h5>完成调车计划数据导入和处理</h5>
                <h5>完成进路冲突判定</h5>
                <h5>完成所有模块数据展示</h5>
                <h5>完成接车、调车数据汇总</h5>
                <h5>完成场调、站调页面</h5>
                <h5>完成数据统计功能</h5>
                <h5>优化冲突判定</h5>
            </div>
        </div>
    </section>
</div>

<%@ include file="/WEB-INF/include/javascript.jsp"%>

<%@ include file="/WEB-INF/include/footer.jsp"%>