
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Exams Of ${courseTitle}</title>
    <style>
        table {
            width: 60%;
            border: 1px solid yellow;
            border-collapse: collapse;
            float: inside;
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
                <input type="hidden" id="examTitle" value="${exam.title}"/>
                <input type="hidden" id="teacher" value="${exam.teacher.id}"/>
                <td><input id="title" name="title" value="${exam.title}"></td>
                <td><input id="description" name="description" value="${exam.description}"></td>
                <td><input id="start" name="start" value="${exam.startDate}"></td>
                <td><input id="end" name="end" value="${exam.endDate}"></td>
                <td><input id="time" name="time" value=" ${exam.time}"></td>
                <td> ${exam.teacher.name}</td>
                <td>${exam.course.courseTitle}</td>
                <td><input type="submit" value="StopExam" onclick="stopExam()"></td>
                <td><input type="submit" value="editExam" onclick="editExam()"></td>

            </tr>

        </c:forEach>
    </table>
</div>
</body>
<script>
    function stopExam() {
        var stopConfirm = confirm("are you sure to stop the exam ?");
        var title = document.getElementById("examTitle").value;
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

    function editExam() {
        var teacher = document.getElementById("teacher").value;
        var xhttp = new XMLHttpRequest();
        var editConfirm = confirm("are you sure to edit the exam ?");
        var exam = {
            "title": document.getElementById("examTitle").value,
            "start": document.getElementById("start").value,
            "end": document.getElementById("end").value,
            "time": document.getElementById("time").value,
            "description": document.getElementById("description").value
        };
        xhttp.addEventListener('click', function () {
            if (this.readyState == 4 && this.status == 200) {
                var data = JSON.parse(this.responseText);
                console.log('data');
            } else {
                console.log('done');
            }
        })
        xhttp.open("PUT", "http://localhost:8080/editExam/" + teacher, true);
       xhttp.setRequestHeader('content-type',"text/html; charset=UTF-8")
        xhttp.send(JSON.stringify(exam));
    }
</script>

</html>
