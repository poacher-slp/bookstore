<%--
  Created by IntelliJ IDEA.
  User: 13211
  Date: 2021/5/3
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="page_nav">

    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>

    <c:choose>
        <%-- 如果总页码小于等于5，页码的范围：1-总页码 --%>
        <c:when test="${requestScope.page.pageTotal <= 5}">
            <c:set var="begin" value="1" />
            <c:set var="end" value="${requestScope.page.pageTotal}" />
        </c:when>
        <%--总页码大于5--%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <%--如果页码小于等于5，页码的范围是1-5，--%>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:set var="begin" value="1" />
                    <c:set var="end" value="5" />
                </c:when>
                <%--	如果当前页码在最后三页中，页码范围：总页码-4 —— 总页码	--%>
                <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}" />
                    <c:set var="end" value="${requestScope.page.pageTotal}" />
                </c:when>
                <%--  当前页码在中间的情况，页码范围：当前页码-2 —— 当前页码+2	--%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo - 2}" />
                    <c:set var="end" value="${requestScope.page.pageNo + 2}" />
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i == requestScope.page.pageNo}">
            [ ${i} ]
        </c:if>
        <c:if test="${i != requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>

    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo + 1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value="4" name="pn" id="pn_input"/>页
    <input id="searchBtn" type="button" value="确定">
    <script type="text/javascript">
        $(function () {
            $("#searchBtn").click(function () {
                var val = $("#pn_input").val();	//获取输入框的值
                var pageTotal = ${requestScope.page.pageTotal};
                //判断输入框的值
                if(val < 1 || val > pageTotal) {
                    alert("页数不存在！请重新输入。")
                    return;
                }
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + val;
            })
        })
    </script>
</div>