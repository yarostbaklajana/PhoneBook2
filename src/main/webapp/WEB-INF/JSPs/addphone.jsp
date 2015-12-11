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

<form action="/addphone?contact=${contactId}" method="post">
    <div><label class="form-label">Type:</label><select name="phoneType" size="1" value="${phoneNumber.type}">
        <option value="">Select Phone Type</option>
        <c:forEach items="${types}" var="type">
            <c:if test="${type.typeId eq phoneNumber.type}">
                <option selected value="${type.typeId}">${type.phoneType}</option>
            </c:if>
            <c:if test="${type.typeId ne phoneNumber.type}">
                <option value="${type.typeId}">${type.phoneType}</option>
            </c:if>
        </c:forEach>
    </select></div>
    <div><label class="form-label">Phone Number:</label><input type="text" name="number" value="${phoneNumber.phoneNumber}"></div>
    <input type="hidden" name="contactId" value="${contactId}">
    <div class="form-controls"><a href="/details?id=${contactId}">Cancel</a>
    <input type="submit" value="Save"></div>
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