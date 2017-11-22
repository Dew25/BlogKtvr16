/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.comment;

import interfaces.BaseRecord;
import entity.Article;
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
public class AddComment implements BaseRecord{
    
    private ArticleFacade articleFacade;
    private CommentFacade commendFacade;
    private final String text;
    private final String article;
    private final User author;
    
    public AddComment(String text,String article,User author) {
        initContext();
        this.text=text;
        this.article=article;
        this.author=author;
    }
    
    private void initContext(){
        Context context; 
        try {
            context = new InitialContext();
            this.articleFacade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
            this.commendFacade = (CommentFacade) context.lookup("java:module/CommentFacade");
            
        } catch (NamingException ex) {
            Logger.getLogger(AddComment.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }
    @Override
    public boolean recordToBase(){
        if(author == null || article==null || article.isEmpty()
                || article==null || article.isEmpty()){
            return false;
        }
        
        Calendar c = new GregorianCalendar();
        Article toArticle = articleFacade.find(new Long(this.article));
        
        Comment newComment = new Comment(toArticle, author, c.getTime(), text, Boolean.TRUE);
        try {
            commendFacade.create(newComment);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
}
