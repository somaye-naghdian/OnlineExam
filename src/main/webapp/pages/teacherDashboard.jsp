<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/teacherCss.css" />">

    <title>Professor Page</title>
</head>
<body>
<h1 align="center">Teacher Page</h1><br>

<div class="course">
    <h3 style=" text-decoration: underline overline;">Course</h3><br>
    <form:form action="getCoursePage"  method="get">
        <input type="hidden" id="email" name="user" value="${user.email}" name="user">
    <button id="btn" >Course List</button>
    </form:form>

    <div id="courseList">
    </div>

</div>



</form><br>




<div class="d-flex align-items-end">
    <form action="/" method="get">
        <button style="margin:5px;color: midnightblue; cursor: pointer; background-color: powderblue;" type="submit"
                value="home" class="btn">Home
        </button>
    </form>
</div>

</body>

<script>
    // var email=document.getElementById("email").value;
    // fetch('http://localhost:8080/teacherCourseList/',{
    //     method:'GET'
    // })
    // .then(response => response.json())
    // .then(data => {
    //     console.log('success:',data);
    //     renderHtml(data);
    // })
    // .catch((error) => {
    //     console.error('Error:',error);
    // });
    //
    // var btn = document.getElementById("btn");
    // btn.addEventListener('click', function () {
    //     var request = new XMLHttpRequest();
    //     request.open("GET", "http://localhost:8080/teacherCourseList/" + email);
    //     // headers.add("Content-Type", "application/json");
    //     contentType: 'application/json; charset=utf-8';
    //     request.onload = function () {
    //         if (this.readyState === 4 && this.status === 200) {
    //             var data = JSON.parse(this.responseText);
    //             renderHtml(data);
    //         } else {
    //             console.log("we connected to server,but it returned an error ");
    //         }
    //     };
    //     request.onerror = function () {
    //         console.log("connection error");
    //     };
    //     request.send();
    // });

    //
    // function renderHtml(data) {
    //     var list = "";
    //     for (i = 0; i < data.length; i++) {
    //        list +='<p>'+ data[i] +'</p>';
    //     }
    //     document.getElementById("courseList").innerHTML = list;
    // }
</script>

</html>
