package br.com.gome.gomebroker.persistence;

import java.util.List;

import br.com.gome.gomebroker.domain.security.ItemMenu;
import br.com.gome.gomebroker.domain.security.Papel;

public interface ItemMenuDAO extends BaseDAO<ItemMenu, Long> {

	List<ItemMenu> findListaItemMenuPai(Papel papel);

}
