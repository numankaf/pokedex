
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    a {
        font-size: 20px;
        color: #16A34A;
    }

    a:link {
        text-decoration: none;
    }

    a:visited {
        text-decoration: none;
    }

    a:hover {
        text-decoration: underline;
    }

    a:active {
        text-decoration: underline;
    }

    /* selected link */
    a:active {
        color: #16A34A;
    }
</style>
<head>
    <title> Successfull</title>
</head>
<body>
<% String message = (String) request.getAttribute("opSucMessage");%>
<div style="display: flex; align-items: center; justify-content: center; flex-direction: column" >
    <img src="https://img.freepik.com/premium-vector/checkmark-vector-icon-put-green-symbol-white-background-vector-illustration_185004-669.jpg?w=2000"  style="width: 300px;height: 300px"/>
    <h1><%=message%></h1>
</div>
<div style="display: flex; justify-content: center; padding: 1rem">
    <a href="./">Return to Main Menu</a>
</div>
</body>
</html>
