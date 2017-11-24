/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.user;

import classes.article.AddArticle;
import classes.RoleUser;
import classes.RoleEnum;
import entity.User;
import interfaces.ActionCommand;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 * @author Melnikov
 */
public class AdminCommand implements ActionCommand  {
    
    private UserFacade userFacade;
    
    
    public AdminCommand() {
        Context context; 
        try {
            context = new InitialContext();
            this.userFacade = (UserFacade) context.lookup("java:module/UserFacade");
            
        } catch (NamingException ex) {
            Logger.getLogger(AddArticle.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }

    @Override
    public String execute(HttpServletRequest request) {
        
        RoleUser ru = new RoleUser();
        String role = ru.getRole(request);
        if(role == null){
            String page = ConfigurationManager.getProperty("path.page.login");
            return page;
        }
        if(!"ADMIN".equals(role)){
            String page = ConfigurationManager.getProperty("path.page.login");
            return page;
        }
        List<User> users = userFacade.findAll();
        
        Map<User,String>mapUsers=new HashMap<>();
        String userRole;
        for (User user : users) {
            userRole = ru.getRole(user);
            mapUsers.put(user, userRole);
        }
        
        request.setAttribute("mapUsers", mapUsers);
        request.setAttribute("roles", RoleEnum.values());
        
        String page = ConfigurationManager.getProperty("path.page.admin");
        return page;
    }
    
}
