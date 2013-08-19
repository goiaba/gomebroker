package br.com.gome.gomebroker.business.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.slf4j.Logger;

import br.com.gome.gomebroker.business.BaseBC;
import br.com.gome.gomebroker.domain.BaseEntity;
import br.com.gome.gomebroker.persistence.BaseDAO;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.Reflections;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public abstract class BaseBCImpl<E extends BaseEntity<I>, I, D extends BaseDAO<E, I>> implements BaseBC<E, I, D> {
	
	private static final long serialVersionUID = 1L;
	
	@Inject Logger logger;
	@Inject ResourceBundle bundle;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(E bean) {

		try {
			bean.getClass().getMethod("setDataCadastro", Date.class).invoke(bean, new Date());
			logger.info(bundle.getString("core.metodo-datacadastro-executado", bean.getClass().getName()));
		} catch (NoSuchMethodException e) {
			logger.info(bundle.getString("core.metodo-datacadastro-nao-existe", bean.getClass().getName()));
		} catch (Exception e) {
			logger.info(bundle.getString("core.metodo-datacadastro-erro-execucao", bean.getClass().getName()));
		}
			
		Beans.getReference(getDAOInterface()).persist(bean);
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public E attach(E bean) {
		
		return Beans.getReference(getDAOInterface()).merge(bean);
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(E bean) {
		
		Beans.getReference(getDAOInterface()).remove(bean);
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(I id) {
		
		Beans.getReference(getDAOInterface()).remove(id);
		
	};
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public E merge(E bean) {
		
		return Beans.getReference(getDAOInterface()).merge(bean);
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public E mergeInNewTransaction(E bean) {

		return merge(bean);
		
	}
	
	public E find(I id) {
		
		return Beans.getReference(getDAOInterface()).find(id);
		
	}
	
	public List<E> findAll() {
		
		return Beans.getReference(getDAOInterface()).findAll();
		
	}
	
	protected Class<D> getDAOInterface() {
      
      Class<D> clazz = Reflections.getGenericTypeArgument(this.getClass(), 2);
      
      return clazz;
      
  }

}
