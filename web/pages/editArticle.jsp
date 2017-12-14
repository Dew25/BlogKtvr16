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
        <h2>Список статей:</h2>        
        <ul>
            <c:forEach var="article" items="${articles}">
                <li>${article.title} 
                    <a href="controller?command=deleteArticle&id=${article.id}">x</a>
                    <a href="controller?command=editArticle&id=${article.id}">Редактировать</a>
                    <c:if test="${article.active eq true}">
                        Опубликовано
                    </c:if>
                    <c:if test="${article.active eq false}">
                        Скрыто
                    </c:if>
                </li>
            </c:forEach>
        </ul>
        <h3>Редактирование статьи</h3>
        <form action="controller?command=doEditArticle" method="POST">
            <input type="hidden" name="id" value="${editArticle.id}">
            <input type="text" name="title" value="${editArticle.title}">
            <br>
            <textarea name="text">${editArticle.text}</textarea>
            <br>
            <c:if test="${editArticle.active eq true && (role eq 'EDITOR' || role eq 'ADMIN')}">
                <input type="checkbox" checked name="active"> Опубликовано
            </c:if>
            <c:if test="${editArticle.active eq false}">
                <input type="checkbox"  name="active"> Скрыто
            </c:if>
            <br>
            <input type="submit" value="Изменить" />
        </form>
    </body>
</html>
