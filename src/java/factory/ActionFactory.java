/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import command.ActionCommand;
import command.EmptyCommand;
import command.NewArticleCommand;
import java.util.logging.Level;
import java.util.logging.Logger;
import route.CommandEnum;
import javax.servlet.http.HttpServletRequest;
import resource.MessageManager;

/**
 *
 * @author jvm
 */
public class ActionFactory {
    public ActionCommand defineCommand(String action){
        ActionCommand current = new EmptyCommand();
        //String action = request.getParameter("command");
        if(action == null || action.isEmpty()){
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
            
        } catch (IllegalArgumentException e) {
            Logger.getLogger(NewArticleCommand.class.getName()).log(Level.SEVERE, "Неверный путь", e);
        }
        return current;
    }
}
