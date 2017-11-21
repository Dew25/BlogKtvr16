/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import entity.Role;
import entity.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import session.RoleFacade;
import session.UserFacade;

/**
 *
 * @author jvm
 */
public class RoleUser {
    private HttpSession session;
    private UserFacade userFacade;
    private RoleFacade roleFacade;
    private String role;
    private User user;

    public RoleUser() {
       Context context; 
        try {
            context = new InitialContext();
            this.userFacade = (UserFacade) context.lookup("java:module/UserFacade");
            this.roleFacade = (RoleFacade) context.lookup("java:module/RoleFacade");
        } catch (NamingException ex) {
            Logger.getLogger(RoleUser.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        } 
    }
    
    public String getRole(User user){
        if(user == null){ return null;}
        for(RoleEnum role : RoleEnum.values()){
            if(this.contains(role.toString(), user)){
               return role.toString();
            }
        }
        return null;
    }
    
    public String getRole(String userId){
        if(userId == null){return null;}
        if("".equals(userId)){return null;}
        try {
            user = userFacade.find(new Long(userId));
            return getRole(user);
        } catch (Exception e) {
            Logger.getLogger(RoleUser.class.getName()).log(Level.INFO, "Не удалось найти роль", e);
            return null;
        }
    }
    
      
    public boolean contains() {
        List<Role> roles = roleFacade.findUserRoles(user);
        for (int i = 0; i < roles.size(); i++) {
            Role get = roles.get(i);
            if (role.equals(get.getRole())) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(String role, User user) {
        this.role = role;
        this.user = user;
        return contains();
    }
    
}