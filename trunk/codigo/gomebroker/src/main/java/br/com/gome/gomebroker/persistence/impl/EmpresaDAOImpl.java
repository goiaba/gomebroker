package br.com.gome.gomebroker.persistence.impl;

import javax.persistence.NoResultException;

import br.com.gome.gomebroker.domain.Empresa;

import br.com.gome.gomebroker.persistence.EmpresaDAO;

public class EmpresaDAOImpl extends BaseDAOImpl<Empresa, Long> implements EmpresaDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Empresa findByNomeResumido(String nomeResumido) {

		String query = "SELECT empresa " +
						"FROM Empresa empresa " +
						"WHERE empresa.nome = :nomeResumido " +
						"AND (empresa.dataDesativacao IS NULL OR empresa.dataDesativacao > current_timestamp())";

		try {
			
			Empresa e = getEntityManager()
							.createQuery(query, Empresa.class)
							.setParameter("nomeResumido", nomeResumido)
							.getSingleResult();
			
			return e;
			
		} catch (NoResultException e) {

			return null;
			
		}
		
	}

}
