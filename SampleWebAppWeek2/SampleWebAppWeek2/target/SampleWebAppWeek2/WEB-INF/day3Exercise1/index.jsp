<%--
  Created by IntelliJ IDEA.
  User: Numan
  Date: 7/26/2023
  Time: 9:54 AM
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
    <title>ExceptionExample</title>
</head>
<body>
<div style="display: flex; align-items: center;justify-content: center">
    <div>
        <h1 style="text-align: center">Divider</h1>
        <form action="exceptionExample" method="POST">
            <div style="padding: 1rem; background-color: #f2f2f2; ">
                <div style="display: flex; flex-direction: column; justify-content: center;">
                    <div style="display: flex;align-items: center;justify-content: space-between">
                        <h2>First Number:</h2>
                        <input type="number" name="firstNum">
                    </div>
                    <div style="display: flex;align-items: center;justify-content: space-between">
                        <h2>Second Number:</h2>
                        <input type="number" name="secondNum">
                    </div>
                </div>

                <input type="submit" value="Divide"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>
