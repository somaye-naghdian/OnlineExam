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

<h4 >Course List</h4>
<table class="center" id="t01">

    <tr>
        <th>Title</th>
        <th>Classification</th>

    </tr>

    <c:forEach items="${allCourse}" var="course" >
    <tr>
        <td>${course.courseTitle}</td>
        <td>${course.classification}</td>
    </tr>
    </c:forEach>
</table>

    <div>
        <form action="/" method="get">
            <button style="margin:5px;color: midnightblue; cursor: pointer; background-color: powderblue;" type="submit"
                    value="home" class="btn" >Home
            </button>
        </form>
    </div>

</body>
</html>
