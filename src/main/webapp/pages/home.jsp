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
<div>
    <h2>Online Exam</h2>
</div>

<div style="display: block" class="container"  >
<form action="register" method="get">

    <button  type="submit" name="submitButton" value="Register" >REGISTER</button>
<br><br>
    <button  type="submit"  value="login" onclick="document.forms[0].action = 'login';">LOGIN</button>

</form>
</div>
<%--</div>--%>
<%--<div class="container" align="left">--%>
<%--    <br><br>--%>
<%--<form action="login" method="get">--%>
<%-- --%>
<%--</form>--%>
<%--</div>--%>
<%--</div>--%>
</body>
</html>