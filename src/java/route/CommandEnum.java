/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package route;


import interfaces.ActionCommand;
import command.article.AddArticleCommand;
import command.article.NewArticleCommand;
import command.login.LoginCommand;
import command.login.LogoutCommand;
import command.user.AddNewUserCommand;
import command.article.ArticleCommand;
import command.article.DeleteArticleCommand;
import command.article.EditArticleCommand;
import command.RegistrationCommand;
import command.UploadFileCommand;
import command.article.DoEditArticleCommand;
import command.comment.AddCommentCommand;
import command.login.CheckLoginCommand;
import command.user.AdminCommand;
import command.user.SetRoleCommand;


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
    EDITARTICLE{{ this.command = new EditArticleCommand();}},
    DOEDITARTICLE{{ this.command = new DoEditArticleCommand();}},
    DELETEARTICLE{{ this.command = new DeleteArticleCommand();}},
    ADDCOMMENT{{ this.command = new AddCommentCommand();}},
    UPLOADFILE{{ this.command = new UploadFileCommand();}},
    ADMIN{{ this.command = new AdminCommand();}},
    SETROLE{{ this.command = new SetRoleCommand();}}
    ;
    
    ActionCommand command;
    
    public ActionCommand getCurrentCommand(){
        return command;
    }
}
