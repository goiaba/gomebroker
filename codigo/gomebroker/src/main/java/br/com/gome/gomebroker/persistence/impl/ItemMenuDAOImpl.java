package br.com.gome.gomebroker.persistence.impl;

import java.util.List;

import br.com.gome.gomebroker.domain.security.ItemMenu;
import br.com.gome.gomebroker.domain.security.Papel;
import br.com.gome.gomebroker.persistence.ItemMenuDAO;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class ItemMenuDAOImpl extends BaseDAOImpl<ItemMenu, Long> implements ItemMenuDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public List<ItemMenu> findListaItemMenuPai(Papel papel) {

		String query = "SELECT   FROM   WHERE";
		
		return getEntityManager()
					.createQuery(query, ItemMenu.class)
					.setParameter("papel", papel)
					.getResultList();
		
	}

}
