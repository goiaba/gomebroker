package br.com.gome.gomebroker.persistence.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import br.com.gome.gomebroker.domain.security.Papel;
import br.com.gome.gomebroker.domain.security.Recurso;
import br.com.gome.gomebroker.persistence.RecursoDAO;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class RecursoDAOImpl extends BaseDAOImpl<Recurso, Long> implements RecursoDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Recurso> findRecursosDisponiveis(Papel papel) {

		String query = "SELECT recurso " +
						"FROM Papel papel JOIN papel.papelRecurso pr JOIN pr.recurso recurso " +
						"WHERE papel.dataDesativacao > :agora " +
						"AND recurso.dataDesativacao > :agora " +
						"AND papel = :papel";
		
		return getEntityManager()
					.createQuery(query, Recurso.class)
					.setParameter("papel", papel)
					.setParameter("agora", new Date())
					.getResultList();
		
	}

	@Override
	public List<Recurso> findRecursosPorTipo(String... tipoRecursos) {

		StringBuilder strQuery = new StringBuilder("SELECT recurso FROM Recurso recurso");
		
		if (null != tipoRecursos && tipoRecursos.length > 0) {

			Map<String, String> params = new HashMap<String, String>();
			
			strQuery.append(" WHERE recurso.tipo in (");
			
			for (String tipo : tipoRecursos) {
				
				String namedParam = tipo + "_parameter";
				params.put(namedParam, tipo);
				strQuery.append(":" + namedParam + ",");
				
			}
			
			strQuery.deleteCharAt(strQuery.length()-1).append(")");
			
			TypedQuery<Recurso> query = getEntityManager().createQuery(strQuery.toString(), Recurso.class);
			
			for (Map.Entry<String, String> param : params.entrySet()) {
				
				query.setParameter(param.getKey(), param.getValue());
				
			}
			
			return query.getResultList();
			
		}
		
		return getEntityManager()
					.createQuery(strQuery.toString(), Recurso.class)
					.getResultList(); 
		
	}

}
