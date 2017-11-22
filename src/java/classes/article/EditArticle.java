/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.article;

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
public class EditArticle implements BaseRecord {
    
    private ArticleFacade articleFacade;
    private UserFacade userFacade;
    private final String id;
    private final String title;
    private final String text;
    private final User regUser;
    private final boolean active;
   
    
    public EditArticle(String id,String title,String text,User regUser, boolean active) {
        initContext();
        this.id=id;
        this.title=title;
        this.text=text;
        this.regUser=regUser;
        this.active = active;
    }
    
    private void initContext(){
        Context context; 
        try {
            context = new InitialContext();
            this.articleFacade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
            this.userFacade = (UserFacade) context.lookup("java:module/UserFacade");
        } catch (NamingException ex) {
            Logger.getLogger(EditArticle.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }
    @Override
    public boolean recordToBase(){
        if(id == null){
            return false;
        }
        Article article = articleFacade.find(new Long(id));
        if( article == null){
            Logger.getLogger(EditArticle.class.getName()).log(Level.INFO, "Статья с id="+id+" не найдена");
            return false;
        }
        if(!regUser.equals(article.getAuthor())){
            Logger.getLogger(EditArticle.class.getName()).log(Level.INFO, "Редактировать статью может только автор");
            return false;
        }
        Calendar c = new GregorianCalendar();
        article.setTitle(title);
        article.setText(text);
        article.setEditDate(c.getTime());
        article.setActive(active);
        try {
            articleFacade.edit(article);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
}
