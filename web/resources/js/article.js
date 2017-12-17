"use strict";
function openForEditBtn(commentId){
    let comment=document.getElementById('comment'+commentId);
    let changeBtnP=document.getElementById('changeBtn'+commentId).parentElement;
    let editBtnP = document.getElementById('editBtn'+commentId).parentElement;
    changeBtnP.style.display="none";
//    changeBtn.disabled = true;
    comment.disabled=false;
    editBtnP.style.display='block';
//    editBtn.value = "Сохранить";    
}
function changeCommentBtn(commentId){
    let comment=document.getElementById('comment'+commentId);
    let changeBtnP=document.getElementById('changeBtn'+commentId).parentElement;
    let editBtnP = document.getElementById('editBtn'+commentId).parentElement;
    let editBtn = document.getElementById('editBtn'+commentId);
    editBtnP.style.display="none";
    changeBtnP.style.display="block";
    this.form.submit();
//    changeBtn.disabled=false;
    comment.disabled = true;
    
    
  
//    editBtn.value = "Сохранено";
}

