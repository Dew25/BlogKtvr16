/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.article;

import classes.article.AddArticle;
import interfaces.ActionCommand;
import entity.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import resource.ConfigurationManager;
import session.ArticleFacade;

/**
 *
 * @author jvm
 */
public class AddArticleCommand implements ActionCommand {
    
    private ArticleFacade articleFacade;
    
    public AddArticleCommand() {
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
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        
        HttpSession session = request.getSession(false);
        if(session == null){
            session = request.getSession(true);
            session.setAttribute("path", "path.page.newArticle");
           String page = ConfigurationManager.getProperty("path.page.login");
           return page;
        }
        User regUser = (User) session.getAttribute("regUser");
        AddArticle addArticle = new AddArticle(title,text,regUser);
        if(addArticle.recordToBase()){
            request.setAttribute("info", "Статья успешно добавлена");
        }else{
            request.setAttribute("info", "Статью добавить не удалось!");
        }
        int[] range = {0,10};
        request.setAttribute("articles", articleFacade.findRange(range));
        
        String page = ConfigurationManager.getProperty("path.page.newArticle");
        return page;
    }
    
    
}
