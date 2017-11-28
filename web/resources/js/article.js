"use strict";
function openForEditBtn(commentId){
    let comment=document.getElementById('comment'+commentId);
    let changeBtn=document.getElementById('changeBtn'+commentId);
    let editBtn = document.getElementById('editBtn'+commentId);
    changeBtn.disabled = true;
    comment.disabled = false;
    editBtn.value = "Сохранить";    
}
function changeCommentBtn (commentId){
    let comment=document.getElementById('comment'+commentId);
    let changeBtn=document.getElementById('changeBtn'+commentId);
    let editBtn = document.getElementById('editBtn'+commentId);
    changeBtn.disabled=false;
    comment.disabled = true;
    editBtn.value = "Сохранено";
}

