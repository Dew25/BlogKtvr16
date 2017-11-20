/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.article;

import classes.RoleContains;
import interfaces.ActionCommand;
import command.login.CheckLoginCommand;
import controller.Controller;
import entity.Article;
import entity.Comment;
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
import session.CommentFacade;

/**
 *
 * @author jvm
 */
public class ArticleCommand implements ActionCommand {
    private ArticleFacade articleFacade;
    private CommentFacade commentFacade;
    public ArticleCommand() {
        Context context; 
        try {
            context = new InitialContext();
            this.articleFacade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
            this.commentFacade = (CommentFacade) context.lookup("java:module/CommentFacade");
        } catch (NamingException ex) {
            Logger.getLogger(CheckLoginCommand.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null){
            session=request.getSession(true);
            String articleId = request.getParameter("id");
            session.setAttribute("id",articleId);
            session.setAttribute("path","path.page.article");
            return ConfigurationManager.getProperty("path.page.login");
        }
        String articleId=null;
        String id = (String) session.getAttribute("id");
        if(id != null){
            articleId = id;
        }else{
            articleId=request.getParameter("id");
        }
        String page = ConfigurationManager.getProperty("path.page.index");
        
        User regUser = (User) session.getAttribute("regUser");
        if(regUser == null){
            session.setAttribute("path","path.page.article");
            return page = ConfigurationManager.getProperty("path.page.login");
        }
        RoleContains rc = new RoleContains();
        
        if(!rc.contains("USER", regUser)){
            session.setAttribute("path","path.page.article");
            return page = ConfigurationManager.getProperty("path.page.login");
        }
        
        if(articleId == null){
            return page;
        }
        
        Article article = articleFacade.find(new Long(articleId));
        List<Comment> comments = commentFacade.findByArticle(article.getId());
        request.setAttribute("article", article);
        request.setAttribute("comments", comments);
        page = ConfigurationManager.getProperty("path.page.article");
        return page;
    }
    
    
}