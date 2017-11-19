<%-- 
    Document   : index
    Created on : Nov 15, 2017, 12:06:19 PM
    Author     : Melnikov
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="controller?command=login">Войти</a><br>
        <a href="controller?command=logout">Выйти</a>
        
        <h1>Добро пожаловать на наш блог!</h1>
        <c:if test="${role == 'ADMIN'}">
             <a href="controller?command=newArticle">новая статья</a><br>  
        </c:if>
        
        Наши статьи:<br>
        
        <c:forEach var="article" items="${articles}" >
            <div class="article">
                <table class="tab-article">
                    <tr>
                        <td class="tab-article-date">${article.editDate}</td>
                        <td class="tab-article-title">${article.title}</td>
                    </tr>
                    <tr>
                        <td class="tab-article-text" colspan="2">${article.text}</td>
                    </tr>
                    <tr>
                        <td class="tab-article-author"  colspan="2">Автор: ${article.author.login}</td>
                    </tr>                       
                </table>
            </div>
        </c:forEach>
    </body>
</html>
