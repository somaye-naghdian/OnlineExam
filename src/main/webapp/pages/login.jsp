<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/loginCss.css" />">

    <title>Login</title>
</head>
<body>
<div id="homeButton">
    <form action="/" method="get" >
        <button type="submit" value="home" class="button" id="b1">home
        </button>
    </form>
</div>

<h2 >Login Form</h2>
<div id="holder">
    <div class="container">
        <form:form action="loginProcess" modelAttribute="user" method="get">

            <form:label path="email">email</form:label>
            <input type="text" name="email" placeholder="email" class="form-control"
                   pattern="^[\w!#$%&'*+/=?`{|}~^-]+(?:\.[\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}$"
                   title="simple@example.com" required="required"><br><br>

            <form:label path="password">password</form:label>
            <input type="password" name="password" placeholder="password" class="form-control"
                   pattern="(?=.*\d)(?=.*[a-z]).{8,}"
                   title="Must contain at least one number, one lowercase letter, at least 8 at most 16 length"
                   required="required"><br><br>

            <input type="submit" name="loginButton" value="Sign in" id="s01">


        </form:form>
    </div>
</div>

<p>${message}</p>

</body>
</html>
