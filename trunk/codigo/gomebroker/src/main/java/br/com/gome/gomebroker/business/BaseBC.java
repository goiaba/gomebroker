package br.com.gome.gomebroker.business;

import java.io.Serializable;
import java.util.List;

import br.com.gome.gomebroker.domain.BaseEntity;
import br.com.gome.gomebroker.persistence.BaseDAO;

public interface BaseBC<E extends BaseEntity<I>, I, D extends BaseDAO<E, I>> extends Serializable {

	void persist(E bean);
	
	E attach(E bean);
	
	void remove(E bean);
	
	void remove(I id);
	
	E merge(E bean);
	
	E find(I id);
	
	List<E> findAll();
	
}
