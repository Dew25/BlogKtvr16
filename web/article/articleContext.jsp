
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="article">
    <div class="article-title-row">
        <div class="article-title-author">
            Автор статьи: <span>${article.author.login}</span>
        </div>
        <div class="article-title-date">
            Дата последнего редактирования: <span><fmt:formatDate value="${article.editDate}" pattern="dd.MM.yyyy HH:mm"/></span>

        </div>
    </div>

    <div class="article-text-row">
        <div class="article-text-row-caption">${article.title}</div>
        <div class="article-text-text">
            ${article.text}
            <c:if test="${role eq 'EDITOR' || role eq 'ADMIN' || regUser.login eq articlt.author}">
                <div class="article-edit"><a  href="controller?command=editArticle&id=${article.id}">Изменить</a></div>
            </c:if>
        </div>
    </div>
</div>
