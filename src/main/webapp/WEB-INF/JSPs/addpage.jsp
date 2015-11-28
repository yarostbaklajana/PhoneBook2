<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Phone Book | Add Contact</title>
</head>
<body>


<form action="/add" method="post">
    <input type="text" name="firstName" value="<%= request.getAttribute("firstName") %>" placeholder="First Name"><br>
    <input type="text" name="lastName" value="<%= request.getAttribute("lastName") %>" placeholder="Last Name"><br>
    <p>
        <a href="/phonebook">Cancel</a>
        <input type="submit" value="Save">
    </p>
</form>


<p>
    <c:if test="${errorMessages ne null}">
        <ul>
            <c:forEach items="${errorMessages}" var="errorMessage">
                <li>${errorMessage}</li>
            </c:forEach>
       </ul>
    </c:if>
</p>

</body>
</html>