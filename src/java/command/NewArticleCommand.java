/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import resource.ConfigurationManager;
import session.UserFacade;

/**
 *
 * @author jvm
 */
public class NewArticleCommand implements ActionCommand {
    
    private UserFacade userFacade;
    
    public NewArticleCommand() {
        Context context; 
        try {
            context = new InitialContext();
            this.userFacade = (UserFacade) context.lookup("java:module/UserFacade");
        } catch (NamingException ex) {
            Logger.getLogger(NewArticleCommand.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }
    
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("users", userFacade.findAll());
        String page = ConfigurationManager.getProperty("path.page.newArticle");
        return page;
    }
    
    
}
