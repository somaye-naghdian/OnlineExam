<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/teacherCss.css" />">
    <title>Exams Of ${courseTitle}</title>
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
<div>

    <table id="table">
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Description</th>
            <th>Start</th>
            <th>End</th>
            <th>Time</th>
            <th>Teacher</th>
            <th>Course</th>

        </tr>
        <c:forEach items="${examsOfCourse}" var="exam">
            <tr>
                <form:form modelAttribute="exam" action="/editExam" method="get">
                    <form:input path="teacher" type="hidden" id="teacher" name="creator" value="${exam.teacher.email}"/>
                   <td> <form:input path="id"  name="id" id="id" value="${exam.id}"/></td>
                    <td><form:input path="title" id="title" class="examTitle" name="title" value="${exam.title}"/></td>
                    <td><form:input path="description" name="description" value="${exam.description}"/></td>
                    <td><form:input path="startDate" name="startDate" value="${exam.startDate}"/></td>
                    <td><form:input path="endDate" name="endDate" value="${exam.endDate}"/></td>
                    <td><form:input path="time" name="time" value=" ${exam.time}"/></td>
                    <td> ${exam.teacher.name}</td>
                    <td>${exam.course.courseTitle}</td><br>
                    <td><input type="submit" value="editExam"></td>
                    <td><input type="submit" value="deleteExam" formaction="/deleteExam"></td>
                    <td>
                        <button type="submit" value="addQuestion" formaction="/addQuestionToExam">Add Question</button>
                    </td>
                </form:form>
                <td><input type="submit" value="StopExam" onclick="stopExam()"></td>
            </tr>
        </c:forEach>
    </table>
    <input type="hidden" name="teacher" value="${user.email}">
</div>


</body>
<script>
    function stopExam() {
        var stopConfirm = confirm("are you sure to stop the exam ?");
        var title = document.getElementById("title").value;
        var teacher = document.getElementById("teacher").value;
        if (stopConfirm === true) {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    alert(this.responseText);
                }
            };
            xhttp.open("PUT", "http://localhost:8080/stopExam/" + title + "/" + teacher, true);
            xhttp.send();
        }
    }

    // function editExam() {
    //     var teacher = document.getElementById("teacher").value;
    //     var editConfirm = confirm("are you sure to edit the exam ?");
    //     var examDto = {
    //         "title": document.getElementById("examTitle").value,
    //         "start": document.getElementById("start").value,
    //         "end": document.getElementById("end").value,
    //         "time": document.getElementById("time").value,
    //         "description": document.getElementById("description").value,
    //         "examId":document.getElementById("examId").value
    //     };
    //     var xhttp = new XMLHttpRequest();
    //     xhttp.onreadystatechange = function () {
    //         if (this.readyState == 4 && this.status == 200) {
    //             var data = JSON.parse(this.responseText);
    //             console.log(data);
    //         } else {
    //             console.log('done');
    //         }
    //     }
    //     xhttp.open("PUT", "http://localhost:8080/editExam/" + teacher, true);
    //     xhttp.send(JSON.stringify(examDto));
    // }


</script>

</html>
