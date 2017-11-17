/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Article;
import entity.User;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.ArticleFacade;
import session.UserFacade;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "Controller",loadOnStartup = 1, urlPatterns = {"/newArticle","/addArticle"})
public class Controller extends HttpServlet {
    @EJB
    private ArticleFacade articleFacade;
    @EJB
    private UserFacade userFacade;

    @Override
    public void init() throws ServletException {
        List<Article> articles = articleFacade.findAll();
        getServletContext().setAttribute("articles", articles);
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        if("/newArticle".equals(path)){
            request.setAttribute("users", userFacade.findAll());
            request.getRequestDispatcher("/NewArticle.jsp").forward(request, response);
        }else if("/addArticle".equals(path)){
            String title = request.getParameter("title");
            String text = request.getParameter("text");
            String authorId = request.getParameter("author");
            Calendar c = new GregorianCalendar();
            Date date = c.getTime();
            User author = userFacade.find(new Long(authorId));
            Article a = new Article(title, text, author, date);
            articleFacade.create(a);
            request.setAttribute("articles", articleFacade.findAll());
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }else{
            List<Article> articles = articleFacade.findAll();
            request.setAttribute("articles", articles);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
