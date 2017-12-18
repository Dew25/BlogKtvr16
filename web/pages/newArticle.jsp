<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <c:set var="title" value="controller?command=addArticle"/>
        <h4>Добавить новую статью</h4>
        <div class="content">
            <%@include file="article/leftContent.jsp" %>
            <%@include file="article/rightContent.jsp" %>
        </div>
        <div class="list-articles">
            <%@include file="article/listArticles.jsp" %>
        </div>
