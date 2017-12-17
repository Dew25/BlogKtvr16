<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="commentNotAdmin">
    <div class="comment-author">Автор: ${comment.author.login} 
        <span>Дата: 
            <fmt:formatDate value="${comment.editDate}" pattern="dd.MM.yyyy HH:mm"/>
        </span>
    </div>
    <div class="comment-text">
        <textarea disabled name="newComment">${comment.text}</textarea>
    </div>
</div>


