<%--
  Created by IntelliJ IDEA.
  User: Numan
  Date: 7/24/2023
  Time: 3:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Entity.Person" %>
<html>
<body>
<% List<Person> characters = (ArrayList<Person>)request.getAttribute("characters");
    out.print("<h3> Characters Information </h3>");
    for(Person c :characters )
    {
        out.print("<img src=\""+c.getImgUrl()+"\" style=\"width:300;height:300;\">");
        out.print("<br/>");
        out.print("Name: " + c.getName());
        out.print("<br/>");
        out.print("Anime: " + c.getAnime());
        out.print("<br/>");
        out.print("Age: " + c.getAge());

        out.print("<br/>");
        out.print("<br/>");
    }
%>

</body>
</html>