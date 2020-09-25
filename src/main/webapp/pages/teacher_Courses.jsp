<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="for" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/teacherCss.css" />">
    <title>Title</title>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <button onclick="location.href='teacherDashboard';">back</button>
</nav>
<div class="container">
    <h3 align="center">Course List</h3>
<div class="table1" >
    <table class="tableCourses" align="center">

        <c:forEach items="${courseList}" var="course">
            <tr>
                <td>${course.courseTitle}</td>
                    <form:form action="newExam" modelAttribute="course" method="get">
                        <input type="hidden" name="courseTitle" id="courseTitle" value="${course.courseTitle}">
                        <input type="hidden" name="userEmail" value="${user.email}">
                     <td><button class="btn" id="newExam" value="ACTIVE">New Exam</button></td>
                    </form:form>

                    <form:form action="getExamsOfCourse" method="get">
                        <input type="hidden" name="courseTitle" value="${course.courseTitle}">
                        <input type="hidden" name="user" value="${user.email}">
                      <td> <button class="btn1" id="examList">Exam List</button></td>
                    </form:form>

<%--                <form:form action="/studentsResult" method="get">--%>
<%--                    <input type="hidden" name="courseTitle" value="${course.courseTitle}">--%>
<%--                    <td><button type="submit" value="studentsResult" >studentsResult</button></td>--%>
<%--                </form:form>--%>
            </tr>

        </c:forEach>
    </table>
</div>
</div>

</body>
</html>
