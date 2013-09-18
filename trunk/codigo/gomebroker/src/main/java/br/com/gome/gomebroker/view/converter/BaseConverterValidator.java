/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.gome.gomebroker.view.converter;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang.StringUtils;

import br.com.gome.gomebroker.business.BaseBC;
import br.com.gome.gomebroker.domain.BaseEntity;
import br.com.gome.gomebroker.persistence.BaseDAO;
import br.com.gome.gomebroker.util.StringConverter;
import br.gov.frameworkdemoiselle.util.Beans;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public abstract class BaseConverterValidator<T extends BaseEntity<I>, I, D extends BaseDAO<T, I>, U extends BaseBC<T, I, D>> implements Serializable, Converter, Validator {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7111479799087129874L;
	
	private Class<U> clazz;
	private Class<I> clazzId;

    public BaseConverterValidator() {

    	this.clazzId = (Class<I>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        this.clazz = (Class<U>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[3];

    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String submittedValue) {

        if (StringUtils.isEmpty(submittedValue)) {

            return null;

        }

        try {

            return Beans.getReference(clazz).find(StringConverter.convert(submittedValue, clazzId));

        } catch (NumberFormatException e) {

            Logger.getLogger(clazz.getName()).log(Level.SEVERE, "BCV.getAsObject", e);
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Conversion Error"));

        }

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {

        if (null == o || !(o instanceof BaseEntity<?>)) {

            return null;
            
        }

        return ((T) o).getId() != null ? ((T) o).getId().toString() : null;

    }


    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {

        ((UIInput) uic).setValid(true);

    }

}
