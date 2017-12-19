<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="content-right">
    <a href="controller?command=uploadFile">Загрузить файл на сервер</a><br>
    <ul>
        <c:forEach var="imageName" items="${imagesNameList}">
            <li>${imageName}</li>
        </c:forEach>
    </ul>
    <div class="content-link">
        <a href="http://radikal.ru/" target="blank">Загрузить изображение для статьи (radikal.ru)</a><br>
        <br>
        <a href="http://da.am/" target="blank">Загрузить изображение для статьи (da.am)</a><br>
    </div>
</div>
