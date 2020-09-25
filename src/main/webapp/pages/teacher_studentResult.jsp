<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher Student Result</title>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/teacher_studentResult.css" />">

</head>
<body>
<p id="p1"> Number of students participating in the exam : ${size} </p>
<table id="table1">
    <tbody>
    <th>name</th>
    <th>family</th>
    <th>score</th>
    <th>correct descriptive</th>
    <c:forEach items="${studentScores}" var="student">
        <form:form action="/getDescriptiveAnswer"  method="get">
        <input type="hidden" name="studentId" value="${student.key.id}">
        <input type="hidden" name="examId" value="${exam.id}">
       <tr>
        <td>${student.key.name}</td>
        <td>${student.key.family}</td>
        <td>${student.value}</td>
           <td><button type="submit" class="button">correct</button></td>
       </tr>
        </form:form>
    </c:forEach>
    </tbody>
</table>

<div>
    <form action="/logout" method="get">
        <button type="submit"
                value="logout" class="button">home
        </button>
    </form>
</div>
</body>
</html>
