/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import command.login.CheckLoginCommand;
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
public class AddArticle {
    
    private ArticleFacade articleFacade;
    private UserFacade userFacade;
    private final String title;
    private final String text;
    private final String author;
    
    public AddArticle(String title,String text,String author) {
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
    public boolean recordToBase(){
        if(author == null | "".equals(author)
                | text==null | "".equals(text)
                | title==null | "".equals(title)){
            return false;
        }
        User authorObj = userFacade.find(new Long(author));
        Calendar c = new GregorianCalendar();
        
        Article newArticle = new Article(title, text, authorObj,c.getTime());
        try {
            articleFacade.create(newArticle);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
}
