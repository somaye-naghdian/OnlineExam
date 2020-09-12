<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/confirmCss.css" />">
    <title>Waiting users</title>

</head>
<body>

<h4 id="h04"> Confirm User And Change User Attributes</h4>

<div align="right" style="float: right;">
    <button class="btn" onclick="location.href='/admin';">back</button>
</div>
<br>

<div class="homeTab">
    <form action="/" method="get">
        <button type="submit" class="btn">
            Home
        </button>
    </form>
</div>
<c:choose>

    <c:when test="${empty waitingUsers}">

    </c:when>

    <c:otherwise>
        <div class="resultTable">
            <table class="table table-hover table-striped table-bordered">
                <thead>
                <tr>

                    <th>name</th>
                    <th>family</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Status</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${waitingUsers}" var="user">
                    <form:form modelAttribute="user" action="/confirmUser" method="get">

                        <tr>
                            <input name="id" path="id"
                                   type="hidden" value="${user.id}"/>

                            <td><input path="name" name="name" type="text"
                                       value="${user.name}"/> </td>
                            <td>
                                <input name="family" path="family"
                                       type="text" value="${user.family}"/>
                            </td>

                            <td>
                                <input name="email" path="email"
                                       type="text" value="${user.email}"/>
                            </td>
                            <td>
                                <input name="role" path="role"
                                       type="text" value="${user.role}"/>
                            </td>
                            <td>
                                <input name="status" path="status"
                                       value="${user.isEnabled()}"/>
                            </td>
                            <td>
                                <button class="btn" value="ACTIVE">Confirm AND Update</button>
                            </td>
                        </tr>

                    </form:form>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:otherwise>
</c:choose>

</body>
</html>
