<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/courseCss.css" />">

    <title>Add Course</title>
</head>
<body>

<div class="header">
    <h2 id="h02">ADD New COURSE</h2>
</div>

<div align="right" style="float: right;"  >
    <button onclick="location.href='/admin';">back</button>
</div>

<div class="container">

    <form:form action="addCourseProcess" modelAttribute="course" method="get" >

        <form:label path="courseTitle">Course Title :</form:label>
        <form:input path="courseTitle" name="courseTitle" placeholder="courseTitle" class="form-control"
                    required="required" /><br><br><br>

        <form:label path="classification">classification:</form:label>
        <form:select path="classification" required="required" cssClass="dropdown">
            <option value="">None</option>
            <c:forEach items="${allClassification}" var="classification">
                <option value="${classification.classificationTitle}">${classification.classificationTitle}</option>
            </c:forEach>
        </form:select>

    <br>

    <button name="add" class="btn">Save Course</button>
    <br><br>

    </form:form>

</div>
<div>
    <form action="/" method="get">
        <button type="submit" class="btn">
            Home
        </button>
    </form>
</div>


</body>
</html>