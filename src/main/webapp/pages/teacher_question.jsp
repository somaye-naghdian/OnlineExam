<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/theme/css/teacherCss.css" />">
    <title>Add Question</title>
</head>
<body>

<h4>Add Question To exam</h4>

<input type="button" value="FromQuestionBank" onclick="getQuestionBank()">
<input type="button" value="CreateNewQuestion" onclick="showButtons()"><br><br>


<div id="btns">

    <input id="title" placeholder="title" style="visibility: hidden">

    <input id="text" placeholder="Question Text" style="visibility: hidden">

    <input type="button" id="mcQuestion" value="MultipleChoiceQuestion" class="styled-button-2"
           style="visibility: hidden" onclick="addOption()">
    <input type="button" id="dQuestion" value="DescriptiveQuestion" class="styled-button-2"
           style="visibility: hidden" onclick="addDescriptiveQuestion()"><br><br>

</div>
<div id="option">
    <input id="answer" placeholder="AnswerContent" style="visibility: hidden">
    <input type="button" id="moreOption" style="visibility: hidden"
         value="more option" onclick="add()"/>

    <input type="button" id="answerOption" value="save" style="visibility: hidden"
           onclick="addMultipleChoiceQuestion()">
</div>

</body>
<script>
    function showButtons() {
        document.getElementById("title").style.visibility = "visible";
        document.getElementById("text").style.visibility = "visible";
        document.getElementById("mcQuestion").style.visibility = "visible";
        document.getElementById("dQuestion").style.visibility = "visible";
    }

    function addOption() {
        document.getElementById("answer").style.visibility = "visible";
        document.getElementById("answerOption").style.visibility = "visible";
        document.getElementById("moreOption").style.visibility = "visible";
    }

    function add() {
        var element = document.createElement("input");
        var type=document.getElementById("answer").value;
        element.setAttribute("type", type);
        element.setAttribute("value", type);
        element.setAttribute("name", type);

        var option = document.getElementById("option");
        option.appendChild(element);
    }

    function addMultipleChoiceQuestion() {
        var question = {
            "title": document.getElementById("title").value,
            "text": document.getElementById("text").value
            // "answer": document.getElementById("answer").value
        };
        $.ajax({
            type : "POST",
            contentType : 'application/json; charset=utf-8',
            dataType : 'json',
            url : "http://localhost:8080/addMultipleChoiceQuestion/${examId}",
            data :  JSON.stringify(data),
            success : function(result) {
                console.log("result");
            },
       <%-- var xhttp = new XMLHttpRequest();--%>
       <%-- xhttp.onreadystatechange = function () {--%>
       <%--     if (this.readyState == 4 && this.status == 200) {--%>
       <%--         console.log(this.responseText);--%>
       <%--     }--%>
       <%-- };--%>
       <%-- xhttp.open("POST", "http://localhost:8080/addMultipleChoiceQuestion/${examId}", true);--%>
       <%--xhttp.setRequestHeader("Content-type","application/json; charset=UTF-8");--%>
       <%-- xhttp.send(JSON.stringify(question));--%>
    });
    }

    function addDescriptiveQuestion() {
        var question = {
            "title": document.getElementById("title").value,
            "text": document.getElementById("text").value,
        };
        console.log(question);
        <%--var xhttp = new XMLHttpRequest();--%>
        <%--xhttp.onreadystatechange = function () {--%>
        <%--    if (this.readyState == 4 && this.status == 200) {--%>
        <%--        console.log(this.responseText);--%>
        <%--    }--%>
        <%--};--%>
        <%--xhttp.open("POST", "http://localhost:8080/DescriptiveQuestion/addDescriptiveQuestion/${examId}", true);--%>
        <%--xhttp.setRequestHeader("Content-type","application/json; charset=UTF-8");--%>
        <%--xhttp.send(JSON.stringify(question));--%>
        $.ajax({
            type : "POST",
            contentType : 'application/json; charset=utf-8',
            dataType : 'json',
            url : "http://localhost:8080/DescriptiveQuestion/addDescriptiveQuestion/${examId}",
            data :  JSON.stringify(question),
            success : function(result) {
                console.log("result");
            },
            error: function (e) {
                console.log("done");
            }

        });
    }

    function getQuestionBank() {
        window.open("http://localhost:8080/questionBank/" +${examId});
    }


    //
    // function addFields() {
    //     var number = document.getElementById("answer").value;
    //     var container = document.getElementById("option");
    //     while (container.hasChildNodes()) {
    //         container.replaceChild (container.lastChild);
    //     }
    //     for (var i = 0; i < number; i++) {
    //         container.appendChild(document.createTextNode("answer " + (i + 1)));
    //         var input = document.createElement("input");
    //         input.type = "text";
    //         input.name = "answer" + i;
    //         container.appendChild(input);
    //         container.appendChild(document.createElement("br"));
    //     }
    // }


</script>
</html>
