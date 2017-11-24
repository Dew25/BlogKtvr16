/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.comment;

import classes.comment.DeleteComment;
import interfaces.ActionCommand;
import entity.User;
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
public class DeleteCommentCommand implements ActionCommand {
    
    private ArticleFacade articleFacade;
    private CommentFacade commentFacade;
    
    public DeleteCommentCommand() {
        Context context; 
        try {
            context = new InitialContext();
            this.articleFacade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
            this.commentFacade = (CommentFacade) context.lookup("java:module/CommentFacade");
            
        } catch (NamingException ex) {
            Logger.getLogger(DeleteCommentCommand.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }

    @Override
    public String execute(HttpServletRequest request) {
        String commentId = request.getParameter("commentId");
        String articleId = request.getParameter("articleId");
        HttpSession session = request.getSession(false);
        if(session == null){
           session = request.getSession(true);
           session.setAttribute("path","path.page.index");
           String page = ConfigurationManager.getProperty("path.page.login");
           return page;
        }
        
        User regUser = (User) session.getAttribute("regUser");
        DeleteComment deleteComment = new DeleteComment();
        if(deleteComment.recordToBase(commentId,articleId)){
            request.setAttribute("info", "Комментарий удален");
        }else{
            request.setAttribute("info", "Комментарий удалить не удалось!");
        }
        
        request.setAttribute("article", articleFacade.find(new Long(articleId)));
        request.setAttribute("comments", commentFacade.findByArticle(new Long(articleId)));
                
        String page = ConfigurationManager.getProperty("path.page.article");
        return page;
    }
    
    
}
