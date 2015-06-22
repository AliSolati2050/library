package com.library.managedbeans;

import com.library.hibernate.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.primefaces.context.RequestContext;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Ali on 2015-05-27.
 */
@ManagedBean(name = "memberManagedBean")
@ViewScoped
public class MemberManagedBean implements Serializable {
    static Boolean status= false;
    String name;
    String family;
    String code_melli;
    String membershipdate;
    String address;
    String memberShipNumber;
    public MemberManagedBean(){

    }

    UIComponent memberRegisterButton;
    UIComponent memberViewButton;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getCode_melli() {
        return code_melli;
    }

    public void setCode_melli(String code_melli) {
        this.code_melli = code_melli;
    }

    public String getMembershipdate() {
        return membershipdate;
    }

    public void setMembershipdate(String membershipdate) {
        this.membershipdate = membershipdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UIComponent getMemberRegisterButton() {
        return memberRegisterButton;
    }

    public void setMemberRegisterButton(UIComponent memberRegisterButton) {
        this.memberRegisterButton = memberRegisterButton;
    }

    public String getMemberShipNumber() {
        return memberShipNumber;
    }

    public void setMemberShipNumber(String memberShipNumber) {
        this.memberShipNumber = memberShipNumber;
    }

    public UIComponent getMemberViewButton() {
        return memberViewButton;
    }

    public void setMemberViewButton(UIComponent memberViewButton) {
        this.memberViewButton = memberViewButton;
    }

    public void memberRegister(){
        FacesContext context = FacesContext.getCurrentInstance();
        Member member = new Member(code_melli , name , family , Calendar.getInstance().getTime(), address);

        Book book = new Book();
        book.setAuthor("solati");
        book.setName("computer");
        book.setRecord_date(Calendar.getInstance().getTime());
       // member.setBorrowed_books(book_borrows);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try{
            session.persist(member);
            tx.commit();
            reset();
            context.addMessage(memberRegisterButton.getClientId(context) , new FacesMessage( "عضو جدید با موفقیت به سیستم اضافه شد" + "<br/>" + "شماره عضویت برابر است با "+ String.valueOf(member.getMembership_number())));
        }catch (Exception ex){
            tx.rollback();

        }

    }
    void reset(){
        setName(null);
        setFamily(null);
        setCode_melli(null);
        setMembershipdate(null);
        setAddress(null);
        setMemberShipNumber(null);
    }
    public void show(){
        FacesContext context = FacesContext.getCurrentInstance();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try{
            Member member = (Member)session.get(Member.class, Long.parseLong(memberShipNumber));


                tx.commit();
                Map<String, Object> properties = new HashMap<String , Object>();
                properties.put("modal" , true);
                properties.put("height" , 600);
                properties.put("width" , 900);
               HttpSession httpSession = (HttpSession) context.getExternalContext().getSession(false);
                httpSession.setAttribute("member" , member);
                setMemberShipNumber(null);
                RequestContext.getCurrentInstance().openDialog("borrow" , properties , null);




        }catch (Exception ex){
            tx.rollback();
        }
    }
}
