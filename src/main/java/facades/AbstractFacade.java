package facades;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Henning
 * @param <T>
 */
public abstract class AbstractFacade<T> {

    private final EntityManagerFactory emf;
    private final Class<T> ENTITY_CLASS;

    public AbstractFacade(Class<T> entityClass, EntityManagerFactory emf) {
        this.ENTITY_CLASS = entityClass;
        this.emf = emf;
    }

    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public T create(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return entity;
    }

    public T edit(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return entity;
    }

    public T remove(long id) {
        EntityManager em = getEntityManager();
        T entity = null;
        try {
            em.getTransaction().begin();
            entity = em.find(ENTITY_CLASS, id);
            em.remove(entity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return entity;
    }

    public T find(Object id) {
        return getEntityManager().find(ENTITY_CLASS, id);
    }

    public List<T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(ENTITY_CLASS));
        return getEntityManager().createQuery(cq).getResultList();
    }
}
