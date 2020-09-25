<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/teacher_studentResult.css" />">
</head>
<body>
<table id="table1">
    <th>answer</th>
    <th>enter score </th>
    <th>submit score</th>
    <c:forEach items="${descriptiveAnswer}" var="answer">
        <form:form action="/addDescriptiveScore" method="get">
            <input type="hidden" name="studentId" value="${student.id}">
            <input type="hidden" name="examId" value="${exam.id}">
            <tr>
                <td size="15">${answer.key} </td>
<%--                <td>${answer.value.toString()}</td>--%>
            <td><input name="score" size="5"></td>
            <td> <input type="submit" class="button"></td>
            </tr>
        </form:form>
    </c:forEach>
</table>
</body>
</html>
