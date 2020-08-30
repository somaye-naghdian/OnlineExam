<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<html>
<head>
    <script language="JavaScript" type="text/javascript" src="/js/jquery-1.2.6.min.js"></script>
    <script language="JavaScript" type="text/javascript" src="/js/jquery-ui-personalized-1.5.2.packed.js"></script>
    <script language="JavaScript" type="text/javascript" src="/js/sprinkle.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/courseCss.css" />">

    <title>Add User To Course</title>
</head>
<body>
<div align="right" style=" position: absolute; right: 20px;">
    <button onclick="location.href='/admin';">back</button>
</div>

<div class="container" align="center">
    <label path="email" style="color: #dff0d8">user:</label>
    <input path="email" name="email" id="email" class="form-control"
           required="required"/>

    <label path="courseList" style="color: #dff0d8">course:</label>
    <select path="courseList" id="courseTitle" required="required" cssClass="dropdown">
        <option value="">None</option>
        <c:forEach var="course" items="${allCourse}">
            <option value="${course.courseTitle}"/>
            ${course.courseTitle}
        </c:forEach>
    </select>
</div>
<br>
<div class="button" align="center">
    <button name="add" class="btn" value="Add User" onclick="addUser()">Add User</button>

    <button name="delete" class="btn" value="Delete User" onclick="deleteUser()">Delete User</button>

    <button id="allStudents" class="btn" value="allStudents " onclick="deleteUser()"
            onClick="removeRequired(this.form)"> ALL Students Of Course
    </button>

</div>
<br>

<ul id="set" align="center" style="color: darkslategrey"></ul>

<p id="demo" align="center" style="color: darkslategrey"></p>

</body>
<script type="text/javascript">

    function addUser() {
        let request = new XMLHttpRequest();
        let courseTitle = document.getElementById("courseTitle").value;
        let email = document.getElementById("email").value;
        request.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                var data = (this.responseText);
                show(data);
                console.log(this.responseText);
            }
        };
        request.open("PUT", "http://localhost:8080/addUserToCourseRest/" + courseTitle + "/" + email, true);
        request.send();
    }

    function deleteUser() {
        let request = new XMLHttpRequest();
        let courseTitle = document.getElementById("courseTitle").value;
        let email = document.getElementById("email").value;
        request.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                var data = (this.responseText);
                show(data);
                console.log(this.responseText);
            }
        };
        request.open("DELETE", "http://localhost:8080/deleteUserFromCourse/" + courseTitle + "/" + email, true);
        request.send();
    }

    function showCourseStudents() {
        let request = new XMLHttpRequest();
        let courseTitle = document.getElementById("courseTitle").value;
        request.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                var data = JSON.parse(this.responseText);
                showALLStudent(data);
                console.log(this.responseText);
            }
        };
        request.open("GET", "http://localhost:8080/showAllStudentOfCourse/" + courseTitle, true);
        request.send();
    }


    function show(data) {
        document.getElementById("demo").innerHTML = data;
    }

    function showALLStudent(data) {
        var set = '';
        data.map(value =>
            set += '<li id=' + value.id + '> ' + value.name +
                '<br> ' + value.family + ' <br>' + value.email + '<br> ' + value.role + '</li>'
        );
        document.getElementById("set").innerHTML = set;
    }


</script>

</html>
