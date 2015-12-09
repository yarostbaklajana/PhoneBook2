<%@ page language="java" contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="Styles/styles.css">
    <meta charset="UTF-8">
    <title>Phone Book | Details</title>
</head>
<body>
<header>
    <h1 class="details-header">Contact Details</h1>
    <hr class="under-line">
</header>
<div class="links"><a href="/">Back To The List</a> | <a href="/edit?id=${contactDetails.contactId}">Edit Details</a></div>

<div> First Name: <c:out value="${contactDetails.firstName}"/></div>

<div> Last Name: <c:out value="${contactDetails.lastName}"/></div>

<header>
    <h1 class="details-header">Phones</h1>
    <hr class="under-line">
</header>
<div class="add-link"><a  href="/addphone?contact=${contactDetails.contactId}">Add Phone</a></div>
    <c:if test="${contactDetails.phones ne null}">
        <table class="table">
            <tr>
                <td class="title-column">Type</td>
                <td class="title-column">Phone Number</td>
            </tr>
            <c:forEach items="${contactDetails.phones}" var="phone">
                <tr>
                    <td class="column">${phone.type}</td>
                    <td class="column">${phone.phoneNumber}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>


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