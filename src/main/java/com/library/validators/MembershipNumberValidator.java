package com.library.validators;

import com.library.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by Administrator on 6/14/2015.
 */
@FacesValidator("com.library.validators.MembershipNumberValidator")
public class MembershipNumberValidator implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Long membershipNumber= Long.parseLong((String) value);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Member m where m.membership_number=:membership_number");
        query.setParameter( "membership_number" , membershipNumber);
        if( query.uniqueResult() == null){
            tx.commit();
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR , "" , "چنین عضوی در سیستم وجود ندارد"));
        }
        else{
            tx.commit();
        }

    }
}
