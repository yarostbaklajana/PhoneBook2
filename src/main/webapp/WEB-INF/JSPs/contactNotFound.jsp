<%@ page language="java" contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Phone Book | Error</title>
</head>
<body>
<c:if test="${errorMessages ne null}">
    <ul>
        <c:forEach items="${errorMessages}" var="error">
            <li>${error}</li>
        </c:forEach>
    </ul>
    <a href="/phonebook">Back to the List</a>
</c:if>

</body>
</html>