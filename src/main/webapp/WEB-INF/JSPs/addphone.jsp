<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="Styles/styles.css">
    <meta charset="UTF-8">
    <title>Phone Book | Add Phone</title>
</head>
<body>

<form action="/addphone" method="post">
    <p>Type: <select name="phoneType" size="1">
        <option selected disabled="selectType">Select Phone Type</option>
        <c:forEach items="${types}" var="type">
            <option value="${type.typeId}">${type.phoneType}</option>
        </c:forEach>
    </select></p>
    <p>Phone Number: <input type="text" name="number"></p>
    <input type="hidden" name="contactId" value="${contactId}">
    <a href="/details?id=${contactId}">Cancel</a>
    <input type="submit" value="Save">
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