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
        <link rel="stylesheet" href="resources/css/newArticle.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Список статей:</h2>        
        <ul>
            <c:forEach var="article" items="${articles}">
                <li>  
                    <a href="controller?command=article&id=${article.id}">${article.title}</a>
                    <a href="controller?command=deleteArticle&id=${article.id}">x</a>
                    <a href="controller?command=editArticle&id=${article.id}">Редактировать</a>
                </li>
            </c:forEach>
        </ul>
        <h3>Новая статья!</h3>
        <div class="content">
            <div class="content-left">
                <form action="controller?command=addArticle" method="POST">
                    <input type="text" name="title"><br>
                    <textarea name="text"></textarea><br>
                    <input type="submit" value="Добавить">
                </form>
            </div>
            <div class="content-right">
                <a href="controller?command=uploadFile">Загрузить новый файл</a><br>
                <ul>
                    <c:forEach var="imageName" items="${imagesNameList}">
                        <li>${imageName}</li>
                    </c:forEach>
                </ul>
                
            </div>
        
        </div>
    </body>
</html>
