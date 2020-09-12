<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>playExam</title>
</head>
<body>
<div class="container">
    <form:form action="nextQuestion" modelAttribute="question" method="post">
        <input type="hidden" name="examId" value="${exam.id}">
        <input type="hidden" name="user" value="${student.id}">

        ${question.id}
        ${question.text}
        <br>
        <c:choose>
            <c:when test="${question.type == 'descriptive' }">
                <label>answer box:</label><br>
                <textarea rows="5" cols="50" name="answer"></textarea>

            </c:when>
            <c:otherwise>
<%--                <c:when test="${question.type=='multipleChoice'}">--%>
                    <c:forEach items="${answers}" var="answer">
                        <li>
                            <input type="radio" name="answer" value="${answer.id}" id="answerId">
                                ${answer.content}
                        </li>

                    </c:forEach>
<%--                </c:when>--%>
            </c:otherwise>
        </c:choose>
        <input type="hidden" name="questionId" value="${question.id}">
        <input type="submit" class="button" name="nextQuestion" value="nextQuestion"/>
        <input type="submit" class="button" name="previousQuestion" value="previousQuestion"
               formaction="/previousQuestion">
    </form:form>
</div>
</body>
</html>
