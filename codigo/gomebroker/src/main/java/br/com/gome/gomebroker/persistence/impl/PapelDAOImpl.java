package br.com.gome.gomebroker.persistence.impl;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.gome.gomebroker.domain.Usuario;
import br.com.gome.gomebroker.domain.security.Papel;
import br.com.gome.gomebroker.persistence.PapelDAO;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class PapelDAOImpl extends BaseDAOImpl<Papel, Long> implements PapelDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Papel> findPapeisValidos(Usuario usuario) {

		String query = "SELECT papel " +
						"FROM Usuario usuario JOIN usuario.usuarioPapel up JOIN up.papel papel " +
						"WHERE usuario = :usuario " +
						"AND (up.dataDesativacao IS NULL OR up.dataDesativacao > current_timestamp()) " +
						"AND (papel.dataDesativacao IS NULL OR papel.dataDesativacao > current_timestamp())";
		
		return getEntityManager()
					.createQuery(query, Papel.class)
					.setParameter("usuario", usuario)
					.getResultList();
		
	}

	@Override
	public Papel findPapelPadrao(Usuario usuario) {

		String query = "SELECT papel " +
						"FROM Usuario usuario JOIN usuario.usuarioPapel up JOIN up.papel papel " +
						"WHERE usuario = :usuario " +
						"AND up.padrao = true " +
						"AND (up.dataDesativacao IS NULL OR up.dataDesativacao > current_timestamp()) " +
						"AND (papel.dataDesativacao IS NULL OR papel.dataDesativacao > current_timestamp())";
		
		try {

			return getEntityManager()
					.createQuery(query, Papel.class)
					.setParameter("usuario", usuario)
					.getSingleResult();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}

}
