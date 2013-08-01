package br.com.gome.gomebroker.business;

import java.util.List;

import br.com.gome.gomebroker.domain.security.ItemMenu;
import br.com.gome.gomebroker.domain.security.Papel;
import br.com.gome.gomebroker.persistence.ItemMenuDAO;

public interface ItemMenuBC extends BaseBC<ItemMenu, Long, ItemMenuDAO> {

	List<ItemMenu> getItensPaiDeMenu(Papel papelUsuario);

}
