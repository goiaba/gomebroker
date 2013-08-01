package br.com.gome.gomebroker.view.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@FacesConverter("dateConverter")
public class DateConverter implements Converter {

	private static final String DATE_PATTERN = "dd/MM/yyyy";
	
	private Logger logger = Beans.getReference(Logger.class);
	private ResourceBundle bundle = Beans.getReference(ResourceBundle.class);

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		
		if (null == value || value.length() == 0) {
			
			return null;
			
		}
		
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
			return sdf.parse(value);
			
		} catch (ParseException e) {
			
			logger.error(bundle.getString("view.converter.parse-date-error"));			
			return null;
			
		}
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value instanceof Date) {
			
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
			return sdf.format(value);
			
		}
		
		logger.error(bundle.getString("view.converter.object-type-error"));
		return null;
		
	}

}
