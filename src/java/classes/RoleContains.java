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
import session.RoleFacade;

/**
 *
 * @author jvm
 */
public class RoleContains {

    private RoleFacade roleFacade;
    private String role;
    private User regUser;

    public RoleContains() {
    }

    public RoleContains(String role, User regUser) {
        this.role = role;
        this.regUser = regUser;
    }

    public boolean contains() {
        Context context;
        try {
            context = new InitialContext();
            this.roleFacade = (RoleFacade) context.lookup("java:module/RoleFacade");
        } catch (NamingException ex) {
            Logger.getLogger(RoleContains.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
        List<Role> roles = roleFacade.findUserRoles(regUser);
        for (int i = 0; i < roles.size(); i++) {
            Role get = roles.get(i);
            if (role.equals(get.getRole())) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(String role, User regUser) {
        this.role = role;
        this.regUser = regUser;
        return contains();
    }

    public String getRole(User user) {
        String userRole = null;
//        List<Role> userRoles = roleFacade.findUserRoles(user);
//        for(RoleEnum role : RoleEnum.values()){
//            if(userRoles.contains(role.toString())){
//                return userRole = role.toString();
//            }
//        }
        for(RoleEnum role : RoleEnum.values()){
            if(this.contains(role.toString(), user)){
               return userRole=role.toString();
            }
        }
        return userRole;
    }
}
