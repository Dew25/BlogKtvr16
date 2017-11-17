<%-- 
    Document   : NewArticle
    Created on : Nov 15, 2017, 1:14:45 PM
    Author     : Melnikov
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Новая статья!</h1>
        <form action="addArticle" method="POST">
            <input type="text" name="title"><br>
            <textarea name="text"></textarea><br>
            <select name="author">
                <c:forEach var="user" items="${users}">
                   <option value="${user.id}">${user.login}</option> 
                </c:forEach>
                   <input type="submit" value="Добавить">
            </select><br>
        </form>
    </body>
</html>
