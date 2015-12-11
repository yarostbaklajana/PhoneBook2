<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="Styles/styles.css">
    <meta charset="UTF-8">
    <title>Phone Book | Edit Contact</title>
</head>
<body>

<form action="/edit" method="post">
    <div><label class="form-label">First Name:</label><input name="firstName" type="text" value="${contact.firstName}"/>
    </div>

    <div><label class="form-label">Last Name:</label><input name="lastName" type="text" value="${contact.lastName}"/></div>
    <input name="id" type="hidden" value="${contact.id}"/>

    <div class="form-controls">
        <a href="/details?id=${contact.id}">Back to Details</a>
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