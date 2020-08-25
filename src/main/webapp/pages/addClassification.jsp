<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <title>Add Classification</title>
</head>
<body>
<div class="header">
    <h2>ADD CLASSIFICATION</h2>
</div>

<div class="container">

    <form:form action="addClassificationProcess" modelAttribute="classification" method="get">

        <form:label path="classificationTitle">Classification Title</form:label>
        <form:input path="classificationTitle" name="classificationTitle" placeholder="classificationTitle"
                    class="form-control"
                    required="required"/><br><br>

        <form:button name="add" class="btn">Add Classification</form:button><br><br>
    </form:form>


    <p>${message}</p>
    <form action="/" method="get">
        <button type="submit" class="btn"
                onClick="removeRequired(this.form)">
            Home
        </button>
    </form>


</div>


</body>
</html>
