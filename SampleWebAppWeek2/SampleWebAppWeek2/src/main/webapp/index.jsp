<%@ page import="Entity.Person" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
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
<body>
<h1>First Servlet Example</h1>

<a action="FirstServlet" method="GET" href="FirstServlet"> Get Characters</a>
<h3>Add Character</h3>
<form action="FirstServlet" method="POST">
    <div style="padding: 1rem; background-color: #f2f2f2; width: 20%;">
        <div style="display: flex; flex-direction: column; justify-content: center;">
            <div style="display: flex;align-items: center;justify-content: space-between">
                <h2>Image Url:</h2>
                <input type="text" name="imgUrl">
            </div>
            <div style="display: flex;align-items: center;justify-content: space-between">
                <h2> Name: </h2>
                <input type="text" name="name">
            </div>
            <div style="display: flex;align-items: center;justify-content: space-between">
                <h2>Anime:</h2>
                <input type="text" name="anime">
            </div>
            <div style="display: flex;align-items: center;justify-content: space-between">
                <h2>Age:</h2>
                <input type="number" name="age">
            </div>
        </div>

        <input type="submit" value="Add Character"/>
    </div>
</form>
</body>
</html>