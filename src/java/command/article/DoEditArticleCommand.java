/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.article;

import classes.AddArticle;
import classes.EditArticle;
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
public class DoEditArticleCommand implements ActionCommand {
    
    private ArticleFacade articleFacade;
    
    public DoEditArticleCommand() {
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
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        HttpSession session = request.getSession(false);
        User regUser = (User) session.getAttribute("regUser");
        EditArticle editArticle = new EditArticle(id,title,text,regUser);
        if(editArticle.recordToBase()){
            request.setAttribute("info", "Статья изменена");
        }else{
            request.setAttribute("info", "Статью изменить не удалось!");
        }
        int[] range = {0,10};
        request.setAttribute("articles", articleFacade.findRange(range));
        String page = ConfigurationManager.getProperty("path.page.newArticle");
        return page;
    }
    
    
}
