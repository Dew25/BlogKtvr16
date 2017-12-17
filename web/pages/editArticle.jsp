
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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

