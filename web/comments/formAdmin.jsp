<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form action="controller?command=editComment" method="POST" id="form${comment.id}">
    <input type="hidden" name="articleId" value="${article.id}">
    <input type="hidden" name="commentId" value="${comment.id}">

    <div class="comment-author">Автор: ${comment.author.login}  <span>Дата:<fmt:formatDate value="${comment.editDate}" pattern="dd.MM.yyyy HH:mm"/></span></div>
    <div class="comment-text">
        <textarea disabled name="newComment" class="comment-textarea" id="comment${comment.id}">${comment.text}</textarea><br>
    </div>
    <div class="comment-button-open">
        <input id="changeBtn${comment.id}"  
               onclick="openForEditBtn(${comment.id})" 
               type="buton"   
               class="comment-btn" 
               value="Открыть для изменения"
        />
    </div> 
    <div class="comment-button-edit">
        <input id="editBtn${comment.id}"  
               onclick="changeCommentBtn(${comment.id})" 
               type="submit"   
               class="comment-btn" 
               value="Изменить"
        />
    </div>
    <a href="controller?command=deleteComment&commentId=${comment.id}&articleId=${article.id}">Удалить</a>
</form>