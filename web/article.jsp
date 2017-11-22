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
            <c:if test="${comment.author.login eq regUser.login}">
                <form action="controller?command=editComment" method="POST">
                    <input type="hidden" name="articleId" value="${article.id}">
                    <input type="hidden" name="commentId" value="${comment.id}">
                    <div>${comment.author.login} ${comment.editDate}</div>
                    <textarea name="newComment">${comment.text}</textarea><br>
                    <input type="submit" value="Изменить комментарий">
                </form>
            </c:if>
            <c:if test="${comment.author.login ne regUser.login}">
                <div>${comment.author.login} ${comment.editDate}</div>
                <div>${comment.text}</div>
            </c:if>
        </c:forEach>
    </body>
</html>
