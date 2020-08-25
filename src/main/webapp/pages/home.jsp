<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/homeStyle.css" />">

    <title>Welcome</title>
</head>
<body>
<h1>Online Test </h1><br><br>
<div class="container">
<form action="register" method="get">
    <button class="btn" type="submit" name="submitButton" value="Register" style="margin:5px;color: midnightblue; cursor: pointer; background-color: powderblue;">
        REGISTER
    </button>
</form>

    <br><br>
<form action="login" method="get">
    <button style="margin:5px;color: midnightblue; cursor: pointer; background-color: powderblue;" type="submit"
            value="login" class="btn">LOGIN
    </button>
</form>
</div>
</body>
</html>