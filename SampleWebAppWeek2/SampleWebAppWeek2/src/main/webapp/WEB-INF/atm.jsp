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
    <title>ATM</title>
</head>
<body>

<h1>Withdraw </h1>
<form action="atm" method="GET">

    <div style="padding: 1rem; background-color: #f2f2f2; width: 20%;">
        <div style="display: flex; flex-direction: column; justify-content: center;">
            <div style="display: flex;align-items: center;justify-content: space-between">
                <h2>Amount : </h2>
                <input type="number" name="money">
            </div>
        </div>
        <input type="submit" value="Withdraw"/>
    </div>
</form>

<h1>Deposite </h1>
<form action="atm" method="POST">

    <div style="padding: 1rem; background-color: #f2f2f2; width: 20%;">
        <div style="display: flex; flex-direction: column; justify-content: center;">
            <div style="display: flex;align-items: center;justify-content: space-between">
                <h2>Amount : </h2>
                <input type="number" name="money">
            </div>
        </div>
        <input type="submit" value="Deposite"/>
    </div>
</form>
</body>
</html>
