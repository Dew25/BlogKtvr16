/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.login;

import interfaces.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import resource.ConfigurationManager;

/**
 *
 * @author Melnikov
 */
public class LoginCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
    
}
