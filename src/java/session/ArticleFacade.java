/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Article;
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
public class ArticleFacade extends AbstractFacade<Article> {

    @PersistenceContext(unitName = "BlogKtvr16PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArticleFacade() {
        super(Article.class);
    }
    
    public List<Article> findActiveArticleAll(int[] range){
        try {
            return em.createQuery("SELECT a FROM Article a WHERE a.active=:active AND a.id>=:min AND a.id<=:max")
                    .setParameter("min", range[0])
                    .setParameter("max", range[1])
                    .setParameter("active", true)
                    .getResultList();
        } catch (Exception ex) {
            Logger.getLogger(ArticleFacade.class.getName()).log(Level.INFO, "Не удалось найти диапазон активных статей", ex);
            return null;
        }
    }
    
}
