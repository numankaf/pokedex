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
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    input[type=submit]:hover {
        opacity: 0.7;
    }
    body{
        display: flex;

        justify-content: center;
    }
</style>
<head>
    <title>Session Example</title>
</head>
<body>
<div>
    <h1> Session Example </h1>
    <form action="create-session" method="GET">
        <div style="padding: 1rem; ">
            <input style="background-color: #45a049;" type="submit" value="Call create-session"/>
        </div>
    </form>
    <form action="list-session-attributes" method="GET">
        <div style="padding: 1rem; ">
            <input style="background-color: #D97706;" type="submit" value="Call list-session-attributes"/>
        </div>
    </form>
    <form action="invalidate-session" method="GET">
        <div style="padding: 1rem; ">
            <input style="background-color: #EF4444;" type="submit" value="Call invalidate-session"/>
        </div>
    </form>
</div>

</body>
</html>
