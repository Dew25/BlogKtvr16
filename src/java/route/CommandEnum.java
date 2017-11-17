/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package route;


import command.ActionCommand;
import command.AddArticleCommand;
import command.NewArticleCommand;
import command.login.LoginCommand;
import command.login.LogoutCommand;
import command.AddNewUserCommand;
import command.ArticleCommand;
import command.RegistrationCommand;
import command.login.CheckLoginCommand;


/**
 *
 * @author jvm
 */
public enum CommandEnum {
    LOGIN{{this.command = new LoginCommand();}},
    LOGOUT{{ this.command = new LogoutCommand();}},
    REGISTRATION{{ this.command = new RegistrationCommand();}},
    CHECKLOGIN{{ this.command = new CheckLoginCommand();}},
    ADDNEWUSER{{ this.command = new AddNewUserCommand();}},
    ARTICLE{{ this.command = new ArticleCommand();}},
    NEWARTICLE{{ this.command = new NewArticleCommand();}},
    ADDARTICLE{{ this.command = new AddArticleCommand();}},
    ;
    
    ActionCommand command;
    
    public ActionCommand getCurrentCommand(){
        return command;
    }
}
