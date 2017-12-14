/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.user;

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
public class ActiveUser implements BaseRecord{
    
    private UserFacade userFacade;
   
 
    private User user;

    public ActiveUser() {
        Context context; 
        try {
            context = new InitialContext();
            this.userFacade = (UserFacade) context.lookup("java:module/UserFacade");
          
        } catch (NamingException ex) {
            Logger.getLogger(SetRole.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }
    
    
    public ActiveUser(User user) {
        
        this.user=user;

        Context context; 
        try {
            context = new InitialContext();
            this.userFacade = (UserFacade) context.lookup("java:module/UserFacade");
            

        } catch (NamingException ex) {
            Logger.getLogger(ActiveUser.class.getName()).log(Level.INFO, "Не удалось найти сессионый бин", ex);
        }
    }
    
    public boolean recordToBase(String userId){
        if(userId == null || userId.isEmpty()){
            Logger.getLogger(ActiveUser.class.getName()).log(Level.INFO, "Не корректный ввод пользователя");
            return false;
        }

        this.user=userFacade.find(new Long(userId));
        return recordToBase();
    }
    
    public boolean recordToBase(User user){
        this.user=user;
        return recordToBase();
    }
    
    @Override
    public boolean recordToBase() {
        if( user == null){ 
            Logger.getLogger(ActiveUser.class.getName()).log(Level.INFO, "Не корректный ввод пользователя");
            return false;
        }
        user.setActive(true);
        try {
            userFacade.edit(user);
            return true;
        } catch (Exception e) {
           Logger.getLogger(ActiveUser.class.getName()).log(Level.INFO, "Неудалось изменить статус");
           return false; 
        }
        
        
    }
    
    

}
