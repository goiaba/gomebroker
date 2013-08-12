package br.com.gome.gomebroker.persistence.impl;

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
						"WHERE (papel.dataDesativacao IS NULL OR papel.dataDesativacao > current_timestamp()) " +
						"AND (recurso.dataDesativacao IS NULL OR recurso.dataDesativacao > current_timestamp()) " +
						"AND papel = :papel";
		
		return getEntityManager()
					.createQuery(query, Recurso.class)
					.setParameter("papel", papel)
					.getResultList();
		
	}

	@Override
	public List<Recurso> findRecursosPorTipo(boolean apenasRecursosAtivos, String... tipoRecursos) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder strQuery = new StringBuilder("SELECT recurso FROM Recurso recurso"); 
		boolean whereClause = false;
		
		if (apenasRecursosAtivos) {
		
			strQuery.append(" WHERE (recurso.dataDesativacao IS NULL OR recurso.dataDesativacao > current_timestamp())");
			whereClause = true;
			
		}
		
		if (null != tipoRecursos && tipoRecursos.length > 0) {
		
			if (whereClause) {
				strQuery.append(" AND recurso.tipo in (");
			} else {
				strQuery.append(" WHERE recurso.tipo in (");
			}
			
			for (String tipo : tipoRecursos) {
				
				String namedParam = tipo + "_parameter";
				params.put(namedParam, tipo);
				strQuery.append(":" + namedParam + ",");
				
			}
			
			strQuery.deleteCharAt(strQuery.length()-1).append(")");
			
		}
		
		TypedQuery<Recurso> query = getEntityManager().createQuery(strQuery.toString(), Recurso.class);
		
		for (Map.Entry<String, Object> param : params.entrySet()) {
			
			query.setParameter(param.getKey(), param.getValue());
			
		}
		
		return query.getResultList();
		
	}

	@Override
	public List<Recurso> find(String searchString) {

		String query = "SELECT recurso " +
						"FROM Recurso recurso " +
						"WHERE lower(recurso.nome) like :searchString " +
						"OR lower(recurso.descricao) like :searchString";
		
		return getEntityManager()
					.createQuery(query, Recurso.class)
					.setParameter("searchString", "%" + searchString.toLowerCase() + "%")
					.getResultList();
		
	}

}
