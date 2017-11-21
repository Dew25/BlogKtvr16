/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import interfaces.BaseRecord;
import entity.Article;
import entity.User;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import session.ArticleFacade;
import session.UserFacade;

/**
 *
 * @author Melnikov
 */
public class AddArticle implements BaseRecord{
    
    private ArticleFacade articleFacade;
    private UserFacade userFacade;
    private final String title;
    private final String text;
    private final User author;
    
    public AddArticle(String title,String text,User author) {
        initContext();
        this.title=title;
        this.text=text;
        this.author=author;
    }
    
    private void initContext(){
        Context context; 
        try {
            context = new InitialContext();
            this.articleFacade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
            this.userFacade = (UserFacade) context.lookup("java:module/UserFacade");
        } catch (NamingException ex) {
            Logger.getLogger(AddArticle.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }
    @Override
    public boolean recordToBase(){
        if(author == null || text==null || "".equals(text)
                || title==null || "".equals(title)){
            return false;
        }
        
        Calendar c = new GregorianCalendar();
        
        Article newArticle = new Article(title, text, author,c.getTime(), true);
        try {
            articleFacade.create(newArticle);
            return true;
        } catch (Exception e) {
            Logger.getLogger(AddArticle.class.getName()).log(Level.INFO, "Не удалось создать статью");
            return false;
        }
    }
    
    
}
