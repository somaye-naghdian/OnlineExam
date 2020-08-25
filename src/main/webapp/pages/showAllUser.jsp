<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/showAllUserCss.css" />">

    <title>Show All Users</title>
</head>
<body>
<h4 >User List</h4>
<table class="center" id="t01">

    <tr>
        <th>Name</th>
        <th>Family</th>
        <th>Email</th>
        <th>Role</th>
        <th>Status</th>
    </tr>

    <c:forEach items="${userList}" var="user" >
    <tr>
        <td>${user.name}</td>
        <td>${user.family}</td>
        <td>${user.email}</td>
        <td>${user.role}</td>
        <td>${user.isEnabled().toString()}</td>
    </tr>
    </c:forEach>

</table>
<div>
    <form action="/" method="get">
        <button style="margin:5px;color: midnightblue; cursor: pointer; background-color: powderblue;" type="submit"
                value="home" class="btn" >Home
        </button>
    </form>
</div>

</body>
</html>
