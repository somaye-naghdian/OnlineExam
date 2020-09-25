<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>playExam</title>

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/startExam.css" />">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            if ($("#index").val() > 1) {
                $("#previousQuestion").show()
            }
            if ($("#index").val() == $("#listSize").val()) {
                $("#nextQuestion").hide();
                $("#finish").show();
            }

            function myTimer() {
                const startTime = Date.parse($("#startTime").val());
                var duration = Number.parseInt($("#examTime").val());
                var durationSec = duration * 60;
                var now = Date.now();
                var passedTime = Math.abs(now - startTime);
                var passedTimeSec = passedTime / 1000;
                var remainingTime = durationSec - passedTimeSec;
                var h = Math.floor(remainingTime / 3600);
                var m = Math.floor(remainingTime % 3600 / 60);
                var s = Math.floor(remainingTime % 3600 % 60);
                var hDisplay = h > 0 ? h + (h == 1 ? " hour, " : " hours, ") : "";
                var mDisplay = m > 0 ? m + (m == 1 ? " minute, " : " minutes, ") : "";
                var sDisplay = s > 0 ? s + (s == 1 ? " second" : " seconds") : "";
                $("#remainingTime").html(hDisplay+mDisplay+sDisplay);
                $("#hours").html(hDisplay);
                $("#minutes").html(mDisplay);
                $("#seconds").html(sDisplay);
              console.log(h);
              console.log(m);
              console.log(s);
                if (remainingTime == 0) {
                    $("#finish").click();
                }
            }
            setInterval(function () {
                myTimer()
            }, 1000)
        });


    </script>
</head>
<br>


<div id="hours"></div>
<div id="minutes"></div>
<div id="seconds"></div>

<div class="container">
    <form:form action="nextQuestion" modelAttribute="question" method="get">
        <h4 id="remainingTime"></h4>

        <input type="hidden" name="examTime" id="examTime" value="${examTime}">
        <input type="hidden" name="startTime" id="startTime" value="${startTime}">
        <input type="hidden" name="examId" value="${exam.id}">
        <input type="hidden" name="user" value="${user.id}">
        <input type="hidden" name="index" id="index" value="${index}">
        <input type="hidden" name="listSize" id="listSize" value="${listSize}">

        <input type="hidden" name="questionId" id="questionId" value="${question.id}">
        <%--        <input type="hidden" name="questionId"  value="${multiQuestion.id}">--%>
        <form:input path="title" type="hidden" name="title" value="${question.title}"/>
        <form:input path="text" type="hidden" name="text" value="${question.text}"/>
        <form:input path="type" name="type" type="hidden" value="${question.type}"/>
        ${index}- ${question.text}
        <br>
        <c:choose>

            <c:when test="${question.type =='descriptive'}">
                <textarea rows="5" cols="50" name="userAnswer" id="descriptive_answer"
                          placeholder="answer box"></textarea>
            </c:when>

            <c:otherwise>
                <div id="radios">
                    <c:forEach items="${question.answers}" var="answer">
                        <input type="radio" name="userAnswer" value="${answer.content}" id="choice_answer">
                        <label for="${answer.id}"> ${answer.content} </label><br>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>

        <button type="submit" class="button" name="previousQuestion"
                id="previousQuestion" formaction="/previousQuestion" hidden>previous
        </button>

        <button type="submit" class="button" name="nextQuestion" id="nextQuestion"
        >next
        </button>

        <button id="finish" type="submit" class="button" formaction="/finishExam" hidden>Finish
        </button>

    </form:form>


</div>

</body>
</html>
