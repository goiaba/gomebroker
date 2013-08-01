package br.com.gome.gomebroker.view;

import java.io.Serializable;
import java.util.List;

import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.separator.Separator;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import br.com.gome.gomebroker.business.ItemMenuBC;
import br.com.gome.gomebroker.constant.SecurityConstants;
import br.com.gome.gomebroker.constant.ViewConstants;
import br.com.gome.gomebroker.domain.security.ItemMenu;
import br.com.gome.gomebroker.domain.security.Papel;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@ViewController
@SessionScoped
public class MenuMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private MenuModel model;

	private @Inject	ItemMenuBC itemMenuBC;
	private @Inject ResourceBundle bundle;
	private @Inject	SecurityContext sc;

	public MenuMB() {
	}

	public MenuModel getModel() {

		model = new DefaultMenuModel();

		Papel papelUsuario = (Papel) sc.getUser().getAttribute(SecurityConstants.PAPEL_USUARIO_SESSAO_KEY);
		
//		model = criaSubMenu(itemMenuBC.getItensPaiDeMenu(papelUsuario));
		model = criaSubMenu(null);

		return model;

	}

	private MenuModel criaSubMenu(List<ItemMenu> itensPaisDoMenu) {

		MenuModel model = new DefaultMenuModel();

//		Collections.sort(itensPaisDoMenu);
//
//		for (ItemMenu item : itensPaisDoMenu) {
//
//			if (item.isSubMenu()) {
//
//				Submenu sm = new Submenu();
//				sm.setId(item.getDescricaoKey());
////				sm.setLabel(item.getDescricaoKey());
//				sm.setLabel(bundle.getString(item.getDescricaoKey()));
//
//				constroiSubMenu(item.getFilhos(), sm);
//
//				model.addSubmenu(sm);
//
//			} else {
//
//				MenuItem mi = new MenuItem();
//				mi.setId(item.getDescricaoKey());
////				mi.setValue(item.getDescricaoKey());
//				mi.setValue(bundle.getString(item.getDescricaoKey()));
//				mi.setUrl(item.getUrl());
//
//				model.addMenuItem(mi);
//
//			}
//
//		}
		
		MenuItem logout = new MenuItem();
		logout.setId("loout-menuitem");
		logout.setValue(bundle.getString("main.app.logout.button"));
		logout.setActionExpression(createMethodExpression(ViewConstants.METHOD_EXPRESSION_LOGOUT, null));
		
		model.addMenuItem(logout);
		
		return model;

	}

	private void constroiSubMenu(List<ItemMenu> filhos, Submenu sm) {

		for (ItemMenu item : filhos) {

			if (item.isSubMenu()) {

				Submenu smf = new Submenu();
				smf.setId(item.getDescricaoKey());
//				smf.setLabel(item.getDescricaoKey());
				smf.setLabel(bundle.getString(item.getDescricaoKey()));
				sm.getChildren().add(smf);

				constroiSubMenu(item.getFilhos(), smf);

			} else if (item.isSeparador()) {

				Separator sp = new Separator();
				sp.setId(item.getDescricaoKey());
				
				sm.getChildren().add(sp);
				
			} else {

				MenuItem mi = new MenuItem();
				mi.setId(item.getDescricaoKey());
//				mi.setValue(item.getDescricaoKey());
				mi.setValue(bundle.getString(item.getDescricaoKey()));
				mi.setUrl(item.getUrl());

				sm.getChildren().add(mi);

			}

		}

	}
	
	
	private MethodExpression createMethodExpression(String expression, Class<?> returnType, Class<?>... parameterTypes) {
     
		FacesContext fC = FacesContext.getCurrentInstance();
		ExpressionFactory eF = fC.getApplication().getExpressionFactory();
		
        return eF.createMethodExpression(fC.getELContext(), expression, returnType, parameterTypes);
        
    }
	
//	ItemMenu im1 = new ItemMenu("SM1", "#", false, true, 0);
//	ItemMenu im2 = new ItemMenu("MI1", "#", false, false, 0);
//	ItemMenu im3 = new ItemMenu("MI2", "#", false, false, 0);
//	ItemMenu im4 = new ItemMenu("SM2", "#", false, true, 0);
//	ItemMenu im5 = new ItemMenu("MI3", "#", false, false, 0);
//	ItemMenu im6 = new ItemMenu("SP1", "#", true, false, 0);
//	ItemMenu im7 = new ItemMenu("MI4", "#", false, false, 0);
//	ItemMenu im8 = new ItemMenu("MI5", "#", false, false, 0);
//	ItemMenu im9 = new ItemMenu("SM3", "#", false, true, 0);
//	ItemMenu im10 = new ItemMenu("MI6", "#", false, false, 0);
//	ItemMenu im11 = new ItemMenu("MI7", "#", false, false, 0);
//
//	im1.addFilho(im2);
//	im1.addFilho(im3);
//	im1.addFilho(im4);
//	im4.addFilho(im5);
//	im4.addFilho(im6);
//	im4.addFilho(im7);
//	im1.addFilho(im8);
//	im9.addFilho(im10);
//	im9.addFilho(im11);
//
//	List<ItemMenu> itens = new ArrayList<ItemMenu>();
//	itens.add(im1);
//	itens.add(im9);
//
//	model = criaSubMenu(itens);

}
