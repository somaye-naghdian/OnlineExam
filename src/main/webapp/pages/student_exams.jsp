<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/showExamCss.css" />">
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
                <input type="hidden" name="examId" value="${exam.id}"/>
                <input type="hidden" name="user" value="${user.id}">
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

<p>${message}</p>
</body>
</html>
