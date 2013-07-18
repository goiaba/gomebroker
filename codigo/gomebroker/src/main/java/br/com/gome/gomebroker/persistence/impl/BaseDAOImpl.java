package br.com.gome.gomebroker.persistence.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.gome.gomebroker.domain.BaseEntity;
import br.com.gome.gomebroker.persistence.BaseDAO;
import br.gov.frameworkdemoiselle.util.Reflections;

public abstract class BaseDAOImpl<E extends BaseEntity<I>, I> implements BaseDAO<E, I> {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(name="gomebroker-ds")
	private EntityManager entityManager;
	
	private Class<E> entityClass;
	
	public BaseDAOImpl() {
		
		this.entityClass = Reflections.getGenericTypeArgument(this.getClass(), 0);
		
	}
	
	public void persist(final E bean) {
		
		getEntityManager().persist(bean);
		
	}
	
	public E attach(final E bean) {
		
		return getEntityManager().merge(bean);
		
	}
	
	public void remove(final E bean) {
		
		getEntityManager().remove(bean);
		
	}
	
	public void remove(final I id) {

		E entity = getEntityManager().getReference(getEntityClass(), id);
		getEntityManager().remove(entity);
		
	}
	
	public E merge(final E bean) {
		
		return getEntityManager().merge(bean);
		
	}
	
	public E find(I id) {
		
		return getEntityManager().find(getEntityClass(), id);
		
	}
	
	public List<E> findAll() {
		
		String entityName = entityClass.getSimpleName();
		
		String query = "SELECT entity FROM " + entityName + " entity";
		
		TypedQuery<E> typedQuery = getEntityManager().createQuery(query, getEntityClass());
		
		return typedQuery.getResultList();
		
	}

	public Class<E> getEntityClass() {
		return entityClass;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

}
