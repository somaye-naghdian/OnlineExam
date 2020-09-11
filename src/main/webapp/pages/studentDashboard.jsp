<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/showExamCss.css" />">
    <title>Student Page</title>
</head>
<body>
<div class="container" align="center">
    <table>
        <th></th>
        <tr>
            <c:forEach items="${courseList}" var="course">
                <form:form action="/examsOfCourse" method="get">
                    <input type="hidden" name="user" value="${user.email}"/>
                    <input type="hidden" name="courseTitle" value="${course.courseTitle}">
                    <td> ${course.courseTitle}</td>
                    <td><input type="submit" class="button" value="Enter"></td>
                </form:form>
            </c:forEach>
        </tr>
    </table>
</div>
<br>
<div>
    <form action="/" method="get" style="justify-content: flex-end">
        <button style="margin:5px;color: midnightblue; cursor: pointer; background-color: powderblue;" type="submit"
                value="home" class="button">Home
        </button>
    </form>
</div>
</body>
</html>
