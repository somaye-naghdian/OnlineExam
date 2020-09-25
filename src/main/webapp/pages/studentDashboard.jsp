<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/studentDashboard.css" />">
    <title>Student Page</title>
</head>
<body>
<nav class="nav">
    <div>
        <form action="/" method="get" style="justify-content: flex-end">
            <button type="submit" value="home" class="button">Home</button>
        </form>
    </div>

    <div>
        <form action="/logout" method="get">
            <button type="submit" value="logout" class="button">logout</button>
        </form>
    </div>
</nav>
<div class="container" align="center">
    <table>
        <th></th>
        <th></th>
        <th></th>

        <c:forEach items="${courseList}" var="course">
            <form:form action="/examsOfCourse" method="get">
                <tr>
                    <input type="hidden" name="user" value="${user.email}"/>
                        <%--                    <input type="hidden" name="course" value="${course.id}"/>--%>
                    <input type="hidden" name="courseTitle" value="${course.courseTitle}">
                    <td> ${course.courseTitle}</td>
                    <td><input type="submit" class="button" value="Enter"></td>
                    <td><input type="submit" class="button" value="Grade"
                               formaction="/getStudentResult"></td>
                    <br>
                </tr>
            </form:form>
        </c:forEach>

    </table>
</div>
<br>
<%--<div>--%>
<%--   <form:form action="/getStudentResult" method="get">--%>

<%--        <input type="submit" name="studentResult" value="Grades" class="button" id="grade">--%>
<%--        &lt;%&ndash;           formaction="/getStudentResult">&ndash;%&gt;--%>
<%--    </form:form>*/--%>
<%--</div>--%>


</body>
</html>
