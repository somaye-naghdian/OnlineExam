<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/addExamCss.css" />">
    <title>create Exam</title>
</head>
<body>
<div class="navbar" align="right">
    <input type="button" value="back" class="button"
           onclick="document.forms[0].action = 'teacher_Courses.jsp'; "/>
    <%--    <button onclick="location.href='teacherDashboard';">Teacher Dashboard</button>--%>
</div>


<div class="container">
    <form:form action="/addNewExam" modelAttribute="exam" method="post">

        <div class="input-group" style="display: block">

            <div class="form-group ">
            <form:label path="title" for="title">Title :</form:label>
            <div class="col-sm-10">
                <form:input path="title" name="title" id="title" pattern="[a-zA-Z].{,20}"
                            title="title must between 2 and 20 character" placeholder="exam title" required="required"/><br>
            </div>
        </div>
        <div class="form-group ">
            <label for="description">Description :</label>
            <div class="col-sm-10">
                <form:textarea path="description" name="description" id="description"
                               placeholder="description" required="required"/><br>
            </div>
        </div>
        <div class="form-group ">
            <label for="startDate">Start Date :</label>
            <div class="col-sm-10">
            <form:input path="startDate" name="startDate" id="startDate"
                        type="date"  placeholder="start date" required="required"/><br>
        </div>
                <%--                        pattern="^\d{4}\-(0?[1-9]|1[012])\-(0?[1-9]|[12][0-9]|3[01])$"--%>

        </div>
        <div class="form-group ">
            <label for="endDate">End Date :</label>
            <div class="col-sm-10">
            <form:input  path="endDate" type="Date" name="endDate" id="endDate"
                placeholder="end date" required="required"
                        oninvalid="checkTime()"/><br>
            </div>
        </div>
        <div class="form-group ">
            <lable>Time :</lable>
            <div class="col-sm-10">
            <form:input path="time" type="number" name="time" id="time" pattern="\b(0*(?:[1-9][0-9]?|100))\b"
                        title="time must less than 100" placeholder="exam duration" required="required"/>
        </div>
        </div>
<%--            oninvalid="checkTime()"--%>
            <br>
            <form:button  >save exam</form:button>
            </form:form>

            <input type="hidden" name="user" id="userId" value="${user.id}">
            <input type="hidden" name="courseTitle" id="course" value="${course.courseTitle}">

        </div>

    </div>
<p align="center">${message}</p>
</body>


<script>
    function getDate(str)
    {
        var dateParts = /^(\d\d(?:\d\d)?)-(\d\d?)-(\d\d?)$/.exec(str);
        if (dateParts === null)
        {
            return null;
        }
        var year = parseInt(dateParts[1]);
        if (year < 100)
        {
            year += 2000;
        }
        var month = parseInt(dateParts[2]) - 1;
        var day = parseInt(dateParts[3]);
        var result = new Date(year, month, day);
        return year === result.getFullYear()
        && month === result.getMonth()
        && day === result.getDate() ? result : null;
    }

    function checkTime() {
        var startDate = document.getElementById("startDate").value;
        var endDate = document.getElementById("endDate").value;
        var regExp = /(^\d{4}\-(0?[1-9]|1[012])\-(0?[1-9]|[12][0-9]|3[01])$)/;
        if (parseInt(endDate.replace(regExp, "$3$2$1")) < parseInt(startDate.replace(regExp, "$3$2$1"))) {
            alert("end date must after start date");
        }
    }


    // var user = document.getElementById("userId").value;

    <%--function addExam() {--%>
    <%--    var exam = {--%>
    <%--        "title": document.getElementById("title").value,--%>
    <%--        "description": document.getElementById("description").value,--%>
    <%--        "startDate": document.getElementById("startDate").value,--%>
    <%--        "endDate": document.getElementById("endDate").value,--%>
    <%--        "time": document.getElementById("time").value--%>
    <%--    };--%>
    <%--    $.ajax({--%>
    <%--        type: "POST",--%>
    <%--        contentType: 'application/json; ',--%>
    <%--        dataType: 'json',--%>
    <%--        url: 'http://localhost:8080/createNewExam/${course.courseTitle}/${user.id}',--%>
    <%--        data: JSON.stringify(exam),--%>
    <%--        success: function (result) {--%>
    <%--            console.log(result);--%>
    <%--        },--%>
    <%--        error: function (e) {--%>
    <%--            console.log("done");--%>
    <%--        }--%>

    <%--    });--%>

    }
    function removeRequired(form) {
        $.each(form, function (key, value) {
            if (value.hasAttribute("required")) {
                value.removeAttribute("required");
            }
        });
    }

</script>

</html>
