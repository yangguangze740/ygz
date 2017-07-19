<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

    <!-- 底部栏 -->
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 0.2.1
        </div>
        <strong><a href="${contextPath}/about/index.action">版本历史</a></strong>
    </footer>
</div>

<script type="text/javascript">
    $(function () {
//       $("#closeNav").click();
    });
</script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="${contextPath}/static/other/html5shiv.min.js"></script>
<script src="${contextPath}/static/other/respond.min.js"></script>
<![endif]-->
</body>
</html>