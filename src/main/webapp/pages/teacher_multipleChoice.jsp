<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/question.css" />">
    <title>Multiple choice Question</title>
</head>
<body>
<div class="container">
    <form:form action="/newMultipleChoiceQuestion" modelAttribute="question" method="get">

        <input name="examId" value="${exam.id}" type="hidden"/>

        <form:label path="title">Title:</form:label>
        <form:input path="title" id="title" name="title" placeholder="title" required="required"/>
        <br>
        <form:label path="text">Text:</form:label>
        <form:textarea path="text" id="text" name="text" placeholder="Question Text"
                       rows="5" cols="60" required="required"/>

        <label>score:   " This score is considered for this exam "</label>
        <input id="score" name="score" pattern="\d" placeholder="score" required="required"/>
<br>
      Click on moreOption Button for multi choice Questions
        <div id="option">
            <input type="button" id="moreOption" class="button"
                   name="answer" value="more option" onclick="add()"/>
        </div>

        <label>Correct Answer:</label>
        <input id="correctAnswer" name="correctAnswer" placeholder="correctAnswer" required="required"/>

        <div>
           Add question to question bank ?
            <label for="yes">YES</label>
            <input type="radio" id="yes" name="status" value="YES" required>
            <label for="no">NO</label>
            <input type="radio" id="no" name="status" value="NO" required>
        </div>

        <form:button type="submit">save</form:button>
    </form:form>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
</body>
<script>
    function add() {
        var newOptionInput = document.createElement('div');
        newOptionInput.innerHTML = "<input id='newOption' name='answer'>";
        document.getElementById("option").appendChild(newOptionInput);
    }

    function test() {
        var radios = document.getElementsByTagName("input");
        var found = 1;
        for (var i = 0; i < radios.length; i++) {
            if (radios[i].checked) {
                found = 0;
                break;
            }
        }
        if(found == 1)
        {
            alert("Please Select Radio");
        }
    }
</script>
</html>
