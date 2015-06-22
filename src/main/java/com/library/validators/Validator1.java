package com.library.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by Administrator on 6/12/2015.
 */
@FacesValidator("com.library.validators.validator1")
public class Validator1 implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String str = value.toString();
        if (str.contains("ali")) throw new ValidatorException(new FacesMessage("ali"));

    }
}
