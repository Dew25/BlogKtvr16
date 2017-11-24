/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.user;

import classes.RoleEnum;
import classes.RoleUser;
import classes.user.ActiveUser;
import classes.user.DeactiveUser;
import classes.user.DeleteRole;
import classes.user.SetRole;
import entity.User;
import interfaces.ActionCommand;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import resource.ConfigurationManager;
import session.UserFacade;

/**
 *
 * @author Melnikov
 */
public class SetRoleCommand implements ActionCommand  {
    
    private UserFacade userFacade;
    
    public SetRoleCommand() {
        Context context;
        try {
            context = new InitialContext();
            this.userFacade = (UserFacade) context.lookup("java:module/UserFacade");
        } catch (NamingException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, "Не удалось найти сессионый бин", ex);
        }
    }

    @Override
    public String execute(HttpServletRequest request) {
        String selectRole = request.getParameter("selectRole");
        String selectUser = request.getParameter("selectUser");
        HttpSession session = request.getSession(false);
        if(session == null){
            String page = ConfigurationManager.getProperty("path.page.login");
            return page;
        }
        User regUser = (User) session.getAttribute("regUser");
        if(regUser == null){
            String page = ConfigurationManager.getProperty("path.page.login");
            return page;
        }

        if(selectRole == null || selectRole.isEmpty()){
            String page = ConfigurationManager.getProperty("path.page.login");
            return page;
        }
        RoleUser ru = new RoleUser();
        String regUserRole = ru.getRole(request);
        if(regUserRole == null ){
            String page = ConfigurationManager.getProperty("path.page.login");
            return page;
        }
        if(!"ADMIN".equals(regUserRole) ){
            String page = ConfigurationManager.getProperty("path.page.login");
            return page;
        }
        boolean done;
        String info;
        if("delete".equals(selectRole)){
            DeleteRole deleteRole = new DeleteRole();
            done = deleteRole.recordToBase(selectUser);
        }else if("activeTrue".equals(selectRole)){
            ActiveUser activeUser = new ActiveUser();
            done = activeUser.recordToBase(selectUser);
        }else if("activeFalse".equals(selectRole)){
            DeactiveUser deactiveUser = new DeactiveUser();
            done = deactiveUser.recordToBase(selectUser);
        }else{
            SetRole setRole = new SetRole();
            done = setRole.recordToBase(selectRole,selectUser);
        }
        if(done){
            request.setAttribute("info","Операция выполнена");
        }else{
            request.setAttribute("info","Операция прервана");
        }
        
        List<User> users = userFacade.findAll();
        Map<User,String>mapUsers=new HashMap<>();
        for (User user : users) {
            String role = ru.getRole(user);
            mapUsers.put(user, role);
        }
        request.setAttribute("mapUsers", mapUsers);
        request.setAttribute("roles", RoleEnum.values());
        String page = ConfigurationManager.getProperty("path.page.admin");
        return page;
    }
    
}
