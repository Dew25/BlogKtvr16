/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.article;

import classes.AddArticle;
import interfaces.ActionCommand;
import entity.Article;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import resource.ConfigurationManager;
import session.ArticleFacade;

/**
 *
 * @author jvm
 */
public class EditArticleCommand implements ActionCommand {
    
    private ArticleFacade articleFacade;
    
    public EditArticleCommand() {
        Context context; 
        try {
            context = new InitialContext();
            this.articleFacade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
            
        } catch (NamingException ex) {
            Logger.getLogger(AddArticle.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");
        if(id == null){
            request.setAttribute("info", "Статью изменить не удалось!");
            String page = ConfigurationManager.getProperty("path.page.newArticle");
            return page;
        }
        try {
            Article editArticle = articleFacade.find(new Long(id));
            int[] range = {0,10};
            request.setAttribute("articles", articleFacade.findRange(range));
            request.setAttribute("editArticle", editArticle);
            String page = ConfigurationManager.getProperty("path.page.editArticle");
            return page;
        } catch (Exception e) {
            request.setAttribute("info", "Статью изменить не удалось!");
            String page = ConfigurationManager.getProperty("path.page.newArticle");
            return page;
        }
        
    }
    
    
}
