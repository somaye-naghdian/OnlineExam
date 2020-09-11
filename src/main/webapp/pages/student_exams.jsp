<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/showExamCss.css" />">
<style>
    th , td{
        text-align: left;
        padding: 8px;
    }
    tr:nth-child(even) {background-color: #f2f2f2;}
</style>
</head>
<body>

<table>

    <th>Title</th>
    <th>Description</th>
    <th>StartDate</th>
    <th>EndDate</th>
    <th>Time</th>
    <th></th>

    <c:forEach items="${examsOfCourse}" var="exam">
        <form:form action="/takeExam" modelAttribute="exam" method="post">
            <tr>
                <input  type="hidden" name="examId" value="${exam.id}" />
                <td>${exam.title}</td>
                <td>${exam.description}</td>
                <td>${exam.startDate}</td>
                <td>${exam.endDate}</td>
                <td>${exam.time}</td>
                <td><input type="submit" class="button" value="take"></td>
            </tr>
        </form:form>
    </c:forEach>
</table>
</body>
</html>
