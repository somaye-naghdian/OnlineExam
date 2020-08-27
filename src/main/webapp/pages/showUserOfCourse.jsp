<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/confirmCss.css" />">
    <title>User List Of Course</title>
    <style>
        .container input {
            height: 30px;
            width: 93%;
            padding: 5px 10px;
            font-size: 16px;
            border-radius: 5px;
            border: 1px solid gray;}
        button{
            padding: 10px;
            font-size: 10px;
            color: white;
            background: darkcyan;
            border: none;
            border-radius: 5px;
            cursor:pointer;}
        label{
            color: #eeeeee;
        }
    </style>
</head>
<body>

<div align="right" style="float: right;"  >
    <button class="btn" onclick="location.href='/admin';">back</button>
</div>
<div class="container">
<form:form modelAttribute="course" action="userOfCourseProcess" method="post">

    <form:label path="courseTitle"> </form:label>
    <form:input path="courseTitle" placeholder="courseTitle" class="form-control"/>

    <form:button name="find" >Find</form:button>
</form:form>
</div>
<br><br>
<div   class="center" >
    <table >
        <thead style="color: #dff0d8"> User List of Course</thead>
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
