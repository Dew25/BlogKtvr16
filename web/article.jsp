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
        <a href="controller?command=editArticle&id=${article.id}">Изменить</a> 
        
        <form action="controller?command=addComment" method="POST">
            <input type="hidden" name="articleId" value="${article.id}">
            <textarea name="comment"></textarea><br>
            <input type="submit" value="Добавить комментарий">
        </form>
        <br>
        <c:forEach var="comment" items="${comments}">
            <div>${comment.author.login} ${comment.editDate}</div>
            <div>${comment.text}</div>
        </c:forEach>
    </body>
</html>
