<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/teacherCss.css" />">
    <title>Question Bank</title>
    <style>
        table {
            border: 1px solid yellow;
            border-collapse: collapse;
            float: inside;
            table-layout: fixed;
            width: 100%;
        }

        table, th, td {
            border: 1px solid yellow;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<h2 align="center" style="color: midnightblue">Question Bank</h2><br>
<table id="table">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Text</th>
        <th>Score</th>
        <th></th>
    </tr>
    <c:forEach var="question" items="${questionFromBank}">
    <form:form modelAttribute="question" action="/addFromBank" method="post">
    <tr>
        <td><form:input path="id" name="questionid" type="hidden" value="${question.id}"/>${question.id}</td>
        <td><form:input path="title" name="title" value="${question.title}" type="hidden"/>${question.title}</td>
        <td><form:input path="text" name="text" value="${question.text}" type="hidden"/> ${question.text}</td>
            <%--    <td><form:input path="classification" name="classification" value="${question.classification}" type="hidden" />${question.classification} </td>--%>
        <td><input name="score" placeholder="score"/></td>
        <input type="hidden" name="examId" value="${exam.id}"/>
        <td><form:button>Add to exam </form:button></td>
    </tr>
    </form:form>
    </c:forEach>
</body>
</html>
