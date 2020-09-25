<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>student result</title>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/studentResult.css" />">

</head>
<body>
<%--<p> total score in test section :  ${score}</p>--%>
<div class="examScore">
    <h4>Score each Exam </h4>
    <table id="table2">

        <th>Title</th>
        <th>Description</th>

            <c:forEach items="${studentExamResult}" var="exam">
                <tr>
                <td>${exam.key.title}</td>
                <td>${exam.value}</td>
                </tr>
            </c:forEach>
<%--        </form:form>--%>
    </table>
</div>


</body>
</html>
