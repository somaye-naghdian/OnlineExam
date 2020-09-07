<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/teacherCss.css" />">
    <title>create Exam</title>
</head>
<body>
<h5> add new Exam to ${course.courseTitle}</h5> <br>
<li id="course" name="course" value=" ${course}"></li>
${course.courseTitle}
<input type="hidden" name="user" id="userId" value="${user.id}">
<input name="title" id="title" placeholder="exam title" required>
<input name="description" id="description" placeholder="description" required>
<input name="startDate" id="startDate" placeholder="start date" required>
<input name="endDate" id="endDate" placeholder="end date" required>
<input type="number" name="time" id="time" placeholder="exam duration" required>

<button onclick="addExam()">save exam</button>



</body>


<script>
    var user = document.getElementById("userId").value;

    function addExam() {
        var exam = {
            "title": document.getElementById("title").value,
            "description": document.getElementById("description").value,
            "startDate": document.getElementById("startDate").value,
            "endDate": document.getElementById("endDate").value,
            "time": document.getElementById("time").value
        };
        $.ajax({
            type: "POST",
            contentType: 'application/json; ',
            dataType: 'json',
            url: 'http://localhost:8080/createNewExam/${course.courseTitle}/${user.id}',
            data: JSON.stringify(exam),
            success: function (result) {
                console.log(result);
            },
            error: function (e) {
                console.log("done");
            }

        });

    }

</script>

</html>
