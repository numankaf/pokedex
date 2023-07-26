<%@ page import="Entity.Contact" %><%--
  Created by IntelliJ IDEA.
  User: Numan
  Date: 7/26/2023
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    body{
        display: flex;
        text-align: center;
        justify-content: center;
    }
</style>
<head>
    <title>Successful</title>
</head>
<body>
<% Contact c = (Contact) request.getAttribute("createdContact");%>
<div style="width: 30%">
    <h1>Operation Successfull  </h1>
    <h3>Created or Updated Contact</h3>
    <div style="display: flex;    ; gap:1rem; padding: 1rem; border: 1px solid black; border-radius: 15px;">
        <div>
            <h2>Name : <%=c.getName()%></h2>
            <h2>Gsm : <%=c.getGsm()%></h2>
        </div>
    </div>
</div>
</body>
</html>
