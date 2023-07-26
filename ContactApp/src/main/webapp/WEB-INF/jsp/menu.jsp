<%--
  Created by IntelliJ IDEA.
  User: Numan
  Date: 7/26/2023
  Time: 1:50 PM
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
    body{
        display: flex;

        justify-content: center;
    }
</style>
<head>
    <title>Menu</title>
</head>
<body>
<div>
    <h1> Welcome to Contact Crud App</h1>
    <form action="createContact" method="GET">
        <div style="padding: 1rem; ">
            <input type="submit" value="Go to Create Contact"/>
        </div>
    </form>
    <form action="searchContact" method="GET">
        <div style="padding: 1rem; ">
            <input type="submit" value="Go to Search Contact"/>
        </div>
    </form>
</div>

</body>
</html>
