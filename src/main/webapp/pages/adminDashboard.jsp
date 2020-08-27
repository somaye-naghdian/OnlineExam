<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/adminCss.css" />">
    <title>Admin Page</title>
</head>
<body>
<h1 id="h1">Admin Page</h1>

<div class="container" style="display: inline-flex">
<div class="navbar-collapse collapse " id="myNavbar">
    <div>
        <ul class="nav navbar-nav">
            <li class="nav-item">
                <form:form action="unconfirmedUser" method="get">
                    <button type="submit" name="confirmUser" value="confirmUser">Confirm User</button>
                </form:form>
            </li>

            <li class="nav-item">
                <form:form action="getUserList" method="get" >
                    <button type="submit" name="userList" value="UserList">User List</button>
                </form:form>
            </li>


            <li class="nav-item">
                <form:form action="search" method="get">
                    <button type="submit" name="searchUser" value="searchUser">Search User</button>
                </form:form>
            </li>

            <li class="nav-item">
                <form:form action="getCourseList" method="get">
                    <button type="submit" name="courseList" value="courseList">Course List</button>
                </form:form>
            </li>
            </nav>
        </ul>
        <ul class="nav navbar-nav">
            <div class="navbar-collapse collapse " id="myNavbar2" style=" position: absolute; right: 20px;" >
                <li class="nav-item">
                    <form:form action="addClassification" method="get">
                        <button type="submit" name="addClassification" value="addClassification">
                            Add Classification
                        </button>
                    </form:form>
                </li>

                <li class="nav-item">
                    <form:form action="addCourse" method="get">
                        <button type="submit" name="addCourse" value="addCourse">Add Course</button>
                    </form:form>
                </li>

                <li class="nav-item">
                    <form:form action="addUserToCourse" method="get">
                        <button type="submit" name="CourseRegistration" value="addUserToCourse">Course Management
                        </button>
                    </form:form>
                </li>

                <li class="nav-item">
                    <form:form action="userOfCourse" method="get">
                        <button type="submit" name="userOfCourse" value="userOfCourse">User Of Course
                        </button>
                    </form:form>
                </li>

                </nav>
            </div>
        </ul>

    </div>
</div>
</div>

<div class="container">


    <form action="/" method="get">
        <button style="margin:5px;color: coral; cursor: pointer; background-color: powderblue;" type="submit"
                value="home">Home
        </button>
    </form>
</div>
</body>
</html>
