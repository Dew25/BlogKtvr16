/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import entity.User;
import interfaces.BaseRecord;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import session.UserFacade;

/**
 *
 * @author jvm
 */
public class SetRole implements BaseRecord{
    
    private UserFacade userFacade;
    private String role;
    private User user;
    
    public SetRole(String role, User user) {
        this.role=role;
        this.user=user;
        Context context; 
        try {
            context = new InitialContext();
            this.userFacade = (UserFacade) context.lookup("java:module/UserFacade");
            
        } catch (NamingException ex) {
            Logger.getLogger(SetRole.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }
    
    @Override
    public boolean recordToBase() {
        
        return false;
    }
    
}
