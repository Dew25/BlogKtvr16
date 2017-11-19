/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import command.article.DeleteArticleCommand;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import session.ArticleFacade;

/**
 *
 * @author jvm
 */
public class Paginator {
    private int range;
    private String commandName;
    private ArticleFacade articleFacade;

    public Paginator(int range, String commandName) {
        if(range < 1){
            range = 1;
        }
        this.range = range;
        Context context; 
        try {
            context = new InitialContext();
            this.articleFacade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
            
        } catch (NamingException ex) {
            Logger.getLogger(Paginator.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }
    public String getPaginator(){
       
        int n = articleFacade.count();
        StringBuilder paginator = null;
        
        if(n % 10 < 1){
            int i = 1;
            paginator.append("<div class=\"paginator\">");
            while(i <= n){
                paginator.append("<a href=\"controller?command=");
                paginator.append(commandName);
                paginator.append("&rang=");
                paginator.append(i);
                paginator.append("\">");
                paginator.append(i);
                paginator.append("</a>, ");
                i++;
            }
        }else{
            int i = range-10;
            int k=i;
            paginator.append("<div class=\"paginator\">");
            do{
                paginator.append("<a href=\"controller?command=");
                paginator.append(commandName);
                paginator.append("&rang=");
                paginator.append(k);
                paginator.append("\">");
                paginator.append(k);
                paginator.append("</a>, ");
                k = k - 10;
            }while(k % 10 < 10);

            while(i <= n){
                paginator.append("<a href=\"controller?command=");
                paginator.append(commandName);
                paginator.append("&rang=");
                paginator.append(i);
                paginator.append("\">");
                paginator.append(i);
                paginator.append("</a>, ");
                i++;
            }


        }
        return paginator.toString();
    }
                   
}
