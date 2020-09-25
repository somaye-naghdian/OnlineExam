<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/showExamCss.css" />">
</head>
<body>
<h4> Exam List</h4>
<table id="table1">

    <th>Title</th>
    <th>Description</th>
    <th>StartDate</th>
    <th>EndDate</th>
    <th>Time</th>
    <th></th>

    <c:forEach items="${examsOfCourse}" var="exam">
        <form:form action="/takeExam" modelAttribute="exam" method="get">
            <tr>
                <input type="hidden" name="examId" value="${exam.id}"/>
                <input type="hidden" name="user" value="${user.id}">
                <input type="hidden" name="index" value="1">
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



<%--<p style="color: red" >${message}</p>--%>
</body>
</html>
