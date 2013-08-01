package br.com.gome.gomebroker.persistence.impl;

import java.util.Date;
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
						"AND up.dataDesativacao > :agora " +
						"AND papel.dataDesativacao > :agora";
		
		return getEntityManager()
					.createQuery(query, Papel.class)
					.setParameter("usuario", usuario)
					.setParameter("agora", new Date())
					.getResultList();
		
	}

	@Override
	public Papel findPapelPadrao(Usuario usuario) {

		String query = "SELECT papel " +
						"FROM Usuario usuario JOIN usuario.usuarioPapel up JOIN up.papel papel " +
						"WHERE usuario = :usuario " +
						"AND up.padrao = true " +
						"AND up.dataDesativacao > :agora " +
						"AND papel.dataDesativacao > :agora";
		
		try {

			return getEntityManager()
					.createQuery(query, Papel.class)
					.setParameter("usuario", usuario)
					.setParameter("agora", new Date())
					.getSingleResult();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}

}
