<%@page contentType="text/html" pageEncoding="UTF-8"%>
        
        <h4>Добавить новую статью</h4>
        <div class="content">
            <div class="content-left">
                <form action="controller?command=addArticle" method="POST">
                    <div class="article-caption">
                        <div class="title-caption">
                            
                        </div>
                        <div class="article-title">
                            Заголовок статьи (не более 200 символов)<br>
                            <input type="text" name="title">
                        </div>
                    </div>
                    <div class="article-text">
                        <div class="text-caption">
                            
                        </div>
                        <div class="article-text-text">
                            Текст статьи (не более 20000 символов)<br>
                           <textarea name="text"></textarea>
                        </div>
                    </div>
                    <div class="article-submit">
                        <input type="submit" value="Добавить">
                    </div>
                </form>
            </div>
            <div class="content-right">
                <a href="controller?command=uploadFile">Загрузить файл на сервер</a><br>
                <ul>
                    <c:forEach var="imageName" items="${imagesNameList}">
                        <li>${imageName}</li>
                    </c:forEach>
                </ul>
                <div class="content-right">
                    <a href="http://radikal.ru/" target="blank">Загрузить изображение для статьи (radikal.ru)</a><br>
                    <br>
                    <a href="http://da.am/" target="blank">Загрузить изображение для статьи (da.am)</a><br>
                </div>
            </div>
        </div>
        <h4>Список статей:</h4>        
        <ul>
            <c:forEach var="article" items="${articles}">
                <li>  
                    <a href="controller?command=article&id=${article.id}">${article.title}</a>
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

