<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<html>
<head>
   <title>Add User To Course</title>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/courseCss.css" />">
</head>
<body>
<div align="right" style=" position: absolute; right: 20px;">
    <button onclick="location.href='/admin';">back</button>
</div>

<table id="table">
    <h4>Student List</h4>
    <tr>
        <th>name</th>
        <th>family</th>
        <th>email</th>
        <th></th>
    </tr>
    <c:forEach items="${students}" var="student">
    <form:form action="addStudentToCourse" modelAttribute="user" method="get">

    <tr>
        <td>${student.name}</td>
        <td>${student.family}</td>
        <td><input type="hidden" name="email" value="${student.email}"> ${student.email}</td>

        <td><select name="course" required="required" cssClass="dropdown">
            <c:forEach var="course" items="${allCourse}">
                <option  value="${course.courseTitle}"/>
                ${course.courseTitle}
            </c:forEach>
        </select></td>
        <td><input type="submit" class="button" value="add"></td>
        <td><input type="submit" class="button" value="delete" formaction="/deleteStudentFromCourse"></td>
    </tr>
    </form:form>
    </c:forEach>
    <br><br>


    <table id="table2">
        <h4>Teacher List</h4>
        <tr>
            <th>name</th>
            <th>family</th>
            <th>email</th>
            <th></th>
        </tr>
        <c:forEach items="${teachers}" var="teacher">
        <form:form action="addTeacherToCourse" modelAttribute="user" method="get">

        <tr>
            <td>${teacher.name}</td>

            <td>${teacher.family}</td>
            <td><input type="hidden" name="email" value="${teacher.email}">${teacher.email}</td>

            <td><select name="course" required="required" cssClass="dropdown">
                <c:forEach var="course" items="${allCourse}">
                    <option value="${course.courseTitle}" required="required"/>
                    ${course.courseTitle}
                </c:forEach>
            </select></td>

            <td><input type="submit" class="button" value="add"></td>
            <td><input type="submit" class="button" value="delete" formaction="/deleteStudentFromCourse"></td>
        </tr>


        </form:form>
        </c:forEach>


</body>


<script type="text/javascript">

    // function addUser() {
    //     let request = new XMLHttpRequest();
    //     let courseTitle = document.getElementById("courseTitle").value;
    //     let email = document.getElementById("email").value;
    //     request.onreadystatechange = function () {
    //         if (this.readyState == 4 && this.status == 200) {
    //             var data = (this.responseText);
    //             show(data);
    //             console.log(this.responseText);
    //         }
    //     };
    //     request.open("PUT", "http://localhost:8080/addUserToCourseRest/" + courseTitle + "/" + email, true);
    //     request.send();
    // }
    //
    // function deleteUser() {
    //     let request = new XMLHttpRequest();
    //     let courseTitle = document.getElementById("courseTitle").value;
    //     let email = document.getElementById("email").value;
    //     request.onreadystatechange = function () {
    //         if (this.readyState == 4 && this.status == 200) {
    //             var data = (this.responseText);
    //             show(data);
    //             console.log(this.responseText);
    //         }
    //     };
    //     request.open("DELETE", "http://localhost:8080/deleteUserFromCourse/" + courseTitle + "/" + email, true);
    //     request.send();
    // }
    //
    // function showCourseStudents() {
    //     let request = new XMLHttpRequest();
    //     let courseTitle = document.getElementById("courseTitle").value;
    //     request.onreadystatechange = function () {
    //         if (this.readyState == 4 && this.status == 200) {
    //             var data = JSON.parse(this.responseText);
    //             showALLStudent(data);
    //             console.log(this.responseText);
    //         }
    //     };
    //     request.open("GET", "http://localhost:8080/showAllStudentOfCourse/" + courseTitle, true);
    //     request.send();
    // }
    //
    //
    // function show(data) {
    //     document.getElementById("demo").innerHTML = data;
    // }
    //
    // function showALLStudent(data) {
    //     var set = '';
    //     data.map(value =>
    //         set += '<li id=' + value.id + '> ' + value.name +
    //             '<br> ' + value.family + ' <br>' + value.email + '<br> ' + value.role + '</li>'
    //     );
    //     document.getElementById("set").innerHTML = set;
    // }


</script>

</html>
