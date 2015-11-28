<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Phone Book | Edit Contact</title>
</head>
<body>
<c:if test="${errorMessages eq null}">
<form action="/edit" method="post">
    <p>First Name: <input name="firstName" type="text" value="${contact.firstName}" placeholder="${contact.firstName}"/> </p>
    <p>Last Name: <input name="lastName" type="text" value="${contact.lastName}" placeholder="${contact.lastName}"/></p>
    <input name="id" type="hidden" value="${contact.id}"/>
    <p>
        <a href="/details?id=${contact.id}">Back to Details</a>
        <input type="submit" value="Save">
    </p>
</form>
</c:if>
<p>
    <c:if test="${errorMessages ne null}">
<ul>
    <c:forEach items="${errorMessages}" var="errorMessage">
        <li>${errorMessage}</li>
    </c:forEach>
</ul>
<a href="/phonebook">Back to the list</a>
</c:if>
</p>

</body>