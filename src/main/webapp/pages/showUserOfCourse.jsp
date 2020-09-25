<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/addUserToCourse.css" />">
    <title>User List Of Course</title>
</head>
<body>

<div align="right" style="float: right;"  >
    <button class="button" onclick="location.href='/admin';">back</button>
</div>
<div class="container1">
<form:form modelAttribute="course" action="userOfCourseProcess" method="post">

    <form:label path="courseTitle"> </form:label>
    <form:input path="courseTitle" placeholder="courseTitle"/>

    <form:button name="find" class="button" >Find</form:button>
</form:form>
</div>

<div   class="center" >
    <table >
        <thead id="thead">User List of Course</thead>
        <tr>
            <th>Name</th>
            <th>Family</th>
            <th>Email</th>
            <th>Role</th>
        </tr>

        <c:forEach items="${userOfCourse}" var="user">
        <tbody style="color: #eeeeee">
        <tr>
            <td>${user.name}</td>
            <td>${user.family}</td>
            <td>${user.email}</td>
            <td>${user.role}</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
