package br.com.gome.gomebroker.persistence.impl;

import javax.persistence.NoResultException;

import br.com.gome.gomebroker.domain.Ativo;
import br.com.gome.gomebroker.persistence.AtivoDAO;

public class AtivoDAOImpl extends BaseDAOImpl<Ativo, Long> implements AtivoDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Ativo findByCodigoNegociacao(String codigoNegociacao) {

		String query = "SELECT ativo " +
						"FROM Ativo ativo " +
						"WHERE ativo.codigo = :codNegociacao " +
						"AND (ativo.dataDesativacao IS NULL OR ativo.dataDesativacao > current_timestamp())";
		
		try {
			
			Ativo a = getEntityManager()
						.createQuery(query, Ativo.class)
						.setParameter("codNegociacao", codigoNegociacao)
						.getSingleResult();
			
			return a;
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}

}
