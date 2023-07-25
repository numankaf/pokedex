<%--
  Created by IntelliJ IDEA.
  User: Numan
  Date: 7/25/2023
  Time: 1:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    input[type=text],input[type=number]  {
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }

    input[type=submit] {
        width: 100%;
        background-color: #4CAF50;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    input[type=submit]:hover {
        background-color: #45a049;
    }
</style>
<head>
    <title>Secure Example</title>
</head>
<body>

<h1>Secure Example </h1>
<form action="secure" method="GET">
    <div style="padding: 1rem; background-color: #f2f2f2; width: 20%;">
        <input type="submit" value="Send Request"/>
    </div>
</form>
</body>
</html>
