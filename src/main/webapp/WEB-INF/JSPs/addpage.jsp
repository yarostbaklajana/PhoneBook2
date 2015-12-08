<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="Styles/styles.css">
    <meta charset="UTF-8">
    <title>Phone Book | Add Contact</title>
</head>
<body>


<form action="/add" method="post">
    <input type="text" name="firstName" value="${contact.firstName}" placeholder="First Name"><br>
    <input type="text" name="lastName" value="${contact.lastName}" placeholder="Last Name"><br>

    <div>
        <a href="/">Cancel</a>
        <input type="submit" value="Save">
    </div>
</form>


<div class="error">
    <c:if test="${errorMessages ne null}">
        <ul class="error-list">
             <c:forEach items="${errorMessages}" var="errorMessage">
                <li>${errorMessage}</li>
            </c:forEach>
        </ul>
    </c:if>
</div>

</body>
</html>