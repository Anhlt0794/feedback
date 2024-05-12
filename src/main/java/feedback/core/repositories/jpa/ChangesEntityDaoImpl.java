package feedback.core.repositories.jpa;
/**
 * Created by Hien on 5/19/2015.
 */
import java.io.Serializable;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import feedback.core.repositories.EntityChangeRepo;
@Repository("dao")
public class ChangesEntityDaoImpl  implements EntityChangeRepo,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	public EntityManager getEm() {
		return em;
	}
	public void setEm(EntityManager em) {
		this.em = em;
	}
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public <T> Collection<T> queryAll(Class<T> class1) {	
		return em.createQuery("SELECT t FROM " + class1.getSimpleName() + " t").getResultList();
	}
	@Transactional(readOnly = true)
	public <T, E> T getByID(Class<T> class1, E id) {
		return em.find(class1, id);
	}
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public <T, E> Collection<T> findByCriteria(Class<T> class1, Criterion... ts) {
			Session session = (Session) em.getDelegate();
			Criteria criteria = session.createCriteria(class1);
			 for (Criterion c : ts) {
				 criteria.add(c);
		        }
		        return criteria.list();
		
	}
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public <T, E> Collection<T> findByExample(Class<T> class1,
			E exampleInstance, String[] excludeProperty) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(class1);
		Example example =  Example.create(exampleInstance);
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        criteria.add(example);
        return criteria.list();
	}
	@Transactional(readOnly = true)
	public <T, E> T findReference(Class<T> class1, E id) {
		return em.getReference(class1, id);
	}
	@Transactional(readOnly = true)
	public<T>  int countByCriteria(Class<T> class1,Criterion... criterion) {
		Session session = (Session) em.getDelegate();
		Criteria crit = session.createCriteria(class1);
		crit.setProjection(Projections.rowCount());

		for (final Criterion c : criterion) {
			crit.add(c);
		}

		return (Integer) crit.list().get(0);
	}
		
	@Transactional(readOnly = true)
	public <T> void refresh(T t) {
		 em.refresh(t);
	}
	@Transactional
	public <T> T save(T t) {
		T  newRecord = null;
		try {
			  em.persist(t);
			  newRecord = em.merge(t);
			return t;
		} catch (Exception e) {
			System.out.println("loi daoSave");			
		}
		return newRecord;
	}
	@Transactional
	public <T> T update(T t) {
		T  newRecord = null;
		try {
			newRecord= em.merge(t);
		} catch (Exception e) {
			
		}
		
		return	newRecord;
		
	}
	@Transactional
	public <T> void delete(T t) {
		try {
			em.remove(em.merge(t));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public <T> Collection<T> findByCriteria(Class<T> class1, int firstResult,
			int maxResults, Criterion... criterion) {
		Session session = (Session) em.getDelegate();
		Criteria crit = session.createCriteria(class1);

		for ( Criterion c : criterion) {
			crit.add(c);
		}

		if (firstResult > 0) {
			crit.setFirstResult(firstResult);
		}

		if (maxResults > 0) {
			crit.setMaxResults(maxResults);
		}		
		return crit.list();
	}
	
}
