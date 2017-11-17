/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.login;

import command.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import resource.ConfigurationManager;

/**
 *
 * @author Melnikov
 */
public class LogoutCommand  implements ActionCommand  {
   
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        
        request.setAttribute("info", "Вы вышли");
        String page = ConfigurationManager.getProperty("page.index");
        return page;
            
    }
    
}
