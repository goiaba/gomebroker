package br.com.gome.gomebroker.business.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.gome.gomebroker.business.ItemMenuBC;
import br.com.gome.gomebroker.domain.security.ItemMenu;
import br.com.gome.gomebroker.domain.security.Papel;
import br.com.gome.gomebroker.persistence.ItemMenuDAO;

@Named
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ItemMenuBCImpl extends BaseBCImpl<ItemMenu, Long, ItemMenuDAO> implements ItemMenuBC {

	private static final long serialVersionUID = 1L;
	
	@Inject private ItemMenuDAO itemMenuDao;

	@Override
	public List<ItemMenu> getItensPaiDeMenu(Papel papelUsuario) {

		return itemMenuDao.findListaItemMenuPai(papelUsuario);
		
	}

}
