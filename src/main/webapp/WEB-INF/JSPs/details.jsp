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
<div class="links"><a href="/">Back To The List</a> | <a href="/edit?id=${contactDetails.id}">Edit Details</a></div>

<div> First Name: <c:out value="${contactDetails.firstName}"/></div><br>

<div> Last Name: <c:out value="${contactDetails.lastName}"/></div>

<header>
    <h1 class="details-header">Phones</h1>
    <hr class="under-line">
</header>
<div class="links"><a  href="/addphone?contact=${contactDetails.id}">Add Phone</a></div>
    <c:if test="${contactDetails.hasPhones}">
        <table class="table">
            <tr class="main-header">
                <td class="title-column">Type</td>
                <td class="title-column">Phone Number</td>
                <td class="buttons"></td>

            </tr>
            <c:forEach items="${contactDetails.phones}" var="phone">
                <tr class="contacts">
                    <td class="column">${phone.type}</td>
                    <td class="column">${phone.phoneNumber}</td>
                    <td class="buttons-column">
                        <form class="delete-form" action="/deletephone" class="delete-phone" method="post">
                            <input type="hidden" name="contactId" value="${contactDetails.id}">
                            <input type="hidden" name="phoneId" value="${phone.id}">
                            <button type="submit">Delete</button>
                        </form>
                    </td>
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