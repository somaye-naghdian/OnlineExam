<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/searchCss.css" />">
    <title>Search</title>
</head>
<body>
<div class="container">
    <form:form action="searchProcess" modelAttribute="user" method="post">
        <div class="input-group">
            <form:input path="name" name="name" placeHolder="Name"/>

            <form:input path="family" name="family" placeHolder="Family"/>

            <form:input path="email" name="email" placeHolder="email"/>

            <form:input path="role" name="role" placeHolder="role"/>

            <form:button name="search">Search</form:button>
        </div>
        <br><br>
        <table align="center">
            <tr>
                <th>Name</th>
                <th>Family</th>
                <th>Email</th>
                <th>Role</th>
            </tr>

            <c:forEach items="${userList}" var="user">
                <tr>
                    <td>${user.name}</td>
                    <td>${user.family}</td>
                    <td>${user.email}</td>
                    <td>${user.role}</td>
                </tr>
            </c:forEach>

            </tr>
        </table>

        <br>
    </form:form>
    <form action="/" method="get">
        <button style="margin:5px;color: midnightblue; cursor: pointer; background-color: powderblue;" type="submit"
                value="home" class="btn">Home
        </button>
    </form>
</div>
</body>
</html>
