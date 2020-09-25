
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Message</title>
</head>
<body>
<table align="center">
    <tr>
        <td>Message: ${message}</td>
    </tr>
    <td><a href="/">Home</a>
</table>

<form action="/login" method="get">
    <button style="margin:5px;color: midnightblue; cursor: pointer; background-color: powderblue;" type="submit"
            value="login" >LOGIN
    </button>
</form>

<div>
    <form action="/logout" method="get">
        <button type="submit"
                value="logout" class="button">logout
        </button>
    </form>
</div>
</body>
</html>
