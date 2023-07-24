<%@ page import="Entity.Person" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<html>
<body>
<h1>First Servlet Example</h1>

<a action="FirstServlet" method="GET" href="FirstServlet"> Get Characters</a>
<h3>Add Character</h3>
<form action = "FirstServlet" method = "POST">

    Image Url: <input type = "text" name = "imgUrl">
    <br />
    Name: <input type = "text" name = "name">
    <br />
    Anime: <input type = "text" name = "anime" />
    <br />
    Age: <input type = "number" name = "age">
    <br />
    <input type = "submit" value = "Add Character" />
</form>
</body>
</html>