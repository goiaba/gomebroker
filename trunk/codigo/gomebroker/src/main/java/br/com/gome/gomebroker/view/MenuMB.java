package br.com.gome.gomebroker.view;

import java.io.Serializable;
import java.util.List;

import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import br.com.gome.gomebroker.constant.ViewConstants;
import br.com.gome.gomebroker.domain.security.ItemMenu;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

//	FIXME: Modificar para trazer menus do banco, de acordo com o usuário logado!

@ViewController
@SessionScoped
public class MenuMB implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private MenuModel model;

	private @Inject ResourceBundle bundle;

	public MenuMB() {
	}

	public MenuModel getModel() {

		model = criaSubMenu(null);

		return model;

	}

	private MenuModel criaSubMenu(List<ItemMenu> itensPaisDoMenu) {

		MenuModel model = new DefaultMenuModel();

		Submenu importacao = new Submenu();
		importacao.setId("importacao-submenu");
		importacao.setLabel(bundle.getString("view.menu.submenu.importacao"));
		
		MenuItem importacaoBovespa = new MenuItem();
		importacaoBovespa.setId("importacao-bovespa-menuitem");
		importacaoBovespa.setValue(bundle.getString("view.menu.submenu.importacao.bovespa"));
		importacaoBovespa.setUrl("/pages/importacaodados/bovespa.jsf");
		
		importacao.getChildren().add(importacaoBovespa);
		
		model.addSubmenu(importacao);

//		===========================================================================================================
		
		Submenu config = new Submenu();
		config.setId("config-submenu");
		config.setLabel(bundle.getString("view.menu.submenu.config"));
		
		Submenu seguranca = new Submenu();
		seguranca.setId("config-seg-submenu");
		seguranca.setLabel(bundle.getString("view.menu.submenu.config.seguranca"));
		
		MenuItem recurso = new MenuItem();
		recurso.setId("config-seg-recurso-menuitem");
		recurso.setValue(bundle.getString("view.menu.submenu.config.seguranca.recurso"));
		recurso.setUrl("/pages/security/recurso/list.jsf");

		seguranca.getChildren().add(recurso);
		config.getChildren().add(seguranca);
		
		model.addSubmenu(config);

//		===========================================================================================================
		
		MenuItem logout = new MenuItem();
		logout.setId("logout-menuitem");
		logout.setValue(bundle.getString("view.menu.menuitem.logout"));
		logout.setActionExpression(createMethodExpression(ViewConstants.METHOD_EXPRESSION_LOGOUT, null));
		
		model.addMenuItem(logout);

//		===========================================================================================================
		
		return model;

	}
	
	private MethodExpression createMethodExpression(String expression, Class<?> returnType, Class<?>... parameterTypes) {
     
		FacesContext fC = FacesContext.getCurrentInstance();
		ExpressionFactory eF = fC.getApplication().getExpressionFactory();
		
        return eF.createMethodExpression(fC.getELContext(), expression, returnType, parameterTypes);
        
    }
	
}
