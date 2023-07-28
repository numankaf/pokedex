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
    input[type=text], input[type=number] {
        width: 80%;
        padding: 12px 20px;
        display: inline-block;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }

    input[type=submit] {
        width: 100%;
        color: white;
        padding: 14px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    input[type=submit]:hover {
        opacity: 0.8;
    }

    a {
        font-size: 20px;
        color: #16A34A;
    }

    a:link {
        text-decoration: none;
    }

    a:visited {
        text-decoration: none;
    }

    a:hover {
        text-decoration: underline;
    }

    a:active {
        text-decoration: underline;
    }

    /* selected link */
    a:active {
        color: #16A34A;
    }

</style>
<head>
    <title>List Of Contacts</title>
</head>
<body>
<% List<Contact> contacts = (ArrayList<Contact>) request.getAttribute("searchedContacts");%>

<div style="display: flex; justify-content: space-between">
    <h1 style="text-align: center"> List of Contacts</h1>
    <div style="display: flex; justify-content: end; padding: 1rem">
        <form  action="createContact" method="GET">
            <div>
                <input style="background-color: #16A34A;" type="submit" value="Create a New Contact"/>
            </div>
        </form>
    </div>

</div>
<form action="searchContact" method="POST" style="background-color: #f2f2f2;">
    <div>
        <div style="display: flex; gap: 1rem; justify-content: center;">
            <div style="display: flex;align-items: center; width:70%; justify-content: space-between">
                <h2>Search By Name:</h2>
                <input type="text" name="name" placeholder="Enter the name...">
            </div>
            <input style="background-color: #A855F7; width: 15%; margin: 12px 0px;" type="submit"
                   value="Search Contact"/>
        </div>

    </div>
</form>

<h3 style="text-align: center"> Total Records : <%=contacts.size()%>
</h3>
<div style="display: flex; justify-content: center; gap:2rem; align-items: center;flex-wrap: wrap;  ">

    <%
        for (Contact c : contacts) {
    %>

    <div style="display: flex; width: 45vw; gap:1rem; border: 1px solid black; border-radius: 15px; flex-direction: column">
        <form action="editContact" method="POST">
            <div style="padding: 0 1rem">
                <div style="display: flex; flex-direction: column; justify-content: center;">
                    <div style="display: flex;align-items: center;justify-content: space-between">
                        <input type="hidden" name="id" value="<%=c.getId()%>">
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
                <input style="background-color: #3B82F6;" type="submit" value="Edit Contact"/>
            </div>
        </form>
        <form action="deleteContact" method="POST">
            <div style="padding: 0 1rem">
                <div style="display: flex; flex-direction: column; justify-content: center;">
                    <div style="display: flex;align-items: center;justify-content: space-between">
                        <input type="hidden" name="id" value="<%=c.getId()%>">
                    </div>
                </div>
                <input style="background-color: #DC2626;" type="submit" value="Delete Contact"/>
            </div>
        </form>
    </div>
    <%
        }

    %>
</div>
<div style="display: flex; justify-content: center; padding: 1rem">
    <a href="./">Return to Main Menu</a>
</div>
</body>
</html>
