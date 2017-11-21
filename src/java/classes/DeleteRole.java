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
public class DeleteRole implements BaseRecord{
    
    private UserFacade userFacade;
    private RoleFacade roleFacade;
    private String role;
    private String userId;
    
    public DeleteRole(String role, String userId) {
        this.role=role;
        this.userId=userId;
        Context context; 
        try {
            context = new InitialContext();
            this.userFacade = (UserFacade) context.lookup("java:module/UserFacade");
            this.roleFacade = (RoleFacade) context.lookup("java:module/RoleFacade");
            
        } catch (NamingException ex) {
            Logger.getLogger(DeleteRole.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }
    
    @Override
    public boolean recordToBase() {
        if(role == null || role.isEmpty() || userId == null || userId.isEmpty()){
            return false;
        }
        User user = userFacade.find(new Long(userId));
        RoleContains rc = new RoleContains();
        String userRole = rc.getRole(user);
                
        Role deleteRole=new Role();
        deleteRole.setUser(user);
        try {
            switch (role) {
                case "ADMIN":
                    deleteRole.setRole("ADMIN");
                    roleFacade.remove(deleteRole);
                    deleteRole.setRole("EDITOR");
                    roleFacade.remove(deleteRole);
                     deleteRole.setRole("USER");
                    roleFacade.remove(deleteRole);
                    break;
                case "EDITOR":
                    deleteRole.setRole("EDITOR");
                    roleFacade.remove(deleteRole);
                     deleteRole.setRole("USER");
                    roleFacade.remove(deleteRole);
                    break;
                case "USER":
                     deleteRole.setRole("USER");
                    roleFacade.remove(deleteRole);
                    break;
            }
            return true;
        } catch (Exception e) {
            Logger.getLogger(SetRole.class.getName()).log(Level.INFO, "Не удалось удалить роль", e);
            return false;
        }
        
        
    }
    
}
