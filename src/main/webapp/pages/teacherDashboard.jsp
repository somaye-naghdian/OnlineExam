<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <title>Professor Page</title>
</head>
<body>
<h1>Professor Page</h1>

<div class="course">
    <h3>Course</h3>
    <form:form action="getCoursePage"  method="get">
        <input type="hidden" value="${user.email}" name="user">
        <button type="submit" value="course"/>course
<%--    <button  onclick="location.href='/getCoursePage/'+${modelUser.email}">Course List</button>--%>
    </form:form>
    <div id="courseList">
    </div>

</div>

<div>
<table>
<c:forEach items="${courseList}" var="course">
    <tr>
    <td>${course.courseTitle}</td>

    </tr>
</c:forEach>
    </table>
</div>
</form>

<div>
    <form action="/" method="get">
        <button style="margin:5px;color: midnightblue; cursor: pointer; background-color: powderblue;" type="submit"
                value="home" class="btn">Home
        </button>
    </form>
</div>

</body>
<!--
<script>

    var btn = document.getElementById("btn");
<%--    var email=${modelUser.email};--%>
    btn.addEventListener('click', function () {
        var request = new XMLHttpRequest();
        request.open("GET", "http://localhost:8080/teacherCourseList/" + email);
        request.onload = function () {
            if (this.readyState === 4 && this.status === 200) {
                var data = JSON.parse(this.responseText);
                renderHtml(data);
            } else {
                console.log("we connected to server,but it returned an error ");
            }
        };
        request.onerror = function () {
            console.log("connection error");
        };
        request.send();
    });


    function renderHtml(data) {
        var list = "";
        for (i = 0; i < data.length; i++) {
           list +='<p>'+data[i].courseTitle+'</p>';
        }
        document.getElementById("courseList").innerHTML = list;
    }
</script>
-->
</html>
