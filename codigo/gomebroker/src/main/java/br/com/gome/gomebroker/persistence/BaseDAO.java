package br.com.gome.gomebroker.persistence;

import java.io.Serializable;
import java.util.List;

import br.com.gome.gomebroker.domain.BaseEntity;

public interface BaseDAO<E extends BaseEntity<I>, I> extends Serializable {

	void persist(E bean);

	E attach(E bean);

	void remove(E bean);
	
	void remove(I id);

	E merge(E bean);
	
	E find(I id);

	List<E> findAll();
	
}
