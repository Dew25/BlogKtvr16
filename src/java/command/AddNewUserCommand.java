/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import classes.RegistrationNewUser;
import javax.servlet.http.HttpServletRequest;
import resource.ConfigurationManager;

/**
 *
 * @author Melnikov
 */
public class AddNewUserCommand implements ActionCommand  {

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2"); 
        String email = request.getParameter("email");
        RegistrationNewUser registrationNewUser = new RegistrationNewUser(login,password1,password2,email);
        if(registrationNewUser.recordToBase()){
            request.setAttribute("info", "Регистрация успешна!");
        }else{
            request.setAttribute("info", "Регистрация не удалось!");
        }
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
    
}
