/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import interfaces.ActionCommand;
import controller.Controller;
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
public class UploadFileCommand implements ActionCommand {
    private ArticleFacade articleFacade;
    public UploadFileCommand() {
        Context context; 
        try {
            context = new InitialContext();
            this.articleFacade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
            
        } catch (NamingException ex) {
            Logger.getLogger(UploadFileCommand.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User regUser = null;
        if(session != null){
            String role = (String) session.getAttribute("role");
            request.setAttribute("role", role);
            regUser = (User) session.getAttribute("regUser");
        }
        if(regUser == null){
           Controller.redirectPath="path.page.newArticle";
           String page = ConfigurationManager.getProperty("path.page.login");
           return page;
        }
        
        String page = ConfigurationManager.getProperty("path.page.uploadFile");
        return page;
    }
    
    
}
