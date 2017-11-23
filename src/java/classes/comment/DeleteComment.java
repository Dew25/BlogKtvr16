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
public class DeleteComment implements BaseRecord {
    
    private ArticleFacade articleFacade;
    private CommentFacade commentFacade;
    private String articleId;
    private String commentId;

       
    
    public DeleteComment() {
        initContext();
    }
    
    private void initContext(){
        Context context; 
        try {
            context = new InitialContext();
            this.articleFacade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
            this.commentFacade = (CommentFacade) context.lookup("java:module/CommentFacade");
        } catch (NamingException ex) {
            Logger.getLogger(DeleteComment.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }
    @Override
    public boolean recordToBase(){
        
        Comment comment = commentFacade.find(new Long(commentId));
        
        if(comment == null){
            Logger.getLogger(DeleteComment.class.getName()).log(Level.INFO, "комментарий с id="+commentId+" не найдена");
            return false;
        }
        
        try {
            commentFacade.remove(comment);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean recordToBase(String articleId,String commentId){
        if(articleId == null || articleId.isEmpty() 
                || commentId == null || commentId.isEmpty()){
            return false;
        }
        this.articleId = articleId;
        this.commentId = commentId;
        return recordToBase();
    }
    
    
}
