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
        <link rel="stylesheet" href="resources/css/article.css"/>
        
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
            <c:if test="${role eq 'ADMIN' || role eq 'EDITOR'}">
                <c:if test="${comment.author.login eq regUser.login}">
                    <form action="controller?command=editComment" method="POST" id="form${comment.id}">
                        <input type="hidden" name="articleId" value="${article.id}">
                        <input type="hidden" name="commentId" value="${comment.id}">
                        <div>${comment.author.login} ${comment.editDate}</div>
                        <textarea disabled name="newComment" class="comment-text" id="comment${comment.id}">${comment.text}</textarea><br>
                        <input id="changeBtn${comment.id}"  onclick="openForEditBtn(${comment.id})" type="submit"   class="comment-btn" value="Открыть для изменения">
                    </form>
                </c:if>
                <c:if test="${comment.author.login ne regUser.login}">
                    <div>${comment.author.login} ${comment.editDate}</div>
                    <div>${comment.text}</div>
                </c:if>
                    <input id="editBtn${comment.id}"  type="button" value="Редактирование..." onclick="changeCommentBtn(${comment.id})">
                    <a href="controller?command=deleteComment&commentId=${comment.id}&articleId=${article.id}">Удалить</a>
                    
            </c:if>
            <c:if test="${role ne 'ADMIN' && role ne 'EDITOR'}">
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
            </c:if>
        </c:forEach>
                    <script src="resources/js/article.js"  defer></script>
    </body>
</html>
