/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Comment;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Melnikov
 */
@Stateless
public class CommentFacade extends AbstractFacade<Comment> {

    @PersistenceContext(unitName = "BlogKtvr16PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommentFacade() {
        super(Comment.class);
    }

    public List<Comment> findByArticle(Long id) {
        try {
            return em.createQuery("SELECT c FROM Comment c WHERE c.article.id=:id")
                .setParameter("id", id)
                .getResultList();
        } catch (Exception e) {
            Logger.getLogger(CommentFacade.class.getName()).log(Level.SEVERE, "Не удалось найти коментариев", e);
            return null;
        }
        
    }
    
}
