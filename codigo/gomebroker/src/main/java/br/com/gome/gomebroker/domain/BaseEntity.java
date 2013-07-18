package br.com.gome.gomebroker.domain;

import java.io.Serializable;

public interface BaseEntity<I> extends Serializable {

	I getId();
	
	void setId(I id);
	
}
