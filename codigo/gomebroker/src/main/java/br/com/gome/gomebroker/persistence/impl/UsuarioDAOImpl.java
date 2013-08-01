package br.com.gome.gomebroker.persistence.impl;

import javax.persistence.NoResultException;

import br.com.gome.gomebroker.domain.Usuario;
import br.com.gome.gomebroker.persistence.UsuarioDAO;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class UsuarioDAOImpl extends BaseDAOImpl<Usuario, Long> implements UsuarioDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public Usuario autentica(String usuario, String passwd) {

		String query = "SELECT u" +
						" FROM Usuario u " +
						" WHERE u.nome = :nome" +
						" AND u.senha = :senha";
		
		try {
			
			return this.getEntityManager()
							.createQuery(query, Usuario.class)
							.setParameter("nome", usuario)
							.setParameter("senha", passwd)
							.getSingleResult();
			
		} catch (NoResultException e) {

			return null;
			
		}
		
	}

}
