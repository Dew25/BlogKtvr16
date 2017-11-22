/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import interfaces.ActionCommand;
import classes.article.AddArticle;
import classes.user.RoleUser;
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
public class EmptyCommand implements ActionCommand {
    private ArticleFacade articleFacade;
    public EmptyCommand() {
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
        HttpSession session = request.getSession(false);
        if(session != null){
            User regUser = (User) session.getAttribute("regUser");
            RoleUser ru = new RoleUser();
            String role = ru.getRole(regUser);
            request.setAttribute("role", role);
        }
        int[] range = {0,10};
        request.setAttribute("articles", articleFacade.findActiveArticleAll(range));
        String page = ConfigurationManager.getProperty("path.page.index");
        return page;
    }
    
    
}
