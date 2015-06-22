package com.library.validators;

import com.library.hibernate.HibernateUtil;
import com.library.hibernate.Member;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.List;

/**
 * Created by Administrator on 6/12/2015.
 */
@FacesValidator("com.library.validators.CodeMelliValidator")
public class CodeMelliValidator implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String codeMelli =(String) value;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from Member m where m.code_melli=:code_melli");
            query.setParameter( "code_melli" , codeMelli);
            if( query.uniqueResult() != null){
                 tx.commit();
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR , "" , "کد ملی نباید تکراری باشد"));
            }
        else{
                tx.commit();
            }

    }
}
