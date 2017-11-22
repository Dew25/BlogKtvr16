/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.user;

import entity.Role;
import entity.User;
import interfaces.BaseRecord;
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
public class SetRole implements BaseRecord{
    
    private UserFacade userFacade;
    private RoleFacade roleFacade;
    private String role;
    private User user;

    public SetRole() {
        Context context; 
        try {
            context = new InitialContext();
            this.userFacade = (UserFacade) context.lookup("java:module/UserFacade");
            this.roleFacade = (RoleFacade) context.lookup("java:module/RoleFacade");
        } catch (NamingException ex) {
            Logger.getLogger(SetRole.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }
    

    
    public SetRole(String role, String userId) {
        this.role=role;
        
        Context context; 
        try {
            context = new InitialContext();
            this.userFacade = (UserFacade) context.lookup("java:module/UserFacade");
            this.roleFacade = (RoleFacade) context.lookup("java:module/RoleFacade");
            this.user=userFacade.find(new Long(userId));
        } catch (NamingException ex) {
            Logger.getLogger(SetRole.class.getName()).log(Level.INFO, "Не удалось найти сессионый бин", ex);
        }
    }
    
    public boolean recordToBase(String role, String userId){
        if(role == null || role.isEmpty() || userId==null || userId.isEmpty()){
             Logger.getLogger(SetRole.class.getName()).log(Level.INFO, "Некорректный ввод роли или пользователя");
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
        if(role == null || role.isEmpty() || user == null){ return false;}
        RoleUser ru = new RoleUser();
        if(ru.contains(role, user)){ return false;}
        
        Role newRole = new Role();
        newRole.setUser(user);
        switch (role) {
            case "ADMIN":
                newRole.setRole("ADMIN");
                roleFacade.create(newRole);
                newRole.setRole("EDITOR");
                roleFacade.create(newRole);
                newRole.setRole("USER");
                roleFacade.create(newRole);
                break;
            case "EDITOR":
                newRole.setRole("EDITOR");
                roleFacade.create(newRole);
                newRole.setRole("USER");
                roleFacade.create(newRole);
                break;
            case "USER":
                newRole.setRole("USER");
                roleFacade.create(newRole);
                break;
            
        }
        return true;
    }
    
    
}
