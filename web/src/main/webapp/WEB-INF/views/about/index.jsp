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
                        <td>沈阳铁路局沈阳站, 大连交通大学交通运输工程学院</td>
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
                <h1>v0.2.5 (2017.07.13)</h1>
                <h3>重点事项</h3>
                <h5>[fix]重点事项总视图</h5>
                <h1>v0.2.4 (2017.07.13)</h1>
                <h3>系统基础模块</h3>
                <h5>[fix]命令文件导入bug</h5>
                <h5>[fix]调整统计图布局</h5>
                <h5>[new]重点事项是否包含命令解读开关</h5>
                <h5>[new]重点事项视图站设定</h5>
                <h5>[new]重点事项列表首页</h5>
                <h1>v0.2.3 (2017.06.27)</h1>
                <h3>系统基础模块</h3>
                <h5>[new]重点事项作业项管理</h5>
                <%--<h5>[new]重点事项和已解读命令读取配置开关</h5>--%>
                <h5>[fix]车次和终到站合并的返回按钮bug</h5>
                <h5>[fix]统计图柱状图y轴坐标为整数bug</h5>
                <h5>[fix]统计图柱状图x轴坐标顺序bug</h5>
                <h5>[new]重点事项系统开关</h5>
                <h1>v0.2.2 (2017.06.19)</h1>
                <h3>系统基础模块</h3>
                <h5>[fix]待审验,已审验,待重做统计数据映射Bug</h5>
                <h5>[fix]命令审验审验完成文字位置Bug</h5>
                <h5>[fix]命令查询班次和状态查询文字</h5>
                <h3>系统配置模块</h3>
                <h3>UI</h3>
                <h5>[fix]系统配置模块页面文字新建和保存</h5>
                <h5>[fix]系统配置模块页面table标题和内容居中</h5>
                <h5>[fix]统一系统基础模块table标题居中</h5>
                <h1>v0.2.1 (2017.06.12)</h1>
                <h3>系统基础模块</h3>
                <h5>[new]命令解读</h5>
                <h5>[fix]命令审验</h5>
                <h5>[new]命令查询</h5>
                <h5>[add]命令跟踪</h5>
                <h5>[add]数据统计</h5>
                <h3>系统配置模块</h3>
                <h5>[modify]车次管理和终到站管理</h5>
                <h1>v0.1.0 (2017.06.01)</h1>
                <h3>系统基础模块</h3>
                <h5>1.命令解读</h5>
                <h5>2.命令审验</h5>
                <h5>3.命令查询</h5>
                <h3>系统配置模块</h3>
                <h5>1.人员管理</h5>
                <h5>2.作业项管理</h5>
                <h5>3.关键字管理</h5>
                <h5>4.车站管理</h5>
                <h5>5.车次管理</h5>
                <h5>6.终到站管理</h5>
                <h5>7.命令文件预览描述开关</h5>
            </div>
        </div>
    </section>
</div>

<%@ include file="/WEB-INF/include/javascript.jsp"%>

<%@ include file="/WEB-INF/include/footer.jsp"%>