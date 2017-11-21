/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.article;

import classes.FilesList;
import interfaces.ActionCommand;
import entity.Article;
import entity.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import resource.ConfigurationManager;
import session.ArticleFacade;
import session.UserFacade;

/**
 *
 * @author jvm
 */
public class NewArticleCommand implements ActionCommand {
    
    private UserFacade userFacade;
    private ArticleFacade articleFacade;
    
    public NewArticleCommand() {
        Context context; 
        try {
            context = new InitialContext();
            this.userFacade = (UserFacade) context.lookup("java:module/UserFacade");
            this.articleFacade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
        } catch (NamingException ex) {
            Logger.getLogger(NewArticleCommand.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }
    
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null){
            session = request.getSession(true);
            session.setAttribute("path","path.page.newArticle");
            String page = ConfigurationManager.getProperty("path.page.login");
            return page;
        }
        User regUser = (User) session.getAttribute("regUser");
        if(regUser != null){
            int[] range = {0,10};
            List<Article> articles = articleFacade.findRange(range);
            FilesList imagesList = new FilesList();
            List<String> imagesNameList = imagesList.listFileNames(request.getServletContext().getRealPath(""));
            request.setAttribute("imagesNameList", imagesNameList);
            request.setAttribute("articles", articles);
            String page = ConfigurationManager.getProperty("path.page.newArticle");
            return page;
        }else{
            session.setAttribute("path","path.page.newArticle");
            String page = ConfigurationManager.getProperty("path.page.login");
            return page;
        }
        
    }
    
    
}
