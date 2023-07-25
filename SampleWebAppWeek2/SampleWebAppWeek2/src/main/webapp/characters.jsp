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
<% List<Person> characters = (ArrayList<Person>) request.getAttribute("characters");%>
<h3> Characters List </h3>
<div style="display: flex; gap:2rem; align-items: center;flex-wrap: wrap;  flex-direction: row;">

    <%
        for (Person c : characters) {
    %>

    <div style="display: flex; width:30%    ; gap:1rem; padding: 1rem; border: 1px solid black; border-radius: 15px;">
        <img src="<%=c.getImgUrl()%>" style="width:200px;height:200px;">
        <div>
            <h2>Name : <%=c.getName()%>
            </h2>
            <h3>Anime : <%=c.getAnime()%>
            </h3>
            <h3>Age <%=c.getAge()%>
            </h3>
        </div>

    </div>


    <%
        }

    %>
</div>
</body>
</html>