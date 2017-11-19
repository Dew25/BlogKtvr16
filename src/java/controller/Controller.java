/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import interfaces.ActionCommand;
import entity.Article;
import factory.ActionFactory;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import resource.ConfigurationManager;
import resource.MessageManager;
import session.ArticleFacade;
import session.UserFacade;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "Controller",loadOnStartup = 1, urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    public static String redirectPath = null;
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
        String pathInfo = request.getPathInfo();
        String page = null;
        ActionFactory client = new ActionFactory();
        String commandParametr = request.getParameter("command");
        if(commandParametr != null){
            ActionCommand command = client.defineCommand(commandParametr);
            page = command.execute(request);
        }
        if(page != null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }else{
            int[] range = {1,10};
            request.setAttribute("articles", articleFacade.findRange(range));
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("info", MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath()+page);
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
