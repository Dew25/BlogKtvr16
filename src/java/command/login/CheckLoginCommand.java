/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.login;

import command.ActionCommand;
import entity.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import resource.ConfigurationManager;
import session.UserFacade;

/**
 *
 * @author Melnikov
 */
public class CheckLoginCommand  implements ActionCommand  {
    private UserFacade userFacade;
    public CheckLoginCommand() {
        Context context; 
        try {
            context = new InitialContext();
            this.userFacade = (UserFacade) context.lookup("java:module/UserFacade");
        } catch (NamingException ex) {
            Logger.getLogger(CheckLoginCommand.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        
        
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "Неправильный логин или пароль");
            String page = ConfigurationManager.getProperty("path.page.index");
            return page;
        }
        User regUser = userFacade.findByLogin(login);
        if(regUser == null){
            request.setAttribute("info", "Неправильный логин или пароль");
            String page = ConfigurationManager.getProperty("path.page.index");
            return page;
        }
        
        if(password.equals(regUser.getPassword())){
            session.setAttribute("regUser", regUser);
            request.setAttribute("info", "Приветствую "+regUser.getLogin());
            request.setAttribute("enterUser", "true");
            String page = ConfigurationManager.getProperty("path.page.index");
            return page;
        }  
        request.setAttribute("info", "Неправильный логин или пароль");
        String page = ConfigurationManager.getProperty("path.page.index");
        return page;
            
    }
    
}
