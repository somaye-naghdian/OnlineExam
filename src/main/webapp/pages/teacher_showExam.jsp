<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/showExamCss.css" />">
</head>
<body>

<div class="navbar" align="left">
    <button onclick="location.href='teacherDashboard';">Teacher Dashboard</button>
</div>

<h3 align="center">Exam List</h3>
<div>


    <table id="table">
        <tr>
            <th>Title</th>
            <th>Description</th>
            <th>Start</th>
            <th>End</th>
            <th>Time</th>

        </tr>
        <c:forEach items="${examsOfCourse}" var="exam">
            <tr>
                <form:form modelAttribute="exam" action="/editExam" method="get">
                    <input type="hidden" id="user" name="user" value="${user.id}"/>
<%--                  //  ${user.id}--%>
                    <form:input path="id" name="id" id="id" value="${exam.id}" type="hidden"/>

                    <td><form:input path="title" id="title" class="examTitle" name="title" value="${exam.title}"
                                    cssClass="title"/></td>

                    <td><form:input path="description" name="description" value="${exam.description}"/></td>

                    <td><form:input path="startDate" name="startDate" value="${exam.startDate}"/></td>

                    <td><form:input path="endDate" name="endDate" value="${exam.endDate}"/></td>

                    <td><form:input path="time" name="time" value=" ${exam.time}"/></td>

                    <td><input type="submit" class="button" value="edit"></td>
                    <td><input type="submit" class="button" value="delete" formaction="/deleteExam"></td>
                    <td><input type="submit" class="button" value="stop" formaction="/stopExam" > </td>

                    <td>
                        <button type="submit" value="addQuestion" formaction="/addQuestionToExam">Add Question</button>
                    </td>
                    </tr>
                </form:form>

<%--            //onclick="stopExam()"--%>
        </c:forEach>
    </table>
</div>


</body>
<%--<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"--%>
<%--        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"--%>
<%--        crossorigin="anonymous"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"--%>
<%--        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"--%>
<%--        crossorigin="anonymous"></script>--%>
<%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"--%>
<%--        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"--%>
<%--        crossorigin="anonymous"></script>--%>
<%--<script>--%>
<%--    // function stopExam() {--%>
<%--    //     var stopConfirm = confirm("are you sure to stop the exam ?");--%>
<%--    //     var title = document.getElementById("title").value;--%>
<%--    //     var teacher = document.getElementById("teacher").value;--%>
<%--    //     if (stopConfirm === true) {--%>
<%--    //         var xhttp = new XMLHttpRequest();--%>
<%--    //         xhttp.onreadystatechange = function () {--%>
<%--    //             if (this.readyState == 4 && this.status == 200) {--%>
<%--    //                 alert(this.responseText);--%>
<%--    //             }--%>
<%--    //         };--%>
<%--    //         xhttp.open("PUT", "http://localhost:8080/stopExam/" + title + "/" + teacher, true);--%>
<%--    //         xhttp.send();--%>
<%--    //     }--%>
<%--    // }//--%>



</html>
