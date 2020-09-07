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
<div>
    <h3>Course List</h3>

    <c:forEach items="${courseList}" var="course">
        <tr><br>
            <td>${course.courseTitle}
                <form:form action="newExam" modelAttribute="course" method="get">
                    <input type="hidden" name="courseTitle" id="courseTitle" value="${course.courseTitle}">
                    <input type="hidden" name="userEmail" value="${user.email}">
                    <button class="btn" id="newExam" value="ACTIVE">New Exam</button>
                </form:form>

                <for:form action="getExamsOfCourse" method="get">
                    <input type="hidden" name="courseTitle" value="${course.courseTitle}">
                    <input type="hidden" name="user" value="${user.email}">
                    <button class="btn1" id="examList">Exam List</button>
                </for:form>


            </td>
        </tr>

    </c:forEach>

</div>
<div>
    <ul>
        <li id="exams">
        </li>
    </ul>
</div>

</body>
</html>
