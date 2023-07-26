<%@ page import="Entity.Contact" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Numan
  Date: 7/26/2023
  Time: 3:58 PM
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
    body{
        display: flex;

        justify-content: center;
    }
</style>
<head>
    <title>List Of Contacts</title>
</head>
<body>
<% List<Contact> contacts = (ArrayList<Contact>) request.getAttribute("searchedContacts");%>
<div style="display: flex; gap:2rem; align-items: center;flex-wrap: wrap;  flex-direction: row;">

    <%
        for (Contact c : contacts) {
    %>

    <div style="display: flex;   ; gap:1rem; padding: 1rem; border: 1px solid black; border-radius: 15px;">
        <form action="editContact" method="POST" style="background-color: #f2f2f2;" >
            <div style="padding: 1rem; ">
                <div style="display: flex; flex-direction: column; justify-content: center;">
                    <div style="display: flex;align-items: center;justify-content: space-between">
                        <input type="hidden"  name="id" value="<%=c.getId()%>">
                    </div>
                    <div style="display: flex;align-items: center;justify-content: space-between">
                        <h2>Name:</h2>
                        <input type="text" name="name" value="<%=c.getName()%>">
                    </div>
                    <div style="display: flex;align-items: center;justify-content: space-between">
                        <h2> GSM: </h2>
                        <input type="text" name="gsm" value="<%=c.getGsm()%>">
                    </div>
                </div>
                <input type="submit" value="Edit Contact"/>
            </div>
        </form>

    </div>
    <%
        }

    %>
</div>
</body>
</html>
