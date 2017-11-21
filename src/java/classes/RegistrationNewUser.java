/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import interfaces.BaseRecord;
import command.login.CheckLoginCommand;
import entity.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import session.ArticleFacade;
import session.UserFacade;
import util.EncriptPass;

/**
 *
 * @author Melnikov
 */
public class RegistrationNewUser implements BaseRecord{
    
    private ArticleFacade articleFacade;
    private UserFacade userFacade;
    private final String login;
    private final String password1;
    private final String password2;
    private final String email;
    private final boolean active;
    
    public RegistrationNewUser(String login,String password1,String password2,String email,boolean active) {
        initContext();
        this.login=login;
        this.password1=password1;
        this.password2=password2;
        this.email=email;
        this.active = active;
    }
    
    private void initContext(){
        Context context; 
        try {
            context = new InitialContext();
            this.articleFacade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
            this.userFacade = (UserFacade) context.lookup("java:module/UserFacade");
        } catch (NamingException ex) {
            Logger.getLogger(CheckLoginCommand.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }
    @Override
    public boolean recordToBase(){
        if(login == null || "".equals(login)
                || password1==null || "".equals(password1)
                || password2==null || "".equals(password2)
                || email==null || "".equals(email)
                || !password1.equals(password2)){
            return false;
        }
        EncriptPass ep = new EncriptPass();
        String salts = ep.getSalts();
        ep.setEncriptPassword(password1, salts);
        String encriptPassword = ep.getEncriptPassword();
        User regUser = new User(login,encriptPassword,salts,email,active);
        try {
            userFacade.create(regUser);
            return true;
        } catch (Exception e) {
            return false;
        }
    }        
    
    
}
