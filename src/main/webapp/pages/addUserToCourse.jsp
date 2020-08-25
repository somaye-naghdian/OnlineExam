<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<%--    <script src="/AjaxWithSpringMVC2Annotations/js/jquery.js"></script>--%>

    <title>Add User To Course</title>
</head>
<body>

<form:form modelAttribute="user" action="addUserToCourseProcess" method="post">


    <label path="userId">user:</label>
    <input path="userId" name="userId" id="userId" class="form-control"
                required="required"/>

    <label path="courseList">course:</label>
    <select path="courseList" id="courseTitle" required="required" cssClass="dropdown">
        <option value="">None</option>
        <c:forEach var="course" items="${allCourse}">
            <option  value="${course.courseTitle}"/>${course.courseTitle}
        </c:forEach>
    </select>

    <button name="add" class="btn" value="Add User" >Add User</button>
<</form:form>

<p id="demo"></p>
</body>
<script type="text/javascript">

    function loadDoc(){
        let request = new XMLHttpRequest();
        let courseTitle= document.getElementById("courseTitle").value;
        let userId= document.getElementById("userId").value;
        request.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                show(JSON.parse(this.responseText));
                console.log(this.responseText);
            }
        };
        request.open("GET", "addUserToCourseRest/"+courseTitle+"/"+userId+"/", true);
        request.send();
    }
    function  show(xml) {

        document.getElementById("demo").innerHTML = xml;
    }

</script>

</html>
