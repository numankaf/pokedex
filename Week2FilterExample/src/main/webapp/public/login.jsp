
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
    <title> Login</title>
</head>
<body>
<div >
    <h3 style="text-align: center">Login</h3>
    <form action="private" method="POST" style="background-color: #f2f2f2;" >
        <div style="padding: 1rem; ">
            <div style="display: flex; flex-direction: column; justify-content: center;">
                <div style="display: flex;align-items: center;justify-content: space-between">
                    <h2>Username: </h2>
                    <input type="text" name="username">
                </div>
                <div style="display: flex;align-items: center;justify-content: space-between">
                    <h2> Password: </h2>
                    <input type="text" name="password">
                </div>
            </div>
            <input type="submit" value="Login"/>
        </div>
    </form>
</div>
</body>
</html>
