/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import interfaces.BaseRecord;
import entity.Article;
import entity.Comment;
import java.util.List;
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
public class DeleteArticle implements BaseRecord {
    
    private ArticleFacade articleFacade;
    private CommentFacade commentFacade;
    private final String id;
    
    public DeleteArticle(String id) {
        initContext();
        this.id=id;
    }
    
    private void initContext(){
        Context context; 
        try {
            context = new InitialContext();
            this.articleFacade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
            this.commentFacade = (CommentFacade) context.lookup("java:module/CommentFacade");
        } catch (NamingException ex) {
            Logger.getLogger(DeleteArticle.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }
    @Override
    public boolean recordToBase(){
        if(id == null | "".equals(id)){
            return false;
        }
        Article deleteArticle = articleFacade.find(new Long(id));
        List<Comment> messages = commentFacade.findByArticle(new Long(id));
        try {
            if(messages != null){
                for (int i = 0; i < messages.size(); i++) {
                    Comment comment = messages.get(i);
                    commentFacade.remove(comment);
                }
            }
            articleFacade.remove(deleteArticle);
            return true;
        } catch (Exception e) {
            Logger.getLogger(DeleteArticle.class.getName()).log(Level.SEVERE, "Не удалось удалить статью или коментарий", e);
            return false;
        }
    }
    
    
}
