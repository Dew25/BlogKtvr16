/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.article;

import classes.article.DeleteArticle;
import interfaces.ActionCommand;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import resource.ConfigurationManager;
import session.ArticleFacade;

/**
 *
 * @author jvm
 */
public class DeleteArticleCommand implements ActionCommand {
    
    private ArticleFacade articleFacade;
    
    public DeleteArticleCommand() {
        Context context; 
        try {
            context = new InitialContext();
            this.articleFacade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
            
        } catch (NamingException ex) {
            Logger.getLogger(DeleteArticleCommand.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");
        
        DeleteArticle deleteArticle = new DeleteArticle(id);
        if(deleteArticle.recordToBase()){
            request.setAttribute("info", "Статья успешно удалена");
        }else{
            request.setAttribute("info", "Статью удалить не удалось!");
        }
        int[] range = {0,10};
        request.setAttribute("articles", articleFacade.findRange(range));
        String page = ConfigurationManager.getProperty("path.page.newArticle");
        return page;
    }
    
    
}
