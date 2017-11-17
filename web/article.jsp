<%-- 
    Document   : article
    Created on : Nov 17, 2017, 1:37:38 PM
    Author     : Melnikov
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Статья</title>
    </head>
    <body>
        <h3>${article.title}!</h3>
        <div>${article.text}</div>
        <div>${article.author.login}</div>
        <c:forEach var="message" items="${messages}">
            <div>${message.text}</div>
            <div>${message.author.login}</div>
        </c:forEach>
    </body>
</html>
