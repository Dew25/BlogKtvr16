function onEdit(commentId){
    let formComment=document.getElementById('form'+commentId);
    if(formComment.disabled===true){
        document.getElementById("form"+commentId).disabled=false;
    }else{
        document.getElementById("form"+commentId).disabled=true;
    }
}

