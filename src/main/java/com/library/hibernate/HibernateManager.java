package com.library.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by Ali on 2015-05-28.
 */
public class HibernateManager {
    public static Object getMemberInformation(long membershipNumber){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try{
            Member member = (Member) session.load(Member.class , membershipNumber);
            return member;

        }catch (Exception ex){
             return "error";
        }

    }

}
