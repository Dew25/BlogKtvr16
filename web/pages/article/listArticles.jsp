<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <div class="caption-list-articles">Список статей:</div>        
        <table class="table-articles">
            <c:forEach var="article" items="${articles}">
                <tr>
                    <td class="table-articles-td-title"><a href="controller?command=article&id=${article.id}">${article.title}</a></td>
                    <td class="table-articles-td-icons">
                        <a href="controller?command=deleteArticle&id=${article.id}">
                            <img width="15px" height="15px" src="resources/files/icons/delete.png">
                        </a>
                        <a href="controller?command=editArticle&id=${article.id}">
                            <img width="15px" height="15px" src="resources/files/icons/edit.ico">
                        </a>
                        <c:if test="${article.active eq true}">
                            <img width="15px" height="15px" src="resources/files/icons/publish.jpg">
                        </c:if>
                        <c:if test="${article.active eq false}">
                            <img width="15px" height="15px" src="resources/files/icons/nopublish.jpg">
                        </c:if>    
                    </td>
                    
                </tr>
            </c:forEach>
        </table>
         
    