<%@ page language="java" contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="Styles/styles.css">
    <meta charset="UTF-8">
    <title>Phone Book</title>
</head>
<p>

<p class="add">
    <a href="/add">Add new contact</a>
</p>


<table class="table">
    <tr class="main-header">
        <td class="column">First name</td>
        <td class="column">Last name</td>
        <td class="column">Phones</td>
        <td class="buttons-column"></td>
    </tr>


    <c:forEach items="${contacts}" var="current">
        <tr class="contacts">
            <td class="column"><c:out value="${current.firstName}"/></td>
            <td class="column"><c:out value="${current.lastName}"/></td>
            <td class="column"><c:out value="${current.phonesCount}"/></td>
            <td class="buttons-column">

                <a class="details-link" href="/details?id=${current.id}">
                    <button>Open details</button>
                </a>

                <form class="delete-form" action="/delete" method="post">
                    <input type="hidden" name="id" value="${current.id}"/>
                    <button type="submit">Delete</button>
                </form>
                <br>


            </td>
        </tr>
    </c:forEach>
</table>
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