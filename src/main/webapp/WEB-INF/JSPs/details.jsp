<%@ page language="java" contentType="text/html; UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Phone Book | Details</title>
</head>
<body>
    <header>
        <h1>Contact Details</h1>
    </header>
    <p> <a href="/phonebook">Back To The List</a> | <a href="/edit?id=${contact.id}">Edit Details</a> </p>

    <p> First Name: <c:out value="${contact.firstName}" /></p>
    <p> Last Name: <c:out value="${contact.lastName}" /></p>
</body>