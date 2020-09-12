<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/showAllUserCss.css" />">
<style>

</style>
    <title>Show All Course</title>
</head>
<body>

<div align="right" style="float: right;"  >
    <button class="btn" onclick="location.href='/admin';">back</button>
</div>
<div>
    <form action="/" method="get">
        <button   type="submit" value="home" class="btn" >Home</button>
    </form>
</div>
<h4 >Course List</h4>
<table class="center" id="t01">

    <tr>
        <th>Title</th>
        <th>Classification</th>

    </tr>

    <c:forEach items="${allCourse}" var="course" >
    <tr>
        <td>${course.courseTitle}</td>
        <td>${course.classification.classificationTitle}</td>
    </tr>
    </c:forEach>
</table>



</body>
</html>
