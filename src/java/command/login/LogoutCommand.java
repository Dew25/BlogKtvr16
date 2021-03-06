/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.login;

import entity.Article;
import interfaces.ActionCommand;
import java.util.List;
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
 * @author Melnikov
 */
public class LogoutCommand  implements ActionCommand  {

    
    private ArticleFacade articleFacade;
    
    public LogoutCommand() {
        Context context; 
        try {
            context = new InitialContext();
            this.articleFacade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
            
        } catch (NamingException ex) {
            Logger.getLogger(LogoutCommand.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }
   
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        if(articleFacade == null){
            request.setAttribute("info", "Войти не удалось");
            String page = ConfigurationManager.getProperty("path.page.index");
            return page;
        }
        int[] range={0,10};
        List<Article> articles = articleFacade.findActiveArticleAll(range);
        request.setAttribute("info", "Вы вышли");
        request.removeAttribute("role");
        request.setAttribute("articles", articles);
        String page = ConfigurationManager.getProperty("path.page.index");
        return page;
            
    }
    
}
