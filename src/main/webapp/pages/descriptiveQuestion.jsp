<%--
  Created by IntelliJ IDEA.
  User: Ehsan
  Date: 9/7/2020
  Time: 11:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>descriptive question </title>
</head>
<body>
<h4>Descriptive Question</h4>
<div>

    <form:form action="/newDescriptiveQuestion" modelAttribute="question" method="get">
        <input name="examId" value="${exam.id}" type="hidden"/>
        <form:input path="title" id="title" name="title" placeholder="title"/>
        <form:input path="text" id="text" name="text" placeholder="Question Text"/>
        <form:input path="score" id="score" name="score" placeholder="score"/>

        <div>
            <p>Add question to question bank ? </p>
            <input type="radio" id="yes" name="status" value="YES" >
            <input type="radio" id="no" name="status" value="NO" >

        </div>

        <button type="submit" value="save">save</button>
        <%--        <button type="submit" value="addToBank" onclick="confirmAdd()" formaction="/addQuestionToBank">add To Bank</button>--%>
        <br><br>
    </form:form>
</div>
</body>
</html>
