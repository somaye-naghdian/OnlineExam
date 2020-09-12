<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/registerStyle.css" />">
    <title>Registration</title>
</head>
<body>
<div id="holder" class="container">
<div class="header">
    <h2>Registration Form</h2>
</div>
<div class=input-group">
    <form:form action="registration" modelAttribute="user" method="post" id="registrationForm" cssClass="content">

        <form:label path="name">name:</form:label>
        <input path="name" name="name" placeholder="name" type="text" class="form-control"
               required="required"/><br>

        <form:label path="family">family:</form:label>
        <input path="family" name="family" placeholder="family" class="form-control" required="required"/><br>


        <form:label path="email">email:</form:label>
        <input path="email" name="email" placeholder="email"
               pattern="^[\w!#$%&'*+/=?`{|}~^-]+(?:\.[\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}$"
               title="simple@example.com" class="form-control"
               required="required"/><br>

        <form:label path="password">password:</form:label>
        <input path="password" type="password" id="password" name="password" placeholder="password" class="form-control"
               pattern="(?=.*\d)(?=.*[a-z]).{8,}"
               title="Must contain at least one number, one lowercase letter, at least 8 at most 16 length"
               required="required"><br>

        <form:label path="password">confirm:</form:label>
        <input type="password" class="form-control" id="confirm_password"
               placeholder="confirm password" oninvalid="validatePassword()" required="required"/><br>

        <form:label path="role">Role:</form:label>
        <form:select id="role" name="role" path="role">
            <option name="student" value="STUDENT">student</option>
            <option name="teacher" value="TEACHER">teacher</option>
            <option name="admin" value="ADMIN">admin</option>
        </form:select><br>

        <form:button name="register" id="register"  onClick="removeRequired(this.form)" class="btn">Register</form:button><br>

    </form:form>
</div>
<div id="h1" >
<form action="/" method="get">
    <button type="submit" class="button">
       home
    </button>
</div>
</form>
</div>


</body>

<script>
    var password = document.getElementById("password")
        , confirm_password = document.getElementById("confirm_password");

    function validatePassword() {
        if (password.value != confirm_password.value) {
            confirm_password.setCustomValidity("Passwords Don't Match");
        } else {
            confirm_password.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;

    function removeRequired(form) {
        $.each(form, function (key, value) {
            if (value.hasAttribute("required")) {
                value.removeAttribute("required");
            }
        });
    }

    document.getElementById("register").onclick = function() {
        let allAreFilled = true;
        document.getElementById("registrationForm").querySelectorAll("[required]").forEach(function(i) {
            if (!allAreFilled) return;
            if (!i.value) allAreFilled = false;
            if (i.type === "radio") {
                let radioValueCheck = false;
                document.getElementById("registrationForm").querySelectorAll(`[name=${i.name}]`).forEach(function(r) {
                    if (r.checked) radioValueCheck = true;
                })
                allAreFilled = radioValueCheck;
            }
        })
        if (!allAreFilled) {
            alert('Fill all the fields');
        }
    };
</script>

</html>
