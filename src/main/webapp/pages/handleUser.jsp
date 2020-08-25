<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/confirmCss.css" />">
    <title>pending users</title>

</head>
<body>
<c:choose>

    <c:when test="${empty waitingUsers}">

        <p>No Waiting User To Confirm</p>

    </c:when>

    <c:otherwise>
        <div class="resultTable">
            <table class="table table-hover table-striped table-bordered">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>name</th>
                    <th>family</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${waitingUsers}" var="user">
                    <form:form modelAttribute="user" action="/confirmUser" method="get">

                        <tr>
                            <td>
                                <input cssStyle="width: 2vw" name="id" path="id"
                                       readonly="true" value="${user.id}"/>
                            </td>

                            <td>
                                <input path="name" name="name" type="text"
                                       value="${user.name}" cssStyle="width: 8vw"/>
                            </td>
                            <td>
                                <input cssStyle="width: 8vw" name="family" path="family"
                                       type="text" value="${user.family}"/>
                            </td>

                            <td>
                                <input cssStyle="width: 20vw" name="email" path="email"
                                       type="text" value="${user.email}"/>
                            </td>
                            <td>
                                <input cssStyle="width: 5vw" name="role" path="role"
                                       type="text" value="${user.role}"/>
                            </td>

                            <c:when test="${fn:containsIgnoreCase(String, 'ACCEPTED')}">
                                <td><button class="btn" value="ACTIVE">Confirm</button></td>
                            </c:when>
                            <c:otherwise>
                            <td><button class="btn" value="ACTIVE">Confirm</button></td>
                            </c:otherwise>
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
