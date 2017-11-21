/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import entity.Role;
import entity.User;
import interfaces.BaseRecord;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import session.RoleFacade;
import session.UserFacade;

/**
 *
 * @author jvm
 */
public class DeleteRole implements BaseRecord{
    
    private UserFacade userFacade;
    private RoleFacade roleFacade;
    private String role;
    private User user;

    public DeleteRole() {
        Context context; 
        try {
            context = new InitialContext();
            this.userFacade = (UserFacade) context.lookup("java:module/UserFacade");
            this.roleFacade = (RoleFacade) context.lookup("java:module/RoleFacade");
        } catch (NamingException ex) {
            Logger.getLogger(SetRole.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }
    
    
    public DeleteRole(String role, User user) {
        this.role=role;
        this.user=user;
        Context context; 
        try {
            context = new InitialContext();
            this.userFacade = (UserFacade) context.lookup("java:module/UserFacade");
            this.roleFacade = (RoleFacade) context.lookup("java:module/RoleFacade");
        } catch (NamingException ex) {
            Logger.getLogger(DeleteRole.class.getName()).log(Level.INFO, "Не удалось найти сессионый бин", ex);
        }
    }
    
    public boolean recordToBase(String role, String userId){
        if(role == null || role.isEmpty() || userId == null || userId.isEmpty()){
            Logger.getLogger(DeleteRole.class.getName()).log(Level.INFO, "Не корректный ввод роли или пользователя");
            return false;
        }
        this.role=role;
        this.user=userFacade.find(new Long(userId));
        return recordToBase();
    }
    
    public boolean recordToBase(String role, User user){
        this.role=role;
        this.user=user;
        return recordToBase();
    }
    
    @Override
    public boolean recordToBase() {
        if(role == null || role.isEmpty() || user == null){ 
            Logger.getLogger(DeleteRole.class.getName()).log(Level.SEVERE, "Не корректный ввод роли или пользователя");
            return false;
        }
        RoleUser ru = new RoleUser();
        if(!ru.contains(role, user)){ 
            Logger.getLogger(DeleteRole.class.getName()).log(Level.SEVERE, "У пользователя нет удаляемой роли");
            return false;
        }
        List<Role> roles = roleFacade.findUserRoles(user);
        for (Role deleteRole : roles) {
            roleFacade.remove(deleteRole);
        }
        return true;
    }
    
    
}
