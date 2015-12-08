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
    <div>First Name: <input name="firstName" type="text" value="${contact.firstName}" placeholder="First Name"/>
    </div>

    <div>Last Name: <input name="lastName" type="text" value="${contact.lastName}" placeholder="Last Name"/></div>
    <input name="id" type="hidden" value="${contact.id}"/>

    <div>
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