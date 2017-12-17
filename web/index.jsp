<%@page contentType="text/html" pageEncoding="UTF-8"%>
        
    <h3>Добро пожаловать на страничку о программировании на Java!</h3>
    <h4>Наши статьи:</h4>
    <div>
    <c:forEach var="artcl" items="${articles}">
        <div class="article">
            <div class="article-title-row">
                <div class="article-title-author">
                    Автор статьи: <span>${artcl.author.login}</span>
                </div>
                <div class="article-title-date">
                    Дата последнего редактирования: <span><fmt:formatDate value="${artcl.editDate}" pattern="dd.MM.yyyy HH:mm"/></span>

                </div>
            </div>

            <div class="article-text-row">
                <div class="article-text-row-caption">${artcl.title}</div>
                <div class="article-text-text">
                    ${fn:substring(artcl.text,0,300)}<br> 
                    <c:if test="${role ne null}">
                        <a href="controller?command=article&id=${artcl.id}">...читать дальше</a>
                    </c:if>
                </div>
            </div>
        </div>
    </c:forEach>
    </div>
 