<%--
  Created by IntelliJ IDEA.
  User: Numan
  Date: 7/26/2023
  Time: 1:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    input[type=text],input[type=number]  {
        padding: 12px 20px;
        margin: 8px 0;
        width: 80%;
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
    <title>Create Contact</title>
</head>
<body>
<div style="width: 50%">
    <h3 style="text-align: center">Create Contact</h3>
    <form action="createContact" method="POST" style="background-color: #f2f2f2;" >
        <div style="padding: 1rem; ">
            <div style="display: flex; flex-direction: column; justify-content: center;">
                <div style="display: flex;align-items: center;justify-content: space-between">
                    <h2>Name:</h2>
                    <input type="text" name="name">
                </div>
                <div style="display: flex;align-items: center;justify-content: space-between">
                    <h2> GSM: </h2>
                    <input type="text" name="gsm">
                </div>
            </div>
            <input type="submit" value="Create Contact"/>
        </div>
    </form>
</div>
</body>
</html>
