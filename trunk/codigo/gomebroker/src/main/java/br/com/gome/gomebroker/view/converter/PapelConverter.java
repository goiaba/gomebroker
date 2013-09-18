package br.com.gome.gomebroker.view.converter;

import javax.faces.convert.FacesConverter;
import javax.faces.validator.FacesValidator;

import br.com.gome.gomebroker.business.PapelBC;
import br.com.gome.gomebroker.domain.security.Papel;
import br.com.gome.gomebroker.persistence.PapelDAO;

@FacesConverter(value="papelConverter", forClass=Papel.class)
@FacesValidator(value="papelValidator")
public class PapelConverter extends BaseConverterValidator<Papel, Long, PapelDAO, PapelBC> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4250272517346704594L;

}
