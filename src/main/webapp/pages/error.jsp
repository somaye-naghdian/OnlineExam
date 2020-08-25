
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ERROR</title>

    <style>
        body{
            background-color: #FFC0CB;
        }
    </style>
</head>
<body>
<table align="center">
    <tr>
        <td style="size: A3">error: ${errorMsg}</td>
    </tr>
    <td><a href="/">Home</a>
</table>

<form action="/" method="get">
    <button style="margin:5px;color: midnightblue; cursor: pointer; background-color: powderblue;" type="submit"
            value="home" >LOGIN
    </button>
</form>

</body>
</html>
