/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

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
    private String userId;
    
    public SetRole(String role, String userId) {
        this.role=role;
        this.userId=userId;
        Context context; 
        try {
            context = new InitialContext();
            this.userFacade = (UserFacade) context.lookup("java:module/UserFacade");
            this.roleFacade = (RoleFacade) context.lookup("java:module/RoleFacade");
            
        } catch (NamingException ex) {
            Logger.getLogger(SetRole.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }
    
    @Override
    public boolean recordToBase() {
        if(role == null || role.isEmpty() || userId == null || userId.isEmpty()){
            return false;
        }
        User user = userFacade.find(new Long(userId));
        RoleContains rc = new RoleContains();
        if(rc.contains(role, user)){
            return false;
        }
        
        Role newRole=new Role();
        newRole.setUser(user);
        try {
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
        } catch (Exception e) {
            Logger.getLogger(SetRole.class.getName()).log(Level.INFO, "Не удалось добавить роль", e);
            return false;
        }
        
        
    }
    
}
