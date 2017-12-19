<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form action="controller?command=addComment" method="POST">
    <div class="comment-text">
        <input type="hidden" name="articleId" value="${article.id}">
        <textarea name="comment"></textarea><br>
        <input type="submit" value="Добавить комментарий">
    </div>
    
</form>