/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.user;

import classes.DeleteRole;
import classes.RoleContains;
import classes.SetRole;
import entity.User;
import interfaces.ActionCommand;
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
public class SetRoleCommand implements ActionCommand  {
    private UserFacade userFacade;
    public SetRoleCommand() {
        Context context;
        try {
            context = new InitialContext();
            this.userFacade = (UserFacade) context.lookup("java:module/UserFacade");
        } catch (NamingException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }

    @Override
    public String execute(HttpServletRequest request) {
        String selectRole = request.getParameter("selectRole");
        String selectUser = request.getParameter("selectUser");
        HttpSession session = request.getSession(false);
        if(session == null){
            String page = ConfigurationManager.getProperty("path.page.login");
            return page;
        }
        
        User regUser = (User) session.getAttribute("regUser");
        if(regUser == null){
            String page = ConfigurationManager.getProperty("path.page.login");
            return page;
        }
        RoleContains rc = new RoleContains();
        if(!rc.contains("ADMIN", regUser)){
            String page = ConfigurationManager.getProperty("path.page.login");
            return page;
        }
        if(selectRole != null && "delete".equals(selectRole)){
            DeleteRole deleteRole = new DeleteRole(selectRole, selectUser);
        }else{
            SetRole setRole = new SetRole(selectRole, selectUser);
        }
        
        
        String page = ConfigurationManager.getProperty("path.page.admin");
        return page;
    }
    
}
