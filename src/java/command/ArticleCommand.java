/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import command.login.CheckLoginCommand;
import entity.Article;
import entity.Message;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import resource.ConfigurationManager;
import session.ArticleFacade;
import session.MessageFacade;

/**
 *
 * @author jvm
 */
public class ArticleCommand implements ActionCommand {
    private ArticleFacade articleFacade;
    private MessageFacade messageFacade;
    public ArticleCommand() {
        Context context; 
        try {
            context = new InitialContext();
            this.articleFacade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
            this.messageFacade = (MessageFacade) context.lookup("java:module/MessageFacade");
        } catch (NamingException ex) {
            Logger.getLogger(CheckLoginCommand.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }

    @Override
    public String execute(HttpServletRequest request) {
        String articleId = request.getParameter("id");
        String page = ConfigurationManager.getProperty("path.page.index");
        
        if(articleId == null){
            return page;
        }
        Long id = new Long(articleId);
        Article article = articleFacade.find(id);
        List<Message> messages = messageFacade.findByArticle(id);
        request.setAttribute("article", article);
        request.setAttribute("messages", messages);
        page = ConfigurationManager.getProperty("path.page.article");
        return page;
    }
    
    
}
