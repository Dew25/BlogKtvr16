/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import classes.RoleUser;
import entity.Role;
import entity.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jvm
 */
@Stateless
public class RoleFacade extends AbstractFacade<Role> {

    @PersistenceContext(unitName = "BlogKtvr16PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RoleFacade() {
        super(Role.class);
    }
    public List<Role> findUserRoles(User user){
        try {
            List<Role> roles = em.createQuery("SELECT r FROM Role r WHERE r.user = :user")
                .setParameter("user", user)
                .getResultList();
            return roles;
        } catch (Exception e) {
             Logger.getLogger(RoleFacade.class.getName()).log(Level.INFO, "Не удалось найти роли у пользователя", e);
            return null;
        }
        
    }
}
