/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import command.login.CheckLoginCommand;
import entity.Article;
import entity.User;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
public class RegistrationNewUser {
    
    private ArticleFacade articleFacade;
    private UserFacade userFacade;
    private String login;
    private String password1;
    private String password2;
    private String email;
    
    public RegistrationNewUser(String login,String password1,String password2,String email) {
        initContext();
        this.login=login;
        this.password1=password1;
        this.password2=password2;
        this.email=email;
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
        User regUser = new User(login,encriptPassword,salts,email);
        try {
            userFacade.create(regUser);
            return true;
        } catch (Exception e) {
            return false;
        }
    }        
    
    
}
