<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/addQuestionByTeacher.css" />">
    <title>Add Question</title>
</head>
<body>
<div class="navbar" align="right" >
    <button onclick="location.href='teacherDashboard';">Teacher Dashboard</button>
</div>

<%--<h4 style="color: midnightblue; text-decoration: underline; ">Add Question To exam From Question Bank</h4>--%>

<div class="container" align="center">
<form:form action="/getFromQuestionBank"  method="get">
    <input name="examId" value="${exam.id}" type="hidden"/>
    <input type="hidden" name="teacher" value="${user.id}"/>
    <input type="submit" class="button" value="FromQuestionBank"/><br><br>
    <input type="submit" class="button" value="DescriptiveQuestion" formaction="/descriptive"><br><br>
    <input type="submit" class="button" value="MultipleChoiceQuestion" formaction="/multipleChoice"/>

</form:form>
</div>

</body>

</html>
