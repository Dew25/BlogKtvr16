/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import classes.AddArticle;
import javax.servlet.http.HttpServletRequest;
import resource.ConfigurationManager;

/**
 *
 * @author jvm
 */
public class AddArticleCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        String author = request.getParameter("author");
        AddArticle addArticle = new AddArticle(title,text,author);
        if(addArticle.recordToBase()){
            request.setAttribute("info", "Статья успешно добавлена");
        }else{
            request.setAttribute("info", "Стать добавить не удалось!");
        }
        
        String page = ConfigurationManager.getProperty("path.page.newArticle");
        return page;
    }
    
    
}
