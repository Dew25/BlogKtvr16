/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.login;

import classes.RoleContains;
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
import session.UserFacade;
import util.EncriptPass;

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
            session=request.getSession(true);
        }
        User regUser = userFacade.findByLogin(login);
        if(regUser == null){
            request.setAttribute("info", "Неправильный логин или пароль");
            String page = ConfigurationManager.getProperty("path.page.index");
            return page;
        }
        EncriptPass encriptPass = new EncriptPass();
        encriptPass.setEncriptPassword(password, regUser.getSalts());
        password = encriptPass.getEncriptPassword();
        if(password.equals(regUser.getPassword())){
            session.setAttribute("regUser", regUser);
            RoleContains rc = new RoleContains();
            String role = rc.getRole(regUser);
            session.setAttribute("role", role);
            request.setAttribute("info", "Приветствую "+regUser.getLogin());
            String path;
            path = (String) session.getAttribute("path");
            session.removeAttribute("path");
            if(path == null){
                path = "path.page.index";
            }
            String page = ConfigurationManager.getProperty(path);
            return page;
        }  
        request.setAttribute("info", "Неправильный логин или пароль");
        String page = ConfigurationManager.getProperty("path.page.index");
        return page;
            
    }
    
}
