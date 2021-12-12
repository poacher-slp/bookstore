<%--
  Created by IntelliJ IDEA.
  User: 13211
  Date: 2021/5/3
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="page_nav">

    <c:if test="${requestScope.page.hasPreviousPage}">
        <a href="${requestScope.url}&pageNum=1">首页</a>
        <a href="${requestScope.url}&pageNum=${requestScope.page.pageNum-1}">上一页</a>
    </c:if>

    <c:forEach items="${requestScope.page.navigatepageNums}" var="page_Num">
        <c:if test="${page_Num == requestScope.page.pageNum}">
            [ ${page_Num} ]
        </c:if>
        <c:if test="${page_Num != requestScope.page.pageNum}">
            <a href="${requestScope.url}&pageNum=${page_Num}">${page_Num}</a>
        </c:if>
    </c:forEach>

    <c:if test="${requestScope.page.hasNextPage}">
        <a href="${requestScope.url}&pageNum=${requestScope.page.pageNum + 1}">下一页</a>
        <a href="${requestScope.url}&pageNum=${requestScope.page.pages}">末页</a>
    </c:if>

    共${requestScope.page.pages}页，${requestScope.page.total}条记录
    到第<input value="${requestScope.page.pageNum}" name="pn" id="pn_input"/>页
    <input id="searchBtn" type="button" value="确定">
    <script type="text/javascript">
        $(function () {
            $("#searchBtn").click(function () {
                var val = $("#pn_input").val();	//获取输入框的值
                var pageTotal = ${requestScope.page.pages};
                //判断输入框的值
                if(val < 1 || val > pageTotal) {
                    alert("页数不存在！请重新输入。")
                    return;
                }
                location.href = "${pageScope.basePath}${requestScope.url}?pageNum=" + val;
            })
        })
    </script>
</div>