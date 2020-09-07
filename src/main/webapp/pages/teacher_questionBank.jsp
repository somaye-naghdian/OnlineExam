<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <link rel="stylesheet" href="<c:url value="/resources/theme/css/teacherCss.css" />">
    <title>Question Bank</title>
</head>
<body>

<c:forEach var="question" items="${questionFromBank}">
    <td>${question.title}</td>
    <td>${question.text}</td>
</c:forEach>
</body>
</html>
