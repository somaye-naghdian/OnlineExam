<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/teacherCss.css" />">
    <title>Add Question</title>
</head>
<body>

<h4 style="color: midnightblue; text-decoration: underline; ">Add Question To exam From Question Bank</h4>
<form:form action="/getFromQuestionBank"  method="get">
    <input name="examId" value="${exam.id}" type="hidden"/>
    <input type="submit" class=".btn" value="FromQuestionBank"/>
</form:form>
<br><br>

<h4 style="color: midnightblue; text-decoration: underline;">ADD New Question</h4>
<div>

    <form:form action="/newDescriptiveQuestion" modelAttribute="question" method="get">
        <input name="examId" value="${exam.id}" type="hidden"/>
        <form:label path="title" >Title:</form:label>
        <form:input path="title" id="title" name="title" placeholder="title"/><br>
        <form:label path="text">Text:</form:label>
        <form:textarea path="text" id="text" name="text" placeholder="Question Text"/><br>
        <p>" This score is considered for this exam "</p>
        <input id="score" name="score" placeholder="score"/>
        <p>Click on moreOption Button for multi choice Questions </p>
        <div id="option">
            <input type="button" id="moreOption" class=".btn"
                   value="more option" onclick="add()"/>
            <input type="hidden" name="counter" id="counter" value="0">
        </div>
        <label >Correct Answer:</label>
        <input id="correctAnswer" name="correctAnswer" placeholder="correctAnswer"/><br>
        <div>
            <p>Add question to question bank ? </p>
            <label for="yes">YES</label>
            <input type="radio" id="yes" name="status" value="YES">
            <label for="no">NO</label>
            <input type="radio" id="no" name="status" value="NO">
        </div>

        <form:button type="submit" value="save">save Descriptive Question</form:button>
        <form:button type="submit" formaction="/newMultipleChoiceQuestion" >save Multiple Question</form:button>
    </form:form>
</div>

</body>
<script>
    function add() {
        var newOptionInput = document.createElement('div');
        newOptionInput.innerHTML = "<input id='newOption' name='answer'>";
        document.getElementById("option").appendChild(newOptionInput);
    }
</script>
</html>
