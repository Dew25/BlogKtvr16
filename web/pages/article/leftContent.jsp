<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="content-left">
    <form action="${title}" method="POST">
        <div class="article-caption">
            Заголовок статьи (не более 200 символов)<br>
            <input type="text" name="title">
        </div>
        <div class="article-text">
            Текст статьи (не более 20000 символов)<br>
           <textarea name="text"></textarea>
        </div>
        <div class="article-submit">
            <input type="submit" value="Добавить">
        </div>
    </form>
</div>
