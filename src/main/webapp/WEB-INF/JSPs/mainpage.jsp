<%@ page language="java" contentType="text/html; UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="Styles/mainpage.css">
    <meta charset="UTF-8">
    <title>Phone Book</title>
</head>
<body>

<p class="add">
    <a href="/add"><button>Add new contact</button></a>
</p>


<table class="table">
    <tr class="header">
        <td class="title-column">First name</td>
        <td class="title-column">Last name</td>
        <td class="buttons"></td>
    </tr>


    <c:forEach items="${contacts}" var="current">
        <tr class="contacts">
            <td class="column"><c:out value="${current.firstName}" /></td>
            <td class="column"><c:out value="${current.lastName}" /></td>
            <td class="buttons-column">
                <form action="/PhoneBook/delete" method="post">
                    <input type="hidden" name="id" value="${current.id}" />
                    <input
                        type="submit" value="Delete">
                </form> <br>

                <a href="/details?id=${current.id}"><button>Open details</button></a>
            </td>
        </tr>
    </c:forEach>
</table>
<h1>
    <c:if test="${errorMessages ne null}">
        <ul>
            <c:forEach items="${errorMessages}" var="errorMessage">
                <li>${errorMessage}</li>
            </c:forEach>
        </ul>
    </c:if>
</h1>




</body>
</html>