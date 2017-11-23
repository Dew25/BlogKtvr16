/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.comment;

import interfaces.BaseRecord;
import entity.Comment;
import entity.User;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import session.ArticleFacade;
import session.CommentFacade;

/**
 *
 * @author Melnikov
 */
public class EditComment implements BaseRecord {
    
    private ArticleFacade articleFacade;
    private CommentFacade commentFacade;
    private String articleId;
    private String commentId;
    private String newComment;
    private User user;
       
    
    public EditComment() {
        initContext();
    }
    
    private void initContext(){
        Context context; 
        try {
            context = new InitialContext();
            this.articleFacade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
            this.commentFacade = (CommentFacade) context.lookup("java:module/CommentFacade");
        } catch (NamingException ex) {
            Logger.getLogger(EditComment.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }
    @Override
    public boolean recordToBase(){
        
        Comment comment = commentFacade.find(new Long(commentId));
        
        if(comment == null){
            Logger.getLogger(EditComment.class.getName()).log(Level.INFO, "комментарий с id="+commentId+" не найдена");
            return false;
        }
        
        Calendar c = new GregorianCalendar();
        comment.setEditDate(c.getTime());
        comment.setText(newComment);
        try {
            commentFacade.edit(comment);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean recordToBase(String articleId,String commentId, String newComment, User regUser){
        if(articleId == null || articleId.isEmpty() 
                || commentId == null || commentId.isEmpty() 
                || newComment == null || newComment.isEmpty() 
                || regUser == null){
            return false;
        }
        this.articleId = articleId;
        this.commentId = commentId;
        this.newComment = newComment;
        this.user = regUser;
        return recordToBase();
    }
    
    
}
